package repositories;

import model.Light;

import java.util.Collection;

public interface LightRepository {
    void switchLight(Light light);
    void add(Light light);
    Collection<Light> getAll();
}
