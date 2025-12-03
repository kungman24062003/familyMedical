<template>
  <div 
    v-if="isOpen" 
    class="fixed inset-0 bg-black/20 flex items-center justify-center p-4 z-50"
    @click.self="closeModal"
  >
    <slot></slot>
  </div>
</template>

<script setup>
import { reactive, watch } from 'vue';

// Định nghĩa Props và Emits
const props = defineProps({
  isOpen: {
    type: Boolean,
    required: true
  }
});

const emit = defineEmits(['update:isOpen', 'save']);

// Dữ liệu Form
const formData = reactive({
  name: '',
  relation: '',
  age: null,
});

// Hàm đóng Modal
const closeModal = () => {
  emit('update:isOpen', false);
};

</script>
