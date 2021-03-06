/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgCorrelativo.java
 *
 * Created on 12-jun-2012, 14:22:38
 */

package view.vtaVenta;

import controller.vtaVenta.CCPuntoVenta;
import controller.vtaVenta.CCSerie;
import controller.vtaVenta.CCSucursal;
import controller.vtaVenta.CCTipoComprobante;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.grlGeneral.entidad.CEImpresora;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import modelo.vtaVenta.entidad.CESerie;
import modelo.vtaVenta.entidad.CESucursal;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Morales
 */
public class DlgCorrelativo extends DialogoPadre {

    /** Creates new form DlgCorrelativo */

    List<CESerie> listaEliminados= new ArrayList<CESerie>();    
    java.awt.Frame vparent;
    public DlgCorrelativo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        vparent=parent;
        CbxSucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
        int idSucursal=((CESucursal)CbxSucursal.getSelectedItem()).getIdSucursal();
        CbxPuntoVenta.setModel(CLComboBox.cargarCombo(CCPuntoVenta.listarPuntoVenta_Todo(idSucursal)));
        tblSerie.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtNum = tblSerie.getColumnModel().getColumn(2);
        columnaTxtNum.setCellEditor(new DefaultCellEditor(jTxtNumero1));
        TableColumn columnaTxtCorrelativo = tblSerie.getColumnModel().getColumn(3);
        columnaTxtCorrelativo.setCellEditor(new DefaultCellEditor(jTxtNumero1));

        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaAll()));
        CEImpresora oCEImpresora =new CEImpresora();
        CbxImpresora.setModel(CLComboBox.cargarCombo(oCEImpresora.ListarImpresorasConNiguno()));
        TableColumn columnaCbxImpresora = tblSerie.getColumnModel().getColumn(5);
        columnaCbxImpresora.setCellEditor(new DefaultCellEditor(CbxImpresora));
        TableColumn columnaTxtTipoDescuento = tblSerie.getColumnModel().getColumn(1);
        columnaTxtTipoDescuento.setCellEditor(new DefaultCellEditor(CbxTipoComprobante));
        cargarSerie();
        
    }


    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTxtNumero1 = new TextField.JTxtNumero();
        CbxTipoComprobante = new ComboBox.ComboBox();
        CbxImpresora = new ComboBox.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSerie = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{

                    super.setValueAt(value,row,column);

                }
                catch(Exception e)
                {
                    if(this.getRowCount()>row){
                        super.setValueAt(null,row,column);
                    }

                }
            }

        };
        label3 = new Label.Label();
        PnlBotones = new javax.swing.JPanel();
        BtnCancelar = new BotonesABM.BtnCancelar();
        BtnEliminar = new BotonesABM.BtnEliminar();
        BtnEditar = new BotonesABM.BtnEditar();
        BtnNuevo = new BotonesABM.BtnNuevo();
        CbxPuntoVenta = new ComboBox.ComboBox();
        label12 = new Label.Label();
        jButton2 = new javax.swing.JButton();
        CbxSucursal = new ComboBox.ComboBox();
        label13 = new Label.Label();
        jButton3 = new javax.swing.JButton();

        jTxtNumero1.setText("jTxtNumero1");
        jTxtNumero1.setTamanio(9);

        CbxTipoComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoComprobanteItemStateChanged(evt);
            }
        });
        CbxTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoComprobanteActionPerformed(evt);
            }
        });

        CbxImpresora.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxImpresoraItemStateChanged(evt);
            }
        });
        CbxImpresora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxImpresoraActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Correlativos");

        tblSerie.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tblSerie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N º", "Tipo Comprobante", "Numero Serie", "Numero Correlativo", "Alto Comp", "Ancho Comp", "Impresora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSerie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblSerie.setRowHeight(22);
        tblSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSerieKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSerie);
        tblSerie.getColumnModel().getColumn(0).setResizable(false);
        tblSerie.getColumnModel().getColumn(0).setPreferredWidth(35);
        tblSerie.getColumnModel().getColumn(1).setResizable(false);
        tblSerie.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSerie.getColumnModel().getColumn(2).setResizable(false);
        tblSerie.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblSerie.getColumnModel().getColumn(3).setResizable(false);
        tblSerie.getColumnModel().getColumn(3).setPreferredWidth(115);
        tblSerie.getColumnModel().getColumn(4).setResizable(false);
        tblSerie.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblSerie.getColumnModel().getColumn(5).setResizable(false);
        tblSerie.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblSerie.getColumnModel().getColumn(6).setResizable(false);
        tblSerie.getColumnModel().getColumn(6).setPreferredWidth(280);

        label3.setText("Gestion Puntos de Venta");
        label3.setFont(new java.awt.Font("Verdana", 1, 14));

        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlBotonesLayout = new javax.swing.GroupLayout(PnlBotones);
        PnlBotones.setLayout(PnlBotonesLayout);
        PnlBotonesLayout.setHorizontalGroup(
            PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PnlBotonesLayout.setVerticalGroup(
            PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlBotonesLayout.createSequentialGroup()
                .addGroup(PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CbxPuntoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxPuntoVentaActionPerformed(evt);
            }
        });

        label12.setText("Punto Venta");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CbxSucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxSucursalItemStateChanged(evt);
            }
        });
        CbxSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxSucursalActionPerformed(evt);
            }
        });

        label13.setText("Sucursal     ");
        label13.setFont(new java.awt.Font("Verdana", 1, 12));

        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 102));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(PnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CbxPuntoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(524, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxPuntoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void cargarSerie()
    {
          limpiarTabla();
           CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)CbxPuntoVenta.getSelectedItem();
           List<CESerie> lista=CCSerie.consultarSerieComprobantePorIdPuntoVenta(oCEPuntoVenta.getIdPuntoVenta());

           if(lista!=null)
           {
            int i=1;
            for (CESerie cESerie : lista) {

                 Vector oVector = new Vector();             
                 oVector.add(i);
                 oVector.add(cESerie.getTipoComprobante());
                 oVector.add(cESerie);
                 oVector.add(cESerie.getUltimoNumeroGenerado());
                 oVector.add(cESerie.getAlto());
                 oVector.add(cESerie.getAncho());
                 oVector.add(cESerie.getNombreImpresora());
                 

                ((DefaultTableModel)tblSerie.getModel()).addRow(oVector);
                i++;
            }
            if(tblSerie.getRowCount()>0)
            {
                tblSerie.requestFocus();
                tblSerie.changeSelection(0, 0, false, false);
            }

        }
    }

 
    private void tblSerieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSerieKeyPressed

        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            }

    }//GEN-LAST:event_tblSerieKeyPressed

    private void buscarPuntoVenta()
    {
       DlgBusquedaPuntoVenta   dialogo=new DlgBusquedaPuntoVenta(vparent, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        cargarSerie();
    }
    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged

    
}//GEN-LAST:event_CbxTipoComprobanteItemStateChanged

    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed


}//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void CbxImpresoraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxImpresoraItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxImpresoraItemStateChanged

    private void CbxImpresoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxImpresoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxImpresoraActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        //Eventocancelar();
}//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        if(tblSerie.getSelectedRow()!=-1&&tblSerie.getRowCount()>0)
        {
            int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Eliminar el registro?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION)
            {
                CESerie oCESerie=(CESerie)tblSerie.getValueAt(tblSerie.getSelectedRow(), 2);
                if(CCSerie.DELPuntoVentaComprobante(oCESerie)) {
                    JOptionPane.showMessageDialog(null,"Operación éxitosa");
                    cargarSerie();
                } else {
                    JOptionPane.showMessageDialog(null,"Operación Fallida");
                }
             }
        }
        else
       {
            JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }


}//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed

        if(tblSerie.getSelectedRow()!=-1&&tblSerie.getRowCount()>0)
        {
            CESerie oCESerie=(CESerie)tblSerie.getValueAt(tblSerie.getSelectedRow(), 2);
            DlgPuntoVentaComprobante odialogo = new DlgPuntoVentaComprobante(vparent, true,oCESerie,false);
            odialogo.setLocationRelativeTo(null);
            odialogo.setVisible(true);
            cargarSerie();
        }
        else
       {
            JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed

        if(tblSerie.getSelectedRow()!=-1&&tblSerie.getRowCount()>0)
        {
        CESerie oCESerie=(CESerie)tblSerie.getValueAt(tblSerie.getSelectedRow(), 2);
        DlgPuntoVentaComprobante odialogo = new DlgPuntoVentaComprobante(vparent, true,oCESerie,true);
        odialogo.setLocationRelativeTo(null);
        odialogo.setVisible(true);
        cargarSerie();
        }
        else
        {
           JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        
        }
}//GEN-LAST:event_BtnNuevoActionPerformed

    private void CbxPuntoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxPuntoVentaActionPerformed
 cargarSerie();
}//GEN-LAST:event_CbxPuntoVentaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

         DlgPuntoVenta odialogo = new DlgPuntoVenta(vparent, true,true);
        odialogo.setLocationRelativeTo(null);
        odialogo.setCerrarVentana(true);
        odialogo.setVisible(true);

}//GEN-LAST:event_jButton2ActionPerformed

    private void CbxSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxSucursalItemStateChanged

    }//GEN-LAST:event_CbxSucursalItemStateChanged

    private void CbxSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxSucursalActionPerformed

        int idSucursal=((CESucursal)CbxSucursal.getSelectedItem()).getIdSucursal();
        CbxPuntoVenta.setModel(CLComboBox.cargarCombo(CCPuntoVenta.listarPuntoVenta_Todo(idSucursal)));

    }//GEN-LAST:event_CbxSucursalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    public void limpiarTabla()
    {
        int filas=tblSerie.getRowCount();

        if(filas>0)
            for(int i=0;i<filas;i++)
            {
                ((DefaultTableModel)tblSerie.getModel()).removeRow(0);
            }
    }
    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnCancelar BtnCancelar;
    private BotonesABM.BtnEditar BtnEditar;
    private BotonesABM.BtnEliminar BtnEliminar;
    private BotonesABM.BtnNuevo BtnNuevo;
    private ComboBox.ComboBox CbxImpresora;
    private ComboBox.ComboBox CbxPuntoVenta;
    private ComboBox.ComboBox CbxSucursal;
    private ComboBox.ComboBox CbxTipoComprobante;
    private javax.swing.JPanel PnlBotones;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private TextField.JTxtNumero jTxtNumero1;
    private Label.Label label12;
    private Label.Label label13;
    private Label.Label label3;
    private javax.swing.JTable tblSerie;
    // End of variables declaration//GEN-END:variables

}
