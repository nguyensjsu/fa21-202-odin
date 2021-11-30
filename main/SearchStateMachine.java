public class SearchStateMachine {

    private ISearchState stopped;
    private ISearchState running;
    private ISearchState currentState;

    public SearchStateMachine() {
        stopped = new StoppedSearchState();
        running = new RunningSearchState();
        currentState = stopped;
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

