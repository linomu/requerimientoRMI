/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.dto;

import java.io.Serializable;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClsClienteDTO implements Serializable{
    
	private int numHabitacion;
	private String nombres;
	private String apellidos;
        private int edad;
        private float frecuenciaCardiaca, presionArterial,frecuenciaRespiratoria, temperatura, saturacionOxigeno;

    public ClsClienteDTO(int numHabitacion, String nombres, String apellidos, int edad) {
        this.numHabitacion = numHabitacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }

        
    public ClsClienteDTO(int numHabitacion, String nombres, String apellidos, int edad, float frecuenciaCardiaca, float presionArterial, float frecuenciaRespiratoria, float temperatura, float saturacionOxigeno) {
        this.numHabitacion = numHabitacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionArterial = presionArterial;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;
    }
       
    
    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(float frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public float getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(float presionArterial) {
        this.presionArterial = presionArterial;
    }

    public float getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(float frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(float saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    
	
    
}
