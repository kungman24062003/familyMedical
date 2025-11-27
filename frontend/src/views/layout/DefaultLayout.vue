<template>
  <v-app>
    <AppHeader @toggle-drawer="drawer = !drawer" />
    <AppSidebar v-model="drawer" />
    <v-main class="bg-grey-lighten-4">
      <v-container fluid class="pa-6">
        <AppBreadcrumbs class="mb-6" />
        <slot />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import AppHeader from '@/components/layout/AppHeader.vue'
import AppSidebar from '@/components/layout/AppSidebar.vue'
import AppBreadcrumbs from '@/components/layout/AppBreadcrumbs.vue'
const drawer = ref(true)
</script>