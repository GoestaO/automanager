package automanager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class AMFrame extends JFrame {

	private JLabel lblHersteller, lblLeistung, lblAnzahlTueren;
	private JTextField txtHersteller;
	private JSpinner spinLeistung;
	private JTable table;
	private JScrollPane scrollTable;
	private JSpinner spinAnzahlTueren;
	private JButton btnAdd;
	private JPanel pnlLeft, pnlAdd, pnlLoadSave;
	private Autobestand model;
	private JButton btnSave, btnLoad;

	public AMFrame(Autobestand model) {
		this.model = model;
		setTitle("Automanager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWidgets();

		addWidgets();

		setupInteractions();

		pack();

	}

	private void setupInteractions() {
		btnAdd.addActionListener(new BtnAddListener());
		btnSave.addActionListener(new BtnSaveListener());
		btnLoad.addActionListener(new BtnLoadListener());

	}

	private void addWidgets() {
		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(BorderLayout.LINE_START, pnlLeft);
		getContentPane().add(scrollTable);
		pnlAdd.add(lblHersteller);
		pnlAdd.add(txtHersteller);
		pnlAdd.add(lblLeistung);
		pnlAdd.add(spinLeistung);
		pnlAdd.add(lblAnzahlTueren);
		pnlAdd.add(spinAnzahlTueren);
		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnAdd);

		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());
		pnlLeft.add(pnlAdd);
		pnlLeft.add(Box.createVerticalGlue());
		pnlLoadSave.add(btnLoad);
		pnlLoadSave.add(btnSave);
		pnlLoadSave.setMaximumSize(pnlAdd.getPreferredSize());
		pnlLeft.add(pnlLoadSave);

	}

	private void createWidgets() {

		lblHersteller = new JLabel("Hersteller");
		lblLeistung = new JLabel("Leistung");
		lblAnzahlTueren = new JLabel("Anzahl Türen");
		txtHersteller = new JTextField();
		SpinnerNumberModel spinLeistungModel = new SpinnerNumberModel(100, 50,
				300, 5);
		spinLeistung = new JSpinner(spinLeistungModel);
		lblAnzahlTueren = new JLabel("Anzahl Türen");
		SpinnerNumberModel spinAnzahlTuerenModel = new SpinnerNumberModel(4, 2,
				5, 1);
		spinAnzahlTueren = new JSpinner(spinAnzahlTuerenModel);
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);

		scrollTable = new JScrollPane(table);
		btnAdd = new JButton("Hinzufügen");

		pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2));

		pnlLoadSave = new JPanel();
		pnlLoadSave.setLayout(new GridLayout(1, 1));
		btnSave = new JButton("Speichern");
		btnLoad = new JButton("Laden");
	}

	class BtnSaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ObjectOutputStream os = null;
			try {
				File file = new File("myCars2.ser");
				FileOutputStream fileStream = new FileOutputStream(file);
				os = new ObjectOutputStream(fileStream);
				os.writeObject(model);
				JOptionPane.showMessageDialog(null, "Daten gespeichert.");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException ioex) {
					ioex.printStackTrace();
				}
			}
		}
	}

	class BtnLoadListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ObjectInputStream os = null;
			try {
				// File file = Dateiauswahl.waehleDatei();
				FileInputStream filestream = new FileInputStream("myCars2.ser");
				os = new ObjectInputStream(filestream);
				try {
					Autobestand bestand = (Autobestand) os.readObject();
					final Autobestand bestandNow = bestand; 
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {					
							for (Auto i : bestandNow) {
								model.add(i);
								model.listen();
							}
						}
					});
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class BtnAddListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String hersteller = txtHersteller.getText();
			int leistung = (Integer) spinLeistung.getValue();
			int anzahlTueren = (Integer) spinAnzahlTueren.getValue();
			Auto a = new Auto(hersteller, leistung, anzahlTueren);
			model.add(a);
			model.listen();
		}
	}

}
