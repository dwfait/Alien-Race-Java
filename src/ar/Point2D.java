/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
public class Point2D {
    public int x,y;


    public Point2D()
    {
        x = 0;
        y = 0;
    }
    public Point2D(int sX,int sY)
    {
        x = sX;
        y = sY;
    }

    public Point2D(Point2D rhs)
    {
        x = rhs.x;
        y = rhs.y;
    }

    public String toString()
    {
        return "("+x + ","+y+")";
    }

}
