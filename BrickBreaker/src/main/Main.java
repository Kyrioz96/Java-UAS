package main;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main 
{
	public static final int WIDTH = 640; //variabel lebar frame
	public static final int HEIGHT = 480; //variabel tinggi frame
	private static JTextField name;
	
	public static void main(String[] args)
	{
		JFrame bbFrame = new JFrame("Brick Breaker"); //membuat frame utama
		
		gameFrame bbPanel = new gameFrame();
		
		Thread t = new Thread(bbPanel);
		
		bbFrame.setLocationRelativeTo(null); //membuat frame di tengah layar
		bbFrame.setResizable(false); //supaya besar frame tidak bisa diubah-ubah
		bbFrame.setSize(WIDTH, HEIGHT); //set besar frame menggunakan variabel di atas
		
		bbFrame.add(bbPanel);
		
		t.start(); //memulai thread bbPanel
		
		bbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default action ketika program ditutup
		bbFrame.setVisible(true); //membuat frame terlihat di layar
	}
	public void setName(JTextField n)
	{
		name = n;
	}
	
	public static String getName()
	{
		return name.getText();
	}
}
