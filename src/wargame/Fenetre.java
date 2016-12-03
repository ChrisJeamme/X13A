package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Fenetre extends JFrame
{
	/** */
	private JPanel p;
	/** */
	JButton chargement;		
	/** */														//TODO mettre public et tout
	JButton lancement;
	/** */
	JPanel tmp;
	/** */
	JMenuBar menuBar;
	/** */
	PanneauJeu panneau;
	
	static final long serialVersionUID=0;
	
	public Fenetre()
	{
		super("Ja va Saigner !");	
		boutons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		pack();
		setVisible(true);
	
	}

	public void boutons()
	{
		/*Ne contient pas que les boutons mais aussi le JPanel*/
		final JFrame f=this;
		p = new LancementJeu();	//Menu
		p.setLayout(new GridBagLayout());
		getContentPane().add(p);

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 50;
		gbc.gridwidth = 10;
		    
		lancement = new Boutton("Lancer une partie", "img/BouttonF.png", "img/BouttonB.png");
		lancement.setPreferredSize(new Dimension(300,30));
		p.add(lancement,gbc);
		lancement.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				p.setVisible(false);
				
				panneau = new PanneauJeu(new Carte(),f); //Panneau sans chargement
				setContentPane(panneau);
				repaint();
				revalidate();
			}
		});
		
		//Bouton Chargement
		
		chargement = new Boutton("Charger une partie", "img/BouttonF.png", "img/BouttonB.png");
		chargement.setPreferredSize(new Dimension(300,30));
		gbc.gridy = -10;
		p.add(chargement,gbc);
		chargement.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				ChargementPartie chargement = new ChargementPartie();	//On invoque le dialogue de chargement de partie
				add(chargement);
				Carte c = chargement.getCarte();
				
				if (c != null)	//Si le dialog OK
				{
					p.setVisible(false);	//On efface le menu
					
					panneau = new PanneauJeu(c, f); //Panneau avec chargement
					setContentPane(panneau);
					repaint();
					revalidate();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		pack();
		setVisible(true);
	}

	public void retourMenu()
	{
		p.setVisible(true);
		setContentPane(p);
		
		repaint();
		revalidate();
	}
}
	