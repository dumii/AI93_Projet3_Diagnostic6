package fr.afcepf.ai93.diag6.controler.tree;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

public class TypeDiagnostic_Tree extends NamedNode implements TreeNode {
	//La liste des enfants dans une liste :
	private List<Diagnostic_Tree> diagnostics = new ArrayList<Diagnostic_Tree>();
	//Le parent en tant qu'entité simple :
	private ERP_Tree erp;

	public TypeDiagnostic_Tree() {
		this.setType("typeDiagnostic");
	}

	public TreeNode getChildAt(int childIndex) {
		return diagnostics.get(childIndex);
	}

	public int getChildCount() {
		return diagnostics.size();
	}

	public TreeNode getParent() {
		return erp;
	}

	public void setParent(ERP_Tree erp) {
		this.erp = erp;
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

	public ERP_Tree getErp() {
		return erp;
	}

	public void setErp(ERP_Tree erp) {
		this.erp = erp;
	}
}