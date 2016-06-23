package view.grlGeneral;

import controller.grlGeneral.CCAtencionSistema;
import javax.swing.JOptionPane;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiRelojHora;
import view.FrmSistemaMenu;

public class DlgAperturaSistema extends DialogoPadre
{

    private MiRelojHora hilo;
    public DlgAperturaSistema(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        LblFechaActual.setText(CLConsultarFechaSistema.consultarFecha());
        hilo = new MiRelojHora(LblHoraSistema);
        hilo.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblHoraSistema = new javax.swing.JLabel();
        LblFechaActual = new javax.swing.JLabel();
        BtnAceptar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apertura del Sistema");

        LblHoraSistema.setFont(new java.awt.Font("Calibri", 1, 72)); // NOI18N
        LblHoraSistema.setForeground(new java.awt.Color(153, 0, 0));
        LblHoraSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblHoraSistema.setText("02:09:12");

        LblFechaActual.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        LblFechaActual.setForeground(new java.awt.Color(0, 0, 51));
        LblFechaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblFechaActual.setText("26/09/2010");

        BtnAceptar.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        BtnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/programas-gratis.png"))); // NOI18N
        BtnAceptar.setText("Aceptar");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

        BtnCancelar.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/exit.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblFechaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblFechaActual)
                .addGap(18, 18, 18)
                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed
    if(!CCAtencionSistema.AperturarSistema())
    {
        JOptionPane.showMessageDialog(null,"No se pudo aperturar el sistema");
    }
    else
    {
        JOptionPane.showMessageDialog(null,"El sistema ya se encuentra operativo");
        FrmSistemaMenu.setSiNoAtencion(2);
        dispose();
    }
    }//GEN-LAST:event_BtnAceptarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JLabel LblFechaActual;
    private javax.swing.JLabel LblHoraSistema;
    // End of variables declaration//GEN-END:variables
}
