'use strict';

const React = require('react');
const ReactNative = require('react-native');
const {
    ScrollView,
    StyleSheet,
    RefreshControl,
    Text,
    TouchableWithoutFeedback,
    View,

} = ReactNative;

const Row = React.createClass({
    _onClick: function(){
        this.props.onClick(this.props.data);
    },

    render: function() {
        return(
            <TouchableWithoutFeedback onPress={this._onClick}>
                <View style={styles.row}>
                    <Text style={styles.text}>
                        {this.props.data.text + ' ( ' +this.props.data.clicks + ' clicks )'}
                    </Text>
                </View>
            </TouchableWithoutFeedback>    
        )
    }
});

const RefreshContralExample = React.createClass({
    statics: {
        title: '<RefreshControl>',
        description: 'Adds pull-to-refrsh to a scrollview'
    },

    getInitialState() {
        return {
            isRefreshIng: false,
            loaded: 0,
            rowData: Array.from(new Array(20)).map((val, i) => ({text: 'initial row ' + i, clicks: 0})),
        };
    },

    _onClick(row){
        row.clicks++;
        this.setState({
           rowData: this.state.rowData, 
        });
    },

  _onRefresh() {
        this.setState({isRefreshIng: true});
        setTimeout(() => {
            const rowData = Array.from(new Array(10))
            .map((val, i) => ({
                text: 'Load row' + (+ this.state.loaded + i),
                clicks: 0,
            }))
            .concat(this.state.rowData);

            this.setState({
              loaded: this.state.loaded + 10,
              isRefreshIng: false,
              rowData: rowData,  
            })
        }, 1000);

    },

    render() {
        const rows = this.state.rowData.map((row, ii) => {
            return <Row key={ii} data={row} onClick={this._onClick}/>
        });

        return(
            <ScrollView
                style={styles.scrollview}
                refreshControl={
                    <RefreshControl
                        refreshing={this.state.isRefreshIng}
                        onRefresh={this._onRefresh}
                        tintColor='#ff0f00'
                        title='Loading...'
                        titleColor='#00ff00'
                        colors={['#ff0000', '#00ff00', '#0000ff']}
                        progressBackgroundColor='#ffff00'
                    ></RefreshControl>
                }>
                {rows}    
            </ScrollView>
        )
    }
});

const styles = StyleSheet.create({
    row: {
         borderColor: 'grey',
         borderWidth: 1,
         padding: 20,
         backgroundColor: '#3a5795',
         margin: 5,
    },

    text: {
        alignSelf: 'center',
        color: '#fff',
    },

    scrollview: {
        flex: 1
    }
});

module.exports = RefreshContralExample;