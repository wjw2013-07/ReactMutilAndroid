'use strict';

import React, {Component} from 'react';

import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    TouchableHighlight,
    Image,
    ListView,

} from 'react-native';

var MACKED_MOVIES_DATA = [{title: 'Title', year: '2015', posters:{thumbnail: 'http://i.imgur.com/UePbdph.jpg'}}];
var REQUEST_URL = 'https://raw.githubusercontent.com/facebook/react-native/master/docs/MoviesExample.json';

class CustomButton extends React.Component {
  render() {
    return (
      <TouchableHighlight
        style={styles.button}
        underlayColor="#a5a5a5"
        onPress={this.props.onPress}>
        <Text>{this.props.text}</Text>
      </TouchableHighlight>
    );
  }
}


export default class DepthComponent16 extends React.Component{

    constructor(props){
        super(props);
        this.state={
            dataSource: new ListView.DataSource({
                rowHasChanged:(row1, row2) => row1 !== row2,
            }),
            
            loaded: false,
        };
    }

    componentDidMount(){
        this.fetchData();
    }

    fetchData(){
        fetch(REQUEST_URL)
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    dataSource: this.state.dataSource.cloneWithRows(responseData.movies),
                    loaded: true,    
                });
            }).done();
    }

    render(){

        if(!this.state.loaded){
            return this.renderLoadingView();
        }
        
       return(
         <ListView
            dataSource={this.state.dataSource}
            renderRow={this.renderMovie}
            style={styles.listView}
         />
       );

    }

    renderLoadingView(){
        return(
            <View style={styles.container}>
                <Text>
                    Loading movies......
                </Text>
            </View>
        );
    }

    renderMovie(movie){
        return(
            // 1 电影票列表实例
            <View style={styles.container}>
                <Image style={styles.thumbnail} source={{uri: movie.posters.thumbnail}}/> 
                <View style={styles.rightContainer}>
                    <Text style={styles.title}>{movie.title}</Text>
                    <Text style={styles.year}>{movie.year}</Text>   
                </View>
                
            </View>
        );
    }
    
    
}

const styles = StyleSheet.create({
  button: {
    margin:5,
    backgroundColor: 'white',
    padding: 15,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#cdcdcd',
  },

 container: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    padding: 15
  },

  text: {
      marginTop: 10
  },

  thumbnail: {
    width: 53,
    height: 81,
  },
 
 rightContainer: {
    flex: 1,
  },

  title: {
    fontSize: 16,
    marginBottom: 8,
    textAlign: 'center',
  },

  year: {
    textAlign: 'center',
  },

listView: {
    paddingTop: 20,
    backgroundColor: '#F5FCFF',
  },

});