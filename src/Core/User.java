package Core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.res.Resources.Theme;

public class User {
	private String name;
	private int point;
	private HashMap level;//clé-valeur
	
	
	public User() {
		super();
		/*
		 *On cherche dans la BD le nom, les points et le niveau de joueur
		this.name
		this.point
		this.level 
		*/
		
		//Pour l'instant on remplit les valeurs en brutes
		this.name ="Alan";
		this.point =10;
		this.level = new HashMap();
		
		
		//on met des niveaux au pif pour chaque thèmes mais normalement
		//on devrait rechercher dans la bd
		level.put(THEMES.MENAGE ,10); //Pour ménage, l'user à 10 points
		level.put(THEMES.MATHS ,20); 
		level.put(THEMES.CULTURE_GENERALE ,14); 
		level.put(THEMES.FRANCAIS ,8); 
		
	} 
	
	/*
	 * Retourne le niveau du joueur par rapport à un thème
	 */
	public int getLevelByTheme (THEMES theme){
		/*
		 * on récupère le nombre de point dans la hashmap
		 * La clé est theme
		 */
		
		int nbPointDuTheme = (Integer) level.get(theme);
		
		if (nbPointDuTheme < 10)
			return 1;
		else if (nbPointDuTheme < 20)
			return 2;
		else if (nbPointDuTheme < 30)
			return 3;
		
		return 0; //Erreur
		
	}
	
	
	
}
