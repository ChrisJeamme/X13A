package wargame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Classe permettant de créer les boutons
 */
public class Bouton extends JButton
{
	private static final long serialVersionUID = -8348361669997490348L;
	/**
	 * Constructeur Bouton
	 * @param texte Texte du bouton
	 * @param img Image du bouton
	 * @param imgHover Image du bouton cliqué
	 */
	public Bouton(String texte, String img, String imgHover)
	{
		super(texte);
		setForeground(Color.WHITE);
		setOpaque(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(new ImageIcon(img));
		setRolloverIcon(new ImageIcon(imgHover));
	}
}