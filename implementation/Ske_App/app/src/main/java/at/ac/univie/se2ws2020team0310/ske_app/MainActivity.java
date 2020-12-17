package at.ac.univie.se2ws2020team0310.ske_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import at.ac.univie.se2ws2020team0310.ske_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.ske_app.viewmodel.AppViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setViewModel(new AppViewModel());
        activityMainBinding.executePendingBindings();
    }
}