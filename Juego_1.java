package paquete_temp;
//Import's
import java.util.*;
import java.util.Timer;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import org.apache.commons.logging.LogFactory;


public class Juego_1 implements ActionListener, ChangeListener, MouseListener, KeyListener, ItemListener{

		JFrame Ventana;
		JPanel Panel_Contenedor, Contenedor_Paneles, Barra_Superior, Barra_Derecha;
		JPanelClass Panel_Principal;
		CardLayout Baraja_Paneles;
//		JButton[] Botones = new JButton[40];
		JSplitPane Division_Vertical, Division_Horizontal;
		JButton PUSH_IT;
		JPanelClass Panel_Inicio;
		JProgressBar Barra_Salud, Barra_Tiempo_Push = new JProgressBar();;
		int salud=100;
		double countdownStarter=3;
		int tiempo_int;
		int hits=0, fallos=0;
		String Most_Temp;
		TimerTask Temporizador_Logico;
		//Parte superior de juego 1
		Timer Metronomo = new Timer();
		JLabel Etiqueta_Tiempo_Superior;
		JScrollPane SC_Tiempo;
		String Texto_Etiqueta_Tiempo;
		TimerTask TT_F_M;
		int minutos=0;
		int segundos=0, segundos_totales=0;
		ButtonClass Iniciar_Sesion;
		BorderFactory border;
		JLabel Indicador_Vida_texto;
		int Segundos_Subir=0, Aciertos_Subir=0, Fallos_Subir=0;
		//segundos totales es para luego hacer el calculo para la tabla de SQL
int segundos_usables;
String cadena_final;
		ButtonClass Iniciar_Juego, Opciones_Juego, Cambiar_Juegos_1, Cambiar_Juegos_2, Cambiar_Juegos_3, Salir_Juego, Estadisticas_Juego;

		JLabel Etiqueta_Area;
		JLabel Etiqueta_Banner;
		JButton Atras, Atras_Secundario;
		//Barra de vida
		
		//Timer used in every TimerTask's
		Timer Sistema_Tiempo;
		
		//Object's button interface 1
		TimerTask Task_Texto_Boton, Task_Logico_Boton; //Logistic's task's
		DecimalFormat Contador_Milis = new DecimalFormat("#.##"); //Format for visual counter time
		
		//Login objects
		boolean Sesion=false;
		String Nombre , Pass ;
		String Nombre_Actual, Pass_Actual;
		JButton Boton_Iniciar, Boton_Crear;
		JFrame Ventana_Sesion;
		JPanel Panel_Sesion;
		JTextField Caja_Nombre, Caja_Pass;
		
		//Object's timer bar life interface 1
		TimerTask Task_Salud;
		int Salud;
		
		//Logistica interfaz
		Boolean juego_1=false, juego_2=false, juego_3=false;
		
		//Logistica juego 2
		JPanelClass Panel_Secundario;
		JLabel Etiqueta_Letra; 
		JTextField Caja_Letras;

		Random Aleatorio = new Random();
		int Seleccion_Letra;
		char[] Letras= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char Letra_Mostrar= Letras[Seleccion_Letra];
		//26 letras en total

		//Temporizadores de juego 2
		Timer Tiempo_Teclas_2 = new Timer();
		TimerTask Temporizador_Teclas_2;
			Timer Tiempo_Teclas = new Timer();
			TimerTask Temporizador_Teclas;
			DecimalFormat Contador_Milis_Teclas = new DecimalFormat("#.##");
			double contador_teclas=3.00;
			int tiempo_teclas=4;
			char Tecla_Memoria;
			int fallos_teclas=0, aciertos_teclas=0, teclas_pasadas=0;
			JTextArea Mostrador_letra;
			
			boolean validador_salir=false;
			
			//Values to validate menu back
			boolean Regresar_Menu=false, Re_Intentar=false;;
	
			//Object's to create stat's panel
			JPanelClass Panel_Opciones;// pos el panel pedazo de weon
			DefaultTableModel Modelo_Puntuaciones; //modelo de la tabla
			JTable Tabla_Puntuaciones; //tabla
			JScrollPane JSTabla;
			String Arrow_String[] = new String[5]; //string para meter todo a la tabla
			ButtonGroup RD_Mejore_tiems, RD_Mejore_punt, RD_Juegos; //para poner restriccion a mejores
			JRadioButton Mejor_tiemp, Peor_tiem, Mejor_punt, Peor_Punt;
			JRadioButton Juego_1, Juego_2, Juego_All; //indicar que dificultad se jugo y cual modo de juego
			JTextField Caja_Tiempo_Min, Caja_Tiempo_Max;//para indicar el minimo y maximo de tiempo osea un rango
			ButtonClass Atras_Estadisticas;
			
			//SQL Objetc's
			Connection Cable_Ethernet; 
			Statement Instruccion_SQL;
			ResultSet Consulta_SQL;
			PreparedStatement PreConsulta_SQL;
			java.sql.ResultSetMetaData RS_Meta;
			
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Juego_1 I = new Juego_1();
					I.Ventana_Inicio();
					I.Conectador();
					I.Desconectador();
					I.Crear_Tablas();
					I.Insertar_Usuarios();
					I.Insertar_Puntuaciones();
					try {
				    	ReproductorMP3 y = new ReproductorMP3();
					     y.AbrirFichero("C:/Zelda Chill Zelda Chill 2.mp3");
					     y.Play();
					}catch (Exception e) {
						System.out.println(e.getMessage());
					}
				    
				} catch (Exception e) {
					System.err.println("No se ha podido cargar el tema: \n"+e.getMessage());
					}
				}
		});
	}//Metodo principal

	public void Ventana_Inicio() {
		Panel_Inicio = new JPanelClass(null, "fondo_1.png");
		Ventana = new JFrame("Clicker pointer!");
		Ventana.setSize(new Dimension(800,750));
		Ventana.setLocationRelativeTo(null);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Contenedor_Paneles = new JPanel();
		Baraja_Paneles = new CardLayout();
		Contenedor_Paneles.setLayout(Baraja_Paneles);
		
		try { 
			ImageIcon icono_programa = new ImageIcon(getClass().getResource("icon_main.png"));
			Ventana.setIconImage(icono_programa.getImage());
		}catch (Exception e) {
			System.err.println("No se ha podido cargar el icono del programa: \n"+e.getMessage());
		}
		Etiqueta_Banner = new JLabel();
		Etiqueta_Banner.setBounds(80,10,600,350);
		try { 
			Icon icon_banner = new ImageIcon(getClass().getResource("banner_base.png"));
			Etiqueta_Banner.setIcon(icon_banner);
		}catch (Exception e) {
			System.err.println("No se ha podido cargar el icono del programa: \n"+e.getMessage());
		}
		
		Iniciar_Juego = new ButtonClass("jugar");
		Iniciar_Juego.setLocation(290,350);
		

		Cambiar_Juegos_1 = new ButtonClass("juego1");
		Cambiar_Juegos_1.setLocation(170,450);
		
		Cambiar_Juegos_2 = new ButtonClass("juego2");
		Cambiar_Juegos_2.setLocation(380,450);

		Estadisticas_Juego = new ButtonClass("estadisticas");
		Estadisticas_Juego.setLocation(290,600);
	
		Salir_Juego = new ButtonClass("salir");
		Salir_Juego.setLocation(290,650);
		
		Iniciar_Sesion = new ButtonClass("iniciar_sesion");
		Iniciar_Sesion.setBounds(10,600,200,40);
		Iniciar_Sesion.addActionListener(this);
		Ventana_Sesion_Frame();
		Interfaz_Juego_1();
		Interfaz_Estadisticas();
		Panel_Inicio.add(Etiqueta_Banner);
		Panel_Inicio.add(Iniciar_Sesion);
		Panel_Inicio.add(Iniciar_Juego);
		Panel_Inicio.add(Cambiar_Juegos_1);
		Panel_Inicio.add(Cambiar_Juegos_2);
		Panel_Inicio.add(Estadisticas_Juego);
		Panel_Inicio.add(Salir_Juego);
		
		for(Component Botones_Juegos:Panel_Inicio.getComponents()) {
			if(Botones_Juegos instanceof ButtonClass) {
				((ButtonClass) Botones_Juegos).addActionListener(this);
			}
		}
		Contenedor_Paneles.add("inicio", Panel_Inicio);
		Contenedor_Paneles.add("juego1", Division_Vertical);
		Contenedor_Paneles.add("estadisticas", Panel_Opciones);


//		Baraja_Paneles.show(Panel_Inicio, "Inicio");
		Ventana.setContentPane(Contenedor_Paneles);
		Ventana.setVisible(true);
		
	}
	
	public void Interfaz_Juego_1() {

		
		Panel_Principal = new JPanelClass(null, "fondo_3.png");
//		Panel_Principal.setLayout(new GridLayout(4,4));
		Panel_Principal.setLayout(null);
		Panel_Principal.setPreferredSize(new Dimension(600,600));
				
		Barra_Derecha = new JPanel(null);
		Barra_Derecha.setPreferredSize(new Dimension(100,150));
		Barra_Superior = new JPanel(null);
		Barra_Superior.setPreferredSize(new Dimension(600,50));
		
		Division_Vertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		Division_Vertical.setResizeWeight(0);
		Division_Vertical.setOneTouchExpandable(false);
		Division_Vertical.setEnabled(true);
		Division_Horizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		Division_Horizontal.setResizeWeight(0.80);
		Division_Horizontal.setOneTouchExpandable(false);
		Division_Horizontal.setEnabled(true);
		
		Division_Vertical.setTopComponent(Barra_Superior);
		Division_Vertical.setBottomComponent(Division_Horizontal);
		
		Division_Horizontal.setLeftComponent(Panel_Principal);
		Division_Horizontal.setRightComponent(Barra_Derecha);



		

		
		Etiqueta_Tiempo_Superior = new JLabel("Tiempo: ");

		Etiqueta_Tiempo_Superior.setBounds(250,10,350,30);
		Derecha();
		Crear_Atras();
		Barra_Superior.add(Atras);



	}
	
	public void Derecha() {
		Indicador_Vida_texto = new JLabel();
		Indicador_Vida_texto.setText("100%");
		Indicador_Vida_texto.setBounds(10,10,200,30);
		
		Barra_Salud = new JProgressBar(0 , 100);
		Barra_Salud.setBounds(10,50,200,75);
		Barra_Salud.setOpaque(true);
		Barra_Salud.addChangeListener(this);
		Barra_Salud.setValue(salud);
		Barra_Derecha.add(Barra_Salud);
		Barra_Superior.add(Etiqueta_Tiempo_Superior);
		
		Barra_Derecha.add(Barra_Salud);
		Barra_Derecha.add(Indicador_Vida_texto);
	}
	
	public void Interfaz_Estadisticas() {
		JLabel Design_1, Design_2, Design_3;
		 Panel_Opciones= new JPanelClass(null, "fondo_2.jpg");
		 
		 Modelo_Puntuaciones = new DefaultTableModel();
		 
		 Tabla_Puntuaciones = new JTable(Modelo_Puntuaciones);
		 Tabla_Puntuaciones.setOpaque(false);
		 Tabla_Puntuaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		 Tabla_Puntuaciones.getTableHeader().setReorderingAllowed(false);
		 Tabla_Puntuaciones.setShowGrid(true);
		 Tabla_Puntuaciones.setShowVerticalLines(true);
		 Tabla_Puntuaciones.setShowHorizontalLines(true);
		 
		 JSTabla = new JScrollPane(Tabla_Puntuaciones);
		 JSTabla.setBounds(250,10,450,600);
		 
		 RD_Mejore_tiems = new ButtonGroup();
		 
		 Mejor_tiemp = new JRadioButton("Más tiempo");
		 Mejor_tiemp.setBounds(10, 300, 150, 30);
		 Mejor_tiemp.setOpaque(false);
		 Mejor_tiemp.setActionCommand("ASC");
		 Mejor_tiemp.addItemListener(this);
		 
		 Peor_tiem = new JRadioButton("Menos tiempo");
		 Peor_tiem.setBounds(10, 330, 150, 30);
		 Peor_tiem.setOpaque(false);
		 Peor_tiem.setActionCommand("DESC");
		 Peor_tiem.addItemListener(this);
		 
		 Design_3 = new JLabel();
		 Design_3.setBounds(5,290,155,80);
		 Design_3.setBorder(border.createTitledBorder(border.createLineBorder(Color.BLACK),"Ordernar por:",TitledBorder.LEFT,TitledBorder.TOP, new Font("Sono", Font.PLAIN, 13), Color.BLACK));
		 
		 
		 RD_Mejore_tiems.add(Mejor_tiemp);
		 RD_Mejore_tiems.add(Peor_tiem);
		 
		 RD_Mejore_punt = new ButtonGroup();
		 
		 Design_1 = new JLabel();
		 Design_1.setBounds(5,380,155,90);
		 Design_1.setBorder(border.createTitledBorder(border.createLineBorder(Color.BLACK),"Ordernar por:",TitledBorder.LEFT,TitledBorder.TOP, new Font("Sono", Font.PLAIN, 13), Color.BLACK));
		 
		 Mejor_punt = new JRadioButton("Puntuación más alta");
		 Mejor_punt.setBounds(10, 400, 150, 30);
		 Mejor_punt.setOpaque(false);
		 Mejor_punt.setActionCommand("ASC");
		 Mejor_punt.addItemListener(this);
		 
		 Peor_Punt = new JRadioButton("Puntuación más baja");
		 Peor_Punt.setBounds(10, 430, 150, 30);
		 Peor_Punt.setActionCommand("DESC");
		 Peor_Punt.addItemListener(this);
		 Peor_Punt.setOpaque(false);
		 
		 RD_Mejore_punt.add(Mejor_punt);
		 RD_Mejore_punt.add(Peor_Punt);
		 
		 RD_Juegos = new ButtonGroup();

		 Design_2 = new JLabel();
		 Design_2.setBounds(5,510,155,110);
		 Design_2.setBorder(border.createTitledBorder(border.createLineBorder(Color.BLACK),"Mostrar:",TitledBorder.LEFT,TitledBorder.TOP, new Font("Sono", Font.PLAIN, 13), Color.BLACK));
		 
		 
		 Juego_1 = new JRadioButton("Juego: Area");
		 Juego_1.setActionCommand("Area");
		 Juego_1.setBounds(10,520,150,30);
		 Juego_1.setOpaque(false);
		 
		 Juego_2 = new JRadioButton("Juego: Type");
		 Juego_2.setActionCommand("Type");
		 Juego_2.setBounds(10,550,150,30);
		 Juego_2.setOpaque(false);
		 
		 Juego_All = new JRadioButton("Ambos");
		 Juego_All.setActionCommand("Todo");
		 Juego_All.setBounds(10,580,150,30);
		 Juego_All.setOpaque(false);
		 
		 RD_Juegos.add(Juego_1);
		 RD_Juegos.add(Juego_2);
		 RD_Juegos.add(Juego_All);
		 
		 Caja_Tiempo_Min = new JTextField();
		 Caja_Tiempo_Max = new JTextField();
		 
		 Atras_Estadisticas = new ButtonClass("atras");
		 Atras_Estadisticas.addActionListener(this);
		 Atras_Estadisticas.setBounds(10,645, 200, 30);
		 
		 Panel_Opciones.add(Atras_Estadisticas);
		 Panel_Opciones.add(Design_1);
		 Panel_Opciones.add(Design_2);
		 Panel_Opciones.add(Design_3);
		 Panel_Opciones.add(JSTabla);
		 Panel_Opciones.add(Mejor_tiemp);
		 Panel_Opciones.add(Peor_tiem);
		 Panel_Opciones.add(Mejor_punt);
		 Panel_Opciones.add(Peor_Punt);
		 Panel_Opciones.add(Juego_1);
		 Panel_Opciones.add(Juego_2);
		 Panel_Opciones.add(Juego_All);
		 Panel_Opciones.add(Caja_Tiempo_Min);
		 Panel_Opciones.add(Caja_Tiempo_Max);
		 
			for(Component Filtradores:Panel_Opciones.getComponents()) {
				if(Filtradores instanceof JRadioButton) {
					((JRadioButton) Filtradores).addActionListener(this);
					((JRadioButton) Filtradores).addItemListener(this);
					Filtradores.setFont(new Font("Sono", Font.PLAIN, 12));
				}
			}
		 
		 
	}
	

	
	public void Iniciar_Juego() {
		ImageIcon normal = new ImageIcon(getClass().getResource("/imghit/hit_base.png"));
		ImageIcon seleccionado = new ImageIcon(getClass().getResource("/imghit/hit_seleccionado.png"));
		ImageIcon presionado = new ImageIcon(getClass().getResource("/imghit/hit_presionado.png"));
		ImageIcon normal_escalado = new ImageIcon(normal.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
		ImageIcon seleccionado_escalado = new ImageIcon(seleccionado.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
		ImageIcon presionado_escalado = new ImageIcon(presionado.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
		PUSH_IT = new JButton(""+countdownStarter,normal);

		PUSH_IT.setFont(new Font("Sono", Font.PLAIN, 15));
		PUSH_IT.setBackground(new Color(111,205,102));
		PUSH_IT.setSize(100,100);
		PUSH_IT.setIcon(normal_escalado);	
		PUSH_IT.addMouseListener(this);
		PUSH_IT.addActionListener(this);
		PUSH_IT.addChangeListener(this);
		PUSH_IT.setBorderPainted(false);
		PUSH_IT.setContentAreaFilled(false);
		PUSH_IT.setFocusable(false);
		PUSH_IT.setRolloverEnabled(true);
		PUSH_IT.setHorizontalTextPosition(SwingConstants.CENTER);
		PUSH_IT.setVerticalTextPosition(SwingConstants.CENTER);
		Random R, R2;
		R = new Random();
		R2 = new Random();
		int X=40,Y=40;
		int Cor_x[] = new int[580], Cor_y[] = new int[580];
		for(int m=0; m<580; m++) {
			Cor_y[m] = m;
			Cor_x[m] = m;
//			System.out.println(Cor_x[m]+" , "+Cor_y[m]);
	}
		X = R.nextInt(580);
		Y = R2.nextInt(580);
		PUSH_IT.setLocation(Cor_x[X], Cor_y[Y]);
		Panel_Principal.add(PUSH_IT);
	}
	
	public void Temporizador_Logico_Juego_1() {

		Salud=100;
		tiempo_int=4;
		countdownStarter=3;
		Barra_Salud.setValue(Salud);
		Sistema_Tiempo = new Timer();
		Task_Texto_Boton = new TimerTask() {

		
			public void run() {		
				Most_Temp =Contador_Milis.format(countdownStarter);
				PUSH_IT.setText(Most_Temp);
				countdownStarter=countdownStarter-0.10;
				Barra_Tiempo_Push.setValue(tiempo_int);
				System.out.println("Tiempo del boton: "+Most_Temp);
			}
		};//Timer text of the button

		TimerTask Tiempo_Segundos = new TimerTask() {
			
			
			
			
			public void run() {
				 
				segundos_totales++;
				segundos_usables++;
				if(segundos_usables==60) {
					minutos++;
					segundos_usables=0;
				}
			}
		};
		Task_Logico_Boton = new TimerTask() {

			public void run() {
				tiempo_int--;
				System.out.println("Tiempo restante: "+tiempo_int);
				if(tiempo_int==0)
					NO_PUSH();	
			}
		};//Timer logic change button

		Task_Salud = new TimerTask() {
			public void run() {
				Barra_Salud.setValue(Salud);
				Indicador_Vida_texto.setText(Salud+"%");
				if(Salud>0)  {
					Salud--;
				}
			}
		};
		//Timer's working
		Sistema_Tiempo.schedule(Task_Salud, 1, 100);
		Sistema_Tiempo.schedule(Task_Texto_Boton, 1,100);
		Sistema_Tiempo.schedule(Task_Logico_Boton, 1,1000);
		Sistema_Tiempo.schedule(Tiempo_Segundos, 1, 1000);


	}//Timer's game 1 Method

	
	private void Fatal_1() {
		Task_Salud.cancel();
		Task_Logico_Boton.cancel();
		Task_Texto_Boton.cancel();
		Sistema_Tiempo.cancel();
		System.out.println("La prueba de juego ha finalizado.");
	}
	
	private void PUSH() {
		countdownStarter=3;
		tiempo_int=3;
		Random R, R2;
		R = new Random();
		R2 = new Random();
		int X=40,Y=40;
		int Cor_x[] = new int[580], Cor_y[] = new int[580];
		for(int m=0; m<580; m++) {
			Cor_y[m] = m;
			Cor_x[m] = m;
	}
		X = R.nextInt(580);
		Y = R2.nextInt(580);
		Salud=Salud+5;
		Barra_Salud.setValue(Salud);
		PUSH_IT.setText(""+tiempo_int);
		PUSH_IT.setLocation(Cor_x[X], Cor_y[Y]);
		hits++;
	}
	
	private void NO_PUSH() {
		countdownStarter=3;
		tiempo_int=3;
		Random R, R2;
		R = new Random();
		R2 = new Random();
		int X=40,Y=40;
		int Cor_x[] = new int[580], Cor_y[] = new int[580];
		for(int m=0; m<580; m++) {
			Cor_y[m] = m;
			Cor_x[m] = m;
	}
		X = R.nextInt(580);
		Y = R2.nextInt(580);
		Salud=Salud-5;
		Barra_Salud.setValue(Salud);
		PUSH_IT.setLocation(Cor_x[X], Cor_y[Y]);
		fallos++;
	}//fallo o se acabo el tiempo


	public void Logistica_Seleccion() {
		if(juego_1==true) {
			Iniciar_Juego();
			Temporizador_Logico_Juego_1();
			Interfaz_Juego_1();
			

			Derecha();
			
			Baraja_Paneles.show(Contenedor_Paneles, "juego1");
		}else if(juego_2==true) {

		}

	}
	
	public void Logica_Regresar_Menu() {
		int Opcion;
		Opcion = JOptionPane.showConfirmDialog(Ventana, "¿Quieres regresar al menú?", "Has perdido!...", JOptionPane.YES_NO_OPTION);
		if(Opcion==0) {//YES is selected
			Regresar_Menu=true;
			Re_Intentar=false;
		}else {//NO is selected
			Regresar_Menu=false;
			Re_Intentar=true;
		}
		Respuesta_Method();
	}//Menu back or retry Method
	
	private void Respuesta_Method() {
		if(Regresar_Menu==true) {
			Baraja_Paneles.show(Contenedor_Paneles, "inicio");
		}else if(Re_Intentar==true) {
			Salud=100;
			Temporizador_Logico_Juego_1();
		}
	}//Private Method Result
	
	public void Crear_Atras() {
		Atras = new JButton("Regresar...");
		Atras.setBounds(0,0,200,40);
		Atras.addActionListener(this);
	}
	
	public void Crear_Atras_Secundario() {
		Atras_Secundario = new JButton("Regresar...");
		Atras_Secundario.setBounds(0,0,200,40);
		Atras_Secundario.addActionListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		ImageIcon seleccionado = new ImageIcon(getClass().getResource("/imghit/hit_seleccionado.png"));


		ImageIcon seleccionado_escalado = new ImageIcon(seleccionado.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));

		if(e.getSource()==PUSH_IT)
			PUSH_IT.setIcon(seleccionado_escalado);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub


		System.out.println();
		ImageIcon normal = new ImageIcon(getClass().getResource("/imghit/hit_base.png"));
		ImageIcon normal_escalado = new ImageIcon(normal.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH));
		if(e.getSource()==PUSH_IT) {
			PUSH_IT.setIcon(normal_escalado);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(Barra_Salud.getValue()==0) {
		Fatal_1();
		Subir_Puntuacion();
		Logica_Regresar_Menu();

	}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
System.out.println("El boton funciona  correctamente bien");
		if(e.getSource()==PUSH_IT) {
			PUSH();
		}//push it
		else if(e.getSource()==Etiqueta_Area) {
			NO_PUSH();
		}
		
		if(e.getSource()==Cambiar_Juegos_1) {
			juego_1=true;
			juego_2=false;
			try { 
				Icon icon_banner = new ImageIcon(getClass().getResource("banner_juego1.png"));
				Etiqueta_Banner.setIcon(icon_banner);
			}catch (Exception Err_1) {
				System.err.println("No se ha podido cargar el nuevo banner: \n"+Err_1.getMessage());
			}
		}else if(e.getSource()==Cambiar_Juegos_2) {
			juego_2=true;
			juego_1=false;
			try { 
				Icon icon_banner = new ImageIcon(getClass().getResource("banner_juego2.png"));
				Etiqueta_Banner.setIcon(icon_banner);
			}catch (Exception Err_1) {
				System.err.println("No se ha podido cargar el nuevo banner: \n"+Err_1.getMessage());
			}
		}
		
		if(e.getSource()==Iniciar_Juego) {
			if(Sesion==false)
			JOptionPane.showMessageDialog(Ventana, "Inicia sesion para jugar...");
			else if(juego_1==false && juego_2==false) {
				JOptionPane.showMessageDialog(Ventana, "Selecciona un modo de juego...");
			}else {
				Logistica_Seleccion();
			}
			
			if(e.getSource()==Atras || e.getSource()==Atras_Secundario) {
				Baraja_Paneles.show(Contenedor_Paneles, "estadisticas");
			}else if(e.getSource()==Salir_Juego)
				Ventana.dispose();
			}else if(e.getSource()==Estadisticas_Juego) {
				Baraja_Paneles.show(Contenedor_Paneles, "estadisticas");
			}
		
		if(e.getSource()==Atras_Estadisticas) {

			Baraja_Paneles.show(Contenedor_Paneles, "inicio");
		}
		if(e.getSource()==Iniciar_Sesion) {
			Ventana_Sesion.setVisible(true);
			Ventana_Sesion.setEnabled(true);
		}
		
		if(e.getSource()==Boton_Iniciar) {
			Validacion_Usuario();

				if(Caja_Nombre.getText().toString().equals(Nombre)==true && Caja_Pass.getText().toString().equals(Pass)==true) {
					JOptionPane.showMessageDialog(Ventana_Sesion, "Sesion iniciada");
					Nombre_Actual=Nombre;
					Pass_Actual=Pass;
					Sesion=true;
				} else {
					JOptionPane.showMessageDialog(Ventana_Sesion, "Usuario y/o contraseña incorrectos...");
				}
			}
		
		if(e.getSource()==Boton_Crear) {
			 if(Caja_Nombre.getText().isEmpty() || Caja_Pass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(Ventana_Sesion, "Falta un campo por llenar...");
				}else
			Crear_Cuenta();
		 }
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Tabla();
//		Filas_Tabla();
	}
	public void Filtrador_Real_Time() {
	
	}//Method in header to update the filter in real time
	
	public void Ventana_Sesion_Frame() {
//		boolean Sesion=false;
//		String Nombre, Pass;
//		JButton Boton_Iniciar, Boton_Crear;
//		JFrame Ventana_Sesion;
//		JPanel Panel_Sesion;
//		JTextField Caja_Nombre, Caja_Pass;
		JLabel Texto_1, Texto_2;
		
		Ventana_Sesion = new JFrame("Iniciar sesion en nekomimint Games!");
		Ventana_Sesion.setSize(500,200);
		Ventana_Sesion.setLocationRelativeTo(Ventana);
		Panel_Sesion = new JPanel(null);
		
		try { 
			ImageIcon icono_programa = new ImageIcon(getClass().getResource("icono_sesio.png"));
			Ventana_Sesion.setIconImage(icono_programa.getImage());
		}catch (Exception e) {
			System.err.println("No se ha podido cargar el icono del programa: \n"+e.getMessage());
		}
		
		Boton_Iniciar = new JButton("Iniciar sesion");
		Boton_Iniciar.setBounds(10,20,150,40);
		Boton_Iniciar.addActionListener(this);
		Boton_Crear = new JButton("Crear cuenta");
		Boton_Crear.setBounds(10,70,150,40);
		Boton_Crear.addActionListener(this);
		
		Caja_Nombre = new JTextField();
		Caja_Nombre.setBounds(170,30,200,30);
		
		Caja_Pass = new JTextField();
		Caja_Pass.setBounds(170,90,200,30);
		
		Texto_1 = new JLabel("Nombre: ");
		Texto_1.setBounds(170,10,200,30);
		
		Texto_2 = new JLabel("Contraseña: ");
		Texto_2.setBounds(170,70,200,30);
		
		Panel_Sesion.add(Boton_Iniciar);
		Panel_Sesion.add(Boton_Crear);
		Panel_Sesion.add(Caja_Nombre);
		Panel_Sesion.add(Caja_Pass);
		Panel_Sesion.add(Texto_1);
		Panel_Sesion.add(Texto_2);
		
		Ventana_Sesion.setContentPane(Panel_Sesion);
		Ventana_Sesion.setVisible(false);
		Ventana_Sesion.setEnabled(false);
		
	}
	
		//HERE IS ALL SQL METHODS
	public void Conectador() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Cable_Ethernet=DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");	
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Instruccion_SQL.executeUpdate("CREATE DATABASE IF NOT EXISTS rankings");			
			Desconectador();			
			Cable_Ethernet=DriverManager.getConnection("jdbc:mysql://localhost/rankings", "root", "");			
		} catch (SQLException e) {
			System.out.println("No existe la Base de datos // XAMPP apagado "); //XAMPP is shutdown or the Database do not exists
		} catch (ClassNotFoundException e) {
			System.out.println("No se puede acceder al conector SQL");
		}//Connector SQL is missing
	}//Connecter
	
	public void Desconectador() {	
		
		try {
			Cable_Ethernet.close();
		} catch (SQLException e) {
			System.out.println("Alguien tiro del cable...");
		}
	}//Disconnecter
	
	public void Crear_Tablas() {
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Instruccion_SQL.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (id_usuario INT AUTO_INCREMENT PRIMARY KEY, nombre varchar(18) NOT NULL, contra varchar(18) NOT NULL)");	
			Instruccion_SQL.executeUpdate("CREATE TABLE IF NOT EXISTS puntuaciones(id_puntuacion INT AUTO_INCREMENT PRIMARY KEY, id_usuario INT NOT NULL,  juego varchar(10) NOT NULL, aciertos INT NOT NULL, fallos INT NOT NULL, tiempo varchar(20), segundos INT NOT NULL, FOREIGN KEY(id_usuario) REFERENCES usuarios(id_usuario) ON UPDATE CASCADE) ENGINE=INNODB");
			Desconectador();
		}catch (Exception e) {
			System.out.println("Ha ocurrido un error al crear las tablas: \n "+e.getMessage());
		}
	}//This method create the 2 tables used in this project
	
	public void Insertar_Usuarios() {
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT * FROM usuarios");
			if(!Consulta_SQL.next()) {
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, 'nekomimint', '1234567890')");
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, 'NekoLoveFan', 'ABCDEFGHIJK')");
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, 'KZero', 'HellouDa')");
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, 'Beks', 'Cool')");
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, 'JJ', 'coso')");
			}
			Desconectador();
		}catch (Exception e) {
			System.out.println("Error al insertar usuarios a las tablas: \n "+e.getMessage());
		}//Can't insert values
	}//Method to insert a default's user's
	
	public void Insertar_Puntuaciones() {
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT * FROM puntuaciones");
			if(!Consulta_SQL.next()) {
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 1, 'Area', 27, 13, '2:38', 158)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 4, 'Area', 41, 5, '2:09', 129)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 2, 'Type', 18, 7, '2:00', 120)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 1, 'Area', 45, 9, '2:08', 128)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 3, 'Type', 67, 21, '1:07', 67)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 3, 'Area', 89, 42, '2:36', 156)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 3, 'Area', 11, 6, '0:48', 48)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 4, 'Type', 24, 15, '1:40', 100)");
				Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES (null, 2, 'Type', 72, 29, '4:00', 240)");
			}
			Desconectador();
		}catch (Exception e) {
			System.out.println("Error al insertar usuarios a las tablas: \n "+e.getMessage());
		}//Can't insert values
	}//Method to insert a default's user's
	
	public void Filas_Tabla() {
		Modelo_Puntuaciones.setColumnCount(0);
		Modelo_Puntuaciones.getDataVector().removeAllElements();
		
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT usuarios.nombre, puntuaciones.juego, puntuaciones.aciertos, puntuaciones.fallos, puntuaciones.tiempo FROM usuarios INNER JOIN puntuaciones; ");					
			RS_Meta = Consulta_SQL.getMetaData();
			
			for(int x=1;x<=RS_Meta.getColumnCount();x++) {
				Modelo_Puntuaciones.addColumn(RS_Meta.getColumnLabel(x));
				
			}
//			while(Consulta_SQL.next()) {
//				for(int x=0;x<Campos_DB.getColumnCount();x++)
//					Arrow_String[x] = Consulta_SQL.getString(x+1);
//				Modelo_Puntuaciones.addRow(Arrow_String);
//			}
			Desconectador();
		}catch (Exception e) {
			System.out.println("Error al cargas los titulos de las columnas: \n "+e.getMessage());
		}
	}//Title table from SQL
public void Filtrador() {
	try {
	Conectador();	
	Modelo_Puntuaciones.setColumnCount(0);
	Modelo_Puntuaciones.getDataVector().removeAllElements();
	
	
	String Restriccionario = " WHERE ";
	String Conector = " AND ";
	String Ordenador = " ORDER BY ";
	StringBuilder Lego_Busqueda = new StringBuilder();
	String Final_R=null;
//	Lego_Busqueda.append("SELECT usuarios.nombre, puntuaciones.juego, puntuaciones.aciertos, puntuaciones.fallos, puntuaciones.tiempo FROM usuarios INNER JOIN puntuaciones ");
//	if(RD_Juegos.getSelection().getActionCommand().equals(null) && RD_Mejore_punt.getSelection().getActionCommand().equals(null) && RD_Mejore_tiems.getSelection().getActionCommand().equals(null)) {
//		Final_R = Lego_Busqueda.toString();
//		System.out.println("No hay ningun RD seleccionado");
//	}else if(RD_Juegos.getSelection().getActionCommand().equals(null)==true && RD_Mejore_punt.getSelection().getActionCommand().equals(null)==true){//1 y 2 estan
//		Lego_Busqueda.append(Restriccionario);
//		Lego_Busqueda.append("puntuaciones.juego='"+RD_Juegos.getSelection().getActionCommand().toString()+"'");
//		Lego_Busqueda.append(Ordenador);
//		Lego_Busqueda.append("puntuaciones.aciertos "+RD_Mejore_punt.getSelection().getActionCommand().toString());
//	}else if(RD_Mejore_punt.getSelection()==null && RD_Mejore_tiems==null) {// 2 y 3 estan
//		
//	}else if(RD_Juegos.getSelection()==null && RD_Mejore_tiems==null) {//1 y 3 estan
//		
//	}else {//todos estan
//		
//	}

    Instruccion_SQL = Cable_Ethernet.createStatement();
    Consulta_SQL=Instruccion_SQL.executeQuery("");
//    Consulta_SQL=Instruccion_SQL.executeQuery(Final_R.toString());
    RS_Meta= Consulta_SQL.getMetaData();
	for(int x=1;x<=RS_Meta.getColumnCount();x++) {
		Modelo_Puntuaciones.addColumn(RS_Meta.getColumnLabel(x));}
	while(Consulta_SQL.next()) {
		for(int x=0;x<RS_Meta.getColumnCount();x++)
			Arrow_String[x] = Consulta_SQL.getString(x+1);
		Modelo_Puntuaciones.addRow(Arrow_String);
		} 
	}catch (SQLException e) {
		System.err.println("No se la logrado filtrar nada: \n"+e.getMessage());
		
	}
}//Method
	
	public void Validacion_Usuario() {
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT	nombre, contra FROM usuarios WHERE nombre='"+Caja_Nombre.getText().toString()+"'");

			if(Consulta_SQL.next()) {
					Nombre = Consulta_SQL.getString("nombre");
					Pass = Consulta_SQL.getString("contra");
			}
			Desconectador();
		}catch (Exception e) {
			System.out.println("Ha habido un error al validar el usuario y password: \n "+e.getMessage());
		}//Login validation
	}
	
	public void Tabla() {
		Modelo_Puntuaciones.setColumnCount(0);
		Modelo_Puntuaciones.getDataVector().removeAllElements();
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT usuarios.nombre, puntuaciones.juego, puntuaciones.aciertos, puntuaciones.fallos, puntuaciones.tiempo FROM usuarios INNER JOIN puntuaciones ON puntuaciones.id_usuario = usuarios.id_usuario ORDER BY puntuaciones.aciertos DESC ");
			RS_Meta = Consulta_SQL.getMetaData();
			for(int x=1;x<=RS_Meta.getColumnCount();x++) {
				Modelo_Puntuaciones.addColumn(RS_Meta.getColumnLabel(x));}
			while(Consulta_SQL.next()) {
				for(int x=0;x<RS_Meta.getColumnCount();x++)
					Arrow_String[x] = Consulta_SQL.getString(x+1);
				Modelo_Puntuaciones.addRow(Arrow_String);
				}
			Desconectador();
		}catch (Exception e) {
			System.out.println("No se ha podido cargar ningun registro de los usuarios: \n "+e.getMessage());
		}
	}//Load default
	
	public void Crear_Cuenta() {
			try {
				Conectador();
				Instruccion_SQL= Cable_Ethernet.createStatement();
				Instruccion_SQL.executeUpdate("INSERT INTO usuarios VALUES(null, '"+Caja_Nombre.getText().toString()+"', '"+Caja_Pass.getText().toString()+"')");
				JOptionPane.showMessageDialog(Ventana_Sesion, "Se ha creado el nuevo usuario");
				Desconectador();
			}catch (Exception e) {
				System.out.println("Ha habido un error al enviar el usuario y password: \n "+e.getMessage());
			}//Upload new user
			Caja_Nombre.setText(null);
			Caja_Pass.setText(null);
		
	}
	
	public void Subir_Puntuacion() {
		try {
			Conectador();
			Instruccion_SQL= Cable_Ethernet.createStatement();
			String Nombre_local, Tiempo_Subir;
			int ID_local=0;

			
			Consulta_SQL = Instruccion_SQL.executeQuery("SELECT nombre, id_usuario FROM usuarios WHERE nombre='"+Nombre_Actual+"'");
			
		
			if(Consulta_SQL.next()) {
				Nombre_local = Consulta_SQL.getString("nombre");
				ID_local = Consulta_SQL.getInt("id_usuario");
			}
			String juego_local=null;
			if(juego_1==true) {
			juego_local="Area";	
			}else if(juego_2==true) {
				juego_local="Type";
			}
			cadena_final = minutos+":"+segundos_usables;
			Instruccion_SQL.executeUpdate("INSERT INTO puntuaciones VALUES(null, "+ID_local+", '"+juego_local+"', "+hits+", "+fallos+", '"+cadena_final+"', "+segundos_totales+")");
			JOptionPane.showMessageDialog(Ventana_Sesion, "Se ha subido la nueva puntuacion");
			Desconectador();
		}catch (Exception e) {
			System.out.println("Ha habido un problema al subir el puntaje del usuario: \n "+e.getMessage());
		}//Upload new score
	}
}//Class


//Tomare estas 2 lineas para hacer comentarios al respecto sobre este trabajo: Resulta que es un proyecto final, donde se decide el 100% de nuestra calificacion, si bien
//este trabajo esta dentro de ese espectro, mis amigos confian mucho en que lograre crear todo lo que necesito en un solo dia, intentare esforzarme al maximo,
//espero no defraudarlos, aunque bueno, supongo que de algunas manera tengo que sacar a relucir el apellido "Klassen" que cargo sobre mis hombros,
//Demasiada presion indirecta...