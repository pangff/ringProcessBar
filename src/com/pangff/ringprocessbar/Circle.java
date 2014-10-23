package com.pangff.ringprocessbar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Circle {

	public int cx;
	public int cy;
	public int r;
	public boolean isOnTouch;
	Canvas canvas;
	Paint paint;
	int number;
	int stockWidth;
	boolean isFinsihedState;
	public Circle(int cx,int cy,int r,int number,int stockWidth){
		this.cx = cx;
		this.cy = cy;
		this.r = r;
		this.stockWidth = stockWidth;
		this.number = number;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
	}
	
	public void setR(int r){
		this.r = r;
	}
	
	public boolean isInCircle(int x,int y){
		if(x>cx-r && x<cx+r && y>cy-r&&y<cy+r){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean setOnTouch(int x,int y){
		if(!isOnTouch){
			isOnTouch = isInCircle(x,y);
		}
		return isOnTouch;
	}
	
	public boolean isOldPoint(){
		return isOnTouch;
	}
	
	public void clearSate(){
		 isOnTouch = false;
	}
	
	public void setFinsihSettingState(boolean isFinsihedState){
		this.isFinsihedState = isFinsihedState;
	}
	
	public void doDraw(Canvas canvas){
		this.canvas = canvas;
		if(isOnTouch){
			if(isFinsihedState){
				paint.setColor(Color.RED);
			}else{
				paint.setColor(Color.WHITE);
			}
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(20);
			canvas.drawCircle(cx, cy, r, paint);
		}
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(cx, cy, r/8, paint);
	}

	public void drawLine(int cx2, int cy2, float currentX, float currentY,
			Canvas canvas2) {
		canvas.drawLine(cx2,cy2,currentX, currentY, paint);
	}
}
