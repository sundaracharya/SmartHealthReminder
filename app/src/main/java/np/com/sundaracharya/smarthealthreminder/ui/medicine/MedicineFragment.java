package np.com.sundaracharya.smarthealthreminder.ui.medicine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentAddMedicineBinding;
import np.com.sundaracharya.smarthealthreminder.databinding.FragmentMedicineInfoBinding;
import np.com.sundaracharya.smarthealthreminder.ui.adapter.CurrentMedicationAdapter;

public class MedicineFragment extends Fragment {

    private FragmentMedicineInfoBinding binding;
    private CurrentMedicationAdapter medicationAdapter;
    MedicineViewModel medicineViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medicineViewModel =
                new ViewModelProvider(this).get(MedicineViewModel.class);

        binding = FragmentMedicineInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_add_medicine_to_addMedicineFragment);
            }
        });
        setRecycleView();
    }

    private void setRecycleView() {
        binding.recycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycleView.setHasFixedSize(true);
        List<Medication> list = new ArrayList<Medication>();

        medicationAdapter = new CurrentMedicationAdapter(this, list, medication -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("medication",  medication);
            Navigation.findNavController(getView()).navigate(R.id.action_nav_add_medicine_to_addMedicineFragment,bundle);
        });
        binding.recycleView.setAdapter(medicationAdapter);

        medicineViewModel.getMedicationList().observe(getViewLifecycleOwner(), medicationList -> {
            Log.e("TAG", "setRecycleView: "+medicationList);
            medicationAdapter.notifyList(medicationList);

        });

    }
    public void confirmDelete(int medicationId) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Medicine Entry ?")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        medicineViewModel.deleteMedication(medicationId);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}