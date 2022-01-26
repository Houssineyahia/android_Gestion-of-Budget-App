package com.example.myaap_gestion_of_budget;

public class MenuClass {
    private String  nommenu;
    private  String Prix ;
    private String data;
    private String userexpence ;
    private String comment;

    MenuClass(String nom , String prix){
        this.nommenu =nom ;
        this.Prix = prix;
    }

    MenuClass(String nom , String prix,String data , String userexpence , String comment){
        this.nommenu =nom ;
        this.Prix = prix;
        this.data =data ;
        this.userexpence = userexpence;
        this.comment = comment;
    }


    void setNommenu(String menu){
        this.nommenu = menu;
    }

    void setPrix(String prix){
        this.Prix = prix;
    }

    void setdata(String data){
        this.nommenu = data;
    }

    void setuserexpence(String userexpence){
        this.userexpence = userexpence;
    }

    String getNommenu(){
        return this.nommenu;
    }

    String getPrix(){
        return this.Prix;
    }

    String getdata(){
        return this.data;
    }

    String getuserexpence(){
        return this.userexpence;
    }
    String getcomment(){
        return this.comment;
    }
    @Override
    public String toString() {
        return ' ' + nommenu;
    }
}