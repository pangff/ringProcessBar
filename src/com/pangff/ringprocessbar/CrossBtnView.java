package com.pangff.ringprocessbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CrossBtnView extends View{

	boolean isPressed;
	int color_circle = Color.parseColor("#9EC4DC");
	int color_cross = Color.parseColor("#1977B3");
	int color_pressed = Color.parseColor("#0A5785");
	
	Paint paint;
	public CrossBtnView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setAntiAlias(true);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setStrokeWidth(10);
		paint.setColor(color_circle);
		paint.setStyle(Style.STROKE);
		paint.setStyle(Style.FILL);
		canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, (canvas.getWidth()-this.getPaddingLeft()-this.getPaddingRight())/2, paint);
		if(isPressed){
			paint.setColor(color_pressed);
			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, (canvas.getWidth()-this.getPaddingLeft()-this.getPaddingRight())/2-5f, paint);
		}else{
			paint.setColor(Color.WHITE);
			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, (canvas.getWidth()-this.getPaddingLeft()-this.getPaddingRight())/2-5f, paint);
		}
		paint.setColor(color_cross);
		paint.setStrokeCap(Cap.ROUND);
		canvas.drawLine(this.getPaddingLeft()+15, canvas.getHeight()/2, canvas.getWidth()-this.getPaddingRight()-15, canvas.getHeight()/2, paint);
		canvas.drawLine(canvas.getWidth()/2, this.getPaddingTop()+15, canvas.getWidth()/2, canvas.getHeight()-this.getPaddingBottom()-15, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.isPressed = true;
			break;
		case MotionEvent.ACTION_CANCEL:
			this.isPressed = false;
			break;
		case MotionEvent.ACTION_UP:
			this.isPressed = false;
			break;
		default:
			break;
		}
		Log.e("dddddd", "event.getAction():"+event.getAction());
		invalidate();
		return true;
	}

}
