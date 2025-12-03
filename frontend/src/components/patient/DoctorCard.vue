<template>
  <div class="bg-white shadow-xl rounded-2xl p-6 border border-gray-100">

    <div class="flex items-start mb-6">
      
      <div class="relative w-16 h-16 mr-4 flex-shrink-0">
        <div class="w-16 h-16 rounded-full bg-gray-200 overflow-hidden flex items-center justify-center">
            <div :style="{ backgroundImage: `url(${props.avatarUrl})` }" class="w-full h-full bg-cover bg-center">
                <div v-if="!props.avatarUrl" class="flex items-center justify-center h-full text-3xl text-gray-500">🧑‍⚕️</div>
            </div>
        </div>
      </div>
      
      <div class="flex-grow">
        <div class="flex justify-between items-start mb-1">
            <h2 class="text-xl font-bold text-gray-900 leading-tight">
              {{ props.name }}  
            </h2>
        </div>
        
        <p class="text-base font-medium text-teal-500">
            {{ props.specialty }}
        </p>
      </div>
    </div>

    <div class="space-y-3 mb-6">
        <div class="flex items-center text-gray-600 text-base">
            <svg class="w-5 h-5 mr-3 text-teal-400 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.828 0l-4.243-4.243a8 8 0 1111.314 0z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path></svg>
            <span>{{ props.location }}</span>
        </div>
        
        <div class="flex items-center text-gray-600 text-base">
            <svg class="w-5 h-5 mr-3 text-teal-400 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 7v10c0 1.1.9 2 2 2h12a2 2 0 002-2V9a2 2 0 00-2-2h-3m-1 7l-3-3m0 0l-3 3m3-3v8"></path></svg>
            <span>{{ props.hospital }}</span>
        </div>
    </div>

    <hr class="my-6 border-gray-100">

    <div class="flex flex-wrap gap-2 mb-6">
        <span v-for="tag in props.tags" :key="tag" class="px-3 py-1 bg-gray-100 text-gray-700 text-sm font-medium rounded-full">
            {{ tag }}
        </span>
    </div>

    <button class="w-full flex items-center justify-center py-3 bg-teal-500 text-white font-bold rounded-xl shadow-lg shadow-teal-300/50 hover:bg-teal-600 transition-colors">
        <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8"></path></svg>
        Send Records
    </button>
    
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  name: {
    type: String,
    required: true,
    default: 'Bác sĩ A'
  },
  specialty: {
    type: String,
    required: true,
    default: 'Chuyên khoa'
  },
  location: {
    type: String,
    required: true,
    default: 'Địa điểm'
  },
  hospital: {
    type: String,
    required: true,
    default: 'Bệnh viện'
  },
  tags: {
    type: Array,
    default: () => [] // Mảng các tag/chuyên môn phụ
  },
  avatarUrl: {
    type: String,
    default: '' // URL ảnh đại diện
  }
});
</script>

<style scoped>
/* Không cần thêm CSS nếu bạn chỉ dùng Tailwind */
</style>