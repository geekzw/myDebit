!function(t,r){"object"==typeof exports&&"object"==typeof module?module.exports=r():"function"==typeof define&&define.amd?define([],r):"object"==typeof exports?exports.initNECaptcha=r():t.initNECaptcha=r()}(this,function(){return function(n){function e(t){if(o[t])return o[t].exports;var r=o[t]={exports:{},id:t,loaded:!1};return n[t].call(r.exports,r,r.exports,e),r.loaded=!0,r.exports}var o={};return e.m=n,e.c=o,e.p="/",e(0)}([function(t,r,n){n(15),n(17),n(16),n(14);var e=n(12);t.exports=e},function(t,r){function n(t,r,n){return r in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t}function e(t,r,n){this.name="CaptchaError",this.code=t,this.message=t+"("+a[t]+")"+(r?" - "+r:""),Error.captureStackTrace?Error.captureStackTrace(this,this.constructor):this.stack=(new Error).stack,this.data=n||{}}var o,i="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t},a=(n(o={},100,"script error"),n(o,200,"business error"),n(o,500,"request error"),n(o,501,"request api error"),n(o,502,"request script error"),n(o,503,"request img error"),n(o,504,"request timeout error"),n(o,1e3,"unknown error"),o);(function(t,r){function n(){}n.prototype=r.prototype,t.prototype=new n,t.prototype.constructor=t})(e,Error),e.prototype.toString=function(){var t=String(this.stack);return 0===t.indexOf("CaptchaError:")?t:this.name+": "+this.message+(t?"\n    "+t:"")},e.set=function(t,r){"number"==typeof t&&"string"==typeof r&&(a[t]=r),"object"===(void 0===t?"undefined":i(t))&&t&&Object.assign(a,t)},e.get=function(t){return a[t]},e.remove=function(t){String(t)in a&&delete a[t]},(r=t.exports=e).SCRIPT_ERROR=100,r.BUSINESS_ERROR=200,r.REQUEST_ERROR=500,r.REQUEST_API_ERROR=501,r.REQUEST_SCRIPT_ERROR=502,r.REQUEST_IMG_ERROR=503,r.REQUEST_TIMEOUT_ERROR=504,r.UNKNOWN_ERROR=1e3},function(t,r,n){var l=Object.assign||function(t){for(var r=1;r<arguments.length;r++){var n=arguments[r];for(var e in n)Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}return t},a=n(18),f=n(3),e=n(7),c=n(10),o=0,i={script:function(e,o){var i=this;a(e,{charset:"UTF-8"},function(t,r){if(t){var n=new Error("Failed to load script("+e+")."+t.message);return n.data={url:e,retry:!!i._options.retry},void o(n)}o(r)})},image:function(n,e){var o=this,i=document.createElement("img");i.onload=function(){i.onload=i.onerror=null,e({width:i.width,height:i.height,src:n})},i.onerror=function(t){i.onload=i.onerror=null;var r=new Error("Failed to load image("+n+")."+t.message);r.data={url:n,retry:!!o._options.retry},e(r)},i.src=n},api:function(e,o,t){var i=this;c(e,t,function(t,r){if(t){var n=new Error("Failed to request api("+e+")."+t.message);return n.data={url:e,retry:!!i._options.retry},void o(n)}o(r)},{timeout:this.timeout})}},p=function(t){this.id=t.id||"resource_"+o++,this.type=t.type||"script",this.url=t.url||"",this.payload=t.payload,this.timeout=t.timeout||6e3,this._options=t,f.call(this),this.load(),this.setTimeout()};e(p,f),Object.assign(p.prototype,{load:function(){var r=this,t=i[this.type];t&&t.call(this,this.url,function(t){return r.resolve(t)},this.payload)},addSupport:function(t,r,n){("function"!=typeof i[t]||n)&&(i[t]=r)},setTimeout:function(){var n=this;window.setTimeout(function(){var t=String(n.url),r=new Error("Timeout: failed to request "+n.type+"("+t+").");r.data={url:t},n.resolve(r)},this.timeout)}}),p.SUPPORTS=i;var u=function(s){i.hasOwnProperty(s)&&(p[s]=function(t){var r=t.disableRetry,i=t.onTryError,a=function(t,r){var n={};for(var e in t)0<=r.indexOf(e)||Object.prototype.hasOwnProperty.call(t,e)&&(n[e]=t[e]);return n}(t,["disableRetry","onTryError"]);if(r){var n=a.url;return Array.isArray(n)&&(n=n[0]||""),new p(l({},a,{url:n,type:s}))}var e,c="string"==typeof(e=t.url)?[e,e]:Array.isArray(e)&&1===e.length?e.concat(e):e,u=new f;return function n(){var e=0<arguments.length&&void 0!==arguments[0]?arguments[0]:0,o=c[e];new p(l({},a,{type:s,url:o,retry:0<e})).then(function(t){return u.resolve(t)}).catch(function(t){var r=c.length;e<r-1?n(e+1):e===r-1&&(t.data=l({},t.data,{url:String(c)}),u.resolve(t)),"function"==typeof i&&i(t,{type:s,urls:c,url:o,index:e})})}(0),u})};for(var s in i)u(s);p.all=function(n){var e=0,o=!1,i=new f,a=[];return n.map(function(t,r){t.then(function(t){o||(a[r]=t,++e===n.length&&i.resolve(a))}).catch(function(t){o=!0,i.resolve(t)})}),i},t.exports=p},function(t,r){function n(){this._state=o,this._arg=void 0,this._fullfilledCallback=[],this._rejectedCallback=[]}function e(t){window.setTimeout(t,1)}var o="pending",i="fullfilled",a="rejected";Object.assign(n.prototype,{then:function(t,r){var n=function(t){return"function"==typeof t};return n(t)&&this._fullfilledCallback.push(t),n(r)&&this._rejectedCallback.push(r),this._state!==o&&this._emit(this._state),this},catch:function(t){return this.then(null,t)},finally:function(t){return this.then(t,t)},resolve:function(t){this._state===o&&(t instanceof Error?this._state=a:this._state=i,this._arg=t,this._emit(this._state))},_emit:function(t){var r=this;switch(t){case i:e(function(){r._fullfilledCallback.map(function(t){return t(r._arg)}),r._fullfilledCallback=[],r._rejectedCallback=[]});break;case a:e(function(){r._rejectedCallback.map(function(t){return t(r._arg)}),r._fullfilledCallback=[],r._rejectedCallback=[]})}}}),n.mixin=function(t){if(t){var r=new n;t.then=function(){return r.then.apply(r,arguments)},t.catch=function(){return r.catch.apply(r,arguments)},t.finally=function(){return r.finally.apply(r,arguments)},t.resolve=function(){return r.resolve.apply(r,arguments)}}},t.exports=n},function(t,r){t.exports=function(t){var r=t.protocol,n=void 0===r?"":r,e=t.host,o=void 0===e?"":e,i=t.port,a=void 0===i?"":i,c=t.path,u=void 0===c?"":c,s=t.search,l=void 0===s?"":s,f=t.hash,p=void 0===f?"":f;if(n&&(n=n.replace(/:?\/{0,2}$/,"://")),o){var h=o.match(/^([-0-9a-zA-Z.:]*)(\/.*)?/);o=h[1],u=(h[2]||"")+"/"+u}if(!o&&(n=""),a){if(!o)throw Error('"host" is required, if "port" was provided');a=":"+a}return u&&(u=u.replace(/^\/*|\/+/g,"/")),l&&(l=l.replace(/^\??/,"?")),p&&(p=p.replace(/^#?/,"#")),n+o+a+u+l+p}},function(t,r,n){var a=Object.assign||function(t){for(var r=1;r<arguments.length;r++){var n=arguments[r];for(var e in n)Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}return t},c=n(2),u=n(9);t.exports=function(i){return function(t,r,n,e){Object.assign(r,{referer:u()});var o=a({},i,e,{url:t,payload:r});c.api(o).then(function(t){return n(null,t)}).catch(n)}}},function(t,r,n){var u=Object.assign||function(t){for(var r=1;r<arguments.length;r++){var n=arguments[r];for(var e in n)Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}return t},s=n(8),l=n(1),f=l.REQUEST_SCRIPT_ERROR,p=l.REQUEST_IMG_ERROR,h=l.REQUEST_API_ERROR;t.exports=function(a,c){return function(t,r){var n=r.type,e=r.url,o=r.index,i=new l({script:f,image:p,api:h}[n],t.message,u({},c,{url:e}));s(i,a,{times:o+1})}}},function(t,r){t.exports=function(t,r){function n(){}n.prototype=r.prototype,t.prototype=new n,t.prototype.constructor=t}},function(t,r,n){function e(t,r,n){return r in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t}var o,y=n(5),v=n(4),i=n(1),a=i.REQUEST_SCRIPT_ERROR,c=i.REQUEST_API_ERROR,u=i.REQUEST_IMG_ERROR,m=n(3),_=n(2),g=(e(o={},c,"api"),e(o,u,"image"),e(o,a,"script"),o),E=null;t.exports=function(t,r,n){var e,o,i=r.protocol,a=r.apiServer,c=r.__serverConfig__,u=r.captchaId,s=r.timeout,l=new m,f=(e=a||c.apiServer||["c.dun.163yun.com","c.dun.163.com"],o="/api/v2/collect",Array.isArray(e)?e.map(function(t){return v({protocol:i,host:t,path:o})}):v({protocol:i,host:e,path:o})),p=y({timeout:s,disableRetry:!0}),h=t.data,d=Object.assign({id:u,token:h.token||"",type:g[t.code]||"other",target:h.url||h.resource||"",message:t.toString()},n);return null==window.ip&&(window.ip=function(t,r,n){E={ip:t,dns:n}}),new _({url:i+"://nstool.netease.com/ip.js",timeout:s}).then(function(t){t&&t.parentElement.removeChild(t)}).finally(function(){Object.assign(d,E),p(f,d,function(t,r){if(t||r.error){console&&console.warn("Failed to collect error.");var n=new Error(t?t.message:r.msg);return n.data={url:f},void l.resolve(n)}l.resolve()})}),l}},function(t,r){t.exports=function(){return location.href.replace(/\?[\s\S]*/,"").substring(0,128)}},function(t,r){function d(){}var y="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t},v=0;t.exports=function(t,r,n,e){function o(){i.parentNode&&i.parentNode.removeChild(i),window[s]=d,a&&clearTimeout(a)}"object"===(void 0===n?"undefined":y(n))&&(e=n,n=null),"function"==typeof r&&(n=r,r=null),e||(e={});var i,a,c=Math.random().toString(36).slice(2,9),u=e.prefix||"__JSONP",s=e.name||u+"_"+c+"_"+v++,l=e.param||"callback",f=e.timeout||6e3,p=window.encodeURIComponent,h=document.getElementsByTagName("script")[0]||document.head;return f&&(a=setTimeout(function(){o(),n&&n(new Error("Timeout"))},f)),window[s]=function(t){o(),n&&n(null,t)},r&&(t=t.split("?")[0]),t=(t+=(~t.indexOf("?")?"&":"?")+function(t){var r=[];for(var n in t)t.hasOwnProperty(n)&&r.push(p(n)+"="+p(t[n]));return r.join("&")}(r)+"&"+l+"="+p(s)).replace("?&","?"),(i=document.createElement("script")).type="text/javascript",i.src=t,h.parentNode.insertBefore(i,h),function(){window[s]&&o()}}},function(t,r,n){function e(){this._events={}}var o=n(3);t.exports=e,Object.assign(e.prototype,{on:function(t,r){var n=this._events;return n[t]||(n[t]=[]),n[t].push(r),this},once:function(r,n){var e=this;return this.on(r,function t(){n.apply(void 0,arguments),e.off(r,t)})},off:function(t,r){if(t)if(t&&!r)this._events[t]=[];else{var n=this._events[t]||[],e=n.indexOf(r);-1<e&&n.splice(e,1)}else this._events={};return this},emit:function(t){for(var r=arguments.length,i=Array(1<r?r-1:0),n=1;n<r;n++)i[n-1]=arguments[n];var a=this._events[t]||[],c=new o,u={};return function r(n){var t=a[n];if(t){var e=!1,o={async:function(){return e=!0,function(t){return t instanceof Error?void c.resolve(t):void r(n+1)}}};t.call.apply(t,[o].concat(i,[u])),!e&&r(n+1)}else c.resolve(u)}(0),c}})},function(t,r,n){function s(r,n,e){n=n||function(){},e=e||function(t){console&&console.error('[NECaptcha] initNECaptcha(config, onload, onerror) has thrown an error. If needed, handle it yourself in callback "onerror".\n',t)};var t=window.location.protocol.replace(":",""),o={protocol:"http"===t||"https"===t?t:"https",timeout:6e3};r=Object.assign({},o,r);var i,a,c="loader_plugins",u=m[c]||f.script({id:c,url:(i=r.staticServer||["cstaticdun.126.net","cstatic.dun.163yun.com"],a="plugins"+(v?"":".min")+".js",Array.isArray(i)?i.map(function(t){return p({protocol:r.protocol,host:t,path:a})}):p({protocol:r.protocol,host:i,path:a})),timeout:r.timeout,onTryError:y(r)});!m[c]&&(m[c]=u),u.then(function(){var t=new l({captchaConfig:r,cache:m});t._hooks={onload:n,onerror:e},s.apply(t),t.run()}).catch(function(t){m[c]=null,e(new h(d,t.message,t.data))})}var l=n(13),f=n(2),e=n(3),p=n(4),h=n(1),d=h.REQUEST_SCRIPT_ERROR,y=n(6),v=!1,m={};s.use=function(t){this._plugins||(this._plugins=[]);var r=t.constructor,n=!!r.singleton;-1<this._plugins.map(function(t){return t.constructor}).indexOf(r)&&n||this._plugins.push(t)},s.apply=function(r){this._plugins&&this._plugins.map(function(t){return t.apply(r)})},s.VERSION="2.1.1",s.ResourceLoader=f,s.Thenable=e,s.CaptchaError=h,t.exports=s},function(t,r,n){function e(t,r){if(!t)throw new Error("[NECaptcha Loader] "+r)}function o(t){a.call(this),e(t.captchaConfig,'option "captchaConfig" is required.'),e(t.cache,'option "cache" is required.'),this._captchaConfig=t.captchaConfig,this._captchaHooks=t.captchaHooks,this._cache=t.cache,this._error=null}var p=Object.assign||function(t){for(var r=1;r<arguments.length;r++){var n=arguments[r];for(var e in n)Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}return t},h=n(5),d=n(4),i=n(1),a=n(11),c=n(7),l=n(2),y=n(6),v=i.REQUEST_API_ERROR,f=i.REQUEST_SCRIPT_ERROR,u=i.UNKNOWN_ERROR;c(o,a),Object.assign(o.prototype,{run:function(){var t=this;this.fetchConfig(function(){return t.loadResources()})},fetchConfig:function(c){var u=this,t=this._captchaConfig,r=t.captchaId,n=t.protocol,e=t.timeout,o=t.apiServer;t.__serverConfig__;null==o&&(o=["c.dun.163yun.com","c.dun.163.com"]);var i,s=(i="/api/v2/getconf",Array.isArray(o)?o.map(function(t){return d({protocol:n,host:t,path:i})}):d({protocol:n,host:o,path:i})),a={id:r},l={timeout:e},f=h(p({},l,{onTryError:y(this._captchaConfig)}));this.emit("before-config",{params:a,jsonpOpts:l}).then(function(){f(s,a,function(t,r){if(t||r.error){var n=t?t.message:r.msg,e=new Error(n+" ("+s+")");return e.data={url:s},void u.catchError(e,v)}var o,i,a=r.data;o=a,null==(i=u._captchaConfig).apiServer&&(i.apiServer=o.apiServer),null==i.staticServer&&(i.staticServer=o.staticServers),i.theme=o.theme,i.__serverConfig__=o,u.emit("after-config",u._captchaConfig.__serverConfig__).then(c).catch(function(t){return u.catchError(t)})})}).catch(function(t){return u.catchError(t)})},loadResources:function(){var a=this,t=this._captchaConfig,c=t.protocol,u=t.timeout,s=t.staticServer,r=t.__serverConfig__;this.emit("before-load",s).then(function(){var t=r.resources.map(function(t){var r,n,e=(r=s,n=t,Array.isArray(r)?r.map(function(t){return d({protocol:c,host:t,path:n})}):d({protocol:c,host:r,path:n})),o=Array.isArray(e)?e[0]:e,i=a._cache[o];return i||(i=l.script({id:o,url:e,timeout:u,onTryError:y(a._captchaConfig)}),(a._cache[o]=i).catch(function(){a._cache[o]=null})),i});l.all(t).then(function(){a.emit("after-load").catch(function(t){return a.catchError(t)})}).catch(function(t){return a.catchError(t,f)})}).catch(function(t){return a.catchError(t)})},catchError:function(t,r){if(!this._error){var n=new i(r||u,t.message,t.data);this._error=n,this.emit("error",n)}}}),t.exports=o},function(t,r){Array.isArray||(Array.isArray=function(t){return"[object Array]"===Object.prototype.toString.call(t)})},function(t,r){"function"!=typeof Object.assign&&(Object.assign=function(t){if(null==t)throw new TypeError("Cannot convert undefined or null to object");t=Object(t);for(var r=1;r<arguments.length;r++){var n=arguments[r];if(null!=n)for(var e in n)Object.prototype.hasOwnProperty.call(n,e)&&(t[e]=n[e])}return t})},function(t,r){Array.prototype.indexOf||(Array.prototype.indexOf=function(t,r){var n;if(null==this)throw new TypeError('"this" is null or not defined');var e=Object(this),o=e.length>>>0;if(0===o)return-1;var i=+r||0;if(Math.abs(i)===1/0&&(i=0),o<=i)return-1;for(n=Math.max(0<=i?i:o-Math.abs(i),0);n<o;){if(n in e&&e[n]===t)return n;n++}return-1})},function(t,r){Array.prototype.map||(Array.prototype.map=function(t,r){var n,e,o;if(null==this)throw new TypeError(" this is null or not defined");var i=Object(this),a=i.length>>>0;if("[object Function]"!==Object.prototype.toString.call(t))throw new TypeError(t+" is not a function");for(r&&(n=r),e=new Array(a),o=0;o<a;){var c,u;o in i&&(c=i[o],u=t.call(n,c,o,i),e[o]=u),o++}return e})},function(t,r){function i(t,r){t.onload=function(){this.onerror=this.onload=null,r(null,t)},t.onerror=function(){this.onerror=this.onload=null,r(new Error("Failed to load "+this.src),t)}}function a(t,r){t.onreadystatechange=function(){"complete"!=this.readyState&&"loaded"!=this.readyState||(this.onreadystatechange=null,r(null,t))}}t.exports=function(t,r,n){var e=document.head||document.getElementsByTagName("head")[0],o=document.createElement("script");"function"==typeof r&&(n=r,r={}),r=r||{},n=n||function(){},o.type=r.type||"text/javascript",o.charset=r.charset||"utf8",o.async=!("async"in r&&!r.async),o.src=t,r.attrs&&function(t,r){for(var n in r)t.setAttribute(n,r[n])}(o,r.attrs),r.text&&(o.text=""+r.text),("onload"in o?i:a)(o,n),o.onload||i(o,n),e.appendChild(o)}}])});