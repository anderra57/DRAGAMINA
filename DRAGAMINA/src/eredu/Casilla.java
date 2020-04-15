package eredu;

public abstract class Casilla {
	protected int i;
	protected int j;
	protected int balioa;
	protected Egoera egoera;
	
	public Casilla(int x, int y, int bal) {
		i=x;
		j=y;
		balioa = bal;
		
	}
	
	public int posizioa() {
		Matrizea m = Matrizea.getNireMatrizea1();
		return (m.getZ()*i+j);
	}
	
	public int geti() {
		return i;
	}
	
	public int getj() {
		return j;
	}
	
	public int getBalioa() {
		return balioa;
	}
	
	public void egoeraAldatu(int zenbakia) {
		switch(zenbakia) {
		case 0:
			egoera = new Irekita();
			break;
		case 1:
			egoera = new Bandera();
			break;
		case 2:
			egoera = new Itxita();
			break;
		}
	}
	
	public int getEgoera() {
		if(egoera instanceof Irekita) {
			return 0;
		}else if(egoera instanceof Itxita) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public void kudeatu() {
		egoera.Kudeatu();
	}

}
