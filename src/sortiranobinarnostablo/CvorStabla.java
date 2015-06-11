package sortiranobinarnostablo;

public class CvorStabla {
	
	public String podatak;
	public int kljuc;
	public CvorStabla levo;
	public CvorStabla desno;
	
	public CvorStabla (String p, int k, CvorStabla l, CvorStabla d) {
		podatak = p;
		kljuc = k;
		levo = l;
		desno = d;	
	}
	
	public CvorStabla(String p, int k) {
		this(p, k, null, null);
	}
}
