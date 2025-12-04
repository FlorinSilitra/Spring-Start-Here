package model;

public class Alarm extends Device {

    public Alarm() {
        super();
    }

    public Alarm(String name, Event lastEvent) {
        super(name, lastEvent);
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "name='" + getName() + '\'' +
                ", lastEvent=" + getLastEvent() +
                '}';
    }
}
