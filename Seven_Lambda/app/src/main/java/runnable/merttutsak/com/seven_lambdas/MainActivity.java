package runnable.merttutsak.com.seven_lambdas;

import android.graphics.LinearGradient;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitButton = findViewById(R.id.submit_button);

        /* NORMAL
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(TAG, "Hello Normal!", Snackbar.LENGTH_LONG).show();
            }
        });
        */

        // LAMBDA
        submitButton.setOnClickListener(view -> {
            Snackbar.make(view, "Hello Lambda!", Snackbar.LENGTH_LONG).show();
            Log.d(TAG,"Hello Lambda!");
        });
    }
}
