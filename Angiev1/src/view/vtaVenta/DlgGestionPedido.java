package view.vtaVenta;

import controller.acceso.ConexionBD;
import controller.almAlmacen.CCProductoPrecio;
import controller.grlGeneral.CCAtencionSistema;
import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCCondicion;
import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCPedido;
import controller.vtaVenta.CCProforma;
import controller.vtaVenta.CCTipoComprobante;
import controller.vtaVenta.CCTipoDescuento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEAlmacenProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.cmrComercial.entidad.CECliente;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CECondicion;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CEPedidoDetalle;
import modelo.vtaVenta.entidad.CEPedidoMatriz;
import modelo.vtaVenta.entidad.CEProformaMatriz;
import modelo.vtaVenta.entidad.CETipoComprobante;
import modelo.vtaVenta.entidad.CETipoDescuento;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.SetterGetter;
import util.clases.grlGeneral.VerificadorDeTxt;
import util.clases.vtaVenta.CLCargarCombo;
import util.clases.vtaVenta.CLImprimirComprobante;
import util.clases.vtaVenta.CLPedido;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProducto;
import view.almAlmacen.DlgBusquedaProductoResumen;
import view.cmrComercial.DlgGestionCliente;

/**
 *
 * @author Luiggi
 */
public class DlgGestionPedido extends DialogoPadre implements  ActionListener{

   private  long idCliente=0;
   private String Cliente="";
   private  long IdPedido=0;
   private  int IdProforma=0;
   private  int IdEstado=0;
   private  boolean codLecBarra;
   private MiReloj hilo;
   private  CLPedido oCLPedido;
   private  int pAccionABM=0;
   private  CLBotonesABM oclBotonesABM;
   private  List<CEPedidoDetalle> lstPedidoEliminados = new ArrayList<CEPedidoDetalle>();
   private java.awt.Frame oparent;
   private boolean guardarConFecha;

    /** Creates new form DlgGestionPedido */
    public DlgGestionPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        guardarConFecha=false;
        this.codLecBarra=false;
        this.setLocationRelativeTo(null);
        oparent=parent;
        TxtTipoCambio.setText("1.0");
        LblUltimoNumeroPedido.setText("");
        CbxUnidadMedida.setEditable(true);
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblPedidoDetalle.setSurrendersFocusOnKeystroke(true);
//        TblPedidoDetalle.setAutoCreateRowSorter(true);
        DtchFecha.setDate(new Date());
        DtchFecha.setEnabled(false);
        DtchFecha.setVisible(false);
        chkFecha.setVisible(false);

    }

    

    private void cargarUtilidades()
    {
        CbxTipoDescuentoPedido.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        oCLPedido = new CLPedido(TblPedidoDetalle,LblSubtotalNeto,LblIgv,
                                LblISC,LblMontoTotal,LblMontoTotalPagar,LblSubTotalNetoSinIgv,LblDescuentoValores,
                                LblSubtotalBruto,LblDescuentoSubtotal,LblDescuentoTotal,TxtDescuento,CbxTipoDescuentoPedido,lblMontoPercepcion,chkPercepcion);
        CbxTipoComprobante.setModel(CLComboBox.cargarCombo(CCTipoComprobante.listaAll()));
        CbxCondicion.setModel(CLComboBox.cargarCombo(CCCondicion.listaAll()));
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listarMonedaConTipoCambio()));
        CbxTipoDescuento.setModel(CLComboBox.cargarCombo(CCTipoDescuento.listaAll()));
        TblPedidoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        CbxTipoDescuento.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
        TableColumn columnaTxtTipoDescuento = TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colTipoDescuento);
        columnaTxtTipoDescuento.setCellEditor(new DefaultCellEditor(CbxTipoDescuento));
        TableColumn columnaUnidadMedida = TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colUnidadMedida);
        columnaUnidadMedida.setCellEditor(new DefaultCellEditor(CbxUnidadMedida));
        DefaultCellEditor oDefaultCellEditor=new DefaultCellEditor(CbxUnidadMedida);
        columnaUnidadMedida.setCellEditor(oDefaultCellEditor);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colCantidad).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colPrecio).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colImporConDesc).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colImporSinDesc).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colExonerado).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colDescuento).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colDestValores).setCellRenderer(tcr);
        TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colPrecioConDesc).setCellRenderer(tcr);
        TableColumn columnaCantidad = TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TableColumn columnaDescuento = TblPedidoDetalle.getColumnModel().getColumn(oCLPedido.colDescuento);
        columnaDescuento.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        CbxTipoDescuento.setSelectedIndex(0);
        CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();
        TblPedidoDetalle.setValueAt(oTipoDescuento, 0, oCLPedido.colTipoDescuento);
        TblPedidoDetalle.requestFocus();
        TblPedidoDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        pAccionABM=1;
//        if(FrmSistemaMenu.isSiNoAtencion())
//        {
        oclBotonesABM.controlBoton(false, true, false, false, true, false);
//        }
//        else
//        {
    //    oclBotonesABM.controlBoton(false, false, false, false, false, false);
        
        ((JTableX)TblPedidoDetalle).setSelectAllForEdit(true);
    }
        List<ArrayList<CEEquivalenciaUnidad>> LstLstEquivalencia= new ArrayList<ArrayList<CEEquivalenciaUnidad>>();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CbxTipoDescuento = new ComboBox.ComboBox();
        CbxUnidadMedida = new javax.swing.JComboBox();
        JmMenuTablaPedido = new javax.swing.JPopupMenu();
        JMAgregar = new javax.swing.JMenuItem();
        JMEliminar = new javax.swing.JMenuItem();
        JMVerPrecios = new javax.swing.JMenuItem();
        JMModificaPrecio = new javax.swing.JMenuItem();
        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        PnlDatos2 = new javax.swing.JPanel();
        LblEtiqCliente = new Label.Label();
        LblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        LblEtiqCondicion = new Label.Label();
        CbxCondicion = new ComboBox.ComboBox();
        CbxMoneda = new ComboBox.ComboBox();
        LblEtiqMoneda = new Label.Label();
        LblEtiqTipoCambio = new Label.Label();
        LblEtiqDNI = new Label.Label();
        LblEtiqDireccion = new Label.Label();
        TxtCliente = new TextField.JTxtLetra();
        TxtlHoraRegistro1 = new Label.Label();
        LblEtiqHoraRegistro = new Label.Label();
        lblEtiqVendedor = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtTipoCambio = new Label.Label();
        TxtDireccion = new Label.Label();
        TxtDNI = new Label.Label();
        jPanel1 = new javax.swing.JPanel();
        LblPorcentaje = new Label.Label();
        LblEtiqTipoDescuento = new Label.Label();
        CbxTipoDescuentoPedido = new ComboBox.ComboBox();
        LblEtiqDescuento = new Label.Label();
        LblEtiqDescTotal = new Label.Label();
        LblDescuentoTotal = new Label.Label();
        TxtDescuento = new javax.swing.JTextField();
        LblEtiqMontoTotal = new Label.Label();
        LblMontoTotal = new Label.Label();
        LblIgv = new Label.Label();
        LblEtiqIgv = new Label.Label();
        LblISC = new Label.Label();
        LblEtiqISC = new Label.Label();
        LblEtiqSubNetoSinIgv = new Label.Label();
        LblSubTotalNetoSinIgv = new Label.Label();
        LblDescuentoValores = new Label.Label();
        LblEtiqDescValores = new Label.Label();
        LblEtiqPercepcion = new Label.Label();
        LblEtiqTotalPagar = new Label.Label();
        LblMontoTotalPagar = new Label.Label();
        lblMontoPercepcion = new Label.Label();
        btnCancelar = new BotonesABM.BtnCancelar();
        btnExportar1 = new BotonesABM.BtnExportar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnEditar = new BotonesABM.BtnEditar();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnNuevo = new BotonesABM.BtnNuevo();
        jPanel3 = new javax.swing.JPanel();
        CbxTipoComprobante = new ComboBox.ComboBox();
        lblEtiqTipoComp = new Label.Label();
        btnImportarProforma = new BotonesABM.BtnBuscar();
        btnBuscarPedido = new BotonesABM.BtnBuscar();
        btnImportarProforma1 = new BotonesABM.BtnBuscar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPedidoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{

                    if(column==oCLPedido.colPrecio||column==oCLPedido.colPrecioConDesc)
                    {
                        if(value!=null)
                        {
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),4),row,column);
                            }
                            else{
                                super.setValueAt(null,row,column);
                            }
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else if(column==oCLPedido.colCantidad)
                    {
                        if(value!=null)
                        {
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                            }
                            else{
                                super.setValueAt(null,row,column);
                            }
                        }
                        else
                        {
                            super.setValueAt(null,row,column);

                        }

                    }
                    else if(column==oCLPedido.colDescuento||column==oCLPedido.colImporConDesc||column==oCLPedido.colImporSinDesc||column==oCLPedido.colExonerado||column==oCLPedido.colDestValores)
                    {
                        if(value!=null){
                            if(!value.toString().trim().equals("")||!value.toString().isEmpty())
                            {
                                super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                            }
                            else
                            {
                                super.setValueAt("0.00",row,column);
                            }
                        }else
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
        BtnEditarPrecio = new javax.swing.JButton();
        columnButton1 = new util.clases.vtaVenta.ColumnButton();
        LblHoraSistema = new Label.Label();
        BtnActualizar = new javax.swing.JButton();
        chkFecha = new Opcion.CheckBox();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        LblEtiqSubtotalBruto = new Label.Label();
        LblSubtotalBruto = new Label.Label();
        LblEtiqDsctSubtValores = new Label.Label();
        LblDescuentoSubtotal = new Label.Label();
        LblEtiqSubtotalNeto = new Label.Label();
        LblSubtotalNeto = new Label.Label();
        chkPercepcion = new Opcion.CheckBox();
        BtnQuitar = new javax.swing.JButton();
        LblUltimoNumeroPedido = new javax.swing.JTextField();
        LblEtiqNum = new Label.Label();
        lblEtiqEstado = new Label.Label();
        TxtEstado = new Label.Label();
        chkBusqDet = new Opcion.CheckBox();

        CbxTipoDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CbxTipoDescuentoMouseClicked(evt);
            }
        });

        CbxUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        JMAgregar.setFont(new java.awt.Font("Verdana", 0, 12));
        JMAgregar.setText("Agregar Nuevo Registro");
        JMAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMAgregarActionPerformed(evt);
            }
        });
        JmMenuTablaPedido.add(JMAgregar);

        JMEliminar.setFont(new java.awt.Font("Verdana", 0, 12));
        JMEliminar.setText("Eliminar Registro");
        JMEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMEliminarActionPerformed(evt);
            }
        });
        JmMenuTablaPedido.add(JMEliminar);

        JMVerPrecios.setFont(new java.awt.Font("Verdana", 0, 12));
        JMVerPrecios.setText("Ver Precios");
        JMVerPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMVerPreciosActionPerformed(evt);
            }
        });
        JmMenuTablaPedido.add(JMVerPrecios);

        JMModificaPrecio.setFont(new java.awt.Font("Verdana", 0, 12));
        JMModificaPrecio.setLabel("Modificar Precio");
        JMModificaPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMModificaPrecioActionPerformed(evt);
            }
        });
        JmMenuTablaPedido.add(JMModificaPrecio);

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
        setTitle("Pedidos");
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

        LblEtiqSucursal.setText("Sucursal   :");

        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEtiqCondicion.setText("Condicion :");

        CbxCondicion.setEnabled(false);
        CbxCondicion.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxCondicion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxCondicionItemStateChanged(evt);
            }
        });

        CbxMoneda.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxMonedaActionPerformed(evt);
            }
        });

        LblEtiqMoneda.setText("Moneda     :");

        LblEtiqTipoCambio.setText("Tipo Cambio    :");

        LblEtiqDNI.setText("DNI             :");

        LblEtiqDireccion.setText("Direccion  :");

        TxtCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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

        TxtlHoraRegistro1.setText("00/00/00");
        TxtlHoraRegistro1.setText("");

        LblEtiqHoraRegistro.setText("Fecha Registro:");

        lblEtiqVendedor.setText("Vendedor :");

        TxtSucursal.setText(" ");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtTipoCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TxtTipoCambio.setText("1.0");

        TxtDireccion.setText("---");

        javax.swing.GroupLayout PnlDatos2Layout = new javax.swing.GroupLayout(PnlDatos2);
        PnlDatos2.setLayout(PnlDatos2Layout);
        PnlDatos2Layout.setHorizontalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtlHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PnlDatos2Layout.setVerticalGroup(
            PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos2Layout.createSequentialGroup()
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblEtiqCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEtiqDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LblEtiqCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(CbxCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(LblEtiqMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(LblEtiqTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(TxtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(PnlDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnlDatos2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblEtiqHoraRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtlHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MONTOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel1.setLayout(null);

        LblPorcentaje.setText("V");
        LblPorcentaje.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblPorcentaje);
        LblPorcentaje.setBounds(440, 20, 20, 16);

        LblEtiqTipoDescuento.setText("TIPO DESCUENTO");
        LblEtiqTipoDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqTipoDescuento);
        LblEtiqTipoDescuento.setBounds(10, 20, 111, 15);

        CbxTipoDescuentoPedido.setFont(new java.awt.Font("Verdana", 0, 11));
        CbxTipoDescuentoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxTipoDescuentoPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(CbxTipoDescuentoPedido);
        CbxTipoDescuentoPedido.setBounds(130, 20, 100, 21);

        LblEtiqDescuento.setText("DESCUENTO :");
        LblEtiqDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescuento);
        LblEtiqDescuento.setBounds(240, 20, 84, 15);

        LblEtiqDescTotal.setText("DESC. TOTAL:");
        LblEtiqDescTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescTotal);
        LblEtiqDescTotal.setBounds(460, 20, 86, 15);

        LblDescuentoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoTotal.setText("0.00");
        LblDescuentoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoTotal);
        LblDescuentoTotal.setBounds(560, 20, 90, 19);

        TxtDescuento.setFont(new java.awt.Font("Verdana", 1, 11));
        TxtDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TxtDescuento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        TxtDescuento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtDescuentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtDescuentoFocusLost(evt);
            }
        });
        TxtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtDescuentoKeyReleased(evt);
            }
        });
        jPanel1.add(TxtDescuento);
        TxtDescuento.setBounds(330, 20, 100, 21);

        LblEtiqMontoTotal.setText("MONTO TOTAL ");
        LblEtiqMontoTotal.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqMontoTotal);
        LblEtiqMontoTotal.setBounds(660, 100, 130, 20);

        LblMontoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotal.setText("0.00");
        LblMontoTotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblMontoTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotal);
        LblMontoTotal.setBounds(790, 100, 120, 16);

        LblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblIgv.setText("0.00");
        LblIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblIgv);
        LblIgv.setBounds(790, 40, 120, 20);

        LblEtiqIgv.setText("I.G.V.");
        LblEtiqIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqIgv);
        LblEtiqIgv.setBounds(660, 40, 130, 20);

        LblISC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblISC.setText("0.00");
        LblISC.setFont(new java.awt.Font("Verdana", 1, 12));
        LblISC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblISC);
        LblISC.setBounds(790, 60, 120, 20);

        LblEtiqISC.setText("I.S.C.");
        LblEtiqISC.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqISC);
        LblEtiqISC.setBounds(660, 60, 130, 20);

        LblEtiqSubNetoSinIgv.setText("SUB. NT. SIN IGV");
        LblEtiqSubNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqSubNetoSinIgv);
        LblEtiqSubNetoSinIgv.setBounds(660, 20, 130, 20);

        LblSubTotalNetoSinIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubTotalNetoSinIgv.setText("0.00");
        LblSubTotalNetoSinIgv.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubTotalNetoSinIgv.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblSubTotalNetoSinIgv);
        LblSubTotalNetoSinIgv.setBounds(790, 20, 120, 20);

        LblDescuentoValores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoValores.setText("0.00");
        LblDescuentoValores.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoValores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblDescuentoValores);
        LblDescuentoValores.setBounds(790, 80, 120, 20);

        LblEtiqDescValores.setText("DESC. EN VALORES");
        LblEtiqDescValores.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqDescValores);
        LblEtiqDescValores.setBounds(660, 80, 130, 20);

        LblEtiqPercepcion.setText("PERCEPCIÓN");
        LblEtiqPercepcion.setFont(new java.awt.Font("Verdana", 1, 11));
        jPanel1.add(LblEtiqPercepcion);
        LblEtiqPercepcion.setBounds(660, 120, 130, 20);

        LblEtiqTotalPagar.setText("TOTAL A PAGAR ");
        LblEtiqTotalPagar.setFont(new java.awt.Font("Verdana", 1, 12));
        jPanel1.add(LblEtiqTotalPagar);
        LblEtiqTotalPagar.setBounds(660, 140, 130, 30);

        LblMontoTotalPagar.setForeground(new java.awt.Color(0, 0, 153));
        LblMontoTotalPagar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblMontoTotalPagar.setText("0.00");
        LblMontoTotalPagar.setFont(new java.awt.Font("Verdana", 1, 15));
        LblMontoTotalPagar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(LblMontoTotalPagar);
        LblMontoTotalPagar.setBounds(790, 140, 120, 30);

        lblMontoPercepcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoPercepcion.setText("0.00");
        lblMontoPercepcion.setFont(new java.awt.Font("Verdana", 1, 12));
        lblMontoPercepcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblMontoPercepcion);
        lblMontoPercepcion.setBounds(790, 120, 120, 20);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(460, 90, 90, 70);

        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExportar1);
        btnExportar1.setBounds(370, 90, 90, 70);

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnular);
        btnAnular.setBounds(280, 90, 90, 70);

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);
        btnEditar.setBounds(190, 90, 90, 70);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(100, 90, 90, 70);

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);
        btnNuevo.setBounds(10, 90, 90, 70);

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        lblEtiqTipoComp.setText("Comprobante :");

        btnImportarProforma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportarProforma.setText("Importar Proforma");
        btnImportarProforma.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportarProforma.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportarProforma.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportarProforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarProformaActionPerformed(evt);
            }
        });

        btnBuscarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarPedido.setText("Buscar Pedido");
        btnBuscarPedido.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPedido.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarPedido.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPedidoActionPerformed(evt);
            }
        });

        btnImportarProforma1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        btnImportarProforma1.setText("Importar Pedido");
        btnImportarProforma1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImportarProforma1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnImportarProforma1.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImportarProforma1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarProforma1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(btnImportarProforma1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportarProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblEtiqTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnBuscarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnImportarProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImportarProforma1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CbxTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblPedidoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblPedidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new Integer(1), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Codigo", "Producto", "Stock", "Cantidad", "U. M. ", "precio_sin_redon", "Precio", "Importe Bruto", "Tipo Desc.", "Descuento", "Dsct. Val.", "Precio/ Desc.", "Importe Neto", "Exonerado", "Almacen", "ListaPrecio", "sinPrecio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false
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
        TblPedidoDetalle.setRowHeight(21);
        TblPedidoDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblPedidoDetalle.getTableHeader().setReorderingAllowed(false);
        TblPedidoDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPedidoDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblPedidoDetalleMousePressed(evt);
            }
        });
        TblPedidoDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblPedidoDetallePropertyChange(evt);
            }
        });
        TblPedidoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblPedidoDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblPedidoDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblPedidoDetalle);
        TblPedidoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TblPedidoDetalle.getColumnModel().getColumn(0).setPreferredWidth(28);
        TblPedidoDetalle.getColumnModel().getColumn(1).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(1).setPreferredWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(1).setMaxWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(2).setPreferredWidth(380);
        TblPedidoDetalle.getColumnModel().getColumn(3).setPreferredWidth(75);
        TblPedidoDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(6).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(6).setPreferredWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(6).setMaxWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(7).setPreferredWidth(85);
        TblPedidoDetalle.getColumnModel().getColumn(8).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(8).setPreferredWidth(90);
        TblPedidoDetalle.getColumnModel().getColumn(9).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(9).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(10).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(10).setPreferredWidth(70);
        TblPedidoDetalle.getColumnModel().getColumn(11).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(12).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(12).setPreferredWidth(80);
        TblPedidoDetalle.getColumnModel().getColumn(13).setResizable(false);
        TblPedidoDetalle.getColumnModel().getColumn(13).setPreferredWidth(95);
        TblPedidoDetalle.getColumnModel().getColumn(14).setPreferredWidth(95);
        TblPedidoDetalle.getColumnModel().getColumn(15).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(15).setPreferredWidth(70);
        TblPedidoDetalle.getColumnModel().getColumn(16).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(16).setPreferredWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(16).setMaxWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(17).setMinWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(17).setPreferredWidth(0);
        TblPedidoDetalle.getColumnModel().getColumn(17).setMaxWidth(0);

        label20.setText("PEDIDO DE PRODUCTOS");
        label20.setFont(new java.awt.Font("Verdana", 1, 12));

        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregar.setToolTipText("Agregar Item");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        BtnEditarPrecio.setFont(new java.awt.Font("Verdana", 1, 11));
        BtnEditarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/editarToolStripMenuItem.Image.png"))); // NOI18N
        BtnEditarPrecio.setText("Editar Precio");
        BtnEditarPrecio.setToolTipText("Editar Precio");
        BtnEditarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarPrecioActionPerformed(evt);
            }
        });

        columnButton1.setToolTipText("Ajustar Columnas");
        columnButton1.setTbl(TblPedidoDetalle);

        LblHoraSistema.setText("hora");
        LblHoraSistema.setFont(new java.awt.Font("Verdana", 1, 11));

        BtnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Refresh.gif"))); // NOI18N
        BtnActualizar.setToolTipText("Refrescar");
        BtnActualizar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        chkFecha.setText("Fecha");
        chkFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFechaActionPerformed(evt);
            }
        });

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });

        LblEtiqSubtotalBruto.setText("SubTotal Bruto:");
        LblEtiqSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalBruto.setForeground(new java.awt.Color(0, 0, 102));
        LblSubtotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalBruto.setText("0.00");
        LblSubtotalBruto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalBruto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqDsctSubtValores.setText("Dsct. Subt. en Valores:");
        LblEtiqDsctSubtValores.setFont(new java.awt.Font("Verdana", 1, 12));

        LblDescuentoSubtotal.setForeground(new java.awt.Color(0, 0, 102));
        LblDescuentoSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblDescuentoSubtotal.setText("0.00");
        LblDescuentoSubtotal.setFont(new java.awt.Font("Verdana", 1, 12));
        LblDescuentoSubtotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        LblEtiqSubtotalNeto.setText("SubTotal Neto");
        LblEtiqSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));

        LblSubtotalNeto.setForeground(new java.awt.Color(0, 0, 102));
        LblSubtotalNeto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblSubtotalNeto.setText("0.00");
        LblSubtotalNeto.setFont(new java.awt.Font("Verdana", 1, 12));
        LblSubtotalNeto.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        chkPercepcion.setText("PERCEPCIÓN");
        chkPercepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPercepcionActionPerformed(evt);
            }
        });

        BtnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/DeleteUser.png"))); // NOI18N
        BtnQuitar.setToolTipText("Quitar Item");
        BtnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQuitarActionPerformed(evt);
            }
        });

        LblUltimoNumeroPedido.setEditable(false);

        LblEtiqNum.setText("Nº Pedido :");
        LblEtiqNum.setFont(new java.awt.Font("Verdana", 1, 11));

        lblEtiqEstado.setText("Estado :");

        TxtEstado.setText("EMITIDO");
        TxtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        chkBusqDet.setForeground(new java.awt.Color(0, 0, 153));
        chkBusqDet.setText("Busq. detallada de producto");
        chkBusqDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBusqDetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(chkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DtchFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblEtiqNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblUltimoNumeroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(512, 512, 512)
                .addComponent(chkBusqDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BtnEditarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlDatos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(622, 622, 622))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(chkPercepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(622, 622, 622))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(622, 622, 622))
            .addGroup(layout.createSequentialGroup()
                .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(627, 627, 627))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblUltimoNumeroPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblEtiqNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblHoraSistema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(chkFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(PnlDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkBusqDet, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(BtnEditarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblEtiqSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblSubtotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblEtiqDsctSubtValores, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblDescuentoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblEtiqSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(LblSubtotalNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        columnButton1.getAccessibleContext().setAccessibleName("0");
        BtnActualizar.getAccessibleContext().setAccessibleName("0");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarCliente()
    {
        if(TxtCliente.isEnabled()){
          DlgGestionCliente odialogo= new DlgGestionCliente(oparent, true,1,1,1);//consultar
       
          odialogo.setCajaTexo(TxtCliente.getText());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Cliente");
          CECliente oCEClienteAux=odialogo.getoCECliente();
                  if(oCEClienteAux!=null){
                    idCliente=oCEClienteAux.getIdCliente();
                    Cliente=oCEClienteAux.getNombreCompleto();
                    TxtCliente.setText(oCEClienteAux.getNombreCompleto());
                    if(oCEClienteAux.getIdTipoPersona()==1){
                    TxtDNI.setText(oCEClienteAux.getDNI());
                    LblEtiqDNI.setText("DNI");
                    }else
                    {
                        TxtDNI.setText(oCEClienteAux.getRUC());
                        LblEtiqDNI.setText("RUC");
                    }
                    TxtDireccion.setText(oCEClienteAux.getDireccion());
                    TblPedidoDetalle.requestFocus();
                    TblPedidoDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
                    }
        }

      }
private void habilitarControles(boolean bol)
    {
    TxtCliente.setEnabled(bol);
    TblPedidoDetalle.setEnabled(bol);
    BtnAgregar.setEnabled(bol);
    BtnQuitar.setEnabled(bol);
    BtnEditarPrecio.setEnabled(bol);
    CbxTipoDescuentoPedido.setEnabled(bol);
    TxtDescuento.setEnabled(bol);
    CbxTipoComprobante.setEnabled(bol);
    CbxMoneda.setEnabled(bol);
    TxtProducto.setEnabled(bol);
    CbxMoneda.setEnabled(bol);
    chkPercepcion.setEnabled(bol);
}


private int ValidarRegistro(int fila)
    {

    try {
     if(TblPedidoDetalle.getRowCount()>0){
    
     int numColumna=0;
        CEPedidoDetalle oCEPedidoDetalle=(CEPedidoDetalle)TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCEPedidoDetalle!=null){
            if(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colCantidad)!=null)
            {
                float cantidad=Float.parseFloat(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLPedido.colCantidad;
                        colValidada=oCLPedido.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
                    float precio=0;
                        if(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colPrecio)!=null)
                        {
                        precio=Float.parseFloat(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colPrecio).toString());
                        }
                        if(precio==0){
                        numColumna = oCLPedido.colPrecio;
                        colValidada = oCLPedido.colCantidad;
                        mensaje="Ingrese un Precio";
                        }
                        if(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colDescuento)!=null)
                        {                           
                            
                            float importesinDesc=VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colImporSinDesc));
                            float descuento=VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(fila-1, oCLPedido.colDestValores));
                            if(descuento>importesinDesc)
                                {
                                    mensaje="El descuento no puede ser mayor al importe.";
                                    numColumna = oCLPedido.colDescuento;
                                    colValidada = oCLPedido.colDescuento;
                                }
                        }
                }
            }else{
                        numColumna = oCLPedido.colCantidad;
                        colValidada = oCLPedido.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLPedido.colProducto;
            colValidada=oCLPedido.colProducto;

        }
        if(numColumna!=0){
            if(numColumna!=oCLPedido.colPrecio){
            JOptionPane.showMessageDialog(this,mensaje);
            }else
                { int filat=TblPedidoDetalle.getSelectedRow();
                  oCLPedido.calcularPrecio(filat);
                }
            }

        return numColumna;
        
        }
    return 0;
        }
    catch (Exception e)
    {
        System.out.println("ValidarRegistro - DlgGestion Pedido : "+ e);
        return 0;
    }
}
private boolean validarbusqProd()
{
    if(TblPedidoDetalle.getRowCount()<=0)
        return false;
    if(!TblPedidoDetalle.isEnabled())
        return false;

    return true;

}
    private void BuscarProductoDetallado()
    {
        if(validarbusqProd()){
        CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
        
        DlgBusquedaProducto odialogo= new DlgBusquedaProducto(oparent, true,oCEMoneda,Float.parseFloat(TxtTipoCambio.getText()));
                String NombreProd=TxtProducto.getText().trim();
               if(NombreProd.equals(""))
               {
               if(TblPedidoDetalle.getValueAt(TblPedidoDetalle.getSelectedRow(), oCLPedido.colProducto)!=null)
                    {
                    NombreProd=TblPedidoDetalle.getValueAt(TblPedidoDetalle.getSelectedRow(), oCLPedido.colProducto)+"";
                    }
                }
               TxtProducto.setText("");
               odialogo.setConPercepcion(chkPercepcion.isSelected());
               odialogo.setListaPedido(this.ObtenerRegistrosAcuales());
               odialogo.setCajaTexto(NombreProd);               
               odialogo.setVisible(true);
               CEAlmacenProducto oCEAlmacenProducto=odialogo.getProductoAlmacen();
               if(!odialogo.getisagregarCantidadPedido()){
               agregarPedidoDetalle(oCEAlmacenProducto,odialogo.getListaPrecio());               
               int fila=TblPedidoDetalle.getSelectedRow();
               TblPedidoDetalle.requestFocus();
               TblPedidoDetalle.changeSelection(fila, oCLPedido.colStock, false, false);
                }else{
                   SeleccionarPedidoExistente(oCEAlmacenProducto);
                }

            }
        }
    }

    private void BuscarProducto()
    {
        if(chkBusqDet.isSelected())
        {
            BuscarProductoDetallado();
        }
        else
        {
            BuscarProductoResumido();
        }
    }
     private void BuscarProductoResumido()
    {
        if(validarbusqProd()){
        CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
        DlgBusquedaProductoResumen odialogo= new DlgBusquedaProductoResumen(oparent, true,oCEMoneda,Float.parseFloat(TxtTipoCambio.getText()));
        //DlgBusquedaProducto odialogo= new DlgBusquedaProducto(oparent, true,oCEMoneda,Float.parseFloat(TxtTipoCambio.getText()));
                String NombreProd=TxtProducto.getText().trim();
               if(NombreProd.equals(""))
               {
               if(TblPedidoDetalle.getValueAt(TblPedidoDetalle.getSelectedRow(), oCLPedido.colProducto)!=null)
                    {
                    NombreProd=TblPedidoDetalle.getValueAt(TblPedidoDetalle.getSelectedRow(), oCLPedido.colProducto)+"";
                    }
                }
               TxtProducto.setText("");
               odialogo.setConPercepcion(chkPercepcion.isSelected());
               odialogo.setListaPedido(this.ObtenerRegistrosAcuales());
               odialogo.setCajaTexto(NombreProd);
               odialogo.setVisible(true);
               CEAlmacenProducto oCEAlmacenProducto=odialogo.getProductoAlmacen();
               if(!odialogo.getisagregarCantidadPedido()){
               agregarPedidoDetalle(oCEAlmacenProducto,odialogo.getListaPrecio());
               int fila=TblPedidoDetalle.getSelectedRow();
               TblPedidoDetalle.requestFocus();
               TblPedidoDetalle.changeSelection(fila, oCLPedido.colStock, false, false);
                }else{
                   SeleccionarPedidoExistente(oCEAlmacenProducto);
                }

            }
        }
    }
    private void SeleccionarPedidoExistente(CEAlmacenProducto oCEAlmacenProducto)
    {
        CEPedidoDetalle oCEPedidoDetalle ;
        for( int i =0;i<TblPedidoDetalle.getRowCount();i++)
            {
//cx
                oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){
                    
                        if(oCEPedidoDetalle.getIdAlmacen()==oCEAlmacenProducto.getIdAlmacen()&&
                           oCEPedidoDetalle.getIdUnidadMedidaVenta()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCEPedidoDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblPedidoDetalle.requestFocus();
                            TblPedidoDetalle.changeSelection(i, oCLPedido.colStock, false, false);
                        }

                }
            }
    }
    private List<CEPedidoDetalle> ObtenerRegistrosAcuales()
    {
        List<CEPedidoDetalle> lstPedidoDetalleTemp = new ArrayList<CEPedidoDetalle>();
        CEPedidoDetalle oCEPedidoDetalle ;
        for( int i =0;i<TblPedidoDetalle.getRowCount();i++)
            {

                oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){
                if(i==TblPedidoDetalle.getSelectedRow())
                {
                    oCEPedidoDetalle.setIdAbm(4);// quiere decir que esta seleccionado
                }
                oCEPedidoDetalle.setPrecio(VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colPrecio).toString()));
                oCEPedidoDetalle.setCantidad(0);
                if(TblPedidoDetalle.getValueAt(i, oCLPedido.colCantidad)!=null){
                oCEPedidoDetalle.setCantidad(VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colCantidad).toString()));
                }
                CEPedidoDetalle oCEPedidoDetalleTemp=(CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                oCEPedidoDetalle.setIdProducto(oCEPedidoDetalleTemp.getIdProducto());
                lstPedidoDetalleTemp.add(oCEPedidoDetalle);
                
                }
            }
        lstPedidoDetalleTemp.addAll(lstPedidoEliminados);
        return lstPedidoDetalleTemp;
    }
    private boolean  ValidarTablaPedido()
     {

        for(int i=0; i<TblPedidoDetalle.getRowCount();i++)
        {
            verificarDatos(i);

            if(colValidada!=-1){
                TblPedidoDetalle.requestFocus();
                TblPedidoDetalle.changeSelection(i, colValidada, false, false);
                colValidada=-1;
                return false;
            }
        }


            eventoGuardar=false;
        return true;
    }
    boolean eventoGuardar=false;

    private void eventoGuardarPedido()
    {
       // this.setEnabled(false);
        if(btnGuardar.isEnabled()){
        verificarPercepcion();
        
        
        int fil=TblPedidoDetalle.getRowCount();
        if(fil>0){
          int row=fil-1;
              CEPedidoDetalle oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(row, oCLPedido.colCodigo);
              if(oCEPedidoDetalle==null){
                oCLPedido.QuitarFilaSel(row);
              }
         }

      oCLPedido.CalcularSubtotales();
      oCLPedido.calcularImportes();
      oCLPedido.eventoDescuento();

      fil=TblPedidoDetalle.getRowCount();
      if(fil>0){
      int columnaValidada=ValidarRegistro(TblPedidoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          
         eventoGuardar=true;
          GuardarRegistro();

      }else
      {
          TblPedidoDetalle.requestFocus();
          TblPedidoDetalle.changeSelection(TblPedidoDetalle.getRowCount()-1, columnaValidada, false, false);
      }
    }else
    {
           JOptionPane.showMessageDialog(this,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblPedidoDetalle.requestFocus();
             TblPedidoDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
    }

   }
  //  this.setEnabled(true);
    }

     public float ObtenerTotal()
    {

        
          Properties props = new Properties();
        try {
                props.load(ConexionBD.class.getResourceAsStream("PropiedadesDelSistema.properties"));
            } catch (IOException ex) {
                Logger.getLogger(CLImprimirComprobante.class.getName()).log(Level.SEVERE, null, ex);
            }
             int validar = Integer.parseInt(props.getProperty("ayn.valpedido"));

        float total=validar;
        if(TblPedidoDetalle.getRowCount()>0){
            for(int i=0;i<TblPedidoDetalle.getRowCount();i++)
            {

                CEPedidoDetalle oCEPedidoDetalle=(CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){

                           total=total+VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colImporConDesc));
                           total=total+VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colExonerado));
                        
                    
               }
            }
        }

        return total;
    }
    private boolean validar()
    {

        if(idCliente==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese un Cliente","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TblPedidoDetalle.clearSelection();
             TxtCliente.requestFocus();
             return false;
        }
        if(TblPedidoDetalle.getRowCount()==0)
        {
             JOptionPane.showMessageDialog(this,"Ingrese Pedido","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblPedidoDetalle.requestFocus();
             TblPedidoDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
             return false;
        }        

        float MontoCorrecto=VerificadorDeTxt.convertFloat(CLRedondear.RedondearString(ObtenerTotal(),2));
        float MontoSistema=VerificadorDeTxt.convertFloat(LblMontoTotal.getText());
         if(MontoCorrecto!=MontoSistema)
            {
              JOptionPane.showMessageDialog(this,"Error de sistema , la suma de importes no es el correcto. \n Monto Sistema: "+MontoSistema+"\nMonto Correcto:"+MontoCorrecto,"Mensaje de Error", JOptionPane.ERROR_MESSAGE);

             return false;
         }

        if(pAccionABM==2)
        {
            CEEstado oCEEstado=CCEstado.consultarUltimoestadoPedido(IdPedido);
            if(oCEEstado.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(this,"El pedido ya fue "+oCEEstado.getmStrDescripcion()+", No se Puede Modificar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }

        if(pAccionABM==1&&IdProforma!=0)
        {
            CEEstado oCEEstado=CCEstado.consultarUltimoestadoPedido(IdProforma);
            if(oCEEstado.getmIntIdEstado()==4)
            {
             JOptionPane.showMessageDialog(this,"La Proforma ya fue "+oCEEstado.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }
        return true;
    }

    private boolean verificarPercepcion()
    {
        String ProductosConPercepcion="";
        String ProductosSinPercepcion="";

        CEPedidoDetalle oCEPedidoDetalle=null;
        for(int i=0; i<TblPedidoDetalle.getRowCount();i++)
            {
                oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){
                    if(oCEPedidoDetalle.isConPercepcion())
                    {
                        ProductosConPercepcion=ProductosConPercepcion+oCEPedidoDetalle.getProducto()+"\n";
                    }
                    else
                    {
                        if(chkPercepcion.isSelected()){
                            ProductosSinPercepcion=ProductosSinPercepcion+"\n"+oCEPedidoDetalle.getProducto();
                            this.QuitarFila(i);
                        }
                    }

                }
            }

        if(!ProductosSinPercepcion.equals("")){
            JOptionPane.showMessageDialog(this,
                    "<html><h3>Los Siguientes Productos seran borrados por que no tienen Percepción: "+ProductosSinPercepcion);
            
        }

        return true;
    }
    private void GuardarRegistro()
    {
        try {
//        if(FrmSistemaMenu.isSiNoAtencion())
//        {
         actualizarStock();
        if(ValidarTablaPedido()){
        if(validar()){
                    if(pAccionABM==1){
                        CEPedidoMatriz oCEPedido=this.getPedido();
                        String Codigopedido=CCPedido.InsPedido(oCEPedido,chkFecha.isSelected());
                        if(Codigopedido!=null)
                        {

                        oCEPedido.setCodigo(Codigopedido);
                        JOptionPane.showMessageDialog(this,"<html><h3>El código de Pedido es: </h3><h2><center>"+Codigopedido+
                                    "</center></h2><h3> El monto a pagar es   : </h3><h2><center>"+LblMontoTotalPagar.getText()+"S/.</center><h2></html>");

                        limpiarFormulario();        
                        lstPedidoEliminados = new ArrayList<CEPedidoDetalle>();
                        TxtlHoraRegistro1.setText("");
                        TblPedidoDetalle.requestFocus();
                        TblPedidoDetalle.changeSelection(0,oCLPedido.colProducto, false, false);                        
                        }
                        else{

                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
                    }else{

                        CEPedidoMatriz oCEPedido=this.getPedido();
                        int iscorrecto=CCPedido.UPDPedido(oCEPedido);
                        if(iscorrecto==1)
                        {
                        JOptionPane.showMessageDialog(this,"<html><CENTER><h3>Operación exitosa,<BR>el Nuevo Monto es: "+LblMontoTotalPagar.getText()+"S/.</h3></CENTER></html>");                        
                        limpiarFormulario();
                        lstPedidoEliminados = new ArrayList<CEPedidoDetalle>();
                        pAccionABM=1;
                        }
                    else{
                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
               }
      }
     }

    }
        catch (Exception e)
    {
        System.out.println("EventoGuardar - DlgGestion Pedido : "+ e);
       JOptionPane.showMessageDialog(this,"Operación Fallida");
    }
//        }
// else
//        {
//        JOptionPane.showMessageDialog(null,"El sistema se encuentra cerrado, no se puede realizar pedidos","Mensaje", JOptionPane.ERROR_MESSAGE);
//        BtnActualizar.requestFocus();
// }
    }


    private CEPedidoMatriz getPedido(){
        try {
        CEPedidoMatriz oCEPedido= new CEPedidoMatriz();

        oCEPedido.setIdPedido(IdPedido);
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComprobante.getSelectedItem();
        oCEPedido.setIdTipoComprobante(oCETipoComprobante.getIdTipoComprobante());
        CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        oCEPedido.setIdMoneda(oCEMoneda.getId_moneda());
        CECondicion oCECondcion=(CECondicion)CbxCondicion.getSelectedItem();
        oCEPedido.setIdCondicion(oCECondcion.getIdCondicion());
        oCEPedido.setTipoCambio(Float.parseFloat(TxtTipoCambio.getText()));
        CETipoDescuento oCETipoDescuento=(CETipoDescuento)CbxTipoDescuentoPedido.getSelectedItem();

        oCEPedido.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEPedido.setIdUltimoEstado(1);
        oCEPedido.setIdTipoDescuento(oCETipoDescuento.getIdTipoDescuento());
        oCEPedido.setIdCliente(idCliente);
        oCEPedido.setCliente(Cliente);
        oCEPedido.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEPedido.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEPedido.setSubtotalBruto(Float.parseFloat(LblSubtotalBruto.getText()));
        oCEPedido.setDescuentoEnSubtotal(Float.parseFloat(LblDescuentoSubtotal.getText()));
        oCEPedido.setSubTotalNeto(Float.parseFloat(LblSubtotalNeto.getText()));
        oCEPedido.setDescuento(oCLPedido.getDescuento());
        oCEPedido.setDescuentoEnValores(Float.parseFloat(LblDescuentoValores.getText()));
        oCEPedido.setSubTotalNetoSinIGV(Float.parseFloat(LblSubTotalNetoSinIgv.getText()));
        oCEPedido.setIGV(Float.parseFloat(LblIgv.getText()));
        oCEPedido.setISC(Float.parseFloat(LblISC.getText()));
        oCEPedido.setIGVActual(oCLPedido.getIgvActual());
        oCEPedido.setDescuentoTotal(Float.parseFloat(LblDescuentoTotal.getText()));
        oCEPedido.setMontoTotal(Float.parseFloat(LblMontoTotal.getText()));
        oCEPedido.setMontoPercepcion(Float.parseFloat(lblMontoPercepcion.getText()));
        oCEPedido.setMontoExonerado(oCLPedido.getMontoExonerado());
        oCEPedido.setTotalPagar(Float.parseFloat(LblMontoTotalPagar.getText()));
        oCEPedido.setTasaPercepcion(0);
        if(chkPercepcion.isSelected()){
        oCEPedido.setTasaPercepcion(oCLPedido.getTasaPercepcion());
            }
            SetterGetter oSetterGetter = new SetterGetter();
            oSetterGetter.setmIntTipo(1);
            ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
            if (DtchFecha.getCalendar() != null)
            {
                oConvertidorFecha.setFecha(DtchFecha.getCalendar());
                oCEPedido.setFecha(oSetterGetter.convertir(oConvertidorFecha.getFechaConvertida()));
            }

        List<CEPedidoDetalle> lstPedidoDetalle = new ArrayList<CEPedidoDetalle>();
        CEPedidoDetalle oCEPedidoDetalle ;
        for( int i =0;i<TblPedidoDetalle.getRowCount();i++)
            {

                oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){

                CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidoDetalle.getValueAt(i, oCLPedido.colUnidadMedida);
                oCEPedidoDetalle.setUnidadMedida(oCEUnidadMedidaProducto.toString());
                CETipoDescuento oCETipoDescuentoDet=(CETipoDescuento)TblPedidoDetalle.getValueAt(i, oCLPedido.colTipoDescuento);
                oCEPedidoDetalle.setIdTipoDescuento(oCETipoDescuentoDet.IdTipoDescuento);
                oCEPedidoDetalle.setPrecio(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colPrecio).toString()));
                oCEPedidoDetalle.setPrecioSinRedon(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colprecio_sin_redon).toString()));
                oCEPedidoDetalle.setCantidad(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colCantidad).toString()));
                oCEPedidoDetalle.setImporteSinDescuento(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colImporSinDesc).toString()));
                if(TblPedidoDetalle.getValueAt(i, oCLPedido.colImporConDesc)!=null){
                    oCEPedidoDetalle.setImporteConDescuento(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colImporConDesc).toString()));
                    }
                if(TblPedidoDetalle.getValueAt(i, oCLPedido.colExonerado)!=null){
                    oCEPedidoDetalle.setExonerado(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colExonerado).toString()));
                    }
                if(TblPedidoDetalle.getValueAt(i, oCLPedido.colDescuento)==null){
                    TblPedidoDetalle.setValueAt("0",i, oCLPedido.colDescuento);
                    }
                    oCEPedidoDetalle.setDescuento(Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colDescuento).toString()));
                    float descuentoValores=0;
                    if(TblPedidoDetalle.getValueAt(i, oCLPedido.colDestValores)!=null){
                        descuentoValores=Float.parseFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colDestValores).toString());
                }
                oCEPedidoDetalle.setDescuentoEnValores(descuentoValores);
                oCEPedidoDetalle.setPrecioConDesc(VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(i, oCLPedido.colPrecioConDesc)));
                lstPedidoDetalle.add(oCEPedidoDetalle);}
            }
                if(pAccionABM==2)
                {
                    lstPedidoDetalle.addAll(lstPedidoEliminados);
                }


            oCEPedido.setLstPedidoDetalle(lstPedidoDetalle);

            
            return oCEPedido;

            }
        catch (Exception e)
    {
        System.out.println("getPedido - DlgGestion Pedido : "+ e);
       return null;
    }

}
    private boolean agregarNuevaFila(int fila)
    {

        try {
        if(TblPedidoDetalle.getRowCount()<12){

         Vector oVector = new Vector();

                ((DefaultTableModel)TblPedidoDetalle.getModel()).addRow(oVector);

                    CbxTipoDescuento.setSelectedIndex(0);
                    CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();

                    TblPedidoDetalle.requestFocus();
     
                    TblPedidoDetalle.setValueAt(oTipoDescuento, fila, oCLPedido.colTipoDescuento);

                   
        }
         else
        {
        
        JOptionPane.showMessageDialog(this,"No se puede agregar,a sobrepasado el límite");
        return  false;
        }
        
        return true;
        }
        catch (Exception e)
    {
        System.out.println("agregarNruvaFila - DlgGestion Pedido : "+ e);
       return false;
    }

    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{

try {
    if(TblPedidoDetalle.isEnabled()){
      int columnaValidada=ValidarRegistro(TblPedidoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          int fila =TblPedidoDetalle.getRowCount();          
          boolean isAgrega=agregarNuevaFila(fila);
                    if(isAgrega){
                    TblPedidoDetalle.requestFocus();
                    TblPedidoDetalle.changeSelection(fila, oCLPedido.colProducto, false, false);
                    }
                    else{ TxtCliente.requestFocus();}
      }else
      {
          TblPedidoDetalle.requestFocus();
          TblPedidoDetalle.changeSelection(TblPedidoDetalle.getRowCount()-1, columnaValidada, false, false);
      }
    }

    }
    catch (Exception e)
    {
        System.out.println("EventoAgregar - DlgGestion Pedido : "+ e);
       JOptionPane.showMessageDialog(this,"Operación Fallida");
    }
}

private void QuitarFila(int rowsel)
{

    try{        
        if(rowsel!=-1){
                       CEPedidoDetalle oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(rowsel, oCLPedido.colCodigo);
                    if(oCEPedidoDetalle!=null){
                        if(oCEPedidoDetalle.getIdPedidoDetalle()>0)
                        {
                            oCEPedidoDetalle.setIdAbm(3);
                            lstPedidoEliminados.add(oCEPedidoDetalle);
                        }
                    }
                    oCLPedido.QuitarFilaSel(rowsel);
                }
        else JOptionPane.showMessageDialog(this,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }
        catch (Exception e)
    {
        System.out.println("Quitar Fila - DlgGestion Pedido : "+ e);

    }

}
    private void BtnEditarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarPrecioActionPerformed

        verPrecios(4);
    }//GEN-LAST:event_BtnEditarPrecioActionPerformed

    private void CbxCondicionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxCondicionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxCondicionItemStateChanged

    private void eventoCancelar()
    {
         if(pAccionABM==4||pAccionABM==1||pAccionABM==2)
       {
            habilitarControles(false);
            oclBotonesABM.controlBoton(true, false, false, false, true, true);
            TxtlHoraRegistro1.setText("");
            pAccionABM=0;
            limpiarFormulario();
            lstPedidoEliminados= new ArrayList<CEPedidoDetalle>();
       }else{
           iscerrando=true;
             dispose();
            }

    }
    private void CbxTipoComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteActionPerformed

        
        if(((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getIdTipoComprobante()==4)
        {
            oCLPedido.setConigv(false);
        }
        else
        {
            oCLPedido.setConigv(true);
        }
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
    }//GEN-LAST:event_CbxTipoComprobanteActionPerformed

    private void CbxTipoComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxTipoComprobanteItemStateChanged

        try{
        CETipoComprobante oCETipoComprobante=(CETipoComprobante)CbxTipoComprobante.getSelectedItem();
      if(oCETipoComprobante!=null)
      {
           
          oCLPedido.setConigv(true);
          if(oCETipoComprobante.getIdTipoComprobante()==3)
          {
              oCLPedido.setConigv(false);
          }
          oCLPedido.CalcularSubtotales();
          oCLPedido.calcularImportes();
           oCLPedido.eventoDescuento();
      }
      }
        catch (Exception e)
    {
        System.out.println("CbxTipoComprobanteItemStateChanged - DlgGestion Pedido : "+ e);

    }
    }//GEN-LAST:event_CbxTipoComprobanteItemStateChanged

    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila){
         try{
             
            colValidada=-1;

            float cantidad=1;
            float descuento=0;
            float precio=1;
            float stock=1;
            if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
            cantidad=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());}
            if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colPrecio)!=null){
                precio=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colPrecio).toString());
            }
             if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colDescuento)!=null){
             descuento=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colDescuento).toString());
            }

              float  desc=VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colDestValores));
              float  importSindesc=VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colImporSinDesc));


            if(desc>importSindesc)
            {
                JOptionPane.showMessageDialog(this,"El descuento no puede ser mayor al importe");
                TblPedidoDetalle.setValueAt(0, fila,oCLPedido.colDescuento);
                descuento=0;
                
               oCLPedido.CalcularSubtotales();
               oCLPedido.eventoDescuento();
               oCLPedido.calcularImportes();

                colValidada=oCLPedido.colDescuento;
                 return;
            }

        if(descuento<0)
            {
                JOptionPane.showMessageDialog(this,"El descuento no puede ser menor a cero");
                TblPedidoDetalle.setValueAt(null, fila,oCLPedido.colDescuento);
                descuento=0;
                TblPedidoDetalle.setValueAt(CLRedondear.Redondear((cantidad*precio)-descuento,2), fila, oCLPedido.colImporConDesc);//con
                colValidada=oCLPedido.colDescuento;
                return;
            }

        if(cantidad<=0)
            {
                JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                TblPedidoDetalle.setValueAt(null,fila,oCLPedido.colCantidad);
                colValidada=oCLPedido.colCantidad;
                 return;
            }
          /*      if(precio<=0)
            {
                JOptionPane.showMessageDialog(null,"El precio tiene q ser mayor a cero");
                colValidada=oCLPedido.colPrecio;
                 return;
            }*/
        stock=VerificadorDeTxt.convertFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colStock));
        if(TblPedidoDetalle.isEnabled()){
            if(cantidad>stock)
            {
                if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colStock)!=null){
               JOptionPane.showMessageDialog(this,"La cantidad no debe ser mayor al stock disponible");
                TblPedidoDetalle.setValueAt(null, fila,oCLPedido.colCantidad);
                colValidada=oCLPedido.colCantidad;
                 return;
                }

            }
        }

        int col=TblPedidoDetalle.getSelectedColumn();
                if(!eventoGuardar){
                if(col==oCLPedido.colCantidad){
                    oCLPedido.calcularPrecio(fila);
                }
             }
            
            }catch(Exception e)
            {
                cont++;
               // System.out.println("metodo Verificar datos");
            }
    }



    private void CbxTipoDescuentoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoPedidoActionPerformed
        
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
             CETipoDescuento oDescuento=(CETipoDescuento)CbxTipoDescuentoPedido.getSelectedItem();
        if(oDescuento!=null)
      {
          if(oDescuento.getIdTipoDescuento()==2)
          {

          LblPorcentaje.setText("%");

          }
        else{

         LblPorcentaje.setText(" V");
        }
      }
     oCLPedido.eventoDescuento();
    }//GEN-LAST:event_CbxTipoDescuentoPedidoActionPerformed
private String Codigo;


private void buscarPedido()
    {
    DlgBusquedaPedido odialogo= new DlgBusquedaPedido(oparent, true);
       odialogo.setVisible(true);
       CEPedidoMatriz oCEPedidoaux=odialogo.getCEPedidoMatriz();
       oCLPedido.setCalcular(false);
       SetPedidoDeBusq(oCEPedidoaux);
       oCLPedido.setCalcular(true);
}
    public void SetPedidoDeBusq(CEPedidoMatriz oCEPedidoaux)
    {
        try{
        
       
       if(oCEPedidoaux!=null)
        {
           CEPedidoMatriz oCEPedidoMatriz= CCPedido.ConsultarPedidosPorId(oCEPedidoaux.getIdPedido());
           lstPedidoEliminados= new ArrayList<CEPedidoDetalle>();
            oCLPedido.limpiarTabla();
            Codigo=oCEPedidoMatriz.getCodigo();
            LblUltimoNumeroPedido.setText(Codigo);
            TxtEstado.setText(oCEPedidoMatriz.getUltimoEstado());
            IdEstado=oCEPedidoMatriz.getIdUltimoEstado();
            LblSubtotalBruto.setText(""+oCEPedidoMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText(""+oCEPedidoMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText(""+oCEPedidoMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEPedidoMatriz.getSubTotalNeto()+"");
            LblSubTotalNetoSinIgv.setText(oCEPedidoMatriz.getSubtotalNetoSinIGV()+"");
            LblDescuentoValores.setText(oCEPedidoMatriz.getDescuentoValores()+"");
            LblIgv.setText(oCEPedidoMatriz.getIGV()+"");
            LblISC.setText(oCEPedidoMatriz.getISC()+"");
            IdPedido=oCEPedidoMatriz.getIdPedido();
            LblMontoTotal.setText(""+oCEPedidoMatriz.getMontoTotal());
            lblMontoPercepcion.setText(""+oCEPedidoMatriz.getMontoPercepcion());
            LblMontoTotalPagar.setText(""+oCEPedidoMatriz.getTotalPagar());
            LblDescuentoTotal.setText(""+oCEPedidoMatriz.getDescuentoEnSubtotal());
            //JOptionPane.showMessageDialog(this,"Exonerado"+oCEPedidoMatriz.getMontoExonerado());
            if(oCEPedidoMatriz.getMontoPercepcion()>0)
            {chkPercepcion.setSelected(true);}
            else{chkPercepcion.setSelected(false);}
           
            idCliente=oCEPedidoMatriz.getIdCliente();
            Cliente=oCEPedidoMatriz.getCliente();
            TxtCliente.setText(oCEPedidoMatriz.getCliente());
            TxtDNI.setText(oCEPedidoMatriz.getDNI());
            TxtDireccion.setText(oCEPedidoMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEPedidoMatriz.getSucursal());
            TxtVendedor.setText(oCEPedidoMatriz.getEmpleado());
            TxtlHoraRegistro1.setText(oCEPedidoMatriz.getFecha());
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoPedido,oCEPedidoMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante,oCEPedidoMatriz.getIdTipoComprobante());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEPedidoMatriz.getIdMoneda());
            oCLPedido.setTipoCambioAnterior(oCEPedidoMatriz.getTipoCambio());
            if(oCEPedidoMatriz.getLstPedidoDetalle()!=null)
            {
               for(int i=0; i<oCEPedidoMatriz.getLstPedidoDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblPedidoDetalle.getModel()).addRow(oVector);
                
                CEPedidoDetalle oCEPedidoDetalle=oCEPedidoMatriz.getLstPedidoDetalle().get(i) ;
                oCEPedidoDetalle.setIdAbm(2);
                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEPedidoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEPedidoDetalle.getIdUnidadMedidaVenta());

                TblPedidoDetalle.setValueAt(oCEPedidoDetalle,i,oCLPedido.colCodigo);
                TxtProducto.setText(oCEPedidoDetalle.getProducto());
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getIdAlmacen(),i,oCLPedido.colAlm);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),i,oCLPedido.colProducto);
                TblPedidoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLPedido.colUnidadMedida);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecio(),i,oCLPedido.colPrecio);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecioSinRedon(),i,oCLPedido.colprecio_sin_redon);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getCantidad(),i,oCLPedido.colCantidad);
                TblPedidoDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEPedidoDetalle.getIdTipoDescuento()),i,oCLPedido.colTipoDescuento);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImportelSinDescuento(),i,oCLPedido.colImporSinDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImporteConDescuento(),i,oCLPedido.colImporConDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuentoEnValores(), i, oCLPedido.colDestValores);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecioConDesc(), i, oCLPedido.colPrecioConDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getExonerado(), i, oCLPedido.colExonerado);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuento(),i,oCLPedido.colDescuento);
                }
            }
     

            habilitarControles(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);

            if(IdEstado!=1)
            {
                btnAnular.setEnabled(false);
                btnEditar.setEnabled(false);
            }
            pAccionABM=2;// 
            btnEditar.requestFocus();
        }
            
       }
        catch (Exception e)
    {
        System.out.println("buscarPedido - DlgGestion Pedido : "+ e);
        JOptionPane.showMessageDialog(this,"operación fallida");

    }
    }
    private void ObtenerListadePrecios()
    {
        CEPedidoDetalle oCEPedidoDetalle;
        List<CEProductoPrecio> listaPrecioProducto;
       for( int i =0;i<TblPedidoDetalle.getRowCount();i++)
            {

                oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){
                    listaPrecioProducto = new ArrayList<CEProductoPrecio>();
                    listaPrecioProducto=CCProductoPrecio.consultarPrecioProducto(oCEPedidoDetalle.getIdProducto());
                    TblPedidoDetalle.setValueAt(listaPrecioProducto,i, oCLPedido.colListaPrecio);
                }
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
private void EventoNuevo()
{
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
       limpiarFormulario();
       lstPedidoEliminados= new ArrayList<CEPedidoDetalle>();
       LblUltimoNumeroPedido.setText("");
       TblPedidoDetalle.requestFocus();
       TblPedidoDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
}
private void EventoNuevoAlImportar()
{
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       habilitarControles(true);
       LblUltimoNumeroPedido.setText("");
       TblPedidoDetalle.requestFocus();
       TblPedidoDetalle.changeSelection(0,oCLPedido.colProducto, false, false);
}
    private void actualizarStock()
    {
        for( int i =0;i<TblPedidoDetalle.getRowCount();i++)
            {

               CEPedidoDetalle oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(i, oCLPedido.colCodigo);
                if(oCEPedidoDetalle!=null){
                    float stock=CCPedido.StockProductoPorPedido(oCEPedidoDetalle.getIdProducto(), oCEPedidoDetalle.getIdAlmacen());
                    
                    if(pAccionABM==1){
                     float StockEqui=stock/oCEPedidoDetalle.getEqivalenciaUMB();
                    TblPedidoDetalle.setValueAt(CLRedondear.Redondear(StockEqui,2), i, oCLPedido.colStock);
                    }
                    if(pAccionABM==2){
                    float Equivalencia=oCEPedidoDetalle.getEqivalenciaUMB();
                    float StockEqui=stock/Equivalencia;
                    float StockReal=StockEqui+(oCEPedidoDetalle.getCantidadBaseAnte()/oCEPedidoDetalle.getEqivalenciaUMB())+CantidadEliminados(oCEPedidoDetalle);
                    TblPedidoDetalle.setValueAt(CLRedondear.Redondear(StockReal,2), i, oCLPedido.colStock);
                    }
                }
           }
    }
    private float CantidadEliminados(CEPedidoDetalle oCEPedidoDetalle)
    {
        for (int i = 0; i < lstPedidoEliminados.size(); i++) {
            CEPedidoDetalle oCEPedidoDetalleEliminiado = lstPedidoEliminados.get(i);

            if(oCEPedidoDetalleEliminiado.getIdProducto()==oCEPedidoDetalle.getIdProducto())
            {
                return oCEPedidoDetalleEliminiado.getCantidadBaseAnte()/oCEPedidoDetalle.getEqivalenciaUMB();
            }


        }
        return 0;
    }
    public void EventoEditar()
    {
        try {

            pAccionABM=2;
            oclBotonesABM.controlBoton(false, true, false, false, true, false);
             habilitarControles(true);
             ObtenerListadePrecios();
             actualizarStock();
             CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
            if(oCEMoneda!=null)
                {
                    if(oCEMoneda.getId_moneda()!=1)
                    {
                        if(oCLPedido.getTipoCambioAnterior()!=Float.parseFloat(TxtTipoCambio.getText())){
                        JOptionPane.showMessageDialog(this,"<HTML>El pedido se registro con el tipo de Cambio: "+oCLPedido.getTipoCambioAnterior()+
                                                            "<BR>se actualizaran los importes con el Tipo Cambio Actual: "+TxtTipoCambio.getText()+"</HTML>");
                        oCLPedido.calcularEquivalenciaMoneda();
                        }
                    }
                }


    }
        catch (Exception e)
    {
        System.out.println("btnEditarActionPerformed - DlgGestion Pedido : "+ e);

    }
    }
    private void CbxTipoDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CbxTipoDescuentoMouseClicked

    }//GEN-LAST:event_CbxTipoDescuentoMouseClicked

    private void JMAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAgregarActionPerformed

        eventoAgregar();
}//GEN-LAST:event_JMAgregarActionPerformed

    private void JMEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMEliminarActionPerformed

        this.QuitarFila(TblPedidoDetalle.getSelectedRow());
    }//GEN-LAST:event_JMEliminarActionPerformed

    private void JMVerPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMVerPreciosActionPerformed
       
    verPrecios(0);
    }//GEN-LAST:event_JMVerPreciosActionPerformed


    private void verPrecios(int accion)// accion =0 ver , accion =3 modificar
    {
        try{
        int fila=TblPedidoDetalle.getSelectedRow();

        if(fila!=-1){
            boolean ismodificable=false;
            float precioNuevo=0;
            CEPedidoDetalle oCEPedidoDetalle= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(fila, oCLPedido.colCodigo);
             if(oCEPedidoDetalle!=null){
                    if(TblPedidoDetalle.getValueAt( fila, oCLPedido.colPrecio)!=null)
                    {
                        precioNuevo=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colPrecio).toString());
                    }
                    if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colisnPrecio)!=null){
                        ismodificable=Boolean.parseBoolean(TblPedidoDetalle.getValueAt(fila, oCLPedido.colisnPrecio).toString());
                    }
                    float cantidad=0;
                    if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
                       cantidad=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());
                       }
                     CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidoDetalle.getValueAt(fila, oCLPedido.colUnidadMedida);
                     DlgConsultarPrecio odialogo=new DlgConsultarPrecio(null, true,oCLPedido.obtenerListaPorUnidad(),oCEPedidoDetalle.getProducto(),
                                                    oCEUnidadMedidaProducto.getTipoUnidad(),accion,ismodificable,precioNuevo,cantidad);
                     odialogo.setTitle("Consulta de Precios");
                     if(accion==2){odialogo.setTitle("Modificar  Precio");}
                     if(accion==4){odialogo.setTitle("Modificar  Precio");}
                     odialogo.setLocationRelativeTo(null);
                     odialogo.setVisible(true);
                     if(accion==2){
                                 precioNuevo=odialogo.getPrecio();
                                 if(precioNuevo!=0){
                                     TblPedidoDetalle.setValueAt(CLRedondear.RedondearString(precioNuevo,4)+"", fila, oCLPedido.colPrecio);
                                     oCLPedido.CalcularCelda(fila, oCLPedido.colCantidad);
                                     }
                                }
                     if(accion==4){
                                 precioNuevo=odialogo.getPrecio();
                                 if(precioNuevo!=0){
                                     TblPedidoDetalle.setValueAt(CLRedondear.RedondearString(precioNuevo,4)+"", fila, oCLPedido.colPrecio);
                                     oCLPedido.CalcularCelda(fila, oCLPedido.colCantidad);
                                     }
                                }
             }
             else{
                 JOptionPane.showMessageDialog(this,"Aun no seleccionado un producto");
             }
        }
        }
        catch (Exception e)
    {
        System.out.println("ver precios - DlgGestion Pedido : "+ e);
        JOptionPane.showMessageDialog(this,"operación fallida");

    }

    }
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void JMModificaPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMModificaPrecioActionPerformed
        verPrecios(2);
    }//GEN-LAST:event_JMModificaPrecioActionPerformed

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

    private void TblPedidoDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPedidoDetalleKeyReleased

        try {
        if(TblPedidoDetalle.getSelectedColumn()==oCLPedido.colImporSinDesc&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblPedidoDetalle.getSelectedRow()+1==TblPedidoDetalle.getRowCount()){
                int fila=TblPedidoDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    boolean isAgrega=agregarNuevaFila(fila);
                    if(isAgrega){
                    TblPedidoDetalle.requestFocus();
                    TblPedidoDetalle.changeSelection(fila, oCLPedido.colProducto, false, false);
                    }
                    else{ TxtCliente.requestFocus();}
                }else{
                    TblPedidoDetalle.requestFocus();
                    TblPedidoDetalle.changeSelection(TblPedidoDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }

        if(colValidada!=-1){

            TblPedidoDetalle.requestFocus();
            TblPedidoDetalle.changeSelection(TblPedidoDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }

        if(TblPedidoDetalle.getSelectedColumn()==oCLPedido.colPrecio&&(evt.getKeyCode()==evt.VK_F2||evt.getKeyCode()==evt.VK_INSERT)){
            verPrecios(2);
        }

        }
        catch (Exception e)
            {
                System.out.println("TblPedidoDetalleKeyReleased - DlgGestion Pedido : "+ e);

            }

    }//GEN-LAST:event_TblPedidoDetalleKeyReleased

    private void TblPedidoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPedidoDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblPedidoDetalle.getSelectedColumn()==oCLPedido.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            if(TblPedidoDetalle.getSelectedColumn()==oCLPedido.colCantidad){
                TblPedidoDetalle.changeSelection(TblPedidoDetalle.getSelectedRow(), oCLPedido.colImporSinDesc-1, false, false);
            }
            if(TblPedidoDetalle.getSelectedColumn()==oCLPedido.colprecio_sin_redon){
                TblPedidoDetalle.changeSelection(TblPedidoDetalle.getSelectedRow(), oCLPedido.colPrecio, false, false);
            }

        }


    }//GEN-LAST:event_TblPedidoDetalleKeyPressed

    private void verficarTextoVacio(int fila){

        try{
                if(fila!=-1)
                   {
                   if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colProducto)!=null){
                       String producto=TblPedidoDetalle.getValueAt(fila, oCLPedido.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCodigo)!=null){

                               TblPedidoDetalle.setValueAt(((CEPedidoDetalle)TblPedidoDetalle.getValueAt(fila, oCLPedido.colCodigo)).getProducto(),fila, oCLPedido.colProducto);
                           }

                       }
                    }
                   }

                }
        catch (Exception e)
            {
                System.out.println("verificartextoVacio - DlgGestion Pedido : "+ e);

            }
    }
    private void TblPedidoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblPedidoDetallePropertyChange
        if(!iscerrando){
            if(cont==1){
                int fila=TblPedidoDetalle.getSelectedRow();
                verficarTextoVacio(fila);
                if(fila!=-1){
                    verificarDatos(fila);}
            }else{
                cont=0;
            }
            cont++;
        }



}//GEN-LAST:event_TblPedidoDetallePropertyChange

    private void TblPedidoDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPedidoDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {
                JmMenuTablaPedido.show( evt.getComponent(),evt.getX() , evt.getY()) ;

            }
}//GEN-LAST:event_TblPedidoDetalleMousePressed

    private void TblPedidoDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPedidoDetalleMouseClicked

    }//GEN-LAST:event_TblPedidoDetalleMouseClicked

    private void TxtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDescuentoKeyReleased
      oCLPedido.eventoDescuento();
    }//GEN-LAST:event_TxtDescuentoKeyReleased

    private void TxtDescuentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusLost
       if(!TxtDescuento.getText().equals("")&&TxtDescuento.getText()!=null&&!TxtDescuento.getText().isEmpty())
       {
          TxtDescuento.setText(CLRedondear.RedondearString(Float.parseFloat(TxtDescuento.getText()), 2));

       }
    }//GEN-LAST:event_TxtDescuentoFocusLost

    private void TxtDescuentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtDescuentoFocusGained
      TxtDescuento.selectAll();
    }//GEN-LAST:event_TxtDescuentoFocusGained

    private void CbxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxMonedaActionPerformed
        // TODO add your handling code here


        TxtTipoCambio.setText(""+oCLPedido.ObtenerTipoCambioMoneda(CbxMoneda));
        obtenerAbrevMoneda();
        oCLPedido.calcularEquivalenciaMoneda();
    }//GEN-LAST:event_CbxMonedaActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed

        BtnActualizar.setEnabled(false);
        FrmSistemaMenu.setSiNoAtencion(CCAtencionSistema.ConsultarAtencionSistema());
        oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
        oCLPedido.eventoDescuento();
        actualizarStock();
        BtnActualizar.setEnabled(true);
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void chkFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFechaActionPerformed

        if(chkFecha.isSelected())DtchFecha.setEnabled(true);
        else DtchFecha.setEnabled(false);

    }//GEN-LAST:event_chkFechaActionPerformed

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

       
}//GEN-LAST:event_DtchFechaPropertyChange

    private void chkPercepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPercepcionActionPerformed

         oCLPedido.CalcularSubtotales();
        oCLPedido.calcularImportes();
        oCLPedido.eventoDescuento();
        if(chkPercepcion.isSelected())
        {
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante,2);
            CbxTipoComprobante.setEnabled(false);

        }else
        {
            CbxTipoComprobante.setEnabled(true);
            CbxTipoComprobante.setSelectedIndex(0);
        }

    }//GEN-LAST:event_chkPercepcionActionPerformed

    private void btnImportarProformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarProformaActionPerformed

       ImportarProforma();
       EventoNuevoAlImportar();
        
}//GEN-LAST:event_btnImportarProformaActionPerformed

    private void ImportarProforma()
    {
        try{

        DlgBusquedaProforma odialogo= new DlgBusquedaProforma(oparent, true);
       odialogo.setVisible(true);
       CEProformaMatriz oCEProformaaux=odialogo.getCEProformaMatriz();
       if(oCEProformaaux!=null)
        {

           CEProformaMatriz oCEProformaMatriz= CCProforma.ConsultarProformasPorId(oCEProformaaux.getIdProforma());
            oCLPedido.limpiarTabla();
            if(oCEProformaMatriz.getIdUltimoEstado()==4)
            {
                JOptionPane.showMessageDialog(this,"La Proforma ya fue "+oCEProformaMatriz.getUltimoEstado()+", No se Puede Importar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                EventoNuevo();
                return;
            }
            IdProforma=oCEProformaMatriz.getIdProforma();
            //Codigo=oCEProformaMatriz.getCodigo();
            LblUltimoNumeroPedido.setText(Codigo);
            TxtEstado.setText(oCEProformaMatriz.getUltimoEstado());
            IdEstado=oCEProformaMatriz.getIdUltimoEstado();
            LblSubtotalBruto.setText(""+oCEProformaMatriz.getSubtotalBruto());
            LblDescuentoSubtotal.setText(""+oCEProformaMatriz.getDescuentoEnSubtotal());
            TxtDescuento.setText(""+oCEProformaMatriz.getDescuento());
            LblSubtotalNeto.setText(oCEProformaMatriz.getSubTotalNeto()+"");
            LblSubTotalNetoSinIgv.setText(oCEProformaMatriz.getSubtotalNetoSinIGV()+"");
            LblDescuentoValores.setText(oCEProformaMatriz.getDescuentoValores()+"");
            LblIgv.setText(oCEProformaMatriz.getIGV()+"");
            LblISC.setText(oCEProformaMatriz.getISC()+"");
            IdPedido=oCEProformaMatriz.getIdPedido();
            LblMontoTotal.setText(""+oCEProformaMatriz.getMontoTotal());
            lblMontoPercepcion.setText(""+oCEProformaMatriz.getMontoPercepcion());
            LblMontoTotalPagar.setText(""+oCEProformaMatriz.getTotalPagar());
            LblDescuentoTotal.setText(""+oCEProformaMatriz.getDescuentoEnSubtotal());
            if(oCEProformaMatriz.getMontoPercepcion()>0)
            {chkPercepcion.setSelected(true);}
            else{chkPercepcion.setSelected(false);}

            idCliente=oCEProformaMatriz.getIdCliente();
            Cliente=oCEProformaMatriz.getCliente();
            TxtCliente.setText(oCEProformaMatriz.getCliente());
            TxtDNI.setText(oCEProformaMatriz.getDNI());
            TxtDireccion.setText(oCEProformaMatriz.getDireccion());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEProformaMatriz.getSucursal());
            TxtVendedor.setText(oCEProformaMatriz.getEmpleado());
            TxtlHoraRegistro1.setText(oCEProformaMatriz.getFecha());
            CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuentoPedido,oCEProformaMatriz.getIdDescuento());
            CLCargarCombo.buscarIdTipoComprobante(CbxTipoComprobante,oCEProformaMatriz.getIdTipoComprobante());
            CLCargarCombo.buscarIdMoneda(CbxMoneda,oCEProformaMatriz.getIdMoneda());
            oCLPedido.setTipoCambioAnterior(oCEProformaMatriz.getTipoCambio());
            if(oCEProformaMatriz.getLstProformaDetalle()!=null)
            {
               for(int i=0; i<oCEProformaMatriz.getLstProformaDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblPedidoDetalle.getModel()).addRow(oVector);

                CEPedidoDetalle oCEPedidoDetalle=oCEProformaMatriz.getLstProformaDetalle().get(i) ;
                oCEPedidoDetalle.setIdAbm(1);
                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEPedidoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEPedidoDetalle.getIdUnidadMedidaVenta());

                TblPedidoDetalle.setValueAt(oCEPedidoDetalle,i,oCLPedido.colCodigo);
                TxtProducto.setText(oCEPedidoDetalle.getProducto());
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getIdAlmacen(),i,oCLPedido.colAlm);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),i,oCLPedido.colProducto);
                TblPedidoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLPedido.colUnidadMedida);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecio(),i,oCLPedido.colPrecio);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecioSinRedon(),i,oCLPedido.colprecio_sin_redon);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getCantidad(),i,oCLPedido.colCantidad);
                TblPedidoDetalle.setValueAt(CLCargarCombo.buscarIdTipoDescuento(CbxTipoDescuento.getModel(), oCEPedidoDetalle.getIdTipoDescuento()),i,oCLPedido.colTipoDescuento);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImportelSinDescuento(),i,oCLPedido.colImporSinDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getImporteConDescuento(),i,oCLPedido.colImporConDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuentoEnValores(), i, oCLPedido.colDestValores);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getPrecioConDesc(), i, oCLPedido.colPrecioConDesc);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getExonerado(), i, oCLPedido.colExonerado);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getDescuento(),i,oCLPedido.colDescuento);
                }
            }


            habilitarControles(false);
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            
        }
            
       }
        catch (Exception e)
    {
        System.out.println("buscarPedido - DlgGestion Pedido : "+ e);
        JOptionPane.showMessageDialog(this,"operación fallida");

    }

    }
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed
        try{
        this.QuitarFila(TblPedidoDetalle.getSelectedRow());
        }
        catch(Exception e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this,"Operacion Fallida");
        }
    }//GEN-LAST:event_BtnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        eventoCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblPedidoDetalle,
                new String[]{lblEtiqTipoComp.getText(),((CETipoComprobante)CbxTipoComprobante.getSelectedItem()).getDescripcion(),
                LblEtiqNum.getText(),LblUltimoNumeroPedido.getText(),lblEtiqEstado.getText(),TxtEstado.getText(),
                LblEtiqCliente.getText(),TxtCliente.getText(),
                LblEtiqDireccion.getText(),TxtDireccion.getText(),LblEtiqDNI.getText(),TxtDNI.getText(),
                LblEtiqCondicion.getText(),((CECondicion)CbxCondicion.getSelectedItem()).getDescripcion(),
                LblEtiqMoneda.getText(),((CEMoneda)CbxMoneda.getSelectedItem()).getDescripcion(),
                LblEtiqTipoCambio.getText(),TxtTipoCambio.getText(),LblEtiqSucursal.getText(),TxtSucursal.getText(),
                lblEtiqVendedor.getText(),TxtVendedor.getText(),LblEtiqHoraRegistro.getText(),TxtlHoraRegistro1.getText()}
        ,new String[]{LblEtiqSubtotalBruto.getText(),LblSubtotalBruto.getText(),LblEtiqDescValores.getText(),LblDescuentoSubtotal.getText(),
        LblEtiqSubtotalNeto.getText(),LblSubtotalNeto.getText(),
        LblEtiqTipoDescuento.getText(),((CETipoDescuento)CbxTipoDescuento.getSelectedItem()).getDescripcion(),
        LblEtiqDescuento.getText(),TxtDescuento.getText(),LblEtiqDescTotal.getText(),LblDescuentoTotal.getText()},
                new String[]{LblEtiqSubNetoSinIgv.getText(),LblSubTotalNetoSinIgv.getText(),LblEtiqIgv.getText(),LblIgv.getText(),
                LblEtiqISC.getText(),LblISC.getText(),LblEtiqDescValores.getText(),LblDescuentoValores.getText(),
                LblEtiqMontoTotal.getText(),LblMontoTotal.getText(),LblEtiqPercepcion.getText(),lblMontoPercepcion.getText(),
                LblEtiqTotalPagar.getText(),LblMontoTotalPagar.getText()
        },"Registro de Pedido");
        oExportar.GuardarArchivoExcel(this);
}//GEN-LAST:event_btnExportar1ActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed


        oclBotonesABM.controlBoton(true, false, false, false, true, false);


        CEPedidoMatriz oCEPedido= new CEPedidoMatriz();
        oCEPedido.setIdPedido(IdPedido);
        oCEPedido.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEPedido.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEPedido.setCliente(TxtCliente.getText());
        oCEPedido.setCodigo(Codigo);


        DlgAnularPedido oDlgAnularPedido= new DlgAnularPedido(oparent,true , oCEPedido);
        oDlgAnularPedido.setLocationRelativeTo(null);
        oDlgAnularPedido.setVisible(true);
        limpiarFormulario();
        habilitarControles(false);
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        EventoEditar();
}//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        eventoGuardarPedido();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        EventoNuevo();
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPedidoActionPerformed
        buscarPedido();
    }//GEN-LAST:event_btnBuscarPedidoActionPerformed

    private void btnImportarProforma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarProforma1ActionPerformed

        buscarPedido();
        EventoNuevoAlImportar();

    }//GEN-LAST:event_btnImportarProforma1ActionPerformed

    private void chkBusqDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBusqDetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBusqDetActionPerformed

private void obtenerAbrevMoneda()
{
    CEMoneda oCEMoneda=(CEMoneda)CbxMoneda.getSelectedItem();
        if(oCEMoneda!=null)
        {
//            lblAbrevMon1.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon2.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon3.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon4.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon5.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon6.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon7.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon8.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon9.setText(oCEMoneda.getAbreviatura());
//            lblAbrevMon10.setText(oCEMoneda.getAbreviatura());

        }
}

    private void agregarPedidoDetalle(CEAlmacenProducto oCEAlmacenProducto,List<CEProductoPrecio> olistaPrecio)
    {
        try{
        if(oCEAlmacenProducto!=null){


        int fila=TblPedidoDetalle.getSelectedRow();

        CEPedidoDetalle  oCEPedidoDetalleExist= (CEPedidoDetalle)TblPedidoDetalle.getValueAt(fila, oCLPedido.colCodigo);
        CEPedidoDetalle oCEPedidoDetalle= new CEPedidoDetalle();
        oCEPedidoDetalle.setIdAbm(1);
        if(oCEPedidoDetalleExist!=null)
        {
            if(oCEPedidoDetalleExist.getIdPedidoDetalle()>0)
            {
                oCEPedidoDetalle.setIdAbm(2);
                oCEPedidoDetalle.setIdPedidoDetalle(oCEPedidoDetalleExist.getIdPedidoDetalle());
                oCEPedidoDetalle.setCantidadBaseAnte(oCEPedidoDetalleExist.getCantidadBaseAnte());
            }
        }

        oCEPedidoDetalle.setProducto(oCEAlmacenProducto.getDescripcion());
        oCEPedidoDetalle.setIdProducto(oCEAlmacenProducto.getIdProducto());
        oCEPedidoDetalle.setIdAlmacen(oCEAlmacenProducto.getIdAlmacen());
        oCEPedidoDetalle.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCEPedidoDetalle.setSinoImpuesto(oCEAlmacenProducto.isSiNoImpuesto());
        oCEPedidoDetalle.setConPercepcion(oCEAlmacenProducto.isPercepcion());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEAlmacenProducto.getUnidad_medida());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEAlmacenProducto.getIdUnidadBase());
        oCEPedidoDetalle.setCodigoProducto(oCEAlmacenProducto.getCodigo());
        oCEPedidoDetalle.setCantidad(oCEAlmacenProducto.getCantidad());
        oCEPedidoDetalle.setEqivalenciaUMB(oCEAlmacenProducto.getEquivalenciaUMB());
        CETipoDescuento oTipoDescuento=(CETipoDescuento)CbxTipoDescuento.getSelectedItem();
        oCLPedido.setActionPrecio(false);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle,fila,oCLPedido.colCodigo);
                TblPedidoDetalle.setValueAt(oCEAlmacenProducto.getStockReal(),fila, oCLPedido.colStock);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getProducto(),fila,oCLPedido.colProducto);
                TblPedidoDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLPedido.colUnidadMedida);
                TblPedidoDetalle.setValueAt(0,fila,oCLPedido.colDescuento);
                TblPedidoDetalle.setValueAt(oTipoDescuento, fila, oCLPedido.colTipoDescuento);
                TblPedidoDetalle.setValueAt(olistaPrecio,fila , oCLPedido.colListaPrecio);
                TblPedidoDetalle.setValueAt(0,fila , oCLPedido.colPrecio);
                TblPedidoDetalle.setValueAt(oCEPedidoDetalle.getIdAlmacen(),fila , oCLPedido.colAlm);
                float cant=oCEAlmacenProducto.getCantidad();
                if(cant==0)
                TblPedidoDetalle.setValueAt(null, fila, oCLPedido.colCantidad);
                else{
                    TblPedidoDetalle.setValueAt(oCEAlmacenProducto.getCantidad(), fila, oCLPedido.colCantidad);
                }
                TblPedidoDetalle.setValueAt(CLRedondear.RedondearString(oCEAlmacenProducto.getPrecio(),4), fila, oCLPedido.colPrecio);
                TblPedidoDetalle.setValueAt(CLRedondear.RedondearString(oCEAlmacenProducto.getPrecio_sin_redon(),4), fila, oCLPedido.colprecio_sin_redon);
                float cantidad=0;
                if(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad)!=null){
                cantidad=Float.parseFloat(TblPedidoDetalle.getValueAt(fila, oCLPedido.colCantidad).toString());
                }
                if(cantidad>0){
                    verificarDatos(fila);

                    oCLPedido.calcularPrecio(fila);
                    oCLPedido.CalcularCelda(fila,oCLPedido.colCantidad);
                    
                }

        }
        }
        catch (Exception e)
            {
                System.out.println("agregarpPedidoDetalle - DlgGestion Pedido : "+ e);

            }
                
    }
    private void limpiarFormulario()
    {

      chkFecha.setSelected(false);
      DtchFecha.setEnabled(false);
      DtchFecha.setDate(new Date());
      chkPercepcion.setSelected(false);
      CbxTipoComprobante.setEnabled(true);
      CbxTipoComprobante.setSelectedIndex(0);
      TxtEstado.setText("EMITIDO");
      CbxCondicion.setSelectedIndex(0);
      CbxMoneda.setSelectedIndex(0);
      CbxTipoComprobante.setSelectedIndex(0);
      jTextFieldNumber.setText("");
      LblUltimoNumeroPedido.setText("");
      TxtProducto.setText("");
      TxtCliente.setText("");
      TxtDireccion.setText("");
      TxtDNI.setText("");
      TxtDescuento.setText("0");
      idCliente=0;
      Cliente="";
      oCLPedido.limpiarTabla();
      LblDescuentoValores.setText("0");
      LblSubtotalNeto.setText("0");
      LblSubTotalNetoSinIgv.setText("0");
      LblIgv.setText("0");
      LblISC.setText("0");
      LblMontoTotal.setText("0");
      LblDescuentoTotal.setText("0");
      TxtDescuento.setText("0");
      CbxTipoDescuentoPedido.setSelectedIndex(0);
      TblPedidoDetalle.setEnabled(false);
      agregarNuevaFila(0);
      TblPedidoDetalle.setEnabled(true);
      TblPedidoDetalle.requestFocus();
      TblPedidoDetalle.changeSelection(0, oCLPedido.colProducto, false, false);
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnEditarPrecio;
    private javax.swing.JButton BtnQuitar;
    private ComboBox.ComboBox CbxCondicion;
    private ComboBox.ComboBox CbxMoneda;
    private ComboBox.ComboBox CbxTipoComprobante;
    private ComboBox.ComboBox CbxTipoDescuento;
    private ComboBox.ComboBox CbxTipoDescuentoPedido;
    private javax.swing.JComboBox CbxUnidadMedida;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private javax.swing.JMenuItem JMAgregar;
    private javax.swing.JMenuItem JMEliminar;
    private javax.swing.JMenuItem JMModificaPrecio;
    private javax.swing.JMenuItem JMVerPrecios;
    private javax.swing.JPopupMenu JmMenuTablaPedido;
    private Label.Label LblDescuentoSubtotal;
    private Label.Label LblDescuentoTotal;
    private Label.Label LblDescuentoValores;
    private Label.Label LblEtiqCliente;
    private Label.Label LblEtiqCondicion;
    private Label.Label LblEtiqDNI;
    private Label.Label LblEtiqDescTotal;
    private Label.Label LblEtiqDescValores;
    private Label.Label LblEtiqDescuento;
    private Label.Label LblEtiqDireccion;
    private Label.Label LblEtiqDsctSubtValores;
    private Label.Label LblEtiqHoraRegistro;
    private Label.Label LblEtiqISC;
    private Label.Label LblEtiqIgv;
    private Label.Label LblEtiqMoneda;
    private Label.Label LblEtiqMontoTotal;
    private Label.Label LblEtiqNum;
    private Label.Label LblEtiqPercepcion;
    private Label.Label LblEtiqSubNetoSinIgv;
    private Label.Label LblEtiqSubtotalBruto;
    private Label.Label LblEtiqSubtotalNeto;
    private Label.Label LblEtiqSucursal;
    private Label.Label LblEtiqTipoCambio;
    private Label.Label LblEtiqTipoDescuento;
    private Label.Label LblEtiqTotalPagar;
    private Label.Label LblHoraSistema;
    private Label.Label LblISC;
    private Label.Label LblIgv;
    private Label.Label LblMontoTotal;
    private Label.Label LblMontoTotalPagar;
    private Label.Label LblPorcentaje;
    private Label.Label LblSubTotalNetoSinIgv;
    private Label.Label LblSubtotalBruto;
    private Label.Label LblSubtotalNeto;
    private javax.swing.JTextField LblUltimoNumeroPedido;
    private javax.swing.JPanel PnlDatos2;
    private javax.swing.JTable TblPedidoDetalle;
    private TextField.JTxtLetra TxtCliente;
    private Label.Label TxtDNI;
    private javax.swing.JTextField TxtDescuento;
    private Label.Label TxtDireccion;
    private Label.Label TxtEstado;
    private TextField.JTxtNinguno TxtProducto;
    private Label.Label TxtSucursal;
    private Label.Label TxtTipoCambio;
    private Label.Label TxtVendedor;
    private Label.Label TxtlHoraRegistro1;
    private BotonesABM.BtnEliminar btnAnular;
    private BotonesABM.BtnBuscar btnBuscarPedido;
    private BotonesABM.BtnCancelar btnCancelar;
    private BotonesABM.BtnEditar btnEditar;
    private BotonesABM.BtnExportar btnExportar1;
    private BotonesABM.BtnGuardar btnGuardar;
    private BotonesABM.BtnBuscar btnImportarProforma;
    private BotonesABM.BtnBuscar btnImportarProforma1;
    private BotonesABM.BtnNuevo btnNuevo;
    private Opcion.CheckBox chkBusqDet;
    private Opcion.CheckBox chkFecha;
    private Opcion.CheckBox chkPercepcion;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label lblEtiqEstado;
    private Label.Label lblEtiqTipoComp;
    private Label.Label lblEtiqVendedor;
    private Label.Label lblMontoPercepcion;
    // End of variables declaration//GEN-END:variables

    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    private static final String ACTION_GUARDAR = "ACTION_NUEVO";
    private static final String ACTION_VER_PRECIOS= "ACTION_VER_PRECIOS";
    private static final String ACTION_QUITAR= "ACTION_QUITAR";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();

        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke Guardad = KeyStroke.getKeyStroke(KeyEvent.VK_G,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        KeyStroke VerPrecios = KeyStroke.getKeyStroke(KeyEvent.VK_P,java.awt.event.InputEvent.CTRL_DOWN_MASK );
        rootPane.registerKeyboardAction(this,ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_GUARDAR, Guardad, JComponent.WHEN_IN_FOCUSED_WINDOW);
        rootPane.registerKeyboardAction(this,ACTION_VER_PRECIOS, VerPrecios, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(ACTION_CLOSE))
        {
            eventoCancelar();

        }

        if(btnGuardar.isVisible()){
             if(e.getActionCommand().equals(ACTION_GUARDAR))
            {
                  eventoGuardarPedido();
                  return;
            }
        }
        if(e.getActionCommand().equals(ACTION_VER_PRECIOS))
        {
            verPrecios(0);return;
        }
  
    }

}
