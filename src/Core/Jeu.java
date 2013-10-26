package Core;

import android.os.Parcel;
import android.os.Parcelable;

public class Jeu implements Parcelable {
	private User user;
	//private Quizz quizzCurrent;
	
	public Jeu (){
		user  = new User();
	}
	
	public User getUser() {
		return user;
	}
	

	public int getLevelByTheme (THEMES theme){
		return user.getLevelByTheme(theme);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
}
