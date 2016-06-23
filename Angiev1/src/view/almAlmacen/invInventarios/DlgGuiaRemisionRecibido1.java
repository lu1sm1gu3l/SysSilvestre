package view.almAlmacen.invInventarios;


import controller.almAlmacen.CCGuiaRemisionRecibido;
import controller.grlGeneral.CCEstado;
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
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibido;
import modelo.almAlmacen.entidad.CEGuiaRemisionRecibidoDetalle;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.grlGeneral.entidad.CEEstado;
import util.clases.almAlmacen.CLGuiaRemisionRecibido;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLBotonesABM;
import util.clases.grlGeneral.CLExportarExcel;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.MiReloj;
import util.clases.grlGeneral.VerificadorDeAreaText;
import util.clases.grlGeneral.VerificadorDeTxt;
import view.FrmSistemaMenu;
import view.almAlmacen.DlgBusquedaProductoCompra;
import view.cmrComercial.DlgGestionProveedor;

/**
 *
 * @author Luiggi
 */
public class DlgGuiaRemisionRecibido1 extends javax.swing.JDialog implements ActionListener{

   private  int idProveedor=0;
   private  int IdGuiaRemisionRecibido=0;
   private  int IdEstado=0;
   private MiReloj hilo;
   private  CLGuiaRemisionRecibido oCLGuiaRemisionRecibido;
   private  int pAccionABM=0;
   private CLBotonesABM oclBotonesABM;
   private java.awt.Frame oparent;
    /** Creates new form DlgGestionGuiaRemisionRecibido */
    public DlgGuiaRemisionRecibido1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);        
        initComponents();
        this.oparent=parent;
        this.setLocationRelativeTo(null);      
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TxtObservacion.setDocument(new VerificadorDeAreaText("String",250,TxtObservacion));
        TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
        TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
        cargarUtilidades();
        TblGuiaRemisionRecibidoDetalle.setSurrendersFocusOnKeystroke(true);
        cont=0;
        DtchFecha.setMaxSelectableDate(FrmSistemaMenu.FechaSistema);
    }


    private void cargarUtilidades()
    {
       
        oCLGuiaRemisionRecibido = new CLGuiaRemisionRecibido(TblGuiaRemisionRecibidoDetalle);
        TblGuiaRemisionRecibidoDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jTextFieldNumber.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaTxtProducto = TblGuiaRemisionRecibidoDetalle.getColumnModel().getColumn(oCLGuiaRemisionRecibido.colProducto);
        columnaTxtProducto.setCellEditor(new DefaultCellEditor(TxtProducto));
              
        TableColumn columnaCantidad = TblGuiaRemisionRecibidoDetalle.getColumnModel().getColumn(oCLGuiaRemisionRecibido.colCantidad);
        columnaCantidad.setCellEditor(new DefaultCellEditor(jTextFieldNumber));      
        hilo= new MiReloj(LblHoraSistema);
        hilo.start();
        oclBotonesABM= new CLBotonesABM();
        oclBotonesABM.setBotones(btnNuevo, btnGuardar, btnAnular, btnEditar, btnCancelar, null,this);
        ((JTableX)TblGuiaRemisionRecibidoDetalle).setSelectAllForEdit(true);
        eventoNuevo();
        alinearColumnas();


    }

    private void alinearColumnas()
    {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblGuiaRemisionRecibidoDetalle.getColumnModel().getColumn(oCLGuiaRemisionRecibido.colCantidad).setCellRenderer(tcr);
      
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        TxtProducto = new TextField.JTxtNinguno();
        GrRbtnNumdoc = new javax.swing.ButtonGroup();
        GrRbtnNumSalida = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        lblEtiqNumIngreso = new Label.Label();
        btnBuscarCompComp = new BotonesABM.BtnBuscar();
        TxtNumComp = new TextField.JTxtNinguno();
        DtchFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        label34 = new Label.Label();
        txtEstado = new Label.Label();
        lblEtiqEstado = new Label.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblGuiaRemisionRecibidoDetalle = new util.clases.almAlmacen.JTableX(){
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==oCLGuiaRemisionRecibido.colCantidad)
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
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new BotonesABM.BtnNuevo();
        btnGuardar = new BotonesABM.BtnGuardar();
        btnEditar = new BotonesABM.BtnEditar();
        btnAnular = new BotonesABM.BtnEliminar();
        btnRecpecion = new BotonesABM.BtnEliminar();
        btnExportar1 = new BotonesABM.BtnExportar();
        btnCancelar = new BotonesABM.BtnCancelar();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObservacion = new javax.swing.JTextArea();
        PnlDatos5 = new javax.swing.JPanel();
        LblEtiqProveedor = new Label.Label();
        lblEtiqSucursal = new Label.Label();
        TxtVendedor = new Label.Label();
        label28 = new Label.Label();
        label29 = new Label.Label();
        lblEtiqRuc = new Label.Label();
        TxtProveedor = new TextField.JTxtLetra();
        LblHoraRegistro6 = new Label.Label();
        LblHoraRegistro1 = new Label.Label();
        lblEtiqVendedor = new Label.Label();
        TxtSucursal = new Label.Label();
        TxtDNI = new Label.Label();
        TxtChofer = new TextField.JTxtLetra();
        label32 = new Label.Label();
        label33 = new Label.Label();
        TxtPlaca = new TextField.JTxtNinguno();
        TxtOrigen = new TextField.JTxtNinguno();
        TxtDestino = new TextField.JTxtNinguno();

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
        setTitle("Guia Remisión Recibido");
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

        jPanel3.setBackground(java.awt.SystemColor.info);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEtiqNumIngreso.setText("Nº Guia Remision :");
        lblEtiqNumIngreso.setFont(new java.awt.Font("Verdana", 1, 12));

        btnBuscarCompComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        btnBuscarCompComp.setText("Buscar Guia Remisión");
        btnBuscarCompComp.setToolTipText("Buscar Guia Remisión");
        btnBuscarCompComp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarCompComp.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarCompComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompCompActionPerformed(evt);
            }
        });

        TxtNumComp.setTamanio(12);
        TxtNumComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNumCompActionPerformed(evt);
            }
        });
        TxtNumComp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNumCompKeyPressed(evt);
            }
        });

        DtchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtchFechaPropertyChange(evt);
            }
        });

        label34.setText("Fecha :");

        txtEstado.setText("EMITIDO");
        txtEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        lblEtiqEstado.setText("Estado :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEtiqNumIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtNumComp, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEtiqEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEtiqEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(lblEtiqNumIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBuscarCompComp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DtchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label34, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(11, Short.MAX_VALUE))))
        );

        btnBuscarCompComp.getAccessibleContext().setAccessibleName("");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        TblGuiaRemisionRecibidoDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblGuiaRemisionRecibidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        TblGuiaRemisionRecibidoDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblGuiaRemisionRecibidoDetalle.setColumnSelectionAllowed(true);
        TblGuiaRemisionRecibidoDetalle.setRowHeight(21);
        TblGuiaRemisionRecibidoDetalle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblGuiaRemisionRecibidoDetalle.getTableHeader().setReorderingAllowed(false);
        TblGuiaRemisionRecibidoDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblGuiaRemisionRecibidoDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TblGuiaRemisionRecibidoDetalleMousePressed(evt);
            }
        });
        TblGuiaRemisionRecibidoDetalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblGuiaRemisionRecibidoDetallePropertyChange(evt);
            }
        });
        TblGuiaRemisionRecibidoDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblGuiaRemisionRecibidoDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblGuiaRemisionRecibidoDetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TblGuiaRemisionRecibidoDetalle);
        TblGuiaRemisionRecibidoDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        columnButton1.setTbl(TblGuiaRemisionRecibidoDetalle);

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

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar);

        btnAnular.setText("ANULAR");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnular);

        btnRecpecion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Entrada.png"))); // NOI18N
        btnRecpecion.setText("RECEP.");
        btnRecpecion.setToolTipText("Recepcionar Entrada");
        btnRecpecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecpecionActionPerformed(evt);
            }
        });
        jPanel2.add(btnRecpecion);

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

        PnlDatos5.setBackground(java.awt.SystemColor.controlHighlight);
        PnlDatos5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "[ DATOS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        LblEtiqProveedor.setText("Proovedor:");

        lblEtiqSucursal.setText("Sucursal   :");

        TxtVendedor.setText("NINGUNO");
        TxtVendedor.setFont(new java.awt.Font("Verdana", 1, 12));

        label28.setText("Chofer :");

        label29.setText("Origen  :");

        lblEtiqRuc.setText("RUC      :");

        TxtProveedor.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtProveedor.setTamanio(200);
        TxtProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtProveedorMouseClicked(evt);
            }
        });
        TxtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtProveedorKeyPressed(evt);
            }
        });

        LblHoraRegistro6.setText("NINGUNO");
        LblHoraRegistro1.setText("");

        LblHoraRegistro1.setText("Fecha Rg");

        lblEtiqVendedor.setText("Usuario :");

        TxtSucursal.setText("NINGUNO");
        TxtSucursal.setFont(new java.awt.Font("Verdana", 1, 12));

        TxtDNI.setText("NINGUNO");

        TxtChofer.setFont(new java.awt.Font("Verdana", 0, 11));
        TxtChofer.setTamanio(100);
        TxtChofer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtChoferMouseClicked(evt);
            }
        });
        TxtChofer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtChoferKeyPressed(evt);
            }
        });

        label32.setText("Destino :");

        label33.setText("Placa   :");

        TxtPlaca.setTamanio(100);
        TxtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPlacaActionPerformed(evt);
            }
        });
        TxtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtPlacaKeyPressed(evt);
            }
        });

        TxtOrigen.setTamanio(120);
        TxtOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtOrigenActionPerformed(evt);
            }
        });
        TxtOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtOrigenKeyPressed(evt);
            }
        });

        TxtDestino.setTamanio(120);
        TxtDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDestinoActionPerformed(evt);
            }
        });
        TxtDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtDestinoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PnlDatos5Layout = new javax.swing.GroupLayout(PnlDatos5);
        PnlDatos5.setLayout(PnlDatos5Layout);
        PnlDatos5Layout.setHorizontalGroup(
            PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos5Layout.createSequentialGroup()
                        .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(TxtSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(PnlDatos5Layout.createSequentialGroup()
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                            .addComponent(TxtChofer, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                            .addComponent(TxtProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(label32, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(label33, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(lblEtiqRuc, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(LblHoraRegistro6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnlDatos5Layout.setVerticalGroup(
            PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDatos5Layout.createSequentialGroup()
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblEtiqProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtChofer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(PnlDatos5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEtiqRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(TxtOrigen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(label32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDatos5Layout.createSequentialGroup()
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlDatos5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEtiqVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEtiqSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PnlDatos5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(TxtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDatos5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlDatos5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblHoraRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblHoraRegistro6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                    .addComponent(PnlDatos5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LblHoraSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(PnlDatos5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(label20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(columnButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar)
                    .addComponent(BtnQuitar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        columnButton1.getAccessibleContext().setAccessibleName("NO");

        getAccessibleContext().setAccessibleName("Registro Guia Remision Reibido");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarProveedor()
    {
        if(TxtProveedor.isEnabled()){
          DlgGestionProveedor odialogo= new DlgGestionProveedor(null, true,1,1,1);//consultar

          odialogo.setCajaTexo(TxtProveedor.getText());
          odialogo.setVisible(true);
          odialogo.setTitle("Busqueda de Proveedor");
                  if(odialogo.getoCEProveedor()!=null){
                    idProveedor=odialogo.getoCEProveedor().getIdProveedor();
                    TxtProveedor.setText(odialogo.getoCEProveedor().getRazonSocial());
                    TxtDNI.setText(odialogo.getoCEProveedor().getRUC());
                    TblGuiaRemisionRecibidoDetalle.requestFocus();
                    TblGuiaRemisionRecibidoDetalle.changeSelection(0,oCLGuiaRemisionRecibido.colProducto, false, false);
                    }
                
        }
      }
private void habilitarControles(boolean bol)
    {
    TxtProveedor.setEnabled(bol);
    TblGuiaRemisionRecibidoDetalle.setEnabled(bol);
    TxtProducto.setEnabled(bol);
    TxtNumComp.setEditable(bol);
    TxtObservacion.setEnabled(bol);
    DtchFecha.setEnabled(bol);
    TxtChofer.setEnabled(bol);
    TxtPlaca.setEnabled(bol);
    TxtOrigen.setEnabled(bol);
    TxtDestino.setEnabled(bol);
    BtnQuitar.setEnabled(bol);
    BtnAgregar.setEnabled(bol);
}

private int ValidarRegistro(int fila)
    {
     if(TblGuiaRemisionRecibidoDetalle.getRowCount()>0){
    
     int numColumna=0;
        CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle=(CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(fila-1, oCLGuiaRemisionRecibido.colCodigo);
        String mensaje="Seleccione un producto";
        if(oCEGuiaRemisionRecibidoDetalle!=null){
            if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila-1, oCLGuiaRemisionRecibido.colCantidad)!=null)
            {
                float cantidad=VerificadorDeTxt.convertFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(fila-1, oCLGuiaRemisionRecibido.colCantidad).toString());
                if(cantidad<0){
                        numColumna=oCLGuiaRemisionRecibido.colCantidad;
                        colValidada=oCLGuiaRemisionRecibido.colCantidad;
                        mensaje="Ingrese una cantidad mayor a cero";
                }else
                {
//                    float precio=0;
//                        if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila-1, oCLGuiaRemisionRecibido.colPrecio)!=null)
//                        {
//                        precio=Float.parseFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(fila-1, oCLGuiaRemisionRecibido.colPrecio).toString());
//                        }
//                        if(precio==0){
//                        numColumna = oCLGuiaRemisionRecibido.colPrecio;
//                        colValidada = oCLGuiaRemisionRecibido.colPrecio;
//                        mensaje="Ingrese el costo";
//                        }
                }
            }else{
                        numColumna = oCLGuiaRemisionRecibido.colCantidad;
                        colValidada = oCLGuiaRemisionRecibido.colCantidad;
                        mensaje="Ingrese una cantidad";                     
                 }
        }
        else{
            numColumna=oCLGuiaRemisionRecibido.colProducto;
            colValidada=oCLGuiaRemisionRecibido.colProducto;

        }
        if(numColumna!=0){
//            if(numColumna!=oCLGuiaRemisionRecibido.colPrecio){
            JOptionPane.showMessageDialog(null,mensaje);
//            }else
//                { int filat=TblGuiaRemisionRecibidoDetalle.getSelectedRow();
//                  oCLGuiaRemisionRecibido.calcularPrecio(filat);
//                }
            }

        return numColumna;
        
        }
    return 0;
}
    private void BuscarProducto()
    {
       
        DlgBusquedaProductoCompra odialogo= new DlgBusquedaProductoCompra(oparent, true,0,0);
                String NombreProd=TxtProducto.getText().trim();
                btnGuardar.requestFocus();
                if(NombreProd.equals(""))
                {
               if(TblGuiaRemisionRecibidoDetalle.getValueAt(TblGuiaRemisionRecibidoDetalle.getSelectedRow(), oCLGuiaRemisionRecibido.colProducto)!=null)
                    {
                    NombreProd=TblGuiaRemisionRecibidoDetalle.getValueAt(TblGuiaRemisionRecibidoDetalle.getSelectedRow(), oCLGuiaRemisionRecibido.colProducto)+"";
                    }
                }
               odialogo.setCajaTexto(NombreProd);
               TxtProducto.setText("");
               odialogo.setVisible(true);
               CEProducto oCEAlmacenProducto=odialogo.getProducto();
               if(!odialogo.getisagregarCantidadOrdenCompra()){
               agregarGuiaRemisionRecibidoDetalle(oCEAlmacenProducto);
               
               int fila=TblGuiaRemisionRecibidoDetalle.getSelectedRow();
               TblGuiaRemisionRecibidoDetalle.requestFocus();
               TblGuiaRemisionRecibidoDetalle.changeSelection(fila, oCLGuiaRemisionRecibido.colCantidad, false, false);

                }else{
                   TblGuiaRemisionRecibidoDetalle.requestFocus();
                   SeleccionarGuiaRemisionRecibidoExistente(oCEAlmacenProducto);
                }

    }
    private void SeleccionarGuiaRemisionRecibidoExistente(CEProducto oCEAlmacenProducto)
    {
        CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle ;
        for( int i =0;i<TblGuiaRemisionRecibidoDetalle.getRowCount();i++)
            {
//cx
                oCEGuiaRemisionRecibidoDetalle= (CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(i, oCLGuiaRemisionRecibido.colCodigo);
                if(oCEGuiaRemisionRecibidoDetalle!=null){
                    
                        if(
                           oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida()==oCEAlmacenProducto.getIdUnidadBase()&&
                           oCEGuiaRemisionRecibidoDetalle.getIdProducto()==oCEAlmacenProducto.getIdProducto())
                        {
                            TblGuiaRemisionRecibidoDetalle.requestFocus();
                            TblGuiaRemisionRecibidoDetalle.changeSelection(i, oCLGuiaRemisionRecibido.colUnidadMedida, false, false);
                        }

                }
            }
    }
 

    private boolean  ValidarTablaGuiaRemisionRecibido()
     {


            for(int i=0; i<TblGuiaRemisionRecibidoDetalle.getRowCount();i++)
            {
                verificarDatos(i,0);

                if(colValidada!=-1){
                    TblGuiaRemisionRecibidoDetalle.requestFocus();
                    TblGuiaRemisionRecibidoDetalle.changeSelection(i, colValidada, false, false);
                    colValidada=-1;
                    return false;
                }

            }
            eventoGuardar=false;

        return true;
    }
    boolean eventoGuardar=false;
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      GuardarIngreso();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void GuardarIngreso()
    {
        int fil=TblGuiaRemisionRecibidoDetalle.getRowCount();
      if(fil>0){
              int row=fil-1;
              CEGuiaRemisionRecibidoDetalle oCEIngreoDetalle= (CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(row, oCLGuiaRemisionRecibido.colCodigo);
              if(oCEIngreoDetalle==null){
                oCLGuiaRemisionRecibido.QuitarFilaSel(row);
              }
        }
      fil=TblGuiaRemisionRecibidoDetalle.getRowCount();
      if(fil>0){
        eventoGuardar=true;
      int columnaValidada=ValidarRegistro(TblGuiaRemisionRecibidoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          GuardarRegistro();
      }else
      {
          TblGuiaRemisionRecibidoDetalle.requestFocus();
          TblGuiaRemisionRecibidoDetalle.changeSelection(TblGuiaRemisionRecibidoDetalle.getRowCount()-1, columnaValidada, false, false);
      }

      }
        else
        {            

             JOptionPane.showMessageDialog(null,"Ingrese un Item","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblGuiaRemisionRecibidoDetalle.requestFocus();
             TblGuiaRemisionRecibidoDetalle.changeSelection(0, oCLGuiaRemisionRecibido.colProducto, false, false);

        }
    }
    private boolean validar()
    {        

        int cantidadCero=0;
        for(int i=0; i<TblGuiaRemisionRecibidoDetalle.getRowCount();i++)
            {
                float cantidad=VerificadorDeTxt.convertFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(i, oCLGuiaRemisionRecibido.colCantidad));
                if(cantidad==0)
                {
                    cantidadCero++;
                }


            }
        if(cantidadCero==TblGuiaRemisionRecibidoDetalle.getRowCount())
                {
                   JOptionPane.showMessageDialog(this,"Para Guardar el Ingreso al menos un registro debe de tener la cantidad mayor a cero","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                   return false;
                }

         if(TblGuiaRemisionRecibidoDetalle.getRowCount()==0)
        {

             JOptionPane.showMessageDialog(this,"Ingrese un Producto","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             agregarNuevaFila(0);
             TblGuiaRemisionRecibidoDetalle.requestFocus();
             TblGuiaRemisionRecibidoDetalle.changeSelection(0, oCLGuiaRemisionRecibido.colProducto, false, false);
             return false;
            
        } 
       
        
         if(idProveedor==0)
        {            
             JOptionPane.showMessageDialog(this,"Ingrese un Proveedor","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtProveedor.requestFocus();
             return false;
        }
        if(TxtNumComp.getText().trim().length()==0)
        {            
             JOptionPane.showMessageDialog(this,"Ingrese el numero de la guia de remisión","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             TxtNumComp.requestFocus();
             return false;
        }
        if(DtchFecha.getCalendar()==null)
        {
             JOptionPane.showMessageDialog(this,"Ingrese la Fecha","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             DtchFecha.requestFocus();
             return false;
        }

        int resul=JOptionPane.showConfirmDialog(this,"<html><h2><BR> ESTÁ SEGURO DE GUARDAR LA GUIA DE REMISIÓN Nº "+TxtNumComp.getText()+"</h2></html>" , "",JOptionPane.YES_NO_OPTION);
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
        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoGuiaRemRecib(IdGuiaRemisionRecibido);
//            if(oCEEstadoInvent.getmIntIdEstado()!=1)
//            {
//             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
//             return false;
//            }
           if(oCEEstadoInvent.getmIntIdEstado()!=13)
            {
             JOptionPane.showMessageDialog(this,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Guardar","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        }
       
        
        return true;
    }
    int IdEstadoComprobanteCompra;
    private void GuardarRegistro()
    {
        
        if(ValidarTablaGuiaRemisionRecibido()){
        if(validar()){
                    if(pAccionABM==1){

                        CEGuiaRemisionRecibido oCEGuiaRemisionRecibido=this.getGuiaRemisionRecibido();
                        boolean guardado=CCGuiaRemisionRecibido.InsGuiaRemisionRecibido(oCEGuiaRemisionRecibido);
                        if(guardado)
                        {

                        int resul=JOptionPane.showConfirmDialog(this,"<html><h3>Se guardó con exito, <BR> ¿DESEA GENERAR LA ENTRADA A ALMACEN?<h3></html>" , "",JOptionPane.YES_NO_OPTION);
                        if (resul==JOptionPane.YES_OPTION) {

                            DlgIngresoProducto1 oDlgIngresoProducto=new DlgIngresoProducto1(oparent, true,true);

                            oDlgIngresoProducto.SetIngresoProductoPorGuiaRemision(oCEGuiaRemisionRecibido);
                            oDlgIngresoProducto.setVisible(true);

                        }
                        lstIngresoDetEliminar= new ArrayList<CEGuiaRemisionRecibidoDetalle>();
                        limpiarFormulario();
                        LblHoraRegistro6.setText("");
                        TblGuiaRemisionRecibidoDetalle.requestFocus();
                        TblGuiaRemisionRecibidoDetalle.changeSelection(0,oCLGuiaRemisionRecibido.colProducto, false, false);
                        eventoNuevo();
                        oCLGuiaRemisionRecibido.limpiarTabla();
                        TxtObservacion.setText("");
                        }
                        else{

                        JOptionPane.showMessageDialog(this,"Operación Fallida");
                        }
                    }else{


                                    int iscorrecto=CCGuiaRemisionRecibido.UPDGuiaRemisionRecibido(this.getGuiaRemisionRecibido());
                                    if(iscorrecto==1)
                                    {
                                    lstIngresoDetEliminar= new ArrayList<CEGuiaRemisionRecibidoDetalle>();
                                    JOptionPane.showMessageDialog(this,"<html><CENTER>Operación exitosa</CENTER></html>");
                                    limpiarFormulario();
                                    eventoNuevo();
                                    oCLGuiaRemisionRecibido.limpiarTabla();
                                    TxtObservacion.setText("");
                                    }
                                else{
                                    JOptionPane.showMessageDialog(null,"Operación Fallida");
                                    }
               }
      }
     }
    
 }

    private CEGuiaRemisionRecibido getGuiaRemisionRecibido(){
        CEGuiaRemisionRecibido oCEGuiaRemisionRecibido= new CEGuiaRemisionRecibido();
        IdEstadoComprobanteCompra=9;
        oCEGuiaRemisionRecibido.setIdGuiaRemisionRecibido(IdGuiaRemisionRecibido);
        oCEGuiaRemisionRecibido.setNumeroDocumento(TxtNumComp.getText());
        oCEGuiaRemisionRecibido.setIdSucursal(FrmSistemaMenu.getIdSucursal());
        oCEGuiaRemisionRecibido.setIdProveedor(idProveedor);
        oCEGuiaRemisionRecibido.setProveedor(TxtProveedor.getText());
        oCEGuiaRemisionRecibido.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
        oCEGuiaRemisionRecibido.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
        oCEGuiaRemisionRecibido.setObservacion(TxtObservacion.getText());
        oCEGuiaRemisionRecibido.setChofer(TxtChofer.getText());
        oCEGuiaRemisionRecibido.setPlaca(TxtPlaca.getText());
        oCEGuiaRemisionRecibido.setDestino(TxtDestino.getText());
        oCEGuiaRemisionRecibido.setOrigen(TxtOrigen.getText());
        ConvertidorFecha oConvertidorFecha = new ConvertidorFecha();
        if (DtchFecha.getCalendar() != null)
        {
            oConvertidorFecha.setFecha(DtchFecha.getCalendar());
            oCEGuiaRemisionRecibido.setFecha(oConvertidorFecha.getFechaConvertida());
        }
        List<CEGuiaRemisionRecibidoDetalle> lstGuiaRemisionRecibidoDetalle = new ArrayList<CEGuiaRemisionRecibidoDetalle>();
        CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle ;

        int cantidadRecepcionada=0;
        for( int i =0;i<TblGuiaRemisionRecibidoDetalle.getRowCount();i++)
            {

                oCEGuiaRemisionRecibidoDetalle= (CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(i, oCLGuiaRemisionRecibido.colCodigo);
                if(oCEGuiaRemisionRecibidoDetalle!=null){

                    float cantidad=VerificadorDeTxt.convertFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(i, oCLGuiaRemisionRecibido.colCantidad));
                    oCEGuiaRemisionRecibidoDetalle.setCantidad(cantidad);
                    lstGuiaRemisionRecibidoDetalle.add(oCEGuiaRemisionRecibidoDetalle);
                    }
               
                
            }
        if(pAccionABM==2){
        lstGuiaRemisionRecibidoDetalle.addAll(lstIngresoDetEliminar);
        }
        if(cantidadRecepcionada==TblGuiaRemisionRecibidoDetalle.getRowCount())
                        {
                            IdEstadoComprobanteCompra=8;
                        }

            oCEGuiaRemisionRecibido.setLstGuiaDetalle(lstGuiaRemisionRecibidoDetalle);


            return oCEGuiaRemisionRecibido;
}
    private void agregarNuevaFila(int fila)
    {
      
         Vector oVector = new Vector();

                ((DefaultTableModel)TblGuiaRemisionRecibidoDetalle.getModel()).addRow(oVector);
             

                    TblGuiaRemisionRecibidoDetalle.requestFocus();
                    TblGuiaRemisionRecibidoDetalle.changeSelection(fila, oCLGuiaRemisionRecibido.colProducto, false, false);
                   
        
    }
    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
    eventoAgregar();
        
    }//GEN-LAST:event_BtnAgregarActionPerformed
private void eventoAgregar()
{
      int columnaValidada=ValidarRegistro(TblGuiaRemisionRecibidoDetalle.getRowCount());
      if(columnaValidada==0)
      {
          int row=TblGuiaRemisionRecibidoDetalle.getRowCount();
          agregarNuevaFila(row);
          TblGuiaRemisionRecibidoDetalle.requestFocus();
          TblGuiaRemisionRecibidoDetalle.changeSelection(TblGuiaRemisionRecibidoDetalle.getRowCount()-1, oCLGuiaRemisionRecibido.colProducto, false, false);


      }else
      {
          TblGuiaRemisionRecibidoDetalle.requestFocus();
          TblGuiaRemisionRecibidoDetalle.changeSelection(TblGuiaRemisionRecibidoDetalle.getRowCount()-1, columnaValidada, false, false);
      }
}

   List<CEGuiaRemisionRecibidoDetalle> lstIngresoDetEliminar= new ArrayList<CEGuiaRemisionRecibidoDetalle>();
    private void BtnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQuitarActionPerformed

        if(TblGuiaRemisionRecibidoDetalle.getSelectedRow()!=-1){
        if(pAccionABM==2)
        {
        CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle= (CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(TblGuiaRemisionRecibidoDetalle.getSelectedRow(), oCLGuiaRemisionRecibido.colCodigo);
            if(oCEGuiaRemisionRecibidoDetalle!=null&&oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle()!=0)
            {
                oCEGuiaRemisionRecibidoDetalle.setOpcionAbm(3);
                lstIngresoDetEliminar.add(oCEGuiaRemisionRecibidoDetalle);
            }
        }

        oCLGuiaRemisionRecibido.QuitarFila();
        }else
        {
            JOptionPane.showMessageDialog(null,"Seleccione una fila","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
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
            btnRecpecion.setEnabled(false);
            LblHoraRegistro6.setText("");
            pAccionABM=0;
            limpiarFormulario();
            oCLGuiaRemisionRecibido.limpiarTabla();
            
       }else{
           iscerrando=true;
             dispose();
            }

    }
    private boolean realizarevento=true;
    private int colValidada=-1;
    private int cont=0;// contador utilizado como artificio
    private void verificarDatos(int fila,float cantidad){
         try{
           if(TblGuiaRemisionRecibidoDetalle.getRowCount()>0)
           {
            colValidada=-1;            
            if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCantidad)!=null){
            cantidad=Float.parseFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCantidad).toString());}



        if(cantidad<=0)
            {
                CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle=(CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCodigo);
                    if(oCEGuiaRemisionRecibidoDetalle.getIdGuiaRemisionRecibidoDetalle()>0){
                    JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                    TblGuiaRemisionRecibidoDetalle.setValueAt(null,fila,oCLGuiaRemisionRecibido.colCantidad);
                    colValidada=oCLGuiaRemisionRecibido.colCantidad;
                    }else
                    {                        
                            JOptionPane.showMessageDialog(null,"La cantidad debe ser mayor a cero");
                            TblGuiaRemisionRecibidoDetalle.setValueAt(null,fila,oCLGuiaRemisionRecibido.colCantidad);
                            colValidada=oCLGuiaRemisionRecibido.colCantidad;
                    }
                
                 return;
            }



        int col=TblGuiaRemisionRecibidoDetalle.getSelectedColumn();
                if(!eventoGuardar){

             }
        }
    }catch(Exception e)
    {
        cont++;
       System.out.println(e+"metodo Verificar datos -formulario ingreso");

    }
    }


private String Codigo;





    CEGuiaRemisionRecibido oCEGuiaRemisionRecibidoGeneral=null;
    public void SetGuiaRemisionRecibido(CEGuiaRemisionRecibido oCEGuiaRemisionRecibido)
    {
        oCLGuiaRemisionRecibido.limpiarTabla();
        isNuevoConDoc=true;
        if(oCEGuiaRemisionRecibido!=null)
        {
            oCEGuiaRemisionRecibidoGeneral=oCEGuiaRemisionRecibido;
            txtEstado.setText(oCEGuiaRemisionRecibido.getUltimoEstado());
            IdEstado=oCEGuiaRemisionRecibido.getUltimoIdEstado();
            IdGuiaRemisionRecibido=oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido();
            TxtDNI.setText(oCEGuiaRemisionRecibido.getRUC());
            TxtNumComp.setText(oCEGuiaRemisionRecibido.getNumeroDocumento());
            TxtProducto.setText("");
            TxtSucursal.setText(oCEGuiaRemisionRecibido.getSucursal());
            TxtVendedor.setText(oCEGuiaRemisionRecibido.getEmpleado());
            LblHoraRegistro6.setText(oCEGuiaRemisionRecibido.getFecha());
            TxtNumComp.setText(oCEGuiaRemisionRecibido.getNumeroDocumento());
            TxtObservacion.setText(oCEGuiaRemisionRecibido.getObservacion());
            TxtChofer.setText(oCEGuiaRemisionRecibido.getChofer());
            TxtPlaca.setText(oCEGuiaRemisionRecibido.getPlaca());
            TxtDestino.setText(oCEGuiaRemisionRecibido.getDestino());
            TxtOrigen.setText(oCEGuiaRemisionRecibido.getOrigen());
            DtchFecha.setDate(oCEGuiaRemisionRecibido.getDate());
            if(oCEGuiaRemisionRecibido.getLstGuiaDetalle()!=null)
            {
               for(int i=0; i<oCEGuiaRemisionRecibido.getLstGuiaDetalle().size();i++){

                   Vector oVector = new Vector();

                ((DefaultTableModel)TblGuiaRemisionRecibidoDetalle.getModel()).addRow(oVector);
                
                CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle=oCEGuiaRemisionRecibido.getLstGuiaDetalle().get(i) ;
                oCEGuiaRemisionRecibidoDetalle.setOpcionAbm(2);
                CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
                oCEUnidadMedidaProducto.setTipoUnidad(oCEGuiaRemisionRecibidoDetalle.getUnidadMedida());
                oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEGuiaRemisionRecibidoDetalle.getIdUnidadMedida());
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle,i,oCLGuiaRemisionRecibido.colCodigo);
                TxtProducto.setText(oCEGuiaRemisionRecibidoDetalle.getProducto());
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getProducto(),i,oCLGuiaRemisionRecibido.colProducto);
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEUnidadMedidaProducto,i,oCLGuiaRemisionRecibido.colUnidadMedida);
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getCantidad(),i,oCLGuiaRemisionRecibido.colCantidad);
                }
            }

            idProveedor=oCEGuiaRemisionRecibido.getIdProveedor();
            TxtProveedor.setText(oCEGuiaRemisionRecibido.getProveedor());
            TblGuiaRemisionRecibidoDetalle.clearSelection();
            oclBotonesABM.controlBoton(false, false, true, true, true, false);
            btnRecpecion.setEnabled(true);
            if(oCEGuiaRemisionRecibido.getUltimoIdEstado()==8||oCEGuiaRemisionRecibido.getUltimoIdEstado()==9)
            {
                oclBotonesABM.controlBoton(false, false, false, false, true, false);
                btnRecpecion.setEnabled(false);
            }
            habilitarControles(false);
            pAccionABM=2;
            btnEditar.requestFocus();
        }
    else
        {
          JOptionPane.showMessageDialog(null,"El codigo no Existe");
        }
    }
    private void eventoNuevo()
    {
       isNuevoConDoc=false;
       pAccionABM=1;
       oclBotonesABM.controlBoton(false, true, false, false, true, false);
       btnRecpecion.setEnabled(false);
       habilitarControles(true);
       TblGuiaRemisionRecibidoDetalle.requestFocus();
       TblGuiaRemisionRecibidoDetalle.changeSelection(0,oCLGuiaRemisionRecibido.colProducto, false, false);
       oCLGuiaRemisionRecibido.setColumnEditable(new int[]{oCLGuiaRemisionRecibido.colProducto,oCLGuiaRemisionRecibido.colCantidad});
       limpiarFormulario();
       agregarNuevaFila(0);
}
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
    eventoNuevo();

   
    
    }//GEN-LAST:event_btnNuevoActionPerformed

    private boolean validarAnulacion()
    {

        CEEstado oCEEstadoInvent=CCEstado.consultarUltimoestadoIngreso(IdGuiaRemisionRecibido);
            if(oCEEstadoInvent.getmIntIdEstado()!=1)
            {
             JOptionPane.showMessageDialog(null,"El Registro ya fué "+oCEEstadoInvent.getmStrDescripcion()+", No se Puede Anular","Mensaje de Error", JOptionPane.ERROR_MESSAGE);
             return false;
            }
        
        return true;
    }
    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed

                
                pAccionABM=4;                
                oCEGuiaRemisionRecibidoGeneral.setIdEmpleado(FrmSistemaMenu.oCEUsuario.getIdEmpleado());
                oCEGuiaRemisionRecibidoGeneral.setIdUsuario(FrmSistemaMenu.oCEUsuario.getIdUsuario());
                DlgAnularGuiaRemisionRecibido oDlgAnularGuiaRemisionRecibido= new DlgAnularGuiaRemisionRecibido(null,true , oCEGuiaRemisionRecibidoGeneral);
                oDlgAnularGuiaRemisionRecibido.setLocationRelativeTo(null);
                oDlgAnularGuiaRemisionRecibido.setVisible(true);
                oCEGuiaRemisionRecibidoGeneral.setObservacion(oDlgAnularGuiaRemisionRecibido.getObservacion());
                if(oDlgAnularGuiaRemisionRecibido.isAnulado()){
                    
                       if(validarAnulacion())
                        {
                           if(CCGuiaRemisionRecibido.AnularGuiaRemisionRecibido(oCEGuiaRemisionRecibidoGeneral)==1)
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

                
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed


         oclBotonesABM.controlBoton(false, true, false, false, true, false);
         habilitarControles(true);
         pAccionABM=2;
         oCLGuiaRemisionRecibido.setColumnEditable(new int[]{oCLGuiaRemisionRecibido.colProducto,oCLGuiaRemisionRecibido.colCantidad});
         if(TblGuiaRemisionRecibidoDetalle.getRowCount()>0)
         {
             TblGuiaRemisionRecibidoDetalle.requestFocus();
            TblGuiaRemisionRecibidoDetalle.changeSelection(0, oCLGuiaRemisionRecibido.colCantidad, false, false);
         }
         

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

    private void TblGuiaRemisionRecibidoDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblGuiaRemisionRecibidoDetalleKeyReleased

        if(TblGuiaRemisionRecibidoDetalle.getSelectedColumn()==oCLGuiaRemisionRecibido.colUnidadMedida&&(evt.getKeyCode()==evt.VK_ENTER||evt.getKeyCode()==evt.VK_TAB)) {
            if(TblGuiaRemisionRecibidoDetalle.getSelectedRow()+1==TblGuiaRemisionRecibidoDetalle.getRowCount()){
                int fila=TblGuiaRemisionRecibidoDetalle.getSelectedRow()+1;
                int columnaValidada=ValidarRegistro(fila);
                if(columnaValidada==0) {
                    if(!isNuevoConDoc){
                    agregarNuevaFila(fila);
                    TblGuiaRemisionRecibidoDetalle.requestFocus();
                    TblGuiaRemisionRecibidoDetalle.changeSelection(fila, oCLGuiaRemisionRecibido.colProducto, false, false);
                    }
                }else{
                    TblGuiaRemisionRecibidoDetalle.requestFocus();
                    TblGuiaRemisionRecibidoDetalle.changeSelection(TblGuiaRemisionRecibidoDetalle.getSelectedRow(), columnaValidada, false, false);
                }
                TxtProducto.setText("");

                return;
            }

        }


        if(colValidada!=-1){

            TblGuiaRemisionRecibidoDetalle.requestFocus();
            TblGuiaRemisionRecibidoDetalle.changeSelection(TblGuiaRemisionRecibidoDetalle.getSelectedRow(), colValidada, false, false);
            colValidada=-1;
        }



    }//GEN-LAST:event_TblGuiaRemisionRecibidoDetalleKeyReleased

    private void TblGuiaRemisionRecibidoDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblGuiaRemisionRecibidoDetalleKeyPressed


        if(evt.getKeyCode()==evt.VK_ENTER&&TblGuiaRemisionRecibidoDetalle.getSelectedColumn()==oCLGuiaRemisionRecibido.colProducto) {
            BuscarProducto();

        }
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
             

                evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);

        }


    }//GEN-LAST:event_TblGuiaRemisionRecibidoDetalleKeyPressed

    private void verficarTextoVacio(int fila){

       int col =TblGuiaRemisionRecibidoDetalle.getSelectedColumn();
       if(TblGuiaRemisionRecibidoDetalle.getRowCount()>0){
                if(fila!=-1)
                   {
                   if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colProducto)!=null){
                       String producto=TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colProducto).toString();
                       if(producto.trim().equals("")){
                           if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCodigo)!=null){

                               TblGuiaRemisionRecibidoDetalle.setValueAt(((CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCodigo)).getProducto(),fila, oCLGuiaRemisionRecibido.colProducto);
                           }

                       }
                    }
                }
        }
    }

    private void TblGuiaRemisionRecibidoDetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblGuiaRemisionRecibidoDetallePropertyChange
        if(!iscerrando){
            if(cont==1){

                int fila=TblGuiaRemisionRecibidoDetalle.getSelectedRow();
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
}//GEN-LAST:event_TblGuiaRemisionRecibidoDetallePropertyChange

    private void TblGuiaRemisionRecibidoDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblGuiaRemisionRecibidoDetalleMousePressed
        if(evt.isMetaDown()) // isMetaDown es el click derecho
            if (!evt.isPopupTrigger()) {

            }
}//GEN-LAST:event_TblGuiaRemisionRecibidoDetalleMousePressed

    private void TblGuiaRemisionRecibidoDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblGuiaRemisionRecibidoDetalleMouseClicked

    }//GEN-LAST:event_TblGuiaRemisionRecibidoDetalleMouseClicked
boolean isNuevoConDoc=false;

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed

        CLExportarExcel oExportar=new CLExportarExcel(TblGuiaRemisionRecibidoDetalle,
                new String[]{lblEtiqNumIngreso.getText(),TxtNumComp.getText(),lblEtiqEstado.getText(),txtEstado.getText(),
                             LblEtiqProveedor.getText(),TxtProveedor.getText(),
                             lblEtiqRuc.getText(),TxtDNI.getText(),lblEtiqSucursal.getText(),TxtSucursal.getText(),
                             lblEtiqVendedor.getText(),TxtVendedor.getText(),LblHoraRegistro1.getText(),LblHoraRegistro6.getText()}
                ,new String[]{},new String[]{},"Entrada de Productos");
        oExportar.GuardarArchivoExcel(this);
    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void TxtNumCompKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumCompKeyPressed

}//GEN-LAST:event_TxtNumCompKeyPressed

    private void TxtNumCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNumCompActionPerformed

}//GEN-LAST:event_TxtNumCompActionPerformed

    private void btnBuscarCompCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompCompActionPerformed

        DlgBusquedaGuiaRemisionRecibido odialogo=new DlgBusquedaGuiaRemisionRecibido(oparent, true);
        odialogo.setVisible(true);
        CEGuiaRemisionRecibido oCEGuiaRemisionRecibido= odialogo.getCEGuiaRemisionRecibido();

        if(oCEGuiaRemisionRecibido!=null) {
            realizarevento=false;
            SetGuiaRemisionRecibido(CCGuiaRemisionRecibido.ConsultarPorId(oCEGuiaRemisionRecibido.getIdGuiaRemisionRecibido()));
            realizarevento=true;
        }
}//GEN-LAST:event_btnBuscarCompCompActionPerformed

    private void TxtProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtProveedorMouseClicked

        if (evt.getClickCount() == 2) {
            buscarProveedor();
        }
}//GEN-LAST:event_TxtProveedorMouseClicked

    private void TxtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtProveedorKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            buscarProveedor();

        }
}//GEN-LAST:event_TxtProveedorKeyPressed

    private void TxtChoferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtChoferMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_TxtChoferMouseClicked

    private void TxtChoferKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtChoferKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_TxtChoferKeyPressed

    private void DtchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtchFechaPropertyChange

}//GEN-LAST:event_DtchFechaPropertyChange

    private void TxtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPlacaActionPerformed

    private void TxtPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPlacaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPlacaKeyPressed

    private void btnRecpecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecpecionActionPerformed
        DlgIngresoProducto1 oDlgIngresoProducto=new DlgIngresoProducto1(oparent, true,true);

        oDlgIngresoProducto.SetIngresoProductoPorGuiaRemision(CCGuiaRemisionRecibido.ConsultarPorId(IdGuiaRemisionRecibido));
        oDlgIngresoProducto.setVisible(true);
        

       
    }//GEN-LAST:event_btnRecpecionActionPerformed

    private void TxtOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtOrigenActionPerformed

    private void TxtOrigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtOrigenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtOrigenKeyPressed

    private void TxtDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDestinoActionPerformed

    private void TxtDestinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtDestinoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDestinoKeyPressed


   
    private void agregarGuiaRemisionRecibidoDetalle(CEProducto oCEProducto)
    {
         int fila=TblGuiaRemisionRecibidoDetalle.getSelectedRow();
        if(oCEProducto!=null){
       
        CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalle= new CEGuiaRemisionRecibidoDetalle();
        oCEGuiaRemisionRecibidoDetalle.setProducto(oCEProducto.getDescripcion());
        oCEGuiaRemisionRecibidoDetalle.setIdProducto(oCEProducto.getIdProducto());
       // oCEGuiaRemisionRecibidoDetalle.setIdAlmacen(oCEProducto.getIdAlmacen());
        oCEGuiaRemisionRecibidoDetalle.setIdUnidadMedida(oCEProducto.getIdUnidadBase());
        CEUnidadMedidaProducto oCEUnidadMedidaProducto=new CEUnidadMedidaProducto();
        oCEUnidadMedidaProducto.setTipoUnidad(oCEProducto.getUMP());
        oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(oCEProducto.getIdUnidadBase());
        oCEGuiaRemisionRecibidoDetalle.setCodigoProducto(oCEProducto.getCodigo());
        oCEGuiaRemisionRecibidoDetalle.setOpcionAbm(1);
        if(pAccionABM==2)
        {
            CEGuiaRemisionRecibidoDetalle oCEGuiaRemisionRecibidoDetalleaux= (CEGuiaRemisionRecibidoDetalle)TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCodigo);
            if(oCEGuiaRemisionRecibidoDetalleaux!=null){
            oCEGuiaRemisionRecibidoDetalle.setIdGuiaRemisionRecibidoDetalle(oCEGuiaRemisionRecibidoDetalleaux.getIdGuiaRemisionRecibidoDetalle());
            oCEGuiaRemisionRecibidoDetalle.setOpcionAbm(2);
            }
            
        }

   

                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle,fila,oCLGuiaRemisionRecibido.colCodigo);
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEGuiaRemisionRecibidoDetalle.getProducto(),fila,oCLGuiaRemisionRecibido.colProducto);
                TblGuiaRemisionRecibidoDetalle.setValueAt(oCEUnidadMedidaProducto,fila,oCLGuiaRemisionRecibido.colUnidadMedida);
                float cantidad=0;
                if(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCantidad)!=null){
                cantidad=Float.parseFloat(TblGuiaRemisionRecibidoDetalle.getValueAt(fila, oCLGuiaRemisionRecibido.colCantidad).toString());
                }
                if(cantidad>0){
                    verificarDatos(fila,1);
                }

        }
        
    }
    
    private void limpiarFormulario()
    {      
      TxtNumComp.setText("");
      TxtObservacion.setText("");
      txtEstado.setText("EMITIDO");
      jTextFieldNumber.setText("");
      TxtNumComp.setText("");
      TxtProducto.setText("");
      TxtProveedor.setText("");
      TxtDNI.setText("");
      TxtObservacion.setText("");
      idProveedor=0;
      oCLGuiaRemisionRecibido.limpiarTabla();
      TxtVendedor.setText(FrmSistemaMenu.oCEUsuario.getNombreCompleto());
      TxtSucursal.setText(FrmSistemaMenu.oCEUsuario.getSucursal());
      DtchFecha.setDate(null);
      TxtChofer.setText("");
      TxtPlaca.setText("");
      TxtDestino.setText("");
      TxtOrigen.setText("");

    }
    /**
    * @param args the command line arguments
    */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnQuitar;
    private com.toedter.calendar.JDateChooser DtchFecha;
    private javax.swing.ButtonGroup GrRbtnNumSalida;
    private javax.swing.ButtonGroup GrRbtnNumdoc;
    private Label.Label LblEtiqProveedor;
    private Label.Label LblHoraRegistro1;
    private Label.Label LblHoraRegistro6;
    private Label.Label LblHoraSistema;
    private javax.swing.JPanel PnlDatos5;
    private javax.swing.JTable TblGuiaRemisionRecibidoDetalle;
    private TextField.JTxtLetra TxtChofer;
    private Label.Label TxtDNI;
    private TextField.JTxtNinguno TxtDestino;
    private TextField.JTxtNinguno TxtNumComp;
    private javax.swing.JTextArea TxtObservacion;
    private TextField.JTxtNinguno TxtOrigen;
    private TextField.JTxtNinguno TxtPlaca;
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
    private BotonesABM.BtnNuevo btnNuevo;
    private BotonesABM.BtnEliminar btnRecpecion;
    private util.clases.vtaVenta.ColumnButton columnButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label20;
    private Label.Label label28;
    private Label.Label label29;
    private Label.Label label32;
    private Label.Label label33;
    private Label.Label label34;
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
             GuardarIngreso();
             return;
        }

  
    }

}
