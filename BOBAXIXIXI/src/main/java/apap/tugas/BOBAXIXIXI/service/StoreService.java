package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.StoreModel;

import java.util.List;

public interface StoreService {
    void addStore(StoreModel store);
    boolean updateStore(StoreModel store);
    boolean removeStore(StoreModel store);

    List<StoreModel> getListStore();
    StoreModel getStoreByIdStore(Long idStore);
}
