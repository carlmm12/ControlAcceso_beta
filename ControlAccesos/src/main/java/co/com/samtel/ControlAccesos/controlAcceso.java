package co.com.samtel.ControlAccesos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.samtel.ControlAccesos.util.FileView;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import java.awt.Panel;

public class controlAcceso extends JFrame {

	private JPanel contentPane;
	private JTextField txtRuta;
	private FileView f = new FileView();
	JFileChooser fc = new JFileChooser();
	private JTextPane txtAlert;
	private viewControlAccesoController vc = new viewControlAccesoController();
	private JComboBox cbxDesde;
	private JComboBox cbxHasta;
	private JComboBox cbxAnio;
	private int year = 0;
	private int mes = 0;
	private int diaI = 0;
	private int diaF = 0;
	private JTextField txtCodigo;
	private JLabel lblNumeroRegistrosMostrar;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("configuration-bean.xml");
					controlAcceso frame = new controlAcceso();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public controlAcceso() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtAlert.setText("");
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setEnabled(false);
		contentPane.add(panel, BorderLayout.NORTH);

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					fc = f.openfolders();
					txtRuta.setText(fc.getSelectedFile().getName());
					
				} catch (IOException e) {

					e.printStackTrace();
				} catch (NullPointerException en) {

				}

			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// verifica que el FileChoose no este vacio.
					// de no tenerlo  copia el archivo  desde la ruta de origen a la ruta de destino
					if (fc != null) {
						
						Boolean val = f.createDirec(fc.getSelectedFile().getAbsolutePath(),
								fc.getSelectedFile().getName());
						
						if (val == true) {
							String name_file = fc.getSelectedFile().getName();

							// volvemos nulo a el filechooser para que no se vuelva a cargar.
							fc = null;

							try {
								
								// se hace la carga a la base de datos
								Boolean resp = vc.cargarData(name_file);
								if (resp == true) {

									// enviamos un mensaje para indicar que el archivo se subio de manera correcta

									getTxtAlert().setText("El archivo se cargo de manera Exitosa");
									getTxtAlert().setForeground(Color.green);

								} else {
									// enviamos un mensaje para indicar que el archivo se subio de manera correcta
									getTxtAlert().setText("Error de archivo, verifique que es el correcto");
									getTxtAlert().setForeground(Color.red);
								}

							} catch (Exception e2) {
								getTxtAlert().setForeground(Color.red);
								getTxtAlert().setText("Error registrando en la base de datos");
							}

							// borramos el nmobre del archivo que se tomo
							txtRuta.setText("");

						} else {
							// borramos el nmobre del archivo que se tomo
							txtRuta.setText("");
							// volvemos nulo a el filechooser para que no se vuelva a cargar.
							fc = null;
							// enviamos un mensaje para indicar que el archivo fallo al subirs
							getTxtAlert().setText("Fallo al subir el archivo");
							getTxtAlert().setForeground(Color.red);
							System.out.println("el archivo no se cargo, verifica cual es el problema");
						}

					} else {
						txtRuta.setText("");
						getTxtAlert().setText("Seleccione un archivo");
						getTxtAlert().setForeground(Color.red);
					}
				} catch (NullPointerException e2) {
					System.out.println(e2);
					txtRuta.setText("");
					getTxtAlert().setText("Seleccione un archivo");
					getTxtAlert().setForeground(Color.red);
				}

			}
		});

		txtRuta = new JTextField();
		txtRuta.setBackground(Color.WHITE);
		txtRuta.setEditable(false);
		txtRuta.setColumns(10);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("\r\n");
		tabbedPane.setBackground(Color.WHITE);

		JPanel pnlReportes = new JPanel();
		pnlReportes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println("SSSSS");
				getLblNumeroRegistrosMostrar().setText(vc.getNumberData().toString());;
				
			}
		});
		pnlReportes.setToolTipText("Reportes");
		tabbedPane.addTab("Reportes", null, pnlReportes, null);

		JComboBox cbxTipoReporte = new JComboBox();
		cbxTipoReporte.setBounds(23, 32, 326, 20);
		cbxTipoReporte.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtAlert.setText("");
			}
		});
		cbxTipoReporte.setModel(new DefaultComboBoxModel(new String[] {"Seleccione el tipo reporte ...", "Retardos\t", "Menor Horas Trabajadas", "Mayor Horas Trabajadas", "Control de horas diarias"}));

		JLabel lblSeleccioneElTipo = new JLabel("Tipo reporte ");
		lblSeleccioneElTipo.setBounds(23, 11, 230, 16);

		cbxAnio = new JComboBox();
		cbxAnio.setBounds(31, 83, 64, 22);
		cbxAnio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// removemos los a�os disponibles de acuerdo a la busqueda en la base de datos
				cbxAnio.removeAllItems();
				// llamado al metodo para buscar todos los a�os disponibles
				List<Integer> data = vc.findbyRequer(0, 0, 1);
				for (int i = 0; i < data.size(); i++) {
					cbxAnio.addItem(data.get(i));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// asignamos a la variable del a�o el valor que se le selecciono en el combo box

				year = Integer.parseInt(String.valueOf(cbxAnio.getSelectedItem()));
				System.out.println(year);
			}
		});

		JComboBox cbxMes = new JComboBox();
		cbxMes.setBounds(120, 83, 64, 22);
		cbxMes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// remueve los item para volver a asignarlso de acuerdo al a�o
				cbxMes.removeAllItems();

				// llamamos al metodo que me `permitira traer todos los meses disponibles que
				// estan registrados en la bse de datos control acceso para buscar el mes
				List<Integer> data = vc.findbyRequer(year, 0, 2);
				for (int i = 0; i < data.size(); i++) {
					cbxMes.addItem(data.get(i));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				// se le asigna el valor a la variable mes con el valor seleccionado en el campo
				// del mes
				mes = Integer.parseInt(String.valueOf(cbxMes.getSelectedItem()));
				System.out.println(mes);
			}
		});
		cbxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cbxDesde = new JComboBox();
		cbxDesde.setBounds(202, 83, 64, 22);
		cbxDesde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				// muestra los dias en los que se registro el control de acceso biometrico
				diaI = Integer.parseInt(String.valueOf(cbxDesde.getSelectedItem()));
				System.out.println(diaI);
			}

			@Override
			public void focusGained(FocusEvent e) {
				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				List<Integer> data = vc.findbyRequer(year, mes, 3);
				for (int i = 0; i < data.size(); i++) {
					cbxDesde.addItem(data.get(i));
					cbxHasta.addItem(data.get(i));
				}
			}
		});
		cbxDesde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cbxHasta = new JComboBox();
		cbxHasta.setBounds(285, 83, 64, 22);
		cbxHasta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				diaF = Integer.parseInt(String.valueOf(cbxHasta.getSelectedItem()));
				System.out.println(diaF);
			}
		});

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(46, 63, 35, 14);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(132, 63, 35, 14);

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(216, 63, 35, 14);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(308, 63, 35, 14);

		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(156, 134, 93, 23);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// este me permite conocer cual es el index o posicion del reporte seleccionado
				int reporte = cbxTipoReporte.getSelectedIndex();

				// validaci�n se realiza las acciones si el mes, a�o y dias son diferentes a
				// cero
				if (year != 0 && mes != 0 && diaI != 0 && diaF != 0) {

					// mensaje en caso de que todo marche bien
				

					// se toma el indice y se hace un switch para hacer las respectivas accioens de
					// acuerdo a el tipo de reporte seleccionado
					switch (reporte) {
					case 1:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que llegan
						// tarde con respecto a la hora estipulada
						vc.AlertaRetardos(mes, year, diaI, diaF);

						// asignamos un mensaje en caso de que todo marche bien.
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 2:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que no
						// cumplen con las horas laborales estipuladas

						vc.AlertaMenorHorasLaboradas(mes, year, diaI, diaF);

						// mensaje en caso de que todo marche bien
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 3:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que
						// superan el numero de horas laboradas
						vc.AlertaMayorHorasLaboradas(mes, year, diaI, diaF);

						// mensaje en caso de que todo marche bien
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 4:
						getTxtAlert().setText("Generando Reporte espere ...");
						getTxtAlert().setForeground(Color.blue);
						vc.getAllControlRegistros(mes, year, diaI, diaF);
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.GREEN);
						break;
					default:
						break;
					}

				} else {

					getTxtAlert().setText("Ingrese los parametros de busqueda");
					getTxtAlert().setForeground(Color.red);
				}

				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				cbxMes.removeAllItems();
				cbxAnio.removeAllItems();
				System.out.println("El reporte seleccionado es: " + cbxTipoReporte.getSelectedIndex());
				cbxTipoReporte.setSelectedIndex(0);
				mes = 0;
				year = 0;
				diaI = 0;
				diaF = 0;

				System.out.println("los valores son:" + " a�o: " + year + " mes: " + mes + " dia inicial: " + diaI
						+ " dia final " + diaF);
			}
		});

		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setBounds(255, 134, 93, 23);
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				cbxMes.removeAllItems();
				cbxAnio.removeAllItems();
				System.out.println("El reporte seleccionado es: " + cbxTipoReporte.getSelectedIndex());
				cbxTipoReporte.setSelectedIndex(0);
				mes = 0;
				year = 0;
				diaI = 0;
				diaF = 0;
				getTxtAlert().setText("");

			}
		});
		pnlReportes.setLayout(null);
		pnlReportes.add(lblSeleccioneElTipo);
		pnlReportes.add(cbxAnio);
		pnlReportes.add(lblAo);
		pnlReportes.add(lblMes);
		pnlReportes.add(cbxMes);
		pnlReportes.add(lblDesde);
		pnlReportes.add(cbxDesde);
		pnlReportes.add(lblHasta);
		pnlReportes.add(btnGenerar);
		pnlReportes.add(btnDescartar);
		pnlReportes.add(cbxHasta);
		pnlReportes.add(cbxTipoReporte);

		txtAlert = new JTextPane();
		txtAlert.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtAlert.setEditable(false);
		txtAlert.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(txtAlert, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
							.addGap(52))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAbrir)
						.addComponent(btnGuardar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAlert, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Mantenimiento", null, panel_1, null);
		panel_1.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(219, 53, 134, 23);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCodigo.setBounds(219, 35, 53, 18);
		panel_1.add(lblCodigo);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getLblNumeroRegistrosMostrar().setText(vc.getNumberData().toString());;
				System.out.println(vc.getNumberData().toString());
			}
		});
		btnConsultar.setBounds(19, 96, 134, 23);
		panel_1.add(btnConsultar);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setBounds(159, 56, -41, -20);
		panel_1.add(layeredPane);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(176, 0, 1, 166);
		panel_1.add(panel_2);
		
		JLabel lblNumeroRegistros = new JLabel("Numero de registros:");
		lblNumeroRegistros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroRegistros.setBounds(25, 38, 145, 18);
		panel_1.add(lblNumeroRegistros);
		
		lblNumeroRegistrosMostrar = new JLabel("0000");
		lblNumeroRegistrosMostrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroRegistrosMostrar.setBounds(65, 67, 88, 18);
		panel_1.add(lblNumeroRegistrosMostrar);
		
		JButton btnLimpiarr = new JButton("Limpiar");
		btnLimpiarr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (vc.deleteDataControlAcceso(getTxtCodigo().getText())) {
					getTxtAlert().setText("Se elimino la información con éxito...");
					getTxtAlert().setForeground(Color.GREEN);
				}else {
					getTxtAlert().setText("Error al eliminar la información... por favor verifique el codigo");
					getTxtAlert().setForeground(Color.RED);

				}
			}
		});
		btnLimpiarr.setBounds(219, 96, 134, 23);
		panel_1.add(btnLimpiarr);
		panel.setLayout(gl_panel);
	}

	public JTextPane getTxtAlert() {
		return txtAlert;
	}

	public JComboBox getCbxDesde() {
		return cbxDesde;
	}

	public JComboBox getCbxHasta() {
		return cbxHasta;
	}



	public JComboBox getCbxAnio() {
		return cbxAnio;
	}
	public JLabel getLblNumeroRegistrosMostrar() {
		return lblNumeroRegistrosMostrar;
	}
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
}
