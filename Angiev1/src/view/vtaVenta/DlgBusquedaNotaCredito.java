package view.vtaVenta;

import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCComprobanteDevolucion;
import controller.vtaVenta.CCNotaCredito;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CENotaCredito;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;
import view.cmrComercial.DlgGestionCliente;


public class DlgBusquedaNotaCredito extends DialogoPadre implements ActionListener
{

    private List<CENotaCredito> oListaNotaCredito;
    private java.awt.Frame oparent;
    private boolean cargar;
    private boolean  es_devol;
    public DlgBusquedaNotaCredito(java.awt.Frame parent, boolean modal,boolean es_devol)
    {
        super(parent, modal);
        initComponents();
        cargar=false;
        this.es_devol=es_devol;
        this.setLocationRelativeTo(null);
        configurarTablaPedidosPedientes();
        DtChFecha.setDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setDate(FrmSistemaMenu.FechaSistema);
        DtChFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        DtChFechaFin.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
        CbxEstado.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstado(0)));
        this.oparent=parent;
        cargar=true;
        this.setTitle("Busqueda Nota Crédito");
        if(es_devol)
        {
            this.setTitle("Busqueda Comp. de Devolución");
        }
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        filtrarTabla();
    }
    private void configurarTablaPedidosPedientes()
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

     //   TblPedidosPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(1).setCellRenderer(tccent);
        //TblPedidosPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(6).setCellRenderer(tccent);
        TblDato.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblDato.getColumnModel().getColumn(5).setCellRenderer(tcr);
  
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
        DtChFechaFin =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        chkFecha1 = new Label.Label();
        jPanel5 = new javax.swing.JPanel();
        LblCodigo = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        btnBuscar2 = new BotonesABM.BtnBuscar();
        jPanel4 = new javax.swing.JPanel();
        LblCodigo1 = new Label.Label();
        TxtBusqCliente = new TextField.JTxtNinguno();
        btnBuscar1 = new BotonesABM.BtnBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDato = new javax.swing.JTable()
        ;
        LblColaPedidos = new javax.swing.JLabel();
        btnExportar1 = new BotonesABM.BtnExportar();
        BtnRefrescar = new javax.swing.JButton();
        btnAceptar1 = new BotonesABM.BtnAceptar();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda Nota Crédito");
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

        chkFecha.setText("Del :");
        jPanel3.add(chkFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 10, 100, 20));

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
        jPanel3.add(TxtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 310, -1));

        lblCliente.setText("Cliente :");
        jPanel3.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        CbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxEstadoActionPerformed(evt);
            }
        });
        jPanel3.add(CbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 161, -1));

        LblEstado.setText("Estado :");
        jPanel3.add(LblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        DtChFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaFinPropertyChange(evt);
            }
        });
        jPanel3.add(DtChFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 40, 100, 20));

        chkFecha1.setText("Al   :");
        jPanel3.add(chkFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        tbBusqueda.addTab("Busqueda Avanzada", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        LblCodigo.setText("Numero :");

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
                .addContainerGap(92, Short.MAX_VALUE))
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
                .addContainerGap(88, Short.MAX_VALUE))
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

        TblDato.setFont(new java.awt.Font("Arial", 0, 14));
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

        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar1.setText("");
        btnExportar1.setToolTipText("Exportar");
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });

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

        btnAceptar1.setText("");
        btnAceptar1.setToolTipText("Seleccionar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(LblColaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblColaPedidos)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        BtnRefrescar.getAccessibleContext().setAccessibleName("0");
        BtnRefrescar.getAccessibleContext().setAccessibleDescription("");
        btnAceptar1.getAccessibleContext().setAccessibleName("0");
        btnAceptar1.getAccessibleContext().setAccessibleDescription("");

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
                DtChFechaFin.setMinSelectableDate(DtChFecha.getDate());
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
         for(CENotaCredito pCENotaCredito:oListaNotaCredito )
                {
                    if(pCENotaCredito.toString().contains(TxtCliente.getText().toUpperCase()))
                    {
                         ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCENotaCredito.getFecha());
                        oArrayList.add(pCENotaCredito.getNumeroNotaCredito());
                        oArrayList.add(pCENotaCredito);
                        oArrayList.add(pCENotaCredito.getTipoComprobante());
                        oArrayList.add(pCENotaCredito.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCENotaCredito.getMontoTotalAcreditar()));
                        oArrayList.add(pCENotaCredito.getUltimoEstado());
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

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        SelecionarRegistro();
}//GEN-LAST:event_btnAceptar1ActionPerformed

    private void DtChFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaFinPropertyChange

          if(cargar)
        if(!iscerrando){
            if(DtChFecha.getDate()!=null){
            filtrarTabla();

            }
        }
          
    }//GEN-LAST:event_DtChFechaFinPropertyChange

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

    CENotaCredito oCENotaCredito=null;

public CENotaCredito getCENotaCredito()
{
    return oCENotaCredito;
}
private void SelecionarRegistro()
    {
        int filSel=TblDato.getSelectedRow();
        if(filSel!=-1){
        oCENotaCredito=(CENotaCredito)TblDato.getValueAt(filSel, 2);
        dispose();

        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CENotaCredito> listaNotaCredito()
    {
        String dato=TxtCodigo.getText();
        int id_Estado=2;
        CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
//        if(RbtCliente.isSelected())
//        {
//            return CCComprobanteCompra.BuscarComprobanteCompra(3, dato,1);
//        }
        if(tbBusqueda.getSelectedIndex()==1){
            return CCNotaCredito.BusqNum(dato);
                }
        
        }
        if(tbBusqueda.getSelectedIndex()==2){
            
            if(id_Cliente!=-1)
            {
                return CCNotaCredito.BuscarNotaCreditoPorCliente(id_Cliente);
            }
        }

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChFecha.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();
            ocConvertidorFecha.setFecha(DtChFechaFin.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
           return CCNotaCredito.BusqAvanzada(pFecha,id_Estado,pFechaFin);
            }
        }
        return null;
      

    }

    private List<CENotaCredito> listaCompDevol()
    {
        String dato=TxtCodigo.getText();
        int id_Estado=2;
        CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
        if(oCEEstado!=null){
            id_Estado=oCEEstado.getmIntIdEstado();
        }
        if(!dato.equals("")){
//        if(RbtCliente.isSelected())
//        {
//            return CCComprobanteCompra.BuscarComprobanteCompra(3, dato,1);
//        }
        if(tbBusqueda.getSelectedIndex()==1){
            return CCComprobanteDevolucion.BusqNum(dato);
                }

        }
        if(tbBusqueda.getSelectedIndex()==2){

            if(id_Cliente!=-1)
            {
                return CCComprobanteDevolucion.BuscarNotaCreditoPorCliente(id_Cliente);
            }
        }

        if(tbBusqueda.getSelectedIndex()==0)
        {
            if(DtChFecha.getCalendar()!=null){
             ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();
            ocConvertidorFecha.setFecha(DtChFechaFin.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFechaFin=ocConvertidorFecha.getFechaSimple2();
           return CCComprobanteDevolucion.BusqAvanzada(pFecha,id_Estado,pFechaFin);
            }
        }
        return null;


    }

    private void filtrarTabla()
    {
        if(es_devol){
        oListaNotaCredito=listaCompDevol();
        }else
        {
            oListaNotaCredito=listaNotaCredito();
        }
       limpiarTabla();
       if(oListaNotaCredito!=null)
       {

                for(CENotaCredito pCENotaCredito:oListaNotaCredito )
                {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(pCENotaCredito.getFecha());
                        oArrayList.add(pCENotaCredito.getNumeroNotaCredito());
                        oArrayList.add(pCENotaCredito);
                        oArrayList.add(pCENotaCredito.getTipoComprobante());
                        oArrayList.add(pCENotaCredito.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(pCENotaCredito.getMontoTotalAcreditar()));
                        oArrayList.add(pCENotaCredito.getUltimoEstado());
                        ((ArrayListTableModel)TblDato.getModel()).addRow(oArrayList);
                    
                }
                LblColaPedidos.setText("Cola de Pedidos: "+TblDato.getRowCount()+" Pedido(s) Encontrados");
            
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
    private com.toedter.calendar.JDateChooser DtChFecha;
    private com.toedter.calendar.JDateChooser DtChFechaFin;
    private Label.Label LblCodigo;
    private Label.Label LblCodigo1;
    private javax.swing.JLabel LblColaPedidos;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private Label.Label lblCliente;
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
