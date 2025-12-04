package main;

import config.ProjectConfig;
import model.Alarm;
import model.Event;
import model.Light;
import model.Thermostat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.AlarmService;
import services.LightService;
import services.ThermostatService;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var lightService = context.getBean(LightService.class);
        var thermostatService = context.getBean(ThermostatService.class);
        var alarmService = context.getBean(AlarmService.class);

        Light livingRoomLight = new Light(
                "Living Room Light",
                new Event(LocalDateTime.now(), false),
                0
        );

        String result = lightService.switchLight(livingRoomLight, true);
        logger.info("Result: " + result + "\n");

        result = lightService.switchLight(livingRoomLight, true);
        logger.info("Result: " + result+ "\n");

        result = lightService.switchLight(livingRoomLight, 60);
        logger.info("Result: " + result+ "\n");

        result = lightService.switchLight(livingRoomLight, 120);
        logger.info("Result: " + result+ "\n");

        result = lightService.switchLight(livingRoomLight, false);
        logger.info("Result: " + result+ "\n");

        result = lightService.switchLight(livingRoomLight, 42);
        logger.info("Result: " + result+ "\n");


        Thermostat livingRoomThermostat = new Thermostat(
                "Living Room Thermostat",
                new Event(LocalDateTime.now(), false),
                0
        );

        result = thermostatService.switchThermostat(livingRoomThermostat, true);
        logger.info("Result: " + result + "\n");

        result = thermostatService.switchThermostat(livingRoomThermostat, true);
        logger.info("Result: " + result + "\n");

        result = thermostatService.setTemperature(livingRoomThermostat, 23.5);
        logger.info("Result: " + result + "\n");

        result = thermostatService.setTemperature(livingRoomThermostat, 40.0);
        logger.info("Result: " + result + "\n");

        result = thermostatService.switchThermostat(livingRoomThermostat, false);
        logger.info("Result: " + result + "\n");

        result = thermostatService.setTemperature(livingRoomThermostat, 18.0);
        logger.info("Result: " + result + "\n");


        Alarm homeAlarm = new Alarm(
                "Home Alarm",
                new Event(LocalDateTime.now(), false) //
        );

        result = alarmService.switchAlarm(homeAlarm, true);
        logger.info("Result: " + result + "\n");

        result = alarmService.switchAlarm(homeAlarm, true);
        logger.info("Result: " + result + "\n");

        result = alarmService.switchAlarm(homeAlarm, false);
        logger.info("Result: " + result + "\n");

        context.close();
    }
}
