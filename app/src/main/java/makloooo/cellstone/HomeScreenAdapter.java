package makloooo.cellstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeScreenAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> factions;

    public HomeScreenAdapter(Context context, ArrayList<String> factions) {
        super(context, -1, factions);
        this.context = context;
        this.factions = factions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View factionItem = layoutInflater.inflate(
                R.layout.menuitem_home_screen, parent, false);

        TextView factionText = factionItem.findViewById(R.id.faction_list_item_text);
        factionText.setText(factions.get(position));

        return factionItem;
    }
}
