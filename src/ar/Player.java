/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
abstract public class Player {
    protected Grid grid;

    protected int score;

    protected Point2D currentSquare;
    protected boolean clicked;

    protected abstract boolean getSquare();
    protected boolean turn;
    private Player(){};
    public Player(Grid pGrid)
    {
        turn = false;
        grid = pGrid;
    }

    public int getScore()
    {
        return score;
    }

    public void reset()
    {
        score = 0;
        currentSquare = new Point2D();
        clicked = false;
    }

    public boolean move()
    {

        
        if (getSquare() == true)
        {
            //Return true to switch player's turn, false to keep turn at this player
            int result = grid.click(currentSquare);
            clicked = false;
            switch (result)
            {
                case -1:
                    return false;
                case 0:
                    return true;
                case 1:
                    ++score;
                    return false;
            }
        }
        return false;
    }

    public void swap()
    {}

    public void setTurn(boolean pTurn)
    {
        turn = pTurn;
    }

    public void setState(int x, int y)
    {
        if (turn == true)
        {
            clicked = true;
            currentSquare.x = x;
            currentSquare.y = y;
        }
    }

}
