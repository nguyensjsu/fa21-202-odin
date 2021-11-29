public class AlgorithmStateMachine {

    private ISearchStrategy dfs;
    private ISearchStrategy bfs;
    private ISearchStrategy dijkstra;
    private ISearchStrategy currentAlgorithm;

    public AlgorithmStateMachine() {
        dfs = new DepthFirstSearch();
        bfs = new BreadthFirstSearch();
        dijkstra = new DijkstraSearch();
        currentAlgorithm = dijkstra;
    }

    public void setAlgorithm(SearchAlgorithm alg) {
        switch (alg) {
            case BFS:
            System.out.println("check bfs====>");
                currentAlgorithm = bfs;
                break;
            case DFS:
            System.out.println("check dfs====>");
                currentAlgorithm = dfs;
                break;
            case Dijkstra:
            System.out.println("check Dijkstra====>");
                currentAlgorithm = dijkstra;
                break;
        }
    }

    public ISearchStrategy getCurrentStrategy() {
        return currentAlgorithm;
    }
}
