/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */


import java.util.LinkedList;

public class GridView {
    private ar.Point2D squareSize,offSet;
    private int columns,rows;
    private ar.Grid grid;
    private ImageFactory imgFactory;

    private Sprite gameScreen;

    private SquareView[][] squareViews;


    private boolean shouldBeDown;
    private ar.Point2D currentDown;

    public GridView(ImageFactory pImgFac)
    {
        squareSize  = new ar.Point2D(27,27);
        offSet      = new ar.Point2D();

        gameScreen = pImgFac.getImage("gamescreen.png");


        imgFactory = pImgFac;

    }

    public void createView(ar.Grid pGrid)
    {
        grid = pGrid;
        ar.Point2D size = grid.getDimensions();
        columns = size.x;
        rows = size.y;
        //System.out.println("createView rows:colums: " + rows +":"+columns);
        offSet.x = 15 + ((459 - (squareSize.x * columns))/2);
        offSet.y = 76 + ((459 - (squareSize.y * rows))/2);
        squareViews = new SquareView[columns][rows];

        for (int c = 0; c < columns;++c)
        {
            for (int r = 0; r < rows;++r)
            {
                squareViews[c][r] = new SquareView(grid.getSquareAt(c,r),imgFactory);
                squareViews[c][r].setPosition( (c * squareSize.x) + offSet.x ,(r * squareSize.y) + offSet.y  );
                ////System.out.println("Square Pos X: "+ ((c * squareSize.x) + offSet.x));
            }
        }
        shouldBeDown = false;
    }

    public void update(Screen screen)
    {
        LinkedList<ar.Point2D> squares = grid.getUpdatedSquares();

        for(int i = 0; i < squares.size();++i)
        {
            ar.Point2D square = squares.pop();
            squareViews[square.x][square.y].update(imgFactory);
            squareViews[square.x][square.y].draw(screen);
        }

    }

    public void draw(Screen screen)
    {
        gameScreen.draw(screen,0,0);

        for (int c = 0; c < columns;++c)
        {
            for (int r = 0; r < rows;++r)
            {
                squareViews[c][r].draw(screen);
            }
        }
    }



    public ar.Point2D squareCoordFromMouse(int x, int y)
    {
        ar.Point2D tmp = new ar.Point2D();
        tmp.x = (x - offSet.x) / squareSize.x;
        tmp.y = (y - offSet.y) / squareSize.y;

        return tmp;
    }

    public ar.Point2D mouseFromSquareCoord(int x, int y)
    {
        ar.Point2D tmp = new ar.Point2D();
        tmp.x = (x * squareSize.x) + offSet.x;
        tmp.y = (y * squareSize.y) + offSet.y ;
        return tmp;
    }

    public void leftDown(int x, int y,Screen screen)
    {
        if(shouldBeDown == true)
        {
            shouldBeDown = false;
            squareViews[currentDown.x][currentDown.y].leftUp();
            squareViews[currentDown.x][currentDown.y].update(imgFactory);
            squareViews[currentDown.x][currentDown.y].draw(screen);
        }
        ar.Point2D square = new ar.Point2D();
        square = squareCoordFromMouse(x, y);
        if (grid.isValidCoord(square))
        {
            shouldBeDown = true;
            currentDown = square;
            squareViews[square.x][square.y].leftDown();
            squareViews[square.x][square.y].update(imgFactory);
            squareViews[square.x][square.y].draw(screen);

        }

    }

    public boolean leftUp(int x, int y,Screen screen)
    {
        if (shouldBeDown == true)
        {
            ar.Point2D square = new ar.Point2D();
            square = squareCoordFromMouse(x, y);
            shouldBeDown = false;
            squareViews[currentDown.x][currentDown.y].leftUp();
            squareViews[currentDown.x][currentDown.y].update(imgFactory);
            squareViews[currentDown.x][currentDown.y].draw(screen);
            if ((square.x == currentDown.x) && (square.y == currentDown.y))
            {
                return true;

            }
            
        }
        return false;
    }

}
