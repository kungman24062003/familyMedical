<template>
  <section>
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-xl font-semibold text-gray-800">My Family</h2>
      <a href="#" class="text-sm text-teal-600 hover:text-teal-700 font-medium">View All →</a>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
      <div class="p-6 bg-gray-100 border-2 border-dashed border-gray-300 rounded-xl text-center flex flex-col items-center justify-center cursor-pointer hover:bg-gray-200 transition">
        <div class="w-12 h-12 rounded-full bg-white border flex items-center justify-center mb-2">
          <span class="text-2xl text-gray-500">+</span>
        </div>
        <p class="text-sm font-medium text-gray-600">Add Member</p>
      </div>

      <FamilyMemberCard v-for="member in familyMembers" :key="member.id" :member="member" />
    </div>
  </section>
</template>

<script setup>
import { ref } from 'vue';
import FamilyMemberCard from './FamilyMemberCard.vue';

const familyMembers = ref([
  { id: 1, name: 'Nguyen Van A', relation: 'Self (Primary)', lastCheckup: 'Jan 15, 2023', lastCheckupText: 'Conditions', condition: 'Hypertension', avatar: 'nvana.png' },
  { id: 2, name: 'Tran Thi B', relation: 'Spouse', lastCheckup: 'N/A', lastCheckupText: 'Allergies', condition: 'None', avatar: 'tranb.png' },
  { id: 3, name: 'Nguyen Van C', relation: 'Child', lastCheckup: '2023-08-10', lastCheckupText: 'Allergies', condition: 'Dust Mites', avatar: 'nguyenc.png' },
]);
</script>
