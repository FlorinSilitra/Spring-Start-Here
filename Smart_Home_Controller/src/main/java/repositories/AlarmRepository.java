package repositories;

import model.Alarm;

public interface AlarmRepository {
    String switchAlarm(Alarm alarm, boolean turnOn);
}
