package com.pangff.ringprocessbar;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PasswordLockView extends View{

	int number[][] = {{0,1,2},{3,4,5},{6,7,8}};
	int width;
	int height;
	int r = 40 ;
	int padding = 50;
	Paint paint;
	int stockWidth = 20;
	List<Circle> circleList = new ArrayList<Circle>();
	List<Circle> selectedPointList = new ArrayList<Circle>();
	String codes = "";
	boolean isSetting = false;
	public void intData(){
		for(int i=0;i<number.length;i++){
			for(int j=0;j<number[i].length;j++){
				Circle circle = new Circle(stockWidth+r+2*r*i+padding*i, stockWidth+r+2*r*j+padding*j, r, number[i][j],stockWidth);
				circleList.add(circle);
			}
		}
	}
	
	
	public PasswordLockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(stockWidth);
		paint.setAntiAlias(true);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public void setSetting(boolean isSetting){
		this.isSetting = isSetting;
	}
	
	boolean hasInit = false;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = canvas.getWidth()-stockWidth*2;
		height = canvas.getHeight();
		r = (width-padding*2)/8;
		if(!hasInit){
			intData();
			hasInit = true;
		}
		for(int i=0;i<circleList.size();i++){
			circleList.get(i).doDraw(canvas);
		}
		for(int i=0;i<selectedPointList.size();i++){
			if(i+1<selectedPointList.size()){
				selectedPointList.get(i).drawLine(selectedPointList.get(i).cx, selectedPointList.get(i).cy, selectedPointList.get(i+1).cx, selectedPointList.get(i+1).cy, canvas);
			}
		}
		if(selectedPointList.size()>0&&currentX!=-1){
			Circle lastPoint = selectedPointList.get(selectedPointList.size()-1);
			lastPoint.drawLine(lastPoint.cx, lastPoint.cy, currentX, currentY, canvas);
		}
	}
	
	float currentX = 0f;
	float currentY = 0f;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()!=MotionEvent.ACTION_UP){
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				codes="";
			}
			for(int i=0;i<circleList.size();i++){
				if(!circleList.get(i).isOldPoint()&&circleList.get(i).setOnTouch((int)event.getX(), (int)event.getY())){
					selectedPointList.add(circleList.get(i));
				}
			}
			currentX = event.getX();
			currentY = event.getY();
			invalidate();
		}else{
			savePoints();
		}
		return true;
	}
	
	
	private void clearSeleted(){
		for(int i=0;i<selectedPointList.size();i++){
			selectedPointList.get(i).clearSate();
			selectedPointList.get(i).setFinsihSettingState(false);
			codes+=selectedPointList.get(i).number;
		}
		selectedPointList.clear();
		invalidate();
		onFinishListener.onFinish(codes);
	}
	
	private void setFinishSettingState(){
		for(int i=0;i<selectedPointList.size();i++){
			selectedPointList.get(i).setFinsihSettingState(true);
		}
		invalidate();
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				clearSeleted();
				invalidate();
			}
		}, 2000);
	}
	
	private void savePoints(){
		currentX=-1;
		if(selectedPointList.size()>0){
			if(isSetting){
				setFinishSettingState();
			}else{
				clearSeleted();
			}
		}
	}
	
	public String getCode(){
		return codes;
	}
	
	public  interface OnFinishListener{
		public void onFinish(String code);
	}
	OnFinishListener onFinishListener;
	public void setOnFinishListener(OnFinishListener onFinishListener){
		this.onFinishListener = onFinishListener;
	}

}
