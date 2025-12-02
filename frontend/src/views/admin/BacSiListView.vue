<template>
  <v-container fluid class="pa-6">
    <div class="d-flex align-center mb-6">
      <v-icon size="36" color="success" class="mr-3">mdi-doctor</v-icon>
      <h1 class="text-h4 font-weight-bold">Danh sách bác sĩ</h1>
    </div>

    <v-text-field
      v-model="searchQuery"
      prepend-inner-icon="mdi-magnify"
      label="Tìm kiếm bác sĩ (tên, mã BS, chuyên khoa, khoa phòng...)"
      variant="outlined"
      clearable
      hide-details
      class="mb-6 max-w-600"
    />

    <v-card elevation="12" rounded="lg">
      <v-data-table
        :headers="headers"
        :items="filteredDoctors"
        :items-per-page="10"
        hover
        no-data-text="Không tìm thấy bác sĩ nào"
      >
        <template v-slot:item.hoTen="{ item }">
          <strong>{{ item.hoTen }}</strong>
        </template>

        <template v-slot:item.trangThai="{ item }">
          <v-chip
            :color="item.trangThai === 'Đang làm việc' ? 'success' : 'grey'"
            dark
            small
            class="font-weight-bold"
          >
            {{ item.trangThai }}
          </v-chip>
        </template>

        <template v-slot:item.actions="{ item }">
          <router-link :to="{ name: 'BacSiDetail', params: { id: item.id } }">
            <v-btn color="primary" variant="tonal" size="small">
              <v-icon left small>mdi-eye</v-icon>
              Chi tiết
            </v-btn>
          </router-link>
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

const searchQuery = ref('')

const doctors = ref([
  { id: 1, maBS: 'BS001', hoTen: 'PGS.TS Nguyễn Văn Hùng', chuyenKhoa: 'Tim mạch can thiệp', trinhDo: 'PGS.TS - Bác sĩ CKII', dienThoai: '0918123456', khoaPhong: 'Khoa Tim mạch', trangThai: 'Đang làm việc' },
  { id: 2, maBS: 'BS002', hoTen: 'ThS.BS Trần Thị Mai', chuyenKhoa: 'Sản phụ khoa', trinhDo: 'Thạc sĩ - Bác sĩ CKI', dienThoai: '0909876543', khoaPhong: 'Khoa Sản', trangThai: 'Đang làm việc' },
  { id: 3, maBS: 'BS003', hoTen: 'BS Lê Minh Tuấn', chuyenKhoa: 'Ngoại tổng hợp', trinhDo: 'Bác sĩ CKI', dienThoai: '0935123456', khoaPhong: 'Khoa Ngoại tổng hợp', trangThai: 'Nghỉ phép' },
  { id: 4, maBS: 'BS004', hoTen: 'TS.BS Phạm Hồng Phước', chuyenKhoa: 'Thần kinh', trinhDo: 'Tiến sĩ - Bác sĩ CKII', dienThoai: '0988112233', khoaPhong: 'Khoa Thần kinh', trangThai: 'Đang làm việc' },
  { id: 5, maBS: 'BS005', hoTen: 'BS CKI Nguyễn Thị Lan Anh', chuyenKhoa: 'Nhi khoa', trinhDo: 'Bác sĩ CKI', dienThoai: '0917234567', khoaPhong: 'Khoa Nhi', trangThai: 'Đang làm việc' }
])

const headers = [
  { title: 'Mã BS', key: 'maBS', align: 'start' },
  { title: 'Họ và tên', key: 'hoTen' },
  { title: 'Chuyên khoa', key: 'chuyenKhoa' },
  { title: 'Trình độ', key: 'trinhDo' },
  { title: 'SĐT', key: 'dienThoai' },
  { title: 'Khoa phòng', key: 'khoaPhong' },
  { title: 'Trạng thái', key: 'trangThai' },
  { title: 'Hành động', key: 'actions', sortable: false, align: 'center' }
]

const filteredDoctors = computed(() => {
  if (!searchQuery.value?.trim()) return doctors.value
  const q = searchQuery.value.toLowerCase()
  return doctors.value.filter(d =>
    d.hoTen.toLowerCase().includes(q) ||
    d.maBS.toLowerCase().includes(q) ||
    d.chuyenKhoa.toLowerCase().includes(q) ||
    d.khoaPhong.toLowerCase().includes(q)
  )
})
</script>

<style scoped>
.max-w-600 { max-width: 600px; }
</style>