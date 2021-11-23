import processing.core.PGraphics;
import processing.core.PApplet;


public class GridDisplay implements IDisplayComponent, IClickEventHandler {

    IClickEventHandler chain;
    private PGraphics graphics;
    private PApplet main;

    private int width, height, cellWidth, cellHeight, mouseX, mouseY;
    int[][] grid;
    
    public GridDisplay(PApplet main, int width, int height, int cellWidth, int cellHeight, int mouseX, int mouseY) {
        this.width = width;
        this.height = height;
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.main = main;
        this.grid = new int[width/cellWidth][height/cellHeight];

        setGraphicsElement(main.createGraphics(width, height, main.JAVA2D));
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
        //@TODO: could probably use some refactoring
        graphics.beginDraw();

        for (int x=0; x<width/cellWidth; x++) {
            for (int y=0; y<height/cellHeight; y++) {
              switch (grid[x][y]) {
                case 0:
                  graphics.fill(0);
                  break;
                case 1:
                  graphics.fill(124, 252, 0);
                  break;
                case 2:
                  graphics.fill(200, 0, 0);
                  break;
                case 3:
              }  
              graphics.rect (x*cellWidth, y*cellWidth, cellWidth, cellHeight);            
            }
          }
          graphics.endDraw();
    }

    public void setCellState(int x, int y, int state) {
        if (x < grid.length && y < grid[0].length && x >= 0 && y >= 0)
            grid[x][y] = state;
    }

    public void click(int x, int y) {
        // if x coordinate is less than the width of the grid 
        // then we know the click happened on the grid itself
        if (x <= width) {

            int xcell = (int)(x/cellWidth);
            int ycell = (int)(y/cellHeight);

            setCellState(xcell, ycell, 2);

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
    
}
