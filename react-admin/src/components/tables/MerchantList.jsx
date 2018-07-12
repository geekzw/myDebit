/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { 
    Table, Input, InputNumber, 
    Popconfirm, Form, Button, 
    Row, Col, Card,
    notification, Icon
} from 'antd';
import { getMerchant, editMerchant, deleteMerchant, notifyPop } from '../../axios';
import BreadcrumbCustom from '../BreadcrumbCustom';

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
        loading: false,
        data: []
    };
    componentDidMount() {
        this.start();
    }
    componentWillUnmount() {
        
    }
    start = () => {
        this.setState({ loading: true });
        const { sessionId } = JSON.parse(localStorage.getItem('user'));
        getMerchant({
            sessionId: sessionId,
            pageNo: 1,
            pageSize: 20
        }).then(res => {
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
    isEditing = (record) => {
        return record.key === this.state.editingKey;
    };
    edit(key) {
        this.setState({ editingKey: key });
    }
    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            var oldRow = this.state.data[key-1];
            var isSameData = Object.keys(row).reduce(
                (rs,k) => {
                    var r = true;
                    if(k){
                        if (typeof(rs)=='string'){
                            r = row[rs] == oldRow[rs];
                        }else{
                            r = rs;
                        }
                        return r && (row[k] == oldRow[k]);
                    }else{
                        return rs;
                    }
                }
            );
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
                    notifyPop('提示',resp.desc);
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
                    notifyPop('提示',resp.desc);
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
                                onChange={this.onChange}
                                components={components}
                                columns={editableColumns} 
                                dataSource={this.state.data}
                                rowClassName="editable-row"
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