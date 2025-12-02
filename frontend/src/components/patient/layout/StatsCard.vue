<template>
  <div :class="['p-5 rounded-xl shadow-lg', cardClass]">
    <div class="flex justify-between items-start mb-2">
      <p class="text-gray-500 text-sm font-medium">{{ title }}</p>
      <span v-if="title === 'Family Members'" class="text-pink-400">❤️</span>
      <span v-else-if="title === 'Last Vitals'" class="text-purple-400">📈</span>
    </div>
    <p class="text-2xl font-bold mb-1">{{ value }}</p>
    <p class="text-xs text-gray-500">{{ subtitle }}</p>
  </div>
</template>

<script setup>
import { defineProps, computed } from 'vue';

const props = defineProps({
  title: String,
  value: [String, Number],
  subtitle: String,
  cardClass: {
    type: String,
    default: 'bg-white'
  }
});
</script>
