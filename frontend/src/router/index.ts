// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/LoginView.vue'
import Register from '@/views/RegisterView.vue'
import Dashboard from '@/views/DashboardView.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/dashboard', component: Dashboard }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router