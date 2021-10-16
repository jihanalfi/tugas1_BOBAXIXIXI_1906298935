package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;

import java.util.List;

public interface ManagerService {
    void addManager(ManagerModel manager);
    List<ManagerModel> getListManager();
    List<ManagerModel> getListAvailableManager();
    List<ManagerModel> getListAvailableChosenManager(long idStore);

    List<ManagerModel> filterManagerByBobaName(String namaBoba);
}
