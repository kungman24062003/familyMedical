<template>
  <div class="bg-white p-4 rounded-lg shadow">
    <p class="text-sm text-gray-500">{{ title }}</p>
    <p class="text-xl font-bold">{{ value }}</p>
    <p class="text-xs text-gray-400">{{ subtitle }}</p>
  </div>
</template>

<script setup>
defineProps({
  title: String,
  value: String,
  subtitle: String
})
</script>
