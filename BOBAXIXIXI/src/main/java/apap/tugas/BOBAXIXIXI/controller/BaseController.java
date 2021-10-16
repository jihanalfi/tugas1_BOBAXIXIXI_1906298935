package apap.tugas.BOBAXIXIXI.controller;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.BobaTeaStoreModel;
import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.ToppingModel;
import apap.tugas.BOBAXIXIXI.service.BobaTeaService;
import apap.tugas.BOBAXIXIXI.service.BobaTeaStoreService;
import apap.tugas.BOBAXIXIXI.service.ManagerService;
import apap.tugas.BOBAXIXIXI.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BaseController {
    @Qualifier("bobaTeaStoreServiceImpl")
    @Autowired
    private BobaTeaStoreService bobaTeaStoreService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/search")
    private String search(
            @RequestParam(value = "bobaName", required = false) String namaBoba,
            @RequestParam(value = "topping", required = false) String namaTopping,
            Model model
    ){
        List<BobaTeaStoreModel> listBobaTeaStore = bobaTeaStoreService.getBobaTeaStoreByNameAndTopping(namaBoba, namaTopping);
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBoba();
        List<ToppingModel> listTopping = toppingService.getListTopping();

        model.addAttribute("namaBoba", namaBoba);
        model.addAttribute("namaTopping", namaTopping);
        model.addAttribute("listBobaTeaStore", listBobaTeaStore);
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listTopping", listTopping);
        return "search";
    }

    @GetMapping("/filter/manager")
    private String filter(
            @RequestParam(value = "nameBoba", required = false) String namaBoba,
            Model model
    ){

        List<ManagerModel> listManager = managerService.filterManagerByBobaName(namaBoba);
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBoba();

        model.addAttribute("namaBoba", namaBoba);
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listManager", listManager);
        return "filter-manager";
    }

    @GetMapping("/bonus")
    private String bonus(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model
    ){
        String prefix = "'%";
        String suffix = "%'";
        String keyword0 = prefix + keyword + suffix;
        System.out.print(keyword0);

        List<BobaTeaModel> listBobaTea = bobaTeaService.getBobaTeaByName(keyword0);
        System.out.print(listBobaTea);
        //System.out.print(keyword);
        model.addAttribute("listBobaTea", listBobaTea);
        return "bonus";
    }

}

