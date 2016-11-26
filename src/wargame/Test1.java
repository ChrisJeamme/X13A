package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.log4j.Logger;

import wargame.ISoldat.TypesH;

public class Test1 extends JFrame
{
	private static Logger log = Logger.getLogger(Test1.class);
	private JPanel p;
	JButton chargement;
	JButton lancement;
	PanneauJeu panneau;
	static final long serialVersionUID=0;
	
	public Test1()
	{
		super("Ja va Saigner !");
		/* Rien compris a ce truc c'�tait pour tester */
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 50;
		gbc.gridwidth = 10;
		    
		p = new LancementJeu();	//Menu
		p.setLayout(new GridBagLayout());
		getContentPane().add(p);
		
		//Bouton Lancement
		
		lancement = new Boutton("Lancer une partie", "img/BouttonF.png", "img/BouttonB.png");
		lancement.setPreferredSize(new Dimension(300,30));
		p.add(lancement,gbc);
		lancement.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				remove(p);	//On supprime le menu
				panneau = new PanneauJeu(false); //Panneau sans chargement
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
			public void mouseClicked(MouseEvent e)
			{
				remove(p);	//On supprime le menu
				panneau = new PanneauJeu(true); //Panneau avec chargement
				setContentPane(panneau);
				repaint();
				revalidate();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new Test1();
		//c = carte
		//c.caseCarte = tableau d'�l�ments (cases de la carte)
		//c.caseCarte[0][0] = case tout en haut � gauche de la carte
		//c.caseCarte[10][2].getPosition() = Position pos de l'�l�ment qui est dans la case (10)(2) stock� dans l'�l�ment lui m�me
		
		
		for(TypesH h: TypesH.values())
			log.info(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());
		

		/*
		Position p=new Position(0,0);
		Position p2= new Position(8,5);
		Position p3= new Position (5,2);
		System.out.println("Test :"+p.distance(p2)+" "+p2.distance(p3));*/
		
	}
}

/*log.info("coucou");
log.warn("Attention");
log.error("Et merde...");*/