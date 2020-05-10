package bista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class IrabazlePanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private static IrabazlePanela nirePanela=null;
	JPanel contentPane;
	JPanel panelNorth,panelSouth,panelCenter;
	JLabel lblIzena_1, lblIzena_2, lblIzena_3, lblIzena_4, lblIzena_5, lblIzena_6, lblIzena_7, lblIzena_8, lblIzena_9, lblIzena_10;
	JLabel lblPunt_1, lblPunt_2, lblPunt_3, lblPunt_4, lblPunt_5, lblPunt_6, lblPunt_7, lblPunt_8, lblPunt_9, lblPunt_10;

	private IrabazlePanela() {
		
	}
	
	public static IrabazlePanela getNireIrabazlePanela() {
		if(nirePanela==null) {
			nirePanela=new IrabazlePanela();
		}
		return nirePanela;
	}
	
	public JPanel getContentPane1(){
		return contentPane;
	}
	public void setContentPane1(JPanel j) {
		contentPane=j;
	}
	
	public JPanel getPanelCenter(){
		return panelCenter;
	}
	public void setPanelCenter(JPanel j) {
		panelCenter=j;
	}
	
	public JPanel getPanelNorth(){
		return panelNorth;
	}
	public void setPanelNorth(JPanel j) {
		panelNorth=j;
	}
	
	public JPanel getPanelSouth(){
		return panelSouth;
	}
	public void setPanelSouth(JPanel j) {
		panelSouth=j;
	}
	
	public JLabel getLabel (int index, int izenaedopunt){
		if (izenaedopunt==1) {
			switch (index) {
			case 1:
				return lblIzena_1;			
			case 2:
				return lblIzena_2;				
			case 3:
				return lblIzena_3;				
			case 4:
				return lblIzena_4;				
			case 5:
				return lblIzena_5;				
			case 6:
				return lblIzena_6;				
			case 7:
				return lblIzena_7;				
			case 8:
				return lblIzena_8;				
			case 9:
				return lblIzena_9;				
			case 10:
				return lblIzena_10;				
			}
		}
		else if (izenaedopunt==2) {
			switch (index) {
			case 1:
				return lblPunt_1;				
			case 2:
				return lblPunt_2;				
			case 3:
				return lblPunt_3;				
			case 4:
				return lblPunt_4;				
			case 5:
				return lblPunt_5;				
			case 6:
				return lblPunt_6;				
			case 7:
				return lblPunt_7;				
			case 8:
				return lblPunt_8;				
			case 9:
				return lblPunt_9;				
			case 10:
				return lblPunt_10;				
			}
		}
		return new JLabel("balio null");
	}
	public void setLabel (JLabel j, int index, int izenaedopunt){
		if (izenaedopunt==1) {
			switch (index) {
			case 1:
				lblIzena_1 = j;	
				break;
			case 2:
				lblIzena_2 = j;		
				break;
			case 3:
				lblIzena_3 = j;		
				break;
			case 4:
				lblIzena_4 = j;	
				break;
			case 5:
				lblIzena_5 = j;				
				break;
			case 6:
				lblIzena_6 = j;
				break;
			case 7:
				lblIzena_7 = j;
				break;
			case 8:
				lblIzena_8 = j;
				break;
			case 9:
				lblIzena_9 = j;				
				break;
			case 10:
				lblIzena_10 = j;
				break;
			}
		}
		else if (izenaedopunt==2) {
			switch (index) {
			case 1:
				lblPunt_1 = j;				
				break;				
			case 2:
				lblPunt_2 = j;					
				break;			
			case 3:
				lblPunt_3 = j;				
				break;				
			case 4:
				lblPunt_4 = j;				
				break;				
			case 5:
				lblPunt_5 = j;					
				break;			
			case 6:
				lblPunt_6 = j;					
				break;			
			case 7:
				lblPunt_7 = j;					
				break;			
			case 8:
				lblPunt_8 = j;					
				break;			
			case 9:
				lblPunt_9 = j;					
				break;			
			case 10:
				lblPunt_10 = j;						
				break;		
			}
		}
	}
	
	public void setIzena (String s, int i){
		switch (i) {
		case 1:
			lblIzena_1.setText(s);
			break;
		case 2:
			lblIzena_2.setText(s);
			break;
		case 3:
			lblIzena_3.setText(s);
			break;
		case 4:
			lblIzena_4.setText(s);
			break;
		case 5:
			lblIzena_5.setText(s);
			break;
		case 6:
			lblIzena_6.setText(s);
			break;
		case 7:
			lblIzena_7.setText(s);
			break;
		case 8:
			lblIzena_8.setText(s);
			break;
		case 9:
			lblIzena_9.setText(s);
			break;
		case 10:
			lblIzena_10.setText(s);
			break;
		}
	}
	
	public void setPunt (String s, int i){
		switch (i) {
		case 1:
			lblPunt_1.setText(s);
			break;
		case 2:
			lblPunt_2.setText(s);
			break;
		case 3:
			lblPunt_3.setText(s);
			break;
		case 4:
			lblPunt_4.setText(s);
			break;
		case 5:
			lblPunt_5.setText(s);
			break;
		case 6:
			lblPunt_6.setText(s);
			break;
		case 7:
			lblPunt_7.setText(s);
			break;
		case 8:
			lblPunt_8.setText(s);
			break;
		case 9:
			lblPunt_9.setText(s);
			break;
		case 10:
			lblPunt_10.setText(s);
			break;
		}
	}
	
}