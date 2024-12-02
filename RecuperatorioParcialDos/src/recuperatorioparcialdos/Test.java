package recuperatorioparcialdos;

import config.AppConstants;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import model.EventoMusical;
import model.GeneroMusical;
import service.GestorEventos;

public class Test {

    public static void main(String[] args) {
        
        // Crear instancia del gestor de eventos
        GestorEventos<EventoMusical> gestor = new GestorEventos<>();
        
        
        // Crear algunos eventos musicales
        gestor.agregar(new EventoMusical(1, "Rock Fest", LocalDate.of(2024, 3, 15), "Queen Revival", GeneroMusical.ROCK));
        gestor.agregar(new EventoMusical(2, "Jazz Night", LocalDate.of(2024, 6, 20), "John Doe Quintet", GeneroMusical.JAZZ));
        gestor.agregar(new EventoMusical(3, "Pop Party", LocalDate.of(2024, 8, 5), "Taylor Tribute", GeneroMusical.POP));
        gestor.agregar(new EventoMusical(4, "Electronic Vibes", LocalDate.of(2024, 10, 12), "DJ Nova", GeneroMusical.ELECTRONICA));
        
        
        // Mostrar todos los eventos
        System.out.println("Lista inicial de eventos:");
        gestor.mostrarTodos();
        
        
        // Ordenar por fecha (orden natural)
        System.out.println("\nEventos ordenados por fecha:");
        gestor.ordenarNatural();
        gestor.mostrarTodos();
        
        
        // Ordenar por nombre de evento
        System.out.println("\nEventos ordenados por nombre:");
        
        gestor.ordenar((e1, e2) -> e1.getNombre().compareTo(e2.getNombre()));
        gestor.mostrarTodos();
        
        
        // Filtrar por género
        System.out.println("\nEventos de género ROCK:");
        List<EventoMusical> rockEvents = gestor.filtrar(e -> e.getGenero().equals(GeneroMusical.ROCK));
        rockEvents.forEach(System.out::println);
        
        
        // Filtrar por palabra clave en el nombre
        System.out.println("\nEventos que contienen 'Night' en el nombre:");
        List<EventoMusical> nightEvents = gestor.filtrar(e -> e.getNombre().contains("Night"));
        nightEvents.forEach(System.out::println);
        
        
        // Buscar por rango de fechas
        System.out.println("\nEventos entre el 01/01/2024 y el 31/07/2024:");
        List<EventoMusical> dateRangeEvents = gestor.buscarPorRango(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 7, 31));
        dateRangeEvents.forEach(System.out::println);
        
        
        // Guardar y cargar en formato binario
        System.out.println("\nGuardando y cargando eventos en binario...");
        gestor.guardarEnBinario(AppConstants.SERIAL.toString());
        gestor.limpiar(); // Vaciar el gestor
        gestor.cargarDesdeBinario(AppConstants.SERIAL.toString());
        gestor.mostrarTodos();
        
        
        // Guardar y cargar en formato CSV
        System.out.println("\nGuardando y cargando eventos en CSV...");
        gestor.guardarEnCSV(AppConstants.CSV.toString());
        gestor.limpiar(); // Vaciar el gestor
        gestor.cargarDesdeCSV(AppConstants.CSV.toString());
        gestor.mostrarTodos();
        
        
        // Filtro dinámico usando Predicate
        System.out.println("\nFiltrar eventos dinámicamente (artista contiene 'DJ'):");
        Predicate<EventoMusical> filtroArtista = ((EventoMusical e) -> e.getArtista().contains("DJ"));
        List<EventoMusical> filtroDinamico = gestor.filtrar(filtroArtista);
        filtroDinamico.forEach(System.out::println);
        
        
    }
    
}
