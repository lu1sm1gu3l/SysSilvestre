package view.ctbContabilidad;

import controller.ctbContabilidad.CCCuentaCorrienteProveedor;
import controller.vtaVenta.CCMoneda;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import modelo.vtaVenta.entidad.CEMoneda;
import util.clases.grlGeneral.DialogoPadre;
import view.cmrComercial.DlgGestionProveedor;

public class DlgNuevaCuentaCorrienteProveedor extends DialogoPadre {

    private int idProveedor;
    public DlgNuevaCuentaCorrienteProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTipoMoneda();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtProveedor = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label8 = new Label.Label();
        TxtRUC = new TextField.JTxtLetra();
        CbxMoneda = new ComboBox.ComboBox();
        label17 = new Label.Label();
        BtnCrearCuenta = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Cuenta Corriente");

        TxtProveedor.setTamanio(200);
        TxtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtProveedorActionPerformed(evt);
            }
        });

        label1.setText("Proveedor :");

        label8.setText("RUC           :");

        TxtRUC.setEditable(false);
        TxtRUC.setTamanio(200);

        label17.setText("Moneda :");

        BtnCrearCuenta.setFont(new java.awt.Font("Verdana", 0, 12));
        BtnCrearCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/programas-gratis.png"))); // NOI18N
        BtnCrearCuenta.setText("Crear Cuenta");
        BtnCrearCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCrearCuenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCrearCuentaActionPerformed(evt);
            }
        });

        BtnSalir.setFont(new java.awt.Font("Verdana", 0, 12));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/exit.png"))); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(TxtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TxtProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BtnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtRUC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(BtnCrearCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   private void cargarTipoMoneda()
    {
        List<CEMoneda> oListTipoIngreso=CCMoneda.listaAll();
        construirModeloCombo(CbxMoneda,(ArrayList)oListTipoIngreso);
    }
   public void construirModeloCombo(JComboBox oBox, ArrayList oLista)
    {
        oBox.removeAllItems();
        if(oLista!=null)
        {
            for(Object objeto:oLista)
            {
                oBox.addItem(objeto);
            }
        }
    }
    private void BtnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCrearCuentaActionPerformed
      if(JOptionPane.showConfirmDialog(null,"Esta Seguro de crear la cuenta corriente?","Mensaje de Confirmación",JOptionPane.OK_OPTION)==JOptionPane.OK_OPTION)
       {
          CECuentaCorrienteProveedor oCECuentaCorriente=new CECuentaCorrienteProveedor();
          oCECuentaCorriente.setIdProveedor((int)idProveedor);
          oCECuentaCorriente.setIdMoneda(((CEMoneda)CbxMoneda.getSelectedItem()).getId_moneda());
          if(CCCuentaCorrienteProveedor.registrarNuevaCuentaCorriente(oCECuentaCorriente))
           {
               JOptionPane.showMessageDialog(null, "Se creo la cuenta Satisfactoriamente");
               dispose();
           }
            else
           {
               JOptionPane.showMessageDialog(null, "La cuenta Corriente ya existe");
               dispose();
            }
       }
    }//GEN-LAST:event_BtnCrearCuentaActionPerformed

    private void TxtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtProveedorActionPerformed
    buscarProveedor();
    CbxMoneda.requestFocus();
    }//GEN-LAST:event_TxtProveedorActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
    dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void buscarProveedor()
    {
        if(TxtProveedor.isEnabled()){
          DlgGestionProveedor odialogo= new DlgGestionProveedor(null, true,1,1,1);//consultar

          odialogo.setCajaTexo(TxtProveedor.getText());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Proveedor");
          if(odialogo.getoCEProveedor()!=null)
          {
            idProveedor=odialogo.getoCEProveedor().getIdProveedor();
            TxtProveedor.setText(odialogo.getoCEProveedor().getRazonSocial());
            TxtRUC.setText(odialogo.getoCEProveedor().getRUC());
          }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCrearCuenta;
    private javax.swing.JButton BtnSalir;
    private ComboBox.ComboBox CbxMoneda;
    private TextField.JTxtLetra TxtProveedor;
    private TextField.JTxtLetra TxtRUC;
    private Label.Label label1;
    private Label.Label label17;
    private Label.Label label8;
    // End of variables declaration//GEN-END:variables

}
