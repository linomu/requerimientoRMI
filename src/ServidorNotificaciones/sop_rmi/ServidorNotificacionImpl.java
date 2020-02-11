/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.sop_rmi;

import ServidorNotificaciones.dto.ClsNotificacionDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorNotificacionImpl extends UnicastRemoteObject implements ServidorNotificacionInt{

    public ServidorNotificacionImpl() throws RemoteException{
        super();
    }
    @Override
    public void notificarAlMedico(ClsNotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("***Datos Servidor Notificaiones***\nNombre: "+objNotificacion.getNombres());
        System.out.println("Cantidad de alertas: "+objNotificacion.getListaIndicadoresAlerta().size());
    }
    
}
