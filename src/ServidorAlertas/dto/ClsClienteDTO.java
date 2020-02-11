/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.dto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClsClienteDTO implements Serializable {

    private int numHabitacion;
    private String nombres;
    private String apellidos;
    private float edad;
    private float frecuenciaCardiaca, presionArterial, frecuenciaRespiratoria, temperatura, saturacionOxigeno, sistolica, diastolica;
    

    public ClsClienteDTO(int numHabitacion, String nombres, String apellidos, float edad) {
        this.numHabitacion = numHabitacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    /*Este metodo tiene genera los indicadores para cada una de las variables*/
    public void GenerarIndicadores() {
        
        int frecuenciaCardiacaExcedente = 10;
        int sistolicaExcedente = 5;
        int diastolicaExcedente = 10;
        int frecueciaRespiratoriaExcedente = 5;
        float temperaturaExcedente = (float) 0.5;
        int saturacionExcedente = 5;
        
        

        this.saturacionOxigeno = (int) (Math.random() * (100) + (95 - saturacionExcedente));
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(this.saturacionOxigeno));
        /*Nacimiento - 6 Semanas*/
        if (this.edad > 0 && this.edad <= 0.115068) {
            System.out.println("Generando indicadores para : Nacimiento - 6 Semanas");
            this.frecuenciaCardiaca = (float) (Math.random() * (140 + frecuenciaCardiacaExcedente) + (120 - frecuenciaCardiacaExcedente));//Aleatorio entre 160 y 100
            this.sistolica = (float) (Math.random() * (100 + sistolicaExcedente) + (70 - sistolicaExcedente));
            this.diastolica = (float) (Math.random() * (68 + diastolicaExcedente) + (50 - diastolicaExcedente));
            this.presionArterial = this.sistolica / this.diastolica;
            this.frecuenciaRespiratoria = (float) (Math.random() * (45 + frecueciaRespiratoriaExcedente) + (40 - frecueciaRespiratoriaExcedente));
            this.temperatura = (float) (Math.random() * (38 + temperaturaExcedente) + (38 - temperaturaExcedente));
        }
        if(this.edad > 0.115068 && this.edad <=1){
            System.out.println("Generando indicadores para : 7 Semanas - 1 año");
            this.frecuenciaCardiaca = (float) (Math.random() * (130 + frecuenciaCardiacaExcedente) + (100 - frecuenciaCardiacaExcedente));//Aleatorio entre 160 y 100
            this.sistolica = (float) (Math.random() * (100 + sistolicaExcedente) + (70 - sistolicaExcedente));
            this.diastolica = (float) (Math.random() * (68 + diastolicaExcedente) + (50 - diastolicaExcedente));
            this.presionArterial = this.sistolica / this.diastolica;
            this.frecuenciaRespiratoria = (float) (Math.random() * (45 + frecueciaRespiratoriaExcedente) + (40 - frecueciaRespiratoriaExcedente));
            this.temperatura = (float) (Math.random() * (38 + temperaturaExcedente) + (38 - temperaturaExcedente));
        }
    }

   

    public float getSistolica() {
        return sistolica;
    }

    public void setSistolica(float sistolica) {
        this.sistolica = sistolica;
    }

    public float getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(float diastolica) {
        this.diastolica = diastolica;
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

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
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
