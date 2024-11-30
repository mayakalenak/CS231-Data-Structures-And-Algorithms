/* Name: Maya Kalenak
 * Purpose: Algorithm to search maze grid using BFS
 */

public class MazeBreadthFirstSearch extends AbstractMazeSearch {

    //fields
    public Queue<Cell> queue;
    
    //constructor
    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);

        queue = new LinkedList<Cell>();
    }

    //return first cell off of queue
    public Cell findNextCell()
    {
        return queue.poll(); 
    }

    //add cell to queue
    public void addCell(Cell next)
    {
        this.queue.offer(next);
    }

    //return total cells in queue
    public int numRemainingCells()
    {
        return this.queue.size();
    }
}
