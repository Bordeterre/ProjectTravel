package ydt.infrastructure;

import ydt.domain.*;

import java.util.*;

public class Agency_repository_in_memory implements Agency_repository{
    static Set<Agency> memory;

    public Agency_repository_in_memory(){
        memory = new HashSet<Agency>();
    }

    public Agency create_agency(){
        Agency agency = new Agency();
        return agency;
    }
    
    public Agency find_agency_by_id(String id){
        Iterator<Agency> it = memory.iterator();
        while(it.hasNext()){
            Agency agency = it.next();
            if(agency.get_id().get_id().equals(id)){
                return agency;
            }
        }
        return null;
    }

    public void save(Agency agency){
        memory.add(agency);
    }
}
