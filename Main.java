import java.util.*;
import java.io.*;

class Main {

  public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		ArrayList<Encode> encodedStrings = new ArrayList<Encode>();
		Utility u = new Utility();

		int minCapInt = 65;
		int maxCapInt = 90;

		int minLowInt = 97;
		int maxLowInt = 122;

		// int shift = 3;

		//Character.getNumericValue(asciiValue);
		//Character.toString(char)
		
		while(true) {
			System.out.println("\nSelect an option: Encrypt (e), Decrypt (d), Quit (q), Create File (c), or Read a File (r)");
			System.out.print(">> ");
			String action = scanner.nextLine();

			if(action.equalsIgnoreCase(("e"))) {

				System.out.println("\nInput your string that you would like to encrypt");
				System.out.print(">> ");
				String input = scanner.nextLine();

				System.out.println("\nCreate a password for this encryption");
				System.out.print(">> ");
				String password = scanner.nextLine();

				Encode newEncode = u.encodeDecode(input, random.nextInt(20) + 1, password, minCapInt, maxCapInt, minLowInt, maxLowInt);

				encodedStrings.add(newEncode);

				System.out.printf("Your encoded string is: \n\n  %s\n", newEncode.string);

				System.out.println("\nPress ENTER to continue to the menu");
				scanner.nextLine();

			} else if(action.equalsIgnoreCase(("d"))) {
				if(encodedStrings.size() <= 0) {
					System.out.println("Looks like there's nothing here! Press ENTER to continue");
					scanner.nextLine();
				} else {
					for(int i = 0; i < encodedStrings.size(); i++) {
						System.out.print((i + 1) + ". ");
						Encode encode = encodedStrings.get(i);
						int cap = 0;
						if(encode.string.length() >= 20) {
							cap = 20;
						} else {
							cap = encode.string.length();
						}
						for(int j = 0; j < cap; j++) {
							System.out.print(Character.toString(encode.string.charAt(j)));							
						}
						System.out.println();
					}

					int numberSelection = u.whileCatchInt("Select an encryption with it's corresponding number:", encodedStrings.size(), "Please provide a correct input from the list provided.", 1, "Must be AT LEAST 1. Please try again") - 1; 
					Encode selectedEncode = encodedStrings.get(numberSelection);

					System.out.print("Enter the password: ");
					String attemptPassword = scanner.nextLine();

					if(selectedEncode.password.equalsIgnoreCase(attemptPassword)) {
						// String decodedString = u.encodeDecode(encode.string, -1 * encode.shift, attemptPassword, minCapInt, maxCapInt, minLowInt, maxLowInt).string;

						String decodedString = u.encodeDecode(selectedEncode.string, (-1 * selectedEncode.shift), attemptPassword, minCapInt, maxCapInt, minLowInt, maxLowInt).string;

						System.out.println("\nThe encrypted text is: \n\n" + decodedString + "\n");

						System.out.println("Press ENTER to return to menu");
						scanner.nextLine();
					} else {
						System.out.println("Incorrect password. Returning to main menu");
					}

				}
			} else if(action.equalsIgnoreCase(("q"))) {
				System.out.println("Goodbye :(");
				break;
			} else if(action.equalsIgnoreCase(("c"))) {
				try {
					System.out.println("Input the filename for the new .txt file");
					System.out.print(">> ");
					String fileName = scanner.nextLine();

					for(int i = 0; i < fileName.length(); i++) {
						if(fileName.charAt(i) == '.') {
							String substring = fileName.substring(i, fileName.length());
							fileName.replace(substring, "");
						}
					}
					
					File newFile = new File(fileName + ".txt");

					System.out.println("Input some text for this new .txt file");
					System.out.print(">> ");
					String newFileString = scanner.nextLine();

					String yesNo = u.pickFromOptions(new String[] {"y", "n"}, "Would you like to encrypt this file? (Y/N)");

					String stringForFile = "";

					if(yesNo.equalsIgnoreCase("y")) {

						System.out.println("\nCreate a password for this encryption");
						System.out.print(">> ");
						String password = scanner.nextLine();

						Encode newEncode = u.encodeDecode(newFileString, random.nextInt(20) + 1, password, minCapInt, maxCapInt, minLowInt, maxLowInt);

						stringForFile = newEncode.string;
						encodedStrings.add(newEncode);
						
					} else {
						stringForFile = newFileString;
					}

					
					if(newFile.createNewFile()) {
						
					}

					FileWriter fileWriter = new FileWriter(newFile);
					fileWriter.write(stringForFile);

					fileWriter.close();

					System.out.println("\nOperation completed. Press ENTER to continue to the menu");
					scanner.nextLine();

				} catch (IOException e) {
					System.out.println("An error has occured. Press ENTER to return to the menu");
					
					scanner.nextLine();
				}
			} else if(action.equalsIgnoreCase(("r"))) {
				// try {
				// 	System.out.println("Input the filename for the existing .txt file");
				// 	System.out.print(">> ");
				// 	String fileName = scanner.nextLine();

				// 	readString​(Path path);
        // } catch (FileNotFoundException fnfe)  {
        //   System.out.println(fileName + " cannot be found. Press ENTER to return to the menu");
				// 	scanner.nextLine();
        // } catch (IOException ioe) {
				// 	System.out.println("An error has occured. Press ENTER to return to the menu");
				// 	scanner.nextLine();
				// }
			}
			
			 else {
				System.out.println("Please type a correct command \n");
			}
		}
  }
}