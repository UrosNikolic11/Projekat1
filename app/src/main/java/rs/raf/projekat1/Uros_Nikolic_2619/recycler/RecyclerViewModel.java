package rs.raf.projekat1.Uros_Nikolic_2619.recycler;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import rs.raf.projekat1.Uros_Nikolic_2619.model.Ticket;

public class RecyclerViewModel extends ViewModel implements Serializable {

    private static int counterToDo = 2;

    private final MutableLiveData<List<Ticket>> toDo = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> toDoFilter = new MutableLiveData<>();
    private final ArrayList<Ticket> toDoList = new ArrayList<>();
    private final MutableLiveData<List<Ticket>> inProgres = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgresFilter = new MutableLiveData<>();
    private final ArrayList<Ticket> inProgresList = new ArrayList<>();
    private final MutableLiveData<List<Ticket>> done = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneFilter = new MutableLiveData<>();
    private final ArrayList<Ticket> doneList = new ArrayList<>();

    public RecyclerViewModel() {
        Ticket ticket1 = new Ticket(0, "Bug", "Low", 1, "Tiket 1", "");
        Ticket ticket2 = new Ticket(1, "Enhancements", "Low", 1, "Tiket 2", "");
        toDoList.add(ticket1);
        toDoList.add(ticket2);
        ArrayList<Ticket> listaZaSubmit = new ArrayList<>(toDoList);
        toDo.setValue(listaZaSubmit);
        toDoFilter.setValue(listaZaSubmit);
    }

    public LiveData<List<Ticket>> getToDo(){
        return toDo;
    }
    public LiveData<List<Ticket>> getToDoFilter(){
        return toDoFilter;
    }

    public LiveData<List<Ticket>> getInProgres(){
        return inProgres;
    }
    public LiveData<List<Ticket>> getInProgresFilter(){
        return inProgresFilter;
    }

    public LiveData<List<Ticket>> getDone(){
        return done;
    }
    public LiveData<List<Ticket>> getDoneFilter(){
        return doneFilter;
    }

   public void addTicket(String tip, String prioritet, Integer est, String naslov, String opis){
       Ticket ticket = new Ticket(counterToDo++, tip, prioritet, est, naslov, opis);
       toDoList.add(ticket);
       ArrayList<Ticket> listaZaSubmit = new ArrayList<>(toDoList);
       toDoFilter.setValue(listaZaSubmit);
       toDo.setValue(listaZaSubmit);
   }

   @RequiresApi(api = Build.VERSION_CODES.N)
   public void removeToDo(int id){
       Optional<Ticket> ticketObject = toDoList.stream().filter(ticket -> ticket.getId() == id).findFirst();
       if (ticketObject.isPresent()) {
           toDoList.remove(ticketObject.get());
           ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
           toDo.setValue(listToSubmit);
           toDoFilter.setValue(listToSubmit);
       }
   }

   @RequiresApi(api = Build.VERSION_CODES.N)
   public void addInProgres(int id){
       Optional<Ticket> ticketObject = toDoList.stream().filter(ticket -> ticket.getId() == id).findFirst();
       if (ticketObject.isPresent()) {
           inProgresList.add(ticketObject.get());
           ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
           inProgres.setValue(listaZaSubmit);
           inProgresFilter.setValue(listaZaSubmit);
           toDoList.remove(ticketObject.get());
           ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoList);
           toDo.setValue(listToSubmit);
           toDoFilter.setValue(listToSubmit);
       }
   }


   @RequiresApi(api = Build.VERSION_CODES.N)
   public void addDone(int id){
       Optional<Ticket> ticketObject = inProgresList.stream().filter(ticket -> ticket.getId() == id).findFirst();
       if (ticketObject.isPresent()) {
           doneList.add(ticketObject.get());
           ArrayList<Ticket> listaZaSubmit = new ArrayList<>(doneList);
           done.setValue(listaZaSubmit);
           doneFilter.setValue(listaZaSubmit);
           inProgresList.remove(ticketObject.get());
           ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgresList);
           inProgres.setValue(listToSubmit);
           inProgresFilter.setValue(listToSubmit);
       }
   }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addToDo(int id){
        Optional<Ticket> ticketObject = inProgresList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            toDoList.add(ticketObject.get());
            ArrayList<Ticket> listaZaSubmit = new ArrayList<>(toDoList);
            toDo.setValue(listaZaSubmit);
            toDoFilter.setValue(listaZaSubmit);
            inProgresList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgresList);
            inProgres.setValue(listToSubmit);
            inProgresFilter.setValue(listToSubmit);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketToDo(String filter) {
        List<Ticket> filteredList = toDoList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        toDoFilter.setValue(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketInProgres(String filter) {
        List<Ticket> filteredList = inProgresList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        inProgresFilter.setValue(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterTicketDone(String filter) {
        List<Ticket> filteredList = doneList.stream().filter(ticket -> ticket.getNaslov().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        doneFilter.setValue(filteredList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void logPlus(int id){
        Optional<Ticket> ticketObject = inProgresList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: inProgresList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br++);
                    inProgresList.remove(ticketObject);
                    inProgresList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    inProgres.setValue(listaZaSubmit);
                    inProgresFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }

        Optional<Ticket> ticketObject2 = toDoList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: toDoList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br++);
                    toDoList.remove(ticketObject2);
                    toDoList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    toDo.setValue(listaZaSubmit);
                    toDoFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }

        Optional<Ticket> ticketObject3 = doneList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: doneList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br++);
                    doneList.remove(ticketObject3);
                    doneList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    done.setValue(listaZaSubmit);
                    doneFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void logMinus(int id){
        Optional<Ticket> ticketObject = inProgresList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: inProgresList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br--);
                    inProgresList.remove(ticketObject);
                    inProgresList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    inProgres.setValue(listaZaSubmit);
                    inProgresFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }

        Optional<Ticket> ticketObject2 = toDoList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: toDoList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br--);
                    toDoList.remove(ticketObject2);
                    toDoList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    toDo.setValue(listaZaSubmit);
                    toDoFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }

        Optional<Ticket> ticketObject3 = doneList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            for(Ticket t: doneList){
                if(t.getId() == ticketObject.get().getId()){
                    int br = t.getLog();
                    t.setLog(br--);
                    doneList.remove(ticketObject3);
                    doneList.add(t);
                    ArrayList<Ticket> listaZaSubmit = new ArrayList<>(inProgresList);
                    done.setValue(listaZaSubmit);
                    doneFilter.setValue(listaZaSubmit);
                    break;
                }
            }
        }
    }
}
