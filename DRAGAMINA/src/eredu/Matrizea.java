package eredu;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bista.IrabazlePanela;
import bista.Kontroladore;
import bista.Panela;


public class Matrizea extends Observable{//EMA
	
	private Casilla[][] matrizea;
	private static Matrizea nireMatrizea1 = null;
	private static int errenkada;
	private static int zutabea;
	private static int minaKop;//matrizean dauden mina kopurua gordeko du
	private static ArrayList<Integer> listaMinak;
	private HashSet<Integer> begiratuak;
	private int kasillaOnak;//minarik ez duten kasilla kopurua
	private static int zailtasuna;
	private static boolean emanda;
	private static boolean bukatua;
	private static boolean amaiera;
	private Panela panela=Panela.getNirePanela();
	private long partidaHasiera;
	private String jokalariarenIzena;
	private static ArrayList<Integer> listaBanderak;
	
	private Matrizea() {
		emanda = false;
		bukatua=false;
	}
	
	public static Matrizea getNireMatrizea1(){
		if(nireMatrizea1 == null){
			nireMatrizea1 = new Matrizea();
		}
		return nireMatrizea1;
	}
	
	
	//////////GETTER AND SETTER//////////
	
	public void seti(int i) {
		errenkada=i;
	}
	
	public void setj(int j) {
		zutabea=j;
	}
	
	public int getKasillaOnak() {
		return kasillaOnak;
	}
	
	public boolean getAmaiera() {
		return amaiera;
	}
	
	public int getE() {
		return Matrizea.errenkada;
	}
	
	public int getZ() {
		return Matrizea.zutabea;
	}
	
	public void setZailtasuna(int zenbakia) {
		zailtasuna=zenbakia;
		minaKop=zailtasuna*zutabea;
	}
	
	public int getZailtasuna() {
		return zailtasuna;
	}
	
	public void setpartidaHasiera(long ctm) {
		partidaHasiera=ctm;
		
	}
	
	public void setIzena(String s) {
		jokalariarenIzena=s;
	}
	
	public ArrayList<Integer> getListaBanderak(){
		return listaBanderak;
	}
	
	public ArrayList<Integer> getListaMinak(){
		return listaMinak;
	}
	
	public Casilla balioaBueltatu(int i, int j) {
		return matrizea[i][j];
	}
	
	public int getMinaKop() {
		return minaKop;
	}
	
	/////////////BESTE METODO BATZUK////////////////

	private int pos(int x, int y) {
		return(zutabea*x+y);
	}
	
	public void kasillaOnakBatKendu() {
		kasillaOnak--;
	}	
	
	///////////////MATRIZEA HASIERATU////////////////
	public void matrizeaSortu(int e, int z) {//Hemen e eta z, klikatu dugun lehenengo kasillaren koordenatuak dira
		CasillaFactory cf = CasillaFactory.getNireCasillaFactory();
		listaBanderak=new ArrayList<Integer>();
		listaMinak = new ArrayList<Integer>();
		kasillaOnak = (errenkada*zutabea)-minaKop;//atributu honetan, mina ez duten kasilla kopurua gordeko da
		begiratuak = new HashSet<Integer>();
		matrizea = new Casilla[errenkada][zutabea];
		
		for(int i=0;i<errenkada;i++) {
			for(int j=0;j<zutabea;j++) {
				matrizea[i][j]=cf.casillaSortu(0, i, j);
			}
		}
		Random rand = new Random();
		int kont = minaKop;
		while(kont>0) {
			int i = rand.nextInt(errenkada);
			int j = rand.nextInt(zutabea);
			Casilla c = matrizea[i][j];
			while(c instanceof CasillaMina || (i==e && j==z)) {
				i = rand.nextInt(errenkada);
				j = rand.nextInt(zutabea);
				c = matrizea[i][j];
			}
			matrizea[i][j] = cf.casillaSortu(-1, i, j);//matrizean, mina bakoitzari -1 balioa emango diegu
			kont--;
			listaMinak.add(pos(i,j));//hemen, mina lista bat sortuko dugu, geroago beste metodo batean erabiltzeko
		}

	}
	
	public void zenbakiakJarri() {
		CasillaFactory cf = CasillaFactory.getNireCasillaFactory();
		for(int i = 0; i<errenkada; i++) {
			for(int j=0; j<zutabea; j++) {
				Casilla c = matrizea[i][j];
				if(!(c instanceof CasillaMina)) {//lehen esan den bezala, matrizearen posizio bateko balio -1 bada, mina bat da
					int zenbat = zenbatMinaInguruan(i,j);
					if(zenbat!=0) {
						matrizea[i][j]=cf.casillaSortu(zenbat,i,j);
					}
				}	
			}
		}
	}
	
	private int zenbatMinaInguruan(int i, int j) {
		//---------
		//| 1 2 3 |
		//| 4 X 5 |
		//| 6 7 8 |
		//---------
		//bi for hauek hurrengoa egingo dute, begiratzen ari garen posizioa 'X' karakterea duena da, eta hurrengo
		//ordenean bigatuko dira--> 1etik 8ra. Hasiko da i-1 eta j-1 posizioa begiratzen(goiko irudian 1 posizioa)
		//ondoren i-1 j, gero i-1 j+1... eta horrela 8.posiziora iritsi arte(i+1 j+1 posizioa(goiko irudian 8 posizioa))
		int kop=0;	
		for(int x = i-1;x<=i+1;x++) {
			for(int y = j-1; y<=j+1;y++) {
				if(x<0 || y<0 || y>zutabea-1 || x>errenkada-1) {
					//matrizetik kanpo dago
				}else {
					Casilla c = matrizea[x][y];
					if(c instanceof CasillaMina) {
						kop++;
					}
				}
			}
		}
		return kop;
	}
	
	
	/////////////MATRIZEA ZABALDU///////////////
	
	public void MatrizeaZabaldu(Casilla k){//Metodo hau, kasilla huts bat pultsatu ondoren exekutatuko da
		Queue<Casilla> begiratuGabe = new LinkedList<Casilla>();
		Casilla kasilla=k;
		int i,j;
		begiratuak.add(pos(kasilla.geti(),kasilla.getj()));//klikatu dugun kasilla sartuko dugu listan berriro ere
		//ez begiratzeko
		begiratuGabe.add(k);
		while(!begiratuGabe.isEmpty()) {
			kasilla=begiratuGabe.remove();
			kasillaOnakBatKendu();
			kasilla.egoeraAldatu(0);
			i = kasilla.geti();
			j = kasilla.getj();
			zabaldu(pos(i,j),kasilla.getBalioa());//laukiaren balioa bistaratuko dugu panelea
			if(kasilla instanceof CasillaHutsa) {
				for(int x = i-1;x<=i+1;x++) {//goiko metodoan erabili den for berdina da
					for(int y = j-1; y<=j+1;y++) {
						if(x<0 || y<0 || y>zutabea-1 || x>errenkada-1) {
							//matrizetik kanpo dago
						}else {
							if(!begiratuak.contains(pos(x,y))){
								Casilla unek = matrizea[x][y];
								begiratuGabe.add(unek); 
								begiratuak.add(pos(x,y));
							}
						}
					}
				}
			}
		}	
	}
	
	public void zabaldu(int num, int zenbat){
	   	Casilla c = balioaBueltatu(num/zutabea, num%zutabea);
	   	setChanged();
	   	notifyObservers(c);
	}
	
	//////////////KASILLA BAT KLIKATU////////////
	public void clickEzkerra(int zenb, JLabel lab) {
		if(!bukatua) {
			int zenbakia = zenb;
			if(!emanda){
					matrizeaSortu(zenbakia/zutabea, zenbakia%zutabea);
					zenbakiakJarri();
					emanda=true;
			}
			Casilla c = balioaBueltatu((zenbakia/zutabea), (zenbakia % zutabea));
			if (c.getEgoera() == 2 || c.getEgoera()==3) {
				if(c instanceof CasillaMina){//balioa = -1 mina bat aurkitu dugu, ondorioz galdu dugu
					c.egoeraAldatu(0);
					minakPantailaratu();
					lab.setIcon(new ImageIcon("res/mina-r.gif"));
					bukatua=true;	
				}else if(c instanceof CasillaHutsa){//balioa= 0 bada, lauki horretan hutsune bat dago, ondorioz, matrizea
						MatrizeaZabaldu(c);
				}else{
						kasillaOnakBatKendu();
						c.egoeraAldatu(0);
						begiratuak.add(c.posizioa());
						setChanged();
						notifyObservers(c);
				}
			}
			if(getKasillaOnak()==0) {
				amaiera=true;
				bukatua = true;
				setChanged();
				notifyObservers();
				amaituPanela();
			}
		}
	}
	
	public void clickEskuina(int zenb, JLabel lab) {
		if(!bukatua) {
			int zenbakia = zenb;
			if(!emanda){
				matrizeaSortu(zenbakia/zutabea, zenbakia%zutabea);
				zenbakiakJarri();
				emanda=true;
			}
			Casilla c = balioaBueltatu((zenbakia/zutabea), (zenbakia % zutabea));
			if(c.getEgoera() == 1) { //bandera kendu
					c.egoeraAldatu(3); //galdera ikurra
					eguneratuMinaKont(false);
					setChanged();
					notifyObservers(c);
					listaBanderak.remove(listaBanderak.indexOf(zenbakia));
			}
			else if (c.getEgoera() == 2) { //bandera kokatu
				c.egoeraAldatu(1); //bandera
				eguneratuMinaKont(true);
				setChanged();
				notifyObservers(c);
				listaBanderak.add(zenbakia);
			}else{//galdera ikurra klikatu dugu
				c.egoeraAldatu(2); //itxita	
				setChanged();
				notifyObservers(c);
			}
		}
	}
	
	public boolean kasillaOnakHutsik() {
		if(kasillaOnak==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void minakPantailaratu(){
		Iterator<Integer> it=listaMinak.iterator();
		int pos;
		while(it.hasNext()){
			pos=it.next();
			Casilla c = this.balioaBueltatu(pos/zutabea, pos%zutabea);
			c.egoeraAldatu(0);
			setChanged();
			notifyObservers(c);
		}
	}
	
	
	public void eguneratuMinaKont(boolean arg0) {
		if(arg0) {
			minaKop--;}
		else {
			minaKop++;
		} 	
	}
	
	public int bilatu(JLabel[] a,JLabel j){
		// metodo hau erabiliko dugu, lauki bat click egitean, jakiteko zein 
		// posiziotan dagoen, horrela M  atrizea metodoan dagoen balioaBueltatu
		// metodoari esker, lauki horren balioa jakingo dugu
		return Arrays.asList(a).indexOf(j);
	}
	
	/////////////// AMAIERAKO METODOAK //////////////
	
	public void amaituPanela() {
		sartuListaIrabazlean();
	}
	
	public void sartuListaIrabazlean() {
		// PUNTUAZIO SISTEMA: (zailtasuna * zutabeKop * errenkadaKop * pi * 1.000.000 / partidarenIraupena_milisegundotan)-ren borobilketa osoa.
		int partidakoPunt=(int)(getZailtasuna()*zutabea*errenkada*Math.PI*1000000/((int)(System.currentTimeMillis()-partidaHasiera)));

		// 1) irabazleak.txt fitxeroa irakurri
		String linea="";
		try{      
			Scanner entrada = new Scanner(new FileReader("res/irabazleak.txt"));     
			linea = entrada.nextLine();
			entrada.close();
		}
		catch(IOException e) {System.out.println("Arazoa egon da \"irabazleak.txt\" fitxategia irekitzean.");}
		
		// 2) split (komagaz banatu) -> arrayFitxero
		String[] arrayFitxero = linea.split(",");
		
		// 3) arrayPair aldagaian sartu eta frogatu ia puntuazioa top10-ean badago, eta hala bada bertan sartu
		int i=0;
		ArrayList<AbstractMap.SimpleEntry<Integer,String>> arrayPair = new ArrayList<AbstractMap.SimpleEntry<Integer,String>>();
		while (i<arrayFitxero.length){
			int punt =	Integer.parseInt(arrayFitxero[i]);
			i++;
			String izen = arrayFitxero[i];
			i++;
			arrayPair.add(new AbstractMap.SimpleEntry<>(punt,izen));
		}

		if (partidakoPunt > arrayPair.get(9).getKey()){ //solo entra si su punt es mayor que la del ultimo de la lista
			i=0;
			boolean aurk=false;
			while (!aurk){
				if (partidakoPunt > arrayPair.get(i).getKey()){ // BERDINKETA BADAGO, PUNTUAZIO ZAHARRENAK DAUKA LEHENTASUNA
					arrayPair.add(i,new AbstractMap.SimpleEntry<>(partidakoPunt,jokalariarenIzena));
					arrayPair.remove(10);
					aurk=true;
				}
				i++;
			}
		}
		// 4) arrayPair irabazlePanela panelera gehitu
		for (i=0;i<10;i++) {
			
			Integer printpunt = arrayPair.get(i).getKey();
			String printizen = arrayPair.get(i).getValue();
			String printpunttext = Integer.toString(printpunt);
			
			IrabazlePanela.getNireIrabazlePanela().setIzena(printizen,i+1);
			IrabazlePanela.getNireIrabazlePanela().setPunt(printpunttext,i+1);

		}
		// 5) irabazleak.txt fitxeroa berridatzi
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("res/irabazleak.txt"));
			for (i=0;i<10;i++) {
				if (i==9) {
					bw.write(arrayPair.get(i).getKey() +","+arrayPair.get(i).getValue());
				} else {
					bw.write(arrayPair.get(i).getKey() +","+arrayPair.get(i).getValue()+",");
				}
			}
			bw.close();
		}
		catch(IOException e) {System.out.println("Arazoa egon da \"irabazleak.txt\" fitxategia berridaztean.");}
	}
	
	//////////////PARTIDAK BERRIRO HASI///////////////
	//MENUAREKIN
	public void menuaAukeratu(int zein) {
		amaiera=false;
		if(zein==1) {
			panela.setVisible(false);
			emanda=false;
			panela.setNirePanela(null);
			nireMatrizea1=null;
			jokoBerriaHasieratu(1);
		}else if(zein==2) {
			panela.setVisible(false);
			emanda=false;
			panela.setNirePanela(null);
			nireMatrizea1=null;
			jokoBerriaHasieratu(2);
		}else if(zein==3) {
			panela.setVisible(false);
			emanda=false;
			panela.setNirePanela(null);
			nireMatrizea1=null;
			jokoBerriaHasieratu(3);
		}else if(zein==4){
			panela.setVisible(false);
			emanda=false;
			panela.setNirePanela(null);
			nireMatrizea1=null;
			jokoBerriaHasieratu(getZailtasuna());
		}else {
			try {
				Desktop.getDesktop().browse(new File("res/info.html").toURI());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//AURPEGIAREKIN
	public void aurpegiaKlikatu(int zailtasuna) {
		emanda=false;
		panela.setNirePanela(null);
		nireMatrizea1=null;
		amaiera=false;
		jokoBerriaHasieratu(zailtasuna);
	}
	
	//PARTIDA LEHEN ALDIZ HASIERATU
	public void hasierakeraBotoia(int zailtasuna, String izena) {
		if(zailtasuna==1) {
			seti(7);
			setj(10);
			setZailtasuna(1);	
			setIzena(izena);
		}else if(zailtasuna==2) {
			seti(10);
			setj(15);// Hemen, zailtasun bakoitzari identifikatzaile bat jarriko dugu, gero
			// Kontroladore klaseko metodo batetan erabiltzeko
			setZailtasuna(2);
			setIzena(izena);
		}else {
			seti(12);
			setj(25);
			setZailtasuna(3);
			setIzena(izena);
		}
	}
	
	public void jokoBerriaHasieratu(int zenbakia) {
		if(zenbakia==1) {
			seti(7);
			setj(10);
			setZailtasuna(1);
		}else if(zenbakia==2) {
			seti(10);
			setj(15);
			setZailtasuna(2);
		}else {
			seti(12);
			setj(25);
			setZailtasuna(3);
		}
	}
}