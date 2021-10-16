package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.StoreDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreDb storeDb;

    @Override
    public void addStore(StoreModel store){
        generateStoreCode(store);
        storeDb.save(store);
    }

    @Override
    public boolean updateStore(StoreModel store){
        LocalTime currentTime = LocalTime.now();
        if (!(currentTime.isAfter(store.getOpenHour()) && currentTime.isBefore(store.getCloseHour()))) {
            generateStoreCode(store);
            storeDb.save(store);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeStore(StoreModel store) {
        LocalTime currentTime = LocalTime.now();
        if ((!(currentTime.isAfter(store.getOpenHour()) && currentTime.isBefore(store.getCloseHour())))
                && store.getListBobaTeaStore().isEmpty()) {
            storeDb.delete(store);
            return true;
        }
        return false;
    }

    @Override
    public List<StoreModel> getListStore() {
        return storeDb.findAll();
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore) {
        Optional<StoreModel> store = storeDb.findByIdStore(idStore);
        if (store.isPresent()){
            return store.get();
        }
        return null;
    }

    public void generateStoreCode(StoreModel store){
        String finalStoreCode;
        StringBuilder tigaHuruf = new StringBuilder(store.getName().substring(0,3)).reverse() ;
        String hurufUsed = tigaHuruf.toString().toUpperCase();
        int openHour = store.getOpenHour().getHour();
        int closeHour = store.getCloseHour().getHour();
        int closeHourUsed = (int)Math.ceil(closeHour/10);
        String hourUsed = Integer.toString(openHour) + Integer.toString(closeHourUsed);
        while (true){
            Random rnd = new Random();
            char y = Character.toUpperCase((char) (rnd.nextInt(26)+'a'));
            char z = Character.toUpperCase((char) (rnd.nextInt(26)+'a'));

            finalStoreCode = "SC" + hurufUsed + openHour + closeHourUsed + y + z;
            StoreModel storeDuplicated = storeDb.findByStoreCodeEquals(finalStoreCode);
            if (storeDuplicated == null) {
                store.setStoreCode(finalStoreCode);
                break;
            }
        }
    }
}
