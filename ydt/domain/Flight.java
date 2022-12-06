package ydt.domain;

// Value Object
public class Flight{
    private int price;
    private String date;
    private String departure;
    private String destination;

    public Flight(String date, String departure, String destination, int price){
        this.date = date;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }

    public String get_date(){
        return this.date;
    }

    public String get_departure(){
        return this.departure;
    }

    public String get_destination(){
        return this.destination;
    }

    public int get_price(){
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Flight)) {
            return false;
        }
        Flight f = (Flight) o;
        if(this.get_date().equals(f.get_date()) 
            && this.get_departure().equals(f.get_departure()) 
            && this.get_destination().equals(f.get_destination())
            && this.get_price() == f.get_price()
        ){
            return true;
        }
        return false;
    } 

    public int hashCode() {
        return (get_date() + get_departure() + get_destination() + Integer.toString(get_price())).hashCode();
    }

}
