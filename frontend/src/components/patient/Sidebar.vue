<template>
  <div class="w-64 bg-white border-r flex flex-col justify-between">
    <div>
      <div class="p-6 border-b-1 border-b-neutral-500 "><h1 class="justify-between items-center align-middle text-center text-xl font-bold ">MedFamily</h1></div>
      <ul class="space-y-3">
        <ul class="space-y-3">
  <router-link
    to="/"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    🏠 Dashboard
  </router-link>

  <router-link
    to="/member"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    👨‍👩‍👧 Family Members
  </router-link>

  <router-link
    to="/doctor"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    🔍 Find Doctor
  </router-link>

  <router-link
    to="/feedback"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    💬 Feedback
  </router-link>
</ul>

      </ul>
    </div>
    <div class="p-4 border-t bg-secondary/30">
      <div class="m-auto">
        <p class="text-sm font-medium">{{ patientName }}</p>
        <p class="text-xs text-gray-500">{{ patientEmail }}</p>
        <button 
          @click="signOut"
          class="mt-2 inline-flex items-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background h-10 px-4 py-2 w-full justify-start gap-2 text-muted-foreground hover:text-destructive hover:bg-destructive/10 hover:border-destructive/50"
        >
          Sign Out
        </button>
      </div>
    </div>
  </div>
</template>


<script setup lang="ts">
import { ref, onMounted } from 'vue';
import api from '@/api';

const patientName = ref(''); 
const patientEmail = ref(''); 

const patientId = localStorage.getItem('patientId');

onMounted(async () => {
  if (patientId) {
    try {
      const res = await api.get(`/users/${patientId}`);
      patientName.value = res.data.name;
      patientEmail.value = res.data.email;
    } catch (err) {
      console.error('Lỗi lấy thông tin người dùng:', err);
    }
  }
});

const signOut = () => {
  localStorage.removeItem('patientToken');
  localStorage.removeItem('patientId');
  localStorage.removeItem('patientName');
  window.location.href = '/login'; // hoặc router.push('/login')
};
</script>
