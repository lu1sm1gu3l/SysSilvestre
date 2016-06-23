package view.grlGeneral;
import controller.grlGeneral.CCRol;
import controller.grlGeneral.CCUsuarioRol;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.grlGeneral.entidad.CERol;
import modelo.grlGeneral.entidad.CEUsuario;
import modelo.grlGeneral.entidad.CEUsuarioRolMatriz;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLObjectABM;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.ManejadorAccionABM;
import util.clases.grlGeneral.ManejadorEventosBotones;

public class DlgRelacionUsaurioRol extends DialogoPadre{
    private int stateFrm=0;
    private List<CLObjectABM> oLstABMUsuarioRol;
    private int IdUsuario=0;
    public DlgRelacionUsaurioRol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarTblRol();
        configurarTblRolUsuario();
        newStateForm(stateFrm);
        cargarTablaRol(new CCRol().ListarRol());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblRolUsuario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblRol = new javax.swing.JTable();
        BtnPassRigth = new javax.swing.JButton();
        BtnPassLeft = new javax.swing.JButton();
        BtnModificarRol = new javax.swing.JButton();
        BtnCancelarRol = new javax.swing.JButton();
        BtnGuardarRol = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        BtnBuscarUsuario = new javax.swing.JButton();
        TxtUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18));
        jLabel3.setText("ROL POR USUARIO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(jLabel3)
                .addContainerGap(411, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles que tiene el usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblRolUsuario.setModel( new ArrayListTableModel(
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
        jScrollPane1.setViewportView(TblRolUsuario);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(781, 471));

        TblRol.setModel(new ArrayListTableModel(
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
        jScrollPane2.setViewportView(TblRol);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
        );

        BtnPassRigth.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnPassRigth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnPassRigth.setText("Eliminar");
        BtnPassRigth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPassRigth.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPassRigth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPassRigthActionPerformed(evt);
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

        BtnModificarRol.setFont(new java.awt.Font("Verdana", 1, 10));
        BtnModificarRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnModificarRol.setText("Modificar ");
        BtnModificarRol.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnModificarRol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnModificarRol.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnModificarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarRolActionPerformed(evt);
            }
        });

        BtnCancelarRol.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        BtnCancelarRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelarRol.setText("Cancelar  ");
        BtnCancelarRol.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelarRol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCancelarRol.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarRolActionPerformed(evt);
            }
        });

        BtnGuardarRol.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        BtnGuardarRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardarRol.setText("Guardar ");
        BtnGuardarRol.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardarRol.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardarRol.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnGuardarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarRolActionPerformed(evt);
            }
        });

        jLabelUsuario.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabelUsuario.setText("Usuario:");

        BtnBuscarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        BtnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarUsuarioActionPerformed(evt);
            }
        });

        TxtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtUsuarioActionPerformed(evt);
            }
        });
        TxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtUsuarioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnPassLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(BtnPassRigth, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(BtnCancelarRol, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(BtnModificarRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnGuardarRol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelUsuario)
                        .addComponent(TxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(BtnModificarRol, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(BtnPassLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPassRigth, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(BtnGuardarRol, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCancelarRol, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void configurarTblRol()
        {
             TblRol.getColumnModel().getColumn(0).setMaxWidth(50);
             TblRol.getColumnModel().getColumn(0).setMinWidth(50);
        }

    private void configurarTblRolUsuario()
        {
             TblRolUsuario.getColumnModel().getColumn(0).setMaxWidth(50);
             TblRolUsuario.getColumnModel().getColumn(0).setMinWidth(50);
        }

   
  
    private void BtnPassRigthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassRigthActionPerformed
eliminarRolesPorUsuario();
}//GEN-LAST:event_BtnPassRigthActionPerformed

    private void BtnPassLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPassLeftActionPerformed
passRolesPorUsuario();
}//GEN-LAST:event_BtnPassLeftActionPerformed

    private void BtnModificarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarRolActionPerformed
newStateForm(ManejadorEventosBotones.onEdit);
}//GEN-LAST:event_BtnModificarRolActionPerformed

    private void BtnCancelarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarRolActionPerformed
newStateForm(ManejadorEventosBotones.onNothing);
TxtUsuario.setText("");
}//GEN-LAST:event_BtnCancelarRolActionPerformed

    private void BtnGuardarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarRolActionPerformed
    if(oLstABMUsuarioRol==null)
      {
          oLstABMUsuarioRol=new ArrayList<CLObjectABM>();
      }
      int rowCount=TblRolUsuario.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          CEUsuarioRolMatriz oCEUsuarioRolMatriz=(CEUsuarioRolMatriz)TblRolUsuario.getValueAt(i,1);
          if(oCEUsuarioRolMatriz.getIdUsuarioRol()==0)
          {
          CLObjectABM objectABM=new CLObjectABM();
          objectABM.setAbm(ManejadorAccionABM.insert);
          oCEUsuarioRolMatriz.setIdUsuario(IdUsuario);
          objectABM.setObjeto(oCEUsuarioRolMatriz);
          oLstABMUsuarioRol.add(objectABM);
          }
      }

      if(!(new CCUsuarioRol()).ABMUsuarioRol((ArrayList)oLstABMUsuarioRol))
      {
          JOptionPane.showMessageDialog(null,"No se pudo guardar los cambios", null, stateFrm);

      }
      else
      {
        newStateForm(ManejadorEventosBotones.onNothing);
        List<CEUsuarioRolMatriz> oLstUsuarioRolmATRIZ= new  CCUsuarioRol().ListarUsuarioRol(1, IdUsuario);
        if(oLstUsuarioRolmATRIZ!=null)
        {
            cargarTablaUsuarioRol((ArrayList)oLstUsuarioRolmATRIZ);
        }

      }
      
    }//GEN-LAST:event_BtnGuardarRolActionPerformed

    private void BtnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarUsuarioActionPerformed
        DlgBuscarUsuario dialogoUsuario = new DlgBuscarUsuario(null, true,TxtUsuario.getText());
        dialogoUsuario.setTitle("Busqueda Usuario");
        dialogoUsuario.setVisible(true);
        CEUsuario oCEUsuario=dialogoUsuario.getUsuario();
        if(oCEUsuario!=null)
        {
            IdUsuario = oCEUsuario.getIdUsuario();
            TxtUsuario.setText(oCEUsuario.getUsuario());
            cargarTablaUsuarioRol((new CCUsuarioRol()).ListarUsuarioRol(1,IdUsuario));
        }
    }//GEN-LAST:event_BtnBuscarUsuarioActionPerformed

    private void TxtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtUsuarioActionPerformed
         limpiarTabla(TblRolUsuario);
         DlgBuscarUsuario dialogoUsuario = new DlgBuscarUsuario(null, true,TxtUsuario.getText());
         dialogoUsuario.setTitle("Busqueda Usuario");
         dialogoUsuario.setVisible(true);
         CEUsuario oCEUsuario=dialogoUsuario.getUsuario();
         if(oCEUsuario!=null)
         {
             IdUsuario=oCEUsuario.getIdUsuario();
             TxtUsuario.setText(oCEUsuario.getUsuario());
             cargarTablaUsuarioRol((new CCUsuarioRol()).ListarUsuarioRol(1,IdUsuario));
         }
    }//GEN-LAST:event_TxtUsuarioActionPerformed

    private void TxtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsuarioKeyReleased
        if(evt.getKeyCode()==evt.VK_BACK_SPACE)
        {
            IdUsuario=0;
            limpiarTabla(TblRolUsuario);
        }
    }//GEN-LAST:event_TxtUsuarioKeyReleased

     private void cargarTablaUsuarioRol(List<CEUsuarioRolMatriz> oLstUsuarioRolMatriz)
    {
        if(oLstUsuarioRolMatriz!=null)
            {
              ArrayListTableModel oModeloTblRoles=(ArrayListTableModel)TblRolUsuario.getModel();
             for(int i=0;i<oLstUsuarioRolMatriz.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(oLstUsuarioRolMatriz.get(i));
                     oArrayList.add(0);
                     oModeloTblRoles.addRow(oArrayList);
                }
            }
    } 
     private void cargarTablaRol(List<CERol> oLstRol)
    {
        if(oLstRol!=null)
            {
              ArrayListTableModel oModeloTblRoles=(ArrayListTableModel)TblRol.getModel();
             for(int i=0;i<oLstRol.size();i++)
                 {
                     ArrayList oArrayList =new ArrayList();
                     oArrayList.add(false);
                     oArrayList.add(oLstRol.get(i));
                     oArrayList.add(0);
                     oModeloTblRoles.addRow(oArrayList);
                }
            }
    }

    private void newStateForm(int state)
    {
        this.stateFrm=state;
      controladorDeBotonesVinculacionABM(stateFrm);
    }
     private void enabledButonsDocenteABM(boolean stateModificar,boolean statePass,boolean stateGuardarCancelar,boolean stateCancelar)
    {
        BtnModificarRol.setEnabled(stateModificar);
        BtnPassRigth.setEnabled(statePass);
        BtnPassLeft.setEnabled(statePass);
        BtnGuardarRol.setEnabled(stateGuardarCancelar);
        BtnCancelarRol.setEnabled(stateCancelar);
    }

    private void controladorDeBotonesVinculacionABM(int stateControlBotonDocente )
      {
        if(stateControlBotonDocente==ManejadorEventosBotones.onNothing)
          {
            this.oLstABMUsuarioRol=null;
            enabledButonsDocenteABM(true,false,false,false);
            enabledPnlRoles(true);
            enabledPnlRolesPorUsuario(true);
            limpiarTabla(TblRolUsuario);

          }
        else
          {
           if(stateControlBotonDocente==ManejadorEventosBotones.onQuery)
            {
                enabledButonsDocenteABM(false,false,false,true);
                enabledPnlRoles(false);
                enabledPnlRolesPorUsuario(true);
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
                    enabledButonsDocenteABM(false,true,true,true);
                    enabledPnlRoles(true);
                    enabledPnlRolesPorUsuario(false);
                  }
                }
            }
        }
    }
      private void enabledPnlRoles(boolean a)
   {
        BtnBuscarUsuario.setEnabled(a);
        TblRol.setEnabled(a);
   }
   private void enabledPnlRolesPorUsuario(boolean a)
   {
        TxtUsuario.setEnabled(a);
   }
    private void limpiarTabla(JTable Tbl)
    {
         ArrayListTableModel oModeloTblRoles=(ArrayListTableModel)Tbl.getModel();
         int size =oModeloTblRoles.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
            oModeloTblRoles.removeRow(0);
            }
         }
     }
private void abrirDialogoDeConsulta()
{
                String parametro = TxtUsuario.getText().trim();
                DlgBuscarUsuario oDialogoConsultaUsuario=new DlgBuscarUsuario(null, rootPaneCheckingEnabled, parametro);
                oDialogoConsultaUsuario.setLocationRelativeTo(null);
                oDialogoConsultaUsuario.setVisible(true);
                CEUsuario oUCEUsuario=oDialogoConsultaUsuario.getUsuario();
}
 private void passRolesPorUsuario()
    {
      int rowCount=TblRol.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblRol.getValueAt(i,0).toString());
          if(valor)
          {
              CERol oRol=(CERol)TblRol.getValueAt(i,1);
              
              if(verificarExistencia(oRol))
              {
                  ArrayList oArrayList=new ArrayList();
                  oArrayList.add(false);
                  CEUsuarioRolMatriz oCEUsuarioRolMatriz=new CEUsuarioRolMatriz();
                  oCEUsuarioRolMatriz.setRol(oRol.getNombre());
                  oCEUsuarioRolMatriz.setIdRol(oRol.getIdRol());
                  oArrayList.add(oCEUsuarioRolMatriz);
                  ((ArrayListTableModel)TblRolUsuario.getModel()).addRow(oArrayList);
              }
              TblRol.setValueAt(false,i,0);

          }
      }
    }
     private void eliminarRolesPorUsuario()
    {
      int rowCount=TblRolUsuario.getRowCount();
      for(int i=0;i<rowCount;i++)
      {
          boolean valor=Boolean.parseBoolean(TblRolUsuario.getValueAt(i,0).toString());
          if(valor)
          {
              CEUsuarioRolMatriz oCEUsuarioRolMatriz=(CEUsuarioRolMatriz)TblRolUsuario.getValueAt(i,1);
              if(oCEUsuarioRolMatriz.getIdUsuarioRol()!=0)
              {
              if(oLstABMUsuarioRol==null)
              {
                  oLstABMUsuarioRol=new ArrayList<CLObjectABM>();
              }
              CLObjectABM objectABM=new CLObjectABM();
              objectABM.setAbm(ManejadorAccionABM.delete);
              objectABM.setObjeto(oCEUsuarioRolMatriz);
              oLstABMUsuarioRol.add(objectABM);
              }

             ((ArrayListTableModel)TblRolUsuario.getModel()).removeRow(i);
              rowCount--;
              i--;
          }
      }
    }
    private boolean verificarExistencia(CERol oRol)
    {
        int rowCount=TblRolUsuario.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            CEUsuarioRolMatriz oCERol=(CEUsuarioRolMatriz)TblRolUsuario.getValueAt(i,1);
            if(oCERol.getIdRol()==oRol.getIdRol())
            {
            return false;
            }
        }
        return true;
    }

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarUsuario;
    private javax.swing.JButton BtnCancelarRol;
    private javax.swing.JButton BtnGuardarRol;
    private javax.swing.JButton BtnModificarRol;
    private javax.swing.JButton BtnPassLeft;
    private javax.swing.JButton BtnPassRigth;
    private javax.swing.JTable TblRol;
    private javax.swing.JTable TblRolUsuario;
    private javax.swing.JTextField TxtUsuario;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
