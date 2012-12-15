/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */


import java.util.ArrayList;
import java.util.Random;
public class AIPlayer extends Player {

    private int difficulty;
    private long AITimer,moveInterval,clickTimer;
    private arv.GridView gridView;
    private arv.View view;
    private Point2D mouseCoords;
    private boolean clicking;
    private int downTime;
    public AIPlayer(Grid pGrid)
    {
        super(pGrid);
        
        
    }

    
    public void setViews(arv.GridView pGridView, arv.View pView)
    {
        gridView = pGridView;
        view = pView;
        
    }
    public void setDifficulty(int pDifficulty)
    {
        //difficulty = pDifficulty;
        grid.getWeigher().setDifficulty(pDifficulty);

    }

    protected boolean getSquare()
    {
        if (clicked == true)
            return true;
        if (clicking == true)
        {
            if ((clickTimer + downTime) < System.currentTimeMillis())
            {
                view.leftUp(mouseCoords.x,mouseCoords.y,false);
                clicking = false;
                resetTimer();
                ////System.out.println("Chosen square: "+currentSquare.toString());
            }
        } else {
            if ((AITimer + moveInterval) < System.currentTimeMillis())
            {
                chooseSquare();
                if (currentSquare != null)
                {
                    mouseCoords = gridView.mouseFromSquareCoord(currentSquare.x, currentSquare.y);
                    view.leftDown(mouseCoords.x,mouseCoords.y,false);
                    clickTimer = System.currentTimeMillis();
                    clicking = true;
                }
                ////System.out.println("Chosen square: "+currentSquare.toString());
            }
        }

        return false;
    }


    private void resetTimer()
    {
        AITimer = System.currentTimeMillis();
    }

    @Override
    public void swap()
    {
        resetTimer();
    }

    @Override
    public void reset()
    {
        super.reset();
        AITimer = 0;
        moveInterval = 2000;
        clickTimer = 0;
        downTime = 200;
        clicking = false;
    }


    public void chooseSquare()
    {
        ArrayList<Point2D> squares = grid.getWeigher().getMaxWeightSquares();
        ////System.out.println("Max Weight Squares size: "+squares.size());
        Random r = new Random(System.currentTimeMillis());
        if (squares.size() == 0)
        {
            currentSquare = null;
        } else {
            int chosen = r.nextInt(squares.size());

            currentSquare= squares.get(chosen);
        }
        ////System.out.println("CHOSEN: "+chosen+" "+currentSquare.toString());
    }
}
