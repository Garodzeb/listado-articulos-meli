package com.grodriguez.melichallenge.repositories;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public abstract class BaseRepositoryTest {

    protected AppRoomDatabase testDatabase;
    protected AppDependenciesContainer dependenciesContainer;

    @Before
    public void createDependencies() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();

        // Crea el contenedor de dependencias a utilizar en las pruebas
        dependenciesContainer = AppDependenciesContainer.getInstance(appContext, testDatabase);
    }

    @After
    public void closeDb() {
        testDatabase.close();
    }

}//End Class
