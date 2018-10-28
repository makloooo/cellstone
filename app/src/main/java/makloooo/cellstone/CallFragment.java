package makloooo.cellstone;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/** This fragment if for calling Vergil when clicking the call
 *  button on the character profile screen. */
public class CallFragment extends Fragment {

    private static final String TAG = "CallFragment";
    private static final String DIALOGUE = "Dialogue";
    private static final String PORTRAIT_ID = "PortraitId";

    private String mDialogue;

    private believe.cht.fadeintextview.TextView mDialogueTextView;
    private int mPortraitResId;

    private OnCallInteractionListener mListener;

    public CallFragment() {
        // Required empty public constructor
    }

    public static CallFragment newInstance(String dialogue, int portraitResId) {
        CallFragment fragment = new CallFragment();
        Bundle args = new Bundle();
        args.putString(DIALOGUE, dialogue);
        args.putInt(PORTRAIT_ID, portraitResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDialogue = getArguments().getString(DIALOGUE);
            mPortraitResId = getArguments().getInt(PORTRAIT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_call, container, false);

        final View progressOverlay = view.findViewById(R.id.caller_progressOverlay);

        mDialogueTextView = view.findViewById(R.id.caller_textbox);
        final ImageView portraitBox = view.findViewById(R.id.caller_portrait);

        /*view.post(new Runnable() {
            @Override
            public void run() {
                dialogueBox.setMaxWidth(dialogueBox.getMeasuredWidth());
                dialogueBox.setText(mDialogue);

                Glide.with(CallFragment.this)
                        .load(R.drawable.sample_silhouette)
                        .into(portraitBox);
            }
        });*/

        progressOverlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressText();
            }
        });

        mDialogueTextView.setText(mDialogue);

        return view;
    }

    public void progressText() {
        Log.d(TAG, "mDialogueTextView.getLineCount() == " + mDialogueTextView.getLineCount());
        Log.d(TAG, "mDialogueTextView.isAnimating() == " + mDialogueTextView.isAnimating());
        // if text is finished, then clearCommentary()
        // else if text is still typing abort typing
        // else somehow go to next line
        if (!mDialogueTextView.isAnimating()) {
            mListener.clearCommentary();
        }

        //mListener.clearCommentary();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallInteractionListener) {
            mListener = (OnCallInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCallInteractionListener {
        void clearCommentary();
    }
}
