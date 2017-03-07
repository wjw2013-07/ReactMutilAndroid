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

 export default class ChancePlusComponent extends React.Component {
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
                        <Text style={styles.topTitle}>机会+</Text>
                    </View>
                    <View style={{width: 50}}></View>
               </View>
               <View style={styles.line}></View>

               
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