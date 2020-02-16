/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.dto;

import ServidorNotificaciones.dto.ClsIndicadoresAlerta;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class GestionAlertaIndicadores implements Serializable{
   /*Este método tienen como finalidad recibir el objPaciente, revisar los indicadores a través de sus variables y 
       retornar una lista con que contiene en forma de objetos las alertas que no están dentro del rango establecido*/
    
    private ArrayList<ClsIndicadoresAlerta> listaIndicadoresAlerta = new ArrayList<>();
    private ClsClienteDTO objPaciente;

    public GestionAlertaIndicadores(ClsClienteDTO objPaciente) {
        this.objPaciente = objPaciente;
    }
    
    
    public ArrayList<ClsIndicadoresAlerta> llenarListaConIndicadores() {
        

        if (objPaciente.getSaturacionOxigeno() < 95 || objPaciente.getSaturacionOxigeno() > 100) {
            ClsIndicadoresAlerta objIndicadorSaturacion = new ClsIndicadoresAlerta("Saturacion de Oxigeno", String.valueOf(objPaciente.getSaturacionOxigeno()));
            listaIndicadoresAlerta.add(objIndicadorSaturacion);
            System.out.println("Alerta de Saturaciòn");
        }
        //Edad Nacimiento - 6 Semanas
        if (objPaciente.getEdad() > 0 && objPaciente.getEdad() <= 0.115068) {
            llenarIndicadoresNacimiento6Semanas();
        }

        //Edad 6 Semanas - 1 año
        if (objPaciente.getEdad() > 0.115068 && objPaciente.getEdad() <= 1) {
            llenarIndicadores6Semanas1Semanas();

        }
        //Edad 1 año - 2 año
        if (objPaciente.getEdad() > 1 && objPaciente.getEdad() <= 2) {
            llenarIndicadores1anio2anio();

        }
        //Edad 2 año - 6 año
        if (objPaciente.getEdad() > 2 && objPaciente.getEdad() <= 6) {
            llenarIndicadores2anio6anios();

        }
        //Edad 6 año - 13 años
        if (objPaciente.getEdad() > 6 && objPaciente.getEdad() <= 13) {
            llenarIndicadores6anios13anios();

        }
        //Edad 13 año - 16 años
        if (objPaciente.getEdad() > 13 && objPaciente.getEdad() <= 16) {
            llenarIndicadores13anios16anios();

        }

        //Edad 16 años o mas
        if (objPaciente.getEdad() > 16) {
           llenarIndicadoresMas16anios();

        }

        return listaIndicadoresAlerta;

    }

    private void llenarIndicadoresNacimiento6Semanas() {
        if (objPaciente.getFrecuenciaCardiaca() < 120 || objPaciente.getFrecuenciaCardiaca() > 140) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 70 || objPaciente.getSistolica() > 100 || objPaciente.getDiastolica() < 50 || objPaciente.getDiastolica() > 68) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 40 || objPaciente.getFrecuenciaRespiratoria() > 45) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() != 38) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

    }

    private void llenarIndicadores6Semanas1Semanas() {
        if (objPaciente.getFrecuenciaCardiaca() < 100 || objPaciente.getFrecuenciaCardiaca() > 130) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 84 || objPaciente.getSistolica() > 106 || objPaciente.getDiastolica() < 56 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 || objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 || objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

    private void llenarIndicadores1anio2anio() {
        if (objPaciente.getFrecuenciaCardiaca() < 100 || objPaciente.getFrecuenciaCardiaca() > 120) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 98 || objPaciente.getSistolica() > 106 || objPaciente.getDiastolica() < 58 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 || objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 || objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

    private void llenarIndicadores2anio6anios() {
        if (objPaciente.getFrecuenciaCardiaca() < 80 || objPaciente.getFrecuenciaCardiaca() > 120) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 99 || objPaciente.getSistolica() > 112 || objPaciente.getDiastolica() < 64 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 || objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 || objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

    private void llenarIndicadores6anios13anios() {
        if (objPaciente.getFrecuenciaCardiaca() < 80 || objPaciente.getFrecuenciaCardiaca() > 100) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 104 || objPaciente.getSistolica() > 124 || objPaciente.getDiastolica() < 64 || objPaciente.getDiastolica() > 86) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 || objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37 || objPaciente.getTemperatura() > 37.5) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

    private void llenarIndicadores13anios16anios() {
        if (objPaciente.getFrecuenciaCardiaca() < 70 || objPaciente.getFrecuenciaCardiaca() > 80) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 118 || objPaciente.getSistolica() > 132 || objPaciente.getDiastolica() < 70 || objPaciente.getDiastolica() > 82) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 || objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() != 37) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

    private void llenarIndicadoresMas16anios() {
         if (objPaciente.getFrecuenciaCardiaca() < 60 || objPaciente.getFrecuenciaCardiaca() > 80) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 110 || objPaciente.getSistolica() > 140 || objPaciente.getDiastolica() < 70 || objPaciente.getDiastolica() > 90) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 || objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Respiratoria", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 36.2 || objPaciente.getTemperatura() > 37.2) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
    }

}
