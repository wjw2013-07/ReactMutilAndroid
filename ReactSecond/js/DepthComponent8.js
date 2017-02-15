'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    TouchableHighlight,
    Navigator,
} from 'react-native';

export default class DepthCompoment extends React.Component{
    render() {
        return (
            <Navigator
                style={{flex: 1}}
                initialRoute={{message: '初始化页面',}}
                renderScene={(route, navigator) => <NavMenu
                    message={route.message}
                    navigator={navigator}
                />}
                configureScene={(route) => {
                    if(route.sceneConfig){
                        return route.sceneConfig;
                    }

                    return Navigator.SceneConfigs.FloatFromBottom
                }}
            ></Navigator>
        )
    }
}


class NavMenu extends React.Component{
    render() {
        return(
            <View>
                <Text>{this.props.message}</Text>
                <NavButton
                    onPress={() => {
                        this.props.navigator.push({
                            message: '向右拖拽关闭页面',
                            sceneConfig: Navigator.SceneConfigs.FloatFromRight
                        });
                    }}
                    text='从右向左切入页面（带有透明度变化）'
                ></NavButton>

                 <NavButton
                    onPress={() => {
                        this.props.navigator.push({
                            message: '向下拖拽关闭页面',
                            sceneConfig: Navigator.SceneConfigs.FloatFromBottom
                        });
                    }}
                    text='从下向上切入页面（带有透明度变化）'
                ></NavButton>

                <NavButton
                    onPress={() => {
                        this.props.navigator.pop();
                    }}
                    text='页面弹出回退一页'
                ></NavButton>

                 <NavButton
                    onPress={() => {
                        this.props.navigator.popToTop();
                    }}
                    text='页面弹出回退到最后一页'
                ></NavButton>
            </View>
        )
    }
}

class NavButton extends React.Component{
    render() {
        return(
            <TouchableHighlight
                underlayColor='#B5B5B5'
                onPress={this.props.onPress}>
                <Text>{this.props.text}</Text>
            </TouchableHighlight>
        )
    }
}