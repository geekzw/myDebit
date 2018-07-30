/**
 * Created by hao.cheng on 2017/4/16.
 */
import axios from 'axios';
import React from 'react';
import { get, post } from './tools';
// import * as config from './config';
import { notification, Icon } from 'antd';

export const getPros = () => axios.post('http://api.xitu.io/resources/github', {
    category: "trending",
    period: "day",
    lang: "javascript",
    offset: 0,
    limit: 30
}).then(function (response) {
    return response.data;
}).catch(function (error) {
    console.log(error);
});

export const npmDependencies = () => axios.get('./npm.json').then(res => res.data).catch(err => console.log(err));

export const weibo = () => axios.get('./weibo.json').then(res => res.data).catch(err => console.log(err));

// const GIT_OAUTH = 'https://github.com/login/oauth';
// export const gitOauthLogin = () => axios.get(`${GIT_OAUTH}/authorize?client_id=792cdcd244e98dcd2dee&redirect_uri=http://localhost:3006/&scope=user&state=reactAdmin`);
// export const gitOauthToken = code => axios.post('https://cors-anywhere.herokuapp.com/' + GIT_OAUTH + '/access_token', {...{client_id: '792cdcd244e98dcd2dee',
//     client_secret: '81c4ff9df390d482b7c8b214a55cf24bf1f53059', redirect_uri: 'http://localhost:3006/', state: 'reactAdmin'}, code: code}, {headers: {Accept: 'application/json'}})
//     .then(res => res.data).catch(err => console.log(err));
// export const gitOauthInfo = access_token => axios({
//     method: 'get',
//     url: 'https://api.github.com/user?access_token=' + access_token,
// }).then(res => res.data).catch(err => console.log(err));

export const notifyPop = (title,desc,icon,duration=4.5,type) => {
    notification[type||'open']({
        message: title,
        description: (
            <div>
                <p>
                    { desc }
                </p>
            </div>
        ),
        icon: icon,
        duration: duration,
      });
};

const baseURL = '';

// 接口
// 登录
// export const login = (params) => post({url: 'merchant/loginPc.json', params: params });
export const login = (params) => 
    post(
        baseURL+'/merchant/loginPc.json', 
        params
    );
// 主页
export const home = () =>
    get(
        baseURL+'/auth/pc/mainView.json',
    );

// 借贷列表
export const getBorrowList = (params) =>
    get(
        baseURL+'/auth/getBorrowList.json', 
        params
    );
export const editBorrow = (params) => 
    post(
        baseURL+'/auth/editBorrow.json', 
        params
    );
export const deleteBorrow = (params) => 
    post(
        baseURL+'/auth/deleteBorrow.json', 
        params
    );

// Banner列表
export const getBannerList = (params) =>
    get(
        baseURL+'/auth/getBannerList.json', 
        params
    );
export const editBanner = (params) => 
    post(
        baseURL+'/auth/editBanner.json', 
        params
    );
export const deleteBanner = (params) => 
    post(
        baseURL+'/auth/deleteBanner.json', 
        params
    );
// 商家列表
export const getMerchant = (params) => 
    get(
        baseURL+'/auth/merchant/getMerchantList.json', 
        params
    );

export const editMerchant = (params) =>
    post(
        baseURL+'/auth/merchant/editMerchant.json', 
        params
    );

export const deleteMerchant = (params) =>
    post(
        baseURL+'/auth/merchant/deleteMerchant.json', 
        params
    );
// 商家流量
export const getMerchantStream = (params) =>
    get(
        baseURL+'/auth/merchant/getMerchantStream.json',
        params
    );
// 规则列表
export const getAnalyzeRule = (params) =>
    get(
        baseURL+'/auth/getAnalyzeRule.json',
        params
    );
export const editAnalyzeRule = (params) =>
    post(
        baseURL+'/auth/editAnalyzeRule.json', 
        params
    );