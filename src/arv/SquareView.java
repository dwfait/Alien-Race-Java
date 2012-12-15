/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */



public class SquareView {

    private ar.Square square;
    private Sprite image;
    private int x,y;
    private boolean mouseDown;

    public SquareView(ar.Square pSquare,ImageFactory pImgFac)
    {
        square = pSquare;
        mouseDown = false;
        update(pImgFac);
    }

    public void update(ImageFactory imgFactory)
    {
        if (square.covered == false)
        {
            if (square.bomb == true)
            {
                image = imgFactory.getImage("bomb.png");
            } else {
                String tmpPath = square.surroundingBombs+".png";

                image = imgFactory.getImage(tmpPath);
            }
        } else {
            if (mouseDown == true)
            {
                image = imgFactory.getImage("clicked.png");
            } else {
                image = imgFactory.getImage("uncovered.png");
            }
        }
        //screen.draw(image, position);
        //draw(screen);
    }
    public void draw(Screen screen)
    {
        image.draw(screen,x,y);
    }

    public void setPosition(int pX, int pY)
    {
        //image.setPosition(x, y);
        x = pX;
        y = pY;
    }

    public void leftDown()
    {
        mouseDown = true;
        //System.out.println("MOUSE DOWN ON SQUARE");
    }

    public void leftUp()
    {
        mouseDown = false;
    }
}
