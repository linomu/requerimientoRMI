/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.sop_rmi;

import ServidorNotificaciones.dto.ClsIndicadoresAlerta;
import ServidorNotificaciones.dto.ClsNotificacionDTO;
import java.awt.TextArea;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorNotificacionImpl extends UnicastRemoteObject implements ServidorNotificacionInt {

    ClsNotificacionDTO objNotificacion;
    JLabel numHabitacion, nombre, apellidos, edad, horaAlerta, fechaAlerta, mensajeDoctorEnfermera;
    JTable tablaIndicadores, tablaRegistros;
    DefaultTableModel objModeloTablaIndicadores = new DefaultTableModel();
    DefaultTableModel objModeloTablaRegistros = new DefaultTableModel();

    public ServidorNotificacionImpl(JLabel numHabitacion, JLabel nombre, JLabel apellidos, JLabel edad, JLabel horaAlerta, JLabel fechaAlerta, JLabel mensajeDoctorEnfermera, JTable tablaIndicadores, JTable tablaRegistros) throws RemoteException {
        super();
        this.numHabitacion = numHabitacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.horaAlerta = horaAlerta;
        this.fechaAlerta = fechaAlerta;
        this.mensajeDoctorEnfermera = mensajeDoctorEnfermera;
        this.tablaIndicadores = tablaIndicadores;
        this.tablaRegistros = tablaRegistros;
        setModelos();

    }

    private void setModelos() {
        String[] cabecera = {"Nombre Indicador", "Valor"};
        objModeloTablaIndicadores.setColumnIdentifiers(cabecera);
        this.tablaIndicadores.setModel(objModeloTablaIndicadores);
        String[] cabeceraTablaRegitros = {"Fecha de Alerta", "Hora de Alerta", "Puntuación"};
        objModeloTablaRegistros.setColumnIdentifiers(cabeceraTablaRegitros);
        this.tablaRegistros.setModel(objModeloTablaRegistros);

    }

    @Override
    public void notificarAlMedico(ClsNotificacionDTO objNotificacion) throws RemoteException {
        System.out.println("***Datos Servidor Notificaiones***\nNombre: " + objNotificacion.getNombres());
        System.out.println("Cantidad de alertas: " + objNotificacion.getListaIndicadoresAlerta().size());
        this.objNotificacion = objNotificacion;

        imprimirInformacion(objNotificacion);

    }

    private void imprimirInformacion(ClsNotificacionDTO objNotificacion) {
        //El comando setRowCount lo utilizamos para Limpiar las tablas
        this.objModeloTablaIndicadores.setRowCount(0);
        this.objModeloTablaRegistros.setRowCount(0);
        
        
        DecimalFormat df = new DecimalFormat("#.00");
            
        this.numHabitacion.setText(String.valueOf(objNotificacion.getNumeroHabitacion()));
        this.nombre.setText(String.valueOf(objNotificacion.getNombres()));
        this.apellidos.setText(String.valueOf(objNotificacion.getApellidos()));
        if(objNotificacion.getEdad()<1){
            //Convertimos la edad en semanas
            double semanas = (365*objNotificacion.getEdad())/7;
            
            this.edad.setText(String.valueOf(df.format(semanas))+" Semanas ");
            
        }
        this.edad.setText(String.valueOf(df.format(objNotificacion.getEdad()) + " Años"));
        this.horaAlerta.setText(String.valueOf(objNotificacion.getHora()));
        this.fechaAlerta.setText(String.valueOf(objNotificacion.getFecha()));
        this.mensajeDoctorEnfermera.setText(String.valueOf(objNotificacion.getMensajeAlMedico()));
        /*El siguiente for recorrer todas las alertas, con el fin de pasarlas a la tabla*/
        Object[] datos = new Object[this.objModeloTablaIndicadores.getColumnCount()];
        for (ClsIndicadoresAlerta objIndicadorAlerta : objNotificacion.getListaIndicadoresAlerta()) {
            datos[0] = objIndicadorAlerta.getNombreIndicador();
            datos[1] = objIndicadorAlerta.getValor();
            this.objModeloTablaIndicadores.addRow(datos);
        }
        this.tablaIndicadores.setModel(objModeloTablaIndicadores);
        /*Imprimir en la tabla las ultimos 5 registros de las alertas*/

        int tamanioLista = objNotificacion.getListaIndicadoresRegistros().size();
        System.err.println("Tamano de la de lista: "+tamanioLista);
        Object[] datosRegistros = new Object[this.objModeloTablaRegistros.getColumnCount()];

        if (tamanioLista >= 5) {
            for (int i = (tamanioLista - 5); i < tamanioLista; i++) {
                datosRegistros[0] = objNotificacion.getListaIndicadoresRegistros().get(i).getFecha();
                datosRegistros[1] = objNotificacion.getListaIndicadoresRegistros().get(i).getHora();
                datosRegistros[2] = objNotificacion.getListaIndicadoresRegistros().get(i).getPuntuacion();
                this.objModeloTablaRegistros.addRow(datosRegistros);
            }
        }
        if (tamanioLista == 1) {
            datosRegistros[0] = objNotificacion.getListaIndicadoresRegistros().get(0).getFecha();
            datosRegistros[1] = objNotificacion.getListaIndicadoresRegistros().get(0).getHora();
            datosRegistros[2] = objNotificacion.getListaIndicadoresRegistros().get(0).getPuntuacion();
            this.objModeloTablaRegistros.addRow(datosRegistros);
        }
        if (tamanioLista == 2) {
            for (int i = 0; i < 1; i++) {
                datosRegistros[0] = objNotificacion.getListaIndicadoresRegistros().get(i).getFecha();
                datosRegistros[1] = objNotificacion.getListaIndicadoresRegistros().get(i).getHora();
                datosRegistros[2] = objNotificacion.getListaIndicadoresRegistros().get(i).getPuntuacion();
                this.objModeloTablaRegistros.addRow(datosRegistros);
            }
        }
        if (tamanioLista == 3) {
            for (int i = 0; i < 2; i++) {
                datosRegistros[0] = objNotificacion.getListaIndicadoresRegistros().get(i).getFecha();
                datosRegistros[1] = objNotificacion.getListaIndicadoresRegistros().get(i).getHora();
                datosRegistros[2] = objNotificacion.getListaIndicadoresRegistros().get(i).getPuntuacion();
                this.objModeloTablaRegistros.addRow(datosRegistros);
            }
        }
        if (tamanioLista == 4) {
            for (int i = 0; i < 3; i++) {
                datosRegistros[0] = objNotificacion.getListaIndicadoresRegistros().get(i).getFecha();
                datosRegistros[1] = objNotificacion.getListaIndicadoresRegistros().get(i).getHora();
                datosRegistros[2] = objNotificacion.getListaIndicadoresRegistros().get(i).getPuntuacion();
                this.objModeloTablaRegistros.addRow(datosRegistros);
            }
        }

        this.tablaRegistros.setModel(objModeloTablaRegistros);

    }

}
