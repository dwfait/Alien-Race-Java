/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ViewStatePreGame extends ViewState{

    private ToggableSprite  btn99,btn1313,btn1717           ,currentSize;
    private ToggableSprite  btnEasy,btnNormal,btnPerfect    ,currentDifficulty;
    private ClickableSprite btnPlay;
    private Sprite          backGround;

    public ViewStatePreGame(ar.Game pGame, Screen screen, ImageFactory imgFac)
    {
        super(pGame,screen,imgFac);
        Sprite btnClicked = imgFac.getImage("buttonclicked.png");
        btn99       = new ToggableSprite(75,229,175,50,null,btnClicked);
        btn1313     = new ToggableSprite(75,300,175,50,null,btnClicked);
        btn1717     = new ToggableSprite(75,371,175,50,null,btnClicked);
        btnEasy     = new ToggableSprite(350,229,175,50,null,btnClicked);
        btnNormal   = new ToggableSprite(350,300,175,50,null,btnClicked);
        btnPerfect  = new ToggableSprite(350,371,175,50,null,btnClicked);
        btnPlay     = new ClickableSprite(206,465,175,50,imgFac.getImage("play.png"));
        backGround  = imgFac.getImage("pregame.png");
        btnPlay.setVisible(false);

    }



    public void startState()
    {
        btn99.reset();
        btn1313.reset();
        btn1717.reset();
        btnEasy.reset();
        btnNormal.reset();
        btnPerfect.reset();

        btnPlay.setVisible(false);

        currentSize = null;
        currentDifficulty = null;
        drawAll();
    }

    public void updateState()
    {

    }


    public void stopState()
    {

    }

    @Override
    public void leftDown(int x, int y,boolean fromMouse)
    {
        ToggableSprite sizeBtn = null,difficultyBtn = null;
        //System.out.println("Clickity clack: "+x+" "+y);
        if (btn99.click(x,y) == true) sizeBtn = btn99;
        if (btn1313.click(x,y) == true) sizeBtn = btn1313;
        if (btn1717.click(x,y) == true) sizeBtn = btn1717;

        if (btnEasy.click(x,y) == true) difficultyBtn = btnEasy;
        if (btnNormal.click(x,y) == true) difficultyBtn = btnNormal;
        if (btnPerfect.click(x,y) == true) difficultyBtn = btnPerfect;

        if (sizeBtn != null)
        {
            if (currentSize == null)
            {
                currentSize = sizeBtn;
            } else {
                if (currentSize != sizeBtn)
                {
                    currentSize.reset();
                    currentSize = sizeBtn;
                }
            }
        }
        if (difficultyBtn != null)
        {
            if (currentDifficulty == null)
            {
                currentDifficulty = difficultyBtn;
            } else {
                if (currentDifficulty != difficultyBtn)
                {
                    currentDifficulty.reset();
                    currentDifficulty = difficultyBtn;
                }
            }
        }


        if (currentDifficulty != null && currentSize != null)
        {
            btnPlay.setVisible(true);
            if (btnPlay.click(x, y))
            {
                ////System.out.println("LET'S PLAY!");
                int size = 0,difficulty = 0;

                if (btn99.getState() == true)
                    size = 1;
                if (btn1313.getState() == true)
                    size = 2;
                if (btn1717.getState() == true)
                    size = 3;
                if (btnEasy.getState() == true)
                    difficulty = 1;
                if (btnNormal.getState() == true)
                    difficulty = 2;
                if (btnPerfect.getState() == true)
                    difficulty = 3;

                game.setSettings(size,difficulty);
                game.startInstructions();
            }
        }
        drawAll();
    }

    public void drawAll()
    {
        backGround.draw(screen, 0, 0);
        btn99.draw(screen);
        btn1313.draw(screen);
        btn1717.draw(screen);
        btnEasy.draw(screen);
        btnNormal.draw(screen);
        btnPerfect.draw(screen);
        btnPlay.draw(screen);

    }

}
