package view.grlGeneral;


import controller.grlGeneral.CCAccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CEAccion;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;

public class DlgMantenimientoAccion extends DialogoPadre implements ActionListener
{
    private int operacion=0;
    private int codigo=0;
    public DlgMantenimientoAccion(java.awt.Frame parent, boolean modal,int state)
    {
        super(parent, modal);
        initComponents();     
        BtnNuevo.requestFocus();
        controladorDeEventosBotonesABM();
        actualizarTablaListado();
        LblId.setVisible(false);
        resetearVerificadores();
        if(state==1)
        {
            nuevo();
        }
    }
    private void CargarModeloDeTabla()
    {
        ArrayListTableModel oModelo=(ArrayListTableModel)TblListaAccion.getModel();
        oModelo.addColumn("Accion");
    }
   private static final String ACTION_CLOSE = "ACTION_CLOSE";

    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    private void controladorDeEventosBotonesABM()
      {
        if(operacion==0)
          {
            habilitarCajasDeTexto(false);
            habilitarBotonesABM(true,false,false,false,false,true);
            limpiarCajas();
          }
        else
          { if(operacion==1)
                {
                limpiarCajas();
                habilitarCajasDeTexto(true);
                TxtDescripcion.setText("");
                habilitarBotonesABM(false,true,false,true,false,false);
                }
              else
              {
                    
                      if(operacion==3)
                        {
                         habilitarCajasDeTexto(false);
                         habilitarBotonesABM(true,false,true,false,true,true);
                        }
                      else
                      {
                        if(operacion==4)
                        {

                         habilitarCajasDeTexto(true);
                         habilitarBotonesABM(false,true,false,true,false,false);
                        }                      
                    }
              }
        }

    }
   private void limpiarCajas()
    {
        TxtDescripcion.setText("");
        TxtAbreviatura.setText("");
        resetearVerificadores();
    }
    private void habilitarCajasDeTexto(boolean c)
    {
        TxtDescripcion.setEditable(c);
        TxtAbreviatura.setEditable(c);

    }
    private void habilitarBotonesABM(boolean cNuevo,boolean cGuardar,boolean cEditar,boolean cCancelar,boolean cEliminar,boolean cSalir)
    {
        BtnNuevo.setEnabled(cNuevo);
        BtnGuardar.setEnabled(cGuardar);
        BtnEditar.setEnabled(cEditar);
        BtnCancelar.setEnabled(cCancelar);
        BtnEliminar.setEnabled(cEliminar);
        BtnSalir.setEnabled(cSalir);
    }

    public void Guardar()
    {
         try
            {
             if(!verificarTxtADescripcion()||!verificarTxtAbreviatura())
             {
              if(operacion==1)
              {
                int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de agregar la acción?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    CEAccion oCEAccion=CCAccion.ejecutarProcedimientoAlta(getObjetoAccion());
                    if(oCEAccion!=null)
                    {
                        JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                        operacion=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
                        setObjetoAccion(oCEAccion);
                        BtnNuevo.requestFocus();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                    }
                }
              }
            else
                {
                    if(operacion==4)
                    {
                        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar los datos de la acción?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                        {
                             CEAccion oCEAccion=getObjetoAccion();
                             if(CCAccion.ejecutarProcedimientoBM(2,oCEAccion))
                                {
                                    JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                                    operacion=0;
                                    controladorDeEventosBotonesABM();
                                    this.codigo=oCEAccion.getIdAccion();
                                    actualizarTablaListado();
                                    this.codigo=0;
                                    BtnNuevo.requestFocus();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                                }
                        }
                    }
                }
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"Faltan Datos","Mensaje",JOptionPane.ERROR_MESSAGE);
             }
         }
            catch(Exception e)
                {
                   e.printStackTrace();
                   JOptionPane.showMessageDialog(null,"Escriba correctamente los datos");
                }
    }
    public void nuevo()
    {
        operacion=1;
        controladorDeEventosBotonesABM();
        TxtDescripcion.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblTitulo = new javax.swing.JLabel();
        PnlEstadoCivil_Datos = new javax.swing.JPanel();
        LblAccionDescripcion = new javax.swing.JLabel();
        LblAbreviatura = new javax.swing.JLabel();
        TxtDescripcion = new javax.swing.JTextField();
        LblId = new javax.swing.JLabel();
        TxtAbreviatura = new javax.swing.JTextField();
        PnlBotonesDeNavegacion = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        ScrTabla = new javax.swing.JScrollPane();
        TblListaAccion = new javax.swing.JTable();
        SptDePanel = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento_Documento");
        setResizable(false);

        LblTitulo.setFont(new java.awt.Font("Arial", 1, 18));
        LblTitulo.setText("Mantenimiento Acción");

        PnlEstadoCivil_Datos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblAccionDescripcion.setFont(new java.awt.Font("Arial", 1, 12));
        LblAccionDescripcion.setForeground(new java.awt.Color(153, 0, 0));
        LblAccionDescripcion.setText("Descripción:");

        LblAbreviatura.setFont(new java.awt.Font("Arial", 1, 12));
        LblAbreviatura.setForeground(new java.awt.Color(153, 0, 0));
        LblAbreviatura.setText("Abreviatura:");

        TxtDescripcion.setEditable(false);

        TxtAbreviatura.setEditable(false);

        javax.swing.GroupLayout PnlEstadoCivil_DatosLayout = new javax.swing.GroupLayout(PnlEstadoCivil_Datos);
        PnlEstadoCivil_Datos.setLayout(PnlEstadoCivil_DatosLayout);
        PnlEstadoCivil_DatosLayout.setHorizontalGroup(
            PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlEstadoCivil_DatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblAbreviatura)
                    .addComponent(LblAccionDescripcion))
                .addGap(20, 20, 20)
                .addComponent(LblId, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        PnlEstadoCivil_DatosLayout.setVerticalGroup(
            PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlEstadoCivil_DatosLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LblId, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(TxtDescripcion)
                    .addComponent(LblAccionDescripcion, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblAbreviatura)
                    .addComponent(TxtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        PnlBotonesDeNavegacion.setLayout(new java.awt.GridLayout(1, 0));

        BtnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        BtnNuevo.setText("Nuevo");
        BtnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNuevo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        BtnNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNuevoKeyPressed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnNuevo);

        BtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        BtnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGuardarKeyPressed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnGuardar);

        BtnEditar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnEditar);

        BtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnCancelar);

        BtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnEliminar);

        BtnSalir.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Salir.png"))); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnSalir);

        TblListaAccion.setModel(new ArrayListTableModel());
        TblListaAccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblListaAccion.getTableHeader().setReorderingAllowed(false);
        TblListaAccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaAccionMouseClicked(evt);
            }
        });
        TblListaAccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaAccionKeyReleased(evt);
            }
        });
        ScrTabla.setViewportView(TblListaAccion);
        CargarModeloDeTabla();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(LblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PnlEstadoCivil_Datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ScrTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(LblTitulo)
                .addGap(39, 39, 39)
                .addComponent(PnlEstadoCivil_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void resetearVerificadores()
    {
//     LblCamposObligatorios.setText("");
//     LblVerificadorDescripcion.setText("");
//     LblVerificadorAbreviatura.setText("");
 }
   private boolean verificarCajasDeTexto()
    {
            boolean verificar=true;
//            if(TxtDescripcion.getText().equals(""))
//            {
//            LblVerificadorDescripcion.setText("*");
//            verificar=false;
//            }
//            else
//            {
//              LblVerificadorDescripcion.setText("");
//            }
//            if(TxtAbreviatura.getText().equals(""))
//            {
//                LblVerificadorAbreviatura.setText("*");
//                verificar=false;
//            }
//            else
//            {
//                LblVerificadorAbreviatura.setText("");
//            }

        return verificar;
    }
    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
    TblListaAccion.clearSelection();
        nuevo();
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if(verificarCajasDeTexto())
        {
        resetearVerificadores();
        Guardar();
        }
        else
        {
        //    LblCamposObligatorios.setText("(*)Campos Obligatorios");
            JOptionPane.showMessageDialog(null,"Faltan Datos","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
          operacion=4;
       controladorDeEventosBotonesABM();
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        operacion=0;
        controladorDeEventosBotonesABM();

    }//GEN-LAST:event_BtnCancelarActionPerformed
    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
      try
        {
              int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el Tipo de Via?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    if(CCAccion.ejecutarProcedimientoBM(3,getObjetoAccion()))
                    {
                        JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                        operacion=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                    }
                }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null,"Escriba los datos correctamente");
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed
    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
       dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed
    private void TblListaAccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaAccionMouseClicked
    if(evt.getClickCount()==1)
        {
        int selectedRow=TblListaAccion.getSelectedRow();
         if(selectedRow!=-1)
         {
            CEAccion oCEAccionSeleccionado=(CEAccion)TblListaAccion.getValueAt(selectedRow,0);
            setObjetoAccion(oCEAccionSeleccionado);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
        }
    }//GEN-LAST:event_TblListaAccionMouseClicked
    private void BtnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGuardarKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER)
        {
            Guardar();
        }
    }//GEN-LAST:event_BtnGuardarKeyPressed
    private void BtnNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNuevoKeyPressed
      if(evt.getKeyCode()==evt.VK_ENTER)
        {
            nuevo();
        }
    }//GEN-LAST:event_BtnNuevoKeyPressed
    private void TblListaAccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaAccionKeyReleased
    if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_DOWN)
    {
        int selectedRow=TblListaAccion.getSelectedRow();
         if(selectedRow!=-1)
         {
            CEAccion oCEAccionSeleccionado=(CEAccion)TblListaAccion.getValueAt(selectedRow,0);
            setObjetoAccion(oCEAccionSeleccionado);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
    }
    }//GEN-LAST:event_TblListaAccionKeyReleased
  private void setObjetoAccion(CEAccion oAccionT)
    {
        LblId.setText(oAccionT.getIdAccion()+"");
        LblId.setVisible(false);
        TxtAbreviatura.setText(oAccionT.getAbreviatura());
        TxtDescripcion.setText(oAccionT.getDescripcion());
    }
    private CEAccion getObjetoAccion()
    {
        CEAccion oAccion=new CEAccion();
        if(this.operacion!=1)
        {
        oAccion.setIdAccion(Integer.parseInt(LblId.getText()));
        oAccion.setDescripcion(TxtDescripcion.getText().trim());
        oAccion.setAbreviatura(TxtAbreviatura.getText());
        }
        else
        {
        oAccion.setIdAccion(1);
        }
        oAccion.setAbreviatura(TxtAbreviatura.getText().trim());
        oAccion.setDescripcion(TxtDescripcion.getText().trim());
        return oAccion;
    }
     private void actualizarTablaListado()
     {
         limpiarTablaListado();
         List<CEAccion> LstCEAccions= CCAccion.listaAccion();
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaAccion.getModel();
         
         if(LstCEAccions!=null)
         {
             ArrayList oArrayList;
             if(this.codigo!=0)
             {
                 for(int i=0;i<LstCEAccions.size();i++)
                 {
                     CEAccion oCEAccion= LstCEAccions.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCEAccion);
                     oModelo.addRow(oArrayList);
                     if(this.codigo==oCEAccion.getIdAccion())
                     {
                      setObjetoAccion(oCEAccion);
                     }
                 }
             }
             else
             {
                 for(int i=0;i<LstCEAccions.size();i++)
                 {
                     CEAccion oCEAccion= LstCEAccions.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCEAccion);
                     oModelo.addRow(oArrayList);
                 }
             }
         }
         else
         {
             limpiarTablaListado();
         }
     }
     private void limpiarTablaListado()
     {
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaAccion.getModel();
         int size =oModelo.getRowCount();
         for(int i=0;i<size;i++)
         {
            oModelo.removeRow(0);
         }
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JLabel LblAbreviatura;
    private javax.swing.JLabel LblAccionDescripcion;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JPanel PnlBotonesDeNavegacion;
    private javax.swing.JPanel PnlEstadoCivil_Datos;
    private javax.swing.JScrollPane ScrTabla;
    private javax.swing.JSeparator SptDePanel;
    private javax.swing.JTable TblListaAccion;
    private javax.swing.JTextField TxtAbreviatura;
    private javax.swing.JTextField TxtDescripcion;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e)
    {
      if(operacion==0||operacion==3)
      {
      if(ACTION_CLOSE.equals(e.getActionCommand()))
        {
            dispose();
        }
      }
      else
      {
          operacion=0;
          controladorDeEventosBotonesABM();
          BtnNuevo.requestFocus();
      }
    }
    private boolean verificarTxtADescripcion()
    {
        return TxtAbreviatura.getText().equals("");

    }
    private boolean verificarTxtAbreviatura()
    {
         return TxtDescripcion.getText().equals("");
    }

}

