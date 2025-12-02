<!-- CardTitle.vue -->
<template>
  <h3 :class="['font-semibold', $attrs.class]">
    <slot />
  </h3>
</template>
<script setup lang="ts">
defineProps({
  class: String
})
</script>
