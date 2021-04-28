/*
 * Hayden Walker's Baudrate Simulator
 * 2021-04-27 23:06
 * Usage: BaudTest [file] [baudrate]
 */

import java.util.*;
import java.io.*;

public class BaudTest {
	public static void main(String[] args) throws InterruptedException {	
		// If the user provides no or too many arguments, throw an error
		if((args.length == 0) || (args.length > 2)){
			inputError();
		}
		
		try {
			// Get input file from arguments
			File inputFile = new File(args[0]);
			// Create a scanner object to read from the input file
			Scanner fileReader = new Scanner(inputFile);
			
			// Get baudrate from arguments, catching any formatting issues
			long waitTime = 1000; // Dummy value before attempting to read args	
			try {
				// Convert baudrate to bytes/second (8 bits * 1 second / bytes/second)
				waitTime = 8000 / Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				// If there was a formatting error, tell the user
				inputError();
			}
			
			// Read through each line in the file
			while(fileReader.hasNextLine()) {
				// Break current line into characters
				String thisLine = fileReader.nextLine();
				char[] thisLineChars = thisLine.toCharArray();
			
				if(thisLineChars.length != 0) {
					// If the line is not blank, iterate through each character and print it, with a pause in between to simulate baudrate
					for(char i : thisLineChars) {
						Thread.sleep(waitTime);
						System.out.print(i);
					}
					// Then make a new line
					Thread.sleep(waitTime);
					System.out.println();
				} else {
					// If the line is blank, only make a new line
					Thread.sleep(waitTime);
					System.out.println();
				}
			}
			
			// Close the file reader
			fileReader.close();
			
		} catch(FileNotFoundException e) {
			// If there was an error reading the file, tell the user
			inputError();
		}
	}
	
	static void inputError() {
		/*
		 * Catch any errors, and tell the user how to use the program
		 */
		System.out.println("Invalid arguments\nUsage: BaudTest [file] [baudrate]");
		System.exit(1);
	}
}

