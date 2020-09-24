import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

public class SignedInUserRestart extends JFrame {

	private JPanel contentPane;
	static SignedInUserRestart frame;
	/**
	 * Launch the application.
	 */
	public static void mainmethod() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SignedInUserRestart();
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
	public SignedInUserRestart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHelloName = new JLabel("Hello " + CurrentUserInfo.getName() + "!");
		lblHelloName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloName.setFont(new Font("Segoe UI Light", Font.PLAIN, 27));
		lblHelloName.setBounds(15, 16, 526, 47);
		contentPane.add(lblHelloName);
		
		JButton btnPractice = new JButton("Practice!");
		btnPractice.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPractice.setFont(new Font("DialogInput", Font.BOLD | Font.ITALIC, 32));
		btnPractice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ProblemMenu.mainmethod();
			}
		});
		btnPractice.setBounds(131, 117, 325, 149);
		contentPane.add(btnPractice);
		
		JButton btnResetScores = new JButton("Reset Scores");
		btnResetScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reset = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset your scores?");
				if(reset == JOptionPane.YES_OPTION)
				{
					JPasswordField jpf = new JPasswordField(24);
				    JLabel jl = new JLabel("Enter Password: ");
				    Box box = Box.createHorizontalBox();
				    box.add(jl);
				    box.add(jpf);
				    int x = JOptionPane.showConfirmDialog(null, box, "Enter password:", JOptionPane.OK_CANCEL_OPTION);

				    if (x == JOptionPane.OK_OPTION) {
				      if(jpf.getText().equals(CurrentUserInfo.getPassword()))
				      {
				    	  try 
				    	  {
							PrintWriter resetwriter = new PrintWriter(new File(CurrentUserInfo.getUserFileName()));
							String temp = CurrentUserInfo.getUsername() + " " + CurrentUserInfo.getPassword() + " " + CurrentUserInfo.getName() + " " +  CurrentUserInfo.getAge();
							resetwriter.print("");
							resetwriter.println(temp);
							resetwriter.close();
							JOptionPane.showMessageDialog(null, "Scores have been reset.");
				    	  } 
				    	  catch (FileNotFoundException e1) {
							}
				      }
				      else
				      {
				    	  JOptionPane.showMessageDialog(null,"Wrong password!");
				    	  return;
				      }
				    }
				}
			}
		});
		btnResetScores.setBounds(621, 98, 157, 29);
		contentPane.add(btnResetScores);
		
		JLabel lblAccountSettings = new JLabel("Account Settings");
		lblAccountSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountSettings.setBounds(621, 35, 167, 20);
		contentPane.add(lblAccountSettings);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(556, 16, 15, 348);
		contentPane.add(separator);
		
		JButton btnGenerateGraph = new JButton("Generate Graph");
		btnGenerateGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Grapher.graphData(readScore());
				} catch (IOException e1) {
					
				}
			}
		});
		btnGenerateGraph.setBounds(621, 203, 157, 29);
		contentPane.add(btnGenerateGraph);
		
		JButton btnPrintReport = new JButton("Print Report");
		btnPrintReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Grapher.graphData(readScore());
					HelloWorldPrinter.mainmethod(Grapher.sendAnalysis(100));
				} catch (IOException e1) {
					
				}
				
			}
		});
		btnPrintReport.setBounds(621, 258, 157, 29);
		contentPane.add(btnPrintReport);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(556, 167, 281, 20);
		contentPane.add(separator_1);
		
		JLabel lblQuote = new JLabel("New label");
		lblQuote.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuote.setFont(new Font("French Script MT", Font.PLAIN, 30));
		String[] quotes = {
				"The only way to learn math is to do it!",
				"Math is not about numbers. It's about understanding!",
				"Mistakes are proof you are trying",
				"Go ahead! Push that button to practice!",
				"You can do it!",
				"Well done " + CurrentUserInfo.getName() + "!",
				"You can do anything!",
				"Every problem has a solution!",
				"What do you want to practice today?"
		};
		if(CurrentUserInfo.getName().equals("Rohith"))
			lblQuote.setText("Rohith = L");
		lblQuote.setText(quotes[(int) (Math.random() * quotes.length)]);
		lblQuote.setBounds(15, 294, 526, 47);
		contentPane.add(lblQuote);
		
		JButton btnAmathCredits = new JButton("A-Math Credits");
		btnAmathCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Aniruddh Sriram : 2017");
			}
		});
		btnAmathCredits.setBounds(621, 312, 157, 29);
		contentPane.add(btnAmathCredits);
		

		
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
