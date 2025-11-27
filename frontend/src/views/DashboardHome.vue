<!-- src/views/DashboardHome.vue -->
<template>
  <div class="pa-6">
    <!-- Tiêu đề -->
    <div class="d-flex align-center mb-8">
      <v-icon size="48" color="primary" class="mr-4">mdi-view-dashboard</v-icon>
      <div>
        <h1 class="text-h4 font-weight-bold">Tổng quan hệ thống</h1>
        <p class="text-body-1 text-medium-emphasis mb-0">
          Chào mừng quay trở lại, <strong>Báo Duy Tân</strong>! Hôm nay là {{ today }}
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

    <!-- Thông báo nhanh -->
    <v-alert
      type="success"
      variant="tonal"
      class="mb-6"
      title="Hệ thống hoạt động bình thường"
      text="Phiên bản 2.4.1 – Cập nhật ngày 26/11/2025"
    />

    <!-- Hướng dẫn nhanh -->
    <v-card>
      <v-card-title class="text-h6 bg-blue-grey-darken-4 text-white">
        <v-icon start>mdi-lightbulb-on</v-icon>
        Bắt đầu nhanh
      </v-card-title>
      <v-card-text class="pa-8">
        <v-row>
          <v-col cols="12" md="4">
            <v-btn block size="x-large" color="primary" class="mb-4" @click="$router.push('/ho-gia-dinh/nhan-khau')">
              <v-icon start size="32">mdi-account-plus</v-icon>
              Thêm nhân khẩu mới
            </v-btn>
          </v-col>
          <v-col cols="12" md="4">
            <v-btn block size="x-large" color="success" class="mb-4" @click="$router.push('/ho-gia-dinh/giao-viec')">
              <v-icon start size="32">mdi-clipboard-check</v-icon>
              Giao nhiệm vụ CSSK
            </v-btn>
          </v-col>
          <v-col cols="12" md="4">
            <v-btn block size="x-large" color="info" class="mb-4" @click="$router.push('/thong-ke')">
              <v-icon start size="32">mdi-chart-bar</v-icon>
              Xem thống kê
            </v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </div>
</template>

<script setup lang="ts">
const today = new Date().toLocaleDateString('vi-VN', {
  weekday: 'long',
  year: 'numeric',
  month: 'long',
  day: 'numeric'
})
</script>