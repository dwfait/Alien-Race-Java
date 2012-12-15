/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arv;


import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Hashtable;
/**
 *
 * @author Dwain
 */
public class ImageFactory {
    private URL codeBase;
    private Hashtable imageCache;

    private String folder;

    public ImageFactory(String pFolder)
    {
        imageCache = new Hashtable(30);
        if (pFolder != null)
        {
            folder = pFolder + "/Images/";
        } else {
            folder = "Images/";
        }

    }

    public void setCodeBase(URL pCodeBase)
    {
        codeBase = pCodeBase;
    }
    
    public Sprite getImage(String pPath)
    {
        BufferedImage img;
        img = (BufferedImage)imageCache.get(pPath);

        ////System.out.println(imageCache.containsKey(pPath));
        
        if (img == null)
        {
            try {

                URL url = new URL(codeBase,folder+ pPath);
                img = ImageIO.read(url);

                imageCache.put(pPath, img);

                ////System.out.println(tmp);
                ////System.out.println(img.getHeight(null));
                //return img;
                Sprite sprite = new Sprite();
                sprite.setImage(img);

                return sprite;
            } catch (IOException e) {
            }
        } else {
            Sprite sprite = new Sprite();
            sprite.setImage(img);
            ////System.out.println("Returning cached image");
            return sprite;
        }
        //System.out.println("Can not load image: "+codeBase.toString()+pPath);
        return null;
    }






    
}
