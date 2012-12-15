/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
public class Weight {
    public float weight;
    public boolean possible;

    public int surroundingBombs,surroundingImpossible,move;
    public boolean bomb;
    public boolean covered;

    public int assumedSurroundingBombs;
    public boolean incrementAssumed;

    public Weight()
    {
        weight = 0;
        possible = true;

        covered = true;
        move = 0;
        incrementAssumed = true;
        assumedSurroundingBombs = 0;

    }
}
