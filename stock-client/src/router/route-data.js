/** note: sub-menu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    will control the page roles (you can set multiple roles)
    title: 'title'               the name show in sub-menu and breadcrumb (recommend set)
    icon: 'font-awesome-name'    the icon show in the sidebar
    noCache: true                if true, the page will no be cached(default is false)
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
    affix: true                  if true, the tag will affix in the tags-view
  }
 **/

export const constantRoutesData = [
  {
    path: '/redirect',
    component: () => import('@/layout/Layout'),
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '',
    component: () => import('@/layout/Layout'),
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard.name', icon: 'home', noCache: true, affix: true }
      }
    ]
  }
]

export const asyncRoutesData = [
  {
    path: '/avatar',
    component: () => import('@/layout/Layout'),
    hidden: true,
    children: [
      {
        path: 'edit',
        component: () => import('@/views/avatar/avatar-edit'),
        name: 'avatarEdit',
        meta: { title: 'navbar.avatar'}
      }
    ]
  },
  {
    path: '/manager',
    component: () => import('@/layout/Layout'),
    meta: {
      roles: ['admin'],
      title: 'manager.name',
      icon: 'tasks'
    },
    children: [
      {
        path: 'route/edit',
        component: () => import('@/views/route/index'),
        name: 'edit-route',
        meta: { title: 'route.edit', icon: 'road' }
      },
      {
        path: 'user/list',
        component: () => import('@/views/user/index'),
        name: 'user-list',
        meta: { title: 'user.list', icon: 'user' }
      }
    ]
  },
  {
    path: '/stock',
    component: () => import('@/layout/Layout'),
    meta: {
      roles: ['normal'],
      title: 'stock.name',
      icon: 'line-chart'
    },
    children: [
      {
        path: 'knowledge',
        component: () => import('@/views/stock/knowledge/index'),
        name: 'stock-knowledge',
        meta: { title: 'stock.knowledge.name', icon: 'line-chart' }
      },
      {
        path: 'trade',
        component: () => import('@/views/stock/trade/index'),
        name: 'trade',
        meta: { title: 'stock.trade.name', icon: 'line-chart' }
      },
      {
        path: 'list',
        component: () => import('@/views/stock/stock-list/index'),
        name: 'stock-list',
        meta: { title: 'stock.list.listName', icon: 'user' }
      },
      {
        path: 'detail',
        component: () => import('@/views/stock/stock-detail/index'),
        name: 'stock-detail',
        meta: { title: 'stock.detail.name', icon: 'line-chart' }
      }
    ]
  }
]
