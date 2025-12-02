<template>
  <div class="p-4 bg-white border-2 border-green-500 rounded-lg shadow-md max-w-sm">
    <div class="flex items-start">
      
      <div>
        <p class="font-bold text-gray-900">Nguyen Van A</p>
        <p class="text-sm text-gray-500">Self (Admin)</p>
      </div>
    </div>
    
    <div class="mt-4 text-sm grid grid-cols-2 gap-2">
      <div class="text-gray-500">Last Checkup</div>
      <div class="text-right text-gray-700 font-medium">Jan 15, 2023</div>

      <div class="text-gray-500">Conditions</div>
      <div class="text-right text-orange-600 font-bold">Hypertension</div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'

// Logic để quản lý danh sách thành viên
const member = reactive({
  name: '',
  dob: '',
  relation: ''
})

// Khởi tạo danh sách thành viên với các thành viên mẫu trong hình ảnh
const members = ref([
    { name: 'Nguyen Van A', relation: 'Self (Admin)', dob: 'N/A' },
    { name: 'Tran Thi B', relation: 'Spouse', dob: 'N/A' },
    { name: 'Nguyen Van C', relation: 'Child', dob: '2015-01-01' }, // Ngày sinh mẫu
])

const addMember = () => {
  members.value.push({ ...member })
  // Đặt lại form sau khi thêm
  member.name = ''
  member.dob = ''
  member.relation = ''
}

const removeMember = (index) => {
  members.value.splice(index, 1)
}
</script>

<style scoped>
/* Optional: Bạn có thể thêm các kiểu tùy chỉnh nếu không sử dụng đầy đủ Tailwind CSS */
</style>