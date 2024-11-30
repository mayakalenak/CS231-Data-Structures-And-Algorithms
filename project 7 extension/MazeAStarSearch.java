/* Name: Maya Kalenak
 * Purpose: Algorithm to search maze grid using A*
 */

import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {

    // fields
    public PriorityQueue<Cell> heap;

    // constructor
    public MazeAStarSearch(Maze maze) {
        super(maze);

        heap = new Heap<Cell>(new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                int r1 = cell1.getRow();
                int c1 = cell1.getCol();
                int r2 = cell2.getRow();
                int c2 = cell2.getCol();
                int targetRow = getTarget().getRow();
                int targetCol = getTarget().getCol();

                int d1 = r1 + c1;
                int d2 = r2 + c2;

                if (d1 < d2) 
                    return 1;
                else if (d1 > d2)
                    return -1;
                else   
                    return 0;
            }
        }, false);
    }

    // return last cell in priority queue
    public Cell findNextCell() {
        return heap.poll();
    }

    // add cell to priority queue
    public void addCell(Cell next) {
        this.heap.offer(next);
    }

    // return total number of cells in priority queue
    public int numRemainingCells() {
        return this.heap.size();
    }
}
