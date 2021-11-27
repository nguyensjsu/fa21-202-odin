import java.util.ArrayList;
// Size of cells
int cellSize = 10;

// Keep track if we are in search state
boolean searchState = false;
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
ISearchStrategy searchStrategy;

Button startSearchButton;
Button endSearchButton;
Button clearGridButton;

Button selectBFSButton;
Button selectDFSButton;
Button selectDijkstraButton;

Label controlsLabel;
Label searchAlgorithmLabel;
Label currentAlgoLabel;

Button setStartPositionButton;
Button setEndPositionButton;

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

  // make a display with a grid that takes up 70 percent of the main display 
  gridDisplay = new GridDisplay(this, gridWidth, gridHeight, cellWidth, cellHeight, 0, 0);
  // make a display that has width 30 percent of the main display
  controlsDisplay = new ControlsDisplay(this, int(width*0.30), height, gridWidth, 0);

  //@TODO: Implement command pattern to set a custom command for each button
  controlsLabel = new Label(this, "Controls Panel", controlsDisplay, label_y_position, ControlsDisplay.CENTER);

  startSearchButton = new Button(this, "Start", controlsDisplay, Button.SIZE_HALF, buttonHeight, button_row_1_y_position, ControlsDisplay.LEFT);
  endSearchButton = new Button(this, "Stop", controlsDisplay, Button.SIZE_HALF, buttonHeight, button_row_1_y_position, ControlsDisplay.RIGHT);
  clearGridButton = new Button(this, "Clear Grid", controlsDisplay, Button.SIZE_FULL, buttonHeight, button_row_2_y_position, ControlsDisplay.LEFT);

  currentAlgoLabel = new Label(this, "Algorithm: ", controlsDisplay, algorithms_y_label, ControlsDisplay.LEFT);

  // This label should reflect which search algorithm is currently set
  searchAlgorithmLabel = new Label(this, "BFS", controlsDisplay, algorithms_y_label, ControlsDisplay.CENTER);

  selectBFSButton = new Button(this, "BFS", controlsDisplay, Button.SIZE_HALF, buttonHeight, 150, ControlsDisplay.LEFT);
  selectDFSButton = new Button(this, "DFS", controlsDisplay, Button.SIZE_HALF, buttonHeight, 150, ControlsDisplay.RIGHT);
  selectDijkstraButton = new Button(this, "Dijkstra's", controlsDisplay, Button.SIZE_HALF, buttonHeight, 177, ControlsDisplay.LEFT);

  setStartPositionButton = new Button(this, "Set Start Position", controlsDisplay, Button.SIZE_HALF, buttonHeight, 245, ControlsDisplay.RIGHT);
  setEndPositionButton = new Button(this, "Set End Position", controlsDisplay, Button.SIZE_HALF, buttonHeight, 245, ControlsDisplay.LEFT);


  mainDisplay = new MainDisplay(width, height, 0, 0);
  keyboard = new Keyboard();

  controlsDisplay.addSubComponent((IDisplayComponent)controlsLabel);
  controlsDisplay.addSubComponent((IDisplayComponent)startSearchButton);
  controlsDisplay.addSubComponent((IDisplayComponent)endSearchButton);
  controlsDisplay.addSubComponent((IDisplayComponent)clearGridButton);
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

  //searchStrategy = new DepthFirstSearch();
  //searchStrategy = new BreadthFirstSearch();
  searchStrategy = new Dijkstra();

}

void draw() {

  // @Todo: Need to refactor this to make use of something like the state pattern
  if (millis()-lastRecordedTime > interval) {
    lastRecordedTime = millis();
    
    if (searchState) {
      found = search(0, 0, ((gridDisplay.getWidth())/gridDisplay.getWidth())-10, (gridDisplay.getHeight()/gridDisplay.getHeight())-10);
    }
    if (found) {
      searchState = false;
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
  searchState = true;
  interval = 50;
  keyboard.keyPressEvent(key);
}

void changeStrategy(ISearchStrategy strategy) {
  searchStrategy = strategy;
}

boolean search(int startX, int startY, int endX, int endY) {
  return searchStrategy.search(gridDisplay.getGrid(), startX, startY, endX, endY);
}

