<script setup>
defineProps({
  class: String
})
</script>

<template>
  <div :class="['rounded-lg border bg-white text-black shadow-sm', $attrs.class]">
    <slot />
  </div>
</template>
