/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar;

/**
 *
 * @author Dwain
 */
public class Game {
    private Player aiPlayer;
    private Player humanPlayer;
    private Player currentPlayer;
    private int columns,rows,difficulty,size;
    private Grid grid;
    private int state;

    private long gameTimer,gameTime;


    public static final int PREGAME = 0,PLAYING = 1,POSTGAME = 2,INSTRUCTIONS = 3;

    public Game()
    {
        grid = new Grid();
        aiPlayer = new AIPlayer(grid);
        humanPlayer = new HumanPlayer(grid);
        //humanPlayer = new AIPlayer(grid);
        state = PREGAME;

    }

    public int getState()
    {
        return state;
    }

    public void setSettings(int pSize, int pDifficulty)
    {
        size = pSize;
        if (size == 1) { columns = 9; rows = 9;}
        if (size == 2) { columns = 13; rows = 13;}
        if (size == 3) { columns = 17; rows = 17;}
        difficulty = pDifficulty;
    }
    public void startGame()
    {
        if (columns != 0 && rows != 0 && difficulty != 0)
        {
            startGame(columns,rows,difficulty);
        } else {
            startGame(17,17,3);
        }
    }
    public void startGame(int pColumns,int pRows,int pDifficulty)
    {
        aiPlayer.reset();
        humanPlayer.reset();

        currentPlayer = humanPlayer;
        currentPlayer.setTurn(true);
        grid.populateGrid(columns, rows);

        state = PLAYING;

        if (aiPlayer instanceof AIPlayer)
        {
            ((AIPlayer)aiPlayer).setDifficulty(pDifficulty);
        }
        if (humanPlayer instanceof AIPlayer)
        {
            ((AIPlayer)humanPlayer).setDifficulty(pDifficulty);
        }
    }

    public void update()
    {
        if (state == PLAYING)
        {
            if ( currentPlayer.getScore() > (grid.getBombs()/2))
            {
                stopTimer();
                state = POSTGAME;
            }

            if (currentPlayer.move())
            {
                swapPlayers();
            }
        }
    }

    public void swapPlayers()
    {
            ////System.out.println(Integer.toString(getAIScore()));
            ////System.out.println(Integer.toString(getHumanScore()));
        currentPlayer.setTurn(false);
        if(currentPlayer == humanPlayer)
        {
            currentPlayer = aiPlayer;
        } else {
            currentPlayer = humanPlayer;
        }
        currentPlayer.swap();
        currentPlayer.setTurn(true);
    }

    public int getHumanScore()
    {
        return humanPlayer.score;
    }

    public int getAIScore()
    {
        return aiPlayer.score;
    }

    public Grid getGrid()
    {
        return grid;
    }


    public boolean getIsHumanPlaying()
    {
        return (currentPlayer == humanPlayer) && humanPlayer instanceof HumanPlayer;
    }


    public void startInstructions()
    {
        state = INSTRUCTIONS;
    }

    public void startPreGame()
    {
        state = PREGAME;
    }


    public void updateCurrentPlayerState(int x, int y)
    {
        //if (humanPlayer instanceof HumanPlayer)
        //{
            currentPlayer.setState(x,y);
        //}
    }

    public int getRemaining()
    {
        return grid.getRemainingBombs();

    }
    public void setAIPlayerViews(arv.View pView,arv.GridView gridView)
    {
        if (aiPlayer instanceof AIPlayer)
        {
            ((AIPlayer)aiPlayer).setViews(gridView, pView);
        }
        if (humanPlayer instanceof AIPlayer)
        {
            ((AIPlayer)humanPlayer).setViews(gridView, pView);
        }
    }


    public void startTimer()
    {
        gameTimer = System.currentTimeMillis();
    }

    public void stopTimer()
    {
        gameTime = System.currentTimeMillis() - gameTimer;
    }

    public int getSize()
    {
        return size;
    }

    public int getDifficulty()
    {
        return difficulty;
    }

    public long getScore()
    {
        return ((difficulty * size) * (20 * (26+(getHumanScore() - getAIScore())))) + ((size * 50));
    }

}
