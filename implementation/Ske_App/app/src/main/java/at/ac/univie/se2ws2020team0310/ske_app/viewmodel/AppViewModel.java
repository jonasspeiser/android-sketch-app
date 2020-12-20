package at.ac.univie.se2ws2020team0310.ske_app.viewmodel;

import android.widget.SeekBar;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import at.ac.univie.se2ws2020team0310.ske_app.BR;
import at.ac.univie.se2ws2020team0310.ske_app.R;

public class AppViewModel extends BaseObservable {

    int strokeWidthSeekBarValue;
    int sizeSeekBarValue;
    boolean strokeWidthSeekBarVisibility;
    boolean sizeSeekBarVisibility;

    @Bindable
    public int getSizeSeekBarValue(){
        return sizeSeekBarValue;
    }

    //BR class is auto-generated, holds the specific resource for the data binding
    public void setSizeSeekBarValue(int progress){
        sizeSeekBarValue = progress;
        notifyPropertyChanged(BR.sizeSeekBarValue);
        setSizeSeekBarValue(progress);
    }

    @Bindable
    public int getStrokeWidthSeekBarValue(){
        return strokeWidthSeekBarValue;
    }

    public void setStrokeWidthSeekBarValue(int progress){
        strokeWidthSeekBarValue = progress;
        notifyPropertyChanged(BR.strokeWidthSeekBarValue);
        setStrokeWidthSeekBarValue(progress);
    }

}
