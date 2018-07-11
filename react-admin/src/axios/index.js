/**
 * Created by hao.cheng on 2017/4/16.
 */
import axios from 'axios';
import { get, post } from './tools';
import * as config from './config';

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

// 接口
// 登录
// export const login = (params) => post({url: 'merchant/loginPc.json', params: params });
export const login = (params) => 
axios.post('http://localhost:8080/merchant/loginPc.json', params).then(function (response) {
    return response.data;
}).catch(function (error) {
    console.log(error);
});

// 商家列表
export const getMerchant = (params) => 
    axios.get('http://localhost:8080/auth/merchant/getMerchantList.json', {params: params}).then(function (response) {
        return response.data;
    }).catch(function (error) {
        console.log(error);
    });