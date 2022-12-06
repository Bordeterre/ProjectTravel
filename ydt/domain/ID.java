package ydt.domain;

import java.util.*;

public class ID{
    
    protected  String id;
    protected static ArrayList<String> assigned_IDs;
    
    public ID(){
        if(assigned_IDs == null){
            assigned_IDs = new ArrayList<String>();
        }

        id = randomID();
        while(assigned_IDs.contains(id)){
            id = randomID();
        }
        
        assigned_IDs.add(id);
    }

    protected String randomID(){
        return UUID.randomUUID().toString().substring(0,20);
    }

    public String get_id(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ID)) {
            return false;
        }
        ID p = (ID) o;
        return get_id().equals(p.get_id());
    } 
}
