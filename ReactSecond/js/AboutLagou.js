'use strict';

import React from 'react';
import {
      StyleSheet, 
      View,
      Text,
      Image
 } from 'react-native';

export default class AboutLagou extends React.Component {
    render(){
        return (
            <View style={styles.container}>
                
               <View style={styles.topContainer}>
                 <Image style={styles.imageBack} source={require('../img/iconback.png')}/>
                 <View style={styles.topTitleContainer}>
                    <Text style={styles.topTitle}>关于拉勾</Text>
                 </View>
               </View>

               <View style={styles.line}></View>

               <View style={styles.contentIconContainer}>
                    <Image style={styles.contentIcon} source={require('../img/iconlagouman.png')} />
               </View>
                <Text style={styles.contentUp}>每一次拉勾，都是对未来的约定</Text>
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
        margin: 10,
        width: 20,
        height: 20
    },

    topTitleContainer: {
        flexDirection: 'row',
        justifyContent: 'center'    
    },

    topTitle: {
        margin: 10,
        fontSize: 17,
        textAlign: 'center'
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
        width: 240,
        height: 240
    },

     contentUp: {
        marginTop: 50,
        fontSize: 15,
        textAlign: 'center'
    },

  contentDown: {
        marginTop: 10,
        fontSize: 16,
        textAlign: 'center'
    },

  contentBottomVersion: {
        marginTop: 100,
        fontSize: 16,
        textAlign: 'center'
    },
    contentBottomUrl: {
        marginTop: 1,
        fontSize: 13,
        textAlign: 'center'
    },
})