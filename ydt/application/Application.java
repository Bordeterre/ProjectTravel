package ydt.application;

import ydt.domain.*;
import ydt.infrastructure.*;

import java.util.*;

public class Application {
    public static void help(){
        System.out.println("0 : exit");
        System.out.println("1 : afficher vos projets de voyage");
        System.out.println("2 : afficher  la liste des vols");
        System.out.println("3 : afficher  la liste des hotels");
        System.out.println("4 : afficher  la liste des voitures");
        System.out.println("5 : créer un projet de voyage");
        System.out.println("6 : changer les vols d'un projet de voyage");
        System.out.println("7 : changer les services d'un projet de voyage");
        System.out.println();
        System.out.println("10 : créer une nouvelle agence");
        System.out.println("11 : sauvegarder l'agence en mémoire");
        System.out.println("12 : charger une agence de la mémoire");

    }

    // 1
    public static void display_travel_projects(Agency agency){
        Iterator<Travel_project> itt = agency.get_travel_projects().iterator();
        while(itt.hasNext()){
            System.out.println();
            display_travel_project(itt.next());
        }
    }

    public static void display_travel_project(Travel_project travel_project){
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

    // 2
    public static void show_flights(Agency agency){
        Iterator<Flight> itf = agency.get_flights().iterator();
        while(itf.hasNext()){
            Flight flight = itf.next();
            System.out.println(" - " + flight.get_departure() + "=>" + flight.get_destination() + " ("+ flight.get_date() + ", " + flight.get_price() + "€‎)");
        }
    }

    // 3
    public static void show_hotels(Agency agency){
        Iterator<Rental> ith = agency.get_hotels().iterator();
        while(ith.hasNext()){
            Rental hotel = ith.next();
            System.out.println(" - " + hotel.get_name() + ". (" + hotel.get_price() + "€‎)");
        }
    }

    // 4
    public static void show_cars(Agency agency){
        Iterator<Rental> ith = agency.get_cars().iterator();
        while(ith.hasNext()){
            Rental car = ith.next();
            System.out.println(" - " + car.get_name() + ". (" + car.get_price() + "€‎)");
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
            
            return false;
        }
        return true;
    }

    public static void create_travel_project(Agency agency, String client_name){
        Travel_project travel_project = agency.create_travel_project(client_name);

        System.out.println("- Combien de vols voulez vous commander ?");
        System.out.print("-> ");
        int nb_flights = IO.saisieEntier();
        for(int i = 0; i < nb_flights; i++){
            while(!create_flight_ticket(agency, travel_project)){
                System.out.println("Ce vol n'existe pas, veuillez recommencer");
            }
        }

        System.out.println("- Quel service voulez vous réserver ? Simple (0), Standard (1), ou Luxueux (2)?");
        int nb_services = Math.max(0, Math.min(2,IO.saisieEntier()));
        for(int i = 0; i < nb_services; i++){
            while(!create_prestation(agency, travel_project)){
                System.out.println("L'hotel et/ou la voiture n'existent pas, veuillez recommencer");
            }
        }
        System.out.println("L'ID de votre projet de voyage est : "+travel_project.get_id().get_id()+" . Veuillez le retenir");
        System.out.println("Ce voyage coutera : "+Double.toString(travel_project.get_price()));
    }

    //6
    public static Travel_project search_travel_project(Agency agency){
        System.out.println("Quel est l'id de votre projet de voyage ?");
        System.out.print("-> ");
        String id = IO.saisieChaine();
        return agency.get_travel_project_by_id(id);
    }

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

    //7
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

    //10
    public static Agency create_agency(Agency_repository memory){
        Agency agency = memory.create_agency();
        Data_fetcher.fetch_data(agency);
        System.out.println("L'id de la nouvelle agence est : " + agency.get_id().get_id());
        return agency;
    }

    //11
    public static void save_agency(Agency_repository memory, Agency agency){
        memory.save(agency);
         System.out.println("L'id de l'agence sauvegardée est : " + agency.get_id().get_id());
    }

    //12
    public static Agency load_agency(Agency_repository memory, Agency previous_agency){
        System.out.println("Quel est l'id de l'agence que vous voulez charger ?");
        System.out.print("-> ");
        String id = IO.saisieChaine();
        Agency agency = memory.find_agency_by_id(id);

        if(agency == null){
            System.out.println("Aucune agence n'ayant cet id n'existe");
            return previous_agency;
        }
        System.out.println("L'agence a bien été chargée");
        return agency;
    }


    public static void main(String args[]){
        Agency_repository_in_memory memory = new Agency_repository_in_memory();
        Agency agency = create_agency(memory);
        String client_name = "Nicolas";
        help();
        
        while(true){
            System.out.print("\n-> ");
            String choice = IO.saisieChaine();

            switch(choice){
                case "0" : System.exit(0);
                case "1" : display_travel_projects(agency); break;
                case "2" : show_flights(agency); break;
                case "3" : show_hotels(agency); break;
                case "4" : show_cars(agency); break;
                case "5" : create_travel_project(agency, client_name); break;
                case "6" : change_flight_ticket(agency); break;
                case "7" : change_prestation(agency); break;

                case "10" : agency = create_agency(memory); break;
                case "11" : save_agency(memory, agency); break;
                case "12" : agency = load_agency(memory, agency); break;
                default : help();
            }
        }
    }
}
