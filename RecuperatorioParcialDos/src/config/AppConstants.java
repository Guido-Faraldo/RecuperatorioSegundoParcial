package config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConstants {
    
    public static final Path PATH_ARCHIVOS = Paths.get("src/data/");
    public static final Path SERIAL = PATH_ARCHIVOS.resolve("eventos.dat");
    public static final Path CSV = PATH_ARCHIVOS.resolve("eventos.csv");
    
}
