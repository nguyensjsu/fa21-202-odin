import processing.core.PGraphics;
import java.util.ArrayList;

public class ControlsDisplay implements IDisplayComponent, IClickEventHandler {

    IClickEventHandler chain;
    private PGraphics graphics;
    private int width, height, xBoundary;

    ArrayList<IDisplayComponent> components;

    public ControlsDisplay(int boundary, int width, int height) {
        this.xBoundary = boundary;
        this.width = width;
        this.height = height;
        components = new ArrayList<IDisplayComponent>();
    }

    public void click(int x, int y) {
        if (x >= xBoundary) {

            System.out.println("Menu Items clicked");

        } else {

            if (chain != null) chain.click(x, y);

        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setNext(IClickEventHandler c) {
        chain = c;
    }

    public void addSubComponent(IDisplayComponent c) {
        components.add(c);
    }

    public String name() {
        return "Menu Items";
    }
    
    @Override
    public void setGraphicsElement(PGraphics graphics) {
        this.graphics = graphics;
        this.graphics.beginDraw();
        this.graphics.noStroke();
        this.graphics.background(65, 65, 65);
        this.graphics.endDraw();
    }

    @Override
    public PGraphics getGraphicsElement() {
        return graphics;
    }

    @Override
    public void draw() {
        for (IDisplayComponent obj: components)
            obj.draw();
        // draw sub components here
    }
}
