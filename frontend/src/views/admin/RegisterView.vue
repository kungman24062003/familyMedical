<template>
  <v-container fluid fill-height class="bg-gradient">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="5" lg="4">
        <v-card class="pa-8" rounded="xl" elevation="24">
          <div class="text-center mb-8">
            <v-icon size="80" color="primary">mdi-account-plus</v-icon>
            <h2 class="text-h5 mt-4">Tạo tài khoản mới</h2>
          </div>

          <v-form>
            <v-text-field label="Họ và tên" prepend-inner-icon="mdi-account" outlined class="mb-4" />
            <v-text-field label="Email" prepend-inner-icon="mdi-email" type="email" outlined class="mb-4" />
            <v-text-field label="Số điện thoại" prepend-inner-icon="mdi-phone" outlined class="mb-4" />
            <v-text-field label="Mật khẩu" type="password" prepend-inner-icon="mdi-lock" outlined class="mb-4" />
            <v-text-field label="Nhập lại mật khẩu" type="password" prepend-inner-icon="mdi-lock-check" outlined class="mb-4" />

            <v-btn color="primary" block x-large class="mb-4">Đăng Ký Ngay</v-btn>

            <div class="text-center">
              <router-link to="/login" class="primary--text text-decoration-none">
                Đã có tài khoản? Đăng nhập
              </router-link>
            </div>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
// Register logic here nếu cần
</script>

<style scoped>
.bg-gradient {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
}
</style>