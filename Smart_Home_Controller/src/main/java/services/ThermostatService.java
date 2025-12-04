package services;

import aspects.ToLog;
import aspects.ValidateThermostatPower;
import aspects.ValidateThermostatTemperature;
import model.Thermostat;
import org.springframework.stereotype.Service;
import repositories.ThermostatRepository;

@Service
public class ThermostatService {

    private final ThermostatRepository thermostatRepository;

    public ThermostatService(ThermostatRepository thermostatRepository) {
        this.thermostatRepository = thermostatRepository;
    }

    /**
     * @param thermostat        device type
     * @param turnOn       true = turn on, false = turn off
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateThermostatPower
    public String switchThermostat(Thermostat thermostat, boolean turnOn) {
        return thermostatRepository.switchThermostat(thermostat, turnOn);
    }

    /**
     * @param thermostat        device type
     * @param newTemperature    double value for the new temperature
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateThermostatTemperature
    public String setTemperature(Thermostat thermostat, double newTemperature) {
        return thermostatRepository.setTemperature(thermostat, newTemperature);
    }
}
