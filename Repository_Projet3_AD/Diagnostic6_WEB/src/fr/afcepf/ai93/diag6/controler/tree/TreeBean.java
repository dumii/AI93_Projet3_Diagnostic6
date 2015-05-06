package fr.afcepf.ai93.diag6.controler.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.swing.tree.TreeNode;

import org.richfaces.component.AbstractTree;
import org.richfaces.event.TreeSelectionChangeEvent;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<TreeNode> rootNodes = new ArrayList<TreeNode>();
	
	private Map<String, Diagnostic_Tree> diagnosticCache = new HashMap<String, Diagnostic_Tree>();
	private Map<String, ERP_Tree> erpCache = new HashMap<String, ERP_Tree>();
	private Map<String, TypeDiagnostic_Tree> typeDiagnosticCache = new HashMap<String, TypeDiagnostic_Tree>();
	
	private TreeNode currentSelection = null;
	
	@EJB
	private IBusinessDiagnostic proxyBusinessDiag; 
	@EJB
	private IBusinessErp proxyBusinessErp; 
	private List<Erp> listeERP; 
	private List<Diagnostic> listeDiag; 

	//Exemple ici avec comme niveaux (de la racine vers les noeuds enfants) :
	//ERP => Type de diagnostic => Diagnostic
	
	//On doit définir le type et le nom pour chaque élément (class NamedNode)	
	@PostConstruct
	public void init() {
		listeERP = proxyBusinessErp.recupereToutErp();
		listeDiag = proxyBusinessDiag.recupereToutDiagnostic();
		
		for(Erp e : listeERP)
		{
			ERP_Tree erp = new ERP_Tree();
			erp.setName(e.getNomErp());
			erp.setType("erp");
			ERP_Tree erp2 = erpCache.get(erp.getName());
			if (erp2 == null) {
				erp2 = new ERP_Tree();
				erp2.setName(erp.getName());
				erpCache.put(erp.getName(), erp2);
				rootNodes.add(erp2);
			}
			
			/*for(Diagnostic diag : listeDiag)
			{
				TypeDiagnostic_Tree type = new TypeDiagnostic_Tree();
				type.setName("Type " + i + ", " + j);
				type.setType("typeDiagnostic");
				type.setErp(erp);
				String typeName = type.getName();
				TypeDiagnostic_Tree type2 = typeDiagnosticCache.get(typeName);
		        
				if (type2 == null) {
		            type2 = new TypeDiagnostic_Tree();
		            type2.setName(typeName);
		            type2.setParent(erp2);
		            erp2.getTypeDiagnostics().add(type2);
		            typeDiagnosticCache.put(typeName, type2);
		        }*/
				List<Diagnostic> listeDiagErp = diagnosticParErp(e);
				for(Diagnostic diag : listeDiagErp) {
					Diagnostic_Tree diagnostic = new Diagnostic_Tree(diag.getNomDiagnostiqueur(),
							diag.getIntituleDiagnostic(), erp2,diag.getTraite());
					erp2.getDiagnostics().add(diagnostic);
				}
			}
		}

	public List<Diagnostic> diagnosticParErp(Erp erp){
		List<Diagnostic> listeResultat = new ArrayList<Diagnostic>();
		for (Diagnostic d : listeDiag)
		{
			if(d.getErp().getIdErp() == erp.getIdErp())
			{
				listeResultat.add(d);
			}
		}
		return listeResultat; 
	}
	
	public void selectionChanged(TreeSelectionChangeEvent selectionChangeEvent) {
		// considering only single selection
		List<Object> selection = new ArrayList<Object>(selectionChangeEvent.getNewSelection());
		Object currentSelectionKey = selection.get(0);
		AbstractTree tree = (AbstractTree) selectionChangeEvent.getSource();

		Object storedKey = tree.getRowKey();
		tree.setRowKey(currentSelectionKey);
		currentSelection = (TreeNode) tree.getRowData();
		tree.setRowKey(storedKey);
	}

	public List<TreeNode> getRootNodes() {
		return rootNodes;
	}

	public void setRootNodes(List<TreeNode> rootNodes) {
		this.rootNodes = rootNodes;
	}

	public TreeNode getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(TreeNode currentSelection) {
		this.currentSelection = currentSelection;
	}
}