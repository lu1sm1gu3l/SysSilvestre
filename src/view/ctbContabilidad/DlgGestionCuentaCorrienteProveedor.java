package view.ctbContabilidad;

import controller.ctbContabilidad.CCCuentaCorrienteProveedor;
import java.awt.Frame;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import util.clases.grlGeneral.DialogoPadre;

public class DlgGestionCuentaCorrienteProveedor extends DialogoPadre
{
    public DlgGestionCuentaCorrienteProveedor(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        listarCuentaCorrientes();
    }
    private void limpiarTabla()
    {
        int size=TblListadoCuentasCorrientes.getRowCount();
        for(int i=0;i<size;i++)
        {
            ((DefaultTableModel)TblListadoCuentasCorrientes.getModel()).removeRow(0);
        }
        TxtTotalSoles.setText("0");
    }
    private void listarCuentaCorrientes()
    {
        limpiarTabla();
        List<CECuentaCorrienteProveedor> oLstCuentaCorriente=CCCuentaCorrienteProveedor.listarCuentaCorriente();
        int i=0;
        float suma=0;
        for(CECuentaCorrienteProveedor oCECuentaCorriente:oLstCuentaCorriente)
        {
            Vector oVector=new Vector();
            oVector.add(i+1);
            oVector.add(oCECuentaCorriente);
            oVector.add(oCECuentaCorriente.getMoneda());
            oVector.add(oCECuentaCorriente.getSaldo());
            oVector.add(oCECuentaCorriente.getTipoCambio());
            oVector.add(oCECuentaCorriente.getSaldoEquivalenteSoles());
            suma=suma+oCECuentaCorriente.getSaldoEquivalenteSoles();
            ((DefaultTableModel)TblListadoCuentasCorrientes.getModel()).addRow(oVector);
            i++;
        }
        LblNumeroCuentas.setText("Existen "+i+" cuenta(s) corrientes registradas");
        TxtTotalSoles.setText(suma+"");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TblListadoCuentasCorrientes = new javax.swing.JTable();
        BtnReportar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        LblNumeroCuentas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtTotalSoles = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtnConsultar = new javax.swing.JButton();
        BtnNuevaCuentaCorriente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtFiltroProveedor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TblListadoCuentasCorrientes.setFont(new java.awt.Font("Verdana", 0, 12));
        TblListadoCuentasCorrientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Proveedor", "Moneda", "Saldo", "Tipo Cambio", "Saldo en S/."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblListadoCuentasCorrientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblListadoCuentasCorrientes.setRowHeight(22);
        jScrollPane1.setViewportView(TblListadoCuentasCorrientes);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(0).setPreferredWidth(40);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(1).setPreferredWidth(250);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(2).setPreferredWidth(70);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(3).setPreferredWidth(100);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(4).setPreferredWidth(70);
        TblListadoCuentasCorrientes.getColumnModel().getColumn(5).setPreferredWidth(100);

        BtnReportar.setFont(new java.awt.Font("Verdana", 0, 12));
        BtnReportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/agt_print.png"))); // NOI18N
        BtnReportar.setText("Reportar");
        BtnReportar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnReportar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnReportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReportarActionPerformed(evt);
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

        LblNumeroCuentas.setFont(new java.awt.Font("Verdana", 0, 14));
        LblNumeroCuentas.setForeground(new java.awt.Color(102, 0, 0));
        LblNumeroCuentas.setText("Existen 5 cuenta(s) corrientes registradas");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 14));
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("S/.");

        TxtTotalSoles.setEditable(false);
        TxtTotalSoles.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 14));
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Total:");

        BtnConsultar.setFont(new java.awt.Font("Verdana", 0, 12));
        BtnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/LettersDetail.png"))); // NOI18N
        BtnConsultar.setText("Consultar");
        BtnConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnConsultar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarActionPerformed(evt);
            }
        });

        BtnNuevaCuentaCorriente.setFont(new java.awt.Font("Verdana", 0, 12));
        BtnNuevaCuentaCorriente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnNuevaCuentaCorriente.setText("Nuevo");
        BtnNuevaCuentaCorriente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNuevaCuentaCorriente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnNuevaCuentaCorriente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevaCuentaCorrienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Filtro de Búsqueda");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Proveedor:");

        TxtFiltroProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFiltroProveedorActionPerformed(evt);
            }
        });
        TxtFiltroProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtFiltroProveedorKeyReleased(evt);
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
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtFiltroProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(LblNumeroCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTotalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BtnNuevaCuentaCorriente, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(BtnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(BtnReportar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(BtnSalir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2))))
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtFiltroProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnNuevaCuentaCorriente, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnReportar, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addGap(87, 87, 87)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblNumeroCuentas, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtTotalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(jLabel2))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
    dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarActionPerformed
    int selectedRow=TblListadoCuentasCorrientes.getSelectedRow();
    if(selectedRow!=-1)
    {
        CECuentaCorrienteProveedor oCECuentaCorrienteT=(CECuentaCorrienteProveedor)TblListadoCuentasCorrientes.getValueAt(selectedRow,1);
         CECuentaCorrienteProveedor oCECuentaCorriente=CCCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle(oCECuentaCorrienteT.getIdProveedor(),oCECuentaCorrienteT.getIdMoneda());
         if(oCECuentaCorriente!=null)
         {
          DlgConsultaCuentaCorrienteProveedor oDlgConsultaCuentaCorriente=new DlgConsultaCuentaCorrienteProveedor((Frame)this.getParent(),true,oCECuentaCorriente);
          oDlgConsultaCuentaCorriente.setLocationRelativeTo(null);
          oDlgConsultaCuentaCorriente.setVisible(true);
         }
   }


    }//GEN-LAST:event_BtnConsultarActionPerformed


    private void VerTrnsaccion()
    {
          int selectedRow=TblListadoCuentasCorrientes.getSelectedRow();
    if(selectedRow!=-1)
    {
        CECuentaCorrienteProveedor oCECuentaCorrienteT=(CECuentaCorrienteProveedor)TblListadoCuentasCorrientes.getValueAt(selectedRow,1);
         CECuentaCorrienteProveedor oCECuentaCorriente=CCCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle(oCECuentaCorrienteT.getIdProveedor(),oCECuentaCorrienteT.getIdMoneda());
         if(oCECuentaCorriente!=null)
         {
          DlgTransaccionCuentaCorrienteProveedor oDlgConsultaCuentaCorriente=new DlgTransaccionCuentaCorrienteProveedor(null,true,oCECuentaCorriente);
          oDlgConsultaCuentaCorriente.setLocationRelativeTo(null);
          oDlgConsultaCuentaCorriente.setVisible(true);
          listarCuentaCorrientes();
         }
   }
    }
    private void BtnNuevaCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevaCuentaCorrienteActionPerformed

          DlgNuevaCuentaCorrienteProveedor oDlgConsultaCuentaCorriente=new DlgNuevaCuentaCorrienteProveedor(null,true);
          oDlgConsultaCuentaCorriente.setLocationRelativeTo(null);
          oDlgConsultaCuentaCorriente.setVisible(true);
          listarCuentaCorrientes();

    }//GEN-LAST:event_BtnNuevaCuentaCorrienteActionPerformed

    private void TxtFiltroProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFiltroProveedorActionPerformed
limpiarTabla();
List<CECuentaCorrienteProveedor> oLstCuentaCorriente=CCCuentaCorrienteProveedor.listarCuentaCorrienteConFiltro(TxtFiltroProveedor.getText());
        int i=0;
        float suma=0;
        for(CECuentaCorrienteProveedor oCECuentaCorriente:oLstCuentaCorriente)
        {
            Vector oVector=new Vector();
            oVector.add(i+1);
            oVector.add(oCECuentaCorriente);
            oVector.add(oCECuentaCorriente.getMoneda());
            oVector.add(oCECuentaCorriente.getSaldo());
            oVector.add(oCECuentaCorriente.getTipoCambio());
            oVector.add(oCECuentaCorriente.getSaldoEquivalenteSoles());
            suma=suma+oCECuentaCorriente.getSaldoEquivalenteSoles();
            ((DefaultTableModel)TblListadoCuentasCorrientes.getModel()).addRow(oVector);
            i++;
        }
        LblNumeroCuentas.setText("Existen "+i+" cuenta(s) corrientes registradas");
        TxtTotalSoles.setText(suma+"");
        
    }//GEN-LAST:event_TxtFiltroProveedorActionPerformed

    private void BtnReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnReportarActionPerformed

    private void TxtFiltroProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFiltroProveedorKeyReleased

        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblListadoCuentasCorrientes.getRowCount()>0)
        {
          TblListadoCuentasCorrientes.requestFocus();
          TblListadoCuentasCorrientes.changeSelection(0, 0, false, false);
        }
        }
    }//GEN-LAST:event_TxtFiltroProveedorKeyReleased

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConsultar;
    private javax.swing.JButton BtnNuevaCuentaCorriente;
    private javax.swing.JButton BtnReportar;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JLabel LblNumeroCuentas;
    private javax.swing.JTable TblListadoCuentasCorrientes;
    private javax.swing.JTextField TxtFiltroProveedor;
    private javax.swing.JTextField TxtTotalSoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
