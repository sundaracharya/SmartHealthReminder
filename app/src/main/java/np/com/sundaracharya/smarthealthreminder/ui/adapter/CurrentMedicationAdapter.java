package np.com.sundaracharya.smarthealthreminder.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.ui.medicine.MedicineFragment;

public class CurrentMedicationAdapter extends RecyclerView.Adapter<CurrentMedicationAdapter.CurrentMedication> {

    List<Medication> medicationList;
    MedicineFragment medicineFragment;
    OnClickMedication onClickMedication;

    public CurrentMedicationAdapter(MedicineFragment medicineFragment, List<Medication> medicationList, OnClickMedication onClickMedication) {
        this.medicineFragment = medicineFragment;
        this.medicationList = medicationList;
        this.onClickMedication = onClickMedication;
    }

    @NonNull
    @Override
    public CurrentMedication onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CurrentMedication(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_medicine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentMedication holder, int position) {

        int no = position+1;
        Medication medication = medicationList.get(position);
        holder.id.setText(String.valueOf(no+". "));
        holder.name.setText(medication.getMedicationName());
        holder.delete.setOnClickListener(view -> {
            medicineFragment.confirmDelete(medication.getMedicationId());
        });

        holder.edit.setOnClickListener(view -> {
            onClickMedication.onClick(medication);
        });
        Log.e("TAG", "onBindViewHolder: "+medicationList.size() );


    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    class CurrentMedication extends RecyclerView.ViewHolder {
        TextView id, name;
        ImageView edit, delete;

        public CurrentMedication(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            name = itemView.findViewById(R.id.textMedicineName);
            edit = itemView.findViewById(R.id.imageEdit);
            delete = itemView.findViewById(R.id.imageDelete);
        }
    }

    public void notifyList(List<Medication> list) {
        medicationList = list;
        notifyDataSetChanged();
    }

    public interface OnClickMedication {
        void onClick(Medication medication);
    }
}
