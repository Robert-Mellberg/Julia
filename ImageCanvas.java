package com.example.lotta.linearregression;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ImageCanvas extends View {

    Bitmap map;
    double cx, cy, ci;
    public ImageCanvas(Context context, Bitmap map, double cx, double cy, double ci){
        super(context);
        this.map = map;
        this.cx = cx;
        this.cy = cy;
        this.ci = ci;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLACK);

        canvas.drawBitmap(map, 0, 0, null);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(45);

        if(cy > 0) {
            canvas.drawText("C = " + cx + " + " + cy + "i", 0, map.getHeight() + 100, paint);
        }
        else{
            canvas.drawText("C = " + cx + " - " + Math.abs(cy) + "i", 0, map.getHeight() + 100, paint);
        }
        canvas.drawText("Color intensity = " + ci, 0, map.getHeight() + 200, paint);


    }
}
