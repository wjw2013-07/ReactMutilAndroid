'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    Animated,
    TouchableHighlight,

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

export default class DepthComponent15 extends React.Component{
    constructor(props){
        super(props);
        this.state={
            fadeAnim: new Animated.Value(0),

        };

    }

    fadeIn(){
        Animated.timing(
            this.state.fadeAnim,
            {
                toValue: 1,
                duration: 3500
            },
        ).start();
    }

    render(){
        return(
            <View style={styles.container}>
                {/*1 动画实例一 加载失败*/}
                <Text style={styles.text}>1 动画实例一</Text>
                <CustomButton text='视图淡入效果' onPress={this.fadeIn}></CustomButton>
                <Animated.View
                    style={{opacity: this.state.fadeAnim}}
                >   
                    {this.props.children}
                </Animated.View>
            </View>
            
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