package np.com.sundaracharya.smarthealthreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import np.com.sundaracharya.smarthealthreminder.data.Profile;
import np.com.sundaracharya.smarthealthreminder.databinding.ActivityMainBinding;
import np.com.sundaracharya.smarthealthreminder.service.AlarmReceiver;
import np.com.sundaracharya.smarthealthreminder.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home/*, R.id.nav_add_profile, R.id.nav_add_medicine,R.id.nav_doctor_appointment*/)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headerView = binding.navView.getHeaderView(0);
        TextView textEmail = headerView.findViewById(R.id.textEmail);
        textEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.sendEmail(MainActivity.this, textEmail.getText().toString());
            }
        });

    }



    public void  runNotification(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Utils().notification(MainActivity.this,intent);

            }
        },2000);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void headerName(Profile profile) {

        try {
            View headerView = binding.navView.getHeaderView(0);
            TextView textEmail = headerView.findViewById(R.id.textEmail);
            TextView textName = headerView.findViewById(R.id.textName);
            textName.setText(profile.getFirstName() + " " + profile.getMiddleName() + " " + profile.getLastName());
            textEmail.setText(profile.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * set first alarm at start date and in interval hour
     * example: start time is 6 AM and interval is 6hr next alarm will
     * be 12PM, 18PM....and so on.
     */
    public void startAlertAtParticularTime(int interval, String startTime) {
        runNotification();
        String[] time = startTime.split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]+2);
        long repeatTime = interval * 60 * 60 * 1000;

        intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 111111, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                repeatTime, pendingIntent);

        Toast.makeText(this, "Alarm will vibrate at time specified",
                Toast.LENGTH_SHORT).show();

    }
}