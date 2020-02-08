/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.sop_rmi;

import ClienteHabitacion.sop_rmi.ClienteCallBackImpl;
import ClienteHabitacion.sop_rmi.ClienteCallBackInt;
import ServidorAlertas.dto.ClsClienteDTO;
import ServidorNotificaciones.dto.ClsIndicadoresAlerta;
import ServidorNotificaciones.dto.ClsNotificacionDTO;
import ServidorNotificaciones.sop_rmi.ServidorNotificacionInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import ServidorNotificaciones.utilidades.*;
/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorAlertasImpl extends UnicastRemoteObject implements ServidorAlertasInt{

    private Hashtable<Integer, ClienteCallBackInt> tablaPacientes;
    /*Enlace con el Servidor de Notificaciones*/
    private ServidorNotificacionInt ORServidorNotificaciones;
    /*El servidor de notificaciones debe recibir la información del paciente, y la o las alertas que se han generado*/
    
    
    public ServidorAlertasImpl() throws RemoteException{
        super();
        tablaPacientes = new Hashtable<>();
    }
    @Override
    public boolean registrarPaciente(ClienteCallBackInt objPaciente, ClsClienteDTO objPacienteDto) throws RemoteException {
        System.out.println("Invocando al método registrar usuario desde el servidor");
        tablaPacientes.put(objPacienteDto.getNumHabitacion(), objPaciente);
        return true;
    }

    @Override
    public String enviarIndicadores(ClsClienteDTO objPaciente) throws RemoteException {
         String respuestaCallback = "";
        ClienteCallBackInt objPacienteCallBack = new ClienteCallBackImpl();
        boolean isWrong = false;
        if (objPaciente.getEdad() >= 13 && objPaciente.getEdad() < 16) {
            if (objPaciente.getFrecuenciaCardiaca() <= 70 || objPaciente.getFrecuenciaCardiaca() >= 80) {
                objPacienteCallBack = tablaPacientes.get(objPaciente.getNumHabitacion());
                isWrong = true;

            }
        } 
        if (isWrong) {
            respuestaCallback = objPacienteCallBack.notificar("El paciente que se encuntra en la habitacion " + objPaciente.getNumHabitacion() + " presenta una frecuencia cardiaca de " + objPaciente.getFrecuenciaCardiaca() + " la cual se encuentra fuera del rango normal");
        }
        
        
        
        //Tengo que validar si se debe o no enviar la informacion al servidor de notificaciones
        
        ClsNotificacionDTO objMensajeNotificacion;
        if(false){
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");

            Date date = new Date();
            String hora = formatterTime.format(date);
            String fecha= formatterDate.format(date);

            objMensajeNotificacion = new ClsNotificacionDTO(objPaciente.getNumHabitacion(), objPaciente.getEdad(),objPaciente.getNombres(),objPaciente.getApellidos(),"Mensaje al medico si es 1 o 2 alertas",fecha,hora);
            //Esta parte debe ser dinamica y depende de la cantidad de indicadores que resultaron mal
            ClsIndicadoresAlerta objIndicadores = new ClsIndicadoresAlerta("frecuencia cardicada","145");
            objMensajeNotificacion.setListaIndicadoresAlerta(objIndicadores);
            //Aqui debo leer del archivo txt los registros que tiene almacenados al mismo tiempo que voy alimentnado el objMensajeNotificacion
            
          //Enviar el mensaje
            ORServidorNotificaciones.notificarAlMedico(objMensajeNotificacion);
            
            
        }
   
        return respuestaCallback;
    }
    
       public void consultarReferenciaRemotaDeNotificacion(String direccionIpRmiRegistry, int numPuertoRmiRegistry){
        this.ORServidorNotificaciones = (ServidorNotificacionInt)UtilidadesRegistroC.obtenerObjRemoto(direccionIpRmiRegistry, numPuertoRmiRegistry, "ObjetoRemotoNotificaciones");
    }
    
}
