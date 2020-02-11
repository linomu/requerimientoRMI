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

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorNotificacionImpl extends UnicastRemoteObject implements ServidorNotificacionInt{

    ClsNotificacionDTO objNotificacion;
    JLabel textArea;
    
    public ServidorNotificacionImpl(JLabel textArea) throws RemoteException{
        super();
        this.textArea = textArea;
        
    }
    @Override
    public void notificarAlMedico(ClsNotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("***Datos Servidor Notificaiones***\nNombre: "+objNotificacion.getNombres());
        System.out.println("Cantidad de alertas: "+objNotificacion.getListaIndicadoresAlerta().size());
        this.objNotificacion = objNotificacion;
        
        this.textArea.setText(String.valueOf(objNotificacion.getNumeroHabitacion()));
        
        
    }

        
    
}
