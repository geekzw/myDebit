/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { 
    Popconfirm, Input, Form, 
    Icon, BackTop, Button, InputNumber, 
} from 'antd';
import { Link } from 'react-router-dom';
import { getBannerList, editBanner, deleteBanner, notifyPop } from '../../axios';
import '../../style/table.less';
import MerchantList, { } from './MerchantList';
import BreadcrumbCustom from '../BreadcrumbCustom';
import AddBannerForm from '../forms/AddBannerForm';
const { TextArea } = Input;

const routes = [{
    path: 'app',
    breadcrumbName: '首页'
  }, {
    path: 'bannerList',
    breadcrumbName: 'Banner列表'
  }];
function itemRender(route, params, routes, paths) {
    const last = routes.indexOf(route) === routes.length - 1;
    return last ? <span>{route.breadcrumbName}</span> : <Link to={'/'+paths.join('/')}>{route.breadcrumbName}</Link>;
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
      return <InputNumber min={0} />;
    }
    return <TextArea autosize />;
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


class BannerList extends MerchantList {
    state = {
        ...(super.state)||{},
        data: [],
        canAddItem: true,
    };
    // 属性相关
    breadcrumbCustom() {
        return (<BreadcrumbCustom itemRender={itemRender} routes={routes} />);
    }
    tableTitle() {
        return "Banner列表";
    }
    toAddItem = () => 
        this.setState({
            editingRecord: null,
            isChecking: false,
            isAddingItem: true
        });
    columns = [
        {
            title: 'id',
            dataIndex: 'id',
            width: 60
        },
        {
            title: '产品名称',
            dataIndex: 'productName',
            width: 160
        },
        {
            title: '跳转链接',
            dataIndex: 'url',
            width: 180,
            editable: true,
            render: (text, record) => (<a href={text} target="_blank" >{text}</a>)
        },
        {
            title: '图片链接',
            dataIndex: 'image',
            width: 180,
            editable: true,
            render: (text, record) => (<a href={text} target="_blank" >{text}</a>)
        },
        {
            title: '排序',
            dataIndex: 'bannerOrder',
            width: 60,
            editable: true,
        },
        {
            title: '操作',
            dataIndex: 'operation',
            width: 80,
            render: (text, record) => {
              const editable = this.isEditing(record);
              return (
                <div>
                  {editable ? (
                    <span>
                      <EditableContext.Consumer>
                        {form => (
                          <a
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
                      <Button onClick={() => this.edit(record.key)} type="primary" icon="edit" />
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
        },
    ];
    // 网络相关
    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            var oldRow;
            for(var i=0;i<this.state.data.length;i++){
                if (this.state.data[i].key === key){
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
            editBanner(params).then(
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
    delete = (row) => {
        if(row){
            var params = {
                id:row.id,
            }
            deleteBanner(params).then(
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
    reload = () => {
        const { pageNo, pageSize } = this.state;
        this.start(pageNo,pageSize);
    };
    start = (
        pageNo=this.state.pageNo,
        pageSize=this.state.pageSize,
        searchParam=''
    ) => {
        (new BackTop({
            target:()=>document.getElementById('rightScroll')
        })).scrollToTop();
        this.setState({ loading: true });
        var params = {
            pageNo: pageNo,
            pageSize: pageSize,
            searchParam: searchParam
        }
        getBannerList(params).then(res => {
            console.log(params);
            console.log(res);
            if(!res.success){
                notifyPop('错误',res.desc,<Icon type="frown" />);
            }
            if(res.data){
                this.setState({
                    data: [...res.data.map(val => {
                        val.key = val.id;
                        return val;
                    })],
                    loading: false,
                    totalCount: res.totalCount,
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                    // registerCount: res.data.registerCount,
                    // intentCount: res.data.intentCount,
                    // accurateCount: res.data.accurateCount
                });
            }else{
                this.setState({
                    data: [],
                    loading: false,
                    totalCount: 0
                });
            }
        }).catch(err=>notifyPop('错误',err,<Icon type="frown" />,5));
    };
    tableComponents = {
        body: {
            row: EditableFormRow,
            cell: EditableCell,
          },
    }
    editableColumns(){ 
        return this.columns.map((col) => {
            if (!col.editable) {
            return col;
            }
            return {
            ...col,
            onCell: record => ({
                record,
                inputType: col.dataIndex==='bannerOrder' ? 'number' : 'text',
                dataIndex: col.dataIndex,
                title: col.title,
                editing: this.isEditing(record),
            }),
            };
        });
    }
    addButton() {
        return <AddBannerForm />
    }
}

export default BannerList;