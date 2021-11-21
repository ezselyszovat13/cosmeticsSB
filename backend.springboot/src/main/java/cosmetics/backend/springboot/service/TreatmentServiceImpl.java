package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.model.User;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService{
    private TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> getTreatments() {
        return this.treatmentRepository.findAll();
    }

    @Override
    public Treatment saveTreatment(Treatment treatment) {
        Optional<Treatment> actualTreatment = treatmentRepository.findByName(treatment.getName());
        if(actualTreatment.isPresent()){
            throw new IllegalStateException("A kezelés már hozzáadásra került!");
        }
        if(treatment.getPrice() < 0){
            throw new IllegalArgumentException("A kezeléshez tartozó összeg nem megfelelő!");
        }
        if(treatment.getLength() < 0){
            throw new IllegalArgumentException("A kezeléshez tartozó időtartam nem megfelelő!");
        }
        return this.treatmentRepository.save(treatment);
    }

    @Override
    public Treatment getTreatmentById(Long id) {
        Optional<Treatment> treatment = treatmentRepository.findById(id);
        if(!treatment.isPresent()){
            throw new IllegalStateException("A keresett kezelés nem található!");
        }
        return treatment.get();
    }
}
