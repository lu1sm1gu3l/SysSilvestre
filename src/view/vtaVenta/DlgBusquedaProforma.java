package view.vtaVenta;

import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCProforma;
import controller.vtaVenta.CCTipoComprobante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CEProformaMatriz;
import modelo.vtaVenta.entidad.CETipoComprobante;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
import view.cmrComercial.DlgGestionCliente;


public class DlgBusquedaProforma extends DialogoPadre implements ActionListener
{

    private List<CEProformaMatriz> oListaProformaMatriz;
    private java.awt.Frame oparent;
    private boolean cargar;
    public DlgBusquedaProforma(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        cargar=false;
        this.setLocationRelativeTo(null);
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        configurarTablaProformasPedientes();        
        DtChFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFinal.setDate(FrmSistemaMenu.FechaSistema);
        DtChFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFinal.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        CbxTipoComp.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaProforma()));
        CbxEstado.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstadProformaMasTodos()));
        this.oparent=parent;
        cargar=true;
        filtrarTabla();
    }
    private void configurarTablaProformasPedientes()
    {
        TableColumnModel oTableColumnModel=TblDato.getColumnModel();
        oTableColumnModel.getColumn(0).setPreferredWidth(140);
        oTableColumnModel.getColumn(1).setPreferredWidth(110);
        oTableColumnModel.getColumn(2).setPreferredWidth(300);
        oTableColumnModel.getColumn(3).setPreferredWidth(100);
        oTableColumnModel.getColumn(4).setPreferredWidth(55);
        oTableColumnModel.getColumn(5).setPreferredWidth(95);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

     //   TblProformasPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(1).setCellRenderer(tccent);
        //TblProformasPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(6).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblDato.getColumnModel().getColumn(5).setCellRenderer(tcr);
  ;
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
        DtChFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        TxtCliente = new TextField.JTxtNinguno();
        lblCliente = new Label.Label();
        CbxEstado = new ComboBox.ComboBox();
        LblEstado = new Label.Label();
        CbxTipoComp = new ComboBox.ComboBox();
        lblTipocomp = new Label.Label();
        chkFecha1 = new Label.Label();
        DtChFechaFinal =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        btnBuscar2 = new BotonesABM.BtnBuscar();
        jPanel4 = new javax.swing.JPanel();
        LblCodigo1 = new Label.Label();
        TxtBusqCliente = new TextField.JTxtNinguno();
        btnBuscar1 = new BotonesABM.BtnBuscar();
        LblColaProformas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDato = new javax.swing.JTable()
        ;
        jPanel1 = new javax.swing.JPanel();
        btnExportar1 = new BotonesABM.BtnExportar();
        BtnRefrescar = new javax.swing.JButton();
        btnAceptar1 = new BotonesABM.BtnAceptar();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Proformas");
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
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkFecha.setText("Al :");
        jPanel3.add(chkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 100, -1));

        TxtCliente.setTamanio(250);
        TxtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtClienteActionPerformed(evt);
            }
        });
        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtClienteKeyReleased(evt);
            }
        });
        jPanel3.add(TxtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 290, -1));

        lblCliente.setText("Cliente :");
        jPanel3.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        CbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(CbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 161, -1));

        LblEstado.setText("Estado:");
        jPanel3.add(LblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        CbxTipoComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoCompActionPerformed(evt);
            }
        });
        jPanel3.add(CbxTipoComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 160, -1));

        lblTipocomp.setText("Comprobante :");
        jPanel3.add(lblTipocomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        chkFecha1.setText("Del :");
        jPanel3.add(chkFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, -1));

        DtChFechaFinal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaFinalPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 100, -1));

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("N° Pedido :");

        TxtCodigo.setTamanio(150);
        TxtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCodigoKeyReleased(evt);
            }
        });

        btnBuscar2.setText("Buscar Número");
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
                .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda Por Numero", jPanel5);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo1.setText("Cliente :");

        TxtBusqCliente.setTamanio(150);
        TxtBusqCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtBusqClienteKeyReleased(evt);
            }
        });

        btnBuscar1.setText("Buscar Cliente");
        btnBuscar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtBusqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtBusqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbBusqueda.addTab("Busqueda por Cliente", jPanel4);

        LblColaProformas.setFont(new java.awt.Font("Arial", 0, 14));
        LblColaProformas.setForeground(new java.awt.Color(153, 0, 0));
        LblColaProformas.setText("Lista Comprobante Venta");

        TblDato.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TblDato.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "Código",
                "Proveedor",
                "Tip.Comprob. Ref.",
                "Moneda",
                "Total",
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
        TblDato.getTableHeader().setResizingAllowed(false);
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

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar1.setText("");
        btnExportar1.setToolTipText("Exportar");
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1);

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
        jPanel1.add(BtnRefrescar);
        BtnRefrescar.getAccessibleContext().setAccessibleName("0");
        BtnRefrescar.getAccessibleContext().setAccessibleDescription("");

        btnAceptar1.setText("");
        btnAceptar1.setToolTipText("Seleccionar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAceptar1);
        btnAceptar1.getAccessibleContext().setAccessibleName("0");
        btnAceptar1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblColaProformas, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(LblColaProformas))
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

    
    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            filtrarTabla();

            }
            if(evt.getPropertyName().equals("date")) {
                DtChFechaFinal.setMinSelectableDate(DtChFecha.getDate());
            }
        }
    }//GEN-LAST:event_DtChFechaPropertyChange
 boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void TxtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
            filtrarTabla();

        }

    }//GEN-LAST:event_TxtCodigoKeyPressed

    private long id_Cliente;
    private void buscarCliente()
    {
          DlgGestionCliente odialogo= new DlgGestionCliente(oparent, true,1,1,1);//consultar

          odialogo.setCajaTexo(TxtBusqCliente.getText().trim());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Cliente");
                  if(odialogo.getoCECliente()!=null){
                    id_Cliente=odialogo.getoCECliente().getIdCliente();
                    TxtBusqCliente.setText(odialogo.getoCECliente().getNombreCompleto());
                    }

      }
    private void TxtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCodigoKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN) {

        if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }
        }
    }//GEN-LAST:event_TxtCodigoKeyReleased

    private void TxtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtClienteActionPerformed

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed

        if(evt.getKeyCode()==evt.VK_ENTER) {
         filtarPorcliente();
        }
        
    }//GEN-LAST:event_TxtClienteKeyPressed

    private void filtarPorcliente()
    {
    limpiarTabla();
         for(CEProformaMatriz pCEProformaMatriz:oListaProformaMatriz )
                {
                    if(pCEProformaMatriz.toString().contains(TxtCliente.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEProformaMatriz.getFecha());
                        oArrayList.add(pCEProformaMatriz.getCodigo());
                        oArrayList.add(pCEProformaMatriz);
                        oArrayList.add(pCEProformaMatriz.getTipoComprobante());
                        oArrayList.add(pCEProformaMatriz.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEProformaMatriz.getTotalPagar()));
                        oArrayList.add(pCEProformaMatriz.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    }
                }

    }
    private void TxtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyReleased


        if(evt.getKeyCode()==evt.VK_DOWN) {
            if(TblDato.getRowCount()>0)
        {
          TblDato.requestFocus();
          TblDato.changeSelection(0, 0, false, false);
        }
        }
        

    }//GEN-LAST:event_TxtClienteKeyReleased

    private void CbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxEstadoActionPerformed
        if(cargar)
        filtrarTabla();
    }//GEN-LAST:event_CbxEstadoActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        buscarCliente();
        filtrarTabla();
}//GEN-LAST:event_btnBuscar1ActionPerformed

    private void TxtBusqClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusqClienteKeyPressed
         if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarCliente();
             filtrarTabla();

        }

    }//GEN-LAST:event_TxtBusqClienteKeyPressed

    private void TxtBusqClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtBusqClienteKeyReleased
         if(evt.getKeyCode()==evt.VK_ENTER) {            

            filtrarTabla();
        }

    }//GEN-LAST:event_TxtBusqClienteKeyReleased

    private void tbBusquedaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbBusquedaStateChanged

        if(cargar){
        id_Cliente=-1;
        if(tbBusqueda.getSelectedIndex()==0){
                TxtCliente.setText("");
            }else if (tbBusqueda.getSelectedIndex()==1){
                TxtCodigo.setText("");
            }else if (tbBusqueda.getSelectedIndex()==2){
                TxtBusqCliente.setText("");
            }
            filtrarTabla();
        }
    }//GEN-LAST:event_tbBusquedaStateChanged

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void TblDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblDatoKeyPressed

        TblDato.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblDato.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                SelecionarRegistro();


            }
        });

    }//GEN-LAST:event_TblDatoKeyPressed

    private void CbxTipoCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoCompActionPerformed

        if(cargar)
            filtrarTabla();
}//GEN-LAST:event_CbxTipoCompActionPerformed

    private void DtChFechaFinalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaFinalPropertyChange

        if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            filtrarTabla();

            }
        }
    }//GEN-LAST:event_DtChFechaFinalPropertyChange

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblDato,
                new String[]{},new String[]{},new String[]{}
        ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed
        BtnRefrescar.setEnabled(false);

        filtrarTabla();

        BtnRefrescar.setEnabled(true);
}//GEN-LAST:event_BtnRefrescarActionPerformed

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        SelecionarRegistro();
}//GEN-LAST:event_btnAceptar1ActionPerformed

    CEProformaMatriz oCEProformaMatriz=null;

public CEProformaMatriz getCEProformaMatriz()
{
    return oCEProformaMatriz;
}
private void SelecionarRegistro()
    {
        int filSel=TblDato.getSelectedRow();
        if(filSel!=-1){
        oCEProformaMatriz=(CEProformaMatriz)TblDato.getValueAt(filSel, 2);
        dispose();

        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CEProformaMatriz> listarRegistros()
    {
        String dato=TxtCodigo.getText();
        int id_Estado=2;
        CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComp.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
//        if(RbtCliente.isSelected())
//        {
//            return CCComprobanteCompra.BuscarComprobanteCompra(3, dato,1);
//        }
        if(tbBusqueda.getSelectedIndex()==1){
            return CCProforma.BusqNum(dato);
                }
        
        }
        if(tbBusqueda.getSelectedIndex()==2){
            
            if(id_Cliente!=-1)
            {
                return CCProforma.BuscarProformaPorCliente(id_Cliente);
            }
        }

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChFecha.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();
            ocConvertidorFecha.setFecha(DtChFechaFinal.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
           return CCProforma.BusqAvanzada(pFecha,id_Estado,oCETipoComprobante.getIdTipoComprobante(),pFechaFin);
            }
        }
        return null;
      

    }

  

    private void filtrarTabla()
    {
       oListaProformaMatriz=listarRegistros();
       limpiarTabla();
       if(oListaProformaMatriz!=null)
       {

                for(CEProformaMatriz pCEProformaMatriz:oListaProformaMatriz )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCEProformaMatriz.getFecha());
                        oArrayList.add(pCEProformaMatriz.getCodigo());
                        oArrayList.add(pCEProformaMatriz);
                        oArrayList.add(pCEProformaMatriz.getTipoComprobante());
                        oArrayList.add(pCEProformaMatriz.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCEProformaMatriz.getTotalPagar()));
                        oArrayList.add(pCEProformaMatriz.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    
                }
                LblColaProformas.setText("Cola de Proformas: "+TblDato.getRowCount()+" Proforma(s) Encontrados");
            
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
    private ComboBox.ComboBox CbxEstado;
    private ComboBox.ComboBox CbxTipoComp;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private com.toedter.calendar.JDateChooser DtChFechaFinal;
    private Label.Label LblCodigo;
    private Label.Label LblCodigo1;
    private javax.swing.JLabel LblColaProformas;
    private Label.Label LblEstado;
    private javax.swing.JTable TblDato;
    private TextField.JTxtNinguno TxtBusqCliente;
    private TextField.JTxtNinguno TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnBuscar btnBuscar1;
    private BotonesABM.BtnBuscar btnBuscar2;
    private BotonesABM.BtnExportar btnExportar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private Label.Label chkFecha;
    private Label.Label chkFecha1;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private Label.Label lblCliente;
    private Label.Label lblTipocomp;
    private javax.swing.JTabbedPane tbBusqueda;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_REFRESCAR = "ACTION_REFRESCAR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke nuevo = KeyStroke.getKeyStroke(KeyEvent.VK_R,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_REFRESCAR, nuevo, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            dispose();
        }
        else
        {
            if(e.getActionCommand().equals(ACTION_REFRESCAR))
            {
               filtrarTabla();
               TxtCodigo.requestFocus();
            }
        }
    }
}
