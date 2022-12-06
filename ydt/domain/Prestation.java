package ydt.domain;

// Entity
public class Prestation{
    private ID id;
    private Car car;
    private Hotel hotel;
    private boolean has_luxurious_prestation;

    // Constructor
    public Prestation(Car car, Hotel hotel, boolean has_luxurious_prestation){
        this.id = new ID();
        this.car = car;
        this.hotel = hotel;
        this.has_luxurious_prestation = has_luxurious_prestation;
    }

    // Setter
    public void assign_car(Car car){
        this.car = car;
    }

    public void assign_hotel(Hotel hotel, boolean has_luxurious_prestation){
        this.hotel = hotel;
        this.has_luxurious_prestation = has_luxurious_prestation;
    }

    // Getter
    public double get_price(){
        int price = car.get_price();
        if(has_luxurious_prestation){
            price += 1.2 * hotel.get_price();
        } else {
            price += hotel.get_price();
        }
        return price;
    }

    public Car get_car(){
        return car;
    }

    public Hotel get_hotel(){
        return hotel;
    }

    public boolean get_has_luxurious_prestation(){
        return has_luxurious_prestation;
    }

    public ID get_id(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prestation)) {
            return false;
        }
        Prestation p = (Prestation) o;
        return get_id().equals(p.get_id());
    } 
}