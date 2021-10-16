package apap.tugas.BOBAXIXIXI.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "manager")
public class ManagerModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idManager;

    @NotNull
    @Size(max = 255)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "gender", nullable = false)
    private int gender;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    // Relasi dengan Store
    @OneToOne(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StoreModel store;
}
