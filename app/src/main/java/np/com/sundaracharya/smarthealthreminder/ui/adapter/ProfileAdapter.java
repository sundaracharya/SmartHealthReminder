package np.com.sundaracharya.smarthealthreminder.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import np.com.sundaracharya.smarthealthreminder.R;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.ui.profile.ProfileFragment;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewModel> {

    ProfileFragment profileFragment;
    List<Profile> profileList;
    OnClickProfile onClickProfile;

    public ProfileAdapter(ProfileFragment profileFragment, List<Profile> profileList, OnClickProfile onClickProfile) {
        this.profileFragment = profileFragment;
        this.profileList = profileList;
        this.onClickProfile = onClickProfile;
    }


    @NonNull
    @Override
    public ProfileViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewModel(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_medicine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewModel holder, int position) {
        Profile profile = profileList.get(position);
        holder.makeDefault.setVisibility(View.VISIBLE);
        int no = position+1;
        if (profile.getIsPrimary()) {
            holder.makeDefault.setChecked(true);

//            holder.id.setTextColor(ContextCompat.getColor(profileFragment.requireContext(),R.color.purple_700));
//            holder.name.setTextColor(ContextCompat.getColor(profileFragment.requireContext(),R.color.purple_700));
//            holder.edit.setImageTintList(ContextCompat.getColorStateList(profileFragment.requireContext(),R.color.purple_700));
//            holder.delete.setImageTintList(ContextCompat.getColorStateList(profileFragment.requireContext(),R.color.purple_700));

        } else {
            holder.makeDefault.setChecked(false);
//            holder.id.setTextColor(ContextCompat.getColor(profileFragment.requireContext(),R.color.black));
//            holder.name.setTextColor(ContextCompat.getColor(profileFragment.requireContext(),R.color.black));
//            holder.edit.setImageTintList(ContextCompat.getColorStateList(profileFragment.requireContext(),R.color.black));
//            holder.delete.setImageTintList(ContextCompat.getColorStateList(profileFragment.requireContext(),R.color.black));

        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileFragment.confirmDelete(profile.getProfileId());
            }
        });
        holder.id.setText(String.valueOf(no)+". ");
        holder.name.setText(profile.getFirstName() + " " + profile.getMiddleName() + " " + profile.getLastName());
        holder.edit.setOnClickListener(view -> onClickProfile.onClick(profile));

        holder.makeDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.makeDefault.setChecked(b);
                if (b){
                    Profile newProfile = profile;
                    newProfile.setPrimary(true);
                    profileFragment.updateAll();
                    profileFragment.updateProfile(newProfile);
                }
            }
        });
//        holder.cardView.setOnClickListener(view -> {
//
//            Profile newProfile = profile;
//            newProfile.setPrimary(true);
//            profileFragment.updateAll();
//            profileFragment.updateProfile(newProfile);
//        });


    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ProfileViewModel extends RecyclerView.ViewHolder {
        TextView id, name;
        ImageView edit, delete;
        CardView cardView;
        SwitchCompat makeDefault;

        public ProfileViewModel(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            name = itemView.findViewById(R.id.textMedicineName);
            edit = itemView.findViewById(R.id.imageEdit);
            delete = itemView.findViewById(R.id.imageDelete);
            cardView = itemView.findViewById(R.id.cardView);
            makeDefault = itemView.findViewById(R.id.switchDefault);
        }
    }

    public void notifyList(List<Profile> newProfileList) {
        profileList = newProfileList;
        notifyDataSetChanged();
    }

    public interface OnClickProfile {
        void onClick(Profile profile);
    }
}
