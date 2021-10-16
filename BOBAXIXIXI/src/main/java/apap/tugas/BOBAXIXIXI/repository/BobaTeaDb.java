package apap.tugas.BOBAXIXIXI.repository;
import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BobaTeaDb extends JpaRepository<BobaTeaModel, Long>{
    @Modifying
    @Query(value = "SELECT * FROM boba_tea b WHERE b.name LIKE ?1",
        nativeQuery = true)
    List<BobaTeaModel> findByNameLike(String keyword);
    List<BobaTeaModel> findBobaTeaModelsByNameLike(String keyword);
}
