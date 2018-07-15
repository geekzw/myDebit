/**
 * Created by 叶子 on 2017/7/30.
 * http通用工具函数
 */
import axios from 'axios';
// import { message } from 'antd';

export function dateFtt(fmt,date)   
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
export function GMTToStr(time){
    if(!time){ return '-'; }
    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(time))||'-';
}

const getHeader = () => {
    var user = JSON.parse(localStorage.getItem('user'));
    if(user && user.sessionId){ 
        return {'sessionId':user.sessionId}; 
    }
    return { };
}

/**
 * 公用get请求
 * @param url       接口全地址
 * @param params       参数
 */
export const get = (url,params) => {
    return axios.get(url,{params: params,headers: getHeader()}).then(response=>response.data).catch(err => err);
};
/**
 * 公用get请求
 * @param url       接口地址
 * @param msg       接口异常提示
 * @param headers   接口所需header配置
 */
// export const get = ({url, msg = '接口异常', headers}) =>
//     axios.get(url, headers).then(res => res.data).catch(err => {
//        console.log(err);
//        message.warn(msg);
//     });

/**
 * 公用post请求
 * @param url       接口地址
 * @param datas      参数
 */
export const post = (url,datas) => {
    return axios({
        method: "POST",
        url: url,
        data: datas,
        headers: getHeader()
    }).then(response=>response.data).catch(err => err);
};
/**
 * 公用post请求
 * @param url       接口地址
 * @param data      接口参数
 * @param msg       接口异常提示
 * @param headers   接口所需header配置
 */
// export const post = ({url, data, msg = '接口异常', headers}) =>
//     axios.post(url, data, headers).then(res => res.data).catch(err => {
//         console.log(err);
//         message.warn(msg);
//     });

    
    