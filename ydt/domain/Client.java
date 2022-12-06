package ydt.domain;

public class Client{
    private ID id;
    private String name;

    public Client(String name, ID id){
        this.name = name;
        this.id = id;
    }

    public String get_name(){
        return this.name;
    }

    public ID get_id(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client)) {
            return false;
        }
        Client p = (Client) o;
        return get_id().equals(p.get_id());
    }  
}