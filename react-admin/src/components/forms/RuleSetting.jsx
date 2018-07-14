/**
 * Created by hao.cheng on 2017/4/13.
 */
import React, { Component } from 'react';
import TweenOne from 'rc-tween-one';
import { Link } from 'react-router-dom';
import { Card, Form, Pagination, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, InputNumber, Layout } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import { getAnalyzeRule, editAnalyzeRule, notifyPop } from '../../axios';
import { relative } from 'path';

const FormItem = Form.Item;
const { Header, Content, Footer } = Layout;

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
    };
    componentDidMount() {
        getAnalyzeRule().then(
            resp => {
                console.log(resp);
                if (resp.success) {
                    this.setState({
                        ...resp
                    })
                }
            }
        ).catch(
            err => notifyPop('错误', err, null, 5, 'error')
        )
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };
    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    };
    checkConfirm = (rule, value, callback) => {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], { force: true });
        }
        callback();
    };
    editRule(rule) {
        this.setState({
            editKey: this.state.editKey == rule.id ? '' : rule.id,
            editDetailCount: rule.detailCount,
            editListCount: rule.listCount,
        });
    };
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
                notifyPop('错误',resp.desc,null,5,'err')
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
                    style={{ left: '45px' }}
                    onClick={() => this.saveRule(rule)} >保存</Button>
            </Card>
        );
        var list = this.state.data.map(rule => {
            return (
                <Col span={6}
                    bordered="false"
                    key={rule.id}
                    hoverable="true"
                >
                    <div style={{
                        ...gridStyle,
                        transform: this.state.editKey == rule.id ? 'rotateY(180deg)' : 'rotateY(0deg)'
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
            <div>
                <BreadcrumbCustom itemRender={itemRender} routes={routes} />
                <Card
                    title="规则设置"
                >
                    <Row justify="space-between" >
                        {this.state.data.length ? this.getRuleList() : <p>暂无规则</p>}
                    </Row>
                </Card>
                <Pagination defaultCurrent={1} total={this.state.totalCount} />
            </div>
        )
    }
}

const RuleSetting = Form.create()(RuleSettings);

export default RuleSetting;