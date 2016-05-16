package com.xtuapp.zsxd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xtuapp.zsxd.R;


/**
 * Author: D.K
 * Time: 2015-11-10
 * Desc:
 */
public class ScaleableImageView extends ImageView {

    private float aspectRatio; //宽高比

    public ScaleableImageView(Context context) {
        super(context);
    }

    public ScaleableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScaleableImageView, defStyle, 0);
        aspectRatio = a.getFloat(R.styleable.ScaleableImageView_aspect_ratio, -1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 若没有指定宽高比，则作为一般的ImageView
        if (aspectRatio <= 0) {
            return;
        }
        //按照指定的宽高比measure（如果宽是EXACTLY或二者都为AT_MOST，以宽为基准；否则以高为基准）
        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (widthSpecMode == MeasureSpec.EXACTLY || (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST)) {
            height = (int) (width / aspectRatio);
        } else {
            width = (int) (height * aspectRatio);
        }
        setMeasuredDimension(width, height);
    }
}
