'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    ScrollView,
    View,
    TouchableHighlight,
    NativeModules,

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


export default class DepthComponet13 extends React.Component{

    render() {
        return(
            <ScrollView>
                <View >
                    {/*1 LayoutAnimation 实例*/}
                    <Text style={styles.text}>1 LayoutAnimation 实例</Text>
                    <CustomButton text='使用自定义的Toast' 
                        onPress={() => NativeModules.ToastCustomModule.show('我是ToastCustomAndroid弹出框',
                         NativeModules.ToastCustomModule.SHORT)}>
                    </CustomButton>
                </View>
            </ScrollView>
        )
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
      marginLeft: 15
  },

  text: {
      marginTop: 10
  },


});