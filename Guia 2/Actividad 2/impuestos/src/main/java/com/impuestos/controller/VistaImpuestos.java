/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.impuestos.controller;

/**
 *
 * @author nadir
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class VistaImpuestos extends JFrame implements ActionListener {
    private JTextField txtUsuario, txtMarca, txtModelo, txtAño, txtCilindraje, txtAvaluo;
    private JPasswordField txtContraseña;
    private JButton btnIngresar, btnCrearUsuario, btnCalcular;
    private JComboBox<String> cbTipoUso;
    private JPanel panelLogin, panelVehiculo;
    private JLabel lblResultado;
    private Controlador controlador;
    private Map<String, String> usuarios;

    public VistaImpuestos() {
        setTitle("Cálculo de Impuestos de un Carro");
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
        panelVehiculo.add(new JLabel("Avalúo Comercial:"), gbc);
        gbc.gridx = 1;
        txtAvaluo = new JTextField(10);
        panelVehiculo.add(txtAvaluo, gbc);

        gbc.gridx = 2;
        panelVehiculo.add(new JLabel("Tipo de Uso:"), gbc);
        gbc.gridx = 3;
        cbTipoUso = new JComboBox<>(new String[]{"Particular", "Público"});
        panelVehiculo.add(cbTipoUso, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        btnCalcular = new JButton("Calcular Impuesto");
        btnCalcular.addActionListener(this);
        panelVehiculo.add(btnCalcular, gbc);

        gbc.gridx = 1;
        lblResultado = new JLabel("Impuesto: ");
        panelVehiculo.add(lblResultado, gbc);

        panelVehiculo.setVisible(false);
        add(panelVehiculo, BorderLayout.CENTER);

        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setResultado(double impuesto) {
        lblResultado.setText("Impuesto: $" + impuesto);
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
            calcularImpuesto();
        }
    }

    private void calcularImpuesto() {
        try {
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            int año = Integer.parseInt(txtAño.getText().trim());
            double cilindraje = Double.parseDouble(txtCilindraje.getText().trim());
            double avaluo = Double.parseDouble(txtAvaluo.getText().trim());
            String tipoUso = (String) cbTipoUso.getSelectedItem();

            double impuesto = controlador.calcularImpuesto(marca, modelo, año, cilindraje, avaluo, tipoUso);
            setResultado(impuesto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}