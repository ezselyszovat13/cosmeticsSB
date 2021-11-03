package cosmetics.backend.springboot.service;

import cosmetics.backend.springboot.model.Treatment;
import cosmetics.backend.springboot.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        return this.treatmentRepository.save(treatment);
    }

    @Override
    public Treatment getTreatment(Long id) {
        return this.treatmentRepository.findById(id).get();
    }
}
