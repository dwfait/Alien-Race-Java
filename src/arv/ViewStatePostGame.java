/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ViewStatePostGame extends ViewState {
    Clickable playAgain;
    Sprite bg,winOrLose;
    arc.Controller controller;
    Clickable highScore;
    public ViewStatePostGame(ar.Game pGame, Screen screen, ImageFactory imgFac,arc.Controller pController)
    {
        super(pGame,screen,imgFac);
        controller = pController;
        playAgain = new Clickable(157,437,286,87);

        bg = imgFac.getImage("postgame.png");
    }



    public void startState()
    {
        if (game.getHumanScore() < game.getAIScore())
        {
            winOrLose = imgFactory.getImage("youlose.png");
        }
        else
        {
            winOrLose = imgFactory.getImage("youwin.png");
        }
        drawAll();
    }

    public void updateState()
    {

    }

    public void leftDown(int x, int y, boolean fromMouse)
    {
        if (playAgain.click(x,y)==true)
            game.startPreGame();
        //if (highScore.click(x,y)==true)
        //    controller.submitHighScore(10);

    }

    public void stopState()
    {

    }

    public void drawAll()
    {
        bg.draw(screen, 0, 0);
        winOrLose.draw(screen,85,79);
    }
}
