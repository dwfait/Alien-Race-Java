/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ViewStatePlaying extends ViewState{

    
    private GridView gridView;

    private ScoreView human,computer,aliensRemaining;

    private Sprite turn;
    private Clickable newGame;

    
    public ViewStatePlaying(ar.Game pGame, Screen screen, ImageFactory imgFac)
    {
        super(pGame,screen,imgFac);

        gridView = new GridView(imgFac);

        human = new ScoreView(499,98,549,98,imgFac);
        computer = new ScoreView(498,192,548,192,imgFac);
        aliensRemaining = new ScoreView(498,293,548,293,imgFac);
        newGame = new Clickable(520,495,50,36);
    }



    public void startState()
    {
        //System.out.println("OH HI THERE!");
        gridView.createView(game.getGrid());

        updateWhosTurn();
        drawAll();
        game.startTimer();
    }

    public void leftDown(int x, int y, boolean fromMouse)
    {
        if (game.getIsHumanPlaying() == fromMouse)
        {
            gridView.leftDown(x, y, screen);

        }
        if (newGame.click(x,y) == true)
                game.startPreGame();
    }

    public void leftUp(int x, int y, boolean fromMouse)
    {

        if (game.getIsHumanPlaying() == fromMouse)
        {

            if (gridView.leftUp(x, y, screen) == true)
            {
                ar.Point2D square;
                square = gridView.squareCoordFromMouse(x, y);
                game.updateCurrentPlayerState(square.x, square.y);
            }
        }
    }

    public void updateState()
    {
        gridView.update(screen);

        human.updateScore(game.getHumanScore());
        computer.updateScore(game.getAIScore());
        aliensRemaining.updateScore(game.getRemaining());
        human.draw(screen);
        computer.draw(screen);
        
        aliensRemaining.draw(screen);
        updateWhosTurn();
        turn.draw(screen,531,396);
    }


    public void stopState()
    {

    }

    public void drawAll()
    {
        gridView.draw(screen);
        human.draw(screen);
        computer.draw(screen);
        aliensRemaining.draw(screen);
        turn.draw(screen,531,396);
    }

    private void updateWhosTurn()
    {
        if (game.getIsHumanPlaying() == true)
        {
            turn = imgFactory.getImage("yourturn.png");
        } else{
            turn = imgFactory.getImage("notyourturn.png");
        }

    }

    public GridView getGridView()
    {
        return gridView;
    }
}
