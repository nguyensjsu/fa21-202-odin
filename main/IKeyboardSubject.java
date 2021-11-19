public interface IKeyboardSubject {

    void attach(IKeyboardObserver obj);
    void notifyObservers(char key);
    
}
