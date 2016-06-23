package view.almAlmacen;

import combobox.ArrayListComboBoxModel;
import controller.almAlmacen.CCProducto;
import controller.almAlmacen.CCUnidadMedidaVenta;
import controller.almAlmacen.CCUnidadPresentacionVenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.almAlmacen.entidad.CECategoria;
import modelo.almAlmacen.entidad.CEEquivalenciaUnidad;
import modelo.almAlmacen.entidad.CEMarca;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CESubfamilia;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import modelo.almAlmacen.entidad.CEUnidadMedidaVenta;
import modelo.almAlmacen.entidad.CEUnidadPresentacionVenta;
import table.ArrayListTableModel;
import util.clases.almAlmacen.CLEquivalenciaABM;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;

public class DlgMantenimientoProducto extends DialogoPadre {

    CESubfamilia oCESubfamilia;
    CECategoria oCECategoria;
    CEMarca oCEMarca;
    int selectedRow = -1;
    CEProducto oCEProducto;
    List<CLEquivalenciaABM> oLstEquivalenciaABM = new ArrayList<CLEquivalenciaABM>();
    List<CEUnidadMedidaProducto> oLstUnidadMedidaVenta = new ArrayList<CEUnidadMedidaProducto>();
    int pIdOperacion = 0;

    public DlgMantenimientoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTipoUnidadBase();
        cargarTipoUnidadPresentacion();
        configurarTabla();
        pIdOperacion = 1;
    }

    public DlgMantenimientoProducto(java.awt.Frame parent, boolean modal, CESubfamilia oCESubfamilia, CECategoria oCECategoria, String Familia, String Categoria, String Rubro) {
        super(parent, modal);
        initComponents();
        this.oCESubfamilia = oCESubfamilia;
        cargarTipoUnidadBase();
        cargarTipoUnidadPresentacion();
        TxtCategoria.setText(Categoria);
        TxtRubro.setText(Rubro);
        TxtFamilia.setText(Familia);
        TxtSubfamilia.setText(oCESubfamilia.getDescripcion());
        configurarTabla();
        this.oCECategoria = oCECategoria;
        ArrayList oArrayList = new ArrayList();
        oArrayList.add(1);
        oArrayList.add(null);
        oArrayList.add(1);
        oArrayList.add(null);
        oArrayList.add(1);
        oArrayList.add(null);
        ((ArrayListTableModel) TblEquivalencias.getModel()).addRow(oArrayList);
        cargarEventoTablaGrupos(TblEquivalencias);
        cargarEventoTablaPedido(TblPedidosEquivalencia);
        pIdOperacion = 1;
    }

    public DlgMantenimientoProducto(java.awt.Frame parent, boolean modal, CEProducto oCEProducto, String Marca, String SubFamilia, String Familia, String Categoria, String Rubro) {
        super(parent, modal);
        initComponents();
        cargarTipoUnidadBase();
        cargarTipoUnidadPresentacion();
        configurarTabla();
        pIdOperacion = 2;
        TxtCategoria.setText(Categoria);
        TxtRubro.setText(Rubro);
        TxtFamilia.setText(Familia);
        TxtSubfamilia.setText(SubFamilia);
        TxtDescripcion.setText(oCEProducto.getDescripcion());
        this.oCEMarca = new CEMarca();
        oCEMarca.setIdMarca(oCEProducto.getIdMarca());
        TxtMarca.setText(Marca);
        buscarUnidadBase(oCEProducto.getIdUnidadBase());
        buscarUnidadPresentacion(oCEProducto.getIdUnidadPresentacionVenta());
        cargarTablaEquivalencias(oCEProducto.getIdProducto());
        setImpuesto(oCEProducto.isSiNoImpuesto());
        this.oCEProducto = oCEProducto;
        this.oCEMarca = new CEMarca();
        oCEMarca.setIdMarca(oCEProducto.getIdMarca());
        this.oCEProducto.setoCEUnidadMedidaProducto(CCProducto.consultarUnidadMedidaProducto(oCEProducto.getIdProducto()));
        if (TblEquivalencias.getRowCount() == 0) {
            ArrayList oArrayList = new ArrayList();
            oArrayList.add(1);
            oArrayList.add((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem());
            oArrayList.add(1);
            oArrayList.add((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem());
            oArrayList.add(1);
            oArrayList.add(null);
            ((ArrayListTableModel) TblEquivalencias.getModel()).addRow(oArrayList);
            procesarListaDeEquivalenciaAListaDeUnidadMedidaProducto();
        } else {
            cargarTablaEquivalenciasPedidos(CCProducto.consultarUnidadMedidaProducto(oCEProducto.getIdProducto()));
        }
        cargarEventoTablaGrupos(TblEquivalencias);
        cargarEventoTablaPedido(TblPedidosEquivalencia);

    }

    private void cargarTablaEquivalenciasPedidos(List<CEUnidadMedidaProducto> oCEUnidadMedidaProductos) {
        for (int i = 0; i < oCEUnidadMedidaProductos.size(); i++) {
            Vector oVector = new Vector();
            oCEUnidadMedidaProductos.get(i).setDescripcion(oCEUnidadMedidaProductos.get(i).getDescripcion());
            oCEUnidadMedidaProductos.get(i).setCorrelativo(i + 1);
            oVector.add(oCEUnidadMedidaProductos.get(i).getDescripcion());
            oVector.add(oCEUnidadMedidaProductos.get(i));
            oVector.add(oCEUnidadMedidaProductos.get(i).getEquivalenciaConBase());
            oVector.add(oCEUnidadMedidaProductos.get(i).getNumUnidades());
            ((DefaultTableModel) TblPedidosEquivalencia.getModel()).addRow(oVector);
        }
        CbxUnidadMedidaComercial.setModel(new DefaultComboBoxModel(oCEUnidadMedidaProductos.toArray()));
        CbxUnidadMedidaComercial.setSelectedIndex(0);
    }

    private void procesarListaDeEquivalenciaAListaDeUnidadMedidaProducto() {
        if ((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem() != null) {
            int IdUnidadMedidaBase = ((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem()).getIdUnidadVentaMedida();
            Vector<CEUnidadMedidaProducto> oVctUnidadMedidaProducto = new Vector<CEUnidadMedidaProducto>();
            CEUnidadMedidaProducto oCEUnidadMedidaProducto = new CEUnidadMedidaProducto();
            oCEUnidadMedidaProducto.setIdUnidadMedidaVenta(IdUnidadMedidaBase);
            oCEUnidadMedidaProducto.setDescripcion("Umb");
            oCEUnidadMedidaProducto.setCorrelativo(1);
            oCEUnidadMedidaProducto.setEquivalenciaConBase(1);
            oCEUnidadMedidaProducto.setIdProducto(0);
            oCEUnidadMedidaProducto.setSiNoUnidadBase(1);
            oCEUnidadMedidaProducto.setTipoUnidad(((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem()).toString());
            oVctUnidadMedidaProducto.add(oCEUnidadMedidaProducto);

            for (int i = 1; i <= TblEquivalencias.getRowCount() - 1; i++) {
                CEUnidadMedidaVenta oCEUnidadMedidaVenta1 = (CEUnidadMedidaVenta) TblEquivalencias.getValueAt(i, 3);
                if (oCEUnidadMedidaVenta1 != null) {
                    float valueCant = Float.parseFloat(TblEquivalencias.getValueAt(i, 0).toString());
                    if (valueCant != 0) {
                        boolean value = false;
                        for (CEUnidadMedidaProducto oCEUnidadMedidaProducto3 : oVctUnidadMedidaProducto) {
                            if (oCEUnidadMedidaProducto3.getIdUnidadMedidaVenta() == oCEUnidadMedidaVenta1.getIdUnidadVentaMedida()) {
                                value = true;
                            }
                        }
                        if (!value) {
                            CEUnidadMedidaProducto oCEUnidadMedidaProductoTemp = new CEUnidadMedidaProducto();
                            oCEUnidadMedidaProductoTemp.setIdUnidadMedidaVenta(oCEUnidadMedidaVenta1.getIdUnidadVentaMedida());
                            oCEUnidadMedidaProductoTemp.setDescripcion("Ump");
                            oCEUnidadMedidaProductoTemp.setCorrelativo(0);
                            oCEUnidadMedidaProductoTemp.setEquivalenciaConBase(valueCant);
                            oCEUnidadMedidaProductoTemp.setIdProducto(0);
                            oCEUnidadMedidaProductoTemp.setSiNoUnidadBase(0);
                            oCEUnidadMedidaProductoTemp.setTipoUnidad(oCEUnidadMedidaVenta1.toString());
                            oVctUnidadMedidaProducto.add(oCEUnidadMedidaProductoTemp);
                        }
                    }
                }
            }
            cargarTablaEquivalenciasPedidos(oVctUnidadMedidaProducto);
        }
    }

    private void cargarTablaEquivalenciasPedidos(Vector<CEUnidadMedidaProducto> oCEUnidadMedidaProductos) {
        limpiarTabla(TblPedidosEquivalencia);
        ordenarListaUnidadPedido(oCEUnidadMedidaProductos);
        for (int i = 0; i < oCEUnidadMedidaProductos.size(); i++) {
            Vector oVector = new Vector();
            if (oCEUnidadMedidaProductos.get(i).getSiNoUnidadBase() != 1) {
                oCEUnidadMedidaProductos.get(i).setDescripcion("Ump" + (i + 1));
            } else {
                oCEUnidadMedidaProductos.get(i).setDescripcion("Umb");
            }
            oCEUnidadMedidaProductos.get(i).setCorrelativo(i + 1);
            oVector.add(oCEUnidadMedidaProductos.get(i).getDescripcion());
            oVector.add(oCEUnidadMedidaProductos.get(i));
            oVector.add(oCEUnidadMedidaProductos.get(i).getEquivalenciaConBase());
            oVector.add("1");
            ((DefaultTableModel) TblPedidosEquivalencia.getModel()).addRow(oVector);
        }
        for (int i = 1; i <= TblPedidosEquivalencia.getRowCount() - 1; i++) {
            float value = Float.parseFloat(TblPedidosEquivalencia.getValueAt(0, 3).toString());
            float num = Float.parseFloat(TblPedidosEquivalencia.getValueAt(i, 2).toString());
            TblPedidosEquivalencia.setValueAt(value * num, i, 3);
        }
        CbxUnidadMedidaComercial.setModel(new DefaultComboBoxModel(oCEUnidadMedidaProductos));
        CbxUnidadMedidaComercial.setSelectedIndex(0);
    }

    private void ordenarListaUnidadPedido(Vector<CEUnidadMedidaProducto> oVctUnidadMedidaProducto) {
        for (int i = 0; i < oVctUnidadMedidaProducto.size(); i++) {

            for (int a = 0; a < oVctUnidadMedidaProducto.size() - 1; a++) {
                CEUnidadMedidaProducto oCEUnidadMedidaProducto = oVctUnidadMedidaProducto.get(a);
                CEUnidadMedidaProducto oCEUnidadMedidaProductoT = oVctUnidadMedidaProducto.get(a + 1);

                if (oCEUnidadMedidaProducto.getEquivalenciaConBase() > oCEUnidadMedidaProductoT.getEquivalenciaConBase()) {
                    oVctUnidadMedidaProducto.setElementAt(oCEUnidadMedidaProducto, a + 1);
                    oVctUnidadMedidaProducto.setElementAt(oCEUnidadMedidaProductoT, a);
                }

            }
        }
        reordenarListaUnidadPedido(oVctUnidadMedidaProducto);
    }

    private void reordenarListaUnidadPedido(Vector<CEUnidadMedidaProducto> oVctUnidadMedidaProducto) {
        if (oVctUnidadMedidaProducto.size() > 1) {
            CEUnidadMedidaProducto oCEUnidadMedidaProducto = null;
            for (int a = 0; a < oVctUnidadMedidaProducto.size(); a++) {
                oCEUnidadMedidaProducto = oVctUnidadMedidaProducto.get(a);
                if (oCEUnidadMedidaProducto.getSiNoUnidadBase() == 1) {
                    oVctUnidadMedidaProducto.remove(a);
                    break;
                }
            }
            oVctUnidadMedidaProducto.add(0, oCEUnidadMedidaProducto);
        }
    }

    private void limpiarTabla(JTable oJTable) {
        int size = oJTable.getRowCount();

        for (int i = 0; i < size; i++) {
            if (oJTable.getModel() instanceof DefaultTableModel) {
                ((DefaultTableModel) oJTable.getModel()).removeRow(0);
            } else {
                ((ArrayListTableModel) oJTable.getModel()).removeRow(0);
            }
        }
    }

    private void setImpuesto(boolean a) {
        if (a) {
            RbtSiImpuesto.setSelected(a);
        } else {
            RbtNoImpuesto.setSelected(true);
        }
    }

    private void cargarTablaEquivalencias(long IdProducto) {
        int rowCount = TblEquivalencias.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            ((ArrayListTableModel) TblEquivalencias.getModel()).removeRow(0);
            rowCount--;
        }
        List<CEEquivalenciaUnidad> oLstEquivalencias = CCProducto.consultarEquivalenciaPorProducto(IdProducto);
        if (oLstEquivalencias != null) {
            for (CEEquivalenciaUnidad oCEEquivalencia : oLstEquivalencias) {
                ArrayList oArrayList = new ArrayList();

                oArrayList.add(oCEEquivalencia.getCantidadPedido());
                oArrayList.add(buscarUnidad(oCEEquivalencia.getIdUnidadPedido()));
                oArrayList.add(oCEEquivalencia.getCantidadBase());
                oArrayList.add(buscarUnidad(oCEEquivalencia.getIdUnidadBase()));
                oArrayList.add(0);
                oArrayList.add(oCEEquivalencia);
                ((ArrayListTableModel) TblEquivalencias.getModel()).addRow(oArrayList);
            }
        }
    }

    private CEUnidadMedidaVenta buscarUnidad(int IdUnidadMedida) {
        int itemCount = CbxUnidadBase.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (((CEUnidadMedidaVenta) CbxUnidadBase.getItemAt(i)).getIdUnidadVentaMedida() == IdUnidadMedida) {
                return ((CEUnidadMedidaVenta) CbxUnidadBase.getItemAt(i));
            }
        }
        return null;
    }

    private void buscarUnidadBase(int IdUnidadBase) {
        int itemCount = CbxUnidadBase.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (((CEUnidadMedidaVenta) CbxUnidadBase.getItemAt(i)).getIdUnidadVentaMedida() == IdUnidadBase) {
                CbxUnidadBase.setSelectedIndex(i);
            }
        }
    }

    private void buscarUnidadPresentacion(int IdUnidadPresentacionVenta) {
        int itemCount = CbxUnidadDePresentacion.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (((CEUnidadPresentacionVenta) CbxUnidadDePresentacion.getItemAt(i)).getIdUnidadPresentacionVenta() == IdUnidadPresentacionVenta) {
                CbxUnidadDePresentacion.setSelectedIndex(i);
            }
        }
    }

    private void configurarTabla() {
        TblPedidosEquivalencia.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            }
        });
        TblEquivalencias.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            }
        });
        TblPedidosEquivalencia.getColumnModel().getColumn(0).setMinWidth(40);
        TblPedidosEquivalencia.getColumnModel().getColumn(0).setMaxWidth(40);
        TblPedidosEquivalencia.getColumnModel().getColumn(1).setMinWidth(90);
        TblPedidosEquivalencia.getColumnModel().getColumn(1).setMaxWidth(90);
        TblPedidosEquivalencia.getColumnModel().getColumn(2).setMinWidth(50);
        TblPedidosEquivalencia.getColumnModel().getColumn(2).setMaxWidth(50);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setMinWidth(50);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setMaxWidth(50);
        ((JTableX) TblEquivalencias).setSelectAllForEdit(true);
        ((JTableX) TblPedidosEquivalencia).setSelectAllForEdit(true);
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero", 0, jTextFieldNumber));
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TblEquivalencias.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        TblEquivalencias.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblEquivalencias.getColumnModel().getColumn(0).setMinWidth(40);
        TblEquivalencias.getColumnModel().getColumn(0).setMaxWidth(40);
        TblEquivalencias.getColumnModel().getColumn(2).setMinWidth(40);
        TblEquivalencias.getColumnModel().getColumn(2).setMaxWidth(40);
        TblEquivalencias.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(CbxUnidad) {

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                JComboBox combo = (JComboBox) getComponent();
                construirModeloCombo(combo, new ArrayList());
                //int a=CbxUnidadBase.getSelectedIndex();
                ArrayList oArrayList = new ArrayList();
                for (int i = 0; i < CbxUnidadBase.getItemCount(); i++) {
                    CEUnidadMedidaVenta oCEUnidadMedidaVenta = (CEUnidadMedidaVenta) CbxUnidadBase.getItemAt(i);
                    boolean valu = false;
                    for (int a = 0; a < TblEquivalencias.getRowCount(); a++) {
                        CEUnidadMedidaVenta oCEUnidadMedid = (CEUnidadMedidaVenta) TblEquivalencias.getValueAt(a, 3);
                        if (oCEUnidadMedid != null) {
                            if (a != row) {
                                if (oCEUnidadMedidaVenta.getIdUnidadVentaMedida() == oCEUnidadMedid.getIdUnidadVentaMedida()) {
                                    valu = true;
                                }
                            }


                        }
                    }
                    if (!valu) {
                        oArrayList.add(oCEUnidadMedidaVenta);
                    }

                }
                construirModeloCombo(combo, oArrayList);
                return combo;
            }
        });
        TblEquivalencias.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(CbxUnidad));
        TblEquivalencias.getColumnModel().getColumn(4).setMinWidth(0);
        TblEquivalencias.getColumnModel().getColumn(4).setMaxWidth(0);
        TblEquivalencias.getColumnModel().getColumn(5).setMinWidth(0);
        TblEquivalencias.getColumnModel().getColumn(5).setMaxWidth(0);

    }

    private void cargarEventoTablaGrupos(final JTable tbl) {
        ((ArrayListTableModel) tbl.getModel()).addTableModelListener(
                new TableModelListener() {

                    public void tableChanged(TableModelEvent tme) {
                        {
                            eventoTablaGrupos(tme, tbl);

                        }
                    }
                });

    }

    private void cargarEventoTablaPedido(final JTable tbl) {
        ((DefaultTableModel) tbl.getModel()).addTableModelListener(
                new TableModelListener() {

                    public void tableChanged(TableModelEvent tme) {
                        {
                            if (tme.getType() == TableModelEvent.UPDATE) {
                                if (tme.getColumn() == 3 && tme.getFirstRow() == 0) {
                                    for (int i = 1; i <= TblPedidosEquivalencia.getRowCount() - 1; i++) {
                                        float value = Float.parseFloat(TblPedidosEquivalencia.getValueAt(tme.getLastRow(), 3).toString());
                                        float num = Float.parseFloat(TblPedidosEquivalencia.getValueAt(i, 2).toString());
                                        TblPedidosEquivalencia.setValueAt(value * num, i, 3);
                                    }
                                }
                            }

                        }
                    }
                });

    }

    private void eventoTablaGrupos(TableModelEvent tme, JTable source) {
        procesarListaDeEquivalenciaAListaDeUnidadMedidaProducto();


        if (pIdOperacion != 1) {
            if (tme.getType() == TableModelEvent.UPDATE) {
                if (tme.getColumn() != 4) {
                    int rowUpdated = tme.getLastRow();
                    if (rowUpdated != -1) {
                        if (source.getValueAt(rowUpdated, 5) instanceof CEEquivalenciaUnidad) {
                            source.setValueAt(2, rowUpdated, 4);
                        }
                    }
                }
            }
            if (tme.getType() == TableModelEvent.INSERT) {
                source.setValueAt(1, source.getRowCount() - 1, 4);
            }
        }

    }

    private void cargarTipoUnidadBase() {
        List<CEUnidadMedidaVenta> oListUnidadMedidaVenta = CCUnidadMedidaVenta.consultarListaUnidadMedidaVentas();
        construirModeloCombo(CbxUnidadBase, (ArrayList) oListUnidadMedidaVenta);
        construirModeloCombo(CbxUnidad, (ArrayList) oListUnidadMedidaVenta);
        if (CbxUnidad.getItemCount() > 0) {
            CbxUnidadBase.setSelectedIndex(0);
        }
    }

    private void cargarTipoUnidadPresentacion() {
        List<CEUnidadPresentacionVenta> oListUnidadMedidaVenta = CCUnidadPresentacionVenta.consultarListaUnidadPresentacionVentas();
        construirModeloCombo(CbxUnidadDePresentacion, (ArrayList) oListUnidadMedidaVenta);
        if (CbxUnidadDePresentacion.getItemCount() > 0) {
            CbxUnidadDePresentacion.setSelectedIndex(0);
        }
    }

    public void construirModeloCombo(JComboBox oBox, ArrayList oLista) {
        ArrayListComboBoxModel model = new ArrayListComboBoxModel(oLista);
        oBox.setModel(model);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jTextFieldNumber = new javax.swing.JTextField();
        jPopupMenuEquivalencias = new javax.swing.JPopupMenu();
        jMenuItemAgregarEquivalencia = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblEquivalencias = new JTableX()
        {

            @Override
            public void setValueAt(Object value,int row,int column)
            {
                if(column==0)
                {
                    if(value.equals(""))
                    {
                        super.setValueAt("1", row, column);
                    }
                    else
                    {
                        super.setValueAt(CLRedondear.Redondear(Float.parseFloat(value.toString()),2), row, column);
                    }
                }
                else
                {
                    super.setValueAt(value, row, column);
                }
            }
        }

        ;
        jLabel11 = new javax.swing.JLabel();
        RbtSiImpuesto = new javax.swing.JRadioButton();
        RbtNoImpuesto = new javax.swing.JRadioButton();
        BtnAgregarEquivalencia = new javax.swing.JButton();
        BtnEliminarEquivalencia = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TxtSubfamilia = new javax.swing.JTextField();
        TxtRubro = new javax.swing.JTextField();
        TxtCategoria = new javax.swing.JTextField();
        TxtFamilia = new javax.swing.JTextField();
        TxtMarca = new javax.swing.JTextField();
        TxtDescripcion = new TextField.JTxtLetraNumero();
        jLabel12 = new javax.swing.JLabel();
        CbxUnidadBase = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPedidosEquivalencia = new JTableX()
        {
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                if(column==3)
                {
                    if(value.equals(""))
                    {
                        super.setValueAt("1", row, column);
                    }
                    else
                    {
                        super.setValueAt(CLRedondear.Redondear(Float.parseFloat(value.toString()),2), row, column);
                    }
                }
                else
                {
                    super.setValueAt(value, row, column);
                }
            }
        }
        ;
        jLabel13 = new javax.swing.JLabel();
        CbxUnidadMedidaComercial = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        CbxUnidadDePresentacion = new javax.swing.JComboBox();
        BtnAgregarUnidadBase = new javax.swing.JButton();
        BtnAgregarEquivalencia2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        RbtSiImpuesto1 = new javax.swing.JRadioButton();
        RbtNoImpuesto1 = new javax.swing.JRadioButton();

        CbxUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        jMenuItemAgregarEquivalencia.setText("Agregar Unidad");
        jMenuItemAgregarEquivalencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgregarEquivalenciaActionPerformed(evt);
            }
        });
        jPopupMenuEquivalencias.add(jMenuItemAgregarEquivalencia);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setForeground(new java.awt.Color(153, 0, 0));
        jLabel2.setText("Categoria    :");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("Familia :");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("Subfamilia  :");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("Marca  :");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Descripción:");

        TblEquivalencias.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Cant.","Und.Base","Equiv.","Und.Ped.","ABM","Objeto"
            }
        )
        {
            Class[] types = new Class [] {
                java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Float.class,
                java.lang.Object.class,
                java.lang.Integer.class,
                java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true,false,false,true,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(rowIndex==0)
                {
                    return false;
                }
                return canEdit [columnIndex];
            }
        });
        TblEquivalencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblEquivalencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblEquivalenciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblEquivalencias);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("¿Impuesto (IGV)?");

        buttonGroup1.add(RbtSiImpuesto);
        RbtSiImpuesto.setText("Sí");

        buttonGroup1.add(RbtNoImpuesto);
        RbtNoImpuesto.setText("No");

        BtnAgregarEquivalencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Agregar2.png"))); // NOI18N
        BtnAgregarEquivalencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarEquivalenciaActionPerformed(evt);
            }
        });

        BtnEliminarEquivalencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Eliminar.gif"))); // NOI18N
        BtnEliminarEquivalencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarEquivalenciaActionPerformed(evt);
            }
        });

        BtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Cancel.png"))); // NOI18N
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Guardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Rubro           :");

        TxtSubfamilia.setEditable(false);

        TxtRubro.setEditable(false);

        TxtCategoria.setEditable(false);

        TxtFamilia.setEditable(false);

        TxtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMarcaActionPerformed(evt);
            }
        });
        TxtMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtMarcaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtMarcaFocusLost(evt);
            }
        });
        TxtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtMarcaKeyReleased(evt);
            }
        });

        TxtDescripcion.setText("jTxtLetraNumero1");
        TxtDescripcion.setObligatorio(false);
        TxtDescripcion.setTamanio(300);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel12.setForeground(new java.awt.Color(153, 0, 0));
        jLabel12.setText("Unidad Base");

        CbxUnidadBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxUnidadBaseActionPerformed(evt);
            }
        });

        TblPedidosEquivalencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Unidad", "Equiv.", "Unid."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(rowIndex==0)
                {
                    if(columnIndex==3)
                    {
                        return true;
                    }
                }
                return false;
            }
        });
        TblPedidosEquivalencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPedidosEquivalencia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(TblPedidosEquivalencia);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setText("U + C");

        CbxUnidadMedidaComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxUnidadMedidaComercialActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel14.setForeground(new java.awt.Color(153, 0, 0));
        jLabel14.setText("Unidad de Presentación:");

        CbxUnidadDePresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxUnidadDePresentacionActionPerformed(evt);
            }
        });

        BtnAgregarUnidadBase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnAgregarUnidadBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarUnidadBaseActionPerformed(evt);
            }
        });

        BtnAgregarEquivalencia2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Editar.png"))); // NOI18N
        BtnAgregarEquivalencia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarEquivalencia2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel15.setForeground(new java.awt.Color(153, 0, 0));
        jLabel15.setText("¿Percepción?");

        buttonGroup2.add(RbtSiImpuesto1);
        RbtSiImpuesto1.setText("Sí");

        buttonGroup2.add(RbtNoImpuesto1);
        RbtNoImpuesto1.setSelected(true);
        RbtNoImpuesto1.setText("No");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtSubfamilia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtRubro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtFamilia))))
                            .addComponent(TxtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CbxUnidadBase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CbxUnidadMedidaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAgregarUnidadBase, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CbxUnidadDePresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAgregarEquivalencia2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnAgregarEquivalencia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnEliminarEquivalencia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RbtSiImpuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RbtNoImpuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RbtSiImpuesto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RbtNoImpuesto1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(BtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(TxtFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtSubfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbxUnidadBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CbxUnidadDePresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnAgregarUnidadBase)
                    .addComponent(BtnAgregarEquivalencia2))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(CbxUnidadMedidaComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BtnEliminarEquivalencia)
                        .addComponent(BtnAgregarEquivalencia)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(RbtSiImpuesto)
                            .addComponent(RbtNoImpuesto)
                            .addComponent(jLabel15)
                            .addComponent(RbtSiImpuesto1)
                            .addComponent(RbtNoImpuesto1))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarEquivalenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarEquivalenciaActionPerformed
        if (CbxUnidadBase.getSelectedIndex() != -1) {
            ArrayList oArrayList = new ArrayList();
            oArrayList.add(1);
            oArrayList.add((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem());
            oArrayList.add(1);
            oArrayList.add(null);
            oArrayList.add(1);
            oArrayList.add(null);
            ((ArrayListTableModel) TblEquivalencias.getModel()).addRow(oArrayList);
        } else {
            JOptionPane.showMessageDialog(null, "Debe elegir antes la unidad base del producto");
        }

    }//GEN-LAST:event_BtnAgregarEquivalenciaActionPerformed

    private void BtnEliminarEquivalenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarEquivalenciaActionPerformed
        int rowSelected = TblEquivalencias.getSelectedRow();
        if (rowSelected != -1 && rowSelected != 0) {
            CLEquivalenciaABM oCLEquivalenciaABM = new CLEquivalenciaABM();
            if ((CEEquivalenciaUnidad) TblEquivalencias.getValueAt(rowSelected, 5) != null) {
                oCLEquivalenciaABM.setAbm(3);
                oCLEquivalenciaABM.setoCEEquivalencia((CEEquivalenciaUnidad) TblEquivalencias.getValueAt(rowSelected, 5));
                oLstEquivalenciaABM.add(oCLEquivalenciaABM);
            }
            ((ArrayListTableModel) TblEquivalencias.getModel()).removeRow(rowSelected);
        }
    }//GEN-LAST:event_BtnEliminarEquivalenciaActionPerformed

    private void TxtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMarcaActionPerformed
        String marca = TxtMarca.getText();
        DlgBusquedaMarca oDlgBusquedaMarca = new DlgBusquedaMarca(null, true, marca);
        oDlgBusquedaMarca.setLocationRelativeTo(null);
        oDlgBusquedaMarca.setVisible(true);
        oCEMarca = oDlgBusquedaMarca.oCEMarca;
        if (oCEMarca != null) {
            TxtMarca.setText(oCEMarca.getDescripcion());
        }
        TxtDescripcion.requestFocus();
    }//GEN-LAST:event_TxtMarcaActionPerformed

    private void TxtMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtMarcaFocusGained
        TxtMarca.setBackground(new Color(255, 255, 204));
    }//GEN-LAST:event_TxtMarcaFocusGained

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if (validarCantidadEquivalencias()) {

            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de registrar el producto?", "Mensaje de Confirmación", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                if (!TxtDescripcion.getText().trim().equals("")) {
                    limpiarTablaDeEquivalenciasSinDatos();
                    limpiarTablaDeEquivalenciasConUnidadBaseDuplicada();
                    if (pIdOperacion == 1) {

                        if (oCEMarca == null) {
                            JOptionPane.showMessageDialog(null, "Elija una marca");
                            TxtMarca.setBackground(new Color(255, 255, 204));
                            TxtMarca.requestFocus();
                        } else {
                            if (CCProducto.registrarProducto(getProducto())) {
                                dispose();
                            } else {
                                TxtMarca.setBackground(Color.white);
                                JOptionPane.showMessageDialog(null, "<html>Operación Fallida<br>Posiblemente ya haya definido precios para las unidades</html>");
                            }
                        }
                    } else {
                        if (oCEMarca == null) {
                            JOptionPane.showMessageDialog(null, "Elija una marca");
                            TxtMarca.setBackground(new Color(255, 255, 204));
                            TxtMarca.requestFocus();
                        } else {

                            int rowCount = TblEquivalencias.getRowCount();
                            for (int i = 0; i < rowCount; i++) {
                                if (Integer.parseInt(TblEquivalencias.getValueAt(i, 4).toString()) == 1 || Integer.parseInt(TblEquivalencias.getValueAt(i, 4).toString()) == 2) {
                                    CEEquivalenciaUnidad oCEEquivalencia = new CEEquivalenciaUnidad();
                                    oCEEquivalencia.setCantidadBase(Float.parseFloat(TblEquivalencias.getValueAt(i, 2).toString()));
                                    oCEEquivalencia.setIdUnidadBase(((CEUnidadMedidaVenta) (TblEquivalencias.getValueAt(i, 3))).getIdUnidadVentaMedida());
                                    oCEEquivalencia.setCantidadPedido(Float.parseFloat(TblEquivalencias.getValueAt(i, 0).toString()));
                                    oCEEquivalencia.setIdUnidadPedido(((CEUnidadMedidaVenta) (TblEquivalencias.getValueAt(i, 1))).getIdUnidadVentaMedida());
                                    if (TblEquivalencias.getValueAt(i, 5) instanceof CEEquivalenciaUnidad) {
                                        oCEEquivalencia.setIdEquivalenciaUnidad(((CEEquivalenciaUnidad) (TblEquivalencias.getValueAt(i, 5))).getIdEquivalenciaUnidad());
                                    }
                                    CLEquivalenciaABM oCLEquivalenciaABM = new CLEquivalenciaABM();
                                    oCLEquivalenciaABM.setoCEEquivalencia(oCEEquivalencia);
                                    oCLEquivalenciaABM.setAbm(Integer.parseInt(TblEquivalencias.getValueAt(i, 4).toString()));
                                    oLstEquivalenciaABM.add(oCLEquivalenciaABM);
                                }

                            }
                            List<CEUnidadMedidaProducto> oCEUnidadMedidaProductoABM = new ArrayList<CEUnidadMedidaProducto>();
                            int rowCount2 = TblPedidosEquivalencia.getRowCount();
                            for (int i = 0; i < rowCount2; i++) {
                                CEUnidadMedidaProducto oCEUnidadMedidaProducto = (CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(i, 1);
                                boolean vlu = true;
                                for (CEUnidadMedidaProducto oCEUnidadMedidaProducto1 : this.oCEProducto.getoCEUnidadMedidaProducto()) {
                                    if (oCEUnidadMedidaProducto.getIdUnidadMedidaVenta() == oCEUnidadMedidaProducto1.getIdUnidadMedidaVenta()) {
                                        oCEUnidadMedidaProducto.setAbm(2);
                                        oCEUnidadMedidaProducto.setIdProducto(oCEProducto.getIdProducto());
                                        oCEUnidadMedidaProducto.setIdUnidadMedidaProducto(oCEUnidadMedidaProducto1.getIdUnidadMedidaProducto());
                                        float numUnidades = Float.parseFloat(TblPedidosEquivalencia.getValueAt(i, 3).toString());
                                        oCEUnidadMedidaProducto.setNumUnidades(numUnidades);
                                        oCEUnidadMedidaProductoABM.add(oCEUnidadMedidaProducto);
                                        vlu = false;
                                    }
                                }
                                if (vlu) {
                                    oCEUnidadMedidaProducto.setIdProducto(oCEProducto.getIdProducto());
                                    oCEUnidadMedidaProducto.setAbm(1);
                                    float numUnidades = Float.parseFloat(TblPedidosEquivalencia.getValueAt(i, 3).toString());
                                    oCEUnidadMedidaProducto.setNumUnidades(numUnidades);
                                    oCEUnidadMedidaProductoABM.add(oCEUnidadMedidaProducto);
                                }
                            }
                            int rowCount3 = TblPedidosEquivalencia.getRowCount();

                            for (CEUnidadMedidaProducto oCEUnidadMedidaProducto1 : this.oCEProducto.getoCEUnidadMedidaProducto()) {

                                boolean vlu2 = true;
                                for (int i = 0; i < rowCount3; i++) {
                                    CEUnidadMedidaProducto oCEUnidadMedidaProducto = (CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(i, 1);
                                    if (oCEUnidadMedidaProducto.getIdUnidadMedidaVenta() == oCEUnidadMedidaProducto1.getIdUnidadMedidaVenta()) {
                                        vlu2 = false;
                                    }
                                }
                                if (vlu2) {
                                    oCEUnidadMedidaProducto1.setAbm(3);
                                    oCEUnidadMedidaProductoABM.add(oCEUnidadMedidaProducto1);
                                }
                            }
                            oCEProducto.setoCEUnidadMedidaProducto(oCEUnidadMedidaProductoABM);
                            if (CCProducto.editarTodoProducto(getProductoParaEdicion(), oLstEquivalenciaABM)) {
                                dispose();
                            } else {
                                TxtMarca.setBackground(Color.white);
                                JOptionPane.showMessageDialog(null, "<html>Operación Fallida<br>Posiblemente ya haya definido precios para las unidades</html>");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe completar el campo descripcion");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las cantidades de equivalencias en algunas filas siguen siendo 1");
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed
    private void limpiarTablaDeEquivalenciasSinDatos() {
        int rowCount2 = TblEquivalencias.getRowCount();
        for (int i = 0; i < rowCount2; i++) {
            CEUnidadMedidaVenta oCEUnidadMedidaProducto = (CEUnidadMedidaVenta) TblEquivalencias.getValueAt(i, 3);
            if (oCEUnidadMedidaProducto == null || Float.parseFloat(TblEquivalencias.getValueAt(i, 0).toString()) == 0) {

                CLEquivalenciaABM oCLEquivalenciaABM = new CLEquivalenciaABM();
                CEEquivalenciaUnidad CEEquivalenciaUnidad = (CEEquivalenciaUnidad) TblEquivalencias.getValueAt(i, 5);
                if (CEEquivalenciaUnidad != null) {
                    oCLEquivalenciaABM.setoCEEquivalencia(CEEquivalenciaUnidad);
                    oLstEquivalenciaABM.add(oCLEquivalenciaABM);
                }
                ((ArrayListTableModel) TblEquivalencias.getModel()).removeRow(i);

                i--;
                rowCount2--;
            }
        }
    }

    private boolean validarCantidadEquivalencias() {
        int size = TblEquivalencias.getRowCount();

        for (int i = 1; i < size; i++) {
            float valor = Float.parseFloat(TblEquivalencias.getValueAt(i, 0).toString());
            if (valor == 1) {
                return false;
            }

        }
        return true;
    }

    private void limpiarTablaDeEquivalenciasConUnidadBaseDuplicada() {
        int rowCount2 = TblEquivalencias.getRowCount();
        for (int i = 1; i < rowCount2; i++) {
            CEUnidadMedidaVenta oCEUnidadMedidaProducto = (CEUnidadMedidaVenta) TblEquivalencias.getValueAt(i, 3);
            CEUnidadMedidaVenta oCEUnidadMedidaProductoT = (CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem();
            if (oCEUnidadMedidaProducto == oCEUnidadMedidaProductoT) {
                CLEquivalenciaABM oCLEquivalenciaABM = new CLEquivalenciaABM();
                CEEquivalenciaUnidad CEEquivalenciaUnidad = (CEEquivalenciaUnidad) TblEquivalencias.getValueAt(i, 5);
                if (CEEquivalenciaUnidad != null) {
                    oCLEquivalenciaABM.setoCEEquivalencia(CEEquivalenciaUnidad);
                    oLstEquivalenciaABM.add(oCLEquivalenciaABM);
                }
                ((ArrayListTableModel) TblEquivalencias.getModel()).removeRow(i);

                i--;
                rowCount2--;
            }

        }
    }
    private void TxtMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtMarcaFocusLost
        TxtMarca.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_TxtMarcaFocusLost

    private void TxtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtMarcaKeyReleased
        if (evt.getKeyCode() == evt.VK_BACK_SPACE) {
            oCEMarca = null;
        }
    }//GEN-LAST:event_TxtMarcaKeyReleased

    private void CbxUnidadBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxUnidadBaseActionPerformed
        if (TblEquivalencias.getRowCount() >= 1) {
            ((ArrayListTableModel) TblEquivalencias.getModel()).setValueAt((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem(), 0, 1);
            ((ArrayListTableModel) TblEquivalencias.getModel()).setValueAt((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem(), 0, 3);
            for (int i = 0; i < TblEquivalencias.getRowCount(); i++) {
                ((ArrayListTableModel) TblEquivalencias.getModel()).setValueAt((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem(), i, 1);
            }
        }
    }//GEN-LAST:event_CbxUnidadBaseActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void CbxUnidadMedidaComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxUnidadMedidaComercialActionPerformed
        int rowCount2 = TblPedidosEquivalencia.getRowCount();
        for (int i = 0; i < rowCount2; i++) {
            CEUnidadMedidaProducto oCEUnidadMedidaProducto = (CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(i, 1);
            if (oCEUnidadMedidaProducto == (CEUnidadMedidaProducto) CbxUnidadMedidaComercial.getSelectedItem()) {
                oCEUnidadMedidaProducto.setSiNoUnidadMasComercial(1);
            } else {
                oCEUnidadMedidaProducto.setSiNoUnidadMasComercial(0);
            }
            if (oCEUnidadMedidaProducto.getIdUnidadMedidaVenta() == ((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem()).getIdUnidadVentaMedida()) {
                oCEUnidadMedidaProducto.setSiNoUnidadBase(1);
            } else {
                oCEUnidadMedidaProducto.setSiNoUnidadBase(0);
            }

        }
    }//GEN-LAST:event_CbxUnidadMedidaComercialActionPerformed

    private void CbxUnidadDePresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxUnidadDePresentacionActionPerformed
    }//GEN-LAST:event_CbxUnidadDePresentacionActionPerformed

    private void BtnAgregarUnidadBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarUnidadBaseActionPerformed
        DlgMantenimientoUnidadVenta oDlgMantenimientoMarca = new DlgMantenimientoUnidadVenta(null, true, 1);
        oDlgMantenimientoMarca.setLocationRelativeTo(null);
        oDlgMantenimientoMarca.setVisible(true);
        int idItemSelected = CbxUnidadBase.getSelectedIndex();
        cargarTipoUnidadBase();
        CbxUnidadBase.setSelectedIndex(idItemSelected);
    }//GEN-LAST:event_BtnAgregarUnidadBaseActionPerformed

    private void BtnAgregarEquivalencia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarEquivalencia2ActionPerformed
        DlgMantenimientoUnidadPresentacion oDlgMantenimientoMarca = new DlgMantenimientoUnidadPresentacion(null, true, 1);
        oDlgMantenimientoMarca.setLocationRelativeTo(null);
        oDlgMantenimientoMarca.setVisible(true);
        cargarTipoUnidadPresentacion();
        CbxUnidadDePresentacion.setSelectedIndex(CbxUnidadDePresentacion.getItemCount() - 1);
    }//GEN-LAST:event_BtnAgregarEquivalencia2ActionPerformed

    private void TblEquivalenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblEquivalenciasMouseClicked
        int row = TblEquivalencias.rowAtPoint(evt.getPoint());
        if (evt.getButton() == evt.BUTTON3) {

            jPopupMenuEquivalencias.show(TblEquivalencias, evt.getX(), evt.getY());

        }
    }//GEN-LAST:event_TblEquivalenciasMouseClicked

    private void jMenuItemAgregarEquivalenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgregarEquivalenciaActionPerformed
        DlgMantenimientoUnidadVenta oDlgMantenimientoMarca = new DlgMantenimientoUnidadVenta(null, true, 1);
        oDlgMantenimientoMarca.setLocationRelativeTo(null);
        oDlgMantenimientoMarca.setVisible(true);
        cargarTipoUnidadBase();
}//GEN-LAST:event_jMenuItemAgregarEquivalenciaActionPerformed
    private CEProducto getProducto() {
        CEProducto oCEProductoT = new CEProducto();
        oCEProductoT.setIdSubFamilia(oCESubfamilia.getIdSubFamilia());
        oCEProductoT.setIdFamilia(oCESubfamilia.getIdFamilia());
        oCEProductoT.setIdCategoria(oCECategoria.getIdCategoria());
        oCEProductoT.setIdRubro(oCECategoria.getIdRubro());
        oCEProductoT.setIdUnidadBase(((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem()).getIdUnidadVentaMedida());
        oCEProductoT.setIdUnidadPresentacionVenta(((CEUnidadPresentacionVenta) CbxUnidadDePresentacion.getSelectedItem()).getIdUnidadPresentacionVenta());
        oCEProductoT.setIdMarca(oCEMarca.getIdMarca());
        oCEProductoT.setSiNoImpuesto(RbtSiImpuesto.isSelected() ? true : false);
        oCEProductoT.setDescripcion(TxtDescripcion.getText());
        oCEProductoT.setCodigo(oCESubfamilia.getCodigo());
        List<CEEquivalenciaUnidad> oListCEEquivalencias = new ArrayList<CEEquivalenciaUnidad>();
        int rowCount = TblEquivalencias.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            CEEquivalenciaUnidad oCEEquivalencia = new CEEquivalenciaUnidad();
            oCEEquivalencia.setCantidadBase(Float.parseFloat(TblEquivalencias.getValueAt(i, 2).toString()));
            oCEEquivalencia.setIdUnidadBase(((CEUnidadMedidaVenta) (TblEquivalencias.getValueAt(i, 3))).getIdUnidadVentaMedida());
            oCEEquivalencia.setCantidadPedido(Float.parseFloat(TblEquivalencias.getValueAt(i, 0).toString()));
            oCEEquivalencia.setIdUnidadPedido(((CEUnidadMedidaVenta) (TblEquivalencias.getValueAt(i, 1))).getIdUnidadVentaMedida());
            oListCEEquivalencias.add(oCEEquivalencia);
        }
        List<CEUnidadMedidaProducto> oListCEUnidadMedidaProducto = new ArrayList<CEUnidadMedidaProducto>();
        int rowCount2 = TblPedidosEquivalencia.getRowCount();
        for (int i = 0; i < rowCount2; i++) {
            CEUnidadMedidaProducto oCEEquivalencia = new CEUnidadMedidaProducto();
            oCEEquivalencia = (CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(i, 1);
            float numUnidades = Float.parseFloat(TblPedidosEquivalencia.getValueAt(i, 3).toString());
            oCEEquivalencia.setNumUnidades(numUnidades);
            oListCEUnidadMedidaProducto.add(oCEEquivalencia);
            if (oCEEquivalencia == (CEUnidadMedidaProducto) CbxUnidadMedidaComercial.getSelectedItem()) {
                oCEEquivalencia.setSiNoUnidadMasComercial(1);
            }
        }
        oCEProductoT.setoListEquivalencia(oListCEEquivalencias);
        oCEProductoT.setoCEUnidadMedidaProducto(oListCEUnidadMedidaProducto);
        return oCEProductoT;
    }

    private CEProducto getProductoParaEdicion() {
        oCEProducto.setIdUnidadBase(((CEUnidadMedidaVenta) CbxUnidadBase.getSelectedItem()).getIdUnidadVentaMedida());
        oCEProducto.setIdMarca(oCEMarca.getIdMarca());
        oCEProducto.setIdUnidadPresentacionVenta(((CEUnidadPresentacionVenta) CbxUnidadDePresentacion.getSelectedItem()).getIdUnidadPresentacionVenta());
        oCEProducto.setSiNoImpuesto(RbtSiImpuesto.isSelected() ? true : false);
        oCEProducto.setDescripcion(TxtDescripcion.getText());
        oCEProducto.setIdProducto(oCEProducto.getIdProducto());
        return oCEProducto;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarEquivalencia;
    private javax.swing.JButton BtnAgregarEquivalencia2;
    private javax.swing.JButton BtnAgregarUnidadBase;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnEliminarEquivalencia;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JComboBox CbxUnidad;
    private javax.swing.JComboBox CbxUnidadBase;
    private javax.swing.JComboBox CbxUnidadDePresentacion;
    private javax.swing.JComboBox CbxUnidadMedidaComercial;
    private javax.swing.JRadioButton RbtNoImpuesto;
    private javax.swing.JRadioButton RbtNoImpuesto1;
    private javax.swing.JRadioButton RbtSiImpuesto;
    private javax.swing.JRadioButton RbtSiImpuesto1;
    private javax.swing.JTable TblEquivalencias;
    private javax.swing.JTable TblPedidosEquivalencia;
    private javax.swing.JTextField TxtCategoria;
    private TextField.JTxtLetraNumero TxtDescripcion;
    private javax.swing.JTextField TxtFamilia;
    private javax.swing.JTextField TxtMarca;
    private javax.swing.JTextField TxtRubro;
    private javax.swing.JTextField TxtSubfamilia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    private javax.swing.JMenuItem jMenuItemAgregarEquivalencia;
    private javax.swing.JPopupMenu jPopupMenuEquivalencias;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldNumber;
    // End of variables declaration//GEN-END:variables

    class myDefaultCellEditor extends DefaultCellEditor implements CellEditorListener {

        public myDefaultCellEditor(JComboBox comboBox) {
            super(CbxUnidad);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            JComboBox combo = (JComboBox) getComponent();
            construirModeloCombo(combo, new ArrayList());
            //int a=CbxUnidadBase.getSelectedIndex();
            ArrayList oArrayList = new ArrayList();
            for (int i = 0; i < CbxUnidadBase.getItemCount(); i++) {
                CEUnidadMedidaVenta oCEUnidadMedidaVenta = (CEUnidadMedidaVenta) CbxUnidadBase.getItemAt(i);
                boolean valu = false;
                for (int a = 0; a < TblEquivalencias.getRowCount(); a++) {
                    CEUnidadMedidaVenta oCEUnidadMedid = (CEUnidadMedidaVenta) TblEquivalencias.getValueAt(a, 3);
                    if (oCEUnidadMedid != null) {
                        if (a != row) {
                            if (oCEUnidadMedidaVenta.getIdUnidadVentaMedida() == oCEUnidadMedid.getIdUnidadVentaMedida()) {
                                valu = true;
                            }
                        }


                    }
                }
                if (!valu) {
                    oArrayList.add(oCEUnidadMedidaVenta);
                }

            }
            construirModeloCombo(combo, oArrayList);
            return combo;
        }

        public void editingStopped(ChangeEvent e) {
            procesarListaDeEquivalenciaAListaDeUnidadMedidaProducto();
        }

        public void editingCanceled(ChangeEvent e) {
            procesarListaDeEquivalenciaAListaDeUnidadMedidaProducto();
        }
    }
}
