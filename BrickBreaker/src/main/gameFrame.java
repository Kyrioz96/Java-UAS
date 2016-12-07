package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.JPanel;

public class gameFrame extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isRunning;
	private BufferedImage image;
	private Graphics2D g; //variabel untuk render image
	private thisMouseMotionListener mouse; //variable untuk mouse listener
	private String name = Main.getName();
	
	Database db;
	Ball bola;
	Board papan;
	Level stage;
	UI userInterface;
	
	public gameFrame() //constructor
	{
		init();
	}
	
	public void init() //method untuk initialize awal
	{ 
		bola = new Ball();
		
		papan = new Board();
		
		stage = new Level(6, 10);
		
		userInterface = new UI();
		
		userInterface.setName(name);
		
		mouse = new thisMouseMotionListener();
		
		addMouseMotionListener(mouse);
		
		isRunning = true;
		
		image = new BufferedImage(Main.WIDTH,Main.HEIGHT,BufferedImage.TYPE_INT_RGB); //setting tinggi,lebar,dan tipe warna RGB pada frame
		
		g = (Graphics2D) image.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //untuk mengaktifkan antialiasing
	}

	@Override
	public void run() //method untuk looping game
	{
		// TODO Auto-generated method stub
		
		while(isRunning)
		{
			//update setiap frame
			try {
				Update();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//rendering gambar
			Draw();	
					
			repaint(); //class untuk menggambar ulang frame
			
			try
			{
				Thread.sleep(8); //agar bola tidak terlalu cepat
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		new MainMenu();
	}
	
	public void Update() throws SQLException
	{
		collisionChecker();
		bola.Update();
		
		if (stage.isDone() == true)
		{
			showWin();
			db.insertScore(name, userInterface.getScore());
			isRunning = false;
		}
		
		if (bola.isLose() == true)
		{
			showLose();		
			db.insertScore(name, userInterface.getScore());
			isRunning = false;
		}
	}
	
	public void Draw()
	{
		//draw background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		bola.Draw(g); //menggambar bola
		papan.Draw(g); //menggambar papan
		stage.Draw(g); //menggambar balok
		userInterface.Draw(g); //menggambar UI
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, null); //menggambar frame
		
		g2.dispose(); //menghilangkan frame dari memori setelah program ditutup
	}
	
	private class thisMouseMotionListener implements MouseMotionListener //method untuk mouse listener
	{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			papan.mouseMoves(e.getX());
		}
		
	}
	
	public void collisionChecker()
	{
		Rectangle ballRect = bola.getBallDetector();
		Rectangle boardRect = papan.getBoardDetector();
		
		if(ballRect.intersects(boardRect))
		{
			bola.setYdir(-bola.getYdir());
		}
		
		for (int row = 0; row < stage.getLevel().length; row++)
		{
			for (int col = 0; col < stage.getLevel()[0].length; col++)
			{
				if (stage.getLevel()[row][col] > 0)
				{
				int brickX = col * stage.getBrickWidth() + stage.horizontal;
				int brickY = row * stage.getBrickHeight() + stage.vertical;
				int brickWidth = stage.getBrickWidth();
				int brickHeight = stage.getBrickHeight();
				
				Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
				
				if (ballRect.intersects(brickRect))
				{
					if (bola.getY() >= brickHeight / 2)
					{
						stage.brickDetector(row, col);
					
						bola.setYdir(-bola.getYdir());
					
						userInterface.addScore(50);
					}
					
					else if (bola.getY() <= brickHeight / 2)
					{
						stage.brickDetector(row, col);
					
						bola.setYdir(-bola.getYdir());
					
						userInterface.addScore(50);
					}
					
					else if (bola.getX() >= brickWidth / 2)
					{
						stage.brickDetector(row, col);
						
						bola.setYdir(-bola.getXdir());
					
						userInterface.addScore(50);
					}
					
					else if (bola.getX() <= brickWidth / 2)
					{
						stage.brickDetector(row, col);
						
						bola.setYdir(-bola.getXdir());
					
						userInterface.addScore(50);
					}
					break;
				}
				
				
			}
		}
		}
	}
	
	public void showWin()
	{
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("YOU WIN!", 200, 200);
	}
	
	public void showLose()
	{
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("YOU LOSE!", 200, 200);
	}
	
	
}
