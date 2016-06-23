package view.grlGeneral;

import controller.grlGeneral.CCVinculacionRolComponenteAccion;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import modelo.grlGeneral.entidad.CERol;
import modelo.grlGeneral.entidad.CEVinculacionRolComponenteAccionMatriz;

import table.ArrayListTableModel;
import util.clases.grlGeneral.CLCheckBoxHeader;
import util.clases.grlGeneral.CLObjectABM;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.ManejadorAccionABM;
import util.clases.grlGeneral.ManejadorEventosBotones;



public class DlgRelacionComponenteRolAccion extends DialogoPadre {

    private int stateFrm=0;
    private List<CLObjectABM> oLstVinculoAccionCompRolABM;
    private int IdRol;
    private int IdComponenteAccion;
    private int IdRolComponenteAccion;
    private List<CEVinculacionRolComponenteAccionMatriz> ListaComponenteAccionDisponible;
    private List<CEVinculacionRolComponenteAccionMatriz> ListaRolComponenteAccionAsignados;
    public DlgRelacionComponenteRolAccion(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        configurarTblCompAccion();
        configurarTblAccionComp();
        TableColumn aColumn = (TableColumn)TblComponenteAccionDisponibles.getColumnModel().getColumn(0);
        aColumn.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox1.getItemListeners()[0]));
        TableColumn aColumn_ = (TableColumn)TblAccionComponenteAsignados.getColumnModel().getColumn(0);
        aColumn_.setHeaderRenderer(new CLCheckBoxHeader(jCheckBox2.getItemListeners()[0]));
        newStateForm(ManejadorEventosBotones.onNothing);
        ListaComponenteAccionDisponible=(new CCVinculacionRolComponenteAccion()).ListarVinculacionRolComponenteAccion();
        cargarTablaAcciones(ListaComponenteAccionDisponible);
        BtnCancelarVinculacion.setEnabled(true);
        BtnModificarVinculacion.setEnabled(true);
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
   private void enabledTblAccionesDisponibles(boolean a)
   {
        TxtRol.setEditable(a);
        TblComponenteAccionDisponibles.setEnabled(a);
        TxtComponenteDisponible.setEnabled(a);
   }
   private void enabledPnlDatosUnidad(boolean a)
   {
        TxtRol.setEditable(a);
   }
   private void controladorDeBotonesVinculacionABM(int stateControlBotonDocente )
      {
        if(stateControlBotonDocente==ManejadorEventosBotones.onNothing)
          {
           // TxtRol.setText("");
            this.oLstVinculoAccionCompRolABM=null;
            enabledButonsDocenteABM(false,false,false);
            enabledTblAccionesDisponibles(false);
            enabledPnlDatosUnidad(true);
            limpiarTabla(TblAccionComponenteAsignados);
            TxtRol.requestFocus();
          }
        else
          {
           if(stateControlBotonDocente==ManejadorEventosBotones.onQuery)
            {
                enabledButonsDocenteABM(false,false,true);
                enabledTblAccionesDisponibles(false);
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
                    enabledTblAccionesDisponibles(true);
                    enabledPnlDatosUnidad(false);
                    
                  }
                }
            }
        }
    }


    
  private void configurarTblCompAccion()
    {
         TblComponenteAccionDisponibles.getColumnModel().getColumn(0).setMaxWidth(40);
         TblComponenteAccionDisponibles.getColumnModel().getColumn(0).setMinWidth(40);
         TblComponenteAccionDisponibles.getColumnModel().getColumn(2).setMaxWidth(110);
         TblComponenteAccionDisponibles.getColumnModel().getColumn(2).setMinWidth(110);
    }
   private void configurarTblAccionComp()
    {
         TblAccionComponenteAsignados.getColumnModel().getColumn(0).setMaxWidth(40);
         TblAccionComponenteAsignados.getColumnModel().getColumn(0).setMinWidth(40);
         TblAccionComponenteAsignados.getColumnModel().getColumn(2).setMaxWidth(110);
         TblAccionComponenteAsignados.getColumnModel().getColumn(2).setMinWidth(110);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        TxtRol = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblAccionComponenteAsignados = new javax.swing.JTable();
        lblCliente1 = new Label.Label();
        TxtComponentesAccionAsignado = new TextField.JTxtNinguno();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblComponenteAccionDisponibles = new javax.swing.JTable(
            new ArrayListTableModel(
                new Object [][] {

                },
                new String [] {
                    "Sel.", "Componente","Accion"
                }
            ) {
                Class[] types = new Class []
                {
                    java.lang.Boolean.class, java.lang.Object.class,java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    true, false,false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            }

        );
        TxtComponenteDisponible = new TextField.JTxtNinguno();
        lblCliente = new Label.Label();
        BtnModificarVinculacion = new javax.swing.JButton();
        BtnPassLeft = new javax.swing.JButton();
        BtnPassRight = new javax.swing.JButton();
        BtnGuardarVinculacion = new javax.swing.JButton();
        BtnCancelarVinculacion = new javax.swing.JButton();

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Datos de Rol]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TxtRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtRolActionPerformed(evt);
            }
        });
        TxtRol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtRolKeyReleased(evt);
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
                .addComponent(TxtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addContainerGap(499, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Acciones que se pueden realizar]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblAccionComponenteAsignados.setModel( new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.", "Componente","Accion",
            }
        ) {
            Class[] types = new Class []
            {
                java.lang.Boolean.class, java.lang.Object.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TblAccionComponenteAsignados);

        lblCliente1.setText("Componente :");

        TxtComponentesAccionAsignado.setTamanio(250);
        TxtComponentesAccionAsignado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtComponentesAccionAsignadoActionPerformed(evt);
            }
        });
        TxtComponentesAccionAsignado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtComponentesAccionAsignadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtComponentesAccionAsignadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtComponentesAccionAsignado, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtComponentesAccionAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[Acciones Disponibles]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblComponenteAccionDisponibles.setModel( new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel.","Componente","Accion"
            }
        ) {
            Class[] types = new Class []
            {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblComponenteAccionDisponibles);

        TxtComponenteDisponible.setTamanio(250);
        TxtComponenteDisponible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtComponenteDisponibleActionPerformed(evt);
            }
        });
        TxtComponenteDisponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtComponenteDisponibleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtComponenteDisponibleKeyReleased(evt);
            }
        });

        lblCliente.setText("Componente :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtComponenteDisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtComponenteDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
        );

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

        BtnCancelarVinculacion.setFont(new java.awt.Font("Verdana", 1, 10));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnCancelarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(BtnGuardarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(BtnModificarVinculacion, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(BtnPassLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(BtnPassRight, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(BtnModificarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(BtnPassLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPassRight, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnGuardarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancelarVinculacion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
       int size =TblComponenteAccionDisponibles.getRowCount();
        for(int i=0;i<size;i++)
        {
            boolean state= evt.getStateChange()==evt.SELECTED?true:false;
            TblComponenteAccionDisponibles.setValueAt(state,i,0);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
   int size =TblAccionComponenteAsignados.getRowCount();
        for(int i=0;i<size;i++)
        {
            boolean state= evt.getStateChange()==evt.SELECTED?true:false;
            TblAccionComponenteAsignados.setValueAt(state,i,0);
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void BtnModificarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarVinculacionActionPerformed
        newStateForm(ManejadorEventosBotones.onEdit);
    }//GEN-LAST:event_BtnModificarVinculacionActionPerformed

    private void BtnPassLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassLeftActionPerformed
    pasarAccionesARol();
    }//GEN-LAST:event_BtnPassLeftActionPerformed

    private void BtnPassRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassRightActionPerformed
    eliminarAsignaturasACargo();
    }//GEN-LAST:event_BtnPassRightActionPerformed

    private void BtnGuardarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarVinculacionActionPerformed
      if(oLstVinculoAccionCompRolABM==null)
      {
          oLstVinculoAccionCompRolABM=new ArrayList<CLObjectABM>();
      }      
        for (CEVinculacionRolComponenteAccionMatriz oCERolComponenteAccionMatriz : ListaRolComponenteAccionAsignados)
        {
                  if(oCERolComponenteAccionMatriz.getIdRolCompoenteAccion()==0)
                  {
                  CLObjectABM objectABM=new CLObjectABM();
                  objectABM.setAbm(ManejadorAccionABM.insert);
                  oCERolComponenteAccionMatriz.setIdRol(IdRol);
                  objectABM.setObjeto(oCERolComponenteAccionMatriz);
                  oLstVinculoAccionCompRolABM.add(objectABM);
                  if(!(new CCVinculacionRolComponenteAccion()).ABMVinculacionComponenteAccion((ArrayList)oLstVinculoAccionCompRolABM))
                  {
                      JOptionPane.showMessageDialog(null,"No se pudo guardar los cambios", null, stateFrm);
                  }
                  else
                  {
                  oCERolComponenteAccionMatriz=null;
                  oLstVinculoAccionCompRolABM.clear();
                  }

                    BtnModificarVinculacion.setEnabled(true);
                    BtnCancelarVinculacion.setEnabled(true);
                  }
          }
        
        newStateForm(ManejadorEventosBotones.onNothing);
        ListaRolComponenteAccionAsignados= new  CCVinculacionRolComponenteAccion().ListarVinculacionComponenteAccion(IdRol);
        if(ListaRolComponenteAccionAsignados!=null)
        {
            cargarTablaAccionComp();
        }
        BtnModificarVinculacion.setEnabled(true);
        filtarAccionAsignadas();
        
    }//GEN-LAST:event_BtnGuardarVinculacionActionPerformed


    private void BtnCancelarVinculacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarVinculacionActionPerformed
    newStateForm(ManejadorEventosBotones.onNothing);
    }//GEN-LAST:event_BtnCancelarVinculacionActionPerformed

    private void TxtRolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtRolKeyReleased
    if(evt.getKeyCode()==evt.VK_BACK_SPACE)
    {
        IdRol=0;
        limpiarTabla(TblAccionComponenteAsignados);
    }
    }//GEN-LAST:event_TxtRolKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    abrirDialogoDeConsulta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtRolActionPerformed
    abrirDialogoDeConsulta();
    }//GEN-LAST:event_TxtRolActionPerformed

    private void TxtComponenteDisponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComponenteDisponibleActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_TxtComponenteDisponibleActionPerformed

    private void TxtComponenteDisponibleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComponenteDisponibleKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            ListaComponenteAccionDisponible=(new CCVinculacionRolComponenteAccion()).ListarVinculacionRolComponenteAccion();
            cargarTablaAcciones(ListaComponenteAccionDisponible);
        }
      
            filtarAccionDisponibles();
       

    }//GEN-LAST:event_TxtComponenteDisponibleKeyPressed

     private void filtarAccionDisponibles()
    {

        limpiarTabla(TblComponenteAccionDisponibles);
   
         for(CEVinculacionRolComponenteAccionMatriz pCECompRolAccion:ListaComponenteAccionDisponible )
                {
                    if(pCECompRolAccion!=null){
                    if(pCECompRolAccion.toString().toUpperCase().contains(TxtComponenteDisponible.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(false);
                        oArrayList.add(pCECompRolAccion);
                        oArrayList.add(pCECompRolAccion.getAccion());
                        ((ArrayListTableModel)TblComponenteAccionDisponibles.getModel()).addRow(oArrayList);
                    }
                 }
           }

    }
    private void TxtComponenteDisponibleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComponenteDisponibleKeyReleased


        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblComponenteAccionDisponibles.getRowCount()>0) {
                TblComponenteAccionDisponibles.requestFocus();
                TblComponenteAccionDisponibles.changeSelection(0, 0, false, false);
            }
        }

    }//GEN-LAST:event_TxtComponenteDisponibleKeyReleased

    private void TxtComponentesAccionAsignadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComponentesAccionAsignadoActionPerformed

    }//GEN-LAST:event_TxtComponentesAccionAsignadoActionPerformed

    private void TxtComponentesAccionAsignadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComponentesAccionAsignadoKeyPressed

        filtarAccionAsignadas();

    }//GEN-LAST:event_TxtComponentesAccionAsignadoKeyPressed

    private void TxtComponentesAccionAsignadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComponentesAccionAsignadoKeyReleased
 if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblAccionComponenteAsignados.getRowCount()>0) {
                TblAccionComponenteAsignados.requestFocus();
                TblAccionComponenteAsignados.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_TxtComponentesAccionAsignadoKeyReleased
    private void filtarAccionAsignadas()
    {

        try{
        limpiarTabla(TblAccionComponenteAsignados);
   
         for(CEVinculacionRolComponenteAccionMatriz pCECompRolAccion:ListaRolComponenteAccionAsignados )
                {
                    if(pCECompRolAccion!=null){
                    if(pCECompRolAccion.toString().toUpperCase().contains(TxtComponentesAccionAsignado.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                         oArrayList.add(false);
                         oArrayList.add(pCECompRolAccion);
                         oArrayList.add(pCECompRolAccion.getAccion());
                        ((ArrayListTableModel)TblAccionComponenteAsignados.getModel()).addRow(oArrayList);
                    }
                 }
           }
        }catch(Exception e)
        {
            
        }

    }

    private void abrirDialogoDeConsulta()
    {
        DlgBuscarRolComponenteAccion oDialogoConsultaDocente=new DlgBuscarRolComponenteAccion(null, true, TxtRol.getText());
        oDialogoConsultaDocente.setLocationRelativeTo(null);
        oDialogoConsultaDocente.setVisible(true);
        CERol oRol=oDialogoConsultaDocente.getRol();
        if(oRol!=null)
        {
            IdRol=oRol.getIdRol();
            TxtRol.setText(oRol.getNombre());
            ListaRolComponenteAccionAsignados= new  CCVinculacionRolComponenteAccion().ListarVinculacionComponenteAccion(IdRol);
            cargarTablaAccionComp();
            newStateForm(ManejadorEventosBotones.onQuery);
            filtarAccionAsignadas();
        }
    }

    private void pasarAccionesARol()
    {
      int rowCount=TblComponenteAccionDisponibles.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblComponenteAccionDisponibles.getValueAt(i,0).toString());
          if(valor)
          {
              CEVinculacionRolComponenteAccionMatriz oRolComponenteAccion=(CEVinculacionRolComponenteAccionMatriz)TblComponenteAccionDisponibles.getValueAt(i, 1);
              TblComponenteAccionDisponibles.setValueAt(false,i,0);
              if(verificarExistencia(oRolComponenteAccion))
              {
                  ArrayList oArrayList=new ArrayList();
                  oArrayList.add(false);
                  CEVinculacionRolComponenteAccionMatriz oCERolComponenteAccionMatriz=new CEVinculacionRolComponenteAccionMatriz();
                  oCERolComponenteAccionMatriz.setAccion(oRolComponenteAccion.getAccion());
                  oCERolComponenteAccionMatriz.setIdComponenteAccion(oRolComponenteAccion.getIdComponenteAccion());
                  oCERolComponenteAccionMatriz.setComponente(oRolComponenteAccion.getComponente());
                  oArrayList.add(oCERolComponenteAccionMatriz);
                  oArrayList.add(oCERolComponenteAccionMatriz.getAccion());
                  ((ArrayListTableModel)TblAccionComponenteAsignados.getModel()).addRow(oArrayList);
                   ListaRolComponenteAccionAsignados.add(oCERolComponenteAccionMatriz);
              }
          }
      }
    }

    private void eliminarAsignaturasACargo()
    {   
      int rowCount=TblAccionComponenteAsignados.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblAccionComponenteAsignados.getValueAt(i,0).toString());
          if(valor)
          {
              CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=(CEVinculacionRolComponenteAccionMatriz)TblAccionComponenteAsignados.getValueAt(i,1);
              IdRolComponenteAccion=oCEVinculacionComponenteAccionMatriz.getIdRolCompoenteAccion();
              if(oCEVinculacionComponenteAccionMatriz.getIdRolCompoenteAccion()!=0)
              {
              if(oLstVinculoAccionCompRolABM==null)
              {
                  oLstVinculoAccionCompRolABM=new ArrayList<CLObjectABM>();
              }
              CLObjectABM objectABM=new CLObjectABM();
              objectABM.setAbm(ManejadorAccionABM.delete);
              oCEVinculacionComponenteAccionMatriz.setIdRolCompoenteAccion(IdRolComponenteAccion);
              objectABM.setObjeto(oCEVinculacionComponenteAccionMatriz);
              oLstVinculoAccionCompRolABM.add(objectABM);
              }

             ((ArrayListTableModel)TblAccionComponenteAsignados.getModel()).removeRow(i);
              rowCount--;
              i--;
              if(!(new CCVinculacionRolComponenteAccion()).ABMVinculacionComponenteAccion((ArrayList)oLstVinculoAccionCompRolABM))
              {
                  JOptionPane.showMessageDialog(null,"No se pudo Eliminar", null, stateFrm);

              }
              else
              {
                 
              }
        }
      }
                newStateForm(ManejadorEventosBotones.onNothing);
                ListaRolComponenteAccionAsignados= new  CCVinculacionRolComponenteAccion().ListarVinculacionComponenteAccion(IdRol);
                if(ListaRolComponenteAccionAsignados!=null)
                {
                    cargarTablaAccionComp();
                    filtarAccionAsignadas();

                }
                BtnModificarVinculacion.setEnabled(true);
    }

    private boolean verificarExistencia(CEVinculacionRolComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz)
    {
        int rowCount=TblAccionComponenteAsignados.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            CEVinculacionRolComponenteAccionMatriz oCEAccion=(CEVinculacionRolComponenteAccionMatriz)TblAccionComponenteAsignados.getValueAt(i,1);
            if(oCEAccion.getIdComponenteAccion()==oCEVinculacionComponenteAccionMatriz.getIdComponenteAccion())
            {
            return false;
            }
        }
        return true;
    }
    public void cargarTablaAcciones(List<CEVinculacionRolComponenteAccionMatriz> oLstCEVinculacionComponenteAccionMatrizs)
    {
      limpiarTabla(TblComponenteAccionDisponibles);


        if(oLstCEVinculacionComponenteAccionMatrizs!=null)
            {   
              ArrayListTableModel oModeloTblAsignatura=(ArrayListTableModel)TblComponenteAccionDisponibles.getModel();
              for(int i=0;i<oLstCEVinculacionComponenteAccionMatrizs.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(oLstCEVinculacionComponenteAccionMatrizs.get(i));
                     oArrayList.add(oLstCEVinculacionComponenteAccionMatrizs.get(i).getAccion());
                     oModeloTblAsignatura.addRow(oArrayList);
                 }
            }

        }

    public void cargarTablaAccionComp()
    {
      limpiarTabla(TblAccionComponenteAsignados);
        if(ListaRolComponenteAccionAsignados!=null)
            {
              ArrayListTableModel oModeloTblAsignatura=(ArrayListTableModel)TblAccionComponenteAsignados.getModel();
              for(int i=0;i<ListaRolComponenteAccionAsignados.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(ListaRolComponenteAccionAsignados.get(i));
                     oArrayList.add(ListaRolComponenteAccionAsignados.get(i).getAccion());
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
    private javax.swing.JTable TblAccionComponenteAsignados;
    private javax.swing.JTable TblComponenteAccionDisponibles;
    private TextField.JTxtNinguno TxtComponenteDisponible;
    private TextField.JTxtNinguno TxtComponentesAccionAsignado;
    private javax.swing.JTextField TxtRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private Label.Label lblCliente;
    private Label.Label lblCliente1;
    // End of variables declaration//GEN-END:variables



}
