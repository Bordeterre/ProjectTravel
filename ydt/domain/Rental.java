package ydt.domain;

// Value Object
public class Rental{
    private String name;
    private int price;

    public Rental(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String get_name(){
        return this.name;
    }

    public int get_price(){
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rental)) {
            return false;
        }
        Rental l = (Rental) o;
        if(this.get_name().equals(l.get_name()) && this.get_price() == l.get_price()){
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (get_name() + Integer.toString(get_price())).hashCode();
    }


}
