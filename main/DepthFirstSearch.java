import java.awt.Point;
import java.util.*;

public class DepthFirstSearch implements ISearchStrategy {

    private static Stack<Point> stack = new Stack<Point>();
    private static HashSet<String> visited = new HashSet<String>();

    public void reset() {
        visited = new HashSet<String>();
        stack = new Stack<Point>();
    }
    /**
     * Searches the grid using depth first search
     * @param grid  int[][] indicating the grid to search
     * @param startX int indicating the starting x coordinate
     * @param startY int indicating the starting y coordinate
     * @param endX int indicating the ending x coordinate
     * @param endY int indicating the ending y coordinate
     */
    public boolean search(int[][] grid, int startX, int startY, int endX, int endY) {
        if (stack.isEmpty()) {
            stack.push(new Point(startX, startY));
        }

        Point p = stack.pop();
        String coordinate = p.x + "," + p.y;
        if (!visited.contains(coordinate)) {
            visited.add(coordinate);
            if(p.x == endX && p.y == endY) {
                System.out.println("Found path");
                return true;
            }
            grid[p.x][p.y] = 1;
            if (p.x >= 1 && grid[p.x - 1][p.y] != 0) {
                stack.push(new Point(p.x - 1, p.y));
            }
            if (p.x <= grid.length - 2 && grid[p.x + 1][p.y] != 2) {
                stack.push(new Point(p.x + 1, p.y));
            }
            if (p.y >= 1 && grid[p.x][p.y - 1] != 2) {
                stack.push(new Point(p.x, p.y - 1));
            }
            if (p.y <= grid[0].length - 2 && grid[p.x][p.y + 1] != 2) {
                stack.push(new Point(p.x, p.y + 1));
            }
        }

        return false;
    }

}
