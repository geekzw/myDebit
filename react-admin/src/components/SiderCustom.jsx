/**
 * Created by hao.cheng on 2017/4/13.
 */
import React, { Component } from 'react';
import { Layout, Icon } from 'antd';
import { withRouter } from 'react-router-dom';
import routes from '../routes/config';
import SiderMenu from './SiderMenu';
import { notifyPop } from '../axios';

const { Sider } = Layout;

class SiderCustom extends Component {
    static getDerivedStateFromProps (props, state) { 
        const state1 = SiderCustom.setMenuOpen(props);
        const state2 = SiderCustom.onCollapse(props.collapsed);
        return {
            ...state1,
            ...state2,
            ...state,
        }
    }
    static setMenuOpen = props => {
        const { pathname } = props.location;
        return {
            openKey: pathname.substr(0, pathname.lastIndexOf('/')),
            selectedKey: pathname
        };
    };
    static onCollapse = (collapsed) => {
        console.log(collapsed);
        return {
            collapsed,
            firstHide: collapsed,
            mode: collapsed ? 'vertical' : 'inline',
        };
    };
    state = {
        collapsed: false,
        mode: 'inline',
        openKey: '',
        selectedKey: '',
        firstHide: true,        // 点击收缩菜单，第一次隐藏展开子菜单，openMenu时恢复
    };
    componentDidMount() {
        // this.setMenuOpen(this.props);
        const state = SiderCustom.setMenuOpen(this.props);
        this.setState(state);
    }
    // componentWillReceiveProps(nextProps) {
    //     console.log(nextProps);
    //     this.onCollapse(nextProps.collapsed);
    //     this.setMenuOpen(nextProps)
    // }
    menuClick = e => {
        localStorage.removeItem('editingBorrow'); // 点击借贷列表的时候清除缓存
        this.setState({
            selectedKey: e.key
        });
        console.log(this.state);
        const { popoverHide } = this.props;     // 响应式布局控制小屏幕点击菜单时隐藏菜单操作
        popoverHide && popoverHide();
    };
    openMenu = v => {
        console.log(v);
        this.setState({
            openKey: v[v.length - 1],
            firstHide: false,
        })
    };
    getMenus(){
        var user = JSON.parse(localStorage.getItem("user"));
        if(!user){ 
            this.props.history.push('/login');
            notifyPop('提醒','请先登录',<Icon type="frown-o" color="red" />,15,'error');
            return []; //如果没有登录，不允许显示菜单，跳转至登录页
        }
        var menus = [];
        for(var i=0;i<routes.menus.length;i++){
            var r = routes.menus[i];
            if(r.permission){
                if(r.permission.indexOf(user.type)<0){
                    continue;//如果没有读取该菜单的权限，跳过该菜单
                }
            }
            if(r.subs){
                var rsubs = [];
                for(var j=0;j<r.subs.length;j++){
                    var q = r.subs[j];
                    if(q.permission){
                        if(q.permission.indexOf(user.type)<0){
                            continue;//如果没有读取该子菜单的权限，跳过该子菜单
                        }
                    }
                    rsubs.push(q);
                }
                if(rsubs.length==0){
                    continue;//如果子菜单没有任何项，跳过该菜单
                }
                r.subs = rsubs;
            }
            menus.push(r);
        }
        return menus;
    }
    render() {
        return (
            <Sider
                trigger={null}
                breakpoint="lg"
                collapsed={this.props.collapsed}
                style={{ overflowY: 'auto' }}
            >
                <div className="logo" />
                <SiderMenu
                    menus={this.getMenus()}
                    onClick={this.menuClick}
                    theme="dark"
                    mode="inline"
                    selectedKeys={[this.state.selectedKey]}
                    openKeys={this.state.firstHide ? null : [this.state.openKey]}
                    onOpenChange={this.openMenu}
                />
                <style>
                    {`
                    #nprogress .spinner{
                        left: ${this.state.collapsed ? '70px' : '206px'};
                        right: 0 !important;
                    }
                    `}
                </style>
            </Sider>
        )
    }
}

export default withRouter(SiderCustom);