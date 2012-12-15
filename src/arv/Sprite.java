/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */

import java.awt.Image;


public class Sprite {


    private int width,height;
    private Image image;
    private boolean visible;
    public Sprite()
    {
        visible = true;
    }

    public void draw(Screen screen,int x, int y)
    {
        ////System.out.println("Sprite coords: ("+x+":"+y+")");
        if (visible == true)
            screen.draw(image, x,y);
    }

    public void setImage(Image pImage)
    {
        image = pImage;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }

    public void setVisible(boolean vis)
    {
        visible = vis;
    }

}
