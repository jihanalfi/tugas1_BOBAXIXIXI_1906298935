package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.repository.ManagerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    ManagerDb managerDb;

    @Override
    public void addManager(ManagerModel manager) {
        managerDb.save(manager);
    }

    @Override
    public List<ManagerModel> getListManager() {
        return managerDb.findAll();
    }

    @Override
    public List<ManagerModel> getListAvailableManager(){
        return managerDb.findAllAvailableManager();
    }

    @Override
    public List<ManagerModel> getListAvailableChosenManager(long idStore){
        return managerDb.findAvailableWithChosenManager(idStore);
    }

    @Override
    public List<ManagerModel> filterManagerByBobaName(String namaBoba){
        return managerDb.filterManagerByBobaTea(namaBoba);
    }
}
