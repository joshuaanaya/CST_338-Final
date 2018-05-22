/*Joshua Anaya - CST 338 Final GUI project
 * This is a MVC model program to calculate a net Income so you can balance your budget. It also includes separate GUI
 * window that allows you to calculate the amount of months needed to add your netIncome, if any, to meet a desired
 * purchase amount. Abstract classes created which use Multithreading used to output the month and year as well as the 
 * monthly savings amount until amount is reach. 
 */
public class MVCfinalbudget {

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		//import all files into main
		FinalView theView = new FinalView();
		FinalModel theModel = new FinalModel();
		NewWindow theWindow = new NewWindow();
		
		FinalController theController = new FinalController(theView, theModel, theWindow);
		//make main GUI window visible
		theView.setSize(theView.width, theView.height);
		theView.setVisible(true);
	}
}
