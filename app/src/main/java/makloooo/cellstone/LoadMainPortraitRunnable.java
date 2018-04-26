package makloooo.cellstone;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.CropTransformation.CropType;

public class LoadMainPortraitRunnable implements Runnable {
    private ImageView mImageView;
    private int mResId;
    private Fragment mFragment;

    public LoadMainPortraitRunnable(ImageView imageView, int resId, Fragment context) {
        mImageView = imageView;
        mResId = resId;
        mFragment = context;
    }

    @Override
    public void run() {
        RequestOptions options = new RequestOptions();
        options.transforms(new CropTransformation(
                mImageView.getWidth(),
                mImageView.getHeight() - mImageView.getPaddingTop(),
                CropType.TOP));
        Glide.with(mFragment)
                .load(mResId)
                .apply(options)
                .into(mImageView);
    }
}