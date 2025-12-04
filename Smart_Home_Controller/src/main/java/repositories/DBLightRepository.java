package repositories;

import model.Event;
import model.Light;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class DBLightRepository implements LightRepository{
    @Override
    public String switchLight(Light light, boolean turnOn) {
        light.setLastEvent(new Event(LocalDateTime.now(), turnOn));

        if(turnOn){
            light.setBrightnessLevel(100);
        }
        else
        {
            light.setBrightnessLevel(0);
        }

        return "SUCCESS";
    }

    @Override
    public String switchLight(Light light, int brightnessLevel) {
        light.setLastEvent(new Event(LocalDateTime.now(), brightnessLevel != 0));

        return "SUCCESS";
    }
}
