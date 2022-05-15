package rs.raf.projekat1.Uros_Nikolic_2619.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;

public class NewFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public NewFragment(){
        super(R.layout.fragment_new);
    }

    private Spinner spinerTip;
    private Spinner spinerPrioritet;
    private EditText est;
    private EditText naslov;
    private EditText opis;
    private Button add;

    private RecyclerViewModel recyclerViewModel;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view){
        initView(view);
        initListeners();
    }

    private void initView(View view){
        add = view.findViewById(R.id.add);
        spinerTip = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(), R.array.tipovi, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerTip.setOnItemSelectedListener(this);
        spinerTip.setAdapter(adapter1);
        spinerPrioritet = view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getContext(), R.array.prioriteti, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerPrioritet.setOnItemSelectedListener(this);
        spinerPrioritet.setAdapter(adapter2);
        est = view.findViewById(R.id.Est);
        naslov = view.findViewById(R.id.naslovTiketa);
        opis = view.findViewById(R.id.opisTiketa);
    }

    private void initListeners() {
        add.setOnClickListener(view -> {
            String title = naslov.getText().toString();
            String desc = opis.getText().toString();
            String procena = est.getText().toString();
            String tip = spinerTip.getSelectedItem().toString();
            String prioritet = spinerPrioritet.getSelectedItem().toString();
            if(title.equals("") || desc.equals("") || est.getText().toString().equals("") || procena.equals("")){
                Toast.makeText(this.getContext(), "Niste uneli sve potrebne podatke", Toast.LENGTH_SHORT).show();
            } else if(!isNumeric(procena)){
                Toast.makeText(this.getContext(), "Broj dana mora da bude celobrojna vrednost", Toast.LENGTH_SHORT).show();
            }
            else {
                Integer e = Integer.valueOf(procena);
                recyclerViewModel.addTicket(tip, prioritet, e, title, desc);
                est.setText("");
                naslov.setText("");
                opis.setText("");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String s = String.valueOf(parent.getItemIdAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
