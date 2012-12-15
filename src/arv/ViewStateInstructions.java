/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ViewStateInstructions extends ViewState {

    private Clickable play;
    private Sprite backGround;
    public ViewStateInstructions(ar.Game pGame, Screen screen, ImageFactory imgFac)
    {
        super(pGame,screen,imgFac);

        play = new Clickable(212,471,175,50);
        backGround = imgFac.getImage("instructions.png");
    }



    public void startState()
    {
        drawAll();
    }

    public void updateState()
    {

    }


    public void stopState()
    {

    }

    public void leftDown(int x, int y, boolean fromMouse)
    {
        if (play.click(x, y) == true)
            game.startGame();

    }
    public void drawAll()
    {
        backGround.draw(screen, 0,0);
    }
}
