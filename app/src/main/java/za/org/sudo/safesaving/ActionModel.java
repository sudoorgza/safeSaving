package za.org.sudo.safesaving;

/**
 * Created by hendrikschalekamp on 15/08/28.
 */
public class ActionModel {
    private String mLetter;
    private String mWord;
    private String mDescription;

    public ActionModel(String letter, String word, String description) {
        mLetter = letter;
        mWord = word;
        mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLetter() {
        return mLetter;
    }

    public String getWord() {
        return mWord;
    }
}
