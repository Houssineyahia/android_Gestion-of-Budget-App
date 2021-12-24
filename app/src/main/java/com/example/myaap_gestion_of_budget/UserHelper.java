package com.example.myaap_gestion_of_budget;

public class UserHelper {
          public String fname,lname,username, email,phone,pswd,dateBirth;

    public UserHelper(String fname, String lname, String username, String email, String phone, String pswd ,String dateBirth) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.pswd = pswd;
        this.dateBirth=dateBirth;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPswd() {
        return pswd;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}
