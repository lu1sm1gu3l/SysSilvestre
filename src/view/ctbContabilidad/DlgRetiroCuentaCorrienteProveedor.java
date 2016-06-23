
package view.ctbContabilidad;
import java.awt.Color;
import javax.swing.JOptionPane;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;

public class DlgRetiroCuentaCorrienteProveedor extends DialogoPadre
{
    CECuentaCorrienteProveedor oCECuentaCorriente;
    public boolean pSiNoCancel;
    private float pago;
    public DlgRetiroCuentaCorrienteProveedor(java.awt.Frame parent, boolean modal,CECuentaCorrienteProveedor oCECuentaCorriente)
    {
        super(parent, modal);
        initComponents();
        VerificadorDeTxt oVerificadorDeTxt=new VerificadorDeTxt("Numero",50,TxtMonto);
        TxtMonto.setDocument(oVerificadorDeTxt);     
        TxtProveedor.setText(oCECuentaCorriente.getProveedor());
        TxtSaldo.setText(oCECuentaCorriente.getSaldo()+"");
        TxtTipoMoneda.setText(oCECuentaCorriente.getMoneda()+"");
        TxtMonto.requestFocus();
        TxtMonto.selectAll();
        this.oCECuentaCorriente=oCECuentaCorriente;
        this.pago=pago;

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TxtProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtTipoMoneda = new javax.swing.JTextField();
        BtnDescargar = new javax.swing.JButton();
        TxtSaldo = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
        TxtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Proveedor  :");

        TxtProveedor.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Saldo    :");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Moneda:");

        TxtTipoMoneda.setEditable(false);

        BtnDescargar.setFont(new java.awt.Font("Arial", 0, 14));
        BtnDescargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/new-meeting.png"))); // NOI18N
        BtnDescargar.setText("Retirar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1))
                    .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TxtTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDescargarActionPerformed
    float Saldo=oCECuentaCorriente.getSaldo();
    float monto=Float.parseFloat(TxtMonto.getText());
    if(monto>Saldo)
    {
        JOptionPane.showMessageDialog(null,"El monto debe ser menor o igual al saldo");
        TxtMonto.requestFocus();
        TxtMonto.setBackground(new Color(255,204,204));
        TxtMonto.selectAll();
    }
    else
    {
        if(monto<0)
        {
            JOptionPane.showMessageDialog(null,"El monto debe ser mayor a 0");
            TxtMonto.requestFocus();
            TxtMonto.setBackground(new Color(255,204,204));
            TxtMonto.selectAll();
        }
        else
        {
            if(monto>pago)
            {
                JOptionPane.showMessageDialog(null,"El monto debe ser menor o igual a :"+pago);
                TxtMonto.requestFocus();
                TxtMonto.setBackground(new Color(255,204,204));
                TxtMonto.selectAll();
            }
            else
            {
            dispose();
            }
        }
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
    private javax.swing.JTextField TxtMonto;
    private javax.swing.JTextField TxtProveedor;
    private javax.swing.JTextField TxtSaldo;
    private javax.swing.JTextField TxtTipoMoneda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

}
