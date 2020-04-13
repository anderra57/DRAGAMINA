package eredu;

public class CasillaFactory {
	
	private static CasillaFactory nireFactory = null;
	
	private CasillaFactory() {
		
	}
	
	public static CasillaFactory getNireCasillaFactory() {
		if(nireFactory == null) {
			nireFactory = new CasillaFactory();
		}
		return nireFactory;
	}
	
	public Casilla casillaSortu(int zenb, int i, int j) {
		if(zenb==-1) {
			return new CasillaMina(i,j,zenb);
		}else if(zenb==0) {
			return new CasillaHutsa(i,j,zenb);
		}else {
			return new CasillaZenbakia(i,j,zenb);
		}
	}
}
