package rs.raf.projekat1.Uros_Nikolic_2619.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;

public class Prikaz2Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz2);
        init();
    }

    public static String PRIKAZ2_KEY = "prikazKey2";

    private Button log;
    private TextView naslov;
    private TextView opis;
    private TextView est;
    private TextView tip;
    private TextView prioritet;
    private ImageView slika;

    private Ticket ticket;
    private RecyclerViewModel recyclerViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(){
        initView();
        initListener();
        parseIntent();
    }

    private void initView(){
        log = findViewById(R.id.prikazBtn2);
        naslov = findViewById(R.id.prikazNaslov2);
        opis = findViewById(R.id.prikazOpis2);
        tip = findViewById(R.id.prikazTip2);
        prioritet = findViewById(R.id.prikazPrioritet2);
        est = findViewById(R.id.prikazEst2);
        slika = findViewById(R.id.prikazSlika2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initListener(){
//        log.setOnClickListener(view -> {
//            recyclerViewModel.logPlus(ticket.getId());
//        });
//
//        log.setOnLongClickListener(view -> {
//            recyclerViewModel.logMinus(ticket.getId());
//            return false;
//        });
    }

    private void parseIntent(){
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.ticket = (Ticket) intent.getExtras().getSerializable(PRIKAZ2_KEY);
            naslov.setText(ticket.getNaslov());
            opis.setText(ticket.getOpis());
            tip.setText(ticket.getTip());
            prioritet.setText(ticket.getPrioritet());
            est.setText(String.valueOf(ticket.getEst()));
            log.setText(String.valueOf(ticket.getLog()));
            if(ticket.getTip().equals("Bug")){
                slika.setImageResource(R.drawable.img_1);
            }
            else slika.setImageResource(R.drawable.img_2);
        }

    }
}