import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class PracticeFrame extends JFrame implements KeyListener {

	private JPanel contentPane;
	private static ProblemGenerator pg;
	private static int currentprob;
	private JTextField txtAnswer;
	
	JLabel lblTopNum;
	JLabel lblBottomNum;
	JLabel lblO;
	JLabel lblQuestionsLeft;
	JLabel lblnumcorrect;
	JLabel lblnumwrong;
	JLabel lblscore;
	int ptvalperq;
	
	
	private int totalprobs;
	private int correct;
	private int wrong;
	private int score = 1000;
	private int Ascore = 0;
	private int Aprobs;
	private int Sscore = 0;
	private int Sprobs;
	private int Mscore = 0;
	private int Mprobs;
	private int Dscore = 0;
	private int Dprobs;
	
	JProgressBar progressBar;
	
	static PrintWriter output;
	private JTextField remfield;
	
	JLabel lblR;
	JLabel piclabel;
	
	static PracticeFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void mainmethod(int p,
			boolean a, boolean s,
			boolean m, boolean d,
			int a1, int a2,
			int s1, int s2,
			int m1, int m2,
			int d1, int d2,
			boolean addcarry, boolean subcarry,
			int bcb) 
	{
		
		try {
			output = new PrintWriter(new FileWriter(CurrentUserInfo.getUserFileName(),true));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pg = new ProblemGenerator(p, a, s, m, d, a1, a2, s1, s2, m1, m2, d1 , d2, addcarry, subcarry, bcb);
					frame = new PracticeFrame();
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
	public PracticeFrame() {
		totalprobs = pg.getNumProbs();
		correct = 0;
		wrong = 0;
		currentprob = 1;
		ptvalperq = 7000/totalprobs;
		
		for(int i = 0; i < pg.getSequence().length; i++)
		{
			switch(pg.getSequence()[i])
			{
			case "ADD": Aprobs++; break;
			case "SUB": Sprobs++; break;
			case "MUL": Mprobs++; break;
			case "DIV": Dprobs++; break;
			}
		}
		if(Aprobs > 0)
			Ascore = 7000%Aprobs + 1000;
		if(Sprobs > 0)
			Sscore = 7000%Sprobs + 1000;
		if(Mprobs > 0)
			Mscore = 7000%Mprobs + 1000;
		if(Dprobs > 0)
			Dscore = 7000%Dprobs + 1000;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(655, 16, 23, 986);
		contentPane.add(separator);
		
		lblTopNum = new JLabel("oi");
		lblTopNum.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblTopNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTopNum.setBounds(987, 260, 355, 78);
		contentPane.add(lblTopNum);
		
		lblBottomNum = new JLabel("oi");
		lblBottomNum.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblBottomNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBottomNum.setBounds(987, 373, 355, 78);
		contentPane.add(lblBottomNum);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(987, 467, 355, 17);
		contentPane.add(separator_1);
		
		txtAnswer = new JTextField();
		txtAnswer.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAnswer.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtAnswer.setText("");
		txtAnswer.setBounds(987, 491, 355, 78);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		txtAnswer.addKeyListener(this);
		
		lblO = new JLabel("O");
		lblO.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblO.setBounds(920, 328, 55, 49);
		contentPane.add(lblO);
		
		progressBar = new JProgressBar(0,totalprobs);
		progressBar.setBounds(15, 415, 625, 69);
		contentPane.add(progressBar);
		
		JLabel lblPressToSubmit = new JLabel("Press <ENTER> to submit answer");
		lblPressToSubmit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPressToSubmit.setBounds(987, 708, 368, 29);
		contentPane.add(lblPressToSubmit);
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int exit = JOptionPane.showConfirmDialog(null, "End practice?");
				if(exit == JOptionPane.YES_OPTION)
				{
					frame.dispose();
					SignedInUserRestart.mainmethod();
				}
				else
					return;
			}
		});
		btnQuit.setBackground(new Color(255, 0, 0));
		btnQuit.setBounds(83, 49, 125, 49);
		contentPane.add(btnQuit);
		
		JButton btnHelp = new JButton("HELP");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Enter your answer in the textbox below the problem.\n"
						+ "The progress bar on the left indicates how many questions are remaining in this practice.\n"
						+ "There is a scratchpad on the right for writing down simple notes while solving a problem.\n"
						+ "After completing the practice unit, you will be able to view a detailed analysis of your performance!");
			}
		});
		btnHelp.setBounds(427, 49, 125, 49);
		contentPane.add(btnHelp);
		
		lblQuestionsLeft = new JLabel("Question " + String.valueOf(currentprob) + " out of " + String.valueOf(totalprobs));
		lblQuestionsLeft.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblQuestionsLeft.setBounds(15, 368, 609, 29);
		contentPane.add(lblQuestionsLeft);
		
		JTextArea txtrS = new JTextArea();
		txtrS.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtrS.setBackground(new Color(173, 216, 230));
		txtrS.setText("");
		txtrS.setBounds(1529, 102, 368, 868);
		contentPane.add(txtrS);
		
		JLabel lblScratch = new JLabel("Scratch");
		lblScratch.setHorizontalAlignment(SwingConstants.CENTER);
		lblScratch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblScratch.setBounds(1529, 63, 368, 35);
		contentPane.add(lblScratch);
		
		lblnumcorrect = new JLabel("Correct: 0");
		lblnumcorrect.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblnumcorrect.setBounds(83, 191, 368, 29);
		contentPane.add(lblnumcorrect);
		
		lblnumwrong = new JLabel("Wrong: 0");
		lblnumwrong.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblnumwrong.setBounds(83, 236, 368, 29);
		contentPane.add(lblnumwrong);
		
		lblscore = new JLabel("Score: 1000");
		lblscore.setBackground(Color.PINK);
		lblscore.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblscore.setBounds(83, 290, 469, 62);
		contentPane.add(lblscore);
		
		remfield = new JTextField();
		remfield.setFont(new Font("Tahoma", Font.PLAIN, 45));
		remfield.setBounds(1435, 491, 79, 78);
		contentPane.add(remfield);
		remfield.setColumns(10);
		remfield.addKeyListener(this);
		
		lblR = new JLabel("R");
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblR.setBounds(1351, 491, 69, 78);
		contentPane.add(lblR);
		
		piclabel = new JLabel("");
		piclabel.setHorizontalAlignment(SwingConstants.CENTER);
		piclabel.setBounds(15, 500, 625, 486);
		contentPane.add(piclabel);
		
		
		printProblem(pg.getProblem(currentprob));
		if(pg.getProblem(currentprob).contains("/"))
		{
			remfield.setVisible(true);
			lblR.setVisible(true);
		}
		else
		{
			remfield.setVisible(false);
			lblR.setVisible(false);
		}
	}
	
	public void printProblem(String prb)
	{
		char upto = '?';
		
		if(prb.contains("+"))
			upto = '+';
		if(prb.contains("-"))
			upto = '-';
		if(prb.contains("*"))
			upto = '*';
		if(prb.contains("/"))
			upto = '/';
		
		String tpn = prb.substring(0,prb.indexOf(upto));
		String btn = prb.substring(prb.indexOf(upto)+1,prb.length());
		
		lblTopNum.setText(tpn);
		lblBottomNum.setText(btn);
		lblO.setText(String.valueOf(upto));
		
	}
	
	
		

	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_ENTER)
		{
			txtAnswer.setEditable(false);
			remfield.setEditable(false);
			int inputanswer;
			try
			{
				inputanswer = Integer.parseInt(txtAnswer.getText());
				if(remfield.isVisible() && remfield.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid remainder!");
					txtAnswer.setEditable(true);
					remfield.setEditable(true);
					return;
				}
					
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid answer!");
				txtAnswer.setText("");
				txtAnswer.setEditable(true);
				remfield.setEditable(true);
				return;
			}
			
			boolean divrem;
			
			if(pg.getProblem(currentprob).contains("/"))
			{
				int rem = Integer.parseInt(remfield.getText());
				int tempremtop = Integer.parseInt(lblTopNum.getText());
				int temprembot = Integer.parseInt(lblBottomNum.getText());
				int tempremans = Integer.parseInt(pg.getAnswer(currentprob));
				
				if(tempremtop-(temprembot*tempremans) == rem)
						divrem = true;
				else
					divrem = false;
			}
			else
			{
				divrem = true;
			}
			
			if(inputanswer == Integer.parseInt(pg.getAnswer(currentprob)) && divrem)
			{
				correct++;
				if(pg.getProblem(currentprob).contains("+"))
				{
					if(Aprobs > 0)
					{
						Ascore += (7000/Aprobs);
					}
				}
				if(pg.getProblem(currentprob).contains("-"))
				{
					if(Sprobs > 0)
					{					
						Sscore += (7000/Sprobs);
					}
				}
				if(pg.getProblem(currentprob).contains("*"))
				{
					if(Mprobs > 0)
					{
						Mscore += (7000/Mprobs);
					}
				}
				if(pg.getProblem(currentprob).contains("/"))
				{
					if(Dprobs > 0 && divrem)
					{
						Dscore += (7000/Dprobs);
					}
				}
				
				score += ptvalperq;
				txtAnswer.setBackground(Color.GREEN);
				if(remfield.isVisible())
					remfield.setBackground(Color.GREEN);
				
				BufferedImage goodpic;
				try {
					goodpic = ImageIO.read(new File("good.png"));
					piclabel.setIcon(new ImageIcon(goodpic));
				} catch (IOException e) {
				}
				
				lblnumcorrect.setText("Correct: " + String.valueOf(correct));
				lblscore.setText("Score: " + String.valueOf(score));
			}
			else
			{
				wrong++;
				txtAnswer.setBackground(Color.RED);
				if(remfield.isVisible())
					remfield.setBackground(Color.RED);
				
				BufferedImage wrongpic;
				try {
					wrongpic = ImageIO.read(new File("bad.png"));
					
					piclabel.setIcon(new ImageIcon(wrongpic));
				} catch (IOException e) {
				}
				lblnumwrong.setText("Wrong: " + String.valueOf(wrong));
			}
			
			progressBar.setValue(currentprob);
			lblQuestionsLeft.setText("Question " + String.valueOf(currentprob) + " out of " + String.valueOf(totalprobs));
			currentprob++;
			
			if(currentprob > totalprobs)
			{
				JOptionPane.showMessageDialog(null, "Practice Completed!\n"
						+ "You got " + correct + " correct out of " + totalprobs + " problems. \n"
								+ "Your score was: " + score);
				
				if(Aprobs > 0)
					output.append("A " + Ascore + "\n");
				if(Sprobs > 0)
					output.append("S " + Sscore + "\n");
				if(Mprobs > 0)
					output.append("M " + Mscore + "\n");
				if(Dprobs > 0)
					output.append("D " + Dscore + "\n");
				
				output.append("Total" + " " + score + "\n");
				output.close();
				this.dispose();
				AfterPracticeMenu.mainmethod(score);//open new jframe with statistics, to view printed data, loop thru and go to next graph set after every "total"
			}
			
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			txtAnswer.setEditable(true);
			remfield.setEditable(true);
			txtAnswer.setText("");
			remfield.setText("");
			txtAnswer.setBackground(Color.WHITE);
			remfield.setBackground(Color.WHITE);
			BufferedImage neutralpic;
			try {
				neutralpic = ImageIO.read(new File("neutral.png"));
				piclabel.setIcon(new ImageIcon(neutralpic));
			} catch (IOException ed) {
			}
			printProblem(pg.getProblem(currentprob));
			
			if(pg.getProblem(currentprob).contains("/"))
			{
				remfield.setVisible(true);
				lblR.setVisible(true);
			}
			else
			{
				remfield.setVisible(false);
				lblR.setVisible(false);
			}
		}
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}





class ProblemGenerator
{
	private int numprobs;
	
	private boolean add;
	private boolean sub;
	private boolean mul;
	private boolean div;
	
	private int addDig1;
	private int addDig2;
	private int subDig1;
	private int subDig2;
	private int mulDig1;
	private int mulDig2;
	private int divDig1;
	private int divDig2;
	private int boundofrand;
	
	private boolean addCarryOver;
	private boolean subCarryOver;
	
	
	private String[] problemsequence;
	private String[] problems;
	private String[] answers;
	
	//CONSTRUCTOR
	public ProblemGenerator(int p,
			boolean a, boolean s,
			boolean m, boolean d,
			int a1, int a2,
			int s1, int s2,
			int m1, int m2,
			int d1, int d2,
			boolean addcarry, boolean subcarry,
			int bcb)
	{
		numprobs = p;
		
		add = a;
		sub = s;
		mul = m;
		div = d;
		
		addDig1 = a1;
		addDig2 = a2;
		subDig1 = s1;
		subDig2 = s2;
		mulDig1 = m1;
		mulDig2 = m2;
		divDig1 = d1;
		divDig2 = d2;
		boundofrand = bcb;
		
		addCarryOver = addcarry;
		subCarryOver = subcarry;
		
		problemsequence = new String[numprobs];
		Arrays.fill(problemsequence,"X");
		setProblemSequence();
		
		problems = new String[numprobs];
		Arrays.fill(problems,"");
		answers = new String[numprobs];
		Arrays.fill(answers,"");
		
		makeProblemSet();
	}
	
	//MAKES SET OF PROBLEMS
	private void makeProblemSet()
	{
		String currprob;
		for(int i = 0; i < problems.length; i++)
		{
			currprob = problemsequence[i];
			switch(currprob)
			{
			case "ADD":
				problems[i] = makeProblem(currprob,addDig1,addDig2,addCarryOver);
				answers[i] = String.valueOf(Integer.parseInt(problems[i].substring(0, problems[i].indexOf('+')))
						+ Integer.parseInt(problems[i].substring(problems[i].indexOf('+')+1, problems[i].length())));
				break;
			case "SUB":
				problems[i] = makeProblem(currprob,subDig1,subDig2,subCarryOver);
				answers[i] = String.valueOf(Integer.parseInt(problems[i].substring(0, problems[i].indexOf('-')))
						- Integer.parseInt(problems[i].substring(problems[i].indexOf('-')+1, problems[i].length())));
				break;
			case "MUL":
				problems[i] = makeProblem(currprob,mulDig1,mulDig2,true);
				answers[i] = String.valueOf(Integer.parseInt(problems[i].substring(0, problems[i].indexOf('*')))
						* Integer.parseInt(problems[i].substring(problems[i].indexOf('*')+1, problems[i].length())));
				break;
			case "DIV":
				problems[i] = makeProblem(currprob,divDig1,divDig2,true);
				answers[i] = String.valueOf(Integer.parseInt(problems[i].substring(0, problems[i].indexOf('/')))
						/ Integer.parseInt(problems[i].substring(problems[i].indexOf('/')+1, problems[i].length())));
				break;
			}
			
		}
	}
	
	public String[] getSequence()
	{
		return problemsequence;
	}
	private ArrayList<String> getPossibles()
	{
		ArrayList<String> possibles = new ArrayList<String>();
		
		if(add)
			possibles.add("ADD");
		if(sub)
			possibles.add("SUB");
		if(mul)
			possibles.add("MUL");
		if(div)
			possibles.add("DIV");
		
		return possibles;
	}
	public String getProblem(int probnum)
	{
		probnum -= 1;
		return problems[probnum];
	}
	public String getAnswer(int ansnum)
	{
		ansnum -= 1;
		return answers[ansnum];
	}
	public int getNumProbs()
	{
		return numprobs;
	}
	//MAKE INDIVIDUAL PROBLEM
	private String makeProblem(String type, int d1, int d2, boolean aco)
	{
		if(d1 == -1)
		{
			if(type.equals("MUL") && boundofrand > 3)
				d1 = (int) (Math.random()*3) +1;
			else
				d1 = (int) (Math.random()*boundofrand) +1;
		}
		if(d2 == -1)
		{
			if(type.equals("MUL") && boundofrand > 3)
				d2 = (int) (Math.random()*3) +1;
			else
				d2 = (int) (Math.random()*boundofrand) +1;
		}

		
		String num1 = "";
		String num2 = "";
		String result = "";
		
		int n1l;
		int n2l;
		if(d1 >= d2)
		{
			n1l = d1;
			n2l = d2;
		}
		else
		{
			n1l = d2;
			n2l = d1;
		}
			
		
		if(!aco)
		{
			if(type.equals("ADD"))
			{
				for(int i = 0; i < n1l-n2l; i++)
					if(i == 0)
						num1 += (int) (Math.random()*9) + 1;
					else
						num1 += (int) (Math.random()*10);
				for(int i = 0; i < n2l; i++)
				{
					if(num1.length() == 0 || num2.length()==0)
					{
						num1 += (int) (Math.random()*9) + 1;
						num2 += (int) (Math.random()*(9-Integer.parseInt(num1.substring(num1.length()-1,num1.length())))) + 1;
					}
					else
					{
						num1 += (int) (Math.random()*10);
						num2 += (int) (Math.random()*(9-Integer.parseInt(num1.substring(num1.length()-1,num1.length()))));
					}
				}
			}
			
			if(type.equals("SUB"))
			{
				for(int i = 0; i < n1l-n2l; i++)
					if(i == 0)
						num1 += (int) (Math.random()*9) + 1;
					else
						num1 += (int) (Math.random()*10);
				for(int i = 0; i < n2l; i++)
				{
					if(num1.length() == 0 || num2.length()==0)
					{
						num1 += (int) (Math.random()*9) + 1;
						num2 += (int) (Math.random()*(Integer.parseInt(num1.substring(num1.length()-1,num1.length())))) + 1;
					}
					else
					{
						num1 += (int) (Math.random()*10);
						num2 += (int) (Math.random()*(Integer.parseInt(num1.substring(num1.length()-1,num1.length()))));
					}
				}
			}
		}
		else
		{
			for(int i = 0; i < n1l-n2l; i++)
				if(i == 0)
					num1 += (int) (Math.random()*9) + 1;
				else
					num1 += (int) (Math.random()*10);
			for(int i = 0; i < n2l; i++)
			{
				if(num1.length() == 0 || num2.length()==0)
				{
					num1 += (int) (Math.random()*9) + 1;
					num2 += (int) (Math.random()*9) + 1;
				}
				else
				{
					num1 += (int) (Math.random()*10);
					num2 += (int) (Math.random()*10);
				}
			}
		}
		
		if(Integer.parseInt(num1) < Integer.parseInt(num2))
		{
			String temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		switch(type)
		{
		case "ADD":
			result += num1 + "+" + num2;
			break;
		case "SUB":
			result += num1 + "-" + num2;
			break;
		case "MUL":
			result += num1 + "*" + num2;
			break;
		case "DIV":
			result += num1 + "/" + num2;
			break;
		}
		
		return result;
	}
	//SET SEQUENCE OF PROBLEM TYPES (ORDERED)
	private void setProblemSequence()
	{
		ArrayList<String> possibles = getPossibles();
		
		int k = -1;
		for(int i = 0; i < possibles.size(); i++)
		{
			k++;
			for(int j = 0; j < numprobs/possibles.size(); j++)
			{
				int randomindex = (int) (Math.random() * problemsequence.length);
				while(!problemsequence[randomindex].equals("X"))
					randomindex = (int) (Math.random() * problemsequence.length);
				
				problemsequence[randomindex] = possibles.get(k);
			}
		}
		
		for(int i = 0; i < numprobs; i++)
			if(problemsequence[i].equals("X"))
				problemsequence[i] = possibles.get((int) (Math.random()*possibles.size()));
	}
}
