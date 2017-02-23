import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    TextInput,
    Image,
    ProgressBarAndroid,
    ScrollView,
    View
} from 'react-native';

export default class DepthComponent extends React.Component {

    render() {
        return (
            <ScrollView 
              showsVerticalScrollIndicator={false}
              >
              <View style={styles.container}>
                {/*Text组件嵌套  内层有样式，会覆盖外层样式*/}
                <Text style={{marginTop:20}}>1 组件之Text组件嵌套</Text>
                <Text numberOfLines={2} style={styles.titleBase}>
                    I am root text!
                    <Text style={styles.titleChild}>I am child text!</Text>
               </Text>     
                
                {/*Image组件深入学习*/}
                <Text style={{marginTop:20}}>2 组件之Image深入学习</Text>
                <Image source={require('../img/iconback.png')}></Image>
                {/*嵌套实现背景*/}
                {/*加载网路图片*/} 
                <Image 
                   source={{uri:'http://pic2.cxtuku.com/00/02/31/b945758fd74d.jpg'}} 
                   style= {{width: 200, height: 200, opacity: 0.5}}
                   >
                   <Text style={{marginTop: 20}}>嵌套实现背景</Text> 
                </Image>

                {/*TextInput组件*/}
                <Text style={{marginTop:20}}>3 TextInput组件</Text>
                <TextInput
                    onChangeText={(text) => this.setState({text})}
                    autoFocus={true}
                    defaultValue="wjw"
                    placeholder="请输入"
                />

                {/*ProgressBarAndroid进度条组件*/}
                <Text style={{marginTop:10}}>4 ProgressBarAndroid组件</Text>
                <ProgressBarAndroid styleAttr='Horizontal' style={{color: '#ff0000'}} progress={0.2} indeterminate={false}/>
                <ProgressBarAndroid styleAttr='Inverse' style={{color: '#ff0000'}} progress={0.2} indeterminate={false}/>
                <ProgressBarAndroid styleAttr='LargeInverse' style={{color: '#ff0000'}} progress={0.2} indeterminate={false}/>
            </View>
        </ScrollView>
        )
    }
}

const styles = StyleSheet.create({

    container: {
       margin: 20,

    },

   containLevel: {
        flexDirection: 'row',
        marginTop: 20
   }, 
   containVertical:{
       flexDirection: 'column',
       marginLeft: 15,
   },
 
   iconItem: {
       width: 50,
       height: 50 ,
       alignSelf: 'center'
   },
   titleItem: {
       textAlign: 'center',
       marginTop: 5,
       fontSize: 14,
       color: '#555555'
   },

    titleBase: {
       textAlign: 'justify',
       color: 'red',
       fontSize: 28,
       fontFamily: 'Cochin',
       textDecorationLine: 'underline',
       textDecorationStyle: 'solid', 
    },

    titleChild: {
        color: 'green',
        fontWeight: 'bold',
    }
})