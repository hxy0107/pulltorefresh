package com.hxy.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by vnut on 2015/9/19.
 */
public class CListView extends ScrollView {
    private Context context;
    public CListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinearLayout ll=new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        addView(ll);
        for(int i=0;i<40;i++){
            TextView tv=new TextView(context);
            tv.setText("textview"+i);
            ll.addView(tv);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
