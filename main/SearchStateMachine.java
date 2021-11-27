public class SearchStateMachine {

    private ISearchState stopped;
    private ISearchState running;
    private ISearchState currentState;

    public SearchStateMachine() {
        stopped = new ISearchState() {};
        running = new ISearchState() {};
    }

    public void setStateStopped() {
        currentState = stopped;
    }

    public void setStateRunning() {
        currentState = running;
    }

    public ISearchState getCurrentState() {
        return currentState;
    }
}

