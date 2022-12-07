package ydt.domain;

public interface Agency_repository{
    public Agency create_agency();

    public Agency find_agency_by_id(String id);

    public void save(Agency agency);
}
