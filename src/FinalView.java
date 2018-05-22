import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class FinalView extends JFrame {
	
	//Variables for View to be used for construction of GUI
	int height = 400;
	int width = 600;
	private JPanel west = new JPanel();
	private JLabel title = new JLabel("Budget");
	private JPanel income = new JPanel();
	private JPanel expense = new JPanel();
	private JLabel instructions = new JLabel("Enter your income and expenses to find out whether you have excess income or need to cut expenses.");
	private JLabel incomeTitle = new JLabel("Income");
	private JLabel expenseTitle = new JLabel("Expenses");
	private JTextField incomeOne = new JTextField(10);
	private JTextField expenseOne = new JTextField(10);
	private JTextField incomeTwo = new JTextField(10);
	private JTextField expenseTwo = new JTextField(10);
	private JTextField incomeThree = new JTextField(10);
	private JTextField expenseThree = new JTextField(10);
	private JButton calc = new JButton("Find excess income");
	private JLabel netText = new JLabel("");
	private JButton whatIf = new JButton("What If");
	
//View making visible portion of GUI
	@SuppressWarnings("unused")
	public FinalView() {
		JFrame newWindow = new JFrame();
		setTitle("Budget");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//center title
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);
		west.setLayout(new GridLayout(10,0));
		
		//add income and expense fields
		income.add(incomeTitle);
		incomeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		expense.add(expenseTitle);
		expenseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		income.add(incomeOne);
		expense.add(expenseOne);
		income.add(incomeTwo);
		expense.add(expenseTwo);
		income.add(incomeThree);
		expense.add(expenseThree);
		
		netText.setHorizontalAlignment(SwingConstants.CENTER);
		//add to Jpanel
		west.add(instructions);
		west.add(income);
		west.add(expense);

		
		
		
		west.add(calc);
		west.add(netText);
		//Add JPanel to JFrame
		add(west, BorderLayout.WEST);
		//add JButton to be used for purchase calculator
		add(whatIf, BorderLayout.SOUTH);
		//JButton to not be visible until net Income calculated.
		whatIf.setVisible(false);
		
	}

	//constuctors and mutators to 'get' user input
	//variables to be used in the FinalController file

	public int getFirstIncome()
	{
		return Integer.parseInt(incomeOne.getText());	
	}
	public int getSecondIncome()
	{
		return Integer.parseInt(incomeTwo.getText());
	}
	public int getThirdIncome()
	{
		return Integer.parseInt(incomeThree.getText());
	}
	public int getFirstExpense()
	{
		return Integer.parseInt(expenseOne.getText());
	}
	public int getSecondExpense()
	{
		return Integer.parseInt(expenseTwo.getText());
	}
	public int getThirdExpense()
	{
		return Integer.parseInt(expenseThree.getText());
	}
	
	
	public String getNetText()
	{
		return netText.getText();
	}
	public void setNetText(String txt)
	{
		netText.setText(txt);
	}
	//Used to change visibility of 'What If' button
	public void setWhatIfVisTrue()
	{
		whatIf.setVisible(true);
	}
	//listeners for JButtons
	void addcalcListener(ActionListener CalcListener)
	{
		calc.addActionListener(CalcListener);
	}
	void addWhatIfListener(ActionListener WIListener)
	{
		whatIf.addActionListener(WIListener);
	}
	
	//Error message for incorrect user input or other error
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}

