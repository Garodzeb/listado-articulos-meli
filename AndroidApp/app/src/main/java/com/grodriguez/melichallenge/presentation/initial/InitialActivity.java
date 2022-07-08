package com.grodriguez.melichallenge.presentation.initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.presentation.search_product.MainActivity;

public class InitialActivity extends AppCompatActivity {

    public static final int MIN_WAIT_UI = 1000;

    InitialViewModel initialViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        Context context = getApplicationContext();
        AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));

        initialViewModel = new InitialViewModel(appDependencies.getSiteRepository());

        initializeObservers();

        initialViewModel.getSiteMetadata();
    }

    @Override
    protected void onPause() {
        super.onPause();

        initialViewModel.dispose();
    }

    private void initializeObservers() {
        initialViewModel.getSavedMetadata().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean savedMetadata) {
                if (savedMetadata) {
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }, MIN_WAIT_UI);
                }
            }
        });
    }

}// End Class