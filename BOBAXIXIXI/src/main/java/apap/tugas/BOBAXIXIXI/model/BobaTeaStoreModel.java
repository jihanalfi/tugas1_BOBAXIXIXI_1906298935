package apap.tugas.BOBAXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "store_bobatea")
public class BobaTeaStoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBobaTeaStore;

    @ManyToOne
    @JoinColumn(name = "id_store")
    private StoreModel store;

    @ManyToOne
    @JoinColumn(name = "id_bobatea")
    private BobaTeaModel bobaTea;

    @NotNull
    @Column(name = "production_code", nullable = false, unique = true)
    private String productionCode;

}
