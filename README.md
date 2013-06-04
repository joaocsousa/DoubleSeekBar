DoubleSeekBar
=============

##Description

This library provides an easy way to add two native SeekBars with minimum and maximum values, to your Android application.

<b>Ice Cream Sandwich</b>

![Double SeekBar ICS](http://www.tinycoolthings.com/double_seekbar_ics.png)

<b>Gingerbread</b>

![Double SeekBar Gingerbread](http://www.tinycoolthings.com/double_seekbar_gingerbread.png)

If you do not want two SeekBars you can also have only one, with also a minimum and a maximum value.

![Double SeekBar Single ICS](http://tinycoolthings.com/double_seekbar_single_ics.png)

##Examples

### XML

####Double SeekBar

    <com.tinycoolthings.DoubleSeekbar
        android:id="@+id/test_doubleseekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hasMaxSeekBar="true"
        app:min_value="10"
        app:max_value="20"
        app:min_title="Min value"
        app:max_title="Max value"
        app:units="%" />

####Single SeekBar

    <com.tinycoolthings.DoubleSeekbar
        android:id="@+id/test_doubleseekbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hasMaxSeekBar="false"
        app:min_value="10"
        app:max_value="20"
        app:min_title="Value"
        app:units="%" />

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
    1. The minimum value must be defined as smaller than the maximum value, and vice-versa.
    3. The minimum value is never higher than the maximum value, and vice-versa.
    4. Both values are never equal.
