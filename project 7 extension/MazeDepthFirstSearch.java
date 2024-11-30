/* Name: Maya Kalenak
 * Purpose: Algorithm to search maze grid using DFS
 */

public class MazeDepthFirstSearch extends AbstractMazeSearch {

    //fields
    public Stack<Cell> stack;
    
    //constructor
    public MazeDepthFirstSearch(Maze maze) {
        super(maze);

        stack = new LinkedList<Cell>();
    }

    //return next cell in stack
    public Cell findNextCell()
    {
        return stack.pop();
    }

    //add cell to stack
    public void addCell(Cell next)
    {
        this.stack.push(next);
    }

    //total cells in stack
    public int numRemainingCells()
    {
        return this.stack.size();
    }
}
