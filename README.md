DoubleSeekBar
=============

##Description

This library provides an easy way to add two (or one, read more below) native SeekBars with minimum and maximum values to your Android application.

<b>Ice Cream Sandwich</b>

![Double SeekBar ICS](http://www.tinycoolthings.com/double_seekbar_ics.png)

<b>Gingerbread</b>

![Double SeekBar Gingerbread](http://www.tinycoolthings.com/double_seekbar_gingerbread.png)

If you do not want two SeekBars you can also have only one with a minimum and a maximum value.

![Double SeekBar Single ICS](http://tinycoolthings.com/double_seekbar_single_ics.png)

##Examples

### XML

####Double SeekBar

    <com.tinycoolthings.DoubleSeekbar
        xmlns:app="http://schemas.android.com/apk/res/com.tinycoolthings.hiperprecos"
        android:id="@+id/test_doubleseekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dsb_hasMaxSeekBar="true"
        app:dsb_min_value="10"
        app:dsb_max_value="20"
        app:dsb_min_title="Min value"
        app:dsb_max_title="Max value"
        app:dsb_units="%" />

####Single SeekBar

    <com.tinycoolthings.DoubleSeekbar
        android:id="@+id/test_doubleseekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dsb_hasMaxSeekBar="false"
        app:dsb_min_value="10"
        app:dsb_max_value="20"
        app:dsb_min_title="Value"
        app:dsb_units="%" />

### Methods

    public void registerOnChangeListenerMinSB(OnSeekBarChangeListener listener)
    public void registerOnChangeListenerMaxSB(OnSeekBarChangeListener listener)
    public void setMinTitle(String title)
    public void setMaxTitle(String title)
    public void setUnits(String units)
    public Integer getCurrentMinValue()
    public Integer getCurrentMaxValue()

### Notes

* For now it only supports Integers. Floats, Lists of values, etc. are on the TODO list.
* The following conditions apply:
    1. The minimum value must be defined as smaller than the maximum value.
    3. The minimum value is never higher than the maximum value.
