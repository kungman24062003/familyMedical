<template>
  <v-container fluid class="pa-6 bg-grey-lighten-5" style="min-height: 100vh">
    <!-- Header -->
    <div class="d-flex align-center mb-8">
      <v-btn icon @click="router.back()" size="large" class="mr-4">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <div>
        <h1 class="text-h4 font-weight-bold">{{ patient.hoTen }}</h1>
        <p class="text-body-1 text-grey-darken-2 mb-0">
          {{ patient.maBN }} • {{ age }} tuổi • {{ patient.gioiTinh === 'M' ? 'Nam' : 'Nữ' }}
        </p>
      </div>
      <v-spacer />
      <v-chip :color="getStatusColor(patient.trangThai)" size="large" label class="px-8 font-weight-bold">
        {{ patient.trangThai }}
      </v-chip>
    </div>

    <v-row>
      <!-- Cột trái: Thông tin + lịch sử -->
      <v-col cols="12" lg="8">
        <!-- Thông tin hành chính -->
        <v-card elevation="10" class="mb-6" rounded="xl">
          <v-card-title class="bg-primary text-white text-h6">
            <v-icon left>mdi-card-account-details-outline</v-icon>
            Thông tin hành chính
          </v-card-title>
          <v-card-text class="pt-6">
            <v-row>
              <v-col cols="12" sm="6"><strong>Mã BN:</strong> {{ patient.maBN }}</v-col>
              <v-col cols="12" sm="6"><strong>Họ tên:</strong> {{ patient.hoTen }}</v-col>
              <v-col cols="12" sm="6"><strong>Ngày sinh:</strong> {{ formatDate(patient.ngaySinh) }}</v-col>
              <v-col cols="12" sm="6"><strong>Giới tính:</strong> {{ patient.gioiTinh === 'M' ? 'Nam' : 'Nữ' }}</v-col>
              <v-col cols="12" sm="6"><strong>SĐT:</strong> {{ patient.dienThoai }}</v-col>
              <v-col cols="12" sm="6"><strong>CCCD:</strong> {{ patient.cccd || 'Chưa cập nhật' }}</v-col>
              <v-col cols="12"><strong>Địa chỉ:</strong> {{ patient.diaChi }}</v-col>
              <v-col cols="12" sm="6"><strong>Nghề nghiệp:</strong> {{ patient.ngheNghiep || 'Không rõ' }}</v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <!-- Lịch sử nhập viện -->
        <v-card elevation="10" rounded="xl">
          <v-card-title class="bg-indigo text-white text-h6">
            <v-icon left>mdi-history</v-icon>
            Lịch sử nhập viện
          </v-card-title>
          <v-data-table
            :headers="historyHeaders"
            :items="patient.lichSu"
            hide-default-footer
            class="elevation-1"
          >
            <template v-slot:item.ngayNhap="{ item }">
              {{ formatDate(item.ngayNhap) }}
            </template>
            <template v-slot:item.ngayRa="{ item }">
              {{ item.ngayRa ? formatDate(item.ngayRa) : 'Đang điều trị' }}
            </template>
            <template v-slot:item.ketQua="{ item }">
              <v-chip :color="item.ketQua === 'Khỏi' ? 'success' : 'orange-darken-2'" small>
                {{ item.ketQua }}
              </v-chip>
            </template>
          </v-data-table>
        </v-card>
      </v-col>

      <!-- Cột phải: Hồ sơ hiện tại -->
      <v-col cols="12" lg="4">
        <v-card v-if="patient.hoSoHienTai" elevation="10" rounded="xl" class="h-100">
          <v-card-title class="bg-teal text-white text-h6">
            <v-icon left>mdi-file-document-edit-outline</v-icon>
            Hồ sơ bệnh án hiện tại
          </v-card-title>
          <v-card-text class="pt-6">
            <div class="mb-5"><strong>Lý do vào viện:</strong><br><em>{{ patient.hoSoHienTai.lyDo }}</em></div>
            <v-divider class="my-4"/>
            <div class="mb-5"><strong>Bệnh sử:</strong><br>{{ patient.hoSoHienTai.benhSu }}</div>
            <v-divider class="my-4"/>
            <div class="mb-5">
              <strong>Dị ứng thuốc:</strong>
              <v-chip v-if="patient.hoSoHienTai.diUng" color="error" class="ml-3" small>{{ patient.hoSoHienTai.diUng }}</v-chip>
              <span v-else class="success--text font-weight-bold">Không</span>
            </div>
            <v-divider class="my-4"/>
            <div class="mt-4"><strong>Chẩn đoán:</strong> {{ patient.hoSoHienTai.chanDoan }}</div>
            <div class="mt-3"><strong>Điều trị:</strong> {{ patient.hoSoHienTai.dieuTri }}</div>
            <div class="mt-3"><strong>Hướng dẫn ra viện:</strong> {{ patient.hoSoHienTai.huongDan }}</div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const id = Number(route.params.id)

const patientsDetail: Record<number, any> = {
  1: {
    id: 1,
    maBN: 'BN000001',
    hoTen: 'Nguyễn Văn An',
    ngaySinh: '1990-05-15',
    gioiTinh: 'M',
    dienThoai: '0901234567',
    cccd: '079190001234',
    diaChi: '123 Lê Lợi, Quận 1, TP.HCM',
    ngheNghiep: 'Kỹ sư phần mềm',
    trangThai: 'Đang điều trị',
    lichSu: [
      { ngayNhap: '2024-06-10', ngayRa: '2024-06-15', chanDoan: 'Viêm ruột thừa cấp', khoa: 'Ngoại tổng hợp', ketQua: 'Khỏi' },
      { ngayNhap: '2025-11-20', ngayRa: null, chanDoan: 'Viêm phổi cộng đồng', khoa: 'Hô hấp', ketQua: 'Đang điều trị' }
    ],
    hoSoHienTai: {
      lyDo: 'Sốt cao, ho khạc đờm vàng, khó thở 3 ngày',
      benhSu: 'Khởi phát 3 ngày nay, sốt 39°C, ho đờm vàng, đau ngực phải...',
      diUng: 'Penicillin',
      chanDoan: 'Viêm phổi thùy phải do phế cầu',
      dieuTri: 'Ceftriaxone + Azithromycin, oxy mũi, truyền dịch',
      huongDan: 'Uống đủ 7 ngày kháng sinh, tái khám sau 1 tuần'
    }
  }
  // Thêm các bệnh nhân khác ở đây
}

const patient = ref(patientsDetail[id] ?? patientsDetail[1])

const age = computed(() => {
  const birth = new Date(patient.value.ngaySinh)
  const today = new Date()
  let a = today.getFullYear() - birth.getFullYear()
  const m = today.getMonth() - birth.getMonth()
  if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) a--
  return a
})

const formatDate = (d: string) => d ? new Date(d).toLocaleDateString('vi-VN') : ''

const getStatusColor = (status: string) => {
  if (status === 'Đang điều trị') return 'error'
  if (status === 'Đã xuất viện') return 'success'
  if (status === 'Chờ khám') return 'warning'
  return 'grey'
}

const historyHeaders = [
  { title: 'Ngày nhập viện', key: 'ngayNhap' },
  { title: 'Ngày ra viện', key: 'ngayRa' },
  { title: 'Chẩn đoán', key: 'chanDoan' },
  { title: 'Khoa', key: 'khoa' },
  { title: 'Kết quả', key: 'ketQua' }
]
</script>