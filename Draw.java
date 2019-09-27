package com.example.lotta.linearregression;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Draw extends AppCompatActivity {

    ImageCanvas can;
    int height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        Bundle b = getIntent().getExtras();

        double cx = b.getDouble("cx");
        double cy = b.getDouble("cy");
        int color = b.getInt("color");
        int intensity = (int)Math.pow(2, (double)(b.getInt("intensity")));

        height = Resources.getSystem().getDisplayMetrics().heightPixels-500;
        width = Resources.getSystem().getDisplayMetrics().widthPixels-50;

        Bitmap map = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int n = getIterations(x, y, cx, cy);
                switch (color) {
                    case 0:
                        map.setPixel(x, y, Color.rgb((n%(256/intensity))*intensity, 0, 0));
                        break;
                    case 1:
                        map.setPixel(x, y, Color.rgb(0, (n%(256/intensity))*intensity, 0));
                        break;

                    case 2:
                        map.setPixel(x, y, Color.rgb(0, 0, (n%(256/intensity))*intensity));
                        break;
                }
            }
        }

        can = new ImageCanvas(this, map, cx, cy, (int)b.getInt("intensity"));
        setContentView(can);


    }


    int maxN = 256;
    private int getIterations(int x, int y, double cx, double cy)
    {
        int n = 0;
        double normX = (((double)x - width/2) / width) * 3;
        double normY = (((double)y - height/2) / height) * 2;

        for (; n < maxN && normX*normX+normY*normY < 4; n++)
        {
            double tempX = normX * normX - normY * normY + cx;
            normY = 2 * normX * normY + cy;
            normX = tempX;
        }


        return n;
    }


}
