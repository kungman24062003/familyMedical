<template>
  <v-app>
    <v-app-bar app color="primary" dark elevation="4">
      <v-toolbar-title class="font-weight-bold">Hồ Sơ Y Tế Gia Đình</v-toolbar-title>
      <v-spacer />
      <v-btn icon @click="logout">
        <v-icon>mdi-logout</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main class="grey lighten-4">
      <v-container class="py-12">
        <h1 class="text-h3 text-center mb-10">Chào mừng bạn trở lại!</h1>
        <v-row>
          <v-col v-for="n in 6" :key="n" cols="12" sm="6" md="4">
            <v-card class="text-center pa-8" height="220" hover>
              <v-avatar size="80" color="primary" class="mb-4">
                <v-icon size="48" color="white">mdi-account-heart</v-icon>
              </v-avatar>
              <h3>Thành viên {{ n }}</h3>
              <p class="mt-2 grey--text">Cập nhật hồ sơ</p>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()

const logout = () => {
  // Xóa token, store...
  router.push('/login')
}
</script>