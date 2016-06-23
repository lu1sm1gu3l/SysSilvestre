package view.almAlmacen.invInventarios;

import controller.almAlmacen.CCAlmacen;
import controller.almAlmacen.CCTransferenciaAlmacen;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEAlmacen;
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CETransferenciaAlmacen;
import modelo.almAlmacen.entidad.CETransferenciaAlmacenDetalle;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmpCompra.entidad.CEOrdenCompraDetalle;
import modelo.cmpCompra.entidad.CEOrdenCompraMatriz;
import util.clases.almAlmacen.CLTransferenciaAlmacen;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProductoInventarios;

/**
 *
 * @author Luiggi
 */
public class DlgTransferenciaAlmacen extends javax.swing.JDialog implements ActionListener{

   private  int IdEstado=0;
   private MiReloj hilo;
   private  CLTransferenciaAlmacen oCLTransferenciaAlmacen;
   private  int pAccionABM=0;
   private CLBotonesABM oclBotonesABM;
   private Frame oparent;

    /** Creates new form DlgGestionTransferenciaAlmacen */
    public DlgTransferenciaAlmacen(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        this.setLocationRelativeTo(null);
        oparent=parent;
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblTransferenciaAlmacenDetalle.setSurrendersFocusOnKeystroke(true);
        realizarEventoCbxOrigen=true;
        btnEditar.setVisible(false);
        btnAnular.setVisible(false);
        
        
    }

    private void cargarUtilidades()
    {
       
        oCLTransferenciaAlmacen = new CLTransferenciaAlmacen(TblTransferenciaAlmacenDetalle);
        TblTransferenciaAlmacenDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
    
    
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colCantidad).setCellRenderer(tcr);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colPrecio).setCellRenderer(tcr);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colImporte).setCellRenderer(tcr);

  

        TableColumn columnaCantidad = TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaPrecio = TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(oCLTransferenciaAlmacen.colPrecio);
        columnaPrecio.setCellEditor(new DefaultCellEditor(jTextFieldNumber));       
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        ((JTableX)TblTransferenciaAlmacenDetalle).setSelectAllForEdit(true);
        ArrayList<CEAlmacen> listaAlmacenDestino=CCAlmacen.listarAlmacenSucursal();
        CbxDestino.setModel(CLComboBox.cargarComboAlmacenConPivot((listaAlmacenDestino)));
        CbxOrigen.setModel(CLComboBox.cargarComboAlmacenConPivot((listaAlmacenDestino)));
        CbxOrigen.setSelectedIndex(0);
        CbxDestino.setSelectedIndex(0);
        eventoNuevo();
        

    }
private void ValidarAlmacen()
{
   
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        LblHoraRegistro1 = new Label.Label();
        LblEtiqHoraRegistro = new Label.Label();
        LblEtiqUsuario = new Label.Label();
        TxtSucursal = new Label.Label();
        CbxOrigen = new ComboBox.ComboBox();
        LblEtiqDestino = new Label.Label();
        LblEtiqOrigen = new Label.Label();
        CbxDestino = new ComboBox.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTransferenciaAlmacenDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLTransferenciaAlmacen.colCantidad)
                    {
                        if(!value.toString().trim().equals("")||value!=null||!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }

                }
                catch(Exception e)
                {
                    if(this.getRowCount()>row){
                        super.setValueAt(null,row,column);
                    }
                    System.out.println("*Constructor tabla - CLPedido : "+ row+" "+e);
                }
            }

        };
        label20 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new BotonesABM.BtnNuevo();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnEditar = new BotonesABM.BtnEditar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnExportar1 = new BotonesABM.BtnExportar();
        btnCancelar = new BotonesABM.BtnCancelar();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnBuscarPedido = new BotonesABM.BtnBuscar();
        TxtEstado = new Label.Label();
        LblEtiqEstado = new Label.Label();
        lblEtiqNumeroTransf = new Label.Label();
        TxtNumTransf = new TextField.JTxtNinguno();

        jTextFieldNumber.setFont(new java.awt.Font("Verdana", 0, 12));
        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        TxtProducto.setBorder(null);
        TxtProducto.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProducto.setTamanio(250);
        TxtProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProductoMouseClicked(evt);
            }
        });
        TxtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtProductoKeyTyped(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transferencia de Almacén");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PnlDatos2.setBackground(java.awt.SystemColor.controlHighlight);
        PnlDatos2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        LblEtiqSucursal.setText("Sucursal   :");

        TxtVendedor.setText("NINGUNO");
        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        LblHoraRegistro1.setText("00/00/00 00:00:00");
        LblHoraRegistro1.setFont(new java.awt.Font("Verdana", 0, 11));
        LblHoraRegistro1.setText("");

        LblEtiqHoraRegistro.setText("Fecha Reg:");

        LblEtiqUsuario.setText("Usuario   : ");

        TxtSucursal.setText("NINGUNO");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        CbxOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxOrigenActionPerformed(evt);
            }
        });

        LblEtiqDestino.setText("Destino :");

        LblEtiqOrigen.setText("Origen     :");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblEtiqOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblTransferenciaAlmacenDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblTransferenciaAlmacenDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Cantidad", "U. M. ", "Costo", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblTransferenciaAlmacenDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblTransferenciaAlmacenDetalle.setColumnSelectionAllowed(true);
        TblTransferenciaAlmacenDetalle.setRowHeight(21);
        TblTransferenciaAlmacenDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblTransferenciaAlmacenDetalle.getTableHeader().setReorderingAllowed(false);
        TblTransferenciaAlmacenDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblTransferenciaAlmacenDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblTransferenciaAlmacenDetalleMousePressed(evt);
            }
        });
        TblTransferenciaAlmacenDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblTransferenciaAlmacenDetallePropertyChange(evt);
            }
        });
        TblTransferenciaAlmacenDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblTransferenciaAlmacenDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblTransferenciaAlmacenDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblTransferenciaAlmacenDetalle);
        TblTransferenciaAlmacenDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(2).setPreferredWidth(420);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(4).setPreferredWidth(90);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(5).setPreferredWidth(90);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(6).setPreferredWidth(90);
        TblTransferenciaAlmacenDetalle.getColumnModel().getColumn(7).setPreferredWidth(90);

        label20.setText("INGRESO DE PRODUCTOS");
        label20.setFont(new java.awt.Font("Verdana", 1, 12));

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setToolTipText("Agregar Item");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/DeleteUser.png"))); // NOI18N
        BtnQuitar.setToolTipText("Quitar Item");
        BtnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarActionPerformed(evt);
            }
        });

        columnButton1.setTbl(TblTransferenciaAlmacenDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar);

        btnEditar.setVisible(false);
        btnAnular.setVisible(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar);

        btnAnular.setVisible(false);
        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnular);

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnExportar1);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Observación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11))); // NOI18N

        TxtObservacion.setColumns(20);
        TxtObservacion.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtObservacion.setRows(5);
        jScrollPane2.setViewportView(TxtObservacion);

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarPedido.setText("Buscar Transferencia");
        btnBuscarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPedido.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarPedido.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPedidoActionPerformed(evt);
            }
        });

        TxtEstado.setText("EMITIDO");
        TxtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqEstado.setText("Estado:");

        lblEtiqNumeroTransf.setText("Nº Transferencia:");

        TxtNumTransf.setEditable(false);
        TxtNumTransf.setTamanio(12);
        TxtNumTransf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumTransfKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEtiqNumeroTransf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtNumTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(btnBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumTransf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblEtiqNumeroTransf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
private void habilitarControles(boolean bol)
    {
    TblTransferenciaAlmacenDetalle.setEnabled(bol);
    BtnAgregar.setEnabled(bol);
    BtnQuitar.setEnabled(bol);
    TxtProducto.setEnabled(bol);
    TxtObservacion.setEnabled(bol);
    CbxDestino.setEnabled(bol);
    CbxOrigen.setEnabled(bol);
    TxtNumTransf.setEnabled(bol);
}

private int ValidarRegistro(int fila)
    {
     if(TblTransferenciaAlmacenDetalle.getRowCount()>0){
    
     int numColumna=0;
        CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle=(CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(fila-1, oCLTransferenciaAlmacen.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCETransferenciaAlmacenDetalle!=null){
            if(TblTransferenciaAlmacenDetalle.getValueAt(fila-1, oCLTransferenciaAlmacen.colCantidad)!=null)
            {
                float cantidad=Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(fila-1, oCLTransferenciaAlmacen.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLTransferenciaAlmacen.colCantidad;
                        colValidada=oCLTransferenciaAlmacen.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
//                    float precio=0;
//                        if(TblTransferenciaAlmacenDetalle.getValueAt(fila-1, oCLTransferenciaAlmacen.colPrecio)!=null)
//                        {
//                        precio=Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(fila-1, oCLTransferenciaAlmacen.colPrecio).toString());
//                        }
//                        if(precio==0){
//                        numColumna = oCLTransferenciaAlmacen.colPrecio;
//                        colValidada = oCLTransferenciaAlmacen.colPrecio;
//                        mensaje="Ingrese el costo";
//                        }
                }
            }else{
                        numColumna = oCLTransferenciaAlmacen.colCantidad;
                        colValidada = oCLTransferenciaAlmacen.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLTransferenciaAlmacen.colProducto;
            colValidada=oCLTransferenciaAlmacen.colProducto;

        }
        if(numColumna!=0){
//            if(numColumna!=oCLTransferenciaAlmacen.colPrecio){
            JOptionPane.showMessageDialog(null,mensaje);
//            }else
//                { int filat=TblTransferenciaAlmacenDetalle.getSelectedRow();
//                  oCLTransferenciaAlmacen.calcularPrecio(filat);
//                }
            }

        return numColumna;
        
        }
    return 0;
}


private boolean validarbusqProducto()
    {
    CEAlmacen oCEAlmacen=(CEAlmacen)CbxOrigen.getSelectedItem();
        if(oCEAlmacen.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(this,"Seleccione un Almacen Origen","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TblTransferenciaAlmacenDetalle.changeSelection(0, oCLTransferenciaAlmacen.colProducto-1, false, false);
             CbxOrigen.requestFocus();
             return false;
        }


    return true;
    }
    private void BuscarProducto()
    {
        CEAlmacen oCEAlmacenOrigen=(CEAlmacen)CbxOrigen.getSelectedItem();
        if(validarbusqProducto()){
        DlgBusquedaProductoInventarios odialogo= new DlgBusquedaProductoInventarios(oparent, true,1,oCEAlmacenOrigen.getIdAlmacen());
                String NombreProd=TxtProducto.getText().trim();
                btnGuardar.requestFocus();
                if(NombreProd.equals(""))
                {
               if(TblTransferenciaAlmacenDetalle.getValueAt(TblTransferenciaAlmacenDetalle.getSelectedRow(), oCLTransferenciaAlmacen.colProducto)!=null)
                    {
                    NombreProd=TblTransferenciaAlmacenDetalle.getValueAt(TblTransferenciaAlmacenDetalle.getSelectedRow(), oCLTransferenciaAlmacen.colProducto)+"";
                    }
                }
               odialogo.setCajaTexto(NombreProd);
               TxtProducto.setText("");
             //  odialogo.setListaTransferenciaAlmacen(this.ObtenerRegistrosAcuales());
               odialogo.setVisible(true);
               CEAlmacenProducto oCEAlmacenProducto=odialogo.getProductoAlmacen();
               if(!odialogo.getisagregarCantidadOrdenCompra()){
               agregarTransferenciaAlmacenDetalle(oCEAlmacenProducto);
               
               int fila=TblTransferenciaAlmacenDetalle.getSelectedRow();
               TblTransferenciaAlmacenDetalle.requestFocus();
               TblTransferenciaAlmacenDetalle.changeSelection(fila, oCLTransferenciaAlmacen.colCantidad, false, false);

                }else{
                   TblTransferenciaAlmacenDetalle.requestFocus();
                   SeleccionarTransferenciaAlmacenExistente(oCEAlmacenProducto);
                }
        }

    }
    private void SeleccionarTransferenciaAlmacenExistente(CEAlmacenProducto oCEAlmacenProducto)
    {
        CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle ;
        for( int i =0;i<TblTransferenciaAlmacenDetalle.getRowCount();i++)
            {
//cx
                oCETransferenciaAlmacenDetalle= (CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCodigo);
                if(oCETransferenciaAlmacenDetalle!=null){
                    
                        if(
                           oCETransferenciaAlmacenDetalle.getIdUnidadMedida()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCETransferenciaAlmacenDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblTransferenciaAlmacenDetalle.requestFocus();
                            TblTransferenciaAlmacenDetalle.changeSelection(i, oCLTransferenciaAlmacen.colUnidadMedida, false, false);
                        }

                }
            }
    }
    private List<CETransferenciaAlmacenDetalle> ObtenerRegistrosAcuales()
    {
        List<CETransferenciaAlmacenDetalle> lstTransferenciaAlmacenDetalleTemp = new ArrayList<CETransferenciaAlmacenDetalle>();
        CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle ;
        for( int i =0;i<TblTransferenciaAlmacenDetalle.getRowCount();i++)
            {
                oCETransferenciaAlmacenDetalle= (CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCodigo);
                if(oCETransferenciaAlmacenDetalle!=null){
                    if(i!=TblTransferenciaAlmacenDetalle.getSelectedRow()){
                oCETransferenciaAlmacenDetalle.setCosto(Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colPrecio).toString()));
                oCETransferenciaAlmacenDetalle.setCantidad(0);
                if(TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCantidad)!=null){
                oCETransferenciaAlmacenDetalle.setCantidad(Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCantidad).toString()));
                }
                CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalleTemp=(CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCodigo);
                oCETransferenciaAlmacenDetalle.setIdProducto(oCETransferenciaAlmacenDetalleTemp.getIdProducto());
                lstTransferenciaAlmacenDetalleTemp.add(oCETransferenciaAlmacenDetalle);
                    }
                }
            }
        return lstTransferenciaAlmacenDetalleTemp;
    }private boolean  ValidarTablaTransferenciaAlmacen()
     {


            for(int i=0; i<TblTransferenciaAlmacenDetalle.getRowCount();i++)
            {
                verificarDatos(i);

                if(colValidada!=-1){
                    TblTransferenciaAlmacenDetalle.requestFocus();
                    TblTransferenciaAlmacenDetalle.changeSelection(i, colValidada, false, false);
                    colValidada=-1;
                    return false;
                }
            }
            eventoGuardar=false;
        return true;
    }
    boolean eventoGuardar=false;
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

     eventoGuardar();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void eventoGuardar(){
    if(btnGuardar.isEnabled()){
        int fil=TblTransferenciaAlmacenDetalle.getRowCount();
        if(fil>0){
          int row=fil-1;
              CETransferenciaAlmacenDetalle oCEPedidoDetalle= (CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(row, oCLTransferenciaAlmacen.colCodigo);
              if(oCEPedidoDetalle==null){

                oCLTransferenciaAlmacen.QuitarFilaSel(row);

              }
         }
               fil=TblTransferenciaAlmacenDetalle.getRowCount();
              if(fil>0){
              int columnaValidada=ValidarRegistro(TblTransferenciaAlmacenDetalle.getRowCount());
              if(columnaValidada==0)
              {
                 eventoGuardar=true;
                  GuardarRegistro();

              }else
              {
                  TblTransferenciaAlmacenDetalle.requestFocus();
                  TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getRowCount()-1, columnaValidada, false, false);
              }
            }else
            {
                   JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                     agregarNuevaFila(0);
                     TblTransferenciaAlmacenDetalle.requestFocus();
                     TblTransferenciaAlmacenDetalle.changeSelection(0, oCLTransferenciaAlmacen.colProducto, false, false);
            }

       }
    }
    private boolean validar()
    {


        if(TblTransferenciaAlmacenDetalle.getRowCount()==0)
        {
             JOptionPane.showMessageDialog(null,"Ingrese una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblTransferenciaAlmacenDetalle.requestFocus();
             TblTransferenciaAlmacenDetalle.changeSelection(0, oCLTransferenciaAlmacen.colProducto, false, false);
             return false;
        }

        CEAlmacen oCEAlmacen=(CEAlmacen)CbxOrigen.getSelectedItem();
        if(oCEAlmacen.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(null,"Seleccione un Almacen Origen","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             CbxOrigen.requestFocus();
             return false;
        }
        CEAlmacen oCEAlmacenDest=(CEAlmacen)CbxDestino.getSelectedItem();
        if(oCEAlmacenDest.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(null,"Seleccione un Almacen Destino","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             CbxDestino.requestFocus();
             return false;
        }
        CEAlmacen oCEAlmacenOrigen=(CEAlmacen)CbxOrigen.getSelectedItem();
        CEAlmacen oCEAlmacenDestino=(CEAlmacen)CbxDestino.getSelectedItem();
        if(oCEAlmacenDestino.getIdAlmacen()==oCEAlmacenOrigen.getIdAlmacen())
        {
            JOptionPane.showMessageDialog(null,"El almacen de origen y el almacen destino no pueden ser iguales","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    private void GuardarRegistro()
    {
        if(ValidarTablaTransferenciaAlmacen()){
        if(validar()){
                    if(pAccionABM==1){
                        CETransferenciaAlmacen oCETransferenciaAlmacen=this.getTransferenciaAlmacen();
                        String Mensaje=CCTransferenciaAlmacen.InsTransferenciaAlmacen(oCETransferenciaAlmacen);
                        if(Mensaje.equals("OK"))
                        {

                        JOptionPane.showMessageDialog(null,"<html>El código de TransferenciaAlmacen es: "+oCETransferenciaAlmacen.getNumeroTransferencia()+"</html>");
                        limpiarFormulario();
                        LblHoraRegistro1.setText("");
                        TblTransferenciaAlmacenDetalle.requestFocus();
                        TblTransferenciaAlmacenDetalle.changeSelection(0,oCLTransferenciaAlmacen.colProducto, false, false);
                        }
                        else{

                        JOptionPane.showMessageDialog(this, "No se pudo completar la operación: \nStock Menor a la cantidad de Salida\nDetalles:\n"+Mensaje);
                        }
                    }else{
                        int iscorrecto=CCTransferenciaAlmacen.UPDTransferenciaAlmacen(this.getTransferenciaAlmacen());
                        if(iscorrecto==1)
                        {
                        JOptionPane.showMessageDialog(null,"<html><CENTER>Operación exitosa</CENTER></html>");                        
                        limpiarFormulario();
                        pAccionABM=1;
                        }
                    else{
                        JOptionPane.showMessageDialog(null,"Operación Fallida");
                        }
               }
      }
     }
    }

    private CETransferenciaAlmacen getTransferenciaAlmacen(){
        CETransferenciaAlmacen oCETransferenciaAlmacen= new CETransferenciaAlmacen();

        CEAlmacen oCEAlmacenOrigen=(CEAlmacen)CbxOrigen.getSelectedItem();
        CEAlmacen oCEAlmacenDestino=(CEAlmacen)CbxDestino.getSelectedItem();
        oCETransferenciaAlmacen.setIdAlmacenOrigen(oCEAlmacenOrigen.getIdAlmacen());
        oCETransferenciaAlmacen.setIdAlmacenDestino(oCEAlmacenDestino.getIdAlmacen());
        oCETransferenciaAlmacen.setIdSucursal(FrmSistemaMenu.getIdSucursal());
      //  oCETransferenciaAlmacen.setIdUltimoEstado(1);

        oCETransferenciaAlmacen.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCETransferenciaAlmacen.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCETransferenciaAlmacen.setObservacion(TxtObservacion.getText());
        List<CETransferenciaAlmacenDetalle> lstTransferenciaAlmacenDetalle = new ArrayList<CETransferenciaAlmacenDetalle>();
        CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle ;
        for( int i =0;i<TblTransferenciaAlmacenDetalle.getRowCount();i++)
            {

                oCETransferenciaAlmacenDetalle= (CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCodigo);
                if(oCETransferenciaAlmacenDetalle!=null){
                oCETransferenciaAlmacenDetalle.setCantidad(Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colCantidad).toString()));
                oCETransferenciaAlmacenDetalle.setImporte(Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(i, oCLTransferenciaAlmacen.colImporte).toString()));
                oCETransferenciaAlmacenDetalle.setIdAlmacen(1);
                   
                }
                lstTransferenciaAlmacenDetalle.add(oCETransferenciaAlmacenDetalle);
            }

            oCETransferenciaAlmacen.setLstCETransferenciaAlmacenDetalle(lstTransferenciaAlmacenDetalle);

            return oCETransferenciaAlmacen;
}
    private void agregarNuevaFila(int fila)
    {
      
         Vector oVector = new Vector();

                ((DefaultTableModel)TblTransferenciaAlmacenDetalle.getModel()).addRow(oVector);
             

                    TblTransferenciaAlmacenDetalle.requestFocus();
                   
        
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{
      int columnaValidada=ValidarRegistro(TblTransferenciaAlmacenDetalle.getRowCount());
      if(columnaValidada==0)
      {
          agregarNuevaFila(TblTransferenciaAlmacenDetalle.getRowCount());
          TblTransferenciaAlmacenDetalle.requestFocus();
          TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getRowCount()-1, oCLTransferenciaAlmacen.colProducto, false, false);


      }else
      {
          TblTransferenciaAlmacenDetalle.requestFocus();
          TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getRowCount()-1, columnaValidada, false, false);
      }
}
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed
      oCLTransferenciaAlmacen.QuitarFila();
    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       if(pAccionABM==1||pAccionABM==2)
       {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            LblHoraRegistro1.setText("");
            pAccionABM=0;

            limpiarFormulario();
       }else{
           iscerrando=true;
             dispose();    
            }


    }//GEN-LAST:event_btnCancelarActionPerformed

    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila){
         try{
            colValidada=-1;

            float cantidad=1;
            float stock=1;
            if(TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colCantidad)!=null){
            cantidad=Float.parseFloat(TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colCantidad).toString());}

         stock=VerificadorDeTxt.convertFloat(TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colStock).toString());
                    if(cantidad>stock)
                    {
                        JOptionPane.showMessageDialog(this,"La cantidad debe ser mayor al Stock");
                        TblTransferenciaAlmacenDetalle.setValueAt(null,fila,oCLTransferenciaAlmacen.colCantidad);
                        colValidada=oCLTransferenciaAlmacen.colCantidad;
                         return;
                    }


        if(cantidad<=0)
            {
                JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                TblTransferenciaAlmacenDetalle.setValueAt(null,fila,oCLTransferenciaAlmacen.colCantidad);
                colValidada=oCLTransferenciaAlmacen.colCantidad;
                 return;
            }

        int col=TblTransferenciaAlmacenDetalle.getSelectedColumn();
                if(!eventoGuardar){
                if(col==oCLTransferenciaAlmacen.colCantidad){
                    oCLTransferenciaAlmacen.calcularPrecio(fila);
                }
             }
            
            }catch(Exception e)
            {
                cont++;
               // System.out.println(e+"metodo Verificar datos");
            }
    }


private String Codigo;

    
    public void SetTransferenciaAlmacen(CETransferenciaAlmacen oCETransferenciaAlmacen)
    {
        oCLTransferenciaAlmacen.limpiarTabla();
        this.Codigo=TxtNumTransf.getText();
        if(oCETransferenciaAlmacen!=null)
        {          
            realizarEventoCbxOrigen=false;
            IdEstado=oCETransferenciaAlmacen.getUltimoIdEstado();
            TxtProducto.setText("");
            TxtSucursal.setText(oCETransferenciaAlmacen.getSucursal());
            TxtObservacion.setText(oCETransferenciaAlmacen.getObservacion());
            TxtVendedor.setText(oCETransferenciaAlmacen.getEmpleado());
            LblHoraRegistro1.setText(oCETransferenciaAlmacen.getFecha());
            CLCargarCombo.buscarIdAlmacen(CbxOrigen, oCETransferenciaAlmacen.getIdAlmacenOrigen());
            CLCargarCombo.buscarIdAlmacen(CbxDestino, oCETransferenciaAlmacen.getIdAlmacenDestino());
            TxtNumTransf.setText(oCETransferenciaAlmacen.getNumeroTransferencia());
            TxtObservacion.setText(oCETransferenciaAlmacen.getObservacion());
            if(oCETransferenciaAlmacen.getLstCETransferenciaAlmacenDetalle()!=null)
            {
               for(int i=0; i<oCETransferenciaAlmacen.getLstCETransferenciaAlmacenDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblTransferenciaAlmacenDetalle.getModel()).addRow(oVector);
                
                CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle=oCETransferenciaAlmacen.getLstCETransferenciaAlmacenDetalle().get(i) ;

                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCETransferenciaAlmacenDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCETransferenciaAlmacenDetalle.getIdUnidadMedida());
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle,i,oCLTransferenciaAlmacen.colCodigo);
                TxtProducto.setText(oCETransferenciaAlmacenDetalle.getProducto());
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle.getProducto(),i,oCLTransferenciaAlmacen.colProducto);
                TblTransferenciaAlmacenDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLTransferenciaAlmacen.colUnidadMedida);
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle.getCosto(),i,oCLTransferenciaAlmacen.colPrecio);
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle.getCantidad(),i,oCLTransferenciaAlmacen.colCantidad);
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle.getImporte(),i,oCLTransferenciaAlmacen.colImporte);
                }
            }

            habilitarControles(false);
            realizarEventoCbxOrigen=true;
            CbxDestino.setEnabled(false);
            CbxOrigen.setEnabled(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=2;
            btnEditar.requestFocus();
        }
    else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
          TxtNumTransf.requestFocus();
        }
    }
    private void eventoNuevo()
    {
       limpiarFormulario();
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
       TblTransferenciaAlmacenDetalle.requestFocus();
       TblTransferenciaAlmacenDetalle.changeSelection(0,oCLTransferenciaAlmacen.colProducto, false, false);

}
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
    eventoNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed


        oclBotonesABM.controlBoton(true, false, false, false, true, false);
      
        CETransferenciaAlmacen oCETransferenciaAlmacen= new CETransferenciaAlmacen();
        oCETransferenciaAlmacen.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCETransferenciaAlmacen.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCETransferenciaAlmacen.setNumeroIngreso(Codigo);
                   
        DlgAnularTransferenciaAlmacen oDlgAnularTransferenciaAlmacen= new DlgAnularTransferenciaAlmacen(null,true , oCETransferenciaAlmacen);
        oDlgAnularTransferenciaAlmacen.setLocationRelativeTo(null);
        oDlgAnularTransferenciaAlmacen.setVisible(true);
        limpiarFormulario();
        habilitarControles(false);
                
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         oclBotonesABM.controlBoton(false, true, false, true, true, false);
         habilitarControles(true);
         pAccionABM=2;
    }//GEN-LAST:event_btnEditarActionPerformed


    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void TxtProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProductoMouseClicked

      if(evt.getClickCount()==2)
        {
            BuscarProducto();
        }
    }//GEN-LAST:event_TxtProductoMouseClicked

    private void TxtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyPressed

      if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
        evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

            BuscarProducto();

        }          

    }//GEN-LAST:event_TxtProductoKeyPressed

    private void TxtProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyReleased

  
        
    }//GEN-LAST:event_TxtProductoKeyReleased

    private void TxtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProductoKeyTyped
         
    }//GEN-LAST:event_TxtProductoKeyTyped

    boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       iscerrando=true;
    }//GEN-LAST:event_formWindowClosing

    private void TblTransferenciaAlmacenDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblTransferenciaAlmacenDetalleKeyReleased

        if(TblTransferenciaAlmacenDetalle.getSelectedColumn()==oCLTransferenciaAlmacen.colPrecio&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblTransferenciaAlmacenDetalle.getSelectedRow()+1==TblTransferenciaAlmacenDetalle.getRowCount()){
                int fila=TblTransferenciaAlmacenDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    agregarNuevaFila(fila);
                    TblTransferenciaAlmacenDetalle.requestFocus();
                    TblTransferenciaAlmacenDetalle.changeSelection(fila, oCLTransferenciaAlmacen.colProducto, false, false);
                }else{
                    TblTransferenciaAlmacenDetalle.requestFocus();
                    TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if(colValidada!=-1){

            TblTransferenciaAlmacenDetalle.requestFocus();
            TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }



    }//GEN-LAST:event_TblTransferenciaAlmacenDetalleKeyReleased

    private void TblTransferenciaAlmacenDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblTransferenciaAlmacenDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblTransferenciaAlmacenDetalle.getSelectedColumn()==oCLTransferenciaAlmacen.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
             

                evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

                
                if(TblTransferenciaAlmacenDetalle.getSelectedColumn()==oCLTransferenciaAlmacen.colCantidad){
                    TblTransferenciaAlmacenDetalle.changeSelection(TblTransferenciaAlmacenDetalle.getSelectedRow(), oCLTransferenciaAlmacen.colPrecio-1, false, false);
                 }
        }


    }//GEN-LAST:event_TblTransferenciaAlmacenDetalleKeyPressed

    private void verficarTextoVacio(int fila){
       int col =TblTransferenciaAlmacenDetalle.getSelectedColumn();
                if(fila!=-1)
                   {
                   if(TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colProducto)!=null){
                       String producto=TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colCodigo)!=null){

                               TblTransferenciaAlmacenDetalle.setValueAt(((CETransferenciaAlmacenDetalle)TblTransferenciaAlmacenDetalle.getValueAt(fila, oCLTransferenciaAlmacen.colCodigo)).getProducto(),fila, oCLTransferenciaAlmacen.colProducto);
                           }

                       }
                    }
                }
    }
    private void TblTransferenciaAlmacenDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblTransferenciaAlmacenDetallePropertyChange
        if(!iscerrando){
            if(cont==1){

                int fila=TblTransferenciaAlmacenDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if(fila!=-1){
                    verificarDatos(fila);}
                cont=0;
            }else{
                cont=0;
            }
            cont++;
        }
}//GEN-LAST:event_TblTransferenciaAlmacenDetallePropertyChange

    private void TblTransferenciaAlmacenDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblTransferenciaAlmacenDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {

            }
}//GEN-LAST:event_TblTransferenciaAlmacenDetalleMousePressed

    private void TblTransferenciaAlmacenDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblTransferenciaAlmacenDetalleMouseClicked

    }//GEN-LAST:event_TblTransferenciaAlmacenDetalleMouseClicked

    private void TxtNumTransfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumTransfKeyPressed

          if(evt.getKeyCode()==evt.VK_ENTER) {
              eventoBuscar();
          }
    }//GEN-LAST:event_TxtNumTransfKeyPressed

private void eventoBuscar()
 {

            
        

}
    private void btnBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPedidoActionPerformed
        DlgBusquedaTransferenciaAlmacen odialogo=new DlgBusquedaTransferenciaAlmacen(oparent, true);
        odialogo.setVisible(true);
        CETransferenciaAlmacen oCETransferenciaAlmacen= odialogo.getCETransferenciaAlmacen();

        if(oCETransferenciaAlmacen!=null) {

            SetTransferenciaAlmacen(CCTransferenciaAlmacen.ConsultarPorId(oCETransferenciaAlmacen.getIdTransferenciaAlmacen()));
        }
    }//GEN-LAST:event_btnBuscarPedidoActionPerformed
   private boolean realizarEventoCbxOrigen;
    private void CbxOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxOrigenActionPerformed
        if(realizarEventoCbxOrigen){
        oCLTransferenciaAlmacen.limpiarTabla();
        agregarNuevaFila(0);
        TblTransferenciaAlmacenDetalle.requestFocus();

        }
    }//GEN-LAST:event_CbxOrigenActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblTransferenciaAlmacenDetalle,
                new String[]{LblEtiqOrigen.getText(),((CEAlmacen)CbxOrigen.getSelectedItem()).getDescripcion(),
                             LblEtiqDestino.getText(),((CEAlmacen)CbxDestino.getSelectedItem()).getDescripcion(),
                             lblEtiqNumeroTransf.getText(),TxtNumTransf.getText(),LblEtiqEstado.getText(),TxtEstado.getText(),                                                          
                             LblEtiqSucursal.getText(),TxtSucursal.getText(),
                             LblEtiqUsuario.getText(),TxtVendedor.getText(),LblEtiqHoraRegistro.getText(),LblHoraRegistro1.getText()}
                ,new String[]{"Observacion",TxtObservacion.getText()},new String[]{},"Transferencia de Alamcén");
        oExportar.GuardarArchivoExcel(this);

    }//GEN-LAST:event_btnExportar1ActionPerformed


    private void agregarTransferenciaAlmacenDetalle(CEAlmacenProducto oCEAlmacenProducto)
    {
         int fila=TblTransferenciaAlmacenDetalle.getSelectedRow();
        if(oCEAlmacenProducto!=null){
       
        CETransferenciaAlmacenDetalle oCETransferenciaAlmacenDetalle= new CETransferenciaAlmacenDetalle();
        oCETransferenciaAlmacenDetalle.setProducto(oCEAlmacenProducto.getDescripcion());
        oCETransferenciaAlmacenDetalle.setIdProducto(oCEAlmacenProducto.getIdProducto());
       // oCETransferenciaAlmacenDetalle.setIdAlmacen(oCEAlmacenProducto.getIdAlmacen());
        oCETransferenciaAlmacenDetalle.setIdUnidadMedida(oCEAlmacenProducto.getIdUnidadBase());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEAlmacenProducto.getUMP());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCETransferenciaAlmacenDetalle.setCodigoProducto(oCEAlmacenProducto.getCodigo());


                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle,fila,oCLTransferenciaAlmacen.colCodigo);
                TblTransferenciaAlmacenDetalle.setValueAt(oCETransferenciaAlmacenDetalle.getProducto(),fila,oCLTransferenciaAlmacen.colProducto);
                TblTransferenciaAlmacenDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLTransferenciaAlmacen.colUnidadMedida);
                TblTransferenciaAlmacenDetalle.setValueAt(oCEAlmacenProducto.getStockReal(), fila, oCLTransferenciaAlmacen.colStock);
              //  TblTransferenciaAlmacenDetalle.setValueAt(oCEAlmacenProducto.getCostoUnitario(), fila, oCLTransferenciaAlmacen.colPrecio);
//                if(precio>0){
//                    verificarDatos(fila);
//                }

        }
        
    }
    private void limpiarFormulario()
    {

//      LblEstado.setText("EMITIDO");
      TxtObservacion.setText("");
      TxtNumTransf.setText("");
      CbxDestino.setSelectedIndex(0);
      CbxOrigen.setSelectedIndex(0);
      jTextFieldNumber.setText("");
      TxtProducto.setText("");
      oCLTransferenciaAlmacen.limpiarTabla();
      agregarNuevaFila(0);
      TblTransferenciaAlmacenDetalle.requestFocus();
      TblTransferenciaAlmacenDetalle.changeSelection(0, oCLTransferenciaAlmacen.colProducto, false, false);
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxDestino;
    private ComboBox.ComboBox CbxOrigen;
    private Label.Label LblEtiqDestino;
    private Label.Label LblEtiqEstado;
    private Label.Label LblEtiqHoraRegistro;
    private Label.Label LblEtiqOrigen;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqUsuario;
    private Label.Label LblHoraRegistro1;
    private Label.Label LblHoraSistema;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblTransferenciaAlmacenDetalle;
    private Label.Label TxtEstado;
    private TextField.JTxtNinguno TxtNumTransf;
    private javax.swing.JTextArea TxtObservacion;
    private TextField.JTxtNinguno TxtProducto;
    private Label.Label TxtSucursal;
    private Label.Label TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarPedido;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnNuevo btnNuevo;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label lblEtiqNumeroTransf;
    // End of variables declaration//GEN-END:variables

    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        KeyStroke VerPrecios = KeyStroke.getKeyStroke(KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            iscerrando=true;
            dispose();return;
        }

         if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
             eventoGuardar();
            
              return;
        }

  
    }

}
