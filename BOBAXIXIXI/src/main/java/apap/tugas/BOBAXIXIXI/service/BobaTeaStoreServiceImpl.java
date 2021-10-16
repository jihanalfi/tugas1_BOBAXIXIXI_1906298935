package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.BobaTeaStoreDb;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class BobaTeaStoreServiceImpl implements BobaTeaStoreService{
    @Autowired
    BobaTeaStoreDb bobaTeaStoreDb;

    @Override
    public List<BobaTeaStoreModel> getBobaTeaStoreByStore(StoreModel store) {
        return bobaTeaStoreDb.findBobaTeaStoreModelsByStore(store);
    }

    @Override
    public void addBobaTeaStore(BobaTeaStoreModel bobaTeaStore){
        bobaTeaStore.setProductionCode(generateProductionCode(bobaTeaStore));
        bobaTeaStoreDb.save(bobaTeaStore);
    }

    @Override
    public List<BobaTeaStoreModel> getBobaTeaStoreByBobaTea(BobaTeaModel bobaTea) {
        return bobaTeaStoreDb.findBobaTeaStoreModelsByBobaTea(bobaTea);
    }

    @Override
    public boolean storeOpenByBobaTea(BobaTeaModel bobaTea){
        LocalTime currentTime = LocalTime.now();
        List<BobaTeaStoreModel> listBobaTeaStore = bobaTeaStoreDb.findBobaTeaStoreByBobaTea(bobaTea);
        for (BobaTeaStoreModel bobaTeaStore : listBobaTeaStore){
            if (currentTime.isAfter(bobaTeaStore.getStore().getOpenHour()) && currentTime.isBefore(bobaTeaStore.getStore().getCloseHour())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteByStore(StoreModel store){
        List<BobaTeaStoreModel> listBobaTeaStore =  bobaTeaStoreDb.findBobaTeaStoreModelsByStore(store);
        for (BobaTeaStoreModel i : listBobaTeaStore){
            bobaTeaStoreDb.delete(i);
        }
    }

    @Override
    public void deleteByBobaTea(BobaTeaModel bobaTea) {
        List<BobaTeaStoreModel> listBobaTeaStore = bobaTeaStoreDb.findBobaTeaStoreModelsByBobaTea(bobaTea);
        for (BobaTeaStoreModel i : listBobaTeaStore){
            bobaTeaStoreDb.delete(i);
        }
    }

    @Override
    public List<BobaTeaStoreModel> getBobaTeaStoreByNameAndTopping(String namaBoba, String namaTopping) {
        return bobaTeaStoreDb.searchByNameAndTopping(namaBoba, namaTopping);
    }

    public String generateProductionCode(BobaTeaStoreModel bobaTeaStore){
        String finalProductionCode;
        Long idStore = bobaTeaStore.getStore().getIdStore();
        String tigaAngkaStore;

        if (idStore<10) tigaAngkaStore = "00" + idStore.toString();
        else if (idStore<100) tigaAngkaStore = "0" + idStore.toString();
        else tigaAngkaStore = idStore.toString();

        String topping;
        if(bobaTeaStore.getBobaTea().getTopping() == null) topping = "0";
        else topping = "1";

        Long idBobaTea = bobaTeaStore.getBobaTea().getIdBobaTea();
        String tigaAngkaBoba;
        if (idBobaTea<10) tigaAngkaBoba = "00" + idBobaTea.toString();
        else if (idBobaTea<100) tigaAngkaBoba = "0" + idBobaTea.toString();
        else tigaAngkaBoba = idBobaTea.toString();

        finalProductionCode = "PC" + tigaAngkaStore + topping + tigaAngkaBoba;
        return finalProductionCode;
    }


}
