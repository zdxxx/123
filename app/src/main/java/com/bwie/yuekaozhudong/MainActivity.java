package com.bwie.yuekaozhudong;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bwie.yuekaozhudong.Adapter.ViewPagerAdapter;
import com.bwie.yuekaozhudong.Fragment.AFragment;
import com.bwie.yuekaozhudong.Fragment.BFragment;
import com.bwie.yuekaozhudong.Fragment.CFragment;
import com.bwie.yuekaozhudong.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewpager;
    private DrawerLayout drawer;
    private List<Fragment> frags;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioGroup radio;
    private TextView text1;
    private TextView text2;
    private TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //initViewpager
        initViewpager();
        //设置Viewpager监听事件
        initViewpagerListener();
        //让Viewpager默认显示第一页
        viewpager.setCurrentItem(0);
        radio1.setBackgroundColor(Color.RED);
    }

    private void initViewpagerListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radio.check(radio.getChildAt(i%frags.size()).getId());
                //设置RadioButton选中状态
                if(radio1.isChecked()){
                    radio1.setBackgroundColor(Color.RED);
                }else {
                    radio1.setBackgroundColor(Color.WHITE);
                }
                if(radio2.isChecked()){
                    radio2.setBackgroundColor(Color.RED);
                }else {
                    radio2.setBackgroundColor(Color.WHITE);
                }
                if(radio3.isChecked()){
                    radio3.setBackgroundColor(Color.RED);
                }else {
                    radio3.setBackgroundColor(Color.WHITE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initViewpager() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), frags);
        viewpager.setAdapter(pagerAdapter);
    }

    private void initData() {
        frags = new ArrayList<>();
        frags.add(new AFragment());
        frags.add(new BFragment());
        frags.add(new CFragment());
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        radio = (RadioGroup) findViewById(R.id.radio);
        text1 = findViewById(R.id.draw_text1);
        text2 = findViewById(R.id.draw_text2);
        text3 = findViewById(R.id.draw_text3);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio1.setOnClickListener(this);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio2.setOnClickListener(this);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radio1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.radio2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.radio3:
                viewpager.setCurrentItem(2);
                break;
            case R.id.draw_text1:
                viewpager.setCurrentItem(0);
                drawer.closeDrawers();
                break;
            case R.id.draw_text2:
                viewpager.setCurrentItem(1);
                drawer.closeDrawers();
                break;
            case R.id.draw_text3:
                viewpager.setCurrentItem(2);
                drawer.closeDrawers();
                break;

        }
    }
}
