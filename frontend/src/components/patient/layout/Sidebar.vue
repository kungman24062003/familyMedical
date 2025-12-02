<template>
  <div class="w-64 bg-white border-r flex flex-col justify-between shadow-lg">
    <div class="p-6">
      <div class="text-xl font-bold text-teal-600 mb-10">MedFamily</div>

      <nav class="space-y-2">
        <a v-for="item in menuItems" :key="item.name" :class="[
            'flex items-center p-3 rounded-lg text-sm font-medium',
            item.current ? 'bg-teal-500 text-white shadow-md' : 'text-gray-600 hover:bg-gray-100'
          ]" href="#">
          <span class="mr-3">{{ item.icon }}</span>
          {{ item.name }}
        </a>
      </nav>
    </div>

    <div class="p-4 border-t">
      <div class="flex items-center mb-2">
        <img class="w-8 h-8 rounded-full mr-3" src="" alt="User Avatar">
        <div>
          <p class="text-sm font-semibold">Nguyen Van A</p>
          <p class="text-xs text-gray-500">nvana@example.com</p>
        </div>
      </div>
      <a href="#" class="text-sm text-gray-500 hover:text-teal-600">→ Sign Out</a>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const menuItems = ref([
  { name: 'Dashboard', icon: '🏠', current: true },
  { name: 'My Records', icon: '📄', current: false },
  { name: 'Family Members', icon: '👨‍👩‍👧‍👦', current: false },
  { name: 'Find Doctor', icon: '🔎', current: false },
  { name: 'Feedback', icon: '💬', current: false },
]);
</script>
