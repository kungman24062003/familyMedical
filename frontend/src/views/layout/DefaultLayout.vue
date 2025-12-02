<template>
  <v-app>
    <AppHeader @toggle-drawer="drawer = !drawer" />
    <AppSidebar v-model="drawer" />
    <v-main class="bg-grey-lighten-4">
      <v-container fluid class="pa-6">
        <AppBreadcrumbs class="mb-6" />
        <router-view />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import AppHeader from '@/components/admin/layout/AppHeader.vue'
import AppSidebar from '@/components/admin/layout/AppSidebar.vue'
import AppBreadcrumbs from '@/components/admin/layout/AppBreadcrumbs.vue'
const drawer = ref(true)
</script>
