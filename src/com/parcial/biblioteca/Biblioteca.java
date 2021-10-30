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

    private Integer obtenerCantidadDePrestamosFotocopiablesPorDNI(String dni) {
        Integer c = 0;
        if (this.listaPrestamos.size() > 0) {
            for (Prestamo pres : this.listaPrestamos) {
                Libro lib = this.getLibroPorCodigo(pres.getCod());
                if (pres.getDni().equals(dni) && lib.esFotocopiable())
                    c++;
            }
        }
        return c;
    }

    private Libro getLibroPorCodigo(String cod) {
        Integer ind = 0;
        while (cod.equals(this.listaLibros.get(ind).getCod())) {
            ind++;
        }
        return this.listaLibros.get(ind);
    }

    public Boolean prestamoLibro(Libro libro, Estudiante alumno) throws NoMoreCopyException {
        Boolean est = false;
        Integer cantPrestamosAnteriores = this.obtenerCantidadDePrestamosPorDNI(alumno.getDni());
        Integer cantPrestamosFotocopiables = this.obtenerCantidadDePrestamosFotocopiablesPorDNI(alumno.getDni());
        Libro libroPrestar = this.getLibroPorCodigo(libro.getCod());
        if (libroPrestar.getCantCopias() == 0)
            throw new NoMoreCopyException("No existe más copias para el libro pedido");
        else if (cantPrestamosFotocopiables == 0 && cantPrestamosAnteriores < 2 && libroPrestar.getDestino().equals(alumno.getTipo())) {
            libroPrestar.setCantCopias(libroPrestar.getCantCopias() - 1);
            this.listaPrestamos.add(new Prestamo(libro.getCod(), alumno.getDni()));
            est = true;
        }
        return est;
    }

    private Integer getCantidadLibrosFotocopiables(List<Libro> libro) {
        Integer c = 0;
        if (this.listaPrestamos.size() > 0) {
            for (Libro lib : libro) {
                if (lib.esFotocopiable())
                    c++;
            }
        }
        return c;
    }

    public Boolean prestamoLibro(List<Libro> librosPrestar, Estudiante alumno) throws Exception {
        /*Boolean est = false;
        Integer cantPrestamos = this.obtenerCantidadDePrestamosPorDNI(alumno.getDni()) + librosPrestar.size() ;
        Integer cantPrestamosFotocopiables = this.obtenerCantidadDePrestamosFotocopiablesPorDNI(alumno.getDni())+this.getCantidadLibrosFotocopiables(librosPrestar);
        if(cantPrestamosFotocopiables > 1 || cantPrestamos > 3)
            throw new NoMoreCopyException("Se excedio el maximo permitido de prestamos fotocopiables o la maxima cantidad de prestamos anteriores");
        else{
            for (Libro lib: librosPrestar) {
                Libro aux = this.getLibroPorCodigo(lib.getCod());
                if(aux.getCantCopias() == 0)
                    throw new NoMoreCopyException("No existe copia para el libro solicitado");
                else{
                    if(aux.getDestino().equals(alumno.getTipo())){
                        this.
                    }
                }
            }*/
        Boolean est = false;
        try {
            for (int i = 0; i < librosPrestar.size() && !est; i++) {
                if (this.prestamoLibro(librosPrestar.get(0), alumno))
                    est = true;
            }
        } catch (Exception e) {
        }
        return est;
    }

}
