package np.com.sundaracharya.smarthealthreminder.ui.appointment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import np.com.sundaracharya.smarthealthreminder.repository.SmartHealthMainRepository;

public class DoctorAppointmentViewModel extends AndroidViewModel {


    public SmartHealthMainRepository smartHealthMainRepository;

    public DoctorAppointmentViewModel(@NonNull Application application) {
        super(application);
        smartHealthMainRepository = new SmartHealthMainRepository(application);
    }
}
