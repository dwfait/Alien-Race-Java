/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ToggableSprite {
    private Clickable clickable;
    private Sprite spriteUnclicked,spriteClicked,currentSprite;
    private boolean state;

    public ToggableSprite(int x, int y, int w, int h,Sprite unclicked,Sprite clicked)
    {
        spriteUnclicked = unclicked;
        spriteClicked = clicked;
        currentSprite = unclicked;
        clickable = new Clickable(x, y, w, h);
        state = false;
    }


    public boolean click(int x, int y)
    {
        boolean result = clickable.click(x,y);
        if (result == true)
        {
            state = !state;
            updateSprite();
        } 
        //return state;
        return result;
    }

    public boolean getState()
    {
        return state;
    }
    private void updateSprite()
    {
        if (state == false)
        {
            currentSprite = spriteUnclicked;
        } else {
            currentSprite = spriteClicked;
        }

    }
    public void reset()
    {
        state = false;
        updateSprite();
    }

    public void draw(Screen screen)
    {
        if (currentSprite != null)
            currentSprite.draw(screen, clickable.getX(), clickable.getY());
    }
}
