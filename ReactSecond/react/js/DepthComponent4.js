'use strict';

import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    ToolbarAndroid,
    Switch,
    Picker,
    ViewPagerAndroid,
    TouchableOpacity,
    ScrollView,
    TouchableNativeFeedback,
    View
} from 'react-native';

var toobarActions = [
    {title: 'Create', icon: require('../img/iconlagouman.png')},
    {title: 'filter'},
    {title: 'Setting', icon: require('../img/iconlagouman.png')}
];
export default class DepthComponent4 extends React.Component{

    constructor() {
        super();
        this.state = {falseSwitchIsOn: false};
    }
    
  getInitialState= function() {
    return {
      page:1,
    };
  }
  onPageSelected = function(e) {
    // this.setState({page: 1+e.nativeEvent.position});
  }

    render() {
        return(
            
           <View>
               {/*ToolbarAndroid的使用*/}
              <ToolbarAndroid
                actions={toobarActions}
                logo={require('../img/iconback.png')}
                subtitle='副标题'
                title="主标题"
                style={{backgroundColor: '#e9eaed', height: 56}} 
              />

            <ScrollView
               showsVerticalScrollIndicator={true}
              >
              <View style={styles.container}>
                  {/*1 Switch的使用*/}
                 <Text>1 Switch的使用</Text>
                 <Switch
                    onValueChange={(value) => this.setState({falseSwitchIsOn: value})}
                    style={{marginBottom: 10, marginTop: 10}}
                    value={this.state.falseSwitchIsOn}
                 ></Switch>

                 {/*2 Picker的使用*/}
                 <Text style={styles.title}>2 Picker的使用</Text>
                 <Picker
                    prompt="请选择编程语言"
                    style={{width: 200}}
                    selectedValue={this.state.language}
                    onValueChange={(lang) => this.setState({language: lang})}>
                    <Picker.Item label='Java' value='java'/>
                    <Picker.Item label='Android' value='android'/>    
                </Picker>
                <Text>当前选择的是：{this.state.language}</Text>


                {/*3 ViewPagerAndroid的使用*/}
                <Text style={styles.title}>3 ViewPagerAndroid的使用的使用</Text>
                <ViewPagerAndroid style={styles.viewPager} initialPage={1} onPageSelected={this.onPageSelected}>
                   <View style={{backgroundColor: 'red'}}>
                        <Text>First Page!</Text>
                   </View>     
                    <View style={{backgroundColor: 'yellow'}}>
                        <Text>Second Page!</Text>
                    </View>   
                </ViewPagerAndroid>   
                <Text>当前第{this.state.page}页</Text>

              {/*4 TouchableOpacity使用*/}
               <Text style={styles.title}>4 TouchableOpacity使用</Text>
               <TouchableOpacity style={{margin: 10 }}>
                    <Text style={{fontSize: 20}}>点击我</Text>
               </TouchableOpacity>

                {/*5 TouchableNativeFeedback使用*/}
               <Text style={styles.title}>5 TouchableNativeFeedback使用</Text>
                <TouchableNativeFeedback style={{marginTop:20}} 
                    background={TouchableNativeFeedback.SelectableBackground()}>
                    <View style={{width: 150, height: 100, marginBottom: 100}}>
                      <Text style={{margin: 30}}>Button</Text>
                    </View>  
                </TouchableNativeFeedback>

              </View>  
             </ScrollView> 
            </View>
        );
    }

    
}

const styles = StyleSheet.create({

    container: {
        margin: 10,
        alignItems: 'flex-start'
    },

    title: {
        marginTop: 10
    },

    viewPager: {
        alignItems: 'center',
        padding: 20,
        height: 200,
        width: 300
    }
})