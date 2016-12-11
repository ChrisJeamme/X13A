package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fenetre extends JFrame
{
	/** Panneau contenant les boutons du menu */
	private JPanel p;
	/** Bouton chargement */
	JButton chargement;		
	/** Bouton lancement */														
	JButton lancement;
	/** Panneau contenant le jeu */
	PanneauJeu panneau;
	/** Sert à activer/désactiver des raccourci clavier */
	boolean partieDemarre = false;
	
	static final long serialVersionUID=0;
	
	/** Constructeur qui fixe les caractéristiques principales de la fenêtre */
	public Fenetre()
	{
		super("Ja va Saigner !");	
		setFocusable(true);
		requestFocusInWindow();
		boutons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		pack();
		setVisible(true);
	
	}

	/** Affichage des boutons lancement et chargement */
	public void boutons()
	{
		/*Ne contient pas que les boutons mais aussi le JPanel*/
		p = new LancementJeu();	//Menu
		p.setLayout(new GridBagLayout());
		getContentPane().add(p);

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 50;
		gbc.gridwidth = 10;

		// Gestion des raccourcis clavier
		
		addKeyListener(new KeyListener()	
		{
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){}	
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==27 && partieDemarre == false)	//ECHAP en menu
				{
					System.exit(0);		//On quitte le jeu
				}
				
				if (e.getKeyCode()==27 && partieDemarre == true)	//ECHAP en jeu
				{
					retourMenu();		//On retourne au menu
				}
				
				if ((e.getKeyCode()==49 || e.getKeyCode()==97) && partieDemarre == false)	//Touche 1 (pavé num ou l'autre)
				{
					lancementPartie();
				}
				
				if ((e.getKeyCode()==50 || e.getKeyCode()==98) && partieDemarre == false) 	//Touche 2 (pavé num ou l'autre)
				{
					chargementPartie();
				}
			}
		});
		
		lancement = new Bouton("(1)   Lancer une partie", "img/BouttonF.png", "img/BouttonB.png");
		lancement.setPreferredSize(new Dimension(300,30));
		p.add(lancement,gbc);
		lancement.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				lancementPartie();
			}
		});
		
		//Bouton Chargement
		
		chargement = new Bouton("(2)   Charger une partie", "img/BouttonF.png", "img/BouttonB.png");
		chargement.setPreferredSize(new Dimension(300,30));
		gbc.gridy = -10;
		p.add(chargement,gbc);
		chargement.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				chargementPartie();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		pack();
		setVisible(true);
	}

	/** Chargement de la partie */
	protected void chargementPartie()
	{
		ChargementPartie chargement = new ChargementPartie();	//On invoque le dialogue de chargement de partie
		add(chargement);
		Carte c = chargement.getCarte();
		
		if (c != null)	//Si le dialog OK
		{
			p.setVisible(false);	//On efface le menu
			
			partieDemarre = true;
			panneau = new PanneauJeu(c, this); //Panneau avec chargement
			setContentPane(panneau);
			repaint();
			revalidate();
		}
	}

	/** Lancement de la partie */
	protected void lancementPartie()
	{
		p.setVisible(false);
		
		partieDemarre = true;
		panneau = new PanneauJeu(new Carte(), this); //Panneau sans chargement
		setContentPane(panneau);
		repaint();
		revalidate();
	}

	/** Fonction qui réaffiche le menu */
	public void retourMenu()
	{
		p.setVisible(true);
		setContentPane(p);
		
		partieDemarre = false;
		repaint();
		revalidate();
	}
}
	