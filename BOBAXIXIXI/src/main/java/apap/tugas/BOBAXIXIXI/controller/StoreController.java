package apap.tugas.BOBAXIXIXI.controller;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.service.BobaTeaService;
import apap.tugas.BOBAXIXIXI.service.BobaTeaStoreService;
import apap.tugas.BOBAXIXIXI.service.ManagerService;
import apap.tugas.BOBAXIXIXI.service.StoreService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StoreController {
    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("bobaTeaStoreServiceImpl")
    @Autowired
    private BobaTeaStoreService bobaTeaStoreService;

    @GetMapping("/store")
    public String landingStore(Model model){
        List<StoreModel> listStore = storeService.getListStore();
        model.addAttribute("listStore", listStore);
        return "landing-store";
    }

    @GetMapping("/store/add")
    public String addStoreForm(Model model){
        List<ManagerModel> listManager = managerService.getListAvailableManager();

        model.addAttribute("store", new StoreModel());
        model.addAttribute("listManager", listManager);
        return "add-store-form";
    }

    @PostMapping("/store/add")
    public String addStoreSuccess(
            @ModelAttribute StoreModel store,
            Model model){
        storeService.addStore(store);
        model.addAttribute("storeCode", store.getStoreCode());
        model.addAttribute("name", store.getName());
        return "add-store";
    }

    @GetMapping("/store/{idStore}")
    public String detailStore(
            @PathVariable Long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);

        model.addAttribute("store", store);
        return "detail-store";
    }

    @GetMapping("/store/update/{idStore}")
    public String updateStoreForm(
            @PathVariable Long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<ManagerModel> listManager = managerService.getListAvailableChosenManager(idStore);
        LocalTime localTime = LocalTime.now();

        model.addAttribute("store", store);
        model.addAttribute("localTime", localTime);
        model.addAttribute("listManager", listManager);
        return "update-store-form";
    }

    @PostMapping("/store/update/{idStore}")
    public String updateStoreSuccess(
            @PathVariable Long idStore,
            @ModelAttribute StoreModel storeModel,
            Model model
    ){
        boolean updateSuccess = storeService.updateStore(storeModel);

        model.addAttribute("name", storeModel.getName());
        model.addAttribute("storeCode", storeModel.getStoreCode());
        model.addAttribute("updateSuccess", updateSuccess);
        return "update-store";
    }

    @GetMapping("/store/delete/{idStore}")
    public String deleteStore(
            @PathVariable long idStore,
            Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        boolean deleteSuccess = storeService.removeStore(store);

        model.addAttribute("deleteSuccess", deleteSuccess);
        model.addAttribute("name", store.getName());
        model.addAttribute("storeCode", store.getStoreCode());

        return "delete-store";
    }

    @GetMapping("/store/{idStore}/assign-boba")
    public String assignBobaForm(
          @PathVariable long idStore,
          Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBoba();
        List<BobaTeaStoreModel> listBobaTeaStore = store.getListBobaTeaStore();
        if (listBobaTeaStore == null){
            store.setListBobaTeaStore(new ArrayList<BobaTeaStoreModel>());
        }

        model.addAttribute("store", store);
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listBobaTeaStore", listBobaTeaStore);
        model.addAttribute("bobaTeaStore", new BobaTeaStoreModel());
        return "assign-storebobatea-form";
    }

    @PostMapping("/store/{idStore}/assign-boba")
    public String assignBobaSuccess(
            @PathVariable Long idStore,
            @ModelAttribute BobaTeaStoreModel bobaTeaStore,
            @RequestParam(value = "listBoba") Optional<Long[]> listBoba,
            Model model
    ){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        // store.getListBobaTeaStore().clear();
        if(listBoba.isPresent()){
            Long[] listBobaExist = listBoba.get();
            bobaTeaStoreService.deleteByStore(store);
            for (Long idBoba : listBobaExist){
                BobaTeaStoreModel newBobaTeaStore = new BobaTeaStoreModel();
                newBobaTeaStore.setStore(store);
                newBobaTeaStore.setBobaTea(bobaTeaService.getBobaTeaByIdBobaTea(idBoba));
                bobaTeaStoreService.addBobaTeaStore(newBobaTeaStore);
            }
        } else {
            bobaTeaStoreService.deleteByStore(store);
        }

        List<BobaTeaStoreModel> listBobaTeaStore = bobaTeaStoreService.getBobaTeaStoreByStore(store);
        model.addAttribute("store", store);
        model.addAttribute("listBobaTeaStore", listBobaTeaStore);
        return "assign-storebobatea";
    }

}
