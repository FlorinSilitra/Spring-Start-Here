package repositories;

import model.Event;
import model.Thermostat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DBThermostatRepository implements ThermostatRepository{
    @Override
    public String setTemperature(Thermostat thermostat, double newTemperature) {
        thermostat.setLastEvent(new Event(LocalDateTime.now(), true));
        thermostat.setTemperature(newTemperature);

        return "SUCCESS";
    }

    @Override
    public String switchThermostat(Thermostat thermostat, boolean turnOn) {
        thermostat.setLastEvent(new Event(LocalDateTime.now(), turnOn));

        if(turnOn){
            thermostat.setTemperature(22);
        }
        else
        {
            thermostat.setTemperature(0);
        }

        return "SUCCESS";
    }
}
