package ydt.domain;

// Entity
public class Prestation{
    private ID id;
    private Rental car;
    private Rental hotel;
    private boolean has_luxurious_prestation;

    // Constructor
    public Prestation(Rental car, Rental hotel, boolean has_luxurious_prestation){
        this.id = new ID();
        this.car = car;
        this.hotel = hotel;
        this.has_luxurious_prestation = has_luxurious_prestation;
    }

    // Setter
    public void assign_car(Rental car){
        this.car = car;
    }

    public void assign_hotel(Rental hotel, boolean has_luxurious_prestation){
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

    public Rental get_car(){
        return car;
    }

    public Rental get_hotel(){
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