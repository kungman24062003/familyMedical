<!-- src/components/ui/AvatarImage.vue -->
<template>
  <img :src="src" alt="avatar" class="w-full h-full object-cover" />
</template>

<script setup lang="ts">
import { defineProps } from 'vue'
const props = defineProps<{ src: string }>()
</script>
