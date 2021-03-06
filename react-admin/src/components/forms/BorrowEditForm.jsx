/**
 * Created by hao.cheng on 2017/4/15.
 */
import React, { Component } from 'react';
import { Row, Col, Card, Button, Icon, Form, Input, InputNumber, Popconfirm } from 'antd';
import BreadcrumbCustom from '../BreadcrumbCustom';
import { itemRender } from '../tables/BorrowList';
import { notifyPop, editBorrow, addBorrow } from '../../axios';

const routes = [{
    path: 'app',
    breadcrumbName: '首页'
}, {
    path: 'borrowList',
    breadcrumbName: '借贷列表'
}, {
    path: '',
    breadcrumbName: '借贷详情'
}];

const { TextArea } = Input;

const FormItem = Form.Item;

const columns = [
    { title: '跳转链接', dataIndex: 'url', },
    { title: '图片链接', dataIndex: 'image', },
    { title: '排序', dataIndex: 'borrowOrder', inputType: 'number' },
    { title: '产品名称', dataIndex: 'productName', },
    { title: '产品介绍', dataIndex: 'productDetail', },
    { title: '贷款介绍', dataIndex: 'debitDesc', },
    { title: '所需材料', dataIndex: 'needData', },
    { title: '申请资格', dataIndex: 'qualification', },
    { title: '放贷人数', dataIndex: 'peopleNumber', },
    { title: '放款速度', dataIndex: 'fastTime', },
    { title: '放款金额', dataIndex: 'debitMoney', },
    { title: '最短借款时间', dataIndex: 'debitTime', },
    { title: '利率', dataIndex: 'monthyRate', },
];

class BorrowEditForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            record: props.record,
            isChecking: props.isChecking,
            isAdding: props.isAdding
        };
    }
    // 属性相关
    breadcrumbCustom() {
        return (<BreadcrumbCustom itemRender={itemRender} routes={routes} />);
    }
    handleCreate = () => {
        const form = this.form;
        form.validateFields((err, values) => {
            if (err) {
                return;
            }

            console.log('Received values of form: ', values);
            form.resetFields();
        });
    };
    saveFormRef = (form) => {
        this.form = form;
    };
    save(form, record) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            if(!this.state.isAdding){
                var isModify = false;
                var keys = columns.map(r => r.dataIndex);
                for (var i = 0; i < keys.length; i++) {
                    if (record[keys[i]] !== row[keys[i]]) {
                        isModify = true;
                        break;
                    }
                }
                if (!isModify) {
                    notifyPop('提示', '数据无变更', <Icon type="smile-circle" style={{ color: 'blue' }} />);
                    this.setState({ editingKey: '' });
                    return;
                }
            }
            var params = {
                ...row
            }
            if(this.state.isAdding){
                console.log(params);
                addBorrow(params).then(
                    resp => {
                        console.log(params);
                        console.log(resp);
                        notifyPop('提示', resp.success ? '新增成功' : resp.desc);
                        if (resp.success) {
                            this.props.finished();
                        }
                    }
                ).catch(err => notifyPop('错误', err, <Icon type="frown" />));
                return;
            }
            params["id"]=record.id;
            editBorrow(params).then(
                resp => {
                    console.log(params);
                    console.log(resp);
                    notifyPop('提示', resp.success ? '修改成功' : resp.desc);
                    if (resp.success) {
                        this.props.finished();
                    }
                }
            ).catch(err => notifyPop('错误', err, <Icon type="frown" />));
        });
    }
    cancel() {
        localStorage.removeItem('editingBorrow');
        this.props.finished();
    }
    textOnChange(key) {
        return (e) => {
            var value = e.target ? e.target.value : e;
            if(this.state.isAdding){
                return;
            }
            var rec = JSON.parse(localStorage.getItem('editingBorrow'));
            rec[key] = value;
            localStorage.setItem('editingBorrow', JSON.stringify(rec));
        }
    }
    render() {
        const { record } = this.state;
        var isChecking = this.state.isChecking||false
        var FormArea = Form.create()(
            (props) => {
                const { form } = props;
                const { getFieldDecorator } = form;
                return (
                    <Form layout="vertical">
                        {
                            columns.map(r => (
                                <FormItem label={r.title} key={r.dataIndex}>
                                    {
                                        !isChecking ? getFieldDecorator(r.dataIndex, {
                                            rules: [{ required: true, message: '请输入' + r.title + '!' }],
                                            initialValue: record?record[r.dataIndex]:null,
                                        })(r.inputType === 'number' ?
                                            <InputNumber onChange={this.textOnChange(r.dataIndex)} /> :
                                            <TextArea onChange={this.textOnChange(r.dataIndex)} autosize />) :
                                            (<TextArea defaultValue={record[r.dataIndex]} disabled autosize />)
                                    }
                                </FormItem>
                            ))
                        }
                        {
                            !isChecking ? (<div>
                                <Button onClick={() => this.save(form, record)} style={{ marginRight: 8 }} >保存</Button>
                                <Popconfirm title="放弃修改吗?" onConfirm={() => this.cancel()}>
                                    <a>取消</a>
                                </Popconfirm>
                            </div>) : (<div>
                                <Button onClick={() => this.setState({isChecking:false})} style={{ marginRight: 8 }} >编辑</Button>
                                <Button onClick={() => this.cancel()} style={{ marginRight: 8 }} >返回</Button>
                                </div>)
                        }
                    </Form>
                );
            }
        )
        return (
            <div className="gutter-example" >
                {this.breadcrumbCustom()}
                <Row gutter={16}>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card title={this.state.isAdding?"增加借贷项目":"借贷详情"} bordered={false}>
                                <FormArea />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default BorrowEditForm;