public interface IClickEventHandler {
    
    void setNext(IClickEventHandler next);
    void click(int x, int y);
}
