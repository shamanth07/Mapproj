package sam;

import java.util.Scanner;

public class ComputerStore {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to Computer Store!! ");
		int maxComp = 0;
		do {
			System.out.println("Enter the maximum of computers that computer store can contain : ");
			maxComp = kb.nextInt();
			if (maxComp <= 0)
				System.out.println("Invalid, the inventory should be positive");
		} while (maxComp <= 0);
		// creation of inventory array of max computer size
		Computer[] inventory = new Computer[maxComp];

		int mainMenu;
		do {
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
			switch (mainMenu) {
			case 1:
				System.out.println("Enter the password: ");
				pswd = kb.next();
				if (verifyPswd(pswd) == false) {
					break;
				}
				do {
					System.out.println("How many computers you want to enter : ");
					compcount = kb.nextInt();
					if (compcount <= 0) {
						System.out.println("Invalid value");
					}
				} while (compcount <= 0 || compcount > maxComp);
				for (int i = 0; i < compcount; i++) {
					System.out.println("Enter Brand:");
					String brnd = kb.next();
					System.out.println("Enter Model:");
					String mdl = kb.next();
					System.out.println("Enter price:");
					double prz = kb.nextDouble();
					inventory[i] = new Computer();
					inventory[i].setBrand(brnd);
					inventory[i].setModel(mdl);
					inventory[i].setPrice(prz);
					Computer.displayComputer(inventory[i]);
				}
				break;
			case 2:
				int option = 0;
				System.out.println("Enter the password: ");
				pswd = kb.next();
				if (verifyPswd(pswd) == false) {
					break;
				}
				int choice = 0;
				int computerNum = 0;
				do {
				System.out.println("Enter the computer number: ");
				computerNum = kb.nextInt() - 1;
				if (computerNum <= Computer.findNumberOfCreatedComputers() && computerNum >= 0) {
					System.out.println("Brand" + inventory[computerNum].getBrand());
					System.out.println("Model" + inventory[computerNum].getModel());
					System.out.println("Price" + inventory[computerNum].getPrice());
					System.out.println("Serial Number" + inventory[computerNum].getSN());
				do {
					System.out.println("What information would you like to change?\n"
							+ "1.	brand\r\n"
							+ "2.	model\r\n"
							+ "3.	SN\r\n"
							+ "4.	price\r\n"
							+ "5.	Quit\r\n"
							+ "Enter your choice >\r\n");
					choice = kb.nextInt();
					switch(choice) {
				    case 1: 
				    	System.out.println("Update Brand");
				    	String brnd = kb.next();
				    	inventory[computerNum].setBrand(brnd);
				    	Computer.displayComputer(inventory[computerNum]);
				    	break;
				    case 2:
				    	System.out.println("Update Model");
				    	String model = kb.next();
				    	inventory[computerNum].setModel(model);
				    	Computer.displayComputer(inventory[computerNum]);
				    	break;
				    case 3:
				    	System.out.println("Update Price");
				    	double prize = kb.nextInt();
				    	inventory[computerNum].setPrice(prize);
				    	Computer.displayComputer(inventory[computerNum]);
				    	break;
				    case 4:
				    	System.out.println("Update Serial Number");
				    	int sn = kb.nextInt();
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
				}while(choice != 5);
				}
				else {
					System.out.println("Entered number is invalid");
					System.out.println("1.Please enter another number: ");
					System.out.println(" or 2.quit this operation and go back to main menu");
					option = kb.nextInt();
				}
				}while(computerNum >= Computer.findNumberOfCreatedComputers() && computerNum <= 0);
				break;
			case 3:
				System.out.println("Enter brand name: ");
				brand = kb.next();
				findComputersBy(brand, inventory);
				break;
			case 4:
				System.out.println("Enter the price: $");
				price = kb.nextDouble();
				findCheaperThan(price, inventory);
				break;
			case 5:
				System.out.println("Thank you");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter between 1 and 5");
				break;
			}

		} while (mainMenu != 5);

	}

	public static boolean verifyPswd(String ParP) {
		Scanner kb = new Scanner(System.in);
		int loopCount = 1;
		final String password = "password";
		while (!ParP.equals(password) && loopCount < 3) {
			System.out.println("Enter the password: ");
			ParP = kb.next();
			loopCount++;
		}
		if (loopCount <= 3 && ParP.equals(password)) {
			return true;
		} else
			return false;
	}

	public static void findComputersBy(String findComp, Computer[] inventory) {
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getBrand().equals(findComp)) {
				Computer.displayComputer(inventory[i]);
			}
		}
	}

	public static void findCheaperThan(Double CheapPrice, Computer[] inventory) {
		for (int i = 0; i < Computer.findNumberOfCreatedComputers(); i++) {
			if (inventory[i].getPrice() <= CheapPrice) {
				Computer.displayComputer(inventory[i]);
			}
		}
	}
}