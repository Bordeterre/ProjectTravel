package ydt.domain;

// Value Object
public class Client{
    private String name;

    public Client(String name){
        this.name = name;
    }

    public String get_name(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client)) {
            return false;
        }
        Client c = (Client) o;
        if(this.get_name().equals(c.get_name())){
            return true;
        }
        return false;
    }

    public int hashCode() {
        return get_name().hashCode();
    }

}