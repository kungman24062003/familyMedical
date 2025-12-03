<template>
  <div class="login-wrapper">
    <!-- Background -->
    <div class="background-image"></div>

    <!-- Login Card -->
    <v-card class="login-card pa-8" min-width="420" max-width="420" elevation="20">
      <div class="text-center mb-8">
        <!-- Logo -->
        <v-avatar size="86" class="mb-6 logo-shadow">
          <v-img src="/logo-health.png" alt="Logo">
            <!-- Nếu không có ảnh logo, dùng icon fallback -->
            <template v-slot:placeholder>
              <v-icon size="56" color="white">mdi-plus</v-icon>
            </template>
          </v-img>
        </v-avatar>

        <!-- Tiêu đề -->
        <h1 class="text-h6 font-weight-bold primary--text text-uppercase letter-spacing">
          Hệ thống hồ sơ
        </h1>
        <h2 class="text-h6 font-weight-bold primary--text text-uppercase letter-spacing">
          Quản lý sức khỏe
        </h2>
      </div>

      <v-form @submit.prevent="login" v-model="valid" ref="formRef">
        <!-- Tên tài khoản -->
        <v-text-field
          v-model="form.username"
          prepend-inner-icon="mdi-account"
          placeholder="Nhập tên tài khoản của bạn"
          outlined
          dense
          hide-details="auto"
          class="mb-4 custom-field"
          bg-color="grey-lighten-4"
          color="primary"
          :rules="usernameRules"
        />

        <!-- Mật khẩu -->
        <v-text-field
          v-model="form.password"
          prepend-inner-icon="mdi-lock"
          placeholder="Nhập mật khẩu của bạn"
          outlined
          dense
          hide-details="auto"
          class="mb-6 custom-field"
          bg-color="grey-lighten-4"
          color="primary"
          :type="showPassword ? 'text' : 'password'"
          :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          @click:append-inner="showPassword = !showPassword"
          :rules="passwordRules"
        />

        <!-- Nút Login -->
        <v-btn
          type="submit"
          block
          large
          height="50"
          color="#0288d1"
          dark
          class="text-uppercase font-weight-bold rounded-lg"
          :loading="loading"
          :disabled="!valid"
        >
          Login
        </v-btn>
            <v-btn
            color="red darken-1"
            class="mb-4"
            @click="loginWithGoogle"
          >
            <v-icon left>mdi-google</v-icon> Login with Google
          </v-btn>
      </v-form>

      <!-- Footer copyright -->
      <div class="text-center mt-10">
        <p class="text-caption grey--text text--darken-1 ">
          Chưa có tài khoản? <router-link to="/register" class="text-blue-700 hover:text-blue-300">Đăng ký</router-link>
        </p>
      </div>
    </v-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import type { VForm } from 'vuetify/components'
import api from '@/api'

const router = useRouter()
const formRef = ref<VForm | null>(null)
const valid = ref(false)
const loading = ref(false)
const showPassword = ref(false)

interface LoginForm {
  username: string
  password: string
}

const form = reactive<LoginForm>({
  username: '',
  password: ''
})

const usernameRules = [(v: string) => !!v || 'Vui lòng nhập tên tài khoản']
const passwordRules = [
  (v: string) => !!v || 'Vui lòng nhập mật khẩu',
  (v: string) => v.length >= 6 || 'Mật khẩu ít nhất 6 ký tự'
]
 const loginWithGoogle = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google"
  }
const login = async () => {
  if (!valid.value) return
  loading.value = true

  // Giả lập đăng nhập
  await new Promise(resolve => setTimeout(resolve, 1500))
  loading.value = false

const login = async () => {
  if (!valid.value) return;
  loading.value = true;

  try {
    const res = await api.post("/users/login/patient", {
      email: form.username,     // backend dùng email
      password: form.password
    });

    // Lưu token từ backend trả về
    localStorage.setItem("patientToken", res.data.token);
    localStorage.setItem("patientName", res.data.name);
    localStorage.setItem("patientId", res.data.id);

    router.push({ name: "dashboardPatient" });
  } catch (err: any) {
    console.error(err);
    alert(err.response?.data || "Đăng nhập thất bại");
  } finally {
    loading.value = false;
  }
};


</script>

<style scoped>
.login-wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.background-image {
  position: absolute;
  top: 0; left: 0; right: 0; bottom: 0;
  background: url('https://images.unsplash.com/photo-1551601651-bc96b4fd7da8?w=1920&q=80') center/cover no-repeat;
  filter: blur(3px) brightness(0.8);
  z-index: 1;
}

.login-card {
  position: relative;
  z-index: 2;
  border-radius: 16px !important;
  background: rgba(255, 255, 255, 0.98) !important;
}

.logo-shadow {
  box-shadow: 0 8px 25px rgba(2, 136, 209, 0.3) !important;
  background: linear-gradient(135deg, #0288d1, #02669d) !important;
}

.letter-spacing {
  letter-spacing: 1.5px;
}

.custom-field :deep(.v-field) {
  border-radius: 8px;
}

.custom-field :deep(.v-field__outline) {
  color: #0288d1;
}
</style>
