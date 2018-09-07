/**
 * Created by hao.cheng on 2017/4/15.
 */
import React, { Component, Fragment } from 'react';
import { Button, Modal, Form, Input, InputNumber, Icon } from 'antd';
import { addBanner, notifyPop } from '../../axios';
const FormItem = Form.Item;

const CollectionCreateForm = Form.create()(
    (props) => {
        const { visible, onCancel, onCreate, form } = props;
        const { getFieldDecorator } = form;
        return (
            <Modal
                visible={visible}
                title="增加Banner项"
                okText="创建"
                onCancel={onCancel}
                onOk={onCreate}
            >
                <Form layout="vertical">
                    <FormItem label="产品名称">
                        {getFieldDecorator('productName', {
                            rules: [{ required: true, message: '请输入产品名称!' }],
                        })(
                            <Input />
                        )}
                    </FormItem>
                    <FormItem label="跳转链接">
                        {getFieldDecorator('url', {
                            rules: [{ required: true, message: '请输入跳转链接!' }],
                        })(
                            <Input />
                        )}
                    </FormItem>
                    <FormItem label="图片链接">
                        {getFieldDecorator('image', {
                            rules: [{ required: true, message: '请输入图片链接!' }],
                        })(
                            <Input />
                        )}
                    </FormItem>
                    <FormItem label="排序">
                        {getFieldDecorator('bannerOrder', {
                            rules: [{ required: true, message: '请输入Banner排序!' }],
                        })(
                            <InputNumber min={0} values={0} />
                        )}
                    </FormItem>
                </Form>
            </Modal>
        );
    }
);

class AddBannerForm extends Component {
    state = {
        visible: false,
    };
    showModal = () => {
        this.setState({ visible: true });
    };
    handleCancel = () => {
        this.setState({ visible: false });
    };
    handleCreate = () => {
        const form = this.form;
        form.validateFields((err, values) => {
            if (err) {
                return;
            }
            addBanner(values).then(
                resp => {
                    console.log(values);
                    console.log(resp);
                    notifyPop('提示', resp.success ? '新增成功' : resp.desc);
                    if (resp.success) {
                        form.resetFields();
                        this.setState({ visible: false });
                    }
                }
            ).catch(err => notifyPop('错误', err, <Icon type="frown" />));
        });
    };
    saveFormRef = (form) => {
        this.form = form;
    };
    render() {
        return (
            <Fragment>
                <Button type="primary" onClick={this.showModal}>增加</Button>
                <CollectionCreateForm
                    ref={this.saveFormRef}
                    visible={this.state.visible}
                    onCancel={this.handleCancel}
                    onCreate={this.handleCreate}
                />
            </Fragment>
        );
    }
}

export default AddBannerForm;