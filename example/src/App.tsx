import * as React from 'react';

import { StyleSheet, Text, View } from 'react-native';
import { ScalePressView } from 'react-native-scale-press';

export default function App() {
  return (
    <View style={styles.container}>
      <ScalePressView onPress={() => {
        console.log('[App.press]', )
      }} style={styles.box}>
        <Text>Press</Text>
      </ScalePressView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
    backgroundColor: 'red',
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    borderColor: 'green',
    borderWidth: 2,
  },
});
