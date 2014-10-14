package com.pangff.ringprocessbar;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	DropFlower dropFlower;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dropFlower = (DropFlower) findViewById(R.id.dropFlower);
		dropFlower.setGrade(DropFlower.GRADE_E);
	}
}
