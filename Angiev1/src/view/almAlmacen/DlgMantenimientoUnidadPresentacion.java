/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJDialog.java
 *
 * Created on 03/09/2011, 12:32:33 AM
 */

package view.almAlmacen;

import controller.almAlmacen.CCUnidadPresentacionVenta;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.almAlmacen.entidad.CEUnidadPresentacionVenta;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;

/**
 *
 * @author Katya
 */
public class DlgMantenimientoUnidadPresentacion extends DialogoPadre implements ActionListener{

int AccionABM=0; //(0) cancelado (1) nuevo (2) editar
int AccionDialogo=0; // pacccopdialogo 1= agregar desde otro formulario 0 = mantenimiento
 private CEUnidadPresentacionVenta vCEUnidadPresentacionVenta=new CEUnidadPresentacionVenta();
 private List<CEUnidadPresentacionVenta> LstUnidadPresentacion;
  private CLValidarControles ovalidar;
    /** Creates new form NewJDialog */
    public DlgMantenimientoUnidadPresentacion(java.awt.Frame parent, boolean modal,int paccioDialogo) 
    {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        ovalidar= new CLValidarControles();
        AccionDialogo=paccioDialogo;
        ovalidar.SetListCompnente(new Component[]{TxtAbrev,TxtNombre}, PnlDatos);
        BtnNuevo.requestFocus();
        if(paccioDialogo==1){
        controladorDeEventosBotonesABM(1);
        AccionABM=1;}
        else{ controladorDeEventosBotonesABM(0);}
        cargarTabla();

        
    }

    private void limpiarCajas()
    {
        TxtNombre.setText("");
        TxtAbrev.setText("");
    }
    private void habilitarCajas(boolean bol)
    {
        TxtNombre.setEnabled(bol);
        TxtAbrev.setEnabled(bol);
    }
    private void FillCajas()
    {
        TxtNombre.setText(vCEUnidadPresentacionVenta.getDescripcion());
        TxtAbrev.setText(vCEUnidadPresentacionVenta.getAbreviatura());
    }
     private void habilitarBotonesABM(boolean cNuevo,boolean cGuardar,boolean cEditar,boolean cEliminar,boolean cCancelar)
    {
        BtnNuevo.setEnabled(cNuevo);
        BtnGuardar.setEnabled(cGuardar);
        BtnEditar.setEnabled(cEditar);
        BtnEliminar.setEnabled(cEliminar);
        BtnCancelar.setEnabled(cCancelar);
    }
    private void controladorDeEventosBotonesABM(int operacion)
      { if(operacion==0) // iniio de aplicacion
          {
            habilitarCajas(false);
            habilitarBotonesABM(true,false,true,true,true);


          }
        else
          { if(operacion==1) // boton nuevo
                {

                TxtNombre.requestFocus();
                TblUnidadPresentacion.clearSelection();
                limpiarCajas();
                habilitarBotonesABM(false,true,false,false,true);
                habilitarCajas(true);
                TblUnidadPresentacion.setEnabled(true);
                }
                else
                  {
                    if(operacion==2) // boton consultar
                    {
                        habilitarBotonesABM(false,true,true,true,true);
                    }
                  }
          }
    }


     private void cargarTabla()
    {
        limpiarTabla();

      LstUnidadPresentacion=CCUnidadPresentacionVenta.consultarListaUnidadPresentacionVentas();

        if(LstUnidadPresentacion!=null)
            {

             for(int i=0;i<LstUnidadPresentacion.size();i++)
                 {
                 CEUnidadPresentacionVenta oCEUnidadPresentacionVenta=LstUnidadPresentacion.get(i);
                   // oCEUnidadPresentacionVenta.setIsmantenimiento(true);
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(oCEUnidadPresentacionVenta);
                     oArrayList.add(oCEUnidadPresentacionVenta.getAbreviatura());
                      ((ArrayListTableModel)TblUnidadPresentacion.getModel()).addRow(oArrayList);
                }
            }

        }
     private void filtarTabla()
    {
         {
            limpiarTabla();
             for(int i=0;i<LstUnidadPresentacion.size();i++)
                 {
                 String cadenaFiltrada=TxtFiltrar.getText().toUpperCase();
                 String Descripcion=LstUnidadPresentacion.get(i).getDescripcion().toUpperCase();
                 int indiceCadena=Descripcion.indexOf(cadenaFiltrada);
                    if(indiceCadena!=-1)
                    {
                     CEUnidadPresentacionVenta oCEUnidadPresentacionVenta=LstUnidadPresentacion.get(i);
                   //  oCEUnidadPresentacionVenta.setIsmantenimiento(true);
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(oCEUnidadPresentacionVenta);
                     oArrayList.add(oCEUnidadPresentacionVenta.getAbreviatura());
                     ((ArrayListTableModel)TblUnidadPresentacion.getModel()).addRow(oArrayList);
                }}
            }
    }

     private void limpiarTabla()
{
        // ArrayListTableModel oModelo=(ArrayListTableModel)TblCambioDia.getModel();
         int size =TblUnidadPresentacion.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              ((ArrayListTableModel)TblUnidadPresentacion.getModel()).removeRow(0);
            }
         }
     }

public void eventoSeleccionar()
    {
    if(TblUnidadPresentacion.getSelectedRow()!=-1)
        {
        if(AccionABM!=1){
         vCEUnidadPresentacionVenta=(CEUnidadPresentacionVenta)TblUnidadPresentacion.getValueAt(TblUnidadPresentacion.getSelectedRow(), 0);
         FillCajas();
         controladorDeEventosBotonesABM(0);
            }
        }
}
private void eventoGuardar()
    {

        ovalidar.validarContorles();
        if(ovalidar.getisVacio()){
        ovalidar.ReiniciarCajas();
        CEUnidadPresentacionVenta oCEUnidadPresentacionVenta= new CEUnidadPresentacionVenta();
        oCEUnidadPresentacionVenta.setDescripcion(TxtNombre.getText());
        oCEUnidadPresentacionVenta.setAbreviatura(TxtAbrev.getText());

        oCEUnidadPresentacionVenta.setIdUnidadPresentacionVenta(vCEUnidadPresentacionVenta.getIdUnidadPresentacionVenta());
         int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Guardar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION)
        {

            if(CCUnidadPresentacionVenta.ABMUnidadPresentacionVenta(AccionABM, oCEUnidadPresentacionVenta)==1)
            {
             JOptionPane.showMessageDialog(null,"Se Guardo con exito");
             cargarTabla();
             if(AccionDialogo==1&&AccionABM==1)
                 {dispose();}
             controladorDeEventosBotonesABM(0);
             AccionABM=0;
             TxtFiltrar.setText("");
             TxtNombre.setText("");
             TxtAbrev.setText("");
             TblUnidadPresentacion.setEnabled(true);
             BtnNuevo.requestFocus();
            }
            else
            {
            JOptionPane.showMessageDialog(null,"Se Canceló la operación");
            }
        }
        }
 else{
         JOptionPane.showMessageDialog(null,"Completar datos");
 }
}
    private void Eventocancelar()
    {
          if(AccionABM==1||AccionABM==2)
       {
           AccionABM=0;
           ovalidar.ReiniciarCajas();
           limpiarCajas();
           controladorDeEventosBotonesABM(0);
           TblUnidadPresentacion.setEnabled(true);
           TblUnidadPresentacion.clearSelection();
           BtnNuevo.requestFocus();
       }
 else
       {
           dispose();
 }
    }
   private void eventoEditar()
    {
         if(TblUnidadPresentacion.getSelectedRow()!=-1) {
            AccionABM=2;
            TxtNombre.requestFocus();
            habilitarCajas(true);
            BtnGuardar.setEnabled(true);
            BtnNuevo.setEnabled(false);


        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblUnidadPresentacion = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TxtFiltrar = new TextField.JTxtLetra();
        TxtAbrev = new TextField.JTxtNinguno();
        TxtNombre = new TextField.JTxtNinguno();
        BtnCancelar = new BotonesABM.BtnCancelar();
        BtnEliminar = new BotonesABM.BtnEliminar();
        BtnEditar = new BotonesABM.BtnEditar();
        BtnGuardar = new BotonesABM.BtnGuardar();
        BtnNuevo = new BotonesABM.BtnNuevo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Unidad Presentasion");

        PnlDatos.setBackground(new java.awt.Color(254, 254, 239));
        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Descripcion:");

        TblUnidadPresentacion.setFont(new java.awt.Font("Verdana", 0, 12));
        TblUnidadPresentacion.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion","Abreviatura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class,java.lang.Object.class

            };
            boolean[] canEdit = new boolean [] {
                false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblUnidadPresentacion.setRowHeight(22);
        TblUnidadPresentacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblUnidadPresentacionMouseClicked(evt);
            }
        });
        TblUnidadPresentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblUnidadPresentacionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblUnidadPresentacionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblUnidadPresentacion);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Abreviatura:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Filtar :");

        TxtFiltrar.setText("jTxtLetra2");
        TxtFiltrar.setTamanio(45);
        TxtFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtFiltrarKeyReleased(evt);
            }
        });

        TxtAbrev.setEnabled(false);
        TxtAbrev.setTamanio(8);
        TxtAbrev.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtAbrevKeyPressed(evt);
            }
        });

        TxtNombre.setEnabled(false);
        TxtNombre.setTamanio(150);
        TxtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNombreKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                            .addComponent(TxtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(TxtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

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

        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(PnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TblUnidadPresentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblUnidadPresentacionMouseClicked

        eventoSeleccionar();
}//GEN-LAST:event_TblUnidadPresentacionMouseClicked

    private void TblUnidadPresentacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblUnidadPresentacionKeyPressed

        if(TblUnidadPresentacion.getRowCount()>0){
            TblUnidadPresentacion.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblUnidadPresentacion.getActionMap().put("ProjSave", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    eventoEditar();
                }
            });
        }
}//GEN-LAST:event_TblUnidadPresentacionKeyPressed

    private void TblUnidadPresentacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblUnidadPresentacionKeyReleased
        if(evt.getKeyCode()==evt.VK_UP|| evt.getKeyCode()==evt.VK_DOWN|| evt.getKeyCode()==evt.VK_TAB) {
            eventoSeleccionar();
        }
    }//GEN-LAST:event_TblUnidadPresentacionKeyReleased

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        Eventocancelar();
}//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Eliminar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            if(CCUnidadPresentacionVenta.ABMUnidadPresentacionVenta(3, vCEUnidadPresentacionVenta)==1) {
                JOptionPane.showMessageDialog(null,"Se Eliminó con exito");
                limpiarCajas();
                AccionABM=0;
                cargarTabla();
                controladorDeEventosBotonesABM(0);
                TblUnidadPresentacion.setEnabled(true);
                BtnNuevo.requestFocus();
                TxtNombre.setText("");
                TxtFiltrar.setText("");
            } else {
                JOptionPane.showMessageDialog(null,"Se Canceló la operación");
            }
        }
}//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        eventoEditar();
}//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        eventoGuardar();
}//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        AccionABM=1;//(1)nuevo
        limpiarCajas();
        TxtNombre.requestFocus();
        TblUnidadPresentacion.clearSelection();
        controladorDeEventosBotonesABM(1);
}//GEN-LAST:event_BtnNuevoActionPerformed

    private void TxtFiltrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFiltrarKeyReleased
      filtarTabla();
    }//GEN-LAST:event_TxtFiltrarKeyReleased

    private void TxtAbrevKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtAbrevKeyPressed
       if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            BtnGuardar.requestFocus();
        }
        if(evt.getKeyCode()==evt.VK_UP) {
            TxtNombre.requestFocus();
        }
    }//GEN-LAST:event_TxtAbrevKeyPressed

    private void TxtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNombreKeyPressed
        if(evt.getKeyCode()==evt.VK_DOWN||evt.getKeyCode()==evt.VK_ENTER) {
            TxtAbrev.requestFocus();
        }
    }//GEN-LAST:event_TxtNombreKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnCancelar BtnCancelar;
    private BotonesABM.BtnEditar BtnEditar;
    private BotonesABM.BtnEliminar BtnEliminar;
    private BotonesABM.BtnGuardar BtnGuardar;
    private BotonesABM.BtnNuevo BtnNuevo;
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblUnidadPresentacion;
    private TextField.JTxtNinguno TxtAbrev;
    private TextField.JTxtLetra TxtFiltrar;
    private TextField.JTxtNinguno TxtNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private static final String ACTION_CLOSE = "ACTION_CLOSE";
 private static final String ACTION_GUARDAR = "ACTION_NUEVO";


    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);

        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            Eventocancelar();return;
        }
        if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
            if(BtnGuardar.isEnabled()){
              eventoGuardar();
              return;}
        }

    }
}
