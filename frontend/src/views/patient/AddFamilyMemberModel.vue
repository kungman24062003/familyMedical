<template>
  <div 
    v-if="isOpen" 
    class="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50"
    @click.self="closeModal"
  >
    <div class="bg-white rounded-xl shadow-2xl w-full max-w-4xl transform transition-transform duration-300 scale-100">
      
      <div class="flex justify-between items-center p-6 border-b border-gray-100">
        <h3 class="text-xl font-semibold text-gray-800">
          Add Family Member
        </h3>
        <button @click="closeModal" class="text-gray-400 hover:text-gray-600">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
          </svg>
        </button>
      </div>

      <div class="p-6">
        <p class="text-gray-500 mb-6 text-sm">
          Create a new profile for a family member to track their health records.
        </p>

        <div class="grid grid-cols-2 gap-4">

          <!-- Full Name -->
          <div>
            <label for="fullname" class="text-sm font-medium text-gray-700 block mb-1">Full Name</label>
            <input 
              type="text" 
              id="fullname" 
              v-model="formData.fullname"
              placeholder="e.g., Tran Thi B"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500 placeholder-gray-400"
            >
          </div>

          <!-- Relation -->
          <div>
            <label for="relation" class="text-sm font-medium text-gray-700 block mb-1">Relation</label>
            <select 
              id="relation" 
              v-model="formData.relation"
              class="appearance-none w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500 text-gray-700 bg-white pr-10"
            >
              <option value="" disabled>Select relationship</option>
              <option value="CHONG">Spouse (Chồng)</option>
              <option value="VO">Wife (Vợ)</option>
              <option value="CON">Child (Con)</option>
              <option value="CHU_HO">Head of Household (Chủ hộ)</option>
            </select>
          </div>

          <!-- Date of Birth -->
          <div>
            <label for="dob" class="text-sm font-medium text-gray-700 block mb-1">Date of Birth</label>
            <input 
              type="date" 
              id="dob" 
              v-model="formData.date_of_birth"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500"
            >
          </div>

          <!-- Gender -->
          <div>
            <label for="gender" class="text-sm font-medium text-gray-700 block mb-1">Gender</label>
            <select 
              id="gender" 
              v-model="formData.gender"
              class="appearance-none w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500 text-gray-700 bg-white pr-10"
            >
              <option value="" disabled>Select gender</option>
              <option value="FEMALE">Female</option>
              <option value="MALE">Male</option>
              <option value="OTHER">Other</option>
            </select>
          </div>

          <!-- Address -->
          <div>
            <label for="address" class="text-sm font-medium text-gray-700 block mb-1">Address</label>
            <input 
              type="text" 
              id="address" 
              v-model="formData.address"
              placeholder="e.g., 123 Nguyen Trai"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500"
            >
          </div>

          <!-- ID Card -->
          <div>
            <label for="id_card" class="text-sm font-medium text-gray-700 block mb-1">ID Card</label>
            <input 
              type="text" 
              id="id_card" 
              v-model="formData.id_card"
              placeholder="e.g., 123456789"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500"
            >
          </div>

          <!-- Health Insurance -->
          <div>
            <label for="bhyt" class="text-sm font-medium text-gray-700 block mb-1">Health Insurance (BHYT)</label>
            <input 
              type="text" 
              id="bhyt" 
              v-model="formData.bhyt"
              placeholder="e.g., 123456789012"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500"
            >
          </div>

          <!-- Household ID -->
          <div>
            <label for="household_id" class="text-sm font-medium text-gray-700 block mb-1">Household ID</label>
            <input 
              type="number" 
              id="household_id" 
              v-model="formData.household_id"
              placeholder="e.g., 1"
              class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-teal-500 focus:border-teal-500"
            >
          </div>

        </div>
      </div>

      <div class="p-6 pt-0 flex justify-end">
        <button 
          @click="saveProfile"
          class="px-6 py-2 bg-teal-500 text-white font-semibold rounded-lg shadow-md hover:bg-teal-600 transition duration-150"
        >
          Save Profile
        </button>
      </div>

    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

const props = defineProps({
  isOpen: Boolean
});

const emit = defineEmits(['update:isOpen', 'save']);

const formData = reactive({
  fullname: '',
  relation: '',
  date_of_birth: '',
  gender: '',
  address: '',
  id_card: '',
  bhyt: '',
  household_id: null
});

const closeModal = () => {
  emit('update:isOpen', false);
};

const resetForm = () => {
  formData.fullname = '';
  formData.relation = '';
  formData.date_of_birth = '';
  formData.gender = '';
  formData.address = '';
  formData.id_card = '';
  formData.bhyt = '';
  formData.household_id = null;
};

const saveProfile = () => {
  console.log('Saving profile:', formData);
  emit('save', { ...formData });
  closeModal();
  resetForm();
};
</script>
