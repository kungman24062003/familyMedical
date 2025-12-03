<template>
  <div 
    class="bg-white rounded-xl shadow-2xl w-full max-w-sm ml-auto h-full overflow-y-auto"
    :class="{'fixed inset-y-0 right-0 z-50': isSidebar}"
  >
    
    <div class="p-6 border-b border-gray-100 flex justify-between items-start">
      <div>
        <h3 class="text-lg font-bold text-gray-800 mb-1">Feedback Details</h3>
        <p class="text-xs text-gray-500">
          Received on {{ props.receivedDate }} regarding {{ props.patientName }}
        </p>
      </div>
      <button @click="$emit('close')" class="text-gray-400 hover:text-gray-600 transition-colors">
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>
      </button>
    </div>

    <div class="p-6 border-b border-gray-100">
      <div class="flex items-center">
        <div>
          <p class="text-base font-bold text-gray-900 leading-snug">
            {{ props.doctorTitle }} {{ props.doctorName }}
          </p>
          <p class="text-sm text-gray-500">Specialist</p>
        </div>
      </div>
    </div>

    <div class="p-6 space-y-6">
      
      <div>
        <h4 class="text-xs font-semibold text-gray-500 uppercase tracking-wider mb-2">SUBJECT</h4>
        <p class="text-base font-medium text-gray-800">{{ props.subject }}</p>
      </div>

      <div>
        <h4 class="text-xs font-semibold text-gray-500 uppercase tracking-wider mb-2">DOCTOR'S MESSAGE</h4>
        <p class="text-base text-gray-700 leading-relaxed whitespace-pre-wrap">{{ props.message }}</p>
      </div>
    </div>

    <div class="p-6 border-t border-gray-100">
      <div class="p-4 bg-blue-50 rounded-lg">
        <h5 class="text-sm font-semibold text-blue-800 mb-1">Follow-up Action</h5>
        <p class="text-sm text-blue-700">
          You can reply to this message or book a **follow-up appointment** if needed.
        </p>
      </div>
    </div>

    <div class="flex justify-end p-4 text-xs text-gray-400">
      <div class="flex items-center">
        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 1.38-.88 2.5-2 2.5s-2-1.12-2-2.5 1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.5 17.5c-1.12.88-2.5 1.5-4.5 1.5s-3.38-.62-4.5-1.5M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z"></path></svg>
        Được thực hiện bởi Emergent
      </div>
    </div>

  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  // Dữ liệu hiển thị trong header
  receivedDate: {
    type: String,
    default: '2023-10-20'
  },
  patientName: {
    type: String,
    default: 'Nguyen Van A'
  },
  // Thông tin Bác sĩ
  doctorTitle: {
    type: String,
    default: 'Dr.'
  },
  doctorName: {
    type: String,
    default: 'Le Van Minh'
  },
  // Nội dung chi tiết
  subject: {
    type: String,
    default: 'Review of Blood Pressure Logs'
  },
  message: {
    type: String,
    default: "Your blood pressure readings look stable. Keep taking the medication as prescribed and avoid salty foods. Come back for a checkup in 3 months."
  },
  // Cài đặt hiển thị (nếu component được dùng làm sidebar cố định)
  isSidebar: {
    type: Boolean,
    default: false
  }
});

// Component này cần có một emit để đóng nó (nếu dùng làm modal)
defineEmits(['close']);
</script>