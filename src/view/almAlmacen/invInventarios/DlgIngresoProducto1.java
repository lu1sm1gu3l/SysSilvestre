package view.almAlmacen.invInventarios;



import controller.almAlmacen.CCAlmacen;
import controller.almAlmacen.CCGuiaRemisionRecibido;
import controller.almAlmacen.CCIngresoProducto;
import controller.almAlmacen.CCTipoIngresoSalida;
import controller.cmpCompra.CCComprobanteCompra;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCTipoComprobante;
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
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibido;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibidoDetalle;
import modelo.almAlmacen.entidad.CEIngresoProducto;
import modelo.almAlmacen.entidad.CEIngresoProductoDetalle;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmpCompra.entidad.CEComprobanteCompraDetalle;
import modelo.cmpCompra.entidad.CEComprobanteCompraMatriz;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CETipoComprobante;
import util.clases.almAlmacen.CLIngresoProducto;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeAreaText;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProductoCompra;
import view.cmrComercial.DlgGestionProveedor;

/**
 *
 * @author Luiggi
 */
public class DlgIngresoProducto1 extends javax.swing.JDialog implements ActionListener{

   private  long idProveedor=0;
   private  long IdIngresoProducto=0;
   private  long IdComprobanteCompra=0;
   private  int IdGuiaRemision=0;
   private  int IdEstado=0;
   private MiReloj hilo;
   private  CLIngresoProducto oCLIngresoProducto;
   private  int pAccionABM=0;
   private CLBotonesABM oclBotonesABM;
   private java.awt.Frame oparent;
   private boolean iscerrarDlg=false;
    /** Creates new form DlgGestionIngresoProducto */
    public DlgIngresoProducto1(java.awt.Frame parent, boolean modal,boolean iscerrar) {
        super(parent, modal);
        this.oparent=parent;
        initComponents();
        this.iscerrarDlg=iscerrar;
        this.setLocationRelativeTo(null);      
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtObservacion.setDocument(new VerificadorDeAreaText("String",250,TxtObservacion));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblIngresoProductoDetalle.setSurrendersFocusOnKeystroke(true);
        BtnAgregar.setEnabled(false);
        BtnQuitar.setEnabled(false);
        cont=0;
        habilitarControles(false);
        CbxAlmacen.setEnabled(false);
        LblMensaje.setText("");
        btnEditar.setVisible(false);
    }

    private ArrayList<CETipoIngresoSalida> listCETipoIngresoSalida =null;
    private ArrayList<CETipoIngresoSalida> listCETipoIngresoSalidaTodo =null;

    private void cargarUtilidades()
    {
       
        oCLIngresoProducto = new CLIngresoProducto(TblIngresoProductoDetalle);
        listCETipoIngresoSalida=CCTipoIngresoSalida.consultarListaTipoIngresosProductos();
        listCETipoIngresoSalidaTodo=CCTipoIngresoSalida.consultarTodoTipoIngresosProductos();
        CbxTipoIngreso.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
        CbxAlmacen.setModel(CLComboBox.cargarComboAlmacenConPivot(CCAlmacen.listarAlmacenPorSucursal(FrmSistemaMenu.getIdSucursal())));
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaComprobanteInvantarios()));
        TblIngresoProductoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
              
        TableColumn columnaCantidad = TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaPrecio = TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colPrecio);
        columnaPrecio.setCellEditor(new DefaultCellEditor(jTextFieldNumber));       
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        ((JTableX)TblIngresoProductoDetalle).setSelectAllForEdit(true);
        eventoNuevo();
        alinearColumnas();


    }

    private void alinearColumnas()
    {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colCantidad).setCellRenderer(tcr);
        TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colPrecio).setCellRenderer(tcr);
        TblIngresoProductoDetalle.getColumnModel().getColumn(oCLIngresoProducto.colImporte).setCellRenderer(tcr);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        GrRbtnNumdoc = new javax.swing.ButtonGroup();
        GrRbtnNumSalida = new javax.swing.ButtonGroup();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqProveedor = new Label.Label();
        lblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        lblEtiqRuc = new Label.Label();
        lblEtiqDireccion = new Label.Label();
        LblHoraRegistro = new Label.Label();
        LblEtiqHoraRegistro = new Label.Label();
        lblEtiqVendedor = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        TxtNumComp = new TextField.JTxtNinguno();
        LblEtiqNumComp = new Label.Label();
        LblEtiqTipoComp = new Label.Label();
        CbxTipoComprobante = new ComboBox.ComboBox();
        lblEtiqAlmacen = new Label.Label();
        CbxAlmacen = new ComboBox.ComboBox();
        TxtProveedor = new TextField.JTxtLetra();
        jPanel3 = new javax.swing.JPanel();
        btnBuscarCompComp = new BotonesABM.BtnBuscar();
        LblEtiqTipoIngreso = new Label.Label();
        CbxTipoIngreso = new ComboBox.ComboBox();
        btnImportCompCompra = new BotonesABM.BtnBuscar();
        btnImportCompCompra1 = new BotonesABM.BtnBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblIngresoProductoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLIngresoProducto.colCantidad)
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
                }
            }

        };
        label20 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtNumIngreso = new Label.Label();
        lblEtiqNumIngreso = new Label.Label();
        txtEstado = new Label.Label();
        lblEtiqEstado = new Label.Label();
        LblMensaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new BotonesABM.BtnNuevo();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnExportar1 = new BotonesABM.BtnExportar();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnEditar = new BotonesABM.BtnEditar();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();

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
        setTitle("Ingresos de Almacén");
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

        LblEtiqProveedor.setText("Proovedor:");

        lblEtiqSucursal.setText("Sucursal   :");

        TxtVendedor.setText("NINGUNO");
        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        lblEtiqRuc.setText("RUC       :");

        lblEtiqDireccion.setText("Direccion  :");

        LblHoraRegistro.setText("00/00/00 00:00:00");
        LblHoraRegistro.setFont(new java.awt.Font("Verdana", 0, 11));
        LblHoraRegistro.setText("");

        LblEtiqHoraRegistro.setText("Fecha Reg:");

        lblEtiqVendedor.setText("Usuario    :");

        TxtSucursal.setText("NINGUNO");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtDireccion.setText("NINGUNO");

        TxtDNI.setText("NINGUNO  ");

        TxtNumComp.setText("2012-123456");
        TxtNumComp.setTamanio(12);

        LblEtiqNumComp.setText("Nº Compr.:");

        LblEtiqTipoComp.setText("Comprob  :");

        CbxTipoComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoComprobanteItemStateChanged(evt);
            }
        });
        CbxTipoComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoComprobanteActionPerformed(evt);
            }
        });

        lblEtiqAlmacen.setText("Almacen   :");

        CbxAlmacen.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxAlmacenMouseClicked(evt);
            }
        });

        TxtProveedor.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProveedor.setTamanio(150);
        TxtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProveedorMouseClicked(evt);
            }
        });
        TxtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtProveedorActionPerformed(evt);
            }
        });
        TxtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProveedorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lblEtiqRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(LblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblEtiqNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEtiqAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDatos2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqRuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscarCompComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarCompComp.setText("Buscar Ingreso");
        btnBuscarCompComp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCompComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompCompActionPerformed(evt);
            }
        });

        LblEtiqTipoIngreso.setText("Tipo Ingreso:");
        LblEtiqTipoIngreso.setFont(new java.awt.Font("Verdana", 1, 12));

        CbxTipoIngreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoIngresoItemStateChanged(evt);
            }
        });
        CbxTipoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoIngresoActionPerformed(evt);
            }
        });

        btnImportCompCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportCompCompra.setText("Importar Guia Remisión");
        btnImportCompCompra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportCompCompra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportCompCompra.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportCompCompra.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportCompCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportCompCompraActionPerformed(evt);
            }
        });

        btnImportCompCompra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportCompCompra1.setText("Importar Comp. Compra");
        btnImportCompCompra1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportCompCompra1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportCompCompra1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportCompCompra1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportCompCompra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportCompCompra1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblEtiqTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportCompCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImportCompCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbxTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImportCompCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(btnImportCompCompra1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblIngresoProductoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblIngresoProductoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Integer(1), null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Cant. Compra", "Cantidad", "U. M. ", "Costo", "Importe", "Almacen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblIngresoProductoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblIngresoProductoDetalle.setColumnSelectionAllowed(true);
        TblIngresoProductoDetalle.setRowHeight(21);
        TblIngresoProductoDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblIngresoProductoDetalle.getTableHeader().setReorderingAllowed(false);
        TblIngresoProductoDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblIngresoProductoDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblIngresoProductoDetalleMousePressed(evt);
            }
        });
        TblIngresoProductoDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblIngresoProductoDetallePropertyChange(evt);
            }
        });
        TblIngresoProductoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblIngresoProductoDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblIngresoProductoDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblIngresoProductoDetalle);
        TblIngresoProductoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        columnButton1.setTbl(TblIngresoProductoDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        jLabel6.setBackground(new java.awt.Color(255, 204, 255));
        jLabel6.setText("  ");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fila No Editable");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("  ");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fila Editable");

        TxtNumIngreso.setText("-------");
        TxtNumIngreso.setFont(new java.awt.Font("Verdana", 1, 11));

        lblEtiqNumIngreso.setText("Nº Ingreso :");
        lblEtiqNumIngreso.setFont(new java.awt.Font("Verdana", 1, 11));

        txtEstado.setText("EMITIDO");
        txtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        lblEtiqEstado.setText("Estado     :");

        LblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11));
        LblMensaje.setForeground(new java.awt.Color(204, 0, 0));
        LblMensaje.setText("AVISO : Al grabar, se tomara el costo unitario actual de cada producto de acuerdo al sistema.");

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

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Observación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 11))); // NOI18N

        TxtObservacion.setColumns(20);
        TxtObservacion.setFont(new java.awt.Font("Verdana", 0, 12));
        TxtObservacion.setRows(5);
        jScrollPane2.setViewportView(TxtObservacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304)
                .addComponent(lblEtiqNumIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(TxtNumIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(LblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqNumIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(LblMensaje)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarProveedor()
    {
        if(TxtProveedor.isEnabled()){
          CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
          if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7){
                  DlgGestionProveedor odialogo= new DlgGestionProveedor(null, true,1,1,1);//consultar

                  odialogo.setCajaTexo(TxtProveedor.getText());
                  odialogo.setVisible(true);
                  odialogo.setTitle("Busqueda de Proveedor");
                          if(odialogo.getoCEProveedor()!=null){
                            idProveedor=odialogo.getoCEProveedor().getIdProveedor();
                            TxtProveedor.setText(odialogo.getoCEProveedor().getRazonSocial());
                            TxtDNI.setText(odialogo.getoCEProveedor().getRUC());
                            TxtDireccion.setText(odialogo.getoCEProveedor().getDireccion());
                            TblIngresoProductoDetalle.requestFocus();
                            TblIngresoProductoDetalle.changeSelection(0,oCLIngresoProducto.colProducto, false, false);
                            }
                }
        }
      }
private void habilitarControles(boolean bol)
    {
    TxtProveedor.setEnabled(bol);
    TblIngresoProductoDetalle.setEnabled(bol);
    TxtProducto.setEnabled(bol);
    CbxTipoComprobante.setEnabled(bol);
    CbxAlmacen.setEnabled(bol);
}

private int ValidarRegistro(int fila)
    {
     if(TblIngresoProductoDetalle.getRowCount()>0){
    
     int numColumna=0;
        CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(fila-1, oCLIngresoProducto.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCEIngresoProductoDetalle!=null){
            if(TblIngresoProductoDetalle.getValueAt(fila-1, oCLIngresoProducto.colCantidad)!=null)
            {
                float cantidad=VerificadorDeTxt.convertFloat(TblIngresoProductoDetalle.getValueAt(fila-1, oCLIngresoProducto.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLIngresoProducto.colCantidad;
                        colValidada=oCLIngresoProducto.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
//                    float precio=0;
//                        if(TblIngresoProductoDetalle.getValueAt(fila-1, oCLIngresoProducto.colPrecio)!=null)
//                        {
//                        precio=Float.parseFloat(TblIngresoProductoDetalle.getValueAt(fila-1, oCLIngresoProducto.colPrecio).toString());
//                        }
//                        if(precio==0){
//                        numColumna = oCLIngresoProducto.colPrecio;
//                        colValidada = oCLIngresoProducto.colPrecio;
//                        mensaje="Ingrese el costo";
//                        }
                }
            }else{
                        numColumna = oCLIngresoProducto.colCantidad;
                        colValidada = oCLIngresoProducto.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLIngresoProducto.colProducto;
            colValidada=oCLIngresoProducto.colProducto;

        }
        if(numColumna!=0){
//            if(numColumna!=oCLIngresoProducto.colPrecio){
            JOptionPane.showMessageDialog(null,mensaje);
//            }else
//                { int filat=TblIngresoProductoDetalle.getSelectedRow();
//                  oCLIngresoProducto.calcularPrecio(filat);
//                }
            }

        return numColumna;
        
        }
    return 0;
}
    private void BuscarProducto()
    {
       // if(!isNuevoConDoc){
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()!=2){
        DlgBusquedaProductoCompra odialogo= new DlgBusquedaProductoCompra(oparent, true,0,0);
                String NombreProd=TxtProducto.getText().trim();
                btnGuardar.requestFocus();
                if(NombreProd.equals(""))
                {
               if(TblIngresoProductoDetalle.getValueAt(TblIngresoProductoDetalle.getSelectedRow(), oCLIngresoProducto.colProducto)!=null)
                    {
                    NombreProd=TblIngresoProductoDetalle.getValueAt(TblIngresoProductoDetalle.getSelectedRow(), oCLIngresoProducto.colProducto)+"";
                    }
                }
               odialogo.setCajaTexto(NombreProd);
               if(pAccionABM==2)
               {
                   odialogo.deshabilitarCaja();
               }
               TxtProducto.setText("");
               odialogo.setVisible(true);
               CEProducto oCEAlmacenProducto=odialogo.getProducto();
               if(!odialogo.getisagregarCantidadOrdenCompra()){
               agregarIngresoProductoDetalle(oCEAlmacenProducto,odialogo.getListaPrecio());
               
               int fila=TblIngresoProductoDetalle.getSelectedRow();
               TblIngresoProductoDetalle.requestFocus();
               TblIngresoProductoDetalle.changeSelection(fila, oCLIngresoProducto.colCantidad, false, false);

                }else{
                   TblIngresoProductoDetalle.requestFocus();
                   SeleccionarIngresoProductoExistente(oCEAlmacenProducto);
                }
        }

    }
    private void SeleccionarIngresoProductoExistente(CEProducto oCEAlmacenProducto)
    {
        CEIngresoProductoDetalle oCEIngresoProductoDetalle ;
        for( int i =0;i<TblIngresoProductoDetalle.getRowCount();i++)
            {
//cx
                oCEIngresoProductoDetalle= (CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);
                if(oCEIngresoProductoDetalle!=null){
                    
                        if(
                           oCEIngresoProductoDetalle.getIdUnidadMedida()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCEIngresoProductoDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblIngresoProductoDetalle.requestFocus();
                            TblIngresoProductoDetalle.changeSelection(i, oCLIngresoProducto.colUnidadMedida, false, false);
                        }

                }
            }
    }
 

    private boolean  ValidarTablaIngresoProducto()
     {


            for(int i=0; i<TblIngresoProductoDetalle.getRowCount();i++)
            {
                verificarDatos(i,0);

                if(colValidada!=-1){
                    TblIngresoProductoDetalle.requestFocus();
                    TblIngresoProductoDetalle.changeSelection(i, colValidada, false, false);
                    colValidada=-1;
                    return false;
                }

            }
            eventoGuardar=false;

        return true;
    }
    boolean eventoGuardar=false;
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      eventoGuardarIngreso();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void eventoGuardarIngreso()
    {
        if(btnGuardar.isEnabled()){
        int fil=TblIngresoProductoDetalle.getRowCount();
      if(fil>0){
              int row=fil-1;
              CEIngresoProductoDetalle oCEIngreoDetalle= (CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(row, oCLIngresoProducto.colCodigo);
              if(oCEIngreoDetalle==null){
                oCLIngresoProducto.QuitarFilaSel(row);
              }
        }
      fil=TblIngresoProductoDetalle.getRowCount();
      if(fil>0){
        eventoGuardar=true;
      int columnaValidada=ValidarRegistro(TblIngresoProductoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          EventoGuardar();
      }else
      {
          TblIngresoProductoDetalle.requestFocus();
          TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getRowCount()-1, columnaValidada, false, false);
      }

      }
        else
        {            
                 CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
                 if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2){

                       JOptionPane.showMessageDialog(null,"Importe un compra","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                       btnImportCompCompra.requestFocus();

                }else{
                 JOptionPane.showMessageDialog(null,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 agregarNuevaFila(0);
                 TblIngresoProductoDetalle.requestFocus();
                 TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colProducto, false, false);
                }


          
        }
      }
    }
    private boolean validar()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
          if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8)
          {
              idProveedor=1;
          }

        int cantidadCero=0;
        for(int i=0; i<TblIngresoProductoDetalle.getRowCount();i++)
            {
                float cantidad=VerificadorDeTxt.convertFloat(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCantidad));
                if(cantidad==0)
                {
                    cantidadCero++;
                }


            }
        if(cantidadCero==TblIngresoProductoDetalle.getRowCount())
                {
                   JOptionPane.showMessageDialog(this,"Para Guardar el Ingreso al menos un registro debe de tener la cantidad mayor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                   return false;
                }

         if(TblIngresoProductoDetalle.getRowCount()==0)
        {

             if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2){
                    JOptionPane.showMessageDialog(this,"Importe una Compra","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    btnImportCompCompra.requestFocus();
             }
             else{
                 JOptionPane.showMessageDialog(this,"Ingrese un Producto","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 agregarNuevaFila(0);
                 TblIngresoProductoDetalle.requestFocus();
                 TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colProducto, false, false);

                }
             return false;
            
        } 
       
        CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();
        if(oCEAlmacen.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(this,"Seleccione un Almacen","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             CbxAlmacen.requestFocus();
             return false;
        }
        
         if(idProveedor==0)
        {            
             if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7){
                if(idProveedor==0)
                {
                    idProveedor=1;
                    int res = JOptionPane.showConfirmDialog(this, "Desea Guardar la Entrada Con un Proveedor", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                        {
                          buscarProveedor();
                        }
                }
             }
             else{
             JOptionPane.showMessageDialog(this,"Ingrese un Proveedor","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtProveedor.requestFocus();
             return false;
             }
        }
        int resul=JOptionPane.showConfirmDialog(this,"<html><h2><BR> ESTÁ SEGURO DE GUARDAR EL INGRESO EN EL "+oCEAlmacen.getDescripcion()+"</h2></html>" , "",JOptionPane.YES_NO_OPTION);
        if (resul==JOptionPane.NO_OPTION) {
                   return false;
        }
        if(!validarBD())
        {
            return false;
        }
               
       
        return true;
    }

    private boolean validarBD()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();

        if(pAccionABM==1&&oCETipoIngresoSalida.getIdTipoIngresoSalida()==2){
        CEEstado oCEEstadoInv=CCEstado.consultarUltimoestadoCompra_inventario(IdComprobanteCompra);
            if(oCEEstadoInv.getmIntIdEstado()==8)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInv.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        CEEstado oCEEstadoCont=CCEstado.consultarUltimoestadoCompra_contable(IdComprobanteCompra);
            if(oCEEstadoCont.getmIntIdEstado()==4)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoCont.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }

        if(pAccionABM==2){
        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoIngreso(IdIngresoProducto);
            if(oCEEstadoInvent.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }
        String Mensaje="";
        if(pAccionABM==1){
         CargarSaldoCantidadesDesdeCompra();
        }else if(pAccionABM==2)
        {
         CargarSaldoCantidades();
         Mensaje=VerificarCambiosEnIngresos();
        }
        if(!Mensaje.equals(""))
        {
            repaint();
            JOptionPane.showMessageDialog(this,"Los siguientes productos, no se pueden actualizar \n"+Mensaje,"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!ValidarTablaIngresoProducto())
        {
            return false;
        }
        return true;
    }
    int IdEstadoComprobanteCompra;

    private String InsertarIngreso(CEIngresoProducto oCEIngresoProducto)
    {
        if(oCEIngresoProducto.getIdTipoIngresoSalida()==2&&oCEIngresoProducto.getIdTipoComprobante()==8)
        {
           return  CCIngresoProducto.InsIngresoProductoPorGuiaRem(this.getIngresoProducto());
        }
        return  CCIngresoProducto.InsIngresoProducto(this.getIngresoProducto());
    }
    private void EventoGuardar()
    {
        
        if(ValidarTablaIngresoProducto()){
        if(validar()){
                    if(pAccionABM==1){
                        String CodigoIngresoProducto=InsertarIngreso(this.getIngresoProducto());
                        if(CodigoIngresoProducto!=null)
                        {
                        JOptionPane.showMessageDialog(this,"<html>El código de IngresoProducto es: "+CodigoIngresoProducto+"</html>");
                        lstIngresoDetEliminar= new ArrayList<CEIngresoProductoDetalle>();
                        limpiarFormulario();
                        CbxTipoComprobante.setSelectedIndex(0);
                        LblHoraRegistro.setText("");
                        TblIngresoProductoDetalle.requestFocus();
                        TblIngresoProductoDetalle.changeSelection(0,oCLIngresoProducto.colProducto, false, false);
                        eventoNuevo();
                        oCLIngresoProducto.limpiarTabla();
                        CbxTipoIngreso.setSelectedIndex(0);
                        CbxAlmacen.setSelectedIndex(0);
                        TxtObservacion.setText("");
                        if(iscerrarDlg)
                            {
                             this.dispose();
                            }
                        }
                        else{

                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
                    }else{
                        
                        int pIdTipoIngreso=oCEIngresoProductoGeneral.getIdTipoIngresoSalida();
                                if(pIdTipoIngreso!=3&&pIdTipoIngreso!=4&&pIdTipoIngreso!=5)
                                {

                                    int iscorrecto=CCIngresoProducto.UPDIngresoProducto(this.getIngresoProducto());
                                    if(iscorrecto==1)
                                    {
                                    lstIngresoDetEliminar= new ArrayList<CEIngresoProductoDetalle>();
                                    JOptionPane.showMessageDialog(this,"<html><CENTER>Operación exitosa</CENTER></html>");
                                    limpiarFormulario();
                                    eventoNuevo();
                                    CbxTipoIngreso.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
                                    CbxTipoIngreso.setSelectedIndex(0);
                                    oCLIngresoProducto.limpiarTabla();
                                    TxtObservacion.setText("");
                                    }
                                else{
                                    JOptionPane.showMessageDialog(null,"Operación Fallida");
                                    }
                            }
                            else {
                            JOptionPane.showMessageDialog(this,"El Tipo de Ingreso "+oCEIngresoProductoGeneral.getTipoIngreso()+" No se puede Editar");
                            }
               }
      }
     }
    
 }

    private CEIngresoProducto getIngresoProducto(){
        CEIngresoProducto oCEIngresoProducto= new CEIngresoProducto();
        IdEstadoComprobanteCompra=9;
        oCEIngresoProducto.setIdIngresoProducto(IdIngresoProducto);
        oCEIngresoProducto.setIdAlmacen(((CEAlmacen)CbxAlmacen.getSelectedItem()).getIdAlmacen());
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        oCEIngresoProducto.setIdTipoIngresoSalida(oCETipoIngresoSalida.getIdTipoIngresoSalida());
        oCEIngresoProducto.setNumeroDocumento(TxtNumComp.getText());
        oCEIngresoProducto.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEIngresoProducto.setIdTipoComprobante(((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getIdTipoComprobante());
        oCEIngresoProducto.setIdComprobanteCompra(IdComprobanteCompra);
        oCEIngresoProducto.setIdProveedor(idProveedor);
        oCEIngresoProducto.setProveedor(TxtProveedor.getText());
        oCEIngresoProducto.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEIngresoProducto.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEIngresoProducto.setObservacion(TxtObservacion.getText());
        oCEIngresoProducto.setIdGuiaRemision(IdGuiaRemision);
        List<CEIngresoProductoDetalle> lstIngresoProductoDetalle = new ArrayList<CEIngresoProductoDetalle>();
        CEIngresoProductoDetalle oCEIngresoProductoDetalle ;

        int cantidadRecepcionada=0;
        for( int i =0;i<TblIngresoProductoDetalle.getRowCount();i++)
            {

                oCEIngresoProductoDetalle= (CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);
                boolean guardar=VerificadorDeTxt.convertBoolean(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colIsEdit));
                if(guardar)
                {
                    if(oCEIngresoProductoDetalle!=null){

                    float cantidad=VerificadorDeTxt.convertFloat(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCantidad));
                    if(cantidad!=0||pAccionABM==2){
                        float Saldocantidad=VerificadorDeTxt.convertFloat(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCantidadOC));
                        if(cantidad==Saldocantidad)
                        {
                            cantidadRecepcionada++;
                        }
                        oCEIngresoProductoDetalle.setCosto(VerificadorDeTxt.convertFloat(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colPrecio)));
                        oCEIngresoProductoDetalle.setCantidad(cantidad);
                        oCEIngresoProductoDetalle.setImporte(Float.parseFloat(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colImporte).toString()));
                        oCEIngresoProductoDetalle.setIdAlmacen(oCEIngresoProducto.getIdAlmacen());
                        lstIngresoProductoDetalle.add(oCEIngresoProductoDetalle);
                        }
                    }
               }
                
            }
        if(pAccionABM==2){
        lstIngresoProductoDetalle.addAll(lstIngresoDetEliminar);
        }
        if(cantidadRecepcionada==TblIngresoProductoDetalle.getRowCount())
                        {
                            IdEstadoComprobanteCompra=8;
                        }

            oCEIngresoProducto.setLstIngresoDetalle(lstIngresoProductoDetalle);
            oCEIngresoProducto.setIdEstadoOC(IdEstadoComprobanteCompra);


            return oCEIngresoProducto;
}
    private void agregarNuevaFila(int fila)
    {
      
         Vector oVector = new Vector();

                ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);
             

                    TblIngresoProductoDetalle.requestFocus();
                   
        
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{
      int columnaValidada=ValidarRegistro(TblIngresoProductoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          int row=TblIngresoProductoDetalle.getRowCount();
          agregarNuevaFila(row);
          TblIngresoProductoDetalle.requestFocus();
          TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getRowCount()-1, oCLIngresoProducto.colProducto, false, false);


      }else
      {
          TblIngresoProductoDetalle.requestFocus();
          TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getRowCount()-1, columnaValidada, false, false);
      }
}

   List<CEIngresoProductoDetalle> lstIngresoDetEliminar= new ArrayList<CEIngresoProductoDetalle>();
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        if(pAccionABM==2)
        {
        CEIngresoProductoDetalle oCEIngresoProductoDetalle= (CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(TblIngresoProductoDetalle.getSelectedRow(), oCLIngresoProducto.colCodigo);
            if(oCEIngresoProductoDetalle!=null&&oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=0)
            {
                oCEIngresoProductoDetalle.setCantidad(0);
                lstIngresoDetEliminar.add(oCEIngresoProductoDetalle);
            }
        }

        oCLIngresoProducto.QuitarFila();
    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      
eventoCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void eventoCancelar()
    {
      if(pAccionABM==1||pAccionABM==2)
       {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            LblHoraRegistro.setText("");
            pAccionABM=0;
            btnImportCompCompra.setEnabled(false);
            limpiarFormulario();
            CbxTipoIngreso.setSelectedIndex(0);
            TxtNumIngreso.setText("");
            TxtNumComp.setText("");
            CbxTipoIngreso.removeAllItems();
            CbxTipoIngreso.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
            CbxTipoIngreso.setEnabled(false);
            TxtObservacion.setEnabled(false);
            TxtObservacion.setText("");

       }else{
           iscerrando=true;
             dispose();
            }

    }
    private boolean realizarevento=true;
    private void CbxTipoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoIngresoActionPerformed
       
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipoIngresoSalida!=null){
        if(realizarevento){
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7)//otros 
        {
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        limpiarFormulario();
        eventoNuevo();
        if(TblIngresoProductoDetalle.getRowCount()==0){
        agregarNuevaFila(0);
            }
        CbxTipoComprobante.setSelectedIndex(0);
        TblIngresoProductoDetalle.setEnabled(true);
        oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colProducto,oCLIngresoProducto.colCantidad,oCLIngresoProducto.colPrecio});
        TxtProducto.setEnabled(true);
        TblIngresoProductoDetalle.requestFocus();
        TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colProducto, false, false);
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        btnImportCompCompra.setEnabled(false);
        TxtNumComp.setEnabled(true);
        TxtProveedor.setEnabled(true);
        CbxTipoComprobante.setEnabled(true);
        CbxAlmacen.setEnabled(true);
        }
        else if(oCETipoIngresoSalida.getIdTipoIngresoSalida() == 8)//ajuste
        {
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        limpiarFormulario();
        TxtDNI.setText("");
        TxtProveedor.setText("");
        TxtDireccion.setText("");
        idProveedor=1;
        eventoNuevo();
        if(TblIngresoProductoDetalle.getRowCount()==0){
        agregarNuevaFila(0);
            }
        TblIngresoProductoDetalle.setEnabled(true);
        oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colProducto,oCLIngresoProducto.colCantidad});
        TxtProducto.setEnabled(true);
        TblIngresoProductoDetalle.requestFocus();
        TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colProducto, false, false);
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        btnImportCompCompra.setEnabled(false);
        CbxTipoComprobante.setSelectedIndex(6);
        TxtNumComp.setEnabled(false);
        TxtProveedor.setEnabled(false);
        CbxTipoComprobante.setEnabled(false);
        CbxAlmacen.setEnabled(true);
        }
        else
        {
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2||oCETipoIngresoSalida.getIdTipoIngresoSalida()==6)
        {
            
            CbxTipoComprobante.setEnabled(false);
            habilitarControles(false);
            btnImportCompCompra.setEnabled(true);
            TxtNumComp.setEditable(false);
            CbxAlmacen.setEnabled(false);
            
            idProveedor=0;
         }

        BtnAgregar.setEnabled(false);
        BtnQuitar.setEnabled(false);
        limpiarFormulario();
        }
      }
        
      }
      
    }//GEN-LAST:event_CbxTipoIngresoActionPerformed

    private void CbxTipoIngresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoIngresoItemStateChanged

        LblMensaje.setText("");
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipoIngresoSalida!=null){
         if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8)//otros
            {
                LblMensaje.setText("AVISO : Al grabar, se tomara el costo unitario actual de cada producto deacuerdo al sistema.");
            }
        }
        
    }//GEN-LAST:event_CbxTipoIngresoItemStateChanged

    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila,float cantidad){
         try{
           if(TblIngresoProductoDetalle.getRowCount()>0)
           {
            colValidada=-1;            
            float cantidadOC=1;
            float precio=1;
            CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
            if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidad)!=null){
            cantidad=Float.parseFloat(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidad).toString());}

            if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidadOC)!=null){
            cantidadOC=Float.parseFloat(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidadOC).toString());}

            if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colPrecio)!=null){
                precio=Float.parseFloat(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colPrecio).toString());
            }

            if(precio<0)
            {
                JOptionPane.showMessageDialog(null,"el costo debe ser mayor a cero");
                TblIngresoProductoDetalle.setValueAt(null,fila,oCLIngresoProducto.colPrecio);
                colValidada=oCLIngresoProducto.colPrecio;
                 return;
            }
            if(isNuevoConDoc){
                if(cantidad>cantidadOC)
                {
                    
                    if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2||oCETipoIngresoSalida.getIdTipoIngresoSalida()==6){
                    JOptionPane.showMessageDialog(null,"la cantidad de ingreso no puede ser mayor a la cantidad de O.C.");
                    TblIngresoProductoDetalle.setValueAt(cantidadOC,fila,oCLIngresoProducto.colCantidad);
                    colValidada=oCLIngresoProducto.colCantidad;
                     return;
                    }
                }
             }


        if(cantidad<=0)
            {
                CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCodigo);
                if(oCETipoIngresoSalida.getIdTipoIngresoSalida()!=2){
                    if(oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()>0){
                    JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                    TblIngresoProductoDetalle.setValueAt(null,fila,oCLIngresoProducto.colCantidad);
                    colValidada=oCLIngresoProducto.colCantidad;
                    }else
                    {                        
                            JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                            TblIngresoProductoDetalle.setValueAt(null,fila,oCLIngresoProducto.colCantidad);
                            colValidada=oCLIngresoProducto.colCantidad;
                    }
                }
                 return;
            }



        int col=TblIngresoProductoDetalle.getSelectedColumn();
                if(!eventoGuardar){
                if(col==oCLIngresoProducto.colCantidad){
                    oCLIngresoProducto.calcularPrecio(fila);
                }
             }
        }
    }catch(Exception e)
    {
        cont++;
       System.out.println(e+"metodo Verificar datos -formulario ingreso");

    }
    }


private String Codigo;





    CEIngresoProducto oCEIngresoProductoGeneral=null;
    public void SetIngresoProducto(CEIngresoProducto oCEIngresoProducto)
    {
        CbxTipoIngreso.removeAllItems();
        CbxTipoIngreso.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalidaTodo));
        oCLIngresoProducto.limpiarTabla();
        isNuevoConDoc=true;
        if(oCEIngresoProducto!=null)
        {
            limpiar=false;
            oCEIngresoProductoGeneral=oCEIngresoProducto;
            txtEstado.setText(oCEIngresoProducto.getUltimoEstado());
            IdEstado=oCEIngresoProducto.getUltimoIdEstado();
            IdIngresoProducto=oCEIngresoProducto.getIdIngresoProducto();
            TxtDNI.setText(oCEIngresoProducto.getRUC());
            TxtDireccion.setText(oCEIngresoProducto.getDireccion());
            TxtNumComp.setText(oCEIngresoProducto.getNumeroDocumento());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEIngresoProducto.getSucursal());
            TxtVendedor.setText(oCEIngresoProducto.getEmpleado());
            LblHoraRegistro.setText(oCEIngresoProducto.getFecha());
            CbxTipoIngreso.setEnabled(false);
            TxtNumIngreso.setEnabled(false);            
            TxtNumIngreso.setText(oCEIngresoProducto.getNumeroIngreso());
            TxtObservacion.setText(oCEIngresoProducto.getObservacion());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEIngresoProducto.getIdTipoComprobante());
            CLCargarCombo.buscarIdAlmacen(CbxAlmacen, oCEIngresoProducto.getIdAlmacen());
            CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoIngreso,oCEIngresoProducto.getIdTipoIngresoSalida());
            if(oCEIngresoProducto.getLstIngresoDetalle()!=null)
            {
               for(int i=0; i<oCEIngresoProducto.getLstIngresoDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);
                
                CEIngresoProductoDetalle oCEIngresoProductoDetalle=oCEIngresoProducto.getLstIngresoDetalle().get(i) ;

                oCEIngresoProductoDetalle.setCantidadAnterior(oCEIngresoProductoDetalle.getCantidad());
                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEIngresoProductoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEIngresoProductoDetalle.getIdUnidadMedida());
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,i,oCLIngresoProducto.colCodigo);
                TxtProducto.setText(oCEIngresoProductoDetalle.getProducto());
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getProducto(),i,oCLIngresoProducto.colProducto);
                TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLIngresoProducto.colUnidadMedida);
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCosto(),i,oCLIngresoProducto.colPrecio);
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCantidad(),i,oCLIngresoProducto.colCantidad);
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getImporte(),i,oCLIngresoProducto.colImporte);
                TblIngresoProductoDetalle.setValueAt(true,i,oCLIngresoProducto.colIsEdit);
                }
            }

            idProveedor=oCEIngresoProducto.getIdProveedor();
            TxtProveedor.setText(oCEIngresoProducto.getProveedor());
            TblIngresoProductoDetalle.clearSelection();
            TblIngresoProductoDetalle.setEnabled(false);
            CbxAlmacen.setEnabled(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            TxtNumComp.setEnabled(false);
            TxtObservacion.setEnabled(false);
            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=2;
            limpiar=true;
            btnEditar.requestFocus();
        }
    else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
        }
    }
    
    private void TxtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProveedorMouseClicked

        if(evt.getClickCount()==2) {
            buscarProveedor();
        }
}//GEN-LAST:event_TxtProveedorMouseClicked

    private void TxtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedorKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarProveedor();
            
        }
}//GEN-LAST:event_TxtProveedorKeyPressed
private void eventoNuevo()
    {
       isNuevoConDoc=false;
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(false);
       BtnAgregar.setEnabled(false);
       BtnQuitar.setEnabled(false);
       CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
       if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7)
       {
         BtnAgregar.setEnabled(true);
         BtnQuitar.setEnabled(true);
       }
       if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8)
       {
           BtnAgregar.setEnabled(true);
           BtnQuitar.setEnabled(true);
       }
       btnImportCompCompra.setEnabled(true);
       TxtNumComp.setEditable(true);
       TxtObservacion.setEnabled(true);
       TblIngresoProductoDetalle.requestFocus();
       TblIngresoProductoDetalle.changeSelection(0,oCLIngresoProducto.colProducto, false, false);
       CbxAlmacen.setEnabled(true);
       oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colProducto,oCLIngresoProducto.colCantidad,oCLIngresoProducto.colPrecio});
       limpiarFormulario();
       CbxTipoIngreso.setEnabled(true);
}
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
    eventoNuevo();

   
    
    }//GEN-LAST:event_btnNuevoActionPerformed

    private boolean validarAnulacion()
    {
        if(!VerificarUltimosIngresoParaAnular())
        {
            JOptionPane.showMessageDialog(null,"<html>Operación Fallida, solo se puede realizar esta operación,<br>"
                                                + "si todos los productos registrados son los ultimos movimientos en el almacen<html>");
            oCLIngresoProducto.pintarTabla();
            return false;
        }
        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoIngreso(IdIngresoProducto);
            if(oCEEstadoInvent.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(null,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        
        return true;
    }
    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

         int pIdTipoIngreso=oCEIngresoProductoGeneral.getIdTipoIngresoSalida();
        if(pIdTipoIngreso!=3&&pIdTipoIngreso!=4&&pIdTipoIngreso!=5)
                {
                pAccionABM=4;
                oCEIngresoProductoGeneral.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
                oCEIngresoProductoGeneral.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
                DlgAnularIngresoProducto oDlgAnularIngresoProducto= new DlgAnularIngresoProducto(null,true , oCEIngresoProductoGeneral);
                oDlgAnularIngresoProducto.setLocationRelativeTo(null);
                oDlgAnularIngresoProducto.setVisible(true);
                oCEIngresoProductoGeneral.setObservacion(oDlgAnularIngresoProducto.getObservacion());
                if(oDlgAnularIngresoProducto.isAnulado()){

                       if(validarAnulacion())
                        {
                           if(CCIngresoProducto.AnularIngresoProducto(oCEIngresoProductoGeneral)==1)
                             {
                                limpiarFormulario();
                                pAccionABM=0;
                                habilitarControles(false);
                                oclBotonesABM.controlBoton(true, false, false, false, true, false);
                                JOptionPane.showMessageDialog(null,"<html>Operación exitosa");
                                btnNuevo.requestFocus();
                            }else
                           {
                                JOptionPane.showMessageDialog(null,"<html>Operación Fallida");
                           }
                       }

              }
          }
         else
         {
            JOptionPane.showMessageDialog(null,"El Tipo de Ingreso "+oCEIngresoProductoGeneral.getTipoIngreso()+" No se puede Anular");
        }
                
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

//        int pIdTipoIngreso=oCEIngresoProductoGeneral.getIdTipoIngresoSalida();
//        if(pIdTipoIngreso!=3&&pIdTipoIngreso!=4&&pIdTipoIngreso!=5)
//        {
//         oclBotonesABM.controlBoton(false, true, false, false, true, false);
//         habilitarControles(false);
//         TxtObservacion.setEnabled(true);
//         TblIngresoProductoDetalle.setEnabled(true);
//         CbxTipoIngreso.setEnabled(false);
//         pAccionABM=2;
//         oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colCantidad,oCLIngresoProducto.colPrecio});
//         VerificarUltimosIngreso();
//         CargarSaldoCantidades();
//         if(TblIngresoProductoDetalle.getRowCount()>0)
//         {
//             TblIngresoProductoDetalle.requestFocus();
//            TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colCantidad, false, false);
//         }
//         oCLIngresoProducto.pintarTabla();
//        }
//        else {
//            JOptionPane.showMessageDialog(this,"El Tipo de Ingreso "+oCEIngresoProductoGeneral.getTipoIngreso()+" No se puede Editar");
//        }
    }//GEN-LAST:event_btnEditarActionPerformed


    private Boolean VerificarAnulacion()
    {
        int NumAptos=0;
        int row=TblIngresoProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);

            CEIngresoProductoDetalle oCEIngresoProductoDetalleTemp=CCIngresoProducto.VerificarUltimoMovimiento(oCEIngresoProductoDetalle.getIdProducto(),
                                                                    oCEIngresoProductoDetalle.getIdAlmacen());

                if(oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=oCEIngresoProductoDetalleTemp.getIdIngresoProductoDetalle())
                {
                   NumAptos++;
                }
        }
        if(NumAptos==TblIngresoProductoDetalle.getRowCount())
        {
            return true;
        }
        return false;
    }

   
    private void VerificarUltimosIngreso()
    {
        int row=TblIngresoProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);

            CEIngresoProductoDetalle oCEIngresoProductoDetalleTemp=CCIngresoProducto.VerificarUltimoMovimiento(oCEIngresoProductoDetalle.getIdProducto(),
                                                                    oCEIngresoProductoDetalle.getIdAlmacen());
            if(oCEIngresoProductoDetalleTemp!=null){
            if(oCEIngresoProductoDetalleTemp.isIsSalida())
            {
                TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);
            }
            else if(oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=oCEIngresoProductoDetalleTemp.getIdIngresoProductoDetalle())
                {
                    TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);                    
                }
            
          }

        }
    }

    private boolean VerificarUltimosIngresoParaAnular()
    {
        int row=TblIngresoProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);

            CEIngresoProductoDetalle oCEIngresoProductoDetalleTemp=CCIngresoProducto.VerificarUltimoMovimiento(oCEIngresoProductoDetalle.getIdProducto(),
                                                                    oCEIngresoProductoDetalle.getIdAlmacen());
            if(oCEIngresoProductoDetalleTemp!=null){
            if(oCEIngresoProductoDetalleTemp.isIsSalida())
            {
                TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);
                return false;
            }
            else if(oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=oCEIngresoProductoDetalleTemp.getIdIngresoProductoDetalle())
                {
                    TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);
                    return false;
                }

          }

        }
        return true;
    }

    private String VerificarCambiosEnIngresos()
    {
        int row=TblIngresoProductoDetalle.getRowCount();
        String Mensaje="";
        for (int i = 0; i < row; i++)
        {
            CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);

            CEIngresoProductoDetalle oCEIngresoProductoDetalleTemp=CCIngresoProducto.VerificarUltimoMovimiento(oCEIngresoProductoDetalle.getIdProducto(),
                                                                    oCEIngresoProductoDetalle.getIdAlmacen());
            if(oCEIngresoProductoDetalleTemp!=null&&oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=0){
             boolean estadoAnte=VerificadorDeTxt.convertBoolean(TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colIsEdit));
            if(oCEIngresoProductoDetalleTemp.isIsSalida())
            {
                
                TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);
                if(estadoAnte)
                {
                  TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCantidadAnterior(), i, oCLIngresoProducto.colCantidad);
                  TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCosto(), i, oCLIngresoProducto.colPrecio);
                  Mensaje=Mensaje+oCEIngresoProductoDetalle.getProducto()+" - "+oCEIngresoProductoDetalle.getUnidadMedida()+"\n";
                }

            }
            else
            {
                if(oCEIngresoProductoDetalle.getIdIngresoProductoDetalle()!=oCEIngresoProductoDetalleTemp.getIdIngresoProductoDetalle())
                {
                    TblIngresoProductoDetalle.setValueAt(false, i, oCLIngresoProducto.colIsEdit);
                    if(estadoAnte)
                    {
                      TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCantidadAnterior(), i, oCLIngresoProducto.colCantidad);
                      TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getCosto(), i, oCLIngresoProducto.colPrecio);
                      Mensaje=Mensaje+oCEIngresoProductoDetalle.getProducto()+" - "+oCEIngresoProductoDetalle.getUnidadMedida()+"\n";
                    }
                }
            }
          }

        }
        return Mensaje;
    }

    private void CargarSaldoCantidades()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2||oCETipoIngresoSalida.getIdTipoIngresoSalida()==6){
        CEComprobanteCompraMatriz oCEComprobanteCompraMatriz=null;       
        oCEComprobanteCompraMatriz= CCComprobanteCompra.ListarComprobanteComprasPorIdIngreso(IdIngresoProducto);
        
        if(oCEComprobanteCompraMatriz!=null)
        {
        IdComprobanteCompra=oCEComprobanteCompraMatriz.getIdComprobanteCompra();
        int idIngresoProdDetall=0;
        float tipocambioTemp=oCEComprobanteCompraMatriz.getTipoCambio();
            List<CEComprobanteCompraDetalle> lstCEComprobanteCompraDetalle= oCEComprobanteCompraMatriz.getoLstComprobanteDetalle();
            if(lstCEComprobanteCompraDetalle!=null)
            {
            boolean existe=false;
            for (CEComprobanteCompraDetalle oCEComprobanteCompraDetalle : lstCEComprobanteCompraDetalle) {
                existe=false;
                int row=TblIngresoProductoDetalle.getRowCount();
                for (int i = 0; i < row; i++) {

                    CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);
                        
                            if(oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle()==oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle())
                            {
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad()+
                                                                    oCEIngresoProductoDetalle.getCantidad(), i, oCLIngresoProducto.colCantidadOC);
                                   existe=true;
                            }
                }

                if(!existe)
                {

                                if(oCEComprobanteCompraDetalle.getSaldoCantidad()!=0){
                                Vector oVector = new Vector();

                                ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);
                                int rowTemp=TblIngresoProductoDetalle.getRowCount()-1;
                                idIngresoProdDetall--;
                                CEIngresoProductoDetalle oCEIngresoProductoDetalle=new CEIngresoProductoDetalle();
                                //oCEIngresoProductoDetalle.setIdAlmacen(oCEComprobanteCompraDetalle.getIdAlmacen());
                                oCEIngresoProductoDetalle.setIdProducto(oCEComprobanteCompraDetalle.getIdProducto());
                                oCEIngresoProductoDetalle.setIdUnidadMedida(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                                oCEIngresoProductoDetalle.setIdComprobanteCompraDetalle(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                                oCEIngresoProductoDetalle.setIdIngresoProductoDetalle(idIngresoProdDetall);

                                CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();

                                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                                oCEUnidadMedidaProducto.setTipoUnidad(oCEComprobanteCompraDetalle.getUnidadMedida());
                                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,rowTemp,oCLIngresoProducto.colCodigo);
                                TxtProducto.setText(oCEComprobanteCompraDetalle.getProducto());
                                TblIngresoProductoDetalle.setValueAt(oCEAlmacen,rowTemp,oCLIngresoProducto.colAlm);
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),rowTemp,oCLIngresoProducto.colProducto);
                                TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,rowTemp,oCLIngresoProducto.colUnidadMedida);

                                   if (oCEComprobanteCompraMatriz.getIdMoneda()!=1) {

                                       float importeConDec=oCLIngresoProducto.convertirImporteMonedaExtranjeraaSoles(oCEComprobanteCompraDetalle.getPrecio(), tipocambioTemp
                                                                                                                     ,oCEComprobanteCompraDetalle.getCantidad(), oCEComprobanteCompraDetalle.getDescuento()
                                                                                                                     ,oCEComprobanteCompraDetalle.getIdTipoDescuento());
                                       float costo=importeConDec/oCEComprobanteCompraDetalle.getCantidad();
                                       costo=Float.parseFloat(CLRedondear.RedondearString(costo, 4));
                                      TblIngresoProductoDetalle.setValueAt(costo,rowTemp,oCLIngresoProducto.colPrecio);
                                      TblIngresoProductoDetalle.setValueAt(importeConDec,rowTemp,oCLIngresoProducto.colImporte);

                                   }else
                                   {
                                      TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecioConDesc(),rowTemp,oCLIngresoProducto.colPrecio);
                                      TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento(),rowTemp,oCLIngresoProducto.colImporte);
                                   }
                                TblIngresoProductoDetalle.setValueAt(0,rowTemp,oCLIngresoProducto.colCantidad);
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad(),rowTemp,oCLIngresoProducto.colCantidadOC);
                                TblIngresoProductoDetalle.setValueAt(true,rowTemp,oCLIngresoProducto.colIsEdit);

                        }
                    }

                }
            }
        }
      }
    }

     private void CargarSaldoCantidadesDesdeCompra()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem();
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==2||oCETipoIngresoSalida.getIdTipoIngresoSalida()==6){
        CEComprobanteCompraMatriz oCEComprobanteCompraMatriz= CCComprobanteCompra.ListarComprobanteComprasPorIdCompra(IdComprobanteCompra);

        if(oCEComprobanteCompraMatriz!=null)
        {
        IdComprobanteCompra=oCEComprobanteCompraMatriz.getIdComprobanteCompra();
        int idIngresoProdDetall=0;
        float tipocambioTemp=oCEComprobanteCompraMatriz.getTipoCambio();
            List<CEComprobanteCompraDetalle> lstCEComprobanteCompraDetalle= oCEComprobanteCompraMatriz.getoLstComprobanteDetalle();
            if(lstCEComprobanteCompraDetalle!=null)
            {
            boolean existe=false;
            for (CEComprobanteCompraDetalle oCEComprobanteCompraDetalle : lstCEComprobanteCompraDetalle) {
                existe=false;
                int row=TblIngresoProductoDetalle.getRowCount();
                for (int i = 0; i < row; i++) {

                    CEIngresoProductoDetalle oCEIngresoProductoDetalle=(CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(i, oCLIngresoProducto.colCodigo);

                            if(oCEIngresoProductoDetalle.getIdComprobanteCompraDetalle()==oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle())
                            {
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad(), i, oCLIngresoProducto.colCantidadOC);
                                   existe=true;
                            }
                }

                if(!existe)
                {

                                if(oCEComprobanteCompraDetalle.getSaldoCantidad()!=0){
                                Vector oVector = new Vector();

                                ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);
                                int rowTemp=TblIngresoProductoDetalle.getRowCount()-1;
                                idIngresoProdDetall--;
                                CEIngresoProductoDetalle oCEIngresoProductoDetalle=new CEIngresoProductoDetalle();
                                //oCEIngresoProductoDetalle.setIdAlmacen(oCEComprobanteCompraDetalle.getIdAlmacen());
                                oCEIngresoProductoDetalle.setIdProducto(oCEComprobanteCompraDetalle.getIdProducto());
                                oCEIngresoProductoDetalle.setIdUnidadMedida(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                                oCEIngresoProductoDetalle.setIdComprobanteCompraDetalle(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                                oCEIngresoProductoDetalle.setIdIngresoProductoDetalle(idIngresoProdDetall);

                                CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();

                                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                                oCEUnidadMedidaProducto.setTipoUnidad(oCEComprobanteCompraDetalle.getUnidadMedida());
                                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,rowTemp,oCLIngresoProducto.colCodigo);
                                TxtProducto.setText(oCEComprobanteCompraDetalle.getProducto());
                                TblIngresoProductoDetalle.setValueAt(oCEAlmacen,rowTemp,oCLIngresoProducto.colAlm);
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),rowTemp,oCLIngresoProducto.colProducto);
                                TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,rowTemp,oCLIngresoProducto.colUnidadMedida);

                                   if (oCEComprobanteCompraMatriz.getIdMoneda()!=1) {

                                       float importeConDec=oCLIngresoProducto.convertirImporteMonedaExtranjeraaSoles(oCEComprobanteCompraDetalle.getPrecio(), tipocambioTemp
                                                                                                                     ,oCEComprobanteCompraDetalle.getCantidad(), oCEComprobanteCompraDetalle.getDescuento()
                                                                                                                     ,oCEComprobanteCompraDetalle.getIdTipoDescuento());
                                       float costo=importeConDec/oCEComprobanteCompraDetalle.getCantidad();
                                       costo=Float.parseFloat(CLRedondear.RedondearString(costo, 4));
                                      TblIngresoProductoDetalle.setValueAt(costo,rowTemp,oCLIngresoProducto.colPrecio);
                                      TblIngresoProductoDetalle.setValueAt(importeConDec,rowTemp,oCLIngresoProducto.colImporte);

                                   }else
                                   {
                                      TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecioConDesc(),rowTemp,oCLIngresoProducto.colPrecio);
                                      TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento(),rowTemp,oCLIngresoProducto.colImporte);
                                   }
                                TblIngresoProductoDetalle.setValueAt(0,rowTemp,oCLIngresoProducto.colCantidad);
                                TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad(),rowTemp,oCLIngresoProducto.colCantidadOC);
                                TblIngresoProductoDetalle.setValueAt(true,rowTemp,oCLIngresoProducto.colIsEdit);

                        }
                    }

                }
            }
        }
      }
    }

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

    private void TblIngresoProductoDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblIngresoProductoDetalleKeyReleased

        if(TblIngresoProductoDetalle.getSelectedColumn()==oCLIngresoProducto.colImporte&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblIngresoProductoDetalle.getSelectedRow()+1==TblIngresoProductoDetalle.getRowCount()){
                int fila=TblIngresoProductoDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    if(!isNuevoConDoc){
                    agregarNuevaFila(fila);
                    TblIngresoProductoDetalle.requestFocus();
                    TblIngresoProductoDetalle.changeSelection(fila, oCLIngresoProducto.colProducto, false, false);
                    }
                }else{
                    TblIngresoProductoDetalle.requestFocus();
                    TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if(colValidada!=-1){

            TblIngresoProductoDetalle.requestFocus();
            TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }



    }//GEN-LAST:event_TblIngresoProductoDetalleKeyReleased

    private void TblIngresoProductoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblIngresoProductoDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblIngresoProductoDetalle.getSelectedColumn()==oCLIngresoProducto.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
             

                evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

                
                if(TblIngresoProductoDetalle.getSelectedColumn()==oCLIngresoProducto.colCantidad){
                    TblIngresoProductoDetalle.changeSelection(TblIngresoProductoDetalle.getSelectedRow(), oCLIngresoProducto.colPrecio-1, false, false);
                 }
        }


    }//GEN-LAST:event_TblIngresoProductoDetalleKeyPressed

    private void verficarTextoVacio(int fila){

       int col =TblIngresoProductoDetalle.getSelectedColumn();
       if(TblIngresoProductoDetalle.getRowCount()>0){
                if(fila!=-1)
                   {
                   if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colProducto)!=null){
                       String producto=TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCodigo)!=null){

                               TblIngresoProductoDetalle.setValueAt(((CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCodigo)).getProducto(),fila, oCLIngresoProducto.colProducto);
                           }

                       }
                    }
                }
        }
    }

    private void TblIngresoProductoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblIngresoProductoDetallePropertyChange
        if(!iscerrando){
            if(cont==1){

                int fila=TblIngresoProductoDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if(fila!=-1){
                    verificarDatos(fila,1);
                }
                cont=0;
            }else{
                cont=0;
            }
            cont++;
        }
}//GEN-LAST:event_TblIngresoProductoDetallePropertyChange

    private void TblIngresoProductoDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblIngresoProductoDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {

            }
}//GEN-LAST:event_TblIngresoProductoDetalleMousePressed

    private void TblIngresoProductoDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblIngresoProductoDetalleMouseClicked

    }//GEN-LAST:event_TblIngresoProductoDetalleMouseClicked
boolean isNuevoConDoc=false;

    private void CbxAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxAlmacenMouseClicked

}//GEN-LAST:event_CbxAlmacenMouseClicked

    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxTipoComprobanteItemStateChanged

    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void btnImportCompCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportCompCompraActionPerformed

        DlgBusquedaGuiaRemisionRecibido odialogo=new DlgBusquedaGuiaRemisionRecibido(oparent, true);
        odialogo.setVisible(true);
         CEGuiaRemisionRecibido oCEGuiaRemisionRecibidoMatriz= odialogo.getCEGuiaRemisionRecibido();

        if(oCEGuiaRemisionRecibidoMatriz!=null)
        {
            
            SetIngresoProductoPorGuiaRemision(CCGuiaRemisionRecibido.ConsultarPorId(oCEGuiaRemisionRecibidoMatriz.getIdGuiaRemisionRecibido()));
            
        }
    }//GEN-LAST:event_btnImportCompCompraActionPerformed

    private void btnBuscarCompCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompCompActionPerformed

         DlgBusquedaIngresoAlmacen odialogo=new DlgBusquedaIngresoAlmacen(oparent, true);
        odialogo.setVisible(true);
         CEIngresoProducto oCEIngresoProducto= odialogo.getCEIngresoProducto();

        if(oCEIngresoProducto!=null)
        {
            realizarevento=false;
            SetIngresoProducto(CCIngresoProducto.ConsultarPorId(oCEIngresoProducto.getIdIngresoProducto()));
            realizarevento=true;
        }
    }//GEN-LAST:event_btnBuscarCompCompActionPerformed

    private void TxtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtProveedorActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblIngresoProductoDetalle,
                new String[]{LblEtiqTipoIngreso.getText(),((CETipoIngresoSalida)CbxTipoIngreso.getSelectedItem()).getDescripcion(),
                             lblEtiqNumIngreso.getText(),TxtNumIngreso.getText(),lblEtiqEstado.getText(),txtEstado.getText(),
                             LblEtiqTipoComp.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                             lblEtiqAlmacen.getText(),((CEAlmacen)CbxAlmacen.getSelectedItem()).getDescripcion(),LblEtiqProveedor.getText(),TxtProveedor.getText(),
                             lblEtiqDireccion.getText(),TxtDireccion.getText(),lblEtiqRuc.getText(),TxtDNI.getText(),lblEtiqSucursal.getText(),TxtSucursal.getText(),
                             lblEtiqVendedor.getText(),TxtVendedor.getText(),LblEtiqHoraRegistro.getText(),LblHoraRegistro.getText()}
                ,new String[]{"Observación",TxtObservacion.getText()},new String[]{},"Entrada de Productos");
        oExportar.GuardarArchivoExcel(this);
    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void btnImportCompCompra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportCompCompra1ActionPerformed
        DlgBusquedaImportComprobanteCompra odialogo=new DlgBusquedaImportComprobanteCompra(oparent, true);
        odialogo.setVisible(true);
         CEComprobanteCompraMatriz oCEComprobanteCompraMatriz= odialogo.getCEComprobanteCompraMatriz();

        if(oCEComprobanteCompraMatriz!=null)
        {

            SetIngresoProductoPorCompra(CCComprobanteCompra.ConsultarComprasPorId(oCEComprobanteCompraMatriz.getIdComprobanteCompra()));

        }
    }//GEN-LAST:event_btnImportCompCompra1ActionPerformed



public void SetIngresoProductoPorCompra(CEComprobanteCompraMatriz oCEComprobanteCompraMatriz)
    {
       realizarevento=false;
        isNuevoConDoc=true;
        oCLIngresoProducto.limpiarTabla();
        if(oCEComprobanteCompraMatriz!=null)
        {
            if(oCEComprobanteCompraMatriz.getUltimoIdEstado()!=8){
                limpiar=false;
                TxtNumIngreso.setText("");
                habilitarControles(false);
                pAccionABM=1;
                TxtNumComp.setText(oCEComprobanteCompraMatriz.getNumComprobante());
                //IdEstado=oCEComprobanteCompraMatriz.getIdUltimoEstado();
                IdIngresoProducto=0;
                IdComprobanteCompra=oCEComprobanteCompraMatriz.getIdComprobanteCompra();
                idProveedor=oCEComprobanteCompraMatriz.getIdProveedor();
                TxtProveedor.setText(oCEComprobanteCompraMatriz.getProveedor());
                TxtDNI.setText(oCEComprobanteCompraMatriz.getRUC());
                TxtDireccion.setText(oCEComprobanteCompraMatriz.getDireccion());
                TxtProducto.setText("");
                TxtNumComp.setEditable(false);
                float tipocambioTemp=oCEComprobanteCompraMatriz.getTipoCambio();
                CbxTipoIngreso.setEnabled(false);
                TxtNumComp.setText(oCEComprobanteCompraMatriz.getNumComprobante());
               // CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoIngreso,2);
                CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEComprobanteCompraMatriz.getIdTipoComprobanteCompra());
                CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoIngreso, 2);

                if(oCEComprobanteCompraMatriz.getoLstComprobanteDetalle()!=null)
                {

                    int row=0;
                   for(int i=0; i<oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().size();i++){

                       CEComprobanteCompraDetalle oCEComprobanteCompraDetalle=oCEComprobanteCompraMatriz.getoLstComprobanteDetalle().get(i) ;
                       if(oCEComprobanteCompraDetalle.getSaldoCantidad()!=0){
                           Vector oVector = new Vector();

                        ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);                                                
                        CEIngresoProductoDetalle oCEIngresoProductoDetalle=new CEIngresoProductoDetalle();
                        oCEIngresoProductoDetalle.setProducto(oCEComprobanteCompraDetalle.getProducto());
                        oCEIngresoProductoDetalle.setIdProducto(oCEComprobanteCompraDetalle.getIdProducto());
                        oCEIngresoProductoDetalle.setIdUnidadMedida(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                        oCEIngresoProductoDetalle.setIdComprobanteCompraDetalle(oCEComprobanteCompraDetalle.getIdComprobanteCompraDetalle());
                        CLCargarCombo.buscarIdAlmacen(CbxAlmacen, oCEIngresoProductoDetalle.getIdAlmacen());
                        CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();

                        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                        oCEUnidadMedidaProducto.setTipoUnidad(oCEComprobanteCompraDetalle.getUnidadMedida());
                        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEComprobanteCompraDetalle.getIdUnidadMedidaCompra());
                        TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,row,oCLIngresoProducto.colCodigo);
                        TxtProducto.setText(oCEComprobanteCompraDetalle.getProducto());
                        TblIngresoProductoDetalle.setValueAt(oCEAlmacen,row,oCLIngresoProducto.colAlm);
                        TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getProducto(),row,oCLIngresoProducto.colProducto);
                        TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,row,oCLIngresoProducto.colUnidadMedida);

                           if (oCEComprobanteCompraMatriz.getIdMoneda()!=1) {

                               float importeConDec=oCLIngresoProducto.convertirImporteMonedaExtranjeraaSoles(oCEComprobanteCompraDetalle.getPrecio(), tipocambioTemp
                                                                                                             ,oCEComprobanteCompraDetalle.getCantidad(), oCEComprobanteCompraDetalle.getDescuento()
                                                                                                             ,oCEComprobanteCompraDetalle.getIdTipoDescuento());
                               float costo=importeConDec/oCEComprobanteCompraDetalle.getCantidad();
                               costo=Float.parseFloat(CLRedondear.RedondearString(costo, 4));
                              TblIngresoProductoDetalle.setValueAt(costo,row,oCLIngresoProducto.colPrecio);
                              TblIngresoProductoDetalle.setValueAt(importeConDec,row,oCLIngresoProducto.colImporte);

                           }else
                           {
                              TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getPrecioConDesc(),row,oCLIngresoProducto.colPrecio);
                              TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getImporteConDescuento()+oCEComprobanteCompraDetalle.getExonerado(),row,oCLIngresoProducto.colImporte);
                           }
                        TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad(),row,oCLIngresoProducto.colCantidad);
                        TblIngresoProductoDetalle.setValueAt(oCEComprobanteCompraDetalle.getSaldoCantidad(),row,oCLIngresoProducto.colCantidadOC);
                        TblIngresoProductoDetalle.setValueAt(true,row,oCLIngresoProducto.colIsEdit);
                          row++;
                        }
                     
                    }
                }

                
                TblIngresoProductoDetalle.setEnabled(true);
                TblIngresoProductoDetalle.clearSelection();
                if(TblIngresoProductoDetalle.getRowCount()>0)
                 {
                     TblIngresoProductoDetalle.requestFocus();
                    TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colCantidad, false, false);
                 }
                oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colCantidad,oCLIngresoProducto.colPrecio});

                oclBotonesABM.controlBoton(false, true, false, false, true, false);
                if(IdEstado!=1)
                {
                    btnAnular.setEnabled(false);
                    btnEditar.setEnabled(false);
                }
                TxtObservacion.setEnabled(true);
                TxtObservacion.setText("");
                CbxAlmacen.setEnabled(true);
                limpiar=true;
                btnGuardar.requestFocus();
            }else
            {
                 JOptionPane.showMessageDialog(null,"El registro ya se recepciono");
            }
          
        }
    else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
          btnImportCompCompra.requestFocus();
        }

        realizarevento=true;

    }

public void SetIngresoProductoPorGuiaRemision(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
       realizarevento=false;
        isNuevoConDoc=true;
        oCLIngresoProducto.limpiarTabla();
        if(oCEGuiaRemisionRecibido!=null)
        {
            if(oCEGuiaRemisionRecibido.getUltimoIdEstado()!=8){
                limpiar=false;
                TxtNumIngreso.setText("");
                habilitarControles(false);
                pAccionABM=1;
                TxtNumComp.setText(oCEGuiaRemisionRecibido.getNumeroDocumento());
                //IdEstado=oCEComprobanteCompraMatriz.getIdUltimoEstado();
                IdIngresoProducto=0;
                IdGuiaRemision=oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido();
                idProveedor=oCEGuiaRemisionRecibido.getIdProveedor();
                TxtProveedor.setText(oCEGuiaRemisionRecibido.getProveedor());
                TxtDNI.setText(oCEGuiaRemisionRecibido.getRUC());
            //    TxtDireccion.setText(oCEGuiaRemisionRecibido.getDireccion());
                TxtProducto.setText("");
                TxtNumComp.setEditable(false);
                CbxTipoIngreso.setEnabled(false);
               // CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoIngreso,2);
                CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, 8);
                CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoIngreso, 2);

                if(oCEGuiaRemisionRecibido.getLstGuiaDetalle()!=null)
                {

                    int row=0;
                   for(int i=0; i<oCEGuiaRemisionRecibido.getLstGuiaDetalle().size();i++){

                       CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle=oCEGuiaRemisionRecibido.getLstGuiaDetalle().get(i) ;
                       if(oCEGuiaRemisionRecibidoDetalle.getSaldoCantidad()!=0){
                           Vector oVector = new Vector();

                        ((DefaultTableModel)TblIngresoProductoDetalle.getModel()).addRow(oVector);
                        CEIngresoProductoDetalle oCEIngresoProductoDetalle=new CEIngresoProductoDetalle();
                        oCEIngresoProductoDetalle.setProducto(oCEGuiaRemisionRecibidoDetalle.getProducto());
                        oCEIngresoProductoDetalle.setIdProducto(oCEGuiaRemisionRecibidoDetalle.getIdProducto());
                        oCEIngresoProductoDetalle.setIdUnidadMedida(oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                        oCEIngresoProductoDetalle.setIdComprobanteCompraDetalle(oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle());
                        CLCargarCombo.buscarIdAlmacen(CbxAlmacen, oCEIngresoProductoDetalle.getIdAlmacen());
                        CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();

                        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                        oCEUnidadMedidaProducto.setTipoUnidad(oCEGuiaRemisionRecibidoDetalle.getUnidadMedida());
                        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                        TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,row,oCLIngresoProducto.colCodigo);
                        TxtProducto.setText(oCEGuiaRemisionRecibidoDetalle.getProducto());
                        TblIngresoProductoDetalle.setValueAt(oCEAlmacen,row,oCLIngresoProducto.colAlm);
                        TblIngresoProductoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getProducto(),row,oCLIngresoProducto.colProducto);
                        TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,row,oCLIngresoProducto.colUnidadMedida);

                        TblIngresoProductoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getSaldoCantidad(),row,oCLIngresoProducto.colCantidad);
                        TblIngresoProductoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getSaldoCantidad(),row,oCLIngresoProducto.colCantidadOC);
                        TblIngresoProductoDetalle.setValueAt(true,row,oCLIngresoProducto.colIsEdit);
                          row++;
                        }

                    }
                }


                TblIngresoProductoDetalle.setEnabled(true);
                TblIngresoProductoDetalle.clearSelection();
                if(TblIngresoProductoDetalle.getRowCount()>0)
                 {
                     TblIngresoProductoDetalle.requestFocus();
                    TblIngresoProductoDetalle.changeSelection(0, oCLIngresoProducto.colCantidad, false, false);
                 }
                oCLIngresoProducto.setColumnEditable(new int[]{oCLIngresoProducto.colCantidad,oCLIngresoProducto.colPrecio});

                oclBotonesABM.controlBoton(false, true, false, false, true, false);
                if(IdEstado!=1)
                {
                    btnAnular.setEnabled(false);
                    btnEditar.setEnabled(false);
                }
                TxtObservacion.setEnabled(true);
                TxtObservacion.setText("");
                CbxAlmacen.setEnabled(true);
                limpiar=true;
                btnGuardar.requestFocus();
            }else
            {
                 JOptionPane.showMessageDialog(null,"El registro ya se recepciono");
            }

        }
    else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
          btnImportCompCompra.requestFocus();
        }

        realizarevento=true;

    }


   
    private void agregarIngresoProductoDetalle(CEProducto oCEProducto,List<CEProductoPrecio> olistaPrecio)
    {
         int fila=TblIngresoProductoDetalle.getSelectedRow();
        if(oCEProducto!=null){
       
        CEIngresoProductoDetalle oCEIngresoProductoDetalle= new CEIngresoProductoDetalle();
        oCEIngresoProductoDetalle.setProducto(oCEProducto.getDescripcion());
        oCEIngresoProductoDetalle.setIdProducto(oCEProducto.getIdProducto());
       // oCEIngresoProductoDetalle.setIdAlmacen(oCEProducto.getIdAlmacen());
        oCEIngresoProductoDetalle.setIdUnidadMedida(oCEProducto.getIdUnidadBase());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEProducto.getUMP());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEProducto.getIdUnidadBase());
        oCEIngresoProductoDetalle.setCodigoProducto(oCEProducto.getCodigo());
        if(pAccionABM==2)
        {
            CEIngresoProductoDetalle oCEIngresoProductoDetalleaux= (CEIngresoProductoDetalle)TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCodigo);
            oCEIngresoProductoDetalle.setIdIngresoProductoDetalle(oCEIngresoProductoDetalleaux.getIdIngresoProductoDetalle());
            
        }

   

                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle,fila,oCLIngresoProducto.colCodigo);
                TblIngresoProductoDetalle.setValueAt(oCEIngresoProductoDetalle.getProducto(),fila,oCLIngresoProducto.colProducto);
                TblIngresoProductoDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLIngresoProducto.colUnidadMedida);
                TblIngresoProductoDetalle.setValueAt(true,fila,oCLIngresoProducto.colIsEdit);
                float cantidad=0;
                if(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidad)!=null){
                cantidad=Float.parseFloat(TblIngresoProductoDetalle.getValueAt(fila, oCLIngresoProducto.colCantidad).toString());
                }
                if(cantidad>0){
                    verificarDatos(fila,1);
                }

        }
        
    }

    private boolean limpiar;
    private void limpiarFormulario()
    {
      if(limpiar){
      txtEstado.setText("EMITIDO");
      CbxAlmacen.setSelectedIndex(0);
      jTextFieldNumber.setText("");
      TxtNumComp.setText("");
      TxtNumIngreso.setText("");
      TxtProducto.setText("");
      TxtProveedor.setText("");
      TxtDireccion.setText("");
      TxtDNI.setText("");
      TxtObservacion.setText("");
      idProveedor=0;
      oCLIngresoProducto.limpiarTabla();      
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
      }
    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxAlmacen;
    private ComboBox.ComboBox CbxTipoComprobante;
    private ComboBox.ComboBox CbxTipoIngreso;
    private javax.swing.ButtonGroup GrRbtnNumSalida;
    private javax.swing.ButtonGroup GrRbtnNumdoc;
    private Label.Label LblEtiqHoraRegistro;
    private Label.Label LblEtiqNumComp;
    private Label.Label LblEtiqProveedor;
    private Label.Label LblEtiqTipoComp;
    private Label.Label LblEtiqTipoIngreso;
    private Label.Label LblHoraRegistro;
    private Label.Label LblHoraSistema;
    private javax.swing.JLabel LblMensaje;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblIngresoProductoDetalle;
    private Label.Label TxtDNI;
    private Label.Label TxtDireccion;
    private TextField.JTxtNinguno TxtNumComp;
    private Label.Label TxtNumIngreso;
    private javax.swing.JTextArea TxtObservacion;
    private TextField.JTxtNinguno TxtProducto;
    private TextField.JTxtLetra TxtProveedor;
    private Label.Label TxtSucursal;
    private Label.Label TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarCompComp;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnBuscar btnImportCompCompra;
    private BotonesABM.BtnBuscar btnImportCompCompra1;
    private BotonesABM.BtnNuevo btnNuevo;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label lblEtiqAlmacen;
    private Label.Label lblEtiqDireccion;
    private Label.Label lblEtiqEstado;
    private Label.Label lblEtiqNumIngreso;
    private Label.Label lblEtiqRuc;
    private Label.Label lblEtiqSucursal;
    private Label.Label lblEtiqVendedor;
    private Label.Label txtEstado;
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
            eventoCancelar();
         
            return;
        }

         if(e.getActionCommand().equals(ACTION_GUARDAR))
        {
             eventoGuardarIngreso();
             return;
        }

  
    }

}
