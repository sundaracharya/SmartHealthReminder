package np.com.sundaracharya.smarthealthreminder.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import np.com.sundaracharya.smarthealthreminder.conveters.Converter;
import np.com.sundaracharya.smarthealthreminder.dao.DiseaseNameDao;
import np.com.sundaracharya.smarthealthreminder.dao.MedicationDao;
import np.com.sundaracharya.smarthealthreminder.dao.ProfileDao;
import np.com.sundaracharya.smarthealthreminder.data.DiseaseName;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;

@Database(entities = {Profile.class, Medication.class, DiseaseName.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class SmartHealthReminderDb extends RoomDatabase {

    public abstract ProfileDao profileDao();

    public abstract MedicationDao medicationDao();

    public abstract DiseaseNameDao diseaseNameDao();

    private static volatile SmartHealthReminderDb smartHealthReminderdb;
    private static final int THREADS = 4;

    public static final ExecutorService smartReminderDbExecutor = Executors.newFixedThreadPool(THREADS);


    public static SmartHealthReminderDb getDbInstance(final Context context) {
        if (smartHealthReminderdb == null) {
            synchronized (SmartHealthReminderDb.class) {
                if (smartHealthReminderdb == null) {
                    smartHealthReminderdb = Room.databaseBuilder(context.getApplicationContext(), SmartHealthReminderDb.class, "smart_health_reminder_database").build();
                }
            }
        }
        return smartHealthReminderdb;
    }

}
