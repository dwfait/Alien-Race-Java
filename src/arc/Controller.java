/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arc;

import java.net.MalformedURLException;
import java.util.TimerTask;
import java.net.URL;
/**
 *
 * @author Dwain
 */
public class Controller extends TimerTask {
    private arv.View view;
    private ar.Game game;

    App applet;
    public Controller(App pApplet)
    {
        applet = pApplet;
        game = new ar.Game();
        //game.startGame(17, 17, 3);

        view = new arv.View(game,applet.getCodeBase(),applet.getScreen(),applet.getParameter("base"),this);
        game.setAIPlayerViews(view, view.getGridView());
        
    }

    public void run()
    {
        update();
    }
    public void update()
    {
        ////System.out.println("Update");
        game.update();
        view.update();
        applet.repaint();
        Thread.yield();
        /**try
        {
            Thread.sleep(10); // do nothing for 1000 miliseconds (1 second)
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }**/

    }


    public void redraw()
    {

        view.draw();
    }


    public void leftClick(int x, int y)
    {
        view.leftClick(x, y);
    }
    public void leftDown(int x, int y)
    {
        view.leftDown(x, y,true);
    }
    public void leftUp(int x, int y)
    {
        view.leftUp(x, y,true);
    }

    public arv.Screen getScreen()
    {
        return view.getScreen();
    }
    
    public void startHighScore(long score,int size, int difficulty)
    {
        applet.startHighScore(score,size,difficulty);

    }
    public void stopHighScore()
    {
        applet.stopHighScore();

    }

}
