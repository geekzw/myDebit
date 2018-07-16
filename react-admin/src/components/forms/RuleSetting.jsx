/**
 * Created by hao.cheng on 2017/4/13.
 */
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Card, Form, Pagination, Spin, Row, Col, Button, InputNumber } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import { getAnalyzeRule, editAnalyzeRule, notifyPop } from '../../axios';

const FormItem = Form.Item;

const routes = [{
    path: 'app',
    breadcrumbName: '首页'
}, {
    path: 'setting',
    breadcrumbName: '设置'
}, {
    path: 'rule',
    breadcrumbName: '规则设置'
}];
function itemRender(route, params, routes, paths) {
    const last = routes.indexOf(route) === routes.length - 1;
    if (route.path == 'merchantList' && (JSON.parse(localStorage.getItem('user')) || {}).type == 1) {
        return last;
    }
    return last ? <span>{route.breadcrumbName}</span> : <Link to={'/' + paths.join('/')}>{route.breadcrumbName}</Link>;
}

const types={
    '1':'意向用户',
    '2':'精准用户',
}

class RuleSettings extends Component {
    state = {
        confirmDirty: false,
        editKey: '',
        editDetailCount: 0,
        editListCount: 0,
        data: [],
        totalCount: 0,
        loading: true,
        pageNo: 1,
        pageSize: 10,
    };
    componentDidMount() {
        this.start();
    }
    reload = () => {
        this.setState({
            loading: true
        });
        this.start();
    }
    start(page=this.state.pageNo,pageSize=this.state.pageSize) {
        getAnalyzeRule({
            pageNo: page,
            pageSize: pageSize
        }).then(
            resp => {
                this.setState({
                    loading: false
                });
                console.log(resp);
                if (resp.success) {
                    this.setState({
                        ...resp
                    })
                }
            }
        ).catch(
            err => {
                this.setState({
                    loading: false
                });
                notifyPop('错误', err, null, 5, 'error');
            }
        )
    }
    paginationOnChange = (page, pageSize) => {
        this.setState({
            pageNo: page,
            pageSize: pageSize,
            loading: true
        });
        this.start(page,pageSize);
    };
    editRule(rule) {
        this.setState({
            editKey: this.state.editKey == rule.id ? '' : rule.id,
            editDetailCount: rule.detailCount,
            editListCount: rule.listCount,
        });
    };
    cancel() {
        this.setState({
            editKey: '',
            editDetailCount: 0,
            editListCount: 0,
        });
    }
    saveRule(rule) {
        const { data, editDetailCount, editListCount } = this.state;
        for(var i=0;i<data.length;i++){
            var r = data[i];
            if(r.id==rule.id){
                data[i].detailCount = editDetailCount;
                data[i].listCount = editListCount;
                break;
            }
        }
        editAnalyzeRule({
            ...rule
        }).then(resp=>{
            if(resp.success){
                notifyPop('提示','保存成功',null,5,'success')
                this.setState({
                    data:data,
                    editKey: '',
                    editDetailCount: 0,
                    editListCount: 0,
                });
            }else{
                notifyPop('错误',resp.desc,null,5,'error')
            }
        }).catch(err=>notifyPop('错误',err,null,5,'error'))
    }
    getRuleList() {
        const gridStyle = {
            width: '100%',
            height: '400px',
            textAlign: 'center',
            position: 'relative'
        };
        const cardStyle = {
            left: '0px',
            width: '100%',
            height: '350px',
            position: 'absolute'
        };
        var editableCard = (rule) => (
            <Card
                style={cardStyle}
                className={`animated ${this.state.editKey != rule.id ? 'flipInY' : 'flipOutY'}`}
                title={types[rule.type]+"设置"}
                bordered
                key={0}
            >
                <FormItem
                    // {...this.formItemLayout}
                    label="详情点击次数"
                    hasFeedback
                >
                    {rule.detailCount || 0}次
                        </FormItem>
                <FormItem
                    // {...formItemLayout}
                    label="列表点击次数"
                    hasFeedback
                >
                    {rule.listCount || 0}次
                        </FormItem>
                    <Button
                        disabled={(this.state.editKey||'')!=''}
                        style={{ left: '45px' }}
                        onClick={() => this.editRule(rule)} >编辑</Button>
            </Card>
        );
        var readableCard = (rule) => (
            <Card
                style={{
                    ...cardStyle,
                    transform: 'rotateY(180deg)',
                    backgroundColor: '#e7f7ff'
                }}
                className={`animated ${this.state.editKey == rule.id ? 'flipInY' : 'flipOutY'}`}
                title={types[rule.type]+"设置"}
                bordered
                key={1}
            >
                <FormItem
                    label="详情点击次数"
                    hasFeedback
                >
                    <InputNumber
                        min={0}
                        value={this.state.editDetailCount}
                        defaultValue={rule.detailCount || 0}
                        onChange={(r)=>this.setState({
                            editDetailCount: r,
                        })}
                        formatter={value => `${value}次`}
                    />
                </FormItem>
                <FormItem
                    label="列表点击次数"
                    hasFeedback
                >
                    <InputNumber
                        min={0}
                        value={this.state.editListCount}
                        defaultValue={rule.listCount || 0}
                        onChange={(r)=>this.setState({
                            editListCount: r,
                        })}
                        formatter={value => `${value}次`}
                    />
                </FormItem>
                <Button
                    style={{ right: '24px' }}
                    onClick={() => this.cancel()} >取消</Button>
                <Button
                    style={{ left: '24px' }}
                    onClick={() => this.saveRule(rule)} >保存</Button>
            </Card>
        );
        var list = this.state.data.map(rule => {
            return (
                <Col md={6}
                    bordered="false"
                    key={rule.id}
                    hoverable="true"
                >
                    <div
                        style={{
                            ...gridStyle,
                            // transform: this.state.editKey != rule.id ? 'rotateY(180deg)' : 'rotateY(0deg)'
                        }}>
                        {
                            this.state.editKey == rule.id ?
                                [editableCard(rule), readableCard(rule)] :
                                [readableCard(rule), editableCard(rule)]
                        }
                    </div>
                </Col>
            );
        });
        return list;
    }
    render() {
        console.log(this.state);
        return (
            <Spin spinning={this.state.loading}>
                <BreadcrumbCustom itemRender={itemRender} routes={routes} />
                <Card
                    title="规则设置"
                >
                    <Button type="primary" onClick={this.reload} style={{marginBottom:12}}
                                    disabled={this.state.loading} loading={this.state.loading} >
                        {this.state.loading ? '正在加载' : '刷新'}
                    </Button>
                    <Row justify="space-between" gutter={16} >
                        {this.state.data.length ? this.getRuleList() : <p>暂无规则</p>}
                    </Row>
                    <Pagination 
                    {
                        ...{
                            current:this.state.pageNo,
                            pageSize:this.state.pageSize,
                            onChange:this.paginationOnChange,
                            showQuickJumper: true,
                            // showSizeChanger: true,
                            // onShowSizeChange: this.paginationOnPageSizeChange,
                            total: this.state.totalCount,
                            showTotal: (total, range) => {
                                return (
                                <div>
                                    共{total}条 当前显示{range[0]}-{range[1]}条
                                </div>
                                );
                            }
                        }
                    }
                />
                </Card>
            </Spin>
        )
    }
}

const RuleSetting = Form.create()(RuleSettings);

export default RuleSetting;