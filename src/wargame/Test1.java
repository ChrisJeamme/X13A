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
	static final long serialVersionUID=0;
	private static int test=0;
	Test1(){
		super("Ja va Saigner !");
		/* Rien compris a ce truc c'était pour tester */
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridheight = 50;
		gbc.gridwidth = 10;

		    
		p=new LancementJeu();
		p.setLayout(new GridBagLayout());
		getContentPane().add(p);
		
		JButton lancement=new JButton("Lancer une partie");
		lancement.setPreferredSize(new Dimension(300,30));
		p.add(lancement,gbc);
		
		JButton chargement=new JButton("Charger une partie");
		chargement.setPreferredSize(new Dimension(300,30));
		gbc.gridy = -10;
		p.add(chargement,gbc);
		
		lancement.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e){
				remove(p);
				setContentPane(new PanneauJeu());
				repaint();
				revalidate();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		
		/* a faire dans une autre classe comme panneaujeu */
		JMenuBar MenuBar=new JMenuBar();
		MenuBar.setOpaque(true);
		MenuBar.setPreferredSize(new Dimension(200,50));
		MenuBar.setBackground(new Color(125,125,125));
		setJMenuBar(MenuBar);
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		JFrame frame=new Test1();
		//c = carte
		//c.caseCarte = tableau d'éléments (cases de la carte)
		//c.caseCarte[0][0] = case tout en haut à gauche de la carte
		//c.caseCarte[10][2].getPosition() = Position pos de l'élément qui est dans la case (10)(2) stocké dans l'élément lui même
		
		Carte c = new Carte();
		
		log.info(c.caseCarte[10][2]);
		log.info(c.caseCarte[10][2].getPosition().getX());
		
		/*log.info("coucou");
		log.warn("Attention");
		log.error("Et merde...");*/
		
		for(TypesH h: TypesH.values())
			System.out.println(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());

		Position p=new Position(0,0);
		Position p2= new Position(8,5);
		Position p3= new Position (5,2);
		System.out.println("Test :"+p.distance(p2)+" "+p2.distance(p3));
		
	}

}