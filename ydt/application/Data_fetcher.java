package ydt.application;
import ydt.domain.*;

public class Data_fetcher {
    public static void fetch_data(Agency agency){
        // Fetch flights
        agency.enter_flight("now", "Paris", "Bordeaux", 50);
        agency.enter_flight("now", "Paris", "Camberra", 50);
        agency.enter_flight("now", "Paris", "Tokyo", 50);
        agency.enter_flight("now", "Paris", "Delhi", 50);

        agency.enter_flight("now", "Bordeaux", "Paris", 50);
        agency.enter_flight("now", "Bordeaux", "Tokyo", 50);
        agency.enter_flight("now", "Bordeaux", "Delhi", 50);

        agency.enter_flight("now", "Camberra", "Paris", 50);
        agency.enter_flight("now", "Camberra", "Tokyo", 50);

        agency.enter_flight("now", "Tokyo", "Bordeaux", 50);
        agency.enter_flight("now", "Tokyo", "Camberra", 50);

        agency.enter_flight("now", "Delhi", "Paris", 50);
        agency.enter_flight("now", "Delhi", "Bordeaux", 50);
        agency.enter_flight("now", "Delhi", "Camberra", 50);


        // fetch clients
        agency.enter_client("Marie");
        agency.enter_client("Nicolas");
        agency.enter_client("Louis");
        agency.enter_client("Léa");
        agency.enter_client("Bluwen");
        agency.enter_client("Anaëlle");
        
        // Fetch hotels
        agency.enter_hotel("Pont",0);
        agency.enter_hotel("1 étoile",10);
        agency.enter_hotel("3 étoiles",100);
        agency.enter_hotel("5 étoiles",1000);

        // Fetch cars
        agency.enter_car("Voiture invisible",0);
        agency.enter_car("Voiture à pédale",10);
        agency.enter_car("Voiture classique",100);
        agency.enter_car("Voiture volante",1000);

    }
}