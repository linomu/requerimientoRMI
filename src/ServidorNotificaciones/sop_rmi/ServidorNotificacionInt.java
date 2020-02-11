/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.sop_rmi;

import ServidorNotificaciones.dto.ClsNotificacionDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lino Alejandro Munoz
 */
public interface ServidorNotificacionInt extends Remote{
    public void notificarAlMedico(ClsNotificacionDTO objNotificacion) throws RemoteException;
    
}
