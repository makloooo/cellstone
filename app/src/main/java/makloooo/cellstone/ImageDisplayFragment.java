package makloooo.cellstone;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
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
    private ObjectAnimator mSigilAnimator;

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

        /*view.post(new LoadMainPortraitRunnable(mImageView,
                mResId,
                this));*/

        // Setting up rotation animator
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(mImageView, "rotationY", 0, 360);
        objectAnimator.setDuration(8000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        mSigilAnimator = objectAnimator;

        view.post(new Runnable() {
            @Override
            public void run() {
                loadSigil();
            }
        });

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

    public interface OnImageInteractionListener {
        // TODO: Update argument type and name
        // void onFragmentInteraction(Uri uri);
    }

    public void changePortrait(int resId) {
        mResId = resId;
        if (resId == R.drawable.sample_sigil) loadSigil();
        else loadPortrait();
    }

    public void loadSigil() {

        // Loading in Sigil Image
        Glide.with(this)
                .load(R.drawable.sample_sigil)
                .apply(RequestOptions.centerInsideTransform())
                .into(mImageView);

        // scaling the sigil
        mImageView.setScaleX(0.75f);
        mImageView.setScaleY(0.75f);

        mSigilAnimator.start();
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

        mImageView.setScaleX(1f);
        mImageView.setScaleY(1f);

        mSigilAnimator.end();
    }
}
