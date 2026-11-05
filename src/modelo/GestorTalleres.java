package modelo;

import java.util.ArrayList;

public class GestorTalleres {
    private final ArrayList<Inscripcion> inscripciones = new ArrayList<>();

    public int obtenerCupos(String taller) {
        int cupos;
        switch (taller) {
            case "Fotografía": cupos = 3; break;
            case "Pintura": cupos = 4; break;
            case "Teatro": cupos = 2; break;
            default: throw new IllegalArgumentException("Selecciona un taller válido.");
        }
        // Restar los registros evita mantener dos estados que puedan descoordinarse.
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getTaller().equals(taller)) { cupos--; }
        }
        return cupos;
    }

    public void inscribir(String identificacion, String nombre, String taller) {
        if (identificacion == null || nombre == null || taller == null) {
            throw new IllegalArgumentException("Completa todos los campos.");
        }
        identificacion = identificacion.trim();
        nombre = nombre.trim();
        if (identificacion.isEmpty() || nombre.isEmpty()) {
            throw new IllegalArgumentException("Completa la identificación y el nombre.");
        }
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getIdentificacion().equals(identificacion)) {
                throw new IllegalArgumentException("Esta identificación ya está inscrita.");
            }
        }
        if (obtenerCupos(taller) == 0) {
            throw new IllegalArgumentException("No quedan cupos en el taller seleccionado.");
        }
        inscripciones.add(new Inscripcion(identificacion, nombre, taller));
    }

    public ArrayList<Inscripcion> obtenerInscripciones() {
        // Entregar una copia impide cambiar la lista sin pasar por las validaciones.
        return new ArrayList<>(inscripciones);
    }
}
