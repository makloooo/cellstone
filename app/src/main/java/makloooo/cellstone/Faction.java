package makloooo.cellstone;

import java.util.ArrayList;

public class Faction {
    // L: Name String, K: Character Data
    ArrayList<Character> roster;

    String name;

    public Faction(String name) {
        this.name = name;
        this.roster = new ArrayList<>();
    }

    public void addCharacter(Character character) { roster.add(character); }

    public Character getCharacter(int index) { return roster.get(index); }

    public String getName() { return name; }

    public ArrayList<String> getCharacterNames() {
        ArrayList<String> characterNames = new ArrayList<>();
        for (Character c : roster) characterNames.add(c.getName());
        return characterNames;
    }
}