package np.com.sundaracharya.smarthealthreminder.ui.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.repository.SmartHealthMainRepository;

public class ProfileViewModel extends AndroidViewModel {


    public SmartHealthMainRepository smartHealthMainRepository;

    private final LiveData<List<Profile>> profileList;


    public ProfileViewModel(@NonNull Application application) {
        super(application);
        smartHealthMainRepository = new SmartHealthMainRepository(application);
        profileList = smartHealthMainRepository.getProfileList();
    }

    LiveData<List<Profile>> getProfileList() {
        return profileList;
    }

    void insertProfile(Profile profile) {
        smartHealthMainRepository.insertProfile(profile);
    }

    void updateProfile(Profile profile) {
        smartHealthMainRepository.updateProfile(profile);
    }

    void deleteProfile(int profileId) {
        smartHealthMainRepository.deleteProfile(profileId);
    }

    void updateAll(boolean isPrimary) {
        smartHealthMainRepository.updateProfileAll(isPrimary);
    }

}