package rs.raf.projekat1.Uros_Nikolic_2619.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;

public class TicketDiffItemCallBack extends DiffUtil.ItemCallback<Ticket>{
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getNaslov().equals(newItem.getNaslov())
                && oldItem.getOpis().equals(newItem.getOpis())
                && oldItem.getPrioritet().equals(newItem.getPrioritet())
                && oldItem.getTip().equals(newItem.getTip())
                && oldItem.getEst().equals(newItem.getEst());
    }
}
