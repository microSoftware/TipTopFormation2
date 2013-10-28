package Core;

import com.example.tiptopformation2.Quizz;

import android.os.Parcel;
import android.os.Parcelable;


/*
 * Jeu est un Singleton. Cela évite d'utiliser des intents. Moins de code. Plus simple
 */
public class Jeu  {
	
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

	



}
