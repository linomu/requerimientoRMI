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
    /*En otra practica, para registrar el paciente pasaba por argumento el numero de habitacion, ahora
      quiero intentar sacar ese numero de habitacion atraves del objPacienteDto, teniendoen cuenta que este
      implementa la interfaz serializable*/
    public boolean registrarPaciente(ClienteCallBackInt objPaciente,ClsClienteDTO objPacienteDto) throws RemoteException;
    public String enviarIndicadores(ClsClienteDTO objPaciente) throws RemoteException;
}
