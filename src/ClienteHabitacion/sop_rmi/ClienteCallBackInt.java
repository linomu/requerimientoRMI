/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteHabitacion.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lino Alejandro Munoz
 */
public interface ClienteCallBackInt extends Remote{
   public void notificar(String mensaje) throws RemoteException;
}
