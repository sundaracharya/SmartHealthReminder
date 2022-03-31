package np.com.sundaracharya.smarthealthreminder.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.DiseaseName;
import np.com.sundaracharya.smarthealthreminder.data.Profile;

@Dao
public interface DiseaseNameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DiseaseName diseaseName);

    @Query("DELETE FROM DiseaseName")
    void deleteAll();

    @Query("DELETE FROM Profile WHERE profileId = :diseaseId")
    void delete(int diseaseId);


    @Query(("SELECT * FROM DiseaseName ORDER by diseaseId ASC"))
    LiveData<List<DiseaseName>> getDiseaseName();


}
