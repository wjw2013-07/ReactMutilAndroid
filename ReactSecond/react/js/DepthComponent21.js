'use strict';

import React, {Component} from 'react';

import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    Image,
    ScrollView,
    TouchableOpacity,
    NativeModules,
    Dimensions,

} from 'react-native';

var {height, width} = Dimensions.get('window');

/***
 * 验证使用原生混合React开发中可能遇到的问题
 */
class ComponentButton extends React.Component{
    render(){
        return(
            <TouchableOpacity
                style={styles.button}
                underlayColor="#a5a5a5"
                onPress={this.props.onPress}>
                <Text>{this.props.text}</Text>    
            </TouchableOpacity>
        )
    }
}

export default class DepthComponent21 extends React.Component{
    render(){
        return(
                <View style={styles.container}>
                     {/*1调用原生加载对话框*/}
                    <Text>1调用原生实现的dialog</Text>  
                    <ComponentButton
                        onPress={() => NativeModules.DialogModule.showLoadingDialog()}
                        text='展示加载loading'
                    >
                    </ComponentButton>
                    <ComponentButton
                        onPress={() => NativeModules.DialogModule.hideLoadingDialog()}
                        text='隐藏加载loading'
                    ></ComponentButton>

                    {/*2展示蒙层效果*/}
                    <Text style={{marginTop: 15}}>2展示蒙层效果</Text>

                    {/*3调用原生请求，并传递复杂数据，给js渲染*/}
                    <Text style={{marginTop: 15}}>3 调用原生请求，并传递复杂数据，给js渲染</Text>
                    <ComponentButton
                        onPress={() => NativeModules.DataModule.loadData((msg) => {
                            ToastAndroid.show('JS界面:从Activity中传输过来的数据为:'+ msg,ToastAndroid.SHORT);
                        })}
                        text='原生传递给js复杂数据'
                    ></ComponentButton>
                    
                </View>
        );
    }
}

const styles = StyleSheet.create({
  button: {
    margin:5,
    padding: 15,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#cdcdcd',
  },

  container: {
      width: width,
      height: height, 
      padding: 10,
  }
});