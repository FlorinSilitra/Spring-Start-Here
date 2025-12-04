package aspects;

import model.Light;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.logging.Logger;

@Aspect
@Order(1)
public class LightValidationAspect {

    private Logger logger = Logger.getLogger(LightValidationAspect.class.getName());

    @Around("@annotation(aspects.ValidateLightOperation)")
    public Object validateLightOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Light light = null;
        Boolean requestedState = null;

        for (Object arg : args) {
            if (arg instanceof Light l) {
                light = l;
            } else if (arg instanceof Boolean b) {
                requestedState = b;
            }
        }

        if (light == null || requestedState == null) {
            logger.warning("Light Validation Aspect: The method signature is bad!");
            return "BAD SIGNATURE";
        }

        String action = requestedState ? "turn on" : "turn off";
        logger.info("Light Validation Aspect: Try to "
                + action + " the light \"" + light.getName() + "\"");

        boolean currentState = light.isOn();
        if (requestedState == currentState) {
            logger.info("Light Validation Aspect: The light is already in this state."
                    + " Invalid operation!");
            return "FAIL";
        }

        Object result = joinPoint.proceed();

        logger.info("Light Validation Aspect: The light was changed with "
                + light.getLastEvent());

        return result;
    }

    @Around("@annotation(aspects.ValidateLightBrightness)")
    public Object validateLightBrightness(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Light light = null;
        Integer requestedBrightness = null;

        for (Object arg : args) {
            if (arg instanceof Light l) {
                light = l;
            } else if (arg instanceof Integer i) {
                requestedBrightness = i;
            }
        }

        if (light == null || requestedBrightness == null) {
            logger.warning("Light Validation Aspect: The method signature is bad!");
            return "BAD SIGNATURE";
        }

        logger.info("Light Validation Aspect: Try to switch brightness"
                + "for the light \"" + light.getName() + "\"");

        boolean currentState = light.isOn();
        if (!currentState) {
            logger.info("Light Validation Aspect: The light is off, can't change brightness."
                    + " Invalid operation!");
            return "FAIL";
        }

        if(requestedBrightness < 0 || requestedBrightness > 100) {
            logger.info("Light Validation Aspect: The requested brightness might be in [0, 100]."
                    + " Invalid operation!");
            return "FAIL";
        }

        Object result = joinPoint.proceed();

        logger.info("Light Validation Aspect: The light brightness was changed with "
                + light.getLastEvent());

        return result;
    }
}
