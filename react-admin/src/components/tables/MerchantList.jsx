/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { Table, Button, Row, Col, Card } from 'antd';
import { getMerchant } from '../../axios';
import BreadcrumbCustom from '../BreadcrumbCustom';

const types = {
    "0" : "超级管理员",
    "1" : "普通商家",
    "2" : "运营"
}

const columns = [{
    title: '渠道号',
    dataIndex: 'channelId',
    width: 100,
    render: (text, record) => <a href={record.url} target="_blank">{text}</a>
}, {
    title: '商家id',
    dataIndex: 'id',
    width: 80
}, {
    title: '名称',
    dataIndex: 'name',
    width: 200
}, {
    title: '密码',
    dataIndex: 'password',
    width: 200
}, {
    title: '权限类型',
    dataIndex: 'type',
    width: 200,
    render: (text, record, index) => {
        return (
            <p>{ types[text] }</p>
        );
    }
}];

class MerchantList extends React.Component {
    state = {
        selectedRowKeys: [],  // Check here to configure the default column
        loading: false,
        data: []
    };
    componentDidMount() {
        this.start();
    }
    start = () => {
        console.log("Start get data");
        this.setState({ loading: true });
        const { sessionId } = JSON.parse(localStorage.getItem('user'));
        console.log("call getMerchat");
        getMerchant({
            sessionId: sessionId,
            pageNo: 1,
            pageSize: 20
        }).then(res => {
            console.log("商家列表：------");
            console.log(res);
            if(res.data){
                this.setState({
                    data: [...res.data.map(val => {
                        val.key = val.id;
                        return val;
                    })],
                    loading: false
                });
            }else{
                this.setState({
                    data: [],
                    loading: false
                });
            }
        });
    };
    onSelectChange = (selectedRowKeys) => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    };
    render() {
        const { loading, selectedRowKeys } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
        };
        const hasSelected = selectedRowKeys.length > 0;
        return (
            <div className="gutter-example">
                <BreadcrumbCustom first="商家列表"/>
                <Row gutter={16}>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card title="商家列表" bordered={false}>
                                <div style={{ marginBottom: 16 }}>
                                    <Button type="primary" onClick={this.start}
                                            disabled={loading} loading={loading}
                                    >刷新</Button>
                                    <span style={{ marginLeft: 8 }}>{hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}</span>
                                </div>
                                <Table rowSelection={rowSelection} columns={columns} dataSource={this.state.data} />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default MerchantList;