package com.reactnativescalepress;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.core.view.GestureDetectorCompat;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

public class ScalePressView extends ReactViewGroup {
  float scale = 0.95f;
  long durationIn = 150L;
  long durationOut = 150L;

  GestureDetectorCompat gestureDetectorCompat;

  public ScalePressView(Context context) {
    super(context);

    gestureDetectorCompat  = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
      @Override
      public void onLongPress(MotionEvent e) {
        super.onLongPress(e);
        scaleToNormal();
        eventEmitter().receiveEvent(getId(), "onLongPress", Arguments.createMap());
      }

      @Override
      public boolean onSingleTapConfirmed(MotionEvent e) {
        scaleToNormal();
        return super.onSingleTapConfirmed(e);
      }

      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        scaleToNormal();
        eventEmitter().receiveEvent(getId(), "onPress", Arguments.createMap());
        return super.onSingleTapUp(e);
      }

      @Override
      public void onShowPress(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) startPress();
        super.onShowPress(e);
      }
    });
  }

  private RCTEventEmitter eventEmitter() {
    return ((ReactContext)getContext()).getJSModule(RCTEventEmitter.class);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    if (gestureDetectorCompat.onTouchEvent(ev)) {
      return true;
    }
    scaleToNormal();
    return super.onTouchEvent(ev);
  }

  private void startPress() {
    animate()
      .scaleX(scale)
      .scaleY(scale)
      .setDuration(durationIn)
      .start();
  }

  private void scaleToNormal() {
    animate()
      .scaleX(1f)
      .scaleY(1f)
      .setDuration(durationOut)
      .start();
  }
}
