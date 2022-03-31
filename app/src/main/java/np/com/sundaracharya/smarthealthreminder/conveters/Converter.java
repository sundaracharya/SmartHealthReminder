package np.com.sundaracharya.smarthealthreminder.conveters;

import androidx.room.TypeConverter;

import np.com.sundaracharya.smarthealthreminder.enumType.Gender;

public class Converter {

    @TypeConverter
    public String fromGender(Gender gender) {
        return gender.name();
    }

    @TypeConverter
    public Gender toGender(String gender) {
        return Gender.valueOf(gender);
    }
}
