import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.io.*;
public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private static PrintWriter cu;
	
	private String usn;
	private String ps;
	private JTextField name;
	private JTextField age;
	private JTextField newusername;
	private JPasswordField newpassword;
	private JPasswordField reenterpassword;
	
	static LoginScreen frame;
	/**
	 * Launch the application.
	 */
	public static void mainmethod() throws IOException{
		cu = new PrintWriter(new File("currentuser.txt"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if(newusername.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter an username!");
					return;
				}
				if(name.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a name!");
					return;
				}
				if(age.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter an age!");
					return;
				}
				try
				{
					Integer.parseInt(age.getText());
				}
				catch(NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid (numerical) age!");
					return;
				}
				
				if(newpassword.getText().isEmpty() || reenterpassword.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a password!");
					return;
				}
				
				if(!newpassword.getText().equals(reenterpassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
					return;
				}
				
				if(new File("logs/" + newusername.getText() + ".txt").isFile())
				{
					JOptionPane.showMessageDialog(null,"There already seems to be an account under this username.\n"
							+ "Please choose a different username!");
					return;
				}
				
				
				
				File newuser = new File("logs/" + newusername.getText() + ".txt");
				
				try 
				{
					newuser.createNewFile();
					
					cu.print(newusername.getText());
					cu.close();
					
				} catch (IOException e1) 
				{
				}
				
				
				try {
					PrintWriter tempinit = new PrintWriter(new FileWriter(newuser,true));
					tempinit.append(newusername.getText() + " ");
					tempinit.append(newpassword.getText() + " ");
					tempinit.append(name.getText() + " ");
					tempinit.append(age.getText() + "\n");
					tempinit.close();
				} catch (IOException e1) {
					return;
				}
				
				try {
					CurrentUserInfo.init();
				} catch (IOException e1) {
					
				}
				JOptionPane.showMessageDialog(null, "Account created! Welcome " + CurrentUserInfo.getName() + "!");
				frame.dispose();
				SignedInUserRestart.mainmethod(); //go to home
				
			}
		});
		btnCreateAccount.setBounds(555, 493, 156, 29);
		contentPane.add(btnCreateAccount);
		
		JLabel lblAmathSoftware = new JLabel("A-Math Software");
		lblAmathSoftware.setFont(new Font("Comic Sans MS", Font.PLAIN, 43));
		lblAmathSoftware.setBounds(244, 0, 371, 64);
		contentPane.add(lblAmathSoftware);
		
		JLabel lblWelcomeToAmath = new JLabel("<html>Welcome to A-Math! <br>A-Math is an educational software which can improve the mathematical skills of children at the primary level through personalized practice! <br>                        Get started today!</html>");
		lblWelcomeToAmath.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToAmath.setBounds(182, 80, 532, 80);
		contentPane.add(lblWelcomeToAmath);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(182, 223, 54, 20);
		contentPane.add(lblLogin);
		
		username = new JTextField();
		username.setBounds(135, 259, 146, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(135, 297, 146, 26);
		contentPane.add(password);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(36, 262, 84, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(36, 300, 84, 20);
		contentPane.add(lblPassword);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usn = username.getText();
				ps = password.getText();
				
				if(new File("logs/" + usn + ".txt").isFile())
				{
					try {
						Scanner readuserdata = new Scanner(new File("logs/" + usn + ".txt"));
						String tempus = readuserdata.next();
						String tempps = readuserdata.next();
						if(!(usn.equals(tempus) && ps.equals(tempps)))
						{
							JOptionPane.showMessageDialog(null,"Incorrect username/password!");
							return;
						}
							
					} catch (FileNotFoundException e1) {
						
					}
					
					cu.print(usn);
					cu.close();
					try {
						CurrentUserInfo.init();
					} catch (IOException e1) {
					}
					JOptionPane.showMessageDialog(null, "Welcome " + CurrentUserInfo.getName() + "!");
					frame.dispose();
					SignedInUserRestart.mainmethod(); //go to home
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Incorrect username/password!");
					return;
				}
			}
		});
		btnOk.setBounds(182, 339, 54, 29);
		contentPane.add(btnOk);
		
		name = new JTextField();
		name.setBounds(555, 259, 146, 26);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewAccount = new JLabel("NEW ACCOUNT");
		lblNewAccount.setBounds(570, 223, 114, 20);
		contentPane.add(lblNewAccount);
		
		age = new JTextField();
		age.setBounds(555, 297, 60, 26);
		contentPane.add(age);
		age.setColumns(10);
		
		newusername = new JTextField();
		newusername.setBounds(555, 364, 146, 26);
		contentPane.add(newusername);
		newusername.setColumns(10);
		
		newpassword = new JPasswordField();
		newpassword.setBounds(555, 406, 146, 26);
		contentPane.add(newpassword);
		
		reenterpassword = new JPasswordField();
		reenterpassword.setBounds(555, 441, 146, 26);
		contentPane.add(reenterpassword);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(486, 262, 54, 20);
		contentPane.add(lblName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(486, 300, 54, 20);
		contentPane.add(lblAge);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(456, 367, 84, 20);
		contentPane.add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(456, 409, 84, 20);
		contentPane.add(lblPassword_1);
		
		JLabel lblReenterPassword = new JLabel("Re-enter password:");
		lblReenterPassword.setBounds(402, 444, 138, 20);
		contentPane.add(lblReenterPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 176, 774, 9);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(362, 176, 25, 321);
		contentPane.add(separator_1);
	}
}
