package apap.tugas.BOBAXIXIXI.repository;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BobaTeaStoreDb extends JpaRepository<BobaTeaStoreModel, Long> {

    List<BobaTeaStoreModel> findBobaTeaStoreByBobaTea(BobaTeaModel bobaTea);

    List<BobaTeaStoreModel> findBobaTeaStoreModelsByStore(StoreModel store);

    List<BobaTeaStoreModel> findBobaTeaStoreModelsByBobaTea(BobaTeaModel bobaTea);

    @Modifying
    @Query(
            value = "SELECT * FROM store_bobatea sb WHERE sb.id_bobatea IN (SELECT b.id_boba_tea FROM boba_tea b WHERE b.name=?1 AND b.id_topping IN (SELECT t.id_topping FROM topping t WHERE t.name=?2))",
            nativeQuery = true
    )
    List<BobaTeaStoreModel> searchByNameAndTopping(String namaBoba, String namaTopping);
}
