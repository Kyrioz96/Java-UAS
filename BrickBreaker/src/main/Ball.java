package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball 
{
	private float x; //lokasi x
	private float y; //lokasi y
	private float xdir; //arah x
	private float ydir; //arah y
	private int ballsize = 20; //besar bola
	
	public Ball() //constructor
	{
		x = 200;
		y = 200;
		xdir = 1;
		ydir = 3;	//assign value dari masing-masing variable di constructor Ball
	};
	
	public void Update()
	{
		setPos();
	}
	
	public void setPos() //method untuk posisi bola
	{
		x += xdir;
		y += ydir;
		
		if(x < 0) //jika bola menyentuh sisi kiri
		{
			xdir = -xdir;
		}
		
		if(y < 0) //jika bola menyentuh sisi bawah
		{
			ydir = -ydir;
		}
			
		if(x > Main.WIDTH - ballsize) //jika bola menyentuh sisi kanan
		{
			xdir = -xdir;
		}
		
		if(y > Main.HEIGHT - ballsize) //jika bola menyentuh sisi atas
		{
			ydir = -ydir;
		}
	}
	
	public void Draw(Graphics2D g) //menggunakan draw dari graphics2D
	{
		g.setColor(Color.DARK_GRAY);
		g.setStroke(new BasicStroke(4));
		g.drawOval((int)x, (int)y, ballsize, ballsize);
	}
	
	public Rectangle getBallDetector()
	{
		return new Rectangle((int) x, (int) y, ballsize, ballsize);
	}
	
	public void setXdir(float dirX)
	{
		xdir = dirX;
	}
	
	public float getXdir()
	{
		return xdir;
	}
	
	public void setYdir(float dirY)
	{
		ydir = dirY;
	}
	
	public float getYdir()
	{
		return ydir;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public boolean isLose()
	{
		boolean lose = false;
		
		if(this.y > Main.HEIGHT - ballsize * 2)
		{
			lose = true;
		}
		
		return lose;
	}
}
