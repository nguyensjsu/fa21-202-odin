import java.util.ArrayList;

public class MainDisplay implements IDisplayComponent, IKeyboardObserver {
    
    IClickEventHandler chain;
    ArrayList<IDisplayComponent> components;

    public MainDisplay() {
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

    public String name() {
        return "";
    }

    public void keyPressed(char key) {
        // @TODO: Flipping to the search state from here needs to be implemented
        System.out.println("Key pressed in main display " + key);
    }

}
