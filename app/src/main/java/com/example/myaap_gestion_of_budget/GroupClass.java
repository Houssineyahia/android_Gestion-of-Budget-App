package com.example.myaap_gestion_of_budget;

public class GroupClass {
    private String  groupName;
    private  String Creator ;
    private  String Id ;

    GroupClass(String nom , String creator , String Id){
        this.groupName =nom ;
        this.Creator = creator;
        this.Id = Id;
    }

    GroupClass(String nom , String creator){

        this.groupName =nom ;
        this.Creator = creator;
    }

    void setgroupName(String groupName){
        this.groupName = groupName;
    }

    void setCreator(String Creator){
        this.Creator = Creator;
    }

    String getgroupName(){
        return this.groupName;
    }

    String getCreator(){
        return this.Creator;
    }

    String getId() {return this.Id; }

    @Override
    public String toString() {
        return ' ' + this.Id;
    }
}
