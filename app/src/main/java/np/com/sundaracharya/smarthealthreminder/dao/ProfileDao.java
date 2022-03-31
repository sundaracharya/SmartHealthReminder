package np.com.sundaracharya.smarthealthreminder.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;

@Dao
public interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Profile profile);

    @Query("DELETE FROM Profile")
    void deleteAll();

    @Query("DELETE FROM Profile WHERE profileId = :profileId")
    void delete(int profileId);


    @Query(("SELECT * FROM Profile ORDER by profileId ASC"))
    LiveData<List<Profile>> getProfileList();

    @Query("SELECT * FROM PROFILE WHERE isPrimary=:isPrimary ")
    LiveData<Profile> getProfile(boolean isPrimary);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Profile profile);


    @Query("UPDATE Profile SET isPrimary = :setKey")
    void updateAll(boolean setKey);

}
