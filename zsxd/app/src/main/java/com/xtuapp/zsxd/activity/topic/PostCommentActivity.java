package com.xtuapp.zsxd.activity.topic;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtuapp.zsxd.GlobalConstant;
import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;
import com.xtuapp.zsxd.dialog.LoadingDialog;
import com.xtuapp.zsxd.utils.PrefUtils;
import com.xtuapp.zsxd.view.FlowLayout;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/11 0011.
 */
public class PostCommentActivity extends BaseActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    @ViewInject(R.id.photo_btn)
    private ImageButton phButton;
    @ViewInject(R.id.face_btn)
    private ImageButton faceButton;
    @ViewInject(R.id.im_btn)
    private ImageButton imButton;
    @ViewInject(R.id.toolbar)
    private Toolbar mToolbar;
    @ViewInject(R.id.fl_layout)
    private FlowLayout mFlowLayout;
    private Intent intent;
    private Uri imageUri;
    @ViewInject(R.id.tv_imagesize)
    private TextView imageSizeTextView;
    private static final int TAKE_PHOTO = 1;
    private static final int PICK_PHOTO = 2;
    private String completePhotoPath;
    private LoadingDialog mDialog;
    private List<String> imageList = new ArrayList<>(GlobalConstant.Conf.PHOTO_SUM);
    @Override
    protected int setView() {
        return R.layout.activity_post;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        mDialog = new LoadingDialog(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            //设置返回点击
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(this);
        phButton.setOnClickListener(this);
        faceButton.setOnClickListener(this);
        imButton.setOnClickListener(this);
        mFlowLayout.setOnChildChangedListener(new FlowLayout.OnChildChangedListener() {
            @Override
            public void onRemove(View view) {
                imageList.remove(view.getTag());
                imageSizeTextView.setText(imageList.size() + "/10");
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commit, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.photo_btn):

                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PICK_PHOTO);

//                对照片进行剪裁
//                intent.putExtra("crop", true);
//                intent.putExtra("scale", true);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                break;
            case (R.id.face_btn):
                break;
            case (R.id.im_btn):
                File outputImage = new File(Environment.getExternalStorageDirectory(), PrefUtils.uuid() + ".jpg");
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                completePhotoPath = outputImage.getAbsolutePath();
                imageUri = Uri.fromFile(outputImage);
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        mDialog.show("正在提交");
        return false;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_PHOTO:
                    if (data == null) {
                        return;
                    }
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        completePhotoPath = cursor.getString(0);
                        cursor.close();
                    }
                    if (TextUtils.isEmpty(completePhotoPath)) {
                        completePhotoPath = uri.getPath();
                    }
                    intoFlowLayout();
                    break;
                case TAKE_PHOTO:
                    if(resultCode == RESULT_OK){
                        intoFlowLayout();
                    }
                    break;
            }

        }
    }

    private void intoFlowLayout() {
        if(imageList.size() + 1> GlobalConstant.Conf.PHOTO_SUM ){
            completePhotoPath = null;
            toast("不能再添加了");
            return;
        }if(imageList.contains(completePhotoPath)){
            completePhotoPath = null;
            toast("该照片已经被添加了");
            return;
        }
        ImageView imageView = new ImageView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(PrefUtils.dip2px(this, 56), PrefUtils.dip2px(this, 56));
        params.setMargins(PrefUtils.dip2px(this,8),PrefUtils.dip2px(this,8),PrefUtils.dip2px(this,8),PrefUtils.dip2px(this,8));
        imageView.setLayoutParams(params);

        imageView.setPadding(0, 0, 0, 0);
//                    Bitmap bitmap = BitmapFactory.decodeFile(completePhotoPath);
//                    if (bitmap != null) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                        imageView.setImageBitmap(bitmap);
        x.image().bind(imageView, "file://" + completePhotoPath);
        mFlowLayout.addView(imageView);
        imageList.add(completePhotoPath);
        imageView.setTag(completePhotoPath);
        completePhotoPath = null;
        imageSizeTextView.setText(imageList.size() + "/10" );
    }
}
