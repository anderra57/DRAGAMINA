
package bista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

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
	private JTextField zailtasun_text;
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
			panel_1.add(getzailtasun_text());
		}
		return panel_1;
	}
	private JLabel getLblJokoarenZailtasunaAukeratuetik() {
		if (lblJokoarenZailtasunaAukeratuetik == null) {
			lblJokoarenZailtasunaAukeratuetik = new JLabel("Jokoaren zailtasuna aukeratu (1etik 3ra):");
		}
		return lblJokoarenZailtasunaAukeratuetik;
	}
	private JTextField getzailtasun_text() {
		if (zailtasun_text == null) {
			zailtasun_text = new JTextField();
			zailtasun_text.addActionListener(new zailtasun_textActionListener());
			zailtasun_text.setColumns(10);
		}
		return zailtasun_text;
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
	private class zailtasun_textActionListener implements ActionListener {
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
		if(textField.getText().equals("")){//izena ez bada sartzen
			JOptionPane.showMessageDialog(textField, "Ez duzu izenik sartu");
		}else if((zailtasun_text.getText().equals("1"))||(zailtasun_text.getText().equals("2"))||(zailtasun_text.getText().equals("3"))){
			setVisible(false);
			Kontroladore k = new Kontroladore();
			if(zailtasun_text.getText().equals("1")) { // Matrizearen zailtasuna = 1 denenan
				k.jokoaLehenAldizHasieratu(1, textField.getText());
			}
			else if(zailtasun_text.getText().equals("2")) { // Matrizearen zailtasuna = 2 denenan
				k.jokoaLehenAldizHasieratu(2, textField.getText());
			}
			else if(zailtasun_text.getText().equals("3")) { // Matrizearen zailtasuna = 3 denenan
				k.jokoaLehenAldizHasieratu(3, textField.getText());
			}
		}else{//zailtasunean {1,2,3} zenbakirik sartzen ez bada
				JOptionPane.showMessageDialog(zailtasun_text, "Sartutako balioa ez da egokia");
				zailtasun_text.setText("");
		}
	}
	
	private Action getAction() {
		if (action == null) {
			action = new SwingAction();
		}
		return action;
	}
}