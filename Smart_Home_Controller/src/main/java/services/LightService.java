package services;

import aspects.ToLog;
import aspects.ValidateLightBrightness;
import aspects.ValidateLightOperation;
import model.Light;
import org.springframework.stereotype.Service;
import repositories.LightRepository;

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
        return lightRepository.switchLight(light, turnOn);
    }

    /**
     * @param light             device type
     * @param brightnessLevel   0-100
     * @return "SUCCESS", "FAIL" OR "BAD SIGNATURE" (via aspect)
     */
    @ToLog
    @ValidateLightBrightness
    public String switchLight(Light light, int brightnessLevel) {
        return lightRepository.switchLight(light, brightnessLevel);
    }
}
