package view.cmpCompra;


import modelo.cmpCompra.entidad.CENotaCreditoProveedor;
import util.clases.grlGeneral.DialogoPadre;

public class DlgAnularNotaCreditoProveedor extends DialogoPadre
{
   
    private CENotaCreditoProveedor oCENotaCreditoProveedor;
    public DlgAnularNotaCreditoProveedor(java.awt.Frame parent, boolean modal,CENotaCreditoProveedor oCENotaCredito)
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.oCENotaCreditoProveedor=oCENotaCredito;
        TxtCliente.setText(oCENotaCredito.getProveedor()+"");
        TxtNumPedido.setText(oCENotaCredito.getNumeroNotaCreditoProveedor());
        TxtObservacion.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LblTitulo = new javax.swing.JLabel();
        TxtCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtNumPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtObservacion = new javax.swing.JTextField();
        BtnAnular = new BotonesABM.BtnEliminar();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LblTitulo.setFont(new java.awt.Font("Arial", 0, 36));
        LblTitulo.setForeground(new java.awt.Color(153, 0, 0));
        LblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTitulo.setText("Anular Nota Crédito");

        TxtCliente.setEditable(false);
        TxtCliente.setFont(new java.awt.Font("Arial", 0, 14));
        TxtCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Nº de Cerdito:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("Cliente:");

        TxtNumPedido.setEditable(false);
        TxtNumPedido.setFont(new java.awt.Font("Arial", 0, 14));
        TxtNumPedido.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Observación:");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtObservacion, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
                    .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(LblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private boolean isAnulado=false;
    public boolean IsAnulado()
    {
        return isAnulado;
    }

    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
     String TxObservacion=TxtObservacion.getText();
     oCENotaCreditoProveedor.setUltimaObservacion(TxObservacion);
     isAnulado=true;
     dispose();

    }//GEN-LAST:event_BtnAnularActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed

        dispose();
}//GEN-LAST:event_btnCancelar1ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnEliminar BtnAnular;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JTextField TxtNumPedido;
    private javax.swing.JTextField TxtObservacion;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}
