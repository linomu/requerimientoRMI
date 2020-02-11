/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorNotificaciones.vistas;

import ServidorAlertas.utilidades.UtilidadesRegistroS;
import ServidorNotificaciones.dto.ClsNotificacionDTO;
import ServidorNotificaciones.sop_rmi.ServidorNotificacionImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Lino Alejandro Munoz
 */
public class ServidorDeObjetos extends javax.swing.JFrame {

    /**
     * Creates new form ServidorDeObjetos
     */
    public ServidorDeObjetos() {
        initComponents();
        registrarseComoObjetoRemoto();
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
        txtNumHabitacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Alerta Generada");

        jLabel2.setText("Número de habitación:");

        txtNumHabitacion.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumHabitacion))
                .addContainerGap(450, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorDeObjetos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorDeObjetos().setVisible(true);
            }
        });
    }

    private void registrarseComoObjetoRemoto() {
         int numPuertoRMIRegistry = 1515;
        String dirIpRMIRegistry = "localhost";
        /*System.out.println("Cual es la direccion Ip donde se encuentra el rmiregistry: ");
        dirIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el puerto donde escucha el rmiregistry: ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        */
        try {
            ServidorNotificacionImpl objRemoto = new ServidorNotificacionImpl(this.txtNumHabitacion);
            UtilidadesRegistroS.arrancarNS(dirIpRMIRegistry, numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, dirIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoNotificaciones");
            System.out.println("Objeto remoto registrado, esperando peticiones... ");
          
            
            
        } catch (Exception e) {
            System.out.println("No se puede arrancar el NS o registrar el objeto remoto");
        }
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtNumHabitacion;
    // End of variables declaration//GEN-END:variables
}