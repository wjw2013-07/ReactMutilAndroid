'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    Clipboard,
    DatePickerAndroid,
    TouchableHighlight,
    ToastAndroid,
    StatusBar,
    TimePickerAndroid
} from 'react-native';

class CustomButtom extends React.Component{
    render() {
        return(
            <TouchableHighlight
                style={styles.button}
                underlayColor='#a5a5a5'
                onPress={this.props.onPress}>
                <Text style={styles.buttonText}>{this.props.text}</Text>
            </TouchableHighlight>
        )
    }
}

export default class DepthCompoment extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            content: '需要保存的内容',
            presetDate: new Date(2016, 3, 5),
            allDate: new Date(2020, 4, 5),
            simpleText: '选择日期默认今天',
            minText: '选择日期，不能比今日再早',
            maxText: '选择日期不能比今日再晚',
            presetText: '选择日期，指定2016/3/5',

            isoFormatText: 'pick a time (24-hour format)',
            presetHour: 4,
            presetMinute: 4,
            presetText2: 'pick a time, default: 4:04AM',
            simpleText2: 'pick a time',
        };
    }

    _formatTime(hour, minute) {
     return hour + ':' + (minute < 10 ? '0' + minute : minute);
    } 

    async showPickerHour(options){
        try {
           const {action, minute, hour} = await 
           TimePickerAndroid.open(options);
            if(action == TimePickerAndroid.timeSetAction){
                ToastAndroid.show('选择的时间为：' + this._formatTime(hour, minute), ToastAndroid.SHORT);
            }else if(action == TimePickerAndroid.dismissedAction){
                ToastAndroid.show('选择器关闭取消',ToastAndroid.SHORT);     
            }
        } catch (error) {
            ToastAndroid.show('错误信息:'+message,ToastAndroid.SHORT);
        }
    }
    async showPicker(stateKey, options){
        try {
            var newState = {};
            const {action, year, month, day} = await
            DatePickerAndroid.open(options);
            if(action == DatePickerAndroid.dismissedAction){
               // newState[stateKey + 'Text'] = 'dismissed';
            } else{
                var date = new Date(year, month, day);
                newState[stateKey + 'Text'] = date.toLocaleDateString();
                newState[stateKey + 'Date'] = date;  
            }
            this.setState(newState);
        } catch ({code, message}) {
            console.warn('Error in example ', message);
        }
    }
    async _setClipboardContent(){
        Clipboard.setString('Hello World');
        try {
            var content = await Clipboard.getString();
            ToastAndroid.show('黏贴版内容： ' + content, ToastAndroid.SHORT);
        } catch (error) {
             ToastAndroid.show(e.message,ToastAndroid.SHORT);
        }
    }

    render() {
        return (
            <View style={styles.container}>
                {/*1 ClipBoard粘贴板演示*/}
                <Text style={styles.textTitle}>1 ClipBoard粘贴板演示</Text>
                <Text style={styles.text} onPress={this._setClipboardContent}>点我把'Hello World'复制到粘贴版</Text>

                {/*2 DatePickerAndroid使用*/}
                <Text style={styles.textTitle}>2 DatePickerAndroid使用</Text>
                <TouchableHighlight 
                    style={styles.button}
                    underlayColor='#a5a5a5'
                    onPress={this.showPicker.bind(this, 'simple', {date: this.state.simpleDate})}>
                    <Text style={styles.buttonText}>点击弹出基本日期选择器</Text>
                </TouchableHighlight>    
                <CustomButtom
                    text={this.state.presetText}
                    onPress={this.showPicker.bind(this, 'presetText', {date: this.state.presetDate})}
                ></CustomButtom>
                <CustomButtom
                    text={this.state.minText}
                    onPress={this.showPicker.bind(this, 'min', {date: this.state.minDate, minDate:new Date()})}
                ></CustomButtom>
                <CustomButtom
                    text={this.state.maxText}
                    onPress={this.showPicker.bind(this, 'max', {date: this.state.maxDate, maxDate:new Date()})}
                ></CustomButtom>

                {/*3 StatusBar使用*/}
                <Text style={styles.textTitle}>3 StatusBar使用</Text>
                <StatusBar
                    backgroundColor='#ff0000'
                    translucent={false}
                    hidden={true}
                    animated={true}
                ></StatusBar>

                 {/*4 TimePickerAndroid使用*/}
                <Text style={styles.textTitle}>4 TimePickerAndroid使用</Text>
                <CustomButtom
                    text='时间选择器，默认为当前时间'
                    onPress={this.showPickerHour.bind(this)}
                ></CustomButtom>
            </View>
        )
    }
}


const styles = StyleSheet.create({
    container: {
        marginLeft: 20
    },

    textTitle: {
      fontWeight: 'bold',
      marginTop: 15
    },

    text: {
        marginLeft: 12
    },

    button: {
        backgroundColor: 'white',
        padding: 15,
        borderBottomWidth: StyleSheet.hairlineWidth,
        borderBottomColor: '#cdcdcd'
    }
})