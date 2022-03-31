package np.com.sundaracharya.smarthealthreminder.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Medication")
public class Medication implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int medicationId;
    String medicationName;
    String medicationColor;
    String medicationDailyDoes;
    String medicationDiseaseName;
    String medicationReminderTime;
    Double medicationDays;
    int timeDifference;

    public Medication(int medicationId, String medicationName, String medicationColor, String medicationDailyDoes, String medicationDiseaseName, String medicationReminderTime, Double medicationDays, int timeDifference) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.medicationColor = medicationColor;
        this.medicationDailyDoes = medicationDailyDoes;
        this.medicationDiseaseName = medicationDiseaseName;
        this.medicationReminderTime = medicationReminderTime;
        this.medicationDays = medicationDays;
        this.timeDifference = timeDifference;
    }

    public Double getMedicationDays() {
        return medicationDays;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public String getMedicationColor() {
        return medicationColor;
    }

    public String getMedicationDailyDoes() {
        return medicationDailyDoes;
    }

    public String getMedicationDiseaseName() {
        return medicationDiseaseName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getMedicationReminderTime() {
        return medicationReminderTime;
    }

    public void setMedicationColor(String medicationColor) {
        this.medicationColor = medicationColor;
    }

    public void setMedicationDailyDoes(String medicationDailyDoes) {
        this.medicationDailyDoes = medicationDailyDoes;
    }


    public void setMedicationDiseaseName(String medicationDiseaseName) {
        this.medicationDiseaseName = medicationDiseaseName;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setMedicationReminderTime(String medicationReminderTime) {
        this.medicationReminderTime = medicationReminderTime;
    }

    public int getTimeDifference() {
        return timeDifference;
    }

    public void setMedicationDays(Double medicationDays) {
        this.medicationDays = medicationDays;
    }
}
