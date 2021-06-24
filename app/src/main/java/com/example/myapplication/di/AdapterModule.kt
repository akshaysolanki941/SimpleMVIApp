package com.example.myapplication.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.RVAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {

//    @ActivityScoped
//    @Provides
//    fun provideRecyclerViewAdapter(@ActivityContext context: Context): RVAdapter {
//        return RVAdapter(context)
//    }

    @ActivityScoped
    @Provides
    fun provideLinearLayoutManager(@ActivityContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }
}