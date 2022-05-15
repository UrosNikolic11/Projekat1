package rs.raf.projekat1.Uros_Nikolic_2619.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.activity.LoginActivity;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.ToDoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            String message = sharedPreferences.getString(LoginActivity.LOGIN_KEY, null);
            if (message == null) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, GlavniActivity.class);
                sharedPreferences.edit().putString(ToDoFragment.KEY, message).apply();
                setResult(RESULT_OK);
                startActivity(intent);
            }
            finish();
            return false;
        });
    }
}