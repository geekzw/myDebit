/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { 
    Row, 
    Icon, BackTop, Button
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
    return last ? <span>{route.breadcrumbName}</span> : <Link to={'/'+paths.join('/')}>{route.breadcrumbName}</Link>;
}

class MerchantDetail extends MerchantList {
    constructor(props){
        super(props);
        this.state = {
            id: props.match.params.id,
        }
    }
    // 属性相关
    breadcrumbCustom() {
        return (<BreadcrumbCustom itemRender={itemRender} routes={routes} />);
    }
    tableTitle() {
        return "商家详情";
    }
    // 控件相关
    tableHeaderArea = (
        <Row style={{ marginBottom: 16 }} type="flex" justify="space-between">
            <Button type="primary" onClick={this.reload}
                        disabled={this.state.loading} loading={this.state.loading}
                >
                {this.state.loading ? '正在加载' : '刷新'}
            </Button>
        </Row>
    );
    // 网络相关
    start = (pageNo=this.state.pageNo,pageSize=this.state.pageSize,searchParam='') => {
        (new BackTop({
            target:()=>document.getElementById('rightScroll')
        })).scrollToTop();
        this.setState({ loading: true });
        const { sessionId } = JSON.parse(localStorage.getItem('user'));
        var params = {
            sessionId: sessionId,
            pageNo: pageNo,
            pageSize: pageSize,
            searchParam: searchParam
        }
        // getMerchant(params).then(res => {
        //     console.log(params);
        //     if(res.data){
        //         this.setState({
        //             data: [...res.data.map(val => {
        //                 val.key = val.id;
        //                 return val;
        //             })],
        //             loading: false,
        //             totalCount: res.totalCount,
        //             pageNo: res.pageNo,
        //             pageSize: res.pageSize,
        //         });
        //     }else{
        //         this.setState({
        //             data: [],
        //             loading: false,
        //             totalCount: 0
        //         });
        //     }
        // }).catch(err=>notifyPop('错误',err,<Icon type="frown" />),5);
    };
}

export default MerchantDetail;