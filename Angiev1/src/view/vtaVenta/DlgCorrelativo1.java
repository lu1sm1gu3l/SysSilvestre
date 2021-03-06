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
import controller.vtaVenta.CCTipoComprobante;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.grlGeneral.datos.CDCorrelativo;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import modelo.vtaVenta.entidad.CESerie;
import modelo.vtaVenta.entidad.CETipoComprobante;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Morales
 */
public class DlgCorrelativo1 extends DialogoPadre {

    /** Creates new form DlgCorrelativo */

    List<CESerie> listaEliminados= new ArrayList<CESerie>();

    public DlgCorrelativo1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tblSerie.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtNum = tblSerie.getColumnModel().getColumn(2);
        columnaTxtNum.setCellEditor(new DefaultCellEditor(jTxtNumero1));
        TableColumn columnaTxtCorrelativo = tblSerie.getColumnModel().getColumn(3);
        columnaTxtCorrelativo.setCellEditor(new DefaultCellEditor(jTxtNumero1));
        ((JTableX)tblSerie).setSelectAllForEdit(true);
        CbxTipoComp.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listarTodos()));
        cargarSerie();
        cargarPuntoVenta();
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
        btnGuardar1 = new BotonesABM.BtnGuardar();
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
        CbxTipoComp = new ComboBox.ComboBox();
        label3 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPuntoVenta = new javax.swing.JTable();
        btnCancelar1 = new BotonesABM.BtnCancelar();

        jTxtNumero1.setText("jTxtNumero1");
        jTxtNumero1.setTamanio(9);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Correlativos");

        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        tblSerie.setFont(new java.awt.Font("Verdana", 0, 12));
        tblSerie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N º", "id", "Numero Serie", "Numero Correlativo", "Punto Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSerie.setRowHeight(22);
        tblSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSerieKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSerie);
        tblSerie.getColumnModel().getColumn(0).setResizable(false);
        tblSerie.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblSerie.getColumnModel().getColumn(1).setMinWidth(0);
        tblSerie.getColumnModel().getColumn(1).setPreferredWidth(0);
        tblSerie.getColumnModel().getColumn(1).setMaxWidth(0);
        tblSerie.getColumnModel().getColumn(2).setResizable(false);
        tblSerie.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblSerie.getColumnModel().getColumn(3).setResizable(false);
        tblSerie.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblSerie.getColumnModel().getColumn(4).setResizable(false);

        CbxTipoComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoCompActionPerformed(evt);
            }
        });

        label3.setText("Tipo Comprobante");

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setToolTipText("Agregar Item");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/DeleteUser.png"))); // NOI18N
        BtnQuitar.setToolTipText("Quitar Item");
        BtnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarActionPerformed(evt);
            }
        });

        jButton1.setText("<<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblPuntoVenta.setFont(new java.awt.Font("Verdana", 0, 12));
        tblPuntoVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Comprobante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPuntoVenta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPuntoVenta.setRowHeight(22);
        jScrollPane3.setViewportView(tblPuntoVenta);
        tblPuntoVenta.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblPuntoVenta.getColumnModel().getColumn(1).setPreferredWidth(180);

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
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CbxTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CbxTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButton1)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private List<CESerie> getSerie()
    {

        List<CESerie> lista= new ArrayList<CESerie>();
        CESerie oCESerie=null;

        for(int i=0;i<tblSerie.getRowCount();i++)
        {
            oCESerie=(CESerie)tblSerie.getValueAt(i, 1);
            if(oCESerie==null)
            {
                oCESerie=new CESerie();
            }
            CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)tblSerie.getValueAt(i, 4);
            oCESerie.setoCEPuntoVenta(oCEPuntoVenta);
            oCESerie.setAccionabm(1);
            oCESerie.setNumero(""+tblSerie.getValueAt(i, 2));
            oCESerie.setUltimoNumeroGenerado(Long.parseLong(tblSerie.getValueAt(i, 3).toString()));
            oCESerie.setIdTipoComprobante(((CETipoComprobante)(CbxTipoComp.getSelectedItem())).getIdTipoComprobante());
            lista.add(oCESerie);

        }

        lista.addAll(listaEliminados);
        return lista;

    }
    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed

        List<CESerie> lista=getSerie();
        int correcto=CDCorrelativo.Guardar(lista);
        if(correcto==1)
        {
            listaEliminados= new ArrayList<CESerie>();
            cargarSerie();
            JOptionPane.showMessageDialog(this, "Operación Exitosa");
        }
            else JOptionPane.showMessageDialog(this, "Operación Fallida");

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void cargarSerie()
    {
      limpiarTabla();
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComp.getSelectedItem();
        if(oCETipoComprobante!=null)
        {
            List<CESerie> lista=CCSerie.consultarSeriePorComp(oCETipoComprobante.getIdTipoComprobante());

            int i=1;
            for (CESerie cESerie : lista) {

                 Vector oVector = new Vector();
                 oVector.add(i);
                 oVector.add(cESerie);
                 oVector.add(cESerie.getNumero());
                 oVector.add(cESerie.getUltimoNumeroGenerado());
                 oVector.add(cESerie.getoCEPuntoVenta());

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

    private void cargarPuntoVenta()
    {
     

            List<CEPuntoVenta> lista=CCPuntoVenta.listarPuntoVenta(0);

            for (CEPuntoVenta oCEPuntoVenta : lista) {

                 Vector oVector = new Vector();
                 oVector.add(oCEPuntoVenta);
                 oVector.add(oCEPuntoVenta.getIP());


                ((DefaultTableModel)tblPuntoVenta.getModel()).addRow(oVector);
            }

            tblPuntoVenta.requestFocus();
            tblPuntoVenta.changeSelection(0, 0, false, false);

        
    }
    private void CbxTipoCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoCompActionPerformed

       cargarSerie();
    }//GEN-LAST:event_CbxTipoCompActionPerformed

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        Vector oVector = new Vector();
                 oVector.add(tblSerie.getRowCount()+1);
                ((DefaultTableModel)tblSerie.getModel()).addRow(oVector);
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        if(tblSerie.getSelectedRow()!=-1){
               CESerie oCESerie=(CESerie)tblSerie.getValueAt(tblSerie.getSelectedRow(), 1);
               oCESerie.setAccionabm(3);
                listaEliminados.add(oCESerie);
                ((DefaultTableModel)tblSerie.getModel()).removeRow(tblSerie.getSelectedRow());
         }
         else
        {
        JOptionPane.showMessageDialog(this, "Sellecione una fila");
        }
}//GEN-LAST:event_BtnQuitarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        int rowsel=tblPuntoVenta.getSelectedRow();
        if(rowsel!=-1){
         CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)tblPuntoVenta.getValueAt(tblPuntoVenta.getSelectedRow(), 0);

             if(tblSerie.getSelectedRow()!=-1){

                tblSerie.setValueAt(oCEPuntoVenta, tblSerie.getSelectedRow(), 4);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void tblSerieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSerieKeyPressed

        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            }

    }//GEN-LAST:event_tblSerieKeyPressed


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
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxTipoComp;
    private BotonesABM.BtnCancelar btnCancelar1;
    private BotonesABM.BtnGuardar btnGuardar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private TextField.JTxtNumero jTxtNumero1;
    private Label.Label label3;
    private javax.swing.JTable tblPuntoVenta;
    private javax.swing.JTable tblSerie;
    // End of variables declaration//GEN-END:variables

}
