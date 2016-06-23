
package view.ctbContabilidad;
import controller.ctbContabilidad.CCCuentaCorrienteCliente;
import javax.swing.JOptionPane;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteCliente;
import modelo.ctbContabilidad.entidad.CEMovimientoCuentaCliente;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;

public class DlgDepositoCuentaCorrienteCliente extends DialogoPadre
{
    CECuentaCorrienteCliente oCECuentaCorriente;
    public boolean pSiNoCancel;
    private float pago;
    public DlgDepositoCuentaCorrienteCliente(java.awt.Frame parent, boolean modal,CECuentaCorrienteCliente oCECuentaCorriente)
    {
        super(parent, modal);
        initComponents();
        VerificadorDeTxt oVerificadorDeTxt=new VerificadorDeTxt("Numero",50,TxtMonto);
        TxtMonto.setDocument(oVerificadorDeTxt);
        TxtCliente.setText(oCECuentaCorriente.getCliente());
        TxtSaldo.setText(oCECuentaCorriente.getSaldo()+"");
        TxtTipoMoneda.setText(oCECuentaCorriente.getMoneda()+"");
        TxtMonto.requestFocus();
        TxtMonto.selectAll();
        this.oCECuentaCorriente=oCECuentaCorriente;
        LblFechaActual.setText(CLConsultarFechaSistema.consultarFecha());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TxtCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtTipoMoneda = new javax.swing.JTextField();
        BtnDescargar = new javax.swing.JButton();
        TxtSaldo = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        TxtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LblFechaActual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Cliente  :");

        TxtCliente.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Saldo    :");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Moneda:");

        TxtTipoMoneda.setEditable(false);

        BtnDescargar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        BtnDescargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/new-meeting.png"))); // NOI18N
        BtnDescargar.setText("Depositar");
        BtnDescargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnDescargar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDescargarActionPerformed(evt);
            }
        });

        TxtSaldo.setEditable(false);

        BtnCancelar.setFont(new java.awt.Font("Arial", 0, 14));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Monto   :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Fecha:");

        LblFechaActual.setFont(new java.awt.Font("Arial", 0, 14));
        LblFechaActual.setText("20/09/2011");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TxtTipoMoneda))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(TxtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addComponent(BtnDescargar)
                                .addGap(18, 18, 18)
                                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblFechaActual)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LblFechaActual))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(BtnDescargar, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDescargarActionPerformed
    
    float monto=Float.parseFloat(TxtMonto.getText());
    if(monto>0)
    {
       CEMovimientoCuentaCliente oCEMovimientoCuenta=new CEMovimientoCuentaCliente();
       oCEMovimientoCuenta.setIdCliente(oCECuentaCorriente.getIdCliente());
       oCEMovimientoCuenta.setIdCuentaCorriente(oCECuentaCorriente.getIdCuentaCorriente());
       oCEMovimientoCuenta.setMonto(monto);
       oCEMovimientoCuenta.setIdMoneda(oCECuentaCorriente.getIdMoneda());
       LblFechaActual.setText(CLConsultarFechaSistema.consultarFecha());
       if(JOptionPane.showConfirmDialog(null,"Esta Seguro de realizar el deposito?","Mensaje de Confirmación",JOptionPane.OK_OPTION)==JOptionPane.OK_OPTION)
       {
           if(CCCuentaCorrienteCliente.registrarDepositoCuentaCorriente(oCEMovimientoCuenta))
           {
               JOptionPane.showMessageDialog(null, "La Transacción se realizo sastifactoriamente");
               dispose();
           }
            else
           {
               JOptionPane.showMessageDialog(null, "No se pudo completar la transacción");
            }
       }
    }
    else
    {
         JOptionPane.showMessageDialog(null,"El deposito de dinero debe ser mayor que 0");
    }

    }//GEN-LAST:event_BtnDescargarActionPerformed
    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        pSiNoCancel=true;
        dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    public float descargarCuentaCorriente()
    {
        if(!pSiNoCancel)
        {
            if(TxtMonto.getText().equals(""))
            {
               return 0;
            }
            else
            {
            float monto=Float.parseFloat(TxtMonto.getText());
            return monto;
            }
        }
        else
        {
            return 0;
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnDescargar;
    private javax.swing.JLabel LblFechaActual;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JTextField TxtMonto;
    private javax.swing.JTextField TxtSaldo;
    private javax.swing.JTextField TxtTipoMoneda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}
