package com.parcial.biblioteca;

import java.util.Objects;

public class Prestamo {
    private String cod;
    private String dni;

    public Prestamo(String cod, String dni) {
        this.cod = cod;
        this.dni = dni;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestamo)) return false;
        Prestamo prestamo = (Prestamo) o;
        return getCod().equals(prestamo.getCod()) && getDni().equals(prestamo.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod(), getDni());
    }
}
