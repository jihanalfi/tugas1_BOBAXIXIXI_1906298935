package apap.tugas.BOBAXIXIXI.repository;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerDb extends JpaRepository<ManagerModel, Long>{
    @Modifying
    @Query(
            value = "SELECT * FROM manager WHERE id_manager NOT IN (SELECT id_manager FROM store)",
            nativeQuery = true
    )
    List<ManagerModel> findAllAvailableManager();

    @Modifying
    @Query(
            value = "SELECT * FROM manager WHERE id_manager NOT IN (SELECT id_manager FROM store WHERE id_store != ?1)",
            nativeQuery = true
    )
    List<ManagerModel> findAvailableWithChosenManager(long idStore);

    @Modifying
    @Query(
            value = "SELECT DISTINCT  m.id_manager, m.full_name, m.gender, m.date_of_birth from store_bobatea as sb left join boba_tea as b on sb.id_bobatea=b.id_boba_tea left join store as s on s.id_store = sb.id_store left join manager as m on s.id_manager = m.id_manager where b.name=?1",
            nativeQuery = true
    )
    List<ManagerModel> filterManagerByBobaTea(String namaBoba);
}
