package np.com.sundaracharya.smarthealthreminder.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import np.com.sundaracharya.smarthealthreminder.MainActivity;
import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentHomeBinding;
import np.com.sundaracharya.smarthealthreminder.ui.adapter.ProfileAdapter;
import np.com.sundaracharya.smarthealthreminder.ui.adapter.TodayReminderAdapter;
import np.com.sundaracharya.smarthealthreminder.ui.medicine.MedicineViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private MedicineViewModel medicineViewModel;
    private TodayReminderAdapter todayReminderAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        medicineViewModel =
                new ViewModelProvider(this).get(MedicineViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonMedications.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.nav_add_medicine);
        });
        setHeader();
        binding.fab.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.nav_add_medicine);
        });
        setRecycleView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void  setHeader(){
        homeViewModel.getProfile().observe(getViewLifecycleOwner(),profile -> {
            ((MainActivity)getActivity()).headerName(profile);
        });
    }

    private void setRecycleView(){
        binding.recycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        List<Medication> list = new ArrayList<>();
        todayReminderAdapter = new TodayReminderAdapter(list);
        binding.recycleView.setAdapter(todayReminderAdapter);
        medicineViewModel.getMedicationList().observe(getViewLifecycleOwner(), medicationList -> {
            todayReminderAdapter.notifyList(medicationList);
            Log.e("TAG", "setRecycleView: "+medicationList.size() );
            if (medicationList.isEmpty()){
                binding.linearLayout.setVisibility(View.GONE);
            }else {
                binding.linearLayout.setVisibility(View.VISIBLE);

            }
        });
    }

}