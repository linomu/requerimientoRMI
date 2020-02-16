/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas;

import ServidorAlertas.utilidades.*;
import ServidorNotificaciones.sop_rmi.ServidorNotificacionInt;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import ServidorAlertas.sop_rmi.*;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorDeObjetos {
    
    
    
    public static void main(String args[]) throws RemoteException{
        int numPuertoRMIRegistry = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de puerto"));
        String dirIpRMIRegistry = JOptionPane.showInputDialog("Ingrese el número de puerto o el nombre de servidor");
        /*System.out.println("Cual es la direccion Ip donde se encuentra el rmiregistry: ");
        dirIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el puerto donde escucha el rmiregistry: ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        */
        
        ServidorAlertasImpl ObjRemoto = new ServidorAlertasImpl();
        ObjRemoto.consultarReferenciaRemotaDeNotificacion(dirIpRMIRegistry, numPuertoRMIRegistry);
    
        
        try {
            ServidorAlertasImpl objRemoto = new ServidorAlertasImpl();
            UtilidadesRegistroS.arrancarNS(dirIpRMIRegistry, numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, dirIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoGestionPacientes");
            System.out.println("Objeto remoto registrado, esperando peticiones... ");
        } catch (Exception e) {
            System.out.println("No se puede arrancar el NS o registrar el objeto remoto");
        }
     }
}
