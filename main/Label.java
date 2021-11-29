import processing.core.PGraphics;
import processing.core.PApplet;

public class Label implements IDisplayComponent, IClickEventHandler {

    private int label_r = 255;
    private int label_g = 255;
    private int label_b = 255;

    private String name;
    private int width, height, mouseX, mouseY;
    private PGraphics graphics;
    private IDisplayComponent parent;
    private PApplet main;
    
    private static final int y_offset = 10;
    private static final int x_offset = 50;

    IClickEventHandler chain;

    private double position;

    public Label(PApplet main, String name, IDisplayComponent parent, int yCord, double position) {
        this.name = name;
        this.parent = parent;
        this.graphics = parent.getGraphicsElement();
        this.main = main;
        this.width = (int)main.textWidth(name);
        if (this.width > x_offset) this.width -= x_offset;
        this.height = (int)(main.textAscent() + main.textAscent());
        this.mouseX = parent.getMouseX() + ((int)((parent.getWidth() - this.width)*position)) ;
        this.mouseY = yCord;
        this.position = position;
    }

    @Override
    public void addSubComponent(IDisplayComponent obj) {
        // not used here
        
    }

    @Override
    public void setGraphicsElement(PGraphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public PGraphics getGraphicsElement() {
        return graphics;
    }

    @Override
    public void draw() {
        drawLabel(label_r, label_g, label_b);
        
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setNext(IClickEventHandler next) {
        this.chain = next;
        
    }

    private boolean mouseInBounds(int x, int y) {    
        return (x >= mouseX && x <= mouseX+width && y >= mouseY-y_offset && y <= mouseY);
    }

    private void drawLabel(int r, int g, int b) {
        int x = (int)((parent.getWidth() - width)*position);
        int y = mouseY;

        this.graphics.beginDraw();
        this.graphics.smooth();
        // this.graphics.background(0,x,y);
        //this.graphics.size(x, y);
        this.graphics.fill(0);
       
        this.graphics.fill(r, g, b);
        this.graphics.text(name, x, y);
        this.graphics.endDraw();
    }


    @Override
    public void click(int x, int y) {
    
        if (mouseInBounds(x, y)) {
            System.out.println(name+" Label clicked..");
        } else {
            if (chain != null) chain.click(x, y);
        }
    }

    @Override
    public int getMouseX() {
        return mouseX;
    }

    @Override
    public int getMouseY() {
        return mouseY;
    }    


    public void setName(String name) {
        this.name=name;
    }
}
