/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.dto;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClsIndicadoresRegistros {
    private String fecha, hora, puntuacion;

    public ClsIndicadoresRegistros(String fecha, String hora, String puntuacion) {
        this.fecha = fecha;
        this.hora = hora;
        this.puntuacion = puntuacion;
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
