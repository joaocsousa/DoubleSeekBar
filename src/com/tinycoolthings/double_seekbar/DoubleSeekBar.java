package com.tinycoolthings.double_seekbar;

import com.tinycoolthings.double_seekbar.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DoubleSeekBar extends LinearLayout {

	private boolean mHasMaxSeekBar = true;
	private Integer mMinValue = 0;
	private Integer mMaxValue = 1;
	private String mMinTitle = "";
	private String mMaxTitle = "";
	private String mUnits = "";
	
	private SeekBar mMinSeekBar = null;
	private SeekBar mMaxSeekBar = null;
	
	CompositeListener mMinChangeListeners = new CompositeListener();
	CompositeListener mMaxChangeListeners = new CompositeListener();
	
	private TextView mMinTitleTv = null;
	private TextView mMinValueTv = null;
	private TextView mMaxTitleTv = null;
	private TextView mMaxValueTv = null;
	
    public DoubleSeekBar(Context context) throws Exception {
         this(context, null);
    }
    
    public DoubleSeekBar(Context context, AttributeSet attrs) throws Exception {
        super(context, attrs);
        
        TypedArray attributesArr = context.obtainStyledAttributes(attrs, R.styleable.DoubleSeekbar);

        mHasMaxSeekBar = attributesArr.getBoolean(R.styleable.DoubleSeekbar_hasMaxSeekBar, true);
        mMinValue = attributesArr.getInteger(R.styleable.DoubleSeekbar_min_value, -1);
        mMinTitle = attributesArr.getString(R.styleable.DoubleSeekbar_min_title);
    	mMaxValue = attributesArr.getInteger(R.styleable.DoubleSeekbar_max_value, -1);
    	
    	if (mMaxValue - mMinValue < 1) {
    		throw new Exception("Minimum value - maximum value >= 1!");
    	}
    	
    	if (mHasMaxSeekBar) {
            mMaxTitle = attributesArr.getString(R.styleable.DoubleSeekbar_max_title);
        }
        mUnits = attributesArr.getString(R.styleable.DoubleSeekbar_units);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.double_seekbar, this, true);

        mMinTitleTv = (TextView) v.findViewById(R.id.double_seekbar_tv_min_title);
        mMinValueTv = (TextView) v.findViewById(R.id.double_seekbar_tv_min_value);
        
        mMaxTitleTv = (TextView) v.findViewById(R.id.double_seekbar_tv_max_title);
        mMaxValueTv = (TextView) v.findViewById(R.id.double_seekbar_tv_max_value);
        
        mMinSeekBar = (SeekBar)v.findViewById(R.id.double_seekbar_sb_min);
        mMaxSeekBar = (SeekBar)v.findViewById(R.id.double_seekbar_sb_max);
        
        updateViews();

        mMinValueTv.setText(String.valueOf(mMinValue) + " " + mUnits);
        mMinTitleTv.setText(mMinTitle == null ? "" : mMinTitle + ": ");
        
        mMinSeekBar.setMax(mMaxValue-mMinValue);
      
        mMinChangeListeners.registerListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onProgressChanged(SeekBar minSeekBar, int progress, boolean fromUser) {
				if (progress > mMaxSeekBar.getProgress()) {
					mMaxSeekBar.setProgress(mMaxSeekBar.getProgress()+1);
				}
				mMinValueTv.setText(String.valueOf(progress+mMinValue) + " " + mUnits);
				invalidate();
		    	requestLayout();
			}
		});
       
        mMinSeekBar.setOnSeekBarChangeListener(mMinChangeListeners);
        
        if (mHasMaxSeekBar) {
            mMaxValueTv.setText(String.valueOf(mMaxValue) + " " + mUnits);
            mMaxTitleTv.setText(mMaxTitle == null ? "" : mMaxTitle + ": ");
            mMaxSeekBar.setMax(mMaxValue-mMinValue);
            mMaxSeekBar.setProgress(mMaxSeekBar.getMax());
            
            mMaxChangeListeners.registerListener(new OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar maxSeekBar, int progress, boolean fromUser) {
    				if (progress < mMinSeekBar.getProgress()) {
    					mMinSeekBar.setProgress(mMinSeekBar.getProgress()-1);
    				}
    				mMaxValueTv.setText(String.valueOf(progress+mMinValue) + " " + mUnits);
    				invalidate();
    		    	requestLayout();
    			}
    		});
            
            mMaxSeekBar.setOnSeekBarChangeListener(mMaxChangeListeners);
            
        }
        
        attributesArr.recycle();
        
    }

	private void updateViews() {
		if (!mHasMaxSeekBar) {
        	mMaxSeekBar.setVisibility(View.GONE);
        	mMaxTitleTv.setVisibility(View.GONE);
        	mMaxValueTv.setVisibility(View.GONE);
        }
	}
    
	/**
	 * Sets if this DoubleSeekBar has a MaximumSeekBar or not. If this is false then only <b>one</b> SeekBar is available.
	 * Every parameter related to the MaximumSeekBar is ignored.
	 * @param hasMax - boolean related to has maximum seekbar or not.
	 */
    public void setHasMax(boolean hasMax) {
    	mHasMaxSeekBar = hasMax;
    	updateViews();
    }
	
	/**
	 * Ŕegisters a listener that listens to events on the Minimum SeekBar. You can add as many as you want.
	 * @param listener - OnSeekBarChangeListener to be added to the queue.
	 */
	public void registerOnChangeListenerMinSB(OnSeekBarChangeListener listener) {
		mMinChangeListeners.registerListener(listener);
	}
	
	/**
	 * Ŕegisters a listener that listens to events on the Maximum SeekBar. You can add as many as you want.
	 * @param listener - OnSeekBarChangeListener to be added to the queue.
	 */
	public void registerOnChangeListenerMaxSB(OnSeekBarChangeListener listener) {
		mMaxChangeListeners.registerListener(listener);
	}
    
	/**
	 * Sets the title of the current Minimum SeekBar. This is the text to the left of the value.
	 * @param title - String representing the title.
	 */
	public void setMinTitle(String title) {
		mMinTitleTv.setText(title);
	}
	
	/**
	 * Sets the title of the current Maximum SeekBar. This is the text to the left of the value.
	 * @param title - String representing the title.
	 */
	public void setMaxTitle(String title) {
		mMaxTitleTv.setText(title);
	}
	
	/**
	 * Sets the units to be displayed on the right of the current value. This string is for display only, therefore it can be
	 * anything, like <b>%</b>, <b>$</b>, <b>Pixels</b>, <b>Bananas</b>
	 * @param units - String representing the units.
	 */
	public void setUnits(String units) {
		mUnits = units;
	}
	
	/**
	 * Returns the current value of the Minimum SeekBar. If hasMax is false this is the value of the SeekBar.
	 * @return value of the Minimum SeekBar.
	 */
	public Integer getCurrentMinValue() {
		return mMinSeekBar.getProgress() + mMinValue;
	}
	
	/**
	 * If hasMax is true, returns the value of the current Maximum SeekBar.
	 * @return value of the current Maximum SeekBar. -1 if hasMax is false.
	 */
	public Integer getCurrentMaxValue() {
		if (mHasMaxSeekBar) {
			return mMaxSeekBar.getProgress() + mMinValue;
		}
		return -1;
	}
	
}
