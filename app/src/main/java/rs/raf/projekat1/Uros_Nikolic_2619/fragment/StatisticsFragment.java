package rs.raf.projekat1.Uros_Nikolic_2619.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.adapter.TicketAdapter;

public class StatisticsFragment extends Fragment {

    public StatisticsFragment(){
        super(R.layout.fragment_statistics);
    }

    private TextView toDoLbl;
    private TextView toDoEncLbl;
    private TextView toDoBugLbl;
    private TextView inProgresLbl;
    private TextView inProgresEncLbl;
    private TextView inProgresBugLbl;
    private TextView doneLbl;
    private TextView doneEncLbl;
    private TextView doneBugLbl;

    private RecyclerViewModel recyclerViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view){
        initView(view);
        initObservers();
    }

    private void initView(View view) {
        toDoLbl = view.findViewById(R.id.todoLbl);
        toDoBugLbl = view.findViewById(R.id.toDoBugLbl);
        toDoEncLbl = view.findViewById(R.id.toDoEncLbl);
        inProgresLbl = view.findViewById(R.id.inProgresLbl);
        inProgresEncLbl = view.findViewById(R.id.inProgresEncLbl);
        inProgresBugLbl = view.findViewById(R.id.inProgresBugLbl);
        doneLbl = view.findViewById(R.id.doneLbl);
        doneEncLbl = view.findViewById(R.id.doneEncLbl);
        doneBugLbl = view.findViewById(R.id.doneBugLbl);
    }

    private void initObservers() {
        recyclerViewModel.getToDo().observe(getViewLifecycleOwner(), tickets -> {
            int bug = 0;
            int enc = 0;
            for(Ticket t: tickets){
                if (t.getTip().equals("Bug")) bug++;
                        else enc++;
            }
            toDoLbl.setText(String.valueOf(tickets.size()));
            toDoEncLbl.setText(String.valueOf(enc));
            toDoBugLbl.setText(String.valueOf(bug));
        });

        recyclerViewModel.getInProgres().observe(getViewLifecycleOwner(), tickets -> {
            int bug = 0;
            int enc = 0;
            for(Ticket t: tickets){
                if (t.getTip().equals("Bug")) bug++;
                else enc++;
            }
            inProgresLbl.setText(String.valueOf(tickets.size()));
            inProgresEncLbl.setText(String.valueOf(enc));
            inProgresBugLbl.setText(String.valueOf(bug));
        });

        recyclerViewModel.getDone().observe(getViewLifecycleOwner(), tickets -> {
            int bug = 0;
            int enc = 0;
            for(Ticket t: tickets){
                if (t.getTip().equals("Bug")) bug++;
                else enc++;
            }
            doneLbl.setText(String.valueOf(tickets.size()));
            doneEncLbl.setText(String.valueOf(enc));
            doneBugLbl.setText(String.valueOf(bug));
        });
    }
}
