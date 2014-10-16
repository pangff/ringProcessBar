package com.pangff.ringprocessbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	DropFlower dropFlower;
	ViewGroup rootView;
	AttBtnView attBtnView;
	ProgressBar progressBar2;
	ProgressBar progressBar;
	ListNameIconView listNameIconView1;
	ListNameIconView listNameIconView2;
	ListNameIconView listNameIconView3;
	ListNameIconView listNameIconView4;
	TextView percentT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dropFlower = (DropFlower) findViewById(R.id.dropFlower);
		dropFlower.setGrade(DropFlower.GRADE_A);
		rootView = (ViewGroup) findViewById(R.id.rootView);
		attBtnView = (AttBtnView) findViewById(R.id.attBtnView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
		listNameIconView1 = (ListNameIconView) findViewById(R.id.listNameIconView1);
		listNameIconView2 = (ListNameIconView) findViewById(R.id.listNameIconView2);
		listNameIconView3 = (ListNameIconView) findViewById(R.id.listNameIconView3);
		listNameIconView4 = (ListNameIconView) findViewById(R.id.listNameIconView4);
		percentT = (TextView) findViewById(R.id.percentT);
		
		listNameIconView1.setIconState("两个图标全部显示的超长度限制文字=============================两个图标全部显示的超长度限制文字", true, true);
		listNameIconView2.setIconState("两个图标显示不超", true, true);
		listNameIconView3.setIconState("一个图标显示的超长度限制文字=============================一个图标显示的超长度限制文字", true, false);
		listNameIconView4.setIconState("一个个图标显示不超", true, false);
		attBtnView.setAttState(false);
		
		
		
		attBtnView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				attBtnView.setOnRequest();
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						if(attBtnView.getState()){
							attBtnView.setAttState(false);
						}else{
							attBtnView.setAttState(true);
						}
					}
				}, 2000);
			}
		});
		setProcess(90);
	}
	
	public void setProcess(int process){
		progressBar.setProgress(0);
		progressBar2.setProgress(0);
		processHandler.post(new ProcessRunnable(process));
	}
	
	public Handler processHandler = new  Handler();
	
	public class ProcessRunnable implements Runnable{
		int process;
		int currentProcess= 0 ;
		public ProcessRunnable(int process){
			this.process = process;
		}
		public void run() {
			if(currentProcess<process){
				currentProcess++;
				processHandler.postDelayed(this, 20);
			}
			percentT.setText(currentProcess+"%");
			progressBar.setProgress(currentProcess);
			progressBar2.setProgress(currentProcess);
		}
	}

}
