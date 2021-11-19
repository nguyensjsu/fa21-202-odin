
// Demo of A* algorithm


/**
 * Game of Life
 * by Joan Soler-Adillon.
 *
 * Press SPACE BAR to pause and change the cell's values 
 * with the mouse. On pause, click to activate/deactivate 
 * cells. Press 'R' to randomly reset the cells' grid. 
 * Press 'C' to clear the cells' grid. The original Game 
 * of Life was created by John Conway in 1970.
 */

import java.util.ArrayList;

// Size of cells
int cellSize = 10;

color green = color(0, 200, 0);
color black = color(0);

int[][] board; 

Keyboard keyboard;
MainDisplay mainDisplay;
MenuItems menuItems;
GridDisplay gridDisplay;

void setup() {
  size (640, 360);

  // Instantiate arrays 
  board = new int[width/cellSize][height/cellSize];
  keyboard = new Keyboard();
  mainDisplay = new MainDisplay();

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

  //Draw grid
  for (int x=0; x<width/cellSize; x++) {
    for (int y=0; y<height/cellSize; y++) {
      if (board[x][y] == 1) fill(green);
      else fill(black);
      rect (x*cellSize, y*cellSize, cellSize, cellSize);
    }
  }
}

void mouseDragged() {
  // Map and avoid out of bound errors
  int x = int(map(mouseX, 0, width, 0, width/cellSize));
  x = constrain(x, 0, width/cellSize-1);
  int y = int(map(mouseY, 0, height, 0, height/cellSize));
  y = constrain(y, 0, height/cellSize-1);

  board[x][y] = 1;

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