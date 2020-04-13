package bista;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import eredu.Matrizea;

public class Panela extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Panela nirePanela=null;
	private JMenuBar barra;
	private JMenu Jokua,Laguntza;
	private JMenuItem erreza,ertaina,zaila,esteka,joku_berria;
	//private JPanel contentPane;
	private JPanel panelGelaxkak;
	private JPanel panelKont;
	private JPanel panelAurpegi;
	private boolean bukatua=false;
	private JLabel minaKontZifra;
	private JLabel minaKontZifra_1;
	private JLabel minaKontZifra_2;
	private JLabel btnAurpegi;

	
	
	JLabel[] listaCasillas;

	
	private Panela() {
		initialize();
	}
	
	public static Panela getNirePanela() {
		if(nirePanela==null) {
			nirePanela=new Panela();
		}
		return nirePanela;
	}
	
	private void initialize() {
		barra = new JMenuBar();
		Jokua = new JMenu("Jokua");
		Laguntza = new JMenu("Laguntza");
		joku_berria = new JMenuItem("Joku Berria");
		erreza = new JMenuItem("Erreza");
		ertaina = new JMenuItem("Ertaina");
		zaila = new JMenuItem("Zaila");
		esteka = new JMenuItem("Esteka");
		Jokua.add(joku_berria);
		Jokua.add(erreza);
		Jokua.add(ertaina);
		Jokua.add(zaila);
		Laguntza.add(esteka);	
		barra.add(Jokua);
		barra.add(Laguntza);
		this.setJMenuBar(barra);
		
		
	}

	public JPanel getPanelGelaxkak() {
		if (panelGelaxkak == null) {
			
		}
		return panelGelaxkak;
	}
	public JPanel getPanelKont() {

		return panelKont;
	}
	public JPanel getPanelAurpegi() {
		if (panelAurpegi == null) {
			
		}
		return panelAurpegi;
	}
	
	public JLabel[] getListaCasillas() {
		return listaCasillas;
		
	}
	
	public void setListaCasillas(JLabel[] lc) {
		listaCasillas=lc;
	}
	

   
	public JLabel getminaKontZifra() {
		return minaKontZifra;
	}
   
	public JLabel getminaKontZifra_1() {
		return minaKontZifra_1;
	}
	public JLabel getminaKontZifra_2() {
		return minaKontZifra_2;
	}
	
	public void setminaKontZifra(JLabel m) {
		minaKontZifra=m;
	}
   
	public void setminaKontZifra_1(JLabel m) {
		minaKontZifra_1=m;
	}
	public void setminaKontZifra_2(JLabel m) {
		minaKontZifra_2=m;
	}
	
	public JLabel getBtnAurpegi() {
		return btnAurpegi;
	}
	
	public void setBtnAurpegi(JLabel b){
		btnAurpegi=b;
	}
	

	public void setPanelKont(JPanel pk) {
		panelKont=pk;
	}

	public void setNirePanela(Panela np) {
		nirePanela=null;
	}

	public JMenuItem getMenu(String s) {
		switch(s) {
		case "erreza":
			return this.erreza;
		case "ertaina":
			return this.ertaina;
		case "zaila":
			return this.zaila;
		case "joku_berria":
			return this.joku_berria;
		case "esteka":
			return this.esteka;
		}
		return null;
	}
	
	public void setMenu(String s, JMenuItem jm) {
		switch(s) {
		case "erreza":
			this.erreza=jm;
		case "ertaina":
			this.ertaina=jm;
		case "zaila":
			this.zaila=jm;
		case "joku_berria":
			this.joku_berria=jm;
		case "esteka":
			this.esteka=jm;
		}
	}

	public void setPanelAurpegi(JPanel pa) {
		panelAurpegi=pa;	
	}

	public void setPanelGelaxkak(JPanel pg) {
		panelGelaxkak=pg;
	}
	
	public void sortu(int nLerro, int nZutabe){
	   	for (int y = 0; y < nLerro; y++) {
	   	  for (int x = 0; x < nZutabe; x++) {
	   		JLabel gelaxkaBerri = gelaxkaSortu();
	   		JLabel[] lc = getListaCasillas();
	   		lc[Matrizea.getNireMatrizea1().getZ()*y+x]=gelaxkaBerri;
	   		getPanelGelaxkak().add(gelaxkaBerri,new GridBagConstraints(x, y, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	   	  }
	    }
	}

	private JLabel gelaxkaSortu(){
		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createLoweredBevelBorder());
		label.setIcon(new ImageIcon("res/tablero.gif"));
		label.setMaximumSize(new Dimension(20, 20));
		label.setMinimumSize(new Dimension(18, 18));
		label.setSize(new Dimension(18, 18));
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0){
				if(!bukatua) {
					int zenbakia = Matrizea.getNireMatrizea1().bilatu(getListaCasillas(),label);
					if(SwingUtilities.isLeftMouseButton(arg0)) { //EZKERREKO BOTOIA
						Matrizea.getNireMatrizea1().clickEzkerra(zenbakia,label);						
					}else if (SwingUtilities.isRightMouseButton(arg0)) { //ESKUINEKO BOTOIA
						Matrizea.getNireMatrizea1().clickEskuina(zenbakia,label);
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		return label;
	}
}	