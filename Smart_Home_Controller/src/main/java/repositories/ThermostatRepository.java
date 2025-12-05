package repositories;

import model.Thermostat;

import java.util.Collection;

public interface ThermostatRepository {
    void switchThermostat(Thermostat thermostat);
    void add(Thermostat thermostat);
    Collection<Thermostat> getAll();
}
