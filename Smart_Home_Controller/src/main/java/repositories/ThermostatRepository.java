package repositories;

import model.Thermostat;

public interface ThermostatRepository {
    String setTemperature(Thermostat thermostat, double newTemperature);
    String switchThermostat(Thermostat thermostat, boolean turnOn);
}
