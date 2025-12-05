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
import static java.lang.Thread.sleep;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var lightService = context.getBean(LightService.class);
        var thermostatService = context.getBean(ThermostatService.class);
        var alarmService = context.getBean(AlarmService.class);

        Light livingRoomLight = new Light(
                "Living Room Light",
                new Event(LocalDateTime.now(), false),
                0
        );

        Light outsideLight = new Light(
                "Outside Light",
                new Event(LocalDateTime.now(), true),
                23
        );

        lightService.addLight(livingRoomLight);
        lightService.addLight(outsideLight);

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

        lightService.getAll().forEach(System.out::println);
        System.out.println();
        sleep(1000);

        Thermostat livingRoomThermostat = new Thermostat(
                "Living Room Thermostat",
                new Event(LocalDateTime.now(), false),
                0
        );

        thermostatService.addThermostat(livingRoomThermostat);

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

        thermostatService.getAll().forEach(System.out::println);
        System.out.println();
        sleep(1000);


        Alarm homeAlarm = new Alarm(
                "Home Alarm",
                new Event(LocalDateTime.now(), false) //
        );
        Alarm garageAlarm = new Alarm(
                "Garage Alarm",
                new Event(LocalDateTime.now(), true) //
        );

        alarmService.addAlarm(homeAlarm);
        alarmService.addAlarm(garageAlarm);

        result = alarmService.switchAlarm(homeAlarm, true);
        logger.info("Result: " + result + "\n");

        result = alarmService.switchAlarm(homeAlarm, true);
        logger.info("Result: " + result + "\n");

        result = alarmService.switchAlarm(homeAlarm, false);
        logger.info("Result: " + result + "\n");

        alarmService.getAll().forEach(System.out::println);

        context.close();
    }
}
