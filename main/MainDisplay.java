import java.util.ArrayList;
import processing.core.PGraphics;


public class MainDisplay implements IDisplayComponent, IKeyboardObserver {
    
    IClickEventHandler chain;
    ArrayList<IDisplayComponent> components;
    int width, height, mouseX, mouseY;

    public MainDisplay(int width, int height, int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.width = width;
        this.height = height;
        components = new ArrayList<IDisplayComponent>();
    }

    public void click(int x, int y) {
        if (chain != null) chain.click(x, y);
    }
    
    public void addSubComponent(IDisplayComponent c) {
        components.add( c ) ;

        if (components.size() == 1 )
        {
            chain = (IClickEventHandler) c ;
        }
        else
        {
            IClickEventHandler prev = (IClickEventHandler) components.get(components.size()-2) ;
            prev.setNext( (IClickEventHandler) c ) ;
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getMouseX() {return mouseX;}
    public int getMouseY() {return mouseY;}

    public String name() {
        return "";
    }

    public void keyPressed(char key) {
        // @TODO: Flipping to the search state from here needs to be implemented
        System.out.println("Key pressed in main display " + key);
    }

    @Override
    public void setGraphicsElement(PGraphics graphics) {
        
    }

    @Override
    public PGraphics getGraphicsElement() {
        return null;
    }

    @Override
    public void draw() {
        for (IDisplayComponent obj:components)
            obj.draw();
    }
}
