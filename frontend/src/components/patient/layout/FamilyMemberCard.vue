<template>
  <div class="bg-white p-4 rounded-xl shadow-lg border border-gray-100">
    <div class="flex items-start justify-between">
      <div class="flex items-center">
        <div>
          <p class="font-semibold text-gray-800"> Nguyen Van A </p>
          <p class="text-xs text-gray-500">Relation </p>
        </div>
      </div>
      <span class="text-xs text-gray-400">LastCheckup</span>
    </div>

    <div class="mt-4 pt-3 border-t">
      <p class="text-xs text-gray-500 mb-1">Last Checkup</p>
      <div class="flex justify-between items-center">
        <span class="text-sm font-medium">lastCheckupText</span>
        <span :class="['text-sm font-bold p-1 rounded', conditionStyle]">Condition</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, computed } from 'vue';

const props = defineProps({
  member: Object
});

const conditionStyle = computed(() => {
  return 'text-gray-600 bg-gray-100';
});
</script>
