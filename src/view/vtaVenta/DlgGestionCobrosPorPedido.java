package view.vtaVenta;
import controller.vtaVenta.CCPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import view.FrmSistemaMenu;


public class DlgGestionCobrosPorPedido extends DialogoPadre implements ActionListener
{
    public List<CEPedidoMatriz> oListaPedidos;
    java.awt.Frame oparent;
    public DlgGestionCobrosPorPedido(java.awt.Frame parent, boolean modal)
    {
        super(parent, false);
        initComponents();
        this.oparent=parent;
        configurarTablaPedidosPedientes();
        oListaPedidos= listarPedidosPendientes();
        filtrarTabla();
        TxtParametro.requestFocus();
        configurarTablaPedidoDetalle();
        controlButton(1);
        LblFechaActual.setText(CLConsultarFechaSistema.consultarFecha());
        if(TblPedidosPendientes.getRowCount()>0)
        {
          TblPedidosPendientes.requestFocus();
          TblPedidosPendientes.changeSelection(0, 0, false, false);
        }
    }
    private void configurarTablaPedidosPedientes()
    {
        TableColumnModel oTableColumnModel=TblPedidosPendientes.getColumnModel();
        
        oTableColumnModel.getColumn(0).setPreferredWidth(150);
        oTableColumnModel.getColumn(1).setPreferredWidth(80);
        oTableColumnModel.getColumn(2).setPreferredWidth(80);
        oTableColumnModel.getColumn(3).setPreferredWidth(300);
        oTableColumnModel.getColumn(4).setPreferredWidth(65);
        oTableColumnModel.getColumn(5).setPreferredWidth(80);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer tccent = new DefaultTableCellRenderer();
        tccent.setHorizontalAlignment(SwingConstants.CENTER);

      //  TblPedidosPendientes.getColumnModel().getColumn(0).setCellRenderer(tccent);
        TblPedidosPendientes.getColumnModel().getColumn(1).setCellRenderer(tccent);
        TblPedidosPendientes.getColumnModel().getColumn(2).setCellRenderer(tccent);
        TblPedidosPendientes.getColumnModel().getColumn(4).setCellRenderer(tccent);

        TblPedidosPendientes.getColumnModel().getColumn(5).setCellRenderer(tcr);

        TblPedidosPendientes.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                    controlButton(2);
            }
        });
    }
    private void controlButton( int state)
    {
        if(state==1) //Sin Consulta
        {
            BtnAnular.setEnabled(false);
            BtnCancelar.setEnabled(false);
            TbpPago.setEnabledAt(1,false);
        }
        else
        {
            if(state==2) //Sin Consulta
            {
                BtnAnular.setEnabled(true);
                BtnCancelar.setEnabled(true);
                TbpPago.setEnabledAt(1,false);
            }
        }
    }
    private void configurarTablaPedidoDetalle()
    {
        TblPedidoDetalle.setDefaultRenderer(java.lang.Float.class,new DefaultTableCellRenderer()
        {
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            }

        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenuPedido = new javax.swing.JPopupMenu();
        jMenuItemCancelarPedido = new javax.swing.JMenuItem();
        jMenuItemAnularPedido = new javax.swing.JMenuItem();
        TbpPago = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        RbtCodigo = new javax.swing.JRadioButton();
        RbtCliente = new javax.swing.JRadioButton();
        TxtParametro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPedidosPendientes = new javax.swing.JTable()
        ;
        jPanel4 = new javax.swing.JPanel();
        RbtTodos = new javax.swing.JRadioButton();
        RbtTresDias = new javax.swing.JRadioButton();
        RbtUltimoDia = new javax.swing.JRadioButton();
        BtnRefrescar = new javax.swing.JButton();
        LblColaPedidos = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        BtnCancelar = new javax.swing.JButton();
        BtnAnular = new javax.swing.JButton();
        LblFechaActual = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PnlDatos2 = new javax.swing.JPanel();
        TxtCliente = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label3 = new Label.Label();
        TxtSucursal = new TextField.JTxtLetra();
        label2 = new Label.Label();
        TxtVendedor = new TextField.JTxtLetra();
        label10 = new Label.Label();
        label17 = new Label.Label();
        label18 = new Label.Label();
        TxtTipoCambio = new TextField.JTxtNinguno();
        label8 = new Label.Label();
        TxtDNI = new TextField.JTxtLetra();
        TxtDireccion = new TextField.JTxtLetra();
        label12 = new Label.Label();
        TxtMoneda = new TextField.JTxtLetra();
        TxtCondicion = new TextField.JTxtLetra();
        label5 = new Label.Label();
        label4 = new Label.Label();
        TxtCodigo = new TextField.JTxtNinguno();
        LblHora = new Label.Label();
        TxtTipoComprobante = new TextField.JTxtNinguno();
        jPanel5 = new javax.swing.JPanel();
        label6 = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        label9 = new Label.Label();
        label11 = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        LblISC = new Label.Label();
        label16 = new Label.Label();
        LblPorcentaje = new Label.Label();
        label7 = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        label21 = new Label.Label();
        LblDescuentoEnValores = new Label.Label();
        TxtDescuentoPorcentual = new TextField.JTxtNumero();
        label23 = new Label.Label();
        label24 = new Label.Label();
        label15 = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        label19 = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        label25 = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        TxtTipoDescuento = new TextField.JTxtNumero();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblPedidoDetalle = new javax.swing.JTable();

        jMenuItemCancelarPedido.setText("Cancelar Pedido");
        jPopupMenuPedido.add(jMenuItemCancelarPedido);

        jMenuItemAnularPedido.setText("Anular Pedido");
        jPopupMenuPedido.add(jMenuItemAnularPedido);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "[Búsqueda]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 0, 0))); // NOI18N

        RbtCodigo.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(RbtCodigo);
        RbtCodigo.setFont(new java.awt.Font("Arial", 0, 14));
        RbtCodigo.setSelected(true);
        RbtCodigo.setText("Código");

        RbtCliente.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup2.add(RbtCliente);
        RbtCliente.setFont(new java.awt.Font("Arial", 0, 14));
        RbtCliente.setText("Cliente");

        TxtParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtParametroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RbtCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RbtCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtParametro, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RbtCodigo)
                    .addComponent(RbtCliente)
                    .addComponent(TxtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        TblPedidosPendientes.setFont(new java.awt.Font("Arial", 0, 14));
        TblPedidosPendientes.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha",
                "Numero",
                "Tip.Comprob.",
                "Cliente",
                "Moneda",
                "Total"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Object.class,
                java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPedidosPendientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPedidosPendientes.setRowHeight(20);
        TblPedidosPendientes.getTableHeader().setReorderingAllowed(false);
        TblPedidosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPedidosPendientesMouseClicked(evt);
            }
        });
        TblPedidosPendientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                TblPedidosPendientesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(TblPedidosPendientes);
        TblPedidosPendientes.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "ProjSave");
        TblPedidosPendientes.getActionMap().put("ProjSave", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int fila = TblPedidosPendientes.getSelectedRow();
                    CEPedidoMatriz oCEPedidoMatriz=(CEPedidoMatriz)TblPedidosPendientes.getValueAt(fila,3);
                    if ((fila > -1))
                    {
                        cargarPedido(oCEPedidoMatriz);
                        TbpPago.setEnabledAt(1,true);
                    }
                }
            });

            TblPedidosPendientes.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ProjSave1");
            TblPedidosPendientes.getActionMap().put("ProjSave1", new AbstractAction()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        int fila = TblPedidosPendientes.getSelectedRow();
                        if ((fila > -1))
                        {
                            pagar();
                        }
                    }
                });

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "[Filtro:Pedidos Pendientes]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(153, 0, 0))); // NOI18N

                RbtTodos.setBackground(new java.awt.Color(255, 255, 255));
                buttonGroup1.add(RbtTodos);
                RbtTodos.setFont(new java.awt.Font("Arial", 0, 14));
                RbtTodos.setText("Todos ");
                RbtTodos.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        RbtTodosItemStateChanged(evt);
                    }
                });
                RbtTodos.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        RbtTodosKeyReleased(evt);
                    }
                });

                RbtTresDias.setBackground(new java.awt.Color(255, 255, 255));
                buttonGroup1.add(RbtTresDias);
                RbtTresDias.setFont(new java.awt.Font("Arial", 0, 14));
                RbtTresDias.setSelected(true);
                RbtTresDias.setText("Ultimo 3 Días");
                RbtTresDias.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        RbtTresDiasItemStateChanged(evt);
                    }
                });
                RbtTresDias.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        RbtTresDiasKeyReleased(evt);
                    }
                });

                RbtUltimoDia.setBackground(new java.awt.Color(255, 255, 255));
                buttonGroup1.add(RbtUltimoDia);
                RbtUltimoDia.setFont(new java.awt.Font("Arial", 0, 14));
                RbtUltimoDia.setText("Ultimo Día ");
                RbtUltimoDia.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        RbtUltimoDiaItemStateChanged(evt);
                    }
                });
                RbtUltimoDia.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        RbtUltimoDiaKeyReleased(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(RbtTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbtTresDias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RbtUltimoDia))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RbtTodos)
                            .addComponent(RbtTresDias)
                            .addComponent(RbtUltimoDia, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                        .addContainerGap())
                );

                BtnRefrescar.setFont(new java.awt.Font("Arial", 1, 14));
                BtnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refrescar.png"))); // NOI18N
                BtnRefrescar.setText("Refrescar");
                BtnRefrescar.setMargin(new java.awt.Insets(0, 0, 0, 0));
                BtnRefrescar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BtnRefrescarActionPerformed(evt);
                    }
                });

                LblColaPedidos.setFont(new java.awt.Font("Arial", 0, 14));
                LblColaPedidos.setForeground(new java.awt.Color(153, 0, 0));
                LblColaPedidos.setText("Cola de Pedidos: 0 Pedido(s)");

                BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cash.png"))); // NOI18N
                BtnCancelar.setText("COBRAR");
                BtnCancelar.setToolTipText("Cancelar Pedido");
                BtnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                BtnCancelar.setMargin(new java.awt.Insets(2, 0, 0, 0));
                BtnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BtnCancelarActionPerformed(evt);
                    }
                });

                BtnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.png"))); // NOI18N
                BtnAnular.setText("ANULAR");
                BtnAnular.setToolTipText("Anular Pedido");
                BtnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                BtnAnular.setMargin(new java.awt.Insets(2, 0, 0, 0));
                BtnAnular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                BtnAnular.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BtnAnularActionPerformed(evt);
                    }
                });

                LblFechaActual.setFont(new java.awt.Font("Arial", 1, 14));
                LblFechaActual.setText("01/02/2011");

                LblFecha.setFont(new java.awt.Font("Arial", 0, 14));
                LblFecha.setForeground(new java.awt.Color(153, 0, 0));
                LblFecha.setText("Fecha:");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LblColaPedidos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                                        .addComponent(LblFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LblFechaActual))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, 0, 69, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblColaPedidos)
                            .addComponent(LblFechaActual)
                            .addComponent(LblFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );

                TbpPago.addTab("Pedidos Pendientes", jPanel1);

                jPanel2.setAutoscrolls(true);

                PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

                TxtCliente.setEditable(false);
                TxtCliente.setTamanio(200);

                label1.setText("Cliente :");
                label1.setFont(new java.awt.Font("Verdana", 1, 12));

                label3.setText("Sucursal :");
                label3.setFont(new java.awt.Font("Verdana", 1, 12));

                TxtSucursal.setEditable(false);
                TxtSucursal.setText("CENTRAL");
                TxtSucursal.setTamanio(50);

                label2.setText("Vendedor :");
                label2.setFont(new java.awt.Font("Verdana", 1, 12));

                TxtVendedor.setEditable(false);
                TxtVendedor.setTamanio(50);

                label10.setText("Condicion :");
                label10.setFont(new java.awt.Font("Verdana", 1, 12));

                label17.setText("Moneda :");
                label17.setFont(new java.awt.Font("Verdana", 1, 12));

                label18.setText("Tipo Cambio :");
                label18.setFont(new java.awt.Font("Verdana", 1, 12));

                TxtTipoCambio.setEditable(false);
                TxtTipoCambio.setTamanio(10);

                label8.setText("DNI :");
                label8.setFont(new java.awt.Font("Verdana", 1, 12));

                TxtDNI.setEditable(false);
                TxtDNI.setTamanio(200);

                TxtDireccion.setEditable(false);
                TxtDireccion.setTamanio(200);

                label12.setText("Direccion :");
                label12.setFont(new java.awt.Font("Verdana", 1, 12));

                TxtMoneda.setEditable(false);
                TxtMoneda.setTamanio(50);

                TxtCondicion.setEditable(false);
                TxtCondicion.setTamanio(50);

                javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
                PnlDatos2.setLayout(PnlDatos2Layout);
                PnlDatos2Layout.setHorizontalGroup(
                    PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TxtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                            .addComponent(TxtCondicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TxtMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TxtSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                                    .addComponent(TxtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(TxtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(118, Short.MAX_VALUE))
                );
                PnlDatos2Layout.setVerticalGroup(
                    PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                label5.setText("Tipo Comprobante :");
                label5.setFont(new java.awt.Font("Arial", 1, 12));

                label4.setText("Código :");
                label4.setFont(new java.awt.Font("Arial", 1, 12));

                TxtCodigo.setEditable(false);
                TxtCodigo.setText("jTxtNinguno1");
                TxtCodigo.setTamanio(250);

                LblHora.setText("hora");

                TxtTipoComprobante.setEditable(false);
                TxtTipoComprobante.setText("jTxtNinguno1");
                TxtTipoComprobante.setTamanio(250);

                jPanel5.setBackground(java.awt.SystemColor.controlHighlight);
                jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

                label6.setText("TOTAL A PAGAR :");
                label6.setFont(new java.awt.Font("Verdana", 1, 18));

                LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblMontoTotal.setText("0");
                LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 18));
                LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblIgv.setText("0");
                LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
                LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                label9.setText("I.G.V                                 :");
                label9.setFont(new java.awt.Font("Verdana", 1, 12));

                label11.setText("SubTotal Neto                   :");
                label11.setFont(new java.awt.Font("Verdana", 1, 12));

                LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblSubtotalNeto.setText("0");
                LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
                LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblISC.setText("0");
                LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
                LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                label16.setText("I.S.C                                 :");
                label16.setFont(new java.awt.Font("Verdana", 1, 12));

                LblPorcentaje.setVisible(false);
                LblPorcentaje.setText("%");
                LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 12));

                label7.setText("SubTotal Neto sin Igv      :");
                label7.setFont(new java.awt.Font("Verdana", 1, 12));

                LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblSubTotalNetoSinIgv.setText("0");
                LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
                LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                label21.setText("Tipo Descuento :");
                label21.setFont(new java.awt.Font("Verdana", 1, 12));

                LblDescuentoEnValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblDescuentoEnValores.setText("0");
                LblDescuentoEnValores.setFont(new java.awt.Font("Verdana", 1, 12));
                LblDescuentoEnValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                TxtDescuentoPorcentual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                TxtDescuentoPorcentual.setTamanio(15);

                label23.setText("Desc. en Val.:");
                label23.setFont(new java.awt.Font("Verdana", 1, 12));

                label24.setText("Descuento :");
                label24.setFont(new java.awt.Font("Verdana", 1, 12));

                label15.setText("<html>SubTotal Bruto:</html>");
                label15.setFont(new java.awt.Font("Verdana", 1, 12));

                LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblSubtotalBruto.setText("0");
                LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
                LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                label19.setText("<html>Desc. en SubT.:</html>");
                label19.setFont(new java.awt.Font("Verdana", 1, 12));

                LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblDescuentoSubtotal.setText("0");
                LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
                LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                label25.setText("Descuento Total               :");
                label25.setFont(new java.awt.Font("Verdana", 1, 12));

                LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                LblDescuentoTotal.setText("0");
                LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
                LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                TxtTipoDescuento.setEditable(false);
                TxtTipoDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                TxtTipoDescuento.setTamanio(15);

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblISC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(LblIgv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LblMontoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(label21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtDescuentoPorcentual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtTipoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LblPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LblDescuentoEnValores, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(87, 87, 87)
                                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LblSubTotalNetoSinIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(LblIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(LblISC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(LblDescuentoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21)
                                    .addComponent(LblMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel7.setBackground(java.awt.SystemColor.controlHighlight);
                jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PEDIDO DE PRODUCTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
                jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

                TblPedidoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
                TblPedidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "N°", "Producto", "Almacen", "Precio", "U. M. ", "Cantidad", "Imp. Bruto", "Tipo Desc.", "Descuento", "Desc. en Valo.", "Imp. Neto", "Exonerado"
                    }
                ) {
                    Class[] types = new Class [] {
                        java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false, false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                TblPedidoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                TblPedidoDetalle.setColumnSelectionAllowed(true);
                TblPedidoDetalle.setRowHeight(22);
                jScrollPane3.setViewportView(TblPedidoDetalle);
                TblPedidoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
                TblPedidoDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
                TblPedidoDetalle.getColumnModel().getColumn(1).setPreferredWidth(320);
                TblPedidoDetalle.getColumnModel().getColumn(2).setPreferredWidth(100);
                TblPedidoDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
                TblPedidoDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
                TblPedidoDetalle.getColumnModel().getColumn(5).setPreferredWidth(75);
                TblPedidoDetalle.getColumnModel().getColumn(6).setPreferredWidth(90);
                TblPedidoDetalle.getColumnModel().getColumn(7).setPreferredWidth(80);
                TblPedidoDetalle.getColumnModel().getColumn(8).setPreferredWidth(75);
                TblPedidoDetalle.getColumnModel().getColumn(10).setPreferredWidth(90);

                jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 810, 160));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(LblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(20, Short.MAX_VALUE)))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LblHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(212, 212, 212)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(199, Short.MAX_VALUE)))
                );

                TbpPago.addTab("Resumen de Pago", jPanel2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TbpPago, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(11, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TbpPago, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents
    private void cargarPedido(CEPedidoMatriz oCEPedidoMatriz)
    {
        TbpPago.setSelectedIndex(1);
        TxtTipoComprobante.setText(oCEPedidoMatriz.getTipoComprobante());
        TxtCodigo.setText(oCEPedidoMatriz.getCodigo());
        TxtCliente.setText(oCEPedidoMatriz.getCliente());
        TxtDNI.setText(oCEPedidoMatriz.getDNI());
        TxtDireccion.setText(oCEPedidoMatriz.getDireccion());
        TxtCondicion.setText(oCEPedidoMatriz.getCondicion());
        TxtMoneda.setText(oCEPedidoMatriz.getMoneda());
        TxtVendedor.setText(oCEPedidoMatriz.getEmpleado());
        TxtSucursal.setText(oCEPedidoMatriz.getSucursal());
        TxtTipoCambio.setText(oCEPedidoMatriz.getTipoCambio()+"");
        TxtTipoDescuento.setText(oCEPedidoMatriz.getTipoDescuento());
        TxtDescuentoPorcentual.setText(oCEPedidoMatriz.getDescuento()+"");
        LblSubtotalNeto.setText(oCEPedidoMatriz.getSubTotalNeto()+"");
        LblSubTotalNetoSinIgv.setText(oCEPedidoMatriz.getSubtotalNetoSinIGV()+"");
        LblDescuentoSubtotal.setText(oCEPedidoMatriz.getDescuentoEnSubtotal()+"");
        LblSubtotalBruto.setText(oCEPedidoMatriz.getSubtotalBruto()+"");
        LblDescuentoEnValores.setText(oCEPedidoMatriz.getDescuentoValores()+"");
        LblIgv.setText(oCEPedidoMatriz.getIGV()+"");
        LblISC.setText(oCEPedidoMatriz.getISC()+"");
        LblMontoTotal.setText(oCEPedidoMatriz.getMontoTotal()+"");
        oCEPedidoMatriz.setLstPedidoDetalle(CCPedido.ConsultarPedidoDetalle(oCEPedidoMatriz.getIdPedido()));
        limpiarTablarPedidoDetalle();
        for(int i=0; i<oCEPedidoMatriz.getLstPedidoDetalle().size();i++)
        {
                Vector oArrayList = new Vector();
               ((DefaultTableModel)TblPedidoDetalle.getModel()).addRow(oArrayList);
                CEPedidoDetalle oCEPedidoDetalle=oCEPedidoMatriz.getLstPedidoDetalle().get(i) ;
                TblPedidoDetalle.setValueAt(i+1,i,0);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),i,1);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getAlmacen(),i,2);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecio(),i,3);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getUnidadMedida(),i,4);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getCantidad(),i,5);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImportelSinDescuento(),i,6);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getTipoDescuento(),i,7);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuento(),i,8);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuentoEnValores(),i,9);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImporteConDescuento(),i,10);
          }
    }
    private void limpiarTablarPedidoDetalle()
    {
        int rowCount=TblPedidoDetalle.getRowCount();
        for(int i=0;i<rowCount;i++)
        {
            ((DefaultTableModel)TblPedidoDetalle.getModel()).removeRow(0);
        }
        BtnAnular.setEnabled(false);
    }
    private void BtnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnularActionPerformed
        int fila = TblPedidosPendientes.getSelectedRow();
        CEPedidoMatriz oCEPedidoMatriz=(CEPedidoMatriz)TblPedidosPendientes.getValueAt(fila,3);
        DlgAnularPedido oDlgAnularPedido=new  DlgAnularPedido(null,true, oCEPedidoMatriz);
        oDlgAnularPedido.setLocationRelativeTo(null);
        oDlgAnularPedido.setVisible(true);
        refreshColaPagos();
}//GEN-LAST:event_BtnAnularActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        pagar();
        

}//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefrescarActionPerformed

        BtnRefrescar.setEnabled(false);
        oListaPedidos=listarPedidosPendientes();
        filtrarTabla();
        if(TblPedidosPendientes.getRowCount()>0)
        {
          TblPedidosPendientes.requestFocus();
          TblPedidosPendientes.changeSelection(0, 0, false, false);
        }
        BtnRefrescar.setEnabled(true);
}//GEN-LAST:event_BtnRefrescarActionPerformed

    private void RbtUltimoDiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RbtUltimoDiaKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER) {
            oListaPedidos=listarPedidosPendientes();
            TxtParametro.requestFocus();
        } else {
            if (evt.getKeyCode() == evt.VK_LEFT) {
                RbtTresDias.requestFocus();
                RbtTresDias.setSelected(true);
            } else {
                if (evt.getKeyCode() == evt.VK_RIGHT) {
                    RbtTodos.requestFocus();
                    RbtTodos.setSelected(true);
                }
            }
        }
}//GEN-LAST:event_RbtUltimoDiaKeyReleased

    private void RbtUltimoDiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RbtUltimoDiaItemStateChanged
        if(evt.getStateChange()==evt.SELECTED) {
            oListaPedidos=listarPedidosPendientes();
            filtrarTabla();
            TxtParametro.requestFocus();
        }
}//GEN-LAST:event_RbtUltimoDiaItemStateChanged

    private void RbtTresDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RbtTresDiasKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER) {
            oListaPedidos=listarPedidosPendientes();
            TxtParametro.requestFocus();
        } else {
            if (evt.getKeyCode() == evt.VK_LEFT) {
                RbtTodos.requestFocus();
                RbtTodos.setSelected(true);
            } else {
                if (evt.getKeyCode() == evt.VK_RIGHT) {
                    RbtUltimoDia.requestFocus();
                    RbtUltimoDia.setSelected(true);
                }
            }
        }
}//GEN-LAST:event_RbtTresDiasKeyReleased

    private void RbtTresDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RbtTresDiasItemStateChanged
        if(evt.getStateChange()==evt.SELECTED) {
            oListaPedidos=listarPedidosPendientes();
            filtrarTabla();
            TxtParametro.requestFocus();
        }
}//GEN-LAST:event_RbtTresDiasItemStateChanged

    private void RbtTodosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RbtTodosKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER) {
            oListaPedidos=listarPedidosPendientes();
            TxtParametro.requestFocus();
        } else {
            if (evt.getKeyCode() == evt.VK_LEFT) {
                RbtUltimoDia.requestFocus();
                RbtUltimoDia.setSelected(true);
            } else {
                if (evt.getKeyCode() == evt.VK_RIGHT) {
                    RbtTresDias.requestFocus();
                    RbtTresDias.setSelected(true);
                }
            }
        }
}//GEN-LAST:event_RbtTodosKeyReleased

    private void RbtTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RbtTodosItemStateChanged
        if(evt.getStateChange()==evt.SELECTED) {
            oListaPedidos=listarPedidosPendientes();
            filtrarTabla();
            TxtParametro.requestFocus();
        }
}//GEN-LAST:event_RbtTodosItemStateChanged

    private void TblPedidosPendientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TblPedidosPendientesFocusLost

}//GEN-LAST:event_TblPedidosPendientesFocusLost

    private void TblPedidosPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPedidosPendientesMouseClicked
        if(evt.getButton()==evt.BUTTON1) {
            if(evt.getClickCount()==2) {
                pagar();
            }
        } else {
            jPopupMenuPedido.show(TblPedidosPendientes, evt.getX(),evt.getY());
        }
}//GEN-LAST:event_TblPedidosPendientesMouseClicked

    private void TxtParametroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtParametroKeyReleased
        if(evt.getKeyCode()==evt.VK_DOWN) {
        if(TblPedidosPendientes.getRowCount()>0)
        {
          TblPedidosPendientes.requestFocus();
          TblPedidosPendientes.changeSelection(0, 0, false, false);
        }
        } else {
            filtrarTabla();
        }
}//GEN-LAST:event_TxtParametroKeyReleased
    private void refreshColaPagos()
    {
    oListaPedidos=listarPedidosPendientes();
    filtrarTabla();
    if(TblPedidosPendientes.getRowCount()>0)
        {
          TblPedidosPendientes.requestFocus();
          TblPedidosPendientes.changeSelection(0, 0, false, false);
        }
    }
    private void pagar()
    {
        if(TblPedidosPendientes.getSelectedRow()!=-1){
//        if(FrmSistemaMenu.isSiNoAtencion())
//        {
            int fila = TblPedidosPendientes.getSelectedRow();
            if(fila!=-1){
            CEPedidoMatriz oCEPedidoMatriz=(CEPedidoMatriz)TblPedidosPendientes.getValueAt(fila,3);
            DlgCobroPedido oDlgPagoPedido=new DlgCobroPedido(oparent,true,oCEPedidoMatriz);
            oDlgPagoPedido.setLocationRelativeTo(null);
            oDlgPagoPedido.setVisible(true);
            refreshColaPagos();
            }
                else
                {
                 JOptionPane.showMessageDialog(null,"<html><b>Seleccione una fila </b></html>");
                }
//            }else
//                {
//                JOptionPane.showMessageDialog(null,"El sistema se encuentra cerrado, no se puede realizar cobros","Mensaje", JOptionPane.ERROR_MESSAGE);
//                }
        }
        
        else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }
    private List<CEPedidoMatriz> listarPedidosPendientes()
    {
        int pIdSucursal=FrmSistemaMenu.getIdSucursal();
       if(opcionFiltro()==3)
       {
           return CCPedido.ListaPedidosPendientesTodos(pIdSucursal);
       }
       else
       {
            if(opcionFiltro()==2)
            {
               return CCPedido.ListaPedidosPendientesUltimoTresDias(pIdSucursal);
            }
            else
            {
                return CCPedido.ListaPedidosPendientesUltimoDia(pIdSucursal);
            }
       }
    }

    private void filtrarTabla()
    {
       limpiarTabla();
       if(oListaPedidos!=null)
       {
            if(opcionBusqueda()==1)
            {
                for(CEPedidoMatriz oCEPedidoMatriz:oListaPedidos )
                {
                    if(oCEPedidoMatriz.getCodigo().contains(TxtParametro.getText().toUpperCase()))
                    {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(oCEPedidoMatriz.getFecha());
                        oArrayList.add(oCEPedidoMatriz.getCodigo());
                        oArrayList.add(oCEPedidoMatriz.getTipoComprobante());
                        oArrayList.add(oCEPedidoMatriz);
                        oArrayList.add(oCEPedidoMatriz.getMoneda());
                        oArrayList.add(CLRedondear.FormatearDosDigitos(oCEPedidoMatriz.getTotalPagar()));
                        ((ArrayListTableModel)TblPedidosPendientes.getModel()).addRow(oArrayList);
                    }
                }
                LblColaPedidos.setText("Cola de Pedidos: "+TblPedidosPendientes.getRowCount()+" Pedido(s) Encontrados");
            }
            else
            {
                for(CEPedidoMatriz oCEPedidoMatriz:oListaPedidos )
                {
                    if(oCEPedidoMatriz.getCliente().contains(TxtParametro.getText().toUpperCase()))
                    {
                        ArrayList oArrayList=new ArrayList();
                        oArrayList.add(oCEPedidoMatriz.getFecha());
                        oArrayList.add(oCEPedidoMatriz.getCodigo());
                        oArrayList.add(oCEPedidoMatriz.getTipoComprobante());
                        oArrayList.add(oCEPedidoMatriz);
                        oArrayList.add(oCEPedidoMatriz.getMoneda());
                        oArrayList.add(oCEPedidoMatriz.getMontoTotal());
                       ((ArrayListTableModel)TblPedidosPendientes.getModel()).addRow(oArrayList);
                    }
                }
                LblColaPedidos.setText("Cola de Pedidos: "+TblPedidosPendientes.getRowCount()+" Pedido(s) Encontrados");
            }
        }
        else
       {
           JOptionPane.showMessageDialog(null,"<html><b>No ha refrescado la cola de pedidos</b></html>");
        }
    }
    private void limpiarTabla()
    {
        int size=TblPedidosPendientes.getRowCount();
        for(int i=0;i<size;i++ )
        {
        ((ArrayListTableModel)TblPedidosPendientes.getModel()).removeRow(0);
        }
    }
    private int opcionBusqueda()
    {
        if(RbtCodigo.isSelected())
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    private int opcionFiltro()
    {
        if(RbtTodos.isSelected())
        {
            return 3;
        }
        else
        {
            if(RbtTresDias.isSelected())
            {
                return 2;
            }
             else
             {
                return 1;
             }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnular;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnRefrescar;
    private javax.swing.JLabel LblColaPedidos;
    private Label.Label LblDescuentoEnValores;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblFechaActual;
    private Label.Label LblHora;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JRadioButton RbtCliente;
    private javax.swing.JRadioButton RbtCodigo;
    private javax.swing.JRadioButton RbtTodos;
    private javax.swing.JRadioButton RbtTresDias;
    private javax.swing.JRadioButton RbtUltimoDia;
    private javax.swing.JTable TblPedidoDetalle;
    private javax.swing.JTable TblPedidosPendientes;
    private javax.swing.JTabbedPane TbpPago;
    private TextField.JTxtLetra TxtCliente;
    private TextField.JTxtNinguno TxtCodigo;
    private TextField.JTxtLetra TxtCondicion;
    private TextField.JTxtLetra TxtDNI;
    private TextField.JTxtNumero TxtDescuentoPorcentual;
    private TextField.JTxtLetra TxtDireccion;
    private TextField.JTxtLetra TxtMoneda;
    private javax.swing.JTextField TxtParametro;
    private TextField.JTxtLetra TxtSucursal;
    private TextField.JTxtNinguno TxtTipoCambio;
    private TextField.JTxtNinguno TxtTipoComprobante;
    private TextField.JTxtNumero TxtTipoDescuento;
    private TextField.JTxtLetra TxtVendedor;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JMenuItem jMenuItemAnularPedido;
    private javax.swing.JMenuItem jMenuItemCancelarPedido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenuPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private Label.Label label1;
    private Label.Label label10;
    private Label.Label label11;
    private Label.Label label12;
    private Label.Label label15;
    private Label.Label label16;
    private Label.Label label17;
    private Label.Label label18;
    private Label.Label label19;
    private Label.Label label2;
    private Label.Label label21;
    private Label.Label label23;
    private Label.Label label24;
    private Label.Label label25;
    private Label.Label label3;
    private Label.Label label4;
    private Label.Label label5;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    private Label.Label label9;
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
               oListaPedidos=listarPedidosPendientes();
               TxtParametro.requestFocus();
            }
        }
    }
}
