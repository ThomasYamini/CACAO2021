package abstraction.eq2Producteur2;

import abstraction.fourni.Filiere;

public class Producteur2Banque extends Producteur2VeudeurFeveCC {

	public Producteur2Banque() {
		super();
	}

	public double coutTotDuStep() { // cout a payer a ce step
		double cout = 0;
		// cout stockage
		return cout;
	}
	
	public void perdreArgent(double montant) {
		Filiere.LA_FILIERE.getBanque().virer( Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme, Filiere.LA_FILIERE.getBanque(), montant);
	}

	
}
