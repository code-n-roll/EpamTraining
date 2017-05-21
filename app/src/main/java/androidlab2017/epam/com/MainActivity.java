package androidlab2017.epam.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import androidlab2017.epam.com.projecttask6.R;

/**
 * Created by roman on 21.5.17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button_start_service);
        button.setOnClickListener((ignored)->clickOnStartServiceButton());
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, SleepService.class));
        super.onDestroy();
    }

    private void clickOnStartServiceButton(){
        startService(new Intent(this, SleepService.class));
        finish();
    }
}
