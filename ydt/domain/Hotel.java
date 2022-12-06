package ydt.domain;

// Value Object
public class Hotel{
    private String name;
    private ID id;
    private int price;

    
    public Hotel(String name, int price, ID id){
        this.name = name;
        this.price = price;
        this.id  = id;
    }

    public String get_name(){
        return this.name;
    }
    
    public int get_price(){
        return this.price;
    }

    public ID get_id(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hotel)) {
            return false;
        }
        Hotel p = (Hotel) o;
        return get_id().equals(p.get_id());
    }  
}
