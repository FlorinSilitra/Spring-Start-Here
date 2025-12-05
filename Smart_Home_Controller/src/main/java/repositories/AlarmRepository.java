package repositories;

import model.Alarm;

import java.util.Collection;

public interface AlarmRepository {
    void switchAlarm(Alarm alarm);
    void add(Alarm alarm);
    Collection<Alarm> getAll();
}
