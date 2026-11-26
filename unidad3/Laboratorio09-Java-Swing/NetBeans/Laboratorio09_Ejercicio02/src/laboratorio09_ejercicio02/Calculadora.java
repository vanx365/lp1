package laboratorio09_ejercicio02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * Laboratorio 09 - Ejercicio 02: Calculadora básica (NetBeans).
 * Componentes: 1 JFrame, 4 JPanel, 3 JLabel, 3 JTextField, 6 JButton.
 */
public class Calculadora extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color AZUL = new Color(0, 51, 120);

    private final JTextField txtNumero1 = new JTextField(10);
    private final JTextField txtNumero2 = new JTextField(10);
    private final JTextField txtResultado = new JTextField(10);

    public Calculadora() {
        super("Calculadora");
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

        // Entradas y resultado
        JPanel campos = new JPanel(new GridLayout(3, 2, 10, 12));
        campos.add(new JLabel("Número 1:"));
        campos.add(txtNumero1);
        campos.add(new JLabel("Número 2:"));
        campos.add(txtNumero2);
        campos.add(new JLabel("Resultado:"));
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(220, 235, 255));
        campos.add(txtResultado);

        // Operaciones
        JPanel operaciones = new JPanel(new GridLayout(2, 2, 8, 8));
        JButton btnSuma = crearBotonOperacion("+");
        JButton btnResta = crearBotonOperacion("−");
        JButton btnMulti = crearBotonOperacion("×");
        JButton btnDiv = crearBotonOperacion("÷");
        operaciones.add(btnSuma);
        operaciones.add(btnResta);
        operaciones.add(btnMulti);
        operaciones.add(btnDiv);

        JPanel centro = new JPanel(new BorderLayout(20, 0));
        centro.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AZUL, 2), "Calculadora Básica",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 16), AZUL));
        centro.add(campos, BorderLayout.CENTER);
        centro.add(operaciones, BorderLayout.EAST);

        // Limpiar y salir
        JPanel inferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnSalir = new JButton("Salir");
        inferior.add(btnLimpiar);
        inferior.add(btnSalir);

        btnSuma.addActionListener(e -> calcular('+'));
        btnResta.addActionListener(e -> calcular('-'));
        btnMulti.addActionListener(e -> calcular('*'));
        btnDiv.addActionListener(e -> calcular('/'));
        btnLimpiar.addActionListener(e -> limpiar());
        btnSalir.addActionListener(e -> salir());

        principal.add(centro, BorderLayout.CENTER);
        principal.add(inferior, BorderLayout.SOUTH);
        return principal;
    }

    private JButton crearBotonOperacion(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("SansSerif", Font.BOLD, 20));
        return b;
    }

    private void calcular(char operacion) {
        String t1 = txtNumero1.getText().trim();
        String t2 = txtNumero2.getText().trim();

        if (t1.isEmpty() || t2.isEmpty()) {
            error("Ingrese los dos números.");
            return;
        }
        double a;
        double b;
        try {
            a = Double.parseDouble(t1.replace(',', '.'));
            b = Double.parseDouble(t2.replace(',', '.'));
        } catch (NumberFormatException ex) {
            error("Los valores ingresados deben ser numéricos.");
            return;
        }

        double resultado;
        switch (operacion) {
            case '+':
                resultado = a + b;
                break;
            case '-':
                resultado = a - b;
                break;
            case '*':
                resultado = a * b;
                break;
            default:
                if (b == 0) {
                    error("No se puede dividir entre cero.");
                    return;
                }
                resultado = a / b;
                break;
        }
        txtResultado.setText(String.valueOf(resultado));
    }

    private void limpiar() {
        txtNumero1.setText("");
        txtNumero2.setText("");
        txtResultado.setText("");
        txtNumero1.requestFocus();
    }

    private void salir() {
        int r = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?",
                "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void error(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Se usa el aspecto por defecto
        }
        SwingUtilities.invokeLater(() -> new Calculadora().setVisible(true));
    }
}
