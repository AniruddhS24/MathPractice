import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

public class Grapher extends JPanel {
	
	static ArrayList<Line> lines = new ArrayList<Line>();
	static Line add;
	static Line sub;
	static Line mul;
	static Line div;
	static Line totals;
	
	static ArrayList<ArrayList<Integer>> scores =  new ArrayList<ArrayList<Integer>>();
	
	public static void graphData(ArrayList<ArrayList<Integer>> sc) {
		
		JFrame frame = new JFrame();
		scores = sc;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(2000,990);
		
		if(scores.get(4).size() > 14)
		{
			try 
	    	  {
				PrintWriter resetwriter = new PrintWriter(new File(CurrentUserInfo.getUserFileName()));
				String temp = CurrentUserInfo.getUsername() + " " + CurrentUserInfo.getPassword() + " " + CurrentUserInfo.getName() + " " +  CurrentUserInfo.getAge();
				resetwriter.print("");
				resetwriter.println(temp);
				resetwriter.close();
				JOptionPane.showMessageDialog(null, "After this, your old scores will be reset due to a surplus of practices.","ALERT",JOptionPane.WARNING_MESSAGE);
	    	  } 
	    	  catch (FileNotFoundException e1) {
			}
		}
		
		Grapher graph = new Grapher();
		
		graph.setLayout(null);
		
		frame.setTitle("Performance Analysis");
		frame.add(graph);
		
		JButton genRep = new JButton("Generate Report");
		genRep.setBounds(10,10,140,30);
		genRep.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[] temp = getAnalysis().split("nenenenenene");
				String res = wrapText(temp[0],60);
				JOptionPane.showMessageDialog(null, res);
				/*
				ArrayList<String> temp = getAnalysis();
				String todisp = "";
				for(int i = 0; i < temp.size(); i++)
				{
					todisp += temp.get(i) + "\n";
				}
				JOptionPane.showMessageDialog(null, todisp);
				*/
			}
		});
		graph.add(genRep);
		
		JLabel title = new JLabel(CurrentUserInfo.getName() + "'s Score Analysis",SwingConstants.LEFT);
		title.setBounds((frame.getWidth()/2)-(title.getText().length()/2),0,1600,40);
		title.setFont(new Font("Serif", Font.PLAIN, 30));
		graph.add(title);
		
		JLabel info = new JLabel("Scores are on a scale of: 1000-8000");
		info.setBounds(10,150,200,20);
		info.setFont(new Font("Serif", Font.PLAIN, 12));
		graph.add(info);
		
		JLabel key = new JLabel("<html>Cyan: Addition<br>"
				+ "Red: Subtraction<br>"
				+ "Magenta: Multiplication<br>"
				+ "Orange: Division<br>"
				+ "Black: Total</html>");
		key.setBounds(10,50,150,80);
		key.setFont(new Font("Serif", Font.PLAIN, 12));
		graph.add(key);
		
		frame.setVisible(true);
		
		File tempfile = new File(CurrentUserInfo.getUserFileNameImage());
		if(tempfile.isFile())
		{
			tempfile.delete();
		}
	
		try {
			BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
            frame.paint(graphics2D);
			ImageIO.write(image,"jpeg", new File(CurrentUserInfo.getUserFileNameImage()));
		} catch (IOException e1) {
			
		}
	}
	
	public static String[] sendAnalysis(int wid)
	{
		String[] temp = getAnalysis().split("nenenenenene");
		temp[0] = wrapText(temp[0],wid);
		return temp;
	}
	
	public static String getAnalysis()
	{
		double addAvg = 0;
		double subAvg = 0;
		double mulAvg = 0;
		double divAvg = 0;
		double total = 0;
		
		if(scores.get(0).size() > 0)
			addAvg = lines.get(0).getPointAvg();
		if(scores.get(1).size() > 0)
			subAvg = lines.get(1).getPointAvg();
		if(scores.get(2).size() > 0)
			mulAvg = lines.get(2).getPointAvg();
		if(scores.get(3).size() > 0)
			divAvg = lines.get(3).getPointAvg();
		if(scores.get(4).size() > 0)
			total = lines.get(4).getPointAvg();
		
		String result = "";
		String grade = "";
		int userage = Integer.parseInt(CurrentUserInfo.getAge());
		
		String suffadd = "";
		String suffsub = "";
		String suffmul = "";
		String suffdiv = "";
		//scale: A B C D F
		if(userage <= 5)
		{
			if(addAvg != 0)
			{
				if(addAvg < 2000)
					suffadd = "F";
				else if(addAvg < 3000)
					suffadd = "D";
				else if(addAvg < 3500)
					suffadd = "C";
				else if(addAvg < 4500)
					suffadd = "B";
				else if(addAvg <= 8000)
					suffadd = "A";
				grade += "Addition: " + addAvg + " / " + suffadd + "         ";
			}
			
			if(subAvg != 0)
			{
				if(subAvg < 2000)
					suffsub = "F";
				else if(subAvg < 3000)
					suffsub = "D";
				else if(subAvg < 3500)
					suffsub = "C";
				else if(subAvg < 4500)
					suffsub = "B";
				else if(subAvg <= 8000)
					suffsub = "A";
				grade += "Subtraction: " + subAvg + " / " + suffsub + "         ";
			}
			
			if(mulAvg != 0)
			{
				if(mulAvg < 2000)
					suffmul = "C";
				else if(mulAvg < 3000)
					suffmul = "B";
				else if(mulAvg <= 8000)
					suffmul = "A";
				grade += "Multiplication: " + mulAvg + " / " + suffmul + "         ";
				
			}
			
			if(divAvg != 0)
			{
				if(divAvg < 1500)
					suffdiv = "C";
				else if(divAvg < 2500)
					suffdiv = "B";
				else if(divAvg <= 8000)
					suffdiv = "A";
				grade += "Division: " + divAvg + " / " + suffdiv + "         ";
			}
			
		}
		else if(userage <= 11)
		{
			if(addAvg != 0)
			{
				if(addAvg < 3000)
					suffadd = "F";
				else if(addAvg < 4000)
					suffadd = "D";
				else if(addAvg < 5000)
					suffadd = "C";
				else if(addAvg < 6000)
					suffadd = "B";
				else if(addAvg <= 8000)
					suffadd = "A";
				grade += "Addition: " + addAvg + " / " + suffadd + "         ";
			}
			
			if(subAvg != 0)
			{
				if(subAvg < 2500)
					suffsub = "F";
				else if(subAvg < 3500)
					suffsub = "D";
				else if(subAvg < 4000)
					suffsub = "C";
				else if(subAvg < 5500)
					suffsub = "B";
				else if(subAvg <= 8000)
					suffsub = "A";
				grade += "Subtraction: " + subAvg + " / " + suffsub + "         ";
			}
			
			if(mulAvg != 0)
			{
				if(mulAvg < 2000)
					suffmul = "C";
				else if(mulAvg < 3000)
					suffmul = "B";
				else if(mulAvg <= 8000)
					suffmul = "A";
				grade += "Multiplication: " + mulAvg + " / " + suffmul + "         ";
			}
			
			if(divAvg != 0)
			{
				if(divAvg < 1500)
					suffdiv = "C";
				else if(divAvg < 2500)
					suffdiv = "B";
				else if(divAvg <= 8000)
					suffdiv = "A";
				grade += "Division: " + divAvg + " / " + suffdiv + "         ";
			}
		}
		else
		{
			if(addAvg != 0)
			{
				if(addAvg < 4000)
					suffadd = "F";
				else if(addAvg < 5000)
					suffadd = "D";
				else if(addAvg < 6000)
					suffadd = "C";
				else if(addAvg < 7000)
					suffadd = "B";
				else if(addAvg <= 8000)
					suffadd = "A";
				grade += "Addition: " + addAvg + " / " + suffadd + "         ";
			}
			
			if(subAvg != 0)
			{
				if(subAvg < 4000)
					suffsub = "F";
				else if(subAvg < 5000)
					suffsub = "D";
				else if(subAvg < 6000)
					suffsub = "C";
				else if(subAvg < 7000)
					suffsub = "B";
				else if(subAvg <= 8000)
					suffsub = "A";
				grade += "Subtraction: " + subAvg + " / " + suffsub + "         ";
			}
			
			if(mulAvg != 0)
			{
				if(mulAvg < 3000)
					suffmul = "F";
				else if(mulAvg < 4000)
					suffmul = "D";
				else if(mulAvg < 5000)
					suffmul = "C";
				else if(mulAvg < 6000)
					suffmul = "B";
				else if(mulAvg <= 8000)
					suffmul = "A";
				grade += "Multiplication: " + mulAvg + " / " + suffmul + "         ";
			}
			
			if(divAvg != 0)
			{
				if(divAvg < 2500)
					suffdiv = "F";
				else if(divAvg < 3000)
					suffdiv = "D";
				else if(divAvg < 4500)
					suffdiv = "C";
				else if(divAvg < 5500)
					suffdiv = "B";
				else if(divAvg <= 8000)
					suffdiv = "A";
				grade += "Division: " + divAvg + " / " + suffdiv + "         ";
			}
			
		}
		
		
		switch(suffadd)
		{
		case "F": result +="Results indicate you need to seriously improve on addition. "; break;
		case "D": result +="You did quite poorly on addition. Practice more, and you can be great! "; break;
		case "C": result +="For " + userage + " years, you are doing fairly well in addition. "; break;
		case "B": result +="Good job! You seem to be good at addition! "; break;
		case "A": result +="Excellent! Your addition score is phenomenal! "; break;
		}
		
		if(addAvg == 8000)
			result +="You have a perfect average score (8000) for addition! ";
		if(addAvg > mulAvg || suffadd.equals("A"))
			result +="Mastery of addition means you may have a knack for multiplication. ";
		if(addAvg > subAvg && addAvg != 0 && subAvg != 0)
			result +="Based on your scores, you are doing better in addition than subtraction. ";
		
		
		if(addAvg > subAvg && addAvg-subAvg > 1500 && addAvg != 0 && subAvg != 0)
			result +="On the other hand, ";
		
		switch(suffsub)
		{
		case "F": result +="Results also indicate you need to seriously improve on subtraction. "; break;
		case "D": result +="Your subtraction score was pretty bad. However, you can improve it! "; break;
		case "C": result +="Your subtraction score is average compared to other " + userage + " year-olds. "; break;
		case "B": result +="Pretty good! You are great at subtraction!"; break;
		case "A": result +="Simply outstanding " + CurrentUserInfo.getName() + "! You are very good at subtraction. "; break;
		}
		
		if(subAvg > divAvg  && subAvg != 0 && divAvg != 0)
			result +="Your performance in subtraction is much better than in division. ";
		if(subAvg == 8000)
			result +="You got a perfect score! ";
		
		switch(suffmul)
		{
		case "F": result +="You did very badly in multiplication. Work on improving your score! "; break;
		case "D": result +="Multiplication can be hard, but you need to practice. "; break;
		case "C": result +="Statistics show your multiplication score is quite good. "; break;
		case "B": result +="You did very well in multiplication. "; break;
		case "A": result +="Your multiplication score was ranked as an A! Very good. "; break;
		}
		if(mulAvg == 8000)
			result +="Not only that, but your average was actually an A+! ";
		
		switch(suffdiv)
		{
		case "F": result +="Yikes! Your division score dropped! "; break;
		case "D": result +="You did quite badly in division. Practice more multiplication to improve in division. "; break;
		case "C": result +="You did decently well. If you have trouble with remainders, learn counting by multiples. ";
		result +="Once you get close enough to the number you are dividing (where you cannot multiply anymore), ";
		result +="the number that's left is the remainder! "; break;
		case "B": result +="You did quite well in division! "; break;
		case "A": result +="Division is your strength. Mastering division is the hardest to do! "; break;
		}
		
		if(divAvg == 8000)
			result +="You also received a perfect score, which is truly amazing! ";
		
		if((suffdiv.equals("A") || suffdiv.equals("B")) && (suffsub.equals("A") || suffsub.equals("B")))
			result +="Your high division score also has a direct correlation to your subtraction score. ";
			
		if(total > 4500)
		{
			result +="Overall, you did quite well. Your report shows you are above average for a " + userage + " year-old. ";
			result +="Keep in mind: Practice makes perfect! ";
		}
		else
		{
			result +="Overall, you did quite okay. Your report shows you are among the average for a " + userage + " year-old. ";
			result +="Practice every day, and you can become the best, " + CurrentUserInfo.getName() + "! ";
		}
		
		
		
		return result + "nenenenenene" + grade + "nenenenenene" + total;
	}
	
	private static String wrapText(String s, int wid)
	{
		String[] parts = s.split(" ");
		String wrapped = "";
		int lastind = 0;
		int part = 0;
		while(lastind < s.length() && part < parts.length)
		{
			if(parts[part].length()+1 > wid-lastind)
			{
				if(parts[part].length() > wid)
					wrapped += "...\n";
				else
				{
					wrapped += "\n";
					wrapped += parts[part];
					wrapped += " ";
				}
				lastind = 0;
			}
			else
			{
				wrapped += parts[part];
				wrapped += " ";
			}
				
			
			lastind += parts[part].length()+1;
			
			part++;
			
		}
		
		return wrapped;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawGraph(g,20);
		
		if(scores.get(4).size() > 0)
		{
			totals = new Line(Color.BLACK);
			Point.setFactor(2000/scores.get(4).size());
			for(int i = 0; i < scores.get(4).size(); i++)
			{
				totals.addPoint(i, scores.get(4).get(i));
			}
			totals.drawLine(g,"Total");
		}
		
		
		if(scores.get(0).size() > 0)
		{
			add = new Line(Color.CYAN);
			Point.setFactor(2000/scores.get(0).size());
			
			for(int i = 0; i < scores.get(0).size(); i++)
			{
				add.addPoint(i, scores.get(0).get(i));
			}
			add.drawLine(g,"ADD");
		}
		lines.add(add);
		
		if(scores.get(1).size() > 0)
		{
			sub = new Line(Color.RED);
			Point.setFactor(2000/scores.get(1).size());
			for(int i = 0; i < scores.get(1).size(); i++)
			{
				sub.addPoint(i, scores.get(1).get(i));
			}
			sub.drawLine(g,"SUB");
		}
		lines.add(sub);
		
		if(scores.get(2).size() > 0)
		{
			mul = new Line(Color.MAGENTA);
			Point.setFactor(2000/scores.get(2).size());
			for(int i = 0; i < scores.get(2).size(); i++)
			{
				mul.addPoint(i, scores.get(2).get(i));
			}
			mul.drawLine(g,"MUL");
		}
		lines.add(mul);
		
		if(scores.get(3).size() > 0)
		{
			div = new Line(Color.ORANGE);
			Point.setFactor(2000/scores.get(3).size());
			for(int i = 0; i < scores.get(3).size(); i++)
			{
				div.addPoint(i, scores.get(3).get(i));
			}
			div.drawLine(g,"DIV");
		}
		lines.add(div);
		
		lines.add(totals);
		
		
	}
	
	public static void drawGraph(Graphics g, int cellsize)
	{
		g.setColor(Color.LIGHT_GRAY);
		for(int i = 0; i < 1920; i+= cellsize)
		{
			g.drawLine(i, 200, i, 930);
		}
		for(int i = 200; i < 940; i+= cellsize)
		{
			g.drawLine(0,i, 1900, i);
		}
		g.setColor(Color.BLACK);
	}

}

class Line
{
	private ArrayList<Point> points;
	private Color ptColor;
	
	public Line(Color c)
	{
		points = new ArrayList<Point>();
		ptColor = c;
	}
	
	public void addPoint(int x, int y)
	{
		points.add(new Point(x,y));
	}
	
	public double getPointAvg()
	{
		double avg = 0;
		for(int i = 0; i < points.size(); i++)
		{
			avg += points.get(i).getScore();
		}
		if(points.size() > 0)
		{
			avg = avg/points.size();
			avg = Math.round(avg * 100.0) / 100.0;
		}
			
		
		return avg;
	}
	
	public void drawLine(Graphics g, String total)
	{
		for(int i = 0; i < points.size(); i++)
		{
			Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5)); //line thiccness
            
			g.setColor(ptColor);
			Point currpoint = points.get(i);
			if(i != points.size()-1)
			{
				Point nextpoint = points.get(i+1);
				g2.draw(new Line2D.Float(currpoint.getX()+15, currpoint.getY()+15, nextpoint.getX()+15, nextpoint.getY()+15));
			}
			if(total.equals("Total"))
				currpoint.drawSqrPoint(g);
			else
				currpoint.drawPoint(g);
		}
	}
	
}

class Point
{
	private int runnum;
	private int score;
	private int xPos;
	private int yPos;
	private static int scalexFactor = 200;
	private static int scaleyFactor = 10;
	
	public Point(int x, int y)
	{
		runnum = x;
		score = y;
		
		xPos =runnum*scalexFactor;
		yPos = (10000-score)/scaleyFactor;
	}
	
	public static void setFactor(int f)
	{
		scalexFactor = f;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void drawPoint(Graphics g)
	{
		g.drawOval(xPos,yPos,30,30);
		g.fillOval(xPos,yPos,30,30);
		g.setColor(Color.black);
		g.drawString(score + "", xPos, yPos+20);
	}
	
	public void drawSqrPoint(Graphics g)
	{
		g.drawRect(xPos,yPos,30,30);
		g.fillRect(xPos,yPos,30,30);
		g.setColor(Color.white);
		g.drawString(score + "", xPos, yPos+20);
	}
}
