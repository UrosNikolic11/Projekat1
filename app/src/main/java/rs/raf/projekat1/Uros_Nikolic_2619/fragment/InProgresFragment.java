package rs.raf.projekat1.Uros_Nikolic_2619.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.activity.Prikaz2Activity;
import rs.raf.projekat1.Uros_Nikolic_2619.activity.PrikazActivity;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.adapter.TicketAdapter;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.adapter.TicketAdapter2;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.differ.TicketDiffItemCallBack;

public class InProgresFragment extends Fragment {

    public InProgresFragment() {
        super(R.layout.fragment_inprogres);
    }

    public static final String KEY = "key";

    private RecyclerView recyclerView;
    private EditText filter;
    private RecyclerViewModel recyclerViewModel;
    private String user;

    private TicketAdapter2 ticketAdapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        user = sharedPreferences.getString(KEY, null);
        init(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init(View view){
        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        filter = view.findViewById(R.id.inProgresFilter);
        recyclerView = view.findViewById(R.id.inProgresRec);
    }

    private void initListeners(){
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable s) {
                recyclerViewModel.filterTicketInProgres(s.toString());
            }
        });
    }

    private void initObservers(){
        recyclerViewModel.getInProgresFilter().observe(this.getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRecycler() {
        ticketAdapter = new TicketAdapter2(new TicketDiffItemCallBack(), ticket -> {
            if(user.startsWith("admin")){
                Intent intent = new Intent(this.getContext(), PrikazActivity.class);
                intent.putExtra(PrikazActivity.PRIKAZ_KEY, ticket);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this.getContext(), Prikaz2Activity.class);
                intent.putExtra(Prikaz2Activity.PRIKAZ2_KEY, ticket);
                startActivity(intent);
            }
        }, ticket -> {
            recyclerViewModel.addDone(ticket.getId());
            filter.setText("");
        }, ticket -> {
            recyclerViewModel.addToDo(ticket.getId());
            filter.setText("");
        }, user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }
}
