package fr.afcepf.ai93.diag6.controler.tree;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;
 
public class Diagnostic_Tree extends NamedNode implements TreeNode {
    
	//Le parent sous la forme d'une entit� simple :
	private ERP_Tree erp;
	//Les attributs du diagnostic car c'est le dernier noeud ainsi que plein de m�thodes ci-dessous :
    private String intitule;
    private int traite;
 
    public Diagnostic_Tree() {
        this.setType("diagnostic");
    }
 
    public Diagnostic_Tree(String name, String intitule, ERP_Tree erp, int traite) {
        super();
        this.setType("diagnostic");
        this.erp = erp;
        this.intitule = intitule;
        this.name = name;
        this.traite = traite;
    }
    
    public TreeNode getChildAt(int childIndex) {
        return null;
    }
 
    public int getChildCount() {
        return 0;
    }
 
    public TreeNode getParent() {
        return erp;
    }
 
    public int getIndex(TreeNode node) {
        return 0;
    }
 
    public boolean getAllowsChildren() {
        return false;
    }
 
    public boolean isLeaf() {
        return true;
    }
 
    public Enumeration<TreeNode> children() {
        return new Enumeration<TreeNode>() {
            public boolean hasMoreElements() {
                return false;
            }
 
            public TreeNode nextElement() {
                return null;
            }
        };
    }

	public ERP_Tree getErp() {
		return erp;
	}

	public void setErp(ERP_Tree erp) {
		this.erp = erp;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getTraite() {
		return traite;
	}

	public void setTraite(int traite) {
		this.traite = traite;
	}
}