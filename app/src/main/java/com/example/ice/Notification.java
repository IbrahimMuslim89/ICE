package com.example.ice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Notification extends AppCompatActivity
{


    private static final String chanel_id="Alert";
    private static final String chanel_name="Alertmess";
    private static final String chanel_description="Alertdet";

    private Toolbar toolbar;

    ToggleButton toggleButton11;
    ToggleButton toggleButton12;
    ToggleButton toggleButton13;
    ToggleButton toggleButton14;
    ToggleButton toggleButton15;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(chanel_id,chanel_name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(chanel_description);
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        setContentView(R.layout.activity_notification);
        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("Notification");

        toggleButton11 = (ToggleButton) findViewById(R.id.toggleButton11);
        toggleButton12 = (ToggleButton) findViewById(R.id.toggleButton12);
        toggleButton13 = (ToggleButton) findViewById(R.id.toggleButton13);
        toggleButton14 = (ToggleButton) findViewById(R.id.toggleButton14);
        toggleButton15 = (ToggleButton) findViewById(R.id.toggleButton15);

        toggleButton11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    toggleButton12.setChecked(true);
                    toggleButton13.setChecked(true);
                    toggleButton14.setChecked(true);
                    toggleButton15.setChecked(true);
                    displaynotification();
                }
                else
                {
                    toggleButton12.setChecked(false);
                    toggleButton13.setChecked(false);
                    toggleButton14.setChecked(false);
                    toggleButton15.setChecked(false);
                }




            }
        });
    }

    private void displaynotification()
    {
        NotificationCompat.Builder   mBuilder = new NotificationCompat.Builder(this,chanel_id)
                .setSmallIcon(R.drawable.ic_049_siren).setContentTitle("Emergrncy Deails").setContentText("Emergency come")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notifiManger=NotificationManagerCompat.from(this);
        notifiManger.notify(1,mBuilder.build() );

    }
}
