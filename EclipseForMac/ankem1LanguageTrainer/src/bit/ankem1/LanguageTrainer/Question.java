package bit.ankem1.LanguageTrainer;


// Question class
// Author: Matt Ankerson
// This class serves to hold the information required for a single question
public class Question 
{
	private int imageID;
	private String gender;

	// Constructor
	public Question(int theImageID, String theGender) 
	{
		this.setImageID(theImageID);
		this.setGender(theGender);
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int article) {
		this.imageID = article;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
