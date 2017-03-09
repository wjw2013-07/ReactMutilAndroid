'use strict';

import React from 'react';
import {
      StyleSheet, 
      View,
      Text,
      Image,
      TouchableWithoutFeedback,
      TouchableOpacity,
      NativeModules,
      ListView,
      ToastAndroid,

 } from 'react-native';

var rows = [
        {"name": "A", "id": 1},
        {"name": "B", "id": 2},
        {"name": "C", "id": 3},
        {"name": "D", "id": 4},
        {"name": "E", "id": 5},
        {"name": "F", "id": 6},
        {"name": "G", "id": 7},
       
]; 
var ds = new ListView.DataSource({  
    rowHasChanged: (r1, r2) => {  
        r1 !== r2  
    }  
});  

 export default class ChancePlusComponent extends React.Component {

      constructor(){
        super();
        this.state={
            dataSource: ds.cloneWithRows(rows),

        }
    }
  

    renderHeaderView(){
        return(
            <View style={{backgroundColor: '#ffffff'}}>
                <Text style={{alignSelf: 'center', marginTop: 20, marginBottom: 24, fontSize: 15}}>企业发现你时，你展示的身份信息</Text>   
                <TouchableWithoutFeedback 
                        onPress={() => NativeModules.ImagePickerModule.pickImage()}                        
                        >
                        <View style={{height: 80}}>
                            <Image 
                                source={require('../img/icon_topic_logo_default.png')} 
                                style={{width: 70, height: 70, alignSelf: 'center', borderRadius: 35}}
                            />   
                            <Image source={require('../img/plus_photo_camare_icon.png')} style={{width: 22, height: 22, alignSelf: 'center', marginTop: -25, marginLeft: 55}}></Image>
                        </View>    
                        
                </TouchableWithoutFeedback> 
                <View style={{height: 50, marginLeft: 40, marginRight: 40, marginTop: 24, flexDirection: 'row', alignItems: 'center', borderColor: '#cccccc', borderWidth: 0.5, borderRadius: 3}}> 
                        <Image source={require('../img/icon_plus_user.png')} style={{width: 20, height: 20, marginLeft: 16}}></Image>
                        <Text style={{fontSize: 17, marginLeft: 11, flex: 1}}>小明</Text>
                        <Image source={require('../img/icon_plus_edit.png')} style={{width: 15, height: 15, marginRight: 20}}></Image>
                </View> 
                <Text style={{fontSize: 14, color: '#cccccc', marginLeft: 40, marginRight: 40, marginTop: 20}}>这里的信息用于投递简历之前的身份展示，例如和企业聊天、企业发现你时的信息展示。</Text>
                <View style={{height: 10, backgroundColor: '#f6f6f7', marginTop: 25}}></View>
                <Text style={{alignSelf: 'center', fontSize: 16, marginTop: 25, marginBottom: 15}}>对以下企业设置不可见</Text>
            </View>
        )
    } 

    renderItem(data, sectionID, rowID){
        return(
            <View style={{backgroundColor: '#ffffff'}}>
                <View style={{height: 45, marginLeft: 40, marginRight: 40, marginTop: 13, flexDirection: 'row', alignItems: 'center', backgroundColor: '#ffffff', borderColor: '#cccccc', borderWidth: 0.5, borderRadius: 3}}>
                    <Text style={{fontSize: 15, color: '#999999', flex: 1, textAlign: 'center'}}>{data.name}</Text>
                    <TouchableWithoutFeedback
                        style={{width: 45, height: 45, alignItems: 'center'}}
                        onPress={() => this.removeClickItem(rowID)}
                    >
                        <Image source={require('../img/filter_del.png')} style={{width: 15, height: 15, marginRight: 10}}></Image>
                    </TouchableWithoutFeedback>
                </View> 
            </View>
            
        )
    }

    removeClickItem(rowID){
        ToastAndroid.show(rowID + "", ToastAndroid.SHORT);
        delete rows[rowID];
        this.setState({dataSource: ds.cloneWithRows(rows)})  

    }
    renderFooter(){
        return( 
            <View style={{backgroundColor: '#ffffff'}}>
                <View style={{height: 45, marginLeft: 40, marginRight: 40, marginTop: 18, justifyContent: 'center', backgroundColor: '#00b38a', borderRadius: 3}}>
                    <Text style={{fontSize: 16, fontWeight: 'bold', alignSelf: 'center', color: '#ffffff'}}>新增企业</Text>
                </View> 
                <View style={{height: 10, backgroundColor: '#f6f6f7', marginTop: 25}}></View>
                <View style={{height: 45, alignItems: 'center', justifyContent: 'center'}}>
                    <Text style={{color: '#00b38a', fontSize: 16, fontWeight: 'bold'}}>关闭拉勾+</Text>   
                </View>
                
            </View>
        )
    }

    render(){
        return (
            <View style={{flex:1}}>
               <View style={styles.topContainer}> 
                    <TouchableOpacity 
                        style={{width: 50, alignItems: 'center', justifyContent: 'center'}}
                        onPress={() => NativeModules.IntentModule.finshActivity()}
                        >
                        <Image style={styles.imageBack} source={require('../img/iconback.png')}/>
                    </TouchableOpacity>
                    <View style={styles.topTitleContainer}>
                        <Text style={styles.topTitle}>机会+</Text>
                    </View> 
                    <View style={{width: 50, justifyContent: 'center', alignItems: 'center'    }}>
                        <Text style={{fontSize: 14}}> 
                            规则
                        </Text>
                    </View>
               </View>
               <View style={styles.line}></View> 
               <ListView
                    style={{flex:1, backgroundColor: '#f0f1f5'}}
                    dataSource={this.state.dataSource}
                    renderRow={this.renderItem.bind(this)}
                    renderHeader={this.renderHeaderView}
                    renderFooter={this.renderFooter}
               >
               </ListView>
              
            </View>   
        )  
    } 
}
  
const styles = StyleSheet.create({

    topContainer: {
       flexDirection: 'row',
       height: 50,
       backgroundColor: '#f7f7f9'
    },

    imageBack: {
        width: 20,
        height: 20,
        marginRight: 10
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

});