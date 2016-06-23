package view;

import controller.acceso.ConexionBD;
import controller.grlGeneral.CCAtencionSistema;
import controller.grlGeneral.CCUsuario;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import modelo.grlGeneral.datos.CDImprimir;
import modelo.grlGeneral.entidad.CEComponenteAccion;
import modelo.grlGeneral.entidad.CEUsuario;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.CLHabilitarControles;
import util.clases.grlGeneral.CLImprimir;
import util.clases.grlGeneral.JMenuItemValidado;
import util.clases.vtaVenta.CLImprimirComprobante;
import view.omeOperacionMonedaExtranjera.DlgMantenimientoTipoCambioDia;
import view.almAlmacen.DlgGestionProducto;
import view.almAlmacen.DlgListadoProductos;
import view.almAlmacen.DlgMantenimientoMarca;
import view.almAlmacen.DlgMantenimientoUnidadPresentacion;
import view.almAlmacen.DlgMantenimientoUnidadVenta;
import view.almAlmacen.DlgStockProducto;
import view.almAlmacen.invInventarios.DlgGuiaRemisionRecibido1;
import view.almAlmacen.invInventarios.DlgIngresoProducto1;
import view.almAlmacen.invInventarios.DlgReporteEntradaAlmacen;
import view.almAlmacen.invInventarios.DlgReporteListadoStockProductos;
import view.almAlmacen.invInventarios.DlgReporteSalidaAlmacen;
import view.almAlmacen.invInventarios.DlgReporteStockMinimo;
import view.almAlmacen.invInventarios.DlgSalidaProducto1;
import view.almAlmacen.invInventarios.DlgTransferenciaAlmacen;
import view.almAlmacen.invInventarios.DlgRPTKardexProducto;
import view.almAlmacen.invInventarios.DlgRPTKardexProducto_Todos;
import view.almAlmacen.invInventarios.DlgVentasPorJerarquiaDeProductos;
import view.cmpCompra.DlgComprobanteCompra;
import view.cmpCompra.DlgGestionComprobanteCompra;
import view.cmpCompra.DlgGestionOrdenCompra;
import view.cmpCompra.DlgNotaCreditoProveedor;
import view.cmpCompra.rptVTAReportes.DlgReporteCierreCajaEgresos;
import view.cmpCompra.rptVTAReportes.DlgReporteCompPorEstado;
import view.cmpCompra.rptVTAReportes.DlgReporteOrdenCompPorEstado;

import view.cmrComercial.DlgGestionCliente;
import view.cmrComercial.DlgGestionEmpleado;
import view.cmrComercial.DlgGestionProveedor;
import view.ctbContabilidad.DlgGestionCuentaCorrienteCliente;
import view.ctbContabilidad.DlgGestionCuentaCorrienteProveedor;
import view.grlGeneral.DlgAperturaSistema;
import view.grlGeneral.DlgCambiarClaveUsuario;
import view.grlGeneral.DlgCerrarSistema;
import view.grlGeneral.DlgMantenimientoRol;
import view.grlGeneral.DlgMantenimientoUsuario;
import view.grlGeneral.DlgMantenimientoAccion;
import view.grlGeneral.DlgMantenimientoComponente;
import view.grlGeneral.DlgRelacionComponenteAccion;
import view.grlGeneral.DlgRelacionComponenteRolAccion;
import view.grlGeneral.DlgRelacionUsaurioRol;
import view.grlGeneral.DlgconfBackup;
import view.grlGeneral.dlgImprimir;
import view.tsoTesoreria.DlgAperturaCaja;
import view.tsoTesoreria.DlgCuadreCaja;
import view.vtaVenta.DlgCorrelativo;
import view.vtaVenta.DlgCorrelativo1;
import view.vtaVenta.DlgGenerarCopiaSeguridad;
import view.vtaVenta.DlgGestionComprobanteVenta;
import view.vtaVenta.DlgGestionCobrosPorPedido;
import view.vtaVenta.DlgGestionPedido;
import view.vtaVenta.DlgGestionProforma;
import view.vtaVenta.DlgPuntoVenta;
import view.vtaVenta.DlgNotaCredito;
import view.vtaVenta.DlgReporteListadoVentaProducto;
import view.vtaVenta.DlgReporteVentasPorVendedor;
import view.vtaVenta.rptVTAReportes.view.DlgRPTUtilidadProductoPorTipoComp;
import view.vtaVenta.rptVTAReportes.view.DlgRPTUtilidadProductoPorVenta;
import view.vtaVenta.rptVTAReportes.view.DlgReporteCierreCaja;
import view.vtaVenta.rptVTAReportes.view.DlgReporteCierreCajaResumen;
import view.vtaVenta.rptVTAReportes.view.DlgReporteIngresosPorDia;
import view.vtaVenta.rptVTAReportes.view.DlgReporteIngresosPorMes;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoCompDevolucion;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoComprobanteCancelado;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoComprobanteCanceladoImpuesto;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoComprobantesAnulados;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoPedidosAnulados;
import view.vtaVenta.rptVTAReportes.view.DlgReporteListadoPedidosCancelado;
import view.vtaVenta.rptVTAReportes.view.DlgReporteVentaProductosPorCliente;
import view.vtaVenta.rptVTAReportes.view.DlgReporteRankingProducto;
import view.vtaVenta.rptVTAReportes.view.DlgReporteRankingProductosProProv;
import view.vtaVenta.rptVTAReportes.view.DlgReporteUtilidadBrutaPorMes;
import view.vtaVenta.rptVTAReportes.view.DlgReporteVentaClientePorProducto;

public class FrmSistemaMenu extends javax.swing.JFrame
{
    private static int IdSucursal;
    public static CEUsuario oCEUsuario;
    private static boolean SiNoAtencion =false;
    public static Date FechaSistema=null;
    public static List<CLImprimir> listImprimir;
    public static int IdPuntoVenta;
    public static CEPuntoVenta oCEPuntoVenta;
    /**
     * @return the IdSucursal
     */
    public static int getIdSucursal() {
        return IdSucursal;
    }

    public static void setListImprimir(List<CLImprimir> listImprimir) {
        FrmSistemaMenu.listImprimir = listImprimir;
    }
   
    /**
     * @param aIdSucursal the IdSucursal to set
     */
    public static void setIdSucursal(int aIdSucursal) {
        IdSucursal = aIdSucursal;
    }

    /**
     * @return the SiNoAtencion
     */
    public static boolean isSiNoAtencion() {
        return SiNoAtencion;
    }

    /**
     * @param aSiNoAtencion the SiNoAtencion to set
     */
    public static void setSiNoAtencion(int aSiNoAtencion)
    {
        if(aSiNoAtencion==2)
        SiNoAtencion = true;
        else
        SiNoAtencion = false;
        LblSucursal.setText("Estado del Sistema: "+(aSiNoAtencion==2?"OPERATIVO":aSiNoAtencion==1?"SIN APERTURAR":"CERRADO"));
    }
    public FrmSistemaMenu(CEUsuario oCEUsuario)
    {
        initComponents();
        try
        {

            jMenu10.setVisible(false);
            FrmSistemaMenu.setListImprimir(CDImprimir.Listado(0));

            Properties props = new Properties();
            try {
                props.load(ConexionBD.class.getResourceAsStream("PropiedadesDelSistema.properties"));
            } catch (IOException ex) {
                Logger.getLogger(FrmSistemaMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sIdSucursal = props.getProperty("ayn.IdSucursal");
            IdSucursal=Integer.parseInt(sIdSucursal);
            oCEUsuario.setIp(InetAddress.getLocalHost().getHostAddress());
            FrmSistemaMenu.oCEUsuario=oCEUsuario;
            this.setExtendedState(FrmSistemaMenu.MAXIMIZED_BOTH);
            jPanelImage1.setImage(new javax.swing.ImageIcon(getClass().getResource("/util/icono/portada.png")).getImage());
            LblIP.setText("IP : " +oCEUsuario.getIp());
            oCEPuntoVenta=CCUsuario.consultarTerminal(oCEUsuario);
            if(oCEPuntoVenta!=null)
            {
               LblTerminal.setText("Terminal : " +oCEPuntoVenta.getDescrpcion());
               IdPuntoVenta=oCEPuntoVenta.getIdPuntoVenta();
            }else
            {
               LblTerminal.setText("Terminal : NO REGISTRADO");
               IdPuntoVenta=0;
            }
            LblUsuario.setText("Usuario : " + oCEUsuario.getUsuario());
            LblSucursal.setText("Sucursal : "+oCEUsuario.getSucursal());
            LblRol.setText("Rol :"+oCEUsuario.getRol());
            setSiNoAtencion(CCAtencionSistema.ConsultarAtencionSistema());
            this.FechaSistema=CLConsultarFechaSistema.consultarFecha_Date();
            LblFecha.setText("Fecha : "+CLConsultarFechaSistema.consultarFecha());
            
            setIconImage (new ImageIcon(getClass().getResource("/util/icono/ayn.png")).getImage());
          //  desahibilitarMenu();

            for(CEComponenteAccion oCEComponenteAccion:oCEUsuario.getoListComponenteAccion())
            {
                CLHabilitarControles oCLHabilitarControles=new CLHabilitarControles();
                oCLHabilitarControles.HabilitarControles(this.getRootPane().getJMenuBar(),oCEComponenteAccion.getComponente());
            }
          BtnProductos.setVisible(JMenuListadoProductos.isVisible());
          BtnClientes.setVisible(JMenuItemCliente.isVisible());
          BtnCobrosPedidos.setVisible(JMenuItemCobroPorPedido.isVisible());
          BtnCompras.setVisible(jMnItOrdenCompra.isVisible());
          BtnPagosxCompras.setVisible(MnItPagosPorCompra.isVisible());
          BtnPedidos.setVisible(JMenuItemGestionPedidos.isVisible());
          BtnProveedores.setVisible(JMenuItemEmpleado.isVisible());
        }
        catch (UnknownHostException ex)
        {
            Logger.getLogger(FrmSistemaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jToolBar1 = new javax.swing.JToolBar();
        BtnProductos = new javax.swing.JButton();
        BtnClientes = new javax.swing.JButton();
        BtnPedidos = new javax.swing.JButton();
        BtnCobrosPedidos = new javax.swing.JButton();
        BtnProveedores = new javax.swing.JButton();
        BtnCompras = new javax.swing.JButton();
        BtnPagosxCompras = new javax.swing.JButton();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        LblUsuario = new javax.swing.JLabel();
        LblRol = new javax.swing.JLabel();
        LblTerminal = new javax.swing.JLabel();
        LblIP = new javax.swing.JLabel();
        LblFecha = new javax.swing.JLabel();
        LblSucursal = new javax.swing.JLabel();
        LblMensajeSistema = new javax.swing.JLabel();
        jPanelImage1 = new util.clases.grlGeneral.JPanelImage();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMnAlmacen = new javax.swing.JMenu();
        JMenuItemCatalagoProducto = new javax.swing.JMenuItem();
        JMenuListadoProductos = new javax.swing.JMenuItem();
        JMenuItemStockProducto = new javax.swing.JMenuItem();
        MnItIngreso = new javax.swing.JMenuItem();
        JmnItSalida = new javax.swing.JMenuItem();
        MnItTransferencia = new javax.swing.JMenuItem();
        jMnCompras = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMnItOrdenCompra = new javax.swing.JMenuItem();
        MnItPagosPorCompra = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        JMenuItemGestionPedidos = new javax.swing.JMenuItem();
        JMenuItemCobroPorPedido = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        JMenuItemNotaCredito = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        JMenuItemCuentaCorriente = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        JMenuItemListaPedidoAnulados = new javax.swing.JMenuItem();
        JMenuItemListadoPedidosCancelados = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        JMenuItemListadoComprabanteAnulados = new javax.swing.JMenuItem();
        JMenuItemJerarquiaProductos = new javax.swing.JMenuItem();
        JMenuItemVentasPorEmpleado = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        JMenuItemProductosMasVendidos = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        JMenuItemReporteCierreCaja = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        JMenuItemAbrirCaja = new JMenuItemValidado();
        JMenuItemCuadreCaja = new JMenuItemValidado();
        JMenuItemEgresos = new JMenuItemValidado();
        jMenu3 = new javax.swing.JMenu();
        JMenuItemCliente = new javax.swing.JMenuItem();
        JMenuItemEmpleado = new javax.swing.JMenuItem();
        JMenuItemProveedor = new javax.swing.JMenuItem();
        MniGeneral1 = new javax.swing.JMenu();
        JMenuItemAccion = new javax.swing.JMenuItem();
        JMenuItemComponente = new javax.swing.JMenuItem();
        JMenuItemComponenteAccion = new javax.swing.JMenuItem();
        JMenuItemRoles = new javax.swing.JMenuItem();
        JMenuItemUsuario = new javax.swing.JMenuItem();
        JMenuItemUsuarioPoRol = new javax.swing.JMenuItem();
        JMenuItemRolComponenteAccion = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        JMenuItemUnidadVenta = new javax.swing.JMenuItem();
        JMenuItemUnidadPresentacion = new javax.swing.JMenuItem();
        JMenuItemMarca = new javax.swing.JMenuItem();
        JMenuItemTipoCambio = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItemAbrirSistema = new javax.swing.JMenuItem();
        jMenuItemCerrarSistema = new javax.swing.JMenuItem();
        JMenuItemCerrarSesion = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Corporación Angie & Naiudu");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        BtnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icon-product.gif"))); // NOI18N
        BtnProductos.setText("Productos");
        BtnProductos.setFocusable(false);
        BtnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProductosActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnProductos);

        BtnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/customers24.png"))); // NOI18N
        BtnClientes.setText("Clientes");
        BtnClientes.setFocusable(false);
        BtnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClientesActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnClientes);

        BtnPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/img_Menu_menu4_ventas.png"))); // NOI18N
        BtnPedidos.setText("Pedidos");
        BtnPedidos.setFocusable(false);
        BtnPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPedidos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPedidosActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnPedidos);

        BtnCobrosPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/how_to_pay.png"))); // NOI18N
        BtnCobrosPedidos.setText("Cobros x Ped.");
        BtnCobrosPedidos.setFocusable(false);
        BtnCobrosPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCobrosPedidos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCobrosPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCobrosPedidosActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnCobrosPedidos);

        BtnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/proveedores2.png"))); // NOI18N
        BtnProveedores.setText("Proveedores");
        BtnProveedores.setFocusable(false);
        BtnProveedores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnProveedores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProveedoresActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnProveedores);

        BtnCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/compra.png"))); // NOI18N
        BtnCompras.setText("Compras");
        BtnCompras.setFocusable(false);
        BtnCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnComprasActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnCompras);

        BtnPagosxCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Pago.png"))); // NOI18N
        BtnPagosxCompras.setText("Gestion de Compras");
        BtnPagosxCompras.setFocusable(false);
        BtnPagosxCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnPagosxCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnPagosxCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPagosxComprasActionPerformed(evt);
            }
        });
        jToolBar1.add(BtnPagosxCompras);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jXStatusBar1.setBackground(java.awt.SystemColor.info);

        LblUsuario.setFont(new java.awt.Font("Arial", 1, 14));
        LblUsuario.setText("Usuario:");
        jXStatusBar1.add(LblUsuario);

        LblRol.setFont(new java.awt.Font("Arial", 1, 14));
        LblRol.setText("Rol:");
        jXStatusBar1.add(LblRol);

        LblTerminal.setFont(new java.awt.Font("Arial", 1, 14));
        LblTerminal.setText("Terminal:");
        jXStatusBar1.add(LblTerminal);

        LblIP.setFont(new java.awt.Font("Arial", 1, 14));
        LblIP.setText("IP:");
        jXStatusBar1.add(LblIP);

        LblFecha.setFont(new java.awt.Font("Arial", 1, 14));
        LblFecha.setText("Fecha:");
        jXStatusBar1.add(LblFecha);

        LblSucursal.setFont(new java.awt.Font("Arial", 1, 14));
        LblSucursal.setText("Sucursal:");
        jXStatusBar1.add(LblSucursal);

        LblMensajeSistema.setFont(new java.awt.Font("Arial", 1, 14));
        LblMensajeSistema.setText("Mensaje:");
        jXStatusBar1.add(LblMensajeSistema);

        getContentPane().add(jXStatusBar1, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(jPanelImage1, java.awt.BorderLayout.CENTER);

        JMnAlmacen.setText("Almacén");
        JMnAlmacen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMnAlmacenPropertyChange(evt);
            }
        });

        JMenuItemCatalagoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/dir.gif"))); // NOI18N
        JMenuItemCatalagoProducto.setText("Catalogo de Productos");
        JMenuItemCatalagoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCatalagoProductoActionPerformed(evt);
            }
        });
        JMnAlmacen.add(JMenuItemCatalagoProducto);

        JMenuListadoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icon-product.gif"))); // NOI18N
        JMenuListadoProductos.setText("Listado de Productos");
        JMenuListadoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuListadoProductosActionPerformed(evt);
            }
        });
        JMenuListadoProductos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuListadoProductosPropertyChange(evt);
            }
        });
        JMnAlmacen.add(JMenuListadoProductos);

        JMenuItemStockProducto.setText("Stock de Productos");
        JMenuItemStockProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemStockProductoActionPerformed1(evt);
            }
        });
        JMnAlmacen.add(JMenuItemStockProducto);

        MnItIngreso.setText("Ingresos de Almacén");
        MnItIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnItIngresoActionPerformed1(evt);
            }
        });
        JMnAlmacen.add(MnItIngreso);

        JmnItSalida.setText("Salidas de Almacén");
        JmnItSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmnItSalidaActionPerformed(evt);
            }
        });
        JMnAlmacen.add(JmnItSalida);

        MnItTransferencia.setText("Transferencia de Almacén");
        MnItTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnItTransferenciaActionPerformed(evt);
            }
        });
        JMnAlmacen.add(MnItTransferencia);

        jMenuBar1.add(JMnAlmacen);

        jMnCompras.setText("Compras");

        jMenuItem23.setText("Orden de Compra");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed1(evt);
            }
        });
        jMnCompras.add(jMenuItem23);

        jMnItOrdenCompra.setText("Compras");
        jMnItOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItOrdenCompraActionPerformed(evt);
            }
        });
        jMnItOrdenCompra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jMnItOrdenCompraPropertyChange(evt);
            }
        });
        jMnCompras.add(jMnItOrdenCompra);

        MnItPagosPorCompra.setText("Gestión de Compras");
        MnItPagosPorCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnItPagosPorCompraActionPerformed(evt);
            }
        });
        jMnCompras.add(MnItPagosPorCompra);

        jMenuItem24.setText("Nota Crédito Proveedor");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMnCompras.add(jMenuItem24);

        jMenuItem33.setText("Guia Remisión Recibido");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMnCompras.add(jMenuItem33);

        jMenuBar1.add(jMnCompras);

        jMenu2.setText("Ventas");

        JMenuItemGestionPedidos.setText("Pedidos");
        JMenuItemGestionPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemGestionPedidosActionPerformed(evt);
            }
        });
        JMenuItemGestionPedidos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuItemGestionPedidosPropertyChange(evt);
            }
        });
        jMenu2.add(JMenuItemGestionPedidos);

        JMenuItemCobroPorPedido.setText("Cobros por Pedidos");
        JMenuItemCobroPorPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCobroPorPedidoActionPerformed(evt);
            }
        });
        JMenuItemCobroPorPedido.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuItemCobroPorPedidoPropertyChange(evt);
            }
        });
        jMenu2.add(JMenuItemCobroPorPedido);

        jMenuItem13.setText("Comprobantes de Venta");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        JMenuItemNotaCredito.setText("Nota de Crédito");
        JMenuItemNotaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemNotaCreditoActionPerformed(evt);
            }
        });
        jMenu2.add(JMenuItemNotaCredito);

        jMenuItem34.setText("Devoluciones de Dinero");
        jMenuItem34.setToolTipText("");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem34);

        jMenuItem32.setText("Proformas");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem32);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Contabilidad");

        JMenuItemCuentaCorriente.setText("Cuenta Corriente Cliente");
        JMenuItemCuentaCorriente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCuentaCorrienteActionPerformed(evt);
            }
        });
        jMenu6.add(JMenuItemCuentaCorriente);

        jMenuItem26.setText("Cuenta Corriente Proveedor");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Reportes");

        jMenu1.setText("Ventas");

        JMenuItemListaPedidoAnulados.setText("Pedidos Anulados");
        JMenuItemListaPedidoAnulados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemListaPedidoAnuladosActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemListaPedidoAnulados);

        JMenuItemListadoPedidosCancelados.setText("Pedidos Cobrados");
        JMenuItemListadoPedidosCancelados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemListadoPedidosCanceladosActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemListadoPedidosCancelados);

        jMenuItem9.setText("Comprobantes Cancelados");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem20.setText("Comprobantes Cancelados Con Exonerado");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem20);

        jMenuItem38.setText("Listado de Comprobantes con Producto");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem38);

        JMenuItemListadoComprabanteAnulados.setText("Comprobantes Anulados");
        JMenuItemListadoComprabanteAnulados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemListadoComprabanteAnuladosActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemListadoComprabanteAnulados);

        JMenuItemJerarquiaProductos.setText("Ventas por Jerarquía de Productos");
        JMenuItemJerarquiaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemJerarquiaProductosActionPerformed1(evt);
            }
        });
        jMenu1.add(JMenuItemJerarquiaProductos);

        JMenuItemVentasPorEmpleado.setText("Ventas por Empleado");
        JMenuItemVentasPorEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemVentasPorEmpleadoActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemVentasPorEmpleado);

        jMenuItem28.setText("Ventas Productos Por Cliente");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem28);

        JMenuItemProductosMasVendidos.setText("Ranking de Productos mas Vendidos");
        JMenuItemProductosMasVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemProductosMasVendidosActionPerformed(evt);
            }
        });
        jMenu1.add(JMenuItemProductosMasVendidos);

        jMenuItem17.setText("Ranking de Productos mas Vendidos Por Proveedor");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem17);

        jMenuItem6.setText("Lista de Devoluciones");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem8.setText("Lista Notas de Crédito");
        jMenu1.add(jMenuItem8);

        jMenuItem11.setText("Ingresos Diarios Por Mes");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem21.setText("Ingresos Mensuales Por Año");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuItem30.setText("Utilidad Mensual");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem30);

        jMenuItem29.setText("Ventas Clientes Por Producto");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem29);

        jMenuItem36.setText("Utilidad Por Producto");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem36);

        jMenuItem37.setText("Utilidad De Producto Por Tipo Comprobante");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem37);

        jMenu7.add(jMenu1);

        jMenu5.setText("Compras");

        jMenuItem25.setText("Lista Ordenes de Compra");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem25);

        jMenuItem19.setText("Lista de Compras");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jMenuItem22.setText("Lista de Pagos");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenu7.add(jMenu5);

        jMenu8.setText("Caja");

        JMenuItemReporteCierreCaja.setText("Reporte Cierre Caja (Solo Ingresos)");
        JMenuItemReporteCierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemReporteCierreCajaActionPerformed(evt);
            }
        });
        jMenu8.add(JMenuItemReporteCierreCaja);

        jMenuItem5.setText("Reporte Cierre Caja Egresos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenuItem1.setText("Reporte Cierre Caja Resumen");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed1(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        jMenu7.add(jMenu8);

        jMenu11.setText("Almacén");

        jMenuItem16.setText("Kardex de Producto");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem16);

        jMenuItem27.setText("Kardex de todos los productos");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem27);

        jMenuItem14.setText("Reporte Entradas de Almacén");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem14);

        jMenuItem15.setText("Reporte Salidas de Almacén");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem15);

        jMenuItem18.setText("Productos Por Stock Mínimo");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem18);

        jMenuItem10.setText("Lista Stock de productos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem10);

        jMenu7.add(jMenu11);

        jMenuBar1.add(jMenu7);

        jMenu10.setText("Tesoreria");

        JMenuItemAbrirCaja.setText("Abrir Caja");
        JMenuItemAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemAbrirCajaActionPerformed(evt);
            }
        });
        jMenu10.add(JMenuItemAbrirCaja);

        JMenuItemCuadreCaja.setText("Cuadre de Caja");
        JMenuItemCuadreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCuadreCajaActionPerformed(evt);
            }
        });
        jMenu10.add(JMenuItemCuadreCaja);

        JMenuItemEgresos.setText("Egresos");
        jMenu10.add(JMenuItemEgresos);

        jMenuBar1.add(jMenu10);

        jMenu3.setText("Tablas");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        JMenuItemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/customers.png"))); // NOI18N
        JMenuItemCliente.setText("Cliente");
        JMenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemClienteActionPerformed(evt);
            }
        });
        JMenuItemCliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuItemClientePropertyChange(evt);
            }
        });
        jMenu3.add(JMenuItemCliente);

        JMenuItemEmpleado.setText("Empleado");
        JMenuItemEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemEmpleadoActionPerformed(evt);
            }
        });
        JMenuItemEmpleado.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuItemEmpleadoPropertyChange(evt);
            }
        });
        jMenu3.add(JMenuItemEmpleado);

        JMenuItemProveedor.setText("Proveedor");
        JMenuItemProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemProveedorActionPerformed(evt);
            }
        });
        JMenuItemProveedor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JMenuItemProveedorPropertyChange(evt);
            }
        });
        jMenu3.add(JMenuItemProveedor);

        MniGeneral1.setText("Accesos de Usuario");

        JMenuItemAccion.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemAccion.setText("Accion");
        JMenuItemAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemAccionActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemAccion);

        JMenuItemComponente.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemComponente.setText("Componente");
        JMenuItemComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemComponenteActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemComponente);

        JMenuItemComponenteAccion.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemComponenteAccion.setText("Componente Accion");
        JMenuItemComponenteAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemComponenteAccionActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemComponenteAccion);

        JMenuItemRoles.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemRoles.setText("Roles");
        JMenuItemRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemRolesActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemRoles);

        JMenuItemUsuario.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemUsuario.setText("Usuario");
        JMenuItemUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemUsuarioActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemUsuario);

        JMenuItemUsuarioPoRol.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemUsuarioPoRol.setText("Usuario por Rol");
        JMenuItemUsuarioPoRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemUsuarioPoRolActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemUsuarioPoRol);

        JMenuItemRolComponenteAccion.setFont(new java.awt.Font("Arial", 0, 14));
        JMenuItemRolComponenteAccion.setText("Permisos de Rol");
        JMenuItemRolComponenteAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemRolComponenteAccionActionPerformed(evt);
            }
        });
        MniGeneral1.add(JMenuItemRolComponenteAccion);

        jMenu3.add(MniGeneral1);

        jMenu4.setText("Almacen");

        JMenuItemUnidadVenta.setText("Unidad de Medida");
        JMenuItemUnidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemUnidadVentaActionPerformed(evt);
            }
        });
        jMenu4.add(JMenuItemUnidadVenta);

        JMenuItemUnidadPresentacion.setText("Unidad Presentacion");
        JMenuItemUnidadPresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemUnidadPresentacionActionPerformed(evt);
            }
        });
        jMenu4.add(JMenuItemUnidadPresentacion);

        JMenuItemMarca.setText("Marca");
        JMenuItemMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemMarcaActionPerformed(evt);
            }
        });
        jMenu4.add(JMenuItemMarca);

        jMenu3.add(jMenu4);

        JMenuItemTipoCambio.setText("Tipo Cambio");
        JMenuItemTipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemTipoCambioActionPerformed(evt);
            }
        });
        jMenu3.add(JMenuItemTipoCambio);

        jMenuItem39.setText("Punto Venta");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem39);

        jMenuItem7.setVisible(true);
        jMenuItem7.setText("Calibrar Impresora");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem2.setText("Ver Impresoras");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed1(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem31.setText("Cambiar Clave de Acceso");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenuItem35.setText("Correlativo");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem35);

        jMenuBar1.add(jMenu3);

        jMenu9.setText("Sistema");
        jMenu9.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu9MenuSelected(evt);
            }
        });

        jMenuItemAbrirSistema.setText("Abrir Sistema");
        jMenuItemAbrirSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirSistemaActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItemAbrirSistema);

        jMenuItemCerrarSistema.setText("Cerrar Sistema");
        jMenuItemCerrarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCerrarSistemaActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItemCerrarSistema);

        JMenuItemCerrarSesion.setText("Cerrar Sesión");
        JMenuItemCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemCerrarSesionActionPerformed(evt);
            }
        });
        jMenu9.add(JMenuItemCerrarSesion);

        jMenuItem4.setText("Copia de Seguridad");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem4);

        jMenuItem12.setText("Configuracion de Backup");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem12);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMenuItemCatalagoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemCatalagoProductoActionPerformed
        DlgGestionProducto oDlgBusquedaProducto=new DlgGestionProducto(this,false);
        oDlgBusquedaProducto.setLocationRelativeTo(null);
        oDlgBusquedaProducto.setVisible(true);
    }//GEN-LAST:event_JMenuItemCatalagoProductoActionPerformed

    private void JMenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemClienteActionPerformed
    dialogo = new DlgGestionCliente(this, false,0,1,1);
    dialogo.setTitle("Gestión de Clientes");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemClienteActionPerformed

    private void JMenuItemEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemEmpleadoActionPerformed
    dialogo = new DlgGestionEmpleado(this, false,0,1,1);
    dialogo.setTitle("Gestión de Empleados");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemEmpleadoActionPerformed

    private void JMenuItemGestionPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemGestionPedidosActionPerformed
     dialogo = new DlgGestionPedido(this, false);
     dialogo.setLocationRelativeTo(null);
     dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemGestionPedidosActionPerformed

    private void JMenuItemAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemAccionActionPerformed
        dialogo=new DlgMantenimientoAccion(this, false, 0);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemAccionActionPerformed

    private void JMenuItemComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemComponenteActionPerformed
        dialogo=new DlgMantenimientoComponente(this, false, 0);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemComponenteActionPerformed

    private void JMenuItemRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemRolesActionPerformed
        dialogo=new DlgMantenimientoRol(this,false, 0);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemRolesActionPerformed

    private void JMenuItemUsuarioPoRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemUsuarioPoRolActionPerformed
        dialogo =new DlgRelacionUsaurioRol(this,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemUsuarioPoRolActionPerformed

    private void JMenuItemComponenteAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemComponenteAccionActionPerformed
        dialogo =new DlgRelacionComponenteAccion(this,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemComponenteAccionActionPerformed

    private void JMenuItemUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemUsuarioActionPerformed
        dialogo =new DlgMantenimientoUsuario(this,false, WIDTH);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemUsuarioActionPerformed

    private void JMenuItemRolComponenteAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemRolComponenteAccionActionPerformed
        dialogo =new DlgRelacionComponenteRolAccion(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);// TODO add your handling code here:
}//GEN-LAST:event_JMenuItemRolComponenteAccionActionPerformed

    private void BtnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProveedoresActionPerformed
       dialogo = new DlgGestionProveedor(this, false,0,1,1);
     dialogo.setTitle("Gestión de Proveedor");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_BtnProveedoresActionPerformed

    private void BtnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClientesActionPerformed
          dialogo = new DlgGestionCliente(this, false,0,1,1);
      dialogo.setTitle("Gestión de Clientes");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_BtnClientesActionPerformed

    private void BtnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProductosActionPerformed
  
    dialogo = new DlgListadoProductos(this, false);
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_BtnProductosActionPerformed

    private void JMenuItemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemCerrarSesionActionPerformed
    dispose();
    }//GEN-LAST:event_JMenuItemCerrarSesionActionPerformed

                                    


    private void JMenuItemNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemNotaCreditoActionPerformed
    dialogo = new DlgNotaCredito(this, false,0,0,false);
    dialogo.setTitle("Nota de Crédito");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemNotaCreditoActionPerformed

    private void JMenuItemCobroPorPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemCobroPorPedidoActionPerformed
    dialogo = new DlgGestionCobrosPorPedido(this, false);
    dialogo.setTitle("Cobros Por Pedido");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemCobroPorPedidoActionPerformed

    private void JMenuItemUnidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemUnidadVentaActionPerformed
    dialogo = new DlgMantenimientoUnidadVenta(this, true,0);
    dialogo.setTitle("Formulario Unidad Venta");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemUnidadVentaActionPerformed

    private void JMenuItemUnidadPresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemUnidadPresentacionActionPerformed
    dialogo = new DlgMantenimientoUnidadPresentacion(this, true,0);
    dialogo.setTitle("Formulario Unidad Presentacion");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemUnidadPresentacionActionPerformed

    private void JMenuItemMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemMarcaActionPerformed
    dialogo = new DlgMantenimientoMarca(this, true,0);
    dialogo.setTitle("Formulario Marca");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemMarcaActionPerformed

    private void JMenuListadoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuListadoProductosActionPerformed
    dialogo = new DlgListadoProductos(this, false);
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuListadoProductosActionPerformed

    private void BtnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPedidosActionPerformed
     dialogo = new DlgGestionPedido(this, false);
     dialogo.setLocationRelativeTo(null);
     dialogo.setVisible(true);
    }//GEN-LAST:event_BtnPedidosActionPerformed

    private void BtnCobrosPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCobrosPedidosActionPerformed
    dialogo = new DlgGestionCobrosPorPedido(this, true);
    dialogo.setTitle("Cobros Por Pedido");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_BtnCobrosPedidosActionPerformed

    private void JMenuItemCuentaCorrienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemCuentaCorrienteActionPerformed
    dialogo=new DlgGestionCuentaCorrienteCliente(this,false);
    dialogo.setTitle("Gestión de Cuentas Corrientes de Clientes");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemCuentaCorrienteActionPerformed

    private void JMenuItemTipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemTipoCambioActionPerformed
     dialogo=new DlgMantenimientoTipoCambioDia(this,false,0);
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemTipoCambioActionPerformed

    private void JMenuItemComprobanteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemComprobanteVentaActionPerformed
    
    }//GEN-LAST:event_JMenuItemComprobanteVentaActionPerformed
                                    

    private void jMenuItemAbrirSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirSistemaActionPerformed
     DlgAperturaSistema oDlgAperturaSistema=new DlgAperturaSistema(this,false);
     oDlgAperturaSistema.setLocationRelativeTo(null);
     oDlgAperturaSistema.setVisible(true);
    }//GEN-LAST:event_jMenuItemAbrirSistemaActionPerformed

    private void jMenuItemCerrarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCerrarSistemaActionPerformed
     DlgCerrarSistema oDlgAperturaSistema=new DlgCerrarSistema(this,false);
     oDlgAperturaSistema.setLocationRelativeTo(null);
     oDlgAperturaSistema.setVisible(true);
    }//GEN-LAST:event_jMenuItemCerrarSistemaActionPerformed

    private void JMenuItemCuadreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemCuadreCajaActionPerformed
        dialogo=new DlgCuadreCaja(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemCuadreCajaActionPerformed

    private void JMenuItemAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemAbrirCajaActionPerformed
        dialogo=new DlgAperturaCaja(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemAbrirCajaActionPerformed

    private void jMnItOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItOrdenCompraActionPerformed
        dialogo=new DlgComprobanteCompra(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMnItOrdenCompraActionPerformed

    private void JMenuItemProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemProveedorActionPerformed
    dialogo = new DlgGestionProveedor(this, false,0,1,1);
    dialogo.setTitle("Gestión de Proveedor");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_JMenuItemProveedorActionPerformed

    private void JMenuItemStockProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemStockProductoActionPerformed


    }//GEN-LAST:event_JMenuItemStockProductoActionPerformed

    private void JMnAlmacenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMnAlmacenPropertyChange
//      if(evt.getPropertyName().equals("visible"))
//      {
//          BtnProductos.setEnabled(Boolean.parseBoolean(evt.getNewValue()+""));
//      }
    }//GEN-LAST:event_JMnAlmacenPropertyChange

    private void JMenuItemClientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuItemClientePropertyChange
     
    }//GEN-LAST:event_JMenuItemClientePropertyChange

    private void JMenuItemEmpleadoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuItemEmpleadoPropertyChange
      
    }//GEN-LAST:event_JMenuItemEmpleadoPropertyChange

    private void JMenuItemCobroPorPedidoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuItemCobroPorPedidoPropertyChange
     
    }//GEN-LAST:event_JMenuItemCobroPorPedidoPropertyChange

    private void JMenuItemGestionPedidosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuItemGestionPedidosPropertyChange
     
    }//GEN-LAST:event_JMenuItemGestionPedidosPropertyChange

    private void MnItIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnItIngresoActionPerformed
        dialogo=new DlgIngresoProducto1(this, true,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_MnItIngresoActionPerformed

    private void JmnItSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmnItSalidaActionPerformed
     dialogo=new DlgSalidaProducto1(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_JmnItSalidaActionPerformed

    private void MnItTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnItTransferenciaActionPerformed
     dialogo=new DlgTransferenciaAlmacen(this, true);
     dialogo.setLocationRelativeTo(null);
     dialogo.setVisible(true);
    }//GEN-LAST:event_MnItTransferenciaActionPerformed

    private void jMenu9MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu9MenuSelected

        try {
        if(oCEUsuario.getUsuario().toUpperCase().equals("ADMIN")) {
            int a=CCAtencionSistema.ConsultarSistema();
            if(a==1) {
                jMenuItemAbrirSistema.setEnabled(false);
                jMenuItemCerrarSistema.setEnabled(false);
            } else {
                if(a==2) {
                    jMenuItemAbrirSistema.setEnabled(false);
                    jMenuItemCerrarSistema.setEnabled(true);
                } else {
                    if(a==4) {
                        jMenuItemAbrirSistema.setEnabled(true);
                        jMenuItemCerrarSistema.setEnabled(false);
                    }
                }
            }
        }
        }catch(Exception e)
        {

            
        }
}//GEN-LAST:event_jMenu9MenuSelected

    private void JMenuListadoProductosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuListadoProductosPropertyChange
      if(evt.getPropertyName().equals("enabled"))
      {
          BtnProductos.setEnabled(Boolean.parseBoolean(evt.getNewValue()+""));
      }
    }//GEN-LAST:event_JMenuListadoProductosPropertyChange

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        dialogo=new dlgImprimir(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void MnItIngresoActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnItIngresoActionPerformed1

         dialogo=new DlgIngresoProducto1(this, false,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        
    }//GEN-LAST:event_MnItIngresoActionPerformed1

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     //   CLImprimirComprobante oclCLImprimir=new CLImprimirComprobante();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void JMenuItemStockProductoActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemStockProductoActionPerformed1
      DlgStockProducto oDlgStockProducto=new DlgStockProducto(this, false);
      oDlgStockProducto.setVisible(true);
    }//GEN-LAST:event_JMenuItemStockProductoActionPerformed1

    private void BtnComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnComprasActionPerformed

        dialogo=new DlgComprobanteCompra(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        
    }//GEN-LAST:event_BtnComprasActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMnItOrdenCompraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jMnItOrdenCompraPropertyChange
        if(evt.getPropertyName().equals("enabled"))
      {
          BtnCompras.setEnabled(Boolean.parseBoolean(evt.getNewValue()+""));
      }
    }//GEN-LAST:event_jMnItOrdenCompraPropertyChange

    private void MnItPagosPorCompraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_MnItPagosPorCompraPropertyChange

        if(evt.getPropertyName().equals("enabled"))
      {
          BtnPagosxCompras.setEnabled(Boolean.parseBoolean(evt.getNewValue()+""));
      }

    }//GEN-LAST:event_MnItPagosPorCompraPropertyChange

    private void JMenuItemProveedorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JMenuItemProveedorPropertyChange

        if(evt.getPropertyName().equals("enabled"))
      {
          BtnProveedores.setEnabled(Boolean.parseBoolean(evt.getNewValue()+""));
      }
    }//GEN-LAST:event_JMenuItemProveedorPropertyChange

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        dialogo=new DlgGestionComprobanteVenta(this, false,true,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        dialogo=new DlgGenerarCopiaSeguridad(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void JMenuItemReporteCierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemReporteCierreCajaActionPerformed
        dialogo=new DlgReporteCierreCaja(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemReporteCierreCajaActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

       // dialogo=new DlgReporteCierreCajaConEgresos(this, false);
         dialogo=new DlgReporteCierreCajaEgresos(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_jMenuItem5ActionPerformed

    private void JMenuItemProductosMasVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemProductosMasVendidosActionPerformed
        dialogo=new DlgReporteRankingProducto(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemProductosMasVendidosActionPerformed

    private void JMenuItemVentasPorEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemVentasPorEmpleadoActionPerformed
        dialogo=new DlgReporteVentasPorVendedor(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemVentasPorEmpleadoActionPerformed

    private void JMenuItemJerarquiaProductosActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemJerarquiaProductosActionPerformed1

        dialogo=new DlgVentasPorJerarquiaDeProductos(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemJerarquiaProductosActionPerformed1

    private void JMenuItemListadoComprabanteAnuladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemListadoComprabanteAnuladosActionPerformed

        dialogo=new DlgReporteListadoComprobantesAnulados(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemListadoComprabanteAnuladosActionPerformed

    private void JMenuItemListadoPedidosCanceladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemListadoPedidosCanceladosActionPerformed
        dialogo=new DlgReporteListadoPedidosCancelado(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemListadoPedidosCanceladosActionPerformed

    private void JMenuItemListaPedidoAnuladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemListaPedidoAnuladosActionPerformed
        dialogo=new DlgReporteListadoPedidosAnulados(this, true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
}//GEN-LAST:event_JMenuItemListaPedidoAnuladosActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed

        dialogo=new DlgGestionComprobanteVenta(this, false,true,true);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

         dialogo=new DlgReporteListadoCompDevolucion(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);


    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        dialogo=new DlgReporteIngresosPorDia(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed

         dialogo=new DlgReporteIngresosPorMes(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

         dialogo=new DlgRPTKardexProducto(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed

        dialogo=new DlgconfBackup(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed

         dialogo=new DlgGestionOrdenCompra(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem23ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed1
         dialogo=new DlgGestionOrdenCompra(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed1

    private void BtnPagosxComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPagosxComprasActionPerformed

        DlgGestionComprobanteCompra odialogo= new DlgGestionComprobanteCompra(this, false);
        odialogo.setLocationRelativeTo(null);
        odialogo.ocultarBotones();
        odialogo.setVisible(true);
}//GEN-LAST:event_BtnPagosxComprasActionPerformed

    private void MnItPagosPorCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnItPagosPorCompraActionPerformed
        DlgGestionComprobanteCompra odialogo= new DlgGestionComprobanteCompra(this, false);
        odialogo.setLocationRelativeTo(null);
        odialogo.ocultarBotones();
        odialogo.setVisible(true);
    }//GEN-LAST:event_MnItPagosPorCompraActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        dialogo= new DlgReporteListadoComprobanteCancelado(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

        dialogo= new DlgReporteCompPorEstado(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
       dialogo= new DlgReporteOrdenCompPorEstado(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed

        dialogo= new DlgReporteSalidaAlmacen(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
         
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed

         dialogo= new DlgReporteEntradaAlmacen(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed

        dialogo= new DlgReporteListadoStockProductos(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed

        dialogo= new DlgReporteStockMinimo(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed

        dialogo= new DlgNotaCreditoProveedor(this, false,0,0);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);


    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
    dialogo=new DlgGestionCuentaCorrienteProveedor(this,false);
    dialogo.setTitle("Gestión de Cuentas Corrientes Empresa");
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed

        dialogo=new DlgRPTKardexProducto_Todos(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem2ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed1
        CLImprimirComprobante oclCLImprimir=new CLImprimirComprobante();
    }//GEN-LAST:event_jMenuItem2ActionPerformed1

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed

        dialogo=new DlgReporteVentaProductosPorCliente(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        dialogo=new DlgReporteVentaClientePorProducto(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed

        dialogo=new DlgReporteCierreCajaEgresos(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed

        dialogo=new DlgReporteRankingProductosProProv(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed

        dialogo=new DlgReporteUtilidadBrutaPorMes(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed


        dialogo=new DlgCambiarClaveUsuario(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        dialogo=new DlgGestionProforma(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed

        dialogo=new DlgGuiaRemisionRecibido1(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem1ActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed1
         dialogo=new DlgReporteCierreCajaResumen(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed1

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
        dialogo = new DlgNotaCredito(this, false,0,0,true);
        dialogo.setTitle("Comprobante de devolución");
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed

        dialogo=new DlgCorrelativo(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        dialogo= new DlgReporteListadoComprobanteCanceladoImpuesto(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        dialogo=new DlgRPTUtilidadProductoPorVenta(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        dialogo=new DlgRPTUtilidadProductoPorTipoComp(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
        dialogo=new DlgReporteListadoVentaProducto(this, false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
        dialogo=new DlgPuntoVenta(this, false,false);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

    }//GEN-LAST:event_jMenuItem39ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnClientes;
    private javax.swing.JButton BtnCobrosPedidos;
    private javax.swing.JButton BtnCompras;
    private javax.swing.JButton BtnPagosxCompras;
    private javax.swing.JButton BtnPedidos;
    private javax.swing.JButton BtnProductos;
    private javax.swing.JButton BtnProveedores;
    private javax.swing.JMenuItem JMenuItemAbrirCaja;
    private javax.swing.JMenuItem JMenuItemAccion;
    private javax.swing.JMenuItem JMenuItemCatalagoProducto;
    private javax.swing.JMenuItem JMenuItemCerrarSesion;
    private javax.swing.JMenuItem JMenuItemCliente;
    private javax.swing.JMenuItem JMenuItemCobroPorPedido;
    private javax.swing.JMenuItem JMenuItemComponente;
    private javax.swing.JMenuItem JMenuItemComponenteAccion;
    private javax.swing.JMenuItem JMenuItemCuadreCaja;
    private javax.swing.JMenuItem JMenuItemCuentaCorriente;
    private javax.swing.JMenuItem JMenuItemEgresos;
    private javax.swing.JMenuItem JMenuItemEmpleado;
    private javax.swing.JMenuItem JMenuItemGestionPedidos;
    private javax.swing.JMenuItem JMenuItemJerarquiaProductos;
    private javax.swing.JMenuItem JMenuItemListaPedidoAnulados;
    private javax.swing.JMenuItem JMenuItemListadoComprabanteAnulados;
    private javax.swing.JMenuItem JMenuItemListadoPedidosCancelados;
    private javax.swing.JMenuItem JMenuItemMarca;
    private javax.swing.JMenuItem JMenuItemNotaCredito;
    private javax.swing.JMenuItem JMenuItemProductosMasVendidos;
    private javax.swing.JMenuItem JMenuItemProveedor;
    private javax.swing.JMenuItem JMenuItemReporteCierreCaja;
    private javax.swing.JMenuItem JMenuItemRolComponenteAccion;
    private javax.swing.JMenuItem JMenuItemRoles;
    private javax.swing.JMenuItem JMenuItemStockProducto;
    private javax.swing.JMenuItem JMenuItemTipoCambio;
    private javax.swing.JMenuItem JMenuItemUnidadPresentacion;
    private javax.swing.JMenuItem JMenuItemUnidadVenta;
    private javax.swing.JMenuItem JMenuItemUsuario;
    private javax.swing.JMenuItem JMenuItemUsuarioPoRol;
    private javax.swing.JMenuItem JMenuItemVentasPorEmpleado;
    private javax.swing.JMenuItem JMenuListadoProductos;
    private javax.swing.JMenu JMnAlmacen;
    private javax.swing.JMenuItem JmnItSalida;
    private javax.swing.JLabel LblFecha;
    private javax.swing.JLabel LblIP;
    private javax.swing.JLabel LblMensajeSistema;
    private javax.swing.JLabel LblRol;
    private static javax.swing.JLabel LblSucursal;
    private javax.swing.JLabel LblTerminal;
    private javax.swing.JLabel LblUsuario;
    private javax.swing.JMenuItem MnItIngreso;
    private javax.swing.JMenuItem MnItPagosPorCompra;
    private javax.swing.JMenuItem MnItTransferencia;
    private javax.swing.JMenu MniGeneral1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemAbrirSistema;
    private javax.swing.JMenuItem jMenuItemCerrarSistema;
    private javax.swing.JMenu jMnCompras;
    private javax.swing.JMenuItem jMnItOrdenCompra;
    private util.clases.grlGeneral.JPanelImage jPanelImage1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    // End of variables declaration//GEN-END:variables
private javax.swing.JDialog dialogo;

        private void desahibilitarMenu()
        {

                CLHabilitarControles oCLHabilitarControles=new CLHabilitarControles();
                oCLHabilitarControles.DeshabilitarControles(this.getRootPane().getJMenuBar());

          

        }
    }

