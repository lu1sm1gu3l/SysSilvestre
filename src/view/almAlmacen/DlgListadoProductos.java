package view.almAlmacen;

import controller.almAlmacen.CCProducto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import modelo.almAlmacen.entidad.CEProducto;
import net.sf.jasperreports.view.JasperViewer;
import table.ArrayListTableModel;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;

public class DlgListadoProductos extends DialogoPadre
{

    private boolean filtrarTbPn=false;
    public DlgListadoProductos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CLBotonesABM oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(null, null, null, null, null, null,this);
        jTabbedPane1.setSelectedIndex(1);
        configurarTabla(TblListadoProducto,"Unidad Base");
        configurarTabla(TblListadoProducto1,"Unidad Medida");
        TblListadoProducto.getColumnModel().getColumn(2).setMaxWidth(0);
        TblListadoProducto.getColumnModel().getColumn(2).setMinWidth(0);
        TblListadoProducto.getColumnModel().getColumn(2).setPreferredWidth(0);
        TblListadoProducto1.getColumnModel().getColumn(2).setMaxWidth(0);
        TblListadoProducto1.getColumnModel().getColumn(2).setMinWidth(0);
        TblListadoProducto1.getColumnModel().getColumn(2).setPreferredWidth(0);
        this.RbtCodigo.setVisible(false);
        filtrarProducto();
        Container con = this.getContentPane();
        con.setBackground(new Color(204, 204, 204));
        TxtParametro.requestFocus();
        filtrarTbPn=true;
    }

    private void configurarTabla(JTable tbl,String UnidadBS_o_UniMed)
    {
        tbl.setModel(new ArrayListTableModel(
                new Object[][]
                {
                },
                new String[]
                {
                    "Nº", "Cod.Barra","Cod.Producto", "Producto", "Marca",UnidadBS_o_UniMed, "Und. Prest.", "Precio Ref", "Precios", "¿Impuesto?", "Subfamilia", "Familia", "Categoria", "Rubro"
                })
        {

            Class[] types = new Class[]
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false, false, false, false, false, false, false, false,false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        });
        tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        
        if (tbl == TblListadoProducto)
        {
            jScrollPane1.setViewportView(tbl);
        }
        if (tbl == TblListadoProducto1)
        {
            jScrollPane2.setViewportView(tbl);
        }
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(350);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(6).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(7).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(8).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(9).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(10).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(11).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(12).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(13).setPreferredWidth(130);
        tbl.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer()
        {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {

                if (value instanceof JLabel)
                {
                    fillColor(table, (JLabel) value, isSelected);
                    return (JLabel) value;
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

            public void fillColor(JTable t, JLabel l, boolean isSelected)
            {
                if (isSelected)
                {
                    l.setBackground(t.getSelectionBackground());
                    l.setForeground(t.getSelectionForeground());
                }
                else
                {
                    l.setBackground(t.getBackground());
                    l.setForeground(t.getForeground());
                }
            }
        });
    }

    private void limpiarTabla(JTable tbl)
    {
        int num = tbl.getRowCount();
        for (int i = 0; i < num; i++)
        {
            ((ArrayListTableModel) tbl.getModel()).removeRow(0);
        }
    }

    

    private void listarProductos(List<CEProducto> oLstProducto, JTable tbl)
    {
        limpiarTabla(tbl);
        int n = 0;
        for (CEProducto oCEProducto : oLstProducto)
        {
            ArrayList oArrayList = new ArrayList();
            oArrayList.add(n + 1);
            oArrayList.add(oCEProducto.getCogidoBarra());
            oArrayList.add(oCEProducto.getCodigo());            
            oArrayList.add(oCEProducto);
            oArrayList.add(oCEProducto.getMarca());
            oArrayList.add(oCEProducto.getUMB());
            oArrayList.add(oCEProducto.getUMP());
            oArrayList.add(oCEProducto.getPrecio());
            oArrayList.add(LblPrecios);
            oArrayList.add(oCEProducto.isSiNoImpuesto());
            oArrayList.add(oCEProducto.getSubfamilia());
            oArrayList.add(oCEProducto.getFamilia());
            oArrayList.add(oCEProducto.getCategoria());
            oArrayList.add(oCEProducto.getRubro());
            ((ArrayListTableModel) tbl.getModel()).addRow(oArrayList);
            n++;
        }
        if(tbl.getRowCount()>0){
        tbl.requestFocus();
        tbl.changeSelection(rowsel, 0, false, false);
        }
        LblResultado.setText("(" + n + ")Resultados");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblPrecios = new javax.swing.JLabel();
        LblUnidad = new javax.swing.JLabel();
        jPopupMenuProducto = new javax.swing.JPopupMenu();
        jMenuItemModificarPrecios = new javax.swing.JMenuItem();
        jMenuItemEditarProducto = new javax.swing.JMenuItem();
        jMenuItemEliminarProducto = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        PnlBotones = new javax.swing.JPanel();
        BtnNuevo = new BotonesABM.BtnNuevo();
        BtnEditar = new BotonesABM.BtnEditar();
        btnEliminar1 = new BotonesABM.BtnEliminar();
        BtnPrecios = new BotonesABM.BtnEditar();
        BtnCatalogo = new BotonesABM.BtnEditar();
        BtnReportar = new BotonesABM.BtnEditar();
        btnExportar1 = new BotonesABM.BtnExportar();
        BtnSalir = new BotonesABM.BtnEditar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TxtParametro = new javax.swing.JTextField();
        RbtProducto = new javax.swing.JRadioButton();
        RbtCodigo = new javax.swing.JRadioButton();
        RbtMarca = new javax.swing.JRadioButton();
        BtnBuscar = new javax.swing.JButton();
        RbtCodigoBarra = new javax.swing.JRadioButton();
        LblResultado = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListadoProducto = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int colIndex)
            {
                Component component = super.prepareRenderer(renderer, rowIndex, colIndex);
                if(TblListadoProducto.getSelectedRow()==rowIndex)
                {
                    component.setBackground(TblListadoProducto.getSelectionBackground());
                }
                else
                {
                    if(rowIndex%2==0)
                    {
                        component.setBackground(new Color(238,235,235));
                    }
                    else
                    {
                        component.setBackground(Color.WHITE);
                    }
                }
                return component;
            }
        }
        ;
        jScrollPane2 = new javax.swing.JScrollPane();
        TblListadoProducto1 = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int colIndex)
            {
                Component component = super.prepareRenderer(renderer, rowIndex, colIndex);
                if(TblListadoProducto1.getSelectedRow()==rowIndex)
                {
                    component.setBackground(TblListadoProducto1.getSelectionBackground());
                }
                else
                {
                    if(rowIndex%2==0)
                    {
                        component.setBackground(new Color(238,235,235));
                    }
                    else
                    {
                        component.setBackground(Color.WHITE);
                    }
                }
                return component;
            }
        }
        ;

        LblPrecios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/precios.gif"))); // NOI18N
        LblPrecios.setOpaque(true);

        LblUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/product.png"))); // NOI18N

        jMenuItemModificarPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/precios.gif"))); // NOI18N
        jMenuItemModificarPrecios.setText("Modificar Precios");
        jMenuItemModificarPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarPreciosActionPerformed(evt);
            }
        });
        jPopupMenuProducto.add(jMenuItemModificarPrecios);

        jMenuItemEditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/product.png"))); // NOI18N
        jMenuItemEditarProducto.setText("Editar Producto");
        jMenuItemEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarProductoActionPerformed(evt);
            }
        });
        jPopupMenuProducto.add(jMenuItemEditarProducto);

        jMenuItemEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        jMenuItemEliminarProducto.setText("Eliminar Producto");
        jMenuItemEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarProductoActionPerformed(evt);
            }
        });
        jPopupMenuProducto.add(jMenuItemEliminarProducto);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Productos");
        setResizable(false);

        PnlBotones.setLayout(new java.awt.GridLayout(1, 0));

        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnNuevo);

        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnEditar);

        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        PnlBotones.add(btnEliminar1);

        BtnPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cash.png"))); // NOI18N
        BtnPrecios.setText("Precios");
        BtnPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPreciosActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnPrecios);

        BtnCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/dir.gif"))); // NOI18N
        BtnCatalogo.setText("Catálogo");
        BtnCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatalogoActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnCatalogo);

        BtnReportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/agt_print.png"))); // NOI18N
        BtnReportar.setText("Reportar");
        BtnReportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReportarActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnReportar);

        btnExportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icono-excel.png"))); // NOI18N
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        PnlBotones.add(btnExportar1);

        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/exit.png"))); // NOI18N
        BtnSalir.setText("Cancelar");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        PnlBotones.add(BtnSalir);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("Filtrar por:");

        TxtParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtParametroActionPerformed(evt);
            }
        });
        TxtParametro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtParametroKeyReleased(evt);
            }
        });

        buttonGroup1.add(RbtProducto);
        RbtProducto.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        RbtProducto.setText("Producto");

        buttonGroup1.add(RbtCodigo);
        RbtCodigo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        RbtCodigo.setText("Código");
        RbtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtCodigoActionPerformed(evt);
            }
        });

        buttonGroup1.add(RbtMarca);
        RbtMarca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        RbtMarca.setText("Marca");

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        BtnBuscar.setToolTipText("Filtar Producto");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        buttonGroup1.add(RbtCodigoBarra);
        RbtCodigoBarra.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        RbtCodigoBarra.setSelected(true);
        RbtCodigoBarra.setText("Codigo Barra");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbtProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbtCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RbtMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RbtCodigoBarra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RbtProducto)
                        .addComponent(RbtCodigo)
                        .addComponent(RbtMarca)
                        .addComponent(TxtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RbtCodigoBarra)))
                .addContainerGap())
        );

        BtnBuscar.getAccessibleContext().setAccessibleName("0");

        LblResultado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        LblResultado.setForeground(new java.awt.Color(102, 0, 0));
        LblResultado.setText("(0)Resultados");

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        TblListadoProducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TblListadoProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TblListadoProducto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblListadoProducto.setRowHeight(20);
        TblListadoProducto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblListadoProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListadoProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblListadoProducto);
        /**
        **/
        TblListadoProducto.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ProjSave");
        TblListadoProducto.getActionMap().put("ProjSave", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int fila = TblListadoProducto.getSelectedRow();

                    if ((fila > -1))
                    {
                        CEProducto oCEProducto=((CEProducto)TblListadoProducto.getValueAt(fila,2));
                        DlgMantenimientoProductoParaListas oDlgMantenimientoPreciosDeProductos=new DlgMantenimientoProductoParaListas(null,true,oCEProducto,false);
                        {
                            oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
                            oDlgMantenimientoPreciosDeProductos.setVisible(true);
                        }
                    }
                }
            });

            jTabbedPane1.addTab("Lista Por Unidad Base", jScrollPane1);

            TblListadoProducto1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
            TblListadoProducto1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
            ));
            TblListadoProducto1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            TblListadoProducto1.setRowHeight(20);
            TblListadoProducto1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            TblListadoProducto1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    TblListadoProducto1MouseClicked(evt);
                }
            });
            TblListadoProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TblListadoProducto1KeyPressed(evt);
                }
            });
            jScrollPane2.setViewportView(TblListadoProducto1);

            jTabbedPane1.addTab("Lista Por Todas las Unidades", jScrollPane2);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(183, 183, 183)
                            .addComponent(PnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(LblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTabbedPane1)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(LblResultado)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(PnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void BtnCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatalogoActionPerformed
        DlgGestionProducto oDlgBusquedaProducto = new DlgGestionProducto(null, true);
        oDlgBusquedaProducto.setLocationRelativeTo(null);
        oDlgBusquedaProducto.setVisible(true);
        filtrarProducto();
    }//GEN-LAST:event_BtnCatalogoActionPerformed

    int rowsel=0;
    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0)
        {
            int fila = TblListadoProducto.getSelectedRow();
            if (fila != -1)
            {
                rowsel=fila;
                CEProducto oCEProducto = (CEProducto) TblListadoProducto.getValueAt(fila, 3);

                if (oCEProducto != null)
                {
                    DlgMantenimientoProductoParaListas oDlgMantenimientoProductoParaListas = new DlgMantenimientoProductoParaListas(null, true, oCEProducto);
                    oDlgMantenimientoProductoParaListas.setLocationRelativeTo(null);
                    oDlgMantenimientoProductoParaListas.setVisible(true);
                    filtrarProducto();
                    TblListadoProducto.requestFocus();
                    TblListadoProducto.changeSelection(rowsel, 0, false, false);

                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe elegir una fila");
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1)
        {
            int fila = TblListadoProducto1.getSelectedRow();
            if (fila != -1)
            {
                rowsel=fila;
                CEProducto oCEProducto = (CEProducto) TblListadoProducto1.getValueAt(fila, 3);

                if (oCEProducto != null)
                {
                    DlgMantenimientoProductoParaListas oDlgMantenimientoProductoParaListas = new DlgMantenimientoProductoParaListas(null, true, oCEProducto);
                    oDlgMantenimientoProductoParaListas.setLocationRelativeTo(null);
                    oDlgMantenimientoProductoParaListas.setVisible(true);
                    filtrarProducto();
                    TblListadoProducto1.requestFocus();
                    TblListadoProducto1.changeSelection(rowsel, 0, false, false);
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe elegir una fila");
            }
        }
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void TblListadoProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListadoProductoMouseClicked
        int fila = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
        int columna = ((JTable) evt.getSource()).columnAtPoint(evt.getPoint());
        if (fila != -1)
        {
            if (evt.getButton() == evt.BUTTON1)
            {
                if (evt.getClickCount() == 1 && columna == 7)
                {
                    CEProducto oCEProducto = ((CEProducto) ((JTable) evt.getSource()).getValueAt(fila, 2));
                    DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos = new DlgMantenimientoPreciosDeProductos(null, true, oCEProducto);
                    {
                        oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
                        oDlgMantenimientoPreciosDeProductos.setVisible(true);
                    }
                }
                else
                {
                    if (evt.getClickCount() == 2)
                    {
                        CEProducto oCEProducto = ((CEProducto) ((JTable) evt.getSource()).getValueAt(fila, 2));
                        DlgMantenimientoProductoParaListas oDlgMantenimientoPreciosDeProductos = new DlgMantenimientoProductoParaListas(null, true, oCEProducto, false);
                        {
                            oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
                            oDlgMantenimientoPreciosDeProductos.setVisible(true);
                        }
                    }
                }
            }
            else
            {
                fila = ((JTable) evt.getSource()).getSelectedRow();
                if (fila != -1)
                {
                    jPopupMenuProducto.show(((JTable) evt.getSource()), evt.getX(), evt.getY());
                }
            }
        }
    }//GEN-LAST:event_TblListadoProductoMouseClicked

    int filaprecSel=0;
    private void BtnPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPreciosActionPerformed

       // if (jTabbedPane1.getSelectedIndex() == 1)
       // {
        eventoPrecio();
        //}
       // else
      //  {
            
       // }
    }//GEN-LAST:event_BtnPreciosActionPerformed

    private void eventoPrecio()
    {
        if (jTabbedPane1.getSelectedIndex() == 0)
        {
            int row = TblListadoProducto.getSelectedRow();
            if (row != -1)
            {
                filaprecSel=row;
                CEProducto oCEProducto = ((CEProducto) TblListadoProducto.getValueAt(TblListadoProducto.getSelectedRow(), 3));
                DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos = new DlgMantenimientoPreciosDeProductos(null, true, oCEProducto);
                oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
                oDlgMantenimientoPreciosDeProductos.setVisible(true);
                filtrarProducto();
                TblListadoProducto.requestFocus();
                TblListadoProducto.changeSelection(filaprecSel, 0, false, false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una fila");
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1)
        {
            int row = TblListadoProducto1.getSelectedRow();
            if (row != -1)
            {
                filaprecSel=row;
                CEProducto oCEProducto = ((CEProducto) TblListadoProducto1.getValueAt(TblListadoProducto1.getSelectedRow(), 3));
                DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos = new DlgMantenimientoPreciosDeProductos(null, true, oCEProducto);

                oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
                oDlgMantenimientoPreciosDeProductos.setVisible(true);
                filtrarProducto();
                TblListadoProducto1.requestFocus();
                TblListadoProducto1.changeSelection(filaprecSel, 0, false, false);

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una fila");
            }
        }
    }
    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed

        DlgMantenimientoProductoParaListas oDlgMantenimientoProductoParaListas = new DlgMantenimientoProductoParaListas(null, true);
        oDlgMantenimientoProductoParaListas.setLocationRelativeTo(null);
        oDlgMantenimientoProductoParaListas.setVisible(true);
        filtrarProducto();

    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void jMenuItemEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarProductoActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0)
        {

            int fila = TblListadoProducto.getSelectedRow();
            if (fila != -1)
            {
                CEProducto oCEProducto = (CEProducto) TblListadoProducto.getValueAt(fila, 3);

                if (oCEProducto != null)
                {
                    DlgMantenimientoProductoParaListas oDlgMantenimientoProductoParaListas = new DlgMantenimientoProductoParaListas(null, true, oCEProducto);
                    oDlgMantenimientoProductoParaListas.setLocationRelativeTo(null);
                    oDlgMantenimientoProductoParaListas.setVisible(true);
                    filtrarProducto();
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe elegir una fila");
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1)
        {

            int fila = TblListadoProducto1.getSelectedRow();
            if (fila != -1)
            {
                CEProducto oCEProducto = (CEProducto) TblListadoProducto1.getValueAt(fila, 3);

                if (oCEProducto != null)
                {
                    DlgMantenimientoProductoParaListas oDlgMantenimientoProductoParaListas = new DlgMantenimientoProductoParaListas(null, true, oCEProducto);
                    oDlgMantenimientoProductoParaListas.setLocationRelativeTo(null);
                    oDlgMantenimientoProductoParaListas.setVisible(true);
                   filtrarProducto();
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe elegir una fila");
            }
        }
}//GEN-LAST:event_jMenuItemEditarProductoActionPerformed

    private void jMenuItemEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarProductoActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0)
        {
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?", "Mensaje de Confirmación", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION)
            {


                int fila = TblListadoProducto.getSelectedRow();
                if (fila != -1)
                {
                    CEProducto oCEProducto = (CEProducto) TblListadoProducto.getValueAt(fila, 3);

                    if (oCEProducto != null)
                    {
                        if (!CCProducto.eliminarProducto(oCEProducto))
                        {
                            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto");
                        }
                        filtrarProducto();
                    }

                }

            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1)
        {
            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?", "Mensaje de Confirmación", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION)
            {


                int fila = TblListadoProducto1.getSelectedRow();
                if (fila != -1)
                {
                    CEProducto oCEProducto = (CEProducto) TblListadoProducto1.getValueAt(fila, 3);

                    if (oCEProducto != null)
                    {
                        if (!CCProducto.eliminarProducto(oCEProducto))
                        {
                            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto");
                        }
                        filtrarProducto();
                    }

                }

            }
        }
}//GEN-LAST:event_jMenuItemEliminarProductoActionPerformed

    private void jMenuItemModificarPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarPreciosActionPerformed
//        if (jTabbedPane1.getSelectedIndex() == 0)
//        {
//            CEProducto oCEProducto = ((CEProducto) TblListadoProducto.getValueAt(TblListadoProducto.getSelectedRow(), 2));
//            DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos = new DlgMantenimientoPreciosDeProductos(null, true, oCEProducto);
//            {
//                oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
//                oDlgMantenimientoPreciosDeProductos.setVisible(true);
//                listarProductos(TblListadoProducto);
//                listarProductos(TblListadoProducto1);
//                filtrarProducto();
//            }
//        }

         if (jTabbedPane1.getSelectedIndex() == 1)
        {
        eventoPrecio();
        }
        else
        {

        }
        
}//GEN-LAST:event_jMenuItemModificarPreciosActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 0)
        {
            int fila = TblListadoProducto.getSelectedRow();
            if (fila != -1)
            {
                if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?", "Mensaje de Confirmación", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION)
                {


                    CEProducto oCEProducto = (CEProducto) TblListadoProducto.getValueAt(fila, 3);

                    if (oCEProducto != null)
                    {
                        if (!CCProducto.eliminarProducto(oCEProducto))
                        {
                            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto");
                        }
                        filtrarProducto();

                    }

                }
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 1)
        {
            int fila = TblListadoProducto1.getSelectedRow();
            if (fila != -1)
            {
                if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?", "Mensaje de Confirmación", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION)
                {


                    CEProducto oCEProducto = (CEProducto) TblListadoProducto1.getValueAt(fila, 3);

                    if (oCEProducto != null)
                    {
                        if (!CCProducto.eliminarProducto(oCEProducto))
                        {
                            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto");
                        }
                        filtrarProducto();

                    }

                }
            }
        }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    
    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        filtrarProducto();
}//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtParametroActionPerformed
        filtrarProducto();

    }//GEN-LAST:event_TxtParametroActionPerformed
    private void filtrarProducto()
    {
         String parametro = TxtParametro.getText();
        {
            List<CEProducto> oLstProducto = CCProducto.consultarListaProductoPorDescripcion(evaluarFiltro(), parametro);
            if (jTabbedPane1.getSelectedIndex() == 0)
            {
                listarProductos(oLstProducto, TblListadoProducto);
            }
            if (jTabbedPane1.getSelectedIndex() == 1)
            {
                listarProductos(oLstProducto, TblListadoProducto1);
            }
        }
    }
    private void TxtParametroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtParametroKeyReleased
        if (evt.getKeyCode() == evt.VK_DOWN)
        {
            if (jTabbedPane1.getSelectedIndex() == 0)
            {
                int size = TblListadoProducto.getRowCount();
                if (size > 0)
                {
                    TblListadoProducto.requestFocus();
                    TblListadoProducto.setRowSelectionInterval(0, 0);
                }
            }
            if (jTabbedPane1.getSelectedIndex() == 1)
            {
               int size = TblListadoProducto.getRowCount();
                    if (size > 0)
                    {
                        TblListadoProducto1.requestFocus();
                        TblListadoProducto1.setRowSelectionInterval(0, 0);
                    }

            }

            
        }
    }//GEN-LAST:event_TxtParametroKeyReleased

    private void BtnReportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReportarActionPerformed
        reportar();
    }//GEN-LAST:event_BtnReportarActionPerformed

    private void TblListadoProducto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListadoProducto1MouseClicked
        int fila = ((JTable) evt.getSource()).rowAtPoint(evt.getPoint());
        int columna = ((JTable) evt.getSource()).columnAtPoint(evt.getPoint());
        if (fila != -1)
        {
            if (evt.getButton() == evt.BUTTON1)
            {
                if (evt.getClickCount() == 1 && columna == 7)
                {
                    eventoPrecio();
                }
                else
                {
                    if (evt.getClickCount() == 2)
                    {
                        eventoPrecio();
                    }
                }
            }
            else
            {
                fila = ((JTable) evt.getSource()).getSelectedRow();
                if (fila != -1)
                {
                    jPopupMenuProducto.show(((JTable) evt.getSource()), evt.getX(), evt.getY());
                }
            }
        }
    }//GEN-LAST:event_TblListadoProducto1MouseClicked

    private void TblListadoProducto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListadoProducto1KeyPressed
         
        if(TblListadoProducto1.getRowCount()>0){
       TblListadoProducto1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblListadoProducto1.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                 eventoPrecio(); 
            }
        });
        
        }
    }//GEN-LAST:event_TblListadoProducto1KeyPressed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        if (this.jTabbedPane1.getSelectedIndex() == 0)
        {

        CLExportarExcel oExportar=new CLExportarExcel(TblListadoProducto,
                new String[]{},new String[]{},new String[]{}
        ,this.getTitle());
        oExportar.GuardarArchivoExcel(this);
        }else
        {
                    CLExportarExcel oExportar=new CLExportarExcel(TblListadoProducto1,
                    new String[]{},new String[]{},new String[]{}
                     ,this.getTitle());
                    oExportar.GuardarArchivoExcel(this);
        }

}//GEN-LAST:event_btnExportar1ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        
        if(filtrarTbPn)
        filtrarProducto();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void RbtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtCodigoActionPerformed
        // TDO add your handling code here:
    }//GEN-LAST:event_RbtCodigoActionPerformed
    private void reportar()
    {
        ClaseReporte oClaseReporte = new ClaseReporte();
        String oUrlC = null;
        CLReporte oCLReporte = new CLReporte();
        URL oUrlRC = getClass().getResource("/view/almAlmacen/rptALMReportes/ALMRPTListadoPrecios.jasper");
        oCLReporte.setUrlReport(oUrlRC.toString());
        oUrlC = oCLReporte.getUrlReport().replace('\'', '/');
        if (oCLReporte != null)
        {
            oClaseReporte.setUrl(oUrlC);
            ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
            JasperViewer jviewer = new JasperViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte), false);
            jviewer.setTitle("Reporte");
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El reporte no se encuentra");
        }


    }
    private int evaluarFiltro()
    {
        if (this.jTabbedPane1.getSelectedIndex() == 0)
        {
            if (RbtProducto.isSelected())
            {
                return 6;
            }
          else
                if(RbtCodigoBarra.isSelected())
                {
                    return 11 ;
                }
            else
            {
                if (RbtCodigo.isSelected())
                {
                    return 4;
                }
                else
                {
                    return 5;
                }
            }
        }
        else
        {
            if (this.jTabbedPane1.getSelectedIndex() == 1)
            {
                if (RbtProducto.isSelected())
                {
                    return 7;
                }
                else
                if (RbtCodigoBarra.isSelected()) {
                        return 10 ;  
                    }
                else
                {
                    if (RbtCodigo.isSelected())
                    {
                        return 8;
                    }
                    else
                    {
                        return 9;
                    }
                }
            }
        }
        return 0;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private BotonesABM.BtnEditar BtnCatalogo;
    private BotonesABM.BtnEditar BtnEditar;
    private BotonesABM.BtnNuevo BtnNuevo;
    private BotonesABM.BtnEditar BtnPrecios;
    private BotonesABM.BtnEditar BtnReportar;
    private BotonesABM.BtnEditar BtnSalir;
    private javax.swing.JLabel LblPrecios;
    private javax.swing.JLabel LblResultado;
    private javax.swing.JLabel LblUnidad;
    private javax.swing.JPanel PnlBotones;
    private javax.swing.JRadioButton RbtCodigo;
    private javax.swing.JRadioButton RbtCodigoBarra;
    private javax.swing.JRadioButton RbtMarca;
    private javax.swing.JRadioButton RbtProducto;
    private javax.swing.JTable TblListadoProducto;
    private javax.swing.JTable TblListadoProducto1;
    private javax.swing.JTextField TxtParametro;
    private BotonesABM.BtnEliminar btnEliminar1;
    private BotonesABM.BtnExportar btnExportar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItemEditarProducto;
    private javax.swing.JMenuItem jMenuItemEliminarProducto;
    private javax.swing.JMenuItem jMenuItemModificarPrecios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenuProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
