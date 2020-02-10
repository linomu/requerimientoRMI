/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteHabitacion.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClienteCallBackImpl extends UnicastRemoteObject implements ClienteCallBackInt{

    public ClienteCallBackImpl() throws RemoteException {
        super();
    }

    
    @Override
    public String notificar(String mensaje) throws RemoteException {
       System.out.println("Mensaje enviado del servidor: " + mensaje);
       return "Mensaje retorno: " + mensaje; 
    }
    
}
