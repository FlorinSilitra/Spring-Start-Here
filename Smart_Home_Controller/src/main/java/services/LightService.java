package services;

import aspects.ToLog;
import aspects.ValidateLightBrightness;
import aspects.ValidateLightOperation;
import model.Event;
import model.Light;
import org.springframework.stereotype.Service;
import repositories.LightRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class LightService {

    private final LightRepository lightRepository;

    public LightService(LightRepository lightRepository) {
        this.lightRepository = lightRepository;
    }

    /**
     * @param light        device type
     * @param turnOn       true = turn on, false = turn off
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateLightOperation
    public String switchLight(Light light, boolean turnOn) {
        light.setLastEvent(new Event(LocalDateTime.now(), turnOn));

        if(turnOn){
            light.setBrightnessLevel(100);
        }
        else
        {
            light.setBrightnessLevel(0);
        }
        lightRepository.switchLight(light);

        return "SUCCESS";
    }

    /**
     * @param light             device type
     * @param brightnessLevel   0-100
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateLightBrightness
    public String switchLight(Light light, int brightnessLevel) {
        light.setLastEvent(new Event(LocalDateTime.now(), brightnessLevel != 0));
        lightRepository.switchLight(light);

        return "SUCCESS";
    }

    /**
     * Add a light to the repository
     */
    public void addLight(Light light) {
        lightRepository.add(light);
    }

    /**
     * Return all lights from repository
     */
    public Collection<Light> getAll() {
        return lightRepository.getAll();
    }
}
