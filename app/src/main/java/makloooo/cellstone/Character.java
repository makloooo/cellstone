package makloooo.cellstone;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

//@Entity(tableName = "characters")
public class Character {
    //@PrimaryKey
    //private int id;

    //@ColumnInfo(name = "portrait_resId")
    private int portraitId;
    //@ColumnInfo(name = "character_name")
    private String name;
    //@ColumnInfo(name = "character_description")
    private String bio;

    private HashMap<String, String> attributes;

    private ArrayList<String> loreCaptions;
    private ArrayList<String> loreMaterial;

    private ArrayList<String> trivia;

    Character(String name, int resId, String description) {
        this.name = name;
        this.portraitId = resId;
        this.bio = description;
        this.attributes = new HashMap<>();
        this.loreCaptions = new ArrayList<>();
        this.loreMaterial = new ArrayList<>();
        this.trivia = new ArrayList<>();
    }

    public void setName(String name) { this.name = name; }
    public void setPortrait(int portraitId) { this.portraitId = portraitId; }
    public void setBiography(String bio) { this.bio = bio; }

    public void addLoreEntry(String caption, String material) {
        loreCaptions.add(caption);
        loreMaterial.add(material);
    }

    public String getName() { return name; }
    public int getPortraitId() { return portraitId; }
    public String getBio() { return bio; }

    public HashMap<String, String> getAttributes() { return attributes; }

    public ArrayList<String> getLoreCaptions() { return loreCaptions; }
    public ArrayList<String> getLoreMaterial() { return loreMaterial; }

    public ArrayList<String> getTriviaEntries() { return trivia; }
}

