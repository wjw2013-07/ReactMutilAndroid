'use strict';

import React from 'react';
import {
      StyleSheet, 
      View,
      Text,
      Image,
      TouchableOpacity,
      NativeModules,

 } from 'react-native';

export default class AboutLagou extends React.Component {
    render(){
        return (
            <View style={styles.container}>
               <View style={styles.topContainer}>
                    <TouchableOpacity 
                        style={{width: 50, alignItems: 'center', alignSelf: 'center'}}
                        onPress={() => NativeModules.IntentModule.finshActivity()}
                        >
                        <Image style={styles.imageBack} source={require('../img/iconback.png')}/>
                    </TouchableOpacity>
                    <View style={styles.topTitleContainer}>
                        <Text style={styles.topTitle}>关于拉勾</Text>
                    </View>
                    <View style={{width: 50}}></View>
               </View>

               <View style={styles.line}></View>

               <View style={styles.contentIconContainer}>
                    <Image style={styles.contentIcon} source={require('../img/iconlagouman.png')} />
               </View>
                <Text style={styles.contentUp}>每一次<Text style={{color:'#00b38a', fontSize: 16}}>拉勾</Text>，都是对未来的约定</Text>
                <Text style={styles.contentDown}>努力工作，便是履约</Text>
                <Text style={styles.contentBottomVersion}>当前版本  3.3.1</Text>
                <Text style={styles.contentBottomUrl}>www.lagou.com</Text>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
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

    contentIconContainer: {
        marginTop: 80,
        height: 240,   
        flexDirection: 'row',
        justifyContent: 'center'
    },

    contentIcon: {
        width: 230,
        height: 230
    },

     contentUp: { 
        marginTop: 50,
        fontSize: 15,
        textAlign: 'center'
    },

  contentDown: {
        marginTop: 10,
        fontSize: 17,
        textAlign: 'center',
        fontWeight: 'bold'
    },

  contentBottomVersion: {
        marginTop: 100,
        fontSize: 16,
        textAlign: 'center'
    },
    contentBottomUrl: {
        marginTop: 1,
        fontSize: 13,
        textAlign: 'center',
        color: '#999999'
    },
})