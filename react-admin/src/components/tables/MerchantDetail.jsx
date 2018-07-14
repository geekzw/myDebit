/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { 
    Row, Card, 
    Icon, BackTop, Button, Col
} from 'antd';
import { Link } from 'react-router-dom';
import { getMerchantStream, notifyPop } from '../../axios';
import '../../style/table.less';
import MerchantList, {} from './MerchantList';
import BreadcrumbCustom from '../BreadcrumbCustom';

const routes = [{
    path: 'app',
    breadcrumbName: '首页'
  }, {
    path: 'merchantList',
    breadcrumbName: '商家列表'
  }, {
    path: 'merchantDetail',
    breadcrumbName: '商家详情'
}];
function itemRender(route, params, routes, paths) {
    const last = routes.indexOf(route) === routes.length - 1;
    if(route.path=='merchantList'&&(JSON.parse(localStorage.getItem('user'))||{}).type==1){
        return last;
    }
    return last ? <span>{route.breadcrumbName}</span> : <Link to={'/'+paths.join('/')}>{route.breadcrumbName}</Link>;
}

const types = {
    "1" : "Android",
    "2" : "iOS",
    "3" : "H5",
}
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 
function GMTToStr(time){
    if(!time){ return '-'; }
    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(time))||'-';
}

class MerchantDetail extends MerchantList {
    state = {
        ...(this.state)||{},
        data: []
    };
    // 属性相关
    breadcrumbCustom() {
        return (<BreadcrumbCustom itemRender={itemRender} routes={routes} />);
    }
    tableTitle() {
        return "商家详情";
    }
    headerInfoArea = (registerCount,intentCount,accurateCount) => {
        var control = (title,number,icon) => (
            <Col className="gutter-box" md={7}>
                <Card bordered={1}>
                    <div className="clear y-center">
                        <div className="pull-left mr-m">
                            <Icon type={icon} className="text-2x text-danger" />
                        </div>
                        <div className="clear">
                            <div className="text-muted">{title}</div>
                            <h2>{this.state.loading?'正在加载...':number}</h2>
                        </div>
                    </div>
                </Card>
            </Col>
        );
        return (
            <Row type="flex" justify="space-between">
                {control('注册用户',registerCount,'line-chart')}
                {control('意向用户',intentCount,'line-chart')}
                {control('精准用户',accurateCount,'line-chart')}
            </Row>
        );
    };
    // 控件相关
    tableHeaderArea = () => (
        <div>
            {this.headerInfoArea(this.state.registerCount,this.state.intentCount,this.state.accurateCount)}
            <Row style={{ marginBottom: 16, marginTop: 12 }} type="flex" justify="space-between">
                <Button type="primary" onClick={this.reload}
                            disabled={this.state.loading} loading={this.state.loading}
                    >
                    {this.state.loading ? '正在加载' : '刷新'}
                </Button>
            </Row>
        </div>
    );
    columns = [
        {
            title: '用户id',
            dataIndex: 'id',
            width: 80
        },
        {
            title: '用户手机',
            dataIndex: 'username',
            width: 160
        },
        {
            title: '注册时间',
            dataIndex: 'gmtCreate',
            width: 160,
            render: (text) => GMTToStr(text)
        },
        {
            title: '是否登录',
            dataIndex: 'isLogin',
            width: 80,
            render: (text) => text==1 ? '是' : '否'
        },
        {
            title: '首次登录',
            dataIndex: 'fistLoginTime',
            width: 160,
            render: (text) => GMTToStr(text)
        },
        {
            title: '列表点击',
            dataIndex: 'listCount',
            width: 80,
            render: (text) => (text||0)+'次'
        },
        {
            title: '详情点击',
            dataIndex: 'detailCount',
            width: 80,
            render: (text) => (text||0)+'次'
        },
        {
            title: '设备来源',
            dataIndex: 'fromWhere',
            width: 80,
            render: (text) => types[text]
        },
    ];
    // 网络相关
    reload = () => {
        const { pageNo, pageSize } = this.state;
        this.start(pageNo,pageSize);
    };
    start = (
        pageNo=this.state.pageNo,
        pageSize=this.state.pageSize,
        merchantId=this.props.match.params.id||JSON.parse(localStorage.getItem('user')).id
    ) => {
        (new BackTop({
            target:()=>document.getElementById('rightScroll')
        })).scrollToTop();
        this.setState({ loading: true });
        var params = {
            merchantId: merchantId,
            pageNo: pageNo,
            pageSize: pageSize
        }
        getMerchantStream(params).then(res => {
            console.log(params);
            console.log(res);
            if(!res.success){
                notifyPop('错误',res.desc,<Icon type="frown" />,0);
            }
            if(res.data){
                this.setState({
                    data: [...res.data.streamInfos.map(val => {
                        val.key = val.id;
                        return val;
                    })],
                    loading: false,
                    totalCount: res.totalCount,
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                    registerCount: res.data.registerCount,
                    intentCount: res.data.intentCount,
                    accurateCount: res.data.accurateCount
                });
            }else{
                this.setState({
                    data: [],
                    loading: false,
                    totalCount: 0
                });
            }
        }).catch(err=>notifyPop('错误',err,<Icon type="frown" />,5));
    };
}

export default MerchantDetail;