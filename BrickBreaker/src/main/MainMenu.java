package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
   /* JLabel label1 = new JLabel("BRICK BREAKER");
    JLabel label2 = new JLabel("Enter Your Name");
    JTextField txtName = new JTextField();
    JButton btnPlay = new JButton("Play");
    JButton btnExit = new JButton("Exit");
    JButton btnScore = new JButton("aScore");*/
    
	private JLabel label1, label2;
	private JButton btnPlay, btnExit, btnScore;
	private JTextField txtName;
	
    public MainMenu(){
        setTitle("Brick Breaker");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        label1 = new JLabel("BRICK BREAKER");
        label1.setFont(new Font("arial", Font.BOLD, 32));
        
		label2 = new JLabel("Enter Your Name: ");
		label2.setFont(new Font("arial", Font.CENTER_BASELINE, 14));
		
		txtName = new JTextField(20);
		
		btnPlay = new JButton("Play");
		btnExit = new JButton("Exit");
		btnScore = new JButton("Score");
		
        JPanel panel1 = new JPanel(new GridLayout(3,5));
        JPanel panel2 = new JPanel(new GridLayout(3,5));
        JPanel panel3 = new JPanel(new GridLayout(3,5));
        
        panel1.add(label1);
        
        panel2.add(label2);
        panel2.add(txtName);
        
        panel3.add(btnPlay);
        panel3.add(btnScore);
        panel3.add(btnExit);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);
        add(panel3);
        setVisible(true);
        
        panel1.setBorder(BorderFactory.createEmptyBorder(50,110,0,100));
        panel2.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));
        panel3.setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
        
        setVisible(true);
       
        btnPlay.addActionListener(this);
        btnExit.addActionListener(this);
        btnScore.addActionListener(this);
    }

    public static void main(String[] args){
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
        try{
            if(e.getSource()== btnPlay){
            	
            	Main nw = new Main();
            	nw.setName(txtName);
            	nw.main(null);
            	
                this.dispose();
            }
            else if(e.getSource()== btnExit){
               dispose();
               return;
            }
            else if(e.getSource()== btnScore){
                scoreFrame sw = new scoreFrame();
                sw.main(null);
                this.dispose();
            }
        }
        catch (Exception e1){
            System.out.println(e1);
        }
    }
}
