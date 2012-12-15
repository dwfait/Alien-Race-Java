/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ClickableSprite extends Clickable {
    private Sprite sprite;
    public ClickableSprite(int pX, int pY, int w, int h, Sprite pSprite)
    {
        super(pX, pY, w, h);

        sprite = pSprite;
    }

    public void setVisible(boolean visible)
    {
        sprite.setVisible(visible);
    }

    public void draw(Screen screen)
    {
        sprite.draw(screen, getX(), getY());
    }


}
