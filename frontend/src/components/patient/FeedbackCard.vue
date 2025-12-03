<template>
  <div @click="openDetails" class="bg-white border mt-5 border-teal-200/50 rounded-xl p-4 sm:p-5 w-full shadow-md cursor-pointer hover:shadow-lg transition-shadow duration-300">
    <div class="flex items-center justify-between mb-3">
      
      <div class="flex items-center">
        <div>
          <p class="text-base font-semibold text-gray-900 leading-snug">
            {{ props.doctorTitle }} {{ props.doctorName }}
          </p>

          <div class="flex items-center space-x-2 mt-0.5">
            <span class="text-sm text-gray-500">
              {{ props.patientName }}
            </span>
            <span v-if="props.isNew" class="text-xs font-bold text-teal-600 bg-teal-100 px-2 py-0.5 rounded-full">
              Mới
            </span>
          </div>
        </div>
      </div>

      <div class="flex items-center space-x-3 text-gray-400">
        <p class="text-sm flex items-center">
          <svg class="w-4 h-4 mr-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path></svg>
          {{ props.date }}
        </p>
        
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path></svg>
      </div>
    </div>
    
    <h4 class="text-lg font-semibold text-gray-900 mb-1">
      {{ props.title }}
    </h4>
    <p class="text-gray-700 leading-relaxed truncate">
      {{ props.summary }}
    </p>

  </div>
  <div v-if="isDetailsOpen" class="fixed inset-0 bg-black/50 bg-opacity-30 z-40">
    <Transition name="slide-fade">
        <FeedbackDetail 
            class="h-full"
            :isSidebar="true"
            :receivedDate="'2023-10-20'"
            :patientName="'Nguyen Van A'"
            :doctorName="'Le Van Minh'"
            :subject="'Review of Blood Pressure Logs'"
            :message="'Your blood pressure readings look stable. Keep taking the medication as prescribed and avoid salty foods. Come back for a checkup in 3 months.'"
            @close="isDetailsOpen = false"
        />
    </Transition>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { ref } from 'vue';
import FeedbackDetail from '../../views/patient/FeedbackDetail.vue'; // Đảm bảo đúng đường dẫn
const props = defineProps({
  doctorTitle: { // Ví dụ: 'Tiến sĩ', 'Bác sĩ'
    type: String,
    default: 'Bác sĩ'
  },
  doctorName: {
    type: String,
    required: true,
    default: 'Lê Văn Minh'
  },
  patientName: { // Tên người được đánh giá/gắn liền với nhật ký
    type: String,
    default: 'Nguyễn Văn A'
  },
  isNew: { // Tag 'Mới'
    type: Boolean,
    default: true
  },
  date: {
    type: String,
    default: '2023-10-20'
  },
  title: { // Tiêu đề đánh giá
    type: String,
    required: true,
    default: 'Đánh giá nhật ký huyết áp'
  },
  summary: { // Tóm tắt nội dung
    type: String,
    required: true,
    default: 'Chỉ số huyết áp của bạn có vẻ ổn định. Hãy tiếp tục dùng thuốc theo chỉ định và tránh ăn đồ mặn. Hãy quay lại khám...'
  }
});
const isDetailsOpen = ref(false);

const openDetails = () => {
    isDetailsOpen.value = true;
};
</script>

<style>
/* Thêm transition cho Modal/Sidebar */
.slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(100%); /* Bắt đầu/kết thúc từ ngoài cùng bên phải */
  opacity: 0;
}
</style>