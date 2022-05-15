package rs.raf.projekat1.Uros_Nikolic_2619.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.Uros_Nikolic_2619.fragment.DoneFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.InProgresFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.NewFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.ProfilFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.StatisticsFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.TicketsFragment;
import rs.raf.projekat1.Uros_Nikolic_2619.fragment.ToDoFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 3;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;

    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_1: fragment = new ToDoFragment(); break;
            case FRAGMENT_2: fragment = new InProgresFragment(); break;
            default: fragment = new DoneFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case FRAGMENT_1: return "ToDo";
            case FRAGMENT_2: return "InProgres";
            default: return "Done";
        }
    }
}
