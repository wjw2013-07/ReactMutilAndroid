'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    ScrollView,
    View,
    TouchableHighlight,
    NativeModules,
    ToastAndroid,


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

    //  componentWillMount(){

    //     DeviceEventEmitter.addListener(DeviceEventEmitter,
    //                    'native',
    //                    function(e:Event) {
    //                         NativeModules.ToastCustomModule.show('自定义toast回调', NativeModules.ToastCustomModule.SHORT);   
    //                         // this.keyboardWillOpenTo = e;
    //                         // this.props.onKeyboardWillShow && this.props.onKeyboardWillShow(e);
    //                    });
    //  }

    componentDidMount(){

        NativeModules.IntentModule.dataToJs((msg) => {
            ToastAndroid.show('Js界面：从Activity中传输过来的数据为：' + msg, ToastAndroid.SHORT);
        },
        (result) => {
            ToastAndroid.show('JS界面:错误信息为:'+result,ToastAndroid.SHORT);
        })

    }

    render() {

        return(
            <ScrollView>
                <View style={styles.container}>
                    {/*1 原生模块封装初步---自定义ToastCustomModule实例*/}
                    <Text style={styles.text}>1 原生模块封装初步---自定义ToastCustomModule实例</Text>
                    <CustomButton text='使用自定义的Toast' 
                        onPress={() => NativeModules.ToastCustomModule.show('我是ToastCustomAndroid弹出框',
                         NativeModules.ToastCustomModule.SHORT)}>
                    </CustomButton>
                    <CustomButton text='使用自定义的Toast2 原生回调Js模块回调方法' 
                        onPress={() => NativeModules.ToastCustomModule.measureLayout((msg) => {
                            console.log(msg);
                        },
                        (x, y, width, height) => {
                            NativeModules.ToastCustomModule.show(x + "坐标，" + y + "坐标，" + width + "宽，" + height + "高", NativeModules.ToastCustomModule.SHORT)
                        }
                        )}>
                    </CustomButton>     


                     {/*2 原生封装之--startActivityForResult启动Activity,获取Activity Result值回调信息  */}
                    <Text style={styles.text}>2 原生封装之--startActivityForResult启动Activity,获取Activity Result值回调信息</Text>
                    <CustomButton text='startActivityForResult启动Activity'
                         onPress={() => NativeModules.ImagePickerModule.pickImage()}>
                    </CustomButton>

                    <Text style={styles.text}>3 原生封装之--Js调用原生方法</Text>
                    <CustomButton text='Js调用原生方法' onPress={() => 
                        NativeModules.IntentModule.startActivityFromJs('mix.react.com.second.activity.NativeLaunchedJsActivity', '我是来自Js传递过来的参数 123456')}>
                    </CustomButton>    

                    <Text style={styles.text}>4 原生封装之--Js调用原生方法，并等待原生数据返回</Text>
                    <CustomButton text='Js调用原生方法，并等待原生数据返回' onPress={() => 
                        NativeModules.IntentModule.startActivityFromJSGetResult('mix.react.com.second.activity.NativeLaunchedJs2Activity', 200, (msg) => {
                            ToastAndroid.show('JS界面：从Activity传递过来的数据为：' + msg, ToastAndroid.SHORT);
                        },
                        (result) => {
                             ToastAndroid.show('JS界面：错误信息为:' + result, ToastAndroid.SHORT);
                        }
                        )}>
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