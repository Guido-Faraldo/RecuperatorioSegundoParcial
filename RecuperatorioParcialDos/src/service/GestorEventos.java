package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import model.Evento;
import model.EventoMusical;

public class GestorEventos<T extends Evento> implements Inventariable<T> {
    
    List<T> eventos = new ArrayList<>();
    @Override
    public void agregar(T item) {
        if (item == null){
            throw new NullPointerException("No se deben agregar nulos a las listas");
        }
        eventos.add(item);
    }

    private void validarIndice(int indice){
        if(indice < 0 || indice >= eventos.size()){
            throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public T obtener(int indice) {
        validarIndice(indice);
        return eventos.get(indice);}

    @Override
    public void eliminar(int indice) {
        validarIndice(indice);
        eventos.remove(indice);
    }

    @Override
    public List<T> filtrar(Predicate<T> predicado) {
        List<T> toReturn = new ArrayList<>();
        for (T l: eventos){
            if(predicado.test(l)){
                toReturn.add(l);
            }
        }
        return toReturn;
    }

    @Override
    public List<T> buscarPorRango(LocalDate inicio, LocalDate fin) {
        return filtrar(T -> !T.getFecha().isBefore(inicio) && !T.getFecha().isAfter(fin));
    }

    @Override
    public void ordenarNatural() {
        ordenar((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public void ordenar(Comparator<T> comparador) {
        eventos.sort(comparador);
    }

    @Override
    public void guardarEnBinario(String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
            ObjectOutputStream salida = new ObjectOutputStream(archivo){
            }){
            
            salida.writeObject(eventos);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void cargarDesdeBinario(String path) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path)){
            }){
            
            eventos = (List<T>)input.readObject();
            
        } catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void guardarEnCSV(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write("id,nombre,especie,alimentacion\n");
            for (T e : eventos){
                bw.write(e.toCSV() + "\n");
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void cargarDesdeCSV(String path) {
        
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            
            bf.readLine();
            String linea;
            while ((linea = bf.readLine()) != null){
                eventos.add((T)EventoMusical.fromCSV(linea));
            }
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mostrarTodos() {
        for (T e: eventos){
            System.out.println(e);
        }
    }

    @Override
    public void limpiar() {
        eventos.clear();
    }
    
}
