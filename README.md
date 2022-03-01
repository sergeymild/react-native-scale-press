# react-native-scale-press

React Native Scale Press animation implementation

## Installation

```sh
#package.json
"react-native-popup-menu": "sergeymild/react-native-popup-menu"
```

## Usage

```js
import { ScalePressView, SKIP_SCALE_PRESS } from 'react-native-scale-press';

// ...

type Props = Pick<
  TouchableOpacityProps,
'accessibilityLabel' | 'style' | 'onPress' | 'onLongPress'
> & {
  scale?: number;
  durationIn?: number;
  durationOut?: number;
  children?: any;
};

<ScalePressView onPress={() => {}} style={styles.box}>
  <Text>Press</Text>
  // scale press will be ignored on that view
  <TouchableOpacity accessibilityLabel={SKIP_SCALE_PRESS}/>
</ScalePressView>
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
