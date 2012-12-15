/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arc;

import java.awt.FlowLayout;
import javax.swing.JApplet;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


import java.util.Timer;
//import java.awt.Image;
/**
 *
 * @author Dwain
 */
public class App extends JApplet implements MouseListener {


 
    private Timer timer;
    private Controller controller;
    private arv.Screen screen;
    private DrawingPanel drawingPanel;

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.setSize(600,550);
        this.setIgnoreRepaint( true );
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
        controller = new Controller(this);
        drawingPanel.setScreen(controller.getScreen());
        drawingPanel.setup();
    }

    @Override
    public void start()
    {
        //System.out.println("start()");
        //screen = controller.getScreen();
        addMouseListener(this);

        controller.run();
        timer = new Timer(false);
        timer.scheduleAtFixedRate(controller,1, 20);


        
        //controller.redraw();
    }

    public Graphics getScreen()
    {
        //return getGraphics();
        return drawingPanel.getGraphics();
    }

//    @Override
//    public void paint(Graphics g)
//    {
//
//        g.drawImage(screen.getImage(), 0, 0, this);
//        this.paintComponents(g);
//        //Image img  = getImage(getCodeBase(),"Images/0.png");
//        //g.drawImage(img, 0, 0, this);
//    }
//    public void update(Graphics g)
//    {
//        paint(g);
//    }

    public void stop()
    {

    }
    // TODO overwrite start(), stop() and destroy() methods


    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            controller.leftClick(e.getX(),e.getY());
            //System.out.println("Clickity clack: "+e.getX()+" "+e.getY());
        }
        e.consume();
    }
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //System.out.println("Down: "+e.getX()+" "+e.getY());
            controller.leftDown(e.getX(),e.getY());
        }
        e.consume();
    }
    public void mouseReleased(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //System.out.println("Up: "+e.getX()+" "+e.getY());
            controller.leftUp(e.getX(),e.getY());
        }
        e.consume();
    }
    public void mouseEntered(MouseEvent e)
    {

    }
    public void mouseExited(MouseEvent e)
    {

    }
    public void startHighScore(long score,int size,int difficulty)
    {
        drawingPanel.startHighScore(score,size,difficulty);

    }
    public void stopHighScore()
    {
        drawingPanel.stopHighScore();

    }

}
