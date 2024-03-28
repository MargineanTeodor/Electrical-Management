package UserBackend.repository;

import UserBackend.model.Device;
import UserBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> findAllByUser(User user);
    Device findFirstById(Long id);
}
