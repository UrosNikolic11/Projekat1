package rs.raf.projekat1.Uros_Nikolic_2619.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.DoneFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.InProgresFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.ToDoFragment;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView loginTextView;
    private EditText username;
    private EditText password;
    private EditText email;

    public static final String LOGIN_KEY = "loginKey";
    public static final String PROFIL_EMAIL = "profilEmail";
    public static final String PROFIL_USERNAME = "profilUsername";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        initView();
        initListeners();
    }

    private void initView(){
        loginBtn = findViewById(R.id.loginBtn);
        loginTextView = findViewById(R.id.loginLbl);
        username = findViewById(R.id.usernameTextView);
        password = findViewById(R.id.passwordTextView);
        email = findViewById(R.id.editTextTextEmailAddress);
    }

    private void initListeners() {
        loginBtn.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            String user = username.getText().toString();
            String sifra = password.getText().toString();
            String mejl = email.getText().toString();
            if (user.startsWith("admin") && isValidEmail(mejl)){
                if (sifra.equals("admin11")){
                    sharedPreferences.edit().putString(LOGIN_KEY, user).apply();
                    sharedPreferences.edit().putString(PROFIL_USERNAME, user).apply();
                    sharedPreferences.edit().putString(PROFIL_EMAIL, mejl).apply();
                    sharedPreferences.edit().putString(ToDoFragment.KEY, user).apply();
                    setResult(RESULT_OK);
                    Intent intent = new Intent(this, GlavniActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(this, "Pogresno korisnicko ime ili sifra", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                }
            } else if(sifra.equals("lozinka11") && !user.equals("") && isValidEmail(mejl)){
                sharedPreferences.edit().putString(LOGIN_KEY, user).apply();
                sharedPreferences.edit().putString(PROFIL_USERNAME, user).apply();
                sharedPreferences.edit().putString(PROFIL_EMAIL, mejl).apply();
                sharedPreferences.edit().putString(ToDoFragment.KEY, user).apply();
                sharedPreferences.edit().putString(InProgresFragment.KEY, user).apply();
                sharedPreferences.edit().putString(DoneFragment.KEY, user).apply();
                setResult(RESULT_OK);
                Intent intent = new Intent(this, GlavniActivity.class);
                startActivity(intent);
                finish();
            }   else {
                Toast.makeText(this, "Pogresno korisnicko ime ili sifra", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED);
            }

        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}