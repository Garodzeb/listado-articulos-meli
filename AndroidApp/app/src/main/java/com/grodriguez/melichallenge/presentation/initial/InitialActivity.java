package com.grodriguez.melichallenge.presentation.initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.framework.utils.UIStatus;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.search_product.MainActivity;
import com.grodriguez.melichallenge.presentation.ui.UIState;

public class InitialActivity extends AppCompatActivity {

    InitialViewModel initialViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_initial);

            Context context = getApplicationContext();
            AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));

            ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

            initialViewModel = new ViewModelProvider(this, factory).get(InitialViewModel.class);
            initializeObservers();

            initialViewModel.getSiteMetadata();
        }
        catch (Exception ex) {
            // Finaliza la aplicación si ocurre algún error en la inicialización
            AppUtils.logError(ex);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            initialViewModel.dispose();
        }
        catch (Exception ex)
        {
            // Finaliza la aplicación si ocurre algún error inesperado
            AppUtils.logError(ex);
            finish();
        }
    }

    private void initializeObservers() {
        initialViewModel.getUIState().observe(this, state -> {
            // Si dio un error en la inicialización finaliza el Activity y no deja continuar
            if (state.getCurrentStatus() == UIStatus.UI_ON_ERROR) {
                AppUtils.logError(state.getError());
                finish();
            }
        });

        initialViewModel.getSavedMetadata().observe(this, savedMetadata -> {
            try {
                if (savedMetadata) {
                    Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
            catch (Exception ex) {
                // Finaliza la aplicación si ocurre algún error inesperado
                AppUtils.logError(ex);
                finish();
            }
        });
    }

}// End Class