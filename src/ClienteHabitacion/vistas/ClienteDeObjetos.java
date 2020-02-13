package ClienteHabitacion.vistas;

import ClienteHabitacion.dto.GestionIndicadores;
import ClienteHabitacion.sop_rmi.ClienteCallBackImpl;
import ClienteHabitacion.sop_rmi.ClienteCallBackInt;
import ServidorAlertas.sop_rmi.ServidorAlertasInt;
import ClienteHabitacion.utilidades.*;
import ServidorAlertas.dto.ClsClienteDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ClienteDeObjetos extends javax.swing.JFrame {

    private static ClienteCallBackInt ORClienteCallBack;
    private ArrayList<String> mensajesTextArea = new ArrayList<>();
    private static ServidorAlertasInt ORServidorAlertas;

    public ClienteDeObjetos() {
        initComponents();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        registroEnElNS();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtNumHabitacion = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea_indicadores = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtarea_notificaciones = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        comboTipoEdad = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Edad:");

        jLabel4.setText("Num Habitación:");

        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtEdad.setName("txtEdad"); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        txtNumHabitacion.setName("txtNumHabitacion"); // NOI18N

        txtApellido.setName("txtApellido"); // NOI18N

        btnGuardar.setText("Guardar y enviar indicadores");
        btnGuardar.setName("btnGuardarCliente"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setText("Indicadores");

        txtarea_indicadores.setColumns(20);
        txtarea_indicadores.setRows(5);
        txtarea_indicadores.setName("txtIndicadores"); // NOI18N
        jScrollPane1.setViewportView(txtarea_indicadores);

        txtarea_notificaciones.setColumns(20);
        txtarea_notificaciones.setRows(5);
        jScrollPane2.setViewportView(txtarea_notificaciones);

        jLabel6.setText("Notificaciones");

        comboTipoEdad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanas", "Años" }));

        jLabel7.setText("Tipo de edad:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTipoEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(58, 58, 58)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumHabitacion)
                                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 157, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(btnGuardar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTipoEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Capturar Informacion

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String tipoMensaje = comboTipoEdad.getSelectedItem().toString();
        float edad = Float.parseFloat(txtEdad.getText());
        int numHabitacion = Integer.parseInt(txtNumHabitacion.getText());
        if (tipoMensaje.equals("Semanas")) {
            edad = (float) (edad * (7.0 / 365.0));
            System.out.println("Entre a semanas: " + edad);
        }
        ClsClienteDTO objPaciente = new ClsClienteDTO(numHabitacion, nombre, apellido, edad);
        
        
        inhabilitarComponentes();

        Timer objTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Generar los indicadores aleatoriamente
                GestionIndicadores objGestion = new GestionIndicadores();
                ClsClienteDTO objNewCliente;
                objNewCliente = objGestion.GenerarIndicadores(objPaciente);
                // Obtener indicadores en un String para luego imprimirlos
                String mensajeTextArea = mensajeIndicadores(objNewCliente);
                //Agrego los indicadores a una lista, para tener un historial de indicadores emitidos
                mensajesTextArea.add(mensajeTextArea);
                //Ejecucion del OR
                try {
                    ORClienteCallBack = new ClienteCallBackImpl();
                    ORServidorAlertas.registrarPaciente(ORClienteCallBack, objNewCliente);
                    /*Enviar el objeto al servidor de alertas*/
                    String respuestaCallBack = ORServidorAlertas.enviarIndicadores(objNewCliente);
                    fijarRespuestaCallBack(respuestaCallBack);
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Fijar los mensajes en el area de Indicadores
                mostrarIndicadoresEnPantalla();
            }
        });
        objTimer.start();


    }//GEN-LAST:event_btnGuardarActionPerformed

    public String mensajeIndicadores(ClsClienteDTO objNewCliente){
        DecimalFormat df = new DecimalFormat("#.00");
        String mensaje = "Enviando Indicadores...\n"
                        + "Frecuencia Cardiaca: " + df.format(objNewCliente.getFrecuenciaCardiaca())
                        + "\nPresion Arterial: " + df.format(objNewCliente.getSistolica()) + "/" + df.format(objNewCliente.getDiastolica())
                        + "\nFrecuencia Respiratoria: " + df.format(objNewCliente.getFrecuenciaRespiratoria())
                        + "\nTemperatura: " + df.format(objNewCliente.getTemperatura())
                        + "\nSaturacion de Oxigento: " + df.format(objNewCliente.getSaturacionOxigeno()) + "\n"
                        + "************\n";
        return mensaje;
    }
    
    public void fijarRespuestaCallBack(String mensaje){
        this.txtarea_notificaciones.setText(mensaje);
    }
    public void mostrarIndicadoresEnPantalla() {
        String mensajeFinal = "";
        for (String string : mensajesTextArea) {

            mensajeFinal += string;

        }

        this.txtarea_indicadores.setText(mensajeFinal);
    }
    
    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteDeObjetos().setVisible(true);
            }
        });
    }

    private void registroEnElNS() {

        int numPuertoRMIRegistry = 1515;
        String dirIpRMIRegistry = "localhost";
        /*System.out.println("CcomboTipoEdadual es la direccion ip donde se encuentra el rmiregistry: ");
        dirIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiregistry: ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
         */
 /*¿Por qué la variable ObjetoRemotoGestionPacientes no tiene una asignación igual a la variable servidor?*/
        ORServidorAlertas = (ServidorAlertasInt) UtilidadesRegistroC.obtenerObjRemoto(dirIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoGestionPacientes");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboTipoEdad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumHabitacion;
    private javax.swing.JTextArea txtarea_indicadores;
    private javax.swing.JTextArea txtarea_notificaciones;
    // End of variables declaration//GEN-END:variables

    private void inhabilitarComponentes() {
        this.txtNumHabitacion.setEnabled(false);
        this.txtNombre.setEnabled(false);
        this.txtApellido.setEnabled(false);
        this.comboTipoEdad.setEnabled(false);
        this.txtEdad.setEnabled(false);
        this.btnGuardar.setEnabled(false);
    }
}
