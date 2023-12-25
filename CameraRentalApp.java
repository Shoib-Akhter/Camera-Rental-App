
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CameraRentalApp {

	private ArrayList<Camera> cameraList;
	private int nextCameraId;
	private double walletBalance;

	public CameraRentalApp() {
		cameraList = new ArrayList<>();
		nextCameraId = 1;
		walletBalance = 0.0;
	}

	public int getCurrentId() {
		return nextCameraId;
	}

	public double getCurrentWalletBalance() {
		return walletBalance;
	}

	public void addCamera(int id, String brand, String model, double perDayRent) {
		Camera camera = new Camera(nextCameraId, brand, model, perDayRent);
		cameraList.add(camera);
		nextCameraId++;
	}

	public List<Camera> getCameraList() {
		return cameraList;
	}

	public void displayCameraList() {
		if (cameraList.isEmpty()) {
			System.out.println("NO DATA PRESENT AT THIS MOMENT!");
			return;
		} else {
			System.out.println("==================================================================");
			System.out.println("CAMERA ID   BRAND      MODEL      PRICE(PER DAY)       STATUS");
			System.out.println("==================================================================");
			for (Camera camera : cameraList) {
				String status = camera.isRented() ? "Rented" : "Available";
				System.out.printf("  %-7d   %-10s   %-10s   %-13.2f   %-12s  \n",
						camera.getId(), camera.getBrand(), camera.getModel(), camera.getPerDayRent(), status);
			}
			System.out.println("==================================================================");
		}
	}

	public void removeCameraById() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {

			int cameraId = scanner.nextInt();
			scanner.nextLine();
			Camera cameraToRemove = null;
			for (Camera camera : cameraList) {
				if (camera.getId() == cameraId) {
					cameraToRemove = camera;
					break;
				}
			}

			if (cameraToRemove != null) {
				cameraList.remove(cameraToRemove);
				System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST.");
			} else {
				System.out.println("CAMERA WITH MENTIONED ID NOT FOUND IN THE LIST.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid integer camera ID.");
			scanner.nextLine(); // Clear the input buffer
		}
	}


	public void displayAvailableCameras() {
		System.out.println("======================================================================");
		System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", "CAMERA ID", "BRAND", "MODEL", "PRICE(PER DAY)", "STATUS");
		System.out.println("======================================================================");

		boolean availableCamerasExist = false;
		for (Camera camera : cameraList) {
			if (!camera.isRented()) {
				availableCamerasExist = true;
				System.out.printf("%-10d %-15s %-15s %.2f %-20s%n", camera.getId(), camera.getBrand(), camera.getModel(), camera.getPerDayRent(), "	  Available");
			}
		}

		if (!availableCamerasExist) {
			System.out.println("NO AVAILABLE CAMERA AT THIS MOMENT !");
		}
	}

	public void rentCamera(int cameraId, Camera camera) {
		Camera selectedCamera = null;
		for (Camera c : cameraList) {
			if (c.getId() == cameraId) {
				selectedCamera = c;
				break;
			}
		}

		if (selectedCamera == null) {
			System.out.println("CAMERA WITH ID " + cameraId + " NOT FOUND.");
			return;
		}

		if (selectedCamera.isRented()) {
			System.out.println("CAMERA WITH ID " + cameraId + " IS ALREADY RENTED.");
			return;
		}

		if (walletBalance < selectedCamera.getPerDayRent()) {
			System.out.println("ERROR : TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE. PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET.");
			return;
		}

		selectedCamera.setRented(true);
		walletBalance -= selectedCamera.getPerDayRent();
		System.out.printf("YOUR TRANSACTION FOR CAMERA - %s %s WITH RENT INR.%.2f HAS SUCCESSFULLY COMPLETED.\n",
				selectedCamera.getBrand(), selectedCamera.getModel(), selectedCamera.getPerDayRent());
	}

	public void depositToWallet(double amount) {
		walletBalance += amount;
		System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR." + walletBalance);
	}

	public void sortCameraList() {
		Collections.sort(cameraList, Comparator.comparing(Camera::getBrand).thenComparing(Camera::getModel));
	}
}