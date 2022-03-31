package np.com.sundaracharya.smarthealthreminder.ui.medicine;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.repository.SmartHealthMainRepository;

public class MedicineViewModel extends AndroidViewModel {


    public SmartHealthMainRepository smartHealthMainRepository;

    private final LiveData<List<Medication>> medicationList;


    public MedicineViewModel(@NonNull Application application) {
        super(application);
        smartHealthMainRepository = new SmartHealthMainRepository(application);
        medicationList = smartHealthMainRepository.getMedicationList();
    }

    public LiveData<List<Medication>> getMedicationList() {
        return medicationList;
    }

    void insertMedication(Medication medication) {
        smartHealthMainRepository.insertMedication(medication);
    }

    void updateMedication(Medication medication) {
        smartHealthMainRepository.updateMedication(medication);
    }

    void deleteMedication(int medicationId) {
        smartHealthMainRepository.deleteMedication(medicationId);
    }

}