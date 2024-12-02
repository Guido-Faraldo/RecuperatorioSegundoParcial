package service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import model.Evento;

public interface Inventariable<T extends Evento> {
    
    void agregar(T item);
    
    T obtener(int indice);
    
    void eliminar(int indice);
    
    List<T> filtrar(Predicate<T> predicado);
    
    List<T> buscarPorRango(LocalDate inicio, LocalDate fin);
    
    void ordenarNatural();
    
    void ordenar(Comparator<T> comparador);
    
    void guardarEnBinario(String path);
    
    void limpiar();
    
    void cargarDesdeBinario(String path);
    
    void guardarEnCSV(String path);
    
    void cargarDesdeCSV(String path);
    
    void mostrarTodos();
}
