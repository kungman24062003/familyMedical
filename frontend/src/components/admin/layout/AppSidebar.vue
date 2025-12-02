<!-- src/components/layout/AppSidebar.vue -->
<template>
  <v-navigation-drawer
    v-model="drawer"
    :temporary="temporary"
    width="280"
    class="bg-blue-grey-darken-4"
    theme="dark"
  >
    <v-list nav density="comfortable" class="py-3">

      <!-- Tổng quan -->
      <v-list-item
        to="/admin/dashboard"
        prepend-icon="mdi-view-dashboard"
        title="Tổng quan"
        class="mx-2"
        rounded="lg"
      />

      <v-list-subheader class="text-overline text-grey-lighten-1 pl-6 mt-4">
        QUẢN LÝ CHÍNH
      </v-list-subheader>

      <!-- Quản lý bệnh nhân -->
      <v-list-item
        to="/admin/benh-nhan"
        prepend-icon="mdi-hospital-box-outline"
        title="Quản lý bệnh nhân"
        class="mx-2"
        rounded="lg"
      />

      <!-- Quản lý bác sĩ -->
      <v-list-item
        to="/admin/bac-si"
        prepend-icon="mdi-stethoscope"
        title="Quản lý bác sĩ"
        class="mx-2"
        rounded="lg"
      />

      <!-- Khám bệnh -->
      <v-list-item
        to="/admin/kham-benh"
        prepend-icon="mdi-clipboard-pulse-outline"
        title="Quản lý khám bệnh"
        class="mx-2"
        rounded="lg"
      />

      <!-- Lịch sử khám bệnh -->
      <v-list-item
        to="/admin/lich-su-kham"
        prepend-icon="mdi-history"
        title="Lịch sử khám bệnh"
        class="mx-2"
        rounded="lg"
      />

      <v-list-subheader class="text-overline text-grey-lighten-1 pl-6 mt-4">
        BÁO CÁO & THỐNG KÊ
      </v-list-subheader>

      <v-list-item
        to="/admin/bao-cao"
        prepend-icon="mdi-file-chart-outline"
        title="Báo cáo - Thống kê"
        class="mx-2"
        rounded="lg"
      />
    </v-list>
  </v-navigation-drawer>
</template>