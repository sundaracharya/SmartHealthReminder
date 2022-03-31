package np.com.sundaracharya.smarthealthreminder.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import np.com.sundaracharya.smarthealthreminder.dao.DiseaseNameDao;
import np.com.sundaracharya.smarthealthreminder.dao.MedicationDao;
import np.com.sundaracharya.smarthealthreminder.dao.ProfileDao;
import np.com.sundaracharya.smarthealthreminder.data.DiseaseName;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.database.SmartHealthReminderDb;

public class SmartHealthMainRepository {

    private DiseaseNameDao diseaseNameDao;
    private MedicationDao medicationDao;
    private ProfileDao profileDao;
    private LiveData<List<Profile>> profileList;
    private LiveData<List<Medication>> medicationList;
    private LiveData<List<DiseaseName>> diseaseNameList;
    private LiveData<Profile> profileLiveData;


    public SmartHealthMainRepository(Context application) {
        SmartHealthReminderDb smartHealthReminderDb = SmartHealthReminderDb.getDbInstance(application);
        diseaseNameDao = smartHealthReminderDb.diseaseNameDao();
        medicationDao = smartHealthReminderDb.medicationDao();
        profileDao = smartHealthReminderDb.profileDao();
        profileList = profileDao.getProfileList();
        medicationList = medicationDao.getMedication();
        diseaseNameList = diseaseNameDao.getDiseaseName();
        profileLiveData=profileDao.getProfile(true);
    }

    public LiveData<List<Profile>> getProfileList() {
        return profileList;
    }

    public LiveData<List<Medication>> getMedicationList() {
        return medicationList;
    }

    public LiveData<List<DiseaseName>> getDiseaseNameList() {
        return diseaseNameList;
    }

    public LiveData<Profile> getProfileLiveData(){
        return profileLiveData;
    }

    public void insertProfile(Profile profile) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            profileDao.insert(profile);
        });
    }

    public void updateProfile(Profile profile) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            profileDao.update(profile);
        });
    }

    public void updateProfileAll(boolean isPrimary){
        SmartHealthReminderDb.smartReminderDbExecutor.execute(()->{
            profileDao.updateAll(isPrimary);
        });
    }

    public void deleteProfile(int id) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            profileDao.delete(id);
        });
    }



    public void insertMedication(Medication medication) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            medicationDao.insert(medication);
        });
    }

    public void updateMedication(Medication medication){
        SmartHealthReminderDb.smartReminderDbExecutor.execute(()->{
            medicationDao.update(medication);
        });
    }

    public void insertDiseaseName(DiseaseName diseaseName) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            diseaseNameDao.insert(diseaseName);
        });
    }


    public void deleteMedication(int id) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            medicationDao.delete(id);
        });
    }

    public void deleteDisease(int id) {
        SmartHealthReminderDb.smartReminderDbExecutor.execute(() -> {
            diseaseNameDao.delete(id);
        });
    }

}
