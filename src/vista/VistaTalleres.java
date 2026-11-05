package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaTalleres extends JFrame {
    private static final long serialVersionUID = 1L;
    public final JTextField txtIdentificacion = new JTextField();
    public final JTextField txtNombre = new JTextField();
    public final JComboBox<String> cmbTaller = new JComboBox<>(new String[]{"Fotografía", "Pintura", "Teatro"});
    public final JLabel lblCupos = new JLabel();
    public final JButton btnInscribir = new JButton("Inscribir");
    public final DefaultTableModel modeloTabla = new DefaultTableModel(new String[]{"Identificación", "Nombre", "Taller"}, 0) {
        private static final long serialVersionUID = 1L;
        @Override public boolean isCellEditable(int fila, int columna) { return false; }
    };
    public final JTable tabla = new JTable(modeloTabla);

    public VistaTalleres() {
        setTitle("Inscripción a talleres");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel principal = new JPanel(new BorderLayout(10, 15));
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.add(new JLabel("Identificación:")); formulario.add(txtIdentificacion);
        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Taller:")); formulario.add(cmbTaller);
        formulario.add(new JLabel("Cupos disponibles:")); formulario.add(lblCupos);
        formulario.add(new JLabel("")); formulario.add(btnInscribir);
        principal.add(formulario, BorderLayout.NORTH);
        principal.add(new JScrollPane(tabla), BorderLayout.CENTER);
        principal.add(new JLabel("Los registros se conservan únicamente mientras la aplicación está abierta."), BorderLayout.SOUTH);
        setContentPane(principal);
        setSize(650, 440);
        setLocationRelativeTo(null);
    }
}
