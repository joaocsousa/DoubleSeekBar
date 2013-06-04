package com.tinycoolthings.double_seekbar;


import java.util.ArrayList;
import java.util.List;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class CompositeListener implements OnSeekBarChangeListener {
	
	private List<OnSeekBarChangeListener> registeredListeners = new ArrayList<OnSeekBarChangeListener>();
	
	public CompositeListener() {}
	
	public void registerListener(OnSeekBarChangeListener listener) {
		registeredListeners.add(listener);
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		for (OnSeekBarChangeListener listener : registeredListeners) {
            listener.onProgressChanged(seekBar, progress, fromUser);
        }
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		for (OnSeekBarChangeListener listener : registeredListeners) {
            listener.onStartTrackingTouch(seekBar);
        }
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		for (OnSeekBarChangeListener listener : registeredListeners) {
            listener.onStopTrackingTouch(seekBar);
        }
	}
}
