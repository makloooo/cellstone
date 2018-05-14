package makloooo.cellstone;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

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

    Character(String name, int resId, String description) {
        this.name = name;
        this.portraitId = resId;
        this.bio = description;
    }

    public void setName(String name) { this.name = name; }
    public void setPortrait(int portraitId) { this.portraitId = portraitId; }
    public void setBiography(String bio) { this.bio = bio; }

    public String getName() { return name; }
    public int getPortraitId() { return portraitId; }
    public String getBio() { return bio; }
}

