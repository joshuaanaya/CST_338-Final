

public class FinalModel {
	//calculation variables
	private int incomeValue;
	private int expenseValue;
	private int netIncome;
	private int saveMonths;
	//validate income entered

	//Calculate income
	public void addIncome(int num1, int num2, int num3)
	{
		incomeValue = num1 + num2 + num3;
		
	}
	//'get' to be used in Controller
	public int getIncomeValue()
	{
		return incomeValue;
	}
	//calculate expenses
	public void addExpenses(int num1, int num2, int num3)
	{
		expenseValue = num1 + num2 + num3;
	}
	public int getExpenseValue()
	{
		return expenseValue;
	}
	//Calculate net Income after Income and Expenses calculated
	public void NetIncome()
	{
		netIncome = incomeValue - expenseValue;
	}
	//return Income to be used in Controller
	public int getNetIncome()
	{
		return netIncome;
	}
	//Calculate months need to amount savings
	public void monthsToSave(int purchase)
	{
		saveMonths = purchase / netIncome;
		Math.round(saveMonths);
	}
	//return whole number of months needed for savings calculator
	public int getMonths()
	{
		return saveMonths;
	}

	
	
}



