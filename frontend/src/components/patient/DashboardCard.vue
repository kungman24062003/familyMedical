<template>
  <div class="p-5 rounded-lg border text-card-foreground border-none shadow-md 
            bg-gradient-to-br from-pink-50 to-white 
            dark:from-pink-200/50 dark:to-white/70">
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
