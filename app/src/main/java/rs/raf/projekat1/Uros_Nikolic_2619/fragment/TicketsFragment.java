package rs.raf.projekat1.Uros_Nikolic_2619.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.recycler.RecyclerViewModel;
import rs.raf.projekat1.Uros_Nikolic_2619.viewpager.TabsAdapter;

public class TicketsFragment extends Fragment {

    public TicketsFragment(){
        super(R.layout.fragment_tickets);
    }

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private RecyclerViewModel recyclerViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initTabs();
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewPagerTab);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    private void initTabs() {
        viewPager.setAdapter(new TabsAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
