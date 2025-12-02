<!-- src/components/layout/AppBreadcrumbs.vue -->
<template>
  <v-breadcrumbs :items="items">
    <template v-slot:prepend>
      <v-icon icon="mdi-home" class="mr-2"></v-icon>
    </template>
  </v-breadcrumbs>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()

const items = computed(() => {
  const list = [{ title: 'Tổng quan', to: '/dashboard' }]

  if (route.path.includes('/ho-gia-dinh')) {
    list.push({ title: 'Quản lý hộ gia đình', to: '/ho-gia-dinh/nhan-khau' })
  }

  const titleMap: any = {
    '/dashboard': 'Tổng quan',
    '/ho-gia-dinh/nhan-khau': 'Quản lý nhân khẩu',
    '/ho-gia-dinh/ho-khau': 'Quản lý hộ khẩu',
    '/ho-gia-dinh/giao-viec': 'Giao việc CSSK hộ GD',
    '/thong-ke': 'Thống kê - Báo cáo',
    '/ct-cssk': 'Quản lý CT/CSSK'
  }

  if (titleMap[route.path]) {
    list.push({ title: titleMap[route.path], disabled: true })
  }

  return list
})
</script>