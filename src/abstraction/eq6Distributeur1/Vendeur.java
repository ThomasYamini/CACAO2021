package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Banque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Variable;

public class Vendeur extends Stocks implements IDistributeurChocolatDeMarque{
	
	protected Map<ChocolatDeMarque,Double> historique;
	protected int quantiteTotaleVendue;// Quantite totale vendue en une période
	protected Map<ChocolatDeMarque,Double> quantiteChocoVendue; //Quantite par chocolat vendue
	protected Map<ChocolatDeMarque,Double> q; //Quantité définie pour chaque produit qu'on vend à partir duquel on considère que les ventes convenables
	
	public Vendeur() {
		super();
		this.historique=new HashMap <ChocolatDeMarque,Double>();
		this.quantiteTotaleVendue=0;
		this.quantiteChocoVendue=new HashMap<ChocolatDeMarque,Double>();
		for (int i=0; i<12; i++) {
			this.quantiteChocoVendue.put(this.getCatalogue().get(i), 0.0);
		}
		this.q=new HashMap <ChocolatDeMarque,Double>();
		/*On initialise l'historique, la quantité totale vendue et 
		Pour chaque type de chocolat on initialise un dictionnaire à quantite vendue =0
		*/
		
		for (int i=0; i<12; i++) {
			this.q.put(this.getCatalogue().get(i), 0.2*this.quantiteEnVente(this.getCatalogue().get(i)));
		}
		//Si les ventes sont inférieures à 20% du stock on diminue le prix de vente.
	}
	
	public Double getQuantiteVendue(ChocolatDeMarque choco) {
		return this.quantiteChocoVendue.get(choco);
	}
		
	
	@Override
	public List<ChocolatDeMarque> getCatalogue() {
		Set<ChocolatDeMarque> Catalogue = stock.keySet();
		List<ChocolatDeMarque> c = new ArrayList<>(Catalogue);
		return c;
	}//retourne le catalogue (liste des produits disponibles)

	@Override
	public double prix(ChocolatDeMarque choco) {
		if(choco!=null) {
			return prix.get(choco);
		}else {
			return 0;
		}
	}//retourne le prix de vente du chocolat "choco"

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stock.get(choco).getValeur();
		}
		else {
			return 0;
		}//retourne la quantité du chocolat choco en vente
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		if (choco!=null && this.stockTG.get(choco)!=null) {
			return this.stockTG.get(choco).getValeur();
		}
		else {
			return 0;
		}//retourne la quantité disponible du chocolat choco en tete de gondole
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		if(choco!=null && quantite>0 && quantite<this.quantiteEnVente(choco)) {
				this.ajouterStock(choco, (-1)*quantite, false);
				historique.put(choco, quantite);
				this.quantiteTotaleVendue+=quantite;
				this.quantiteChocoVendue.put(choco, this.getQuantiteVendue(choco)+quantite);
		}//on retire du stock ce qui a été vendu, on note ca dans l'historique et on ajoute la quantite à quantiteVendue
	}// on actualise aussi quantiteChocoVendue

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco) {
		if (quantiteEnVente(choco)==0) {
			System.out.println("Plus de : "+ choco.getMarque()+" en rayon");
		}
	}	
	public void NouveauPrix(ChocolatDeMarque choco, double prix, int QuantiteVendue) {
		//prix correspond au prix de vente initial
		if (this.getQuantiteVendue(choco)==0 || this.getQuantiteVendue(choco)<q.get(choco)) {
			this.setPrix(choco, prix*0.9); 
			//Si les ventes ne sont pas convenables, on baisse le prix de vente de 10% pour la prochaine période
		}
	}
	public void next() {
		super.next();
		this.quantiteTotaleVendue=0;
		this.quantiteChocoVendue.clear();
	}//méthode next qui remet les quantités à 0
}
