/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
public class HumanPlayer extends Player {

    public HumanPlayer(Grid pGrid)
    {
        super(pGrid);
    }

    protected boolean getSquare()
    {
        if(clicked == true)
        {
            clicked = false;
            return true;
        }
        return false;
    }




}
