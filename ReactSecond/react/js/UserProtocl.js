'use strict';

import React from 'react';
import {
      StyleSheet, 
      View,
      Text,
      Image,
      TouchableOpacity,
      NativeModules,
      WebView,

 } from 'react-native';

 var REQUEST_URL = 'https://app.lagou.com/statement/statement.html';
 var WEBVIEW_REF = 'webview';

 export default class UserProtocl extends React.Component {
    constructor(){
        super();
        this.state={
            url: REQUEST_URL,
        }
    }

    render(){
        return(
            <View style={styles.container}>
               <View style={styles.topContainer}>
                    <TouchableOpacity 
                        style={{width: 50, alignItems: 'center', alignSelf: 'center'}}
                        onPress={() => NativeModules.IntentModule.finshActivity()}
                        >
                        <Image style={styles.imageBack} source={require('../img/iconback.png')}/>
                    </TouchableOpacity>
                    <View style={styles.topTitleContainer}>
                        <Text style={styles.topTitle}>用户协议</Text>
                    </View>
                    <View style={{width: 50}}></View>
               </View>

               <View style={styles.line}></View> 
               <WebView 
                      ref={WEBVIEW_REF}
                      source={{uri: this.state.url}}    
                      automaticallyAdjustContentInsets={false}
                      javaScriptEnabled={true}
                      domStorageEnabled={true}  
                      decelerationRate='nomal'
                    />
              </View>
        );
    }
 }


const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
    },

    topContainer: {
       flexDirection: 'row',
       height: 50,
       backgroundColor: '#f7f7f9'
    },

    imageBack: {
        width: 20,
        height: 20
    },

    topTitleContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'    
    },

    topTitle: {
        fontSize: 17,
        textAlign: 'center',
    },

    line: {
        height: 0.5,
        backgroundColor: "#cccccc"     
    },
})