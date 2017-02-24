'use strict';

import React, {Component} from 'react';
import {COMMENT_DATA} from '../common/VirtualData';
import GridView from '../component/GridView';

import {
    StyleSheet,
    Text,
    View,
    ToastAndroid,
    Image,
    Dimensions,
    ListView,
    TouchableOpacity,
    NativeModules,

} from 'react-native';

var {height, width}=Dimensions.get('window');

export default class DepthComponent20 extends React.Component {

    constructor(props){
        super(props);
        this.renderItem=this.renderItem.bind(this);
        this.renderHeaderItem=this.renderHeaderItem.bind(this);
        this.state={
            dataSource: new ListView.DataSource({
               rowHasChanged: (row1, row2) => row1 !== row2
            }),
            commentList: COMMENT_DATA.data,

        };
    }

    renderContent(dataSource){
        return(
            <ListView
                initialListSize={10}
                dataSource={dataSource}
                renderRow={this.renderItem}
                renderHeader={this.renderHeaderItem}
                style={{backgroundColor:'white',flex:1}}
            ></ListView>
        )
    }

    renderItem(comment){
        return(
            <View style={{padding: 10}}>
                <View style={{flexDirection: 'row'}}>
                    <Image source={require('../img/ic_comment_icon.png')}/>
                    <View style={{flex: 1, marginLeft: 10}}>
                        <Text style={{fontSize: 16, fontWeight: 'bold'}}>{comment.nickname}</Text>
                        <Text style={{fontSize: 13, marginTop: 3}}>{comment.content}</Text>
                    </View>
                    <Text style={{fontSize: 13}}>{comment.createTime}</Text>
                </View>
                {this.renderCommentImg(comment.imgs)}
            </View>
        );
    }

    renderCommentImg(imgs){
        return(
            <View style={{marginLeft: 50, marginBottom: 5}}>
                <GridView
                    items={Array.from(imgs)}
                    itemsPerRow={3}
                    renderItem={this.renderImageItem}
                ></GridView>
            </View>
        );
    }

    renderImageItem(rowData){
        return(
            <Image source={{uri: rowData.imgUrl}} style={{width: 68, height: 68, margin: 5}}/>
        );
    }

    renderHeaderItem(){
        return(
            <View>
                    {/*商家基本信息*/}
                    <Image source={require('../img/ic_store_top_bg.png')} style={{width: width, height: 160}}>
                        <View>
                            <View style={{flexDirection: 'row', marginLeft: 20, marginTop: 15}}>
                                <Image source={require('../img/ic_merchants_icon.png')} style={{width: 65, height: 65}}/>
                                <View style={{marginLeft: 15}}>
                                    <Text style={{fontSize: 16, color: '#ffffff'}}>四川川二娃</Text>
                                    <Text style={{fontSize: 14, color: '#ffffff', marginTop: 4}}>川菜.中国菜</Text>
                                    <View style={{flexDirection: 'row', marginTop: 5, alignItems: 'center'}}>
                                        <Image source={require('../img/ic_merchants_comment.png')} style={{width: 15, height: 15}}/>
                                        <Text style={{fontSize: 13, color: '#ffffff', marginLeft: 4}}>59条评论</Text>
                                    </View>
                                     <View style={{flexDirection: 'row', marginTop: 2, alignItems: 'center'}}>
                                        <Image source={require('../img/ic_merchants_time.png')} style={{width: 15, height: 15}}/>
                                        <Text style={{fontSize: 13, color: '#ffffff', marginLeft: 4}}>10:00am-12:00am</Text>
                                    </View>
                                     <View style={{flexDirection: 'row', marginTop: 2, alignItems: 'center'}}>
                                        <Image source={require('../img/ic_merchants_peisong.png')} style={{width: 15, height: 15}}/>
                                        <Text style={{fontSize: 13, color: '#ffffff', marginLeft: 4}}>5km之内免费</Text>
                                    </View>
                                </View>
                            </View>
                            <Text style={{fontSize: 14, marginLeft: 12, marginTop: 12, color: '#ffffff'}}>公告信息：全场满200,8折畅享美食</Text>
                        </View>
                    </Image>

                    {/*中间功能条*/}
                    <View style={{flexDirection: 'row', height: 60, alignItems: 'center'}}>
                        <View style={{flex: 1, alignItems: 'center'}}>
                            <Image source={require('../img/ic_merchants_comment_icon.png')} style={{width: 22.5, height: 21}}/>
                            <Text style={{fontSize: 12, marginTop: 3}}>评论</Text>
                        </View>
                        <View style={{width: 1, height: 40,backgroundColor: '#cccccc'}}></View>
                        <View style={{flex: 1, alignItems: 'center'}}>
                            <Image source={require('../img/ic_merchants_collect.png')} style={{width: 22.5, height: 21}}/>
                            <Text style={{fontSize: 12, marginTop: 3}}>评论</Text>
                        </View>
                        <View style={{width: 1, height: 40,backgroundColor: '#cccccc'}}></View>
                        <View style={{flex: 1, alignItems: 'center'}}>
                            <Image source={require('../img/ic_merchants_share.png')} style={{width: 19.5, height: 22.5}}/>
                            <Text style={{fontSize: 12, marginTop: 3}}>分享</Text>
                        </View>
                    </View>
                    <View style={{height: 1, backgroundColor: '#cccccc'}}></View>
                    <View style={{flexDirection: 'row', height: 45, marginLeft: 15, alignItems: 'center'}}>
                        <Image source={require('../img/ic_merchants_location.png')} style={{width: 18, height: 22.5}}/>
                        <Text style={{flex: 1, fontSize: 12, marginLeft: 10}}>上海徐家汇区123号</Text>
                        <Image source={require('../img/ic_center_right_arrow.png')} style={{width: 11.2, height: 19.6, marginRight: 12}}/>
                    </View>
                    <View style={{height: 1, marginLeft: 12, backgroundColor: '#cccccc'}}></View>
                     <View style={{flexDirection: 'row', height: 45, marginLeft: 15, alignItems: 'center'}}>
                        <Image source={require('../img/ic_merchants_phone.png')} style={{width: 15.75, height: 18}}/>
                        <Text style={{flex: 1, fontSize: 12, marginLeft: 10}}>+18866897715</Text>
                        <Image source={require('../img/ic_center_right_arrow.png')} style={{width: 11.2, height: 19.6, marginRight: 12}}/>
                     </View>
                    <View style={{height: 1, backgroundColor: '#cccccc'}}></View>
                    <View style={{flex: 1, height: 45, backgroundColor: '#FF0000', marginLeft: 40, marginRight: 40, marginTop: 10, alignItems: 'center', justifyContent: 'center'}}>
                         <Text style={{color: '#ffffff', fontSize: 16}}>
                            开始点餐
                         </Text>
                    </View>
                    <View style={{flexDirection: 'row', height: 40, marginTop: 10, alignItems: 'center', backgroundColor: '#D3D3D3'}}>
                        <Text style={{flex: 1, marginLeft: 10, fontSize: 16, color: '#999999'}}>评论信息</Text>
                         <Text style={{marginRight: 3, fontSize: 14, color: '#999999'}}>添加评论</Text>
                        <Image source={require('../img/ic_center_right_arrow.png')} style={{width: 11.2, height: 19.6, marginRight: 12}}/>
                     </View>
                </View>
        );
    }

    renderBottomComment(){
        return(
            <View style={{height: height, paddingBottom: 35,backgroundColor: '#ff0000'}}>
                {this.renderContent(this.state.dataSource.cloneWithRows(
                    this.state.commentList === undefined ? [] : this.state.commentList
                ))}
            </View>
        );
    }

    render(){
        return(
                <View>
                    {/*标题栏*/}
                    <View style={{flexDirection: 'row', height: 48, backgroundColor: '#A0522D', alignItems: 'center'}}>
                        <TouchableOpacity
                            onPress={() => {NativeModules.IntentModule.finshActivity()}}
                            style={{width: 48, height: 48, alignItems: 'center', justifyContent: 'center'}}>
                            <Image source={require('../img/ic_center_back.png')} style={{width: 13, height: 20}}/>
                        </TouchableOpacity>
                        <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
                            <Text style={{textAlign: 'center', color: '#ffffff', fontSize: 18}}>商家详情</Text> 
                        </View>
                        <View style={{height:48,width:48}}/>
                    </View>
                    {this.renderBottomComment()}
                </View>
        );
    }
}