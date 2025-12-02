// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'

// Trang không cần layout
import LoginPatient from '@/views/patient/LoginPatient.vue'
import RegisterPatient from '@/views/patient/RegisterPatient.vue'
import Login from '@/views/admin/LoginView.vue'
import Register from '@/views/admin/RegisterView.vue'

// Layout chính cho patient
import DefaultLayoutPatient from '@/views/layout/DefaultLayoutPatient.vue'

// Các view trong patient dashboard
import Dashboard from '@/views/patient/Dashboard.vue'
// import Profile from '@/views/patient/Profile.vue'
// import Family from '@/views/patient/Family.vue'
// import SendToDoctor from '@/views/patient/SendToDoctor.vue'
// import Feedback from '@/views/patient/Feedback.vue'

// Layout chính cho admin
import DefaultLayout from '@/views/layout/DefaultLayout.vue'

// Các view trong patient dashboard
import FamilyMember from '@/views/patient/FamilyMember.vue'
import FindDoctor from '@/views/patient/FindDoctor.vue'

// Các view trong admin dashboard
import DashboardHome from '@/views/admin/DashboardHome.vue'
import BenhNhanListView from '@/views/admin/BenhNhanListView.vue'
import BenhNhanDetailView from '@/views/admin/BenhNhanDetailView.vue'

import BacSiListView from '@/views/admin/BacSiListView.vue'
import BacSiDetailView from '@/views/admin/BacSiDetailView.vue'

import KhamBenhView from '@/views/admin/KhamBenhView.vue'

import LichSuKhamListView from '@/views/admin/LichSuKhamListView.vue'
import LichSuKhamDetailView from '@/views/admin/LichSuKhamDetailView.vue'

import BaoCaoView from '@/views/admin/BaoCaoView.vue'

const router = createRouter({
  history: createWebHistory(),
  scrollBehavior: () => ({ top: 0 }),
  routes: [
    // ── Trang đăng nhập / đăng ký (Patient) ─────────────────
    { path: '/', redirect: '/login' },
    { path: '/login', component: LoginPatient, meta: { requiresAuth: false } },
    { path: '/register', component: RegisterPatient, meta: { requiresAuth: false } },

    {
      path: '/',
      component: DefaultLayoutPatient,
      meta: { requiresAuth: true },
      children: [
        { path: '', name: 'Dashboard', component: Dashboard },
        { path: 'member', component: FamilyMember },
        { path: 'doctor', component: FindDoctor },
      ]
    },

    // ── Trang đăng nhập / đăng ký (Admin) ─────────────────
        { path: '/admin', redirect: '/admin/login' },
        { path: '/admin/login', component: Login, meta: { requiresAuth: false } },
        { path: '/admin/register', component: Register, meta: { requiresAuth: false } },

    // ── Tất cả trang admin → dùng DefaultLayout và thêm /admin ───────
    {
      path: '/admin',
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

    // ── Redirect nếu không khớp route ─────────────────────────────
    { path: '/:pathMatch(.*)*', redirect: '/admin/dashboard' }
  ]
})

// // ── Navigation Guard (bảo vệ route) ─────────────────────────────
// router.beforeEach((to, from, next) => {
//   const patientLoggedIn = !!localStorage.getItem('patientToken')
//   const adminLoggedIn = !!localStorage.getItem('adminToken')

//   // Route cần auth (ví dụ admin hoặc patient)
//   if (to.meta.requiresAuth) {
//     if (to.path.startsWith('/admin')) {
//       // Trang admin → cần admin login
//       if (!adminLoggedIn) next('/admin/login')
//       else next()
//     } else {
//       // Trang patient → cần patient login
//       if (!patientLoggedIn) next('/login')
//       else next()
//     }
//   } else {
//     // Trang login/register → nếu đã login → redirect đúng dashboard
//     if (to.path === '/login' || to.path === '/register') {
//       if (patientLoggedIn) next({ name: 'Dashboard' })
//       else next()
//     } else if (to.path === '/admin/login' || to.path === '/admin/register') {
//       if (adminLoggedIn) next('/admin/dashboard')
//       else next()
//     } else {
//       next()
//     }
//   }
// })


export default router
