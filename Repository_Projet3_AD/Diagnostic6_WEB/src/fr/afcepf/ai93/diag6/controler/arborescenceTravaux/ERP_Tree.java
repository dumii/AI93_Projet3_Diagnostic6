package fr.afcepf.ai93.diag6.controler.arborescenceTravaux;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class ERP_Tree extends NamedNode implements TreeNode {

	//Le parent sous la forme d'une entité simple :
	private TypeERP_Tree typeErp;
	//Les attributs du diagnostic car c'est le dernier noeud ainsi que plein de méthodes ci-dessous :
	private String intitule;
	private int idERP;

	public ERP_Tree() {
		this.setType("erp");
	}

	public ERP_Tree(String name, String intitule, TypeERP_Tree typeErp, int idERP) {
		super();
		this.setType("erp");
		this.typeErp = typeErp;
		this.intitule = intitule;
		this.name = name;
		this.idERP = idERP;
	}

	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	public int getChildCount() {
		return 0;
	}

	public TreeNode getParent() {
		return typeErp;
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

	public TypeERP_Tree getTypeErp() {
		return typeErp;
	}

	public void setTypeErp(TypeERP_Tree typeErp) {
		this.typeErp = typeErp;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getIdERP() {
		return idERP;
	}

	public void setIdERP(int idERP) {
		this.idERP = idERP;
	}
}