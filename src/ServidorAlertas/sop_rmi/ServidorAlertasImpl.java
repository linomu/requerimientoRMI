/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorAlertas.sop_rmi;

import ClienteHabitacion.sop_rmi.ClienteCallBackImpl;
import ClienteHabitacion.sop_rmi.ClienteCallBackInt;
import ServidorAlertas.dao.ClsPersistencia;
import ServidorAlertas.dto.ClsClienteDTO;
import ServidorNotificaciones.dto.ClsIndicadoresAlerta;
import ServidorNotificaciones.dto.ClsIndicadoresRegistros;
import ServidorNotificaciones.dto.ClsNotificacionDTO;
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
        String respuestaCallback = "El paciente se encuentra bien";

        //La variable listaConIndicadores me retorna un arreglo que tiene las alertas generadas con base en los indicadores    
        ArrayList<ClsIndicadoresAlerta> listaConIndicadores = llenarListaConIndicadores(objPaciente);
        //Tengo que validar si se debe o no enviar la informacion al servidor de notificaciones y esto debe ser con base en la cantidad de alertas
        if (listaConIndicadores.size() > 1) {
            
            /*Parte del Callback*/
            ClienteCallBackInt objPacienteCallBack = new ClienteCallBackImpl();
            //Si hay una alerta se debe notificar al paciente
           objPacienteCallBack = tablaPacientes.get(objPaciente.getNumHabitacion());
           String nombreAlertasGeneradas="";
            for (ClsIndicadoresAlerta listaConIndicadore : listaConIndicadores) {
                nombreAlertasGeneradas+=listaConIndicadore.getNombreIndicador()+"\n";
            }
           respuestaCallback = objPacienteCallBack.notificar("El paciente que se encuntra en la habitacion " + objPaciente.getNumHabitacion() + " tiene  " + listaConIndicadores.size() + " indicadores que se encuentran fuera del rango normal.\n"+nombreAlertasGeneradas);

            /*Realizar la persistencia*/
            ClsPersistencia objPersistencia = new ClsPersistencia();
           
            
            String mensaje = "La enfermera debe revisar al paciente";
            if (listaConIndicadores.size() >= 3) {
                mensaje = "El medico y la enfermera deben revisar al paciente";
            }

            SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");

            Date date = new Date();
            String hora = formatterTime.format(date);
            String fecha = formatterDate.format(date);
            
            /*Persistencia*/
            ClsIndicadoresRegistros objRegistro = new ClsIndicadoresRegistros(String.valueOf(objPaciente.getNumHabitacion()), fecha, hora, String.valueOf(listaConIndicadores.size()));
            objPersistencia.GuardarRegistro(objRegistro);
            

            objMensajeNotificacion = new ClsNotificacionDTO(objPaciente.getNumHabitacion(), objPaciente.getEdad(), objPaciente.getNombres(), objPaciente.getApellidos(), mensaje, fecha, hora);
            //Le paso al mensajeNotificacion las alertas generadas
            objMensajeNotificacion.setListaIndicadoresAlerta(listaConIndicadores);

            //Aqui debo leer del archivo txt los registros que tiene almacenados al mismo tiempo que voy alimentnado el objMensajeNotificacion
            
            objMensajeNotificacion.setListaIndicadoresRegistros(objPersistencia.LeerRegistros(String.valueOf(objPaciente.getNumHabitacion())));
            //Enviar el mensaje*/
            
            ORServidorNotificaciones.notificarAlMedico(objMensajeNotificacion);
            //ORServidorNotificaciones.notificarAlMedicoConMensaje();

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

        if (objPaciente.getSaturacionOxigeno() < 95) {
            ClsIndicadoresAlerta objIndicadorSaturacion = new ClsIndicadoresAlerta("Saturacion de Oxigeno", String.valueOf(objPaciente.getSaturacionOxigeno()));
            listaIndicadoresAlerta.add(objIndicadorSaturacion);
            System.out.println("Alerta de Saturaciòn");
        }
        //Edad Nacimiento - 6 Semanas
        if (objPaciente.getEdad() > 0 && objPaciente.getEdad() <= 0.115068) {
            if (objPaciente.getFrecuenciaCardiaca() < 120 || objPaciente.getFrecuenciaCardiaca() > 140) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 70 || objPaciente.getSistolica() > 100 || objPaciente.getDiastolica() < 50 || objPaciente.getDiastolica() > 68) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 40 && objPaciente.getFrecuenciaRespiratoria() > 45) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() != 38) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }

        //Edad 6 Semanas - 1 año
        if (objPaciente.getEdad() > 0.115068 && objPaciente.getEdad() <= 1) {
            if (objPaciente.getFrecuenciaCardiaca() < 100 || objPaciente.getFrecuenciaCardiaca() > 130) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 84 || objPaciente.getSistolica() > 106 || objPaciente.getDiastolica() < 56 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 && objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 && objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }
        //Edad 1 año - 2 año
        if (objPaciente.getEdad() > 1 && objPaciente.getEdad() <= 2) {
            if (objPaciente.getFrecuenciaCardiaca() < 100 || objPaciente.getFrecuenciaCardiaca() > 120) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 98 || objPaciente.getSistolica() > 106 || objPaciente.getDiastolica() < 58 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 && objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 && objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }
        //Edad 2 año - 6 año
        if (objPaciente.getEdad() > 2 && objPaciente.getEdad() <= 6) {
            if (objPaciente.getFrecuenciaCardiaca() < 80 || objPaciente.getFrecuenciaCardiaca() > 120) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 99 || objPaciente.getSistolica() > 112 || objPaciente.getDiastolica() < 64 || objPaciente.getDiastolica() > 70) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 20 && objPaciente.getFrecuenciaRespiratoria() > 30) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37.5 && objPaciente.getTemperatura() > 37.8) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }
        //Edad 6 año - 13 años
        if (objPaciente.getEdad() > 6 && objPaciente.getEdad() <= 13) {
            if (objPaciente.getFrecuenciaCardiaca() < 80 || objPaciente.getFrecuenciaCardiaca() > 100) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 104 || objPaciente.getSistolica() > 124 || objPaciente.getDiastolica() < 64 || objPaciente.getDiastolica() > 86) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 && objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 37 && objPaciente.getTemperatura() > 37.5) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }
        //Edad 13 año - 16 años
        if (objPaciente.getEdad() > 13 && objPaciente.getEdad() <= 16) {
            if (objPaciente.getFrecuenciaCardiaca() < 70 || objPaciente.getFrecuenciaCardiaca() > 80) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 118 || objPaciente.getSistolica() > 132 || objPaciente.getDiastolica() < 70 || objPaciente.getDiastolica() > 82) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 && objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() != 37) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }

        //Edad 16 años o mas
        if (objPaciente.getEdad() > 16) {
            if (objPaciente.getFrecuenciaCardiaca() < 60 || objPaciente.getFrecuenciaCardiaca() > 80) {
                ClsIndicadoresAlerta objIndicadorFrecuencia = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaCardiaca()));
                listaIndicadoresAlerta.add(objIndicadorFrecuencia);
                System.out.println("Alerta de Frecuencia Cardiaca");
            }
            if (objPaciente.getSistolica() < 110 || objPaciente.getSistolica() > 140 || objPaciente.getDiastolica() < 70 || objPaciente.getDiastolica() > 90) {
                ClsIndicadoresAlerta objIndicadorPresion = new ClsIndicadoresAlerta("Presion Arterial", String.valueOf(objPaciente.getSistolica()) + "/" + String.valueOf(objPaciente.getDiastolica()));
                listaIndicadoresAlerta.add(objIndicadorPresion);
                System.out.println("Alerta por Presion Arterial");
            }
            if (objPaciente.getFrecuenciaRespiratoria() < 12 && objPaciente.getFrecuenciaRespiratoria() > 20) {
                ClsIndicadoresAlerta objIndicadorFreResp = new ClsIndicadoresAlerta("Frecuencia Cardiaca", String.valueOf(objPaciente.getFrecuenciaRespiratoria()));
                listaIndicadoresAlerta.add(objIndicadorFreResp);
                System.out.println("Alerta por Frecuencia Respiratoria");
            }
            if (objPaciente.getTemperatura() < 36.2 && objPaciente.getTemperatura() > 37.2) {
                ClsIndicadoresAlerta objIndicadorTemp = new ClsIndicadoresAlerta("Temperatura", String.valueOf(objPaciente.getTemperatura()));
                listaIndicadoresAlerta.add(objIndicadorTemp);
                System.out.println("Alerta por Temperatura");
            }

        }

        return listaIndicadoresAlerta;

    }

}
