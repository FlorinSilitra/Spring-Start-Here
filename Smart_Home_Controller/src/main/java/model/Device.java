package model;

public abstract class Device {

    private String name;
    private Event lastEvent;

    public Device(){
    }

    public Device(String name, Event lastEvent) {
        this.name = name;
        this.lastEvent = lastEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(Event lastEvent) {
        this.lastEvent = lastEvent;
    }

    public boolean isOn() {
        return lastEvent != null && lastEvent.getActive();
    }
}
