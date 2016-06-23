package view.tsoTesoreria;

import controller.tsoTesoreria.CCCuadreCaja;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCPuntoVenta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.tsoTesoreria.entidad.CECuadreCaja;
import modelo.tsoTesoreria.entidad.CECuadreCajaDetalle;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.MiRelojHora;
import view.FrmSistemaMenu;

public class DlgAperturaCaja extends javax.swing.JDialog {

    private MiRelojHora hilo;

    public DlgAperturaCaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CbxCaja.setModel(CLComboBox.cargarCombo(CCPuntoVenta.listarCaja(FrmSistemaMenu.getIdSucursal())));
        LblFechaActual.setText(CLConsultarFechaSistema.consultarFecha());
        hilo = new MiRelojHora(LblHoraSistema);
        hilo.start();
        TblMoneda.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        ((JTableX)TblMoneda).setSelectAllForEdit(true);
        CargarTabla();
        cargarCuadreCaja();
    }

    private void CargarTabla()
    {
        ArrayList<CEMoneda> listaMoneda=CCMoneda.listaAll();
        for (CEMoneda oCEMoneda : listaMoneda)
                    {
                        Vector oVector=new Vector();
                            oVector.add(oCEMoneda);
                            ((DefaultTableModel)TblMoneda.getModel()).addRow(oVector);

                    }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnAceptar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblMoneda = new util.clases.almAlmacen.JTableX();
        CbxCaja = new ComboBox.ComboBox();
        label12 = new Label.Label();
        label13 = new Label.Label();
        Lblestado = new Label.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();
        label14 = new Label.Label();
        LblHoraSistema = new javax.swing.JLabel();
        LblFechaActual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apertura de Caja");

        BtnAceptar.setFont(new java.awt.Font("Verdana", 0, 24));
        BtnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/programas-gratis.png"))); // NOI18N
        BtnAceptar.setText("Aceptar");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

        BtnCancelar.setFont(new java.awt.Font("Verdana", 0, 24));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/exit.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        TblMoneda.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TblMoneda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Moneda", "Monto Efectivo Inicial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TblMoneda.setRowHeight(22);
        TblMoneda.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(TblMoneda);
        TblMoneda.getColumnModel().getColumn(0).setPreferredWidth(75);
        TblMoneda.getColumnModel().getColumn(1).setPreferredWidth(95);

        CbxCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxCajaActionPerformed(evt);
            }
        });

        label12.setForeground(new java.awt.Color(153, 0, 0));
        label12.setText("Caja    : ");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        label13.setForeground(new java.awt.Color(153, 0, 0));
        label13.setText("Estado   : ");
        label13.setFont(new java.awt.Font("Verdana", 1, 12));

        Lblestado.setText("No Iniciado");
        Lblestado.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtObservacion.setColumns(20);
        TxtObservacion.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtObservacion.setRows(5);
        jScrollPane2.setViewportView(TxtObservacion);

        label14.setForeground(new java.awt.Color(153, 0, 0));
        label14.setText("Observación    : ");
        label14.setFont(new java.awt.Font("Verdana", 1, 12));

        LblHoraSistema.setFont(new java.awt.Font("Calibri", 1, 40));
        LblHoraSistema.setForeground(new java.awt.Color(153, 0, 0));
        LblHoraSistema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblHoraSistema.setText("02:09:12");

        LblFechaActual.setFont(new java.awt.Font("Calibri", 1, 40));
        LblFechaActual.setForeground(new java.awt.Color(0, 0, 51));
        LblFechaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblFechaActual.setText("26/09/2010");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(Lblestado, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnCancelar)
                            .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(LblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Lblestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed

       CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)CbxCaja.getSelectedItem();
        if(pIdestado==0){
         int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Aperturar la "+oCEPuntoVenta.getDescrpcion()+"?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION)
        {
           
                
                CECuadreCaja oCECuadreCaja= new CECuadreCaja();
                oCECuadreCaja.setIdPuntoVenta(oCEPuntoVenta.getIdPuntoVenta());
                oCECuadreCaja.setIdSucursal(FrmSistemaMenu.getIdSucursal());
                oCECuadreCaja.setUltimoIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
                oCECuadreCaja.setObservacion(TxtObservacion.getText());
                List<CECuadreCajaDetalle> listaCECuadreCajaDetalle=new ArrayList<CECuadreCajaDetalle>();
                CECuadreCajaDetalle oCECuadreCajaDetalle;
                for (int i = 0; i < TblMoneda.getRowCount(); i++) {
                   if(TblMoneda.getValueAt(i, 1)!=null){
                        CEMoneda oCEMoneda=(CEMoneda)TblMoneda.getValueAt(i, 0);
                        oCECuadreCajaDetalle= new CECuadreCajaDetalle();
                        oCECuadreCajaDetalle.setIdMoneda(oCEMoneda.getId_moneda());
                        oCECuadreCajaDetalle.setMontoEfectivoInicial(BigDecimal.valueOf(Double.parseDouble(TblMoneda.getValueAt(i, 1).toString())));
                        listaCECuadreCajaDetalle.add(oCECuadreCajaDetalle);
                    }
                    oCECuadreCaja.setLstCECuadreCajaDetalle(listaCECuadreCajaDetalle);

                }
                if(CCCuadreCaja.INSCuadreCaja(oCECuadreCaja)==1){
                    JOptionPane.showMessageDialog(null,"Se Aperturó la "+oCEPuntoVenta.getDescrpcion());
                     cargarCuadreCaja();
                }
                    else
                {
                    JOptionPane.showMessageDialog(null,"Se Canceló la operación");
                }

            }
        }
       else
            {
                 JOptionPane.showMessageDialog(null,"Se Canceló la operación, La "+oCEPuntoVenta.getDescrpcion()+" ya esta aperturada");
            }
    }//GEN-LAST:event_BtnAceptarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void CbxCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxCajaActionPerformed
    cargarCuadreCaja();
}//GEN-LAST:event_CbxCajaActionPerformed
    private long pIdestado=0;
    private void limpiarTabla()
    {

         int size =TblMoneda.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              TblMoneda.setValueAt(null, i, 1);
            }
         }
     }
    private void cargarCuadreCaja()
    {

        limpiarTabla();
        CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)CbxCaja.getSelectedItem();
        CECuadreCaja oCECuadreCaja= CCCuadreCaja.ObtenerCuadreCajaApertura(oCEPuntoVenta.getIdPuntoVenta());
        if(oCECuadreCaja!=null){
            Lblestado.setText(oCECuadreCaja.getUltimoEstado());
            pIdestado=oCECuadreCaja.getUltimoIdEstado();
                    for (CECuadreCajaDetalle oCECuadreCajaDetalle : oCECuadreCaja.getLstCECuadreCajaDetalle())
                    {
                        int fila=ObtenerfilaIdMoneda(oCECuadreCajaDetalle.getIdMoneda());
                        if(fila!=-1){
                            TblMoneda.setValueAt(oCECuadreCajaDetalle.getMontoEfectivoInicial(), fila, 1);
                        }
                        
                    }
        }
        else
            {
             Lblestado.setText("NO INCIADO");
             pIdestado=0;
            }
    
    }
    private int ObtenerfilaIdMoneda(int pIdMoneda)
    {
        for(int i=0;i<TblMoneda.getRowCount();i++){
            CEMoneda ocEMoneda=(CEMoneda)TblMoneda.getValueAt(i, 0);
            if(ocEMoneda.getId_moneda()==pIdMoneda)
            {
                return i;
            }
        }
        return -1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JButton BtnCancelar;
    private ComboBox.ComboBox CbxCaja;
    private javax.swing.JLabel LblFechaActual;
    private javax.swing.JLabel LblHoraSistema;
    private Label.Label Lblestado;
    private javax.swing.JTable TblMoneda;
    private javax.swing.JTextArea TxtObservacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private Label.Label label12;
    private Label.Label label13;
    private Label.Label label14;
    // End of variables declaration//GEN-END:variables
}
