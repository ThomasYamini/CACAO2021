package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

//Manuelo
public class Transformateur3AchatContratCadre extends Transformateur3Stock implements IAcheteurContratCadre {
	
	public Echeancier contrePropositionDeLAcheteur1(ExemplaireContratCadre contrat) {
		List<Echeancier> precedentesPropositions = contrat.getEcheanciers();
		Echeancier dernierEcheancier = contrat.getEcheancier();
		int stepDebut = dernierEcheancier.getStepDebut();
		int nbStep = dernierEcheancier.getNbEcheances();
		double quantiteParStep = dernierEcheancier.getQuantite(stepDebut);
		int stepCourant = Filiere.LA_FILIERE.getEtape();
		
		if ((nbStep>12) || (quantiteParStep>=1000)) {
			return null;
		} else{
			return dernierEcheancier;
		} 
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		List<Double> precedentesPropositions = contrat.getListePrix();
		Double dernierPrix = contrat.getPrix();
		if (dernierPrix>this.prix_max_fèves.getValeur()) {
			return -1;
		} else {
			return dernierPrix;
		} 
	}	
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.ajouter((Feve)produit, quantite);
	}

	@Override
	public String getNom() {
		return this.getNom();
	}

	@Override
	public String getDescription() {
		return this.getDescription();
	}

	@Override
	public Color getColor() {
		return new Color(233, 30, 99);
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getNomsFilieresProposees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filiere getFiliere(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variable> getIndicateurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variable> getParametres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Journal> getJournaux() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCryptogramme(Integer crypto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationFaillite(IActeur acteur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationOperationBancaire(double montant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
	}

}
