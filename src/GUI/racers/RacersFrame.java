package GUI.racers;

import factory.*;
import game.arenas.Arena;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Maor Huri 207871435
 * @author Matan Atias 318856937 */

public class RacersFrame extends JFrame implements ActionListener {
	
	private static RaceBuilder builder = RaceBuilder.getInstance();;
	private static ArrayList<Racer> racers;
	private boolean raceStarted = false;
	private boolean raceFinished = false;
	private int numOfRacers = 0;
	private JComboBox<String> arenaOptions;
	private JComboBox<String> racerOptions;

	private String[] optionsArray5;

	private JComboBox<String> racerOptions2;

	private JComboBox<String> colorOptions;
	private JTextField tf_ArenaLength;
	private JTextField tf_MaxRacers;
	private JTextField tf_RacerName;
	private JTextField tf_MaxSpeed;
	private JTextField tf_Acceleration;
	private int arenaLength = 1000;
	private int maxRacers = 8;
	private int arenaHeight = 700;
	private String chosen_Arena = null;
	private ImageIcon racersIcons[] = null;
	private Arena arena = null;
	private JFrame infoTable = null;

	private Point finish = new Point(arenaLength, arenaHeight);

	private JButton BdefaultRace = new JButton("Default Race");

	private int N = 5;

	private String[] optionsArray3 = {"Black", "Green", "Blue", "Red", "Yellow"};


	/**
	 * Creates a new RacersFrame instance.
	 */
	public RacersFrame(){

    	super("Race");
    	this.setContentPane(MainPanel());
		this.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
    }



	/**
	 * Handles the action performed event.
	 *
	 * @param e The action event.
	 */


    @Override
    public void actionPerformed(ActionEvent e) {
    	switch (e.getActionCommand()) {

			case "Default Race":

				racers = new ArrayList<>();
				racersIcons = new ImageIcon[maxRacers];
				numOfRacers = 0;
				raceStarted = raceFinished = false;
				String defRacerClass = null;


				if (raceStarted && !raceFinished) {
					JOptionPane.showMessageDialog(this, "Race started! Please wait.");
					return;
				}


				RaceDefaultBuilder rBuilder = new MyBuilder();
				CarRaceBuilder carRace = new CarRaceBuilder(rBuilder);
				carRace.constructRace();
				Race race = carRace.getRace();


				chosen_Arena = race.getNameArena();
				racers = race.getRacers();
				arena = race.getArena();
				arena.setActiveRacers(race.getRacers());
				arena.initRace();


				for(int i =0; i < 8;i++) {
					racersIcons[i]= new ImageIcon(new ImageIcon("icons/" + "Car" + "RED" + ".png").getImage()
							.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
					numOfRacers++;
				}

				updateFrame();
				break;


			case "Build arena":

				racers = new ArrayList<>();
				racersIcons = new ImageIcon[maxRacers];
				chosen_Arena = (String) arenaOptions.getSelectedItem();
				numOfRacers = 0;
				raceStarted = raceFinished = false;

				if (raceStarted && !raceFinished) {
					JOptionPane.showMessageDialog(this, "Race started! Please wait.");
					return;
				}
				try {
					arenaLength = Integer.parseInt(tf_ArenaLength.getText());
					maxRacers = Integer.parseInt(tf_MaxRacers.getText());
					if (arenaLength < 100 || arenaLength > 3000 || maxRacers <= 0 || maxRacers > 20)
						throw new Exception();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
					return;
				}

				int newHeight = (maxRacers + 1) * 60;
				if (newHeight > 700)
					this.arenaHeight = newHeight;
				else
					this.arenaHeight = 700;

				//Factory method

				arena_Factory arena_factory = new arena_Factory();
				arena = arena_factory.createArena(chosen_Arena,arenaLength, maxRacers);
				updateFrame();
				break;


			case "Add Racer":

				String name;
				double maxSpeed;
				double acceleration;
				String color = (String) colorOptions.getSelectedItem();
				EnumContainer.Color col = null;
				String racerType = (String) racerOptions.getSelectedItem();
				String racerClass = null;



				if (raceFinished) {
					JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
					return;
				}
				if (raceStarted) {
					JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
					return;
				}
				if (arena == null) {
					JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
					return;
				}
				if (numOfRacers == maxRacers) {
					JOptionPane.showMessageDialog(this, "Can't add more racers! there is maximum racers");
					return;
				}

				try {
					name = tf_RacerName.getText();
					maxSpeed = Double.parseDouble(tf_MaxSpeed.getText());
					acceleration = Double.parseDouble(tf_Acceleration.getText());
					if (name.isEmpty() || maxSpeed <= 0 || acceleration <= 0)
						throw new Exception();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
					return;
				}


				if (color.equals("Black"))
					col = EnumContainer.Color.RED;
				else if (color.equals("Red"))
					col = EnumContainer.Color.BLACK;
				else if (color.equals("Blue"))
					col = EnumContainer.Color.GREEN;
				else if (color.equals("Green"))
					col = EnumContainer.Color.BLUE;
				else if (color.equals("Yellow"))
					col = EnumContainer.Color.YELLOW;


				if (racerType.equals("Helicopter"))
					racerClass = "game.racers.air.Helicopter";
				else if (racerType.equals("Airplane"))
					racerClass = "game.racers.air.Airplane";
				else if (racerType.equals("Car"))
					racerClass = "game.racers.land.Car";
				else if (racerType.equals("Horse"))
					racerClass = "game.racers.land.Horse";
				else if (racerType.equals("Bicycle"))
					racerClass = "game.racers.land.Bicycle";
				else if (racerType.equals("SpeedBoat"))
					racerClass = "game.racers.naval.SpeedBoat";
				else if (racerType.equals("RowBoat"))
					racerClass = "game.racers.naval.RowBoat";

				try {
					Racer racer = null;
					if (racerClass == "game.racers.air.Airplane") {
						racer = builder.buildWheeledRacer(racerClass, name, maxSpeed, acceleration, col, 3);
					} else if (racerClass == "game.racers.land.Car") {
						racer = builder.buildWheeledRacer(racerClass, name, maxSpeed, acceleration, col, 4);
					} else if (racerClass == "game.racers.land.Bicycle") {
						racer = builder.buildWheeledRacer(racerClass, name, maxSpeed, acceleration, col, 2);
					} else {
						racer = builder.buildRacer(racerClass, name, maxSpeed, acceleration, col);
					}

					arena.addRacer(racer);
					arena.initRace();
					racers.add(racer);
				} catch (RacerTypeException ex) {
					JOptionPane.showMessageDialog(this, "Racer does not match to the arena! Choose another racer.");
					return;
				} catch (Exception ex) {
					System.out.println(ex);
				}


				racersIcons[numOfRacers] = new ImageIcon(new ImageIcon("icons/" + racerType + color + ".png").getImage()
						.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
				numOfRacers++;

				updateFrame();
				break;

			case "Add similar racer":

				String newColor = (String) colorOptions.getSelectedItem();
				EnumContainer.Color col2 = null;


				if (raceFinished) {
					JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
					return;
				}
				if (raceStarted) {
					JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
					return;
				}
				if (arena == null) {
					JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
					return;
				}
				if (numOfRacers == maxRacers) {
					JOptionPane.showMessageDialog(this, "Can't add more racers! there is maximum racers");
					return;
				}

				if (newColor.equals("Black"))
					col2 = EnumContainer.Color.BLACK;
				else if (newColor.equals("Red"))
					col2 = EnumContainer.Color.RED;
				else if (newColor.equals("Blue"))
					col2 = EnumContainer.Color.BLUE;
				else if (newColor.equals("Green"))
					col2 = EnumContainer.Color.GREEN;
				else if (newColor.equals("Yellow"))
					col2 = EnumContainer.Color.YELLOW;

				String choosenRacer2 = (String) racerOptions2.getSelectedItem();
				String racerType2 = null;
				Racer newRacer = null;
				try {
					for (int i = 0; i < numOfRacers; i++) {
						if (choosenRacer2 == racers.get(i).getName()) {
							newRacer = racers.get(i).clone();
							newRacer.upgrade(col2);
						}
					}
					racerType2 = newRacer.className();
                    arena.addRacer(newRacer);
					arena.initRace();
					racers.add(newRacer);
				} catch (RacerTypeException ex) {
					JOptionPane.showMessageDialog(this, "Racer does not match to the arena! Choose another racer.");
					return;
				} catch (Exception ex) {
					System.out.println(ex);
				}


				racersIcons[numOfRacers] = new ImageIcon(new ImageIcon("icons/" + racerType2 + newColor + ".png").getImage()
						.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
				numOfRacers++;

				updateFrame();

				break;


			case "Start Race":

				for (int i = 0; i < numOfRacers; i++) {
					racers.get(i).setFinish(finish);
				}

				for (int i = 0; i < numOfRacers; i++) {
					racers.get(i).setArena(arena);
				}

				if (arena == null || numOfRacers == 0) {
					JOptionPane.showMessageDialog(this, "Please build the arena first and add racers!");
					return;
				}
				if (raceFinished) {
					JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena and add racers.");
					return;
				}
				if (raceStarted) {
					JOptionPane.showMessageDialog(this, "Race already started!");
					return;
				}

				raceStarted = true;
				try {
					arena.startRace();
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
				if(raceStarted) {
					BdefaultRace.setVisible(false);
					for(int i =0; i < numOfRacers;i++){
						racers.get(i).setState(new active());
					}
				}





				new Thread(new Runnable() {
					public void run() {

						while (arena.hasActiveRacers()) {
							if (!arena.allFinished()) {
								try {
									Thread.sleep(30);
								} catch (InterruptedException ex) {
									ex.printStackTrace();
								}
								updateFrame();
								try {

									updateFrame();
								} catch (Exception e) {
								}

							}
						}
						updateFrame();

						raceFinished = true;

					}
				}).start();
				for (int i = 0; i < arena.getActiveRacers().size(); i++) {
					Thread thread = new Thread(arena.getActiveRacers().get(i));
					thread.start();
					Random random = new Random();
					int who = random.nextInt(0,numOfRacers);
					arena.getActiveRacers().get(who).getCurrentLocation().setX(arena.getActiveRacers().get(who).getBrokenSpot());

				}

				break;



			case "Show Info":

				if (arena == null || numOfRacers == 0) {
					JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
					return;
				}
				String[] column = { "Racer name", "Current speed", "Max speed", "Current X location", "State", "broken time" };
				String[][] data = new String[numOfRacers][6];
				int i = 0;


				arena.checkFinished();


				for(int j =0; j < arena.getActiveRacers().size(); j++){
					if(arena.getActiveRacers().get(j).getCurrentLocation().getX() < arenaLength)
						arena.getDisableds().add((arena.getActiveRacers().get(j)));
						arena.getActiveRacers().remove((arena.getActiveRacers().get(j)));

				}

				for(int j =0; j < arena.getActiveRacers().size(); j++){
					arena.getBrokens().add(arena.getDisableds().get(j));
					arena.getActiveRacers().remove(j);

				}




				for (Racer racer : arena.getCompletedRacers()) {
					data[i][0] = racer.getName();
					data[i][1] = "" + racer.getCurrentSpeed();
					data[i][2] = "" + racer.getMaxSpeed();
					data[i][3] = "" + racer.getCurrentLocation().getX();
					data[i][4] = "Completed";
					i++;
				}

				for (Racer racer : arena.getActiveRacers()) {
					data[i][0] = racer.getName();
					data[i][1] = "" + racer.getCurrentSpeed();
					data[i][2] = "" + racer.getMaxSpeed();
					data[i][3] = "" + racer.getCurrentLocation().getX();
					data[i][4] = "Active";
					i++;
				}
				for (Racer racer : arena.getBrokens()) {
					data[i][0] = racer.getName();
					data[i][1] = "" + racer.getCurrentSpeed();
					data[i][2] = "" + racer.getMaxSpeed();
					data[i][3] = "" + racer.getCurrentLocation().getX();
					data[i][4] = "broken";
					data[i][5] = "" + arena.getFinishTime() + " miliseconds";
					i++;
				}

				for (Racer racer : arena.getDisableds()) {
					data[i][0] = racer.getName();
					data[i][1] = "" + racer.getCurrentSpeed();
					data[i][2] = "" + racer.getMaxSpeed();
					data[i][3] = "" + racer.getCurrentLocation().getX();
					data[i][4] = "Failed";
					i++;
				}



				JTable table = new JTable(data, column);
				table.setPreferredScrollableViewportSize(table.getPreferredSize());
				JScrollPane scrollPane = new JScrollPane(table);

				JPanel tabPan = new JPanel();
				tabPan.add(scrollPane);

				if (infoTable != null)
					infoTable.dispose();
				infoTable = new JFrame("Racers information");
				infoTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				infoTable.setLocation(0, 700);
				infoTable.setContentPane(scrollPane);
				infoTable.pack();
				infoTable.setVisible(true);

				break;
		}


	}

	/**
	 * Creates the main panel of the frame that contains the arena.
	 *
	 * @return The main panel containing the arena.
	 */
    public JPanel arenaPanel() {

		JPanel arena_Panel = new JPanel();
		arena_Panel.setLayout(null);
		arena_Panel.setPreferredSize(new Dimension(arenaLength + 100, arenaHeight));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("icons/" + chosen_Arena + ".jpg").getImage()
				.getScaledInstance(arenaLength + 80, arenaHeight, Image.SCALE_DEFAULT));
		
		JLabel arenaPic = new JLabel(imageIcon);
		arenaPic.setLocation(0, 0);
		arenaPic.setSize(arenaLength + 80, arenaHeight);
		arena_Panel.add(arenaPic);

		for (int i = 0; i < numOfRacers; i++) {
			if((racers.get(i) != null)){
				JLabel racerIcon = new JLabel(racersIcons[i]);
				racerIcon.setLocation((int) racers.get(i).getCurrentLocation().getX() + 5,
						((int) racers.get(i).getCurrentLocation().getY()) + i * 65);
				racerIcon.setSize(70, 70);
				arenaPic.add(racerIcon);
			}
		}



		return arena_Panel;
	
    }

	/**
	 * Creates the control panel of the frame that contains various options for managing the racers and races.
	 *
	 * @return The control panel.
	 */
    public JPanel ControlPanel() {
    	
    	JPanel controlPanel = new JPanel();
		controlPanel.setLayout(null);
		controlPanel.setPreferredSize(new Dimension(140, arenaHeight));
        
        String[] optionsArray1 = {"Aerial Arena", "Land Arena", "Naval Arena"};
        arenaOptions = new JComboBox<>(optionsArray1);

		if (chosen_Arena != null)
			arenaOptions.setSelectedItem(chosen_Arena);
        
        JLabel LchooseArena = new JLabel("Choose arena:");
        controlPanel.add(LchooseArena);
        LchooseArena.setLocation(10, 15);
        LchooseArena.setSize(100, 10);
        
        controlPanel.add(arenaOptions);
        controlPanel.setLocation(10, 15);
        controlPanel.setSize(100, 20);
        arenaOptions.setBounds(10, 40, 120, 25);
        arenaOptions.addActionListener(this);

        
        JLabel LarenaLength = new JLabel("Arena length:");
        LarenaLength.setLocation(10, 70);
        LarenaLength.setSize(100, 10);
        controlPanel.add(LarenaLength);

        tf_ArenaLength = new JTextField("" + arenaLength);
    	tf_ArenaLength.setLocation(10, 85);
		tf_ArenaLength.setSize(100, 25);
        controlPanel.add(tf_ArenaLength);
        tf_ArenaLength.addActionListener(this);


        JLabel LmaxRacers = new JLabel("Max racers number:");
        LmaxRacers.setLocation(10, 115);
        LmaxRacers.setSize(150, 10);
        controlPanel.add(LmaxRacers);


        tf_MaxRacers = new JTextField("" + maxRacers); 
    	tf_MaxRacers.setLocation(10, 130);
		tf_MaxRacers.setSize(100, 25);
        controlPanel.add(tf_MaxRacers);
        tf_MaxRacers.addActionListener(this);


        JButton BbuildArena = new JButton("Build arena");
        BbuildArena.setLocation(10, 160);
        BbuildArena.setSize(100, 30);
        BbuildArena.addActionListener(this);
        controlPanel.add(BbuildArena);

        
    	JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
    	sep.setBackground(Color.BLACK);
		sep.setLocation(0, 195);
		sep.setSize(150, 20);
		controlPanel.add(sep);
    	
	    String[] optionsArray2 = {"Airplane", "Helicopter", "Bicycle", "Car", "Horse", "RowBoat", "SpeedBoat"};

	    racerOptions = new JComboBox<>(optionsArray2);
		racerOptions2 = new JComboBox<>(optionsArray2);

	    
	    JLabel LchooseRacer = new JLabel("Choose Racer:");
		controlPanel.add(LchooseRacer);
		LchooseRacer.setLocation(10, 210);
		LchooseRacer.setSize(100, 10);
	        
		controlPanel.add(racerOptions);
		racerOptions.setLocation(10, 230);
		racerOptions.setSize(100, 20);
		racerOptions.addActionListener(this);

	        
        JLabel LchooseColor = new JLabel("Choose Color:");
    	controlPanel.add(LchooseColor);
    	LchooseColor.setLocation(10, 255);
    	LchooseColor.setSize(100, 10);

    	
    	   
        colorOptions = new JComboBox<>(optionsArray3);
    	controlPanel.add(colorOptions);
    	colorOptions.setLocation(10, 275);
    	colorOptions.setSize(100, 20);
    	colorOptions.addActionListener(this);

        
        JLabel LracerName = new JLabel("Racer Name:");
        LracerName.setLocation(10, 305);
        LracerName.setSize(150, 10);
		controlPanel.add(LracerName);

    	tf_RacerName = new JTextField();
    	tf_RacerName.setLocation(10, 325);
		tf_RacerName.setSize(100, 25);
		controlPanel.add(tf_RacerName);
		tf_RacerName.addActionListener(this);

    	
        JLabel LmaxSpeed = new JLabel("Max Speed:");
        LmaxSpeed.setLocation(10, 350);
        LmaxSpeed.setSize(150, 14);
		controlPanel.add(LmaxSpeed);
		
        tf_MaxSpeed = new JTextField();
		tf_MaxSpeed.setLocation(10, 370);
		tf_MaxSpeed.setSize(100, 25);
		controlPanel.add(tf_MaxSpeed);
		tf_MaxSpeed.addActionListener(this);

		
        JLabel Lacceleration = new JLabel("Acceleration:");
        Lacceleration.setLocation(10, 400);
        Lacceleration.setSize(150, 10);
		controlPanel.add(Lacceleration);
		
    	tf_Acceleration = new JTextField();
		tf_Acceleration.setLocation(10, 420);
		tf_Acceleration.setSize(100, 25);
		controlPanel.add(tf_Acceleration);
		tf_Acceleration.addActionListener(this);

		
        JButton BaddRacer = new JButton("Add Racer");
        BaddRacer.addActionListener(this);
        BaddRacer.setLocation(10, 450);
        BaddRacer.setSize(100, 30);
		controlPanel.add(BaddRacer);

		JSeparator sep4 = new JSeparator(SwingConstants.HORIZONTAL);
		sep4.setLocation(0, 480);
		sep4.setSize(150, 10);
		sep4.setBackground(Color.BLACK);
		controlPanel.add(sep4);

		JLabel LchooseRacer2 = new JLabel("Choose Racer:");
		controlPanel.add(LchooseRacer2);
		LchooseRacer2.setLocation(10, 500);
		LchooseRacer2.setSize(100, 10);

		this.optionsArray5 = new String[numOfRacers];

		for(int i=0; i < numOfRacers;i++){
			this.optionsArray5[i] = racers.get(i).getName();
		}
		racerOptions2 = new JComboBox<>(optionsArray5);

		controlPanel.add(racerOptions2);
		racerOptions2.setLocation(10, 520);
		racerOptions2.setSize(100, 20);
		racerOptions2.addActionListener(this);

		JLabel LchooseColor2 = new JLabel("Choose Color:");
		controlPanel.add(LchooseColor2);
		LchooseColor2.setLocation(11, 550);
		LchooseColor2.setSize(100, 10);



		String[] optionsArray4 = {"Black", "Green", "Blue", "Red", "Yellow"};
		colorOptions = new JComboBox<>(optionsArray4);
		controlPanel.add(colorOptions);
		colorOptions.setLocation(10, 570);
		colorOptions.setSize(100, 20);
		colorOptions.addActionListener(this);

		JButton BsimilarRacer = new JButton("Add similar racer");
		BsimilarRacer.addActionListener(this);
		BsimilarRacer.setLocation(0, 600);
		BsimilarRacer.setSize(135, 30);
		controlPanel.add(BsimilarRacer);

		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep2.setLocation(0, 630);
		sep2.setSize(150, 10);
		sep2.setBackground(Color.BLACK);
		controlPanel.add(sep2);
		
		
        JButton BstartRace = new JButton("Start Race");
        BstartRace.setLocation(10, 640);
        BstartRace.setSize(100, 30);
        BstartRace.addActionListener(this);
		controlPanel.add(BstartRace);
		
		
        JButton BshowInfo = new JButton("Show Info");
        BshowInfo.setLocation(10, 670);
        BshowInfo.setSize(100, 30);
        BshowInfo.addActionListener(this);
		controlPanel.add(BshowInfo);



    	return controlPanel;
    }

	/**
	 * Creates the main panel of the frame that contains the arena and the control panel.
	 *
	 * @return The main panel.
	 */
    
	public JPanel MainPanel() {

	
		JPanel gamePanel = new JPanel();

		gamePanel.setLayout(new BorderLayout());
		BdefaultRace.setLocation(arenaLength -30, 10);
		BdefaultRace.setSize(130, 30);
		BdefaultRace.addActionListener(this);
		gamePanel.add(BdefaultRace);

		gamePanel.add(arenaPanel(), BorderLayout.WEST);
		gamePanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
		gamePanel.add(ControlPanel(), BorderLayout.EAST);

		return gamePanel;
		
		
	}


	/**
	 * Updates the frame content by refreshing the main panel and centering the frame on the screen.
	 */
	public void updateFrame() {
		this.setContentPane(MainPanel());
		this.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}
	/**
	 * The entry point of the application.
	 *
	 * @param args The command-line arguments.
	 */
    public static void main(String[] args) {
		new RacersFrame();


	}
}
    
