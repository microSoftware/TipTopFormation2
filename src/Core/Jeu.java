package Core;

import android.os.Parcel;
import android.os.Parcelable;


/*
 * Jeu est un Singleton. Cela évite d'utiliser des intents. Moins de code. Plus simple
 */
public class Jeu  {
	
	private static Jeu instance;
	private User user;
	private THEMES themeChoisi;
	private int levelChoisi;
	//private Quizz quizzCurrent;
	
	
	private Jeu (){
		user  = new User();
	}
	
	public static Jeu getInstance() {
	        if (null == instance) { // Premier appel
	            instance = new Jeu();
	        }
	        return instance;
	}
	 
	public THEMES getThemeChoisi() {
		return themeChoisi;
	}

	public int getLevelChoisi() {
		return levelChoisi;
	}

	public void setLevelChoisi(int levelChoisi) {
		this.levelChoisi = levelChoisi;
	}

	public void setThemeChoisi(THEMES themeChoisi) {
		this.themeChoisi = themeChoisi;
	}

	
	
	public User getUser() {
		return user;
	}
	

	public int getLevelByTheme (THEMES theme){
		return user.getLevelByTheme(theme);
	}

	



}
