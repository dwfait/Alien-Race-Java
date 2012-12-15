/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;


import java.util.Random;
import java.util.LinkedList;
/**
 *
 * @author Dwain
 */
public class Grid {
    private Square[][] squares;
    private int columns,rows;

    private int bombs,bombsRemaining;

    private Weigher weigher;

    private LinkedList<Point2D> updatedSquares;

    private void createSquares()
    {
        squares = new Square[columns][rows];
        weigher = new Weigher();
        weigher.setUpWeights(columns, rows);
        for (int c = 0;c<columns;++c)
        {
            for (int r = 0;r < rows;++r)
            {
                squares[c][r] = new Square();
            }
        }
        updatedSquares = new LinkedList<Point2D>();
    }

    private void placeBombs()
    {
        Random r = new Random(System.currentTimeMillis());

        int bombsPlaced = 0;
        while (bombsPlaced < bombs)
        {
            int chance = r.nextInt() % 10;
            if (chance > 8)
            {
                int randX = r.nextInt(columns);
                int randY = r.nextInt(rows);
                if(squares[randX][randY].bomb == false)
                {
                    squares[randX][randY].bomb = true;
                    ++bombsPlaced;
                }
            }
        }
    }

    private void generateNumbers()
    {
        for (int c = 0;c<columns;++c)
        {
            for (int r = 0;r < rows;++r)
            {
                int counter = 0;

                for (int x = c - 1;x <= c + 1; ++x)
                {
                    for (int y = r - 1; y <= r + 1; ++y)
                    {
                        if ( ( (x >= 0) && (x < columns) ) && ( (y >= 0) && (y < rows) ) && ( !( (y == r) && (x == c) ) ) )
                        {
                            if ( squares[x][y].bomb == true ) 
                                ++counter;
                        }
                    }
                    squares[c][r].surroundingBombs = counter;
                }
            }
        }
    }

    private void emptyCascade(Point2D square)
    {
        if ((square.x < 0 || square.x > columns-1) || (square.y < 0 || square.y > rows-1))
            return;
        if((squares[square.x][square.y].covered == false) || (squares[square.x][square.y].bomb == true))
            return;
        if(squares[square.x][square.y].surroundingBombs == 0)
        {
            squares[square.x][square.y].covered=false;
            updatedSquares.add(square);
            weigher.uncover(square, squares[square.x][square.y]);
            emptyCascade(new Point2D(square.x-1,square.y));
            emptyCascade(new Point2D(square.x+1,square.y));
            emptyCascade(new Point2D(square.x,square.y-1));
            emptyCascade(new Point2D(square.x,square.y+1));
        }
    }

    public void populateGrid(int sColumns,int sRows)
    {
        rows = sRows;
        columns = sColumns;
        bombs = (columns * rows) / 5;
        if ((bombs % 2 == 0)) ++bombs;
        bombsRemaining = bombs;

        createSquares();
        placeBombs();
        generateNumbers();
    }

    public int getBombs()
    {
        return bombs;
    }

    public int getRemainingBombs()
    {
        return bombsRemaining;
    }

    public int click(Point2D square)
    {
        if ((square.x < 0 || square.x > columns-1) || (square.y < 0 || square.y > rows-1))
            return -1;
        if (squares[square.x][square.y].covered == false)
            return -1;

        ////System.out.println("Clicked");

        if(squares[square.x][square.y].surroundingBombs == 0)
            emptyCascade(square);

        squares[square.x][square.y].covered = false;
        updatedSquares.add(square);
        weigher.uncover(square, squares[square.x][square.y]);

        if (squares[square.x][square.y].bomb == true)
        {
            --bombsRemaining;
            return 1;
        }
        return 0;
    }

    Weigher getWeigher()
    {
        return weigher;
    }

    public Point2D getDimensions()
    {
        return new Point2D(rows,columns);
    }

    public Square getSquareAt(int x, int y)
    {
        if (isValidCoord(x,y) == true)
            return squares[x][y];
        return null;
    }

    public Square getSquareAt(Point2D pos)
    {
        return getSquareAt(pos.x,pos.y);
    }
    public boolean isValidCoord(int x, int y)
    {
        if ((x < 0 || x > columns-1) || (y < 0 || y > rows-1))
            return false;
        return true;
    }
    public boolean isValidCoord(Point2D pos)
    {
        return isValidCoord(pos.x,pos.y);
    }

    public LinkedList<Point2D> getUpdatedSquares()
    {
        return updatedSquares;
    }
}
