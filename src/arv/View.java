/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */

import java.net.URL;
import java.awt.Graphics;
public class View {
    private ar.Game game;

    private Screen screen;
    private ImageFactory imgFactory;
    private arc.Controller controller;
    private ViewState instructionsState,preGameState,playingState,postGameState,currentState;
    private int currentStateInt;
    public View(ar.Game pGame, URL codeBase,Graphics g,String pFolder,arc.Controller controller)
    {
        game = pGame;
        //screen = new Screen(g);
        screen = new Screen();
        imgFactory = new ImageFactory(pFolder);
        imgFactory.setCodeBase(codeBase);
        cacheImages();
        playingState        = new ViewStatePlaying(game, screen, imgFactory);
        instructionsState   = new ViewStateInstructions(game, screen, imgFactory);
        preGameState        = new ViewStatePreGame(game, screen, imgFactory);
        postGameState       = new ViewStatePostGame(game, screen, imgFactory,controller);

        //currentState = playingState;
        currentStateInt = -1;
        this.controller = controller;
    }


    public void update()
    {
        updateState();
        currentState.updateState();


    }

    public void updateState()
    {
        if (currentStateInt != game.getState())
        {
            if (currentState != null) currentState.stopState();
            controller.stopHighScore();
            switch (game.getState())
            {
                case ar.Game.INSTRUCTIONS:
                    currentState = instructionsState;
                    break;
                case ar.Game.PREGAME:
                    currentState = preGameState;
                    break;
                case ar.Game.PLAYING:
                    currentState = playingState;
                    break;
                case ar.Game.POSTGAME:
                    controller.startHighScore(game.getScore(),game.getSize(),game.getDifficulty());
                    currentState = postGameState;
                    break;
            }
            currentStateInt = game.getState();
            currentState.startState();
            currentState.drawAll();
        }
        

    }

    public void draw()
    {
        currentState.drawAll();
        //System.out.println("REDRAW");
    }

    public Screen getScreen()
    {
        return screen;
    }

    public void leftClick(int x, int y)
    {
        currentState.leftClick(x, y);
    }
    public void leftDown(int x, int y, boolean fromMouse)
    {
        currentState.leftDown(x, y, fromMouse);
    }
    public void leftUp(int x, int y, boolean fromMouse)
    {
        currentState.leftUp(x, y, fromMouse);
    }

    private void cacheImages()
    {
        //System.out.println("Starting cache");
        imgFactory.getImage("0.png");imgFactory.getImage("1.png");imgFactory.getImage("2.png");imgFactory.getImage("3.png");
        imgFactory.getImage("4.png");imgFactory.getImage("5.png");imgFactory.getImage("6.png");imgFactory.getImage("7.png");
        imgFactory.getImage("8.png");imgFactory.getImage("uncovered.png");imgFactory.getImage("bomb.png");imgFactory.getImage("clicked.png");
        imgFactory.getImage("yourturn.png");imgFactory.getImage("notyourturn.png");
        imgFactory.getImage("youlose.png");imgFactory.getImage("youwin.png");
        imgFactory.getImage("pregame.png");
        imgFactory.getImage("gamescreen.png");
        imgFactory.getImage("postgame.png");
        imgFactory.getImage("buttonclicked.png");
        imgFactory.getImage("play.png");
        imgFactory.getImage("instructions.png");
        for (int i = 0; i < 10; ++i)
        {
            imgFactory.getImage("Numbers/"+i+".png");
        }
        //System.out.println("Ending cache");
    }

    public GridView getGridView()
    {
        return ((ViewStatePlaying)playingState).getGridView();
    }
}
