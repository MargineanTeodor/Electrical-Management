package UserBackend.controller;

import UserBackend.service.ServiceSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private ServiceSensor serviceSensor;
    public SensorController(ServiceSensor serviceSensor) {
        this.serviceSensor = serviceSensor;
    }
    @RequestMapping(value="/getArray",method = RequestMethod.GET)
    public float[] changePassword(@RequestParam String date, Long id) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date timestamp = formatter.parse(date);
        return serviceSensor.getArrayOfEnergyConsumption(timestamp,id);
    }
}
