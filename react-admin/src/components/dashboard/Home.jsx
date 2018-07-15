/**
 * Created by hao.cheng on 2017/5/3.
 */
import React from 'react';
import { Row, Col, Card, Timeline, Icon } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import EchartsViews from './EchartsViews';
import EchartsProjects from './EchartsProjects';
import b1 from '../../style/imgs/b1.jpg';
import { dateFtt } from '../../axios/tools';


class Home extends React.Component {
    constructor(props) {
        super(props);
    }
    state = {
        date: {},
        timerId: null
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

        var uName = (user&&user.username) ? ','+user.username : ''
        return (
            <div>
                <BreadcrumbCustom first="首页" />
                <Card
                    title={"您好"+uName}
                >
                    现在是  
                    {dateFtt("yyyy-MM-dd hh:mm:ss",new Date())||'-'}
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
                <Row gutter={10}>
                    <Col className="gutter-row" md={4}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="heart" className="text-2x text-danger" />
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">收藏</div>
                                        <h2>301</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="cloud" className="text-2x" />
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">云数据</div>
                                        <h2>30122</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>
                    </Col>
                    <Col className="gutter-row" md={4}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="camera" className="text-2x text-info" />
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">照片</div>
                                        <h2>802</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="clear y-center">
                                    <div className="pull-left mr-m">
                                        <Icon type="mail" className="text-2x text-success" />
                                    </div>
                                    <div className="clear">
                                        <div className="text-muted">邮件</div>
                                        <h2>102</h2>
                                    </div>
                                </div>
                            </Card>
                        </div>
                    </Col>
                    <Col className="gutter-row" md={16}>
                        <div className="gutter-box">
                            <Card bordered={false} className={'no-padding'}>
                                <EchartsProjects />
                            </Card>
                        </div>
                    </Col>
                </Row>
                <Row gutter={10}>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="pb-m">
                                    <h3>任务</h3>
                                    <small>10个已经完成，2个待完成，1个正在进行中</small>
                                </div>
                                <a className="card-tool"><Icon type="sync" /></a>
                                <Timeline>
                                    <Timeline.Item color="green">新版本迭代会</Timeline.Item>
                                    <Timeline.Item color="green">完成网站设计初版</Timeline.Item>
                                    <Timeline.Item color="red">
                                        <p>联调接口</p>
                                        <p>功能验收</p>
                                    </Timeline.Item>

                                    <Timeline.Item color="#108ee9">
                                        <p>登录功能设计</p>
                                        <p>权限验证</p>
                                        <p>页面排版</p>
                                    </Timeline.Item>
                                </Timeline>
                            </Card>
                        </div>
                    </Col>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="pb-m">
                                    <h3>消息栏</h3>
                                </div>
                                <a className="card-tool"><Icon type="sync" /></a>
                                <ul className="list-group no-border">
                                    <li className="list-group-item">
                                        <a href="" className="pull-left w-40 mr-m">
                                            <img src={b1} className="img-responsive img-circle" alt="test" />
                                        </a>
                                        <div className="clear">
                                            <a href="" className="block">鸣人</a>
                                            <span className="text-muted">终于当上火影了！</span>
                                        </div>
                                    </li>
                                    <li className="list-group-item">
                                        <a href="" className="pull-left w-40 mr-m">
                                            <img src={b1} className="img-responsive img-circle" alt="test" />
                                        </a>
                                        <div className="clear">
                                            <a href="" className="block">佐助</a>
                                            <span className="text-muted">吊车尾~~</span>
                                        </div>
                                    </li>
                                    <li className="list-group-item">
                                        <a href="" className="pull-left w-40 mr-m">
                                            <img src={b1} className="img-responsive img-circle" alt="test" />
                                        </a>
                                        <div className="clear">
                                            <a href="" className="block">小樱</a>
                                            <span className="text-muted">佐助，你好帅！</span>
                                        </div>
                                    </li>
                                    <li className="list-group-item">
                                        <a href="" className="pull-left w-40 mr-m">
                                            <img src={b1} className="img-responsive img-circle" alt="test" />
                                        </a>
                                        <div className="clear">
                                            <a href="" className="block">雏田</a>
                                            <span className="text-muted">鸣人君。。。那个。。。我。。喜欢你..</span>
                                        </div>
                                    </li>
                                </ul>
                            </Card>
                        </div>
                    </Col>
                    <Col className="gutter-row" md={8}>
                        <div className="gutter-box">
                            <Card bordered={false}>
                                <div className="pb-m">
                                    <h3>访问量统计</h3>
                                    <small>最近7天用户访问量</small>
                                </div>
                                <a className="card-tool"><Icon type="sync" /></a>
                                <EchartsViews />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        )
    }
}

export default Home;