package Core;

public class Jeu {
	private User user;
	private Quizz quizzCurrent;
	
	public Jeu (){
		user  = new User();
	}
	
	public void creerQuizz(Quizz quizzCurrent) {
		this.quizzCurrent = quizzCurrent;
	}

	public User getUser() {
		return user;
	}
	
	public Quizz getQuizzCurrent() {
		return quizzCurrent;
	}
	
	public int getLevelByTheme (THEMES theme){
		return user.getLevelByTheme(theme);
	}
	
}
