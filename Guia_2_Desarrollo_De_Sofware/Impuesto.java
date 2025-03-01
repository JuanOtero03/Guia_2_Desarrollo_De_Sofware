import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class VistaImpuestos extends JFrame implements ActionListener {
    private JTextField txtUsuario, txtMarca, txtModelo, txtAño, txtCilindraje, txtTiempo;
    private JPasswordField txtContraseña;
    private JButton btnIngresar, btnCrearUsuario, btnCalcular, btnAgregar;
    private JComboBox<String> cbTipoUso;
    private JPanel panelLogin, panelVehiculo;
    private JLabel lblResultado;
    private Controlador controlador;
    private Map<String, String> usuarios;

    public VistaImpuestos() {
        setTitle("Parqueadero");
        setLayout(new BorderLayout());
        usuarios = new HashMap<>();
        usuarios.put("admin", "1234");
        usuarios.put("usuario", "1234");

        // Panel de Login
        panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelLogin.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        txtUsuario = new JTextField(10);
        panelLogin.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelLogin.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtContraseña = new JPasswordField(10);
        panelLogin.add(txtContraseña, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(this);
        panelLogin.add(btnIngresar, gbc);

        gbc.gridx = 1;
        btnCrearUsuario = new JButton("Crear Usuario");
        btnCrearUsuario.addActionListener(this);
        panelLogin.add(btnCrearUsuario, gbc);

        add(panelLogin, BorderLayout.NORTH);

        // Panel de Vehículo
        panelVehiculo = new JPanel(new GridBagLayout());

        gbc.gridx = 0; gbc.gridy = 0;
        panelVehiculo.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        txtMarca = new JTextField(10);
        panelVehiculo.add(txtMarca, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 3;
        txtModelo = new JTextField(10);
        panelVehiculo.add(txtModelo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelVehiculo.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        txtAño = new JTextField(10);
        panelVehiculo.add(txtAño, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(new JLabel("Cilindraje:"), gbc);
        gbc.gridx = 3;
        txtCilindraje = new JTextField(10);
        panelVehiculo.add(txtCilindraje, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelVehiculo.add(new JLabel("Tiempo (horas):"), gbc);
        gbc.gridx = 1;
        txtTiempo = new JTextField(10);
        panelVehiculo.add(txtTiempo, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(new JLabel("Tipo de Vehículo:"), gbc);
        gbc.gridx = 3;
        cbTipoUso = new JComboBox<>(new String[]{"Particular", "Público"});
        panelVehiculo.add(cbTipoUso, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        btnCalcular = new JButton("Calcular Tarifa");
        btnCalcular.addActionListener(this);
        panelVehiculo.add(btnCalcular, gbc);

        gbc.gridx = 1;
        lblResultado = new JLabel("Tarifa: ");
        panelVehiculo.add(lblResultado, gbc);

        gbc.gridx = 2;
        btnAgregar = new JButton("Seleccionar Parqueadero");
        btnAgregar.addActionListener(this);
        panelVehiculo.add(btnAgregar, gbc);

        panelVehiculo.setVisible(false);
        add(panelVehiculo, BorderLayout.CENTER);

        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setResultado(double tarifa) {
        lblResultado.setText("Tarifa: $" + tarifa);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            String usuario = txtUsuario.getText().trim();
            String contraseña = new String(txtContraseña.getPassword()).trim();

            if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contraseña)) {
                panelLogin.setVisible(false);
                panelVehiculo.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCalcular) {
            calcularTarifa();
        } else if (e.getSource() == btnAgregar) {
            String input = JOptionPane.showInputDialog("Ingrese el número de parqueadero (1-10):");
            try {
                int numero = Integer.parseInt(input);
                controlador.agregarAlParqueadero(numero);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Número inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calcularTarifa() {
        try {
            int tiempo = Integer.parseInt(txtTiempo.getText().trim());
            if (tiempo < 0) throw new NumberFormatException();

            int tarifaPorHora = cbTipoUso.getSelectedItem().equals("Particular") ? 5000 : 10000;
            int tarifaTotal = tiempo * tarifaPorHora;

            setResultado(tarifaTotal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un tiempo válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}

class Controlador {
    private VistaImpuestos vista;
    private boolean[] parqueadero;

    public Controlador(VistaImpuestos vista) {
        this.vista = vista;
        this.parqueadero = new boolean[10];
    }

    public void agregarAlParqueadero(int numero) {
        if (numero < 1 || numero > 10) {
            JOptionPane.showMessageDialog(null, "Número de parqueadero inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!parqueadero[numero - 1]) {
            parqueadero[numero - 1] = true;
            JOptionPane.showMessageDialog(null, "Vehículo agregado en el parqueadero " + numero);
        } else {
            JOptionPane.showMessageDialog(null, "El parqueadero " + numero + " está ocupado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public class Impuesto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaImpuestos vista = new VistaImpuestos();
            Controlador controlador = new Controlador(vista);
            vista.setControlador(controlador);
        });
    }
}
