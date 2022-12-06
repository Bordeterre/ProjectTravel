package ydt.domain;

// Value Object
public class Flight{
    private ID id;
    private int price;
    private String date;
    private String departure;
    private String destination;

    public Flight(String date, String departure, String destination, int price, ID id){
        this.date = date;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.id = id;
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

    public ID get_id(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Flight)) {
            return false;
        }
        Flight p = (Flight) o;
        return get_id().equals(p.get_id());
    }  

}
