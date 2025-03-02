package com.impuestos.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author Nadia Irina M Morales L
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaImpuestos vista = new VistaImpuestos();
            Controlador controlador = new Controlador(vista);
            vista.setControlador(controlador);
        });
    }
}
