/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.impuestos.controller;

/**
 *
 * @author Nadia Irina M Morales L
 */
class CalculadoraImpuestos {
    private static final double TARIFA_PARTICULAR = 0.02; // 2% para vehículos particulares
    private static final double TARIFA_PUBLICO = 0.03;    // 3% para vehículos públicos
    private static final double DESCUENTO_ANTIGUEDAD = 0.10; // 10% de descuento para vehículos con más de 10 años
    private static final double RECARGO_CILINDRAJE = 0.05; // 5% de recargo para cilindraje > 2000 cc

    public double calcularImpuesto(Vehiculo vehiculo) {
        double tarifaBase = (vehiculo.getTipoUso().equalsIgnoreCase("Particular")) ? TARIFA_PARTICULAR : TARIFA_PUBLICO;
        double impuestoBase = vehiculo.getAvaluoComercial() * tarifaBase;

        // Aplicar descuento por antigüedad (si aplica)
        int antiguedad = java.time.Year.now().getValue() - vehiculo.getAño();
        if (antiguedad > 10) {
            impuestoBase -= impuestoBase * DESCUENTO_ANTIGUEDAD;
        }

        // Aplicar recargo por cilindraje (si aplica)
        if (vehiculo.getCilindraje() > 2000) {
            impuestoBase += impuestoBase * RECARGO_CILINDRAJE;
        }

        return impuestoBase;
    }
}
