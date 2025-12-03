<template>
  <v-container fluid fill-height class="bg-gradient">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="10" md="8" lg="6">
        <v-card class="pa-8" rounded="xl" elevation="24">
          <div class="text-center mb-8">
            <v-icon size="80" color="primary">mdi-account-plus</v-icon>
            <h2 class="text-h5 mt-4">Tạo tài khoản mới</h2>
          </div>

          <v-form ref="formRef" v-model="valid">
            <v-row dense>
              <!-- Cột trái -->
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="form.name"
                  label="Họ và tên"
                  prepend-inner-icon="mdi-account"
                  outlined
                  class="mb-4"
                  :rules="[rules.name]"
                />
                <v-text-field
                  v-model="form.dateOfBirth"
                  label="Ngày sinh"
                  type="date"
                  prepend-inner-icon="mdi-calendar"
                  outlined
                  class="mb-4"
                  :rules="[rules.required]"
                />
                <v-text-field
                  v-model="form.bhyt"
                  label="Số BHYT"
                  prepend-inner-icon="mdi-card-account-details"
                  outlined
                  class="mb-4"
                  :rules="[rules.bhyt]"
                />
                <v-text-field
                  v-model="form.password"
                  label="Mật khẩu"
                  type="password"
                  prepend-inner-icon="mdi-lock"
                  outlined
                  class="mb-4"
                  :rules="[rules.password]"
                />
              </v-col>

              <!-- Cột phải -->
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="form.email"
                  label="Email"
                  prepend-inner-icon="mdi-email"
                  type="email"
                  outlined
                  class="mb-4"
                  :rules="[rules.email]"
                />
                <v-text-field
                  v-model="form.phone"
                  label="Số điện thoại"
                  prepend-inner-icon="mdi-phone"
                  outlined
                  class="mb-4"
                  :rules="[rules.phone]"
                />
                <v-text-field
                  v-model="form.cccd"
                  label="CCCD"
                  prepend-inner-icon="mdi-card-account-details-outline"
                  outlined
                  class="mb-4"
                  :rules="[rules.cccd]"
                />
                <v-text-field
                  v-model="form.confirmPassword"
                  label="Nhập lại mật khẩu"
                  type="password"
                  prepend-inner-icon="mdi-lock-check"
                  outlined
                  class="mb-4"
                  :rules="[rules.confirmPassword]"
                />
              </v-col>
            </v-row>

            <v-btn color="primary" block x-large class="mb-4" @click="submitForm">Đăng Ký Ngay</v-btn>

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
import { reactive, ref } from 'vue';
import { useToast } from 'vue-toastification'; // nếu muốn hiện thông báo
import type { VForm } from 'vuetify/components';


const toast = useToast();

const formRef = ref<InstanceType<typeof VForm> | null>(null);

const valid = ref(false);

const form = reactive({
  name: '',
  email: '',
  phone: '',
  bhyt: '',
  cccd: '',
  dateOfBirth: '',
  password: '',
  confirmPassword: ''
});

const rules = {
  required: (v: any) => !!v || 'Trường này bắt buộc',
  name: (v: string) => /^[a-zA-ZÀ-ỹ\s]+$/.test(v) || 'Tên không được chứa số hoặc ký tự đặc biệt',
  email: (v: string) => /^\S+@\S+\.\S+$/.test(v) || 'Email không hợp lệ',
  phone: (v: string) => /^0\d{9}$/.test(v) || 'SĐT phải đủ 10 số và bắt đầu bằng 0',
  bhyt: (v: string) => /^\d{12}$/.test(v) || 'Số BHYT phải đủ 12 số',
  cccd: (v: string) => /^\d{12}$/.test(v) || 'CCCD phải đủ 12 số',
  password: (v: string) => v.length >= 6 || 'Mật khẩu tối thiểu 6 ký tự',
  confirmPassword: (v: string) => v === form.password || 'Mật khẩu không trùng khớp'
};

const submitForm = () => {
  const validForm = formRef.value?.validate?.() ?? false;
  if (validForm) {
    console.log('Form hợp lệ, submit:', form);
    toast.success('Đăng ký thành công!');
    // TODO: gọi API backend
  } else {
    toast.error('Vui lòng kiểm tra lại thông tin');
  }
};

</script>

<style scoped>
.bg-gradient {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
}
</style>
