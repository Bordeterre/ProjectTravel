package ydt.application;

import ydt.domain.*;

import java.util.*;

public class Application {
    public static void help(){
        System.out.println("0 : exit");
        System.out.println("1 : entrer un client dans le système");
        System.out.println("2 : entrer un vol dans le système");
        System.out.println("3 : entrer un hotel dans le système");
        System.out.println("4 : entrer une voiture dans le système");
        System.out.println("5 : créer un projet de voyage");
        System.out.println("6 : afficher un projet de voyage");
        System.out.println("7 : changer les vols d'un projet de voyage");
        System.out.println("6 : changer les services d'un projet de voyage");
    }

    // 1
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

    // 2
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

    // 3
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

    // 4
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

    // 5
    public static boolean create_flight_ticket(Agency agency, Travel_project travel_project){
        // search flight
        System.out.println("- A quelle date voulez vous partir ?");
        System.out.print("-> ");
        String date = IO.saisieChaine();

        System.out.println("- Quel est le lieu de départ du vol ?");
        System.out.print("-> ");
        String departure = IO.saisieChaine();

        System.out.println("- Quelle est la destination du vol ?");
        System.out.print("-> ");
        String destination = IO.saisieChaine();

        Flight flight = agency.search_flight(date, departure, destination);
        if(flight == null){
            System.out.println("Ce vol n'existe pas, veuillez recommencer");
            return false;
        }
        // create flight ticket
        System.out.println("Si vous possédez un ticket de réduction, tapez 1");
        System.out.print("-> ");
        String discount = IO.saisieChaine();
        
        System.out.println("Si vous désirez voyager en première classe, tapez 1");
        System.out.print("-> ");
        String first_class = IO.saisieChaine();

        agency.add_flight_ticket(travel_project, flight, discount.equals("1"), first_class.equals("1"));
        System.out.println("Le ticket de vol a bien été commandé");
        return true;
    }
    
    public static boolean create_prestation(Agency agency, Travel_project travel_project){
        System.out.println("- Quel est le nom de l'hotel que vous souhaitez réserver ?");
        System.out.print("-> ");
        String hotel_name = IO.saisieChaine();

        System.out.println("Si vous désirez un service de luxe à l'hotel, tapez 1");
        System.out.print("-> ");
        String luxurious = IO.saisieChaine();

        System.out.println("- Quel est le nom de la voiture que vous souhaitez réserver ?");
        System.out.print("-> ");
        String car_name = IO.saisieChaine();

        Prestation prestation = agency.add_prestation(travel_project, hotel_name, car_name, luxurious.equals("1"));
        if(prestation == null){
            System.out.println("L'hotel et/ou la voiture n'existent pas, veuillez recommencer");
            return false;
        }
        return true;
    }

    public static void create_travel_project(Agency agency){
        System.out.println("- Quel est le nom du client orchestrant ce projet de voyage ?");
        System.out.print("-> ");
        String name = IO.saisieChaine();
        Travel_project travel_project = agency.create_travel_project(name);
        if(travel_project == null){
            System.out.println("Ce client n'existe pas, veuillez recommencer");
            return;
        }

        System.out.println("- Combien de vols voulez vous commander ?");
        int nb_flights = IO.saisieEntier();
        for(int i = 0; i < nb_flights; i++){
            if(!create_flight_ticket(agency, travel_project)){
                return;
            }
        }

        System.out.println("- Quel service voulez vous réserver ? Simple (0), Standard (1), ou Luxueux (2)?");
        int nb_services = Math.max(0, Math.min(2,IO.saisieEntier()));
        for(int i = 0; i < nb_services; i++){
            if(!create_prestation(agency, travel_project)){
                return;
            }
        }
        System.out.println("L'ID de votre projet de voyage est : "+travel_project.get_id().get_id()+" . Veuillez le retenir");
        System.out.println("Ce voyage coutera : "+Double.toString(travel_project.get_price()));
    }

    // 6
    public static Travel_project search_travel_project(Agency agency){
        System.out.println("Quel est l'id de votre projet de voyage ?");
        System.out.print("-> ");
        String id = IO.saisieChaine();
        return agency.get_travel_project_by_id(id);
    }

    public static void display_travel_project(Agency agency){
        Travel_project travel_project = search_travel_project(agency);
        if(travel_project == null){
            System.out.println("Ce projet de voyage n'existe pas");
            return;
        }

        System.out.println("ID : " + travel_project.get_id().get_id());
        System.out.println("Client : " + travel_project.get_client().get_name());
        System.out.println("Vols : ");
        Iterator<Flight_ticket> itf =  travel_project.get_flight_tickets().iterator();
        while(itf.hasNext()){
            Flight_ticket flight_ticket = itf.next();
            Flight flight = flight_ticket.get_flight();
            System.out.print(" - Ticket ");
            if(flight_ticket.get_is_first_class()){
                System.out.print("première classe ");
            }
            if(flight_ticket.get_is_discounted()){
                System.out.print("réduit ");
            }
            System.out.println(". " + flight.get_departure() + "=>" + flight.get_destination() + ". ("+ flight.get_date() + ", " + flight_ticket.get_price() + "€‎)");
        }

        Iterator<Prestation> itp =  travel_project.get_prestations().iterator();
        while(itp.hasNext()){
            Prestation prestation = itp.next();
            System.out.print(" - Hotel : "+ prestation.get_hotel().get_name());
            if(prestation.get_has_luxurious_prestation()){
                System.out.print(" (luxurious)");
            }
            System.out.print(". Car : "+prestation.get_car().get_name());
            System.out.print(". (" + prestation.get_price() + "€‎)");
        }
    }

    //7
    public static void change_flight_ticket(Agency agency){
        Travel_project travel_project = search_travel_project(agency);
        if(travel_project == null){
            System.out.println("Ce projet de voyage n'existe pas");
            return;
        }

        System.out.println("Pour supprimer les tickets de vols existants, tapez 1");
        System.out.print("-> ");
        String supprimer = IO.saisieChaine();
        if(supprimer.equals("1")){agency.remove_all_flight_tickets(travel_project);}

        System.out.println("- Combien de vols voulez vous créer ?");
        int nb_flights = IO.saisieEntier();
        for(int i = 0; i < nb_flights; i++){
            if(!create_flight_ticket(agency, travel_project)){
                return;
            }
        }
    }

    //8
    public static void change_prestation(Agency agency){
        Travel_project travel_project = search_travel_project(agency);
        if(travel_project == null){
            System.out.println("Ce projet de voyage n'existe pas");
            return;
        }

        System.out.println("Pour supprimer les prestations existantes, tapez 1");
        System.out.print("-> ");
        String supprimer = IO.saisieChaine();
        if(supprimer.equals("1")){agency.remove_all_prestations(travel_project);}

        System.out.println("- Combien de prestations voulez vous ajouter ?");
        System.out.print("-> ");
        int nb_services = IO.saisieEntier();
        for(int i = 0; i < nb_services; i++){
            if(!create_prestation(agency, travel_project)){
                return;
            }
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
                case "6" : display_travel_project(agency); break;
                case "7" : change_flight_ticket(agency); break;
                case "8" : change_prestation(agency); break;

                default : help();
            }
        }
    }
}
