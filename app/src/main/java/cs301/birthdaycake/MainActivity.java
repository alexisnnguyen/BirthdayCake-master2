package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeview);
        CakeController cakeController = new CakeController(cakeView);

        Button blowButton = findViewById(R.id.buttonBlow);
        blowButton.setOnClickListener(cakeController);

        Button candleSwitch = findViewById(R.id.switchCandles);


    }
    public void goodbye(View button) {
        //System.out.println("Goodbye");
        Log.i("button","Goodbye");
        finishAffinity();
    }
}
