/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteHabitacion.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ClienteCallBackImpl extends UnicastRemoteObject implements ClienteCallBackInt{


    JTextArea textAreaCallBack;
    public ClienteCallBackImpl() throws RemoteException {
        super();
    }
    
    public ClienteCallBackImpl(JTextArea textArea) throws RemoteException {
        super();
        this.textAreaCallBack = textArea;
    }

    
    @Override
    public void notificar(String mensaje) throws RemoteException {
       System.out.println("Mensaje enviado del servidor: " + mensaje);
       this.textAreaCallBack.setText("Call back: " +mensaje);
       
    }
    
}
