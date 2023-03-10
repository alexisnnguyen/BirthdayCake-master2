package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint red = new Paint();
    Paint green = new Paint();
    Paint coords = new Paint();

    Paint balloonPaint = new Paint();

    Paint stringPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 200.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cake = new CakeModel();



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(Color.BLUE);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        red.setColor(Color.RED);
        green.setColor(Color.GREEN);
        balloonPaint.setColor(Color.BLUE);
        stringPaint.setColor(Color.BLACK);

        setBackgroundColor(Color.WHITE);  //better than black default

        //coord drawing
        coords.setTextSize(50.0f);
        coords.setColor(Color.RED);

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {

        if (cake.hasCandle == true) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

            if (cake.litCandle == true) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

        }
    }

    public void drawBalloon(Canvas canvas) {
        if (cake.hasTouched) {
            canvas.drawOval(cake.balloonX - 25, cake.balloonY - 40,cake.balloonX + 25 , cake.balloonY + 40, balloonPaint);
            canvas.drawLine(cake.balloonX, cake.balloonY + 40, cake.balloonX, cake.balloonY + 100, stringPaint);

        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        for (int i = 1; i <= cake.numCandle; i++){
            drawCandle(canvas, cakeLeft + cakeWidth * i/(cake.numCandle+1) - candleWidth / 2, cakeTop);
        }

        drawBalloon(canvas);
        //Now a candle
        //drawCandle(canvas, cakeLeft + cakeWidth / 3 - candleWidth / 2, cakeTop);

        //Draw second candle
        //drawCandle(canvas, cakeLeft + cakeWidth * 2 / 3 - candleWidth / 2, cakeTop);

        if(cake.hasTouched == true){

            //canvas.drawRect(cake.x-10,cake.y+10, cake.y, cake.x, green);
            //canvas.drawRect(cake.x , cake.y, cake.x + 10, cake.y+10, green);
            //canvas.drawRect(cake.x, cake.y + 10, cake.x+10, cake.y, red);
            //canvas.drawRect(cake.x - 10, cake.y, cake.x, cake.y+107, red);

            canvas.drawRect(cake.balloonX-20, cake.balloonY-20, cake.balloonX+20, cake.balloonY+20, green);

            // top right red
            canvas.drawRect(cake.balloonX, cake.balloonY-20,cake.balloonX+20,cake.balloonY, red);

            //bottom right red
            canvas.drawRect(cake.balloonX-20, cake.balloonY, cake.balloonX, cake.balloonY+20, red);
            canvas.drawText(cake.balloonX + ", " + cake.balloonY, 1600f, 700f, coords);
        }



    }//onDraw



    public CakeModel getCake() {
        return cake;
    }



}//class CakeView

