/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Icon } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import { CustomEcharts, EchartsViews } from './CustomEcharts';
import { dateFtt } from '../../axios/tools';
import { home, notifyPop } from '../../axios';

const keyNames = {
    'aliveDatas': '日活数据',
    'merchantRegisterDatas': '商家注册数据',
    'registerDatas': '用户注册数据',
}


class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: {},
            timerId: null,
            data: {}
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
        home().then(
            resp => {
                this.setState({
                    data: resp.data
                });
                console.log(resp);
            }
        ).catch(err => notifyPop('错误', err, null, null, 'error'));
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
                        <img style={backgroundImg} />
                        <div style={container} >
                            <div style={{ ...containerDiv, ...clockMinuteLine, transform: 'rotateZ(' + minute + 'deg)' }}></div>
                            <div style={{ ...containerDiv, ...clockHourLine, transform: 'rotateZ(' + hour + 'deg)' }}></div>
                            <div style={{ ...containerDiv, ...clockSecondLine, transform: 'rotateZ(' + second + 'deg)' }}></div>
                        </div>

                    </div>
                </Card>
            </div>
        )
    }
    getCharts() {
        var charts = []
        var keys = Object.keys(this.state.data);
        for(var i=0;i<keys.length;i++){
            var name = keyNames[keys[i]];
            charts.push(
                <Row gutter={10}>
                    <Col className="gutter-row" >
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="pb-m">
                                    <h3>{name}</h3>
                                    {/* <small>最近7天用户访问量</small> */}
                                </div>
                                {/* <a className="card-tool"><Icon type="sync" /></a> */}
                                {EchartsViews(name, this.state.data[keys[i]])}
                            </Card>
                        </div>
                    </Col>
                </Row>
            );
        }
        return charts;
    }
    render() {
        var user = JSON.parse(localStorage.getItem('user'))
        if (!user || user.type != 0) {
            return this.getUnPermissionControl(user);
        }
        if (this.state.timerId) {
            clearInterval(this.state.timerId);
            this.setState({ timerId: null });
        }
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom />
                { this.getCharts() }
            </div>
        )
    }
}

export default Home;