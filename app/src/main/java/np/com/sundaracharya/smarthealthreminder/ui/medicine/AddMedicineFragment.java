package np.com.sundaracharya.smarthealthreminder.ui.medicine;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Calendar;

import np.com.sundaracharya.smarthealthreminder.MainActivity;
import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentAddMedicineBinding;
import np.com.sundaracharya.smarthealthreminder.enumType.Gender;
import np.com.sundaracharya.smarthealthreminder.service.AlarmReceiver;

public class AddMedicineFragment extends Fragment {
    private FragmentAddMedicineBinding binding;
    private Boolean isUpdate = false;
    private int medicationId = 0;
    private MedicineViewModel medicineViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medicineViewModel =
                new ViewModelProvider(this).get(MedicineViewModel.class);

        binding = FragmentAddMedicineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView();
        binding.textMedicineRemainderTime.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        setViewWithData();

        binding.addMedicine.setOnClickListener(view1 -> {
            if (isValidUI()) {
                Medication medication = new Medication(medicationId,
                        binding.textMedicineName.getEditText().getText().toString(),
                        binding.textMedicineColor.getEditText().getText().toString(),
                        binding.textMedicineDailyDoes.getEditText().getText().toString(),
                        binding.textMedicineDiseaseName.getEditText().getText().toString(),
                        binding.textMedicineRemainderTime.getEditText().getText().toString(),
                        Double.valueOf(binding.textMedicineDays.getEditText().getText().toString()),
                        Integer.valueOf(binding.textTimeDifference.getEditText().getText().toString()));
                ((MainActivity)getActivity()).startAlertAtParticularTime(medication.getTimeDifference(),medication.getMedicationReminderTime());
                if (isUpdate) {
                    medicineViewModel.updateMedication(medication);

                } else {
                    medicineViewModel.insertMedication(medication);

                }
                Navigation.findNavController(view1).navigate(R.id.action_addMedicineFragment_to_nav_add_medicine);
            }
        });
        binding.textMedicineRemainderTime.setEndIconOnClickListener(view1 -> {
            Calendar calendar = Calendar.getInstance();
            new TimePickerDialog(requireContext(), mTimeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();

        });
        setAddTextListener();

    }

    private void setView() {
        ArrayList<String> list = new ArrayList<>();
        list.add(getString(R.string.disable));
        list.add(getString(R.string.blood_cancer));
        list.add(getString(R.string.diabetes));
        list.add(getString(R.string.heart_disease));
        list.add(getString(R.string.abc));
        list.add(getString(R.string.xys));

        ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.item_dropdown, list);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) binding.textMedicineDiseaseName.getEditText();
        autoCompleteTextView.setAdapter(adapter);
    }

    private void setViewWithData() {
        try {
            Medication medication = (Medication) getArguments().getSerializable("medication");

            if (medication != null) {
                binding.textMedicineName.getEditText().setText(medication.getMedicationName());
                binding.textMedicineColor.getEditText().setText(medication.getMedicationColor());
                binding.textMedicineDailyDoes.getEditText().setText(medication.getMedicationDailyDoes());
                binding.textMedicineDiseaseName.getEditText().setText(medication.getMedicationDiseaseName());
                binding.textMedicineRemainderTime.getEditText().setText(medication.getMedicationReminderTime());
                binding.textMedicineDays.getEditText().setText(medication.getMedicationDays().toString());
                binding.addMedicine.setText(getString(R.string.update_medicine));
                isUpdate = true;
                medicationId = medication.getMedicationId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private TimePickerDialog.OnTimeSetListener mTimeListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hour, int minute) {

                    String mHour;
                    String mMinute;
                    if (hour < 10) {
                        mHour = "0" + hour;
                    } else {
                        mHour = hour + "";
                    }
                    if (minute < 10) {
                        mMinute = "0" + minute;
                    } else {
                        mMinute = minute + "";
                    }

                    binding.textMedicineRemainderTime.getEditText().setText(mHour + ":" + mMinute);
                }
            };

    private boolean isValidUI() {
        if (binding.textMedicineName.getEditText().getText().toString() == null || binding.textMedicineName.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineName.setError("Enter medicine name");
            return false;
        }
        if (binding.textMedicineColor.getEditText().getText().toString() == null || binding.textMedicineColor.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineColor.setError("Enter medicine color");
            return false;
        }

        if (binding.textMedicineDailyDoes.getEditText().getText().toString() == null || binding.textMedicineDailyDoes.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineDailyDoes.setError("Enter daily dose");
            return false;
        }

        if (binding.textMedicineDiseaseName.getEditText().getText().toString() == null || binding.textMedicineDiseaseName.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineDiseaseName.setError("Enter disease name");
            return false;
        }
        if (binding.textMedicineRemainderTime.getEditText().getText().toString() == null || binding.textMedicineRemainderTime.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineRemainderTime.setError("Enter remainder time");
            return false;
        }
        if (binding.textMedicineDays.getEditText().getText().toString() == null || binding.textMedicineDays.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineDays.setError("Enter medicine days");
            return false;
        }

        if (binding.textTimeDifference.getEditText().getText().toString() == null || binding.textTimeDifference.getEditText().getText().toString().trim().length() < 1) {
            binding.textMedicineDays.setError("Enter time difference");
            return false;
        }
        return true;
    }


    private void setAddTextListener() {
        binding.textMedicineName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineName.setError(null);
            }
        });

        binding.textMedicineColor.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineColor.setError(null);
            }
        });

        binding.textMedicineDailyDoes.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineDailyDoes.setError(null);
            }
        });

        binding.textMedicineDiseaseName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineDiseaseName.setError(null);
            }
        });


        binding.textMedicineRemainderTime.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineRemainderTime.setError(null);
            }
        });


        binding.textMedicineDays.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMedicineDays.setError(null);
            }
        });

        binding.textTimeDifference.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textTimeDifference.setError(null);
            }
        });
    }


}
