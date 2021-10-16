package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ToppingModel;
import apap.tugas.BOBAXIXIXI.repository.ToppingDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService{
    @Autowired
    ToppingDb toppingDb;

    @Override
    public void addTopping(ToppingModel topping) {
        toppingDb.save(topping);
    }

    @Override
    public List<ToppingModel> getListTopping(){
        return toppingDb.findAll();
    }
}
