package com.parcial.biblioteca;

import java.util.LinkedList;
import java.util.List;

public class Biblioteca {

    private String nombre;
    private List<Libro> listaLibros;
    private List<Prestamo> listaPrestamos;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.listaLibros = new LinkedList<>();
        this.listaPrestamos = new LinkedList<>();
    }

    public Boolean agregarLibro(Libro nuevo) {
        Boolean est = false;
        if (!this.listaLibros.contains(nuevo)) {
            this.listaLibros.add(nuevo);
            est = true;
        }
        return est;
    }

    private Integer obtenerCantidadDePrestamosPorDNI(String dni) {
        Integer c = 0;
        if (this.listaPrestamos.size() > 0) {
            for (Prestamo pres : this.listaPrestamos) {
                if (pres.getDni().equals(dni))
                    c++;
            }
        }
        return c;
    }
}
