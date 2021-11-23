// import g4p_controls.GButton;
import processing.core.PGraphics;
import processing.core.PApplet;

public class Button implements IDisplayComponent, IClickEventHandler {

    private String name;
    private int width, height, mouseX, mouseY;
    private PGraphics graphics;
    private IDisplayComponent parent;
    private PApplet main;

    private int hover_r = 126;
    private int hover_g = 255;
    private int hover_b = 255;
    
    private int norm_r = 77;
    private int norm_g = 208;
    private int norm_b = 225;
    
    private static final int y_offset = 3;

    IClickEventHandler chain;

    public Button(PApplet main, String name, IDisplayComponent parent, int width, int height, int yCord) {
        this.name = name;
        this.width = width;
        this.height = height;

        this.parent = parent;
        this.mouseX = parent.getMouseX() + ((int)(parent.getWidth() - width)/2);
        this.mouseY = yCord;

        this.graphics = parent.getGraphicsElement();
        this.main = main;

        // this.button = new GButton(main, x+parent.getMouseX(), y, width, height, name);
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
        if (mouseInBounds(main.mouseX, main.mouseY))
            this.drawButton(hover_r, hover_b, hover_g);
        else 
            this.drawButton(norm_r, norm_b, norm_g);
        
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
        return (x >= mouseX && x <= mouseX+width && y >= mouseY+y_offset && y <= mouseY+height+y_offset);
    }

    private void drawButton(int r, int g, int b) {
        int x = ((int)(parent.getWidth() - width)/2);
        int y = mouseY;

        this.graphics.beginDraw();
        this.graphics.smooth();
        this.graphics.fill(r, g, b);
        this.graphics.rect(x, y, width, height);
        this.graphics.fill(0);
        this.graphics.text(name, x+10, y+ (int)(height/2 + 5));
        this.graphics.endDraw();
    }


    @Override
    public void click(int x, int y) {
        if (mouseInBounds(x, y)) {
            drawButton(0, 0, 0);
        }else {
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
}
