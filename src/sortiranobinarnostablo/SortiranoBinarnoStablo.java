package sortiranobinarnostablo;


public class SortiranoBinarnoStablo {
	
	public CvorStabla koren;
	
	public SortiranoBinarnoStablo () {
		koren = null;
	}
	
	public String pronadjiPoKljucu(CvorStabla koren, int kljuc) throws Exception{
		if(daLiJePraznoStablo())
			throw new Exception("Stablo je prazno");
		if(koren.kljuc == kljuc)
			return koren.podatak;
		if(kljuc < koren.kljuc && koren.levo != null) 
			return pronadjiPoKljucu(koren.levo, kljuc);
		else {
			if(koren.desno != null)
				return pronadjiPoKljucu(koren.desno, kljuc);
		}
		return null;
	}
	
	
	
	//neke dodatne metode, ali nije uradjeno opciono balansiranje
	public CvorStabla pronadjiCvorPoKljucu(CvorStabla koren, int kljuc) {
		if(daLiJePraznoStablo())
			return null;
		if(koren.kljuc == kljuc || koren == null)
			return koren;
		if(kljuc < koren.kljuc)
			return pronadjiCvorPoKljucu(koren.levo, kljuc);
		else 
			return pronadjiCvorPoKljucu(koren.desno, kljuc);
	}
	
	public CvorStabla pronadjiMax(CvorStabla koren) {
		while(koren.desno != null)
			koren = koren.desno;
		return koren;
	}
	
	public void ubaciUStablo(CvorStabla koren, int kljuc, String podatak) {
		if(daLiJePraznoStablo())
			koren = new CvorStabla(podatak, kljuc);
		else {
			if(kljuc < koren.kljuc) {
				if(koren.levo != null)
					ubaciUStablo(koren.levo, kljuc, podatak);
				else
					koren.levo = new CvorStabla(podatak, kljuc);
			}
			else {
				if(koren.desno != null)
					ubaciUStablo(koren.desno, kljuc, podatak);
				else
					koren.desno = new CvorStabla(podatak, kljuc);
				
			}
		}
	}
	
	public CvorStabla nadjiRoditelja(CvorStabla koren, CvorStabla dete) {
		if(koren == null || dete == null)
			return null;
		if(dete.kljuc < koren.kljuc) {
			if(koren.levo != null && koren.levo == dete)
				return koren;
			return  nadjiRoditelja(koren.levo, dete);
		} else {
			if(koren.desno != null && koren.desno == dete)
				return koren;
			return  nadjiRoditelja(koren.desno, dete);
		}
	}
	
	public void izbaciListPolulist(CvorStabla cvor) {
		CvorStabla roditelj = nadjiRoditelja(koren, cvor);
		CvorStabla dete = null;
		dete = cvor.levo != null ? cvor.levo : cvor.desno;
		if(roditelj == null)
			koren = dete;
		else{
			if(roditelj.levo == cvor) 
				roditelj.levo = dete;
			else
				roditelj.desno = dete;
		}
	}
	
	public void izbaciIzStablaPoKljucu(CvorStabla koren, int kljuc) {
		CvorStabla cvor =  pronadjiCvorPoKljucu(koren, kljuc);
		if(cvor == null)
			return;
		if(cvor.levo != null && cvor.desno != null) {
			CvorStabla maxLevo = pronadjiMax(koren.levo);
			cvor.podatak = maxLevo.podatak;
			izbaciListPolulist(maxLevo);
		}
		else {
			izbaciListPolulist(cvor);
		}
	}
	
	public int visinaStabla(CvorStabla koren) {
		if(koren == null)
			return 0;
		return 1 + Math.max(visinaStabla(koren.desno), visinaStabla(koren.levo));
	}
	
	public boolean daLiJePraznoStablo() {
		return koren== null;
	}
}

