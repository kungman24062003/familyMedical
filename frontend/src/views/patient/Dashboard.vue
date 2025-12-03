<template>
    <h2 class="text-2xl font-bold mb-6">Hello, {{ patientName }} 👋</h2>

    <!-- Cards -->
    <div class="grid grid-cols-3 gap-4 mb-6">
      <DashboardCard title="Tổng số feedback đã nhận" :value="totalFeedback" />
      <DashboardCard title="Tổng số bác sĩ trong hệ thống" :value="totalDoctor" />
      <DashboardCard title="Số thành viên trong gia đình" :value="totalMember" />
    </div>

    <!-- Family Section -->
    <div class="mb-6 flex justify-between items-center">
      <h3 class="text-xl font-semibold">My Family</h3>
    </div>

    <div class="grid grid-cols-3 gap-2">
      <div class="grid grid-cols-2 col-span-2 gap-4">
        <FamilyMemberForm name="Add Member" isAdd />
        <FamilyMemberForm name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
        <FamilyMemberForm name="Tran Thi B" role="Spouse" lastCheckup="N/A" condition="None" />
        <FamilyMemberForm name="Nguyen Van C" role="Child" lastCheckup="2023-08-10" condition="Dust Mites" />
      </div>
      <div>
        <div class="space-y-6 max-w-sm">
        <div class="p-6 bg-green-50 rounded-lg border-t-4 border-green-200">
          <h3 class="text-lg font-semibold text-green-800 mb-2">Need a Doctor?</h3>
          <p class="text-sm text-green-700 mb-4">Find the best specialists near you for your family's health needs.</p>
          <button @click="goDoctor" class="w-full bg-teal-700 text-white font-bold py-2 rounded-lg hover:bg-teal-700 transition duration-150">Find a Doctor</button>
        </div>

        <div class="p-6 bg-white rounded-lg shadow-md">
          <h3 class="text-lg font-semibold text-gray-800 mb-4">Recent Feedback</h3>
          
          <div class="border-l-4 border-blue-500 pl-4 py-2 bg-blue-50 rounded-r-lg">
            <div class="flex items-center mb-1">
              <span class="w-2 h-2 rounded-full bg-blue-500 mr-2 flex-shrink-0"></span>
              <p class="font-medium text-gray-900 text-sm">Dr. Le Van Minh</p>
            </div>
            <p class="text-gray-600 text-sm italic line-clamp-2">Your blood pressure readings look stable. Keep taking the medication...</p>
          </div>
          
          <button @click="goFeedback" class="mt-4 w-full border border-gray-300 text-gray-700 font-medium py-2 rounded-lg hover:bg-gray-100 transition duration-150">View All Feedback</button>
        </div>
        
      </div>
      </div>
    </div>
</template>

<script setup lang="ts">
import DashboardCard from '@/components/patient/DashboardCard.vue'
import FamilyMemberForm from '@/components/patient/FamilyMemberForm.vue'
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue';
import api from '@/api';

const patientId = localStorage.getItem('patientId');

const patientName = ref('');
const patientEmail = ref('');

const totalFeedback = ref(0); 
const totalDoctor = ref(0); 
const totalMember = ref(0);

const householdId = ref(null);

const router = useRouter();
const goDoctor = () => router.push('/doctor');
const goFeedback = () => router.push('/feedback');

onMounted(async () => {
  if (!patientId) return;

  // 1. Lấy thông tin user
  try {
    const res = await api.get(`/users/${patientId}`);
    patientName.value = res.data.name;
    patientEmail.value = res.data.email;
  } catch (err) {
    console.error('Lỗi lấy thông tin người dùng:', err);
  }

  // 2. Lấy householdId từ backend
  try {
    const resHouse = await api.get(`/households/by-user/${patientId}`);
    householdId.value = resHouse.data.id;   // 🔥 householdId đúng đây
  } catch (err) {
    console.error("Lỗi lấy householdId:", err);
  }

  // 3. Lấy tổng feedback
  try {
    if (householdId.value) {
      const resRecords = await api.get(`/medical-records/household/${householdId.value}`);
      totalFeedback.value = resRecords.data.length;
    }
  } catch (err) {
    console.error("Lỗi lấy feedback:", err);
  }

  // 4. Đếm thành viên trong gia đình
  try {
    const resMembers = await api.get(`/members/household/${householdId.value}`);
    totalMember.value = resMembers.data.length;
  } catch (err) {
    console.error("Lỗi lấy member:", err);
  }

  // 5. Tổng số bác sĩ
  try {
    const resDoctor = await api.get(`/users/doctors`);
    totalDoctor.value = resDoctor.data.length;
  } catch (err) {
    console.error('Lỗi lấy bác sĩ:', err);
  }

});
</script>

