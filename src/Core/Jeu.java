package Core;

import android.content.Context;
import android.util.Log;


/*
 * Jeu est un Singleton. Cela évite d'utiliser des intents. Moins de code. Plus simple.
 */
public class Jeu  {
	
	private Context contexte;
	private static Jeu instance;
	private User user;
	private QuizzModel quizz;
	
	
	public QuizzModel getQuizz() {
		return quizz;
	}

	private Jeu (){
		
			user  = new User();
		
	}
	
	public static Jeu getInstance() {
	        if (null == instance) { // Premier appel
	        	instance = new Jeu();
	        }
	        return instance;
	}
	 
	
	public User getUser() {
		return user;
	}
	

	public int getLevelByTheme (THEMES theme){
		return user.getLevelByTheme(theme);
	}

	public void initialiserLeQuizz() {
		// TODO Auto-generated method stub
		quizz = new QuizzModel();
		
	}

	/*
	 * Pour recommencer une partie 
	 * Il faut sauvegarder le thème et le level choisi
	 * Mettre quizz à null et recréer un quizz avec le thème et le level
	 */
	public void recommencer(){
		
		
		//sauvegarde du thème et du level
		THEMES theme = quizz.getTheme();
		int level = quizz.getLevelChoisi();
		
		quizz = null;
		quizz = new QuizzModel();
		quizz.recommencer(theme, level);
	}
	
	/*
	 * Pour s'entrainer sur un thème, 
	 * on récupère le thème en argument, l'utilisateur
	 * choisira son niveau par la suite
	 */
	public void sentrainer(THEMES theme){
		
		quizz = null;
		quizz = new QuizzModel();
		quizz.setTheme(theme);
	}
	
	
	
	/*
	 * Cette méthode pour récupérer les ressources (fichiers
	 * csv). En effet, si une classe n'est pas une activité,
	 * elle ne peut pas acceder au ressource. L'astuce consiste
	 * donc à passer en argument le contexte d'une activité android
	 * à une classe java.
	 */
	public void initialiserContexteAndroid(Context context) {
		
		this.contexte = context;
		
	}

	public Context getContexte() {
		return contexte;
	}
	
	


}
