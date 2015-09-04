package za.org.sudo.safesaving;

/**
 * Created by hendrikschalekamp on 15/08/28.
 */
public class ActionModel {
    private String letter;
    private String word;
    private String description;
    private Boolean hidden;
    private int level;

    public ActionModel(String letter, String word, String description, int level) {
        this.letter = letter;
        this.word = word;
        this.description = description;
        this.hidden = true;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public String getLetter() {
        return letter;
    }

    public String getWord() {
        return word;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public int getLevel() {
        return level;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
