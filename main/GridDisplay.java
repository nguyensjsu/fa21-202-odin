public class GridDisplay implements IDisplayComponent, IClickEventHandler {

    IClickEventHandler chain;

    public void click(int x, int y) {
        if (x < 300) {

            System.out.println("Grid Display clicked");

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
