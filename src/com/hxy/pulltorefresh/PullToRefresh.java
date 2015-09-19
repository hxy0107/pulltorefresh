package com.hxy.pulltorefresh;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by vnut on 2015/9/19.
 */
public class PullToRefresh extends ScrollView {
    RotateAnimation animation;
    ImageView imageView;
    Context context;
    ListView listView;
    boolean once=false;

    public PullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public PullToRefresh(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollY=this.getScrollY();
                if(scrollY>0){
                    this.smoothScrollTo(0,imageView.getHeight());
                }else{
                    imageView.startAnimation(animation);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imageView.setAnimation(null);
                            imageView.setImageResource(R.drawable.zore);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    new Thread(){

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally {
                                Message msg=new Message();
                                msg.what=1;
                                handlerd.sendMessage(msg);
                            }
                        }
                    }.start();
                }return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            imageView=(ImageView)((LinearLayout)getChildAt(0)).getChildAt(0);
            animation=new RotateAnimation(0f,180f, Animation.RELATIVE_TO_SELF,
                    0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            listView=(ListView)((LinearLayout)getChildAt(0)).getChildAt(1);
            LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)listView.getLayoutParams();
            params.height=this.getHeight();
            params.width=this.getWidth();
            listView.setLayoutParams(params);
            this.scrollTo(0,imageView.getHeight());
        }
    }

    private Handler handlerd=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            switch (what){
                case 1:
                    imageView.setImageResource(R.drawable.pull);
                    PullToRefresh.this.smoothScrollTo(0,imageView.getHeight());
                    break;
                default:
                    break;
            }
        }
    };

}





















