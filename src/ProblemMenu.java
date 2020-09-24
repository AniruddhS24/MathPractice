import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ProblemMenu extends JFrame {

	private JPanel contentPane;
	private static ProblemMenu frame;
	private JTextField numQ;
	/**
	 * Launch the application.
	 */
	public static void mainmethod() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ProblemMenu();
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
	public ProblemMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 467);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPracticeCreationMenu = new JLabel("Practice Creation Menu");
		lblPracticeCreationMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPracticeCreationMenu.setForeground(Color.DARK_GRAY);
		lblPracticeCreationMenu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblPracticeCreationMenu.setBounds(109, 26, 286, 26);
		contentPane.add(lblPracticeCreationMenu);
		
		JLabel lblBy = new JLabel("by");
		lblBy.setBounds(232, 164, 22, 20);
		contentPane.add(lblBy);
		
		JLabel label = new JLabel("by");
		label.setBounds(232, 201, 22, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("by");
		label_1.setBounds(232, 238, 22, 20);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("by");
		label_2.setBounds(232, 275, 22, 20);
		contentPane.add(label_2);
		
		JCheckBox chckbxAddition = new JCheckBox("Addition");
		chckbxAddition.setBounds(11, 160, 139, 29);
		contentPane.add(chckbxAddition);
		
		JCheckBox chckbxSubtraction = new JCheckBox("Subtraction");
		chckbxSubtraction.setBounds(11, 197, 139, 29);
		contentPane.add(chckbxSubtraction);
		
		JCheckBox chckbxMultiplication = new JCheckBox("Multiplication");
		chckbxMultiplication.setBounds(11, 234, 139, 29);
		contentPane.add(chckbxMultiplication);
		
		JCheckBox chckbxDivision = new JCheckBox("Division");
		chckbxDivision.setBounds(11, 271, 139, 29);
		contentPane.add(chckbxDivision);
		
		numQ = new JTextField();
		numQ.setBackground(Color.LIGHT_GRAY);
		numQ.setBounds(169, 104, 146, 26);
		contentPane.add(numQ);
		numQ.setColumns(10);
		
		JLabel lblNumberOfQuestions = new JLabel("Number of Questions");
		lblNumberOfQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfQuestions.setBounds(169, 83, 151, 20);
		contentPane.add(lblNumberOfQuestions);
		
		JComboBox addComboBox1 = new JComboBox();
		addComboBox1.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		addComboBox1.setBounds(181, 161, 36, 26);
		contentPane.add(addComboBox1);
		
		JComboBox addComboBox2 = new JComboBox();
		addComboBox2.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		addComboBox2.setBounds(269, 161, 36, 26);
		contentPane.add(addComboBox2);
		
		JComboBox subComboBox1 = new JComboBox();
		subComboBox1.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		subComboBox1.setBounds(181, 198, 36, 26);
		contentPane.add(subComboBox1);
		
		JComboBox subComboBox2 = new JComboBox();
		subComboBox2.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		subComboBox2.setBounds(269, 198, 36, 26);
		contentPane.add(subComboBox2);
		
		JComboBox mulComboBox1 = new JComboBox();
		mulComboBox1.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3"}));
		mulComboBox1.setBounds(181, 235, 36, 26);
		contentPane.add(mulComboBox1);
		
		JComboBox mulComboBox2 = new JComboBox();
		mulComboBox2.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3"}));
		mulComboBox2.setBounds(269, 235, 36, 26);
		contentPane.add(mulComboBox2);
		
		JComboBox divComboBox1 = new JComboBox();
		divComboBox1.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		divComboBox1.setBounds(181, 272, 36, 26);
		contentPane.add(divComboBox1);
		
		JComboBox divComboBox2 = new JComboBox();
		divComboBox2.setModel(new DefaultComboBoxModel(new String[] {"-1","1", "2", "3", "4", "5"}));
		divComboBox2.setBounds(269, 272, 36, 26);
		contentPane.add(divComboBox2);
		
		JCheckBox addchckbxCarryOver = new JCheckBox("Carry Over");
		addchckbxCarryOver.setBounds(346, 160, 139, 29);
		contentPane.add(addchckbxCarryOver);
		
		JCheckBox subchckbxCarryOver = new JCheckBox("Carry Over");
		subchckbxCarryOver.setBounds(346, 197, 139, 29);
		contentPane.add(subchckbxCarryOver);
		
		JLabel lblDigitsMeans = new JLabel("*-1 digits means a random number of digits will be chosen");
		lblDigitsMeans.setBounds(11, 364, 418, 20);
		contentPane.add(lblDigitsMeans);
		
		JComboBox boundcomboBox = new JComboBox();
		boundcomboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		boundcomboBox.setBounds(218, 385, 36, 26);
		contentPane.add(boundcomboBox);
		
		JLabel lblBoundForRandom = new JLabel("Bound for random digits:");
		lblBoundForRandom.setBounds(21, 388, 179, 20);
		contentPane.add(lblBoundForRandom);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int numprobs;
				
				try
				{
					numprobs = Integer.parseInt(numQ.getText());
					if(numprobs <= 0)
					{
						JOptionPane.showMessageDialog(null,"Please enter a valid number of questions!");
						return;
					}
						
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null,"Please enter a valid number of questions!");
					return;
				}
				
				if(numprobs > 60)
				{
					int okprobs = JOptionPane.showConfirmDialog(null,"Hmm... That seems like quite a lot of questions!"
							+ " Do you still want to continue?");
					if(okprobs == JOptionPane.NO_OPTION || okprobs == JOptionPane.CANCEL_OPTION)
						return;
				}
				if(numprobs < 20)
				{
					int okprobs = JOptionPane.showConfirmDialog(null,"Hmm... That seems like a small amount of questions!"
							+ " Do you still want to continue?");
					if(okprobs == JOptionPane.NO_OPTION || okprobs == JOptionPane.CANCEL_OPTION)
						return;
				}
				
				if(!(chckbxAddition.isSelected() ||chckbxSubtraction.isSelected() ||
						chckbxMultiplication.isSelected() || chckbxDivision.isSelected()))
				{
					JOptionPane.showMessageDialog(null, "You don't seem to have selected a practice choice. \nPlease choose"
							+ " addition, subtraction, multiplication, or division!");
					return;
				}
				
				int start = JOptionPane.showConfirmDialog(null,"Start practice?");
				if(start == JOptionPane.NO_OPTION || start == JOptionPane.CANCEL_OPTION)
					return;
				else
					frame.dispose();
				
				PracticeFrame.mainmethod(numprobs,
						chckbxAddition.isSelected(),chckbxSubtraction.isSelected(),
						chckbxMultiplication.isSelected(),chckbxDivision.isSelected(),
						Integer.parseInt(String.valueOf(addComboBox1.getSelectedItem())), Integer.parseInt(String.valueOf(addComboBox2.getSelectedItem())),
						Integer.parseInt(String.valueOf(subComboBox1.getSelectedItem())), Integer.parseInt(String.valueOf(subComboBox2.getSelectedItem())),
						Integer.parseInt(String.valueOf(mulComboBox1.getSelectedItem())), Integer.parseInt(String.valueOf(mulComboBox2.getSelectedItem())),
						Integer.parseInt(String.valueOf(divComboBox1.getSelectedItem())), Integer.parseInt(String.valueOf(divComboBox2.getSelectedItem())),
						addchckbxCarryOver.isSelected(),subchckbxCarryOver.isSelected(),
						Integer.parseInt(String.valueOf(boundcomboBox.getSelectedItem()))); //give this parameters based on checked data (addition, sub, num probs, etc)
			
			}
		});
		btnOk.setBounds(190, 319, 105, 29);
		btnOk.setBackground(Color.GREEN);
		contentPane.add(btnOk);
		
		
	}
}
