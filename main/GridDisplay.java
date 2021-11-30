import processing.core.PGraphics;
import processing.core.PApplet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class GridDisplay implements IDisplayComponent, IClickEventHandler {

    public static final int WALL_CELL = 2;
    public static final int EMPTY_CELL = 0;
    public static final int VISITED_CELL = 1;
    public static final int START_CELL = 3;
    public static final int END_CELL = 4;

    private HashMap<Integer, ArrayList<Integer>> stateToColorMap;
  
    IClickEventHandler chain;
    private PGraphics graphics;
    private PApplet main;

    private boolean settingStartLocation, settingEndLocation, drawingState, stoppedState;
    private int width, height, cellWidth, cellHeight, mouseX, mouseY, startX, startY, endX, endY;
    int[][] grid;
    
    public GridDisplay(PApplet main, int width, int height, int cellWidth, int cellHeight, int mouseX, int mouseY) {
        this.width = width;
        this.height = height;
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.main = main;

        initGrid();
        initColorMapping();
        setGraphicsElement(main.createGraphics(width, height, main.JAVA2D));
    }

    private void initGrid() {
      this.grid = new int[width/cellWidth][height/cellHeight];
      // generate random start and end positions to start with
      this.startX = new Random().nextInt(grid.length);
      this.startY = new Random().nextInt(grid[0].length);;
      this.endX = new Random().nextInt(grid.length);
      this.endY = new Random().nextInt(grid[0].length);;
    }

    private void initColorMapping() {
      stateToColorMap = new HashMap<Integer, ArrayList<Integer>>();
      stateToColorMap.put(EMPTY_CELL, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
      stateToColorMap.put(WALL_CELL, new ArrayList<Integer>(Arrays.asList(200, 0, 0)));
      stateToColorMap.put(VISITED_CELL, new ArrayList<Integer>(Arrays.asList(124, 252, 0)));
      stateToColorMap.put(START_CELL, new ArrayList<Integer>(Arrays.asList(0, 191, 255)));
      stateToColorMap.put(END_CELL, new ArrayList<Integer>(Arrays.asList(255, 255, 0)));
    }

    public void setGraphicsElement(PGraphics graphics) {
        this.graphics = graphics;
        this.graphics.beginDraw();
        this.graphics.stroke(48);
        this.graphics.background(0);
        this.graphics.endDraw();
    }

    public PGraphics getGraphicsElement(){return graphics;}

    public int[][] getGrid() {return grid;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getMouseX() {return mouseX;}
    public int getMouseY() {return mouseY;}
    public int getCellWidth() {return cellWidth;}
    public int getCellHeight() {return cellHeight;}

    public void draw() {
        main.image(graphics, 0, 0, width, height);

        //@TODO: could probably use some refactoring
        graphics.beginDraw();

        for (int x=0; x<width/cellWidth; x++) {
            for (int y=0; y<height/cellHeight; y++) {
              Integer state = grid[x][y];
              
              // dont redraw start/end positions
              if (x==startX && y == startY) state = START_CELL;
              else if (x == endX && y ==endY) state = END_CELL;

              ArrayList<Integer> cellColor = stateToColorMap.get(state);
              graphics.fill(cellColor.get(0), cellColor.get(1), cellColor.get(2));
              graphics.rect (x*cellWidth, y*cellWidth, cellWidth, cellHeight);            
            }
          }
          graphics.endDraw();
    }

    public void setCellState(int x, int y, int state) {
        if (x < grid.length && y < grid[0].length && x >= 0 && y >= 0)
            grid[x][y] = state;
      }
    public int getCellState(int x, int y) {
      if (x < grid.length && y < grid[0].length && x >= 0 && y >= 0)
        return grid[x][y];
      return 0;
    }

    public void click(int x, int y) {
        // if x coordinate is less than the width of the grid 
        // then we know the click happened on the grid itself
        if (x <= width) {

            int xcell = (int)(x/cellWidth);
            int ycell = (int)(y/cellHeight);

            if (drawingState) {
              if (getCellState(xcell, ycell) == EMPTY_CELL || getCellState(xcell, ycell) == WALL_CELL) {
                setCellState(xcell, ycell, WALL_CELL);
              }
            }
            else if (settingStartLocation) {
              setCellState(startX, startY, EMPTY_CELL);
              startX = xcell;
              startY = ycell;
              setCellState(xcell, ycell, START_CELL);
            }
            else if (settingEndLocation) {
              setCellState(endX, endY, EMPTY_CELL);
              endX = xcell;
              endY = ycell;
              setCellState(xcell, ycell, END_CELL);
            }

        } else {

            if (chain != null) chain.click(x, y);

        }
    }

    public void setNext(IClickEventHandler c) {
        chain = c;
    }

    public void addSubComponent(IDisplayComponent c) {
        // not used here
    }

    public String name() {
        return "Grid Display";
    }

    public void clearGrid() {
      for (int i=0; i < grid.length; i++) {
        for(int j=0; j < grid[i].length; j++) {
          grid[i][j] = EMPTY_CELL;
        }
      }
    }

    public void setStart() {
      settingEndLocation = false;
      drawingState = false;
      settingStartLocation = true;
    }

    public void setEnd() {
      settingStartLocation = false;
      drawingState = false;
      settingEndLocation = true;
    }
  
    public void startDrawingWalls() {
      settingStartLocation = false;
      settingEndLocation = false;
      drawingState = true;
    }

    public void stopDrawingWalls() {
      settingStartLocation = false;
      settingEndLocation = false;
      drawingState = false;
    }

    public int getStartX() {return startX;}
    public int getEndX() {return endX;}
    public int getStartY() {return startY;}
    public int getEndY() {return endY;}

}
