package cs301.birthdaycake;

import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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


}
