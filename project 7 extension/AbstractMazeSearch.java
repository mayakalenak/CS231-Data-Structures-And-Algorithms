/* Name: Maya Kalenak
 * Purpose: Make a searchable maze
 */

//import statements
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class AbstractMazeSearch {
    // fields
    private Maze maze;
    private Cell start;
    private Cell target;
    private Cell cur;

    // constructor
    public AbstractMazeSearch(Maze maze) {
        this.maze = maze;
        this.cur = null;
        this.target = null;
        this.start = null;
    }

    // abstract methods
    public abstract Cell findNextCell();
    public abstract void addCell(Cell next);
    public abstract int numRemainingCells();

    // return underlying maze
    public Maze getMaze() {
        return maze;
    }

    // set target cell
    public void setTarget(Cell target) {
        this.target = target;
    }

    // return target cell
    public Cell getTarget() {
        return this.target;
    }

    // set current cell
    public void setCur(Cell cell) {
        this.cur = cell;
    }

    // return current cell
    public Cell getCur() {
        return this.cur;
    }

    // set starting cell
    public void setStart(Cell start) {
        this.start = start;
        // this.start = start.getPrev();
        start.setPrev(start);
    }

    // return starting cell
    public Cell getStart() {
        return this.start;
    }

    // reset board/fields
    public void reset() {
        this.cur = null;
        this.start = null;
        this.target = null;
    }

    // return cells from current cell/end to start
    public LinkedList<Cell> traceback(Cell cell) {
        LinkedList<Cell> traceback = new LinkedList<Cell>();

        setCur(cell);
        
        while (!this.getCur().equals(this.start)) {
            traceback.add(this.getCur());
            setCur(this.getCur().getPrev());
        }
        
        traceback.add(this.start);
        
        if (!this.getCur().equals(this.start))
        return null;
        
        return traceback;
    }
    
    // search from start cell up to/for target cell
    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay) {
        // MazeSearchDisplay.pauseSim = false;
        // if (MazeSearchDisplay.obj == MazeSearchDisplay.density)
        //     MazeSearchDisplay.pauseSim = true;
        MazeSearchDisplay mazeDisplay = null;
        
        if (display == true)
        mazeDisplay = new MazeSearchDisplay(this, 20);
        
        this.setStart(start);
        this.setTarget(target);
        this.setCur(start);
        
        this.addCell(start);
        
        while (this.numRemainingCells() > 0) {
            this.setCur(this.findNextCell());
            
            for (Cell neighbor : maze.getNeighbors(this.getCur())) {
                if (neighbor.getPrev() == null) {
                    neighbor.setPrev(this.getCur());
                    this.addCell(neighbor);

                    if (neighbor.equals(target))
                        return this.traceback(target);
                }
            }

            if (display == true) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) { };
                mazeDisplay.repaint();
            }
        }
        return null;
    }

    // visualization
    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);

        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }
}
