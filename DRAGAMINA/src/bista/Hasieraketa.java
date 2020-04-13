
package bista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import eredu.Matrizea;

import javax.swing.JPanel;
import javax.swing.JButton;
//import javax.swing.JTextPane;
//import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.AbstractAction;

public class Hasieraketa extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblZureIzenaSar;
	private JTextField textField;
	private JPanel panel_1;
	private JLabel lblJokoarenZailtasunaAukeratuetik;
	private JTextField textField_1;
	private JPanel panel_2;
	private JButton btnJarraitu;
	private Action action;

	public void hasiPrograma() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hasieraketa dialog = new Hasieraketa();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Hasieraketa() {
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 140);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		setTitle("Dragamina");
		setLocationRelativeTo(null);
		getContentPane().add(getPanel(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
		getContentPane().add(getPanel_2(), BorderLayout.SOUTH);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblZureIzenaSar());
			panel.add(getTextField());
		}
		return panel;
	}
	private JLabel getLblZureIzenaSar() {
		if (lblZureIzenaSar == null) {
			lblZureIzenaSar = new JLabel("Zure izena sar ezazu:");
		}
		return lblZureIzenaSar;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLblJokoarenZailtasunaAukeratuetik());
			panel_1.add(getTextField_1());
		}
		return panel_1;
	}
	private JLabel getLblJokoarenZailtasunaAukeratuetik() {
		if (lblJokoarenZailtasunaAukeratuetik == null) {
			lblJokoarenZailtasunaAukeratuetik = new JLabel("Jokoaren zailtasuna aukeratu (1etik 3ra):");
		}
		return lblJokoarenZailtasunaAukeratuetik;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.addActionListener(new TextField_1ActionListener());
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getBtnJarraitu());
		}
		return panel_2;
	}
	private JButton getBtnJarraitu() {
		if (btnJarraitu == null) {
			btnJarraitu = new JButton("Jarraitu");
			btnJarraitu.setAction(getAction());
		}
		return btnJarraitu;
	}
	private class TextField_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {//Hau exekutatuko da zailtasun panelean intro sakatzean
			botoiaEdoIntroEmatean();
		}
	}
	

	private class SwingAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Jarraitu");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {//Hau exekutatuko da zailtasun panelean jarraitu botoia sakatzean
			botoiaEdoIntroEmatean();
		}
	}
	
	public void botoiaEdoIntroEmatean() { // Kodea birritan ez errepikatzeko sortu dugu metodo hau.
		
		if((textField_1.getText().equals("1"))||(textField_1.getText().equals("2"))||(textField_1.getText().equals("3"))){
			setVisible(false);
			Matrizea m = Matrizea.getNireMatrizea1();
			if(textField_1.getText().equals("1")) { // Matrizearen zailtasuna = 1 denenan
				m.hasierakeraBotoia(1,textField.getText());
			}
			else if(textField_1.getText().equals("2")) { // Matrizearen zailtasuna = 2 denenan
				m.hasierakeraBotoia(2,textField.getText());
			}
			else if(textField_1.getText().equals("3")) { // Matrizearen zailtasuna = 3 denenan
				m.hasierakeraBotoia(3,textField.getText());
			}
		}else{
			JOptionPane.showMessageDialog(textField_1, "Sartutako balioa ez da egokia");
			textField_1.setText("");
		}
	}
	
	private Action getAction() {
		if (action == null) {
			action = new SwingAction();
		}
		return action;
	}
	
	public void jokuBerriaHasieratu(int zenbakia) {//Metodo hau erabiliko dugu, joku berri bat sortu nahi denean,
		//bai aurpegi botoia sakatzean eta bai menuan dagoen aukera bat hautatzean
		Matrizea m = Matrizea.getNireMatrizea1();
		if(zenbakia==1) { //erreza
			m.jokoBerriaHasieratu(1);
		}else if(zenbakia==2) { // ertaina
			m.jokoBerriaHasieratu(2);
		}else{ // zaila
			m.jokoBerriaHasieratu(3);
		}
	}
}