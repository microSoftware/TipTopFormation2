package Exercices;

import Core.EXERCICES;
import Core.Jeu;
import Core.QuizzModel;
import Core.THEMES;
import android.content.res.Resources.Theme;

public abstract class QuestionReponse {
	
	/*
	 * Tous ce qui est commun à toutes les questions :
	 * - le thème du quizz, le type de l'exo, le level
	 * - numéro de la question, l'id de la question
	 */
	protected Jeu jeu;
	protected QuizzModel quizz;
	protected Theme themeDuQuizz;
	protected EXERCICES typeExo;
	private int level;//0 pour savoir quel fichier csv lire
	protected int id;
	
	protected int numeroDeLaQuestion;
	protected String question;
	protected String phraseCorrection;
	
	
	
	

	

	public QuizzModel getQuizz() {
		return quizz;
	}

	public QuestionReponse() {
		super();
		jeu = Jeu.getInstance();
		quizz = jeu.getQuizz();
	}
	
	public void setNumeroDeLaQuestion(int numeroDeLaQuestion) {
		this.numeroDeLaQuestion = numeroDeLaQuestion;
	}

	

	public String getQuestion() {
		return question;
	}

	public String getPhraseCorrection() {
		return phraseCorrection;
	}

	public int getLevel() {
		return level;
	}

	public int getNumeroDeLaQuestion() {
		return numeroDeLaQuestion;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public int getId() {
		return id;
	}

	public QuestionReponse getInstanceDeLaQuestion() {
		return this;
	}

	

	public Theme getThemeDuQuizz() {
		return themeDuQuizz;
	}

	
	public static String[]  lireCSV (int[] idANePasPrendre){
		/*
		 int nbLigne = nbLigneFichier(urlFichier);
		String[][] monTableau = new String[nbLigne][nbColonneMax];
 
		try {
 
			FileInputStream fichier = new FileInputStream(urlFichier);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					fichier));
			LineNumberReader l = new LineNumberReader(buffer);
			String str;
			String ligne;
			while ((ligne = l.readLine()) != null) {
				// on parcourt toutes les lignes
 
				// ligne = notre ligne
 
				StringTokenizer colonnes = new StringTokenizer(ligne, ";");
				// nos colonnne
				int i = 0;
				while (colonnes.hasMoreTokens()) {
 
					monTableau[l.getLineNumber() - 1][i] = colonnes.nextToken();
					i++;
				}
 
			}
 
		} catch (IOException e) {
			System.out.println("Erreur dans la lecture du fichier");
			e.printStackTrace();
		}
		//retourne donc un tableau[ligne][colonne] qui contient tout notre fichier
		return monTableau;
		 */
		
		//NE PAS OUBLIER D'AJOUTER l'id DE LA QUESTION DANS "tabQuestionHistorique" et d'INCREMENTER "nbQuestionDejaAjouter"
		String chaine[]={"01","Ma question est ","réponse 1", "réponse 2","réponse 3"};
		return chaine;
	}
	
	/*
	 * Fonction commune à toutes les classes filles
	 * Elle permet d'extraire les éléments d'un tableau d'une position x jusqu'à la fin du tableau
	 */
	public String[] extraireTousElementsTableau(String[] tableau, int x){
		String tab[] = null;
		int counterTab = 0;
		boolean encoreElement = true;
		int i=x;
		while (encoreElement){
			if (tableau[i]!=null){
				tab[counterTab] = tableau[i];
			}
			else {
				encoreElement = false;
			}
		}
		return tab;
	}
	
	public EXERCICES getTypeExo() {
		return typeExo;
	}
	
	
}
