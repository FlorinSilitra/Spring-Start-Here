package services;

import aspects.ToLog;
import aspects.ValidateAlarmOperation;
import model.Alarm;
import org.springframework.stereotype.Service;
import repositories.AlarmRepository;

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
        return alarmRepository.switchAlarm(alarm, turnOn);
    }
}
