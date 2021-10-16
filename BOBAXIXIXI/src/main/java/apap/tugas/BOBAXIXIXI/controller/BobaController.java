package apap.tugas.BOBAXIXIXI.controller;
import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.model.ToppingModel;
import apap.tugas.BOBAXIXIXI.service.BobaTeaService;
import apap.tugas.BOBAXIXIXI.service.BobaTeaStoreService;
import apap.tugas.BOBAXIXIXI.service.StoreService;
import apap.tugas.BOBAXIXIXI.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BobaController {
    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("bobaTeaStoreServiceImpl")
    @Autowired
    private BobaTeaStoreService bobaTeaStoreService;

    @GetMapping("/boba")
    public String landingBoba(Model model){
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBoba();

        model.addAttribute("listBobaTea", listBobaTea);
        return "landing-boba";
    }

    @GetMapping("/boba/add")
    public String addBobaTeaForm(Model model){
        List<ToppingModel> listTopping = toppingService.getListTopping();

        model.addAttribute("bobaTea", new BobaTeaModel());
        model.addAttribute("listTopping", listTopping);
        return "add-bobatea-form";
    }

    @PostMapping("/boba/add")
    public String addBobaTeaSuccess(
            @ModelAttribute BobaTeaModel bobaTea,
            Model model
    ){
        bobaTeaService.addBobaTea(bobaTea);
        model.addAttribute("name", bobaTea.getName());
        return "add-bobatea";
    }

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaForm(
            @PathVariable Long idBobaTea,
            Model model
    ){
        List<ToppingModel> listTopping = toppingService.getListTopping();
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);

        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("listTopping", listTopping);
        return "update-bobatea-form";
    }

    @PostMapping("/boba/update/{idBobaTea}")
    public String updateBobaTeaSuccess(
            @PathVariable Long idBobaTea,
            @ModelAttribute BobaTeaModel bobaTeaModel,
            Model model
    ){
        boolean updateSuccess = bobaTeaService.updateBobaTea(bobaTeaModel);

        model.addAttribute("updateSuccess", updateSuccess);
        model.addAttribute("name", bobaTeaModel.getName());
        return "update-bobatea";
    }

    @GetMapping("/boba/delete/{idBobaTea}")
    public String deleteBobaTea(
            @PathVariable long idBobaTea,
            Model model
    ){
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        boolean deleteSuccess = bobaTeaService.removeBobaTea(bobaTea);

        model.addAttribute("deleteSuccess", deleteSuccess);
        model.addAttribute("name", bobaTea.getName());
        return "delete-bobatea";
    }

    @GetMapping("/boba/{idBoba}/assign-store")
    public String assignStoreForm(
            @PathVariable long idBoba,
            Model model
    ){
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBoba);
        List<StoreModel> listStore = storeService.getListStore();
        List<BobaTeaStoreModel> listBobaTeaStore = bobaTea.getListBobaTeaStore();
        if(listBobaTeaStore == null){
            bobaTea.setListBobaTeaStore(new ArrayList<BobaTeaStoreModel>());
        }

        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("listStore", listStore);
        model.addAttribute("listBobaTeaStore", listBobaTeaStore);
        model.addAttribute("bobaTeaStore", new BobaTeaStoreModel());

        return "assign-bobateastore-form";
    }

    @PostMapping("/boba/{idBoba}/assign-store")
    public String assignStoreSuccess(
            @PathVariable Long idBoba,
            @ModelAttribute BobaTeaStoreModel bobaTeaStore,
            @RequestParam(value = "listStore")Optional<Long[]> listStore,
            Model model
    ){
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBoba);
        if(listStore.isPresent()){
            Long[] listStoreExist = listStore.get();
            bobaTeaStoreService.deleteByBobaTea(bobaTea);
            for (Long idStore : listStoreExist){
                BobaTeaStoreModel newBobaTeaStore = new BobaTeaStoreModel();
                newBobaTeaStore.setBobaTea(bobaTea);
                newBobaTeaStore.setStore(storeService.getStoreByIdStore(idStore));
                bobaTeaStoreService.addBobaTeaStore(newBobaTeaStore);
            }
        } else {
            bobaTeaStoreService.deleteByBobaTea(bobaTea);
        }

        List<BobaTeaStoreModel> listBobaTeaStore = bobaTea.getListBobaTeaStore();

        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("listBobaTeaStore", listBobaTeaStore);
        return "assign-bobateastore";
    }
}
