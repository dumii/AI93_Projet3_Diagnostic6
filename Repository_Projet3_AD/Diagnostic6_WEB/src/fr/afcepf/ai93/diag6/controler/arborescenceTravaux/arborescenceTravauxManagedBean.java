package fr.afcepf.ai93.diag6.controler.arborescenceTravaux;

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

@ManagedBean(name="mbArborescenceTravaux")
@SessionScoped
public class arborescenceTravauxManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//Accordéon 1
	private List<TreeNode> rootNodes1 = new ArrayList<TreeNode>();
	
	private Map<String, ERP_Tree> erpCache1 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache1 = new HashMap<String, TypeERP_Tree>();
	
	//Accordéon 2
	private List<TreeNode> rootNodes2 = new ArrayList<TreeNode>();
	
	private Map<String, ERP_Tree> erpCache2 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache2 = new HashMap<String, TypeERP_Tree>();
	
	//Accordéon 3
	private List<TreeNode> rootNodes3 = new ArrayList<TreeNode>();
	
	private Map<String, ERP_Tree> erpCache3 = new HashMap<String, ERP_Tree>();
	private Map<String, TypeERP_Tree> typeERPCache3 = new HashMap<String, TypeERP_Tree>();
	
	private TreeNode currentSelection = null;
	
	@EJB
	private IBusinessDiagnostic proxyDiagnostic; 
	@EJB
	private IBusinessErp proxyERP; 

	private List<TypeErp> listeTypeERP;
	private List<Erp> listeERP; 
	private List<Erp> listeERPIntervEnCours; 
	private List<Erp> listeERPIntervAttente;
	private List<Erp> listeERPIntervArchive; 
	
	//On doit définir le type et le nom pour chaque élément (class NamedNode)	
	@PostConstruct
	public void init() {

		listeTypeERP = proxyERP.recupererListeTypeERP();
		listeERP = proxyERP.recupereToutErp();

		initialisationListesERP();
		
		chargerArborescence1();
		chargerArborescence2();
		chargerArborescence3();
	}

	//Méthodes	
	public void initialisationListesERP()
	{
		listeERPIntervEnCours = new ArrayList<>(); 
		listeERPIntervAttente = new ArrayList<>();
		listeERPIntervArchive = new ArrayList<>();
		
		for (Erp erp : listeERP)
		{
			proxyDiagnostic.recupereToutDiagnosticParErp(erp);
			boolean enCours = proxyDiagnostic.interventionEnCoursSurERP();
			boolean enAttente = proxyDiagnostic.interventionEnAttenteSurERP();
			boolean archives = proxyDiagnostic.interventionArchivesSurERP();
			
			if (enCours)
			{
				listeERPIntervEnCours.add(erp);
			}
			else
			{
				if (enAttente)
				{
					listeERPIntervAttente.add(erp);
				}
				else
				{
					listeERPIntervArchive.add(erp);
				}
			}
		}
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

			for(Erp erp : listeERPIntervEnCours)
			{

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp())
				{
					enfants++;
					ERP_Tree erpTree = new ERP_Tree(erp.getNomErp(),
								erp.getNomErp(), typeTree, erp.getIdErp());
					typeTree.getERPs().add(erpTree);
				}
			}			
			if (enfants == 0)
			{
				typeERPCache1.remove(typeTree.getName());
				rootNodes1.remove(typeTree);
			}
			else
			{
				typeTree.setName(type.getTypeErpCourt()+ " (" + enfants + ")");
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

			for(Erp erp : listeERPIntervAttente)
			{

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp())
				{
					enfants++;
					ERP_Tree erpTree = new ERP_Tree(erp.getNomErp(),
								erp.getNomErp(), typeTree, erp.getIdErp());
					typeTree.getERPs().add(erpTree);
				}
			}

			if (enfants == 0)
			{
				typeERPCache2.remove(typeTree.getName());
				rootNodes2.remove(typeTree);
			}
			else
			{
				typeTree.setName(type.getTypeErpCourt()+ " (" + enfants + ")");
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

			for(Erp erp : listeERPIntervArchive)
			{

				int idTypeERP = erp.getTypeErp().getIdTypeErp();

				if (idTypeERP == type.getIdTypeErp())
				{
					enfants++;
					ERP_Tree erpTree = new ERP_Tree(erp.getNomErp(),
								erp.getNomErp(), typeTree, erp.getIdErp());
					typeTree.getERPs().add(erpTree);
				}
			}

			if (enfants == 0)
			{
				typeERPCache3.remove(typeTree.getName());
				rootNodes3.remove(typeTree);
			}
			else
			{
				typeTree.setName(type.getTypeErpCourt()+ " (" + enfants + ")");
			}
		}
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

	
	//Getters et Setters
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

	public List<Erp> getListeERPIntervEnCours() {
		return listeERPIntervEnCours;
	}

	public void setListeERPIntervEnCours(List<Erp> listeERPIntervEnCours) {
		this.listeERPIntervEnCours = listeERPIntervEnCours;
	}

	public List<Erp> getListeERPIntervAttente() {
		return listeERPIntervAttente;
	}

	public void setListeERPIntervAttente(List<Erp> listeERPIntervAttente) {
		this.listeERPIntervAttente = listeERPIntervAttente;
	}

	public List<Erp> getListeERPIntervArchive() {
		return listeERPIntervArchive;
	}

	public void setListeERPIntervArchive(List<Erp> listeERPIntervArchive) {
		this.listeERPIntervArchive = listeERPIntervArchive;
	}
}