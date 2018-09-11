/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon, Spin } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import { EchartsViews } from './CustomEcharts';
import { dateFtt } from '../../axios/tools';
import { home, notifyPop } from '../../axios';

const keysSorts = ['registerDatas', 'aliveDatas', 'merchantRegisterDatas'];

const keyNames = {
    'registerDatas': '用户注册量(近一个月)',
    'aliveDatas': '日活量(近一个月)',
    'merchantRegisterDatas': '商家注册量',
}
const dataStyle = {
    'aliveDatas': 'charts2',
    'merchantRegisterDatas': 'cards',
    'registerDatas': 'charts1',
}
const widthStyle = {
    'aliveDatas': 24,
    'merchantRegisterDatas': 24,
    'registerDatas': 24,
}

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: {},
            timerId: null,
            data: {},
            loading: true
        }
    }
    componentWillMount() {
        var func = function () {
            this.setState({
                date: new Date()
            })
        }.bind(this)
        func()
        this.setState({
            timerId: setInterval(func, 1000)
        });
    }
    componentDidMount() {
        var user = JSON.parse(localStorage.getItem('user'))
        if (user && user.type === 0) {
            home().then(
                resp => {
                    if(!resp||!resp.data){
                        console.log(resp);
                        return;
                    }
                    Object.keys(resp.data).forEach(k=>{
                        if(k==="registerDatas"){
                            (resp.data[k]||[]).forEach(r=>{
                                r.dayNumber = new Date(r.resultValue).getDate();
                            })
                            resp.data[k].barTitle = '注册量'
                        }
                    })
                    this.setState({
                        data: resp.data,
                        loading: false
                    });
                    console.log(resp);
                }
            ).catch(err => notifyPop('错误', err, null, null, 'error'));
        }
    }
    getUnPermissionControl(user) {
        var divStyle = {
            display: 'flex',
            width: '220px',
            height: '220px',
            alignItems: 'center',
            justifyContent: 'center',
        }
        var backgroundImg = {
            width: '220px',
            height: '220px',
            position: 'absolute'
        }

        var container = {
            flexDirection: 'row',
            display: 'flex',
            position: 'relative'
        }

        var containerDiv = {
            position: 'absolute',
            left: 0,
            right: 0,
            bottom: 0,
            boxShadow: '1px 1px 8px 2px rgba(0, 0, 0, .4)',
            backgroundColor: 'white'
        }

        var clockMinuteLine = {
            width: 3,
            height: 95,
            transformOrigin: 'bottom'
        }

        var clockHourLine = {
            width: 4,
            height: 65,
            transformOrigin: 'bottom'
        }

        var clockSecondLine = {
            width: 2,
            height: 105,
            transformOrigin: 'bottom'
        }


        var date = this.state.date
        var minutes = date.getMinutes()
        var seconds = date.getSeconds()

        var hour = (date.getHours()) % 12 * (360 / 12) + (360 / 12) * (minutes / 60)
        var minute = minutes * (360 / 60) + (360 / 60) * (seconds / 60)
        var second = seconds * (360 / 60)

        var uName = (user && user.username) ? ',' + user.username : ''
        return (
            <div>
                <BreadcrumbCustom first="首页" />
                <Card
                    title={"您好" + uName}
                >
                    现在是
                    {dateFtt("yyyy-MM-dd hh:mm:ss", new Date()) || '-'}
                    <div style={divStyle}>
                        <img style={backgroundImg} alt={'欢迎'+uName} />
                        <div style={container} >
                            <div style={{ ...containerDiv, ...clockMinuteLine, transform: 'rotateZ(' + minute + 'deg)' }} />
                            <div style={{ ...containerDiv, ...clockHourLine, transform: 'rotateZ(' + hour + 'deg)' }} />
                            <div style={{ ...containerDiv, ...clockSecondLine, transform: 'rotateZ(' + second + 'deg)' }} />
                        </div>

                    </div>
                </Card>
            </div>
        )
    }
    toMerchantDetail(id) {
        if (id) {
            this.props.history.push('/app/merchantList/merchantDetail/' + id);
        } else {
            notifyPop('错误', '找不到id为' + id + '的商家', null, 3, 'error');
        }
    }
    getCharts() {
        var charts = []
        console.log();
        try {
            var keys = keysSorts;
            for (var i = 0; i < keys.length; i++) {
                var k = keys[i];
                var name = keyNames[k];
                var datas = this.state.data[k];
                console.log(this.state.data);
                if (dataStyle[k] === "cards") {
                    var columns = [];
                    for (var j = 0; j < datas.length; j++) {
                        var d = datas[j];
                        columns.push(
                            <Col key={j} className="gutter-row" md={8} style={{ marginBottom: 8 }}>
                                <Card.Grid onClick={() => this.toMerchantDetail(d.merchantId)} className="gutter-box" style={{ width: '100%' }}>
                                    <Card bordered={false}>
                                        <div className="clear y-center">
                                            <div className="pull-left mr-m">
                                                <Icon type="book" className="text-2x text-danger" />
                                            </div>
                                            <div className="clear">
                                                <div className="text-muted">{d.resultValue}</div>
                                                <h2>{d.count}</h2>
                                            </div>
                                        </div>
                                    </Card>
                                </Card.Grid>
                            </Col>
                        );
                    }
                    charts.push(
                        <Col md={widthStyle[k]} key={i} style={{ marginBottom: 12 }} >
                            <div>
                                <Card
                                    bordered={false}
                                    hoverable="true"
                                >
                                    <div>
                                        <h3>{name}</h3>
                                        {/* <small>最近7天用户访问量</small> */}
                                    </div>
                                    {/* <a className="card-tool"><Icon type="sync" /></a> */}
                                    <div style={{ height: '350px', width: '100%' }} >
                                        <Row gutter={10}>
                                            {columns}
                                        </Row>
                                    </div>
                                </Card>
                            </div>
                        </Col>
                    );
                } else if (dataStyle[k].indexOf("charts" > -1)) {
                    var chartsDatas = this.state.data[k];
                    charts.push(
                        <Col md={widthStyle[k]} key={i} style={{ marginBottom: 12 }} >
                            <div>
                                <Card
                                    bordered={false}
                                    hoverable="true"
                                >
                                    <div>
                                        <h3>{name}</h3>
                                        {/* <small>最近7天用户访问量</small> */}
                                    </div>
                                    {/* <a className="card-tool"><Icon type="sync" /></a> */}
                                    {EchartsViews(name, chartsDatas, dataStyle[k] === "charts1" ? 1 : 2)}
                                </Card>
                            </div>
                        </Col>
                    );
                }
            }
        } catch (error) {
            return;
        }

        return charts;
    }
    render() {
        var user = JSON.parse(localStorage.getItem('user'))
        if (!user || user.type !== 0) {
            return this.getUnPermissionControl(user);
        }
        if (this.state.timerId) {
            clearInterval(this.state.timerId);
            this.setState({ timerId: null });
        }
        return (
            <Spin spinning={this.state.loading} tip="Loading..." size="large" >
                <BreadcrumbCustom />
                <Row justify="space-around" align="middle" gutter={16} >
                    {this.getCharts()}
                </Row>
            </Spin>
        )
    }
}

export default Home;