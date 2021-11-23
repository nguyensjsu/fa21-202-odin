import java.util.ArrayList;
import g4p_controls.*;
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

Button setStartPositionButton;
Button setEndPositionButton;

void setup() {
  size (displayWidth, displayHeight);
  
  int gridWidth = int(width*0.70);
  int gridHeight = height;
  int cellWidth = 10;
  int cellHeight = 10;
  int cellSize = 10;
  int buttonHeight = 25;

  // make a display with a grid that takes up 70 percent of the main display 
  gridDisplay = new GridDisplay(this, gridWidth, gridHeight, cellWidth, cellHeight, 0, 0);
  // make a display that has width 30 percent of the main display
  controlsDisplay = new ControlsDisplay(this, int(width*0.30), height, gridWidth, 0);

  //@TODO: Implement command pattern to set a custom command for each button
  setStartPositionButton = new Button(this, "Select Start Position", controlsDisplay, (int)(controlsDisplay.getWidth()*0.9), buttonHeight, 30);
  setEndPositionButton = new Button(this, "Select End Position", controlsDisplay, (int)(controlsDisplay.getWidth()*0.9), buttonHeight, 65);

  mainDisplay = new MainDisplay(width, height, 0, 0);
  keyboard = new Keyboard();

  controlsDisplay.addSubComponent((IDisplayComponent)setStartPositionButton);
  controlsDisplay.addSubComponent((IDisplayComponent)setEndPositionButton);

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

public PApplet getMain() {
  return this;
}
