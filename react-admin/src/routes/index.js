/**
 * Created by 叶子 on 2017/8/13.
 */
import React, { Component } from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';
import AllComponents from '../components';
import routesConfig from './config';

export default class CRouter extends Component {
    state = {

    }
    componentWillMount() {
        var user = JSON.parse(localStorage.getItem("user"));
        console.log(user.type == 0);
        if (user && user.type == 0){
            routesConfig["menus"] = routesConfig["menus"].concat([
                {   key: '/app/merchantList', title: '商家列表', icon: 'copy',component: 'MerchantList'    }
            ]);
        }
        this.setState({
            configs: routesConfig
        });
    }
    requireAuth = (permission, component) => {
        const { auth } = this.props;
        const { permissions } = auth.data;
        // const { auth } = store.getState().httpData;
        if (!permissions || !permissions.sessionId) return <Redirect to={'404'} />;
        return component;
    };
    requireLogin = (component, permission) => {
        const { auth } = this.props;
        const { respData } = auth.data;
        var permissions;
        if (respData){
            permissions = respData.data;
            // if (process.env.NODE_ENV === 'production' && !permissions && !permissions.sessionId) { // 线上环境判断是否登录
            //     return <Redirect to={'/login'} />;
            // }
            if (!permissions || !permissions.sessionId) { // 线上环境判断是否登录
                return <Redirect to={'/login'} />;
            }
        }
        return permission ? this.requireAuth(permission, component) : component;
    };
    render() {
        const { configs } = this.state;
        return (
            <Switch>
                {
                    Object.keys(configs).map(key => 
                        configs[key].map(r => {
                            const route = r => {
                                const Component = AllComponents[r.component];
                                return (
                                    <Route
                                        key={r.route || r.key}
                                        exact
                                        path={r.route || r.key}
                                        component={props => r.login ? 
                                            <Component {...props} />
                                            : this.requireLogin(<Component {...props} />, r.auth)}
                                    />
                                )
                            }
                            return r.component ? route(r) : r.subs.map(r => route(r));
                        })
                    )
                }

                <Route render={() => <Redirect to="/404" />} />
            </Switch>
        )
    }
}