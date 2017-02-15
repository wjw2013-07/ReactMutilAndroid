import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    ListView,
    TouchableOpacity,
    Image,
    RefreshControl,
    View
} from 'react-native';

var IMAGES = [
    require('../img/iconback.png'),
    require('../img/iconback.png'),
    require('../img/iconback.png'),
    require('../img/iconback.png'),
    require('../img/iconback.png')
];

export default class DepthComponent5 extends React.Component{
    constructor(props){
        super(props);
        var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.state={
             dataSource : ds.cloneWithRows(['row 1', 'row 2', 'row 3', 'row 4', 'row 5']),
             isRefreshing : false,
             loaded : 0,
        };
        
    }

    _renderRow(rowData: string, sectionID: number, rowID: number) {
    var imgSource = IMAGES[rowID];
    return (
        <TouchableOpacity>
          <View>
            <View style={styles.row}>
              <Image style={styles.thumb} source={imgSource} />
              <Text style={{flex:1,fontSize:16,color:'blue'}}>
                {rowData + '我是测试行号哦~'}
              </Text>
            </View>
          </View>
        </TouchableOpacity>
    );
   }
    render() {
        return(
            <View style={styles.container}>
                <ListView
                  dataSource={this.state.dataSource}
                  renderRow={this._renderRow} 
                ></ListView>
            </View>
        )
    }
}


const styles = StyleSheet.create({
    container: {
        margin: 10
    },

   row: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 10,
    backgroundColor: '#F6F6F6',
  },
  thumb: {
    width: 50,
    height: 50,
  },
})