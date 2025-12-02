<!-- CardHeader.vue -->
<template>
  <div :class="['flex items-center justify-between', $attrs.class]">
    <slot />
  </div>
</template>
<script setup lang="ts">
defineProps({
  class: String
})
</script>
