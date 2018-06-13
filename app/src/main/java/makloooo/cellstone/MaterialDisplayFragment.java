package makloooo.cellstone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaterialDisplayFragment.OnMaterialDisplayListener} interface
 * to handle interaction events.
 * Use the {@link MaterialDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialDisplayFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "MaterialDisplayFragment";
    private static final String DISPLAY_TEXT = "DISPLAY_TEXT";

    private String mDisplayText;

    private OnMaterialDisplayListener mListener;

    public MaterialDisplayFragment() {
        // Required empty public constructor
    }

    public static MaterialDisplayFragment newInstance(String displayText) {
        MaterialDisplayFragment fragment = new MaterialDisplayFragment();
        Bundle args = new Bundle();
        args.putString(DISPLAY_TEXT, displayText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDisplayText = getArguments().getString(DISPLAY_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_material_display, container, false);

        final ScrollView materialView = view.findViewById(R.id.materialDisplay_scrollView);
        TextView textView = view.findViewById(R.id.materiaDisplay_textView);

        textView.setText(mDisplayText);

        //ImageButton backButton = view.findViewById(R.id.materialDisplay_backButton);

        // TODO: Make whole screen clickable
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearMaterialDisplay();
            }
        });

        // TODO: Fade in animation
        //materialView.setVisibility(View.VISIBLE);
        //materialView.bringToFront();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMaterialDisplayListener) {
            mListener = (OnMaterialDisplayListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMaterialDisplayListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMaterialDisplayListener {
        // TODO: Update argument type and name
        void clearMaterialDisplay();
    }
}
