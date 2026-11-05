package pruebas;
import modelo.GestorTalleres;

public class PruebasModelo {
    public static void main(String[] args) {
        GestorTalleres g = new GestorTalleres();
        verificar(g.obtenerCupos("Fotografía") == 3 && g.obtenerCupos("Pintura") == 4 && g.obtenerCupos("Teatro") == 2);
        g.inscribir(" 001 ", " Matias ", "Teatro");
        verificar(g.obtenerCupos("Teatro") == 1);
        verificar(g.obtenerInscripciones().get(0).getIdentificacion().equals("001"));
        verificar(g.obtenerInscripciones().get(0).getNombre().equals("Matias"));
        rechazar(g, "001", "Matias", "Pintura");
        verificar(g.obtenerCupos("Pintura") == 4);
        g.inscribir("002", "Ana", "Teatro");
        rechazar(g, "003", "Luis", "Teatro");
        verificar(g.obtenerCupos("Teatro") == 0 && g.obtenerInscripciones().size() == 2);
        rechazar(g, "", "Luis", "Fotografía");
        rechazar(g, "003", "  ", "Fotografía");
        rechazar(g, "003", "Luis", "Otro");
        rechazar(g, null, "Luis", "Fotografía");
        g.obtenerInscripciones().clear();
        verificar(g.obtenerInscripciones().size() == 2);
        for (int i = 0; i < 3; i++) g.inscribir("F" + i, "Persona", "Fotografía");
        for (int i = 0; i < 4; i++) g.inscribir("P" + i, "Persona", "Pintura");
        rechazar(g, "F3", "Persona", "Fotografía");
        rechazar(g, "P4", "Persona", "Pintura");
        verificar(g.obtenerInscripciones().size() == 9);
        verificar(new GestorTalleres().obtenerInscripciones().isEmpty());
        System.out.println("OK: cupos, duplicados globales, ceros iniciales, rechazos sin cambios y nueva sesión.");
    }
    private static void verificar(boolean condicion) { if (!condicion) throw new AssertionError(); }
    private static void rechazar(GestorTalleres g, String id, String nombre, String taller) {
        int cantidad = g.obtenerInscripciones().size();
        try { g.inscribir(id, nombre, taller); throw new AssertionError("Aceptó inscripción inválida"); }
        catch (IllegalArgumentException esperado) { }
        verificar(cantidad == g.obtenerInscripciones().size());
    }
}
