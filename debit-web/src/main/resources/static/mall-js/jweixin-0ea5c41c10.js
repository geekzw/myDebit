!function(e,n){"function"==typeof define&&(define.amd||define.cmd)?define(function(){return n(e)}):n(e,!0)}(this,function(o,e){function c(n,e,i){o.WeixinJSBridge?WeixinJSBridge.invoke(n,r(e),function(e){a(n,e,i)}):d(n,i)}function n(n,i,t){o.WeixinJSBridge?WeixinJSBridge.on(n,function(e){t&&t.trigger&&t.trigger(e),a(n,e,i)}):d(n,t||i)}function r(e){return(e=e||{}).appId=x.appId,e.verifyAppId=x.appId,e.verifySignType="sha1",e.verifyTimestamp=x.timestamp+"",e.verifyNonceStr=x.nonceStr,e.verifySignature=x.signature,e}function i(e){return{timeStamp:e.timestamp+"",nonceStr:e.nonceStr,package:e.package,paySign:e.paySign,signType:e.signType||"SHA1"}}function a(e,n,i){"openEnterpriseChat"==e&&(n.errCode=n.err_code),delete n.err_code,delete n.err_desc,delete n.err_detail;var t=n.errMsg;t||(t=n.err_msg,delete n.err_msg,t=function(e,n){var i=e,t=f[i];t&&(i=t);var o="ok";if(n){var r=n.indexOf(":");"confirm"==(o=n.substring(r+1))&&(o="ok"),"failed"==o&&(o="fail"),-1!=o.indexOf("failed_")&&(o=o.substring(7)),-1!=o.indexOf("fail_")&&(o=o.substring(5)),("access denied"==(o=(o=o.replace(/_/g," ")).toLowerCase())||"no permission to execute"==o)&&(o="permission denied"),"config"==i&&"function not exist"==o&&(o="ok"),""==o&&(o="fail")}return n=i+":"+o}(e,t),n.errMsg=t),(i=i||{})._complete&&(i._complete(n),delete i._complete),t=n.errMsg||"",x.debug&&!i.isInnerInvoke&&alert(JSON.stringify(n));var o=t.indexOf(":");switch(t.substring(o+1)){case"ok":i.success&&i.success(n);break;case"cancel":i.cancel&&i.cancel(n);break;default:i.fail&&i.fail(n)}i.complete&&i.complete(n)}function s(e){if(e){for(var n=0,i=e.length;n<i;++n){var t=e[n],o=p[t];o&&(e[n]=o)}return e}}function d(e,n){if(!(!x.debug||n&&n.isInnerInvoke)){var i=f[e];i&&(e=i),n&&n._complete&&delete n._complete,console.log('"'+e+'",',n||"")}}function u(){return(new Date).getTime()}function l(e){v&&(o.WeixinJSBridge?e():t.addEventListener&&t.addEventListener("WeixinJSBridgeReady",e,!1))}if(!o.jWeixin){var p={config:"preVerifyJSAPI",onMenuShareTimeline:"menu:share:timeline",onMenuShareAppMessage:"menu:share:appmessage",onMenuShareQQ:"menu:share:qq",onMenuShareWeibo:"menu:share:weiboApp",onMenuShareQZone:"menu:share:QZone",previewImage:"imagePreview",getLocation:"geoLocation",openProductSpecificView:"openProductViewWithPid",addCard:"batchAddCard",openCard:"batchViewCard",chooseWXPay:"getBrandWCPayRequest",openEnterpriseRedPacket:"getRecevieBizHongBaoRequest",startSearchBeacons:"startMonitoringBeacons",stopSearchBeacons:"stopMonitoringBeacons",onSearchBeacons:"onBeaconsInRange",consumeAndShareCard:"consumedShareCard",openAddress:"editAddress"},f=function(){var e={};for(var n in p)e[p[n]]=n;return e}(),t=o.document,m=t.title,g=navigator.userAgent.toLowerCase(),h=navigator.platform.toLowerCase(),S=!(!h.match("mac")&&!h.match("win")),I=-1!=g.indexOf("wxdebugger"),v=-1!=g.indexOf("micromessenger"),y=-1!=g.indexOf("android"),_=-1!=g.indexOf("iphone")||-1!=g.indexOf("ipad"),w=(O=g.match(/micromessenger\/(\d+\.\d+\.\d+)/)||g.match(/micromessenger\/(\d+\.\d+)/))?O[1]:"",T={initStartTime:u(),initEndTime:0,preVerifyStartTime:0,preVerifyEndTime:0},k={version:1,appId:"",initTime:0,preVerifyTime:0,networkType:"",isPreVerifyOk:1,systemType:_?1:y?2:-1,clientVersion:w,url:encodeURIComponent(location.href)},x={},M={_completes:[]},C={state:0,data:{}};l(function(){T.initEndTime=u()});var V=!1,P=[],A={config:function(e){d("config",x=e);var t=!1!==x.check;l(function(){if(t)c(p.config,{verifyJsApiList:s(x.jsApiList)},function(){M._complete=function(e){T.preVerifyEndTime=u(),C.state=1,C.data=e},M.success=function(e){k.isPreVerifyOk=0},M.fail=function(e){M._fail?M._fail(e):C.state=-1};var t=M._completes;return t.push(function(){!function(e){if(!(S||I||x.debug||w<"6.0.2"||k.systemType<0)){var i=new Image;k.appId=x.appId,k.initTime=T.initEndTime-T.initStartTime,k.preVerifyTime=T.preVerifyEndTime-T.preVerifyStartTime,A.getNetworkType({isInnerInvoke:!0,success:function(e){k.networkType=e.networkType;var n="https://open.weixin.qq.com/sdk/report?v="+k.version+"&o="+k.isPreVerifyOk+"&s="+k.systemType+"&c="+k.clientVersion+"&a="+k.appId+"&n="+k.networkType+"&i="+k.initTime+"&p="+k.preVerifyTime+"&u="+k.url;i.src=n}})}}()}),M.complete=function(e){for(var n=0,i=t.length;n<i;++n)t[n]();M._completes=[]},M}()),T.preVerifyStartTime=u();else{C.state=1;for(var e=M._completes,n=0,i=e.length;n<i;++n)e[n]();M._completes=[]}}),x.beta&&(A.invoke||(A.invoke=function(e,n,i){o.WeixinJSBridge&&WeixinJSBridge.invoke(e,r(n),i)},A.on=function(e,n){o.WeixinJSBridge&&WeixinJSBridge.on(e,n)}))},ready:function(e){0!=C.state?e():(M._completes.push(e),!v&&x.debug&&e())},error:function(e){w<"6.0.2"||(-1==C.state?e(C.data):M._fail=e)},checkJsApi:function(e){c("checkJsApi",{jsApiList:s(e.jsApiList)},(e._complete=function(e){if(y){var n=e.checkResult;n&&(e.checkResult=JSON.parse(n))}e=function(e){var n=e.checkResult;for(var i in n){var t=f[i];t&&(n[t]=n[i],delete n[i])}return e}(e)},e))},onMenuShareTimeline:function(e){n(p.onMenuShareTimeline,{complete:function(){c("shareTimeline",{title:e.title||m,desc:e.title||m,img_url:e.imgUrl||"",link:e.link||location.href,type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareAppMessage:function(e){n(p.onMenuShareAppMessage,{complete:function(){c("sendAppMessage",{title:e.title||m,desc:e.desc||"",link:e.link||location.href,img_url:e.imgUrl||"",type:e.type||"link",data_url:e.dataUrl||""},e)}},e)},onMenuShareQQ:function(e){n(p.onMenuShareQQ,{complete:function(){c("shareQQ",{title:e.title||m,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareWeibo:function(e){n(p.onMenuShareWeibo,{complete:function(){c("shareWeiboApp",{title:e.title||m,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},onMenuShareQZone:function(e){n(p.onMenuShareQZone,{complete:function(){c("shareQZone",{title:e.title||m,desc:e.desc||"",img_url:e.imgUrl||"",link:e.link||location.href},e)}},e)},startRecord:function(e){c("startRecord",{},e)},stopRecord:function(e){c("stopRecord",{},e)},onVoiceRecordEnd:function(e){n("onVoiceRecordEnd",e)},playVoice:function(e){c("playVoice",{localId:e.localId},e)},pauseVoice:function(e){c("pauseVoice",{localId:e.localId},e)},stopVoice:function(e){c("stopVoice",{localId:e.localId},e)},onVoicePlayEnd:function(e){n("onVoicePlayEnd",e)},uploadVoice:function(e){c("uploadVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadVoice:function(e){c("downloadVoice",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},translateVoice:function(e){c("translateVoice",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},chooseImage:function(e){c("chooseImage",{scene:"1|2",count:e.count||9,sizeType:e.sizeType||["original","compressed"],sourceType:e.sourceType||["album","camera"]},(e._complete=function(e){if(y){var n=e.localIds;n&&(e.localIds=JSON.parse(n))}},e))},getLocation:function(e){},previewImage:function(e){c(p.previewImage,{current:e.current,urls:e.urls},e)},uploadImage:function(e){c("uploadImage",{localId:e.localId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},downloadImage:function(e){c("downloadImage",{serverId:e.serverId,isShowProgressTips:0==e.isShowProgressTips?0:1},e)},getLocalImgData:function(e){!1===V?(V=!0,c("getLocalImgData",{localId:e.localId},(e._complete=function(e){if(V=!1,0<P.length){var n=P.shift();wx.getLocalImgData(n)}},e))):P.push(e)},getNetworkType:function(e){c("getNetworkType",{},(e._complete=function(e){e=function(e){var n=e.errMsg;e.errMsg="getNetworkType:ok";var i=e.subtype;if(delete e.subtype,i)e.networkType=i;else{var t=n.indexOf(":"),o=n.substring(t+1);switch(o){case"wifi":case"edge":case"wwan":e.networkType=o;break;default:e.errMsg="getNetworkType:fail"}}return e}(e)},e))},openLocation:function(e){c("openLocation",{latitude:e.latitude,longitude:e.longitude,name:e.name||"",address:e.address||"",scale:e.scale||28,infoUrl:e.infoUrl||""},e)},getLocation:function(e){c(p.getLocation,{type:(e=e||{}).type||"wgs84"},(e._complete=function(e){delete e.type},e))},hideOptionMenu:function(e){c("hideOptionMenu",{},e)},showOptionMenu:function(e){c("showOptionMenu",{},e)},closeWindow:function(e){c("closeWindow",{},e=e||{})},hideMenuItems:function(e){c("hideMenuItems",{menuList:e.menuList},e)},showMenuItems:function(e){c("showMenuItems",{menuList:e.menuList},e)},hideAllNonBaseMenuItem:function(e){c("hideAllNonBaseMenuItem",{},e)},showAllNonBaseMenuItem:function(e){c("showAllNonBaseMenuItem",{},e)},scanQRCode:function(e){c("scanQRCode",{needResult:(e=e||{}).needResult||0,scanType:e.scanType||["qrCode","barCode"]},(e._complete=function(e){if(_){var n=e.resultStr;if(n){var i=JSON.parse(n);e.resultStr=i&&i.scan_code&&i.scan_code.scan_result}}},e))},openAddress:function(e){c(p.openAddress,{},(e._complete=function(e){var n;(n=e).postalCode=n.addressPostalCode,delete n.addressPostalCode,n.provinceName=n.proviceFirstStageName,delete n.proviceFirstStageName,n.cityName=n.addressCitySecondStageName,delete n.addressCitySecondStageName,n.countryName=n.addressCountiesThirdStageName,delete n.addressCountiesThirdStageName,n.detailInfo=n.addressDetailInfo,delete n.addressDetailInfo,e=n},e))},openProductSpecificView:function(e){c(p.openProductSpecificView,{pid:e.productId,view_type:e.viewType||0,ext_info:e.extInfo},e)},addCard:function(e){for(var n=e.cardList,i=[],t=0,o=n.length;t<o;++t){var r=n[t],a={card_id:r.cardId,card_ext:r.cardExt};i.push(a)}c(p.addCard,{card_list:i},(e._complete=function(e){var n=e.card_list;if(n){for(var i=0,t=(n=JSON.parse(n)).length;i<t;++i){var o=n[i];o.cardId=o.card_id,o.cardExt=o.card_ext,o.isSuccess=!!o.is_succ,delete o.card_id,delete o.card_ext,delete o.is_succ}e.cardList=n,delete e.card_list}},e))},chooseCard:function(e){c("chooseCard",{app_id:x.appId,location_id:e.shopId||"",sign_type:e.signType||"SHA1",card_id:e.cardId||"",card_type:e.cardType||"",card_sign:e.cardSign,time_stamp:e.timestamp+"",nonce_str:e.nonceStr},(e._complete=function(e){e.cardList=e.choose_card_info,delete e.choose_card_info},e))},openCard:function(e){for(var n=e.cardList,i=[],t=0,o=n.length;t<o;++t){var r=n[t],a={card_id:r.cardId,code:r.code};i.push(a)}c(p.openCard,{card_list:i},e)},consumeAndShareCard:function(e){c(p.consumeAndShareCard,{consumedCardId:e.cardId,consumedCode:e.code},e)},chooseWXPay:function(e){c(p.chooseWXPay,i(e),e)},openEnterpriseRedPacket:function(e){c(p.openEnterpriseRedPacket,i(e),e)},startSearchBeacons:function(e){c(p.startSearchBeacons,{ticket:e.ticket},e)},stopSearchBeacons:function(e){c(p.stopSearchBeacons,{},e)},onSearchBeacons:function(e){n(p.onSearchBeacons,e)},openEnterpriseChat:function(e){c("openEnterpriseChat",{useridlist:e.userIds,chatname:e.groupName},e)}},L=1,B={};return t.addEventListener("error",function(e){if(!y){var n=e.target,i=n.tagName,t=n.src;if("IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i)if(-1!=t.indexOf("wxlocalresource://")){e.preventDefault(),e.stopPropagation();var o=n["wx-id"];if(o||(o=L++,n["wx-id"]=o),B[o])return;B[o]=!0,wx.ready(function(){wx.getLocalImgData({localId:t,success:function(e){n.src=e.localData}})})}}},!0),t.addEventListener("load",function(e){if(!y){var n=e.target,i=n.tagName;if(n.src,"IMG"==i||"VIDEO"==i||"AUDIO"==i||"SOURCE"==i){var t=n["wx-id"];t&&(B[t]=!1)}}},!0),e&&(o.wx=o.jWeixin=A),A}var O});