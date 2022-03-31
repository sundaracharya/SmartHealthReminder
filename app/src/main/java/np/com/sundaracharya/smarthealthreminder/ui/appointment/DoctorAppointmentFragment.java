package np.com.sundaracharya.smarthealthreminder.ui.appointment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import np.com.sundaracharya.smarthealthreminder.databinding.FragmentDoctorAppointmentBinding;

public class DoctorAppointmentFragment extends Fragment {
    private FragmentDoctorAppointmentBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DoctorAppointmentViewModel addProfileViewModel =
                new ViewModelProvider(this).get(DoctorAppointmentViewModel.class);

        binding = FragmentDoctorAppointmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
