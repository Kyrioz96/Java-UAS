package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class scoreFrame extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dialogButton = JOptionPane.YES_NO_OPTION;
	Database db = new Database();
	JButton btnBack = new JButton("Back");
	
	public scoreFrame() throws Exception
	{
			setTitle("High Score");
			setSize(400, 400);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			Vector<Vector<Object>> data = null;
			Vector<Object> columnNames = new Vector<Object>();
			columnNames.add("Name");
			columnNames.add("High Score");
			
			data = db.selectScore();
				
			JPanel panel1 = new JPanel(new GridLayout(1, 250));
			JPanel panel2 = new JPanel(new GridLayout(3,5));
			
			JTable table = new JTable(data,columnNames);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			table.setEnabled(true);	
			
			
			btnBack.addActionListener(this);

			//panel1.add(lb);
			panel1.add(scrollPane, BorderLayout.CENTER);
			panel1.setBorder(BorderFactory.createEmptyBorder());
			panel1.setBorder(BorderFactory.createTitledBorder("High Score"));
		

			panel2.add(btnBack);
			
			setLayout(new GridLayout(2, 1));
			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
			
			add(panel1);
			add(panel2);
			setVisible(true);
		}
		

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource()==btnBack)				
			{
				{
					try 
					{
						MainMenu nw = new MainMenu();
						nw.main(null);
						this.dispose();
					} 
					
					catch (Exception e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
	}
		
	public static void main(String args[]) throws Exception
	{
		new scoreFrame();
	}
}
