package com.dcl.myclock;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class MyClock extends View {

	private Paint paint;
	private int mRadius;
	private Paint linepaint;
	private Paint textPaint;
	private int top;
	private int startDegrees = 12;
	private int mCenter;
	private Paint paint2;
	private int c;
	private Paint recPaint;

	public MyClock(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(60);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.WHITE);
		linepaint = new Paint();
		linepaint.setAntiAlias(true);
		linepaint.setStrokeWidth(1);
		linepaint.setStyle(Paint.Style.STROKE);
		linepaint.setColor(0xffdddddd);
		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setColor(0xff64646f);
		textPaint.setTextSize(30);
		textPaint.setTextAlign(Paint.Align.CENTER);
		paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setStrokeWidth(5);
		paint2.setStyle(Paint.Style.STROKE);
		recPaint = new Paint();
		recPaint.setAntiAlias(true);
		recPaint.setStrokeWidth(1);
		recPaint.setStyle(Paint.Style.STROKE);
		recPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Resources resources = this.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		float density1 = dm.density;
		int height3 = dm.heightPixels;
		int width3 = dm.widthPixels;
		mCenter = width3 / 2;
		mRadius = width3 / 2 - 100;
		RectF arcRectf = new RectF(mCenter - mRadius, mCenter - mRadius,
				mCenter + mRadius, mCenter + mRadius);
		canvas.drawArc(arcRectf, 0, 360, false, paint);
		canvas.drawCircle(mCenter, mCenter, 3, paint);

		for (int i = 0; i < 60; i++) {
			top = mCenter - mRadius + 60;
			if (i % 5 == 0) {
				top = top + 25;
			}
			canvas.drawLine(mCenter, mCenter - mRadius + 30, mCenter, top,
					linepaint);
			canvas.rotate(6, mCenter, mCenter);
		}
		int x = mRadius - 60 / 2 - 45;
		// 斜边
		c = mRadius - 60 / 2 - 45;
		Log.e("CXX", "x:" + x);
		x = (int) c / 2;
		Log.e("CXX", "x:" + x + "mcenter" + mCenter);
		canvas.drawText(startDegrees + "", mCenter, mCenter - c + 20, textPaint);
		canvas.drawText((startDegrees - 11) + "", mCenter + x,
				(float) (mCenter - Math.sqrt(3) * c / 2) + 20, textPaint);
		canvas.drawText((startDegrees - 10) + "",
				(float) (mCenter + Math.sqrt(3) * c / 2) - 20,
				mCenter - x + 10, textPaint);
		canvas.drawText((startDegrees - 9) + "", mCenter + c - 20,
				mCenter + 10, textPaint);
		canvas.drawText((startDegrees - 8) + "",
				(float) (mCenter + Math.sqrt(3) * c / 2), mCenter + x,
				textPaint);
		canvas.drawText((startDegrees - 7) + "", mCenter + x,
				(float) (mCenter + Math.sqrt(3) * c / 2), textPaint);
		canvas.drawText((startDegrees - 6) + "", mCenter, mCenter + c - 20,
				textPaint);
		canvas.drawText((startDegrees - 5) + "", mCenter - x,
				(float) (mCenter + Math.sqrt(3) * c / 2), textPaint);
		canvas.drawText((startDegrees - 4) + "",
				(float) (mCenter - Math.sqrt(3) * c / 2), mCenter + x,
				textPaint);
		canvas.drawText((startDegrees - 3) + "", mCenter - c + 20,
				mCenter + 10, textPaint);
		canvas.drawText((startDegrees - 2) + "",
				(float) (mCenter - Math.sqrt(3) * c / 2) + 20,
				mCenter - x + 10, textPaint);
		canvas.drawText((startDegrees - 1) + "", mCenter - x,
				(float) (mCenter - Math.sqrt(3) * c / 2) + 20, textPaint);
		drawHand(canvas);
		
	}

	private void drawHand(Canvas canvas) {
		int x = mCenter;
		int y = x;
		int hour;
		int minute;
		int second;
		
		final Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);

		float h = ((hour + (float) minute / 60) / 12) * 360;
		float m = ((minute + (float) second / 60) / 60) * 360;
		float s = ((float) second / 60) * 360;

		// 时针
		paint2.setColor(Color.WHITE);
		canvas.save(); // 线锁定画布
		canvas.rotate(h, mCenter, mCenter); // 旋转画布
		Path path1 = new Path();
		path1.moveTo(mCenter,mCenter); // 开始的基点
		path1.lineTo(mCenter, mCenter-200); // 最后的基点
		canvas.drawPath(path1, paint2);
		canvas.restore();

		// 分针
		paint2.setColor(Color.BLACK);
		canvas.save();
		canvas.rotate(m, mCenter, mCenter); // 旋转画布
		Path path2 = new Path();
		path2.moveTo(mCenter, mCenter); // 开始的基点
		path2.lineTo(mCenter, mCenter-250); // 最后的基点
		canvas.drawPath(path2, paint2);
		canvas.restore();

		// 秒针
		paint2.setColor(Color.RED);
		canvas.save();
		canvas.rotate(s, mCenter, mCenter); // 旋转画布
		Path path3 = new Path();
		path3.moveTo(mCenter, mCenter); // 开始的基点
		path3.lineTo(mCenter,mCenter-300); // 最后的基点
		canvas.drawPath(path3, paint2);
		canvas.restore();
		
		int show = (int)s/6;
		canvas.drawRect(mCenter+c/2-30, mCenter-40, mCenter+c/2+30, mCenter+20, recPaint);
		canvas.drawText(""+show, mCenter+c/2, mCenter, textPaint);
	}

}
