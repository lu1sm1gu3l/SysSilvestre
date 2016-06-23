package view.grlGeneral;

import controller.grlGeneral.CCUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.cmrComercial.entidad.CEEmpleado;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;

public class DlgBuscarEmpleadoParaUsuario extends JDialog  implements ActionListener
{
    public DlgBuscarEmpleadoParaUsuario(java.awt.Dialog parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.configurarTablaHistorial();
        TxtUsername.requestFocus();
    }
    private void configurarTablaHistorial()
    {
         TblResultados.getColumnModel().getColumn(0).setMinWidth(300);
         TblResultados.getColumnModel().getColumn(1).setMinWidth(100);
    }
    public DlgBuscarEmpleadoParaUsuario(java.awt.Dialog parent, boolean modal,String parametro)
    {
        super(parent, modal);
        initComponents();
        TxtUsername.setText(parametro);
        buscarUsuario();
        TxtUsername.requestFocus();
        this.configurarTablaHistorial();
    }
    
    public void sendDatoUsuario(String datoUsuario)
    {
        TxtUsername.setText(datoUsuario);
        buscarUsuario();
    }
    public CEEmpleado getUsuario()
    {
        int selectedRow=TblResultados.getSelectedRow();
        CEEmpleado oCEUsuario=null;
        if(selectedRow!=-1)
        {
        oCEUsuario=(CEEmpleado)TblResultados.getValueAt(selectedRow,0);
        dispose();
        }
        return oCEUsuario;
    }
    private void limpiarTabla()
    {
        ArrayListTableModel oModelTbl=(ArrayListTableModel)TblResultados.getModel();
        int i=oModelTbl.getRowCount();
        for(int a=0;a<i;a++)
        {
           oModelTbl.removeRow(0);
        }
    }
    private void cargarTabla(List<CEEmpleado> oLstSujeto)
    {
        limpiarTabla();
        ArrayList oList;
        if(oLstSujeto!=null)
        {
            int size=oLstSujeto.size();
            for(int a=0;a<size;a++)
            {
            oList=new ArrayList();
            oList.add(oLstSujeto.get(a));
            oList.add(oLstSujeto.get(a).getSucursal());
            ((ArrayListTableModel)TblResultados.getModel()).addRow(oList);
            }
         }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        TxtUsername = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda Docente");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta de Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Ingrese los datos:");

        TxtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtUsernameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(14, 14, 14)
                .addComponent(TxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(TxtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblResultados.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TblResultados.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado",
                "Sucursal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class,
                java.lang.Object.class,
            };
            boolean[] canEdit = new boolean [] {
                false,false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblResultados.setRowHeight(22);
        TblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblResultados);
        TblResultados.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ProjSave");
        TblResultados.getActionMap().put("ProjSave", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int fila = TblResultados.getSelectedRow();
                    if ((fila > -1))
                    {
                        dispose();
                    }
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    private void TxtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUsernameKeyReleased
     if(evt.getKeyCode()==evt.VK_ENTER)
     {
         buscarUsuario();
     }
     if(evt.getKeyCode()==evt.VK_DOWN)
     {
         int size =TblResultados.getRowCount();
         if(size>0)
         {
         TblResultados.requestFocus();
         TblResultados.setRowSelectionInterval(0,0);
         }
     }
    }//GEN-LAST:event_TxtUsernameKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    if(evt.getClickCount()==2)
    {
        getUsuario();
    }
    }//GEN-LAST:event_formMouseClicked

    private void TblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblResultadosMouseClicked
     if(evt.getClickCount()==2)
        {
            int FilaSeleccionada =TblResultados.getSelectedRow();
            if(FilaSeleccionada!=-1)
            {
            dispose();
            }
        }
    }//GEN-LAST:event_TblResultadosMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      TblResultados.clearSelection();
    }//GEN-LAST:event_formWindowClosed
    private void buscarUsuario()
    {
         String datoUsuario=TxtUsername.getText().trim();
         
            
                CCUsuario oCCUsuario=new CCUsuario();
                List<CEEmpleado> oVctUsuario=oCCUsuario.buscarSujeto(datoUsuario);
                cargarTabla(oVctUsuario);
                     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblResultados;
    private javax.swing.JTextField TxtUsername;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {

        if(ACTION_CLOSE.equals(e.getActionCommand()))
            {
            TblResultados.clearSelection();
            dispose();
            }

    }
}
