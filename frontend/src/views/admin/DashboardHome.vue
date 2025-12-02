<template>
  <div class="pa-6">
    <!-- Tiêu đề -->
    <div class="d-flex align-center mb-8">
      <v-icon size="48" color="teal" class="mr-4">mdi-heart-pulse</v-icon>
      <div>
        <h1 class="text-h4 font-weight-bold">Hồ sơ sức khỏe gia đình</h1>
        <p class="text-body-1 text-medium-emphasis mb-0">
          Chào mừng quay lại, <strong>Báo Duy Tân</strong>! Hôm nay là {{ today }}
        </p>
      </div>
    </div>

    <!-- 4 ô thống kê lớn – chủ đề y tế -->
    <v-row class="mb-8">
      <v-col cols="12" sm="6" lg="3">
        <v-card class="text-center pa-6" color="blue-darken-3" dark elevation="8">
          <v-icon size="64" class="mb-3">mdi-account-heart</v-icon>
          <div class="text-h3 font-weight-bold">12,458</div>
          <div class="text-h6">Tổng hồ sơ sức khỏe</div>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" lg="3">
        <v-card class="text-center pa-6" color="green-darken-2" dark elevation="8">
          <v-icon size="64" class="mb-3">mdi-home-heart</v-icon>
          <div class="text-h3 font-weight-bold">3,892</div>
          <div class="text-h6">Hộ gia đình đang theo dõi</div>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" lg="3">
        <v-card class="text-center pa-6" color="orange-darken-3" dark elevation="8">
          <v-icon size="64" class="mb-3">mdi-calendar-alert</v-icon>
          <div class="text-h3 font-weight-bold">156</div>
          <div class="text-h6">Lịch khám / Tiêm chủng hôm nay</div>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" lg="3">
        <v-card class="text-center pa-6" color="red-darken-2" dark elevation="8">
          <v-icon size="64" class="mb-3">mdi-alert-decagram</v-icon>
          <div class="text-h3 font-weight-bold">8</div>
          <div class="text-h6">Cảnh báo sức khỏe cần xử lý</div>
        </v-card>
      </v-col>
    </v-row>

    <!-- Thông báo hệ thống -->
    <v-alert
      type="success"
      variant="tonal"
      class="mb-6"
      title="Hệ thống hoạt động ổn định"
      text="Phiên bản 2.4.1 – Cập nhật ngày 26/11/2025"
    />

    <!-- Hướng dẫn nhanh – các chức năng y tế chính -->
    <v-card>
      <v-card-title class="text-h6 bg-teal text-white">
        <v-icon start>mdi-playlist-plus</v-icon>
        Bắt đầu nhanh với hồ sơ y tế
      </v-card-title>
      <v-card-text class="pa-8">
        <v-row>
          <v-col cols="12" md="4">
            <v-btn
              block
              size="x-large"
              color="primary"
              class="mb-4"
              @click="$router.push('/ho-so-y-te/them-moi')"
            >
              <v-icon start size="32">mdi-account-plus</v-icon>
              Thêm hồ sơ sức khỏe mới
            </v-btn>
          </v-col>

          <v-col cols="12" md="4">
            <v-btn
              block
              size="x-large"
              color="success"
              class="mb-4"
              @click="$router.push('/ho-so-y-te/lich-kham')"
            >
              <v-icon start size="32">mdi-calendar-clock</v-icon>
              Quản lý lịch khám & tiêm chủng
            </v-btn>
          </v-col>

          <v-col cols="12" md="4">
            <v-btn
              block
              size="x-large"
              color="indigo"
              class="mb-4"
              @click="$router.push('/ho-so-y-te/canh-bao')"
            >
              <v-icon start size="32">mdi-bell-alert</v-icon>
              Xem cảnh báo & nhắc thuốc
            </v-btn>
          </v-col>
        </v-row>

        <v-row class="mt-4">
          <v-col cols="12" md="6">
            <v-btn
              block
              size="large"
              variant="outlined"
              color="deep-purple"
              @click="$router.push('/thong-ke-y-te')"
            >
              <v-icon start>mdi-chart-pie</v-icon>
              Báo cáo – Thống kê sức khỏe
            </v-btn>
          </v-col>

          <v-col cols="12" md="6">
            <v-btn
              block
              size="large"
              variant="outlined"
              color="teal"
              @click="$router.push('/ho-so-y-te/tra-cuu')"
            >
              <v-icon start>mdi-magnify</v-icon>
              Tra cứu hồ sơ nhanh
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

<style scoped>
.v-card-title.bg-teal {
  background: linear-gradient(135deg, #00695c, #009688);
}
</style>