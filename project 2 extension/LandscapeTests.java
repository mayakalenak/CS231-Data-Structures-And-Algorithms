/*
file name:      LandscapeTests.java
Authors:        Maya Kalenak
last modified:  March 3
Use : Test Landscape.java
How to run:     java -ea LandscapeTests
*/

import java.util.ArrayList;

public class LandscapeTests {

    public static void landscapeTests() {

        // case 1: testing Landscape(int, int)
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10);

            // verify
            System.out.println(l1);
            System.out.println(l2);

            // test
            assert l1 != null : "Error in Landscape::Landscape(int, int)";
            assert l2 != null : "Error in Landscape::Landscape(int, int)";
        }

        // case 2: testing reset()
        {
            // set up
            Landscape l1 = new Landscape(2, 4);
            Landscape l2 = new Landscape(10, 10, 0.5);

            // verify
            int rowBefore1 = l1.getRows();
            int colBefore1 = l1.getCols();
            int rowBefore2 = l2.getRows();
            int colBefore2 = l2.getCols();

            String before1 = l1.toString();
            String before2 = l2.toString();

            System.out.println(before1);
            System.out.println(before2);

            l1.reset();
            l2.reset();

            int rowAfter1 = l1.getRows();
            int colAfter1 = l1.getCols();
            int rowAfter2 = l2.getRows();
            int colAfter2 = l2.getCols();

            String after1 = l1.toString();
            String after2 = l2.toString();

            System.out.println(after1);
            System.out.println(after2);

            // test
            assert l1 != null : "Error in Landscape::reset()";
            assert l2 != null : "Error in Landscape::reset()";
            assert rowBefore1 == rowAfter1 : "Error in Landscape::reset()";
            assert colBefore1 == colAfter1 : "Error in Landscape::reset()";
            assert rowBefore2 == rowAfter2 : "Error in Landscape::reset()";
            assert colBefore2 == colAfter2 : "Error in Landscape::reset()";
            assert before1.equals(after1) : "Error in Landscape::reset()";
            // assert !before2.equals(after2) : "Error in Landscape::reset()";
        }

        // case 3: testing getRows()
        {
            // set up
            Landscape l1 = new Landscape(4, 1);
            Landscape l2 = new Landscape(2, 5, 0.5);

            // verify
            System.out.println(l1.getRows() + " == 424");
            System.out.println(l2.getRows() + " == 2");

            // test
            assert l1.getRows() == 4 : "Error in Landscape::getRows()";
            assert l2.getRows() == 2 : "Error in Landscape::getRows()";
        }

        // case 4: testing getCols()
        {
            // set up
            Landscape l1 = new Landscape(4, 1);
            Landscape l2 = new Landscape(2, 5, 0.5);

            // verify
            System.out.println(l1.getCols() + " == 421");
            System.out.println(l2.getCols() + " == 5");

            // test
            assert l1.getCols() == 1 : "Error in Landscape::getCols()";
            assert l2.getCols() == 5 : "Error in Landscape::getCols()";

        }

        // case 5: testing getCell(int, int)
        {
            // set up
            Landscape l1 = new Landscape(5, 6);
            Landscape l2 = new Landscape(4, 4, 0.5);

            // verify
            System.out.println(l1.getCell(1, 2) + " == 540");
            System.out.println(l2.getCell(3, 1) + " == 0/1");

            // test
            assert l1.getCell(1, 2) != null : "Error in Landscape::getCell()";
            assert l2.getCell(3, 1) != null : "Error in Landscape::getCell()";
        }

        // case 6: testing getNeighbors()
        {
            // set up
            Landscape l1 = new Landscape(6, 8);
            Landscape l2 = new Landscape(5, 10, 0.5);

            // verify
            System.out.println(l1.getNeighbors(2, 5) + " == 65[0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println(l2.getNeighbors(3, 4) + " == [0/1, 0/1, 0/1]");

            // test
            assert l1.getNeighbors(2, 5) != null : "Error in Landscape::getNeighbors()";
            assert l2.getNeighbors(3, 4) != null : "Error in Landscape::getNeighbors()";

        }

        // case 7: testing advance()
        {
            // set up
            Landscape l1 = new Landscape(2, 3);
            Landscape l2 = new Landscape(3, 5, 0.5);

            // verify
            // test
            String before1 = l1.toString();
            String before2 = l2.toString();

            l1.advance();
            l2.advance();

            String after1 = l1.toString();
            String after2 = l2.toString();

            // test
            assert !before1.equals(after1) : "Error in Landscape::advance()";
            assert !before2.equals(after2) : "Error in Landscape::advance()";

        }
    }

    public static void main(String[] args) {
        landscapeTests();
    }
}
