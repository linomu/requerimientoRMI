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
import ServidorAlertas.dto.GestionAlertaIndicadores;
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
        System.out.println("Invocando al método registrar usuario desde el servidor...");
        tablaPacientes.put(objPacienteDto.getNumHabitacion(), objPaciente);
        return true;
    }

    @Override
    public void enviarIndicadores(ClsClienteDTO objPaciente) throws RemoteException {
        System.out.println("Invocando al método enviarIndicadores desde el servidor...");
        ClsNotificacionDTO objMensajeNotificacion;
        String respuestaCallback = "El paciente se encuentra bien";

        //La variable listaConIndicadores me retorna un arreglo que tiene las alertas generadas con base en los indicadores    
        GestionAlertaIndicadores objIndicadoresAlertas = new GestionAlertaIndicadores(objPaciente);
        ArrayList<ClsIndicadoresAlerta> listaConIndicadores = objIndicadoresAlertas.llenarListaConIndicadores();
        //Tengo que validar si se debe o no enviar la informacion al servidor de notificaciones y esto debe ser con base en la cantidad de alertas
        if (listaConIndicadores.size() > 1) {

            /*Parte del Callback*/
            ClienteCallBackInt objPacienteCallBack = new ClienteCallBackImpl();
            //Si hay una alerta se debe notificar al paciente, obtengo la refencia de ese OR
            objPacienteCallBack = tablaPacientes.get(objPaciente.getNumHabitacion());
            String nombreAlertasGeneradas = "";
            //De la listaConIndicadores, solo voy a obtener el nombre de los indicadores, esto con el fin de enviárselo al paciente
            for (ClsIndicadoresAlerta listaConIndicadore : listaConIndicadores) {
                nombreAlertasGeneradas += listaConIndicadore.getNombreIndicador() + " [ "+listaConIndicadore.getValor()+" ] "+"\n";
            }
            //Esta es la respuesta que se enviará al Paciente, mediate el retorno del método.
            objPacienteCallBack.notificar("El paciente que se encuntra en la habitacion " + objPaciente.getNumHabitacion() + " tiene  " + listaConIndicadores.size() + " indicadores que se encuentran fuera del rango normal.\n" + nombreAlertasGeneradas);

            String mensaje = "La enfermera debe revisar al paciente";
            if (listaConIndicadores.size() >= 3) {
                mensaje = "El medico y la enfermera deben revisar al paciente";
            }
            String hora = obtenerHora(); String fecha = obtenerFecha();

            /*Realizar la persistencia*/
            ClsPersistencia objPersistencia = new ClsPersistencia();
            /*Creo un objeto de la clase ClsIndicadoresRegistros, con el fin de estructurar la alerta y así guardarlo en el txt*/
            ClsIndicadoresRegistros objRegistro = new ClsIndicadoresRegistros(String.valueOf(objPaciente.getNumHabitacion()),objPaciente.getNombres(),objPaciente.getApellidos(), fecha, hora, String.valueOf(listaConIndicadores.size()));
            objPersistencia.GuardarRegistro(objRegistro);

            objMensajeNotificacion = new ClsNotificacionDTO(objPaciente.getNumHabitacion(), objPaciente.getEdad(), objPaciente.getNombres(), objPaciente.getApellidos(), mensaje, fecha, hora);
            //Le paso al mensajeNotificacion las alertas generadas
            objMensajeNotificacion.setListaIndicadoresAlerta(listaConIndicadores);
            /*Aqui debo leer del archivo txt los registros que tiene almacenados con base en su edad, 
            al mismo tiempo que le paso esa lista al objMensajeNotificacion*/
            objMensajeNotificacion.setListaIndicadoresRegistros(objPersistencia.LeerRegistros(String.valueOf(objPaciente.getNumHabitacion())));
            //Enviar el mensaje*/
            ORServidorNotificaciones.notificarAlMedico(objMensajeNotificacion);
            //ORServidorNotificaciones.notificarAlMedicoConMensaje();

        }

        
    }

    public void consultarReferenciaRemotaDeNotificacion(String direccionIpRmiRegistry, int numPuertoRmiRegistry) {
        try {
            System.out.println("consulté la referencia remota de notificacion");
            this.ORServidorNotificaciones = (ServidorNotificacionInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRmiRegistry, numPuertoRmiRegistry, "ObjetoRemotoNotificaciones");

        } catch (Exception e) {
            System.out.println("Tenemos un error!");
        }

    }

  
    private String obtenerHora(){
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return formatterTime.format(date);
    }
    
    private String obtenerFecha(){
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatterDate.format(date);
    }
}
