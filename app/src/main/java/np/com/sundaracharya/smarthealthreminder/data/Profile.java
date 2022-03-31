package np.com.sundaracharya.smarthealthreminder.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import np.com.sundaracharya.smarthealthreminder.enumType.Gender;

@Entity(tableName = "Profile")
public class Profile  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int profileId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private int age;
    public boolean isPrimary;
    String email;

    public Profile(int profileId,String firstName,String middleName,String lastName, Gender gender, int age,boolean isPrimary,String email){
        this.profileId=profileId;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.gender=gender;
        this.age=age;
        this.isPrimary=isPrimary;
        this.email=email;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getProfileId() {
        return profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
    public boolean getIsPrimary(){
        return isPrimary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
