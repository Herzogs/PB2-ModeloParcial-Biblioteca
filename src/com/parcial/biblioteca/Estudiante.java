package com.parcial.biblioteca;

public class Estudiante {

    private String dni;
    private String nom;
    private tipoEstudiante tipo;

    public Estudiante(String dni, String nom, tipoEstudiante tipo) {
        this.dni = dni;
        this.nom = nom;
        this.tipo = tipo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public tipoEstudiante getTipo() {
        return tipo;
    }

    public void setTipo(tipoEstudiante tipo) {
        this.tipo = tipo;
    }
}
