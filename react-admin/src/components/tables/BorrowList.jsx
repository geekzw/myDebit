/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import {
    Row, Popconfirm, Input, Form, Col, Card, Table,
    Icon, BackTop, Button, InputNumber,
} from 'antd';
import { Link } from 'react-router-dom';
import { getBorrowList, editBorrow, deleteBorrow, notifyPop } from '../../axios';
import '../../style/table.less';
import MerchantList, { } from './MerchantList';
import BreadcrumbCustom from '../BreadcrumbCustom';
import BorrowEditForm from '../forms/BorrowEditForm';


const routes = [{
    path: 'app',
    breadcrumbName: '首页'
}, {
    path: 'borrowList',
    breadcrumbName: '借贷列表'
}];
export function itemRender(route, params, routes, paths) {
    const last = routes.indexOf(route) === routes.length - 1;
    return last ? <span>{route.breadcrumbName}</span> : <Link onClick={() => localStorage.removeItem('editingBorrow')} to={'/' + paths.join('/')}>{route.breadcrumbName}</Link>;
}



class BorrowList extends MerchantList {
    state = {
        ...(super.state) || {},
        data: [],
        editingRecord: JSON.parse(localStorage.getItem('editingBorrow'))
    };
    // 属性相关
    breadcrumbCustom() {
        return (<BreadcrumbCustom itemRender={itemRender} routes={routes} />);
    }
    tableTitle() {
        return "借贷列表";
    }
    // // 控件相关
    // tableHeaderArea = () => (
    //     <div>
    //         <Row style={{ marginBottom: 16, marginTop: 12 }} type="flex" justify="space-between">
    //             <Button type="primary" onClick={this.reload}
    //                 disabled={this.state.loading} loading={this.state.loading}
    //             >
    //                 {this.state.loading ? '正在加载' : '刷新'}
    //             </Button>
    //         </Row>
    //     </div>
    // );
    toBorrow(r){
        this.setState({
            editingRecord: r,
            isChecking: true,
        });
    }
    finishedEdit = () => {
        this.setState({
            editingRecord: null
        });
        this.start();
    }
    columns = [
        { title: 'id', dataIndex: 'id', width: 60,
            render: (text,record) => <a onClick={()=>this.toBorrow(record)}>{text}</a>
        },
        { title: '产品名称', dataIndex: 'productName', width: 120, 
            render: (text,record) => <a onClick={()=>this.toBorrow(record)}>{text}</a>
        },
        // {
        //     title: '跳转链接', dataIndex: 'url', width: 200,
        //     render: (text, record) => (<a href={text} target="_blank" >{text}</a>)
        // },
        // {
        //     title: '图片链接', dataIndex: 'image', width: 200,
        //     render: (text, record) => (<a href={text} target="_blank" >{text}</a>)
        // },
        // { title: '排序', dataIndex: 'borrowOrder', width: 80 },
        // { title: '产品介绍', dataIndex: 'productDetail', width: 200 },
        // { title: '贷款介绍', dataIndex: 'debitDesc', width: 200 },
        // { title: '所需材料', dataIndex: 'needData', width: 160 },
        // { title: '申请资格', dataIndex: 'qualification', width: 200 },
        { title: '放贷人数', dataIndex: 'peopleNumber', width: 120 },
        // { title: '放款速度', dataIndex: 'fastTime', width: 120 },
        // { title: '放款金额', dataIndex: 'debitMoney', width: 160 },
        // { title: '最短借款时间', dataIndex: 'debitTime', width: 160 },
        { title: '利率', dataIndex: 'monthyRate', width: 80 },
        {
            title: '操作', key: 'operation', width: 96, //fixed: 'right',
            render: (text, record) => {
                return (
                    <Row style={{width:'72px'}}>
                        <Col md={12}>
                            <Button onClick={() => this.edit(record)} type="primary" icon="edit" ></Button>
                        </Col>
                        <Col md={12}>
                            <Popconfirm
                                title="确认删除吗?"
                                onConfirm={() => this.delete(record)}
                            >
                                <Button type="danger" icon="delete" />
                            </Popconfirm>
                        </Col>
                    </Row>
                );
            },
        },
    ];
    // 网络相关
    edit(record) {
        localStorage.setItem('editingBorrow', JSON.stringify(record));
        this.setState({
            editingRecord: record,
            isChecking: false
        });
    }
    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            var oldRow;
            for (var i = 0; i < this.state.data.length; i++) {
                if (this.state.data[i].key == key) {
                    oldRow = this.state.data[i];
                    break;
                }
            }
            if (!oldRow) {
                notifyPop('警告', '数据出错，请刷新', <Icon type="frown" style={{ color: 'red' }} />);
            }
            var isSameData = this.isRecordChange(row, oldRow);
            if (isSameData) {
                notifyPop('提示', '数据无变更', <Icon type="smile-circle" style={{ color: 'blue' }} />);
                this.setState({ editingKey: '' });
                return;
            }
            var params = {
                ...row,
                id: oldRow.id
            }
            editBorrow(params).then(
                resp => {
                    console.log(params);
                    console.log(resp);
                    notifyPop('提示', resp.success ? '修改成功' : resp.desc);
                    if (resp.success) {
                        const newData = [...this.state.data];
                        const index = newData.findIndex(item => key === item.key);
                        if (index > -1) {
                            const item = newData[index];
                            newData.splice(index, 1, {
                                ...item,
                                ...row,
                            });
                            this.setState({ data: newData, editingKey: '' });
                        } else {
                            // newData.push(data);
                            this.setState({ data: newData, editingKey: '' });
                        }
                    }
                }
            ).catch(err => notifyPop('错误', err, <Icon type="frown" />), 5);
        });
    }
    delete = (row) => {
        if (row) {
            var params = {
                id: row.id,
            }
            deleteBorrow(params).then(
                resp => {
                    console.log(params);
                    console.log(resp);
                    notifyPop('提示', resp.success ? '删除成功' : resp.desc);
                    if (resp.success) {
                        const newData = [...this.state.data];
                        const index = newData.findIndex(item => row.key === item.key);
                        if (index > -1) {
                            newData.splice(index, 1);
                            this.setState({ data: newData, editingKey: '' });
                        } else {
                            // newData.push(data);
                            this.setState({ data: newData, editingKey: '' });
                        }
                    }
                }
            ).catch(err => notifyPop('错误', err, <Icon type="frown" />), 5);
        }
    };
    reload = () => {
        const { pageNo, pageSize } = this.state;
        this.start(pageNo, pageSize);
    };
    start = (
        pageNo = this.state.pageNo,
        pageSize = this.state.pageSize,
        searchParam = ''
    ) => {
        (new BackTop({
            target: () => document.getElementById('rightScroll')
        })).scrollToTop();
        this.setState({ loading: true });
        var params = {
            pageNo: pageNo,
            pageSize: pageSize,
            searchParam: searchParam
        }
        getBorrowList(params).then(res => {
            console.log(params);
            console.log(res);
            if (!res.success) {
                notifyPop('错误', res.desc, <Icon type="frown" />);
            }
            if (res.data) {
                this.setState({
                    data: [...res.data.map(val => {
                        val.key = val.id;
                        return val;
                    })],
                    loading: false,
                    totalCount: res.totalCount,
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                    registerCount: res.data.registerCount,
                    intentCount: res.data.intentCount,
                    accurateCount: res.data.accurateCount
                });
            } else {
                this.setState({
                    data: [],
                    loading: false,
                    totalCount: 0
                });
            }
        }).catch(err => notifyPop('错误', err, <Icon type="frown" />, 5));
    };
    tableContent() {
        return (<div className="gutter-example" >
            {this.breadcrumbCustom()}
            <Row gutter={16}>
                <Col className="gutter-row" md={24}>
                    <div className="gutter-box">
                        <Card title={this.tableTitle()} bordered={false}>
                            {this.tableHeaderArea()}
                            <Table
                                // scroll={{ x: 1500 }}
                                onRow={(record) => {
                                    return {
                                        onClick: () => { },       // 点击行
                                        onMouseEnter: () => { },  // 鼠标移入行
                                    };
                                }}
                                bordered
                                onChange={this.onChange}
                                loading={this.state.loading}
                                components={this.tableComponents}
                                columns={this.columns}
                                dataSource={this.state.data}
                                rowClassName="editable-row customRowHighlight"
                                pagination={
                                    {
                                        current: this.state.pageNo,
                                        pageSize: this.state.pageSize,
                                        onChange: this.paginationOnChange,
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
                    </div>
                </Col>
            </Row>
        </div>);
    }
    render() {
        var content = this.state.editingRecord ?
            <BorrowEditForm isChecking={this.state.isChecking} record={this.state.editingRecord} finished={this.finishedEdit} /> : this.tableContent()
        return content;
    }
}

export default BorrowList;