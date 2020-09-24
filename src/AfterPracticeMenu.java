import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AfterPracticeMenu extends JFrame {

	private JPanel contentPane;
	private static int lastscore;
	
	static AfterPracticeMenu frame;
	/**
	 * Launch the application.
	 */
	public static void mainmethod(int lscore) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lastscore = lscore;
					frame = new AfterPracticeMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AfterPracticeMenu() {
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 479);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGreatJobYour = new JLabel("Great Job! Your final score was:");
		lblGreatJobYour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGreatJobYour.setBounds(308, 16, 291, 20);
		contentPane.add(lblGreatJobYour);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setForeground(Color.BLACK);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblScore.setText(lastscore + " (" + String.valueOf((int) Math.round((((double)lastscore)/8000)*100)) + "%)");
		lblScore.setBounds(257, 91, 389, 194);
		contentPane.add(lblScore);
		
		JButton btnRestart = new JButton("HOME");
		btnRestart.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 27));
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				SignedInUserRestart.mainmethod();
			}
		});
		btnRestart.setBounds(370, 330, 152, 77);
		contentPane.add(btnRestart);
	}
	
	public static ArrayList<ArrayList<Integer>> readScore() throws IOException
	{
		ArrayList <ArrayList<Integer>> tempscores = new ArrayList <ArrayList<Integer>>();
		ArrayList <Integer> tempaddscores = new ArrayList<Integer>();
		ArrayList <Integer> tempsubscores = new ArrayList<Integer>();
		ArrayList <Integer> tempmulscores = new ArrayList<Integer>();
		ArrayList <Integer> tempdivscores = new ArrayList<Integer>();
		ArrayList <Integer> temptotscores = new ArrayList<Integer>();
		Scanner readdoc = new Scanner(new File(CurrentUserInfo.getUserFileName()));
		readdoc.nextLine();
		
		while(readdoc.hasNextLine())
		{
			String temptype = readdoc.next();
			int score = readdoc.nextInt();
			System.out.println(temptype + " " + score);
			if(temptype.equals("A"))
				tempaddscores.add(score);
			if(temptype.equals("S"))
				tempsubscores.add(score);
			if(temptype.equals("M"))
				tempmulscores.add(score);
			if(temptype.equals("D"))
				tempdivscores.add(score);
			if(temptype.equals("Total"))
				temptotscores.add(score);
			readdoc.nextLine();
				
		}
		
		tempscores.add(tempaddscores);
		tempscores.add(tempsubscores);
		tempscores.add(tempmulscores);
		tempscores.add(tempdivscores);
		tempscores.add(temptotscores);
		return tempscores;
	}
}
