package bista;

import java.awt.BorderLayout;
import java.awt.Desktop;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import eredu.Bandera;
import eredu.Casilla;
import eredu.CasillaHutsa;
import eredu.CasillaMina;
import eredu.Matrizea;

public class Kontroladore implements ActionListener, Observer{
	
	Panela panela;
	IrabazlePanela irabazlePanela;
	Matrizea m = Matrizea.getNireMatrizea1();
	//private static boolean emanda = false;
	//private boolean bukatua = false;
	
	public String jokalariarenIzena;
	public long partidaHasiera;
	
	public Kontroladore() {
		Matrizea.getNireMatrizea1().addObserver(this);
		panela=Panela.getNirePanela();
		irabazlePanela=IrabazlePanela.getNireIrabazlePanela();
	}
	
	public void hasieratu() {	
		panelaHasieratu();
		menuaHasieratu();
		panelGelaxkakHasieratu();
		panelAurpegiHasieratu();
		panelKontHasieratu();
		panela.setVisible(true);
	}
	
	/*@Override
	public void update(Observable arg0, Object arg1) {
		
		
		
	}*/

	@Override
	public void actionPerformed(ActionEvent e) { // Metodo hau daukagu menuaren edozein aukera click-atzen dugunean.
		Matrizea m = Matrizea.getNireMatrizea1();
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
		panelKontHasieratu();
		panelAurpegiHasieratu();
		partidaHasiera=System.currentTimeMillis();
	}
	
	private void leihoarenTamaina(int z) {
		if (z==10) {
			panela.setSize(300,300);
		}
		else if (z==15) {
			panela.setVisible(true);	panela.setSize(380,380);
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
		JLabel[] listaCasillas = new JLabel[m.getE()*m.getZ()];
		panela.setListaCasillas(listaCasillas);		
		Panela.getNirePanela().sortu(m.getE(),m.getZ());		
		panela.getContentPane().add(panela.getPanelGelaxkak(), BorderLayout.CENTER);
	}
		
	
	
	
	public void setIzena(String s) {
		jokalariarenIzena=s;
	}
	

	
	private void panelAurpegiHasieratu() {
		JLabel btnAurpegi = new JLabel();
		btnAurpegi.setIcon(new ImageIcon("res/cara1.gif"));		
		btnAurpegi.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				Matrizea.getNireMatrizea1().aurpegiaKlikatu();
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
		
		JButton btnJarraitu = new JButton("JARRAITU");
		btnJarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Matrizea.getNireMatrizea1().amaituPanela();
			}
		});
		panelAurpegi.add(btnJarraitu);
		btnJarraitu.setVisible(false);
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
		m.hasierakoSetMinak();
		m.eguneratuMinaKont(true);
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
		
		JLabel btnAurpegiIrab = new JLabel();
		btnAurpegiIrab.setIcon(new ImageIcon("res/cara1.gif"));		
		btnAurpegiIrab.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				Matrizea.getNireMatrizea1().aurpegiaKlikatu();
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
		irabazlePanela.getPanelSouth().add(btnAurpegiIrab);
	}

	public void update(Observable arg0, Object arg1) {//arg1-->klikatutako kasilla
		Panela panela = Panela.getNirePanela();
		JLabel[] lc = Panela.getNirePanela().getListaCasillas();
		JLabel label = lc[((Casilla)arg1).posizioa()];
		
		if(((Casilla)arg1).getKlika()==1) {//klik ezkerra
			if(arg1 instanceof CasillaMina) {
				label.setIcon(new ImageIcon("res/mina-n.gif"));
				panela.getminaKontZifra().setIcon(new ImageIcon("res/n-.gif"));
				panela.getminaKontZifra_1().setIcon(new ImageIcon("res/n-.gif"));
				panela.getminaKontZifra_2().setIcon(new ImageIcon("res/n-.gif"));
				
			}else if(arg1 instanceof CasillaHutsa) {
				label.setIcon(new ImageIcon("res/c0.gif"));
				
			}else {
				label.setIcon(new ImageIcon("res/c"+((Casilla) arg1).getBalioa()+".gif"));
			}
			
		}else {//klik eskuina
			if(((Casilla)arg1).getEgoera()==1){//kendu bandera
				label.setIcon(new ImageIcon("res/bandera.gif"));
				
			}else {//jarri bandera
				label.setIcon(new ImageIcon("res/tablero.gif"));
			}
		}
	}
}
