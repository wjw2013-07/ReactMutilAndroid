'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    TextInput,
    Image,
    View,
    WebView
} from 'react-native';


var DEFAULT_URL = 'https://www.baidu.com';

export default class DepthComponent extends React.Component{
     render(){
        return(
            <View style={{flex:1}}>
                <WebView
                    
                    url={DEFAULT_URL}
                    startInLoadingState={true}
                    domStorageEnabled={true}
                    javaScriptEnabled={true}
                ></WebView>


            </View>
        )
    }
}