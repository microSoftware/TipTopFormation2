package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

public class User extends Activity implements Serializable {
	
	private static final long serialVersionUID = 1367898756;

	private final static int LEVEL1 = 10;
	private final static int LEVEL2 = 20;
	private final static int LEVEL3 = 34;
	
	
	private HashMap<THEMES, Integer> level;//clé-valeur
	private HashMap<THEMES, Integer> pointPartieEnCours;//clé-valeur
	
	public User() {
		super();
		
		//Pour l'instant on remplit les valeurs en brutes
		this.level = new HashMap();
		
		
		//on met des niveaux au pif pour chaque thèmes mais normalement
		//on devrait rechercher dans la bd
		level.put(THEMES.MENAGE ,8); //Pour ménage, l'user à 10 points => level 2
		level.put(THEMES.MATHS ,0); //level 3
		level.put(THEMES.FRANCAIS ,0); //level 1
		
		Log.w("","---------------------------------------------------");
		Log.w("","---------------------------------------------------");
		Log.w("","---------------   Creation d'une instance de de USER    ------------------");
		Log.w("","---------------   ------------------");
		Log.w("","---------------------------------------------------");
		Log.w("","---------------------------------------------------");
	} 
	
	private HashMap<THEMES, Integer> getLevel() {
		return level;
	}

	private void setLevel(HashMap<THEMES, Integer> level) {
		this.level = level;
	}

	private HashMap getPointPartieEnCours() {
		return pointPartieEnCours;
	}

	private void setPointPartieEnCours(HashMap pointPartieEnCours) {
		this.pointPartieEnCours = pointPartieEnCours;
	}

	public void initialiserPointPartie (){
		this.pointPartieEnCours = new HashMap();
		pointPartieEnCours.put(THEMES.MENAGE ,0);
		pointPartieEnCours.put(THEMES.MATHS ,0);
		pointPartieEnCours.put(THEMES.FRANCAIS ,0);
	}
	
	public void ajouterPoint(THEMES theme){
		int point = (Integer) pointPartieEnCours.get(theme) + 1;
		pointPartieEnCours.remove(theme);
		pointPartieEnCours.put(theme ,point);
	}
	
	public int nbPointsGagnes (THEMES theme){
		
		//pour la culture générale 
		//on retourne la sommme de tous les thèmes
		
		if (theme == THEMES.CULTURE_GENERALE){
			int menage = (Integer) pointPartieEnCours.get(THEMES.MENAGE);
			int maths = (Integer) pointPartieEnCours.get(THEMES.MATHS);
			int francais = (Integer) pointPartieEnCours.get(THEMES.FRANCAIS);
			
			int total = menage + maths + francais;
			return total;
			
		}

		return (Integer) pointPartieEnCours.get(theme);
	}
	
	/*
	 * Quand le quizz est fini, on transfert les points de la partie
	 * en cours dans la hashmap level
	 */
	public void sauvegarderPointPartie (){
		int menage = (Integer) pointPartieEnCours.get(THEMES.MENAGE);
		int maths = (Integer) pointPartieEnCours.get(THEMES.MATHS);
		int francais = (Integer) pointPartieEnCours.get(THEMES.FRANCAIS);
		
		menage +=  (Integer) level.get(THEMES.MENAGE);
		maths += (Integer) level.get(THEMES.MATHS);
		francais += (Integer) level.get(THEMES.FRANCAIS);
		
		level.remove(THEMES.MENAGE); 
		level.remove(THEMES.MATHS); 
		level.remove(THEMES.FRANCAIS); 
		
		
		level.put(THEMES.MENAGE ,menage); 
		level.put(THEMES.MATHS ,maths); 
		level.put(THEMES.FRANCAIS ,francais); 
		
	}
	
	
	/*
	 * Quand on affiche les résultats du Quizz
	 * On affiche à un moment le nombre de point
	 * neccessaire pour passer au level suivant
	 * Cette fonction est faite pour cela
	 */
	public int differencePointLevelSuivant (THEMES theme){
		int point =  (Integer) level.get(theme) + nbPointsGagnes(theme);
		int level = getLevelByTheme (theme);
		int pointManquant = 0;
		if (level == 1)
			pointManquant = LEVEL1 - point;
		else if (level == 2)
			pointManquant = LEVEL2 - point;
		else if (level == 3)
			pointManquant = LEVEL3 - point;
		
		return pointManquant;
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
		
		if (nbPointDuTheme < LEVEL1)
			return 1;
		else if (nbPointDuTheme < LEVEL2)
			return 2;
		else if (nbPointDuTheme < LEVEL3)
			return 3;
		
		return 0;
		
	}
	
	/*
	 * Retourne le niveau du joueur par rapport à un thème
	 */
	public int getNouveauLevelByTheme (THEMES theme){
		/*
		 * on récupère le nombre de point dans la hashmap
		 * La clé est theme
		 */
		
		int nbPointDuTheme = (Integer) level.get(theme)  + nbPointsGagnes(theme);
		
		if (nbPointDuTheme < LEVEL1)
			return 1;
		else if (nbPointDuTheme < LEVEL2)
			return 2;
		else if (nbPointDuTheme < LEVEL3)
			return 3;
		
		return 0;
		
	}
	
	
	public int scoreToral (){
		int scoreTotal = 0;
		scoreTotal +=  (Integer) level.get(THEMES.MENAGE);
		scoreTotal += (Integer) level.get(THEMES.MATHS);
		scoreTotal += (Integer) level.get(THEMES.FRANCAIS);
		
		return scoreTotal;
	}
	
}
