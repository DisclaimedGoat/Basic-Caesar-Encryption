import java.util.*;

public class Utility {

	public Encode encodeDecode(String input, int shift, String password, int minCap, int maxCap, int minLow, int maxLow) {
		String encodedString = "";
		Random random = new Random();

		for(int i = 0; i < input.length(); i++) {
			char tChar = input.charAt(i);
			int asciiChar = tChar;

			if (asciiChar == 32) {
					asciiChar = random.nextInt(126) + 128;
					encodedString += Character.toString((char)asciiChar);
				
			} else if (asciiChar >= 128 && asciiChar <= 256) {
				asciiChar = 32;
				encodedString += Character.toString((char)asciiChar);
				
			} else if((asciiChar >= minCap && asciiChar <= maxCap) || (asciiChar >= minLow && asciiChar <= maxLow)) {
				
				//If the char is Uppercase, then isLowercase is false and vise versa
				boolean isLowercase = asciiChar >= minLow && asciiChar <= maxLow;

				if(isLowercase) {
					asciiChar += shift;

					if(asciiChar > maxLow) {
						int difference = asciiChar - maxLow;
						asciiChar = minLow + difference;
					} else if (asciiChar < minLow) {
						int difference = minLow - asciiChar;
						asciiChar = maxLow - difference;
					}

				} else {
					asciiChar += shift;

					if(asciiChar > maxCap) {
						int difference = asciiChar - maxCap;
						asciiChar = minCap + difference;
					}else if (asciiChar < minCap) {
						int difference = minCap - asciiChar;
						asciiChar = maxCap - difference;
					}
				} 
				encodedString += Character.toString((char)asciiChar);

			} else {
				encodedString += Character.toString((char)asciiChar);
			}
		}
		return new Encode(shift, encodedString, password);
	}

	public int whileCatchInt(String prompt, int upperBound, String exceedsUpperBound, int lowerBound, String exceedsLowerBound) {
		Scanner scanner = new Scanner(System.in);
		//Basic prompt. One-time message
		System.out.println("- " + prompt);
		//Set for the while loop
		int input = lowerBound - 1; 

		//Checks to see if the user inputed the correct number and format. Must run at least once
		do {
			try {
				System.out.print(">> ");
				input = scanner.nextInt(); 
				
				//If the user exceeded the bounds. If so, display the exceeds bounds message 
				if(input < lowerBound) {
					System.out.println(exceedsLowerBound);
				} else if(input > upperBound)	{
					System.out.println(exceedsUpperBound);
				}
			} catch (Exception e) {
				System.out.println("Oops! Please provide an allowed input.");
			} 
		} while(input < lowerBound || input > upperBound);

		return input;
	}

	public String pickFromOptions(String[] possibleChoices, String prompt) {
		Scanner scanner = new Scanner(System.in);
		boolean hasCorrectInput = false;
		String input = "";

		do {

			System.out.println(prompt);
			System.out.print(">> ");
			input = scanner.nextLine();

			for(String option : possibleChoices) {
				if(option.equalsIgnoreCase(input)) {
					hasCorrectInput = true;
				}
			}

		} while(!hasCorrectInput);

		return input;
	}

}