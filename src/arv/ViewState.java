/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
abstract public class ViewState {
    protected ar.Game game;
    protected Screen screen;
    protected ImageFactory imgFactory;

    public ViewState(ar.Game pGame, Screen pScreen, ImageFactory pImgFac)
    {
        game = pGame;
        screen = pScreen;
        imgFactory = pImgFac;
    }

    abstract public void startState();
    abstract public void updateState();
    abstract public void stopState();
    abstract public void drawAll();


    public void leftClick(int x, int y)
    {

    }
    public void leftDown(int x, int y, boolean fromMouse)
    {

    }
    public void leftUp(int x, int y, boolean fromMouse)
    {

    }
}
