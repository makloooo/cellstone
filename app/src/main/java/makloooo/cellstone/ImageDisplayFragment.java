package makloooo.cellstone;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.CropTransformation.CropType;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageDisplayFragment.OnImageInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String IMG_ID = "ImageID";

    // TODO: Rename and change types of parameters
    private int mResId = 0;
    private ImageView mImageView;

    private OnImageInteractionListener mListener;

    public ImageDisplayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ImageDisplayFragment newInstance(int resId) {
        ImageDisplayFragment fragment = new ImageDisplayFragment();
        Bundle args = new Bundle();
        args.putInt(IMG_ID, resId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mResId = getArguments().getInt(IMG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater
                .inflate(R.layout.fragment_image_display, container, false);

        mImageView = view.findViewById(R.id.main_image_display);

        view.post(new LoadMainPortraitRunnable(mImageView,
                mResId,
                this));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnImageInteractionListener) {
            mListener = (OnImageInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnImageInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnImageInteractionListener {
        // TODO: Update argument type and name
        // void onFragmentInteraction(Uri uri);
    }

    public void changePortrait(int resId) {
        mResId = resId;
        loadPortrait();
    }

    public void loadPortrait() {
        RequestOptions options = new RequestOptions();
        options.transforms(new CropTransformation(
                mImageView.getWidth(),
                mImageView.getHeight() - mImageView.getPaddingTop(),
                CropType.TOP));
        Glide.with(this)
                .load(mResId)
                .apply(options)
                .into(mImageView);
    }
}
