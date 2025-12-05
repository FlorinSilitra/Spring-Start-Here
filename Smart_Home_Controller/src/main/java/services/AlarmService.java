package services;

import aspects.ToLog;
import aspects.ValidateAlarmOperation;
import model.Alarm;
import model.Event;
import org.springframework.stereotype.Service;
import repositories.AlarmRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    /**
     * @param alarm   device type
     * @param turnOn  true = arm, false = disarm
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateAlarmOperation
    public String switchAlarm(Alarm alarm, boolean turnOn) {
        alarm.setLastEvent(new Event(LocalDateTime.now(), turnOn));
        alarmRepository.switchAlarm(alarm);

        return "SUCCESS";
    }

    /**
     * Add an alarm to the repository
     */
    public void addAlarm(Alarm alarm) {
        alarmRepository.add(alarm);
    }

    /**
     * Return all alarms from repository
     */
    public Collection<Alarm> getAll() {
        return alarmRepository.getAll();
    }
}
