/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';
import { RNNumberSelector } from 'react-native-number-selector'

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  render() {
    let items1 = [0, 1, 2, 3, 4, 5];
    let items2 = [10, 11, 22, 33, 44, 55, 66, 77, 88, 99, 100, 111];
    let items3 = [1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

    return (
      <View style={styles.container}>
        <RNNumberSelector key={1} style={{ left: 0, width: '100%', height: 50 }} items={items1} selectedItem={4} spacing={50} highlightedFontSize={25} fontSize={20} textColor={'#345345'} highlightedTextColor={'#634534'} viewAnimation={0}  onChange={(number) => {
          console.log('selected: ' + number)
        }}/>
        <RNNumberSelector key={2} style={{ left: 0, width: '100%', height: 50 }} items={items2} selectedItem={4} spacing={50} highlightedFontSize={25} fontSize={20} textColor={'#345345'} highlightedTextColor={'#634534'} viewAnimation={0} onChange={(number) => {
          console.log('selected: ' + number)
        }} />
        <RNNumberSelector key={3} style={{ left: 0, width: '100%', height: 50 }} items={items3} selectedItem={4} spacing={50} highlightedFontSize={25} fontSize={20} textColor={'#345345'} highlightedTextColor={'#634534'} viewAnimation={0} onChange={(number) => {
          console.log('selected: ' + number)
        }} />
        <Text style={styles.welcome}>ReactNative: Native Number Picker!</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
