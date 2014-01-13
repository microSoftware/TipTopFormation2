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

	private final static int LEVEL1 = 15;
	private final static int LEVEL2 = 25;
	private final static int LEVEL3 = 40;
	
	
	private HashMap<THEMES, Integer> level;//cl�-valeur
	private HashMap<THEMES, Integer> pointPartieEnCours;//cl�-valeur
	
	public User() {
		super();
		
		//Pour l'instant on remplit les valeurs en brutes
		this.level = new HashMap();
		
		
		//on met des niveaux au pif pour chaque th�mes mais normalement
		//on devrait rechercher dans la bd
		level.put(THEMES.MENAGE ,30); //Pour m�nage, l'user � 10 points => level 2
		level.put(THEMES.MATHS ,0); //level 3
		level.put(THEMES.FRANCAIS ,0); //level 1
		int total = (Integer) level.get(THEMES.MENAGE) + (Integer) level.get(THEMES.MATHS) + (Integer) level.get(THEMES.FRANCAIS);
		int moyenne = total / 3;
		level.put(THEMES.CULTURE_GENERALE ,moyenne);
		
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
		pointPartieEnCours.put(THEMES.CULTURE_GENERALE ,0);
	}
	
	public void ajouterPoint(THEMES theme){
		int point = (Integer) pointPartieEnCours.get(theme) + 1;
		pointPartieEnCours.remove(theme);
		pointPartieEnCours.put(theme ,point);
	}
	
	public int nbPointsGagnes (THEMES theme){
		
		//pour la culture g�n�rale 
		//on retourne la sommme de tous les th�mes
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
		
		int total = menage + maths + francais;
		int moyenne = total / 3;
		
		level.remove(THEMES.MENAGE); 
		level.remove(THEMES.MATHS); 
		level.remove(THEMES.FRANCAIS); 
		level.remove(THEMES.CULTURE_GENERALE); 
		
		level.put(THEMES.MENAGE ,menage); 
		level.put(THEMES.MATHS ,maths); 
		level.put(THEMES.FRANCAIS ,francais); 
		level.put(THEMES.CULTURE_GENERALE ,moyenne);
	}
	
	
	/*
	 * Quand on affiche les r�sultats du Quizz
	 * On affiche � un moment le nombre de point
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
	 * Retourne le niveau du joueur par rapport � un th�me
	 */
	public int getLevelByTheme (THEMES theme){
		/*
		 * on r�cup�re le nombre de point dans la hashmap
		 * La cl� est theme
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
	
	public void sauvegarder (){
		//on cr�er l'objet � sauvegarder
		User usr = new User();
		usr.setLevel(level);
		//usr.setPointPartieEnCours(pointPartieEnCours);
		
		try
        {
			Context context = Jeu.getInstance().getContexte();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		                context.openFileOutput("save.bin", Context.MODE_PRIVATE));
			objectOutputStream.writeObject(usr);
			objectOutputStream.flush(); 
			objectOutputStream.close();
        }
        catch(Exception ex)
        {
        	Log.v("User class SAUVEGARDE : ",ex.getMessage());
        	ex.printStackTrace();
        }
	}
	
	public static Object restaurer (){
		File f = new File("save.bin");
		{
		    try
		    {
		    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		    	Object o = ois.readObject();
		    	return o;
		    }
		    catch(Exception ex)
		    {
			Log.v("User Class - RESTAURER",ex.getMessage());
		    	ex.printStackTrace();
		    }
		    return null;
		}


	}
	
	public int scoreToral (){
		int scoreTotal = 0;
		scoreTotal +=  (Integer) level.get(THEMES.MENAGE);
		scoreTotal += (Integer) level.get(THEMES.MATHS);
		scoreTotal += (Integer) level.get(THEMES.FRANCAIS);
		
		return scoreTotal;
	}
	
}
