package wargame;

import org.apache.log4j.Logger;

public class Test1
{
	private static Logger log = Logger.getLogger(Test1.class);
	public static void main(String[] args)
	{
		
		//c = carte
		//c.caseCarte = tableau d'�l�ments (cases de la carte)
		//c.caseCarte[0][0] = case tout en haut � gauche de la carte
		//c.caseCarte[10][2].getPosition() = Position pos de l'�l�ment qui est dans la case (10)(2) stock� dans l'�l�ment lui m�me
		
		Carte c = new Carte();
		
		log.info(c.caseCarte[10][2]);
		log.info(c.caseCarte[10][2].getPosition().getX());
		
		/*log.info("coucou");
		log.warn("Attention");
		log.error("Et merde...");*/
		
	}

}
