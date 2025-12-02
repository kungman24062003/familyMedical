<template>
  <v-container fluid class="pa-6">
    <div class="d-flex align-center mb-6">
      <v-icon size="36" color="primary" class="mr-3">mdi-account-group</v-icon>
      <h1 class="text-h4 font-weight-bold">Danh sách bệnh nhân</h1>
    </div>

    <v-text-field
      v-model="searchQuery"
      prepend-inner-icon="mdi-magnify"
      label="Tìm kiếm theo tên, mã BN, số điện thoại..."
      variant="outlined"
      clearable
      hide-details
      class="mb-6 max-w-600"
    />

    <v-card elevation="12" rounded="lg">
      <v-data-table
        :headers="headers"
        :items="filteredPatients"
        :items-per-page="10"
        class="elevation-1"
        hover
        no-data-text="Không tìm thấy bệnh nhân nào"
      >
        <template v-slot:item.hoTen="{ item }">
          <strong>{{ item.hoTen }}</strong>
        </template>

        <template v-slot:item.trangThai="{ item }">
          <v-chip
            :color="getStatusColor(item.trangThai)"
            dark
            small
            class="font-weight-bold"
          >
            {{ item.trangThai }}
          </v-chip>
        </template>

        <template v-slot:item.actions="{ item }">
          <router-link :to="{ name: 'BenhNhanDetail', params: { id: item.id } }">
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

const patients = ref([
  { id: 1, maBN: 'BN000001', hoTen: 'Nguyễn Văn An', ngaySinh: '1990-05-15', gioiTinh: 'M', dienThoai: '0901234567', diaChi: 'Quận 1, TP.HCM', ngayNhapVien: '2025-11-20', trangThai: 'Đang điều trị' },
  { id: 2, maBN: 'BN000002', hoTen: 'Trần Thị Bình', ngaySinh: '1985-12-30', gioiTinh: 'F', dienThoai: '0912345678', diaChi: 'Quận 3, TP.HCM', ngayNhapVien: '2025-11-25', trangThai: 'Đã xuất viện' },
  { id: 3, maBN: 'BN000003', hoTen: 'Lê Văn Cường', ngaySinh: '1978-03-22', gioiTinh: 'M', dienThoai: '0934567890', diaChi: 'Bình Thạnh, TP.HCM', ngayNhapVien: '2025-11-28', trangThai: 'Chờ khám' },
  { id: 4, maBN: 'BN000004', hoTen: 'Phạm Hồng Đào', ngaySinh: '1995-08-10', gioiTinh: 'F', dienThoai: '0987654321', diaChi: 'Quận 7, TP.HCM', ngayNhapVien: '2025-11-15', trangThai: 'Đang điều trị' },
  { id: 5, maBN: 'BN000005', hoTen: 'Hoàng Minh Đức', ngaySinh: '1988-01-20', gioiTinh: 'M', dienThoai: '0923456789', diaChi: 'Gò Vấp, TP.HCM', ngayNhapVien: '2025-11-27', trangThai: 'Đang điều trị' }
])

const headers = [
  { title: 'Mã BN', key: 'maBN', align: 'start' },
  { title: 'Họ và tên', key: 'hoTen' },
  { title: 'Ngày sinh', key: 'ngaySinh', value: (i: any) => new Date(i.ngaySinh).toLocaleDateString('vi-VN') },
  { title: 'Giới tính', key: 'gioiTinh', value: (i: any) => i.gioiTinh === 'M' ? 'Nam' : 'Nữ' },
  { title: 'SĐT', key: 'dienThoai' },
  { title: 'Địa chỉ', key: 'diaChi' },
  { title: 'Ngày nhập viện', key: 'ngayNhapVien', value: (i: any) => new Date(i.ngayNhapVien).toLocaleDateString('vi-VN') },
  { title: 'Trạng thái', key: 'trangThai' },
  { title: 'Hành động', key: 'actions', sortable: false, align: 'center' }
]

const filteredPatients = computed(() => {
  if (!searchQuery.value.trim()) return patients.value
  const q = searchQuery.value.toLowerCase()
  return patients.value.filter(p =>
    p.hoTen.toLowerCase().includes(q) ||
    p.maBN.toLowerCase().includes(q) ||
    p.dienThoai.includes(q)
  )
})

const getStatusColor = (status: string) => {
  if (status === 'Đang điều trị') return 'error'
  if (status === 'Đã xuất viện') return 'success'
  if (status === 'Chờ khám') return 'warning'
  return 'grey'
}
</script>

<style scoped>
.max-w-600 { max-width: 600px; }
</style>