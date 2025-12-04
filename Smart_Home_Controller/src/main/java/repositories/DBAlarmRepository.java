package repositories;

import model.Alarm;
import model.Event;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DBAlarmRepository implements AlarmRepository{
    @Override
    public String switchAlarm(Alarm alarm, boolean turnOn) {
        alarm.setLastEvent(new Event(LocalDateTime.now(), turnOn));

        return "SUCCESS";
    }
}
