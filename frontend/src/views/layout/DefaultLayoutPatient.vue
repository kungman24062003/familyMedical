<template>
  <div class="flex h-screen font-sans">
    <Sidebar />

    <!-- Đây là nơi các page như Dashboard, FamilyMember... được render -->
    <div class="flex-1 p-6 overflow-auto">
        <div class="flex-1 p-8 bg-gray-50 overflow-auto">
          <router-view />
        </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import Sidebar from '@/components/patient/Sidebar.vue'
</script>
