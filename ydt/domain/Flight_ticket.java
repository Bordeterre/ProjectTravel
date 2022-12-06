package ydt.domain;

// Value Object
public class Flight_ticket{
    private Flight flight;
    private boolean is_discounted;
    private boolean is_first_class;

    // Constructor
    public Flight_ticket(Flight flight, boolean is_discounted, boolean is_first_class){
        this.flight = flight;
        this.is_discounted = is_discounted;
        this.is_first_class = is_first_class;
    }

    // Getter
    public double get_price(){
        int price = flight.get_price();
        if(is_discounted){
            price *= 0.8;
        }
        if(is_first_class){
            price *= 1.3;
        }
        return price;
    }

    public Flight get_flight(){
        return flight;
    }

    public boolean get_is_discounted(){
        return is_discounted;
    }

    public boolean get_is_first_class(){
        return is_first_class;
    }

    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Flight_ticket)) {
            return false;
        }
        Flight_ticket f = (Flight_ticket) o;
        if(this.get_flight().equals(f.get_flight()) 
            && this.get_is_discounted() == .f.get_is_discounted()
            && this.get_is_first_class() == .f.get_is_first_class()
        ){
            return true;
        }
        return false;
    }  
}