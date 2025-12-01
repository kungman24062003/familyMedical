<template>
  <v-app-bar color="#0d47a1" dark density="compact">
    <v-app-bar-nav-icon @click="$emit('toggle-drawer')"></v-app-bar-nav-icon>
    <v-toolbar-title class="d-flex align-center">
      <v-img src="/logo.png" max-width="40" class="mr-3"></v-img>
      <span class="text-h6 font-weight-bold">HỆ THỐNG HỒ SƠ SỨC KHỎE CÁ NHÂN</span>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-menu offset-y>
      <template v-slot:activator="{ props }">
        <v-btn icon v-bind="props">
          <v-avatar size="36">
            <v-img src="https://via.placeholder.com/36" alt="Báo Duy Tân"></v-img>
          </v-avatar>
        </v-btn>
      </template>
      <v-list>
        <v-list-item title="Báo Duy Tân" subtitle="Admin"></v-list-item>
        <v-divider></v-divider>
        <v-list-item title="Đăng xuất"></v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>