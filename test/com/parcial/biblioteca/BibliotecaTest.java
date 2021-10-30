package com.parcial.biblioteca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BibliotecaTest {

    private Biblioteca biblio;

    @Before
    public void seCreaLaBiblioteca() {
        this.biblio = new Biblioteca("Biblioteca Nacional");
    }

    @Test
    public void queSeAgregaUnLibroALaBiblioteca() {
        Assert.assertTrue(this.biblio.agregarLibro(new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO)));
    }

    @Test
    public void queSeAgregaUnLibroRepetidoALaBiblioteca() {
        this.biblio.agregarLibro(new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO));
        Assert.assertFalse(this.biblio.agregarLibro(new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO)));
    }

    @Test
    public void queSeIntenteAlquilarUnLibroDeDistintaDestino() throws NoMoreCopyException {
        this.biblio.agregarLibro(new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO));
        this.biblio.agregarLibro(new Libro("0002", "Cs Sociales", "none", "none", tipoEstudiante.PRIMARIO));
        Boolean valorEsperado = false;
        Libro lib = new Libro("0002", "Cs Sociales", "none", "none", tipoEstudiante.PRIMARIO);
        Estudiante est = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.UNIVERSITARIO);
        Boolean valorObtenido = this.biblio.prestamoLibro(lib, est);
        Assert.assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void queSeIntenteAlquilarUnLibroPeroElAlumnoYaHabiaLlegadoAlMaximoPermitidoPorEsoDaError() throws NoMoreCopyException {
        Libro lib1 = new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Libro lib2 = new Libro("0002", "Algebra", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Libro lib3 = new Libro("0003", "Fisica", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Estudiante est = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.UNIVERSITARIO);
        this.biblio.agregarLibro(lib1);
        this.biblio.agregarLibro(lib2);
        this.biblio.agregarLibro(lib3);
        this.biblio.prestamoLibro(lib1, est);
        this.biblio.prestamoLibro(lib2, est);
        Boolean valorObtenido = this.biblio.prestamoLibro(lib3, est);
        Boolean valorEsperado = false;
        Assert.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void queSeIntenteAlquilarUnLibroPeroNoHayCopiasSuficientesPorEsoDaExcepcion() {
        Exception myException = null;
        try {
            Libro lib1 = new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO);
            Estudiante cristian_feldman = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.UNIVERSITARIO);
            Estudiante juan_perez = new Estudiante("33557056", "Juan Perez", tipoEstudiante.UNIVERSITARIO);
            Estudiante carlos_lopez = new Estudiante("33557057", "Carlos López", tipoEstudiante.UNIVERSITARIO);
            Estudiante andrea_suarez = new Estudiante("33557058", "Andrea Suarez", tipoEstudiante.UNIVERSITARIO);
            this.biblio.agregarLibro(lib1);
            this.biblio.prestamoLibro(lib1, cristian_feldman);
            this.biblio.prestamoLibro(lib1, andrea_suarez);
            this.biblio.prestamoLibro(lib1, juan_perez);
            this.biblio.prestamoLibro(lib1, carlos_lopez);
        }catch (NoMoreCopyException e){
            myException = e;
        }finally {
            Assert.assertEquals(myException.getClass(),NoMoreCopyException.class);
        }
    }

    @Test
    public void queSeIntenteAlquilarUnLibroFotocopiablePeroElAlumnoYaHabiaAlquiladoUnoAnteriormentPorDaError() throws NoMoreCopyException {
        Libro lib1 = new Libro("0001", "Cs Naturales", "none", "none", tipoEstudiante.PRIMARIO);
        Libro lib2 = new Libro("0002", "Historia", "none", "none", tipoEstudiante.PRIMARIO);
        Estudiante est = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.PRIMARIO);
        this.biblio.agregarLibro(lib1);
        this.biblio.agregarLibro(lib2);
        this.biblio.prestamoLibro(lib1, est);
        Boolean valorObtenido = this.biblio.prestamoLibro(lib2, est);
        Assert.assertEquals(false,valorObtenido);
    }

    @Test
    public void queSeIntenteDevolverUnLibroYElLibroNoHayaSidoAlquiladoAnteriormentePorEsoDaError() throws NoMoreCopyException{
        Libro lib1 = new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Libro lib2 = new Libro("0002", "Física", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Estudiante cristian_feldman = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.UNIVERSITARIO);
        this.biblio.agregarLibro(lib1);
        this.biblio.prestamoLibro(lib1,cristian_feldman);
        Assert.assertEquals(false,this.biblio.devolverLibro(lib2,cristian_feldman));
    }

    @Test
    public void queSeIntenteDevolverUnLibroYElLibroSiHayaSidoAlquiladoAnteriormentePorEsoNoDaError() throws NoMoreCopyException{
        Libro lib1 = new Libro("0001", "Analisis Matematico", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Libro lib2 = new Libro("0002", "Física", "none", "none", tipoEstudiante.UNIVERSITARIO);
        Estudiante cristian_feldman = new Estudiante("33557055", "Cristian Feldman", tipoEstudiante.UNIVERSITARIO);
        this.biblio.agregarLibro(lib1);
        this.biblio.prestamoLibro(lib1,cristian_feldman);
        Assert.assertTrue(this.biblio.devolverLibro(lib1,cristian_feldman));
    }
}
