package fr.afcepf.ai93.diag6.controler.arborescenceDiagnostic;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;

public class ERP_Tree extends NamedNode implements TreeNode {

	//La liste des enfants dans une liste :
	private List<Diagnostic_Tree> diagnostics = new ArrayList<Diagnostic_Tree>();
	//Le parent en tant qu'entité simple :
	private TypeERP_Tree typeErp;

	public ERP_Tree() {
		this.setType("erp");
	}

	public TreeNode getChildAt(int childIndex) {
		return diagnostics.get(childIndex);
	}

	public int getChildCount() {
		return diagnostics.size();
	}

	public TreeNode getParent() {
		return typeErp;
	}

	public void setParent(TypeERP_Tree typeErp) {
		this.typeErp = typeErp;
	}

	public int getIndex(TreeNode node) {
		return diagnostics.indexOf(node);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return diagnostics.isEmpty();
	}

	public Enumeration children() {
		return Iterators.asEnumeration(diagnostics.iterator());
	}

	public List<Diagnostic_Tree> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(List<Diagnostic_Tree> diagnostics) {
		this.diagnostics = diagnostics;
	}

	public TypeERP_Tree getTypeErp() {
		return typeErp;
	}

	public void setTypeErp(TypeERP_Tree typeErp) {
		this.typeErp = typeErp;
	}
}