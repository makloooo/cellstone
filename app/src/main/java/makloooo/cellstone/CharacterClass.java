package makloooo.cellstone;

import android.media.Image;

public class CharacterClass {
    private Image portrait;
    private String name;
    private String bio;

    public void setName(String name) { this.name = name; }
    public void setPortrait(Image portrait) { this.portrait = portrait; }
    public void setBiography(String bio) { this.bio = bio; }

    public String getName() { return name; }
    public Image getPortrait() { return portrait; }
    public String getBio() { return bio; }
}
