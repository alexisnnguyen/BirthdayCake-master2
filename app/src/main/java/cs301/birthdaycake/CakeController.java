package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnTouchListener {

    private CakeView initCakeView;
    private CakeModel initCakeModel;

    public CakeController(CakeView cakeView) {
        initCakeView = cakeView;
        initCakeModel = cakeView.getCake();
    }

    public void onClick(View v){
        Log.d("button","Blow Out");
        initCakeModel.litCandle = false;
        initCakeView.invalidate();
    }
    public void onCheckedChanged(CompoundButton v, boolean isChecked){
        Log.d("button","Candles");
        if(initCakeModel.hasCandle == true) {
            initCakeModel.hasCandle = false;
        } else {
            initCakeModel.hasCandle = true;
        }
        initCakeView.invalidate();
    }
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("button","Seek");
        initCakeModel.numCandle = i;
        initCakeView.invalidate();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    // Person B
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("balloonTouch", "Balloon");

            initCakeModel.balloonX = event.getX();
            initCakeModel.balloonY = event.getY();
            initCakeModel.hasTouched = true;
            initCakeView.invalidate();

            return false;
    }


}
