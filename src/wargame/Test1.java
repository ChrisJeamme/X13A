package wargame;

import javax.swing.*;
import wargame.ISoldat.TypesH;

public class Test1
{
	public static void main(String[] args)
	{
		JFrame frame = new Fenetre();

		
		for(TypesH h: TypesH.values())
			System.out.println(h+" "+h.getPoints()+" "+h.getPortee()+" "+h.getPuissance()+" "+h.getTir());
		
	}
}