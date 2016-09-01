package com.example.sjqcjstock.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sjqcjstock.R;
import com.example.sjqcjstock.app.ExitApplication;
import com.example.sjqcjstock.constant.Constants;
import com.example.sjqcjstock.fragment.FragmentForum;
import com.example.sjqcjstock.fragment.FragmentHome;
import com.example.sjqcjstock.fragment.FragmentInform;
import com.example.sjqcjstock.fragment.FragmentMy;
import com.example.sjqcjstock.fragment.stocks.FragmentAnalogHome;
import com.example.sjqcjstock.helper.ChangeFragmentHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 框架主体显示页面
 */
public class MainActivity extends FragmentActivity {

    private Fragment currentShowFragment;
    private FragmentHome fragmentHome;
    private FragmentForum fragmentForum;
    private FragmentAnalogHome fragmentAnalogHome;
    private FragmentInform fragmentInform;
    private FragmentMy fragmentMy;
    
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);       
        
        ExitApplication.getInstance().addActivity(this);
        
        initView();      
    }

    //初始时默认显示的界面
    private void defaultShowFragment() {
		fragmentHome = new FragmentHome();
        currentShowFragment = fragmentHome;
        ChangeFragmentHelper helper = new ChangeFragmentHelper();
        helper.setTargetFragment(fragmentHome);
        helper.setIsClearStackBack(true);
        changeFragment(helper);
	}

	private void initView() {
		RadioGroup main_tabBar = ((RadioGroup) findViewById(R.id.main_tabBar));
		manager = getSupportFragmentManager();
        fragmentTransaction = manager.beginTransaction();        
            
//        if(Constants.intentFlag.equals("1")){
//        	Constants.intentFlag = "";
//
//        	main_tabBar.check(R.id.main_forum);
//
//        	defaultShowFragment(new FragmentForum());
//
//            main_tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId) {
//               	 radioGruopClick(group, checkedId);
//                }
//            });
//
//        } else {
             
        	main_tabBar.check(R.id.main_home);

        	//默认显示首页界面
            defaultShowFragment();
            main_tabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(RadioGroup group, int checkedId) {
                	 radioGruopClick(group, checkedId);
                 }
             });
//        }
    }

    protected void radioGruopClick(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
    	switch (checkedId) {
        case R.id.main_home:           
        	if(currentShowFragment != fragmentHome){

        		if(fragmentHome == null){
        			fragmentHome = new FragmentHome();
        			getSupportFragmentManager().beginTransaction().hide(currentShowFragment).add(R.id.main_container,fragmentHome).commit();
        			currentShowFragment = fragmentHome;
				}else{
            		getSupportFragmentManager().beginTransaction().hide(currentShowFragment).show(fragmentHome).commit();
            		currentShowFragment = fragmentHome;
            	}
        	}                       	
            break;
        case R.id.main_forum:                    	
        	if(currentShowFragment != fragmentForum){
        		if(fragmentForum == null){
        			fragmentForum = new FragmentForum();
        			getSupportFragmentManager().beginTransaction().hide(currentShowFragment).add(R.id.main_container,fragmentForum).commit();
        			currentShowFragment = fragmentForum;
					// 设置为不刷新 其他地方控制刷新了
					Constants.isreferforumlist = "1";
            	}else{
            		getSupportFragmentManager().beginTransaction().hide(currentShowFragment).show(fragmentForum).commit();                        		
            		currentShowFragment = fragmentForum;
            	}                    		                       		                    		
        	}                    	
           break;
        case R.id.main_match:
        	if(currentShowFragment != fragmentAnalogHome){
        		if(fragmentAnalogHome == null){
					fragmentAnalogHome = new FragmentAnalogHome();
        			getSupportFragmentManager().beginTransaction().hide(currentShowFragment).add(R.id.main_container,fragmentAnalogHome).commit();
        			currentShowFragment = fragmentAnalogHome;
            	}else{                        		                    		                        		                     		                    			
            		getSupportFragmentManager().beginTransaction().hide(currentShowFragment).show(fragmentAnalogHome).commit();
            		currentShowFragment = fragmentAnalogHome;
            	}                    		                       		                    		
        	}               	
            break; 
            
        case R.id.main_inform:                       
        	if(currentShowFragment != fragmentInform){
        		if(fragmentInform == null){
        			fragmentInform = new FragmentInform();
        			getSupportFragmentManager().beginTransaction().hide(currentShowFragment).add(R.id.main_container,fragmentInform).commit();
        			currentShowFragment = fragmentInform;
            	}else{                        		                    		                        		                     		                    			
            		getSupportFragmentManager().beginTransaction().hide(currentShowFragment).show(fragmentInform).commit();
            		currentShowFragment = fragmentInform;
            	}                    		                       		                    		
        	}               	
            break;
            
        case R.id.main_my:                        
        	if(currentShowFragment != fragmentMy){
        		if(fragmentMy == null){
        			fragmentMy = new FragmentMy();
        			getSupportFragmentManager().beginTransaction().hide(currentShowFragment).add(R.id.main_container,fragmentMy).commit();
        			currentShowFragment = fragmentMy;
            	}else{                        		                    		                        		                     		                    			
            		getSupportFragmentManager().beginTransaction().hide(currentShowFragment).show(fragmentMy).commit();
            		currentShowFragment = fragmentMy;
            	}                    		                       		                    		
        	}                 	
            break;
    	}
	}

	public void changeFragment(ChangeFragmentHelper helper) {

        //获取需要跳转的Fragment
        Fragment targetFragment = helper.getTargetFragment();

        //获取是否清空回退栈
        boolean clearStackBack = helper.isClearStackBack();

        //获取是否加入回退栈
        String targetFragmentTag = helper.getTargetFragmentTag();

        //获取Bundle
        Bundle bundle = helper.getBundle();

        //是否给Fragment传值
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }
        
        if (targetFragment != null) {
            //将目标Fragment添加到容器中
            fragmentTransaction.replace(R.id.main_container,targetFragment);
        }

        //是否添加到回退栈
        if (targetFragmentTag != null) {
            fragmentTransaction.addToBackStack(targetFragmentTag);
        }

        //是否清空回退栈
        if(clearStackBack){
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragmentTransaction.commit();
    }

	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			exitBy2Click(); //调用双击退出函数
		}
		return false;
	}
	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			finish();
			ExitApplication.getInstance().exit();
//			System.exit(0);
		}
	}
}

