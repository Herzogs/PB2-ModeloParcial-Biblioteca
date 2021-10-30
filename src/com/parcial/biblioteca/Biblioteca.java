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

    private Integer obtenerLibroPorCodigo(String cod) {
        Integer ind = 0;
        Boolean est = false;
        for (; ind < this.listaLibros.size() && !est; ind++) {
            if(this.listaLibros.get(ind).getCod().equals(cod))
                est = true;
        }
        return ind-1;
    }

    private Libro getLibroPorCodigo(String cod) {
        Integer ind = 0;
        Boolean est = false;
        for (; ind < this.listaLibros.size() && !est; ind++) {
            if(this.listaLibros.get(ind).getCod().equals(cod))
                est = true;
        }
        return this.listaLibros.get(ind-1);
    }

    public Boolean prestamoLibro(Libro libro, Estudiante alumno) throws NoMoreCopyException {
        Boolean est = false;
        Integer cantPrestamosAnteriores = this.obtenerCantidadDePrestamosPorDNI(alumno.getDni());
        Integer cantPrestamosFotocopiables = this.obtenerCantidadDePrestamosFotocopiablesPorDNI(alumno.getDni());
        Libro libroPrestar = this.getLibroPorCodigo(libro.getCod());
        if (libroPrestar.getCantCopias() == 0)
            throw new NoMoreCopyException("No existe más copias para el libro pedido");
        else if (cantPrestamosFotocopiables == 0 && cantPrestamosAnteriores < 2 && libroPrestar.getDestino().equals(alumno.getTipo())) {
            libroPrestar.decrementarCopia();
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

    public Boolean prestamoLibro(List<Libro> librosPrestar, Estudiante alumno) {
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
        } catch (NoMoreCopyException e) {
            est = false;
        }
        return est;
    }



    public Boolean devolverLibro (Libro libroADevolver, Estudiante alumno){
        Boolean enc = false;
        Integer i=0;
        for (; i < this.listaPrestamos.size() && !enc; i++) {
            Prestamo aux = this.listaPrestamos.get(i);
            if(libroADevolver.getCod().equals(aux.getCod())&&alumno.getDni().equalsIgnoreCase(aux.getDni())){
                enc = true;
            }
        }
        if (enc){
            this.listaPrestamos.remove(i);
            this.listaLibros.get(this.obtenerLibroPorCodigo(libroADevolver.getCod())).incrementarCopia();
        }
        return enc;
    }

    public Libro fotocopiarLibro(Libro libroFotocopiar, Estudiante alumno){
        Libro copia = null;
        if(libroFotocopiar.esFotocopiable()&&this.devolverLibro(libroFotocopiar,alumno)){
            copia = libroFotocopiar;
        }
        return copia;
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }
}
