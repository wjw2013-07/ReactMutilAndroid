'use strict';

import React, {Component} from 'react';
import {
  StyleSheet,
  Text,
  View,
  Alert,
  ToastAndroid,
  TouchableHighlight,
  NetInfo,
  AppState,
  AsyncStorage,
  ScrollView,
  Dimensions,
  BackAndroid,
  PixelRatio,
  Vibration,
  Linking,

} from 'react-native';


var STORAGE_KEY_ONE = '@AsyncStorageDemo:key_one';
var STORAGE_KEY_MESSAGE = '@AsyncStorageDemo:key_message';
var count = 3;

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


class CustomButton2 extends React.Component {
   
   constructor(props){
       super(props);
   }
   propTypes = {
       url: React.PropTypes.string,
   }

   render() {
    return (
      <TouchableHighlight
        style={styles.button}
        underlayColor="#a5a5a5"
        onPress={() => Linking.canOpenURL(this.props.url).then(supported => {
            if(supported){
                 Linking.openURL(this.props.url);     
            }else{
                ToastAndroid.show('无法打开url：' + this.props.url, ToastAndroid.SHORT);
            }
        })}>
        <Text>{this.props.text}</Text>
      </TouchableHighlight>
    );
  }
}

export default class DepthComponent11 extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currentAppState: AppState.currentState,
            isConnected: null,
            connectInfo: null,
            messages:[],
        };
    }

    componentDidMount() {
        AppState.addEventListener('change', this._handleAppStateChange);
        
        NetInfo.isConnected.addEventListener('netchange', this._handleConnectivityChange);
        NetInfo.isConnected.fetch().done(
            (isConnected) => {this.setState({isConnected});}
        );
       NetInfo.fetch().done(
            (connectionInfo) => { this.setState({connectionInfo}); }
       );

       this._loadInitialState().done();

       BackAndroid.addEventListener('hardwareBackPress', function(){
            if(count >= 1){
                ToastAndroid.show('收到返回键事件...' + count, ToastAndroid.SHORT);
                count--;
                return true;
            }

            return false;
       });
    }
    
    componentWillUnmount() {
        AppState.removeEventListener('change', this._handleAppStateChange);

        NetInfo.isConnected.removeEventListener('netchange', this._handleConnectivityChange);
    }

    _handleAppStateChange = (nextAppState) => {
        ToastAndroid.show('当前状态：' + nextAppState, ToastAndroid.SHORT);
        this.setState({appState: nextAppState});
    }

    _handleConnectivityChange(isConnected){
        ToastAndroid.show(isConnected ? 'online' : 'offline', ToastAndroid.SHORT);
    }

    async _loadInitialState(){
        try {
            var value = await AsyncStorage.getItem(STORAGE_KEY_ONE);
            if(value != null){
                this._appendMessage('从存取中获取到的数据为：' + value);
            }else{
                this._appendMessage('存储中无数据，初始化为空数据');
            }
        } catch (error) {
            this._appendMessage('AsyncStorage 错误' + error.message);
        }
    }

    async _saveValue_One(){
        try {
            await AsyncStorage.setItem(STORAGE_KEY_ONE, '我是老王');
            this._appendMessage('保存到存储的数据是：' + '我是老王');
        } catch (error) {
            this._appendMessage('AsyncStorage错误' + error.message);
        }
    }

    async _removeValue_One(){
        try {
            await AsyncStorage.removeItem(STORAGE_KEY_ONE);
            this._appendMessage('数据删除成功...');
        } catch (error) {
            this._appendMessage('AsyncStorage错误' + error.message);
        }
    }

    _appendMessage(message){
        this.setState({messages: this.state.messages.concat(message)});
    }

    render() {
        return(
            <ScrollView> 
             <View style={styles.container}>
                {/*1 弹出框实例*/}
                <Text style={styles.text}>1 弹出框实例</Text>
                <CustomButton
                    text='点击弹出默认alert'
                    onPress={() => Alert.alert('温馨提示', '确定要退出么？')}
                ></CustomButton>
                <CustomButton
                    text='弹出有两个按钮的alert'
                    onPress={() => Alert.alert('温馨提示', '确定要退出么？',
                     [{text: '取消', onPress:()=>ToastAndroid.show('你点击了取消~', ToastAndroid.SHORT)},
                       {text: '确定', onPress:()=>ToastAndroid.show('你点击了确定', ToastAndroid.SHORT)}])}
                ></CustomButton>

                 {/*2 AppState实例*/}
                <Text style={styles.text}>2 AppState实例</Text>
                <Text style={styles.button}>
                   当前状态：{this.state.currentAppState}
                </Text>  

                 {/*3 NetInfo实例*/}
                <Text style={styles.text}>3 NetInfo实例</Text>
                <Text style={styles.button}>
                    网络状态：{this.state.isConnected ? '网络在线' : '离线'}
                </Text>
                <Text style={styles.button}>
                   网络连接类型：{this.state.connectInfo}
                </Text>      

                  {/*4 AsyncStorage实例*/}
                <Text style={styles.text}>4 AsyncStorage实例</Text>
                <CustomButton text='点击存储数据_我是老王' onPress={this._saveValue_One}></CustomButton>
                <CustomButton text='点击删除数据' onPress={this._removeValue_One}></CustomButton>
                {this.state.messages.map((m) => <Text style={styles.button} key={m}>{m}</Text>)}

                  {/*5 Dimensions实例*/}
                <Text style={styles.text}>5 Dimensions实例</Text>
                <Text style={styles.button}>当前屏幕宽度：{Dimensions.get('window').width}</Text>
                <Text style={styles.button}>当前屏幕高度：{Dimensions.get('window').height}</Text>

                 {/*6 BackAndroid实例*/}
                <Text style={styles.text}>6 BackAndroid实例</Text>
                <Text style={styles.button}>请点击返回键查看效果</Text>

                {/*7 PixelRatio实例*/}
                <Text style={styles.text}>7 PixelRatio实例</Text>
                <Text style={styles.button}>当前屏幕的像素比密度：{PixelRatio.get()}</Text>

                {/*8 Vibation实例*/}
                <Text style={styles.text}>8 Vibation实例</Text>
                <CustomButton text='点击设备震动' onPress={() => Vibration.vibrate()}></CustomButton>

                
                {/*9 Linking实例*/}
                <Text style={styles.text}>9 Linking实例</Text>
                <CustomButton2 url={'https://wwww.baidu.com'} text='打开http网页'/>
                <CustomButton2 url={'smsto:18352402477'}  text="点击进行发送短信"/> 
                <CustomButton2 url={'tel:18352402477'} text="点击进行打电话"/>
                <CustomButton2 url={'mailto:jiangqqlmj@163.com'} text="点击进行发邮件"/>
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
  }
});