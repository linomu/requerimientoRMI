/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.sop_rmi;

import ClienteHabitacion.sop_rmi.ClienteCallBackInt;
import ServidorAlertas.dto.ClsClienteDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lino Alejandro Munoz
 */
public interface ServidorAlertasInt extends Remote{
    /*El metodo registrarPaciente tiene como finalidad recibir como argumentos la referencia remotoa del callback y 
    objPacienteDto, del cual se utilizará la edad para generar la clave*/
    public boolean registrarPaciente(ClienteCallBackInt objPaciente,ClsClienteDTO objPacienteDto) throws RemoteException;
    /*El método enviarIndicadores tiene como finalidad recibir el paciente y verificar como se encuentran sus indicadores,
    si la cantidad de indicadores fuera de rango son 2 o más, entonces debe retornarse un mensaje al cliente y enviar el
    objeto junto con mas información al Servidor de Notificaciones*/
    public String enviarIndicadores(ClsClienteDTO objPaciente) throws RemoteException;
}
