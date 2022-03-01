import {
  Platform,
  requireNativeComponent,
  TouchableOpacityProps,
  UIManager,
  View,
} from 'react-native';
import React, { forwardRef, memo } from 'react';

const LINKING_ERROR =
  `The package 'react-native-scale-press' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

type Props = Pick<
  TouchableOpacityProps,
  'accessibilityLabel' | 'style' | 'onPress' | 'onLongPress'
> & {
  scale?: number;
  durationIn?: number;
  durationOut?: number;
  children?: any;
};

const ComponentName = 'ScalePressView';

const ScalePressViewManager =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<Props>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const SKIP_SCALE_PRESS = 'skipScalePress';

export const ScalePressView = memo(
  forwardRef<View, Props>((props, ref) => {
    return (
      <ScalePressViewManager
        //@ts-ignore
        ref={ref}
        onLongPress={props.onLongPress}
        scale={props.scale}
        durationIn={props.durationIn}
        durationOut={props.durationOut}
        accessibilityLabel={props.accessibilityLabel}
        style={props.style}
        onPress={props.onPress}
        children={props.children}
      />
    );
  })
);
