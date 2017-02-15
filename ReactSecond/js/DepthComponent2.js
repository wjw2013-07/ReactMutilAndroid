import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    TextInput,
    Image,
    View
} from 'react-native';

export default class DepthComponent extends React.Component {
    render(){
        return(
            // 模仿QQ登录页面
            <View style={{backgroundColor: '#f4f4f4', flex: 1}}>
                <Image style={styles.iconTop} source={require('../img/iconlagouman.png')}></Image>
                <TextInput 
                   style={styles.textInput}
                   placeholder='QQ号/手机号/邮箱'
                   underlineColorAndroid={'transparent'}
                   numberOfLines={1}
                   autoFocus={true}
                   />

                 <View
                   style={{height:1,backgroundColor:'#f4f4f4'}}
                 />

                <TextInput
                  style={styles.textInput}
                  placeholder='密码'
                  underlineColorAndroid={'transparent'}
                  secureTextEntry={true}
                  />
                <View style={styles.textLogin}>
                    <Text style={{color: '#fff', fontSize: 16}}>登录</Text>
                </View>

                <View 
                    style={{flex: 1, flexDirection: 'row', alignItems: 'flex-end', bottom: 10}}>
                    <Text style={{marginLeft: 10, color: '#63B8FF', fontSize: 15}}>无法登录</Text>    
                    <Text style={styles.textRegister}>新用户</Text>
                </View>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    iconTop: {
        width: 70, 
        height: 70,
        marginTop: 80,
        marginBottom: 30,
        alignSelf: 'center',
    },

    textInput: {
        backgroundColor: '#ffffff',
    },

    textLogin: {
        backgroundColor: '#63B8FF',
        marginTop: 20,
        marginLeft: 10,
        marginRight: 10,
        height: 45,
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 5
    },

    textRegister: {
       color:'#63B8FF',
       marginRight:10,
       flex:1,
       textAlign:'right',
    }
})