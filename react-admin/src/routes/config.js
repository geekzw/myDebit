export default {
    menus: [    // 菜单相关路由
        { key: '/app', title: '首页', icon: 'mobile', component: 'Dashboard' },
        {
            key: '/app/merchantList', title: '商家列表', icon: 'shopping-cart',component: 'MerchantList', permission: [0],
        },
        {
            key: '/app/merchantDetail', title: '流量详情', icon: 'line-chart',component: 'MerchantDetail', permission: [1,2],
        },
        {
            key: '/app/bannerList', title: 'Banner', icon: 'tags',component: 'BannerList', permission: [0],
        },
        {
            key: '/app/borrowList', title: '借贷列表', icon: 'bank',component: 'BorrowList', permission: [0],
        },
        {
            key: '/app/setting', title: '设置', icon: 'setting',
            subs: [
                { key: '/app/setting/rule', title: '规则设置', component: 'RuleSetting', permission: [0]},
            ],
        },
        // {
        //     key: '/app/ui', title: 'UI', icon: 'scan',
        //     subs: [
        //         { key: '/app/ui/buttons', title: '按钮', component: 'Buttons'},
        //         { key: '/app/ui/icons', title: '图标', component: 'Icons'},
        //         { key: '/app/ui/spins', title: '加载中', component: 'Spins'},
        //         { key: '/app/ui/modals', title: '对话框', component: 'Modals'},
        //         { key: '/app/ui/notifications', title: '通知提醒框', component: 'Notifications'},
        //         { key: '/app/ui/tabs', title: '标签页', component: 'Tabs'},
        //         { key: '/app/ui/banners', title: '轮播图', component: 'Banners'},
        //         { key: '/app/ui/wysiwyg', title: '富文本', component: 'WysiwygBundle'},
        //         { key: '/app/ui/drags', title: '拖拽', component: 'Drags'},
        //         { key: '/app/ui/gallery', title: '画廊', component: 'Gallery'},
        //         { key: '/app/ui/map', title: '地图', component: 'MapUi'},
        //     ],
        // },
        // {
        //     key: '/app/animation', title: '动画', icon: 'rocket',
        //     subs: [
        //         { key: '/app/animation/basicAnimations', title: '基础动画', component: 'BasicAnimations'},
        //         { key: '/app/animation/exampleAnimations', title: '动画案例', component: 'ExampleAnimations'},
        //     ],
        // },
        // {
        //     key: '/app/table', title: '表格', icon: 'copy',
        //     subs: [
        //         { key: '/app/table/basicTable', title: '基础表格', component: 'BasicTable'},
        //         { key: '/app/table/advancedTable', title: '高级表格', component: 'AdvancedTable'},
        //         { key: '/app/table/asynchronousTable', title: '异步表格', component: 'AsynchronousTable'},
        //     ],
        // },
        // {
        //     key: '/app/form', title: '表单', icon: 'edit',
        //     subs: [
        //         { key: '/app/form/basicForm', title: '基础表单', component: 'BasicForm'},
        //     ],
        // },
        // {
        //     key: '/app/chart', title: '图表', icon: 'area-chart',
        //     subs: [
        //         { key: '/app/chart/echarts', title: 'echarts', component: 'Echarts' },
        //         { key: '/app/chart/recharts', title: 'recharts', component: 'Recharts' },
        //     ],
        // },
        // {
        //     key: '/subs4', title: '页面', icon: 'switcher',
        //     subs: [
        //         { key: '/login', title: '登录' },
        //         { key: '/404', title: '404' },
        //     ],
        // },
        // {
        //     key: '/app/auth', title: '权限管理', icon: 'safety',
        //     subs: [
        //         { key: '/app/auth/basic', title: '基础演示', component: 'AuthBasic' },
        //         { key: '/app/auth/routerEnter', title: '路由拦截', component: 'RouterEnter', auth: 'auth/testPage' },
        //     ],
        // },
        // {
        //     key: '/app/cssModule', title: 'cssModule', icon: 'star', component: 'Cssmodule'
        // },
    ],
    others: [
        {
            key: '/app/merchantList/merchantDetail/:id', title: '商家', icon: 'bank',component: 'MerchantDetail', permission: [0]
        }
    ]  // 非菜单相关路由
}