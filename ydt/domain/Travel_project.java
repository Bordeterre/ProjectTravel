package ydt.domain;

import java.util.*;

// Entity
public class Travel_project{
    private ArrayList<Prestation> prestations;
    private ArrayList<Flight_ticket> flight_tickets;
    private Client client;
    private ID id;

    //Constructor
    public Travel_project(Client client, ID id){
        this.client = client;
        this.id = id;
        this.prestations = new ArrayList<Prestation>();
        this.flight_tickets = new ArrayList<Flight_ticket>();
    }

    // Setters
    public void add_prestation(Prestation prestation){
        prestations.add(prestation);
    }

    public void add_flight_ticket(Flight_ticket flight_ticket){
        flight_tickets.add(flight_ticket);
    }

    public void remove_prestation(Prestation prestation){
        prestations.remove(prestation);
    }

    public void remove_flight_ticket(Flight_ticket flight_ticket){
        flight_tickets.remove(flight_ticket);
    }

    // Getters
    public double get_price(){
        double price = 0;
        ListIterator<Prestation> itp = prestations.listIterator();
        while(itp.hasNext()){
            price += itp.next().get_price();
        }

        ListIterator<Flight_ticket> itf = flight_tickets.listIterator();
        while(itf.hasNext()){
            price += itf.next().get_price();
        }

        return price;
    }

    public ArrayList<Prestation> get_prestations(){
        return prestations;
    }

    public ArrayList<Flight_ticket> get_flight_tickets(){
        return flight_tickets;
    }

    public ID get_id(){
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Travel_project)) {
            return false;
        }
        Travel_project p = (Travel_project) o;
        return get_id().equals(p.get_id());
    }  


}