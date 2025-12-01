<template>
  <div class="pa-6">
    <!-- Loading -->
    <div v-if="loading">Đang tải dữ liệu người dùng...</div>

    <!-- Dashboard chính -->
    <div v-else>
      <div class="d-flex align-center mb-8">
        <v-icon size="48" color="primary" class="mr-4">mdi-view-dashboard</v-icon>
        <div>
          <h1 class="text-h4 font-weight-bold">
            Chào mừng quay trở lại, <strong>{{ user.name }}</strong>!
          </h1>
          <p class="text-body-1 text-medium-emphasis mb-0">
            Email: {{ user.email }}<br>
            Hôm nay là {{ today }}
          </p>
        </div>
      </div>

      <!-- 4 ô thống kê lớn -->
      <v-row class="mb-8">
        <v-col cols="12" sm="6" lg="3">
          <v-card class="text-center pa-6" color="blue-darken-4" dark elevation="8">
            <v-icon size="64" class="mb-3">mdi-account-multiple</v-icon>
            <div class="text-h3 font-weight-bold">12,458</div>
            <div class="text-h6">Tổng nhân khẩu</div>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6" lg="3">
          <v-card class="text-center pa-6" color="green-darken-3" dark elevation="8">
            <v-icon size="64" class="mb-3">mdi-home-group</v-icon>
            <div class="text-h3 font-weight-bold">3,892</div>
            <div class="text-h6">Tổng hộ gia đình</div>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6" lg="3">
          <v-card class="text-center pa-6" color="orange-darken-3" dark elevation="8">
            <v-icon size="64" class="mb-3">mdi-clipboard-check</v-icon>
            <div class="text-h3 font-weight-bold">156</div>
            <div class="text-h6">Nhiệm vụ hôm nay</div>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6" lg="3">
          <v-card class="text-center pa-6" color="purple-darken-3" dark elevation="8">
            <v-icon size="64" class="mb-3">mdi-file-document-multiple</v-icon>
            <div class="text-h3 font-weight-bold">8</div>
            <div class="text-h6">Chương trình đang chạy</div>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/api'
import router from '@/router'

const route = useRoute()

const user = ref<any>(null)
const loading = ref(true)

// Ngày hôm nay
const today = new Date().toLocaleDateString('vi-VN', {
  weekday: 'long',
  year: 'numeric',
  month: 'long',
  day: 'numeric'
})

onMounted(async () => {
  console.log('Dashboard mounted');
  const queryToken = route.query.token as string | undefined;
  if (queryToken) {
    console.log('Token found in query:', queryToken.substring(0, 10) + '...');
    localStorage.setItem('jwtToken', queryToken);
    // Clean URL so ?token=... disappears
    router.replace({ path: '/dashboard' });
  }

  const token = localStorage.getItem('jwtToken');
  console.log('Token in localStorage:', token ? token.substring(0, 10) + '...' : 'null');
  if (!token) {
    console.log('No token, redirecting to OAuth');
    // No token yet → redirect to Google OAuth
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    return;
  }

  try {
    console.log('Trying to fetch user with token');
    const res = await api.get('/users/me');
    console.log('User data:', res.data);
    user.value = res.data;
  } catch (err) {
    console.error('Error fetching user:', err);
    // Invalid token → remove and redirect again
    localStorage.removeItem('jwtToken');
    console.log('Removed token, redirecting to OAuth');
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  } finally {
    loading.value = false;
  }
});
</script>
