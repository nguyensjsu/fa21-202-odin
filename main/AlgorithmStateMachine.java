public class AlgorithmStateMachine {

    private ISearchStrategy dfs;
    private ISearchStrategy bfs;
    private ISearchStrategy dijkstra;
    private ISearchStrategy currentAlgorithm;

    public AlgorithmStateMachine() {
        dfs = new DepthFirstSearch();
        bfs = new BreadthFirstSearch();
        dijkstra = new DijkstraSearch();
        currentAlgorithm = bfs;
    }

    public void setAlgorithm(SearchAlgorithm alg) {
        switch (alg) {
            case BFS:
                currentAlgorithm = bfs;
                break;
            case DFS:
                currentAlgorithm = dfs;
                break;
            case Dijkstra:
                currentAlgorithm = dijkstra;
                break;
        }
    }

    public ISearchStrategy getCurrentStrategy() {
        return currentAlgorithm;
    }
}
