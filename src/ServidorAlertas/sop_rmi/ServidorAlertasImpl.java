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
import ServidorNotificaciones.sop_rmi.ServidorNotificacionImpl;
import ServidorNotificaciones.sop_rmi.ServidorNotificacionInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import ServidorNotificaciones.utilidades.*;
import java.util.ArrayList;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorAlertasImpl extends UnicastRemoteObject implements ServidorAlertasInt {

    private Hashtable<Integer, ClienteCallBackInt> tablaPacientes;
    /*Enlace con el Servidor de Notificaciones*/
 /*El errror estaba con el static! porque tiene que ser static y no normal*/
    private static ServidorNotificacionInt ORServidorNotificaciones;

    /*El servidor de notificaciones debe recibir la información del paciente, y la o las alertas que se han generado*/

    public ServidorAlertasImpl() throws RemoteException {
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
        

        ClsNotificacionDTO objMensajeNotificacion;
    
        //La variable listaConIndicadores me retorna un arreglo que tiene las alertas generadas con base en los indicadores    
        ArrayList<ClsIndicadoresAlerta> listaConIndicadores = llenarListaConIndicadores(objPaciente);
        //Tengo que validar si se debe o no enviar la informacion al servidor de notificaciones y esto debe ser con base en la cantidad de alertas
        if (listaConIndicadores.size() > 1) {
            
            /*Realizar la persistencia*/
            String mensaje = "La enfermera debe revisar al paciente";
            if(listaConIndicadores.size()>=3){
                mensaje = "El medico y la enfermera deben revisar al paciente";
            }
            
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");

            Date date = new Date();
            String hora = formatterTime.format(date);
            String fecha = formatterDate.format(date);
            
            objMensajeNotificacion = new ClsNotificacionDTO(objPaciente.getNumHabitacion(), objPaciente.getEdad(),objPaciente.getNombres(),objPaciente.getApellidos(),mensaje,fecha,hora);
            //Le paso al mensajeNotificacion las alertas generadas
            objMensajeNotificacion.setListaIndicadoresAlerta(listaConIndicadores);
             
            //Aqui debo leer del archivo txt los registros que tiene almacenados al mismo tiempo que voy alimentnado el objMensajeNotificacion
            
          //Enviar el mensaje*/
            ORServidorNotificaciones.notificarAlMedico(objMensajeNotificacion);

        }
        
        /*Parte del Callback*/
        String respuestaCallback = "";
        ClienteCallBackInt objPacienteCallBack = new ClienteCallBackImpl();
        boolean isWrong = false;
        //Si hay una alerta se debe notificar al paciente
        if (listaConIndicadores.size()>1) {
            objPacienteCallBack = tablaPacientes.get(objPaciente.getNumHabitacion());
            isWrong = true;

        }
        if (isWrong) {
            respuestaCallback = objPacienteCallBack.notificar("El paciente que se encuntra en la habitacion " + objPaciente.getNumHabitacion() + " tiene  " + listaConIndicadores.size() + " indicadores que se encuentran fuera del rango normal");
        }
        
        return respuestaCallback;
    }

    public void consultarReferenciaRemotaDeNotificacion(String direccionIpRmiRegistry, int numPuertoRmiRegistry) {
        try {
            System.out.println("consulté la referencia remota de notificacion");
            this.ORServidorNotificaciones = (ServidorNotificacionInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRmiRegistry, numPuertoRmiRegistry, "ObjetoRemotoNotificaciones");

        } catch (Exception e) {
            System.out.println("Tenemos un error!");
        }

    }

    /*Este método tienen como finalidad recibir el objPaciente, revisar los indicadores a través de sus variables y 
       retornar una lista con que contiene en forma de objetos las alertas que no están dentro del rango establecido*/
    private ArrayList<ClsIndicadoresAlerta> llenarListaConIndicadores(ClsClienteDTO objPaciente) {
        ArrayList<ClsIndicadoresAlerta> listaIndicadoresAlerta = new ArrayList<>();
        
        if(objPaciente.getSaturacionOxigeno()<95){
            ClsIndicadoresAlerta objIndicadorSaturacion = new ClsIndicadoresAlerta("Saturacion de Oxigeno", String.valueOf(objPaciente.getSaturacionOxigeno()));
            listaIndicadoresAlerta.add(objIndicadorSaturacion);
            System.out.println("Alerta de Saturaciòn");
        }
        //Edad Nacimiento - 6 Semanas
        if (objPaciente.getEdad()>0 && objPaciente.getEdad()<=0.115068) {
            if(objPaciente.getFrecuenciaCardiaca()<120 || objPaciente.getFrecuenciaCardiaca()>140){
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if( objPaciente.getSistolica()<70 || objPaciente.getSistolica()>100 || objPaciente.getDiastolica()<50 || objPaciente.getDiastolica()>68 ){
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if(objPaciente.getFrecuenciaRespiratoria()<40 && objPaciente.getFrecuenciaRespiratoria()>45){
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if(objPaciente.getTemperatura() != 38){
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }
            
        }
        return listaIndicadoresAlerta;

    }

}
