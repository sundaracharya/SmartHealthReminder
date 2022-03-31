package np.com.sundaracharya.smarthealthreminder.ui.home;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.repository.SmartHealthMainRepository;

public class HomeViewModel extends AndroidViewModel {

    public SmartHealthMainRepository smartHealthMainRepository;
    private final LiveData<Profile> profileList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        smartHealthMainRepository = new SmartHealthMainRepository(application);
        profileList = smartHealthMainRepository.getProfileLiveData();
    }

    LiveData<Profile> getProfile() {
        return profileList;
    }

}