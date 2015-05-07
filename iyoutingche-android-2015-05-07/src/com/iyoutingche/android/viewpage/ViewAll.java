package com.iyoutingche.android.viewpage;

import java.util.ArrayList;  
import java.util.List;  

import com.iyoutingche.android.activity.LoginActivity;
import com.zhy.weixin6.ui.R;
  

import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.support.v4.view.ViewPager;  
import android.support.v4.view.ViewPager.OnPageChangeListener;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;
import android.widget.ImageView;  
import android.widget.LinearLayout;  
  
public class ViewAll extends Activity implements OnClickListener, OnPageChangeListener{  
      
    private ViewPager vp;  
    private ViewPagerAdapter vpAdapter;  
    private List<View> views;  
    private Button button;
      
    //引导图片资源  
    private static final int[] pics = { R.drawable.img2,  
            R.drawable.img3, R.drawable.img1,  
           /* R.drawable.img4 */};  
      
    //底部小店图片  
    private ImageView[] dots ;  
      
    //记录当前选中位置  
    private int currentIndex;  
      
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_view);  
          
        views = new ArrayList<View>();  
         
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,  
                LinearLayout.LayoutParams.FILL_PARENT);  
          
        //初始化引导图片列表  
        for(int i=0; i<pics.length; i++) {  
            ImageView iv = new ImageView(this);  
            iv.setLayoutParams(mParams);  
            iv.setImageResource(pics[i]);  
            views.add(iv);  
        }  
        
        //跳转按钮
        button = (Button)findViewById(R.id.button);
    	button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ViewAll.this.startActivity(new Intent().setClass(ViewAll.this, LoginActivity.class));
				ViewAll.this.finish();
			}
		});
        
        vp = (ViewPager) findViewById(R.id.viewpager);  
        //初始化Adapter  
        vpAdapter = new ViewPagerAdapter(views);  
        vp.setAdapter(vpAdapter);  
        //绑定回调  
        vp.setOnPageChangeListener(this);  
        vp.setPageTransformer(true, new DepthPageTransformer());//添加切换动画
        //初始化底部小点  
        initDots();  	
          
    }  
      
    private void initDots() {  
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);  
  
        dots = new ImageView[pics.length];  
  
        //循环取得小点图片  
        for (int i = 0; i < pics.length; i++) {  
            dots[i] = (ImageView) ll.getChildAt(i);  
            dots[i].setEnabled(true);//都设为灰色  
            dots[i].setOnClickListener(this);  
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应  
        }  
  
        currentIndex = 0;  
        dots[currentIndex].setEnabled(false);//设置为白色，即选中状态  
    }  
      
    /** 
     *设置当前的引导页  
     */  
    private void setCurView(int position)  
    {  
        if (position < 0 || position >= pics.length) {  
            return ;
        }  
  
        vp.setCurrentItem(position);  
    }  
  
    /** 
     *这只当前引导小点的选中  
     */  
    private void setCurDot(int positon)  
    {  
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {  
            return;  
        }  
  
        //当滑动到最后一张图片时，将隐藏的Button显示出来
        if(positon == pics.length - 1){
        	button.setVisibility(1);
        }else{
        	button.setVisibility(View.GONE);
        }
        
        //绘制底部的小圆点
        dots[positon].setEnabled(false);  
        dots[currentIndex].setEnabled(true);  
  
        currentIndex = positon;  
        
    }  
  
    //当滑动状态改变时调用  
    @Override  
    public void onPageScrollStateChanged(int arg0) {  
          
    }  
  
    //当当前页面被滑动时调用  
    @Override  
    public void onPageScrolled(int arg0, float arg1, int arg2) {  
          
    }  
  
    //当新的页面被选中时调用  
    @Override  
    public void onPageSelected(int arg0) {  
        //设置底部小点选中状态  
        setCurDot(arg0); 
        	
    }  
  
    @Override  
    public void onClick(View v) {  
        int position = (Integer)v.getTag();  
        setCurView(position);  
        setCurDot(position);  
    }  
}  