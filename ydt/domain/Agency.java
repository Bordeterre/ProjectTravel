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

    public Flight search_flight(String date, String departure, String destination){
        Iterator<Flight> it = this.flights.iterator();
        while(it.hasNext()){
            Flight flight = it.next();
            if((date == null || date.equals(flight.get_date()))
                && (departure == null || departure.equals(flight.get_departure()))
                && (destination == null || destination.equals(flight.get_destination()))
            ){
                return flight;
            }
        }
        return null;
    }

    public Rental search_rental(Set<Rental> rental_set, String name){
        Iterator<Rental> it = rental_set.iterator();
        while(it.hasNext()){
            Rental rental = it.next();
            if(rental.get_name().equals(name)){
                return rental;
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

    public Travel_project create_travel_project(String name){
        Client client = this.get_client_by_name(name);
        if(client == null){ return null;}

        Travel_project travel_project = new Travel_project(client, new ID());
        this.travel_projects.add(travel_project);  
        return travel_project;
    }

    public void add_flight_ticket(Travel_project travel_project, Flight flight, boolean is_discounted, boolean is_first_class){
        Flight_ticket flight_ticket = new Flight_ticket(flight, is_discounted, is_first_class);
        travel_project.add_flight_ticket(flight_ticket);
    }

    public Prestation add_prestation(Travel_project travel_project, String hotel_name, String car_name, boolean has_luxurious_prestation){
        Rental hotel = this.search_rental(this.hotels, hotel_name);
        if(hotel == null){return null;}
        Rental car = this.search_rental(this.cars, car_name);
        if(car == null){return null;}
        Prestation prestation = new Prestation(car, hotel, has_luxurious_prestation);

        travel_project.add_prestation(prestation);
        return prestation;
    }

}

