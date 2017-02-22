'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    Animated,

} from 'react-native';


// Animated内容介绍

export default class DepthComponent14 extends React.Component{

constructor(props: any) {
    super(props);
    this.state = {
      bounceValue: new Animated.Value(0),
    };
  }
  
  render(){
    return (    
      <Animated.Image                         // 基础组件: Image, Text, View
        source={{uri: 'http://i.imgur.com/XMKOH81.jpg'}}
        style={{
          flex: 1,
          transform: [                        // `transform`   有顺序的数组
            {scale: this.state.bounceValue},  // Map `bounceValue` to `scale`
            {translateY: this.state.bounceValue.interpolate({
              inputRange: [0, 1],
              outputRange: [1, 0]
            })}
          ]
        }}
      />
    );
  }
  
  componentDidMount() {
    this.state.bounceValue.setValue(1.5);     // 动画开始的时候 设置一个比较大的值
    Animated.spring(                          // 动画可选类型: spring, decay, timing
      this.state.bounceValue,                 // Animate `bounceValue`
      {
        toValue: 0.8,                         // Animate to smaller size
        friction: 1,                          // Bouncier spring
      }
    ).start();                                // 开始执行动画
  }
}