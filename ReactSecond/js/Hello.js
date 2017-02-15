'use strict';

import React, {Component} from 'react';
import {
  StyleSheet,
  Text,
  TextInput,
  ListView,
  View
} from 'react-native';
 
class Greeting extends Component {
  render() {
    return(
      <Text style={styles.textContent}>Hello {this.props.name}</Text>
    ); 
  }
}  

// .States属性的使用
class Blink extends Component {
  constructor(props) {
    super(props);
    this.state = {showText: true};

    setInterval(() => {
      this.setState({showText: !this.state.showText});
    }, 1000);
  }

    render() {
      let display = this.state.showText ? this.props.text : ' ';
      return(
         <Text style={styles.textContent}>{display}</Text> 
      );
    }
}

export default class Hello extends React.Component {
  
  render() {
     var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1!== r2});
     var dataSource = ds.cloneWithRows(['A', 'B', 'C', 'D', 'E', 'F'])
     return (
      <View style={styles.container}>
        <Text style={styles.hello}>个人信息</Text>
        <TextInput placeholder="用户名"/>
        
        {/*学习props属性的使用*/}
        <View style={styles.props}>
          <Text>学习props属性的使用</Text>
          <Greeting name='Tom'/>
          <Greeting name='John'/>
        </View>

        {/*学习state属性的使用*/}
        <View style={styles.props}>
            <Text>学习state属性的使用</Text>
            <Blink text='I love to blink'/>
            <Blink text='Yes blinking is so great'/>
        </View>
        
        <ListView
          dataSource={dataSource}
          renderRow={(rowData) => <Text style={styles.container}>{rowData}</Text>}
        />
          
      </View>
    )
  }
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    margin: 5
  },
  
  hello: {
    fontSize: 16,
    marginTop: 20
  },

 props: {
   marginTop: 10,
   marginBottom: 10
 },

 textContent:{
   color:  '#999999',
   fontWeight: 'bold'
 }
})