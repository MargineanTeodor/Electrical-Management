package UserBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=1,max=30)
    private String description;
    @NotNull
    @Size(min=1,max=50)
    private String address;
    @NotNull
    @Max(50)
    @Min(1)
    private Long maximHrEnergyConsumption;
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;
}
