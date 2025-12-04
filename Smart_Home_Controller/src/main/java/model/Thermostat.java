package model;

public class Thermostat extends Device {

    private double temperature; // 5 - 35

    public Thermostat() {
        super();
        this.temperature = 0;
    }

    public Thermostat(String name, Event lastEvent, double temperature) {
        super(name, lastEvent);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Thermostat{" +
                "name='" + getName() + '\'' +
                ", lastEvent=" + getLastEvent() +
                ", temperature=" + temperature +
                '}';
    }
}
