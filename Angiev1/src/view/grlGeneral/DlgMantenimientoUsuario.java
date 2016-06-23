package view.grlGeneral;


import combobox.ArrayListComboBoxModel;
import controller.grlGeneral.CCUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CEEmpleado;
import modelo.grlGeneral.entidad.CEUsuario;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;

public class DlgMantenimientoUsuario extends DialogoPadre implements ActionListener
{

    private int operacion=0;
    private int codigo=0;
    private int IdEmpleado=0;
    private int IdUsuario=0;   
    public DlgMantenimientoUsuario(java.awt.Frame parent, boolean modal,int state)
    {
        super(parent, modal);
        initComponents();
        actualizarTablaListado();
        configurarTabla();
        BtnNuevo.requestFocus();
        controladorDeEventosBotonesABM();
        resetearVerificadores();     
        if(state==0)
        {
            nuevo();
        }
    }
 private void configurarTabla()
    { TblListaUsuario.getColumnModel().getColumn(0).setMaxWidth(150);
      TblListaUsuario.getColumnModel().getColumn(0).setMinWidth(100);
      TblListaUsuario.getColumnModel().getColumn(1).setMaxWidth(250);
      TblListaUsuario.getColumnModel().getColumn(1).setMinWidth(250);
      TblListaUsuario.getColumnModel().getColumn(2).setMaxWidth(180);
      TblListaUsuario.getColumnModel().getColumn(2).setMinWidth(180);

    }
    private void abrirDialogoDeConsulta()
    {
                DlgBuscarEmpleadoParaUsuario oDialogoConsultaUsuario=new DlgBuscarEmpleadoParaUsuario(null,true,TxtSujeto.getText());
                oDialogoConsultaUsuario.setLocationRelativeTo(null);
                oDialogoConsultaUsuario.setVisible(true);
                CEEmpleado oUsuarioCE=oDialogoConsultaUsuario.getUsuario();
                if(oUsuarioCE!=null)
                {
                IdEmpleado=oUsuarioCE.getIdEmpleado();
                TxtSujeto.setText(oUsuarioCE.getNombreCompleto());
                }

    }

   
    
   private static final String ACTION_CLOSE = "ACTION_CLOSE";

    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }

 

    public void limpiarCmb(JComboBox oCombo)
    {
       construirModeloCombo(oCombo, new ArrayList());
    }
    public void construirModeloCombo(JComboBox oBox, ArrayList oLista)
    {
               ArrayListComboBoxModel model = new ArrayListComboBoxModel( oLista ) ;
               oBox.setModel(model);
    }
    private void controladorDeEventosBotonesABM()
      {
        if(operacion==0)
          {
            habilitarCajasDeTexto(false);
            habilitarBotonesABM(true,false,false,false,false,false,true);
            limpiarCajas();
          }
        else
          { if(operacion==1)
                {
                limpiarCajas();
                habilitarCajasDeTexto(true);
                TxtSujeto.setText("");
                habilitarBotonesABM(false,true,true,false,true,false,false);
                }
              else
              {
                    
                      if(operacion==3)
                        {
                         habilitarCajasDeTexto(false);
                         habilitarBotonesABM(true,true,false,true,false,true,true);
                        }
                      else
                      {
                        if(operacion==4)
                        {

                         habilitarCajasDeTexto(true);
                         TxtSujeto.requestFocus();
                         habilitarBotonesABM(false,false,true,false,true,false,false);
                       
                        }                      
                    }
              }
        }

    }
   private void limpiarCajas()
    {
        TxtSujeto.setText("");
        TxtUsername.setText("");
        JpfPassword.setText("");
        JpfPassword1.setText("");
        resetearVerificadores();
    }
    private void habilitarCajasDeTexto(boolean c)
    {
        TxtSujeto.setEditable(c);
        TxtUsername.setEditable(c);
        JpfPassword.setEditable(c);
        JpfPassword1.setEditable(c);
    }
    private void habilitarBotonesABM(boolean cNuevo,boolean cBuscar,boolean cGuardar,boolean cEditar,boolean cCancelar,boolean cEliminar,boolean cSalir)
    {
        BtnNuevo.setEnabled(cNuevo);
        BtnBuscar.setEnabled(cBuscar);
        BtnGuardar.setEnabled(cGuardar);
        BtnEditar.setEnabled(cEditar);
        BtnCancelar.setEnabled(cCancelar);
        BtnEliminar.setEnabled(cEliminar);
        BtnSalir.setEnabled(cSalir);
    }
    public void Guardar()
    {
         try
            {
             if(!verificarJpfUsername())
             {
              if(operacion==1)
              {
                int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de agregar Usuario?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    boolean a =CCUsuario.ejecutarProcedimientoAlta(getObjetoUsuario());
                    if(a)
                    {
                        JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                        operacion=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
                        BtnNuevo.requestFocus();
                        IdEmpleado=0;
                        IdUsuario=0;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                    }
                }
              }
            else
                {
                    if(operacion==4)
                    {
                        int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de modificar los datos?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION)
                        {
                             CEUsuario oCEUsuario=getObjetoUsuario();
                             if(CCUsuario.ejecutarProcedimientoBM(3,oCEUsuario))
                                {
                                    JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                                    operacion=0;
                                    controladorDeEventosBotonesABM();
                                    this.codigo=oCEUsuario.getIdUsuario();
                                    actualizarTablaListado();
                                    this.codigo=0;
                                    BtnNuevo.requestFocus();
                                    IdEmpleado=0;
                                    IdUsuario=0;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                                }
                        }
                    }
                }
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"Faltan Datos","Mensaje",JOptionPane.ERROR_MESSAGE);
             }
         }
            catch(Exception e)
                {
                   JOptionPane.showMessageDialog(null,"Escriba correctamente los datos");
                }
    }
   
    public void nuevo()
    {
        operacion=1;
        TblListaUsuario.clearSelection();
        limpiarCajas();
        IdEmpleado=0;
        IdUsuario=0;
        controladorDeEventosBotonesABM();
        TxtSujeto.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LblTitulo = new javax.swing.JLabel();
        PnlUsuario_Datos = new javax.swing.JPanel();
        LblSujeto = new javax.swing.JLabel();
        LblPassword = new javax.swing.JLabel();
        TxtSujeto = new javax.swing.JTextField();
        LblCamposObligatorios = new javax.swing.JLabel();
        LblVerificadorSujeto = new javax.swing.JLabel();
        JpfPassword = new javax.swing.JPasswordField();
        LblVerificadorPassword = new javax.swing.JLabel();
        BtnBuscar = new javax.swing.JButton();
        LblUsername = new javax.swing.JLabel();
        TxtUsername = new javax.swing.JTextField();
        LblVerificadorUsername = new javax.swing.JLabel();
        JpfPassword1 = new javax.swing.JPasswordField();
        LblReptPassword = new javax.swing.JLabel();
        LblPassword2 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        PnlBotonesDeNavegacion = new javax.swing.JPanel();
        BtnNuevo = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        ScrTabla = new javax.swing.JScrollPane();
        TblListaUsuario = new javax.swing.JTable();
        SptDePanel = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulario de Usuario");
        setResizable(false);

        LblTitulo.setFont(new java.awt.Font("Arial", 1, 18));
        LblTitulo.setText("Mantenimiento Usuario");

        PnlUsuario_Datos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblSujeto.setFont(new java.awt.Font("Arial", 1, 12));
        LblSujeto.setForeground(new java.awt.Color(153, 0, 0));
        LblSujeto.setText("Empleado:");

        LblPassword.setFont(new java.awt.Font("Arial", 1, 12));
        LblPassword.setForeground(new java.awt.Color(153, 0, 0));
        LblPassword.setText("Password:");

        TxtSujeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSujetoActionPerformed(evt);
            }
        });

        LblCamposObligatorios.setFont(new java.awt.Font("Tahoma", 1, 11));
        LblCamposObligatorios.setForeground(java.awt.Color.red);
        LblCamposObligatorios.setText("(*)Campos Obligatorios");

        LblVerificadorSujeto.setForeground(java.awt.Color.red);
        LblVerificadorSujeto.setText("*");

        LblVerificadorPassword.setForeground(java.awt.Color.red);
        LblVerificadorPassword.setText("*");

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Buscar.png"))); // NOI18N
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        LblUsername.setFont(new java.awt.Font("Arial", 1, 12));
        LblUsername.setForeground(new java.awt.Color(153, 0, 0));
        LblUsername.setText("Username:");

        LblVerificadorUsername.setForeground(java.awt.Color.red);
        LblVerificadorUsername.setText("*");

        LblReptPassword.setFont(new java.awt.Font("Arial", 1, 12));
        LblReptPassword.setForeground(new java.awt.Color(153, 0, 0));
        LblReptPassword.setText("(Confirme Pass.)");

        LblPassword2.setFont(new java.awt.Font("Arial", 1, 12));
        LblPassword2.setForeground(new java.awt.Color(153, 0, 0));
        LblPassword2.setText("Password:");

        jCheckBox5.setVisible(false);
        jCheckBox5.setFont(new java.awt.Font("Arial", 1, 11));
        jCheckBox5.setText("Mostrar Caracteres");
        jCheckBox5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox5StateChanged(evt);
            }
        });
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlUsuario_DatosLayout = new javax.swing.GroupLayout(PnlUsuario_Datos);
        PnlUsuario_Datos.setLayout(PnlUsuario_DatosLayout);
        PnlUsuario_DatosLayout.setHorizontalGroup(
            PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                        .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblUsername)
                            .addComponent(LblSujeto)
                            .addComponent(LblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JpfPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblVerificadorPassword)
                                    .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                                        .addComponent(LblVerificadorUsername)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LblCamposObligatorios))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlUsuario_DatosLayout.createSequentialGroup()
                                .addComponent(TxtSujeto, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblVerificadorSujeto, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlUsuario_DatosLayout.createSequentialGroup()
                        .addComponent(LblPassword2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JpfPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblReptPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5)
                        .addGap(180, 180, 180))))
        );
        PnlUsuario_DatosLayout.setVerticalGroup(
            PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUsuario_DatosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtSujeto)
                        .addComponent(LblVerificadorSujeto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSujeto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblUsername)
                    .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblVerificadorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblCamposObligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblVerificadorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblPassword))
                .addGap(7, 7, 7)
                .addGroup(PnlUsuario_DatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JpfPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblReptPassword)
                    .addComponent(LblPassword2)
                    .addComponent(jCheckBox5))
                .addContainerGap())
        );

        PnlBotonesDeNavegacion.setLayout(new java.awt.GridLayout(1, 0));

        BtnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Nuevo.png"))); // NOI18N
        BtnNuevo.setText("Nuevo");
        BtnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnNuevo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        BtnNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNuevoKeyPressed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnNuevo);

        BtnGuardar.setFont(new java.awt.Font("Arial", 1, 11));
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnGuardar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });
        BtnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGuardarKeyPressed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnGuardar);

        BtnEditar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEditar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnEditar);

        BtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnCancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnCancelar);

        BtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnEliminar);

        BtnSalir.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Salir.png"))); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnSalir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        PnlBotonesDeNavegacion.add(BtnSalir);

        TblListaUsuario.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario","Empleado","Sucursal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class,
            };
            boolean[] canEdit = new boolean [] {
                false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblListaUsuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblListaUsuario.getTableHeader().setReorderingAllowed(false);
        TblListaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblListaUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TblListaUsuarioMouseEntered(evt);
            }
        });
        TblListaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblListaUsuarioKeyReleased(evt);
            }
        });
        ScrTabla.setViewportView(TblListaUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(LblTitulo)
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(PnlUsuario_Datos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(SptDePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PnlUsuario_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ScrTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PnlBotonesDeNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 private void resetearVerificadores()
    {
     LblCamposObligatorios.setText("");
     LblVerificadorSujeto.setText("");
     LblVerificadorUsername.setText("");
     LblVerificadorPassword.setText("");
     LblReptPassword.setText("");
    }
   private boolean verificarCajasDeTexto()
    {
            boolean verificar=true;

            LblVerificadorSujeto.setText("");
            if(TxtSujeto.getText().equals("")&&IdEmpleado!=0)
            {
            LblVerificadorSujeto.setText("*");
            verificar=false;
            }
            else
            {
              LblVerificadorUsername.setText("");
            }

           if(TxtUsername.getText().equals(""))
           {
                LblVerificadorUsername.setText("*");
                verificar=false;
                }
            else
            {
               LblVerificadorPassword.setText("");
            }
            if(String.copyValueOf(JpfPassword.getPassword()).equals(""))
                {
                 LblVerificadorPassword.setText("*");
                 verificar=false;
                }
                else
                {
                 LblVerificadorPassword.setText("");
                }
             if(String.copyValueOf(JpfPassword1.getPassword()).equals(""))
                {
                 LblReptPassword.setText("(Confirme Pass.)");
                 verificar=false;
                }
                else
                {
                 LblReptPassword.setText("");
                }
            if(!String.copyValueOf(JpfPassword1.getPassword()).equals(String.copyValueOf(JpfPassword.getPassword())))
                {
                JOptionPane.showMessageDialog(null,"El password no coincide");
                verificar=false;
                }
           
        return verificar;
    }
    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
    TblListaUsuario.clearSelection();
    TxtSujeto.setEnabled(true);
        nuevo();
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if(verificarCajasDeTexto())
        {
        resetearVerificadores();
        Guardar();
        }
        else
        {
            LblCamposObligatorios.setText("(*)Campos Obligatorios");
            JOptionPane.showMessageDialog(null,"Faltan Datos","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
          operacion=4;
          BtnBuscar.setEnabled(true);
       controladorDeEventosBotonesABM();
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        operacion=0;
        controladorDeEventosBotonesABM();

    }//GEN-LAST:event_BtnCancelarActionPerformed
    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
      try
        {
              int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar ?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    CEUsuario oCEUsuario=(CEUsuario)TblListaUsuario.getValueAt(TblListaUsuario.getSelectedRow(), 0);
                    if(CCUsuario.ejecutarProcedimientoBM(2, oCEUsuario))
                    {
                        JOptionPane.showMessageDialog(null,"Operacion Exitosa");
                        operacion=0;
                        IdEmpleado=0;
                        IdUsuario=0;
                        controladorDeEventosBotonesABM();
                        actualizarTablaListado();
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null,"No se pudo completar la operacion");
                    }
                }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null,"Escriba los datos correctamente");
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed
    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
       dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed
    private void TblListaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaUsuarioMouseClicked
    if(evt.getClickCount()==1)
        {
        int selectedRow=TblListaUsuario.getSelectedRow();
         if(selectedRow!=-1)
         {
            jCheckBox5.setSelected(false);
            CEUsuario oCEUsuario=(CEUsuario)TblListaUsuario.getValueAt(selectedRow,0);
            setObjetoUsuario(oCEUsuario);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
        }
    }//GEN-LAST:event_TblListaUsuarioMouseClicked
    private void BtnGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGuardarKeyPressed
        if(evt.getKeyCode()==evt.VK_ENTER)
        {
            Guardar();
        }
    }//GEN-LAST:event_BtnGuardarKeyPressed
    private void BtnNuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNuevoKeyPressed
      if(evt.getKeyCode()==evt.VK_ENTER)
        {
            nuevo();
        }
    }//GEN-LAST:event_BtnNuevoKeyPressed
    private void TblListaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblListaUsuarioKeyReleased
    if(evt.getKeyCode()==evt.VK_UP||evt.getKeyCode()==evt.VK_DOWN)
    {
        int selectedRow=TblListaUsuario.getSelectedRow();
         if(selectedRow!=-1)
         {
            CEUsuario oCEUsuario=(CEUsuario)TblListaUsuario.getValueAt(selectedRow,0);
            setObjetoUsuario(oCEUsuario);
            operacion=3;
            controladorDeEventosBotonesABM();
         }
    }
    }//GEN-LAST:event_TblListaUsuarioKeyReleased

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        abrirDialogoDeConsulta();
}//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtSujetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSujetoActionPerformed
        abrirDialogoDeConsulta();
    }//GEN-LAST:event_TxtSujetoActionPerformed

    private void TblListaUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblListaUsuarioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TblListaUsuarioMouseEntered

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox5StateChanged


    }//GEN-LAST:event_jCheckBox5StateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
     if(evt.getStateChange()==evt.SELECTED)
     {
         JpfPassword.setEchoChar((char)0);
         JpfPassword1.setEchoChar((char)0);
     }
    else
     {
         JpfPassword.setEchoChar('*');
         JpfPassword1.setEchoChar('*');
     }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged
  private void setObjetoUsuario(CEUsuario oCEUsuario)
    {
        TxtSujeto.setText(oCEUsuario.getNombreCompleto()+"");
        TxtUsername.setText(oCEUsuario.getUsuario()+"");
       // JpfPassword.setText(oCEUsuario.getPassword()+"");
       // JpfPassword1.setText(oCEUsuario.getPassword()+"");
        IdEmpleado=oCEUsuario.getIdEmpleado();
        IdUsuario=oCEUsuario.getIdUsuario();
    }
 
    private CEUsuario getObjetoUsuario()
    {
        CEUsuario oCUsuario=new CEUsuario();
        oCUsuario.setUsuario(TxtUsername.getText().trim());
        oCUsuario.setPassword(String.copyValueOf(JpfPassword.getPassword()).trim());      
        oCUsuario.setIdEmpleado(IdEmpleado);
        oCUsuario.setIdUsuario(IdUsuario);
        return oCUsuario;
    }

     private void actualizarTablaListado()
     {
         limpiarTablaListado();
         List<CEUsuario> LstCEUsuario= CCUsuario.listaUsuario();
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaUsuario.getModel();
         
         if(LstCEUsuario!=null)
         {
             ArrayList oArrayList;
             for(int i=0;i<LstCEUsuario.size();i++)
             {
                     CEUsuario oCEUsuario= LstCEUsuario.get(i);
                     oArrayList =new ArrayList();
                     oArrayList.add(oCEUsuario);
                     oArrayList.add(oCEUsuario.getNombreCompleto());
                     oArrayList.add(oCEUsuario.getSucursal());
                     oModelo.addRow(oArrayList);
              }
             
         }
     }
     private void limpiarTablaListado()
     {
         ArrayListTableModel oModelo=(ArrayListTableModel)TblListaUsuario.getModel();
         int size =oModelo.getRowCount();
         for(int i=0;i<size;i++)
         {
            oModelo.removeRow(0);
         }
     }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JPasswordField JpfPassword;
    private javax.swing.JPasswordField JpfPassword1;
    private javax.swing.JLabel LblCamposObligatorios;
    private javax.swing.JLabel LblPassword;
    private javax.swing.JLabel LblPassword2;
    private javax.swing.JLabel LblReptPassword;
    private javax.swing.JLabel LblSujeto;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JLabel LblUsername;
    private javax.swing.JLabel LblVerificadorPassword;
    private javax.swing.JLabel LblVerificadorSujeto;
    private javax.swing.JLabel LblVerificadorUsername;
    private javax.swing.JPanel PnlBotonesDeNavegacion;
    private javax.swing.JPanel PnlUsuario_Datos;
    private javax.swing.JScrollPane ScrTabla;
    private javax.swing.JSeparator SptDePanel;
    private javax.swing.JTable TblListaUsuario;
    private javax.swing.JTextField TxtSujeto;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox5;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e)
    {
      if(operacion==0||operacion==3)
      {
      if(ACTION_CLOSE.equals(e.getActionCommand()))
        {
            dispose();
        }
      }
      else
      {
          operacion=0;
          controladorDeEventosBotonesABM();
          BtnNuevo.requestFocus();
      }
    }
    private boolean verificarJpfUsername()
    {
        return JpfPassword.getText().equals("");
    }

}

