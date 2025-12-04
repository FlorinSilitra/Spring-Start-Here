package repositories;

import model.Light;

public interface LightRepository {
    String switchLight(Light light, boolean turnOn);
    String switchLight(Light light, int brightnessLevel);
}
