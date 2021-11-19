import java.awt.Point;
import java.util.*;

public class DepthFirstSearch implements ISearchStrategy {

    /**
     * Searches the grid using depth first search
     * @param grid  int[][] indicating the grid to search
     * @param startX int indicating the starting x coordinate
     * @param startY int indicating the starting y coordinate
     * @param endX int indicating the ending x coordinate
     * @param endY int indicating the ending y coordinate
     */
    public void search(int[][] grid, int startX, int startY, int endX, int endY) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(startX, startY));

        HashSet<String> visited = new HashSet<>();

        while(!stack.isEmpty()) {
            Point p = stack.pop();
            String coordinate = p.x + "," + p.y;
            if (!visited.contains(coordinate)) {
                visited.add(coordinate);
                if(p.x == endX && p.y == endY) {
                    System.out.println("Found path");
                    return;
                }
                grid[p.x][p.y] = 2;
                if (p.x > 1) {
                    stack.push(new Point(p.x - 1, p.y));
                }
                if (p.x < grid.length - 2) {
                    stack.push(new Point(p.x + 1, p.y));
                }
                if (p.y > 1) {
                    stack.push(new Point(p.x, p.y - 1));
                }
                if (p.y < grid[0].length - 2) {
                    stack.push(new Point(p.x, p.y + 1));
                }
            }
        }
    }

}