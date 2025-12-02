<!-- src/components/layout/AppBreadcrumbs.vue -->
<template>
  <v-breadcrumbs :items="items" class="pl-4">
    <template v-slot:prepend>
      <v-icon icon="mdi-home" class="mr-2" color="primary"></v-icon>
    </template>
  </v-breadcrumbs>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()

const items = computed(() => {
  const path = route.path
  const list: any[] = [{ title: 'Tổng quan', to: '/dashboard' }]

  const titleMap: Record<string, string> = {
    '/dashboard': 'Tổng quan',
    '/lich-kham': 'Quản lý lịch khám',
    '/benh-nhan': 'Quản lý bệnh nhân',
    '/bac-si': 'Quản lý bác sĩ',
    '/kham-benh': 'Khám bệnh',
    '/lich-su-kham': 'Lịch sử khám bệnh',
    '/bao-cao': 'Báo cáo - Thống kê'
  }

  // Thêm trang hiện tại (disabled)
  if (titleMap[path]) {
    list.push({ title: titleMap[path], disabled: true })
  } else if (path.startsWith('/benh-nhan/') && route.params.id) {
    list.push({ title: 'Quản lý bệnh nhân', to: '/benh-nhan' })
    list.push({ title: 'Chi tiết bệnh nhân', disabled: true })
  } else if (path.startsWith('/bac-si/') && route.params.id) {
    list.push({ title: 'Quản lý bác sĩ', to: '/bac-si' })
    list.push({ title: 'Chi tiết bác sĩ', disabled: true })
  } else if (path.startsWith('/lich-su-kham/')) {
    list.push({ title: 'Lịch sử khám bệnh', to: '/lich-su-kham' })
    list.push({ title: 'Chi tiết phiếu khám', disabled: true })
  }

  return list
})
</script>