package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.repository.BobaTeaDb;
import apap.tugas.BOBAXIXIXI.repository.BobaTeaStoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService {

    @Autowired
    BobaTeaDb bobaTeaDb;

    @Qualifier("bobaTeaStoreServiceImpl")
    @Autowired
    private BobaTeaStoreService bobaTeaStoreService;

    @Override
    public void addBobaTea(BobaTeaModel bobaTea) {
        bobaTeaDb.save(bobaTea);
    }

    @Override
    public boolean updateBobaTea(BobaTeaModel bobaTea) {
        if (bobaTeaStoreService.storeOpenByBobaTea(bobaTea)){
            return false;
        }
        bobaTeaDb.save(bobaTea);
        return true;
    }

    @Override
    public List<BobaTeaModel> getListBoba() {
        return bobaTeaDb.findAll();
    }

    @Override
    public boolean removeBobaTea(BobaTeaModel bobaTea) {
        List<BobaTeaStoreModel> listBobaTeaStore = bobaTeaStoreService.getBobaTeaStoreByBobaTea(bobaTea);
        if (listBobaTeaStore.isEmpty()){
            bobaTeaDb.delete(bobaTea);
            return true;
        }
        return false;
    }

    @Override
    public BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea) {
        Optional<BobaTeaModel> bobaTea = bobaTeaDb.findById(idBobaTea);
        if (bobaTea.isPresent())
            return bobaTea.get();
        return null;
    }

    @Override
    public List<BobaTeaModel> getBobaTeaByName(String keyword){
        List<BobaTeaModel> listBobaTea = bobaTeaDb.findByNameLike(keyword);
        return listBobaTea;
    }

}
