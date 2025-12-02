<template>
  <div class="flex-1 p-8 bg-gray-50 overflow-auto">
    <h2 class="text-2xl font-bold mb-6">Hello, A! 👋</h2>

    <!-- Cards -->
    <div class="grid grid-cols-3 gap-4 mb-6">
      <DashboardCard title="Next Appointment" value="Oct 24" subtitle="Dr. Le Van Minh • Cardiology" />
      <DashboardCard title="Last Vitals" value="120/80" subtitle="Blood Pressure • Normal" />
      <DashboardCard title="Family Members" value="3" subtitle="Active profiles monitored" />
    </div>

    <!-- Family Section -->
    <div class="mb-6 flex justify-between items-center">
      <h3 class="text-xl font-semibold">My Family</h3>
    </div>

    <div class="grid grid-cols-3 gap-2">
      <div class="grid grid-cols-2 col-span-2 gap-4">
        <FamilyMemberCard name="Add Member" isAdd />
        <FamilyMemberCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
        <FamilyMemberCard name="Tran Thi B" role="Spouse" lastCheckup="N/A" condition="None" />
        <FamilyMemberCard name="Nguyen Van C" role="Child" lastCheckup="2023-08-10" condition="Dust Mites" />
      </div>
      <div>
        <div class="space-y-6 max-w-sm">
        <div class="p-6 bg-green-50 rounded-lg border-t-4 border-green-200">
          <h3 class="text-lg font-semibold text-green-800 mb-2">Need a Doctor?</h3>
          <p class="text-sm text-green-700 mb-4">Find the best specialists near you for your family's health needs.</p>
          <button class="w-full bg-teal-700 text-white font-bold py-2 rounded-lg hover:bg-teal-700 transition duration-150">Find a Doctor</button>
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
          
          <button class="mt-4 w-full border border-gray-300 text-gray-700 font-medium py-2 rounded-lg hover:bg-gray-100 transition duration-150">View All Feedback</button>
        </div>
        
      </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import DashboardCard from '@/components/patient/DashboardCard.vue'
import FamilyMemberCard from '@/components/patient/FamilyMemberForm.vue'
</script>
