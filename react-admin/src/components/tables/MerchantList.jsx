/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { 
    Table, Input, InputNumber, Pagination,
    Popconfirm, Form, Button, 
    Row, Col, Card,
    notification, Icon
} from 'antd';
import { getMerchant, editMerchant, deleteMerchant, notifyPop } from '../../axios';
import BreadcrumbCustom from '../BreadcrumbCustom';
import '../../style/table.less';

const types = {
    "0" : "超级管理员",
    "1" : "普通商家",
    "2" : "运营"
}

function PrefixInteger(num, length) {
    return (Array(length).join('0') + num).slice(-length);
}


const FormItem = Form.Item;
const EditableContext = React.createContext();

const EditableRow = ({ form, index, ...props }) => (
  <EditableContext.Provider value={form}>
    <tr {...props} />
  </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

class EditableCell extends React.Component {
  getInput = () => {
    if (this.props.inputType === 'number') {
      return <InputNumber />;
    }
    return <Input />;
  };

  render() {
    const {
      editing,
      dataIndex,
      title,
      inputType,
      record,
      index,
      ...restProps
    } = this.props;
    return (
      <EditableContext.Consumer>
        {(form) => {
          const { getFieldDecorator } = form;
          return (
            <td {...restProps}>
              {editing ? (
                <FormItem style={{ margin: 0 }}>
                  {getFieldDecorator(dataIndex, {
                    rules: [{
                      required: true,
                      message: `请输入: ${title}!`,
                    }],
                    initialValue: record[dataIndex],
                  })(this.getInput())}
                </FormItem>
              ) : restProps.children}
            </td>
          );
        }}
      </EditableContext.Consumer>
    );
  }
}

class MerchantList extends React.Component {
    state = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        loading: false,
        data: []
    };
    componentDidMount() {
        this.start();
    }
    componentWillUnmount() {
        
    }
    start = (pageNo=this.state.pageNo,pageSize=this.state.pageSize) => {
        this.setState({ loading: true });
        const { sessionId } = JSON.parse(localStorage.getItem('user'));
        var params = {
            sessionId: sessionId,
            pageNo: pageNo,
            pageSize: pageSize,
        }
        console.log(params);
        getMerchant(params).then(res => {
            if(res.data){
                console.log(res);
                this.setState({
                    data: [...res.data.map(val => {
                        val.key = val.id;
                        return val;
                    })],
                    loading: false,
                    totalCount: res.totalCount,
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                });
            }else{
                this.setState({
                    data: [],
                    loading: false
                });
            }
        });
    };
    isEditing = (record) => {
        return record.key === this.state.editingKey;
    };
    edit(key) {
        this.setState({ editingKey: key });
    }
    isRecordChange(record,oldRecord) {
        if(!oldRecord){
            for(var i=0;i<this.state.data.length;i++){
                if(this.state.data[i].id==record.id){
                    oldRecord = this.state.data[i];
                    break;
                }
            }
        }
        if(!oldRecord){ return false; }
        var isSameData = Object.keys(record).reduce(
            (rs,k) => {
                var r = true;
                if(k){
                    if (typeof(rs)=='string'){
                        r = record[rs] == oldRecord[rs];
                    }else{
                        r = rs;
                    }
                    return r && (record[k] == oldRecord[k]);
                }else{
                    return rs;
                }
            }
        );
        return isSameData;
    }
    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            var oldRow;
            for(var i=0;i<this.state.data.length;i++){
                if (this.state.data[i].key == key){
                    oldRow = this.state.data[i];
                    break;
                }
            }
            if(!oldRow){
                notifyPop('警告','数据出错，请刷新',<Icon type="frown" style={{ color: 'red' }} />);
            }
            var isSameData = this.isRecordChange(row,oldRow);
            if (isSameData){
                notifyPop('提示','数据无变更',<Icon type="smile-circle" style={{ color: 'blue' }} />);
                this.setState({ editingKey: '' });
                return;
            }
            var params = {
                ...row,
                id: oldRow.id
            }
            editMerchant(params).then(
                resp => {
                    console.log(params);
                    console.log(resp);
                    notifyPop('提示',resp.success ? '修改成功' : resp.desc);
                    if (resp.success){
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
            ).catch(err=>notifyPop('错误',err,<Icon type="frown" />),5);
        });
    }
    cancel = () => {
        this.setState({ editingKey: '' });
    };
    delete = (row) => {
        if(row){
            var params = {
                id:row.id,
            }
            deleteMerchant(params).then(
                resp => {
                    console.log(params);
                    console.log(resp);
                    notifyPop('提示',resp.success ? '删除成功' : resp.desc);
                    if (resp.success){
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
            ).catch(err=>notifyPop('错误',err,<Icon type="frown" />),5);
        }
    };
    onChange = (pagination, filters, sorter) => {
        console.log(pagination, filters, sorter);
    };
    paginationOnChange = (page, pageSize) => {
        this.setState({
            pageNo: page,
            pageSize: pageSize,
            loading: true
        });
        this.start(page,pageSize);
    };
    paginationOnPageSizeChange = (current, pageSize) => {
        this.setState({
            pageNo: current,
            pageSize: pageSize,
            loading: true
        });
        this.start(current,pageSize);
    };
    render() {
        const { loading } = this.state;
        const components = {
            body: {
              row: EditableFormRow,
              cell: EditableCell,
            },
          };

        const columns = [{
            title: '商家id',
            dataIndex: 'id',
            width: 80,
            render: (text) => <a>{PrefixInteger(text,5)}</a>
        }, {
            title: '名称',
            dataIndex: 'name',
            width: 200,
            editable: true
        }, {
            title: '用户名',
            dataIndex: 'username',
            width: 200,
            editable: true
        },{
            title: '密码',
            dataIndex: 'password',
            width: 200,
            editable: true
        }, {
            title: '权限类型',
            dataIndex: 'type',
            width: 200,
            render: (text, record, index) => {
                return (
                    <p>{ types[text] }</p>
                );
            }
        },{
            title: '渠道号',
            dataIndex: 'channelId',
            width: 100,
            render: (text, record) => <a href={record.url} target="_blank">{text}</a>
        }, 
        {
          title: '操作',
          dataIndex: 'operation',
          width: 200,
          render: (text, record) => {
            const editable = this.isEditing(record);
            return (
              <div>
                {editable ? (
                  <span>
                    <EditableContext.Consumer>
                      {form => (
                        <a
                          href="javascript:;"
                          onClick={() => this.save(form, record.key)}
                          style={{ marginRight: 8 }}
                        >
                          保存
                        </a>
                      )}
                    </EditableContext.Consumer>
                    <Popconfirm
                      title="放弃修改吗?"
                      onConfirm={() => this.cancel(record.key)}
                    >
                      <a>取消</a>
                    </Popconfirm>
                  </span>
                ) : (
                    <div>
                    <Button onClick={() => this.edit(record.key)} 
                        type="primary" icon="edit" ></Button>
                    <Popconfirm
                      title="确认删除吗?"
                      onConfirm={() => this.delete(record)}
                    >
                        <Button type="danger" icon="delete" />
                    </Popconfirm>
                    </div>
                )}
              </div>
            );
          },
        }];
      
          const editableColumns = columns.map((col) => {
            if (!col.editable) {
              return col;
            }
            return {
              ...col,
              onCell: record => ({
                record,
                inputType: 'text',
                dataIndex: col.dataIndex,
                title: col.title,
                editing: this.isEditing(record),
              }),
            };
          });
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
                                    >
                                    {loading ? '正在加载' : '刷新'}
                                    </Button>
                                </div>
                                <Table 
                                    onRow={(record) => {
                                        return {
                                        onClick: () => {},       // 点击行
                                        onMouseEnter: () => {},  // 鼠标移入行
                                        };
                                    }}
                                    bordered
                                    onChange={this.onChange}
                                    loading={this.state.loading}
                                    components={components}
                                    columns={editableColumns} 
                                    dataSource={this.state.data}
                                    rowClassName="editable-row customRowHighlight"
                                    pagination={
                                        {
                                            current:this.state.pageNo,
                                            pageSize:this.state.pageSize,
                                            onChange:this.paginationOnChange,
                                            showSizeChanger: true,
                                            onShowSizeChange: this.paginationOnPageSizeChange,
                                            total: this.state.totalCount
                                        }
                                    }
                                />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default MerchantList;