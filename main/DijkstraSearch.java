import java.awt.Point;
import java.util.*;

public class DijkstraSearch implements ISearchStrategy {

    private static Stack<Point> stack = new Stack<>();
    private static Set<String> visited = new HashSet<>();
    private static Map<String, Integer> costs = new HashMap<>();

    public boolean search(int[][] grid, int startX, int startY, int endX, int endY) {
        if (stack.isEmpty()) { // initial step
            stack.push(new Point(startX, startY));
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j<grid[0].length; j++) {
                    costs.put(j+","+i, Integer.MAX_VALUE);
                }
            }
            costs.put(startX+","+startY, 0);
        }
        Point p = stack.pop();
        String co = p.x + "," + p.y;
        if (!visited.contains(co)) 
        {
            visited.add(co);
            if (p.x == endX && p.y == endY) {
                System.out.println("Found path");
                return true;
            }
            grid[p.x][p.y] = 1;
            List<Point> neighbors = new ArrayList<>();
            // get neighbor nodes if they are not off the grid
            if (p.x != 0) 
                neighbors.add(new Point(p.x - 1, p.y));
            if (p.x != grid[0].length-1) 
                neighbors.add(new Point(p.x + 1, p.y));
            if (p.y != 0)
                neighbors.add(new Point(p.x, p.y - 1));
            if (p.y != grid.length-1)
                neighbors.add(new Point(p.x, p.y + 1));
            int min = Integer.MAX_VALUE;
            Point nextPoint = null;
            for (Point n: neighbors) {
                if (grid[n.x][n.y] != 0)
                    continue;
                int c = costs.get(co);
                int dis = calculateDistance(n.x, n.y, startX, startY);
                if (costs.get(n.x+","+n.y) > dis) {
                    costs.put(n.x+","+n.y, dis);
                    if (dis < min) {
                        min = dis;
                        nextPoint = n;
                    }
                }
            }
            stack.push(nextPoint);
        }
        return false;
    }

    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x2-x1) + Math.abs(y2-y1);        
    }

}
