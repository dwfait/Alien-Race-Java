/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;


public class Screen {
    private BufferedImage buffer;
    private Graphics bufferGraphics;

    public Screen()
    {
        buffer = new BufferedImage(600,550, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.createGraphics();
    }

    public Screen(Graphics g)
    {
        bufferGraphics = g;
        //buffer = new BufferedImage(600,550, BufferedImage.TYPE_INT_RGB);
       // bufferGraphics = buffer.createGraphics();
    }


    public void draw(Image pImage,int x, int y)
    {
        bufferGraphics.drawImage(pImage, x, y, null);

        ////System.out.println("Drawing with coords: ("+x+":"+y+")");
    }


    public Graphics getBuffer()
    {
        return bufferGraphics;
    }

    public Image getImage()
    {
        return buffer;
    }


}
