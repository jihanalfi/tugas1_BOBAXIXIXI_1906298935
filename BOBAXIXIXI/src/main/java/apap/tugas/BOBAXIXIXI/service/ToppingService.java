package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ToppingModel;

import java.util.List;

public interface ToppingService {
    void addTopping(ToppingModel topping);

    List<ToppingModel> getListTopping();
}
