package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI 
{
	private int score;
	private String name;
	
	public UI() //constructor
	{
		init();
	}
	
	public void init()
	{
		score = 0;
	}
	
	public void Draw(Graphics2D g)
	{
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 20, 40);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.setColor(Color.BLACK);
		g.drawString(this.getName(), 20, 20);
	}
	
	public void addScore(int scoreValue)
	{
		score += scoreValue;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getScore()
	{
		String scoreasString = Integer.toString(score);
		return scoreasString;
	}
}
