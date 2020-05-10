package bista;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import eredu.Matrizea;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BerriroJokatu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private String izena;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BerriroJokatu dialog = new BerriroJokatu();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public BerriroJokatu() {
		setBounds(100, 100, 450, 100);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("BERRIRO JOKATU NAHI DUZU?");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("BAI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroladore k = new Kontroladore();
				k.aurpegianKlikEgin(Matrizea.getNireMatrizea1().getZailtasuna(),izena);
				setVisible(false);
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EZ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel_1.add(btnNewButton_1);

	}
	
	public void setIzena(String iz) {
		iz = izena;
	}

}
