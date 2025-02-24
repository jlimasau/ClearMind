package com.clearmind123;

import static androidx.core.app.AlarmManagerCompat.setAlarmClock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.os.LocaleListCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.UiModeManager;
import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

//update abcde
//update marimon zoom
// key is 6 characters

public class MainActivity extends AppCompatActivity {

    AdView mAdView;
    private TextView abouttext;
    boolean showhide = false;

    //String lang;
    int x = 0;

    int hour;
    int minute;
    int toMilliseconds;

    Locale locale;
    String lang;
    Menu optionsMenu;

    private ReviewInfo reviewInfo;
    private ReviewManager manager;
    String languageToLoad;


    @SuppressLint({"MissingPermission", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    /*    LocaleListCompat aLocale = LocaleListCompat.forLanguageTags(locale.getLanguage());
        AppCompatDelegate.setApplicationLocales(aLocale);*/

        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();





        activateReviewInfo();


        abouttext = findViewById(R.id.textView);
        Button about = findViewById(R.id.button2);
        Button tools = findViewById(R.id.button);
        Button reminder = findViewById(R.id.button3);

       Button language = findViewById(R.id.language1);

        startAnimation1();

        createNotificationChannel();



        tools.setOnClickListener(view -> openActivityTools());


        locale = MainActivity.this.getResources().getConfiguration().locale;
        lang = locale.toString();

        System.out.println(lang);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                if (locale.toString().matches("es")||locale.toString().matches("es_US")){
                   // lang = "en";
                    System.out.println("english");

                    String languageToLoad = "en_US";
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;

                    LocaleListCompat aLocale = LocaleListCompat.forLanguageTags(locale.getLanguage());
                    AppCompatDelegate.setApplicationLocales(aLocale);
                   // MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (locale!=null) {
                    System.out.println("spanish");

                    String languageToLoad = "es";
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;

                    LocaleListCompat aLocale = LocaleListCompat.forLanguageTags(locale.getLanguage());
                    AppCompatDelegate.setApplicationLocales(aLocale);
                    //MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    
                }
                else{




                    //lang = "es";
                    System.out.println("spanish");

                    languageToLoad = "es";
                    locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;

                    LocaleListCompat aLocale = LocaleListCompat.forLanguageTags(locale.getLanguage());
                    AppCompatDelegate.setApplicationLocales(aLocale);


                   // MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());

                    //AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("es"));

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });



        about.setOnClickListener(view -> {

            //showReviewFlow();
            showhide=!showhide;

            if (showhide == true) {

                startReviewFlow();










            abouttext.setClickable(true);
            abouttext.setMovementMethod(LinkMovementMethod.getInstance());
            String about2 = getResources().getString(R.string.about2);
            String about1 = getResources().getString(R.string.about1);

            //String text = String.valueOf(R.string.about2);
            String text = about1 + "<br>" + about2 + "<a href='https://venmo.com/u/jefe-rey'> Venmo me<a/>";
            abouttext.setText(Html.fromHtml(text));
            }
            else{
                abouttext.setText("");
            }
        });







        reminder.setOnClickListener(view -> {

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
            final androidx.appcompat.app.AlertDialog alert = builder.create();



            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour = i;
                    minute = i1;
                    System.out.println(hour );
                    System.out.println(minute );
                    ArrayList<Integer> alarmDays= new ArrayList<Integer>();
                    alarmDays.add(Calendar.SATURDAY);
                    alarmDays.add(Calendar.MONDAY);
                    alarmDays.add(Calendar.TUESDAY);
                    alarmDays.add(Calendar.WEDNESDAY);
                    alarmDays.add(Calendar.THURSDAY);
                    alarmDays.add(Calendar.FRIDAY);
                    alarmDays.add(Calendar.SUNDAY);


                    Intent j = new Intent(AlarmClock.ACTION_SET_ALARM)
                            .putExtra(AlarmClock.EXTRA_MESSAGE, "Reminder to use Clear Mind")
                            .putExtra(AlarmClock.EXTRA_HOUR, hour)
                            .putExtra(AlarmClock.EXTRA_MINUTES, minute)
                            .putExtra(AlarmClock.EXTRA_DAYS, alarmDays);



                    // Intent i = new Intent(AlarmClock.ACTION_SET_TIMER);
                    // i.putExtra(AlarmClock.EXTRA_LENGTH, 1500);
                    // i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                    startActivity(j);

                }
            };
            int style = R.style.MyTimePickerDialog;
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,hour,minute,false);
            timePickerDialog.setTitle(R.string.reminder1);
            //timePickerDialog.
            timePickerDialog.show();



            UiModeManager uiModeManager = (UiModeManager) this.getSystemService(Context.UI_MODE_SERVICE);
            int mode = uiModeManager.getNightMode();
            if (mode == UiModeManager.MODE_NIGHT_YES) {
                timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
                timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.WHITE);



            }
            else{

            }





























       /*     TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour = i;
                    minute = i1;
                    System.out.println(hour );
                    System.out.println(minute );

                }
            };
            int style = AlertDialog.THEME_HOLO_DARK;
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,hour,minute,false);
            timePickerDialog.setTitle("Set a reminder");
            timePickerDialog.show();
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "Reminder")
                    .putExtra(AlarmClock.EXTRA_HOUR, hour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, minute);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }*/


      /*
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();


            //System.out.println(hour + minute);

            Calendar time1 = Calendar.getInstance();

               TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int i, int i1) {
                       hour = i;
                       minute = i1;
                       System.out.println(hour );
                       System.out.println(minute );





                   }
               };
               int style = AlertDialog.THEME_HOLO_DARK;

               TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener,hour,minute,false);
               timePickerDialog.setTitle("Set a reminder");
               timePickerDialog.show();



*/
            //enter time dialog
            //enter time variable into notification to set off
            //alternatively open alarm clock to create own alarm
            //what about am or pm
        });


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        SharedPreferences sharedpref = this.getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        boolean isFirstRun = sharedpref.getBoolean("first1", true);

        if (isFirstRun){


            Toast.makeText(getApplicationContext(), getString(R.string.toast1),
                    Toast.LENGTH_LONG).show();
            editor.putBoolean("first1", false);
            editor.apply();
        }
        //only show the first time



        //backs up sharedpref
        BackupManager.dataChanged("com.clearmind123");

    }




//for app rating
    void activateReviewInfo(){
        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> managerInfoTask = manager.requestReviewFlow();
        managerInfoTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                reviewInfo = task.getResult();
            } else {
                // There was some problem, log or handle the error code.

                Toast.makeText(this, "Review failed to start", Toast.LENGTH_SHORT).show();
            }
        });
    }

//for app rating
    private void startReviewFlow(){
        if(reviewInfo != null){
            Task<Void> flow = manager.launchReviewFlow(MainActivity.this, reviewInfo);
            flow.addOnCompleteListener(task -> {
                //Toast.makeText(this, "Review Successful", Toast.LENGTH_LONG).show();

                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
            });
        }
        else{

        }
    }


    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    private void startAnimation1() {
        final ImageView marimon = findViewById(R.id.marimon);

            marimon.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.breath_in));

    }


    public void openActivityTools(){
        Intent intent = new Intent(this, Tools.class);
        startActivity(intent);
    }



/*    @Override
    public void onBackPressed() {
        finishAffinity();
        //intent start intent tools.java
        // super.onBackPressed();
    }*/



    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed();
            finishAffinity();
            ActivityCompat.finishAffinity(MainActivity.this);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.MainActivity_backbutton, Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        optionsMenu = menu;
        //MenuItem item = optionsMenu.getItem(R.id.help);


        MenuItem item = optionsMenu.findItem(R.id.help);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.MainActivity_text);
                final androidx.appcompat.app.AlertDialog alert = builder.create();
                alert.show();
                System.out.println("THIS!!!!");



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}