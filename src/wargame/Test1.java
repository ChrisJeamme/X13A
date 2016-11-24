package wargame;

import javax.swing.*;
import java.awt.*;

import org.apache.log4j.Logger;

import wargame.ISoldat.TypesH;

public class Test1 extends JFrame
{
	private static Logger log = Logger.getLogger(Test1.class);
	static final long serialVersionUID=0;
	Test1(){
		super("Ja va Saigner !");
		PanneauJeu p=new PanneauJeu();
		getContentPane().add(p);
		
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

	}

}