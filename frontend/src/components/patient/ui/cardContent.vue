<!-- CardContent.vue -->
<template>
  <div :class="['p-4', $attrs.class]">
    <slot />
  </div>
</template>
<script setup lang="ts">
defineProps({
  class: String
})
</script>
