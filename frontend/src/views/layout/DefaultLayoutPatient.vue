<template>
  <div class="flex h-screen font-sans">
    <Sidebar />

    <!-- Đây là nơi các page như Dashboard, FamilyMember... được render -->
    <div class="flex-1 p-6 overflow-auto">
      <router-view />
    </div>
  </div>
</template>

<script setup>
import Sidebar from '@/components/patient/Sidebar.vue'
</script>
