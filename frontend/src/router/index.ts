// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'

// ── Trang không cần layout (public) ─────────────────────
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'

// ── Layout chính ───────────────────────────────────────
import DefaultLayout from '@/views/layout/DefaultLayout.vue'

// ── Các trang chính trong hệ thống phòng khám ───────────
import DashboardHome from '@/views/DashboardHome.vue'

import BenhNhanListView from '@/views/BenhNhanListView.vue'
import BenhNhanDetailView from '@/views/BenhNhanDetailView.vue'

import BacSiListView from '@/views/BacSiListView.vue'
import BacSiDetailView from '@/views/BacSiDetailView.vue'

import KhamBenhView from '@/views/KhamBenhView.vue'

import LichSuKhamListView from '@/views/LichSuKhamListView.vue'
import LichSuKhamDetailView from '@/views/LichSuKhamDetailView.vue'

import BaoCaoView from '@/views/BaoCaoView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior: () => ({ top: 0 }),
  routes: [
    // ── Trang đăng nhập / đăng ký ───────────────────────────
    { path: '/', redirect: '/login' },
    {
      path: '/login',
      name: 'Login',
      component: LoginView,
      meta: { requiresAuth: false, layout: 'empty' }
    },
    {
      path: '/register',
      name: 'Register',
      component: RegisterView,
      meta: { requiresAuth: false, layout: 'empty' }
    },

    // ── Các trang chính (có DefaultLayout) ──────────────────
    {
      path: '/',
      component: DefaultLayout,
      meta: { requiresAuth: true },
      children: [
        { path: 'dashboard', name: 'Dashboard', component: DashboardHome, meta: { title: 'Tổng quan' } },

        { path: 'benh-nhan', name: 'BenhNhanList', component: BenhNhanListView, meta: { title: 'Quản lý bệnh nhân' } },
        { path: 'benh-nhan/:id', name: 'BenhNhanDetail', component: BenhNhanDetailView, props: true, meta: { title: 'Chi tiết bệnh nhân' } },

        { path: 'bac-si', name: 'BacSiList', component: BacSiListView, meta: { title: 'Quản lý bác sĩ' } },
        { path: 'bac-si/:id', name: 'BacSiDetail', component: BacSiDetailView, props: true, meta: { title: 'Chi tiết bác sĩ' } },

        { path: 'kham-benh', name: 'KhamBenh', component: KhamBenhView, meta: { title: 'quản lý khám bệnh' } },

        { path: 'lich-su-kham', name: 'LichSuKhamList', component: LichSuKhamListView, meta: { title: 'Lịch sử khám bệnh' } },
        { path: 'lich-su-kham/:id', name: 'LichSuKhamDetail', component: LichSuKhamDetailView, meta: { title: 'Chi tiết phiếu khám' } },

        { path: 'bao-cao', name: 'BaoCao', component: BaoCaoView, meta: { title: 'Báo cáo - Thống kê' } }
      ]
    },

    // ── 404 ───────────────────────────────────────────────
    { path: '/:pathMatch(.*)*', redirect: '/dashboard' }
  ]
})

// ── Bảo vệ route ───────────────────────────────────────
// router.beforeEach((to, from, next) => {
//   const isLoggedIn = !!localStorage.getItem('token')

//   if (to.meta.requiresAuth && !isLoggedIn) {
//     next('/login')
//   } else if ((to.name === 'Login' || to.name === 'Register') && isLoggedIn) {
//     next('/dashboard')
//   } else {
//     next()
//   }
// })

export default router