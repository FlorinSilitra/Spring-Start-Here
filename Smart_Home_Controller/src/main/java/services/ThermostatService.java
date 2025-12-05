package services;

import aspects.ToLog;
import aspects.ValidateThermostatPower;
import aspects.ValidateThermostatTemperature;
import model.Event;
import model.Thermostat;
import org.springframework.stereotype.Service;
import repositories.ThermostatRepository;

import java.time.LocalDateTime;
import java.util.Collection;

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
        thermostat.setLastEvent(new Event(LocalDateTime.now(), turnOn));

        if(turnOn){
            thermostat.setTemperature(22);
        }
        else
        {
            thermostat.setTemperature(0);
        }
        thermostatRepository.switchThermostat(thermostat);

        return "SUCCESS";
    }

    /**
     * @param thermostat        device type
     * @param newTemperature    double value for the new temperature
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateThermostatTemperature
    public String setTemperature(Thermostat thermostat, double newTemperature) {
        thermostat.setLastEvent(new Event(LocalDateTime.now(), true));
        thermostat.setTemperature(newTemperature);

        thermostatRepository.switchThermostat(thermostat);

        return "SUCCESS";
    }

    /**
     * Add a thermostat to the repository
     */
    public void addThermostat(Thermostat thermostat) {
        thermostatRepository.add(thermostat);
    }

    /**
     * Return all thermostats from repository
     */
    public Collection<Thermostat> getAll() {
        return thermostatRepository.getAll();
    }
}
