package lab2;
import java.util.InputMismatchException;
import java.util.Scanner;

public class lab2 {
	static Scanner scn = new Scanner(System.in);
	public static void main(String[] args) {
		double length = 0, width = 0, height = 0;
		double area = 0, perimeter = 0, volume = 0, wallSurfaceArea = 0;
		
		char cont = ' ';
		Boolean allStatsEntered = false;
		
		System.out.println("Welcome to Grand Circus' Room Detail Generator!");

		do 
		{
			allStatsEntered = false;
			try {
				//Taking input from user
				length = promptUser("Length");
				width = promptUser("Width");
				height = promptUser("Height");
				
				//Setting this to true (for exception catching)
				allStatsEntered = true;
				
				System.out.println("");

				//Calculating room stats based on input
				area = width * length;
				perimeter = 2.0 * (length + width);
				volume = area * height;
				wallSurfaceArea = (2.0 * area) + (perimeter * height);
								
				//Printing calculated statistics
				printRmStat("Area",area);
				printRmStat("Perimeter",perimeter);
				printRmStat("Volume",volume);
				printRmStat("Surface area (of floor, walls, and ceiling)", wallSurfaceArea);
				
				System.out.println("");
				
				//Prompting the user to continue
				System.out.print("Continue? (y/n)");
				cont = Character.toLowerCase(scn.next().charAt(0));
				if ((cont != 'y') && (cont != 'n')) {
					throw new InputMismatchException();
				}
			}
			catch (InputMismatchException e) {
				System.out.println("");
				System.out.println("Data entry error");
				if (!allStatsEntered) {
					System.out.println("Perhaps you just entered a letter or special character where you were supposed to enter a number? Or maybe you messed up when trying to enter the dimensions using \' and \" ?");
				}
				else {
					System.out.println("Please enter either a y for yes, or an n for no");
				}
				System.out.println("Let's try this again.......");
				System.out.println("");
				scn.nextLine();
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
			
		} while (cont != 'n');
		scn.close();
	}
	/*
	 * Asks user to input a room dimension and returns what was entered
	 */
	static double promptUser(String varName) {
		double returnVal = 0.0;
		System.out.print("Enter " + varName + ":");
		
		//Reading input string
		String rawInput = scn.next();
		
		//Checking if it includes ' and ""
		int in1 = rawInput.indexOf('\'');
		if (in1 != -1) {
			if (rawInput.charAt(rawInput.length() - 1) != '\"') {
				throw new InputMismatchException();
			}
			else
			{
				rawInput = rawInput.substring(0,rawInput.length() - 1);
				int numFeet = Integer.valueOf(rawInput.split("\'")[0]);
				int numInches = Integer.valueOf(rawInput.split("\'")[1]);
				returnVal = numFeet + ((double) numInches / 12.0);
			}
		}
		else {
			returnVal = Double.valueOf(rawInput);
		}
		
		scn.nextLine();
		return returnVal;
	}
	
	/*
	 * Prints out the given statistic in the format required by lab spec
	 */
	static void printRmStat(String statName, double stat) {
		
		System.out.println(statName + ": " + stat);
	}
	
	
}
