/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 03/09/2011, 12:32:33 AM
 */

package view.vtaVenta;


import controller.vtaVenta.CCPuntoVenta;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Katya
 */
public class DlgBusquedaPuntoVenta extends DialogoPadre {


 private CEPuntoVenta vCEPuntoVenta=new CEPuntoVenta();
 List<CEPuntoVenta> LstUnidadVenta;
  private CLValidarControles ovalidar;
  
    /** Creates new form NewJDialog */
    public DlgBusquedaPuntoVenta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarTabla();
    }


     private void cargarTabla()
    {
        limpiarTabla();

      LstUnidadVenta=CCPuntoVenta.listarPuntoVenta(0);

        if(LstUnidadVenta!=null)
            {

             for(int i=0;i<LstUnidadVenta.size();i++)
                 {
                 CEPuntoVenta oCEPuntoVenta=LstUnidadVenta.get(i);
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(oCEPuntoVenta);
                     oArrayList.add(oCEPuntoVenta.getIP());
                     oArrayList.add(oCEPuntoVenta.getSucursal());
                     oArrayList.add(oCEPuntoVenta.isSinocaja());
                      ((ArrayListTableModel)TblPuntoVenta.getModel()).addRow(oArrayList);
                }
            }

        }
     private void filtarTabla()
    {
         {
            limpiarTabla();
             for(int i=0;i<LstUnidadVenta.size();i++)
                 {
                 String cadenaFiltrada=TxtFiltrar.getText().toUpperCase();
                 String Descripcion=LstUnidadVenta.get(i).getDescrpcion().toUpperCase();
                 int indiceCadena=Descripcion.indexOf(cadenaFiltrada);
                    if(indiceCadena!=-1)
                    {
                     CEPuntoVenta oCEPuntoVenta=LstUnidadVenta.get(i);
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(oCEPuntoVenta);
                     oArrayList.add(oCEPuntoVenta.getIP());
                     oArrayList.add(oCEPuntoVenta.getSucursal());
                     oArrayList.add(oCEPuntoVenta.isSinocaja());
                     ((ArrayListTableModel)TblPuntoVenta.getModel()).addRow(oArrayList);
                }}
            }
    }

     private void limpiarTabla()
{
        // ArrayListTableModel oModelo=(ArrayListTableModel)TblCambioDia.getModel();
         int size =TblPuntoVenta.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              ((ArrayListTableModel)TblPuntoVenta.getModel()).removeRow(0);
            }
         }
     }

public void eventoSeleccionar()
    {
    if(TblPuntoVenta.getSelectedRow()!=-1)
        {
         vCEPuntoVenta=(CEPuntoVenta)TblPuntoVenta.getValueAt(TblPuntoVenta.getSelectedRow(), 0);
         dispose();
        }
}





    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtFiltrar = new TextField.JTxtLetra();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPuntoVenta = new javax.swing.JTable();
        btnAceptar1 = new BotonesABM.BtnAceptar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Unidad Venta");

        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true), "Busqueda Punto Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N
        PnlDatos.setForeground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Punto Venta ");

        TxtFiltrar.setText("jTxtLetra2");
        TxtFiltrar.setTamanio(45);
        TxtFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtFiltrarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TblPuntoVenta.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TblPuntoVenta.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion","Abreviatura","Sucursal","Caja"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class,java.lang.Object.class
                ,java.lang.String.class,java.lang.Boolean.class

            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPuntoVenta.setRowHeight(22);
        TblPuntoVenta.setSurrendersFocusOnKeystroke(true);
        TblPuntoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPuntoVentaMouseClicked(evt);
            }
        });
        TblPuntoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblPuntoVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblPuntoVentaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblPuntoVenta);

        btnAceptar1.setText("");
        btnAceptar1.setToolTipText("Seleccionar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TblPuntoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPuntoVentaMouseClicked

        if(evt.getClickCount()==2)
        eventoSeleccionar();
        
}//GEN-LAST:event_TblPuntoVentaMouseClicked

    private void TblPuntoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPuntoVentaKeyPressed

        if(TblPuntoVenta.getRowCount()>0){
            TblPuntoVenta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblPuntoVenta.getActionMap().put("ProjSave", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    eventoSeleccionar();
                }
            });
        }
}//GEN-LAST:event_TblPuntoVentaKeyPressed

    private void TblPuntoVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPuntoVentaKeyReleased
        
    }//GEN-LAST:event_TblPuntoVentaKeyReleased

    private void TxtFiltrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFiltrarKeyReleased
       filtarTabla();
    }//GEN-LAST:event_TxtFiltrarKeyReleased

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
       eventoSeleccionar();
}//GEN-LAST:event_btnAceptar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblPuntoVenta;
    private TextField.JTxtLetra TxtFiltrar;
    private BotonesABM.BtnAceptar btnAceptar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private static final String ACTION_CLOSE = "ACTION_CLOSE";
 private static final String ACTION_GUARDAR = "ACTION_NUEVO";




}
