/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
import java.util.Random;
import java.util.ArrayList;
public class Weigher {

    private int columns,rows,currentMove;
    private Weight[][] weights;


    private int weightRange,difficulty;

    public void setUpWeights(int pColumns,int pRows)
    {
        columns = pColumns;
        rows = pRows;
        weights = new Weight[columns][rows];
        for (int c = 0;c<columns;++c)
        {
            for (int r = 0;r < rows;++r)
            {
                weights[c][r] = new Weight();
            }
        }
        currentMove = 1;
        weightRange = 0;
    }

    public void uncover(Point2D pos,Square square)
    {
        weights[pos.x][pos.y].covered = false;
        weights[pos.x][pos.y].surroundingBombs = square.surroundingBombs;
        weights[pos.x][pos.y].bomb = square.bomb;
        weights[pos.x][pos.y].possible = false;
        calculateWeight(pos);
        calculateSurroundingWeights(pos);

        ++currentMove;

    }

    private void calculateWeight(Point2D pos)
    {
        ////System.out.println("1");
        if (weights[pos.x][pos.y].covered == true && (weights[pos.x][pos.y].surroundingBombs == 0)) return;
        ////System.out.println("2");
        if (weights[pos.x][pos.y].bomb == true) return;
        ////System.out.println("3");
        if (weights[pos.x][pos.y].surroundingBombs == 0)
        {
            impossibleCascade(pos);
            return;
        }
        ////System.out.println("4");
        float possibleBombs = weights[pos.x][pos.y].surroundingBombs, possibleCovered = 0;
        ////System.out.println(possibleBombs+" | "+possibleCovered);
        for (int x = pos.x - 1;x <= pos.x + 1; ++x)
        {
            for (int y = pos.y - 1; y <= pos.y + 1; ++y)
            {
                if  ( ((x >= 0) && (x < columns)) && ((y>=0) && (y < rows)))
                {
                    if (!((y == pos.x) && (x == pos.y)))
                    {
                        if (weights[x][y].covered == false)
                        {
                            if(weights[x][y].bomb == true)
                            {
                                --possibleBombs;
                            }

                        } else {
                            if (weights[x][y].possible == true)
                                ++possibleCovered;
                        }
                    }
                }
            }
        }
       ////System.out.println(possibleBombs+" | "+possibleCovered);
        if ((possibleBombs < 1.0) || (possibleCovered < 1.0))
        {
            ////System.out.println("IMPOSSIBLE: "+possibleBombs+" | "+possibleCovered);
            impossibleCascade(pos);
        } else {
            setWeight(pos,(possibleBombs / possibleCovered) * 100);
            ////System.out.println("WEIGHT: " + weights[pos.x][pos.y].weight);
        }
    }

    private void setWeight(Point2D pos,float weight)
    {
        ////System.out.println("----WEIGHT: "+ weight);
        for (int x = pos.x - 1;x <= pos.x + 1; ++x)
        {
            for (int y = pos.y - 1; y <= pos.y + 1; ++y)
            {
                if  ( ((x >= 0) && (x < columns)) && ((y>=0) && (y < rows)))
                {
                    if (!((y == pos.x) && (x == pos.y)))
                    {
                        if ((weight > weights[x][y].weight) || (currentMove > weights[x][y].move))
                        {
                                weights[x][y].weight = weight;
                                weights[x][y].move = currentMove;
                        }
                    }
                }
            }
        }

    }

    private void impossibleCascade(Point2D pos)
    {

            if (weights[pos.x][pos.y].covered == false)
            {
                boolean set = (weights[pos.x][pos.y].surroundingBombs == 0);
                for (int x = pos.x - 1;x <= pos.x + 1; ++x)
                {
                    for (int y = pos.y - 1; y <= pos.y + 1; ++y)
                    {
                        if  ( ((x >= 0) && (x < columns)) && ((y>=0) && (y < rows)))
                        {
                            if (!((y == pos.x) && (x == pos.y)))
                            {
                                if(weights[x][y].possible == true)
                                {
                                    weights[x][y].possible = false;
                                    if ((weights[x][y].surroundingBombs == 0) && (set == true) && (difficulty == 3))
                                        weights[x][y].surroundingBombs = 1;
                                    calculateSurroundingWeights(new Point2D(x,y));

                                }
                            }
                        }
                    }
                }
            }
        
        //calculateSurroundingWeights(pos);
    }

    private void calculateSurroundingWeights(Point2D pos)
    {
        for (int x = pos.x - 1;x <= pos.x + 1; ++x)
        {
            for (int y = pos.y - 1; y <= pos.y + 1; ++y)
            {
                if  ( ((x >= 0) && (x < columns)) && ((y>=0) && (y < rows)))
                {
                    //if (!((y == pos.x) && (x == pos.y)))
                    //{
                        calculateWeight(new Point2D(x,y));
                    //}
                }
            }
        }
    }


    public ArrayList<Point2D> getMaxWeightSquares()
    {
        ArrayList<Point2D> squares = new ArrayList<Point2D>();

        float maxWeight = findMaxWeight();
        float minWeight = maxWeight - weightRange;

        for (int x = 0; x< columns; ++ x)
        {
            for (int y = 0; y < rows; ++ y)
            {
                if (weights[x][y].possible == true)
                {
                    if ((weights[x][y].weight >= minWeight) && (weights[x][y].weight <= maxWeight))
                        squares.add(new Point2D(x,y));
                }
            }
        }



        return squares;

    }

    private float findMaxWeight()
    {
        float maxWeight = 0;
        for (int x = 0; x< columns; ++ x)
        {
            for (int y = 0; y < rows; ++ y)
            {
                if (weights[x][y].possible == true)
                {
                    if (weights[x][y].weight > maxWeight)
                        maxWeight = weights[x][y].weight;
                }
            }
        }

        return maxWeight;
    }

    public void setDifficulty(int pDifficulty)
    {
        difficulty = pDifficulty;
        switch (difficulty)
        {
            case 1:
                weightRange = 40;
                break;
            case 2:
                weightRange = 20;
                break;
            case 3:
                weightRange = 0;
                break;
        }
    }
}
