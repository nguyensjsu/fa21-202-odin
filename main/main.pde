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

void setup() {
  size (displayWidth, displayHeight);
  
  // make a display with a grid that takes up 70 percent of the main display 
  int gridWidth = int(width*0.70);
  int gridHeight = height;
  int cellWidth = 10;
  int cellHeight = 10;
  int cellSize = 10;
  gridDisplay = new GridDisplay(gridWidth, gridHeight, cellWidth, cellHeight);
  gridDisplay.setGraphicsElement(createGraphics(gridDisplay.getWidth(), gridDisplay.getHeight(), JAVA2D));

  // make a display that has width 30 percent of the main display
  controlsDisplay = new ControlsDisplay(gridWidth, int(width*0.30), height);
  controlsDisplay.setGraphicsElement(createGraphics(controlsDisplay.getWidth(), controlsDisplay.getHeight(), JAVA2D));

  mainDisplay = new MainDisplay();
  keyboard = new Keyboard();

  mainDisplay.addSubComponent((IDisplayComponent)gridDisplay);
  mainDisplay.addSubComponent((IDisplayComponent)controlsDisplay);

  keyboard.attach((IKeyboardObserver) mainDisplay);

  // searchStrategy = new DepthFirstSearch();
  searchStrategy = new BreadthFirstSearch();

}

void draw() {

  // draw the grid and the control panel on the main display
  image(gridDisplay.getGraphicsElement(), 0, 0, gridDisplay.getWidth(), gridDisplay.getHeight());
  image(controlsDisplay.getGraphicsElement(), gridDisplay.getWidth(), 0, controlsDisplay.getWidth(), controlsDisplay.getHeight());

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

    mainDisplay.draw();
  }

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