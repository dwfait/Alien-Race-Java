/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
public class Square {
    public int surroundingBombs;
    public boolean bomb;
    public boolean covered;

    public Square()
    {
        surroundingBombs = 0;
        bomb = false;
        covered = true;
    }
}
