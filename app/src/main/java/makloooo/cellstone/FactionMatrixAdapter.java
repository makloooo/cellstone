package makloooo.cellstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FactionMatrixAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> members;

    public FactionMatrixAdapter(Context context, ArrayList<String> members) {
        super(context, -1, members);
        this.context = context;
        this.members = members;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View characterEntry = layoutInflater.inflate(
                R.layout.menuitem_faction_matrix, parent, false);

        TextView characterEntryText = characterEntry.findViewById(R.id.faction_list_item_text);
        characterEntryText.setText(members.get(position));

        return characterEntry;
    }
}
