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
import controller.vtaVenta.CCSucursal;
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
import modelo.vtaVenta.entidad.CEPuntoVenta;
import modelo.vtaVenta.entidad.CESucursal;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLValidarControles;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.vtaVenta.CLCargarCombo;

/**
 *
 * @author Katya
 */
public class DlgPuntoVenta extends DialogoPadre implements ActionListener{

int AccionABM=0; //(0) cancelado (1) nuevo (2) editar
int AccionDialogo=0; // pacccopdialogo 1= agregar desde otro formulario 0 = mantenimiento
 private CEPuntoVenta vCEPuntoVenta=new CEPuntoVenta();
 private boolean vNuevoRegistro;
 List<CEPuntoVenta> LstUnidadVenta;
 private boolean cerrarventana=false;
  private CLValidarControles ovalidar;
  
    /** Creates new form NewJDialog */
    public DlgPuntoVenta(java.awt.Frame parent, boolean modal,boolean nuevoRegisto) { // 1: nuevo
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        ovalidar= new CLValidarControles();
        vNuevoRegistro=nuevoRegisto;
        ovalidar.SetListCompnente(new Component[]{TxtIP,TxtNombre}, PnlDatos);
        CbxSucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
         BtnNuevo.requestFocus();
        if(nuevoRegisto){
        controladorDeEventosBotonesABM(1);
        AccionABM=1;}
        else{ controladorDeEventosBotonesABM(0);}
        cargarTabla();
        cerrarventana=false;
    }
    public void setCerrarVentana(boolean cerrar)
    {
     cerrarventana=cerrar;
    }
    private void limpiarCajas()
    {
        TxtNombre.setText("");
        TxtIP.setText("");
        chkCaja.setSelected(false);
        CbxSucursal.setSelectedIndex(0);

    }
     public void OcultarBotones()
    {
        PnlBotones.setVisible(false);
        PnlDatos.setVisible(false);
        if(TblPuntoVenta.getRowCount()>0)
        {
        TblPuntoVenta.requestFocus();
        TblPuntoVenta.changeSelection(0, 0, false, false);
        }
    }
    private void habilitarCajas(boolean bol)
    {
        TxtNombre.setEnabled(bol);
        TxtIP.setEnabled(bol);
        CbxSucursal.setEnabled(bol);
        chkCaja.setEnabled(bol);
    }
    private void FillCajas()
    {
        TxtNombre.setText(vCEPuntoVenta.getDescrpcion());
        TxtIP.setText(vCEPuntoVenta.getIP());
        CLCargarCombo.buscarIdSucursal(CbxSucursal,vCEPuntoVenta.getIdSucursal());
        chkCaja.setSelected(vCEPuntoVenta.isSinocaja());
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
        else if(operacion==1) // boton nuevo
        {
        TxtNombre.requestFocus();
        limpiarCajas();
        habilitarBotonesABM(false,true,false,false,true);
        TblPuntoVenta.setEnabled(false);
        habilitarCajas(true);
        TblPuntoVenta.clearSelection();
        }
        else if(operacion==2) // boton consultar
            {
                habilitarBotonesABM(false,true,true,true,true);
            }
                  
          
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
        if(AccionABM!=1){
         vCEPuntoVenta=(CEPuntoVenta)TblPuntoVenta.getValueAt(TblPuntoVenta.getSelectedRow(), 0);
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
        CEPuntoVenta oCEPuntoVenta= new CEPuntoVenta();
        oCEPuntoVenta.setDescrpcion(TxtNombre.getText());
        oCEPuntoVenta.setIP(TxtIP.getText());
        oCEPuntoVenta.setIdSucursal(((CESucursal)(CbxSucursal.getSelectedItem())).getIdSucursal());
        oCEPuntoVenta.setIdPuntoVenta(vCEPuntoVenta.getIdPuntoVenta());
        oCEPuntoVenta.setSinocaja(chkCaja.isSelected());
         int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Guardar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION)
        {

            if(CCPuntoVenta.ABMPuntoVenta(AccionABM, oCEPuntoVenta)==1)
            {
              if(cerrarventana)
                 {dispose();}
             cargarTabla();            
             controladorDeEventosBotonesABM(0);
             AccionABM=0;
             TxtFiltrar.setText("");
             TxtNombre.setText("");
             TxtIP.setText("");
             TblPuntoVenta.setEnabled(true);
             BtnNuevo.requestFocus();
             
            }
            else
            {
            JOptionPane.showMessageDialog(null,"Operación Fallida");
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
           TblPuntoVenta.setEnabled(true);
           TblPuntoVenta.clearSelection();
           BtnNuevo.requestFocus();
       }
 else
       {
           dispose();
 }
    }
   private void eventoEditar()
    {
         if(TblPuntoVenta.getSelectedRow()!=-1) {
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
        jLabel3 = new javax.swing.JLabel();
        TxtIP = new TextField.JTxtNinguno();
        TxtNombre = new TextField.JTxtNinguno();
        CbxSucursal = new ComboBox.ComboBox();
        jLabel4 = new javax.swing.JLabel();
        chkCaja = new Opcion.CheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPuntoVenta = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        TxtFiltrar = new TextField.JTxtLetra();
        PnlBotones = new javax.swing.JPanel();
        BtnCancelar = new BotonesABM.BtnCancelar();
        BtnEliminar = new BotonesABM.BtnEliminar();
        BtnEditar = new BotonesABM.BtnEditar();
        BtnGuardar = new BotonesABM.BtnGuardar();
        BtnNuevo = new BotonesABM.BtnNuevo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario Unidad Venta");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnlDatos.setBackground(new java.awt.Color(254, 254, 239));
        PnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Descripcion:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("IP                   :");

        TxtIP.setEnabled(false);
        TxtIP.setTamanio(20);

        TxtNombre.setEnabled(false);
        TxtNombre.setTamanio(150);

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

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14));
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("Sucursal      :");

        chkCaja.setText("CAJA");
        chkCaja.setOpaque(false);
        chkCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCajaActionPerformed(evt);
            }
        });

        TblPuntoVenta.setFont(new java.awt.Font("Verdana", 0, 12));
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

        javax.swing.GroupLayout PnlDatosLayout = new javax.swing.GroupLayout(PnlDatos);
        PnlDatos.setLayout(PnlDatosLayout);
        PnlDatosLayout.setHorizontalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CbxSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(chkCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(TxtIP, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addGap(95, 95, 95))
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        PnlDatosLayout.setVerticalGroup(
            PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatosLayout.createSequentialGroup()
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel4))
                    .addGroup(PnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PnlDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 570, 440));

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

        javax.swing.GroupLayout PnlBotonesLayout = new javax.swing.GroupLayout(PnlBotones);
        PnlBotones.setLayout(PnlBotonesLayout);
        PnlBotonesLayout.setHorizontalGroup(
            PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        PnlBotonesLayout.setVerticalGroup(
            PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(PnlBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TblPuntoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPuntoVentaMouseClicked

        eventoSeleccionar();
        
}//GEN-LAST:event_TblPuntoVentaMouseClicked

    private void TblPuntoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPuntoVentaKeyPressed

        if(TblPuntoVenta.getRowCount()>0){
            TblPuntoVenta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblPuntoVenta.getActionMap().put("ProjSave", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    eventoEditar();
                }
            });
        }
}//GEN-LAST:event_TblPuntoVentaKeyPressed

    private void TblPuntoVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPuntoVentaKeyReleased
        if(evt.getKeyCode()==evt.VK_UP|| evt.getKeyCode()==evt.VK_DOWN|| evt.getKeyCode()==evt.VK_TAB) {
            eventoSeleccionar();
        }
    }//GEN-LAST:event_TblPuntoVentaKeyReleased

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        Eventocancelar();
}//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Eliminar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {

            if(CCPuntoVenta.ABMPuntoVenta(3, vCEPuntoVenta)==1) {
                limpiarCajas();
                AccionABM=0;
                cargarTabla();
                controladorDeEventosBotonesABM(0);
                TblPuntoVenta.setEnabled(true);
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
        AccionABM=1;
        controladorDeEventosBotonesABM(1);
}//GEN-LAST:event_BtnNuevoActionPerformed

    private void TxtFiltrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFiltrarKeyReleased
       filtarTabla();
    }//GEN-LAST:event_TxtFiltrarKeyReleased

    private void CbxSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxSucursalItemStateChanged

  
}//GEN-LAST:event_CbxSucursalItemStateChanged

    private void CbxSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxSucursalActionPerformed


      
}//GEN-LAST:event_CbxSucursalActionPerformed

    private void chkCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCajaActionPerformed

       
    }//GEN-LAST:event_chkCajaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnCancelar BtnCancelar;
    private BotonesABM.BtnEditar BtnEditar;
    private BotonesABM.BtnEliminar BtnEliminar;
    private BotonesABM.BtnGuardar BtnGuardar;
    private BotonesABM.BtnNuevo BtnNuevo;
    private ComboBox.ComboBox CbxSucursal;
    private javax.swing.JPanel PnlBotones;
    private javax.swing.JPanel PnlDatos;
    private javax.swing.JTable TblPuntoVenta;
    private TextField.JTxtLetra TxtFiltrar;
    private TextField.JTxtNinguno TxtIP;
    private TextField.JTxtNinguno TxtNombre;
    private Opcion.CheckBox chkCaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
