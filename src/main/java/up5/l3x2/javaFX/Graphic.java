package up5.l3x2.javaFX;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import up5.l3x2.io.ImporterFonction;
import up5.l3x2.io.settings.Calculcccoef;
import up5.l3x2.io.settings.ExportCSV;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.exception.ImportException;
import up5.l3x2.io.ImporterFonction;
import up5.l3x2.io.settings.Calculcccoef;
import up5.l3x2.io.settings.Import;
import up5.l3x2.pdf.Creation_pdf;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Graphic {

	private JFrame frame;
	private File yourFolder;
	private JFileChooser chooser;
	private DefaultListModel model;
	private static DefaultTableModel md;
	private Import imp;
	private static JTable tab ;
	private static JScrollPane jsp ; 


	
	public Graphic() {
		initialize();
		
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 847, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		md = new DefaultTableModel();
		md.setColumnIdentifiers(new String []{"Vet"});
		 
		DefaultTableModel md2 = new DefaultTableModel();
		md2.setColumnIdentifiers(new String []{"Vet à exporter"});
		
		JButton btnParcourir = new JButton("Parcourir");
		btnParcourir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));//repertoire courrant
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							File fichier = chooser.getSelectedFile();
				 
							try {
								Import imp2 = ImporterFonction.importer(fichier.getName());
								Calculcccoef.parcourarbo(imp2);
								imp=imp2;
								
								for(int i= 0; i < imp.getVets().size(); i++)
								{	
									md.addRow(new Object[]{imp.getVets().get(i).getListeLSE().get(0).getNom()});
								
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
					}
				}
				
			}
		});
		
		btnParcourir.setBounds(512, 11, 106, 30);
		frame.getContentPane().add(btnParcourir);
		
		  
		final JTable tab = new JTable(md);
		
		JScrollPane jsp = new JScrollPane(tab);
					
		jsp.setBounds(42, 116, 269, 257);
		frame. getContentPane().add(jsp);
		
		
		
		
		final JTable tab2 = new JTable(md2);
		JScrollPane jsp2 = new JScrollPane(tab2);
					
		jsp2.setBounds(523, 116, 269, 257);
		frame. getContentPane().add(jsp2);
		
		
		
		
		
		
		
		JLabel label = new JLabel("Choisissez un r\u00E9p\u00E9rtoire \u00E0 importer");
		label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label.setBounds(97, 14, 258, 23);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("VET contenu dans le r\u00E9pertoire");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(77, 91, 207, 14);
		frame.getContentPane().add(label_1);
		
		JLabel lblAjouterLesVets = new JLabel("Ajouter les Vets \u00E0 exporter");
		lblAjouterLesVets.setBounds(347, 132, 166, 14);
		frame.getContentPane().add(lblAjouterLesVets);
		
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try{
							
						Object ob = tab.getValueAt(tab.getSelectedRow(),0);
					
						md2.addRow(new Object[]{ob});
						md.removeRow(tab.getSelectedRow());
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,"Veuillez choisir une vet ");
					}
			}
		});
		btnAjouter.setBounds(367, 185, 106, 23);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
					
						Object ob2 = tab2.getValueAt(tab2.getSelectedRow(),0);
				
						md.addRow(new Object[]{ob2});
						md2.removeRow(tab2.getSelectedRow());
					
					}catch(Exception ee){
						JOptionPane.showMessageDialog(null,"Veuillez choisir une vet ");
					}
			}
		});
		btnSupprimer.setBounds(367, 239, 106, 23);
		frame.getContentPane().add(btnSupprimer);
		
		JButton btnExpoter = new JButton("Exporter");
		btnExpoter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String file= "exportPdf_" + imp.getLec().getNomRepertoire() +".pdf";
					
					ArrayList<String> vet=new ArrayList<String>();
					
					for (int c = 0; c< md2.getRowCount(); c++){
						  vet.add(md2.getValueAt(c, 0).toString());
						}
					
					new Creation_pdf(file,imp,vet);
					ExportCSV.exporterCSV(imp);
				
					JOptionPane.showMessageDialog(null,"Export généré avec succès");
					
										
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null,"Veuillez choisir une vet à exporter");
				}
			}
		});
		btnExpoter.setBounds(615, 383, 89, 23);
		frame.getContentPane().add(btnExpoter);
		
		
		
		
		
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphic window = new Graphic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
