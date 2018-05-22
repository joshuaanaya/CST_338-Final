import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class FinalController {
	//add files to be used in controller
	private FinalView theView;
	private FinalModel theModel;
	private NewWindow theWindow;
	
	public FinalController(FinalView theView, FinalModel theModel, NewWindow theWindow)
	{
		//GUI Constructed with all file information now available to be used
		this.theView = theView;
		this.theModel = theModel;
		this.theWindow = theWindow;
		//add listeners to JButtons
		this.theView.addcalcListener(new CalcListener());
		this.theView.addWhatIfListener(new WIListener());
		
		
	}
	//This listener calculates the net income available.
	class CalcListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			try 
			{
			//Income and expenses received from user input. 
			int income1 = theView.getFirstIncome();
			int income2 = theView.getSecondIncome();
			int income3 = theView.getThirdIncome();
			int expense1 = theView.getFirstExpense();
			int expense2 = theView.getSecondExpense();
			int expense3 = theView.getThirdExpense();
			
				
				//This is actual calculations section
				theModel.addIncome(income1, income2, income3);
				theModel.addExpenses(expense1, expense2, expense3);
				
				theModel.NetIncome();
				if(theModel.getNetIncome()> 0)
				{
					//set JPanel with net income and Make What If button visible
					if(theModel.getNetIncome() > 0)
					{
					theView.setNetText("Your net income is $" + Integer.toString(theModel.getNetIncome()));
					theView.setWhatIfVisTrue();
					}
				}
				else//output if expenses more than income
					theView.setNetText("Your expenses are more than your income. Try finding a way to reduce your expenses.");
			}
			//catch for input error
			catch(NumberFormatException e)
			{
				theView.displayErrorMessage("You must enter integers into all boxes. Enter 0 for any boxes that do not apply.");
			}
		}
	}
	//What If button listener
	class WIListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//Allows user to used Savings Plan calculator.
			//net income is received as it has already been calculated.
			theWindow.setIncome("NetIncome: $" + Integer.toString(theModel.getNetIncome()));
			//action listener for Calculate button on Savings Plan screen
			
			theWindow.addcalListener(new CalListener());
			//size and location on screen set and made visible
			theWindow.setSize(500,400);
			theWindow.setLocation(600,300);
			theWindow.setVisible(true);
		}
	//action for Savings Plan calculator button
	class CalListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{
			//validate expense input entered
			int p = theWindow.getExpense();
			
			//Months thread outputs months and year required to add net Income to purchase value
			Months m = new Months();
			//Save thread created whose output shows the net income added until it meets purchase value
			Save s = new Save();
			//months thread started
			m.start();
			//sleep implemented to ensure proper timing of multithreading output
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {}
			//Save thread started
			s.start();
			//messsage if your desired purchase is less than current month net Income
			if (p < theModel.getNetIncome())
			{
				JOptionPane.showMessageDialog(null, "You have enough saved this month for the desired purchase.");
			}
			else
			{
				//string set for message dialog to be used as output is to console 
				theWindow.setStr1("See the results in the console section.");
				//display message dialog
				JOptionPane.showMessageDialog(null, theWindow.getStr1());
			}
			}
			//catch for input error
			catch(NumberFormatException e)
			{
				theView.displayErrorMessage("You must enter integers into all boxes. Enter 0 for any boxes that do not apply.");
			}
			
		}
		
	}
		
	}
	//Month thread used to calculate months required to add up net Income to desired expense
	class Months extends Thread
	{	//thread implemented with run()
		public void run()
		{
			try
			{
			//desired purchase amount received
			int purchase = theWindow.getExpense();
			//purchase amount used to calculate months needed to save desired amount. 
			theModel.monthsToSave(purchase);
			//months amount received
			int months = theModel.getMonths();
			//Calendar used to get current month and year
			Calendar cal = Calendar.getInstance();
			//months start at 0. 1 added to match normal reading output.(i.e. 10 = October but .get(Calendar.MONTH) returns 9
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			
			//for loop used to output months to console in real time. 
			for (int j = 0 ; j < months; j++)
			{	//used to output months as 1 through 12 as it keeps count up to the months needed to add up Net income to 
				// desired purchase price.
				int i = month + j; 
				int x = 12; 
				if(i < x)
					i = i + 0;
				else if(i % 12 == 0)
					i = 12;
				else if(i > 12 && i <= 24)
					i = i - 12;
				else if(i > 24 && i <= 36)
					i = i - 24;
				else if(i > 36 && i <= 48)
					i = i - 36;
				else if(i > 48 && i <= 60)
					i = i - 48;
				//Actual output
				System.out.print("Month of saving " + (i) + " " + year + ": ");
				//if to match year as months increase.
				if(i % 12 == 0)
				{
					year += 1;
				}
				//sleep used to slow process for viewing pleasure.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				}
			}
			catch(NumberFormatException e)
			{
				theWindow.displayErrorMessage("You must enter integers into all boxes. Enter 0 for any boxes that do not apply.");
			}
		}
	}
	/*Save thread used to calculate the amount from net income 
	 * to the desired expense amount. It takes your net income and adds
	 * it each month until you reach your desired amount. 
	 */
	class Save extends Thread
	{//Thread implemented with run()
		public void run()
		{	//needed values for net income addition
			int purchase = theWindow.getExpense();
			int savings = theModel.getNetIncome();
			//round to whole number
			Math.round(savings);
			//for loop used to output to console
			for (int i = savings; i <= purchase; i += savings)
			{
				//output of current saved amount
				System.out.print("Saved $" + i + "." );
				//output if saved amount is still less that desired purchase price.
				if(i < purchase)
					System.out.println("You still need $" + (purchase - i) + " to go.");
				else//output if you have reached your savings goal
					JOptionPane.showMessageDialog(null,"You now have enough money to make your desired purchase.");
				try {
					Thread.sleep(1000);//used to slow process for viewing capabilities
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
