package apap.tugas.BOBAXIXIXI.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "boba_tea")
public class BobaTeaModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBobaTea;

    @NotNull
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "size")
    private int size;

    @NotNull
    @Column(name = "ice_level")
    private int iceLevel;

    @NotNull
    @Column(name = "sugar_level")
    private int sugarLevel;

    // Relasi dengan Topping
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTopping", referencedColumnName = "idTopping", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel topping;

    // Relasi dengan Store
    /***@ManyToMany(mappedBy = "listBobaTea")
    List<StoreModel> listStore;***/
    @OneToMany(mappedBy = "bobaTea")
    List<BobaTeaStoreModel> listBobaTeaStore;

}
