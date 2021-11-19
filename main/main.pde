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
color black = color(0);
color red = color(200, 0, 0);

int[][] board; 

Keyboard keyboard;
MainDisplay mainDisplay;
MenuItems menuItems;
GridDisplay gridDisplay;
ISearchStrategy searchStrategy;

void setup() {
  size (640, 360);

  // Instantiate arrays 
  board = new int[width/cellSize][height/cellSize];
  keyboard = new Keyboard();
  mainDisplay = new MainDisplay();
  // searchStrategy = new DepthFirstSearch();
  searchStrategy = new BreadthFirstSearch();

  mainDisplay.addSubComponent((IDisplayComponent)(new GridDisplay()));
  mainDisplay.addSubComponent((IDisplayComponent)(new MenuItems()));

  keyboard.attach((IKeyboardObserver) mainDisplay);

  // This stroke will draw the background grid
  stroke(48);

  noSmooth();

  // Fill in black in case cells don't cover all the windows
  background(0); 
}


void draw() {
  // Draw grid
  // @Todo: Need to refactor this to make use of something like the state pattern
  if (millis()-lastRecordedTime > interval) {
    lastRecordedTime = millis();
    if (searchState) {
      found = search(0, 0, width/cellSize-1, height/cellSize-1);
    }
    if (found) {
      searchState = false;
      interval = 0;
    }
    for (int x=0; x<width/cellSize; x++) {
      for (int y=0; y<height/cellSize; y++) {
        switch (board[x][y]) {
          case 0:
            fill(black);
            break;
          case 1:
            fill(green);
            break;
          case 2:
            fill(red);
            break;
          case 3:
          
        }
        rect (x*cellSize, y*cellSize, cellSize, cellSize);
      }
    }
  }
}

void mouseDragged() {
  // Map and avoid out of bound errors
  int x = int(map(mouseX, 0, width, 0, width/cellSize));
  x = constrain(x, 0, width/cellSize-1);
  int y = int(map(mouseY, 0, height, 0, height/cellSize));
  y = constrain(y, 0, height/cellSize-1);

  board[x][y] = 2;

  mainDisplay.click(mouseX,mouseY);
}

void mousePressed() {
  mainDisplay.click(mouseX,mouseY);
}

void mouseClicked() {
  mainDisplay.click(mouseX, mouseY);
}

void keyPressed() {
  // search(0, 0, width/cellSize-1, height/cellSize-1);
  searchState = true;
  interval = 50;
  keyboard.keyPressEvent(key);
}

void changeStrategy(ISearchStrategy strategy) {
  searchStrategy = strategy;
}

boolean search(int startX, int startY, int endX, int endY) {
  return searchStrategy.search(board, startX, startY, endX, endY);
}