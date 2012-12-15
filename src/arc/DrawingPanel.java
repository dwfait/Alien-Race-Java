/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arc;

import java.awt.Graphics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JPanel;

import javax.swing.SwingWorker;
/**
 *
 * @author Dwain
 */
public class DrawingPanel extends JPanel {

    private arv.Screen screen;

    public void setup()
    {

        this.setSize(600,550);
        this.setLayout(null);

        clrYellow = new java.awt.Color(255, 255, 51);
        clrGreen = new java.awt.Color(51, 255, 51);
        clrRed = new java.awt.Color(255, 51, 51);
        font = new java.awt.Font("Candara", 0, 18);

        txtName = new javax.swing.JTextField(12);
        txtName.setBounds(227,380,100,20);
        txtName.setVisible(false);
        btnSubmit = new javax.swing.JButton("Submit");
        btnSubmit.setBounds(330,380,100,20);
        btnSubmit.setVisible(false);
        lblName = new javax.swing.JLabel("Enter name:");
        lblName.setFont(font); // NOI18N
        lblName.setForeground(clrGreen);
        lblName.setBounds(227,360,100,20);
        lblName.setVisible(false);
        lblScore = new javax.swing.JLabel("Your score: WHAT");
        lblScore.setFont(font); // NOI18N
        lblScore.setForeground(clrGreen);
        lblScore.setBounds(227,330,200,20);
        lblScore.setVisible(false);
        lblResult = new javax.swing.JLabel("RESULT");
        lblResult.setFont(font); // NOI18N
        lblResult.setForeground(clrGreen);
        lblResult.setBounds(150,410,300,20);
        lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResult.setVisible(false);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitHighScore(evt);
            }
        });
        add(lblResult);
        add(lblScore);
        add(btnSubmit);
        add(lblName);
        add(txtName);

        
    }

    public void setScreen(arv.Screen pScreen)
    {
        screen = pScreen;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(screen.getImage(), 0, 0, this);
//        paintComponents(g);
    }

    public void startHighScore(long score,int size,int difficulty)
    {
        this.score = Long.toString(score);
        this.difficulty = Integer.toString(difficulty);
        this.size = Integer.toString(size);
        lblScore.setText("Your score: " + this.score);
        lblResult.setText("");
        txtName.setText("");
        txtName.setVisible(true);
        btnSubmit.setEnabled(true);
        txtName.setEnabled(true);
        btnSubmit.setVisible(true);

        lblName.setVisible(true);

        lblScore.setVisible(true);

        lblResult.setVisible(true);
    }


    public void stopHighScore()
    {
        txtName.setVisible(false);

        btnSubmit.setVisible(false);

        lblName.setVisible(false);

        lblScore.setVisible(false);

        lblResult.setVisible(false);
    }


    private void submitHighScore(java.awt.event.ActionEvent evt)
    {
        if (txtName.getText().length() > 12 || txtName.getText().length() < 3)
        {
            lblResult.setText("Name must be 3 to 12 characters");
        } else {
            lblResult.setText("Working...");
            lblResult.setForeground(clrYellow);
            btnSubmit.setEnabled(false);
            txtName.setEnabled(false);
            txtName.setText(stripGarbage(txtName.getText()));
            new HighScoreSubmit().execute();
                
        }
    }

  public String stripGarbage(String s) {
    String good =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String result = "";
    for ( int i = 0; i < s.length(); i++ ) {
        if ( good.indexOf(s.charAt(i)) >= 0 )
           result += s.charAt(i);
        }
    return result;
    }

    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblResult;
    private javax.swing.JTextField txtName;
    private java.awt.Color clrGreen,clrRed,clrYellow;
    private java.awt.Font font;
    private String score,size,difficulty;



       class HighScoreSubmit extends SwingWorker<Integer, Object> {

           @Override
           public Integer doInBackground() {
               try {
                    // Create a URL for the desired page
                   String address = "http://www.someonestudios.com/hsadd.php?name="+txtName.getText();
                   address = address + "&score="+score+"&size="+size+"&difficulty="+difficulty;

                    URL url = new URL(address);
                    System.out.println(address);
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//                    while ((str = in.readLine()) != null) {
//                        System.out.println(str);
//                    }
                    in.close();
                    System.out.println("done");
                    return 1;
                } catch (Exception e) {
                    
                    return 0;
                }
            //return 0;
           }

           @Override
           protected void done() {
               try {
                   int result = get();

                   if (result == 1)
                   {
                        lblResult.setText("Success.");
                        lblResult.setForeground(clrGreen);
                        btnSubmit.setEnabled(false);
                        txtName.setEnabled(false);
                   } else {
                        lblResult.setText("Error.");
                        lblResult.setForeground(clrRed);
                        btnSubmit.setEnabled(true);
                        txtName.setEnabled(true);
                   }

               } catch (Exception ignore) {
               }
           }
     }




}
