package com.example.myaap_gestion_of_budget;

public class GroupClass {

    String Id , Admin , Title , Description ;


    public  GroupClass(){

    }

    public GroupClass(String title,String admin, String id, String description) {
        Id = id;
        Admin = admin;
        Title = title;
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
