package aspects;

import model.Alarm;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(1)
public class AlarmValidationAspect {

    private Logger logger = Logger.getLogger(AlarmValidationAspect.class.getName());

    @Around("@annotation(aspects.ValidateAlarmOperation)")
    public Object validateAlarmOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Alarm alarm = null;
        Boolean requestedState = null;

        for (Object arg : args) {
            if (arg instanceof Alarm a) {
                alarm = a;
            } else if (arg instanceof Boolean b) {
                requestedState = b;
            }
        }

        if (alarm == null || requestedState == null) {
            logger.warning("Alarm Validation Aspect: The method signature is bad!");
            return "BAD SIGNATURE";
        }

        String action = requestedState ? "arm" : "disarm";
        logger.info("Alarm Validation Aspect: Try to "
                + action + " the alarm \"" + alarm.getName() + "\"");

        boolean currentState = alarm.isOn();
        if (requestedState == currentState) {
            logger.info("Alarm Validation Aspect: The alarm is already in this state."
                    + " Invalid operation!");
            return "FAIL";
        }

        Object result = joinPoint.proceed();

        logger.info("Alarm Validation Aspect: The alarm was changed with "
                + alarm.getLastEvent());

        return result;
    }
}
