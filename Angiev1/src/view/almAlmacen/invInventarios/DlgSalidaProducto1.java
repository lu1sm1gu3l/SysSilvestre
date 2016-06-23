package view.almAlmacen.invInventarios;

import controller.almAlmacen.CCAlmacen;
import controller.almAlmacen.CCIngresoProducto;
import controller.almAlmacen.CCSalidaProducto;
import controller.almAlmacen.CCTipoIngresoSalida;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCPedido;
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
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CESalidaProducto;
import modelo.almAlmacen.entidad.CESalidaProductoDetalle;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CETipoIngresoSalida;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import modelo.vtaVenta.entidad.CETipoComprobante;
import util.clases.almAlmacen.CLSalidaProducto;
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
import view.cmrComercial.DlgGestionCliente;

/**
 *
 * @author Luiggi
 */
public class DlgSalidaProducto1 extends javax.swing.JDialog implements ActionListener{

   private  long idCliente=0;
   private  long IdSalidaProducto=0;
   private  int IdEstado=0;
   private MiReloj hilo;
   private  CLSalidaProducto oCLSalidaProducto;
   private  int pAccionABM=0;
   private CLBotonesABM oclBotonesABM;
   private java.awt.Frame oparent;
   private ArrayList<CETipoIngresoSalida> listCETipoIngresoSalida =null;
    private ArrayList<CETipoIngresoSalida> listCETipoIngresoSalidaTodo =null;

    /** Creates new form DlgGestionSalidaProducto */
    public DlgSalidaProducto1(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        this.oparent=parent;
        initComponents();
        this.setLocationRelativeTo(null);      
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        realizarEventoCbxAlmacen=true;
        cargarUtilidades();
        TblSalidaProductoDetalle.setSurrendersFocusOnKeystroke(true);
        btnEditar.setVisible(false);
    }

    private void cargarUtilidades()
    {
       
        oCLSalidaProducto = new CLSalidaProducto(TblSalidaProductoDetalle);
        listCETipoIngresoSalida=CCTipoIngresoSalida.consultarListaTipoSalidasProductos();
        listCETipoIngresoSalidaTodo=CCTipoIngresoSalida.consultarTodoTipoSalidaProductos();
        CbxTipoSalida.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaComprobanteInvantarios()));
        CbxAlmacen.setModel(CLComboBox.cargarComboAlmacenConPivot(CCAlmacen.listarAlmacenPorSucursal(FrmSistemaMenu.getIdSucursal())));
        TblSalidaProductoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
            
        TableColumn columnaCantidad = TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaPrecio = TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colPrecio);
        columnaPrecio.setCellEditor(new DefaultCellEditor(jTextFieldNumber));       
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        ((JTableX)TblSalidaProductoDetalle).setSelectAllForEdit(true);
        eventoNuevo();
        habilitarContorlesEnVenta();
        LblMensaje.setText("");
        alinearColumnas();
        

    }
    private void alinearColumnas()
    {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colCantidad).setCellRenderer(tcr);
        TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colPrecio).setCellRenderer(tcr);
        TblSalidaProductoDetalle.getColumnModel().getColumn(oCLSalidaProducto.colImporte).setCellRenderer(tcr);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        GrRbtnNumdoc = new javax.swing.ButtonGroup();
        GrRbtnNumSalida = new javax.swing.ButtonGroup();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqCliente = new Label.Label();
        lblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        LblEtiqDNI = new Label.Label();
        lblEtiqDireccion = new Label.Label();
        TxtCliente = new TextField.JTxtLetra();
        LblHoraRegistro1 = new Label.Label();
        lblEtiqHoraRegistro = new Label.Label();
        lblEtiqVendedor = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        TxtNumComp = new TextField.JTxtNinguno();
        lblEtiqNumComp = new Label.Label();
        lblEtiqTipoComp = new Label.Label();
        CbxTipoComprobante = new ComboBox.ComboBox();
        CbxAlmacen = new ComboBox.ComboBox();
        LblEtiqAlmacen = new Label.Label();
        jPanel3 = new javax.swing.JPanel();
        TxtNumSalida = new Label.Label();
        btnBuscarCompComp = new BotonesABM.BtnBuscar();
        CbxTipoSalida = new ComboBox.ComboBox();
        LblEtiqTipoIngreso = new Label.Label();
        lblEtiqNumeroSalida = new Label.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblSalidaProductoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLSalidaProducto.colCantidad)
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
                    super.setValueAt(null,row,column);
                }
            }

        };
        label20 = new Label.Label();
        BtnAgregar = new javax.swing.JButton();
        BtnQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        lblEtiqEstado = new Label.Label();
        TxtEstado = new Label.Label();
        LblUltimoNumSalida = new Label.Label();
        label37 = new Label.Label();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        setTitle("Salidas de Almacén");
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

        LblEtiqCliente.setText("Cliente     :");

        lblEtiqSucursal.setText("Sucursal      :");

        TxtVendedor.setText("NINGUNO");
        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqDNI.setText("DNI          :");

        lblEtiqDireccion.setText("Direccion  :");

        TxtCliente.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtCliente.setTamanio(200);
        TxtCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtClienteMouseClicked(evt);
            }
        });
        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteKeyPressed(evt);
            }
        });

        LblHoraRegistro1.setText("00/00/00 00:00:00");
        LblHoraRegistro1.setFont(new java.awt.Font("Verdana", 0, 11));
        LblHoraRegistro1.setText("");

        lblEtiqHoraRegistro.setText("Fecha Reg:");

        lblEtiqVendedor.setText("Usuario    :");

        TxtSucursal.setText("NINGUNO");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtDireccion.setText("NINGUNO");

        TxtDNI.setText("--------------");

        TxtNumComp.setText("2012-123456");
        TxtNumComp.setTamanio(12);

        lblEtiqNumComp.setText("Nº Comprob:");

        lblEtiqTipoComp.setText("Comprob  :");

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

        CbxAlmacen.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxAlmacenMouseClicked(evt);
            }
        });
        CbxAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxAlmacenActionPerformed(evt);
            }
        });

        LblEtiqAlmacen.setText("Almacen   :");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblEtiqNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PnlDatos2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))))
                .addGap(24, 24, 24))
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TxtNumSalida.setText("--------------");

        btnBuscarCompComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarCompComp.setText("Buscar Salida");
        btnBuscarCompComp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCompComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompCompActionPerformed(evt);
            }
        });

        CbxTipoSalida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxTipoSalidaItemStateChanged(evt);
            }
        });
        CbxTipoSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoSalidaActionPerformed(evt);
            }
        });

        LblEtiqTipoIngreso.setText("Tipo Salida :");

        lblEtiqNumeroSalida.setText("Num Salida     :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(LblEtiqTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(CbxTipoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(lblEtiqNumeroSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(TxtNumSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CbxTipoSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblEtiqTipoIngreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtNumSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEtiqNumeroSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblSalidaProductoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblSalidaProductoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Integer(1), null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Cantidad", "U. M. ", "Costo", "Importe", "Almacen", "edit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblSalidaProductoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblSalidaProductoDetalle.setColumnSelectionAllowed(true);
        TblSalidaProductoDetalle.setRowHeight(21);
        TblSalidaProductoDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblSalidaProductoDetalle.getTableHeader().setReorderingAllowed(false);
        TblSalidaProductoDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblSalidaProductoDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblSalidaProductoDetalleMousePressed(evt);
            }
        });
        TblSalidaProductoDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblSalidaProductoDetallePropertyChange(evt);
            }
        });
        TblSalidaProductoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblSalidaProductoDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblSalidaProductoDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblSalidaProductoDetalle);
        TblSalidaProductoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblSalidaProductoDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
        TblSalidaProductoDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(2).setPreferredWidth(480);
        TblSalidaProductoDetalle.getColumnModel().getColumn(3).setPreferredWidth(100);
        TblSalidaProductoDetalle.getColumnModel().getColumn(4).setPreferredWidth(100);
        TblSalidaProductoDetalle.getColumnModel().getColumn(5).setPreferredWidth(120);
        TblSalidaProductoDetalle.getColumnModel().getColumn(6).setMinWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(6).setPreferredWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(6).setMaxWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(7).setMinWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(7).setPreferredWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(7).setMaxWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(8).setMinWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(8).setPreferredWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(8).setMaxWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(9).setPreferredWidth(0);
        TblSalidaProductoDetalle.getColumnModel().getColumn(9).setMaxWidth(0);

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

        columnButton1.setTbl(TblSalidaProductoDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        lblEtiqEstado.setText("Estado     :");

        TxtEstado.setText("EMITIDO");
        TxtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        LblUltimoNumSalida.setText("00000");
        LblUltimoNumSalida.setFont(new java.awt.Font("Verdana", 1, 11));

        label37.setText("Ultimo Nº Salida :");
        label37.setFont(new java.awt.Font("Verdana", 1, 11));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Fila Editable");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("  ");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Fila No Editable");

        jLabel6.setBackground(new java.awt.Color(255, 204, 255));
        jLabel6.setText("  ");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        LblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11));
        LblMensaje.setForeground(new java.awt.Color(204, 0, 0));
        LblMensaje.setText("AVISO : El Tipo Salida ventas no se puede editar.");

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevo);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed1(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296)
                        .addComponent(label37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(LblUltimoNumSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(45, 45, 45)
                                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
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
                                .addComponent(LblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(PnlDatos2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(label37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblUltimoNumSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(LblMensaje)))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarCliente()
    {
        if(TxtCliente.isEnabled()){
          DlgGestionCliente odialogo= new DlgGestionCliente(null, true,1,1,1);//consultar
       
          odialogo.setCajaTexo(TxtCliente.getText());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Cliente");
                  if(odialogo.getoCECliente()!=null){
                    idCliente=odialogo.getoCECliente().getIdCliente();
                    TxtCliente.setText(odialogo.getoCECliente().getNombreCompleto());
                    TxtDNI.setText(odialogo.getoCECliente().getRUC());
                    TxtDireccion.setText(odialogo.getoCECliente().getDireccion());
                    TblSalidaProductoDetalle.requestFocus();
                    TblSalidaProductoDetalle.changeSelection(0,oCLSalidaProducto.colProducto, false, false);
                    }
        }

      }
private void habilitarControles(boolean bol)
    {
    
    TxtCliente.setEnabled(bol);
    BtnAgregar.setEnabled(bol);
    BtnQuitar.setEnabled(bol);
    TxtNumComp.setEnabled(bol);
    CbxTipoComprobante.setEnabled(bol);
    TxtObservacion.setEnabled(bol);
    
  
}

private int ValidarRegistro(int fila)
    {
     if(TblSalidaProductoDetalle.getRowCount()>0){
    
     int numColumna=0;
        CESalidaProductoDetalle oCESalidaProductoDetalle=(CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(fila-1, oCLSalidaProducto.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCESalidaProductoDetalle!=null){
            if(TblSalidaProductoDetalle.getValueAt(fila-1, oCLSalidaProducto.colCantidad)!=null)
            {
                float cantidad=VerificadorDeTxt.convertFloat(TblSalidaProductoDetalle.getValueAt(fila-1, oCLSalidaProducto.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLSalidaProducto.colCantidad;
                        colValidada=oCLSalidaProducto.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
                    /*float precio=0;
                        if(TblSalidaProductoDetalle.getValueAt(fila-1, oCLSalidaProducto.colPrecio)!=null)
                        {
                        precio=Float.parseFloat(TblSalidaProductoDetalle.getValueAt(fila-1, oCLSalidaProducto.colPrecio).toString());
                        }
                        if(precio==0){
                        numColumna = oCLSalidaProducto.colPrecio;
                        colValidada = oCLSalidaProducto.colPrecio;
                        mensaje="Ingrese el costo";
                        }*/
                }
            }else{
                        numColumna = oCLSalidaProducto.colCantidad;
                        colValidada = oCLSalidaProducto.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLSalidaProducto.colProducto;
            colValidada=oCLSalidaProducto.colProducto;

        }
        if(numColumna!=0){
//            if(numColumna!=oCLSalidaProducto.colPrecio){
            JOptionPane.showMessageDialog(this,mensaje);
//            }else
//                { int filat=TblSalidaProductoDetalle.getSelectedRow();
//                  oCLSalidaProducto.calcularPrecio(filat);
//                }
            }

        return numColumna;
        
        }
    return 0;
}

private boolean validarbusqProducto()
    {
    CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();
         
        if(oCEAlmacen.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(this,"Seleccione un Almacen","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto-1, false, false);
             CbxAlmacen.requestFocus();
             return false;
        }         
         CESalidaProductoDetalle oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(TblSalidaProductoDetalle.getSelectedRow(), oCLSalidaProducto.colCodigo);
         if(oCESalidaProductoDetalle==null){
             return true;
         }


    return true;
    }

boolean isNuevoConDoc=false;
    private void BuscarProducto()
    {        
            
                if(validarbusqProducto()){
                CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();
                DlgBusquedaProductoInventarios odialogo= new DlgBusquedaProductoInventarios(oparent, true,1,oCEAlmacen.getIdAlmacen());
                String NombreProd=TxtProducto.getText().trim();
                btnGuardar.requestFocus();
                if(NombreProd.equals(""))
                {
               if(TblSalidaProductoDetalle.getValueAt(TblSalidaProductoDetalle.getSelectedRow(), oCLSalidaProducto.colProducto)!=null)
                    {
                    NombreProd=TblSalidaProductoDetalle.getValueAt(TblSalidaProductoDetalle.getSelectedRow(), oCLSalidaProducto.colProducto)+"";
                    }
                }
               odialogo.setCajaTexto(NombreProd);
               if(pAccionABM==2){
               odialogo.InhabilitarBusqedaProd();
                    }
               TxtProducto.setText("");
             //  odialogo.setListaSalidaProducto(this.ObtenerRegistrosAcuales());
               odialogo.setVisible(true);
               CEAlmacenProducto oCEAlmacenProducto=odialogo.getProductoAlmacen();
               if(!odialogo.getisagregarCantidadOrdenCompra()){
               agregarSalidaProductoDetalle(oCEAlmacenProducto);
               
               int fila=TblSalidaProductoDetalle.getSelectedRow();
               TblSalidaProductoDetalle.requestFocus();
               TblSalidaProductoDetalle.changeSelection(fila, oCLSalidaProducto.colCantidad, false, false);

                }else{
                   TblSalidaProductoDetalle.requestFocus();
                   SeleccionarSalidaProductoExistente(oCEAlmacenProducto);
                }
             }
          
        
     

    }
    private void SeleccionarSalidaProductoExistente(CEAlmacenProducto oCEAlmacenProducto)
    {
        CESalidaProductoDetalle oCESalidaProductoDetalle ;
        for( int i =0;i<TblSalidaProductoDetalle.getRowCount();i++)
            {
//cx
                oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);
                if(oCESalidaProductoDetalle!=null){
                    
                        if(oCESalidaProductoDetalle.getIdAlmacen()==oCEAlmacenProducto.getIdAlmacen()&&
                           oCESalidaProductoDetalle.getIdUnidadMedida()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCESalidaProductoDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblSalidaProductoDetalle.requestFocus();
                            TblSalidaProductoDetalle.changeSelection(i, oCLSalidaProducto.colUnidadMedida, false, false);
                        }

                }
            }
    }
    private List<CESalidaProductoDetalle> ObtenerRegistrosAcuales()
    {
        List<CESalidaProductoDetalle> lstSalidaProductoDetalleTemp = new ArrayList<CESalidaProductoDetalle>();
        CESalidaProductoDetalle oCESalidaProductoDetalle ;
        for( int i =0;i<TblSalidaProductoDetalle.getRowCount();i++)
            {
                oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);
                if(oCESalidaProductoDetalle!=null){
                    if(i!=TblSalidaProductoDetalle.getSelectedRow()){
                oCESalidaProductoDetalle.setCosto(Float.parseFloat(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colPrecio).toString()));
                oCESalidaProductoDetalle.setCantidad(0);
                if(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCantidad)!=null){
                oCESalidaProductoDetalle.setCantidad(Float.parseFloat(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCantidad).toString()));
                }
                CESalidaProductoDetalle oCESalidaProductoDetalleTemp=(CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);
                oCESalidaProductoDetalle.setIdProducto(oCESalidaProductoDetalleTemp.getIdProducto());
                lstSalidaProductoDetalleTemp.add(oCESalidaProductoDetalle);
                    }
                }
            }
        return lstSalidaProductoDetalleTemp;
    }private boolean  ValidarTablaSalidaProducto()
     {


            for(int i=0; i<TblSalidaProductoDetalle.getRowCount();i++)
            {
                verificarDatos(i);

                if(colValidada!=-1){
                    TblSalidaProductoDetalle.requestFocus();
                    TblSalidaProductoDetalle.changeSelection(i, colValidada, false, false);
                    colValidada=-1;
                    return false;
                }
            }
            eventoGuardar=false;
        return true;
    }
    boolean eventoGuardar=false;
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      eventoGuardar=true;
      int columnaValidada=ValidarRegistro(TblSalidaProductoDetalle.getRowCount());
      if(columnaValidada==0)
      {

          EventoGuardar();
         
      }else
      {
          TblSalidaProductoDetalle.requestFocus();
          TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getRowCount()-1, columnaValidada, false, false);
      }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private boolean validar()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
          if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8)
          {
              idCliente=1;
          }
        if(idCliente==0)
        {

             JOptionPane.showMessageDialog(this,"Ingrese un Cliente","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtCliente.requestFocus();
             return false;
        }
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()!=8)
          {
            if(TxtNumComp.getText().trim().equals(""))
            {
                 JOptionPane.showMessageDialog(this,"Ingrese el Numero de Comprobante","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 TxtNumComp.requestFocus();
                 return false;
            }
        }
        CEAlmacen oCEAlmacen=(CEAlmacen)CbxAlmacen.getSelectedItem();
        if(oCEAlmacen.getIdAlmacen()==0)
        {
             JOptionPane.showMessageDialog(this,"Seleccione un Almacen","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             CbxAlmacen.requestFocus();
             return false;
        }
        if(TblSalidaProductoDetalle.getRowCount()==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese SalidaProducto","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblSalidaProductoDetalle.requestFocus();
             TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto, false, false);
             return false;
        }
         int resul=JOptionPane.showConfirmDialog(this,"<html><h2><BR> ESTÁ SEGURO DE GUARDAR LA SALIDA EN EL "+oCEAlmacen.getDescripcion()+"</h2></html>" , "",JOptionPane.YES_NO_OPTION);
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
         if(pAccionABM==2){
        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoSalida(IdSalidaProducto);
            if(oCEEstadoInvent.getmIntIdEstado()==4)
            {
             JOptionPane.showMessageDialog(this,"No se Puede Guardar, el Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+"","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
           String Mensaje=VerificarUltimosCambiosDeMovimiento();
            if(!Mensaje.equals(""))
            {
                repaint();
                JOptionPane.showMessageDialog(this,"Los siguientes productos, no se pueden actualizar \n"+Mensaje,"Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    private void EventoGuardar()
    {
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8||oCETipoIngresoSalida.getIdTipoIngresoSalida()==7){
        if(ValidarTablaSalidaProducto()){
        if(validar()){
                    if(pAccionABM==1){

                        CESalidaProducto oCESalidaProducto=this.getSalidaProducto();
                        String Mensaje=CCSalidaProducto.InsSalidaProducto(oCESalidaProducto);
                        if(Mensaje.equals("OK"))
                        {
                        JOptionPane.showMessageDialog(this,"<html>El código de SalidaProducto es: "+oCESalidaProducto.getNumeroSalida()+"</html>");
                        limpiarFormulario();
                        CbxTipoSalida.setSelectedIndex(0);
                        LblHoraRegistro1.setText("");
                        LblUltimoNumSalida.setText(oCESalidaProducto.getNumeroSalida());
                        TblSalidaProductoDetalle.requestFocus();
                        TblSalidaProductoDetalle.changeSelection(0,oCLSalidaProducto.colProducto, false, false);
                        eventoNuevo();
                        CbxTipoSalida.setEnabled(true);
                        limpiarCliente();
                        TxtNumSalida.setText("");
                        TxtNumComp.setText("");
                        TxtObservacion.setText("");
                      //  eventoCancelar();
                        }
                        else{

                        JOptionPane.showMessageDialog(this, "No se pudo completar la operación: \nStock Menor a la cantidad de Salida\nDetalles:\n"+Mensaje);
                        }
                    }else{
                        int iscorrecto=CCSalidaProducto.UPDSalidaProducto(this.getSalidaProducto());
                        if(iscorrecto==1)
                        {
                        JOptionPane.showMessageDialog(this,"<html><CENTER>Operación exitosa</CENTER></html>");
                        limpiarFormulario();
                        CbxTipoSalida.setSelectedIndex(0);
                        limpiarCliente();
                        TxtNumComp.setText("");
                        TxtNumSalida.setText("");
                        eventoNuevo();
                        CbxTipoSalida.setEnabled(true);
                        TxtObservacion.setText("");
                        CbxTipoSalida.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
                        }
                    else{
                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
               }
           }
        }
    }else
        {
         LblMensaje.setText("AVISO : El Tipo Salida: "+oCETipoIngresoSalida.getDescripcion()+" no se puede editar, ni anular.");
        }
    }

    private CESalidaProducto getSalidaProducto(){
        CESalidaProducto oCESalidaProducto= new CESalidaProducto();

        oCESalidaProducto.setIdSalidaProducto(IdSalidaProducto);
        CETipoIngresoSalida oCETipoSalidaSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
        oCESalidaProducto.setIdTipoIngresoSalida(oCETipoSalidaSalida.getIdTipoIngresoSalida());
        oCESalidaProducto.setNumeroDocumento(TxtNumComp.getText());
        oCESalidaProducto.setIdSucursal(FrmSistemaMenu.getIdSucursal());
      //  oCESalidaProducto.setIdUltimoEstado(1);
        oCESalidaProducto.setIdAlmacen(((CEAlmacen)CbxAlmacen.getSelectedItem()).getIdAlmacen());
        oCESalidaProducto.setIdCliente(idCliente);
        oCESalidaProducto.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCESalidaProducto.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCESalidaProducto.setIdTipoComprobante(((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getIdTipoComprobante());
        oCESalidaProducto.setObservacion(TxtObservacion.getText());
        List<CESalidaProductoDetalle> lstSalidaProductoDetalle = new ArrayList<CESalidaProductoDetalle>();
        CESalidaProductoDetalle oCESalidaProductoDetalle ;
        for( int i =0;i<TblSalidaProductoDetalle.getRowCount();i++)
            {

                oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);
                if(oCESalidaProductoDetalle!=null){

                    boolean guardar=VerificadorDeTxt.convertBoolean(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colIsEdit));
                    if(guardar){
                        oCESalidaProductoDetalle.setCantidad(Float.parseFloat(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCantidad).toString()));
                        lstSalidaProductoDetalle.add(oCESalidaProductoDetalle);
                    }
                   
                }
                
            }

            if(pAccionABM==2)
            {
              lstSalidaProductoDetalle.addAll(lstSalidaDetEliminar);
            }
            oCESalidaProducto.setLstSalidaDetalle(lstSalidaProductoDetalle);


            return oCESalidaProducto;
}
    private void agregarNuevaFila(int fila)
    {
      
         Vector oVector = new Vector();

                ((DefaultTableModel)TblSalidaProductoDetalle.getModel()).addRow(oVector);
             

                    TblSalidaProductoDetalle.requestFocus();
                   
        
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{
    CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
      if(oCETipoIngresoSalida!=null){
       if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7||oCETipoIngresoSalida.getIdTipoIngresoSalida()==8){
              int columnaValidada=ValidarRegistro(TblSalidaProductoDetalle.getRowCount());
              if(columnaValidada==0)
              {
                  agregarNuevaFila(TblSalidaProductoDetalle.getRowCount());
                  TblSalidaProductoDetalle.requestFocus();
                  TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getRowCount()-1, oCLSalidaProducto.colProducto, false, false);


              }else
              {
                  TblSalidaProductoDetalle.requestFocus();
                  TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getRowCount()-1, columnaValidada, false, false);
              }
          }
    }
}
private List<CESalidaProductoDetalle> lstSalidaDetEliminar =new ArrayList<CESalidaProductoDetalle>();
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        if(pAccionABM==2)
        {
        CESalidaProductoDetalle oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(TblSalidaProductoDetalle.getSelectedRow(), oCLSalidaProducto.colCodigo);
            if(oCESalidaProductoDetalle!=null&&oCESalidaProductoDetalle.getIdSalidaProductoDetalle()!=0)
            {
                oCESalidaProductoDetalle.setCantidad(0);
                lstSalidaDetEliminar.add(oCESalidaProductoDetalle);
            }
        }
        oCLSalidaProducto.QuitarFila();
    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
     
eventoCancelar();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void eventoCancelar()
    {
          if(pAccionABM==1||pAccionABM==2)
       {
            CbxTipoSalida.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            LblHoraRegistro1.setText("");
            pAccionABM=0;
            CbxTipoSalida.setEnabled(false);
            CbxTipoSalida.setSelectedIndex(0);
            CbxTipoComprobante.setSelectedIndex(0);
            limpiarFormulario();
            TxtNumComp.setText("");
            LblMensaje.setText("");
            TxtObservacion.setText("");
            limpiarCliente();
            oCLSalidaProducto.limpiarTabla();
            habilitarContorlesEnVenta();
            CbxAlmacen.setSelectedIndex(0);
            CbxAlmacen.setEnabled(false);
       }else{
           iscerrando=true;
             dispose();
            }
    }
    private void CbxTipoSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoSalidaActionPerformed

        limpiarFormulario();
        idCliente=0;
        CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
        if(oCETipoIngresoSalida!=null){
        if(CbxTipoSalida.isEnabled()){
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==7)
        {        
        eventoNuevo();
        if(TblSalidaProductoDetalle.getRowCount()==0){
        agregarNuevaFila(0);
            }
        TblSalidaProductoDetalle.setEnabled(true);        
        TxtProducto.setEnabled(true);
        CbxTipoComprobante.setSelectedIndex(0);
        TblSalidaProductoDetalle.requestFocus();
        TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto, false, false);
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        TxtNumComp.setEditable(true);
        TxtCliente.setEditable(true);
        CbxTipoComprobante.setEnabled(true);
        }        
        else if( oCETipoIngresoSalida.getIdTipoIngresoSalida() == 8)
        {
        idCliente=1;       
        eventoNuevo();
        if(TblSalidaProductoDetalle.getRowCount()==0){
        agregarNuevaFila(0);
        }
        TblSalidaProductoDetalle.setEnabled(true);        
        TxtProducto.setEnabled(true);
        TblSalidaProductoDetalle.requestFocus();
        TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto, false, false);
        habilitarContorlesEnVenta();
        TxtCliente.setText("");
        TxtDNI.setText("");
        TxtDireccion.setText("");
        idCliente=1;
        BtnAgregar.setEnabled(true);
        BtnQuitar.setEnabled(true);
        CbxTipoComprobante.setSelectedIndex(6);
        TxtObservacion.setEnabled(true);
         }
        else{
            if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==1)
            {
            habilitarContorlesEnVenta();            
            }
            BtnAgregar.setEnabled(false);
            BtnQuitar.setEnabled(false);
            limpiarFormulario();
            oCLSalidaProducto.limpiarTabla();
        }
        
      }
        CbxAlmacen.setEnabled(true);
      }
            
    }//GEN-LAST:event_CbxTipoSalidaActionPerformed

    private void habilitarContorlesEnVenta()
     {
            CbxTipoComprobante.setEnabled(false);
            habilitarControles(false);
            idCliente=0;
    }
    private void CbxTipoSalidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoSalidaItemStateChanged

        
    }//GEN-LAST:event_CbxTipoSalidaItemStateChanged

    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila){
         try{
            colValidada=-1;

            float cantidad=1;
            float precio=1;
            float stock=1;
            if(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colCantidad)!=null){
                cantidad=Float.parseFloat(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colCantidad).toString());
                if(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colStock)!=null){
                    stock=Float.parseFloat(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colStock).toString());
                    if(cantidad>stock)
                    {
                        JOptionPane.showMessageDialog(this,"La cantidad debe ser mayor al Stock");
                        TblSalidaProductoDetalle.setValueAt(null,fila,oCLSalidaProducto.colCantidad);
                        colValidada=oCLSalidaProducto.colCantidad;
                         return;
                    }
                }
            }
            if(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colPrecio)!=null){
                precio=Float.parseFloat(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colPrecio).toString());
            }
            



        if(cantidad<=0)
            {
                JOptionPane.showMessageDialog(this,"La cantidad debe ser mayor a cero");
                TblSalidaProductoDetalle.setValueAt(null,fila,oCLSalidaProducto.colCantidad);
                colValidada=oCLSalidaProducto.colCantidad;
                 return;
            }            

        int col=TblSalidaProductoDetalle.getSelectedColumn();
                if(!eventoGuardar){
                if(col==oCLSalidaProducto.colCantidad){
                    oCLSalidaProducto.calcularPrecio(fila);
                }
             }
            
            }catch(Exception e)
            {
                cont++;
               // System.out.println(e+"metodo Verificar datos");
            }
    }


private String Codigo;

    
    public void SetSalidaProducto(CESalidaProducto oCESalidaProducto)
    {
        oCLSalidaProducto.limpiarTabla();
        isNuevoConDoc=true;
        this.Codigo=TxtNumSalida.getText();
        if(oCESalidaProducto!=null)
        {
            CbxTipoSalida.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalidaTodo));
            realizarEventoCbxAlmacen=false;
            TxtEstado.setText(oCESalidaProducto.getUltimoEstado());
            IdEstado=oCESalidaProducto.getUltimoIdEstado();
            IdSalidaProducto=oCESalidaProducto.getIdSalidaProducto();
            idCliente=oCESalidaProducto.getIdCliente();
            TxtCliente.setText(oCESalidaProducto.getCliente());
            TxtDNI.setText(oCESalidaProducto.getDNI());
            TxtDireccion.setText(oCESalidaProducto.getDireccion());
            TxtNumComp.setText(oCESalidaProducto.getNumeroDocumento());
            TxtNumSalida.setText(oCESalidaProducto.getNumeroSalida());
            TxtProducto.setText("");
            TxtSucursal.setText(oCESalidaProducto.getSucursal());
            TxtVendedor.setText(oCESalidaProducto.getEmpleado());
            LblHoraRegistro1.setText(oCESalidaProducto.getFecha());
            TxtObservacion.setText(oCESalidaProducto.getObservacion());
            CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoSalida,oCESalidaProducto.getIdTipoIngresoSalida());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCESalidaProducto.getIdTipoComprobante());
            CLCargarCombo.buscarIdAlmacen(CbxAlmacen, oCESalidaProducto.getIdAlmacen());
            oCLSalidaProducto.limpiarTabla();
            if(oCESalidaProducto.getLstSalidaDetalle()!=null)
            {
               for(int i=0; i<oCESalidaProducto.getLstSalidaDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblSalidaProductoDetalle.getModel()).addRow(oVector);
                
                CESalidaProductoDetalle oCESalidaProductoDetalle=oCESalidaProducto.getLstSalidaDetalle().get(i) ;

                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCESalidaProductoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCESalidaProductoDetalle.getIdUnidadMedida());
                oCESalidaProductoDetalle.setCantidadAnterior(oCESalidaProductoDetalle.getCantidad());
              
                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle,i,oCLSalidaProducto.colCodigo);
                TxtProducto.setText(oCESalidaProductoDetalle.getProducto());
                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle.getProducto(),i,oCLSalidaProducto.colProducto);
                TblSalidaProductoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLSalidaProducto.colUnidadMedida);
                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle.getCantidad(),i,oCLSalidaProducto.colCantidad);
                TblSalidaProductoDetalle.setValueAt(true,i,oCLSalidaProducto.colIsEdit);
                }
            }
            idCliente=oCESalidaProducto.getIdCliente();
            habilitarControles(false);
            CbxAlmacen.setEnabled(false);
            CbxTipoSalida.setEnabled(false);
            realizarEventoCbxAlmacen=true;
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            TblSalidaProductoDetalle.setEnabled(false);
            TblSalidaProductoDetalle.clearSelection();            

            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=2;
            CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8||oCETipoIngresoSalida.getIdTipoIngresoSalida()==7){
            LblMensaje.setText("");
            btnEditar.requestFocus();
            }else
            {
             LblMensaje.setText("AVISO : El Tipo Salida: "+oCETipoIngresoSalida.getDescripcion()+" no se puede editar, ni anular.");
             oclBotonesABM.controlBoton(true, false, false, false, true, false);
            }
            
        }
    else
        {
          JOptionPane.showMessageDialog(this,"El codigo no Existe");
          TxtNumSalida.requestFocus();
        }
    }
    
    private void TxtClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtClienteMouseClicked

        if(evt.getClickCount()==2) {
            buscarCliente();
        }
}//GEN-LAST:event_TxtClienteMouseClicked

    private void TxtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER) {
            buscarCliente();
            
        }
}//GEN-LAST:event_TxtClienteKeyPressed
private void eventoNuevo()
    {       
       isNuevoConDoc=false;
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);       
       CbxAlmacen.setSelectedIndex(0);
       CbxTipoComprobante.setSelectedIndex(0);
       TblSalidaProductoDetalle.requestFocus();
       TblSalidaProductoDetalle.changeSelection(0,oCLSalidaProducto.colProducto, false, false);
       limpiarFormulario();
       oCLSalidaProducto.setColumnEditable(new int[]{oCLSalidaProducto.colCantidad,oCLSalidaProducto.colProducto});
       
}
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
    eventoNuevo();
    CbxTipoSalida.setModel(CLComboBox.cargarCombo(listCETipoIngresoSalida));
    CbxTipoSalida.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

      oclBotonesABM.controlBoton(true, false, false, false, true, false);

       CESalidaProducto oCESalidaProducto= getSalidaProducto();
        oCESalidaProducto.setIdSalidaProducto(IdSalidaProducto);
        oCESalidaProducto.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCESalidaProducto.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCESalidaProducto.setCliente(TxtCliente.getText());
        oCESalidaProducto.setNumeroSalida(TxtNumSalida.getText());


        DlgAnularSalidaProducto oDlgAnularSalidaProducto= new DlgAnularSalidaProducto(null,true , oCESalidaProducto);
        oDlgAnularSalidaProducto.setLocationRelativeTo(null);
        oDlgAnularSalidaProducto.setVisible(true);
        if(oDlgAnularSalidaProducto.isAnulado()){

                       if(validarAntesDeAnular())
                        {
                           if(CCSalidaProducto.AnularSalidaProducto(oCESalidaProducto)==1)
                             {
                                limpiarFormulario();
                                pAccionABM=0;
                                habilitarControles(false);
                                oclBotonesABM.controlBoton(true, false, false, false, true, false);
                                JOptionPane.showMessageDialog(null,"<html>Operación exitosa");
                                CbxTipoSalida.setModel(CLComboBox.cargarCombo(CCTipoIngresoSalida.consultarListaTipoSalidasProductos()));
                                btnNuevo.requestFocus();
                            }else
                           {
                                JOptionPane.showMessageDialog(null,"<html>Operación Fallida");
                           }
                       }

              }
                
    }//GEN-LAST:event_btnAnularActionPerformed


    private boolean validarAntesDeAnular()
    {

        if(pAccionABM==2){
        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoSalida(IdSalidaProducto);
            if(oCEEstadoInvent.getmIntIdEstado()==4)
            {
             JOptionPane.showMessageDialog(this,"No se Puede Guardar, el Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+"","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }
        if(!VerificarAnulacion())
        {
            JOptionPane.showMessageDialog(null,"<html>Operación Fallida, solo se puede realizar esta operación,<br>"
                                                + "si todos los productos registrados son los ultimos movimientos en el almacen<html>");
               oCLSalidaProducto.pintarTabla();
            return false;
        }
        return true;
    }
    private boolean VerificarAnulacion()
    {
        int row=TblSalidaProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CESalidaProductoDetalle oCESalidaProductoDetalle=(CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);

            CESalidaProductoDetalle oCESalidaProductoDetalleTemp=CCSalidaProducto.VerificarUltimoMovimientoSalida(oCESalidaProductoDetalle.getIdProducto(),
                                                                    oCESalidaProductoDetalle.getIdAlmacen());
            if(oCESalidaProductoDetalleTemp!=null){
            if(!oCESalidaProductoDetalleTemp.isSalida())
            {
                TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
                return false;

            }
            else if (oCESalidaProductoDetalle.getIdSalidaProductoDetalle()!=oCESalidaProductoDetalleTemp.getIdSalidaProductoDetalle())
            {
                TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
                
                return false;
            }
          }

        }


        oCLSalidaProducto.pintarTabla();
      //  alinearColumnas();
        return true;
    }
    private void VerificarUltimosMovimiento()
    {
        int row=TblSalidaProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CESalidaProductoDetalle oCESalidaProductoDetalle=(CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);

            CESalidaProductoDetalle oCESalidaProductoDetalleTemp=CCSalidaProducto.VerificarUltimoMovimientoSalida(oCESalidaProductoDetalle.getIdProducto(),
                                                                    oCESalidaProductoDetalle.getIdAlmacen());
            if(oCESalidaProductoDetalleTemp!=null){
            if(!oCESalidaProductoDetalleTemp.isSalida())
            {
                TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
            }
            else if (oCESalidaProductoDetalle.getIdSalidaProductoDetalle()!=oCESalidaProductoDetalleTemp.getIdSalidaProductoDetalle())
            {
                TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
            }
          }

        }
        oCLSalidaProducto.pintarTabla();
      //  alinearColumnas();
    }
    private String VerificarUltimosCambiosDeMovimiento()
    {
        String Mensaje="";
        boolean isedit=true;
        int row=TblSalidaProductoDetalle.getRowCount();
        for (int i = 0; i < row; i++)
        {
            CESalidaProductoDetalle oCESalidaProductoDetalle=(CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);

            CESalidaProductoDetalle oCESalidaProductoDetalleTemp=CCSalidaProducto.VerificarUltimoMovimientoSalida(oCESalidaProductoDetalle.getIdProducto(),
                                                                    oCESalidaProductoDetalle.getIdAlmacen());
            if(oCESalidaProductoDetalleTemp!=null&&oCESalidaProductoDetalle.getIdSalidaProductoDetalle()!=0){
              isedit=true;
              boolean estadoAnte=VerificadorDeTxt.convertBoolean(TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colIsEdit));
            if(!oCESalidaProductoDetalleTemp.isSalida())
            {
                TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
                isedit=false;

            }
            else if(oCESalidaProductoDetalle.getIdSalidaProductoDetalle()!=oCESalidaProductoDetalleTemp.getIdSalidaProductoDetalle())
            {
              TblSalidaProductoDetalle.setValueAt(false, i, oCLSalidaProducto.colIsEdit);
                isedit=false;
            }
            if(!isedit){
              if(estadoAnte)
                {
                  TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle.getCantidadAnterior(), i, oCLSalidaProducto.colCantidad);
                  TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle.getCosto(), i, oCLSalidaProducto.colPrecio);
                  Mensaje=Mensaje+oCESalidaProductoDetalle.getProducto()+" - "+oCESalidaProductoDetalle.getUnidadMedida()+"\n";
                }
            }
          }

        }
        oCLSalidaProducto.pintarTabla();
      //  alinearColumnas();
        return Mensaje;
    }

    private void actualizarStock()
    {
        for( int i =0;i<TblSalidaProductoDetalle.getRowCount();i++)
            {

               CESalidaProductoDetalle oCESalidaProductoDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(i, oCLSalidaProducto.colCodigo);
                if(oCESalidaProductoDetalle!=null){
                    float stock=CCPedido.StockProductoPorPedido(oCESalidaProductoDetalle.getIdProducto(), oCESalidaProductoDetalle.getIdAlmacen());
                    float StockEqui=stock/(oCESalidaProductoDetalle.getCantidadBase()/oCESalidaProductoDetalle.getCantidad());
                    TblSalidaProductoDetalle.setValueAt(CLRedondear.Redondear(StockEqui+oCESalidaProductoDetalle.getCantidad(),2), i, oCLSalidaProducto.colStock);
                }
           }
    }
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         
//         CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
//        if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8||oCETipoIngresoSalida.getIdTipoIngresoSalida()==7){
//            habilitarControles(true);
//            BtnAgregar.setEnabled(false);
//            BtnQuitar.setEnabled(false);
//            if(oCETipoIngresoSalida.getIdTipoIngresoSalida()==8)
//            {
//                TxtCliente.setEnabled(false);
//                TxtNumComp.setEnabled(false);
//                CbxTipoComprobante.setEnabled(false);
//            }
//            TblSalidaProductoDetalle.setEnabled(true);
//            LblMensaje.setText("");
//            CbxAlmacen.setEnabled(false);
//            CbxTipoSalida.setEnabled(false);
//            VerificarUltimosMovimiento();
//            actualizarStock();
//            TblSalidaProductoDetalle.requestFocus();
//            TblSalidaProductoDetalle.changeSelection(0,oCLSalidaProducto.colProducto, false, false);
//            oclBotonesABM.controlBoton(false, true, false, true, true, false);
//            oCLSalidaProducto.setColumnEditable(new int[]{oCLSalidaProducto.colCantidad});
//            pAccionABM=2;
//            }else
//            {
//             LblMensaje.setText("AVISO : El Tipo Salida: "+oCETipoIngresoSalida.getDescripcion()+" no se puede editar, ni anular.");
//            }
         
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

    private void TblSalidaProductoDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblSalidaProductoDetalleKeyReleased

        if(TblSalidaProductoDetalle.getSelectedColumn()==oCLSalidaProducto.colCantidad+1&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblSalidaProductoDetalle.getSelectedRow()+1==TblSalidaProductoDetalle.getRowCount()){
                int fila=TblSalidaProductoDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    CETipoIngresoSalida oCETipoIngresoSalida=(CETipoIngresoSalida)CbxTipoSalida.getSelectedItem();
                     if(oCETipoIngresoSalida.getIdTipoIngresoSalida()!=1)
                        {
                        if(pAccionABM==1){
                            agregarNuevaFila(fila);
                            TblSalidaProductoDetalle.requestFocus();
                            TblSalidaProductoDetalle.changeSelection(fila, oCLSalidaProducto.colProducto, false, false);
                        }
                        }
                }else{
                    TblSalidaProductoDetalle.requestFocus();
                    TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if(colValidada!=-1){

            TblSalidaProductoDetalle.requestFocus();
            TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }



    }//GEN-LAST:event_TblSalidaProductoDetalleKeyReleased

    private void TblSalidaProductoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblSalidaProductoDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblSalidaProductoDetalle.getSelectedColumn()==oCLSalidaProducto.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
             

                evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

                
//                if(TblSalidaProductoDetalle.getSelectedColumn()==oCLSalidaProducto.colStock){
//                    TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getSelectedRow(), oCLSalidaProducto.colStock+1, false, false);
//                 }
        }


    }//GEN-LAST:event_TblSalidaProductoDetalleKeyPressed

    private void verficarTextoVacio(int fila){
        try{
       int col =TblSalidaProductoDetalle.getSelectedColumn();
                if(fila!=-1)
                   {
                   if(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colProducto)!=null){
                       String producto=TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colCodigo)!=null){

                               TblSalidaProductoDetalle.setValueAt(((CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colCodigo)).getProducto(),fila, oCLSalidaProducto.colProducto);
                           }

                       }
                    }
                }
        }
       catch(Exception e)
        {

       }
    }
    private void TblSalidaProductoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblSalidaProductoDetallePropertyChange
        if(!iscerrando){
            if(cont==1){

                int fila=TblSalidaProductoDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if(fila!=-1){
                    verificarDatos(fila);}
                cont=0;
            }else{
                cont=0;
            }
            cont++;
        }
}//GEN-LAST:event_TblSalidaProductoDetallePropertyChange

    private void TblSalidaProductoDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblSalidaProductoDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {

            }
}//GEN-LAST:event_TblSalidaProductoDetalleMousePressed

    private void TblSalidaProductoDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblSalidaProductoDetalleMouseClicked

    }//GEN-LAST:event_TblSalidaProductoDetalleMouseClicked


 
           


    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged
        // TODO add your handling code here:
}//GEN-LAST:event_CbxTipoComprobanteItemStateChanged

    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void btnGuardarActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed1
     eventoGuardarSalida();
    }//GEN-LAST:event_btnGuardarActionPerformed1


    private void eventoGuardarSalida()
    {
     if(btnGuardar.isEnabled()){
      int fil=TblSalidaProductoDetalle.getRowCount();
      if(fil>0){
              int row=fil-1;
              CESalidaProductoDetalle oCESalidaDetalle= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(row, oCLSalidaProducto.colCodigo);
              if(oCESalidaDetalle==null){
                oCLSalidaProducto.QuitarFilaSel(row);
              }
        }
      fil=TblSalidaProductoDetalle.getRowCount();
      if(fil>0){
              eventoGuardar=true;
              TblSalidaProductoDetalle.editingStopped(null);
              btnGuardar.requestFocus();
              int columnaValidada=ValidarRegistro(TblSalidaProductoDetalle.getRowCount());
              if(columnaValidada==0)
              {
              TblSalidaProductoDetalle.requestFocus();
              TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getRowCount(), oCLSalidaProducto.colProducto, false, false);
              EventoGuardar();
              }else
              {
                  TblSalidaProductoDetalle.requestFocus();
                  TblSalidaProductoDetalle.changeSelection(TblSalidaProductoDetalle.getRowCount()-1, columnaValidada, false, false);
              }
          }
        else
        {
               JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                 agregarNuevaFila(0);
                 TblSalidaProductoDetalle.requestFocus();
                 TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto, false, false);
        }

      }
    }
    private void CbxAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxAlmacenMouseClicked

}//GEN-LAST:event_CbxAlmacenMouseClicked

    private boolean realizarEventoCbxAlmacen;
    private void CbxAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxAlmacenActionPerformed

        if(realizarEventoCbxAlmacen){
        oCLSalidaProducto.limpiarTabla();
        agregarNuevaFila(0);
        TblSalidaProductoDetalle.requestFocus();

        }
    }//GEN-LAST:event_CbxAlmacenActionPerformed

    private void btnBuscarCompCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompCompActionPerformed

        DlgBusquedaSalidaAlmacen odialogo=new DlgBusquedaSalidaAlmacen(oparent, true);
        odialogo.setVisible(true);
        CESalidaProducto oCEIngresoProducto= odialogo.getCESalidaProducto();

        if(oCEIngresoProducto!=null) {

            SetSalidaProducto(CCSalidaProducto.ConsultarPorId(oCEIngresoProducto.getIdSalidaProducto()));
        }
}//GEN-LAST:event_btnBuscarCompCompActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed
        CLExportarExcel oExportar=new CLExportarExcel(TblSalidaProductoDetalle,
                new String[]{LblEtiqTipoIngreso.getText(),((CETipoIngresoSalida)CbxTipoSalida.getSelectedItem()).getDescripcion(),
                             lblEtiqNumeroSalida.getText(),TxtNumSalida.getText(),lblEtiqEstado.getText(),TxtEstado.getText(),
                             lblEtiqTipoComp.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                             LblEtiqAlmacen.getText(),((CEAlmacen)CbxAlmacen.getSelectedItem()).getDescripcion(),LblEtiqCliente.getText(),TxtCliente.getText(),
                             lblEtiqDireccion.getText(),TxtDireccion.getText(),LblEtiqDNI.getText(),TxtDNI.getText(),lblEtiqSucursal.getText(),TxtSucursal.getText(),
                             lblEtiqVendedor.getText(),TxtVendedor.getText(),lblEtiqHoraRegistro.getText(),LblHoraRegistro1.getText()}
                ,new String[]{"Observacion",TxtObservacion.getText()},new String[]{},"Salida de Productos");
        oExportar.GuardarArchivoExcel(this);
    }//GEN-LAST:event_btnExportar1ActionPerformed

public void SetSalidaProductoPorPedido(CEPedidoMatriz oCEPedidoMatriz)
    {
        oCLSalidaProducto.limpiarTabla();
        this.Codigo=TxtNumSalida.getText();
        if(oCEPedidoMatriz!=null)
        {
            CbxTipoSalida.setEnabled(false);
         //   LblEstado.setText(oCESalidaProductoMatriz.getUltimoEstado());
            IdEstado=oCEPedidoMatriz.getIdUltimoEstado();
            IdSalidaProducto=0;
            idCliente=oCEPedidoMatriz.getIdCliente();
            TxtCliente.setText(oCEPedidoMatriz.getCliente());
            TxtDNI.setText(oCEPedidoMatriz.getDNI());
            TxtDireccion.setText(oCEPedidoMatriz.getDireccion());
            TxtProducto.setText("");
            TxtNumComp.setText(oCEPedidoMatriz.getNumComprobante());
            TxtNumComp.setEnabled(false);
            float tipocambioTemp=oCEPedidoMatriz.getTipoCambio();
            CLCargarCombo.buscarIdTipoIngresoSalida(CbxTipoSalida,1);
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante, oCEPedidoMatriz.getIdTipoComprobante());

            if(oCEPedidoMatriz.getLstPedidoDetalle()!=null)
            {

               for(int i=0; i<oCEPedidoMatriz.getLstPedidoDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblSalidaProductoDetalle.getModel()).addRow(oVector);

                CEPedidoDetalle oCEPedidoDetalle=oCEPedidoMatriz.getLstPedidoDetalle().get(i) ;
                CESalidaProductoDetalle oCESalidaProductoDetalle=new CESalidaProductoDetalle();
                oCESalidaProductoDetalle.setIdAlmacen(oCEPedidoDetalle.getIdAlmacen());
                oCESalidaProductoDetalle.setIdProducto(oCEPedidoDetalle.getIdProducto());
                oCESalidaProductoDetalle.setIdUnidadMedida(oCEPedidoDetalle.getIdUnidadMedidaVenta());



                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEPedidoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEPedidoDetalle.getIdUnidadMedidaVenta());
                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle,i,oCLSalidaProducto.colCodigo);
                TxtProducto.setText(oCEPedidoDetalle.getProducto());
                TblSalidaProductoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),i,oCLSalidaProducto.colProducto);
                TblSalidaProductoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLSalidaProducto.colUnidadMedida);

                   if (oCEPedidoMatriz.getIdMoneda()!=1) {

                       float importeConDec=oCLSalidaProducto.convertirImporteMonedaExtranjeraaSoles(oCEPedidoDetalle.getPrecio(), tipocambioTemp
                                                                                                     ,oCEPedidoDetalle.getCantidad(), oCEPedidoDetalle.getDescuento()
                                                                                                     ,oCEPedidoDetalle.getIdTipoDescuento());
                       float costo=importeConDec/oCEPedidoDetalle.getCantidad();
                       costo=Float.parseFloat(CLRedondear.RedondearString(costo, 4));
                      TblSalidaProductoDetalle.setValueAt(costo,i,oCLSalidaProducto.colPrecio);
                      TblSalidaProductoDetalle.setValueAt(importeConDec,i,oCLSalidaProducto.colImporte);

                   }else
                   {
                      TblSalidaProductoDetalle.setValueAt(oCEPedidoDetalle.getPrecio(),i,oCLSalidaProducto.colPrecio);
                      TblSalidaProductoDetalle.setValueAt(oCEPedidoDetalle.getImporteConDescuento(),i,oCLSalidaProducto.colImporte);
                   }

                TblSalidaProductoDetalle.setValueAt(oCEPedidoDetalle.getCantidad(),i,oCLSalidaProducto.colCantidad);

                }
            }

          
           habilitarControles(false);
            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=1;
            
            btnGuardar.requestFocus();
        }
    else
        {
          JOptionPane.showMessageDialog(this,"El codigo no Existe");
        }
    }
    public void SetFormularioPorPedido(String pcodigo)
    {

       TxtNumSalida.setText(pcodigo);
    }
    private void agregarSalidaProductoDetalle(CEAlmacenProducto oCEAlmacenProducto)
    {
         int fila=TblSalidaProductoDetalle.getSelectedRow();
        if(oCEAlmacenProducto!=null){

        CESalidaProductoDetalle oCESalidaProductoDetalle= new CESalidaProductoDetalle();
        oCESalidaProductoDetalle.setProducto(oCEAlmacenProducto.getDescripcion());
        oCESalidaProductoDetalle.setIdProducto(oCEAlmacenProducto.getIdProducto());
        oCESalidaProductoDetalle.setIdAlmacen(oCEAlmacenProducto.getIdAlmacen());
        oCESalidaProductoDetalle.setIdUnidadMedida(oCEAlmacenProducto.getIdUnidadBase());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEAlmacenProducto.getUnidad_medida());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCESalidaProductoDetalle.setCodigoProducto(oCEAlmacenProducto.getCodigo());
        if(pAccionABM==2)
        {
             CESalidaProductoDetalle oCESalidaDetalleaux= (CESalidaProductoDetalle)TblSalidaProductoDetalle.getValueAt(fila, oCLSalidaProducto.colCodigo);
             if(oCESalidaDetalleaux!=null)
             {
                 oCESalidaProductoDetalle.setIdSalidaProductoDetalle(oCESalidaDetalleaux.getIdSalidaProductoDetalle());
             }
        }



                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle,fila,oCLSalidaProducto.colCodigo);
                TblSalidaProductoDetalle.setValueAt(oCESalidaProductoDetalle.getProducto(),fila,oCLSalidaProducto.colProducto);
                TblSalidaProductoDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLSalidaProducto.colUnidadMedida);
                TblSalidaProductoDetalle.setValueAt(oCEAlmacenProducto.getCostoUnitario(), fila, oCLSalidaProducto.colPrecio);
                TblSalidaProductoDetalle.setValueAt(oCEAlmacenProducto.getStockReal(), fila, oCLSalidaProducto.colStock);
                TblSalidaProductoDetalle.setValueAt(true, fila, oCLSalidaProducto.colIsEdit);

        }
        
    }
    private void limpiarFormulario()
    {

      TxtEstado.setText("EMITIDO");
      jTextFieldNumber.setText("");
      TxtProducto.setText("");
   
      oCLSalidaProducto.limpiarTabla();
      TblSalidaProductoDetalle.requestFocus();
      TblSalidaProductoDetalle.changeSelection(0, oCLSalidaProducto.colProducto, false, false);
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
    }

    private void limpiarCliente()
    {
      TxtCliente.setText("");
      TxtDireccion.setText("");
      TxtDNI.setText("");
      idCliente=0;
    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxAlmacen;
    private ComboBox.ComboBox CbxTipoComprobante;
    private ComboBox.ComboBox CbxTipoSalida;
    private javax.swing.ButtonGroup GrRbtnNumSalida;
    private javax.swing.ButtonGroup GrRbtnNumdoc;
    private Label.Label LblEtiqAlmacen;
    private Label.Label LblEtiqCliente;
    private Label.Label LblEtiqDNI;
    private Label.Label LblEtiqTipoIngreso;
    private Label.Label LblHoraRegistro1;
    private Label.Label LblHoraSistema;
    private javax.swing.JLabel LblMensaje;
    private Label.Label LblUltimoNumSalida;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblSalidaProductoDetalle;
    private TextField.JTxtLetra TxtCliente;
    private Label.Label TxtDNI;
    private Label.Label TxtDireccion;
    private Label.Label TxtEstado;
    private TextField.JTxtNinguno TxtNumComp;
    private Label.Label TxtNumSalida;
    private javax.swing.JTextArea TxtObservacion;
    private TextField.JTxtNinguno TxtProducto;
    private Label.Label TxtSucursal;
    private Label.Label TxtVendedor;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarCompComp;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
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
    private Label.Label label37;
    private Label.Label lblEtiqDireccion;
    private Label.Label lblEtiqEstado;
    private Label.Label lblEtiqHoraRegistro;
    private Label.Label lblEtiqNumComp;
    private Label.Label lblEtiqNumeroSalida;
    private Label.Label lblEtiqSucursal;
    private Label.Label lblEtiqTipoComp;
    private Label.Label lblEtiqVendedor;
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
             eventoGuardarSalida();
            
              return;
        }

  
    }

}
