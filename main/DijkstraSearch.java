import java.awt.Point;
import java.util.*;

public class DijkstraSearch implements ISearchStrategy {

    private static Stack<Point> stack = new Stack<>();
    private static Set<String> visited = new HashSet<>();
    private static Map<String, Integer> costs = new HashMap<>();
    private static int[][] ggrid;
    int sX, sY;

    public boolean search(int[][] grid, int startX, int startY, int endX, int endY) {
        ggrid = grid;
        if (startX != sX && startY != sY) { // initial step
            sX = startX;
            sY = startY;
            stack = new Stack<>();
            visited = new HashSet<>();
            costs = new HashMap<>();
            stack.push(new Point(startX, startY));
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j<grid[0].length; j++) {
                    costs.put(i+","+j, Integer.MAX_VALUE);
                }
            }
            costs.put(startX+","+startY, 0);
        }
        Point p;
        try {
            p = stack.pop();
        } catch (EmptyStackException e) { // no route to point, do nothing
            return false;
        } 
        if (p == null)
            return false; // no route to point, do nothing
        String co = p.x + "," + p.y;
        visited.add(co);
        System.out.println(p + "\t" + endX + "\t" + endY);
        if (p.x == endX && p.y == endY) {
            System.out.println("Found path");
            return true;
        }
        grid[p.x][p.y] = 1;
        List<Point> neighbors = new ArrayList<>();
        // get neighbor nodes if they are not off the grid
        if (p.x != 0) 
            neighbors.add(new Point(p.x - 1, p.y));
        if (p.x != grid.length-1) 
            neighbors.add(new Point(p.x + 1, p.y));
        if (p.y != 0)
            neighbors.add(new Point(p.x, p.y - 1));
        if (p.y != grid[0].length-1)
            neighbors.add(new Point(p.x, p.y + 1));
        for (Point n: neighbors) {
            if (grid[n.x][n.y] == 1)
                continue;
            int c = costs.get(co);
            int dis = calculateDistance(n.x, n.y, startX, startY);
            if (costs.get(n.x+","+n.y) != null && costs.get(n.x+","+n.y) > dis) {
                costs.put(n.x+","+n.y, dis);
            }
        }
        stack.push(findNext());
        return false;
    }

    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2-x1) + Math.abs(y2-y1);        
    }

    private Point findNext() {
        int d = Integer.MAX_VALUE;
        Point p = null;
        for (int i=0; i<ggrid.length; i++) {
            for (int j=0; j<ggrid[0].length; j++) {
                int t = costs.get(i+","+j);
                if (t < d && !visited.contains(i+","+j)) {
                    d = t;
                    p = new Point(i, j);
                }
            }
        }
        return p;
    }

    public void reset() {}
}
