package com.parcial.biblioteca;

import java.util.Objects;

public class Libro implements Fotocopiable{

    private String cod;
    private String nomb;
    private String aut;
    private String edit;
    private tipoEstudiante destino;
    private Integer cantCopias;

    public Libro(String cod, String nomb, String aut, String edit, tipoEstudiante dest) {
        this.cod = cod;
        this.nomb = nomb;
        this.aut = aut;
        this.edit = edit;
        this.destino = dest;
        this.cantCopias = 3;
    }

    public void incrementarCopia(){
        this.cantCopias++;
    }

    public Integer decrementarCopia(){
        if(this.cantCopias > 0){
            this.cantCopias--;
        }
        return this.cantCopias;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getAut() {
        return aut;
    }

    public void setAut(String aut) {
        this.aut = aut;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public Integer getCantCopias() {
        return cantCopias;
    }

    public void setCantCopias(Integer cantCopias) {
        this.cantCopias = cantCopias;
    }

    public tipoEstudiante getDestino() {
        return destino;
    }

    public void setDestino(tipoEstudiante destino) {
        this.destino = destino;
    }

    @Override
    public Boolean esFotocopiable() {
        return this.nomb.equals("Historia") || this.nomb.equals("Geografía") || this.nomb.equals("Cs Naturales") || this.nomb.equals("Cs Sociales") || this.nomb.equals("Quimica") || this.nomb.equals("Física");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return getCod().equals(libro.getCod()) && getNomb().equals(libro.getNomb()) && getAut().equals(libro.getAut()) && getEdit().equals(libro.getEdit()) && getDestino() == libro.getDestino() && getCantCopias().equals(libro.getCantCopias());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod(), getNomb(), getAut(), getEdit(), getDestino(), getCantCopias());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
