package apap.tugas.BOBAXIXIXI.repository;

import apap.tugas.BOBAXIXIXI.model.ToppingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingDb extends JpaRepository<ToppingModel, Long>{

}
