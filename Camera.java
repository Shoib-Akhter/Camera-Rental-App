
public class Camera {

	private int id;
	private String brand;
	private String model;
	private double perDayRent;
	private boolean rented;

	public Camera(int id, String brand, String model, double perDayRent) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.perDayRent = perDayRent;
		this.rented = false; //Initialize as not rented
	}

	// Getters and setters for the camera

	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public double getPerDayRent() {
		return perDayRent;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}
}
