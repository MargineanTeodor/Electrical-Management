package UserBackend.mapper;

import UserBackend.DTO.SensorDTO;
import UserBackend.model.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {
    public static SensorDTO mapModelToDto(Sensor sensor)
    {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setId(sensor.getId());
        sensorDTO.setHour(sensor.getHour());
        sensorDTO.setValue(sensor.getValue());
        sensorDTO.setDevice_id(sensor.getDeviceId());
        sensorDTO.setTimestamp(sensor.getTimestamp());
        return sensorDTO;
    }
    public static Sensor mapDtoToModel(SensorDTO sensorDTO)
    {
        Sensor sensor = new Sensor();
        sensor.setId(sensorDTO.getId());
        sensor.setHour(sensorDTO.getHour());
        sensor.setValue(sensorDTO.getValue());
        sensor.setDeviceId(sensorDTO.getDevice_id());
        sensor.setTimestamp(sensorDTO.getTimestamp());
        return sensor;
    }
}
