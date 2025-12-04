package config;

import aspects.AlarmValidationAspect;
import aspects.LoggingAspect;
import aspects.LightValidationAspect;
import aspects.ThermostatValidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"services", "repositories"})
@EnableAspectJAutoProxy
public class ProjectConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public LightValidationAspect lightValidationAspect() {
        return new LightValidationAspect();
    }

    @Bean
    public ThermostatValidationAspect thermostatValidationAspect() {
        return new ThermostatValidationAspect();
    }

    @Bean
    public AlarmValidationAspect alarmValidationAspect() {
        return new AlarmValidationAspect();
    }
}
