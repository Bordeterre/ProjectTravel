package ydt.domain;

import java.util.*;

//Agreggate
public class Agency{
    private ID id;
    private ArrayList<Travel_project> travel_projects;
    private ArrayList<Client> clients;
    private ArrayList<Hotel> hotels;
    private ArrayList<Car> cars;
    private ArrayList<Flight> flights;

    public Agency(){
        this.id = new ID();
        this.clients = new ArrayList<Client>();
        this.hotels = new ArrayList<Hotel>();
        this.cars = new ArrayList<Car>();
        this.flights = new ArrayList<Flight>();
        this.travel_projects = new ArrayList<Travel_project>();
    }

    // Creation
    public void create_client(String name){
        this.clients.add(new Client(name, new ID()));
    }

    public void create_hotel(String name, int price){
        this.hotels.add(new Hotel(name, price, new ID()));
    }

    public void create_car(String name, int price){
        this.cars.add(new Car(name, price, new ID()));
    }

    public void create_flight(String date, String departure, String destination, int price){
        this.flights.add(new Flight(date, departure, destination, price, new ID()));
    }

    public void create_travel_project(Client client){
        this.travel_projects.add(new Travel_project(client, new ID()));
    }


    // Modification
    public void add_flight_ticket_to_travel_project(Travel_project travel_project, Flight flight, boolean is_discounted, boolean is_first_class){
        Flight_ticket flight_ticket = new Flight_ticket(flight, is_discounted, is_first_class, new ID());
        travel_project.add_flight_ticket(flight_ticket);

    }


    // Cherche
    public Client search_client_by_name(String name){
        ListIterator<Client> it = clients.listIterator();
        while(it.hasNext()){
            Client client = it.next();
            if(name.equals(client.get_name())){
                return client;
            }
        }
        return null;
    }



    //Get
    public ArrayList<Flight> get_flights(){
        return flights;
    }

    public ArrayList<Travel_project> get_travel_projects(){
        return travel_projects;
    }

}

