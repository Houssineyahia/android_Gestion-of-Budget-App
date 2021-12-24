package com.example.myaap_gestion_of_budget;

public class MenuClass {
    private String  nommenu;
    private  String Prix ;

    MenuClass(String nom , String prix){
        this.nommenu =nom ;
        this.Prix = prix;
    }

    void setNommenu(String menu){
        this.nommenu = menu;
    }

    void setPrix(String prix){
        this.Prix = prix;
    }

    String getNommenu(){
        return this.nommenu;
    }

    String getPrix(){
        return this.Prix;
    }

    @Override
    public String toString() {
        return ' ' + nommenu;
    }
}