package view.grlGeneral;

import controller.grlGeneral.CCAccion;
import controller.grlGeneral.CCVinculacionComponenteAccion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import modelo.grlGeneral.entidad.CEAccion;
import modelo.grlGeneral.entidad.CEComponente;
import modelo.grlGeneral.entidad.CEVinculacionComponenteAccion;
import modelo.grlGeneral.entidad.CEVinculacionComponenteAccionMatriz;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLCheckBoxHeader;
import util.clases.grlGeneral.CLObjectABM;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.ManejadorAccionABM;
import util.clases.grlGeneral.ManejadorEventosBotones;


public class DlgRelacionComponenteAccion extends DialogoPadre {

    private int stateFrm=0;
    private List<CLObjectABM> oLstVinculacionDepartamentoABM;
    private int IdComponente;
    public DlgRelacionComponenteAccion(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        configurarTblAsignatura();
        configurarTblAsignaturaUnidad();
        TableColumn aColumn = (TableColumn)TblAcciones.getColumnModel().getColumn(0);
        aColumn.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox1.getItemListeners()[0]));
        TableColumn aColumn_ = (TableColumn)TblAccionComponente.getColumnModel().getColumn(0);
        aColumn_.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox2.getItemListeners()[0]));
        newStateForm(ManejadorEventosBotones.onNothing);
        cargarTablaAcciones((new CCAccion()).listaAccion());
        BtnCancelarVinculacion.setEnabled(true);
    }
    private void enabledButonsDocenteABM(boolean statePass,boolean stateGuardarCancelar,boolean stateEditar)
    {
        BtnPassRight.setEnabled(statePass);
        BtnPassLeft.setEnabled(statePass);
        BtnGuardarVinculacion.setEnabled(stateGuardarCancelar);
        BtnModificarVinculacion.setEnabled(stateEditar);
        BtnCancelarVinculacion.setEnabled(stateGuardarCancelar);
    }
    private void newStateForm(int state)
    {
        this.stateFrm=state;
        controladorDeBotonesVinculacionABM(stateFrm);
    }
   private void enabledPnlAsignaturas(boolean a)
   {
        TxtAsignaturaFiltro1.setEditable(a);
        TblAcciones.setEnabled(a);
   }
   private void enabledPnlDatosUnidad(boolean a)
   {
        TxtAsignaturaFiltro1.setEditable(a);
   }
   private void controladorDeBotonesVinculacionABM(int stateControlBotonDocente )
      {
        if(stateControlBotonDocente==ManejadorEventosBotones.onNothing)
          {
            this.oLstVinculacionDepartamentoABM=null;
            enabledButonsDocenteABM(false,false,false);
            enabledPnlAsignaturas(false);
            enabledPnlDatosUnidad(true);
            limpiarTabla(TblAcciones);
            limpiarTabla(TblAccionComponente);

          }
        else
          {
           if(stateControlBotonDocente==ManejadorEventosBotones.onQuery)
            {
                enabledButonsDocenteABM(false,false,true);
                enabledPnlAsignaturas(false);
                enabledPnlDatosUnidad(true);
            }
            else
           {
               if(stateControlBotonDocente==ManejadorEventosBotones.onNew)
                {

                }
                else
                {
                  if(stateControlBotonDocente==ManejadorEventosBotones.onEdit)
                  {
                    enabledButonsDocenteABM(true,true,false);
                    enabledPnlAsignaturas(true);
                    enabledPnlDatosUnidad(false);
                    
                  }
                }
            }
        }
    }

  

    
  private void configurarTblAsignatura()
    {
         TblAcciones.getColumnModel().getColumn(0).setMaxWidth(50);
         TblAcciones.getColumnModel().getColumn(0).setMinWidth(50);
    }
   private void configurarTblAsignaturaUnidad()
    {
         TblAccionComponente.getColumnModel().getColumn(0).setMaxWidth(50);
         TblAccionComponente.getColumnModel().getColumn(0).setMinWidth(50);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblAccionComponente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        TxtAsignaturaFiltro1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblAcciones = new javax.swing.JTable(
            new ArrayListTableModel(
                new Object [][] {

                },
                new String [] {
                    "Sel.", "Asigantura."
                }
            ) {
                Class[] types = new Class []
                {
                    java.lang.Boolean.class, java.lang.Object.class
                };
                boolean[] canEdit = new boolean [] {
                    true, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            }

        );
        BtnPassRight = new javax.swing.JButton();
        BtnPassLeft = new javax.swing.JButton();
        BtnModificarVinculacion = new javax.swing.JButton();
        BtnCancelarVinculacion = new javax.swing.JButton();
        BtnGuardarVinculacion = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jCheckBox2.setText("jCheckBox2");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18));
        jLabel3.setText("Componente-Accion");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(jLabel3)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Acciones que se pueden realizar]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblAccionComponente.setModel( new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.", "Acciones"
            }
        ) {
            Class[] types = new Class []
            {
                java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TblAccionComponente);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Datos de Componente]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TxtAsignaturaFiltro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAsignaturaFiltro1ActionPerformed(evt);
            }
        });
        TxtAsignaturaFiltro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtAsignaturaFiltro1KeyReleased(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TxtAsignaturaFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtAsignaturaFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Acciones Disponibles]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblAcciones.setModel( new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.","Rol"
            }
        ) {
            Class[] types = new Class []
            {
                java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblAcciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BtnPassRight.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnPassRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnPassRight.setText("Eliminar");
        BtnPassRight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPassRight.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPassRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPassRightActionPerformed(evt);
            }
        });

        BtnPassLeft.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnPassLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/verAtraz.png"))); // NOI18N
        BtnPassLeft.setText("Agregar");
        BtnPassLeft.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPassLeft.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPassLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPassLeftActionPerformed(evt);
            }
        });

        BtnModificarVinculacion.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnModificarVinculacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnModificarVinculacion.setText("Modificar ");
        BtnModificarVinculacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnModificarVinculacion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnModificarVinculacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnModificarVinculacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarVinculacionActionPerformed(evt);
            }
        });

        BtnCancelarVinculacion.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        BtnCancelarVinculacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelarVinculacion.setText("Cancelar  ");
        BtnCancelarVinculacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelarVinculacion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCancelarVinculacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelarVinculacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarVinculacionActionPerformed(evt);
            }
        });

        BtnGuardarVinculacion.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnGuardarVinculacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardarVinculacion.setText("Guardar ");
        BtnGuardarVinculacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardarVinculacion.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardarVinculacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnGuardarVinculacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarVinculacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnPassRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnPassLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnModificarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(BtnGuardarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnCancelarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BtnModificarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(BtnPassLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPassRight, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(BtnGuardarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnCancelarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       int size =TblAcciones.getRowCount();
        for(int i=0;i<size;i++)
        {
            boolean state= evt.getStateChange()==evt.SELECTED?true:false;
            TblAcciones.setValueAt(state,i,0);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
   int size =TblAccionComponente.getRowCount();
        for(int i=0;i<size;i++)
        {
            boolean state= evt.getStateChange()==evt.SELECTED?true:false;
            TblAccionComponente.setValueAt(state,i,0);
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void BtnModificarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarVinculacionActionPerformed
        newStateForm(ManejadorEventosBotones.onEdit);
    }//GEN-LAST:event_BtnModificarVinculacionActionPerformed

    private void BtnPassLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassLeftActionPerformed
    passAsignaturasACargo();
    }//GEN-LAST:event_BtnPassLeftActionPerformed

    private void BtnPassRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassRightActionPerformed
    eliminarAsignaturasACargo();
    }//GEN-LAST:event_BtnPassRightActionPerformed

    private void BtnGuardarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarVinculacionActionPerformed
      if(oLstVinculacionDepartamentoABM==null)
      {
          oLstVinculacionDepartamentoABM=new ArrayList<CLObjectABM>();
      }
      int rowCount=TblAccionComponente.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          CEVinculacionComponenteAccion oCEVinculacionComponenteAccionMatriz=(CEVinculacionComponenteAccion)TblAccionComponente.getValueAt(i,1);
          if(oCEVinculacionComponenteAccionMatriz.getIdComponenteAccion()==0)
          {
          CLObjectABM objectABM=new CLObjectABM();
          objectABM.setAbm(ManejadorAccionABM.insert);
          oCEVinculacionComponenteAccionMatriz.setIdComponente(IdComponente);
          objectABM.setObjeto(oCEVinculacionComponenteAccionMatriz);
          oLstVinculacionDepartamentoABM.add(objectABM);
          }
      }

       if(!(new CCVinculacionComponenteAccion()).ABMVinculacionAsignaturaDepartamento((ArrayList)oLstVinculacionDepartamentoABM))
      {
          JOptionPane.showMessageDialog(null,"No se pudo guardar los cambios", null, stateFrm);

      }
      else
      {
        newStateForm(ManejadorEventosBotones.onNothing);
        List<CEVinculacionComponenteAccionMatriz> oLstUsuarioRolmATRIZ= new  CCVinculacionComponenteAccion().ListarVinculacionComponenteAccion(IdComponente);
        if(oLstUsuarioRolmATRIZ!=null)
        {
            cargarTablaCambioDia((ArrayList)oLstUsuarioRolmATRIZ);

        }
cargarTablaAcciones((new CCAccion()).listaAccion());
BtnModificarVinculacion.setEnabled(true);
BtnCancelarVinculacion.setEnabled(true);
      }

    }//GEN-LAST:event_BtnGuardarVinculacionActionPerformed


    private void BtnCancelarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarVinculacionActionPerformed
    newStateForm(ManejadorEventosBotones.onNothing);
    }//GEN-LAST:event_BtnCancelarVinculacionActionPerformed

    private void TxtAsignaturaFiltro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtAsignaturaFiltro1KeyReleased
         if(evt.getKeyCode()==evt.VK_BACK_SPACE)
        {
            IdComponente=0;
            limpiarTabla(TblAccionComponente);
        }
    }//GEN-LAST:event_TxtAsignaturaFiltro1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 abrirDialogoDeConsulta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtAsignaturaFiltro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAsignaturaFiltro1ActionPerformed

        abrirDialogoDeConsulta();


    }//GEN-LAST:event_TxtAsignaturaFiltro1ActionPerformed
    private void abrirDialogoDeConsulta()
    {

                DlgBuscarComponente oDialogoConsultaDocente=new DlgBuscarComponente(null, true, TxtAsignaturaFiltro1.getText());
                oDialogoConsultaDocente.setLocationRelativeTo(null);
                oDialogoConsultaDocente.setVisible(true);
                CEComponente oComponenteCE=oDialogoConsultaDocente.getComponente();
                if(oComponenteCE!=null)
                {
                 IdComponente=oComponenteCE.getIdComponente();
                 TxtAsignaturaFiltro1.setText(oComponenteCE.getNombre());
                 cargarTablaCambioDia((new CCVinculacionComponenteAccion()).ListarVinculacionComponenteAccion(IdComponente));
                 newStateForm(ManejadorEventosBotones.onQuery);
                }
                          
        
    }

 
   

    private void passAsignaturasACargo()
    {
      int rowCount=TblAcciones.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblAcciones.getValueAt(i,0).toString());
          if(valor)
          {
              CEAccion oAccion=(CEAccion)TblAcciones.getValueAt(i,1);
              TblAcciones.setValueAt(false,i,0);
              if(verificarExistencia(oAccion))
              {
                  ArrayList oArrayList=new ArrayList();
                  oArrayList.add(false);
                  CEVinculacionComponenteAccionMatriz oAsignaturaDepartamentoMatriz=new CEVinculacionComponenteAccionMatriz();
                  oAsignaturaDepartamentoMatriz.setAccion(oAccion.getDescripcion());
                  oAsignaturaDepartamentoMatriz.setIdAccion(oAccion.getIdAccion());
                  oArrayList.add(oAsignaturaDepartamentoMatriz);
                  oArrayList.add(1);
                  ((ArrayListTableModel)TblAccionComponente.getModel()).addRow(oArrayList);
              }
          }
      }
    }
     private void eliminarAsignaturasACargo()
    {
      int rowCount=TblAccionComponente.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblAccionComponente.getValueAt(i,0).toString());
          if(valor)
          {
              CEVinculacionComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=(CEVinculacionComponenteAccionMatriz)TblAccionComponente.getValueAt(i,1);
              if(oCEVinculacionComponenteAccionMatriz.getIdComponenteAccion()!=0)
              {
              if(oLstVinculacionDepartamentoABM==null)
              {
                  oLstVinculacionDepartamentoABM=new ArrayList<CLObjectABM>();
              }
              CLObjectABM objectABM=new CLObjectABM();
              objectABM.setAbm(ManejadorAccionABM.delete);
              objectABM.setObjeto(oCEVinculacionComponenteAccionMatriz);
              oLstVinculacionDepartamentoABM.add(objectABM);
              }

             ((ArrayListTableModel)TblAccionComponente.getModel()).removeRow(i);
              rowCount--;
              i--;
          }
      }
    }
     private void eliminarRolesPorUsuario()
    {
      int rowCount=TblAccionComponente.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblAccionComponente.getValueAt(i,0).toString());
          if(valor)
          {
              CEVinculacionComponenteAccionMatriz oCEUsuarioRolMatriz=(CEVinculacionComponenteAccionMatriz)TblAccionComponente.getValueAt(i,1);
              if(oCEUsuarioRolMatriz.getIdComponenteAccion()!=0)
              {
              if(oLstVinculacionDepartamentoABM==null)
              {
                  oLstVinculacionDepartamentoABM=new ArrayList<CLObjectABM>();
              }
              CLObjectABM objectABM=new CLObjectABM();
              objectABM.setAbm(ManejadorAccionABM.delete);
              objectABM.setObjeto(oCEUsuarioRolMatriz);
              oLstVinculacionDepartamentoABM.add(objectABM);
              }

             ((ArrayListTableModel)TblAccionComponente.getModel()).removeRow(i);
              rowCount--;
              i--;
          }
      }
    }

    private boolean verificarExistencia(CEAccion oAsignatura)
    {
        int rowCount=TblAccionComponente.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            CEVinculacionComponenteAccionMatriz oCEAccion=(CEVinculacionComponenteAccionMatriz)TblAccionComponente.getValueAt(i,1);
            if(oCEAccion.getIdAccion()==oAsignatura.getIdAccion())
            {
            return false;
            }
        }
        return true;
    }
        public void cargarTablaAcciones(List<CEAccion> oLstAccion)
    {
      limpiarTabla(TblAcciones);


        if(oLstAccion!=null)
            {
              ArrayListTableModel oModeloTblAsignatura=(ArrayListTableModel)TblAcciones.getModel();
              for(int i=0;i<oLstAccion.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(oLstAccion.get(i));
                     oModeloTblAsignatura.addRow(oArrayList);
                 }
            }

        }

    public void cargarTablaCambioDia(List<CEVinculacionComponenteAccionMatriz> oLstVinculacionComponenteAccionMatrizs)
    {
      limpiarTabla(TblAccionComponente);
     

        if(oLstVinculacionComponenteAccionMatrizs!=null)
            {
              ArrayListTableModel oModeloTblAsignatura=(ArrayListTableModel)TblAccionComponente.getModel();
              for(int i=0;i<oLstVinculacionComponenteAccionMatrizs.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(oLstVinculacionComponenteAccionMatrizs.get(i));
                     oModeloTblAsignatura.addRow(oArrayList);
                 }
            }

        }
    private void limpiarTabla(JTable Tbl)
    {
         ArrayListTableModel oModeloTblAsignatura=(ArrayListTableModel)Tbl.getModel();
         int size =oModeloTblAsignatura.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
            oModeloTblAsignatura.removeRow(0);
            }
         }
     }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelarVinculacion;
    private javax.swing.JButton BtnGuardarVinculacion;
    private javax.swing.JButton BtnModificarVinculacion;
    private javax.swing.JButton BtnPassLeft;
    private javax.swing.JButton BtnPassRight;
    private javax.swing.JTable TblAccionComponente;
    private javax.swing.JTable TblAcciones;
    private javax.swing.JTextField TxtAsignaturaFiltro1;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    

}
