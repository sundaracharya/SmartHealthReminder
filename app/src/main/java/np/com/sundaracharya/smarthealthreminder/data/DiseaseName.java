package np.com.sundaracharya.smarthealthreminder.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DiseaseName")
public class DiseaseName {
    @PrimaryKey (autoGenerate = true)
  public   int diseaseId;
    public String disease;
    public  boolean isSelected;


    public DiseaseName(String disease, boolean isSelected){
        this.disease=disease;
        this.isSelected=isSelected;
    }

    public String getDisease() {
        return disease;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public Boolean getSelected(){
        return isSelected;
    }

}
