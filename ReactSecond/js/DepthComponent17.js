'use strict';

import React, {Component} from 'react';

import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    TouchableHighlight,
    Image,

} from 'react-native';


class CustomButton extends React.Component {
  render() {
    return (
      <TouchableHighlight
        style={styles.button}
        underlayColor="#a5a5a5"
        onPress={this.props.onPress}>
        <Text>{this.props.text}</Text>
      </TouchableHighlight>
    );
  }
}

export default class DepthComponent17 extends React.Component{
    render(){
        return(
            <View style={styles.container}>
                <Text style={styles.text}>1 微信分享实例</Text>

            </View>
        );
    }
}

const styles = StyleSheet.create({
  button: {
    margin:5,
    backgroundColor: 'white',
    padding: 15,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#cdcdcd',
  },

 container: {
    flex: 1,
    padding: 15
  },

 text: {
      marginTop: 10
  },

});