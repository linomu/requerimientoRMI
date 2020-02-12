/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.sop_rmi;

import ServidorNotificaciones.dto.ClsNotificacionDTO;
import java.awt.TextArea;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorNotificacionImpl extends UnicastRemoteObject implements ServidorNotificacionInt{

    ClsNotificacionDTO objNotificacion;
    JLabel numHabitacion, nombre, apellidos, edad, horaAlerta, fechaAlerta, mensajeDoctorEnfermera;
    JTable tablaIndicadores, tablaRegistros;

    public ServidorNotificacionImpl(JLabel numHabitacion, JLabel nombre, JLabel apellidos, JLabel edad, JLabel horaAlerta, JLabel fechaAlerta, JLabel mensajeDoctorEnfermera, JTable tablaIndicadores, JTable tablaRegistros) throws RemoteException{
        super();
        this.numHabitacion = numHabitacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.horaAlerta = horaAlerta;
        this.fechaAlerta = fechaAlerta;
        this.mensajeDoctorEnfermera = mensajeDoctorEnfermera;
        this.tablaIndicadores = tablaIndicadores;
        this.tablaRegistros = tablaRegistros;
    }
    
    
    @Override
    public void notificarAlMedico(ClsNotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("***Datos Servidor Notificaiones***\nNombre: "+objNotificacion.getNombres());
        System.out.println("Cantidad de alertas: "+objNotificacion.getListaIndicadoresAlerta().size());
        this.objNotificacion = objNotificacion;
        
        imprimirInformacion(objNotificacion);
        
        
    }

    private void imprimirInformacion(ClsNotificacionDTO objNotificacion) {
        this.numHabitacion.setText(String.valueOf(objNotificacion.getNumeroHabitacion()));
        this.nombre.setText(String.valueOf(objNotificacion.getNombres()));
        this.apellidos.setText(String.valueOf(objNotificacion.getApellidos()));
        this.edad.setText(String.valueOf(objNotificacion.getEdad()));
        this.horaAlerta.setText(String.valueOf(objNotificacion.getHora()));
        this.fechaAlerta.setText(String.valueOf(objNotificacion.getFecha()));
        this.mensajeDoctorEnfermera.setText(String.valueOf(objNotificacion.getMensajeAlMedico()));
        
    }

        
    
}
