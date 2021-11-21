import processing.core.PGraphics;

public interface IDisplayComponent {

    void addSubComponent(IDisplayComponent obj);
    void setGraphicsElement(PGraphics graphics);
    PGraphics getGraphicsElement();
    void draw() ;
    String name();

}