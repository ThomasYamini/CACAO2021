package abstraction.eq5Transformateur3;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;


//Léna

public class Transformateur3VenteContratCadre extends Transformateur3Fabricant implements IVendeurContratCadre{

	protected HashMap<ExemplaireContratCadre, Integer> contrats ;
	
	public Transformateur3VenteContratCadre() {
		this.contrats = new HashMap<ExemplaireContratCadre, Integer>();
	}
	public HashMap<ExemplaireContratCadre, Integer> getContrats() {
		return contrats;
	}
	public int getEtape(ExemplaireContratCadre contrat) {
		return this.getContrats().get(contrat);
	}
	
	public boolean peutVendre(Object produit) {
		if (produit == Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE || produit == Chocolat.TABLETTE_MOYENNE || produit == Chocolat.CONFISERIE_MOYENNE) {
			return true; }
		else { return false; }
	}

	@Override
	public boolean vend(Object produit) {
		Chocolat choco = ((ChocolatDeMarque)produit).getChocolat();
		System.out.println(produit);
		if (!this.getChocolatsProduits().contains(produit)) {
			System.out.println("false");
			return false;
		}
		else if(this.getChocolats().get(choco).getValeur()>0){
			System.out.println("true");
			return true;
		}
		else { 
			System.out.println("false2");
			return false;}

	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getProduit().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) || contrat.getProduit().equals(Chocolat.TABLETTE_MOYENNE) || contrat.getProduit().equals(Chocolat.CONFISERIE_MOYENNE)) {
			if (contrat.getEcheancier().getQuantiteTotale() <= this.getChocolats().get(contrat.getProduit()).getValeur()) {
				return contrat.getEcheancier(); } 
			else { 
				return null; } }
		else { return null; }
	}
		
	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (contrat.getProduit().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE)) {
			return this.prix_tablette_equi.getValeur()*contrat.getQuantiteTotale();	}
		if (contrat.getProduit().equals(Chocolat.TABLETTE_MOYENNE)) {
			return this.prix_tablette.getValeur()*contrat.getQuantiteTotale(); }
		if (contrat.getProduit().equals(Chocolat.CONFISERIE_MOYENNE)) {
			return this.prix_confiserie.getValeur()*contrat.getQuantiteTotale(); }
		else { return -1; }
		
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		
		if (contrat.getProduit().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE)) {
			if (contrat.getPrix()>this.prix_min_vente_EQ.getValeur()) {
				return contrat.getPrix();}
			else { return this.prix_min_vente_EQ.getValeur()*contrat.getQuantiteTotale(); } }
		
		if (contrat.getProduit().equals(Chocolat.TABLETTE_MOYENNE)) {
			if (contrat.getPrix()>this.prix_min_vente_MG.getValeur()) {
				return contrat.getPrix(); }
			else { return this.prix_min_vente_MG.getValeur()*contrat.getQuantiteTotale();} }
			
		if (contrat.getProduit().equals(Chocolat.CONFISERIE_MOYENNE)) {
			if (contrat.getPrix()>this.prix_min_vente_confiserie.getValeur()) {
				return contrat.getPrix(); }
		    else { return this.prix_min_vente_confiserie.getValeur()*contrat.getQuantiteTotale(); } }
		
		else { return -1;} 
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.JournalVenteContratCadre.ajouter("nouveau contrat cadre vente " + "avec " + contrat.getAcheteur() + ". Vente de " + contrat.getQuantiteTotale() + "de " + contrat.getProduit() + "pendant " + contrat.getEcheancier() + "pour " + contrat.getPrix());
		this.getContrats().put(contrat, Filiere.LA_FILIERE.getEtape());
	}
	
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = Math.min(this.getChocolats().get(produit).getValeur(), quantite);
		if (livre>0.0) {
			this.retirer((Chocolat)produit, livre);
		}
		return livre;
	}
	
}
	
