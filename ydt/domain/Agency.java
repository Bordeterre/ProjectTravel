package ydt.domain;

import java.util.*;

//Agreggate
public class Agency{
    private ID id;
    private Set<Client> clients;
    private Set<Rental> hotels;
    private Set<Rental> cars;
    private Set<Flight> flights;

    private Set<Travel_project> travel_projects;

    public Agency(){
        this.id = new ID();
        this.clients = new HashSet<Client>();
        this.hotels = new HashSet<Rental>();
        this.cars = new HashSet<Rental>();
        this.flights = new HashSet<Flight>();

        this.travel_projects = new HashSet<Travel_project>();
    }


    // Get

    // Search
    public Client get_client_by_name(String name){
        Iterator<Client> it = this.clients.iterator();
        while(it.hasNext()){
            Client client = it.next();
            if(name.equals(client.get_name())){
                return client;
            }
        }

        return null;
    }

    // Creation
    public boolean enter_client(String name){
        return this.clients.add(new Client(name));
    }

    public boolean enter_flight(String date, String departure, String destination, int price){
        return this.flights.add(new Flight(date, departure, destination, price));
    }

    public boolean enter_hotel(String name, int price){
        return this.hotels.add(new Rental(name, price));
    }

    public boolean enter_car(String name, int price){
        return this.cars.add(new Rental(name, price));
    }

    public boolean create_travel_project(String name){
        Client client = this.get_client_by_name(name);
        if(client == null){ return false;}

        this.travel_projects.add(new Travel_project(client, new ID()));  
        return true;
    }

}

