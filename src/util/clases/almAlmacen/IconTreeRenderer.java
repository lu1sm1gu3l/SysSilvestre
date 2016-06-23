/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.clases.almAlmacen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import modelo.almAlmacen.entidad.CECategoria;
import modelo.almAlmacen.entidad.CEFamilia;
import modelo.almAlmacen.entidad.CEMarca;
import modelo.almAlmacen.entidad.CEProducto;
import modelo.almAlmacen.entidad.CERubro;
import modelo.almAlmacen.entidad.CESubfamilia;

/**
 *
 * @author iuga
 */
public class IconTreeRenderer extends DefaultTreeCellRenderer {

    private final JLabel label;
    private final Font orgFont;
    private final Font boldFont;

     private DefaultMutableTreeNode te;

    public IconTreeRenderer()
    {
        label = new JLabel();
        label.setBackground(null);

        orgFont = label.getFont().deriveFont(label.getFont().getStyle() ^ Font.PLAIN);
        boldFont = label.getFont().deriveFont(label.getFont().getStyle() ^ Font.BOLD);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        te = (DefaultMutableTreeNode) value;

        label.setText(te.toString());
        ImageIcon oImageIcon=null;

        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CERubro)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Rubro.gif"));
        }
        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CECategoria)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Categoria.gif"));
        }
        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CEFamilia)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Familia.gif"));
        }
        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CESubfamilia)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"));
        }
        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CEMarca)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/Marca.gif"));
        }
        if(((DefaultMutableTreeNode) value).getUserObject() instanceof CEProducto)
        {
            oImageIcon=new ImageIcon(getClass().getResource("/util/icono/product.png"));
        }
        if (sel)
        {
            label.setFont(orgFont);
            label.setForeground(Color.BLUE);
            label.setIcon(oImageIcon);
        }
        else
        {
            label.setFont(boldFont);
            label.setIcon(oImageIcon);
            label.setForeground(Color.BLACK);

        }
        label.setPreferredSize(new Dimension(400, 20));
        return label;
    }

}
