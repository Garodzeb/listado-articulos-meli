package com.grodriguez.melichallenge.presentation.initial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.presentation.search_product.MainActivity;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);

        }, 2000);
    }

}// End Class