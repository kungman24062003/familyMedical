<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps({
  variant: { type: String, default: 'default' },
  size: { type: String, default: 'default' }
})

const base = 'inline-flex items-center justify-center rounded-md font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50'

const variantClasses = computed(() => ({
  default: 'bg-primary text-white hover:bg-primary/90',
  destructive: 'bg-red-600 text-white hover:bg-red-700',
  outline: 'border bg-white hover:bg-gray-100',
  secondary: 'bg-gray-100 text-black hover:bg-gray-200',
  ghost: 'hover:bg-gray-50',
  link: 'text-primary underline'
})[props.variant])

const sizeClasses = computed(() => ({
  default: 'h-10 px-4',
  sm: 'h-8 px-3',
  lg: 'h-12 px-6',
  icon: 'h-10 w-10'
})[props.size])
</script>

<template>
  <button :class="[base, variantClasses, sizeClasses, $attrs.class]">
    <slot />
  </button>
</template>
