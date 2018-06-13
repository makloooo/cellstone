package makloooo.cellstone;

import java.util.ArrayList;
import java.util.HashMap;

//@Entity(tableName = "characters")
public class Character {
    //@PrimaryKey
    //private int id;

    //@ColumnInfo(name = "portrait_resId")
    private int portraitId;
    //@ColumnInfo(name = "character_description")
    private String bio;

    //@ColumnInfo(name = "character_name")
    private String name;

    private HashMap<String, String> attributes;
    private HashMap<String, String> stats;

    private ArrayList<String> loreCaptions;
    private ArrayList<String> loreMaterial;

    private ArrayList<String> speculation;

    Character(String name, int resId, String description) {
        this.name = name;
        this.portraitId = resId;
        this.bio = description;
        initAttributes();
        this.loreCaptions = new ArrayList<>();
        this.loreMaterial = new ArrayList<>();
        this.speculation = new ArrayList<>();
    }

    private void initAttributes() {
        String[] attrs = {"NAME", "TITLE", "HEIGHT/WEIGHT", "RACE/SPECIALIZATION", "MISC", "NOTES"};
        this.attributes = new HashMap<>();
        for (String attr : attrs) { attributes.put(attr, "NULL"); }

        String[] statNames = {"STR", "DEX", "INT", "WIS", "CON", "CHR"};
        this.stats = new HashMap<>();
        for (String name : statNames) { stats.put(name, "NULL"); }
    }

    public void setPortrait(int portraitId) { this.portraitId = portraitId; }

    public void setName(String name) {
        this.name = name;
        attributes.put("NAME", name);
    }
    public void setTitle(String title) { attributes.put("TITLE", title); }
    public void setHeightWeight(int height, int weight) {
        attributes.put("HEIGHT/WEIGHT", height + "cm / " + weight + "kg");
    }
    public void setRaceSpecialization(String race, String specialization) {
        attributes.put("RACE/SPECIALIZATION", race + " / " + specialization);
    }
    public void setMisc(String misc) { attributes.put("MISC", misc); }
    public void setStats(String str, String dex, String int_, String wis, String con, String chr) {
        String statKeys[] = {"STR", "DEX", "INT", "WIS", "CON", "CHR"};
        String statValues[] = {str, dex, int_, wis, con, chr};
        for (int i = 0; i < stats.size(); ++i) {
            stats.put(statKeys[i], statValues[i]);
        }
    }
    public void setNotes(String notes) { attributes.put("NOTES", notes); }

    public void addLoreEntry(String caption, String material) {
        loreCaptions.add(caption);
        loreMaterial.add(material);
    }
    public void addSpeculationEntry(String entry) { speculation.add(entry); }

    public String getName() { return attributes.get("NAME"); }
    public int getPortraitId() { return portraitId; }

    public HashMap<String, String> getAttributes() { return attributes; }
    public HashMap<String, String> getStats() { return stats; }

    public ArrayList<String> getLoreCaptions() { return loreCaptions; }
    public ArrayList<String> getLoreMaterial() { return loreMaterial; }
    public ArrayList<String> getSpeculationEntries() { return speculation; }
}

