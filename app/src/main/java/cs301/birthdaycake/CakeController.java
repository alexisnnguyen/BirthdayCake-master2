package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnTouchListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private CakeView initCakeView;
    private CakeModel initCakeModel;

    float x,y;

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


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        initCakeModel.touch = true;
        initCakeModel.x = event.getX();
        initCakeModel.y = event.getY();
        initCakeView.invalidate();
        return false;
    }
}
