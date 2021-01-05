package at.ac.univie.se2ws2020team0310.ske_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SeekBar;

import at.ac.univie.se2ws2020team0310.ske_app.R;
import at.ac.univie.se2ws2020team0310.ske_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.ske_app.viewmodel.AppViewModel;

public class MainActivity extends AppCompatActivity {

    private SeekBar strokeWidthSeekBar;
    private SeekBar sizeSeekBar;
    private String example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new AppViewModel());
        activityMainBinding.executePendingBindings();

        strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        sizeSeekBar = findViewById(R.id.sizeSeekBar);

        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sizeSeekBar, int progress, boolean fromUser) {
                activityMainBinding.getViewModel().setSizeSeekBarValue(progress);
            }

            public void onStartTrackingTouch(SeekBar sizeSeekBar) {
                // TODO Auto-generated method stubs
            }

            public void onStopTrackingTouch(SeekBar sizeSeekBar) {
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
}