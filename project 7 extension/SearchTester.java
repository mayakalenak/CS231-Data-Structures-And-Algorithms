public class SearchTester {
    public static void main(String[] args) {
        Maze maze = new Maze(15, 15, 0.2);
        Cell start = maze.get(0, 0);
        Cell target = maze.get(14, 14);

        if (args.length == 0) {
            System.out.println("Enter \"java SearchTester\" then \"DFS\", \"BFS\", or \"AStar\" to run a search on the grid.\n");
        } else {
            if (args[0].equals("DFS")) {
                MazeDepthFirstSearch searcher = new MazeDepthFirstSearch(maze);
                searcher.search(start, target, true, 100);
                System.out.println("Number of cells searched\n" + searcher.stack.size());
            } else if (args[0].equals("BFS")) {
                MazeBreadthFirstSearch searcher = new MazeBreadthFirstSearch(maze);
                searcher.search(start, target, true, 50);
                System.out.println("Number of cells searched\n" + searcher.queue.size());
            } else if (args[0].equals("AStar")) {
                MazeAStarSearch searcher = new MazeAStarSearch(maze);
                searcher.search(start, target, true, 50);
                System.out.println("Number of cells searched\n" + searcher.heap.size());
            } else {
                System.out.println("You must enter \"DFS\", \"BFS\", or \"AStar\" to run that search on the grid.\n");
                return;
            }
        }
    }
}
