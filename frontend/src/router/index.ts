// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'

// Trang không cần layout
import Login from '@/views/LoginView.vue'
import Register from '@/views/RegisterView.vue'

// Layout chính
import DefaultLayout from '@/views/layout/DefaultLayout.vue'

// Các view trong dashboard
import DashboardHome from '../views/DashboardHome.vue'
import PersonManagementView from '@/views/PersonManagementView.vue'
import HouseholdView from '@/views/HouseholdView.vue'
import TaskView from '../views/TaskView.vue'
import StatisticsView from '@/views/StatisticsView.vue'
import ProgramView from '@/views/ProgramView.vue'

const router = createRouter({
  history: createWebHistory(),
  scrollBehavior: () => ({ top: 0 }),
  routes: [
    // ── Trang đăng nhập / đăng ký (không có layout) ─────────────────
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login, meta: { requiresAuth: false } },
    { path: '/register', component: Register, meta: { requiresAuth: false } },

    // ── Tất cả trang sau khi đăng nhập → dùng DefaultLayout ───────
    {
      path: '/',
      component: DefaultLayout,
      children: [
        { path: 'dashboard', component: DashboardHome },
        { path: 'ho-gia-dinh/nhan-khau', component: PersonManagementView },
        { path: 'ho-gia-dinh/ho-khau', component: HouseholdView },
        { path: 'ho-gia-dinh/giao-viec', component: TaskView },
        { path: 'thong-ke', component: StatisticsView },
        { path: 'ct-cssk', component: ProgramView },
      ]
    },

    { path: '/:pathMatch(.*)*', redirect: '/dashboard' }

  ]
})

// ── Navigation Guard (tùy chọn – bảo vệ route) ─────────────────────
router.beforeEach((to, from, next) => {
  const isLoggedIn = !!localStorage.getItem('token') // hoặc cách check của bạn

  if (to.meta.requiresAuth && !isLoggedIn) {
    next('/login')
  } else if ((to.path === '/login' || to.path === '/register') && isLoggedIn) {
    next('/dashboard')
  } else {
    next()
  }
})

export default router