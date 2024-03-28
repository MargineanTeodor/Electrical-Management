package UserBackend.service;

import UserBackend.mapper.SensorMapper;
import UserBackend.model.Sensor;
import UserBackend.service.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceSensor {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;
    public ServiceSensor(SensorRepository sensorRepository, SensorMapper sensorMapper)
    {
        this.sensorMapper= sensorMapper;
        this.sensorRepository = sensorRepository;
    }
    public void addSensor(int hour, Date timestamp, Long DeviceID, float value)
    {
        Sensor newSensor= new Sensor();
        newSensor.setValue(value);
        newSensor.setTimestamp(timestamp);
        newSensor.setDeviceId(DeviceID);
        newSensor.setHour(hour);
        this.sensorRepository.save(newSensor);
    }
    public float[] getArrayOfEnergyConsumption(Date date,Long id)
    {
        float [] array = new float[24];
        int i=0;
        float value;
        for(i=0;i<24;i++)
        {
            try {
                value = this.sensorRepository.findSensorByTimestampAndHourAndDeviceId(date,i,id).getValue();
            }
            catch (Exception e)
            {
                value=0;
            }
            array[i] = value;
        }
        return array;
    }
}
