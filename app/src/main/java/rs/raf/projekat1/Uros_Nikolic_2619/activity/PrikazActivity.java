package rs.raf.projekat1.Uros_Nikolic_2619.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;

public class PrikazActivity extends AppCompatActivity {

    public PrikazActivity() {
        super(R.layout.activity_prikaz);
    }

    public static String PRIKAZ_KEY = "prikazKey";
    public static String MODEL_KEY = "prikazModel";

    private Button edit;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(){
        initView();
        parseIntent();
        initListener();
    }

    private void initView(){
        edit = findViewById(R.id.prikazEditBtn1);
        log = findViewById(R.id.prikazBtn);
        naslov = findViewById(R.id.prikazNaslov);
        opis = findViewById(R.id.prikazOpis);
        tip = findViewById(R.id.prikazTip);
        prioritet = findViewById(R.id.prikazPrioritet);
        est = findViewById(R.id.prikazEst);
        slika = findViewById(R.id.prikazSlika);
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
            this.ticket = (Ticket) intent.getExtras().getSerializable(PRIKAZ_KEY);
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
