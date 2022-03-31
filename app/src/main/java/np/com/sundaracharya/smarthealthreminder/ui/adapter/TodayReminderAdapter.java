package np.com.sundaracharya.smarthealthreminder.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Medication;
import np.com.sundaracharya.smarthealthreminder.data.Profile;

public class TodayReminderAdapter extends RecyclerView.Adapter<TodayReminderAdapter.TodayReminderViewHolder> {
    private List<Medication> medicationList;
    public TodayReminderAdapter(List<Medication> medicationList){
        this.medicationList=medicationList;
    }

    @NonNull
    @Override
    public TodayReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_today,parent,false);
        return new TodayReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayReminderViewHolder holder, int position) {
        Medication medication = medicationList.get(position);
        holder.nameOfMedicine.setText(medication.getMedicationName());
        holder.timeOfMedicine.setText(medication.getTimeDifference()+"");
        holder.doseOfMedicine.setText(medication.getMedicationDailyDoes());
        holder.action.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    class TodayReminderViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfMedicine, timeOfMedicine, doseOfMedicine;
        CheckBox action;

        public TodayReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfMedicine= itemView.findViewById(R.id.textMedicineName);
            timeOfMedicine = itemView.findViewById(R.id.textTime);
            doseOfMedicine = itemView.findViewById(R.id.timeDosage);
            action = itemView.findViewById(R.id.actionCheck);
        }
    }

    public void notifyList(List<Medication> newProfileList) {
        medicationList = newProfileList;
        notifyDataSetChanged();
    }
}
