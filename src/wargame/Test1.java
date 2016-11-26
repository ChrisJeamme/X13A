package wargame;

import javax.swing.*;
import org.apache.log4j.Logger;
import wargame.ISoldat.TypesH;

public class Test1
{
	private static Logger log = Logger.getLogger(Test1.class);
	
	public static void main(String[] args)
	{
		JFrame frame = new Fenetre();

		
		for(TypesH h: TypesH.values())
			log.info(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());
		
	}
}



/*log.info("coucou");
log.warn("Attention");
log.error("Et merde...");*/