<template>
  <v-container fluid class="kham-container pa-6">
    <!-- Header -->
    <div class="d-flex align-center mb-6">
      <h1 class="text-h5 font-weight-bold">Phiếu khám bệnh</h1>
    </div>

    <v-row>
      <!-- Cột trái: Bệnh nhân -->
      <v-col cols="12" md="6">
        <v-card elevation="4" class="mb-6">
          <v-card-title class="bg-primary text-white">
            <v-icon left>mdi-account</v-icon> Thông tin bệnh nhân
          </v-card-title>
          <v-card-text class="pt-6">
            <v-autocomplete
              v-model="form.benhNhanId"
              :items="danhSachBenhNhan"
              item-title="display"
              item-value="id"
              label="Tìm & chọn bệnh nhân *"
              prepend-inner-icon="mdi-magnify"
              variant="outlined"
              clearable
              @update:model-value="loadBenhNhan"
            />

            <v-alert v-if="selectedBenhNhan" type="info" dense class="mb-0">
              <div class="d-flex flex-column">
                <span><strong>Mã BN:</strong> {{ selectedBenhNhan.maBN }}</span>
                <span><strong>Họ tên:</strong> {{ selectedBenhNhan.hoTen }}</span>
                <span><strong>Tuổi:</strong> {{ calculateAge(selectedBenhNhan.ngaySinh) }} tuổi • 
                  <strong>Giới tính:</strong> {{ selectedBenhNhan.gioiTinh === 'M' ? 'Nam' : 'Nữ' }}
                </span>
                <span><strong>Địa chỉ:</strong> {{ selectedBenhNhan.diaChi }}</span>
                <span><strong>SĐT:</strong> {{ selectedBenhNhan.dienThoai }}</span>
                <span>
                  <strong>Dị ứng thuốc:</strong>
                  <v-chip v-if="selectedBenhNhan.diUng" color="error" small class="ml-2">
                    {{ selectedBenhNhan.diUng }}
                  </v-chip>
                  <span v-else class="success--text">Không</span>
                </span>
              </div>
            </v-alert>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Cột phải: Bác sĩ -->
      <v-col cols="12" md="6">
        <v-card elevation="4" class="mb-6">
          <v-card-title class="bg-success text-white">
            <v-icon left color="white">mdi-doctor</v-icon> Bác sĩ khám
          </v-card-title>
          <v-card-text class="pt-6">
            <v-select
              v-model="form.bacSiId"
              :items="danhSachBacSi"
              item-title="hoTen"
              item-value="id"
              label="Chọn bác sĩ phụ trách *"
              prepend-inner-icon="mdi-account-tie"
              variant="outlined"
            >
              <template v-slot:item="{ item, props }">
                <v-list-item v-bind="props">
                  <v-list-item-title>{{ item.raw.hoTen }}</v-list-item-title>
                  <v-list-item-subtitle>{{ item.raw.chuyenKhoa }} - {{ item.raw.khoaPhong }}</v-list-item-subtitle>
                </v-list-item>
              </template>
            </v-select>

            <v-alert v-if="selectedBacSi" type="success" dense colored-border border="left" class="mb-0">
              <strong>{{ selectedBacSi.hoTen }}</strong><br>
              {{ selectedBacSi.chuyenKhoa }} • {{ selectedBacSi.khoaPhong }}
            </v-alert>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- Lý do khám -->
    <v-card class="mb-6" elevation="4">
      <v-card-title class="bg-grey-lighten-2">Lý do khám / Triệu chứng</v-card-title>
      <v-card-text>
        <v-textarea
          v-model="form.lyDo"
          label="Mô tả triệu chứng, thời gian khởi phát..."
          variant="outlined"
          rows="3"
          auto-grow
        />
      </v-card-text>
    </v-card>

    <!-- Khám lâm sàng -->
    <v-card class="mb-6" elevation="4">
      <v-card-title class="bg-blue-lighten-4">Khám lâm sàng & Dấu hiệu sinh tồn</v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="6" sm="3">
            <v-text-field v-model="form.mach" label="Mạch (lần/phút)" variant="outlined" type="number" />
          </v-col>
          <v-col cols="6" sm="3">
            <v-text-field v-model="form.huyetAp" label="Huyết áp" placeholder="120/80" variant="outlined" />
          </v-col>
          <v-col cols="6" sm="3">
            <v-text-field v-model="form.nhietDo" label="Nhiệt độ (°C)" variant="outlined" type="number" step="0.1" />
          </v-col>
          <v-col cols="6" sm="3">
            <v-text-field v-model="form.nhipTho" label="Nhịp thở" variant="outlined" type="number" />
          </v-col>
        </v-row>
        <v-textarea
          v-model="form.khamLamSang"
          label="Kết quả khám toàn thân & các cơ quan"
          variant="outlined"
          rows="4"
          auto-grow
        />
      </v-card-text>
    </v-card>

    <!-- Chẩn đoán -->
    <v-card class="mb-6" elevation="4">
      <v-card-title class="bg-purple-lighten-4">Chẩn đoán</v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.chanDoanChinh" label="Chẩn đoán chính (ICD-10)" variant="outlined" />
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field v-model="form.chanDoanKem" label="Chẩn đoán kèm theo" variant="outlined" />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>

    <!-- Chỉ định cận lâm sàng -->
    <v-card class="mb-6" elevation="4">
      <v-card-title class="bg-orange-lighten-4">Chỉ định cận lâm sàng</v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="6" v-for="item in canLamSangOptions" :key="item">
            <v-checkbox
              v-model="form.canLamSang"
              :label="item"
              :value="item"
              color="primary"
              hide-details
            />
          </v-col>
        </v-row>
        <v-text-field
          v-model="form.canLamSangKhac"
          label="Chỉ định khác"
          variant="outlined"
          class="mt-4"
        />
      </v-card-text>
    </v-card>

    <!-- Kê đơn thuốc -->
    <v-card class="mb-6" elevation="4">
      <v-card-title class="bg-teal-lighten-4">
        Kê đơn thuốc
        <v-spacer />
        <v-btn color="primary" prepend-icon="mdi-plus" @click="themThuoc">Thêm thuốc</v-btn>
      </v-card-title>
      <v-card-text>
        <v-table v-if="form.donThuoc.length" density="comfortable">
          <thead>
            <tr>
              <th>STT</th>
              <th>Tên thuốc / Hoạt chất</th>
              <th>Số lượng</th>
              <th>Cách dùng</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(thuoc, i) in form.donThuoc" :key="i">
              <td>{{ i + 1 }}</td>
              <td>
                <v-text-field v-model="thuoc.ten" density="compact" hide-details />
              </td>
              <td width="120">
                <v-text-field v-model="thuoc.soLuong" type="number" density="compact" hide-details />
              </td>
              <td>
                <v-text-field v-model="thuoc.cachDung" density="compact" hide-details />
              </td>
              <td>
                <v-btn icon color="error" size="small" @click="xoaThuoc(i)">
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </td>
            </tr>
          </tbody>
        </v-table>
        <div v-else class="text-center pa-8 text-grey">Chưa kê thuốc nào</div>
      </v-card-text>
    </v-card>

    <!-- Nút hành động -->
    <div class="text-center">
      <v-btn
        size="x-large"
        color="success"
        class="mx-4 px-12"
        @click="luuBenhAn"
        :disabled="!form.benhNhanId || !form.bacSiId"
      >
        <v-icon left>mdi-content-save</v-icon>
        Lưu phiếu khám
      </v-btn>
      <v-btn size="x-large" color="primary" class="mx-4 px-10" @click="inPhieu">
        <v-icon left>mdi-printer</v-icon>
        In phiếu
      </v-btn>
    </div>
  </v-container>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// Dữ liệu giả lập
const danhSachBenhNhan = ref([
  { id: 1, maBN: 'BN000001', hoTen: 'Nguyễn Văn An', ngaySinh: '1990-05-15', gioiTinh: 'M', diaChi: 'Quận 1, TP.HCM', dienThoai: '0901234567', diUng: 'Penicillin', display: 'BN000001 - Nguyễn Văn An (Nam, 35 tuổi)' },
  { id: 2, maBN: 'BN000002', hoTen: 'Trần Thị Bình', ngaySinh: '1985-12-30', gioiTinh: 'F', diaChi: 'Quận 3, TP.HCM', dienThoai: '0912345678', diUng: null, display: 'BN000002 - Trần Thị Bình (Nữ, 40 tuổi)' },
  { id: 4, maBN: 'BN000004', hoTen: 'Phạm Hồng Đào', ngaySinh: '1995-08-10', gioiTinh: 'F', diaChi: 'Quận 7, TP.HCM', dienThoai: '0987654321', diUng: 'Cefixim', display: 'BN000004 - Phạm Hồng Đào (Nữ, 30 tuổi)' }
])

const danhSachBacSi = ref([
  { id: 1, maBS: 'BS001', hoTen: 'PGS.TS Nguyễn Văn Hùng', chuyenKhoa: 'Tim mạch can thiệp', khoaPhong: 'Khoa Tim mạch' },
  { id: 2, maBS: 'BS002', hoTen: 'ThS.BS Trần Thị Mai', chuyenKhoa: 'Sản phụ khoa', khoaPhong: 'Khoa Sản' },
  { id: 3, maBS: 'BS003', hoTen: 'BS Lê Minh Tuấn', chuyenKhoa: 'Ngoại tổng hợp', khoaPhong: 'Khoa Ngoại' }
])

const canLamSangOptions = [
  'X-quang tim phổi', 'Siêu âm bụng tổng quát', 'Công thức máu', 'Sinh hóa máu', 
  'Điện tim đồ', 'Nội soi dạ dày', 'CT Scanner', 'MRI'
]

// Form
const form = ref({
  benhNhanId: '',
  bacSiId: '',
  lyDo: '',
  mach: '', huyetAp: '', nhietDo: '', nhipTho: '',
  khamLamSang: '',
  chanDoanChinh: '', chanDoanKem: '',
  canLamSang: [] as string[],
  canLamSangKhac: '',
  donThuoc: [] as any[]
})

// Computed
const selectedBenhNhan = computed(() => danhSachBenhNhan.value.find(b => b.id === Number(form.value.benhNhanId)))
const selectedBacSi = computed(() => danhSachBacSi.value.find(b => b.id === Number(form.value.bacSiId)))

const calculateAge = (ngaySinh: string) => {
  const birth = new Date(ngaySinh)
  const today = new Date()
  let age = today.getFullYear() - birth.getFullYear()
  const m = today.getMonth() - birth.getMonth()
  if (m < 0 || (m === 0 && today.getDate() < birth.getDate())) age--
  return age
}

const loadBenhNhan = () => { /* có thể gọi API chi tiết */ }

const themThuoc = () => form.value.donThuoc.push({ ten: '', soLuong: 10, cachDung: '' })
const xoaThuoc = (i: number) => form.value.donThuoc.splice(i, 1)

const luuBenhAn = () => {
  console.log('ĐÃ LƯU PHIẾU KHÁM:', form.value)
  alert('Lưu thành công! (Xem console để kiểm tra dữ liệu)')
}

const inPhieu = () => alert('Chức năng in phiếu đang phát triển...')
</script>

<style scoped>
.kham-container {
  background: linear-gradient(to bottom, #f5f7fa, #e4edf5);
  min-height: 100vh;
}
</style>