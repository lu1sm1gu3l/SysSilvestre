package view.almAlmacen;


import controller.almAlmacen.CCProducto;
import controller.almAlmacen.CCProductoPrecio;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CEProductoPrecio;
import modelo.almAlmacen.entidad.CEUnidadMedidaProducto;
import util.clases.almAlmacen.JTableXOriginal;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.grlGeneral.VerificadorDeTxt;

public class DlgMantenimientoPreciosDeProductos extends DialogoPadre
{
    CEProducto oCEProducto;
    CEUnidadMedidaProducto oCEUnidadMedidaProductoSeleccionada;
    int rowSelectedAnt=-1;
    int tipoEvento=0;
    List<CEProductoPrecio> LstInicialPrecioProd=new ArrayList<CEProductoPrecio>();
    int idUM=0;
    public DlgMantenimientoPreciosDeProductos(java.awt.Frame parent, boolean modal,CEProducto oCEProducto)
    {
        super(parent, modal);
        initComponents();
        LblMensajePrecios.setVisible(false);
        LblMensajeFilas.setVisible(false);
        this.oCEProducto=oCEProducto;
        LblUndBase.setText(oCEProducto.getUMB());
        LblCodigoProducto.setText(oCEProducto.getCodigo());
        TxtDescripcion.setText(oCEProducto.getDescripcion());
        TxtPrecioRef.setDocument(new VerificadorDeTxt("Numero",0,TxtPrecioRef));
        TxtPrecioRef.setText((oCEProducto.getPrecio()+""));
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));

        idUM=oCEProducto.getIdUnidadPresentacionVenta(); // en realidad es la unidad de medida
        cargarTablaEquivalenciasPedidos(CCProducto.consultarUnidadMedidaProducto(oCEProducto.getIdProducto()));
        TblReglaDePrecios.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblReglaDePrecios.setSurrendersFocusOnKeystroke(true);
        TblReglaDePrecios.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if(value instanceof JLabel)
                {
                    fillColor(table, (JLabel)value, isSelected);
                    return (JLabel)value;
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            public void fillColor(JTable t,JLabel l,boolean isSelected )
            {
                if(isSelected)
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
            TblReglaDePrecios.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if(value instanceof JLabel)
                {
                    fillColor(table, (JLabel)value, isSelected);
                    return (JLabel)value;
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            public void fillColor(JTable t,JLabel l,boolean isSelected )
            {
                if(isSelected)
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
        TblPedidosEquivalencia.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {

                int row_equi=TblPedidosEquivalencia.getSelectedRow();
                int row_count=TblPedidosEquivalencia.getRowCount();
                if(row_equi!=-1&&TblPedidosEquivalencia.getRowCount()>0){
               CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidosEquivalencia.getValueAt(row_equi,1);
               LblUnidadPedidoSeleccionada.setText(oCEUnidadMedidaProducto.getTipoUnidad());
                
               List<CEProductoPrecio> oCEProductoPrecios=(ArrayList<CEProductoPrecio>)TblPedidosEquivalencia.getValueAt(TblPedidosEquivalencia.getSelectedRow(),3);
                   if(oCEProductoPrecios!=null)
                   {
                        if(rowSelectedAnt!=-1)
                        {
                            getListaReglasPrecio(rowSelectedAnt);
                        }
                           rowSelectedAnt=TblPedidosEquivalencia.getSelectedRow();
                           setListaReglasPrecio(rowSelectedAnt);
                           oCEUnidadMedidaProductoSeleccionada=oCEUnidadMedidaProducto;
                   }
               }
            }
        });

        ((JLabel)TblReglaDePrecios.getDefaultRenderer(java.lang.Object.class)).setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TblReglaDePrecios.setDefaultEditor(java.lang.Object.class,new DefaultCellEditor(jTextFieldNumber));
        ((JTableXOriginal)TblReglaDePrecios).setSelectAllForEdit(true);
        TblReglaDePrecios.getColumnModel().getColumn(0).setMaxWidth(40);
        TblReglaDePrecios.getColumnModel().getColumn(0).setMinWidth(40);
        TblReglaDePrecios.getColumnModel().getColumn(1).setMinWidth(30);
        TblReglaDePrecios.getColumnModel().getColumn(1).setMinWidth(30);
        TblReglaDePrecios.getColumnModel().getColumn(1).setMaxWidth(30);
        TblReglaDePrecios.getColumnModel().getColumn(6).setMaxWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(6).setMinWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(6).setPreferredWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(7).setMinWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(7).setMaxWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(7).setPreferredWidth(0);
        TblReglaDePrecios.getColumnModel().getColumn(2).setMinWidth(100);
        TblReglaDePrecios.getColumnModel().getColumn(3).setMinWidth(100);
        TblReglaDePrecios.getColumnModel().getColumn(4).setMinWidth(100);
        TblReglaDePrecios.getColumnModel().getColumn(8).setMinWidth(30);
        TblReglaDePrecios.getColumnModel().getColumn(8).setMaxWidth(30);
        TblReglaDePrecios.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e)
            {

                
                 if(e.getType()==e.DELETE)
                    {
                       verificarSecuenciaDeIntervalos();
                    }
                if(e.getColumn()==2||e.getColumn()==3||e.getColumn()==5)
                {
                    CEProductoPrecio oCEProductoPrecio=(CEProductoPrecio)TblReglaDePrecios.getValueAt(e.getLastRow(),7);

                    if(e.getType()==e.UPDATE)
                    {
                        getListaReglasPrecio(rowSelectedAnt);
                        if(oCEProductoPrecio!=null)
                        {
                        TblReglaDePrecios.setValueAt(2,e.getLastRow(),6);
                        }
                    }
                    if(e.getType()==e.INSERT)
                    {
                        TblReglaDePrecios.setValueAt(1,e.getLastRow(),6);
                    }
                   
                }
            }
        });

        selecionarFila();
    }

    int filaUM=0;
    private void selecionarFila()
    {
        TblPedidosEquivalencia.requestFocus();
        TblPedidosEquivalencia.changeSelection(filaUM, 0, false, false);
        if(TblReglaDePrecios.getRowCount()>0)
        {
            TblReglaDePrecios.requestFocus();
            TblReglaDePrecios.changeSelection(0, 5, false, false);
        }else if(TblReglaDePrecios.getRowCount()==0)
        {
            
            agregarNuevoIntervalo();
            TblReglaDePrecios.requestFocus();
            TblReglaDePrecios.changeSelection(0, 5, false, false);
        }
        
    }
    private boolean verificarSecuenciaDeIntervalos()
    {
        boolean v=false;
        int rowCount=(TblReglaDePrecios.getRowCount());
        for(int i=1;i<rowCount;i++)
        {
           if(TblReglaDePrecios.getValueAt(i,2)!=null&&TblReglaDePrecios.getValueAt(i-1,3)!=null)
           {
              float value=Float.parseFloat(TblReglaDePrecios.getValueAt(i,2).toString());
              float value2=Float.parseFloat(TblReglaDePrecios.getValueAt(i-1,3).toString());
              if(value!=value2)
              {                  
                  TblReglaDePrecios.setValueAt(LblRango,i-1,1);
                  TblReglaDePrecios.setValueAt("["+value2+" - "+value+">",i-1,4);
                  v=true;
              }
             else
              {
                    TblReglaDePrecios.setValueAt("",i-1,1);
                    TblReglaDePrecios.setValueAt("--------",i-1,4);
              }
            }
         else
           {
                    TblReglaDePrecios.setValueAt("",i-1,1);
                    TblReglaDePrecios.setValueAt("--------",i-1,4);
           }
        }
        if(TblReglaDePrecios.getRowCount()>0)
        {
         if(TblReglaDePrecios.getValueAt(TblReglaDePrecios.getRowCount()-1,2)!=null&&TblReglaDePrecios.getValueAt(TblReglaDePrecios.getRowCount()-1,3)!=null)
           {

                float max=50000;
                if(rowSelectedAnt+1<TblPedidosEquivalencia.getRowCount())
                {
                    max =(float) CLRedondear.Redondear(((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt + 1, 1)).getEquivalenciaConBase()/((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt, 1)).getEquivalenciaConBase(),2);
                }
                float value=Float.parseFloat(TblReglaDePrecios.getValueAt(TblReglaDePrecios.getRowCount()-1,3).toString());
                if(value!=max)
                {
                    v=true;
                    TblReglaDePrecios.setValueAt(value==max?"-------":"["+value+"-"+max+">",TblReglaDePrecios.getRowCount()-1,4);
                    TblReglaDePrecios.setValueAt(LblRango,TblReglaDePrecios.getRowCount()-1,1);
                }
                else
                {
                    TblReglaDePrecios.setValueAt("",TblReglaDePrecios.getRowCount()-1,1);
                    TblReglaDePrecios.setValueAt("--------",TblReglaDePrecios.getRowCount()-1,4);
                }
        }
        }
        LblMensajeFilas.setVisible(v);
        return v;
    }
    private boolean verificarPreciosDiferenteCero()
    {
        boolean v=true;
        int rowCount=(TblReglaDePrecios.getRowCount());
        for(int i=0;i<rowCount;i++)
        {
            if(TblReglaDePrecios.getValueAt(i,5)!=null)
            {
              float value=Float.parseFloat(TblReglaDePrecios.getValueAt(i,5).toString());
              if(value==0)
              {
                  TblReglaDePrecios.setValueAt(LblPrecio,i,8);
                  v=false;
              }
                else
              {
                 TblReglaDePrecios.setValueAt("",i,8);
                }
            }
        }
        LblMensajePrecios.setVisible(!v);
        return v;
    }
    private float generarNumeroInmediateSuperior(float value,float max)
    {
        int numDecimal=CLRedondear.getNumberOfDecimalPlace(value);
        if(numDecimal==0)
        {
            return value+1==max? value+(float)0.1:value+(float)1;
        }
        else
        {
            if(numDecimal==1)
            {
                return (float)CLRedondear.Redondear(value+(float)0.1,2)==max? value+(float)0.01:(float)CLRedondear.Redondear(value+(float)0.1,2);
                
            }
            else
            {
                if(numDecimal==2)
                {
                return (float)CLRedondear.Redondear(value+(float)0.01,2)==max? 0:(float)CLRedondear.Redondear(value+(float)0.01,2);
                }
            }
        }
        return 0;
    }
      private float generarNumeroInmediateInferior(float value)
    {
        int numDecimal=CLRedondear.getNumberOfDecimalPlace(value);
        if(numDecimal==0)
        {
            return value-1;
        }
        else
        {
            if(numDecimal==1)
            {
                return (float)CLRedondear.Redondear(value-(float)0.1,2);
            }
            else
            {
                if(numDecimal==2)
                {
                return (float)CLRedondear.Redondear(value-(float)0.01,2);
                }
            }
        }
        return 0;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        LblPrecio = new javax.swing.JLabel();
        LblRango = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtDescripcion = new TextField.JTxtLetraNumero();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblPedidosEquivalencia = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblReglaDePrecios = new util.clases.almAlmacen.JTableXOriginal()
        {
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                if(column==2||column==3||column==5)
                {
                    if(!value.equals(""))
                    {
                        Object valued=evaluarValor(value, row, column);
                        super.setValueAt(valued,row,column);
                        if(column==5&&row==0)
                        {
                            TxtPrecioRefPorUnidadMedida.setText(valued+"");
                        }
                        float max=50000;
                        if(rowSelectedAnt+1<TblPedidosEquivalencia.getRowCount())
                        {
                            max =(float) CLRedondear.Redondear(((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt + 1, 1)).getEquivalenciaConBase()/((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt, 1)).getEquivalenciaConBase(),2);
                        }
                        if(column==2)
                        {

                            if(TblReglaDePrecios.getRowCount()==0)
                            {
                                super.setValueAt(max,row,3);

                            }
                            else
                            {
                                if(row+1<TblReglaDePrecios.getRowCount())
                                {
                                    float va=Float.parseFloat(TblReglaDePrecios.getValueAt(row+1,2).toString());

                                    super.setValueAt(generarNumeroInmediateInferior(va),row,3);

                                }
                            }
                        }
                        verificarSecuenciaDeIntervalos();
                        verificarPreciosDiferenteCero();
                    }
                    else
                    {
                        verificarPreciosDiferenteCero();
                    }

                }
                else
                {
                    super.setValueAt(value,row,column);
                }
            }

        }
        ;
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        LblUnidadPedidoSeleccionada = new javax.swing.JLabel();
        BtnAgregarEquivalencia = new javax.swing.JButton();
        BtnEliminarEquivalencia = new javax.swing.JButton();
        btnGuardar1 = new BotonesABM.BtnGuardar();
        btnCancelar1 = new BotonesABM.BtnCancelar();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        LblMensajePrecios = new javax.swing.JLabel();
        LblCodigoProducto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LblMensajeFilas = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TxtPrecioRef = new javax.swing.JTextField();
        BtnAgregarEquivalencia1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        TxtPrecioRefPorUnidadMedida = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        LblUndBase = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        LblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/alerta.png"))); // NOI18N
        LblPrecio.setInheritsPopupMenu(false);
        LblPrecio.setOpaque(true);

        LblRango.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblRango.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icon_faq.png"))); // NOI18N
        LblRango.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Unidades de Pedido");

        TxtDescripcion.setEditable(false);
        TxtDescripcion.setObligatorio(false);
        TxtDescripcion.setTamanio(300);

        TblPedidosEquivalencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Unidad", "Equiv.", "List"
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
                return canEdit [columnIndex];
            }
        });
        TblPedidosEquivalencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblPedidosEquivalencia.setRowHeight(22);
        TblPedidosEquivalencia.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TblPedidosEquivalencia.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblPedidosEquivalenciaPropertyChange(evt);
            }
        });
        TblPedidosEquivalencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblPedidosEquivalenciaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(TblPedidosEquivalencia);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setMinWidth(0);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setPreferredWidth(0);
        TblPedidosEquivalencia.getColumnModel().getColumn(3).setMaxWidth(0);

        TblReglaDePrecios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "¿R?", "Cant. Minima", "Cant. Maxima.", "Rango Faltante", "Precio Unit.","abm","Objeto","¿P?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, true,false,false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblReglaDePrecios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TblReglaDePrecios.setRowHeight(20);
        TblReglaDePrecios.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblReglaDePrecios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblReglaDePreciosPropertyChange(evt);
            }
        });
        TblReglaDePrecios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblReglaDePreciosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TblReglaDePreciosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TblReglaDePrecios);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("Producto    :");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setText("Regla de Precios");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("Unidad de Pedido Selecionada:");

        LblUnidadPedidoSeleccionada.setFont(new java.awt.Font("Arial", 1, 14));
        LblUnidadPedidoSeleccionada.setText("--------------------------------");

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

        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyPressed(evt);
            }
        });

        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        LblMensajePrecios.setFont(new java.awt.Font("Arial", 0, 12));
        LblMensajePrecios.setForeground(new java.awt.Color(153, 0, 0));
        LblMensajePrecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/alerta.png"))); // NOI18N
        LblMensajePrecios.setText("<html>Hay Precios igual a 0. Se pide modificarlos en las filas señaladas</html>");

        LblCodigoProducto.setFont(new java.awt.Font("Arial", 1, 12));
        LblCodigoProducto.setText("..................");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setText("Código        :");

        LblMensajeFilas.setFont(new java.awt.Font("Arial", 0, 12));
        LblMensajeFilas.setForeground(new java.awt.Color(0, 0, 153));
        LblMensajeFilas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/icon_faq.png"))); // NOI18N
        LblMensajeFilas.setText("<html>Hay cantidades no definidas en los intervalos.Se sugiere incluirlos en las filas señaladas.</html>");

        //jLabel12.setVisible(false);
        jLabel12.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel12.setForeground(new java.awt.Color(0, 0, 51));
        jLabel12.setText("Precio Ref:");

        //TxtPrecioRef.setVisible(false);

        //BtnAgregarEquivalencia1.setVisible(false);
        BtnAgregarEquivalencia1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Familia.gif"))); // NOI18N
        BtnAgregarEquivalencia1.setToolTipText("Calcular");
        BtnAgregarEquivalencia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarEquivalencia1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setText("Precio Ref:");

        TxtPrecioRefPorUnidadMedida.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel15.setForeground(new java.awt.Color(153, 0, 0));
        jLabel15.setText("Und. Base:");

        LblUndBase.setFont(new java.awt.Font("Arial", 1, 12));
        LblUndBase.setText("..................");

        jButton1.setText("Ejemplo de rangos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblMensajeFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblMensajePrecios, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtPrecioRefPorUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LblUnidadPedidoSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BtnAgregarEquivalencia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnEliminarEquivalencia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(LblCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblUndBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtPrecioRef, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAgregarEquivalencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(LblCodigoProducto)
                            .addComponent(jLabel15)
                            .addComponent(LblUndBase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addComponent(BtnAgregarEquivalencia1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                .addComponent(TxtPrecioRef, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(LblUnidadPedidoSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jButton1)
                                    .addComponent(TxtPrecioRefPorUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BtnAgregarEquivalencia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BtnEliminarEquivalencia))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(LblMensajeFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LblMensajePrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void getListaReglasPrecio(int row)
    {
        List<CEProductoPrecio> oLstCEProductoPrecio=new ArrayList<CEProductoPrecio>();
        if(TblReglaDePrecios.getRowCount()>0){
        for(int i=0;i<TblReglaDePrecios.getRowCount();i++)
        {
        CEProductoPrecio oCEProductoPrecio=new CEProductoPrecio();
        oCEProductoPrecio.setRangoInicial(VerificadorDeTxt.convertFloat(TblReglaDePrecios.getValueAt(i, 2)));
        oCEProductoPrecio.setRangoFinal(VerificadorDeTxt.convertFloat(TblReglaDePrecios.getValueAt(i, 3)));
        if(TblReglaDePrecios.getValueAt(i, 4)!=null)
        {
            oCEProductoPrecio.setRangoFaltante(TblReglaDePrecios.getValueAt(i, 4).toString());
        }
        
        oCEProductoPrecio.setPrecioUnitario(VerificadorDeTxt.convertFloat(TblReglaDePrecios.getValueAt(i, 5)));
        oCEProductoPrecio.setIdProductoPrecio(((CEProductoPrecio)(TblReglaDePrecios.getValueAt(i, 7)))!=null?((CEProductoPrecio)(TblReglaDePrecios.getValueAt(i, 7))).getIdProductoPrecio():0);
        if(TblReglaDePrecios.getValueAt(i, 6)!=null) 
        {
            oCEProductoPrecio.setAbm(Integer.parseInt(TblReglaDePrecios.getValueAt(i, 6).toString()));
        }
        oLstCEProductoPrecio.add(oCEProductoPrecio);
        }
         CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidosEquivalencia.getValueAt(row,1);
         oCEUnidadMedidaProducto.setPrecioReferencia(TxtPrecioRefPorUnidadMedida.getText().equals("")?0:Float.parseFloat(TxtPrecioRefPorUnidadMedida.getText()));
        TblPedidosEquivalencia.setValueAt(oLstCEProductoPrecio,row, 3);

        }
    }
     private void setListaReglasPrecio(int row)
    {
        TxtPrecioRefPorUnidadMedida.setText(0+"");
        limpiarTabla(TblReglaDePrecios);
        List<CEProductoPrecio> oLstCEProductoPrecio= (ArrayList<CEProductoPrecio>)TblPedidosEquivalencia.getValueAt(row,3);
        if(oLstCEProductoPrecio!=null)
        {
            if(!oLstCEProductoPrecio.isEmpty())
            {
                int rows=0;               
                for(CEProductoPrecio oCEProductoPrecio:oLstCEProductoPrecio)
                {
                    Vector oVector=new Vector();
                    ((DefaultTableModel) TblReglaDePrecios.getModel()).addRow(oVector);
                    TblReglaDePrecios.setValueAt(rows+1,rows,0);
                    TblReglaDePrecios.setValueAt("",rows,1);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio.getRangoInicial(),rows,2);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio.getRangoFinal(),rows,3);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio.getRangoFaltante(),rows,4);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio.getPrecioUnitario(),rows,5);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio.getAbm(),rows,6);
                    TblReglaDePrecios.setValueAt(oCEProductoPrecio,rows,7);
                    TblReglaDePrecios.setValueAt("",rows,8);

                    rows++;
                }
            }
        }
    }
     
    private void limpiarTabla(JTable oJTable)
    {
        int size=oJTable.getRowCount();

        for(int i=0;i<size;i++)
        {
            ((DefaultTableModel) oJTable.getModel()).removeRow(0);
        }
    }

    private void BtnAgregarEquivalenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarEquivalenciaActionPerformed

        agregarNuevoIntervalo();
    }//GEN-LAST:event_BtnAgregarEquivalenciaActionPerformed

    private void agregarNuevoIntervalo()
    {
    if(oCEUnidadMedidaProductoSeleccionada!=null)
    {
        ArrayList oArrayList=new ArrayList();
        oArrayList.add(TblReglaDePrecios.getRowCount()+1);
        oArrayList.add("");
        float max=50000;
        if(rowSelectedAnt+1<TblPedidosEquivalencia.getRowCount())
        {
            max =(float) CLRedondear.Redondear(((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt + 1, 1)).getEquivalenciaConBase()/((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt, 1)).getEquivalenciaConBase(),2);
        }
        if(TblReglaDePrecios.getRowCount()==0)
        {

        oArrayList.add(1);
        oArrayList.add(max);
        oArrayList.add("-------");
        }
        else
        {
        float min=Float.parseFloat(TblReglaDePrecios.getValueAt(TblReglaDePrecios.getRowCount()-1,3).toString());

        oArrayList.add(min);
        oArrayList.add(max);
        oArrayList.add("-------");

        }
        oArrayList.add(0);
        oArrayList.add(1);
        oArrayList.add(null);
        if(TblReglaDePrecios.getRowCount()>0)
        {
          float min=Float.parseFloat(TblReglaDePrecios.getValueAt(TblReglaDePrecios.getRowCount()-1,3).toString());
          if(min!=max)
           {
            ((DefaultTableModel)TblReglaDePrecios.getModel()).addRow(oArrayList.toArray());
            }
            else
           {
                 JOptionPane.showMessageDialog(null,"Ha completado lo intervalos permitidos");
                 return;
           }
        }
        else
        {
            ((DefaultTableModel) TblReglaDePrecios.getModel()).addRow(oArrayList.toArray());
        }
        TblReglaDePrecios.requestFocus();
        TblReglaDePrecios.changeSelection(TblReglaDePrecios.getRowCount()-1,5,false,false);
        verificarSecuenciaDeIntervalos();
     }
    else
    {
        JOptionPane.showMessageDialog(null,"Debe elegir antes una UNIDAD DE PEDIDO");
    }
    }

   
    private void cargarTablaEquivalenciasPedidos(List<CEUnidadMedidaProducto> oCEUnidadMedidaProductos)
    {
        for(int i=0;i<oCEUnidadMedidaProductos.size();i++)
        {
            List<CEProductoPrecio> lstPrecioTemp=CCProductoPrecio.consultarPrecioMediaProducto(oCEUnidadMedidaProductos.get(i).getIdUnidadMedidaProducto());
            if(idUM==oCEUnidadMedidaProductos.get(i).getIdUnidadMedidaVenta())
            {
                filaUM=i;
            }
                Vector oVector=new Vector();
            oVector.add(oCEUnidadMedidaProductos.get(i).getDescripcion());
            oVector.add(oCEUnidadMedidaProductos.get(i));
            oVector.add(oCEUnidadMedidaProductos.get(i).getEquivalenciaConBase());
            oVector.add(lstPrecioTemp);
            ((DefaultTableModel)TblPedidosEquivalencia.getModel()).addRow(oVector);
            LstInicialPrecioProd.addAll(lstPrecioTemp);


        }
    }
    private void BtnEliminarEquivalenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarEquivalenciaActionPerformed
     int rowSelected=TblReglaDePrecios.getSelectedRow();
     if(rowSelected!=-1)
     {
         if(rowSelected!=0)
         {
             if((CEProductoPrecio)TblReglaDePrecios.getValueAt(rowSelected,7)!=null)
             {
                //oLstCEProductoPreciosEliminar.add((CEProductoPrecio)TblReglaDePrecios.getValueAt(rowSelected,7));
             }
             ((DefaultTableModel)TblReglaDePrecios.getModel()).removeRow(rowSelected);
         }
        else
         {
             JOptionPane.showMessageDialog(null, "Debe definir al menos una regla de precios");
        }
     }
}//GEN-LAST:event_BtnEliminarEquivalenciaActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed

        eventoGuardar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed


 private void eventoGuardar()
 {
  if(verificarPreciosDiferenteCero())
  {
    if(verificarSecuenciaDeIntervalos())
    {

            oCEProducto.setPrecio(!TxtPrecioRef.getText().equals("")?Float.parseFloat(TxtPrecioRef.getText()):0);
            if(CCProductoPrecio.abmProductoPrecio(getListaPrecios(),oCEProducto,getListUnidadMedidaProducto()))
            {
               // JOptionPane.showMessageDialog(null,"Operacion exitosa");
                dispose();
            }
            else
            {
                 JOptionPane.showMessageDialog(null,"Operacion Fallida");
            }

   }
    else
    {

                    if(CCProductoPrecio.abmProductoPrecio(getListaPrecios(),oCEProducto,getListUnidadMedidaProducto()))
                    {
                       // JOptionPane.showMessageDialog(null,"Operacion exitosa");
                        dispose();
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"Operacion Fallida");
                    }

    }
   }
 else
  {
    JOptionPane.showMessageDialog(null,"No ha definido correctamente los precios ");
  }
    }
    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
    dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void BtnAgregarEquivalencia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarEquivalencia1ActionPerformed
    calcularPrecioRefProducto();
    }//GEN-LAST:event_BtnAgregarEquivalencia1ActionPerformed

    private void TblReglaDePreciosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblReglaDePreciosKeyReleased
     
    }//GEN-LAST:event_TblReglaDePreciosKeyReleased

    private void btnGuardar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyPressed
       // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyPressed

    private void TblReglaDePreciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblReglaDePreciosKeyPressed

          if(TblReglaDePrecios.getRowCount()>0){
       TblReglaDePrecios.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
        TblReglaDePrecios.getActionMap().put("ProjSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                 btnGuardar1.requestFocus();
                 eventoGuardar();
            }
        });

        }
    }//GEN-LAST:event_TblReglaDePreciosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        DlgAyudaReglaPrecio oDialgo= new DlgAyudaReglaPrecio(null, true);
        oDialgo.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void calcularPrecioRefProducto()
    {
        if(TblReglaDePrecios.getSelectedColumn()==5){
            CEUnidadMedidaProducto objeto =(CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt, 1);
                           if(objeto.getEquivalenciaConBase()==1)
                           {
                               if(TblReglaDePrecios.getRowCount()>0&&TblReglaDePrecios.getSelectedRow()!=-1)
                              {
                                   float precioref =VerificadorDeTxt.convertFloat(TblReglaDePrecios.getValueAt(0,5));
                                   TxtPrecioRef.setText(precioref+"");
                              }
                           }
        }
    }
    private void TblReglaDePreciosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblReglaDePreciosPropertyChange

        
        calcularPrecioRefProducto();
    }//GEN-LAST:event_TblReglaDePreciosPropertyChange

    private void TblPedidosEquivalenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblPedidosEquivalenciaKeyPressed

        if(TblPedidosEquivalencia.getRowCount()>0){
            TblPedidosEquivalencia.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"ProjSave");
            TblPedidosEquivalencia.getActionMap().put("ProjSave", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    if(TblReglaDePrecios.getRowCount()>0) {
                        TblReglaDePrecios.requestFocus();
                        TblReglaDePrecios.changeSelection(0, 5, false, false);
                    }
                }
            });

        }
}//GEN-LAST:event_TblPedidosEquivalenciaKeyPressed

    private void TblPedidosEquivalenciaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblPedidosEquivalenciaPropertyChange

}//GEN-LAST:event_TblPedidosEquivalenciaPropertyChange
   
    private Object evaluarValor(Object value,int row,int column)
    {
        if(column==2||column==3||column==5)
        {
            if(TblReglaDePrecios.getValueAt(row,3)==null||TblReglaDePrecios.getValueAt(row,2)==null)
            {
                return value;
            }
            if(!value.equals(""))
            {
                
                float max=50000;
                if(rowSelectedAnt+1<TblPedidosEquivalencia.getRowCount())
                {

                  max =(float) CLRedondear.Redondear(((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt + 1, 1)).getEquivalenciaConBase()/((CEUnidadMedidaProducto) TblPedidosEquivalencia.getValueAt(rowSelectedAnt, 1)).getEquivalenciaConBase(),2);
                }
                if(column==3)
                {
                    if(row==TblReglaDePrecios.getRowCount()-1)
                    {
                    float value1=Float.parseFloat(TblReglaDePrecios.getValueAt(row,2).toString());
                    if(value1>CLRedondear.Redondear(Float.parseFloat(value.toString()),2))
                    {
                        JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor a "+value1);

                        TblReglaDePrecios.requestFocus();
                        TblReglaDePrecios.changeSelection(row,column,false,false);
                        return generarNumeroInmediateSuperior(value1,max);
                    }
                    else
                    {
                        float value_=Float.parseFloat(value.toString());
                        if(value_<=max)
                        {
                            return CLRedondear.Redondear(Float.parseFloat(value.toString()),2);
                        }
                        else
                        {

                            JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor a "+value1+" y menor o igual a " +max );
                            TblReglaDePrecios.requestFocus();
                            TblReglaDePrecios.changeSelection(row,column,false,false);
                            return max;
                        }
                    }
                    }
                    else
                    {
                            max = Float.parseFloat(TblReglaDePrecios.getValueAt(row+1,2).toString());
                            float value1=Float.parseFloat(TblReglaDePrecios.getValueAt(row,2).toString());
                            if(value1>CLRedondear.Redondear(Float.parseFloat(value.toString()),2))
                            {
                                JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor a "+value1);

                                TblReglaDePrecios.requestFocus();
                                TblReglaDePrecios.changeSelection(row,column,false,false);
                                return generarNumeroInmediateSuperior(value1,max);
                            }
                            else
                            {
                                float value_=Float.parseFloat(value.toString());
                                if(value_<=max)
                                {
                                    return CLRedondear.Redondear(Float.parseFloat(value.toString()),2);
                                }
                                else
                                {

                                    JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor a "+value1+" y menor o igual a " +max );
                                    TblReglaDePrecios.requestFocus();
                                    TblReglaDePrecios.changeSelection(row,column,false,false);
                                    return max;
                                }
                            }
                    }
                }
                else
                {
                    if(column==2)
                    {
                        if(row==0)
                        {
                            float max2=Float.parseFloat(TblReglaDePrecios.getValueAt(row,3).toString());
                            if(CLRedondear.Redondear(Float.parseFloat(value.toString()),2)<max2)
                            {
                                if(CLRedondear.Redondear(Float.parseFloat(value.toString()),2)>0)
                                {
                                    return CLRedondear.Redondear(Float.parseFloat(value.toString()),2);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor a 0");
                                    TblReglaDePrecios.requestFocus();
                                    TblReglaDePrecios.changeSelection(row,column,false,false);
                                    return 1.00;
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Debe ingresar un parametro menor o igual a "+max2);
                                TblReglaDePrecios.requestFocus();
                                TblReglaDePrecios.changeSelection(row,column,false,false);
                                return 1.00;
                            }
                        }
                        else
                        {
                            float min=0;

                            min=Float.parseFloat(TblReglaDePrecios.getValueAt(row-1,3).toString());
                            float max2=Float.parseFloat(TblReglaDePrecios.getValueAt(row,3).toString());
                            if(CLRedondear.Redondear(Float.parseFloat(value.toString()),2)<=max2)
                            {
                                if(CLRedondear.Redondear(Float.parseFloat(value.toString()),2)>=min)
                                {
                                    return CLRedondear.Redondear(Float.parseFloat(value.toString()),2);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"Debe ingresar un parametro mayor o igual a "+min);

                                    TblReglaDePrecios.requestFocus();
                                    TblReglaDePrecios.changeSelection(row,column,false,false);
                                    return min;
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Debe ingresar un parametro menor o igual "+max2);
                                TblReglaDePrecios.requestFocus();
                                TblReglaDePrecios.changeSelection(row,column,false,false);
                                return min;

                            }
                        }

                    }
                }
            }
            else
            {
                return "0";
            }
        }
            return value;
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarEquivalencia;
    private javax.swing.JButton BtnAgregarEquivalencia1;
    private javax.swing.JButton BtnEliminarEquivalencia;
    private javax.swing.JLabel LblCodigoProducto;
    private javax.swing.JLabel LblMensajeFilas;
    private javax.swing.JLabel LblMensajePrecios;
    private javax.swing.JLabel LblPrecio;
    private javax.swing.JLabel LblRango;
    private javax.swing.JLabel LblUndBase;
    private javax.swing.JLabel LblUnidadPedidoSeleccionada;
    private javax.swing.JTable TblPedidosEquivalencia;
    private javax.swing.JTable TblReglaDePrecios;
    private TextField.JTxtLetraNumero TxtDescripcion;
    private javax.swing.JTextField TxtPrecioRef;
    private javax.swing.JTextField TxtPrecioRefPorUnidadMedida;
    private BotonesABM.BtnCancelar btnCancelar1;
    private BotonesABM.BtnGuardar btnGuardar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldNumber;
    // End of variables declaration//GEN-END:variables



    private List<CEProductoPrecio> getListaReglaDePrecios()
    {
        oCEProducto.setPrecio(!TxtPrecioRef.getText().equals("")?Float.parseFloat(TxtPrecioRef.getText()):0);
        List<CEProductoPrecio> oLstCEProductoPrecio=new ArrayList<CEProductoPrecio>();
        for(int i=0;i<TblReglaDePrecios.getRowCount();i++)
        {
        CEProductoPrecio oCEProductoPrecio=new CEProductoPrecio();
        oCEProductoPrecio.setRangoInicial(Float.parseFloat(TblReglaDePrecios.getValueAt(i, 2).toString()));
        oCEProductoPrecio.setRangoFinal(Float.parseFloat(TblReglaDePrecios.getValueAt(i, 3).toString()));
        oCEProductoPrecio.setRangoFaltante(TblReglaDePrecios.getValueAt(i, 4).toString());
        oCEProductoPrecio.setPrecioUnitario(Float.parseFloat(TblReglaDePrecios.getValueAt(i, 5).toString()));
        oCEProductoPrecio.setIdProducto(oCEProducto.getIdProducto());
        oCEProductoPrecio.setIdUnidadMedidaVenta(oCEUnidadMedidaProductoSeleccionada.getIdUnidadMedidaVenta());
        oCEProductoPrecio.setIdUnidadMedidaProducto(oCEUnidadMedidaProductoSeleccionada.getIdUnidadMedidaProducto());
        oCEProductoPrecio.setIdProductoPrecio(((CEProductoPrecio)(TblReglaDePrecios.getValueAt(i, 7)))==null?0:((CEProductoPrecio)(TblReglaDePrecios.getValueAt(i, 7))).getIdProductoPrecio()
                );
        oCEProductoPrecio.setAbm(Integer.parseInt(TblReglaDePrecios.getValueAt(i, 6).toString()));
        oLstCEProductoPrecio.add(oCEProductoPrecio);
        }

        for(int i=0;i<TblPedidosEquivalencia.getRowCount();i++)
        {
            CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidosEquivalencia.getValueAt(i,1);
            List<CEProductoPrecio> oLstCEProductoPrecioT= (ArrayList<CEProductoPrecio>)TblPedidosEquivalencia.getValueAt(i,3);
            if(oLstCEProductoPrecio!=null)
            {
                for(CEProductoPrecio oCEProductoPrecio:oLstCEProductoPrecioT)
                {
                    if(oCEUnidadMedidaProducto!=oCEUnidadMedidaProductoSeleccionada&&oCEUnidadMedidaProducto!=null)
                    {
                    oCEProductoPrecio.setIdProducto(oCEProducto.getIdProducto());
                    oCEProductoPrecio.setIdUnidadMedidaVenta(oCEUnidadMedidaProducto.getIdUnidadMedidaVenta());
                    oCEProductoPrecio.setIdUnidadMedidaProducto(oCEUnidadMedidaProducto.getIdUnidadMedidaProducto());
                    oLstCEProductoPrecio.add(oCEProductoPrecio);
                    }
                }
            }
            //for(CEProductoPrecio oCEProductoPrecio:oLstCEProductoPreciosEliminar)
              //  {
                //    oCEProductoPrecio.setAbm(3);
                 //   oLstCEProductoPrecio.add(oCEProductoPrecio);
               // }
        }
        
        return oLstCEProductoPrecio;
    }

    private List<CEProductoPrecio> getListaPrecios()
    {
        boolean esEliminado=true;
        List<CEProductoPrecio> LstPreciosActual =getListaReglaDePrecios();
        List<CEProductoPrecio> LstPreciosEliminar =new ArrayList<CEProductoPrecio>();
        for(CEProductoPrecio oCEProductoPrecioInicial:LstInicialPrecioProd)
            {
                    esEliminado=true;
                    for(CEProductoPrecio oCEProductoPrecioActual:LstPreciosActual)
                    {

                        if(oCEProductoPrecioActual.getIdProductoPrecio()==0)
                            {
                                oCEProductoPrecioActual.setAbm(1);
                                oCEProductoPrecioActual.setIdProductoPrecio(-1);
                            }

                            if(oCEProductoPrecioInicial.getIdProductoPrecio()==oCEProductoPrecioActual.getIdProductoPrecio())
                            {
                                oCEProductoPrecioActual.setAbm(2);
                                esEliminado=false;
                                break ;
                            }

                    }
                    if(esEliminado)
                    {
                        oCEProductoPrecioInicial.setAbm(3);
                        LstPreciosEliminar.add(oCEProductoPrecioInicial);
                    }
            }
        
        LstPreciosActual.addAll(LstPreciosEliminar);
        return LstPreciosActual;
    }
   private List<CEUnidadMedidaProducto> getListUnidadMedidaProducto()
    {
        int size=TblPedidosEquivalencia.getRowCount();
        List<CEUnidadMedidaProducto> oLstUnidadProducto=new ArrayList<CEUnidadMedidaProducto>();
        for(int i=0;i<size;i++)
        {
            CEUnidadMedidaProducto oCEUnidadMedidaProducto=(CEUnidadMedidaProducto)TblPedidosEquivalencia.getValueAt(i,1);
            int selectedRow=TblPedidosEquivalencia.getSelectedRow();
//            if(selectedRow!=-1)
//            {
//              CEUnidadMedidaProducto oCEUnidadMedidaProducto1=(CEUnidadMedidaProducto)TblPedidosEquivalencia.getValueAt(selectedRow,1);
//              if(oCEUnidadMedidaProducto1==oCEUnidadMedidaProducto)
//              {
//               oCEUnidadMedidaProducto.setPrecioReferencia(Float.parseFloat(TxtPrecioRefPorUnidadMedida.getText()));
//               oLstUnidadProducto.add(oCEUnidadMedidaProducto);
//             }
//            }
           // else
           // {
            List<CEProductoPrecio> oLstCEProductoPrecio= (ArrayList<CEProductoPrecio>)TblPedidosEquivalencia.getValueAt(i,3);
            if(oLstCEProductoPrecio!=null)
            {
                if(!oLstCEProductoPrecio.isEmpty())
                {
                oCEUnidadMedidaProducto.setPrecioReferencia(oLstCEProductoPrecio.get(0).getPrecioUnitario());
                oLstUnidadProducto.add(oCEUnidadMedidaProducto);         
                }               
            }
           // }
        }
        return oLstUnidadProducto;
    }
}
