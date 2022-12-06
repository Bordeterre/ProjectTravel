package ydt.application;

import ydt.domain.*;


public class Application {
    public static void help(){
        System.out.println("0 : exit");
        System.out.println("1 : entrer un client dans le système");
        System.out.println("2 : entrer un vol dans le système");
        System.out.println("3 : entrer un hotel dans le système");
        System.out.println("4 : entrer une voiture dans le système");
        System.out.println("5 : créer un projet de voyage");
        System.out.println("6 : modifier un projet de voyage");
    }

    // Créer value objects simples
    public static void enter_client(Agency agency){
        System.out.println("- Quel est le nom de ce client ?");
        System.out.print("-> ");
        String name = IO.saisieChaine();

        if(agency.enter_client(name)){
            System.out.println("- " + name + " créé avec succès");
        } else {
            System.out.println("- " + name + " existe déja");
        }
    }

    public static void enter_flight(Agency agency){
        System.out.println("- Quelle est la date du vol ?");
        System.out.print("-> ");
        String date = IO.saisieChaine();

        System.out.println("- Quel est le lieu de départ du vol ?");
        System.out.print("-> ");
        String departure = IO.saisieChaine();

        System.out.println("- Quelle est la destination du vol ?");
        System.out.print("-> ");
        String destination = IO.saisieChaine();

        System.out.println("- Quelle est le prix du vol (en euros) ?");
        System.out.print("-> ");
        int price = IO.saisieEntier();


        if(agency.enter_flight(date, departure, destination, price)){
            System.out.println("- Le vol a été créé avec succès");
        } else {
            System.out.println("- Le vol existe déja");
        }
    }

    public static void enter_hotel(Agency agency){
        System.out.println("- Quel est le nom de l'hotel ?");
        System.out.print("-> ");
        String name = IO.saisieChaine();

        System.out.println("- Quelle est le prix de l'hotel (en euros) ?");
        System.out.print("-> ");
        int price = IO.saisieEntier();


        if(agency.enter_hotel(name, price)){
            System.out.println("- " + name + " créé avec succès");
        } else {
            System.out.println("- " + name + " existe déja");
        }
    }

    public static void enter_car(Agency agency){
        System.out.println("- Quel est le nom de la voiture ?");
        System.out.print("-> ");
        String name = IO.saisieChaine();

        System.out.println("- Quelle est le prix de la voiture (en euros) ?");
        System.out.print("-> ");
        int price = IO.saisieEntier();


        if(agency.enter_car(name, price)){
            System.out.println("- " + name + " créé avec succès");
        } else {
            System.out.println("- " + name + " existe déja");
        }
    }

    public static void create_travel_project(Agency agency){
        System.out.println("- Quel est le nom du client orchestrant ce projet de voyage ?");
        System.out.print("-> ");
        String name = IO.saisieChaine();
        if(!agency.create_travel_project(name)){
            System.out.println("Ce client n'existe pas");
            return;
        }

        System.out.println("- Combien de vols voulez vous commander ?");
        int nb_flights = Math.max(0, IO.saisieEntier());
        for(int i = 0; i < nb_flights; i++){
            // search flight
            // if exist, create flight ticket
        }

        System.out.println("- Quel service voulez vous réserver ? Simple (0), Standard (1), ou Luxueux (2)?");
        int nb_services = Math.max(0, Math.min(2,IO.saisieEntier()));
        for(int i = 0; i < nb_services; i++){

            // create car
            // create hotel
        }
    }




    public static void main(String args[]){
        help();
        Agency agency = new Agency();
        while(true){
            System.out.print("\n-> ");
            String choice = IO.saisieChaine();

            switch(choice){
                case "0" : System.exit(0);
                case "1" : enter_client(agency); break;
                case "2" : enter_flight(agency); break;
                case "3" : enter_hotel(agency); break;
                case "4" : enter_car(agency); break;
                case "5" : create_travel_project(agency); break;

                default : help();
            }
        }
    }
}
