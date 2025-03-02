/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.impuestos.controller;

/**
 *
 * @author Nadia Irina M Morales L
 */
class Controlador {
    private VistaImpuestos vista;
    private CalculadoraImpuestos calculadora;

    public Controlador(VistaImpuestos vista) {
        this.vista = vista;
        this.calculadora = new CalculadoraImpuestos();
    }

    public double calcularImpuesto(String marca, String modelo, int año, double cilindraje, double avaluo, String tipoUso) {
        Vehiculo vehiculo = new Vehiculo(marca, modelo, año, cilindraje, avaluo, tipoUso);
        return calculadora.calcularImpuesto(vehiculo);
    }
}