package view.almAlmacen.invInventarios;


import controller.almAlmacen.CCAlmacen;
import controller.almAlmacen.CCTransferenciaAlmacen;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.almAlmacen.entidad.CETransferenciaAlmacen;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;


public class DlgBusquedaTransferenciaAlmacen extends DialogoPadre
{

    private List<CETransferenciaAlmacen> oListaTransferenciaAlmacen;
    private java.awt.Frame oparent;
    private boolean cargar;
    public DlgBusquedaTransferenciaAlmacen(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        cargar=false;
        this.setLocationRelativeTo(null);
        configurarTabla();
        DtChDel.setDate(FrmSistemaMenu.FechaSistema);
        DtChAl.setDate(FrmSistemaMenu.FechaSistema);
        DtChDel.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChAl.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        this.oparent=parent;
        cargar=true;
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        filtrarTabla();
    }
    private void configurarTabla()
    {
        TableColumnModel oTableColumnModel=TblDato.getColumnModel();
        oTableColumnModel.getColumn(0).setPreferredWidth(150);
        oTableColumnModel.getColumn(1).setPreferredWidth(100);
        oTableColumnModel.getColumn(2).setPreferredWidth(280);
        oTableColumnModel.getColumn(3).setPreferredWidth(100);
        oTableColumnModel.getColumn(4).setPreferredWidth(95);
        oTableColumnModel.getColumn(5).setPreferredWidth(95);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

     //   TblPedidosPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(1).setCellRenderer(tccent);
        //TblPedidosPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(6).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblDato.getColumnModel().getColumn(5).setCellRenderer(tcr);

        ArrayList<CEAlmacen> listaAlmacenDestino=CCAlmacen.listarAlmacenSucursalMasTodos();
        CbxDestino.setModel(CLComboBox.cargarCombo((listaAlmacenDestino)));
        CbxOrigen.setModel(CLComboBox.cargarCombo((listaAlmacenDestino)));
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenuPedido = new javax.swing.JPopupMenu();
        jMenuItemCancelarPedido = new javax.swing.JMenuItem();
        jMenuItemAnularPedido = new javax.swing.JMenuItem();
        tbBusqueda = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        chkFecha = new Label.Label();
        DtChDel =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        lblTipocomp = new Label.Label();
        CbxOrigen = new ComboBox.ComboBox();
        CbxDestino = new ComboBox.ComboBox();
        LblEstado = new Label.Label();
        chkFecha1 = new Label.Label();
        DtChAl =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        btnBuscar2 = new BotonesABM.BtnBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDato = new javax.swing.JTable()
        ;
        LblColaPedidos = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnExportar4 = new BotonesABM.BtnExportar();
        BtnRefrescar = new javax.swing.JButton();
        btnAceptar1 = new BotonesABM.BtnAceptar();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Transferecia a Almacén");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbBusqueda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbBusquedaStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        chkFecha.setText("DEL :");

        DtChDel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChDelPropertyChange(evt);
            }
        });

        lblTipocomp.setText("Origen   :");

        CbxOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxOrigenActionPerformed(evt);
            }
        });

        CbxDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxDestinoActionPerformed(evt);
            }
        });

        LblEstado.setText("Destino  :");

        chkFecha1.setText("AL   :");

        DtChAl.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChAlPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DtChDel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                        .addComponent(DtChAl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTipocomp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CbxDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CbxOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(386, 386, 386))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DtChDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTipocomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DtChAl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("Numero Doc:");

        TxtCodigo.setTamanio(150);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyReleased(evt);
            }
        });

        btnBuscar2.setText("Buscar Numero Doc");
        btnBuscar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar2.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Por Codigo", jPanel5);

        TblDato.setFont(new java.awt.Font("Arial", 0, 14));
        TblDato.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "N° Transferencia",
                "Almacen Orgigen",
                "N° Salida",
                "Almacen Destino",
                "Nº Ingreso",
                "Estado"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDato.setRowHeight(20);
        TblDato.getTableHeader().setReorderingAllowed(false);
        TblDato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDatoMouseClicked(evt);
            }
        });
        TblDato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TblDatoFocusLost(evt);
            }
        });
        TblDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblDatoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TblDato);

        LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
        LblColaPedidos.setText("Lista Comprobante Venta");

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btnExportar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar4.setText("");
        btnExportar4.setToolTipText("Exportar");
        btnExportar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar4ActionPerformed(evt);
            }
        });
        jPanel7.add(btnExportar4);

        BtnRefrescar.setFont(new java.awt.Font("Verdana", 1, 12));
        BtnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refrescar.png"))); // NOI18N
        BtnRefrescar.setToolTipText("Refrescar");
        BtnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnRefrescar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefrescarActionPerformed(evt);
            }
        });
        jPanel7.add(BtnRefrescar);
        BtnRefrescar.getAccessibleContext().setAccessibleName("0");
        BtnRefrescar.getAccessibleContext().setAccessibleDescription("");

        btnAceptar1.setText("");
        btnAceptar1.setToolTipText("Seleccionar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnAceptar1);
        btnAceptar1.getAccessibleContext().setAccessibleName("0");
        btnAceptar1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
                    .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblColaPedidos))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(818, 818, 818)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblColaPedidos)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(554, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


  
    private void TblDatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TblDatoFocusLost

}//GEN-LAST:event_TblDatoFocusLost

    private void TblDatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDatoMouseClicked

        if (evt.getClickCount()==2) {
            SelecionarRegistro();
        }
}//GEN-LAST:event_TblDatoMouseClicked

     boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

  
   
    private void TblDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblDatoKeyPressed

        TblDato.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblDato.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                SelecionarRegistro();


            }
        });

    }//GEN-LAST:event_TblDatoKeyPressed

    private void tbBusquedaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbBusquedaStateChanged

        if(cargar){
            
            if(tbBusqueda.getSelectedIndex()==0){
                
            }else if (tbBusqueda.getSelectedIndex()==1){
                TxtCodigo.setText("");
            }
            filtrarTabla();
        }
}//GEN-LAST:event_tbBusquedaStateChanged

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        filtrarTabla();
}//GEN-LAST:event_btnBuscar2ActionPerformed

    private void TxtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN) {

            if(TblDato.getRowCount()>0) {
                TblDato.requestFocus();
                TblDato.changeSelection(0, 0, false, false);
            }
        }
}//GEN-LAST:event_TxtCodigoKeyReleased

    private void TxtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            filtrarTabla();

        }
    }//GEN-LAST:event_TxtCodigoKeyPressed

    private void DtChAlPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChAlPropertyChange
        if(cargar)
            if(!iscerrando){
                if(DtChDel.getDate()!=null){
                    filtrarTabla();

                }
            }
}//GEN-LAST:event_DtChAlPropertyChange

    private void CbxDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxDestinoActionPerformed
        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxDestinoActionPerformed

    private void CbxOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxOrigenActionPerformed

        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxOrigenActionPerformed

    private void DtChDelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChDelPropertyChange

        if(cargar)
            if(!iscerrando){
                if(DtChDel.getDate()!=null){
                    filtrarTabla();

                }
                if(evt.getPropertyName().equals("date")) {
                DtChAl.setMinSelectableDate(DtChDel.getDate());
                }
            }
}//GEN-LAST:event_DtChDelPropertyChange

    private void btnExportar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar4ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblDato,
                new String[]{},new String[]{},new String[]{}
        ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar4ActionPerformed

    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed
        BtnRefrescar.setEnabled(false);

        filtrarTabla();

        BtnRefrescar.setEnabled(true);
}//GEN-LAST:event_BtnRefrescarActionPerformed

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        SelecionarRegistro();
}//GEN-LAST:event_btnAceptar1ActionPerformed

    CETransferenciaAlmacen oCETransferenciaAlmacen=null;

public CETransferenciaAlmacen getCETransferenciaAlmacen()
{
    return oCETransferenciaAlmacen;
}
private void SelecionarRegistro()
    {
        int filSel=TblDato.getSelectedRow();
        if(filSel!=-1){
        oCETransferenciaAlmacen=(CETransferenciaAlmacen)TblDato.getValueAt(filSel, 2);
        dispose();
        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CETransferenciaAlmacen> listarRegistros()
    {
        String dato=TxtCodigo.getText();

        CEAlmacen oCEAlmacenOrigen=(CEAlmacen)CbxOrigen.getSelectedItem();
        CEAlmacen oCEAlmacenDestino=(CEAlmacen)CbxDestino.getSelectedItem();
        
        if(tbBusqueda.getSelectedIndex()==1){
            return CCTransferenciaAlmacen.BuscarPorCodigo(dato);
                }
        
        

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChDel.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChDel.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaIni=ocConvertidorFecha.getFechaSimple2();
            ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChDel.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
           return CCTransferenciaAlmacen.BusqAvanzada(oCEAlmacenOrigen.getIdAlmacen(),oCEAlmacenDestino.getIdAlmacen(),pFechaIni,pFechaFin);
            }
        }
        return null;
      

    }

    private void filtrarTabla()
    {
        oListaTransferenciaAlmacen=listarRegistros();
       limpiarTabla();
       if(oListaTransferenciaAlmacen!=null)
       {

                for(CETransferenciaAlmacen vCETransferenciaAlmacen:oListaTransferenciaAlmacen )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(vCETransferenciaAlmacen.getFecha());
                        oArrayList.add(vCETransferenciaAlmacen.getNumeroTransferencia());
                        oArrayList.add(vCETransferenciaAlmacen);
                        oArrayList.add(vCETransferenciaAlmacen.getNumeroSalida());
                        oArrayList.add(vCETransferenciaAlmacen.getAlmacenDestino());                        
                        oArrayList.add(vCETransferenciaAlmacen.getNumeroIngreso());
                        oArrayList.add(vCETransferenciaAlmacen.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    
                }
                LblColaPedidos.setText("Cola de Registros: "+TblDato.getRowCount()+" registros(s) Encontrados");
            
        }
       if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }

    }
    private void limpiarTabla()
    {
        int size=TblDato.getRowCount();
        for(int i=0;i<size;i++ )
        {
        ((ArrayListTableModel)TblDato.getModel()).removeRow(0);
        }
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRefrescar;
    private ComboBox.ComboBox CbxDestino;
    private ComboBox.ComboBox CbxOrigen;
    private com.toedter.calendar.JDateChooser DtChAl;
    private com.toedter.calendar.JDateChooser DtChDel;
    private Label.Label LblCodigo;
    private javax.swing.JLabel LblColaPedidos;
    private Label.Label LblEstado;
    private javax.swing.JTable TblDato;
    private TextField.JTxtNinguno TxtCodigo;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnBuscar btnBuscar2;
    private BotonesABM.BtnExportar btnExportar4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private Label.Label chkFecha;
    private Label.Label chkFecha1;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private Label.Label lblTipocomp;
    private javax.swing.JTabbedPane tbBusqueda;
    // End of variables declaration//GEN-END:variables
   
}
