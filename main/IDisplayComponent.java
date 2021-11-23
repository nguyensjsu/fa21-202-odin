import processing.core.PGraphics;

public interface IDisplayComponent {

    void addSubComponent(IDisplayComponent obj);
    void setGraphicsElement(PGraphics graphics);
    PGraphics getGraphicsElement();
    int getMouseX();
    int getMouseY();
    int getWidth();
    int getHeight();
    void draw();
    String name();

}