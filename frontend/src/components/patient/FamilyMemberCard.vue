<template>
  <div class="bg-white shadow-xl rounded-2xl p-6 border border-gray-100">

    <div class="flex justify-end mb-4">
      <button class="text-sm font-semibold text-gray-700 px-3 py-1 bg-gray-100 rounded-full hover:bg-gray-200 transition-colors">
        Spouse
      </button>
    </div>

    <div class="flex items-start">
      <div>
        <h2 class="text-2xl font-semibold text-gray-900 mb-1">
          {{ user.name }}
        </h2>
        
        <p class="text-base text-gray-600 mb-4">
          <span class="font-medium">{{ user.age }} years old</span> 
          <span class="mx-1">•</span> 
          <span class="font-medium">{{ user.gender }}</span> 
          <span class="mx-1">•</span> 
          <span class="font-medium">Blood: {{ user.bloodType }}</span>
        </p>

        <div class="mt-4">
          <h3 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-1">
            ACTIVE CONDITIONS
          </h3>
          <p class="text-base italic text-gray-700">
            {{ user.activeConditions }}
          </p>
        </div>
      </div>
    </div>

    <hr class="my-6 border-gray-100"> <div class="flex space-x-4 mt-6">
      
      <button class="flex-1 flex items-center justify-center py-3 px-4 text-gray-700 font-semibold border-2 border-gray-200 rounded-xl hover:bg-gray-50 transition-colors">
        <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-7 1l4-4m-4-4l4 4m0 0l-4-4"></path></svg>
        Sửa
      </button>

      <button class="flex-1 flex items-center justify-center py-3 px-4 text-white font-semibold bg-teal-500 rounded-xl shadow-lg shadow-teal-300/50 hover:bg-teal-600 transition-colors">
        <svg class="w-5 h-5 mr-2" fill="white" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/></svg>
        Hồ sơ
      </button>
    </div>
    
  </div>
</template>

<script setup>
import { reactive } from 'vue';

// Dữ liệu mẫu (sử dụng reactive để mô phỏng data trong thực tế)
const user = reactive({
  name: 'Tran Thi B',
  age: 32,
  gender: 'Female',
  bloodType: 'A+',
  activeConditions: 'No chronic conditions',
  // Thêm path avatar thực tế nếu cần
  // avatarUrl: 'path/to/image.png' 
});
</script>

<style scoped>
/* Không cần thêm CSS nếu bạn chỉ dùng Tailwind */
</style>