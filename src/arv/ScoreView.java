/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;

/**
 *
 * @author Dwain
 */
public class ScoreView {
    private int x1,x2,y2,y1;
    private Sprite score1,score2;
    private ImageFactory imgFac;
    public ScoreView(int pX1, int pY1, int pX2, int pY2,ImageFactory imgFactory)
    {
        x1 = pX1;
        x2 = pX2;
        y1 = pY1;
        y2 = pY2;
        imgFac = imgFactory;

        score1 = imgFac.getImage("Numbers/0.png");
        score2 = imgFac.getImage("Numbers/0.png");
    }


    public void draw(Screen screen)
    {
        score1.draw(screen, x1, y1);
        score2.draw(screen, x2, y2);
    }

    public void updateScore(int s)
    {
        score1 = imgFac.getImage("Numbers/"+(s/10)+".png");
        score2 = imgFac.getImage("Numbers/"+(s%10)+".png");
    }



}
