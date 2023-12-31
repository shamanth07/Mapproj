
// Description: This Java code represents a simple computer store management system. 
// The program allows users to interact with the store by performing various actions such as adding new computers, modifying computer information, displaying computers by brand or price, and quitting the program. 
// The code utilizes a Computer class to handle computer information and implements functionalities like password verification, managing inventory, and displaying computer details based on user input. 
// The program runs in a loop until the user chooses to exit, enhancing the user experience in interacting with the virtual computer store.
package sam;

import java.util.Scanner;

public class ComputerStore {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the store ");
		// This line declares an integer variable named maxComp and initializes it to 0.
		int maxComp = 0;
		// This line starts a do-while loop that prompts the user to enter the number of
		// computers to store until a positive value is provided.
		do {
			System.out.println("Please enter the number of computers to store : ");
			maxComp = kb.nextInt();
			if (maxComp <= 0)
				System.out.println("Please enter a positive number");
		} while (maxComp <= 0);
		// creation of inventory array of max computer size
		Computer[] inventory = new Computer[maxComp];
		// This line starts a do-while loop that displays a menu and executes different
		// actions based on the user's choice until the user chooses to quit (option 5).
		int mainMenu;
		do {
			// Display the menu options
			System.out.println("What do you want to do?\n" + "1.\tEnter new computers (password required)\n"
					+ "2.\tChange information of a computer (password required)\n"
					+ "3.\tDisplay all computers by a specific brand\n"
					+ "4.\tDisplay all computers under a certain a price.\n" + "5.\tQuit\n"
					+ "Please enter your choice >\n");
			mainMenu = kb.nextInt();
			String pswd;
			String brand;
			double price;
			int compcount;
			int addedComputers = Computer.findNumberOfCreatedComputers();
			// Switch statement to handle different menu options
			switch (mainMenu) {
			case 1:
				// Option to enter new computers
				double prz = 0;
				System.out.println("Enter the password: ");
				pswd = kb.next();
				if (verifyPswd(pswd) == false) {
					break;
				}
				// Loop to enter details for multiple computers
				do {
					System.out.println("Enter number of computers : ");
					compcount = kb.nextInt();
					if (compcount <= 0) {
						System.out.println("Enter positive integer");
					}
				} while (compcount <= 0 || compcount > maxComp);
				// Loop to enter details for each computer
				for (int i = 0; i < compcount; i++) {
					System.out.println("Enter Brand:");
					String brnd = kb.next();
					System.out.println("Enter Model:");
					String mdl = kb.next();
					do {
						System.out.println("Enter price:");
						prz = kb.nextDouble();
						if (prz <= 0) {
							System.out.println("Please enter a positive number");
						}
					} while (prz <= 0);
					// Create a Computer object and store it in the inventory array
					inventory[addedComputers + i] = new Computer(brnd, mdl, prz);
					Computer.displayComputer(inventory[addedComputers + i]);
				}
				break;
			case 2:
				// Option to change information of a computer
				System.out.println("Enter the password: ");
				pswd = kb.next();
				if (verifyPswd(pswd) == false) {
					break;
				}
				int choice = 0;
				int computerNum = 0;
				// Loop to choose a computer to modify
				do {
					System.out.println("Enter the computer number: ");
					computerNum = kb.nextInt();

				} while (computerNum > Computer.findNumberOfCreatedComputers() || computerNum <= 0);
				computerNum--;
				// Display the selected computer's information and provide options to modify it
				if (computerNum < Computer.findNumberOfCreatedComputers() && computerNum >= 0) {
					System.out.println("Computer " + (computerNum + 1));
					System.out.println("Brand: " + inventory[computerNum].getBrand());
					System.out.println("Model: " + inventory[computerNum].getModel());
					System.out.println("Price: " + inventory[computerNum].getPrice());
					System.out.println("Serial Number: " + inventory[computerNum].getSN());
					// Loop to handle the different modification options
					do {
						System.out.println("What information would you like to change?\n" + "1.	brand\n" + "2.	model\n"
								+ "3.	price\n" + "4.	Sn\n" + "5.	Quit\n" + "Enter your choice >\n");
						choice = kb.nextInt();
						switch (choice) {
						case 1:
							// Option to update brand
							System.out.println("Update Brand");
							String brnd = kb.next();
							inventory[computerNum].setBrand(brnd);
							Computer.displayComputer(inventory[computerNum]);
							break;
						case 2:
							// Option to update model
							System.out.println("Update Model");
							String model = kb.next();
							inventory[computerNum].setModel(model);
							Computer.displayComputer(inventory[computerNum]);
							break;
						case 3:
							// Option to update price
							double prize = 0;
							do {
								System.out.println("Enter price:");
								prize = kb.nextDouble();
								if (prize <= 0) {
									System.out.println("Please enter a positive number");
								}
							} while (prize <= 0);
							inventory[computerNum].setPrice(prize);
							Computer.displayComputer(inventory[computerNum]);
							break;
						case 4:
							// Option to update serial number
							int sn = 0;
							do {
								System.out.println("Enter serial number:");
								sn = kb.nextInt();
								if (sn <= 0) {
									System.out.println("Please enter a positive number");
								}
							} while (sn <= 0);
							inventory[computerNum].setSN(sn);
							Computer.displayComputer(inventory[computerNum]);
							break;
						case 5:
							System.out.println("Thank you");
							break;
						default:
							System.out.println("Please enter between 1 and 5");
							break;
						}
					} while (choice != 5);
				}
				break;
			case 3:
				// Option to display computers by a specific brand
				System.out.println("Enter brand name: ");
				brand = kb.next();
				findComputersBy(brand, inventory);
				break;
			case 4:
				// Option to display computers under a certain price
				price = 0;
				do {
					System.out.println("Enter price:");
					price = kb.nextDouble();
					if (price <= 0) {
						System.out.println("Please enter a positive number");
					}
				} while (price <= 0);
				findCheaperThan(price, inventory);
				break;
			case 5:
				// Option to quit the program
				System.out.println("Thank you");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter between 1 and 5");
				break;
			}

		} while (mainMenu != 5);

		kb.close();
	}

	// Function to verify the password
	public static boolean verifyPswd(String pswd) {
		Scanner kb = new Scanner(System.in);
		int loopCount = 1;
		final String password = "password";
		while (!pswd.equals(password) && loopCount < 3) {
			System.out.println("Enter the password: ");
			pswd = kb.next();
			loopCount++;
		}
		if (loopCount <= 3 && pswd.equals(password)) {
			return true;
		} else
			return false;
	}

	// This line defines a function findComputersBy that displays computers by a
	// specific brand.
	public static void findComputersBy(String findComp, Computer[] inventory) {
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getBrand().equals(findComp)) {
				Computer.displayComputer(inventory[i]);
			}
		}
	}

	// This line defines a function to find computers whose price is less than given
	// price
	public static void findCheaperThan(Double CheapPrice, Computer[] inventory) {
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getPrice() <= CheapPrice) {
				Computer.displayComputer(inventory[i]);
			}
		}
	}
}
