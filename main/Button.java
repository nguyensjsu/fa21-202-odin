// import g4p_controls.GButton;
import processing.core.PGraphics;
import processing.core.PApplet;

public class Button implements IDisplayComponent, IClickEventHandler {

    private String name;
    private int width, height, mouseX, mouseY;
    private PGraphics graphics;
    private IDisplayComponent parent;
    private PApplet main;

    public static double SIZE_HALF = 0.47;
    public static double SIZE_FULL = 0.945;

    private int hover_r = 126;
    private int hover_g = 255;
    private int hover_b = 255;
    
    private int norm_r = 77;
    private int norm_g = 208;
    private int norm_b = 225;
    
    private static final int y_offset = 3;

    IClickEventHandler chain;

    private double position;

    public Button(PApplet main, String name, IDisplayComponent parent, double sizeModifier, int height, int yCord, double position) {
        this.name = name;
        this.parent = parent;

        this.width = (int)(this.parent.getWidth()*sizeModifier);
        this.height = height;
        this.mouseX = parent.getMouseX() + ((int)((this.parent.getWidth() - this.width)*position));
        this.mouseY = yCord;

        this.graphics = this.parent.getGraphicsElement();
        this.main = main;
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
        int x = (int)((parent.getWidth() - width)*position);
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
