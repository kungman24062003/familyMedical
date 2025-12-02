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
import PersonManagementView from '@/views/admin/PersonManagementView.vue'
import HouseholdView from '@/views/admin/HouseholdView.vue'
import TaskView from '../views/admin/TaskView.vue'
import StatisticsView from '@/views/admin/StatisticsView.vue'
import ProgramView from '@/views/admin/ProgramView.vue'

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
      children: [
        { path: 'dashboard', component: DashboardHome },
        { path: 'ho-gia-dinh/nhan-khau', component: PersonManagementView },
        { path: 'ho-gia-dinh/ho-khau', component: HouseholdView },
        { path: 'ho-gia-dinh/giao-viec', component: TaskView },
        { path: 'thong-ke', component: StatisticsView },
        { path: 'ct-cssk', component: ProgramView },
      ]
    },

    // ── Redirect nếu không khớp route ─────────────────────────────
    { path: '/:pathMatch(.*)*', redirect: '/login' }
  ]
})

// ── Navigation Guard (bảo vệ route) ─────────────────────────────
router.beforeEach((to, from, next) => {
  const patientLoggedIn = !!localStorage.getItem('patientToken')
  const adminLoggedIn = !!localStorage.getItem('adminToken')

  // Route cần auth (ví dụ admin hoặc patient)
  if (to.meta.requiresAuth) {
    if (to.path.startsWith('/admin')) {
      // Trang admin → cần admin login
      if (!adminLoggedIn) next('/admin/login')
      else next()
    } else {
      // Trang patient → cần patient login
      if (!patientLoggedIn) next('/login')
      else next()
    }
  } else {
    // Trang login/register → nếu đã login → redirect đúng dashboard
    if (to.path === '/login' || to.path === '/register') {
      if (patientLoggedIn) next({ name: 'Dashboard' })
      else next()
    } else if (to.path === '/admin/login' || to.path === '/admin/register') {
      if (adminLoggedIn) next('/admin/dashboard')
      else next()
    } else {
      next()
    }
  }
})


export default router
