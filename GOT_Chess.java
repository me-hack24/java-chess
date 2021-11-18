import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import javax.swing.JOptionPane;
import java.applet.*;

public class GOT_Chess extends Applet implements ActionListener
{
    Panel p_card;  //Main panel - holds all screens
    Panel card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14; //the other screens
    CardLayout cdLayout = new CardLayout ();

    String player1, player2; //Player Name for house battle
    String houseW = " ";
    String warW = " "; //Final winning pieces
    JLabel moves, pic1, pic2, b1, b2;

    //Variables recieve which pieces was clicked and set the arrays accordingly
    char attacker = 'W'; 
    char defender = 'Z';
    char colorA = 'c';
    char colorB = 'z';

    //Sound files
    AudioClip theme1, chessSound, piano; 

    //Move count
    int move = 0; 

    JButton redo, undo;

    //Progress Bar
    JProgressBar MoveCount;
    JProgressBar MoveCount2;

    String[] possibleHouses2 = {"Baratheon", "Lannister", "Stark", "Targaryen"}; //House options

    //Turns
    JLabel turnpic, turnpic2; 
    char turn = defender;
    int last = -1;

    int row = 8; //grid
    int col = 8;

    //Buttons for each game
    JButton a1[] = new JButton [row * col]; 
    JButton a2[] = new JButton [row * col];

    //Team (the variables determine which teams are picked)
    char War[] [] = {{attacker, attacker, attacker, attacker, attacker, attacker, attacker, attacker}, {attacker, attacker, attacker, attacker, attacker, attacker, attacker, attacker},
	    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
	    {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
	    {defender, defender, defender, defender, defender, defender, defender, defender}, {defender, defender, defender, defender, defender, defender, defender, defender}};

    //Chess Piece
    char piece[] [] = {{'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}, {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'}};

    //Background color (the colors match the color of the teams fighting)
    char bg[] [] = {{colorA, colorB, colorA, colorB, colorA, colorB, colorA, colorB}, {colorB, colorA, colorB, colorA, colorB, colorA, colorB, colorA},
	    {colorA, colorB, colorA, colorB, colorA, colorB, colorA, colorB}, {colorB, colorA, colorB, colorA, colorB, colorA, colorB, colorA},
	    {colorA, colorB, colorA, colorB, colorA, colorB, colorA, colorB}, {colorB, colorA, colorB, colorA, colorB, colorA, colorB, colorA},
	    {colorA, colorB, colorA, colorB, colorA, colorB, colorA, colorB}, {colorB, colorA, colorB, colorA, colorB, colorA, colorB, colorA}};

    //Selection
    char select[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};

    //Undo piece array
    char pieceU[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    //Undo Team or war array
    char WarU[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    //Undo background color array
    char bgU[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    //Redo piece array
    char pieceR[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    //Redo war or team array
    char WarR[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    //Redo background color array
    char bgR[] [] = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    /*//Empty array to hold pieces
    char recall[] [] = {{' '}, {' '}, {' '}};*/

    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	HomeScreen ();
	Instruction1 ();
	Instruction2 ();
	Instruction3 ();
	Instruction4 ();
	Instruction5 ();
	Instruction6 ();
	PlayOptions ();
	WarBattleIntro ();
	WarBattle ();
	WarBattleConclusion ();
	HouseBattleIntro ();
	HouseBattle ();
	HouseBattleConclusion ();
	resize (720, 790);
	setLayout (new BorderLayout ());
	add ("Center", p_card);
	theme1 = getAudioClip (getDocumentBase (), "GOTMAINTHEME.au");
	chessSound = getAudioClip (getDocumentBase (), "Chessmove.au");
	piano = getAudioClip (getDocumentBase (), "piano.au");
    }


    public void HomeScreen ()
    {
	card1 = new Panel ();
	card1.setBackground (new Color (1, 1, 35));
	//Button goes to Game Options screen
	JButton play = new JButton (createImageIcon ("play.png"));
	play.setPreferredSize (new Dimension (220, 50));
	play.setBorder (null);
	play.setActionCommand ("playOptions");
	play.addActionListener (this);
	//Button goes to first Instructions Screen
	JButton directions = new JButton (createImageIcon ("directions.png"));
	directions.setPreferredSize (new Dimension (220, 50));
	directions.setBorder (null);
	directions.setActionCommand ("directions");
	directions.addActionListener (this);
	JLabel pic = new JLabel (createImageIcon ("background.png"));
	JLabel pic2 = new JLabel (createImageIcon ("background2.png"));
	card1.add (pic);
	card1.add (play);
	card1.add (directions);
	card1.add (pic2);
	p_card.add ("1", card1);
    }


    public void Instruction1 ()
    {
	card2 = new Panel ();
	card2.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i1.png"));
	pic.setBorder (null);
	//Goes to second Instruction screen
	JButton next = new JButton (createImageIcon ("next.png"));
	next.setActionCommand ("next");
	next.addActionListener (this);
	next.setPreferredSize (new Dimension (220, 50));
	next.setBorder (null);
	//Goes back to Home screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setActionCommand ("back");
	back.addActionListener (this);
	back.setPreferredSize (new Dimension (220, 50));
	back.setBorder (null);
	card2.add (pic);
	card2.add (back);
	card2.add (next);
	p_card.add ("2", card2);
    }


    public void Instruction2 ()
    {
	card3 = new Panel ();
	card3.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i2.png"));
	pic.setBorder (null);
	//Goes to third Instruction screen
	JButton next = new JButton (createImageIcon ("next.png"));
	next.setPreferredSize (new Dimension (220, 50));
	next.setActionCommand ("next2");
	next.addActionListener (this);
	next.setBorder (null);
	//Goes to first Instruction screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setPreferredSize (new Dimension (220, 50));
	back.setActionCommand ("back2");
	back.addActionListener (this);
	back.setBorder (null);
	card3.add (pic);
	card3.add (back);
	card3.add (next);
	p_card.add ("3", card3);
    }


    public void Instruction3 ()
    {
	card4 = new Panel ();
	card4.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i3.png"));
	pic.setBorder (null);
	//Goes to fourth Instruction screen
	JButton next = new JButton (createImageIcon ("next.png"));
	next.setPreferredSize (new Dimension (220, 50));
	next.setActionCommand ("next3");
	next.addActionListener (this);
	next.setBorder (null);
	//Goes to second Instruction screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setPreferredSize (new Dimension (220, 50));
	back.setActionCommand ("back3");
	back.addActionListener (this);
	back.setBorder (null);
	card4.add (pic);
	card4.add (back);
	card4.add (next);
	p_card.add ("4", card4);
    }


    public void Instruction4 ()
    {
	card5 = new Panel ();
	card5.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i4.png"));
	pic.setBorder (null);
	//Goes to fifth Instruction screen
	JButton next = new JButton (createImageIcon ("next.png"));
	next.setPreferredSize (new Dimension (220, 50));
	next.setActionCommand ("next4");
	next.addActionListener (this);
	next.setBorder (null);
	//Goes to third Instruction screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setPreferredSize (new Dimension (220, 50));
	back.setActionCommand ("back4");
	back.addActionListener (this);
	back.setBorder (null);
	card5.add (pic);
	card5.add (back);
	card5.add (next);
	p_card.add ("5", card5);
    }


    public void Instruction5 ()
    {
	card6 = new Panel ();
	card6.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i5.png"));
	pic.setBorder (null);
	//Goes to sixth Instruction screen
	JButton next = new JButton (createImageIcon ("next.png"));
	next.setPreferredSize (new Dimension (220, 50));
	next.setActionCommand ("next5");
	next.addActionListener (this);
	next.setBorder (null);
	//Goes to fourth Instruction screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setPreferredSize (new Dimension (220, 50));
	back.setActionCommand ("back5");
	back.addActionListener (this);
	back.setBorder (null);
	card6.add (pic);
	card6.add (back);
	card6.add (next);
	p_card.add ("6", card6);
    }


    public void Instruction6 ()
    {
	card7 = new Panel ();
	card7.setBackground (new Color (69, 42, 9));
	JLabel pic = new JLabel (createImageIcon ("i6.png"));
	pic.setBorder (null);
	//Goes to Play Options screen
	JButton play = new JButton (createImageIcon ("play.png"));
	play.setPreferredSize (new Dimension (220, 50));
	play.setActionCommand ("play2");
	play.addActionListener (this);
	play.setBorder (null);
	//Goes to fifth Instruction screen
	JButton back = new JButton (createImageIcon ("back.png"));
	back.setPreferredSize (new Dimension (220, 50));
	back.setActionCommand ("back6");
	back.addActionListener (this);
	back.setBorder (null);
	card7.add (pic);
	card7.add (back);
	card7.add (play);
	p_card.add ("7", card7);
    }


    public void PlayOptions ()
    {
	card8 = new Panel ();
	card8.setBackground (new Color (1, 1, 35));
	JLabel image1 = new JLabel (createImageIcon ("pagetwo1.png"));
	image1.setBorder (null);
	JLabel image2 = new JLabel (createImageIcon ("pagetwo2.png"));
	image2.setBorder (null);
	JLabel image3 = new JLabel (createImageIcon ("pagetwo3.png"));
	image3.setBorder (null);
	//Button to House Battle
	JButton house = new JButton (createImageIcon ("multi.png"));
	house.setActionCommand ("house");
	house.addActionListener (this);
	house.setPreferredSize (new Dimension (500, 113));
	house.setBorder (null);
	//Button to War Screen
	JButton war = new JButton (createImageIcon ("single.png"));
	war.setActionCommand ("war");
	war.addActionListener (this);
	war.setPreferredSize (new Dimension (500, 113));
	war.setBorder (null);
	card8.add (image1);
	card8.add (war);
	card8.add (image2);
	card8.add (house);
	card8.add (image3);
	p_card.add ("8", card8);
    }


    public void WarBattleIntro ()
    {
	card9 = new Panel ();
	card9.setBackground (Color.black);
	JLabel intro = new JLabel (createImageIcon ("whiteWalkers.png"));
	Panel warintro = new Panel ();
	//Button to chess board
	JButton warButton = new JButton (createImageIcon ("continue.png"));
	warButton.setActionCommand ("warButton");
	warButton.addActionListener (this);
	warButton.setPreferredSize (new Dimension (220, 50));
	//Button to home screen
	JButton Main = new JButton (createImageIcon ("main.png"));
	Main.setActionCommand ("main");
	Main.addActionListener (this);
	Main.setPreferredSize (new Dimension (220, 50));
	warintro.add (Main);
	warintro.add (warButton);
	card9.add (intro);
	card9.add (warintro);
	p_card.add ("9", card9);
    }


    public void WarBattle ()
    {
	card10 = new Panel ();
	card10.setBackground (new Color (1, 1, 35));
	Panel h = new Panel ();
	//turn
	JLabel title = new JLabel ("Turn:");
	title.setFont (new Font ("Goudy Stout", Font.BOLD, 40));
	title.setForeground (new Color (255, 202, 0));
	turnpic2 = new JLabel (createImageIcon ("base.png"));
	h.add (title);
	h.add (turnpic2);
	//Set up board
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a2 [move] = new JButton (createImageIcon (War [i] [j] + "" + piece [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		a2 [move].setPreferredSize (new Dimension (75, 75));
		a2 [move].addActionListener (this);
		a2 [move].setActionCommand ("" + move);
		p.add (a2 [move]);
		move++;
	    }
	}
	Panel hold = new Panel ();
	Panel hold2 = new Panel ();
	undo = new JButton (createImageIcon ("undo.png"));
	undo.setPreferredSize (new Dimension (220, 50));
	undo.setActionCommand ("undo");
	undo.addActionListener (this);
	redo = new JButton (createImageIcon ("redo.png"));
	redo.setPreferredSize (new Dimension (220, 50));
	redo.setActionCommand ("redo");
	redo.addActionListener (this);
	hold.add (undo);
	hold.add (redo);
	//Moves
	MoveCount2 = new JProgressBar (0, 0, 100);
	MoveCount2.setStringPainted (true);
	MoveCount2.setForeground (Color.cyan);
	JLabel tally = new JLabel ("Total Moves:");
	tally.setForeground (Color.white);
	hold2.add (tally);
	hold2.add (MoveCount2);
	card10.add (h);
	card10.add (p);
	card10.add (hold);
	card10.add (hold2);
	p_card.add ("10", card10);
    }


    public void WarBattleConclusion ()
    {
	card11 = new Panel ();
	card11.setBackground (Color.black);
	pic1 = new JLabel (createImageIcon ("victory.png"));
	JButton quit = new JButton (createImageIcon ("quit.png"));
	quit.setPreferredSize (new Dimension (220, 50));
	quit.setActionCommand ("quit");
	quit.addActionListener (this);
	card11.add (pic1);
	card11.add (quit);
	p_card.add ("11", card11);
    }


    public void HouseBattleIntro ()
    {
	card12 = new Panel ();
	card12.setBackground (new Color (29, 28, 24));
	JLabel title = new JLabel (createImageIcon ("battleofhouses.png"));
	JLabel pic = new JLabel (createImageIcon ("backscreen.png"));
	//Goes to Main Screen
	JButton Main = new JButton (createImageIcon ("main.png"));
	Main.setPreferredSize (new Dimension (220, 50));
	Main.setActionCommand ("main2");
	Main.addActionListener (this);
	//Goes to House Battle board
	JButton houseButton = new JButton (createImageIcon ("continue.png"));
	houseButton.setPreferredSize (new Dimension (220, 50));
	houseButton.setActionCommand ("houseButton");
	houseButton.addActionListener (this);
	card12.add (title);
	card12.add (pic);
	card12.add (Main);
	card12.add (houseButton);
	p_card.add ("12", card12);
    }


    public void HouseBattle ()
    {
	card13 = new Panel ();
	card13.setBackground (new Color (1, 1, 35));
	Panel h = new Panel ();
	//turn
	JLabel title = new JLabel ("Turn:");
	title.setFont (new Font ("Goudy Stout", Font.BOLD, 40));
	title.setForeground (new Color (255, 202, 0));
	turnpic = new JLabel (createImageIcon ("base.png"));
	h.add (title);
	h.add (turnpic);
	//Set up board
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a1 [move] = new JButton (createImageIcon (War [i] [j] + "" + piece [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		a1 [move].setPreferredSize (new Dimension (75, 75));
		a1 [move].addActionListener (this);
		a1 [move].setActionCommand ("" + move);
		p.add (a1 [move]);
		move++;
	    }
	}
	Panel hold = new Panel ();
	Panel hold2 = new Panel ();
	//Progress Bar
	undo = new JButton (createImageIcon ("undo.png"));
	undo.setPreferredSize (new Dimension (220, 50));
	undo.setActionCommand ("undo2");
	undo.addActionListener (this);
	redo = new JButton (createImageIcon ("redo.png"));
	redo.setPreferredSize (new Dimension (220, 50));
	redo.setActionCommand ("redo2");
	redo.addActionListener (this);
	hold.add (undo);
	hold.add (redo);
	MoveCount = new JProgressBar (0, 0, 100);
	MoveCount.setStringPainted (true);
	MoveCount.setForeground (Color.cyan);
	JLabel tally = new JLabel ("Total Moves:");
	tally.setForeground (Color.white);
	hold2.add (tally);
	hold2.add (MoveCount);
	card13.add (h);
	card13.add (p);
	card13.add (hold);
	card13.add (hold2);
	p_card.add ("13", card13);
    }


    public void HouseBattleConclusion ()
    {
	card14 = new Panel ();
	card14.setBackground (new Color (3, 169, 240));
	pic2 = new JLabel (createImageIcon ("vb.png"));
	Panel d = new Panel ();
	b1 = new JLabel (createImageIcon ("b1.png"));
	JLabel vs = new JLabel ("VS");
	vs.setFont (new Font ("Goudy Stout", Font.BOLD, 56));
	vs.setForeground (Color.white);
	b2 = new JLabel (createImageIcon ("b2.png"));
	d.add (b1);
	d.add (vs);
	d.add (b2);
	JButton quit = new JButton (createImageIcon ("quit.png"));
	quit.setPreferredSize (new Dimension (220, 50));
	quit.setActionCommand ("quit2");
	quit.addActionListener (this);
	card14.add (pic2);
	card14.add (quit);
	p_card.add ("14", card14);
    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = GOT_Chess.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void redrawWar ()
    {
	int move = 0;
	if (defender == 'B' || defender == 'L' || defender == 'S' || defender == 'T')
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    a1 [move].setIcon (createImageIcon (War [i] [j] + "" + piece [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		    move++;
		}
	    }
	//Button icon according to which game is being played
	else
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    a2 [move].setIcon (createImageIcon (War [i] [j] + "" + piece [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".png"));
		    move++;
		}
	    }
    }


    public boolean check (int x, int y)
    {
	//Winning Condition: If the opposing team is able to capture the king in one move. The game is over.
	Object[] options = {"OK"};
	if (War [x] [y] != turn && piece [x] [y] == 'k' && select [x] [y] == 's')
	{
	    JOptionPane.showOptionDialog (null, "You have been Checked", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options [0]);
	    return true;
	}
	else if (move > 100)
	{
	    JOptionPane.showOptionDialog (null, "Total Move count is: " + move, "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options [0]);
	    return true;
	    //warW = "draw";
	    //houseW = "draw";
	}
	else
	    return false;
    }


    public void HouseChoice ()
    {
	player1 = JOptionPane.showInputDialog ("Player 1: Please enter your name");
	String[] possibleHouses = {"Baratheon", "Lannister", "Stark", "Targaryen"};
	String selectedHouse = (String) JOptionPane.showInputDialog (null, player1 + " Pick a House", "House Choice", JOptionPane.INFORMATION_MESSAGE, null, possibleHouses, possibleHouses [0]);
	//recieves which house was selected and sets variables accordingly
	if (selectedHouse == "Baratheon")
	{
	    defender = 'B';
	    colorB = 'y';
	    possibleHouses2 [0] = "This option is unavailable";
	}
	else if (selectedHouse == "Lannister")
	{
	    defender = 'L';
	    colorB = 'r';
	    possibleHouses2 [1] = "This option is unavailable";
	}
	else if (selectedHouse == "Stark")
	{
	    defender = 'S';
	    colorB = 'g';
	    possibleHouses2 [2] = "This option is unavailable";
	}
	else if (selectedHouse == "Targaryen")
	{
	    defender = 'T';
	    colorB = 'b';
	    possibleHouses2 [3] = "This option is unavailable";
	}
	else
	    showStatus (player1 + "Pick an option");
	turn = defender;
	HouseChoice2 ();
	setupGame (attacker, colorA, defender, colorB);
    }


    public void HouseChoice2 ()
    {
	//The second player picks their house options
	player2 = JOptionPane.showInputDialog ("Player 2: Please enter your name");
	String selectedHouse2 = (String) JOptionPane.showInputDialog (null, player2 + " Pick a House. Do not pick the same house as " + player1, "House Choice", JOptionPane.INFORMATION_MESSAGE, null, possibleHouses2, possibleHouses2 [0]);
	//Sets the values for the attacker and color
	if (selectedHouse2 == "Baratheon")
	{
	    attacker = 'B';
	    colorA = 'y';
	}
	else if (selectedHouse2 == "Lannister")
	{
	    attacker = 'L';
	    colorA = 'r';
	}
	else if (selectedHouse2 == "Stark")
	{
	    attacker = 'S';
	    colorA = 'g';
	}
	else if (selectedHouse2 == "Targaryen")
	{
	    attacker = 'T';
	    colorA = 'b';
	}
	else
	{
	    HouseChoice2 ();
	}
    }


    public void setupGame (char attacker1, char colorA1, char defender1, char colorB1)
    { //To values (house piece: attacker) for the second player
	for (int i = 0 ; i < 2 ; i++)
	    for (int j = 0 ; j < 8 ; j++)
		War [i] [j] = attacker1;
	//To values (house piece: defender)for the first player
	for (int i = 6 ; i < 8 ; i++)
	    for (int j = 0 ; j < 8 ; j++)
		War [i] [j] = defender1;
	//To set values for the background colors, determined if the button is even or odd
	for (int i = 0 ; i < 8 ; i++)
	    for (int j = 0 ; j < 8 ; j++)
		if ((i + j) % 2 == 0)
		    bg [i] [j] = colorA1;
		else
		    bg [i] [j] = colorB1;
    }


    public void selectPawn (int x, int y)
    {
	int cx1 = x - 1;
	int cx2 = x + 1;
	int cx3 = x - 2;
	int cx4 = x + 2;
	int cy1 = y - 1;
	int cy2 = y + 1;
	//Vertical Movement: Allows Pawn to move up.
	if (War [x] [y] == defender && x == 6 && War [cx1] [y] == 'X' && War [cx3] [y] == 'X')
	{
	    select [cx1] [y] = 's';
	    select [cx3] [y] = 's';
	}
	else if (War [x] [y] == attacker && x == 1 && War [cx2] [y] == 'X' && War [cx4] [y] == 'X')
	{
	    select [cx2] [y] = 's';
	    select [cx4] [y] = 's';
	}
	else if (War [x] [y] == defender && War [cx1] [y] == 'X' && cx1 >= 0)
	{
	    select [cx1] [y] = 's';
	}
	else if (War [x] [y] == attacker && War [cx2] [y] == 'X' && cx2 < row)
	{
	    select [cx2] [y] = 's';
	}
	//Killing Condition: Pawn can only kill diagonally.
	if (War [x] [y] == defender && cx1 >= 0 && cy1 >= 0 && War [cx1] [cy1] == attacker)
	    select [cx1] [cy1] = 's';
	if (War [x] [y] == defender && cx1 >= 0 && cy2 < col && War [cx1] [cy2] == attacker)
	    select [cx1] [cy2] = 's';
	if (War [x] [y] == attacker && cx2 < row && cy1 >= 0 && War [cx2] [cy1] == defender)
	    select [cx2] [cy1] = 's';
	if (War [x] [y] == attacker && cx2 < row && cy2 < col && War [cx2] [cy2] == defender)
	    select [cx2] [cy2] = 's';
    }


    public void pawnUpgrade (int x, int y)
    {
	//Pawn Upgrade: Once a pawn reaches the other side of a board, it has the option of becoming a queen, rook, bishop or knight. Options for the living team and Wights
	String[] possibleValues = {"Queen", "Knight", "Bishop", "Rook"};
	String[] possibleValues2 = {"Queen", "Knight", "Bishop", "Rook"};
	//If the pawn is a defending piece
	if (War [x] [y] == defender && x == 0 && y < 8)
	{
	    String selectedValue = (String) JOptionPane.showInputDialog (null, "Choose one", "Pawn Upgrade", JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues [0]);
	    if (selectedValue == "Queen")
		piece [x] [y] = 'q';
	    else if (selectedValue == "Knight")
		piece [x] [y] = 'n';
	    else if (selectedValue == "Bishop")
		piece [x] [y] = 'b';
	    else if (selectedValue == "Rook")
		piece [x] [y] = 'r';
	    else
		showStatus ("Pick an Option");
	}
	//if the pawn is an attacking piece
	else if (War [x] [y] == attacker && x == 7 && y < 8)
	{
	    String selectedValue2 = (String) JOptionPane.showInputDialog (null, "Choose one", "Pawn Upgrade", JOptionPane.QUESTION_MESSAGE, null, possibleValues2, possibleValues2 [0]);
	    if (selectedValue2 == "Queen")
		piece [x] [y] = 'q';
	    else if (selectedValue2 == "Knight")
		piece [x] [y] = 'n';
	    else if (selectedValue2 == "Bishop")
		piece [x] [y] = 'b';
	    else if (selectedValue2 == "Rook")
		piece [x] [y] = 'r';
	    else
		showStatus ("Pick an Option");
	}
    }


    public void selectKing (int x, int y)
    {
	//first row of the kings movement, backwards directly, or back and right or left. Includes killing conditions
	if (x - 1 >= 0 && y - 1 >= 0 && War [x - 1] [y - 1] != turn)
	    select [x - 1] [y - 1] = 's';
	if (x - 1 >= 0 && y + 1 < col && War [x - 1] [y + 1] != turn)
	    select [x - 1] [y + 1] = 's';
	if (x - 1 >= 0 && War [x - 1] [y] != turn)
	    select [x - 1] [y] = 's';
	//third row (the bottom row) directly forwards, left or right corner fowards
	if (x + 1 < row && y - 1 >= 0 && War [x + 1] [y - 1] != turn)
	    select [x + 1] [y - 1] = 's';
	if (x + 1 < row && y + 1 < col && War [x + 1] [y + 1] != turn)
	    select [x + 1] [y + 1] = 's';
	if (x + 1 < row && War [x + 1] [y] != turn)
	    select [x + 1] [y] = 's';
	//second row (the middle row) can move left or right
	if (y - 1 >= 0 && War [x] [y - 1] != turn)
	    select [x] [y - 1] = 's';
	if (y + 1 < col && War [x] [y + 1] != turn)
	    select [x] [y + 1] = 's';
    }


    public void selectKnight (int x, int y)
    {
	//first row (back). Includes killing conditions
	if (x - 2 >= 0 && y - 1 >= 0 && War [x - 2] [y - 1] != turn)
	    select [x - 2] [y - 1] = 's';
	if (x - 2 >= 0 && y + 1 < col && War [x - 2] [y + 1] != turn)
	    select [x - 2] [y + 1] = 's';
	//second row (second from back)
	if (x - 1 >= 0 && y - 2 >= 0 && War [x - 1] [y - 2] != turn)
	    select [x - 1] [y - 2] = 's';
	if (x - 1 >= 0 && y + 2 < col && War [x - 1] [y + 2] != turn)
	    select [x - 1] [y + 2] = 's';
	//third row (third from back)
	if (x + 1 < row && y - 2 >= 0 && War [x + 1] [y - 2] != turn)
	    select [x + 1] [y - 2] = 's';
	if (x + 1 < row && y + 2 < col && War [x + 1] [y + 2] != turn)
	    select [x + 1] [y + 2] = 's';
	//fourth row (fourth from back)
	if (x + 2 < row && y - 1 >= 0 && War [x + 2] [y - 1] != turn)
	    select [x + 2] [y - 1] = 's';
	if (x + 2 < row && y + 1 < col && War [x + 2] [y + 1] != turn)
	    select [x + 2] [y + 1] = 's';
    }


    public void selectRook (int x, int y)
    {
	//UP_________________________________________ (backwards)
	int cx1 = x - 1;
	//blank and not off board
	while (cx1 >= 0 && War [cx1] [y] == 'X')
	{
	    select [cx1] [y] = 's';
	    cx1--;
	}
	//killing condition. on board, identifies if the selected piece is a team member or enemy
	if (cx1 >= 0 && War [cx1] [y] != turn && War [cx1] [y] != 'X')
	    select [cx1] [y] = 's';
	//DOWN_______________________________________ (fowards)
	cx1 = x + 1;
	while (cx1 < row && War [cx1] [y] == 'X')
	{
	    select [cx1] [y] = 's';
	    cx1++;
	}
	if (cx1 < row && War [cx1] [y] != turn && War [cx1] [y] != 'X')
	    select [cx1] [y] = 's';
	//LEFT_______________________________________
	int cy1 = y - 1;
	while (cy1 >= 0 && War [x] [cy1] == 'X')
	{
	    select [x] [cy1] = 's';
	    cy1--;
	}
	if (cy1 >= 0 && War [x] [cy1] != turn && War [x] [cy1] != 'X')
	    select [x] [cy1] = 's';
	//RIGHT______________________________________
	cy1 = y + 1;
	while (cy1 < col && War [x] [cy1] == 'X')
	{
	    select [x] [cy1] = 's';
	    cy1++;
	}
	if (cy1 < col && War [x] [cy1] != turn && War [x] [cy1] != 'X')
	    select [x] [cy1] = 's';
    }


    public void selectBishop (int x, int y)
    {
	//BACK AND LEFT_________________________________________
	int cx1 = x - 1;
	int cy1 = y - 1;
	//blank and not off board
	while (cx1 >= 0 && cy1 >= 0 && War [cx1] [cy1] == 'X')
	{
	    select [cx1] [cy1] = 's';
	    cx1--;
	    cy1--;
	}
	//killing condition. on board, is not us or blank
	if (cx1 >= 0 && cy1 >= 0 && War [cx1] [cy1] != turn && War [cx1] [cy1] != 'X')
	    select [cx1] [cy1] = 's';
	//BACK AND RIGHT_________________________________________
	cx1 = x - 1;
	cy1 = y + 1;
	while (cx1 >= 0 && cy1 < col && War [cx1] [cy1] == 'X')
	{
	    select [cx1] [cy1] = 's';
	    cx1--;
	    cy1++;
	}
	if (cx1 >= 0 && cy1 < col && War [cx1] [cy1] != turn && War [cx1] [cy1] != 'X')
	    select [cx1] [cy1] = 's';
	//FORWARDS AND LEFT_________________________________________
	cx1 = x + 1;
	cy1 = y - 1;
	while (cx1 < row && cy1 >= 0 && War [cx1] [cy1] == 'X')
	{
	    select [cx1] [cy1] = 's';
	    cx1++;
	    cy1--;
	}
	if (cx1 < row && cy1 >= 0 && War [cx1] [cy1] != turn && War [cx1] [cy1] != 'X')
	    select [cx1] [cy1] = 's';
	//FORWARDS AND RIGHT_________________________________________
	cx1 = x + 1;
	cy1 = y + 1;
	while (cx1 < row && cy1 < col && War [cx1] [cy1] == 'X')
	{
	    select [cx1] [cy1] = 's';
	    cx1++;
	    cy1++;
	}
	if (cx1 < row && cy1 < col && War [cx1] [cy1] != turn && War [cx1] [cy1] != 'X')
	    select [cx1] [cy1] = 's';
    }


    public void KingSlayer ()
    {
	/*A random piece from the opposing team becomes your piece at the beginning of the match. Hence the name kingslayer.
	This piece is only avaible for the non-wight team. I included pawns because they have the option of upgrading.*/
	//Creates random numbers for x and y values, excluding 3 because that is the king's spot.
	int x_value = (int) (Math.random () * 2);
	int y_value = 3;
	do
	    y_value = (int) (Math.random () * 8);
	while (y_value == 3);
	String name = " ";
	War [x_value] [y_value] = 'Z';
	switch (piece [x_value] [y_value])
	{
	    case 'q':
		name = "Queen";
		break;
	    case 'r':
		name = "Rook";
		break;
	    case 'b':
		name = "Bishop";
		break;
	    case 'n':
		name = "Knight";
		break;
	    case 'p':
		name = "Pawn";
		break;
	}
	JOptionPane.showMessageDialog (null, name + " is located at " + x_value + ", " + y_value, "KingSlayer", JOptionPane.INFORMATION_MESSAGE);
    }


    public void Assassin (int x, int y)
    { //Movement: Can move one space in a cross space and two spaces diagonally. This piece is only avaible for the non-wight team and can swap places with other pieces.*Not finished
	int cx1 = x - 2;
	int cx2 = x - 1;
	int cx3 = x + 1;
	int cx4 = x + 2;
	int cy1 = y - 2;
	int cy2 = y - 1;
	int cy3 = y + 1;
	int cy4 = y + 2;
	//first row
	if (cx2 >= 0 && War [cx2] [y] != turn)
	    select [cx2] [y] = 's';
	//third row
	if (cx3 < row && War [cx3] [y] != turn)
	    select [cx3] [y] = 's';
	//second row
	if (cy2 >= 0 && War [x] [cy2] != turn)
	    select [x] [cy2] = 's';
	//last row
	if (cy3 < col && War [x] [cy3] != turn)
	    select [x] [cy3] = 's';
	//Back Left Corner
	if (cx1 >= 0 && cy1 >= 0 && War [cx1] [cy1] != turn && piece [cx1] [cy1] != 'x')
	    select [cx1] [cy1] = 's';
	//Back Right Corner
	if (cx1 >= 0 && cy4 < col && War [cx1] [cy4] != turn && piece [cx1] [cy4] != 'x')
	    select [cx1] [cy4] = 's';
	//Front Left Corner
	if (cx4 < row && cy1 >= 0 && War [cx4] [cy1] != turn && piece [cx4] [cy1] != 'x')
	    select [cx4] [cy1] = 's';
	//Front Right Corner
	if (cx4 < row && cy4 < col && War [cx4] [cy4] != turn && piece [cx4] [cy4] != 'x')
	    select [cx4] [cy4] = 's';
    }

    public void Mountain (int x, int y)
    {
	//Movement: This piece has a wide capture range. It moves two spaces forwards in all forwards and sideways directions, but cannot attack directy in front of itself. This piece is only avaible for the non-wight team and the code includes killing conditions
	int cx1 = x - 2;
	int cx2 = x - 1;
	int cx3 = x + 1;
	int cx4 = x + 2;
	int cy1 = y - 2;
	int cy2 = y - 1;
	int cy3 = y + 1;
	int cy4 = y + 2;
	//TOP ROW______________________________________________
	if (cx1 >= 0 && cy1 >= 0 && War [cx1] [cy1] != turn)
	    select [cx1] [cy1] = 's';
	if (cx1 >= 0 && cy2 >= 0 && War [cx1] [cy2] != turn)
	    select [cx1] [cy2] = 's';
	if (cx1 >= 0 && War [cx1] [y] != turn)
	    select [cx1] [y] = 's';
	if (cx1 >= 0 && cy3 < col && War [cx1] [cy3] != turn)
	    select [cx1] [cy3] = 's';
	if (cx1 >= 0 && cy4 < col && War [cx1] [cy4] != turn)
	    select [cx1] [cy4] = 's';
	//LEFT COLUMN__________________________________________
	if (cx2 >= 0 && cy1 >= 0 && War [cx2] [cy1] != turn)
	    select [cx2] [cy1] = 's';
	if (cy1 >= 0 && War [x] [cy1] != turn)
	    select [x] [cy1] = 's';
	//RIGHT COLUMN__________________________________________
	if (cx2 >= 0 && cy4 < col && War [cx2] [cy4] != turn)
	    select [cx2] [cy4] = 's';
	if (cy4 < col && War [x] [cy1] != turn)
	    select [x] [cy4] = 's';
    }


    public void WarSetup ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("wallAttack.png"), "The Kingdom is under Attack!", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * TASK * * * \n \n" + "The Kingdom is under Attack! \n" + "The undead are marching on your walls.\n" + "Prepare to defend yourself and your people,\n" + "Or face life as a Wight.\n" + "You will be aided by special pieces.\n" + "Good Luck on your Quest.\n \n", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	//The defending player picks from these options
	JOptionPane.showMessageDialog (null, "The defending player (Undead) will choose special pieces to aid their quest.", "Defending Player", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("mountain.png"), "Bonus Pieces", JOptionPane.INFORMATION_MESSAGE);
	//Identitfies the option the defending (undead) player has picked and sets the values accordingly
	String[] possibleValues = {"Kingslayer", "Assassin", "Mountain"};
	String selectedValue = (String) JOptionPane.showInputDialog (null, "Pick an Option", "Wight Battle", JOptionPane.PLAIN_MESSAGE, null, possibleValues, possibleValues [0]);
	if (selectedValue == "Kingslayer")
	    KingSlayer ();
	else if (selectedValue == "Assassin")
	{
	    piece [6] [0] = 'a';
	    piece [6] [7] = 'a';
	}
	else if (selectedValue == "Mountain")
	    piece [6] [5] = 'm';
	//message for the attacking (zombie) player
	JOptionPane.showMessageDialog (null, "The attacking (Wights) player can recall captured pieces as their own.", "Attacking Player", JOptionPane.INFORMATION_MESSAGE);
    }


    public void Undo ()
    { //Replaces empty undo array with placement before the second move has been made
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		pieceU [i] [j] = piece [i] [j];
		WarU [i] [j] = War [i] [j];
		bgU [i] [j] = bg [i] [j];
	    }
    }


    public void redo ()
    { //Replaces empty redo array with values before undo is completed
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		pieceR [i] [j] = piece [i] [j];
		WarR [i] [j] = War [i] [j];
		bgR [i] [j] = bg [i] [j];
	    }
    }


    public void UndoSet ()
    { //Calls redo to set piece values. Sets undo values in place
	redo ();
	redo.enable (true);
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = pieceU [i] [j];
		War [i] [j] = WarU [i] [j];
		bg [i] [j] = bgU [i] [j];
	    }
	redrawWar ();
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		pieceU [i] [j] = ' ';
		WarU [i] [j] = ' ';
		bgU [i] [j] = ' ';
	    }
    }


    public void RedoSet ()
    { //Call redo valyes and clears array
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = pieceR [i] [j];
		War [i] [j] = WarR [i] [j];
		bg [i] [j] = bgR [i] [j];
	    }
	redrawWar ();
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		pieceR [i] [j] = ' ';
		WarR [i] [j] = ' ';
		bgR [i] [j] = ' ';
	    }
	redo.enable (false);
    }

    public void actionPerformed (ActionEvent e)
    { //Allows the screens to switch
	if (e.getActionCommand ().equals ("directions"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("playOptions"))
	    cdLayout.show (p_card, "8");
	else if (e.getActionCommand ().equals ("next"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("back"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("next2"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("back2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("next3"))
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("back3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("next4"))
	    cdLayout.show (p_card, "6");
	else if (e.getActionCommand ().equals ("back4"))
	    cdLayout.show (p_card, "4");
	else if (e.getActionCommand ().equals ("next5"))
	    cdLayout.show (p_card, "7");
	else if (e.getActionCommand ().equals ("back5"))
	    cdLayout.show (p_card, "5");
	else if (e.getActionCommand ().equals ("play2"))
	    cdLayout.show (p_card, "8");
	else if (e.getActionCommand ().equals ("back6"))
	    cdLayout.show (p_card, "6");
	else if (e.getActionCommand ().equals ("war"))
	{
	    cdLayout.show (p_card, "9");
	    WarSetup ();
	}
	else if (e.getActionCommand ().equals ("main"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("warButton"))
	{
	    redrawWar ();
	    cdLayout.show (p_card, "10");
	    piano.play ();
	    piano.loop ();
	}
	else if (e.getActionCommand ().equals ("main2"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("houseButton"))
	{
	    redrawWar ();
	    cdLayout.show (p_card, "13");
	    piano.play ();
	    piano.loop ();
	}
	else if (e.getActionCommand ().equals ("house"))
	{
	    cdLayout.show (p_card, "12");
	    HouseChoice ();
	}
	else if (e.getActionCommand ().equals ("undo"))
	    UndoSet ();
	else if (e.getActionCommand ().equals ("redo"))
	    RedoSet ();
	else if (e.getActionCommand ().equals ("undo2"))
	    UndoSet ();
	else if (e.getActionCommand ().equals ("redo2"))
	    RedoSet ();
	else if (e.getActionCommand ().equals ("quit"))
	    System.exit (0);
	else if (e.getActionCommand ().equals ("quit2"))
	    System.exit (0);
	else
	{ //code to handle the game
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / row;
	    int y = n % col;
	    //Displays new position
	    switch (piece [x] [y])
	    {
		case 'q':
		    showStatus ("Queen (" + x + ", " + y + ")");
		    break;
		case 'k':
		    showStatus ("King (" + x + ", " + y + ")");
		    break;
		case 'r':
		    showStatus ("Rook (" + x + ", " + y + ")");
		    break;
		case 'b':
		    showStatus ("Bishop (" + x + ", " + y + ")");
		    break;
		case 'n':
		    showStatus ("Knight (" + x + ", " + y + ") ");
		    break;
		case 'p':
		    showStatus ("Pawn (" + x + ", " + y + ")");
		    break;
		case 'a':
		    showStatus ("Assassin(" + x + ", " + y + ")");
		    break;
		case 'm':
		    showStatus ("Mounatin (" + x + ", " + y + ")");
		    break;
	    }
	    //click #1, not your piece
	    if (turn != War [x] [y] && last == -1)
		showStatus ("It is not your turn.");
	    else if (last == -1 && turn == War [x] [y])
	    { //click #1, select move
		if (piece [x] [y] == 'p')
		{
		    pawnUpgrade (x, y);
		    selectPawn (x, y);
		}
		else if (piece [x] [y] == 'k')
		    selectKing (x, y);
		else if (piece [x] [y] == 'n')
		    selectKnight (x, y);
		else if (piece [x] [y] == 'r')
		    selectRook (x, y);
		else if (piece [x] [y] == 'b')
		    selectBishop (x, y);
		else if (piece [x] [y] == 'q')
		{
		    selectRook (x, y);
		    selectBishop (x, y);
		}
		else if (piece [x] [y] == 'a')
		    Assassin (x, y);
		else if (piece [x] [y] == 'm')
		    Mountain (x, y);
		last = n;
	    }
	    else
	    { //click #2, need to move
		int lastx = last / row;
		int lasty = last % col;
		//Winning Condition
		check (x, y);
		if (check (x, y) && defender != 'Z')
		{
		    cdLayout.show (p_card, "14");
		    piano.stop ();
		    theme1.play ();
		    theme1.loop ();
		}
		else if (check (x, y) && defender == 'Z')
		{
		    cdLayout.show (p_card, "11");
		    piano.stop ();
		    theme1.play ();
		    theme1.loop ();
		}
		else
		{ //move
		    if (select [x] [y] == 's')
		    {
			Undo ();
			piece [x] [y] = piece [lastx] [lasty];
			piece [lastx] [lasty] = 'x';
			War [x] [y] = War [lastx] [lasty];
			War [lastx] [lasty] = 'X';
			chessSound.play ();
			//Updates progress bar
			move++;
			if (attacker == 'B' || attacker == 'L' || attacker == 'S' || attacker == 'T')
			    MoveCount.setValue (move);
			else
			    MoveCount2.setValue (move);
			//switches turn
			{
			    //switches turn
			    if (attacker == 'B' || attacker == 'L' || attacker == 'S' || attacker == 'T')
			    {
				if (turn == defender)
				{
				    turn = attacker;
				    turnpic.setIcon (createImageIcon (attacker + "k" + colorA + "u.png"));
				}
				else
				{
				    turn = defender;
				    turnpic.setIcon (createImageIcon (defender + "k" + colorB + "u.png"));
				}
			    }
			    else
				if (turn == defender)
				{
				    turn = attacker;
				    turnpic2.setIcon (createImageIcon (attacker + "k" + colorA + "u.png"));
				}
				else
				{
				    turn = defender;
				    turnpic2.setIcon (createImageIcon (defender + "k" + colorB + "u.png"));
				}
			}
		    } //move complete
		    //reset select to unselected
		    for (int i = 0 ; i < row ; i++)
		    {
			for (int j = 0 ; j < col ; j++)
			{
			    select [i] [j] = 'u';
			}
		    }
		    //resets turn value
		    last = -1;
		}
	    }
	    //new board with updated movements
	    redrawWar ();
	}
    }
}
