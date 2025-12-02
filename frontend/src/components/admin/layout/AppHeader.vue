<!-- src/components/layout/AppHeader.vue -->
<template>
  <v-app-bar color="#0d47a1" dark density="compact" elevation="2">
    <v-app-bar-nav-icon @click="$emit('toggle-drawer')"></v-app-bar-nav-icon>

    <v-toolbar-title class="d-flex align-center">
      <v-img src="/logo.png" max-width="40" class="mr-3" contain></v-img>
      <span class="text-h6 font-weight-bold">HỆ THỐNG QUẢN LÝ HỒ SƠ Y TẾ GIA ĐÌNH</span>
    </v-toolbar-title>

    <v-spacer></v-spacer>

    <!-- User menu -->
    <v-menu offset-y open-on-hover>
      <template v-slot:activator="{ props }">
        <v-btn icon v-bind="props" class="mr-2">
          <v-avatar size="38">
            <v-img src="https://ui-avatars.com/api/?name=Admin&background=0D47A1&color=fff&bold=true"></v-img>
          </v-avatar>
        </v-btn>
      </template>
      <v-list density="compact">
        <v-list-item>
          <v-list-item-title class="font-weight-bold">Bác sĩ Nguyễn Văn A</v-list-item-title>
          <v-list-item-subtitle>Bác sĩ đa khoa</v-list-item-subtitle>
        </v-list-item>
        <v-divider></v-divider>
        <v-list-item prepend-icon="mdi-cog" title="Cài đặt"></v-list-item>
        <v-list-item prepend-icon="mdi-logout" title="Đăng xuất" @click="logout"></v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script setup lang="ts">
const logout = () => {
  // Xử lý đăng xuất ở đây
  alert('Đã đăng xuất!')
}
</script>