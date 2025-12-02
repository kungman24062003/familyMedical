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
  const list: any[] = [{ title: 'Tổng quan', to: '/admin/dashboard' }]

  const titleMap: Record<string, string> = {
    '/admin/dashboard': 'Tổng quan',
    '/admin/lich-kham': 'Quản lý lịch khám',
    '/admin/benh-nhan': 'Quản lý bệnh nhân',
    '/admin/bac-si': 'Quản lý bác sĩ',
    '/admin/kham-benh': 'Khám bệnh',
    '/admin/lich-su-kham': 'Lịch sử khám bệnh',
    '/admin/bao-cao': 'Báo cáo - Thống kê'
  }

  // Thêm trang hiện tại (disabled)
  if (titleMap[path]) {
    list.push({ title: titleMap[path], disabled: true })
  } else if (path.startsWith('/admin/benh-nhan/') && route.params.id) {
    list.push({ title: 'Quản lý bệnh nhân', to: '/benh-nhan' })
    list.push({ title: 'Chi tiết bệnh nhân', disabled: true })
  } else if (path.startsWith('/admin/bac-si/') && route.params.id) {
    list.push({ title: 'Quản lý bác sĩ', to: '/bac-si' })
    list.push({ title: 'Chi tiết bác sĩ', disabled: true })
  } else if (path.startsWith('/admin/lich-su-kham/')) {
    list.push({ title: 'Lịch sử khám bệnh', to: '/lich-su-kham' })
    list.push({ title: 'Chi tiết phiếu khám', disabled: true })
  }

  return list
})
</script>