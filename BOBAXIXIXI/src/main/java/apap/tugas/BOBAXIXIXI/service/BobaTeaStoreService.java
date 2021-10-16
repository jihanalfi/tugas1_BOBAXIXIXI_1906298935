package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import org.apache.catalina.Store;

import java.util.List;

public interface BobaTeaStoreService {
    void addBobaTeaStore(BobaTeaStoreModel bobaTeaStore);

    List<BobaTeaStoreModel> getBobaTeaStoreByBobaTea(BobaTeaModel bobaTea);

    boolean storeOpenByBobaTea(BobaTeaModel bobaTea);

    List<BobaTeaStoreModel> getBobaTeaStoreByStore(StoreModel store);

    void deleteByStore(StoreModel store);

    void deleteByBobaTea(BobaTeaModel bobaTea);

    List<BobaTeaStoreModel> getBobaTeaStoreByNameAndTopping(String namaBoba, String namaTopping);
}
