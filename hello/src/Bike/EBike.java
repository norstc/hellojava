package Bike;

public class EBike extends Bike{
	private double batteryVoltage;

	public EBike(){
		setBatteryVoltage(60);
	}
	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

}
