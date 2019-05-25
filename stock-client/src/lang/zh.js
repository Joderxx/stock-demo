export default {
  logo: '管理后台',
  manager: {
    name: '后台管理'
  },
  route: {
    name: '路由操作',
    edit: '路由编辑',
    select: '查看路由',
    update: '修改路由',
    add: '添加路由',
    delete: '删除路由',
    'select-route': {
      select: '选择',
      expand: '展开',
      path: '路径',
      name: '名称',
      'i18n-name': '国际化名称',
      redirect: '重定向',
      base: '是否可编辑',
      permission: '权限',
      operate: '操作',
      detail: '详细信息',
      noCache: '是否缓存',
      icon: '图标'
    }
  },
  user: {
    name: '用户管理',
    list: '用户列表',
    'user-list': {
      id: 'ID',
      username: '用户名',
      role1: '权限1',
      role2: '权限2',
      role3: '权限3',
      operator: '操作',
      search: '输入关键字搜索',
      please: '请选择'
    }
  },
  avatar: {
    change: '修改头像',
  },
  stock: {
    name: '股票信息',
    list: {
      property: '属性列',
      listName: '股票数据',
      'ts_code': 'TS代码',
      symbol: '股票代码',
      name: '股票名称',
      area: '地域',
      industry: '行业',
      fullname: '全称',
      enname: '英文全称',
      market: '市场类型',
      exchange: '交易所代码',
      'curr_type': '交易货币',
      'list_status': '上市状态',
      'list_date': '上市日期',
      'delist_date': '退市日期',
      'is_hs': '是否沪深港通标'
    },
    trade: {
      name: '行情数据',
      type: '类型',
      daily: '日K线',
      weekly: '周K线',
      monthly: '月K线'
    },
    detail: {
      name: '详细信息'
    },
    knowledge: {
      name: "股票知识",
    }
  },
  dashboard: {
    name: '首页',
    subscribe: {
      name: '订阅的股票',
      stockName: '股票名称',
      current: '当前价格',
      rate: '涨跌幅'
    },

  },

  navbar: {
    logOut: '退出登录',
    dashboard: '首页',
    github: '项目地址',
    theme: '换肤',
    size: '布局大小',
    avatar: '修改头像'
  },
  login: {
    title: '系统登录',
    logIn: '登录',
    username: '账号',
    password: '密码',
    any: '随便填',
    support: '点击注册Tushare账号对该项目的支持。',
    thirdparty: '第三方登录',
    thirdpartyTips: '本地不能模拟，请结合自己业务进行模拟！！！',
    expireTime: '登录超时'
  },
  register: {
    name: '注册',
    title: '系统注册',
    register: '注册',
    username: '账号',
    password: '密码',
    rePassword: '确认密码',
  },
  documentation: {
    name: '文档',
    documentation: '文档',
    github: 'Github 地址'
  },
  permission: {
    addRole: '新增角色',
    editPermission: '编辑权限',
    roles: '你的权限',
    switchRoles: '切换权限',
    tips: '在某些情况下，不适合使用 v-permission。例如：Element-UI 的 Tab 组件或 el-table-column 以及其它动态渲染 dom 的场景。你只能通过手动设置 v-if 来实现。',
    delete: '删除',
    confirm: '确定',
    cancel: '取消'
  },
  guide: {
    name: '引导',
    description: '引导页对于一些第一次进入项目的人很有用，你可以简单介绍下项目的功能。本 Demo 是基于',
    button: '打开引导'
  },
  components: {
    documentation: '文档',
    tinymceTips: '富文本是管理后台一个核心的功能，但同时又是一个有很多坑的地方。在选择富文本的过程中我也走了不少的弯路，市面上常见的富文本都基本用过了，最终权衡了一下选择了Tinymce。更详细的富文本比较和介绍见',
    dropzoneTips: '由于我司业务有特殊需求，而且要传七牛 所以没用第三方，选择了自己封装。代码非常的简单，具体代码你可以在这里看到 @/components/Dropzone',
    stickyTips: '当页面滚动到预设的位置会吸附在顶部',
    backToTopTips1: '页面滚动到指定位置会在右下角出现返回顶部按钮',
    backToTopTips2: '可自定义按钮的样式、show/hide、出现的高度、返回的位置 如需文字提示，可在外部使用Element的el-tooltip元素',
    imageUploadTips: '由于我在使用时它只有vue@1版本，而且和mockjs不兼容，所以自己改造了一下，如果大家要使用的话，优先还是使用官方版本。'
  },
  table: {
    dynamicTips1: '固定表头, 按照表头顺序排序',
    dynamicTips2: '不固定表头, 按照点击顺序排序',
    dragTips1: '默认顺序',
    dragTips2: '拖拽后顺序',
    title: '标题',
    importance: '重要性',
    type: '类型',
    remark: '点评',
    search: '搜索',
    add: '添加',
    export: '导出',
    reviewer: '审核人',
    id: '序号',
    date: '时间',
    author: '作者',
    readings: '阅读数',
    status: '状态',
    actions: '操作',
    edit: '编辑',
    publish: '发布',
    draft: '草稿',
    delete: '删除',
    cancel: '取 消',
    confirm: '确 定'
  },
  errorLog: {
    tips: '请点击右上角bug小图标',
    description: '现在的管理后台基本都是spa的形式了，它增强了用户体验，但同时也会增加页面出问题的可能性，可能一个小小的疏忽就导致整个页面的死锁。好在 Vue 官网提供了一个方法来捕获处理异常，你可以在其中进行错误处理或者异常上报。',
    documentation: '文档介绍'
  },
  theme: {
    change: '换肤',
    documentation: '换肤文档',
    tips: 'Tips: 它区别于 navbar 上的 theme-pick, 是两种不同的换肤方法，各自有不同的应用场景，具体请参考文档。',
    success: '换肤成功'
  },
  tagsView: {
    refresh: '刷新',
    close: '关闭',
    closeOthers: '关闭其它',
    closeAll: '关闭所有'
  },
  button: {
    detail: '详情',
    click: '点击',
    delete: '删除',
    submit: '提交',
    edit: '编辑',
    finish: '完成'
  },
  general: {
    name: "名称",
    none: '无',
    have: '有',
    resultList: '结果列表',
    all: '全选',
    hide: '隐藏',
    date: '日期',
    startDate: '开始时间',
    endDate: '结束时间',
    to: '至',
    recentWeek: '最近一周',
    recentMonth: '最近一个月',
    recent3Month: '最近三个月',
    recentYear: '最近一年',
  }
}
