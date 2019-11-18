package com.fennec.allojib.entity;

public class Client {

    public int id;
    public String email;
    public String pass;
    public String nom;
    public String prenom;
    public String tel;
    public String adresse;
    public String ville;
    public int sexe;

    public Client() { }

    public Client(int id, String email, String pass, String nom, String prenom, String tel, String adresse, String ville, int sexe)
    {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.ville = ville;
        this.sexe = sexe;
    }

    public Client(String email, String pass, String nom, String prenom, String tel, String adresse, String ville, int sexe)
    {
        this.email = email;
        this.pass = pass;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.ville = ville;
        this.sexe = sexe;
    }
}
