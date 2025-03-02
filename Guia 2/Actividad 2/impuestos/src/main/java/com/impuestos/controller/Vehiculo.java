/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.impuestos.controller;

/**
 *
 * @author Nadia Irina M Morales L
 */
class Vehiculo {
    private String marca;
    private String modelo;
    private int año;
    private double cilindraje;
    private double avaluoComercial;
    private String tipoUso; // "Particular" o "Público"

    public Vehiculo(String marca, String modelo, int año, double cilindraje, double avaluoComercial, String tipoUso) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.cilindraje = cilindraje;
        this.avaluoComercial = avaluoComercial;
        this.tipoUso = tipoUso;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAño() { return año; }
    public double getCilindraje() { return cilindraje; }
    public double getAvaluoComercial() { return avaluoComercial; }
    public String getTipoUso() { return tipoUso; }
}
