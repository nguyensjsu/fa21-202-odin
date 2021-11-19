import java.util.ArrayList;


public class Keyboard implements IKeyboardSubject{

    ArrayList<IKeyboardObserver> observers;

    public Keyboard() {
        observers = new ArrayList<IKeyboardObserver>();

    }

    public void attach(IKeyboardObserver obj) {
        observers.add(obj);
    }

    public void notifyObservers(char key) {
        for (IKeyboardObserver observer: observers)
            observer.keyPressed(key);
    }

    public void keyPressEvent(char key) {
        notifyObservers(key);
    }
    
}
