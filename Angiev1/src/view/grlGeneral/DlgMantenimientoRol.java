package view.grlGeneral;

import controller.grlGeneral.CCRol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CERol;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;




public class DlgMantenimientoRol extends DialogoPadre implements ActionListener
{
    private int operacion=0;
    private int codigo=0;
    public DlgMantenimientoRol(java.awt.Frame parent, boolean modal,int state)
    {
        super(parent, modal);
        initComponents();     
        BtnNuevo.requestFocus();
        controladorDeEventosBotonesABM();
        actualizarTablaListado();
        resetearVerificadores();
        if(state==1)
        {
            nuevo();
        }
    }
    private void CargarModeloDeTabla()
    {
        ArrayListTableModel oModelo=(ArrayListTableModel)TblListaRol.getModel();
        oModelo.addColumn("Rol");
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
                TxTRol.setText("");
                TxTRol.setEditable(true);
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
                         TxTRol.setEditable(true);
                         habilitarBotonesABM(false,true,false,true,false,false);
                        }                      
                    }
              }
        }

    }
   private void limpiarCajas()
    {
        TxTRol.setText("");
        TxtADescripcion.setText("");
        resetearVerificadores();
    }
    private void habilitarCajasDeTexto(boolean c)
    {
        TxTRol.setEditable(c);
        TxtADescripcion.setEditable(c);

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
             if(!verificarTxtADescripcion())
             {
              if(operacion==1)
              {
                int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de agregar el Rol?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    CERol oCERol = CCRol.ejecutarProcedimientoAlta(getObjetoRol());
                    if(oCERol!=null)
                    {
                        JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                        operacion=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
                        setObjetoRol(oCERol);
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
                        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar los datos del Rol?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                        {
                             CERol oCERol=getObjetoRol();
                             if(CCRol.ejecutarProcedimientoBM(2,oCERol))
                                {
                                    JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                                    operacion=0;
                                    controladorDeEventosBotonesABM();
                                    this.codigo=oCERol.getIdRol();
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
                   JOptionPane.showMessageDialog(null,"Escriba correctamente los datos");
                }
    }
    public void nuevo()
    {
        operacion=1;
        controladorDeEventosBotonesABM();
        TxTRol.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblTitulo = new javax.swing.JLabel();
        PnlRol_Datos = new javax.swing.JPanel();
        LblRol = new javax.swing.JLabel();
        LblDescripcion = new javax.swing.JLabel();
        LblCamposObligatorios = new javax.swing.JLabel();
        LblVerificadorRol = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtADescripcion = new javax.swing.JTextArea();
        TxTRol = new javax.swing.JTextField();
        PnlBotonesDeNavegacion = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        ScrTabla = new javax.swing.JScrollPane();
        TblListaRol = new javax.swing.JTable();
        SptDePanel = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento_Documento");
        setResizable(false);

        LblTitulo.setFont(new java.awt.Font("Arial", 1, 18));
        LblTitulo.setText("Mantenimiento de Rol");

        PnlRol_Datos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlRol_Datos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblRol.setFont(new java.awt.Font("Arial", 1, 12));
        LblRol.setForeground(new java.awt.Color(153, 0, 0));
        LblRol.setText("Nombre de Rol:");
        PnlRol_Datos.add(LblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        LblDescripcion.setFont(new java.awt.Font("Arial", 1, 12));
        LblDescripcion.setForeground(new java.awt.Color(153, 0, 0));
        LblDescripcion.setText("Descripción:");
        PnlRol_Datos.add(LblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        LblCamposObligatorios.setFont(new java.awt.Font("Tahoma", 1, 11));
        LblCamposObligatorios.setForeground(java.awt.Color.red);
        LblCamposObligatorios.setText("(*)Campos Obligatorios");
        PnlRol_Datos.add(LblCamposObligatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        LblVerificadorRol.setForeground(java.awt.Color.red);
        LblVerificadorRol.setText("*");
        PnlRol_Datos.add(LblVerificadorRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 7, -1));

        TxtADescripcion.setColumns(20);
        TxtADescripcion.setRows(5);
        jScrollPane1.setViewportView(TxtADescripcion);

        PnlRol_Datos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 300, 90));
        PnlRol_Datos.add(TxTRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 190, -1));

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

        TblListaRol.setModel(new ArrayListTableModel());
        TblListaRol.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblListaRol.getTableHeader().setReorderingAllowed(false);
        TblListaRol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaRolMouseClicked(evt);
            }
        });
        TblListaRol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaRolKeyReleased(evt);
            }
        });
        ScrTabla.setViewportView(TblListaRol);
        CargarModeloDeTabla();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(LblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(PnlRol_Datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(ScrTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(LblTitulo)
                .addGap(28, 28, 28)
                .addComponent(PnlRol_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ScrTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void resetearVerificadores()
    {
     LblCamposObligatorios.setText("");
     LblVerificadorRol.setText("");
    }
   private boolean verificarCajasDeTexto()
    {
            boolean verificar=true;
            if(TxTRol.getText().equals(""))
            {
            LblVerificadorRol.setText("*");
            verificar=false;
            }
            else
            {
              LblVerificadorRol.setText("");
            }

        return verificar;
    }
    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
    TblListaRol.clearSelection();
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
            LblCamposObligatorios.setText("(*)Campos Obligatorios");
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
                    CERol oCERol=(CERol)TblListaRol.getValueAt(TblListaRol.getSelectedRow(),0);
                    if(CCRol.ejecutarProcedimientoBM(3,oCERol))
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
    private void TblListaRolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaRolMouseClicked
    if(evt.getClickCount()==1)
        {
        int selectedRow=TblListaRol.getSelectedRow();
         if(selectedRow!=-1)
         {
            CERol oCERolSeleccionado=(CERol)TblListaRol.getValueAt(selectedRow,0);
            setObjetoRol(oCERolSeleccionado);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
        }
    }//GEN-LAST:event_TblListaRolMouseClicked
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
    private void TblListaRolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaRolKeyReleased
    if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_DOWN)
    {
        int selectedRow=TblListaRol.getSelectedRow();
         if(selectedRow!=-1)
         {
            CERol oCERolSeleccionado=(CERol)TblListaRol.getValueAt(selectedRow,0);
            setObjetoRol(oCERolSeleccionado);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
    }
    }//GEN-LAST:event_TblListaRolKeyReleased
  private void setObjetoRol(CERol oCERolT)
    {
        TxTRol.setText(oCERolT.getIdRol()+"");
        TxtADescripcion.setText(oCERolT.getDescripcion()+"");
        TxTRol.setText(oCERolT.getNombre());
    }
    private CERol getObjetoRol()
    {
        CERol oRol=new CERol();
        //if(!TxTRol.getText().equals(""))
        //{
        //oRol.setIdRol(Integer.parseInt(TxTRol.getText().trim()));
        //}
        //else
        //{
        
        //}
        if(operacion==4)
        {
            CERol oCERol=(CERol)TblListaRol.getValueAt(TblListaRol.getSelectedRow(),0);
            oRol.setIdRol(oCERol.getIdRol());
        }
        else if(operacion==1)
        {
            oRol.setIdRol(oRol.getIdRol());
        }
        oRol.setDescripcion(TxtADescripcion.getText().trim());
        oRol.setNombre(TxTRol.getText().trim());
        return oRol;
    }
     private void actualizarTablaListado()
     {
         limpiarTablaListado();
         List<CERol> LstCERoles= CCRol.listaRol();
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaRol.getModel();
         
         if(LstCERoles!=null)
         {
             ArrayList oArrayList;
             if(this.codigo!=0)
             {
                 for(int i=0;i<LstCERoles.size();i++)
                 {
                     CERol oCERol= LstCERoles.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCERol);
                     oModelo.addRow(oArrayList);
                     if(this.codigo==oCERol.getIdRol())
                     {
                      setObjetoRol(oCERol);
                     }
                 }
             }
             else
             {
                 for(int i=0;i<LstCERoles.size();i++)
                 {
                     CERol oCERol= LstCERoles.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCERol);
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
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaRol.getModel();
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
    private javax.swing.JLabel LblCamposObligatorios;
    private javax.swing.JLabel LblDescripcion;
    private javax.swing.JLabel LblRol;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblVerificadorRol;
    private javax.swing.JPanel PnlBotonesDeNavegacion;
    private javax.swing.JPanel PnlRol_Datos;
    private javax.swing.JScrollPane ScrTabla;
    private javax.swing.JSeparator SptDePanel;
    private javax.swing.JTable TblListaRol;
    private javax.swing.JTextField TxTRol;
    private javax.swing.JTextArea TxtADescripcion;
    private javax.swing.JScrollPane jScrollPane1;
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
        return TxTRol.getText().equals("");
    }

}

