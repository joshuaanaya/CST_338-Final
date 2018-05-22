import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/*This class is used to create the Purchase calculator. 
 * This class is called with the What If button on the main GUI window.
 * It can only be called after the net Income has been calculated.
 */
public class NewWindow extends JFrame{

	/**
	 * Variables to be used to construct new JFrame for Expense calculator
	 */
	private static final long serialVersionUID = 8545509125420172613L;
	private JLabel title = new JLabel("Savings Plan");	
	private JLabel instruction = new JLabel("Find out how many months it would take you to save for a big purchase.");
	
	private static JLabel income = new JLabel();
	private JTextField expense = new JTextField(10);
	private JButton calculate = new JButton("Calculate savings");
	private JPanel center = new JPanel();
	//String used to output message after calculations completed.
	private static String str1 = "This is a text";
	
	public NewWindow()
	{
	@SuppressWarnings("unused")
	JFrame newWindow = new JFrame();
	setTitle("Budget - Find Out");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	title.setHorizontalAlignment(SwingConstants.CENTER);
	add(title, BorderLayout.NORTH);
	center.setLayout(new GridLayout(10,1));
	//add components to JFrame
	center.add(instruction);
	center.add(income);
	center.add(expense);
	center.add(calculate);
	add(center, BorderLayout.CENTER);
	}
	//listener for calculate button
	void addcalListener(ActionListener CalListener)
	{
		calculate.addActionListener(CalListener);
	}
	//Error message for incorrect user input
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public void setStr1(String txt)
	{
		str1 = txt;
	}
	//Get the expense value to be used in the Controller file
	public int getExpense()
	{
		return Integer.parseInt(expense.getText());
	}
	public String getStr1()
	{
		return str1;
	}
	//used to set income value JPanel with previously calculated Net Income
	public void setIncome(String str)
	{
		income.setText(str);
	}
}
