package model;

import java.io.Serializable;
import java.time.LocalDate;

public class EventoMusical extends Evento implements Comparable<EventoMusical>, Serializable{
    
    private static final long SerialVersionUID = 1L;
    private String artista;
    private GeneroMusical genero;

    public EventoMusical(int id, String nombre, LocalDate fecha, String artista, GeneroMusical genero) {
        super(id, nombre, fecha);
        this.artista = artista;
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "EventoMusical{" + super.toString() + "artista=" + artista + ", genero=" + genero + '}';
    }

    @Override
    public int compareTo(EventoMusical e) {
        return this.getFecha().compareTo(e.getFecha());
    }
    
    public String toCSV(){
        return super.toCSV() + "," + artista + "," + genero.toString();
    }
    
    public static EventoMusical fromCSV(String eventoCSV){
        EventoMusical e = null;
        String[] valores = eventoCSV.split(",");
            if (valores.length == 5){
                int id = Integer.parseInt(valores[0]);
                String nombre = valores[1];
                LocalDate fecha = LocalDate.parse(valores[2]);
                String artista = valores[3];
                GeneroMusical genero = GeneroMusical.valueOf(valores[4]);
                e = new EventoMusical(id, nombre, fecha, artista, genero);
            }
        return e;
    }
}
