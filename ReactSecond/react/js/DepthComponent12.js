'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    ScrollView,
    View,
    TouchableHighlight,
    LayoutAnimation,
    ToastAndroid,
    Platform,
    UIManager,
    Share,
    PermissionsAndroid,
    TextInput,
    TouchableWithoutFeedback,

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

var CustomLayoutAnimation = {
    duration: 800,
    create: {
        type: LayoutAnimation.Types.linear,
        property: LayoutAnimation.Properties.opacity,
    },
    update: {
        type: LayoutAnimation.Types.easeInEaseOut,
    },
};


export default class DepthComponent12 extends React.Component{

    constructor(props, context){
        super(props, context);
        this._shareMessage = this._shareMessage.bind(this);
        this._shareText = this._shareText.bind(this);
        this._showResult = this._showResult.bind(this);

        this.state={
            views:[],
            num:0,
            content:'',
            mag:'',
            sum:0,
            result:'',
            permission: PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
            hasPermission: 'Not Checked',

        };

        if(Platform.OS === 'android'){
            UIManager.setLayoutAnimationEnabledExperimental(true);
        }
    }

    componentWillUpdate(){
        console.log('componentWillUpdate...');
        LayoutAnimation.easeInEaseOut();

        this.timer = setTimeout(
            () => {
                this.setState({content:'我是定时器打印的内容...One'})
            },
            500
        );
        this.timer_two = setTimeout(
            () => {
                this.setState({msg:'我是定时器打印的内容...Two'})
            },
            1000
        );
    }

    componentWillUnMount(){
        this.timer && clearTimeout(this.timer);
        this.timer_two && clearTimeout(this.timer_two);
    }

  async _onPressAddView(){
        this.setState({num: Number.parseInt(this.state.num)+1});
    }

  async  _onPressRemoveView(){
        this.setState({num:Number.parseInt(this.state.num)-1});
    }

  async  _renderAddedView(i){
        return(
            <View key={i} style={styles.view}>
                <Text style={{color: '#fff'}}>{i}</Text>
            </View>
        );
    }

  async _shareMessage(){
        Share.share({
            message: '我是被分享的信息'
        })
        .then(this._showResult)
        .catch((error) => this.setState({result: 'error' + error.message}));
    }

   async _shareText(){
        Share.share({
            message:'我是被分享的信息',
            url:'http://www.lcode.org',
            title:'React Native'
        }, {
            dialogTitle:'分享博客地址',
            excludeActivityTypes: [
                'com.apple.UIKit.activity.PostToTwitter' 
            ],
            tintColor: 'green'
        })
        .then(this._showResult)
        .catch((error) => this.setState({result: 'error' + error.message}));
    }

    async _showResult(result){
        if(result.action === Share.sharedAction){
            if(result.activityType){
                this.setState({result: 'shared with an activityType' + result.activityType});
            }else{
                this.setState({result: 'shared'});
            }
        }else if (result.action === Share.dismissedAction) {
            this.setState({result: 'dismissed'});
        }
    }
    
    // _updateText=(event: Object) => {
    //     this.setState({
    //         permission: event.nativeEvent.text,
    //     });
    // }

    _checkPermission=async ()=> {
        let result = await PermissionsAndroid.check(this.state.permission);
        this.setState({
            hasPermission: (result ? '授权成功' : '授权失败') + ' for ' + this.state.permission,
        });
    }

    _requestPermission = async ()=> {
        let result = await PermissionsAndroid.request(
            this.state.permission,
            {
                title: '权限请求',
                message:
                '该应用需要如下权限' + this.state.permission + '请授权'
            },
        );

      this.setState({
            hasPermission: (result ? '授权成功' : '授权失败') + ' for ' + this.state.permission,
        });
    }
    render(){
        this.state.views.length=0;
        for(var i=0; i<this.state.num;i++){
            this.state.views.push(this._renderAddedView(i));
        }
        return(
            <ScrollView>
                <View style={styles.container}>

                    {/*1 LayoutAnimation 实例*/}
                    <Text style={styles.text}>1 LayoutAnimation 实例</Text>
                    <CustomButton text='添加view' onPress={this._onPressAddView.bind(this)}></CustomButton>
                    <CustomButton text='删除view' onPress={this._onPressRemoveView.bind(this)}></CustomButton>
                    <View style={styles.viewContainer}>
                        {this.state.views}
                    </View>

                    {/*2 Timers 实例*/}
                    <Text style={styles.text}>2 Timers 实例</Text>
                    <Text style={styles.button}>{this.state.content}</Text> 
                    <Text style={styles.button}>{this.state.msg}</Text>    
                    <CustomButton text='测试setInterval'
                        onPress={()=>{
                            this.interval=setInterval(() => {this.setState({sum:(this.state.sum+1)});
                        },400);
                        }}
                    />
                    <Text style={styles.button}>{this.state.sum}</Text>  

                    {/*3 Share 实例*/}
                    <Text style={styles.text}>3 Share 实例</Text>  
                    <CustomButton text='点击分享文本' onPress={this._shareMessage}></CustomButton>  
                    <CustomButton text='点击分享文本、url和标题' onPress={this._shareText}></CustomButton>  
                    <Text style={styles.button}>{this.state.result}</Text>

                    {/*4 PermissionsAndroid 实例*/}
                    <Text style={styles.text}>4 PermissionsAndroid 实例</Text>  
                    <TextInput
                        autoFocus={true}
                        autoCorrect={false}
                        style={styles.singleLine}
                        //{/*onChange={this._updateText}*/}
                        defaultValue={this.state.permission}
                        underlineColorAndroid={'#00000000'}
                    />
                    <TouchableWithoutFeedback onPress={this._checkPermission}>
                        <View>
                            <Text style={[styles.touchable, styles.text]}>Check Permission</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <Text style={styles.text}>Permission Status: {this.state.hasPermission}</Text>
                    <TouchableWithoutFeedback onPress={this._requestPermission}>
                        <View>
                            <Text style={[styles.touchable, styles.text]}>Request Permission</Text>
                        </View>
                    </TouchableWithoutFeedback>
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

 viewContainer: {
    flex: 1,
    flexDirection: 'row',
    flexWrap: 'wrap',
  },

   view: {
    height: 50,
    width: 50,
    backgroundColor: 'green',
    margin: 8,
    alignItems: 'center',
    justifyContent: 'center',
  },

   singleLine: {
    fontSize: 16,
    padding: 4,
  },

   touchable: {
    color: '#007AFF',
  },

});