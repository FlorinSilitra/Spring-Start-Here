package repositories;

import model.Thermostat;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryThermostatRepository implements ThermostatRepository{
    private final Map<String, Thermostat> thermostats = new ConcurrentHashMap<>();

    @Override
    public void switchThermostat(Thermostat thermostat) {
        thermostats.put(thermostat.getName(), thermostat);
    }

    @Override
    public void add(Thermostat thermostat) {
        thermostats.put(thermostat.getName(), thermostat);
    }

    @Override
    public Collection<Thermostat> getAll() {
        return thermostats.values();
    }
}
