package UserBackend.service.repository;

import UserBackend.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
 Sensor findSensorByTimestampAndHourAndDeviceId(Date timestamp, int hour,Long id);
}
