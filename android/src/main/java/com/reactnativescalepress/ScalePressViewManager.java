package com.reactnativescalepress;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaConstants;

import java.util.Map;

public class ScalePressViewManager extends ViewGroupManager<ScalePressView> {
  public static final String REACT_CLASS = "ScalePressView";

  @Override
  @NonNull
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  @NonNull
  public ScalePressView createViewInstance(ThemedReactContext reactContext) {
    return new ScalePressView(reactContext);
  }

  private static final int[] SPACING_TYPES = {
    Spacing.ALL,
    Spacing.LEFT,
    Spacing.RIGHT,
    Spacing.TOP,
    Spacing.BOTTOM,
    Spacing.START,
    Spacing.END,
  };


  @ReactProp(name = "scale")
  public void setScale(ScalePressView view, double scale) {
    view.scale = (float) scale;
  }

  @ReactProp(name = "durationIn")
  public void setDurationIn(ScalePressView view, double duration) {
    view.durationIn = (long) duration;
  }

  @ReactProp(name = "durationOut")
  public void setDurationOut(ScalePressView view, double duration) {
    view.durationOut = (long) duration;
  }

  @ReactProp(name = "longPressEnabled")
  public void setOnLongPress(ScalePressView view, boolean isEnabled) {
    view.gestureDetectorCompat.setIsLongpressEnabled(isEnabled);
  }


  @ReactPropGroup(
    names = {
      ViewProps.BORDER_RADIUS,
      ViewProps.BORDER_TOP_LEFT_RADIUS,
      ViewProps.BORDER_TOP_RIGHT_RADIUS,
      ViewProps.BORDER_BOTTOM_RIGHT_RADIUS,
      ViewProps.BORDER_BOTTOM_LEFT_RADIUS,
      ViewProps.BORDER_TOP_START_RADIUS,
      ViewProps.BORDER_TOP_END_RADIUS,
      ViewProps.BORDER_BOTTOM_START_RADIUS,
      ViewProps.BORDER_BOTTOM_END_RADIUS,
    },
    defaultFloat = YogaConstants.UNDEFINED)
  public void setBorderRadius(ScalePressView view, int index, float borderRadius) {
    if (!YogaConstants.isUndefined(borderRadius) && borderRadius < 0) {
      borderRadius = YogaConstants.UNDEFINED;
    }

    if (!YogaConstants.isUndefined(borderRadius)) {
      borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
    }

    if (index == 0) {
      view.setBorderRadius(borderRadius);
    } else {
      view.setBorderRadius(borderRadius, index - 1);
    }
  }

  @ReactProp(name = "borderStyle")
  public void setBorderStyle(ScalePressView view, @Nullable String borderStyle) {
    view.setBorderStyle(borderStyle);
  }

  @ReactPropGroup(
    names = {
      ViewProps.BORDER_WIDTH,
      ViewProps.BORDER_LEFT_WIDTH,
      ViewProps.BORDER_RIGHT_WIDTH,
      ViewProps.BORDER_TOP_WIDTH,
      ViewProps.BORDER_BOTTOM_WIDTH,
      ViewProps.BORDER_START_WIDTH,
      ViewProps.BORDER_END_WIDTH,
    },
    defaultFloat = YogaConstants.UNDEFINED)
  public void setBorderWidth(ScalePressView view, int index, float width) {
    if (!YogaConstants.isUndefined(width) && width < 0) {
      width = YogaConstants.UNDEFINED;
    }

    if (!YogaConstants.isUndefined(width)) {
      width = PixelUtil.toPixelFromDIP(width);
    }

    view.setBorderWidth(SPACING_TYPES[index], width);
  }

  @ReactPropGroup(
    names = {
      ViewProps.BORDER_COLOR,
      ViewProps.BORDER_LEFT_COLOR,
      ViewProps.BORDER_RIGHT_COLOR,
      ViewProps.BORDER_TOP_COLOR,
      ViewProps.BORDER_BOTTOM_COLOR,
      ViewProps.BORDER_START_COLOR,
      ViewProps.BORDER_END_COLOR
    },
    customType = "Color")
  public void setBorderColor(ScalePressView view, int index, Integer color) {
    float rgbComponent =
      color == null ? YogaConstants.UNDEFINED : (float) ((int) color & 0x00FFFFFF);
    float alphaComponent = color == null ? YogaConstants.UNDEFINED : (float) ((int) color >>> 24);
    view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
  }

  @ReactProp(name = ViewProps.OVERFLOW)
  public void setOverflow(ScalePressView view, String overflow) {
    view.setOverflow(overflow);
  }


  @Nullable
  @Override
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.<String, Object>builder()
      .put("onPress", MapBuilder.of("registrationName", "onPress"))
      .put("onLongPress", MapBuilder.of("registrationName", "onLongPress"))
      .build();
  }
}
