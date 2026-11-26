package laboratorio09_ejercicio01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * Laboratorio 09 - Ejercicio 01: Formulario de registro de estudiantes (NetBeans).
 * Componentes: 1 JFrame, 3 JPanel, 5 JLabel, 3 JTextField, 2 JComboBox, 3 JButton.
 */
public class FormularioEstudiante extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color AZUL = new Color(0, 51, 120);

    private final JTextField txtCodigo = new JTextField(20);
    private final JTextField txtNombres = new JTextField(20);
    private final JTextField txtEdad = new JTextField(20);
    private final JComboBox<String> cboCarrera = new JComboBox<>(new String[] {
            "Seleccione...", "Ingeniería de Software", "Ingeniería de Sistemas",
            "Ingeniería Industrial", "Ingeniería Civil", "Administración" });
    private final JComboBox<String> cboGenero = new JComboBox<>(new String[] {
            "Seleccione...", "Femenino", "Masculino", "Otro" });

    public FormularioEstudiante() {
        super("Registro de Estudiantes");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                salir();
            }
        });
        setContentPane(crearPanelPrincipal());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /** Construye el contenido (separado del JFrame para facilitar pruebas). */
    JPanel crearPanelPrincipal() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Panel de datos: 5 etiquetas + 3 campos de texto + 2 combos
        JPanel datos = new JPanel(new GridLayout(5, 2, 10, 12));
        datos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AZUL, 2), "Registro de Estudiantes",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 16), AZUL));
        datos.add(new JLabel("Código:"));
        datos.add(txtCodigo);
        datos.add(new JLabel("Nombres y Apellidos:"));
        datos.add(txtNombres);
        datos.add(new JLabel("Edad:"));
        datos.add(txtEdad);
        datos.add(new JLabel("Carrera Profesional:"));
        datos.add(cboCarrera);
        datos.add(new JLabel("Género:"));
        datos.add(cboGenero);

        // Panel de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnSalir = new JButton("Salir");
        botones.add(btnRegistrar);
        botones.add(btnLimpiar);
        botones.add(btnSalir);

        btnRegistrar.addActionListener(e -> registrar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnSalir.addActionListener(e -> salir());

        principal.add(datos, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);
        return principal;
    }

    private void registrar() {
        String codigo = txtCodigo.getText().trim();
        String nombres = txtNombres.getText().trim();
        String edadTxt = txtEdad.getText().trim();

        if (codigo.isEmpty() || nombres.isEmpty() || edadTxt.isEmpty()) {
            error("Todos los campos de texto son obligatorios.");
            return;
        }
        if (cboCarrera.getSelectedIndex() == 0 || cboGenero.getSelectedIndex() == 0) {
            error("Seleccione la carrera profesional y el género.");
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(edadTxt);
        } catch (NumberFormatException ex) {
            error("La edad debe ser un número entero.");
            return;
        }
        if (edad < 0) {
            error("La edad no puede ser negativa.");
            return;
        }

        String mensaje = "ESTUDIANTE REGISTRADO\n\n"
                + "Código: " + codigo + "\n"
                + "Nombres y apellidos: " + nombres + "\n"
                + "Edad: " + edad + "\n"
                + "Carrera: " + cboCarrera.getSelectedItem() + "\n"
                + "Género: " + cboGenero.getSelectedItem();
        JOptionPane.showMessageDialog(this, mensaje, "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombres.setText("");
        txtEdad.setText("");
        cboCarrera.setSelectedIndex(0);
        cboGenero.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }

    private void salir() {
        int r = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?",
                "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void error(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Se usa el aspecto por defecto
        }
        SwingUtilities.invokeLater(() -> new FormularioEstudiante().setVisible(true));
    }
}
