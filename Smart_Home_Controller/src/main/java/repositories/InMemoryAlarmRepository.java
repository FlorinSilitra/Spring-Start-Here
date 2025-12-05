package repositories;

import model.Alarm;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAlarmRepository implements AlarmRepository{
    private final Map<String, Alarm> alarms = new ConcurrentHashMap<>();

    @Override
    public void switchAlarm(Alarm alarm) {
        alarms.put(alarm.getName(), alarm);
    }

    @Override
    public void add(Alarm alarm) {
        alarms.put(alarm.getName(), alarm);
    }

    @Override
    public Collection<Alarm> getAll() {
        return alarms.values();
    }
}
