package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Board 
{
	private float x;
	private int width;
	private int height;
	
	public final int posY = Main.HEIGHT - 75;
	
	public Board() //constructor
	{ 
		width = 100;
		height = 20;
		x = Main.WIDTH / 2 - width / 2; //memposisikan papan di tengah layar
	}
	
	public void Update()
	{
		
	}
	
	public void Draw(Graphics2D g) //menggambar papan
	{
		g.setColor(Color.RED);
		g.fillRect((int) x, posY, width, height);
	}
	
	public void mouseMoves(int mouseX) //mouse listener untuk papan
	{
		x = mouseX;
		
		if (x > Main.WIDTH - width)
		{
			x = Main.WIDTH - width;
		}
	}
	
	public Rectangle getBoardDetector()
	{
		return new Rectangle((int) x, (int) posY, width, height);
	}
}
