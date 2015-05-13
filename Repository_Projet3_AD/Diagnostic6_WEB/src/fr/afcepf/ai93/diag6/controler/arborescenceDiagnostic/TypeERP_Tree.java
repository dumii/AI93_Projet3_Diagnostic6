package fr.afcepf.ai93.diag6.controler.arborescenceDiagnostic;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

public class TypeERP_Tree extends NamedNode implements TreeNode {
	
	//La liste des enfants :
	private List<ERP_Tree> ERPs = new ArrayList<ERP_Tree>();
	 
    public TypeERP_Tree() {
        this.setType("typeErp");
    }
 
    public TreeNode getChildAt(int childIndex) {
        return ERPs.get(childIndex);
    }
 
    public int getChildCount() {
        return ERPs.size();
    }
 
    public TreeNode getParent() {
        return null;
    }
 
    public int getIndex(TreeNode node) {
        return ERPs.indexOf(node);
    }
 
    public boolean getAllowsChildren() {
        return true;
    }
 
    public boolean isLeaf() {
        return ERPs.isEmpty();
    }
 
    public Enumeration<ERP_Tree> children() {
        return Iterators.asEnumeration(ERPs.iterator());
    }
 
    public List<ERP_Tree> getERPs() {
        return ERPs;
    }
}