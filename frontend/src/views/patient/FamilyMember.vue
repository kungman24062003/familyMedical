<template>
  <div class="mb-6 flex justify-between items-center">
    <h3 class="text-xl font-semibold">Quản lý gia đình</h3>
    <button class=" px-5 bg-teal-500 text-white font-bold py-2 rounded-lg hover:bg-teal-600 transition duration-150">Thêm thành viên</button>
  </div>

    <div class="flex flex-wrap gap-4">
      <FamilyMemberCard name="Add Member" isAdd />
      <FamilyMemberCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
      <FamilyMemberCard name="Tran Thi B" role="Spouse" lastCheckup="N/A" condition="None" />
      <FamilyMemberCard name="Nguyen Van C" role="Child" lastCheckup="2023-08-10" condition="Dust Mites" />
    </div>
</template>

<style scoped></style>
<script setup>
import FamilyMemberCard from '@/components/patient/FamilyMemberCard.vue'
</script>