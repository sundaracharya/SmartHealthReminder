package np.com.sundaracharya.smarthealthreminder.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.DiseaseName;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;

@Dao
public interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Medication medication);

    @Query("DELETE FROM Medication")
    void deleteAll();

    @Query("DELETE FROM Medication WHERE medicationId = :medicationId")
    void delete(int medicationId);


    @Query(("SELECT * FROM Medication ORDER by medicationId ASC"))
    LiveData<List<Medication>> getMedication();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Medication medication);
}
