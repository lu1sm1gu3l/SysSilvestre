package view.ctbContabilidad;

import controller.ctbContabilidad.CCCuentaCorrienteProveedor;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import modelo.ctbContabilidad.entidad.CECuentaCorrienteProveedor;
import modelo.ctbContabilidad.entidad.CEMovimientoCuentaProveedor;
import util.clases.grlGeneral.DialogoPadre;

public class DlgTransaccionCuentaCorrienteProveedor extends DialogoPadre {
    private CECuentaCorrienteProveedor oCECuentaCorriente;
    public DlgTransaccionCuentaCorrienteProveedor(java.awt.Frame parent, boolean modal,CECuentaCorrienteProveedor oCECuentaCorriente) {
        super(parent, modal);
        initComponents();
        this.oCECuentaCorriente=oCECuentaCorriente;
        cargarCuentaCorriente();
    }
    private void limpiarTabla()
    {
        int size=TblTransacciones.getRowCount();
        for(int i=0;i<size;i++)
        {
            ((DefaultTableModel)TblTransacciones.getModel()).removeRow(0);
        }
    }
    private void cargarCuentaCorriente()
    {
        limpiarTabla();
        LblProveedor.setText(oCECuentaCorriente.getProveedor());
        LblDni.setText(oCECuentaCorriente.getDni());
        LblMoneda.setText(oCECuentaCorriente.getMoneda());
        LblSaldo.setText(oCECuentaCorriente.getSaldo()+"");
        int i=0;
        for(CEMovimientoCuentaProveedor oCEMovimientoCuenta:oCECuentaCorriente.getoLstMovimientoCuenta())
        {
            Vector oVector=new Vector();
            oVector.add(i+1);
            oVector.add(oCEMovimientoCuenta.getFecha());
            oVector.add(oCEMovimientoCuenta.getTipoTransaccion());
            oVector.add(oCEMovimientoCuenta.getTipoMovimiento());            
            oVector.add(oCEMovimientoCuenta.getNumComprobanteVenta());
            oVector.add(oCEMovimientoCuenta.getMonto());
            ((DefaultTableModel)TblTransacciones.getModel()).addRow(oVector);
            i++;
        }
        LblNumeroTransacciones.setText(i+" Transaccion(es) realizadas");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TblTransacciones = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LblProveedor = new javax.swing.JLabel();
        LblDni = new javax.swing.JLabel();
        LblMoneda = new javax.swing.JLabel();
        LblSaldo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LblNumeroTransacciones = new javax.swing.JLabel();
        BtnDeposito = new javax.swing.JButton();
        BtnRetiro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TblTransacciones.setFont(new java.awt.Font("Verdana", 0, 11));
        TblTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Fecha", "Tipo Trans.", "Tipo Mov.", "Nº Comprb.", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
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
        TblTransacciones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblTransacciones.setRowHeight(22);
        jScrollPane1.setViewportView(TblTransacciones);
        TblTransacciones.getColumnModel().getColumn(0).setPreferredWidth(40);
        TblTransacciones.getColumnModel().getColumn(1).setPreferredWidth(130);
        TblTransacciones.getColumnModel().getColumn(2).setPreferredWidth(80);
        TblTransacciones.getColumnModel().getColumn(3).setPreferredWidth(180);
        TblTransacciones.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblTransacciones.getColumnModel().getColumn(5).setPreferredWidth(75);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de la Cuenta Corriente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12), new java.awt.Color(0, 0, 51))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Moneda    :");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Proveedor :");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("RUC          :");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("Saldo        :");

        LblProveedor.setFont(new java.awt.Font("Verdana", 0, 12));
        LblProveedor.setText("------------------------------------------------------------------");

        LblDni.setFont(new java.awt.Font("Verdana", 0, 12));
        LblDni.setText("-------------------");

        LblMoneda.setFont(new java.awt.Font("Verdana", 0, 12));
        LblMoneda.setText("-------------------");

        LblSaldo.setFont(new java.awt.Font("Verdana", 0, 12));
        LblSaldo.setText("-------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LblDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LblMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LblProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LblDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LblMoneda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LblSaldo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Transacciones");

        LblNumeroTransacciones.setFont(new java.awt.Font("Verdana", 0, 12));
        LblNumeroTransacciones.setForeground(new java.awt.Color(102, 0, 0));
        LblNumeroTransacciones.setText("20 Transaccion(es) realizadas.");

        BtnDeposito.setFont(new java.awt.Font("Verdana", 0, 14));
        BtnDeposito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/arriba.png"))); // NOI18N
        BtnDeposito.setText("Depósito");
        BtnDeposito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnDeposito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDepositoActionPerformed(evt);
            }
        });

        BtnRetiro.setFont(new java.awt.Font("Verdana", 0, 14));
        BtnRetiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/abajo.png"))); // NOI18N
        BtnRetiro.setText("Retiro");
        BtnRetiro.setEnabled(false);
        BtnRetiro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRetiro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRetiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addComponent(LblNumeroTransacciones, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnDeposito, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(BtnRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(BtnDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnRetiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblNumeroTransacciones)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDepositoActionPerformed
     DlgDepositoCuentaCorrienteProveedor oDlgDepositoCuentaCorriente=new DlgDepositoCuentaCorrienteProveedor(null,true,oCECuentaCorriente);
     oDlgDepositoCuentaCorriente.setLocationRelativeTo(null);
     oDlgDepositoCuentaCorriente.setVisible(true);
     this.oCECuentaCorriente=CCCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle(oCECuentaCorriente.getIdProveedor(),oCECuentaCorriente.getIdMoneda());
     cargarCuentaCorriente();
    }//GEN-LAST:event_BtnDepositoActionPerformed

    private void BtnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRetiroActionPerformed
     DlgRetiroCuentaCorrienteProveedor oDlgDepositoCuentaCorriente=new DlgRetiroCuentaCorrienteProveedor(null,true,oCECuentaCorriente);
     oDlgDepositoCuentaCorriente.setLocationRelativeTo(null);
     oDlgDepositoCuentaCorriente.setVisible(true);
     this.oCECuentaCorriente=CCCuentaCorrienteProveedor.consultarCuentaCorrientePorProveedorConDetalle(oCECuentaCorriente.getIdProveedor(),oCECuentaCorriente.getIdMoneda());
     cargarCuentaCorriente();
    }//GEN-LAST:event_BtnRetiroActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDeposito;
    private javax.swing.JButton BtnRetiro;
    private javax.swing.JLabel LblDni;
    private javax.swing.JLabel LblMoneda;
    private javax.swing.JLabel LblNumeroTransacciones;
    private javax.swing.JLabel LblProveedor;
    private javax.swing.JLabel LblSaldo;
    private javax.swing.JTable TblTransacciones;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
