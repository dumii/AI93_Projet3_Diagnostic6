package fr.afcepf.ai93.diag6.controler.arborescenceDiagnostic;

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
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;

@ManagedBean(name="mbArborescenceDiagnostic")
@SessionScoped
public class arborescenceDiagnosticManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//Accordéon 1
	private List<TreeNode> rootNodes1 = new ArrayList<TreeNode>();
	
	private Map<String, Diagnostic_Tree> diagnosticCache1 = new HashMap<String, Diagnostic_Tree>();
	private Map<String, ERP_Tree> erpCache1 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache1 = new HashMap<String, TypeERP_Tree>();
	
	//Accordéon 2
	private List<TreeNode> rootNodes2 = new ArrayList<TreeNode>();
	
	private Map<String, Diagnostic_Tree> diagnosticCache2 = new HashMap<String, Diagnostic_Tree>();
	private Map<String, ERP_Tree> erpCache2 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache2 = new HashMap<String, TypeERP_Tree>();
	
	//Accordéon 3
	private List<TreeNode> rootNodes3 = new ArrayList<TreeNode>();
	
	private Map<String, Diagnostic_Tree> diagnosticCache3 = new HashMap<String, Diagnostic_Tree>();
	private Map<String, ERP_Tree> erpCache3 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache3 = new HashMap<String, TypeERP_Tree>();
	
	private TreeNode currentSelection = null;
	
	@EJB
	private IBusinessDiagnostic proxyDiagnostic; 
	@EJB
	private IBusinessErp proxyERP; 

	private List<TypeErp> listeTypeERP;
	private List<Erp> listeERP; 
	private List<Diagnostic> listeDiagnostic; 
	private List<Diagnostic> listeDiagnosticsIntervEnCours; 
	private List<Diagnostic> listeDiagnosticsIntervAttente;
	private List<Diagnostic> listeDiagnosticsIntervArchive; 
	
	//On doit définir le type et le nom pour chaque élément (class NamedNode)	
	@PostConstruct
	public void init() {

		listeTypeERP = proxyERP.recupererListeTypeERP();
		listeERP = proxyERP.recupereToutErp();
		listeDiagnostic = proxyDiagnostic.recupereToutDiagnostic();
		listeDiagnosticsIntervEnCours = proxyDiagnostic.recupereToutDiagnosticIntervEnCours();
		listeDiagnosticsIntervAttente = proxyDiagnostic.recupereToutDiagnosticEnAttente();
		listeDiagnosticsIntervArchive = proxyDiagnostic.recupereToutDiagnosticArchives();

		chargerArborescence1();
		chargerArborescence2();
		chargerArborescence3();
	}

	public void chargerArborescence1() {
		
		for (TypeErp type : listeTypeERP)
		{
			int enfants = 0;

			TypeERP_Tree typeTree = new TypeERP_Tree();
			typeTree.setName(type.getTypeErpCourt());
			typeTree.setType("typeErp");

			typeERPCache1.put(typeTree.getName(), typeTree);
			rootNodes1.add(typeTree);

			for(Erp erp : listeERP)
			{
				List<Diagnostic> listeDiag = listerDiagnosticParErp(erp, listeDiagnosticsIntervEnCours);

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp() && listeDiag.size() > 0)
				{
					enfants++;

					ERP_Tree erpTree = new ERP_Tree();
					erpTree.setName(erp.getNomErp() + " (" + listeDiag.size() + ")");
					erpTree.setType("erp");

					erpTree.setParent(typeTree);
					erpTree.setTypeErp(typeTree);				

					typeTree.getERPs().add(erpTree);

					erpCache1.put(erpTree.getName(), erpTree);

					for(Diagnostic diag : listeDiag)
					{
						Diagnostic_Tree diagnostic = new Diagnostic_Tree(diag.getNomDiagnostiqueur(),
								diag.getIntituleDiagnostic(), erpTree, diag.getIdDiagnostic());
						erpTree.getDiagnostics().add(diagnostic);
					}
				}
			}

			if (enfants == 0)
			{
				typeERPCache1.remove(typeTree.getName());
				rootNodes1.remove(typeTree);
			}
		}
	}

	public void chargerArborescence2() {

		for (TypeErp type : listeTypeERP)
		{
			int enfants = 0;

			TypeERP_Tree typeTree = new TypeERP_Tree();
			typeTree.setName(type.getTypeErpCourt());
			typeTree.setType("typeErp");

			typeERPCache2.put(typeTree.getName(), typeTree);
			rootNodes2.add(typeTree);

			for(Erp erp : listeERP)
			{
				List<Diagnostic> listeDiag = listerDiagnosticParErp(erp, listeDiagnosticsIntervAttente);

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp() && listeDiag.size() > 0)
				{
					enfants++;

					ERP_Tree erpTree = new ERP_Tree();
					erpTree.setName(erp.getNomErp() + " (" + listeDiag.size() + ")");
					erpTree.setType("erp");

					erpTree.setParent(typeTree);
					erpTree.setTypeErp(typeTree);				

					typeTree.getERPs().add(erpTree);

					erpCache1.put(erpTree.getName(), erpTree);

					for(Diagnostic diag : listeDiag)
					{
						Diagnostic_Tree diagnostic = new Diagnostic_Tree(diag.getNomDiagnostiqueur(),
								diag.getIntituleDiagnostic(), erpTree, diag.getIdDiagnostic());
						erpTree.getDiagnostics().add(diagnostic);
					}
				}
			}

			if (enfants == 0)
			{
				typeERPCache2.remove(typeTree.getName());
				rootNodes2.remove(typeTree);
			}
		}
	}

	public void chargerArborescence3() {

		for (TypeErp type : listeTypeERP)
		{
			int enfants = 0;

			TypeERP_Tree typeTree = new TypeERP_Tree();
			typeTree.setName(type.getTypeErpCourt());
			typeTree.setType("typeErp");

			typeERPCache3.put(typeTree.getName(), typeTree);
			rootNodes3.add(typeTree);

			for(Erp erp : listeERP)
			{
				List<Diagnostic> listeDiag = listerDiagnosticParErp(erp, listeDiagnosticsIntervArchive);

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp() && listeDiag.size() > 0)
				{
					enfants++;

					ERP_Tree erpTree = new ERP_Tree();
					erpTree.setName(erp.getNomErp() + " (" + listeDiag.size() + ")");
					erpTree.setType("erp");

					erpTree.setParent(typeTree);
					erpTree.setTypeErp(typeTree);				

					typeTree.getERPs().add(erpTree);

					erpCache3.put(erpTree.getName(), erpTree);

					for(Diagnostic diag : listeDiag)
					{
						Diagnostic_Tree diagnostic = new Diagnostic_Tree(diag.getNomDiagnostiqueur(),
								diag.getIntituleDiagnostic(), erpTree, diag.getIdDiagnostic());
						erpTree.getDiagnostics().add(diagnostic);
					}
				}
			}

			if (enfants == 0)
			{
				typeERPCache3.remove(typeTree.getName());
				rootNodes3.remove(typeTree);
			}
		}
	}

	public List<Diagnostic> listerDiagnosticParErp(Erp erp, List<Diagnostic> listeBrute){
		List<Diagnostic> listeResultat = new ArrayList<Diagnostic>();
		for (Diagnostic d : listeBrute)
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
		return rootNodes1;
	}

	public void setRootNodes(List<TreeNode> rootNodes) {
		this.rootNodes1 = rootNodes;
	}

	public TreeNode getCurrentSelection() {
		return currentSelection;
	}

	public void setCurrentSelection(TreeNode currentSelection) {
		this.currentSelection = currentSelection;
	}

	public Map<String, Diagnostic_Tree> getDiagnosticCache() {
		return diagnosticCache1;
	}

	public void setDiagnosticCache(Map<String, Diagnostic_Tree> diagnosticCache) {
		this.diagnosticCache1 = diagnosticCache;
	}

	public Map<String, ERP_Tree> getErpCache() {
		return erpCache1;
	}

	public void setErpCache(Map<String, ERP_Tree> erpCache) {
		this.erpCache1 = erpCache;
	}

	public Map<String, TypeERP_Tree> getTypeERPCache() {
		return typeERPCache1;
	}

	public void setTypeERPCache(Map<String, TypeERP_Tree> typeERPCache) {
		this.typeERPCache1 = typeERPCache;
	}

	public IBusinessDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IBusinessDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

	public IBusinessErp getProxyERP() {
		return proxyERP;
	}

	public void setProxyERP(IBusinessErp proxyERP) {
		this.proxyERP = proxyERP;
	}

	public List<TypeErp> getListeTypeERP() {
		return listeTypeERP;
	}

	public void setListeTypeERP(List<TypeErp> listeTypeERP) {
		this.listeTypeERP = listeTypeERP;
	}

	public List<Erp> getListeERP() {
		return listeERP;
	}

	public void setListeERP(List<Erp> listeERP) {
		this.listeERP = listeERP;
	}

	public List<Diagnostic> getListeDiag() {
		return listeDiagnostic;
	}

	public void setListeDiag(List<Diagnostic> listeDiag) {
		this.listeDiagnostic = listeDiag;
	}

	public List<Diagnostic> getListeDiagnosticsIntervEnCours() {
		return listeDiagnosticsIntervEnCours;
	}

	public void setListeDiagnosticsIntervEnCours(
			List<Diagnostic> listeDiagnosticsIntervEnCours) {
		this.listeDiagnosticsIntervEnCours = listeDiagnosticsIntervEnCours;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TreeNode> getRootNodes1() {
		return rootNodes1;
	}

	public void setRootNodes1(List<TreeNode> rootNodes1) {
		this.rootNodes1 = rootNodes1;
	}

	public List<TreeNode> getRootNodes2() {
		return rootNodes2;
	}

	public void setRootNodes2(List<TreeNode> rootNodes2) {
		this.rootNodes2 = rootNodes2;
	}

	public List<TreeNode> getRootNodes3() {
		return rootNodes3;
	}

	public void setRootNodes3(List<TreeNode> rootNodes3) {
		this.rootNodes3 = rootNodes3;
	}

	public List<Diagnostic> getListeDiagnostic() {
		return listeDiagnostic;
	}

	public void setListeDiagnostic(List<Diagnostic> listeDiagnostic) {
		this.listeDiagnostic = listeDiagnostic;
	}

	public List<Diagnostic> getListeDiagnosticsIntervAttente() {
		return listeDiagnosticsIntervAttente;
	}

	public void setListeDiagnosticsIntervAttente(
			List<Diagnostic> listeDiagnosticsIntervAttente) {
		this.listeDiagnosticsIntervAttente = listeDiagnosticsIntervAttente;
	}

	public List<Diagnostic> getListeDiagnosticsIntervArchive() {
		return listeDiagnosticsIntervArchive;
	}

	public void setListeDiagnosticsIntervArchive(
			List<Diagnostic> listeDiagnosticsIntervArchive) {
		this.listeDiagnosticsIntervArchive = listeDiagnosticsIntervArchive;
	}
}