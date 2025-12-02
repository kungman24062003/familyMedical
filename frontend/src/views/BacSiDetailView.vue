<template>
  <v-container fluid class="pa-6 bg-grey-lighten-5" style="min-height: 100vh">
    <div class="d-flex align-center mb-8">
      <v-btn icon @click="router.back()" size="large" class="mr-4">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <div>
        <h1 class="text-h4 font-weight-bold">{{ doctor.hoTen }}</h1>
        <p class="text-body-1 text-grey-darken-2 mb-0">
          {{ doctor.maBS }} • {{ doctor.chuyenKhoa }} • {{ doctor.khoaPhong }}
        </p>
      </div>
      <v-spacer />
      <v-chip
        :color="doctor.trangThai === 'Đang làm việc' ? 'success' : 'grey'"
        size="large"
        label
        class="px-8 font-weight-bold"
      >
        {{ doctor.trangThai }}
      </v-chip>
    </div>

    <v-row>
      <v-col cols="12" lg="8">
        <!-- Thông tin cá nhân -->
        <v-card elevation="10" rounded="xl" class="mb-6 text-center">
          <v-card-title class="bg-success text-white text-h6">
            <v-icon left>mdi-account-circle</v-icon>
            Thông tin cá nhân
          </v-card-title>

          <!-- Avatar tròn + chữ cái đầu tên -->
          <div class="pa-8">
            <v-avatar size="140" :color="avatarColor" class="mb-4">
              <span class="white--text text-h3 font-weight-bold">{{ avatarText }}</span>
            </v-avatar>
          </div>

          <v-card-text>
            <v-row>
              <v-col cols="12" sm="6"><strong>Mã bác sĩ:</strong> {{ doctor.maBS }}</v-col>
              <v-col cols="12" sm="6"><strong>Họ và tên:</strong> {{ doctor.hoTen }}</v-col>
              <v-col cols="12" sm="6"><strong>Chuyên khoa:</strong> {{ doctor.chuyenKhoa }}</v-col>
              <v-col cols="12" sm="6"><strong>Trình độ:</strong> {{ doctor.trinhDo }}</v-col>
              <v-col cols="12" sm="6"><strong>Khoa phòng:</strong> {{ doctor.khoaPhong }}</v-col>
              <v-col cols="12" sm="6"><strong>Số điện thoại:</strong> {{ doctor.dienThoai }}</v-col>
              <v-col cols="12" sm="6"><strong>Email:</strong> {{ doctor.email || 'Chưa cập nhật' }}</v-col>
              <v-col cols="12" sm="6"><strong>Năm sinh:</strong> {{ doctor.namSinh }}</v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <!-- Kinh nghiệm & chứng chỉ -->
        <v-card elevation="10" rounded="xl" class="mb-6">
          <v-card-title class="bg-teal text-white text-h6">
            <v-icon left>mdi-trophy-award</v-icon>
            Kinh nghiệm & Chứng chỉ nổi bật
          </v-card-title>
          <v-card-text>
            <v-list lines="two">
              <v-list-item v-for="(item, i) in doctor.kinhNghiem" :key="i">
                <v-list-item-title>{{ item }}</v-list-item-title>
              </v-list-item>
            </v-list>
            <div v-if="!doctor.kinhNghiem?.length" class="text-center pa-6 text-grey">
              Chưa cập nhật kinh nghiệm
            </div>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- Lịch trực tuần này -->
      <v-col cols="12" lg="4">
        <v-card elevation="10" rounded="xl" class="h-100">
          <v-card-title class="bg-purple text-white text-h6">
            <v-icon left>mdi-calendar-clock</v-icon>
            Lịch trực tuần này (11/2025)
          </v-card-title>
          <v-table density="comfortable">
            <thead>
              <tr>
                <th class="text-left">Thứ</th>
                <th class="text-left">Sáng</th>
                <th class="text-left">Chiều</th>
                <th class="text-left">Ghi chú</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="lich in doctor.lichTruc" :key="lich.thu">
                <td><strong>{{ lich.thu }}</strong></td>
                <td>{{ lich.sang }}</td>
                <td>{{ lich.chieu }}</td>
                <td><em>{{ lich.ghiChu }}</em></td>
              </tr>
            </tbody>
          </v-table>
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

// Hàm tạo avatar text + màu cố định theo tên
const getAvatarText = (name: string) => {
  const words = name.trim().split(/\s+/)
  const first = words[0][0] || ''
  const last = words[words.length - 1][0] || ''
  return (first + last).toUpperCase()
}

const getAvatarColor = (name: string) => {
  const colors = ['#e74c3c', '#3498db', '#2ecc71', '#9b59b6', '#f1c40f', '#1abc9c', '#34495e', '#e67e22']
  let hash = 0
  for (let i = 0; i < name.length; i++) hash = name.charCodeAt(i) + ((hash << 5) - hash)
  return colors[Math.abs(hash % colors.length)]
}

const doctorsDetail: Record<number, any> = {
  1: {
    id: 1, maBS: 'BS001', hoTen: 'PGS.TS Nguyễn Văn Hùng', chuyenKhoa: 'Tim mạch can thiệp', trinhDo: 'PGS.TS - Bác sĩ CKII',
    dienThoai: '0918123456', khoaPhong: 'Khoa Tim mạch', email: 'hung.nguyen@benhvien.com', namSinh: '1975',
    trangThai: 'Đang làm việc',
    kinhNghiem: [
      'Hơn 20 năm kinh nghiệm tim mạch can thiệp',
      'Tu nghiệp Singapore General Hospital 2015-2017',
      'Thực hiện >3000 ca can thiệp mạch vành',
      'Tác giả 25 bài báo SCI/SCIE',
      'Giải thưởng Bác sĩ Trái tim vàng 2023'
    ],
    lichTruc: [
      { thu: 'Thứ 2', sang: '7h-12h', chieu: '14h-18h', ghiChu: 'Khám VIP' },
      { thu: 'Thứ 4', sang: '7h-12h', chieu: 'Nghỉ', ghiChu: 'Hội chẩn' },
      { thu: 'Thứ 6', sang: '7h-12h', chieu: '14h-20h', ghiChu: 'Trực cấp cứu' }
    ]
  },
  2: {
    id: 2, maBS: 'BS002', hoTen: 'ThS.BS Trần Thị Mai', chuyenKhoa: 'Sản phụ khoa', trinhDo: 'Thạc sĩ - Bác sĩ CKI',
    dienThoai: '0909876543', khoaPhong: 'Khoa Sản', email: 'mai.tran@benhvien.com', namSinh: '1982',
    trangThai: 'Đang làm việc',
    kinhNghiem: [
      '10 năm công tác Bệnh viện Từ Dũ',
      'Chuyên sâu sinh non, tiền sản giật, mổ lấy thai',
      'Hỗ trợ >5000 ca sinh',
      'Giảng viên ĐH Y Dược TP.HCM'
    ],
    lichTruc: [
      { thu: 'Thứ 3', sang: '7h-12h', chieu: '14h-18h', ghiChu: '' },
      { thu: 'Thứ 5', sang: '7h-12h', chieu: '14h-18h', ghiChu: 'Khám thai' },
      { thu: 'Thứ 7', sang: '7h-12h', chieu: 'Nghỉ', ghiChu: '' }
    ]
  }
  // Thêm bác sĩ khác...
}

const doctor = ref(doctorsDetail[id] ?? doctorsDetail[1])

const avatarText = computed(() => getAvatarText(doctor.value.hoTen))
const avatarColor = computed(() => getAvatarColor(doctor.value.hoTen))
</script>