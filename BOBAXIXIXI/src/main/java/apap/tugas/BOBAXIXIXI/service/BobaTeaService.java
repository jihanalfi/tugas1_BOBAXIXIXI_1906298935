package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;

import java.util.List;

public interface BobaTeaService {
    void addBobaTea (BobaTeaModel boba);
    boolean updateBobaTea(BobaTeaModel boba);

    List<BobaTeaModel> getListBoba();

    <Optional> BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea);

    boolean removeBobaTea(BobaTeaModel bobaTea);

    List<BobaTeaModel> getBobaTeaByName(String keyword);
}
