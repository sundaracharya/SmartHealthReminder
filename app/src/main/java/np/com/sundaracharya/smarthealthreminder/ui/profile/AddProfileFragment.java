package np.com.sundaracharya.smarthealthreminder.ui.profile;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentAddProfileBinding;
import np.com.sundaracharya.smarthealthreminder.enumType.Gender;
import np.com.sundaracharya.smarthealthreminder.utils.Utils;

public class AddProfileFragment extends Fragment {

    private FragmentAddProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private Boolean isUpdate=false;
    private int profileId=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentAddProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView();
        setViewData();
        binding.addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "onClick: "+binding.textFieldGender.getEditText().getText().toString() );
                if (isValidUI()){
                    Profile profile = new Profile(profileId,binding.textFieldFirstName.getEditText().getText().toString(),
                            binding.textFieldMiddleName.getEditText().getText().toString(),
                            binding.textFieldLastName.getEditText().getText().toString(),
                            Gender.valueOf(binding.textFieldGender.getEditText().getText().toString()),
                            Integer.valueOf(binding.textFieldAgeName.getEditText().getText().toString()),false,
                            binding.textEmail.getEditText().getText().toString());
                    if (isUpdate){
                        profileViewModel.updateProfile(profile);

                    }else {
                        profileViewModel.insertProfile(profile);

                    }
                    Navigation.findNavController(view).navigate(R.id.action_addProfileFragment_to_nav_profile);
                }
            }
        });

    }

    private void setViewData() {
        try {
            Profile profile = (Profile) getArguments().getSerializable("profile");
            if (profile != null) {
                binding.textFieldFirstName.getEditText().setText(profile.getFirstName());
                binding.textFieldMiddleName.getEditText().setText(profile.getMiddleName());
                binding.textFieldLastName.getEditText().setText(profile.getLastName());
                binding.textFieldAgeName.getEditText().setText(String.valueOf(profile.getAge()));
                binding.textFieldGender.getEditText().setText(String.valueOf(profile.getGender()));
                binding.addProfile.setText(getString(R.string.update_profile));
                isUpdate=true;
                profileId=profile.getProfileId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setView() {
        ArrayList<String> list = new ArrayList<>();
        list.add(getString(R.string.male));
        list.add(getString(R.string.female));
        list.add(getString(R.string.other));
        ArrayAdapter adapter = new ArrayAdapter(requireContext(), R.layout.item_dropdown, list);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) binding.textFieldGender.getEditText();
        autoCompleteTextView.setAdapter(adapter);
        binding.textFieldFirstName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textFieldFirstName.setError(null);
            }
        });
        binding.textFieldMiddleName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textFieldMiddleName.setError(null);
            }
        });
        binding.textFieldLastName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textFieldLastName.setError(null);
            }
        });
        binding.textEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textEmail.setError(null);
            }
        });

        binding.textFieldGender.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textFieldGender.setError(null);
            }
        });

        binding.textFieldAgeName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textFieldAgeName.setError(null);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isValidUI() {
        if (binding.textFieldFirstName.getEditText().getText().toString() == null || binding.textFieldFirstName.getEditText().getText().toString().trim().length() < 1) {
            binding.textFieldFirstName.setError("Enter first name");
            return false;
        }
        if (binding.textFieldLastName.getEditText().getText().toString() == null || binding.textFieldLastName.getEditText().getText().toString().trim().length() < 1) {
            binding.textFieldLastName.setError("Enter last name");
            return false;
        }



        if (binding.textEmail.getEditText().getText().toString() == null || !Utils.isValidEmail(binding.textEmail.getEditText().getText().toString())) {
            binding.textEmail.setError("Enter valid email");
            return false;
        }

        if (binding.textFieldGender.getEditText().getText().toString() == null || binding.textFieldGender.getEditText().getText().toString().trim().length() < 1) {
            binding.textFieldGender.setError("Enter gender name");
            return false;
        }

        if (binding.textFieldAgeName.getEditText().getText().toString() == null || binding.textFieldAgeName.getEditText().getText().toString().trim().length() < 1) {
            binding.textFieldAgeName.setError("Enter age name");
            return false;
        }
        return true;
    }
}