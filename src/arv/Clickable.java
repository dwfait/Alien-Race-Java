/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;


/**
 *
 * @author Dwain
 */
public class Clickable {
    private int x,y,width,height;
    

    public Clickable(int pX, int pY, int w, int h)
    {
        x = pX; y = pY; width = w; height = h;

    }

    public void setPosition(int pX, int pY)
    {
        x = pX; y = pY;
    }


    public void setSize(int w,int h)
    {
        width = w; height = h;
    }


    public boolean click(int pX, int pY)
    {
        if ((pX >= x) && ( pX <= (x+width)) && (pY >= y) && (pY <= (y+height)))
        {
            //System.out.println(x + " : "+ (x+width) + "  |  "+ y + " : "+ (y+height));
            
            return true;
        }
        return false;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }




}
