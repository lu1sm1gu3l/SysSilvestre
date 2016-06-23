package view.grlGeneral;


import controller.grlGeneral.CCComponente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CEComponente;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;
public class DlgMantenimientoComponente extends DialogoPadre implements ActionListener
{
    private int operacion=0;
    private int codigo=0;
    public DlgMantenimientoComponente(java.awt.Frame parent, boolean modal,int state)
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
        ArrayListTableModel oModelo=(ArrayListTableModel)TblListaComponente.getModel();
        oModelo.addColumn("Componente");
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
                TxtComponente.setText("");
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
                       //  TxtComponente.setEditable(true);
                         habilitarBotonesABM(false,true,false,true,false,false);
                        }                      
                    }
              }
        }

    }
   
   private void limpiarCajas()
    {
        TxtComponente.setText("");
        TxtADescripcion.setText("");
        resetearVerificadores();
    }
    private void habilitarCajasDeTexto(boolean c)
    {
        
        TxtComponente.setEditable(c);
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
                int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de agregar el Componente?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    CEComponente oCEComponente=CCComponente.ejecutarProcedimientoAlta(getObjetoComponente());
                    if(oCEComponente!=null)
                    {                        
                        operacion=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
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
                        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar los datos del Componente?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                        {
                             CEComponente oCEComponente=getObjetoComponente();
                             if(CCComponente.ejecutarProcedimientoBM(2,oCEComponente))
                                {
                                    JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                                    operacion=0;
                                    controladorDeEventosBotonesABM();
                                    this.codigo=oCEComponente.getIdComponente();
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
        TxtComponente.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblId = new javax.swing.JLabel();
        LblTitulo = new javax.swing.JLabel();
        PnlEstadoCivil_Datos = new javax.swing.JPanel();
        LblCodigoTipoVia = new javax.swing.JLabel();
        LblTipoVia = new javax.swing.JLabel();
        TxtComponente = new javax.swing.JTextField();
        LblCamposObligatorios = new javax.swing.JLabel();
        LblVerificadorComponente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtADescripcion = new javax.swing.JTextArea();
        PnlBotonesDeNavegacion = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        ScrTabla = new javax.swing.JScrollPane();
        TblListaComponente = new javax.swing.JTable();
        SptDePanel = new javax.swing.JSeparator();

        LblId.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento_Componente");
        setResizable(false);

        LblTitulo.setFont(new java.awt.Font("Arial", 1, 18));
        LblTitulo.setText("Mantenimiento Componente");

        PnlEstadoCivil_Datos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblCodigoTipoVia.setFont(new java.awt.Font("Arial", 1, 12));
        LblCodigoTipoVia.setForeground(new java.awt.Color(153, 0, 0));
        LblCodigoTipoVia.setText("Componente:");

        LblTipoVia.setFont(new java.awt.Font("Arial", 1, 12));
        LblTipoVia.setForeground(new java.awt.Color(153, 0, 0));
        LblTipoVia.setText("Descripcion:");

        TxtComponente.setEditable(false);

        LblCamposObligatorios.setFont(new java.awt.Font("Tahoma", 1, 11));
        LblCamposObligatorios.setForeground(java.awt.Color.red);
        LblCamposObligatorios.setText("(*)Campos Obligatorios");

        LblVerificadorComponente.setForeground(java.awt.Color.red);
        LblVerificadorComponente.setText("*");

        TxtADescripcion.setColumns(20);
        TxtADescripcion.setRows(5);
        jScrollPane1.setViewportView(TxtADescripcion);

        javax.swing.GroupLayout PnlEstadoCivil_DatosLayout = new javax.swing.GroupLayout(PnlEstadoCivil_Datos);
        PnlEstadoCivil_Datos.setLayout(PnlEstadoCivil_DatosLayout);
        PnlEstadoCivil_DatosLayout.setHorizontalGroup(
            PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlEstadoCivil_DatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(PnlEstadoCivil_DatosLayout.createSequentialGroup()
                        .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblTipoVia)
                            .addComponent(LblCodigoTipoVia))
                        .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlEstadoCivil_DatosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LblCamposObligatorios, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addComponent(LblVerificadorComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PnlEstadoCivil_DatosLayout.setVerticalGroup(
            PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlEstadoCivil_DatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCodigoTipoVia)
                    .addComponent(TxtComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblVerificadorComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlEstadoCivil_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCamposObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTipoVia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        BtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        TblListaComponente.setModel(new ArrayListTableModel());
        TblListaComponente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblListaComponente.getTableHeader().setReorderingAllowed(false);
        TblListaComponente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaComponenteMouseClicked(evt);
            }
        });
        TblListaComponente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaComponenteKeyReleased(evt);
            }
        });
        ScrTabla.setViewportView(TblListaComponente);
        CargarModeloDeTabla();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ScrTabla, 0, 0, Short.MAX_VALUE)
                                    .addComponent(PnlEstadoCivil_Datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(LblTitulo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(PnlEstadoCivil_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(LblTitulo)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void resetearVerificadores()
    {
     LblCamposObligatorios.setText("");
     LblVerificadorComponente.setText("");
    }
   private boolean verificarCajasDeTexto()
    {
            boolean verificar=true;
            if(TxtComponente.getText().equals(""))
            {
            LblVerificadorComponente.setText("*");
            verificar=false;
            }
            else
            {
              LblVerificadorComponente.setText("");
            }

        return verificar;
    }
    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
    TblListaComponente.clearSelection();
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
                    if(CCComponente.ejecutarProcedimientoBM(3,getObjetoComponente()))
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
    private void TblListaComponenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaComponenteMouseClicked
    if(evt.getClickCount()==1)
        {
        int selectedRow=TblListaComponente.getSelectedRow();
         if(selectedRow!=-1)
         {
            CEComponente oCEComponenteSeleccionado=(CEComponente)TblListaComponente.getValueAt(selectedRow,0);
            setObjetoComponente(oCEComponenteSeleccionado);
            LblId.setText(oCEComponenteSeleccionado.getIdComponente()+"");
            operacion=3;
            controladorDeEventosBotonesABM();
         }
        }
    }//GEN-LAST:event_TblListaComponenteMouseClicked
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
    private void TblListaComponenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaComponenteKeyReleased
    if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_DOWN)
    {
        int selectedRow=TblListaComponente.getSelectedRow();
         if(selectedRow!=-1)
         {
            CEComponente oCEComponenteSeleccionado=(CEComponente)TblListaComponente.getValueAt(selectedRow,0);
            setObjetoComponente(oCEComponenteSeleccionado);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
    }
    }//GEN-LAST:event_TblListaComponenteKeyReleased
  private void setObjetoComponente(CEComponente oComponenteT)
    {
        TxtComponente.setText(oComponenteT.getIdComponente()+"");
        TxtADescripcion.setText(oComponenteT.getDescripcion()+"");
        TxtComponente.setText(oComponenteT.getNombre());
    }
    private CEComponente getObjetoComponente()
    {
        CEComponente oComponente=new CEComponente();
        if(operacion!=1)
        {
        oComponente.setIdComponente(Integer.parseInt(LblId.getText().trim()));
        }
        else
        {
        oComponente.setIdComponente(1);
        }
        oComponente.setDescripcion(TxtADescripcion.getText().trim());
        oComponente.setNombre(TxtComponente.getText().trim());
        return oComponente;
    }
     private void actualizarTablaListado()
     {
         limpiarTablaListado();
         List<CEComponente> LstCEComponentes= CCComponente.listaComponente();
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaComponente.getModel();
         
         if(LstCEComponentes!=null)
         {
             ArrayList oArrayList;
             if(this.codigo!=0)
             {
                 for(int i=0;i<LstCEComponentes.size();i++)
                 {
                     CEComponente oCEComponente= LstCEComponentes.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCEComponente);
                     oModelo.addRow(oArrayList);
                     if(this.codigo==oCEComponente.getIdComponente())
                     {
                      setObjetoComponente(oCEComponente);
                     }
                 }
             }
             else
             {
                 for(int i=0;i<LstCEComponentes.size();i++)
                 {
                     CEComponente oCEComponente= LstCEComponentes.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCEComponente);
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
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaComponente.getModel();
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
    private javax.swing.JLabel LblCodigoTipoVia;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblTipoVia;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblVerificadorComponente;
    private javax.swing.JPanel PnlBotonesDeNavegacion;
    private javax.swing.JPanel PnlEstadoCivil_Datos;
    private javax.swing.JScrollPane ScrTabla;
    private javax.swing.JSeparator SptDePanel;
    private javax.swing.JTable TblListaComponente;
    private javax.swing.JTextArea TxtADescripcion;
    private javax.swing.JTextField TxtComponente;
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
        return TxtADescripcion.getText().equals("");
    }

}

