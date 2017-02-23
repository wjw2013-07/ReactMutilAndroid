import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    DrawerLayoutAndroid,
    View
} from 'react-native';

export default class DrawerLayoutDemo extends React.Component{
    // 抽屉DrawerLayoutAndroid的使用
    render(){
        var navigationView = (
            <View style={{flex: 1, backgroundColor: '#fff'}}>
                <Text style={{margin: 10, fontSize: 15, textAlign: 'left'}}>I am the drawer</Text>
            </View>
        );

        return (
            <DrawerLayoutAndroid
                drawerWidth={200}
                drawerPosition={DrawerLayoutAndroid.positions.Light}
                renderNavigationView= {() => navigationView}>
                <View style={{flex: 1, alignItems: 'center'}}>
                    <Text style={styles.textContent}>Hello</Text>
                    <Text style={styles.textContent}>World</Text>
                </View>
            </DrawerLayoutAndroid>
        );
    }
}

const styles = StyleSheet.create({
    textContent: {
        margin: 10, 
        fontSize: 15, 
        textAlign: 'right'
    }
})