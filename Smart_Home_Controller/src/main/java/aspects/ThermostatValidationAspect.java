package aspects;

import model.Thermostat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(1)
public class ThermostatValidationAspect {

    private Logger logger = Logger.getLogger(ThermostatValidationAspect.class.getName());

    @Around("@annotation(aspects.ValidateThermostatPower)")
    public Object validateThermostatPower(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        Thermostat thermostat = null;
        Boolean requestedState = null;

        for (Object arg : args) {
            if (arg instanceof Thermostat t) {
                thermostat = t;
            } else if (arg instanceof Boolean b) {
                requestedState = b;
            }
        }

        if (thermostat == null || requestedState == null) {
            logger.warning("Thermostat Validation Aspect: The method signature is bad!");
            return "BAD SIGNATURE";
        }

        String action = requestedState ? "turn ON" : "turn OFF";
        logger.info("Thermostat Validation Aspect: Trying to " + action +
                " thermostat \"" + thermostat.getName() + "\"");

        boolean currentState = thermostat.isOn();

        if (requestedState == currentState) {
            logger.info("Thermostat Validation Aspect: Thermostat is already in this state."
                    + " Invalid operation!");
            return "FAIL";
        }

        Object result = joinPoint.proceed();

        logger.info("Thermostat Validation Aspect: The thermostat state changed: "
                + thermostat.getLastEvent());

        return result;
    }

    @Around("@annotation(aspects.ValidateThermostatTemperature)")
    public Object validateThermostatTemperature(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        Thermostat thermostat = null;
        Double requestedTemperature = null;

        for (Object arg : args) {
            if (arg instanceof Thermostat t) {
                thermostat = t;
            } else if (arg instanceof Double d) {
                requestedTemperature = d;
            }
        }

        if (thermostat == null || requestedTemperature == null) {
            logger.warning("Thermostat Validation Aspect: The method signature is bad!");
            return "BAD SIGNATURE";
        }

        logger.info("Thermostat Validation Aspect: Trying to set thermostat \"" +
                thermostat.getName() + "\" temperature to " + requestedTemperature);

        if (!thermostat.isOn()) {
            logger.info("Thermostat Validation Aspect: Thermostat is OFF. "
                    + "Can't change temperature. Invalid operation!");
            return "FAIL";
        }

        if (requestedTemperature < 5 || requestedTemperature > 35) {
            logger.info("Thermostat Validation Aspect: Temperature must be in [5, 35]°C."
                    + " Invalid operation!");
            return "FAIL";
        }

        Object result = joinPoint.proceed();

        logger.info("Thermostat Validation Aspect: The thermostat temperature changed. "
                + thermostat.getLastEvent());

        return result;
    }
}
