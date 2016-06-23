package view.almAlmacen.invInventarios;

import controller.almAlmacen.CCTransferenciaAlmacen;
import javax.swing.JOptionPane;
import modelo.almAlmacen.entidad.CETransferenciaAlmacen;
import util.clases.grlGeneral.DialogoPadre;



public class DlgAnularTransferenciaAlmacen extends DialogoPadre
{

    CETransferenciaAlmacen oCETransferenciaAlmacen;
    public DlgAnularTransferenciaAlmacen(java.awt.Frame parent, boolean modal,CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        super(parent, modal);
        initComponents();
        this.oCETransferenciaAlmacen=oCETransferenciaAlmacen;
        TxtNumOrdenCompra.setText(oCETransferenciaAlmacen.getNumeroIngreso());
        TxtObservacion.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtNumOrdenCompra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtObservacion = new javax.swing.JTextField();
        BtnAnular = new BotonesABM.BtnEliminar();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 36));
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Anular Ingreso Producto");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Nº Transferencia :");

        TxtNumOrdenCompra.setEditable(false);
        TxtNumOrdenCompra.setFont(new java.awt.Font("Arial", 0, 14));
        TxtNumOrdenCompra.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Observación       :");

        TxtObservacion.setFont(new java.awt.Font("Arial", 0, 14));
        TxtObservacion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TxtObservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtObservacionActionPerformed(evt);
            }
        });

        BtnAnular.setText("ANULAR");
        BtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnularActionPerformed(evt);
            }
        });

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtNumOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtObservacion, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtObservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtObservacionActionPerformed
    BtnAnular.requestFocus();
    }//GEN-LAST:event_TxtObservacionActionPerformed

    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
     String TxObservacion=TxtObservacion.getText();
     oCETransferenciaAlmacen.setObservacion(TxObservacion);
     if(CCTransferenciaAlmacen.AnularTransferenciaAlmacen(oCETransferenciaAlmacen)==1)
     {
        dispose();
     }
    else
     {
        JOptionPane.showMessageDialog(null, "No se pudo completar la operacion");
     }

   
    }//GEN-LAST:event_BtnAnularActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnEliminar BtnAnular;
    private javax.swing.JTextField TxtNumOrdenCompra;
    private javax.swing.JTextField TxtObservacion;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}
