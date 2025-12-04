package model;

public class Light extends Device {

    private int brightnessLevel; // 0 - 100

    public Light() {
        super();
        this.brightnessLevel = 0;
    }

    public Light(String name, Event lastEvent, int brightnessLevel) {
        super(name, lastEvent);
        this.brightnessLevel = brightnessLevel;
    }

    public int getBrightnessLevel() {
        return brightnessLevel;
    }

    public void setBrightnessLevel(int brightnessLevel) {
        this.brightnessLevel = brightnessLevel;
    }

    @Override
    public String toString() {
        return "Light{" +
                "name='" + getName() + '\'' +
                ", lastEvent=" + getLastEvent() +
                ", brightnessLevel=" + brightnessLevel +
                '}';
    }
}
