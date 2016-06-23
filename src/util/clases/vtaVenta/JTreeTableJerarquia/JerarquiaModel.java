package util.clases.vtaVenta.JTreeTableJerarquia;

import controller.almAlmacen.CCCategoria;
import controller.almAlmacen.CCFamilia;
import controller.almAlmacen.CCProducto;
import controller.almAlmacen.CCRubro;
import controller.almAlmacen.CCSubFamilia;
import java.util.ArrayList;
import java.util.List;


public class JerarquiaModel extends AbstractTreeTableModel implements TreeTableModel
{
    public  String primeraFecha;
    public  String SegundaFecha;
    public  int IdConsulta;


    public static Class[] getcTypes()
    {
        return cTypes;
    }

    public static void setcTypes(Class[] cTypes)
    {
        cTypes = cTypes;
    }


    static protected String[]  cNames = {"Descripción","Nº Ventas", "Cantidad"};

    // Types of the columns.
    static protected Class[]  cTypes = {TreeTableModel.class, Float.class, Float.class};

    // The the returned ObjetoJerarquia length for directories.
    public static final Integer ZERO = new Integer(0); 

 

    public JerarquiaModel(String primeraFecha, String SegundaFecha, int IdConsulta)
    {
        super(new ObjetoJerarquiaNode(new ObjetoJerarquia(),primeraFecha, SegundaFecha, IdConsulta));
        this.primeraFecha = primeraFecha;
        this.SegundaFecha = SegundaFecha;
        this.IdConsulta = IdConsulta;
    } 

    protected ObjetoJerarquia getObjetoJerarquia(Object node) {
	ObjetoJerarquiaNode ObjetoJerarquiaNode = ((ObjetoJerarquiaNode)node);
	return ObjetoJerarquiaNode.getObjetoJerarquia();
    }

    protected List getChildren(Object node) {
	ObjetoJerarquiaNode ObjetoJerarquiaNode = ((ObjetoJerarquiaNode)node);
	return ObjetoJerarquiaNode.getChildren();
    }


    public int getChildCount(Object node) { 
	List children = getChildren(node);
	return (children == null) ? 0 : children.size();
    }

    public Object getChild(Object node, int i) { 
	return getChildren(node).get(i);
    }

    
    public boolean isLeaf(Object node) { return false; }

   
    public int getColumnCount() {
	return cNames.length;
    }

    public String getColumnName(int column) {
	return cNames[column];
    }

    public Class getColumnClass(int column) {
	return cTypes[column];
    }
 
    public Object getValueAt(Object node, int column) {
	ObjetoJerarquia ObjetoJerarquia = getObjetoJerarquia(node);
	try {
	    switch(column)
            {
	     case 0:
		return ObjetoJerarquia;
	     case 1:
		return ObjetoJerarquia.getNumVentas();
	     case 2:
		return ObjetoJerarquia.getCantidadVendida();
	    }
	}
	catch  (SecurityException se) { }
   
	return null; 
    }
}

class ObjetoJerarquiaNode
{
    ObjetoJerarquia ObjetoJerarquia;
    List<ObjetoJerarquiaNode> children;
    String primeraFecha;
    String SegundaFecha;
    int IdConsulta;

    public ObjetoJerarquiaNode(ObjetoJerarquia ObjetoJerarquia,String primeraFecha, String SegundaFecha, int IdConsulta) {
        this.ObjetoJerarquia = ObjetoJerarquia;
        this.primeraFecha = primeraFecha;
        this.SegundaFecha = SegundaFecha;
        this.IdConsulta = IdConsulta;
    }

    public int getIdConsulta() {
        return IdConsulta;
    }

    public void setIdConsulta(int IdConsulta) {
        this.IdConsulta = IdConsulta;
    }

    public static MergeSort getObjetoJerarquiaMS() {
        return ObjetoJerarquiaMS;
    }

    public static void setObjetoJerarquiaMS(MergeSort ObjetoJerarquiaMS) {
        ObjetoJerarquiaNode.ObjetoJerarquiaMS = ObjetoJerarquiaMS;
    }

    public String getSegundaFecha() {
        return SegundaFecha;
    }

    public void setSegundaFecha(String SegundaFecha) {
        this.SegundaFecha = SegundaFecha;
    }

    public void setChildren(List<ObjetoJerarquiaNode> children) {
        this.children = children;
    }

    public String getPrimeraFecha() {
        return primeraFecha;
    }

    public void setPrimeraFecha(String primeraFecha) {
        this.primeraFecha = primeraFecha;
    }

    public ObjetoJerarquiaNode(ObjetoJerarquia ObjetoJerarquia)
    {
	this.ObjetoJerarquia = ObjetoJerarquia;
    }

    
    static private MergeSort  ObjetoJerarquiaMS = new MergeSort() {
	public int compareElementsAt(int a, int b) {
	    return ((String)toSort[a]).compareTo((String)toSort[b]);
	}
    };

    public String toString() { 
	return ObjetoJerarquia.toString();
    }

    public ObjetoJerarquia getObjetoJerarquia() {
	return ObjetoJerarquia;
    }

    protected List getChildren()
    {
	if (children != null)
        {
	    return children;
	}
	try
        {
            List<ObjetoJerarquia> ObjetoJerarquias=null;
            if(ObjetoJerarquia.getTipoObjeto()==null)
            {
                ObjetoJerarquias=CCRubro.consultarListaRubrosNumVentas(IdConsulta, primeraFecha,SegundaFecha);
            }
            else
            {
                if(ObjetoJerarquia.getTipoObjeto().equals("Rubro"))
                {
                   ObjetoJerarquias=CCCategoria.consultarListaCategoriaPorIdRubrosVenta(IdConsulta,ObjetoJerarquia.getIdRubro(),primeraFecha,SegundaFecha);
                }
                else
                {
                    if(ObjetoJerarquia.getTipoObjeto().equals("Categoria"))
                    {
                    ObjetoJerarquias=CCFamilia.consultarListaFamiliaPorIdCategoriaVenta(IdConsulta,ObjetoJerarquia.getIdRubro(),ObjetoJerarquia.getIdCategoria(),primeraFecha,SegundaFecha);
                    }
                    else
                    {
                        if(ObjetoJerarquia.getTipoObjeto().equals("Familia"))
                        {
                           ObjetoJerarquias=CCSubFamilia.consultarListaSubFamiliaPorIdCategoriaVenta(IdConsulta,ObjetoJerarquia.getIdRubro(),ObjetoJerarquia.getIdCategoria(),ObjetoJerarquia.getIdFamilia(),primeraFecha,SegundaFecha);
                        }
                         else
                        {
                            if(ObjetoJerarquia.getTipoObjeto().equals("Subfamilia"))
                            {
                               ObjetoJerarquias=CCProducto.consultarProductoPorMarca(IdConsulta,ObjetoJerarquia.getIdRubro(),ObjetoJerarquia.getIdCategoria(),ObjetoJerarquia.getIdFamilia(),ObjetoJerarquia.getIdSubfamilia(),primeraFecha,SegundaFecha);
                            }
                        }
                    }
                }

            }	    
	    if(ObjetoJerarquias != null)
            {
		children = new ArrayList<ObjetoJerarquiaNode>(ObjetoJerarquias.size());
		for(int i = 0; i < ObjetoJerarquias.size(); i++)
                {
		    children.add(new ObjetoJerarquiaNode(ObjetoJerarquias.get(i),primeraFecha, SegundaFecha, IdConsulta));
		}
	    }
	} catch (SecurityException se) {}
	return children;
    }
}


