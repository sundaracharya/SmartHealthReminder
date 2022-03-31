package np.com.sundaracharya.smarthealthreminder.ui.profile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentProfileBinding;
import np.com.sundaracharya.smarthealthreminder.ui.adapter.ProfileAdapter;

public class ProfileFragment extends Fragment {


    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private ProfileAdapter profileAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_profile_to_addProfileFragment);
            }
        });


        binding.recycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        List<Profile> list = new ArrayList<Profile>();
         profileAdapter = new ProfileAdapter(this, list, new ProfileAdapter.OnClickProfile() {
            @Override
            public void onClick(Profile profile) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("profile",  profile);
                Navigation.findNavController(view).navigate(R.id.action_nav_profile_to_addProfileFragment,bundle);
            }
        });
        binding.recycleView.setAdapter(profileAdapter);
        setRecycleView();
    }

    private void setRecycleView() {
        profileViewModel.getProfileList().observe(getViewLifecycleOwner(), list -> {
            Log.e("TAG", "setRecycleView: "+list);
            if (list.size()>0){
                profileAdapter.notifyList(list);
            }
        });
    }

    public void confirmDelete(int profileId) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Profile Entry ?")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        profileViewModel.deleteProfile(profileId);
                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void updateAll(){
        profileViewModel.updateAll(false);
    }
    public void updateProfile(Profile profile){
        profileViewModel.updateProfile(profile);
    }

}
