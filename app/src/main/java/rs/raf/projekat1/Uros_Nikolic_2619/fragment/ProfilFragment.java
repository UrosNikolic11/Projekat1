package rs.raf.projekat1.Uros_Nikolic_2619.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.activity.LoginActivity;
import rs.raf.projekat1.Uros_Nikolic_2619.activity.MainActivity;

public class ProfilFragment extends Fragment {

    public ProfilFragment(){
        super(R.layout.fragment_profile);
    }

    private Button logOutBtn;
    private TextView username;
    private TextView email;
    private ImageView slika;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    public void init(View view){
        initView(view);
        initListeners();
    }

    private void initView(View view){
        logOutBtn = view.findViewById(R.id.buttonLogOut);
        username = view.findViewById(R.id.profileUser);
        email = view.findViewById(R.id.profileMail);
        slika = view.findViewById(R.id.imageView2);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        String u = sharedPreferences.getString(LoginActivity.PROFIL_USERNAME,null);
        String e = sharedPreferences.getString(LoginActivity.PROFIL_EMAIL, null);
        username.setText(u);
        email.setText(e);
    }

    private void initListeners() {
        logOutBtn.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(this.getActivity(), MainActivity.class);
            startActivity(intent);
        });
    }
}
