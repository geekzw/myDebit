/**
 * Created by hao.cheng on 2017/4/16.
 */
import React from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { fetchData, receiveData } from '@/action';
import { notifyPop } from '../../axios';

const FormItem = Form.Item;

class Login extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            loading: false
        }
    }
    componentWillMount() {
        const { receiveData } = this.props;
        receiveData(null, 'resp');
    }
    // componentWillReceiveProps(nextProps) {
    //     const { auth: nextAuth = {} } = nextProps;
    //     const { history } = this.props;
    //     if (nextAuth.data && nextAuth.data.uid) {   // 判断是否登陆
    //         localStorage.setItem('user', JSON.stringify(nextAuth.data));
    //         history.push('/');
    //     }
    // }
    componentDidUpdate(prevProps) { // React 16.3+弃用componentWillReceiveProps
        const { resp: resp = {}, history } = this.props;
        var respData = resp.data;
        // const { history } = this.props;
        if (!resp.isFetching && this.state.loading){
            this.setState({loading:false});
        }
        if (respData && respData.code === 1000) {   // 判断是否登陆
            localStorage.setItem('user', JSON.stringify(respData.data));
            history.push('/');
        }else if(prevProps && prevProps.resp && prevProps.resp.isFetching && respData){
            notifyPop('提示',respData.desc,<Icon type="smile-circle" style={{ color: 'red' }} />);
        }
    }
    handleSubmit = (e) => {
        this.setState({loading:true});
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                const { fetchData } = this.props;
                fetchData({
                    funcName: 'login',
                    params: {
                        username: values.userName,
                        password: values.password
                    },
                    stateName: 'resp'
                })
            }
        });
    };
    gitHub = () => {
        // window.location.href = 'https://github.com/login/oauth/authorize?client_id=792cdcd244e98dcd2dee&redirect_uri=http://localhost:3006/&scope=user&state=reactAdmin';
    };
    render() {
        const { getFieldDecorator } = this.props.form;
        var loading = this.state.loading;
        return (
            <div className="login">
                <div className="login-form" >
                    <div className="login-logo">
                        <span>管家记账后台管理</span>
                    </div>
                    <Form onSubmit={this.handleSubmit} style={{maxWidth: '300px'}}>
                        <FormItem>
                            {getFieldDecorator('userName', {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="用户名" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('password', {
                                rules: [{ required: true, message: '请输入密码!' }],
                            })(
                                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="密码" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                            )}
                            {/* <a className="login-form-forgot" href="" style={{float: 'right'}}>忘记密码</a> */}
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{width: '100%'}} disabled={loading} loading={loading}>
                                登录
                            </Button>
                            {/* <p style={{display: 'flex', justifyContent: 'space-between'}}>
                                <a href="">或 现在就去注册!</a>
                                <a onClick={this.gitHub} ><Icon type="github" />(第三方登录)</a>
                            </p> */}
                        </FormItem>
                    </Form>
                </div>
            </div>

        );
    }
}

const mapStateToPorps = state => {
    const { resp } = state.httpData;
    return { resp };
};
const mapDispatchToProps = dispatch => ({
    fetchData: bindActionCreators(fetchData, dispatch),
    receiveData: bindActionCreators(receiveData, dispatch)
});


export default connect(mapStateToPorps, mapDispatchToProps)(Form.create()(Login));