
import java.util.InputMismatchException;
import java.util.Scanner;

//Driver code
public class Main {
	public static void main(String[] args) {
		//Initialising scanner class
		Scanner scanner = new Scanner(System.in);
		//Initialising objects by using their class
		LoginManager loginManager = new LoginManager();
		CameraRentalApp app = new CameraRentalApp();
		System.out.println("+--------------------------------+");
		System.out.println("|  WELCOME TO CAMERA RENTAL APP  |");
		System.out.println("+--------------------------------+");
		boolean authenticated = false;

		while (!authenticated) {
			System.out.println("\nPLEASE LOGIN TO CONTINUE - ");
			System.out.print("USERNAME - ");
			String username = scanner.next();
			System.out.print("PASSWORD - ");
			String password = scanner.next();

			if (loginManager.authenticateUser(username, password)) {
				authenticated = true;
			} else {
				System.out.println("INVALID USERNAME OR PASSWORD. PLEASE TRY AGIN ! ");
			}
		}
		boolean exit = false;
		while (!exit) {
			System.out.println("1. MY CAMERA");
			System.out.println("2. RENT A CAMERA");
			System.out.println("3. VIEW ALL CAMERAS");
			System.out.println("4. MY WALLET");
			System.out.println("5. Exit");
			try {
				int choice = scanner.nextInt();

				switch (choice) {

				case 1:
					boolean cameraExit = false;
					do {
						System.out.println("1. ADD");
						System.out.println("2. REMOVE");
						System.out.println("3. VIEW MY CAMERAS");
						System.out.println("4. GO TO PREVIOUS MENU");
						int cameraChoice = scanner.nextInt();

						switch (cameraChoice) {

						case 1: 
							System.out.print("ENTER THE CAMERA BRAND - ");
							String brand = scanner.next();
							System.out.print("EMTER THE MODEL - ");
							String model = scanner.next();
							System.out.print("ENTER THE PER DAY PRICE (INR) - ");
							double perDayRent = scanner.nextDouble();
							app.addCamera(choice, brand, model, perDayRent);
							System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST.");
							break;
						case 2: 
							app.displayCameraList();
							System.out.println("ENTER THE CAMERA ID TO REMOVE - ");
							app.removeCameraById();
							break;
						case 3:
							System.out.println("3. VIEW MY CAMERAS");
							app.displayCameraList();
							break;
						case 4:
							cameraExit = true;
							break;
						default:
							System.out.println("INVALID CHOICE!");
						}	
					} while (!cameraExit);
					break;

				case 2:
					System.out.println("FOLLOWING IS THE LIST OF AVAILABLE CAMERA(S) - ");
					app.displayAvailableCameras(); // Call a method to display the available cameras
					System.out.println("ENTER THE CAMERA ID YOU WANT TO RENT - ");
					int cameraId = scanner.nextInt();
					scanner.nextLine();

					Camera selectedCamera = null;
					for (Camera camera : app.getCameraList()) {
						if (camera.getId() == cameraId) {

							selectedCamera = camera;
							break;
						}
					}

					if (selectedCamera != null) {
						app.rentCamera(cameraId, selectedCamera);
					} else {
						System.out.println("INVALID CAMERA ID.");
					}
					break;

				case 3:
					app.displayCameraList();
					break;

				case 4:
					double walletBalance = app.getCurrentWalletBalance();
					System.out.println("YOUR CURRENT WALLET BALANCE IS - INR." + walletBalance);
					System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2.NO)\n");
					int walletChoice = scanner.nextInt();

					switch (walletChoice) {

					case 1:
						System.out.print("ENTER THE AMOUNT (INR) - ");
						double amount = scanner.nextDouble();
						app.depositToWallet(amount);
						break;
					case 2:
						System.out.println("NO UPDATES HAS BEEN DONE BY THE USER. THUS CURRENT BALANCE - INR." +walletBalance);
						break;
					default:
						System.out.println("INVALID CHOICE.");
					}
					break;

				case 5:
					exit = true;
					System.out.println("EXITING THE APPLICATION... THANK YOU!");
					break;
				default:
					System.out.println("INVALID CHOICE!");
					break;
				}
			} catch (InputMismatchException e) {
				// TODO: handle exception
				System.out.println("INVALID INPUT. PLEASE ENTER A VALID INTEGER CHOICE.");
				scanner.nextLine();
			}
		}

		scanner.close();
	}
}
