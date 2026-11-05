package controlador;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.GestorTalleres;
import modelo.Inscripcion;
import vista.VistaTalleres;

public class ControladorTalleres {
    private final GestorTalleres gestor;
    private final VistaTalleres vista;

    public ControladorTalleres(GestorTalleres gestor, VistaTalleres vista) {
        this.gestor = gestor;
        this.vista = vista;
        vista.btnInscribir.addActionListener(e -> inscribir());
        vista.cmbTaller.addActionListener(e -> actualizarCupos());
        actualizarCupos();
    }

    private void actualizarCupos() {
        String taller = (String) vista.cmbTaller.getSelectedItem();
        vista.lblCupos.setText(String.valueOf(gestor.obtenerCupos(taller)));
    }

    private void inscribir() {
        try {
            gestor.inscribir(vista.txtIdentificacion.getText(), vista.txtNombre.getText(),
                    (String) vista.cmbTaller.getSelectedItem());
            vista.modeloTabla.setRowCount(0);
            for (Inscripcion inscripcion : gestor.obtenerInscripciones()) {
                vista.modeloTabla.addRow(new Object[]{inscripcion.getIdentificacion(),
                        inscripcion.getNombre(), inscripcion.getTaller()});
            }
            actualizarCupos();
            JOptionPane.showMessageDialog(vista, "Inscripción registrada correctamente.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Revisa los datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaTalleres vista = new VistaTalleres();
            new ControladorTalleres(new GestorTalleres(), vista);
            vista.setVisible(true);
        });
    }
}
