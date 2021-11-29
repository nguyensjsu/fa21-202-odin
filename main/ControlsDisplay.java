import processing.core.PGraphics;
import processing.core.PApplet;
import java.util.ArrayList;

public class ControlsDisplay implements IDisplayComponent, IClickEventHandler {

    // offset modifiers to position elements inside the control panel
    public static double LEFT = 0.0;
    public static double CENTER = 0.5;
    public static double RIGHT = 0.9;

    IClickEventHandler chain;
    IClickEventHandler subChain;

    private PGraphics graphics;
    private PApplet main;
    private int width, height, mouseX, mouseY;

    ArrayList<IDisplayComponent> components;

    public ControlsDisplay(PApplet main, int width, int height, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.width = width;
        this.height = height;
        this.main = main;
        components = new ArrayList<IDisplayComponent>();

        setGraphicsElement(main.createGraphics(width, height, main.JAVA2D));
    }

    public void click(int x, int y) {
        if (x >= mouseX) {

            if (subChain != null) subChain.click(x, y);

        } else {

            if (chain != null) chain.click(x, y);

        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getMouseX() {return mouseX;}
    public int getMouseY() {return mouseY;}

    public void setNext(IClickEventHandler c) {
        chain = c;
    }

    public void addSubComponent(IDisplayComponent c) {
        components.add( c ) ;

        if (components.size() == 1 )
        {
            subChain = (IClickEventHandler) c ;
        }
        else
        {
            IClickEventHandler prev = (IClickEventHandler) components.get(components.size()-2) ;
            prev.setNext( (IClickEventHandler) c ) ;
        }
    }

    public String name() {
        return "Menu Items";
    }
    
    @Override
    public void setGraphicsElement(PGraphics graphics) {
        this.graphics = graphics;
        this.graphics.beginDraw();
        this.graphics.noStroke();
        this.graphics.background(0);
        this.graphics.endDraw();
    }

    @Override
    public PGraphics getGraphicsElement() {
        return graphics;
    }

    @Override
    public void draw() {
        main.image(graphics, mouseX, 0, width, height);
        graphics.beginDraw();
        graphics.background(0);
        graphics.endDraw();
        for (IDisplayComponent obj: components)
            obj.draw();
    }
}
