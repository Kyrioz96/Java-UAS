package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Level 
{
	private int[][] stage;
	private int brickHeight;
	private int brickWidth;
	
	public final int horizontal = 80;
	public final int vertical = 50;
	
	public Level(int row, int col)
	{
		initLevel(row, col);
		
		brickWidth = (Main.WIDTH - 2 * horizontal) / col;
		brickHeight = (Main.HEIGHT / 2 - vertical * 2) / row;
	}
	
	public void initLevel(int row, int col) //initialize level
	{
		stage = new int[row][col];
		
		for (int i = 0; i < stage.length; i++)
		{
			for (int j = 0; j < stage[0].length; j++)
			{
				stage[i][j] = 1;
			}
		}
	}
	
	public void Draw(Graphics2D g) //menggambar balok-balok
	{
		for (int i = 0; i < stage.length; i++) 
		{
			for(int j = 0; j < stage[0].length; j++)
			{
				if(stage[i][j] > 0)
				{
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j * brickWidth + horizontal, i * brickHeight + vertical, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawRect(j * brickWidth + horizontal, i * brickHeight + vertical, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrick(int row, int col, int v)
	{
		stage[row][col] = v;
	}
	
	public int[][] getLevel()
	{
		return stage;
	}
	
	public int getBrickWidth()
	{
		return brickWidth;
	}
	
	public int getBrickHeight()
	{
		return brickHeight;
	}
	
	public void brickDetector(int row, int col) //method untuk mendeteksi balok mana yang terkena bola
	{
		stage[row][col]--;
		
		if (stage[row][col] < 0) //memastikan value brick tidak kurang dari 0
		{
			stage[row][col] = 0;
		}
	}
	
	public boolean isDone()
	{
		boolean win = false;
		
		int remainingBricks = 0;
		
		for(int row = 0; row < stage.length; row++)
		{
			for(int col = 0; col < stage[0].length; col++)
			{
				remainingBricks += stage[row][col];
			}
		}
		
		if (remainingBricks == 0)
		{
			win = true;
		}
		
		return win;
	}
}
