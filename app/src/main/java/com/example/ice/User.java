package com.example.ice;

public class User {

    public String fname, lname, dob, email, password,cpasswrod,phonecountry, phonenum, health, addres, contactcountry,econtact, ename, erelation, diabte, bp,blood_group;

    public User(){

    }



    public User(String fname, String lname, String dob, String blood_group, String email, String password, String cpasswrod, String phonecountry, String phonenum, String health, String addres, String contactcountry, String econtact, String ename, String erelation, String diabte, String bp) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.blood_group = blood_group;
        this.email = email;
        this.password = password;
        this.cpasswrod = cpasswrod;
        this.phonecountry = phonecountry;
        this.phonenum = phonenum;
        this.health = health;
        this.addres = addres;
        this.contactcountry = contactcountry;
        this.econtact = econtact;
        this.ename = ename;
        this.erelation = erelation;
        this.diabte = diabte;
        this.bp = bp;
    }
}