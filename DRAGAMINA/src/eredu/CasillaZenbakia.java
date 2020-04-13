package eredu;

public class CasillaZenbakia extends Casilla{
	public CasillaZenbakia(int x, int y, int bal) {
		super(x,y,bal);
		egoera = new Itxita();
		klika = new EzKlikatua();
	}
	
}
