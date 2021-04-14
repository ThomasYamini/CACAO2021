package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;

public class Vendeur extends Stocks implements IDistributeurChocolatDeMarque{
	

	@Override
	public List<ChocolatDeMarque> getCatalogue() {
		Set<ChocolatDeMarque> Catalogue = stock.keySet();
		List<ChocolatDeMarque> c = new ArrayList<>(Catalogue);
		return c;
	}

	@Override
	public double prix(ChocolatDeMarque choco) {
		if(choco!=null) {
			return prix.get(choco);
		}
		return 0;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stock.get(choco).getValeur() + quantiteEnVenteTG(choco);
		}
		else {
			return 0;
		}
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stockTG.get(choco).getValeur();
		}
		else {
			return 0;
		}
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		
	}

}
