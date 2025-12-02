<!-- src/components/ui/sheet.vue -->
<template>
  <div>
    <slot name="trigger"></slot>
    <div v-if="open" class="fixed inset-0 bg-black/30 z-40" @click="open = false"></div>
    <div v-if="open" class="fixed z-50" :class="sideClasses">
      <slot></slot>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, defineProps } from 'vue'

const props = defineProps({
  modelValue: { type: Boolean, default: false },
  side: { type: String, default: 'left' }
})

const open = ref(props.modelValue)

</script>
