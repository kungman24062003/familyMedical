<!-- src/components/layout/AppSidebar.vue -->
<template>
  <v-navigation-drawer
    v-model="drawer"
    :temporary="temporary"
    width="280"
    class="bg-blue-grey-darken-4"
    theme="dark"
  >
    <v-list nav density="comfortable" class="py-3">
      <v-list-subheader class="text-overline text-grey-lighten-1 pl-6">
        QUẢN LÝ CHÍNH
      </v-list-subheader>

      <v-list-item to="/dashboard" prepend-icon="mdi-home" title="Tổng quan" class="mx-2" rounded="lg" />

      <!-- GROUP QUẢN LÝ HỘ GIA ĐÌNH -->
      <v-list-group :value="isHoGiaDinhOpen">
        <template v-slot:activator="{ props }">
          <v-list-item v-bind="props" prepend-icon="mdi-account-multiple" title="Quản lý hộ gia đình" class="mx-2 font-weight-bold" rounded="lg" />
        </template>
        <v-list-item to="/ho-gia-dinh/nhan-khau"  title="Quản lý nhân khẩu"      prepend-icon="mdi-account-box"       class="pl-12" />
        <v-list-item to="/ho-gia-dinh/ho-khau"   title="Quản lý hộ khẩu"        prepend-icon="mdi-hospital-box"      class="pl-12" />
        <v-list-item to="/ho-gia-dinh/giao-viec" title="Giao việc CSSK hộ GD"   prepend-icon="mdi-clipboard-check"   class="pl-12" />
      </v-list-group>

      <v-list-subheader class="text-overline text-grey-lighten-1 pl-6 mt-4">
        CÔNG VIỆC & BÁO CÁO
      </v-list-subheader>

      <v-list-item to="/ct-cssk"   title="Quản lý CTCSSK"      prepend-icon="mdi-file-document-multiple" class="mx-2" rounded="lg" />
      <v-list-item to="/thong-ke"  title="Thống kê - Báo cáo"   prepend-icon="mdi-chart-line"             class="mx-2" rounded="lg" />
    </v-list>
  </v-navigation-drawer>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()

const props = withDefaults(defineProps<{ modelValue?: boolean; temporary?: boolean }>(), { temporary: false })
const emit = defineEmits(['update:modelValue'])
const drawer = computed({
  get: () => props.modelValue ?? true,
  set: v => emit('update:modelValue', v)
})

const isHoGiaDinhOpen = computed(() => route.path.includes('/ho-gia-dinh'))
</script>

<style scoped>
.v-list-item--active {
  background: rgba(255,255,255,0.25) !important;
  color: white !important;
  font-weight: 600;
}
</style>