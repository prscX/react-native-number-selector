
package px.numberselector;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.graphics.Typeface;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.text.ReactFontManager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.shawnlin.numberpicker.NumberPicker;

public class RNNumberSelector extends ViewGroupManager<ViewGroup> {

    public static final String REACT_CLASS = "RNNumberSelector";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected FrameLayout createViewInstance(final ThemedReactContext reactContext) {

        final FrameLayout frameLayout = new FrameLayout(reactContext.getCurrentActivity());
        NumberPicker numberPicker = new NumberPicker(reactContext.getCurrentActivity());
        frameLayout.addView(numberPicker);

        numberPicker.setOrientation(LinearLayout.HORIZONTAL);

        // Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

        // Set scroller enabled
        numberPicker.setScrollerEnabled(true);

        // Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);
        // Set wrap selector wheel

        // OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "Click on current value");
            }
        });

        // OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int id = frameLayout.getId();

                reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()
                        .dispatchEvent(new NumberSelectorEvent(id, newVal));
            }
        });

        return frameLayout;
    }

    @ReactProp(name = "maxValue")
    public void setmaxValue(FrameLayout numberPickerFrame, int maxValue) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setMaxValue(maxValue);

    }

    @ReactProp(name = "minValue")
    public void setMinValue(FrameLayout numberPickerFrame, int minValue) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setMinValue(minValue);

    }

    @ReactProp(name = "value")
    public void setSelectedItem(FrameLayout numberPickerFrame, int value) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);
        numberPicker.setValue(value);
    }

    @ReactProp(name = "textColor")
    public void setTextColor(FrameLayout numberPickerFrame, String textColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setTextColor(Color.parseColor(textColor));
    }

    @ReactProp(name = "highlightedTextColor")
    public void setHighlightedTextColor(FrameLayout numberPickerFrame, String highlightedTextColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setSelectedTextColor(Color.parseColor(highlightedTextColor));
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(FrameLayout numberPickerFrame, float fontSize) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setTextSize(fontSize);
    }

    @ReactProp(name = "highlightedFontSize")
    public void setHighlightedFontSize(FrameLayout numberPickerFrame, float highlightedFontSize) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setSelectedTextSize(highlightedFontSize);
    }

    @ReactProp(name = "dividerColor")
    public void setDividerColor(FrameLayout numberPickerFrame, String dividerColor) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);

        numberPicker.setDividerColor(Color.parseColor(dividerColor));
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(FrameLayout numberPickerFrame, String fontFamily) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);
        Typeface typeface = ReactFontManager.getInstance().getTypeface(fontFamily, Typeface.NORMAL,
                numberPicker.getContext().getAssets());
        numberPicker.setTypeface(typeface);
    }

    @ReactProp(name = "dividerThickness")
    public void setDividerThickness(FrameLayout numberPickerFrame, int dividerThickness) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);
        numberPicker.setDividerThickness(dividerThickness);
    }

    @ReactProp(name = "wheelItemCount")
    public void setWheelItemCount(FrameLayout numberPickerFrame, int wheelItemCount) {
        NumberPicker numberPicker = (NumberPicker) numberPickerFrame.getChildAt(0);
        numberPicker.setWheelItemCount(wheelItemCount);
    }
}