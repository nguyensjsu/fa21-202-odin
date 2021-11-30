import java.util.ArrayList;
// Size of cells
int cellSize = 10;

// Keep track if we are in search state
boolean found = false;

// Variables for timer
int interval = 0;
int lastRecordedTime = 0;

color green = color(0, 200, 0);
color black = color(10,10,10);
color red = color(200, 0, 0);

int displayWidth = 840;
int displayHeight = 460;

Keyboard keyboard;
MainDisplay mainDisplay;
ControlsDisplay controlsDisplay;
GridDisplay gridDisplay;

Button startSearchButton;
Button endSearchButton;
Button clearGridButton;
Button drawWallsButton;

Button selectBFSButton;
Button selectDFSButton;
Button selectDijkstraButton;


Label controlsLabel;
Label searchAlgorithmLabel;
Label currentAlgoLabel;

Button setStartPositionButton;
Button setEndPositionButton;

AlgorithmStateMachine algStateMachine;
SearchStateMachine searchStateMachine;

IButtonCommand startCommand = new ButtonCommand();
IButtonCommand stopCommand = new ButtonCommand();
IButtonCommand clearGridCommand = new ButtonCommand();
IButtonCommand drawWallsCommand = new ButtonCommand();
IButtonCommand bfsCommand = new ButtonCommand();
IButtonCommand dfsCommand = new ButtonCommand();
IButtonCommand dijkstraCommand = new ButtonCommand();
IButtonCommand setStartCommand = new ButtonCommand();
IButtonCommand setEndCommand = new ButtonCommand();

void setup() {
  size (displayWidth, displayHeight);
  pixelDensity(2);
  
  int gridWidth = int(width*0.70);
  int gridHeight = height;
  int cellWidth = 10;
  int cellHeight = 10;
  int cellSize = 10;

  int buttonHeight = 25;
  int label_y_position = 20;
  int button_row_1_y_position = 40;
  int button_row_2_y_position = 67;
  int algorithms_y_label = 140;

  // simpification call for commands setup
  setupCommands();
  
  searchStateMachine = new SearchStateMachine();

  algStateMachine = new AlgorithmStateMachine();

  // make a display with a grid that takes up 70 percent of the main display 
  gridDisplay = new GridDisplay(this, gridWidth, gridHeight, cellWidth, cellHeight, 0, 0);
  // make a display that has width 30 percent of the main display
  controlsDisplay = new ControlsDisplay(this, int(width*0.30), height, gridWidth, 0);

  //@TODO: Implement command pattern to set a custom command for each button
  controlsLabel = new Label(this, "Controls Panel", controlsDisplay, label_y_position, ControlsDisplay.CENTER);

  startSearchButton = new Button(this, "Start", controlsDisplay, startCommand, Button.SIZE_HALF, buttonHeight, button_row_1_y_position, ControlsDisplay.LEFT);
  endSearchButton = new Button(this, "Stop", controlsDisplay, stopCommand, Button.SIZE_HALF, buttonHeight, button_row_1_y_position, ControlsDisplay.RIGHT);
  clearGridButton = new Button(this, "Clear Grid", controlsDisplay, clearGridCommand, Button.SIZE_HALF, buttonHeight, button_row_2_y_position, ControlsDisplay.LEFT);
  drawWallsButton = new Button(this, "Draw Walls", controlsDisplay, drawWallsCommand, Button.SIZE_HALF, buttonHeight, button_row_2_y_position, ControlsDisplay.RIGHT);

  currentAlgoLabel = new Label(this, "Algorithm: ", controlsDisplay, algorithms_y_label, ControlsDisplay.LEFT);

  // This label should reflect which search algorithm is currently set
  searchAlgorithmLabel = new Label(this, "BFS", controlsDisplay, algorithms_y_label, ControlsDisplay.CENTER);

  selectBFSButton = new Button(this, "BFS", controlsDisplay, bfsCommand, Button.SIZE_HALF, buttonHeight, 150, ControlsDisplay.LEFT);
  selectDFSButton = new Button(this, "DFS", controlsDisplay, dfsCommand, Button.SIZE_HALF, buttonHeight, 150, ControlsDisplay.RIGHT);
  selectDijkstraButton = new Button(this, "Dijkstra's", controlsDisplay, dijkstraCommand, Button.SIZE_HALF, buttonHeight, 177, ControlsDisplay.LEFT);

  setStartPositionButton = new Button(this, "Set Start Position", controlsDisplay, setStartCommand, Button.SIZE_HALF, buttonHeight, 245, ControlsDisplay.LEFT);
  setEndPositionButton = new Button(this, "Set End Position", controlsDisplay, setEndCommand, Button.SIZE_HALF, buttonHeight, 245, ControlsDisplay.RIGHT);

  mainDisplay = new MainDisplay(width, height, 0, 0);
  keyboard = new Keyboard();

  controlsDisplay.addSubComponent((IDisplayComponent)controlsLabel);
  controlsDisplay.addSubComponent((IDisplayComponent)startSearchButton);
  controlsDisplay.addSubComponent((IDisplayComponent)endSearchButton);
  controlsDisplay.addSubComponent((IDisplayComponent)clearGridButton);
  controlsDisplay.addSubComponent((IDisplayComponent)drawWallsButton);
  controlsDisplay.addSubComponent((IDisplayComponent)currentAlgoLabel);
  controlsDisplay.addSubComponent((IDisplayComponent)searchAlgorithmLabel);
  controlsDisplay.addSubComponent((IDisplayComponent)selectBFSButton);
  controlsDisplay.addSubComponent((IDisplayComponent)selectDFSButton);
  controlsDisplay.addSubComponent((IDisplayComponent)selectDijkstraButton);
  controlsDisplay.addSubComponent((IDisplayComponent)setStartPositionButton);
  controlsDisplay.addSubComponent((IDisplayComponent)setEndPositionButton);

  mainDisplay.addSubComponent((IDisplayComponent)gridDisplay);
  mainDisplay.addSubComponent((IDisplayComponent)controlsDisplay);

  keyboard.attach((IKeyboardObserver) mainDisplay);

}


void draw() {

  // @Todo: Need to refactor this to make use of something like the state pattern
  if (millis()-lastRecordedTime > interval) {
    lastRecordedTime = millis();
    
    if (searchStateMachine.getCurrentState() instanceof RunningSearchState) {
      found = search(gridDisplay.getStartX(), gridDisplay.getStartY(), gridDisplay.getEndX(), gridDisplay.getEndY());
    }
    if (found) {
      searchStateMachine.setStateStopped();
      interval = 0;
    }
  }
  // draw main display and all child displays
  mainDisplay.draw();

}

void mouseDragged() {
  mainDisplay.click(mouseX,mouseY);
}

void mousePressed() {
  mainDisplay.click(mouseX,mouseY);
}

void mouseClicked() {
  mainDisplay.click(mouseX, mouseY);
}

void keyPressed() {
  keyboard.keyPressEvent(key);
}

boolean search(int startX, int startY, int endX, int endY) {
  return algStateMachine.getCurrentStrategy().search(gridDisplay.getGrid(), startX, startY, endX, endY);
}

void startSearch() {
  searchStateMachine.setStateRunning();
  interval = 50;
}

void stopSearch() {
  searchStateMachine.setStateStopped();
}

void resetSearch(){
  algStateMachine.getCurrentStrategy().reset();
}

void setupCommands() {
// setup command for each button
  bfsCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
    if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
      gridDisplay.clearGrid();
      resetSearch();
      algStateMachine.setAlgorithm(SearchAlgorithm.BFS);
      searchAlgorithmLabel.setName("BFS");
    }
   } 
  });

  dfsCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
      if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
        gridDisplay.clearGrid();
        resetSearch();
        algStateMachine.setAlgorithm(SearchAlgorithm.DFS);
        searchAlgorithmLabel.setName("DFS");
      }
   } 
  });

  dijkstraCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
       if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
          gridDisplay.clearGrid();
          resetSearch();
          algStateMachine.setAlgorithm(SearchAlgorithm.Dijkstra);
          searchAlgorithmLabel.setName("Dijkstra");
       }
   } 
  });

  startCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
      gridDisplay.stopDrawingWalls();
      startSearch();
   } 
  });

  stopCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
        stopSearch();
   } 
  });

  clearGridCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
    if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
       gridDisplay.clearGrid();
       resetSearch();
    }
   } 
  });

  drawWallsCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
     if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState))
       gridDisplay.startDrawingWalls();
   } 
  });

   setStartCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){ 
     if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
       gridDisplay.clearGrid();
       resetSearch();
       gridDisplay.setStart();
     }
   } 
  });

   setEndCommand.setReceiver( new IButtonReceiver() {
   public void doAction(){
     if (!(searchStateMachine.getCurrentState() instanceof RunningSearchState)) {
       gridDisplay.clearGrid();
       resetSearch();
       gridDisplay.setEnd();
     }
   } 
  });

}