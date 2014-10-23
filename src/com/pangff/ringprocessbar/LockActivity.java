package com.pangff.ringprocessbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pangff.ringprocessbar.PasswordLockView.OnFinishListener;

@SuppressLint("NewApi")
public class LockActivity extends Activity {

	PasswordLockView passwordLockView;
	String codeLast;
	boolean isSetting = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock);
		passwordLockView = (PasswordLockView) findViewById(R.id.passwordLockView);

		passwordLockView.setSetting(true);//true是设置密码 //false是解密码
		passwordLockView.setOnFinishListener(new OnFinishListener() {
			@Override
			public void onFinish(String code) {
				Log.e("pangff","code:"+code);
				if(isSetting){
					if(codeLast==null){
						codeLast = code;
						Toast.makeText(LockActivity.this, "请重复绘制一遍", Toast.LENGTH_LONG).show();
					}else{
						if(codeLast.equals(code)){
							Toast.makeText(LockActivity.this, "设置成功,进行解锁", Toast.LENGTH_LONG).show();
							passwordLockView.setSetting(false);
							isSetting = false;
						}else{
							codeLast = null;
							Toast.makeText(LockActivity.this, "两次图形不一样,请重新设置", Toast.LENGTH_LONG).show();
						}
					}
				}else{
					if(codeLast.equals(code)){
						Toast.makeText(LockActivity.this, "解锁成功", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
						intent.setClass(LockActivity.this,MainActivity.class);
						LockActivity.this.startActivity(intent);
					}else{
						Toast.makeText(LockActivity.this, "密码错误", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}
}
