package rs.raf.projekat1.Uros_Nikolic_2619.recycler.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.function.Consumer;

import rs.raf.projekat1.Uros_Nikolic_2619.R;
import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;

public class TicketAdapter3 extends ListAdapter<Ticket, TicketAdapter3.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;
    private final Consumer<Ticket> onDesnoClicked;
    private final Consumer<Ticket> onLevoClicked;
    private String user;

    public TicketAdapter3(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked, Consumer<Ticket> onDesnoClicked, Consumer<Ticket> onLevoClicked, String user) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        this.onDesnoClicked = onDesnoClicked;
        this.onLevoClicked = onLevoClicked;
        this.user = user;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public TicketAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item2, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.accept(ticket);
        }, (position) ->{
            Ticket ticket = getItem(position);
            onDesnoClicked.accept(ticket);
        }, (position) -> {
            Ticket ticket = getItem(position);
            onLevoClicked.accept(ticket);
        });
    }


    @Override
    public void onBindViewHolder(@NonNull TicketAdapter3.ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket, user);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked, Consumer<Integer> onDesnoClicked, Consumer<Integer> onMinusClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });

            itemView.findViewById(R.id.inProgresDesno).setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDesnoClicked.accept(getBindingAdapterPosition());
                }
            });

            itemView.findViewById(R.id.inProgresLevo).setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onMinusClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket, String user) {
            int slika;
            if (ticket.getTip().equals("Bug")) {
                slika = R.drawable.img_1;
            }
            else slika = R.drawable.img_2;
            ImageView imageView = itemView.findViewById(R.id.ticketImg2);
            Glide
                    .with(context)
                    .load(slika)
                    .circleCrop()
                    .into(imageView);

            ((TextView) itemView.findViewById(R.id.inProgresTitle)).setText(ticket.getNaslov());
            ((TextView) itemView.findViewById(R.id.inProgresDesc)).setText(ticket.getOpis());

            ImageButton ib1 = itemView.findViewById(R.id.inProgresDesno);
            ImageButton ib2 = itemView.findViewById(R.id.inProgresLevo);
            ib1.setVisibility(View.GONE);
            ib2.setVisibility(View.GONE);
        }

    }

}

