package com.pangff.ringprocessbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DropFlower extends View {

	Paint mPaint = new Paint();

	float a1 = 1.9f;
	float a2 = 5f / 9;
	float a3 = 7f / 9;
	float r = 30;
	Path path;
	int flag = 1;
	Point point = new Point();
	private String grade = "C";
	public static final String GRADE_A = "A";
	public static final String GRADE_B = "B";
	public static final String GRADE_C = "C";
	public static final String GRADE_D = "D";
	public static final String GRADE_E = "E";
	
	int colors[] = { Color.parseColor("#F45145"), Color.parseColor("#AE92FC"),
			Color.parseColor("#23BDEC"), Color.parseColor("#77D568"),
			Color.parseColor("#FFA914") };
	int no_colors= Color.parseColor("#C6C6C6");

	
	public void setGrade(String grade){
		this.grade = grade;
		if(grade.equals(GRADE_B)){
			colors[1] = no_colors;
		}
		if(grade.equals(GRADE_C)){
			colors[1] = no_colors;
			colors[2] = no_colors;
		}
		if(grade.equals(GRADE_D) ){
			colors[1] = no_colors;
			colors[2] = no_colors;
			colors[3] = no_colors;
		}
		if(grade.equals(GRADE_E) ){
			colors[1] = no_colors;
			colors[2] = no_colors;
			colors[3] = no_colors;
			colors[4] = no_colors;
		}
	}
			
	public DropFlower(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint.setAntiAlias(true);
		mPaint.setColor(colors[0]);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		r=this.getMeasuredHeight()/6;
		point.x = this.getMeasuredWidth() / 2;
		point.y = (int) (this.getMeasuredHeight() / 2+(r-r*Math.sin(54*Math.PI/180))/2);
		mPaint.setStyle(Style.FILL);
		canvas.drawPath(getPath(point), mPaint);
		canvas.save();
		for (int i = 0; i < 4; i++) {
			mPaint.setColor(colors[i+1]);
			canvas.rotate(72, point.x, point.y + r/6);
			canvas.drawPath(getPath(point), mPaint);
		}
		canvas.restore();
	}

	private Path getPath(Point point) {
		if (path == null) {
			path = new Path();
		}
		path.moveTo(point.x, point.y);
		path.cubicTo(point.x, point.y, point.x + r, point.y
				+ (-a1 * r + a3 * r) * flag, point.x + r, point.y - flag * a1
				* r);
		path.cubicTo(point.x + r, point.y + (-a1 * r - a2 * r) * flag, point.x
				+ a2 * r, point.y - flag * (1 + a1) * r, point.x, point.y
				- flag * (1 + a1) * r);
		path.cubicTo(point.x - a2 * r, point.y - flag * (1 + a1) * r, point.x
				- r, point.y - flag * (a1 + a2) * r, point.x - r, point.y
				- flag * a1 * r);
		path.cubicTo(point.x - r, point.y + (-a1 * r + a3 * r) * flag, point.x,
				point.y, point.x, point.y);
		path.close();
		return path;
	}

}
