package view.almAlmacen;

import combobox.ArrayListComboBoxModel;
import controller.almAlmacen.CCCategoria;
import controller.almAlmacen.CCFamilia;
import controller.almAlmacen.CCMarca;
import controller.almAlmacen.CCProducto;
import controller.almAlmacen.CCRubro;
import controller.almAlmacen.CCSubFamilia;
import controller.almAlmacen.CCUnidadMedidaVenta;
import controller.almAlmacen.CCUnidadPresentacionVenta;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import modelo.almAlmacen.entidad.CECategoria;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEFamilia;
import modelo.almAlmacen.entidad.CEMarca;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CERubro;
import modelo.almAlmacen.entidad.CESubfamilia;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.almAlmacen.entidad.CEUnidadMedidaVenta;
import modelo.almAlmacen.entidad.CEUnidadPresentacionVenta;
import table.ArrayListTableModel;
import util.clases.almAlmacen.IconTreeRenderer;
import util.clases.grlGeneral.DialogoPadre;

public class DlgGestionProducto extends DialogoPadre {

    private TreePath ultimoNodoClic;
    
    public DlgGestionProducto(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();  
        cargarRubro();
        TreeProducto.setCellRenderer(new IconTreeRenderer());
        TreeProducto.setRootVisible(false);
       TreeProducto.setShowsRootHandles(true);
       cargarTipoUnidadBase();
       cargarTipoUnidadPresentacion();
       configurarTabla();
       Container con = this.getContentPane();
		con.setBackground( new Color(204,204,204 ));
        
    }
     private void configurarTabla()
    {
        TblEquivalencias.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblEquivalencias.getColumnModel().getColumn(0).setMinWidth(40);
        TblEquivalencias.getColumnModel().getColumn(0).setMaxWidth(40);
        TblEquivalencias.getColumnModel().getColumn(2).setMinWidth(40);
        TblEquivalencias.getColumnModel().getColumn(2).setMaxWidth(40);
        TblEquivalencias.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(CbxUnidad));
        TblEquivalencias.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(CbxUnidad));
    }
    private void cargarRubro()
    {
        List<CERubro> oLstRubro= CCRubro.consultarListaRubros();
        if(oLstRubro!=null)
        {
            DefaultMutableTreeNode oDefaultMutableTreeNodeRoot= new DefaultMutableTreeNode()
            {

                @Override
                public boolean isLeaf() {
                  return false;
                }

            }             ;
            oDefaultMutableTreeNodeRoot.setUserObject(new CERubro());
            cargarNodo(oLstRubro,oDefaultMutableTreeNodeRoot);
            oDefaultMutableTreeNodeRoot.setAllowsChildren(true);
            DefaultTreeModel oAllTreeTableModel= new DefaultTreeModel(oDefaultMutableTreeNodeRoot);
            TreeProducto.setModel(oAllTreeTableModel);
        }
    }

    public void cargarNodo(Object arraylist,DefaultMutableTreeNode oDefaultMutableTreeNodeRoot)
    {
        oDefaultMutableTreeNodeRoot.removeAllChildren();
        ArrayList oArrayList=(ArrayList)arraylist;
        for(Object objeto:oArrayList)
        {
            oDefaultMutableTreeNodeRoot.add(new DefaultMutableTreeNode(objeto)
            {
                @Override
                public boolean isLeaf()
                {
                  return false;
                }
            });
        }
        ((DefaultTreeModel)TreeProducto.getModel()).reload(oDefaultMutableTreeNodeRoot);
      
    }
    private void cargarTipoUnidadBase()
    {
        List<CEUnidadMedidaVenta> oListUnidadMedidaVenta=CCUnidadMedidaVenta.consultarListaUnidadMedidaVentas();
        construirModeloCombo(CbxUnidadBase,(ArrayList)oListUnidadMedidaVenta);
        construirModeloCombo(CbxUnidad,(ArrayList)oListUnidadMedidaVenta);
    }
        private void cargarTipoUnidadPresentacion()
    {
        List<CEUnidadPresentacionVenta> oListUnidadMedidaVenta=CCUnidadPresentacionVenta.consultarListaUnidadPresentacionVentas();
        construirModeloCombo(CbxUnidadDePresentacion,(ArrayList)oListUnidadMedidaVenta);
    }
   public void construirModeloCombo(JComboBox oBox, ArrayList oLista)
    {
               ArrayListComboBoxModel model = new ArrayListComboBoxModel( oLista ) ;
               oBox.setModel(model);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuRubro = new javax.swing.JPopupMenu();
        jMenuItemAgregarNuevaCategoria = new javax.swing.JMenuItem();
        jMenuItemEditarRubro = new javax.swing.JMenuItem();
        jMenuItemEliminarRubro = new javax.swing.JMenuItem();
        jPopupMenuCategoria = new javax.swing.JPopupMenu();
        jMenuItemAgregaNuevaFamilia = new javax.swing.JMenuItem();
        jMenuItemEditarCategoria = new javax.swing.JMenuItem();
        jMenuItemEliminarCategoria = new javax.swing.JMenuItem();
        jPopupMenuFamilia = new javax.swing.JPopupMenu();
        jMenuItemAgregarSubFamilia = new javax.swing.JMenuItem();
        jMenuItemEditarFamilia = new javax.swing.JMenuItem();
        jMenuItemEliminarFamilia = new javax.swing.JMenuItem();
        PnlFormularioCategoria = new javax.swing.JPanel();
        TxtCodigoCategoria = new TextField.JTxtNumero();
        TxtDescripcionCategoria = new TextField.JTxtLetra();
        label1Categoria = new Label.Label();
        label2Categoria = new Label.Label();
        TxtRubroCategoria = new TextField.JTxtLetra();
        label3Categoria = new Label.Label();
        PnlFormularioRubro = new javax.swing.JPanel();
        TxtCodigoRubro = new TextField.JTxtNumero();
        TxtDescripcionRubro = new TextField.JTxtLetra();
        label1Rubro = new Label.Label();
        label2Rubro = new Label.Label();
        PnlFormularioFamilia = new javax.swing.JPanel();
        TxtCodigoFamilia = new TextField.JTxtNumero();
        TxtDescripcionFamilia = new TextField.JTxtLetra();
        label1Familia = new Label.Label();
        label2Familia = new Label.Label();
        TxtRubroFamilia = new TextField.JTxtLetra();
        label3Familia = new Label.Label();
        TxtCategoriaFamilia = new TextField.JTxtLetra();
        label5Familia = new Label.Label();
        PnlFormularioSubfamilia = new javax.swing.JPanel();
        TxtCodigoSubfamilia = new TextField.JTxtNumero();
        TxtDescripcionSubfamilia = new TextField.JTxtLetra();
        label1 = new Label.Label();
        label2 = new Label.Label();
        TxtCategoriaSubfamilia = new TextField.JTxtLetra();
        label3 = new Label.Label();
        label4 = new Label.Label();
        TxtRubroSubfamilia = new TextField.JTxtLetra();
        label5 = new Label.Label();
        TxtFamiliaSubfamilia = new TextField.JTxtLetra();
        jPopupMenuSubfamilia = new javax.swing.JPopupMenu();
        jMenuItemAgregarProducto = new javax.swing.JMenuItem();
        jMenuItemEditarSubfamilia = new javax.swing.JMenuItem();
        jMenuItemEliminarSubfamilia = new javax.swing.JMenuItem();
        jPopupMenuRoot = new javax.swing.JPopupMenu();
        jMenuItemAgregarRubro = new javax.swing.JMenuItem();
        jPopupMenuProducto = new javax.swing.JPopupMenu();
        jMenuItemEditarProducto = new javax.swing.JMenuItem();
        jMenuItemEliminarProducto = new javax.swing.JMenuItem();
        jMenuItemModificarPrecios = new javax.swing.JMenuItem();
        PnlProducto = new javax.swing.JPanel();
        TxtCategoriaProducto = new javax.swing.JTextField();
        TxtDescripcionProducto = new TextField.JTxtLetraNumero();
        TxtSubfamiliaProducto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtRubroProducto = new javax.swing.JTextField();
        CbxUnidadBase = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        RbtNoImpuesto = new javax.swing.JRadioButton();
        TxtMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtFamiliaProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        RbtSiImpuesto = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblEquivalencias = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LblCodigoProducto = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblPedidosEquivalencia = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        TxtUnidadMasComercial = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        CbxUnidadDePresentacion = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        BtnModificarProducto = new javax.swing.JButton();
        CbxUnidad = new javax.swing.JComboBox()
        {
            private boolean layingOut = false;

            public void doLayout(){
                try{
                    layingOut = true;
                    super.doLayout();
                }finally{
                    layingOut = false;
                }
            }

            public Dimension getSize(){
                Dimension dim = super.getSize();
                if(!layingOut)
                dim.width = Math.max(dim.width, getPreferredSize().width);
                return dim;
            }

        }
        ;
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        PnlConsulta = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        LblLeyendaRubro = new javax.swing.JLabel();
        LblLeyendaRubro1 = new javax.swing.JLabel();
        LblLeyendaRubro2 = new javax.swing.JLabel();
        LblLeyendaRubro3 = new javax.swing.JLabel();
        LblLeyendaRubro4 = new javax.swing.JLabel();
        LblLeyendaRubro5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TreeProducto = new javax.swing.JTree();

        jMenuItemAgregarNuevaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Categoria.gif"))); // NOI18N
        jMenuItemAgregarNuevaCategoria.setText("Agregar Nueva Categoria");
        jMenuItemAgregarNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarNuevaCategoriaActionPerformed(evt);
            }
        });
        jPopupMenuRubro.add(jMenuItemAgregarNuevaCategoria);

        jMenuItemEditarRubro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Rubro.gif"))); // NOI18N
        jMenuItemEditarRubro.setText("Editar Rubro");
        jMenuItemEditarRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarRubroActionPerformed(evt);
            }
        });
        jPopupMenuRubro.add(jMenuItemEditarRubro);

        jMenuItemEliminarRubro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        jMenuItemEliminarRubro.setText("Eliminar Rubro");
        jMenuItemEliminarRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarRubroActionPerformed(evt);
            }
        });
        jPopupMenuRubro.add(jMenuItemEliminarRubro);

        jMenuItemAgregaNuevaFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Familia.gif"))); // NOI18N
        jMenuItemAgregaNuevaFamilia.setText("Agregar Nueva Familia");
        jMenuItemAgregaNuevaFamilia.setActionCommand("Agregar Nueva Categoria");
        jMenuItemAgregaNuevaFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregaNuevaFamiliaActionPerformed(evt);
            }
        });
        jPopupMenuCategoria.add(jMenuItemAgregaNuevaFamilia);

        jMenuItemEditarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Categoria.gif"))); // NOI18N
        jMenuItemEditarCategoria.setText("Editar Categoria");
        jMenuItemEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarCategoriaActionPerformed(evt);
            }
        });
        jPopupMenuCategoria.add(jMenuItemEditarCategoria);

        jMenuItemEliminarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        jMenuItemEliminarCategoria.setText("Eliminar Categoria");
        jMenuItemEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarCategoriaActionPerformed(evt);
            }
        });
        jPopupMenuCategoria.add(jMenuItemEliminarCategoria);

        jMenuItemAgregarSubFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"))); // NOI18N
        jMenuItemAgregarSubFamilia.setText("Agregar Nueva SubFamilia");
        jMenuItemAgregarSubFamilia.setActionCommand("Agregar Nueva Subfamilia");
        jMenuItemAgregarSubFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarSubFamiliaActionPerformed(evt);
            }
        });
        jPopupMenuFamilia.add(jMenuItemAgregarSubFamilia);

        jMenuItemEditarFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Familia.gif"))); // NOI18N
        jMenuItemEditarFamilia.setText("Editar Familia");
        jMenuItemEditarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarFamiliaActionPerformed(evt);
            }
        });
        jPopupMenuFamilia.add(jMenuItemEditarFamilia);

        jMenuItemEliminarFamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        jMenuItemEliminarFamilia.setText("Eliminar Familia");
        jMenuItemEliminarFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarFamiliaActionPerformed(evt);
            }
        });
        jPopupMenuFamilia.add(jMenuItemEliminarFamilia);

        PnlFormularioCategoria.setBackground(new java.awt.Color(255, 255, 255));
        PnlFormularioCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "[Categoria]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigoCategoria.setEditable(false);
        TxtCodigoCategoria.setTamanio(10);

        TxtDescripcionCategoria.setEditable(false);
        TxtDescripcionCategoria.setTamanio(50);

        label1Categoria.setForeground(new java.awt.Color(102, 0, 0));
        label1Categoria.setText("Codigo :");

        label2Categoria.setForeground(new java.awt.Color(102, 0, 0));
        label2Categoria.setText("Descripcion :");

        TxtRubroCategoria.setEditable(false);
        TxtRubroCategoria.setTamanio(50);

        label3Categoria.setForeground(new java.awt.Color(102, 0, 0));
        label3Categoria.setText("Rubro :");

        javax.swing.GroupLayout PnlFormularioCategoriaLayout = new javax.swing.GroupLayout(PnlFormularioCategoria);
        PnlFormularioCategoria.setLayout(PnlFormularioCategoriaLayout);
        PnlFormularioCategoriaLayout.setHorizontalGroup(
            PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlFormularioCategoriaLayout.createSequentialGroup()
                        .addComponent(label3Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(TxtRubroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PnlFormularioCategoriaLayout.createSequentialGroup()
                        .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtDescripcionCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                            .addComponent(TxtCodigoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );
        PnlFormularioCategoriaLayout.setVerticalGroup(
            PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioCategoriaLayout.createSequentialGroup()
                .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtRubroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcionCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PnlFormularioRubro.setBackground(new java.awt.Color(255, 255, 255));
        PnlFormularioRubro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "[Rubro]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigoRubro.setEditable(false);
        TxtCodigoRubro.setTamanio(2);

        TxtDescripcionRubro.setEditable(false);
        TxtDescripcionRubro.setTamanio(50);

        label1Rubro.setForeground(new java.awt.Color(102, 0, 0));
        label1Rubro.setText("Codigo :");

        label2Rubro.setForeground(new java.awt.Color(102, 0, 0));
        label2Rubro.setText("Descripcion :");

        javax.swing.GroupLayout PnlFormularioRubroLayout = new javax.swing.GroupLayout(PnlFormularioRubro);
        PnlFormularioRubro.setLayout(PnlFormularioRubroLayout);
        PnlFormularioRubroLayout.setHorizontalGroup(
            PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtCodigoRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcionRubro, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnlFormularioRubroLayout.setVerticalGroup(
            PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigoRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcionRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PnlFormularioFamilia.setBackground(new java.awt.Color(255, 255, 255));
        PnlFormularioFamilia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "[Familia]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigoFamilia.setEditable(false);
        TxtCodigoFamilia.setTamanio(10);
        TxtCodigoFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCodigoFamiliaActionPerformed(evt);
            }
        });

        TxtDescripcionFamilia.setEditable(false);
        TxtDescripcionFamilia.setTamanio(50);

        label1Familia.setForeground(new java.awt.Color(102, 0, 0));
        label1Familia.setText("Codigo :");

        label2Familia.setForeground(new java.awt.Color(102, 0, 0));
        label2Familia.setText("Descripcion :");

        TxtRubroFamilia.setEditable(false);
        TxtRubroFamilia.setTamanio(50);

        label3Familia.setForeground(new java.awt.Color(102, 0, 0));
        label3Familia.setText("Categoria :");

        TxtCategoriaFamilia.setEditable(false);
        TxtCategoriaFamilia.setTamanio(50);

        label5Familia.setForeground(new java.awt.Color(102, 0, 0));
        label5Familia.setText("Rubro :");

        javax.swing.GroupLayout PnlFormularioFamiliaLayout = new javax.swing.GroupLayout(PnlFormularioFamilia);
        PnlFormularioFamilia.setLayout(PnlFormularioFamiliaLayout);
        PnlFormularioFamiliaLayout.setHorizontalGroup(
            PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioFamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlFormularioFamiliaLayout.createSequentialGroup()
                        .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtCategoriaFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtRubroFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnlFormularioFamiliaLayout.createSequentialGroup()
                        .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtDescripcionFamilia, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                            .addComponent(TxtCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
        PnlFormularioFamiliaLayout.setVerticalGroup(
            PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioFamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtRubroFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCategoriaFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigoFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioFamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcionFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        PnlFormularioSubfamilia.setBackground(new java.awt.Color(255, 255, 255));
        PnlFormularioSubfamilia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "[SubFamilia]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        TxtCodigoSubfamilia.setEditable(false);
        TxtCodigoSubfamilia.setTamanio(12);
        TxtCodigoSubfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCodigoSubfamiliaActionPerformed(evt);
            }
        });

        TxtDescripcionSubfamilia.setEditable(false);
        TxtDescripcionSubfamilia.setTamanio(50);

        label1.setForeground(new java.awt.Color(102, 0, 0));
        label1.setText("Codigo :");

        label2.setForeground(new java.awt.Color(102, 0, 0));
        label2.setText("Descripcion :");

        TxtCategoriaSubfamilia.setEditable(false);
        TxtCategoriaSubfamilia.setTamanio(50);

        label3.setForeground(new java.awt.Color(102, 0, 0));
        label3.setText("Categoria :");

        label4.setForeground(new java.awt.Color(102, 0, 0));
        label4.setText("Rubro :");

        TxtRubroSubfamilia.setEditable(false);
        TxtRubroSubfamilia.setTamanio(50);

        label5.setForeground(new java.awt.Color(102, 0, 0));
        label5.setText("Familia :");

        TxtFamiliaSubfamilia.setEditable(false);
        TxtFamiliaSubfamilia.setTamanio(50);

        javax.swing.GroupLayout PnlFormularioSubfamiliaLayout = new javax.swing.GroupLayout(PnlFormularioSubfamilia);
        PnlFormularioSubfamilia.setLayout(PnlFormularioSubfamiliaLayout);
        PnlFormularioSubfamiliaLayout.setHorizontalGroup(
            PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFormularioSubfamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlFormularioSubfamiliaLayout.createSequentialGroup()
                        .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtFamiliaSubfamilia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtRubroSubfamilia, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(TxtCategoriaSubfamilia, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))
                    .addGroup(PnlFormularioSubfamiliaLayout.createSequentialGroup()
                        .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtCodigoSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtDescripcionSubfamilia, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );
        PnlFormularioSubfamiliaLayout.setVerticalGroup(
            PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlFormularioSubfamiliaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtRubroSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCategoriaSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtFamiliaSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigoSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnlFormularioSubfamiliaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDescripcionSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jMenuItemAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/product.png"))); // NOI18N
        jMenuItemAgregarProducto.setText("Agregar Producto");
        jMenuItemAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarProductoActionPerformed(evt);
            }
        });
        jPopupMenuSubfamilia.add(jMenuItemAgregarProducto);

        jMenuItemEditarSubfamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"))); // NOI18N
        jMenuItemEditarSubfamilia.setText("Editar SubFamilia");
        jMenuItemEditarSubfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarSubfamiliaActionPerformed(evt);
            }
        });
        jPopupMenuSubfamilia.add(jMenuItemEditarSubfamilia);

        jMenuItemEliminarSubfamilia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        jMenuItemEliminarSubfamilia.setText("Eliminar SubFamilia");
        jMenuItemEliminarSubfamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarSubfamiliaActionPerformed(evt);
            }
        });
        jPopupMenuSubfamilia.add(jMenuItemEliminarSubfamilia);

        jMenuItemAgregarRubro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Rubro.gif"))); // NOI18N
        jMenuItemAgregarRubro.setText("Agregar Rubro");
        jMenuItemAgregarRubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarRubroActionPerformed(evt);
            }
        });
        jPopupMenuRoot.add(jMenuItemAgregarRubro);

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

        jMenuItemModificarPrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/precios.gif"))); // NOI18N
        jMenuItemModificarPrecios.setText("Modificar Precios");
        jMenuItemModificarPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarPreciosActionPerformed(evt);
            }
        });
        jPopupMenuProducto.add(jMenuItemModificarPrecios);

        PnlProducto.setBackground(new java.awt.Color(255, 255, 255));
        PnlProducto.setLayout(null);

        TxtCategoriaProducto.setEditable(false);
        PnlProducto.add(TxtCategoriaProducto);
        TxtCategoriaProducto.setBounds(85, 44, 241, 20);

        TxtDescripcionProducto.setEditable(false);
        TxtDescripcionProducto.setText("jTxtLetraNumero1");
        TxtDescripcionProducto.setObligatorio(false);
        TxtDescripcionProducto.setTamanio(300);
        PnlProducto.add(TxtDescripcionProducto);
        TxtDescripcionProducto.setBounds(85, 106, 539, 22);

        TxtSubfamiliaProducto.setEditable(false);
        PnlProducto.add(TxtSubfamiliaProducto);
        TxtSubfamiliaProducto.setBounds(85, 75, 241, 20);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel12.setForeground(new java.awt.Color(153, 0, 0));
        jLabel12.setText("Unidad Base");
        PnlProducto.add(jLabel12);
        jLabel12.setBounds(10, 149, 71, 15);

        TxtRubroProducto.setEditable(false);
        PnlProducto.add(TxtRubroProducto);
        TxtRubroProducto.setBounds(85, 13, 241, 20);

        CbxUnidadBase.setEnabled(false);
        PnlProducto.add(CbxUnidadBase);
        CbxUnidadBase.setBounds(85, 147, 116, 20);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Rubro           :");
        PnlProducto.add(jLabel3);
        jLabel3.setBounds(10, 15, 70, 15);

        buttonGroup1.add(RbtNoImpuesto);
        RbtNoImpuesto.setText("No");
        RbtNoImpuesto.setEnabled(false);
        PnlProducto.add(RbtNoImpuesto);
        RbtNoImpuesto.setBounds(588, 146, 39, 23);

        TxtMarca.setEditable(false);
        TxtMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtMarcaFocusGained(evt);
            }
        });
        PnlProducto.add(TxtMarca);
        TxtMarca.setBounds(383, 75, 241, 20);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Familia:");
        PnlProducto.add(jLabel5);
        jLabel5.setBounds(336, 46, 43, 15);

        TxtFamiliaProducto.setEditable(false);
        PnlProducto.add(TxtFamiliaProducto);
        TxtFamiliaProducto.setBounds(383, 44, 241, 20);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Categoria    :");
        PnlProducto.add(jLabel2);
        jLabel2.setBounds(10, 46, 70, 15);

        buttonGroup1.add(RbtSiImpuesto);
        RbtSiImpuesto.setSelected(true);
        RbtSiImpuesto.setText("Sí");
        RbtSiImpuesto.setEnabled(false);
        PnlProducto.add(RbtSiImpuesto);
        RbtSiImpuesto.setBounds(537, 146, 33, 23);

        TblEquivalencias.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Cant.","UND.","Equiv.","Unidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class,
                java.lang.Object.class,
                java.lang.Float.class,
                java.lang.Object.class,
            };
            boolean[] canEdit = new boolean [] {
                true,true,true,true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblEquivalencias.setEnabled(false);
        jScrollPane2.setViewportView(TblEquivalencias);

        PnlProducto.add(jScrollPane2);
        jScrollPane2.setBounds(246, 187, 381, 150);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Descripción:");
        PnlProducto.add(jLabel8);
        jLabel8.setBounds(10, 110, 71, 15);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("Marca:");
        PnlProducto.add(jLabel7);
        jLabel7.setBounds(340, 76, 39, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("Subfamilia  :");
        PnlProducto.add(jLabel6);
        jLabel6.setBounds(10, 77, 69, 15);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("¿Impuesto?");
        PnlProducto.add(jLabel11);
        jLabel11.setBounds(464, 149, 67, 15);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setText("Código:");
        PnlProducto.add(jLabel13);
        jLabel13.setBounds(426, 11, 42, 15);

        LblCodigoProducto.setFont(new java.awt.Font("Arial", 1, 12));
        LblCodigoProducto.setText("..................");
        PnlProducto.add(LblCodigoProducto);
        LblCodigoProducto.setBounds(474, 11, 116, 15);

        TblPedidosEquivalencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Unidad", "Equiv.", "Und."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPedidosEquivalencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(TblPedidosEquivalencia);
        TblPedidosEquivalencia.getColumnModel().getColumn(0).setPreferredWidth(40);
        TblPedidosEquivalencia.getColumnModel().getColumn(1).setPreferredWidth(80);
        TblPedidosEquivalencia.getColumnModel().getColumn(2).setPreferredWidth(50);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setPreferredWidth(50);

        PnlProducto.add(jScrollPane3);
        jScrollPane3.setBounds(10, 187, 230, 150);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel14.setForeground(new java.awt.Color(153, 0, 0));
        jLabel14.setText("U.P.C.");
        PnlProducto.add(jLabel14);
        jLabel14.setBounds(10, 350, 33, 15);

        TxtUnidadMasComercial.setEditable(false);
        TxtUnidadMasComercial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtUnidadMasComercialFocusGained(evt);
            }
        });
        PnlProducto.add(TxtUnidadMasComercial);
        TxtUnidadMasComercial.setBounds(50, 350, 193, 20);

        jButton1.setText("Ver Listado de Precios");
        PnlProducto.add(jButton1);
        jButton1.setBounds(250, 350, 137, 23);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/precios.gif"))); // NOI18N
        jButton2.setText("Modificar Lista de Precios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PnlProducto.add(jButton2);
        jButton2.setBounds(390, 350, 180, 25);

        CbxUnidadDePresentacion.setEnabled(false);
        CbxUnidadDePresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxUnidadDePresentacionActionPerformed(evt);
            }
        });
        PnlProducto.add(CbxUnidadDePresentacion);
        CbxUnidadDePresentacion.setBounds(299, 147, 161, 20);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel15.setForeground(new java.awt.Color(153, 0, 0));
        jLabel15.setText("Und.  Prest.:");
        PnlProducto.add(jLabel15);
        jLabel15.setBounds(219, 147, 76, 18);

        BtnModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/editarToolStripMenuItem.Image.png"))); // NOI18N
        BtnModificarProducto.setToolTipText("Modificar Datos del Producto");
        BtnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarProductoActionPerformed(evt);
            }
        });
        PnlProducto.add(BtnModificarProducto);
        BtnModificarProducto.setBounds(610, 0, 18, 23);

        CbxUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Productos");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/BannerCatalagoDeProductos.jpg"))); // NOI18N

        PnlConsulta.setBackground(new java.awt.Color(255, 255, 255));
        PnlConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlConsulta.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()), "Leyenda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        LblLeyendaRubro.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Rubro.gif"))); // NOI18N
        LblLeyendaRubro.setText("Rubro");

        LblLeyendaRubro1.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Familia.gif"))); // NOI18N
        LblLeyendaRubro1.setText("Familia");

        LblLeyendaRubro2.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Categoria.gif"))); // NOI18N
        LblLeyendaRubro2.setText("Categoria");

        LblLeyendaRubro3.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"))); // NOI18N
        LblLeyendaRubro3.setText("SubFamilia");

        LblLeyendaRubro4.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Marca.gif"))); // NOI18N
        LblLeyendaRubro4.setText("Marca");

        LblLeyendaRubro5.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/product.png"))); // NOI18N
        LblLeyendaRubro5.setText("Producto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblLeyendaRubro3)
                    .addComponent(LblLeyendaRubro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblLeyendaRubro2)
                    .addComponent(LblLeyendaRubro4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblLeyendaRubro5)
                    .addComponent(LblLeyendaRubro1))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblLeyendaRubro)
                    .addComponent(LblLeyendaRubro2)
                    .addComponent(LblLeyendaRubro1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblLeyendaRubro3)
                    .addComponent(LblLeyendaRubro4)
                    .addComponent(LblLeyendaRubro5))
                .addContainerGap())
        );

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        TreeProducto.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        TreeProducto.setMaximumSize(new java.awt.Dimension(43, 50));
        TreeProducto.setRequestFocusEnabled(false);
        TreeProducto.setRowHeight(24);
        TreeProducto.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                TreeProductoTreeWillExpand(evt);
            }
        });
        TreeProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TreeProducto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PnlConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAgregarNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarNuevaCategoriaActionPerformed
      DlgMantenimientoCategoria oDlgMantenimientoFamilia=new DlgMantenimientoCategoria(null,true,(CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject());
      oDlgMantenimientoFamilia.setLocationRelativeTo(null);
      oDlgMantenimientoFamilia.setVisible(true);
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent());
    }//GEN-LAST:event_jMenuItemAgregarNuevaCategoriaActionPerformed

    private void jMenuItemAgregaNuevaFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregaNuevaFamiliaActionPerformed
      DlgMantenimientoFamilia oDlgMantenimientoFamilia=new DlgMantenimientoFamilia(null,true,(CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString());
      oDlgMantenimientoFamilia.setLocationRelativeTo(null);
      oDlgMantenimientoFamilia.setVisible(true);
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent());

    }//GEN-LAST:event_jMenuItemAgregaNuevaFamiliaActionPerformed

    private void jMenuItemAgregarSubFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarSubFamiliaActionPerformed
      DlgMantenimientosubFamilia oDlgMantenimientoFamilia=new DlgMantenimientosubFamilia(null,true,
              (CEFamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),
              ((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString(),
              ((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString());
      oDlgMantenimientoFamilia.setLocationRelativeTo(null);
      oDlgMantenimientoFamilia.setVisible(true);

      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent());
    }//GEN-LAST:event_jMenuItemAgregarSubFamiliaActionPerformed

    private void TreeProductoTreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_TreeProductoTreeWillExpand
             Object nodo=evt.getPath().getLastPathComponent();
             DefaultMutableTreeNode nodoAux=(DefaultMutableTreeNode)nodo;
             refrescarTree(nodoAux);


    }//GEN-LAST:event_TreeProductoTreeWillExpand
 public void refrescarTree(DefaultMutableTreeNode nodoAux)
    {
            nodoAux.removeAllChildren();
             if(nodoAux.getUserObject() instanceof CERubro)
             {

                List<CECategoria> oLstCategoria= CCCategoria.consultarListaCategoriaPorIdRubrosSinVacios(((CERubro)nodoAux.getUserObject()).getIdRubro());
                if(oLstCategoria!=null)
                {
                    cargarNodo(oLstCategoria,nodoAux);
                }
             }
            else
             {
             if(nodoAux.getUserObject() instanceof CECategoria)
             {
                List<CEFamilia> oLstFamilia= CCFamilia.consultarListaFamiliaPorIdCategoriaSinVacios(((CECategoria)nodoAux.getUserObject()).getIdCategoria());
                if(oLstFamilia!=null)
                {
                   cargarNodo(oLstFamilia,nodoAux);
                }
             }
            else
             {
                 if(nodoAux.getUserObject() instanceof CEFamilia)
                 {
                    List<CESubfamilia> oLstSubfamilia= CCSubFamilia.consultarListaFamiliaPorIdFamiliaSinVacios(((CEFamilia)nodoAux.getUserObject()).getIdFamilia());
                    if(oLstSubfamilia!=null)
                    {
                        cargarNodo(oLstSubfamilia,nodoAux);
                    }
                 }
                 else
                 {
                     if(nodoAux.getUserObject() instanceof CESubfamilia)
                     {
                         int IdRubro=((CERubro)((DefaultMutableTreeNode)nodoAux.getParent().getParent().getParent()).getUserObject()).getIdRubro();
                         int IdCategoria=((CECategoria)((DefaultMutableTreeNode)nodoAux.getParent().getParent()).getUserObject()).getIdCategoria();
                         int IdFamilia=((CEFamilia)((DefaultMutableTreeNode)nodoAux.getParent()).getUserObject()).getIdFamilia();
                         int IdSubfamilia=((CESubfamilia)(nodoAux).getUserObject()).getIdSubFamilia();
                         List<CEMarca> oLstMarca= CCMarca.consultarListaMarcas(IdSubfamilia,IdFamilia,IdCategoria,IdRubro);
                        if(oLstMarca!=null)
                        {
                                cargarNodo(oLstMarca,nodoAux);
                        }
                     }
                     if(nodoAux.getUserObject() instanceof CEMarca)
                     {

                        List<CEProducto> oLstMarca= CCProducto.consultarProductoPorMarca(((CEMarca)nodoAux.getUserObject()).getIdMarca(),((CESubfamilia)((DefaultMutableTreeNode)nodoAux.getParent()).getUserObject()).getIdSubFamilia());
                        if(oLstMarca!=null)
                        {
                                cargarNodo(oLstMarca,nodoAux);
                        }
                     }
                }
            }
         }
 }
    private void TreeProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeProductoMouseClicked

     TreePath nodo=TreeProducto.getPathForLocation(evt.getX(),evt.getY());
     ultimoNodoClic=nodo;
     if(evt.getButton()==evt.BUTTON3)
        {
            if(nodo==null)
            {
                jPopupMenuRoot.show(TreeProducto,evt.getX(),evt.getY());
            }
            else
            {
                if(((DefaultMutableTreeNode)(nodo.getLastPathComponent())).getUserObject() instanceof CERubro)
                {
                    jPopupMenuRubro.show(TreeProducto,evt.getX(), evt.getY());
                }
                else
                {
                    if(((DefaultMutableTreeNode)(nodo.getLastPathComponent())).getUserObject() instanceof CECategoria)
                    {
                         jPopupMenuCategoria.show(TreeProducto,evt.getX(), evt.getY());
                    }
                    else
                    {
                        if(((DefaultMutableTreeNode)(nodo.getLastPathComponent())).getUserObject() instanceof CEFamilia)
                        {
                             jPopupMenuFamilia.show(TreeProducto,evt.getX(), evt.getY());
                        }
                        else
                        {
                            if(((DefaultMutableTreeNode)(nodo.getLastPathComponent())).getUserObject() instanceof CESubfamilia)
                            {
                                 jPopupMenuSubfamilia.show(TreeProducto,evt.getX(), evt.getY());
                            }
                            else
                            {
                                if(((DefaultMutableTreeNode)(nodo.getLastPathComponent())).getUserObject() instanceof CEProducto)
                                {
                                 jPopupMenuProducto.show(TreeProducto,evt.getX(), evt.getY());
                                }
                            }
                        }
                    }
                }
            }
    }
    else
    {
        if(TreeProducto.getPathForLocation(evt.getX(),evt.getY())!=null)
        {
         Object nodoT= TreeProducto.getPathForLocation(evt.getX(),evt.getY()).getLastPathComponent();
         DefaultMutableTreeNode nodoAux=(DefaultMutableTreeNode)nodoT;
         if(nodoAux.getUserObject() instanceof CERubro)
         {
            cargarRubro((CERubro)nodoAux.getUserObject());
            PnlConsulta.removeAll();
            PnlConsulta.add(PnlFormularioRubro, java.awt.BorderLayout.CENTER);
            PnlConsulta.updateUI();
         }
        else
         {
            if(nodoAux.getUserObject() instanceof CECategoria)
            {
                String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
                cargarCategoria((CECategoria)nodoAux.getUserObject(),rubro);
                PnlConsulta.removeAll();
                PnlConsulta.add(PnlFormularioCategoria, java.awt.BorderLayout.CENTER);
                PnlConsulta.updateUI();
            }
            else
             {
                     if(nodoAux.getUserObject() instanceof CEFamilia)
                     {
                         String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                         String categoria=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
                         cargarFamilia((CEFamilia)nodoAux.getUserObject(),categoria,rubro);
                         PnlConsulta.removeAll();
                         PnlConsulta.add(PnlFormularioFamilia, java.awt.BorderLayout.CENTER);
                         PnlConsulta.updateUI();
                     }
                     else
                     {
                         if(nodoAux.getUserObject() instanceof CESubfamilia)
                         {

                             String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                             String categoria=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                             String familia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
                             cargarSubFamilia((CESubfamilia)nodoAux.getUserObject(),familia,categoria,rubro);
                             PnlConsulta.removeAll();
                             PnlConsulta.add(PnlFormularioSubfamilia, java.awt.BorderLayout.CENTER);
                             PnlConsulta.updateUI();
                         }
                           else
                             {
                                 if(nodoAux.getUserObject() instanceof CEProducto)
                                 {

                                     String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                                     String categoria=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                                     String familia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                                     String subfamilia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
                                     String marca=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
                                     cargarProducto((CEProducto)nodoAux.getUserObject(),marca,subfamilia,familia,categoria,rubro);
                                     PnlConsulta.removeAll();
                                     PnlConsulta.add(PnlProducto, java.awt.BorderLayout.CENTER);
                                     PnlConsulta.updateUI();
                                 }
                            }
                    }
            }
           }
        }
    }
    }//GEN-LAST:event_TreeProductoMouseClicked

    private void jMenuItemEditarRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarRubroActionPerformed
      DlgMantenimientoRubro oDlgMantenimientoRubro=new DlgMantenimientoRubro(null,true,(CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),2);
      oDlgMantenimientoRubro.setLocationRelativeTo(null);
      oDlgMantenimientoRubro.setVisible(true);
      cargarRubro();
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
    }//GEN-LAST:event_jMenuItemEditarRubroActionPerformed

    private void jMenuItemEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarCategoriaActionPerformed
      DlgMantenimientoCategoria oDlgMantenimientoCategoria=new DlgMantenimientoCategoria(null,true,(CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(), ((CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).toString());
      oDlgMantenimientoCategoria.setLocationRelativeTo(null);
      oDlgMantenimientoCategoria.setVisible(true);
      List<CECategoria> oLstCategoria= CCCategoria.consultarListaCategoriaPorIdRubro(((CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).getIdRubro());
                if(oLstCategoria!=null)
                {
                    cargarNodo(oLstCategoria,(((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent()))));
                }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
      
    }//GEN-LAST:event_jMenuItemEditarCategoriaActionPerformed

    private void jMenuItemEditarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarFamiliaActionPerformed
      DlgMantenimientoFamilia oDlgMantenimientoFamilia=new DlgMantenimientoFamilia(null,true,(CEFamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),((CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).toString(), ((CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject()).toString());
      oDlgMantenimientoFamilia.setLocationRelativeTo(null);
      oDlgMantenimientoFamilia.setVisible(true);
      List<CEFamilia> oLstFamilia= CCFamilia.consultarListaFamiliaPorIdCategoria(((CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).getIdCategoria());
                if(oLstFamilia!=null)
                {
                   cargarNodo(oLstFamilia,(((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent()))));
                }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
    }//GEN-LAST:event_jMenuItemEditarFamiliaActionPerformed

    private void jMenuItemAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarProductoActionPerformed
//    DlgMantenimientoProducto oDlgAgregarProducto=new DlgMantenimientoProducto(null,true,
//              (CESubfamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),
//              ((CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject()),
//              ((CEFamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).toString(),
//              ((CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject()).toString(),
//              ((CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject()).toString());
//      oDlgAgregarProducto.setLocationRelativeTo(null);
//      oDlgAgregarProducto.setVisible(true);
//      PnlConsulta.removeAll();
//      PnlConsulta.updateUI();
//       refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent());
    }//GEN-LAST:event_jMenuItemAgregarProductoActionPerformed

    private void jMenuItemEditarSubfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarSubfamiliaActionPerformed
DlgMantenimientosubFamilia oDlgMantenimientosubFamilia=new DlgMantenimientosubFamilia(null,true,
              (CESubfamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getLastPathComponent())).getUserObject(),
              ((CEFamilia)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject()).toString(),
              ((CECategoria)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject()).toString(),
              ((CERubro)((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject()).toString());
      oDlgMantenimientosubFamilia.setLocationRelativeTo(null);
      oDlgMantenimientosubFamilia.setVisible(true);

      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
    }//GEN-LAST:event_jMenuItemEditarSubfamiliaActionPerformed

    private void TxtCodigoFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCodigoFamiliaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCodigoFamiliaActionPerformed

    private void TxtCodigoSubfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCodigoSubfamiliaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCodigoSubfamiliaActionPerformed

    private void jMenuItemAgregarRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarRubroActionPerformed
      DlgMantenimientoRubro oDlgMantenimientoRubro=new DlgMantenimientoRubro(null,true,null,1);
      oDlgMantenimientoRubro.setLocationRelativeTo(null);
      oDlgMantenimientoRubro.setVisible(true);
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
      cargarRubro();
    }//GEN-LAST:event_jMenuItemAgregarRubroActionPerformed

    private void jMenuItemEliminarRubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarRubroActionPerformed
     int res=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar el Rubro?","Dialogo de Confirmación",JOptionPane.OK_OPTION);

     if(res==JOptionPane.OK_OPTION)
     {
       try
       {
        CCRubro.ABMRubro(3,(CERubro)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null,"No se puede eliminar el rubro");
       }
     }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
      cargarRubro();
    }//GEN-LAST:event_jMenuItemEliminarRubroActionPerformed

    private void jMenuItemEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarCategoriaActionPerformed
     int res=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar la categoria?","Dialogo de Confirmación",JOptionPane.OK_OPTION);
     if(res==JOptionPane.OK_OPTION)
     {
       try
       {
        CCCategoria.ABMCategoria(3,(CECategoria)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null,"No se puede eliminar la categoria");
       }
     }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
      DefaultMutableTreeNode o=(DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent();
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent());
      ((DefaultTreeModel)TreeProducto.getModel()).reload(o);
    }//GEN-LAST:event_jMenuItemEliminarCategoriaActionPerformed

    private void jMenuItemEliminarFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarFamiliaActionPerformed
      int res=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar la Familia?","Dialogo de Confirmación",JOptionPane.OK_OPTION);

     if(res==JOptionPane.OK_OPTION)
     {
       try
       {
        CCFamilia.ABMFamilia(3,(CEFamilia)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null,"No se puede eliminar la familia");
       }
     }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();

        DefaultMutableTreeNode o=(DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent();
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent());
      ((DefaultTreeModel)TreeProducto.getModel()).reload(o);
    }//GEN-LAST:event_jMenuItemEliminarFamiliaActionPerformed

    private void jMenuItemEliminarSubfamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarSubfamiliaActionPerformed
     int res=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar la Subfamilia?","Dialogo de Confirmación",JOptionPane.OK_OPTION);
     if(res==JOptionPane.OK_OPTION)
     {
       try
       {
        CCSubFamilia.ABMSubFamilia(3,(CESubfamilia)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
       }
       catch(Exception ex)
       {
        JOptionPane.showMessageDialog(null,"No se puede eliminar la subfamilia");
       }
     }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();
      DefaultMutableTreeNode o=(DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent();
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent());
      ((DefaultTreeModel)TreeProducto.getModel()).reload(o);
    }//GEN-LAST:event_jMenuItemEliminarSubfamiliaActionPerformed

    private void jMenuItemEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarProductoActionPerformed
     if(JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar el producto?","Mensaje de Confirmación", JOptionPane.OK_OPTION)==JOptionPane.OK_OPTION)
        {
            if(!CCProducto.eliminarProducto((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject()))
            {
               JOptionPane.showMessageDialog(null,"No se puede eliminar al producto");
            }
        }
      PnlConsulta.removeAll();
      PnlConsulta.updateUI();

        DefaultMutableTreeNode o=(DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent();
      refrescarTree((DefaultMutableTreeNode)ultimoNodoClic.getParentPath().getLastPathComponent());
      ((DefaultTreeModel)TreeProducto.getModel()).reload(o);
    }//GEN-LAST:event_jMenuItemEliminarProductoActionPerformed

    private void TxtMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtMarcaFocusGained

}//GEN-LAST:event_TxtMarcaFocusGained

    private void jMenuItemEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarProductoActionPerformed
//     String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
//     String categoria=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
//     String familia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
//     String subfamilia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
//     String marca=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
//     CEProducto oCEProducto=((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
//
//     DlgMantenimientoProducto oDlgMantenimientoProducto=new DlgMantenimientoProducto(null,true, oCEProducto, marca, subfamilia, familia, categoria, rubro);
//     oDlgMantenimientoProducto.setLocationRelativeTo(null);
//     oDlgMantenimientoProducto.setVisible(true);
//     cargarProducto((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject(),marca,subfamilia,familia,categoria,rubro);
    }//GEN-LAST:event_jMenuItemEditarProductoActionPerformed

    private void TxtUnidadMasComercialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtUnidadMasComercialFocusGained
     
    }//GEN-LAST:event_TxtUnidadMasComercialFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    CEProducto oCEProducto=((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
    DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos=new DlgMantenimientoPreciosDeProductos(null,true,oCEProducto);
    {
        oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
        oDlgMantenimientoPreciosDeProductos.setVisible(true);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CbxUnidadDePresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxUnidadDePresentacionActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_CbxUnidadDePresentacionActionPerformed

    private void BtnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarProductoActionPerformed
          String rubro=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
     String categoria=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
     String familia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
     String subfamilia=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getParentPath().getLastPathComponent())).getUserObject().toString();
     String marca=((DefaultMutableTreeNode)(ultimoNodoClic.getParentPath().getLastPathComponent())).getUserObject().toString();
     CEProducto oCEProducto=((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());

     DlgMantenimientoProducto oDlgMantenimientoProducto=new DlgMantenimientoProducto(null,true, oCEProducto, marca, subfamilia, familia, categoria, rubro);
     oDlgMantenimientoProducto.setLocationRelativeTo(null);
     oDlgMantenimientoProducto.setVisible(true);
cargarProducto((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject(),marca,subfamilia,familia,categoria,rubro);
    }//GEN-LAST:event_BtnModificarProductoActionPerformed

    private void jMenuItemModificarPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarPreciosActionPerformed
        CEProducto oCEProducto=((CEProducto)((DefaultMutableTreeNode)ultimoNodoClic.getLastPathComponent()).getUserObject());
    DlgMantenimientoPreciosDeProductos oDlgMantenimientoPreciosDeProductos=new DlgMantenimientoPreciosDeProductos(null,true,oCEProducto);
    {
        oDlgMantenimientoPreciosDeProductos.setLocationRelativeTo(null);
        oDlgMantenimientoPreciosDeProductos.setVisible(true);
    }
    }//GEN-LAST:event_jMenuItemModificarPreciosActionPerformed
    public void cargarCategoria(CECategoria oCECategoria, String Rubro)
    {
        TxtRubroCategoria.setText(Rubro);
        TxtCodigoCategoria.setText(oCECategoria.getCodigo());
        TxtDescripcionCategoria.setText(oCECategoria.getDescripcion());
    }
    public void cargarFamilia(CEFamilia oCEFamilia,String Categoria,String Rubro)
    {
        TxtCategoriaFamilia.setText(Categoria);
        TxtRubroFamilia.setText(Rubro);
        TxtCodigoFamilia.setText(oCEFamilia.getCodigo());
        TxtDescripcionFamilia.setText(oCEFamilia.getDescripcion());
    }
    public void cargarSubFamilia(CESubfamilia oCESubfamilia,String Familia,String Categoria,String Rubro)
    {
        TxtCategoriaSubfamilia.setText(Categoria);
        TxtRubroSubfamilia.setText(Rubro);
        TxtFamiliaSubfamilia.setText(Familia);
        TxtCodigoSubfamilia.setText(oCESubfamilia.getCodigo());
        TxtDescripcionSubfamilia.setText(oCESubfamilia.getDescripcion());
    }
    public void cargarProducto(CEProducto oCEProducto,String Marca,String SubFamilia,String Familia,String Categoria,String Rubro)
    {
        TxtCategoriaProducto.setText(Categoria);
        TxtRubroProducto.setText(Rubro);
        TxtFamiliaProducto.setText(Familia);
        TxtSubfamiliaProducto.setText(SubFamilia);
        LblCodigoProducto.setText(oCEProducto.getCodigo());
        TxtDescripcionProducto.setText(oCEProducto.getDescripcion());
        TxtMarca.setText(Marca);
        buscarUnidadBase(oCEProducto.getIdUnidadBase());
        buscarUnidadPresentacion(oCEProducto.getIdUnidadPresentacionVenta());
        cargarTablaEquivalencias(oCEProducto.getIdProducto());
        cargarTablaEquivalenciasPedidos(CCProducto.consultarUnidadMedidaProducto(oCEProducto.getIdProducto()));
        setImpuesto(oCEProducto.isSiNoImpuesto());
    }
    private void cargarTablaEquivalencias(long IdProducto)
    {
        int rowCount=TblEquivalencias.getRowCount();
        if(rowCount>0)
        {
            for(int i=0;i<rowCount;i++)
            {
               ((ArrayListTableModel)TblEquivalencias.getModel()).removeRow(0);
            }
        }
        List<CEEquivalenciaUnidad> oLstEquivalencias=CCProducto.consultarEquivalenciaPorProducto(IdProducto);
        if(oLstEquivalencias!=null)
        for(CEEquivalenciaUnidad oCEEquivalencia:oLstEquivalencias)
        {
        ArrayList oArrayList=new ArrayList();
        oArrayList.add(oCEEquivalencia.getCantidadPedido());
        oArrayList.add(buscarUnidad(oCEEquivalencia.getIdUnidadPedido()));
        oArrayList.add(oCEEquivalencia.getCantidadBase());
        oArrayList.add(buscarUnidad(oCEEquivalencia.getIdUnidadBase()));
        ((ArrayListTableModel)TblEquivalencias.getModel()).addRow(oArrayList);
        }
    }
    private CEUnidadMedidaVenta buscarUnidad(int IdUnidadMedida)
    {
        int itemCount=CbxUnidadBase.getItemCount();        
        for(int i=0;i<itemCount;i++)
        {
            if(((CEUnidadMedidaVenta)CbxUnidadBase.getItemAt(i)).getIdUnidadVentaMedida()==IdUnidadMedida)
            {
                return ((CEUnidadMedidaVenta)CbxUnidadBase.getItemAt(i));
            }
        }
        return null;
    }
    private void setImpuesto(boolean a)
    {
        if(a)
        {
            RbtSiImpuesto.setSelected(a);
        }
        else
        {
            RbtNoImpuesto.setSelected(true);
        }
    }
    private void buscarUnidadBase(int IdUnidadBase)
    {
        int itemCount=CbxUnidadBase.getItemCount();
        for(int i=0;i<itemCount;i++)
        {
            if(((CEUnidadMedidaVenta)CbxUnidadBase.getItemAt(i)).getIdUnidadVentaMedida()==IdUnidadBase)
            {
            CbxUnidadBase.setSelectedIndex(i);
            }
        }
    }
     private void buscarUnidadPresentacion(int IdUnidadPresentacionVenta)
       {
        int itemCount=CbxUnidadDePresentacion.getItemCount();
        for(int i=0;i<itemCount;i++)
        {
            if(((CEUnidadPresentacionVenta)CbxUnidadDePresentacion.getItemAt(i)).getIdUnidadPresentacionVenta()==IdUnidadPresentacionVenta)
            {
            CbxUnidadDePresentacion.setSelectedIndex(i);
            }
        }
        }
   public void cargarRubro(CERubro oCERubro)
    {
        TxtDescripcionRubro.setText(oCERubro.getDescripcion());
        TxtCodigoRubro.setText(oCERubro.getCodigo());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnModificarProducto;
    private javax.swing.JComboBox CbxUnidad;
    private javax.swing.JComboBox CbxUnidadBase;
    private javax.swing.JComboBox CbxUnidadDePresentacion;
    private javax.swing.JLabel LblCodigoProducto;
    private javax.swing.JLabel LblLeyendaRubro;
    private javax.swing.JLabel LblLeyendaRubro1;
    private javax.swing.JLabel LblLeyendaRubro2;
    private javax.swing.JLabel LblLeyendaRubro3;
    private javax.swing.JLabel LblLeyendaRubro4;
    private javax.swing.JLabel LblLeyendaRubro5;
    private javax.swing.JPanel PnlConsulta;
    private javax.swing.JPanel PnlFormularioCategoria;
    private javax.swing.JPanel PnlFormularioFamilia;
    private javax.swing.JPanel PnlFormularioRubro;
    private javax.swing.JPanel PnlFormularioSubfamilia;
    private javax.swing.JPanel PnlProducto;
    private javax.swing.JRadioButton RbtNoImpuesto;
    private javax.swing.JRadioButton RbtSiImpuesto;
    private javax.swing.JTable TblEquivalencias;
    private javax.swing.JTable TblPedidosEquivalencia;
    private javax.swing.JTree TreeProducto;
    private TextField.JTxtLetra TxtCategoriaFamilia;
    private javax.swing.JTextField TxtCategoriaProducto;
    private TextField.JTxtLetra TxtCategoriaSubfamilia;
    private TextField.JTxtNumero TxtCodigoCategoria;
    private TextField.JTxtNumero TxtCodigoFamilia;
    private TextField.JTxtNumero TxtCodigoRubro;
    private TextField.JTxtNumero TxtCodigoSubfamilia;
    private TextField.JTxtLetra TxtDescripcionCategoria;
    private TextField.JTxtLetra TxtDescripcionFamilia;
    private TextField.JTxtLetraNumero TxtDescripcionProducto;
    private TextField.JTxtLetra TxtDescripcionRubro;
    private TextField.JTxtLetra TxtDescripcionSubfamilia;
    private javax.swing.JTextField TxtFamiliaProducto;
    private TextField.JTxtLetra TxtFamiliaSubfamilia;
    private javax.swing.JTextField TxtMarca;
    private TextField.JTxtLetra TxtRubroCategoria;
    private TextField.JTxtLetra TxtRubroFamilia;
    private javax.swing.JTextField TxtRubroProducto;
    private TextField.JTxtLetra TxtRubroSubfamilia;
    private javax.swing.JTextField TxtSubfamiliaProducto;
    private javax.swing.JTextField TxtUnidadMasComercial;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItemAgregaNuevaFamilia;
    private javax.swing.JMenuItem jMenuItemAgregarNuevaCategoria;
    private javax.swing.JMenuItem jMenuItemAgregarProducto;
    private javax.swing.JMenuItem jMenuItemAgregarRubro;
    private javax.swing.JMenuItem jMenuItemAgregarSubFamilia;
    private javax.swing.JMenuItem jMenuItemEditarCategoria;
    private javax.swing.JMenuItem jMenuItemEditarFamilia;
    private javax.swing.JMenuItem jMenuItemEditarProducto;
    private javax.swing.JMenuItem jMenuItemEditarRubro;
    private javax.swing.JMenuItem jMenuItemEditarSubfamilia;
    private javax.swing.JMenuItem jMenuItemEliminarCategoria;
    private javax.swing.JMenuItem jMenuItemEliminarFamilia;
    private javax.swing.JMenuItem jMenuItemEliminarProducto;
    private javax.swing.JMenuItem jMenuItemEliminarRubro;
    private javax.swing.JMenuItem jMenuItemEliminarSubfamilia;
    private javax.swing.JMenuItem jMenuItemModificarPrecios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenuCategoria;
    private javax.swing.JPopupMenu jPopupMenuFamilia;
    private javax.swing.JPopupMenu jPopupMenuProducto;
    private javax.swing.JPopupMenu jPopupMenuRoot;
    private javax.swing.JPopupMenu jPopupMenuRubro;
    private javax.swing.JPopupMenu jPopupMenuSubfamilia;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private Label.Label label1;
    private Label.Label label1Categoria;
    private Label.Label label1Familia;
    private Label.Label label1Rubro;
    private Label.Label label2;
    private Label.Label label2Categoria;
    private Label.Label label2Familia;
    private Label.Label label2Rubro;
    private Label.Label label3;
    private Label.Label label3Categoria;
    private Label.Label label3Familia;
    private Label.Label label4;
    private Label.Label label5;
    private Label.Label label5Familia;
    // End of variables declaration//GEN-END:variables
 
    private void cargarTablaEquivalenciasPedidos(List<CEUnidadMedidaProducto> oCEUnidadMedidaProductos)
    {
        limpiarTabla(TblPedidosEquivalencia);
        for(int i=0;i<oCEUnidadMedidaProductos.size();i++)
        {
            Vector oVector=new Vector();
            oVector.add(oCEUnidadMedidaProductos.get(i).getDescripcion());
            oVector.add(oCEUnidadMedidaProductos.get(i));
            oVector.add(oCEUnidadMedidaProductos.get(i).getEquivalenciaConBase());
            oVector.add(oCEUnidadMedidaProductos.get(i).getNumUnidades());
            if(oCEUnidadMedidaProductos.get(i).getSiNoUnidadMasComercial()==1)
            {
                TxtUnidadMasComercial.setText(oCEUnidadMedidaProductos.get(i).getTipoUnidad());
            }
            ((DefaultTableModel)TblPedidosEquivalencia.getModel()).addRow(oVector);
        }

    }
    private void limpiarTabla(JTable oJTable)
    {
        int size=oJTable.getRowCount();

        for(int i=0;i<size;i++)
        {
            ((DefaultTableModel) TblPedidosEquivalencia.getModel()).removeRow(0);
        }
    }
}
