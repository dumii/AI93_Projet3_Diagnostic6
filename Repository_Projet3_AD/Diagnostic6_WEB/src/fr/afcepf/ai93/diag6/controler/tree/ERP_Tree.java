package fr.afcepf.ai93.diag6.controler.tree;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
 
public class ERP_Tree extends NamedNode implements TreeNode {
	//La liste des enfants :
	private List<Diagnostic_Tree> diagnostics = new ArrayList<Diagnostic_Tree>();
	 
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
        return null;
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
 
    public Enumeration<Diagnostic_Tree> children() {
        return Iterators.asEnumeration(diagnostics.iterator());
    }
 
    public List<Diagnostic_Tree> getDiagnostics() {
        return diagnostics;
    }
}