package at.ac.univie.se2ws2020team0310.ske_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.SeekBar;

import at.ac.univie.se2ws2020team0310.ske_app.R;
import at.ac.univie.se2ws2020team0310.ske_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.ske_app.viewmodel.AppViewModel;

public class MainActivity extends AppCompatActivity {

    private SeekBar strokeWidthSeekBar;
    private SeekBar sizeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new AppViewModel());
        activityMainBinding.executePendingBindings();

        strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        sizeSeekBar = findViewById(R.id.sizeSeekBar);

    }
}