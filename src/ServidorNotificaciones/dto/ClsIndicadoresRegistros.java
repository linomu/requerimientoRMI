/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.dto;

import java.io.Serializable;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClsIndicadoresRegistros implements Serializable{
    private String numHabitacion, fecha, hora, puntuacion;

    public ClsIndicadoresRegistros(){
    }

    public ClsIndicadoresRegistros(String numHabitacion, String fecha, String hora, String puntuacion) {
        this.numHabitacion = numHabitacion;
        this.fecha = fecha;
        this.hora = hora;
        this.puntuacion = puntuacion;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
    
}
