package wargame;

import org.apache.log4j.Logger;

public class Test1
{
	private static Logger log = Logger.getLogger(Test1.class);
	public static void main(String[] args)
	{
		
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
		
	}

}
