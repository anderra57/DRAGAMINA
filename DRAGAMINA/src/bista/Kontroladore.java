package bista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import eredu.Casilla;
import eredu.CasillaHutsa;
import eredu.CasillaMina;
import eredu.Matrizea;

public class Kontroladore implements ActionListener, Observer{
	
	Panela panela;
	IrabazlePanela irabazlePanela;
	Matrizea m = Matrizea.getNireMatrizea1();
	JLabel[] listaCasillas;
	private boolean bukatua = false;
	
	public Kontroladore() {
		Matrizea.getNireMatrizea1().addObserver(this);
		panela=Panela.getNirePanela();
		irabazlePanela=IrabazlePanela.getNireIrabazlePanela();
	}
	
	public void hasieratu() {
		Matrizea.getNireMatrizea1().addObserver(this);
		panela = Panela.getNirePanela();
		irabazlePanela = IrabazlePanela.getNireIrabazlePanela();
		panelaHasieratu();
		menuaHasieratu();
		panelGelaxkakHasieratu();
		panelAurpegiHasieratu();
		panelKontHasieratu();
		panela.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) { // Metodo hau daukagu menuaren edozein aukera click-atzen dugunean.
		Matrizea m = Matrizea.getNireMatrizea1();
		panela.setVisible(false);
		panela.setNirePanela(null);
		if(e.getSource()==panela.getMenu("erreza")) {
			m.menuaAukeratu(1);
		}else if(e.getSource()==panela.getMenu("ertaina")) {
			m.menuaAukeratu(2);
		}else if(e.getSource()==panela.getMenu("zaila")){
			m.menuaAukeratu(3);
		}else if(e.getSource()==panela.getMenu("joku_berria")){
			m.menuaAukeratu(4);			
		}else {
			m.menuaAukeratu(5);
		}
		hasieratu();
	}
	
	public void panelaHasieratu() {
		panela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panela.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		panela.setTitle("Dragamina");
		panela.setLocationRelativeTo(null);
		leihoarenTamaina(m.getZ());
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		panela.setContentPane(contentPane);
		panela.setVisible(true);
		panela.setResizable(false);
		panela.setLocationRelativeTo(null);
		m.setpartidaHasiera(System.currentTimeMillis());
	}
	
	private void leihoarenTamaina(int z) {
		if (z==10) {
			panela.setSize(300,300);
		}
		else if (z==15) {
			panela.setSize(380,380);
		}
		else {
			panela.setSize(580,400);
		}
	}
	
	private void menuaHasieratu(){
		panela.getMenu("joku_berria").addActionListener(this);
		panela.getMenu("erreza").addActionListener(this);
		panela.getMenu("ertaina").addActionListener(this);
		panela.getMenu("zaila").addActionListener(this);
		panela.getMenu("esteka").addActionListener(this);
	}

	private void panelGelaxkakHasieratu(){
		JPanel panelGelaxkak = new JPanel();
		panelGelaxkak.setLayout(new GridBagLayout());
		panela.setPanelGelaxkak(panelGelaxkak);
		JLabel[] listaGelaxka = new JLabel[m.getE()*m.getZ()];
		listaCasillas=listaGelaxka;		
		sortu(m.getE(),m.getZ());		
		panela.getContentPane().add(panela.getPanelGelaxkak(), BorderLayout.CENTER);
	}
	
	
	
	private void panelAurpegiHasieratu() {
		JLabel btnAurpegi = new JLabel();
		btnAurpegi.setIcon(new ImageIcon("res/cara1.gif"));		
		btnAurpegi.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				if(m.getAmaiera()) {
					BerriroJokatu berriz = new BerriroJokatu();
					berriz.setIzena(Matrizea.getNireMatrizea1().getIzena());
					berriz.setVisible(true);
				}else {
						aurpegianKlikEgin(m.getZailtasuna(),m.getIzena());
					
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
		
		JPanel panelAurpegi = new JPanel();
		panelAurpegi.add(btnAurpegi);
		panela.setBtnAurpegi(btnAurpegi);
		panela.setPanelAurpegi(panelAurpegi);
		panela.getContentPane().add(panela.getPanelAurpegi(), BorderLayout.SOUTH);
	}

	private void panelKontHasieratu() {
		JPanel panelKont = new JPanel();
		JLabel minaKontZifra = new JLabel();
		minaKontZifra.setIcon(new ImageIcon("res/n0.gif"));
		JLabel minaKontZifra_1 = new JLabel();
		minaKontZifra_1.setIcon(new ImageIcon("res/n0.gif"));
		JLabel minaKontZifra_2 = new JLabel();
		minaKontZifra_2.setIcon(new ImageIcon("res/n0.gif"));
		panela.setminaKontZifra(minaKontZifra);
		panela.setminaKontZifra_1(minaKontZifra_1);
		panela.setminaKontZifra_2(minaKontZifra_2);
		panelKont.add(panela.getminaKontZifra());
		panelKont.add(panela.getminaKontZifra_1());
		panelKont.add(panela.getminaKontZifra_2());
		panela.setPanelKont(panelKont);
		panela.getContentPane().add(panela.getPanelKont(), BorderLayout.NORTH);
	}

	public void hasieratuIrabazlePanela() {
		
		irabazlePanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		irabazlePanela.setSize(400, 500);
		irabazlePanela.setLocationRelativeTo(null);
		irabazlePanela.setResizable(false);
		irabazlePanela.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		irabazlePanela.setTitle("Dragamina");
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		irabazlePanela.setContentPane1(contentPane);
		irabazlePanela.setContentPane(irabazlePanela.getContentPane1());
		
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(0, 2, 0, 0));
		irabazlePanela.setPanelCenter(panelCenter);
		irabazlePanela.getContentPane1().add(irabazlePanela.getPanelCenter(), BorderLayout.CENTER);
		
		for (int i=1;i<11;i++) {
			JLabel lblIzena = new JLabel();
			lblIzena.setHorizontalAlignment(SwingConstants.CENTER);
			irabazlePanela.setLabel(lblIzena, i, 1);
			irabazlePanela.getPanelCenter().add(irabazlePanela.getLabel(i, 1));
			
			JLabel lblPunt = new JLabel();
			lblPunt.setHorizontalAlignment(SwingConstants.CENTER);
			irabazlePanela.setLabel(lblPunt, i, 2);
			irabazlePanela.getPanelCenter().add(irabazlePanela.getLabel(i, 2));
		}
		
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new EmptyBorder(10, 0, 20, 0));
		panelNorth.setLayout(new GridLayout(0, 1, 0, 0));
		irabazlePanela.setPanelNorth(panelNorth);
		contentPane.add(irabazlePanela.getPanelNorth(), BorderLayout.NORTH);
		
		
		JLabel lblTitle = new JLabel("IRABAZLEEN ZERRENDA:");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		irabazlePanela.getPanelNorth().add(lblTitle);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		irabazlePanela.setPanelSouth(panelSouth);
		contentPane.add(irabazlePanela.getPanelSouth(), BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("JARRAITU");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IrabazlePanela.getNireIrabazlePanela().setVisible(false);
				BerriroJokatu berriro = new BerriroJokatu();
				berriro.setVisible(true);
				
			}
		});
		irabazlePanela.getPanelSouth().add(btnNewButton);
	}

	public void eguneratuMinaKont() {
		int mKop=m.getMinaKop();
		if (mKop>=0) {
			int hamarreko= mKop/10;
			int bateko= mKop%10;
			panela.getminaKontZifra().setIcon(new ImageIcon("res/n0.gif"));
			panela.getminaKontZifra_1().setIcon(new ImageIcon("res/n"+ hamarreko +".gif"));
			panela.getminaKontZifra_2().setIcon(new ImageIcon("res/n"+ bateko +".gif"));
		}
		else { mKop=-mKop;
			int hamarreko= mKop/10;
			int bateko= mKop%10;
			panela.getminaKontZifra().setIcon(new ImageIcon("res/n-.gif"));
			panela.getminaKontZifra_1().setIcon(new ImageIcon("res/n"+ hamarreko +".gif"));
			panela.getminaKontZifra_2().setIcon(new ImageIcon("res/n"+ bateko +".gif"));
		}
	}
	
	public void banderakBegiratu() {
		Iterator<Integer> it=m.getListaBanderak().iterator();
		int pos;
		while(it.hasNext()) {
			pos=it.next();
			Casilla c= m.balioaBueltatu(pos/m.getZ(), pos%m.getZ());
			if(!(c instanceof CasillaMina )) {
				listaCasillas[pos].setIcon(new ImageIcon("res/mina-x.gif"));
			}
		}
	}
	

	
	
	////////////////////OBSERVERREKO UPDATE-A////////////////////////
	
	public void update(Observable arg0, Object arg1) {//arg1-->klikatutako kasilla
		Panela panela = Panela.getNirePanela();
		
		if(!m.getAmaiera()) {//kasilla klikatu dugu
			JLabel label = listaCasillas[((Casilla)arg1).posizioa()];//LORTUKO DUGU KASILLA HORREN LABEL-A
			
			if(((Casilla)arg1).getEgoera()==0) {//Ireki dugu kasilla(CLICK EZKERRA)
				if(arg1 instanceof CasillaMina) {
					panela.getBtnAurpegi().setIcon(new ImageIcon("res/cara2.gif"));
					banderakBegiratu();
					label.setIcon(new ImageIcon("res/mina-n.gif"));
					panela.getminaKontZifra().setIcon(new ImageIcon("res/n-.gif"));
					panela.getminaKontZifra_1().setIcon(new ImageIcon("res/n-.gif"));
					panela.getminaKontZifra_2().setIcon(new ImageIcon("res/n-.gif"));
				}else if(arg1 instanceof CasillaHutsa) {
					label.setIcon(new ImageIcon("res/c0.gif"));
					
				}else {
					label.setIcon(new ImageIcon("res/c"+((Casilla) arg1).getBalioa()+".gif"));
				}
				
			}else if(((Casilla)arg1).getEgoera()==1) {//bandera jarri dugu
				eguneratuMinaKont();
				label.setIcon(new ImageIcon("res/bandera.gif"));
			}else if(((Casilla)arg1).getEgoera()==3) {//BANDERA BATEN GAINEAN CLICK ESKUINA EGINEZ, GALDERA IKURRA ATERA
				eguneratuMinaKont();
				label.setIcon(new ImageIcon("res/marca.gif"));
			}else {//bandera kendu dugu
					eguneratuMinaKont();
					label.setIcon(new ImageIcon("res/tablero.gif"));
			}
			
		}else {//partida amaitu da, bakarrik aktibatuko da partida irabazten badugu
			amaierakoBanderak();
			panela.getBtnAurpegi().setIcon(new ImageIcon("res/cara3.gif"));
			hasieratuIrabazlePanela();
		}
	}
	
	
	////////////////////INTERFAZEKO KASILLAK SORTU//////////////////////
	public void sortu(int nLerro, int nZutabe){
	   	for (int y = 0; y < nLerro; y++) {
	   	  for (int x = 0; x < nZutabe; x++) {
	   		JLabel gelaxkaBerri = gelaxkaSortu();
	   		listaCasillas[Matrizea.getNireMatrizea1().getZ()*y+x]=gelaxkaBerri;
	   		panela.getPanelGelaxkak().add(gelaxkaBerri,new GridBagConstraints(x, y, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
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
					int zenbakia = Matrizea.getNireMatrizea1().bilatu(listaCasillas,label);
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
	
	
	///////////////////JOKO BERRIA HASIERATU(LEHENENGOA)///////////////////////

	public void jokoaLehenAldizHasieratu(int zailtasuna, String izena) {
		if(zailtasuna==1) {
			m.hasierakeraBotoia(zailtasuna, izena);
			hasieratu();
		}else if(zailtasuna==2) {
			m.hasierakeraBotoia(zailtasuna, izena);
			hasieratu();
		}else {
			m.hasierakeraBotoia(zailtasuna, izena);
			hasieratu();
		}
	}
	
	
	///////////////////JOKOA BERRIZ ERE HASI(AURPEGIA)/////////////////////////
	public void aurpegianKlikEgin(int zailtasuna, String izena) { //aurpegia klikatzen denean, joko berria hasieratuko da
		panela.setVisible(false);
		panela.setNirePanela(null);
		if(zailtasuna==1) {
			m.aurpegiaKlikatu(zailtasuna);
			hasieratu();
		}else if(zailtasuna==2) {
			m.aurpegiaKlikatu(zailtasuna);
			hasieratu();
		}else {
			m.aurpegiaKlikatu(zailtasuna);
			hasieratu();
		}
	}
	
	////////////////////AMAIERAKO PANELA////////////////////////////
	public void amaierakoBanderak() {
		Iterator<Integer> it=m.getListaMinak().iterator();
		int pos;
		while(it.hasNext()) {
			pos=it.next();
			listaCasillas[pos].setIcon(new ImageIcon("res/bandera.gif"));
		}
	}
}
