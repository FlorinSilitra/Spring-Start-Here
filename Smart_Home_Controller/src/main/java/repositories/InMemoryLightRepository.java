package repositories;

import model.Light;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryLightRepository implements LightRepository{
    private final Map<String, Light> lights = new ConcurrentHashMap<>();
    @Override
    public void switchLight(Light light) {
        lights.put(light.getName(), light);
    }

    @Override
    public void add(Light light) {
        lights.put(light.getName(), light);
    }

    @Override
    public Collection<Light> getAll() {
        return lights.values();
    }
}
