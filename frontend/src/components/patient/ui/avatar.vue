<!-- src/components/ui/avatar.vue -->
<template>
  <div class="relative w-10 h-10 rounded-full overflow-hidden bg-muted" :class="class">
    <slot />
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue'

const props = defineProps({
  class: { type: String, default: '' }
})
</script>
