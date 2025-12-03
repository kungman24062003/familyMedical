<template>
  <div class="bg-gray-50 border w-full border-gray-100 rounded-3xl p-8 shadow-md">

    <div class="mb-8">
      <h1 class="text-3xl sm:text-4xl font-extrabold text-gray-900 mb-2">
        Tìm chuyên gia
      </h1>
      <p class="text-gray-600 text-lg">
        Kết nối với các bác sĩ hàng đầu trong khu vực của bạn. Gửi hồ sơ bệnh án của gia đình bạn một cách an toàn để được tư vấn từ xa.
      </p>
    </div>

    <div class="flex flex-col sm:flex-row space-y-4 sm:space-y-0 sm:space-x-4">
      
      <div class="flex-grow relative">
        <input
          type="text"
          placeholder="Tìm kiếm theo tên bác sĩ hoặc địa điểm..."
          class="w-full pl-12 pr-4 py-4 border border-gray-200 rounded-xl focus:ring-teal-500 focus:border-teal-500 text-gray-700 placeholder-gray-500 shadow-sm transition-colors"
        >
        <svg class="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
      </div>

      <div class="relative sm:w-1/4 min-w-[180px]">
        <div class="w-full bg-gray-100 py-4 px-4 border border-gray-200 rounded-xl cursor-pointer flex justify-between items-center text-gray-700 shadow-sm hover:bg-gray-200 transition-colors">
          <span>Tất cả chuyên khoa</span>
          <svg class="w-5 h-5 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path></svg>
        </div>
      </div>

      <button class="sm:w-auto px-8 py-4 bg-teal-500 text-white font-bold rounded-xl shadow-lg shadow-teal-300/50 hover:bg-teal-600 transition-colors">
        Tìm kiếm
      </button>
    </div>
  </div>
  <div class="flex justify-between flex-wrap mt-5 gap-y-4">
      <DoctorCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
      <DoctorCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
      <DoctorCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
      <DoctorCard name="Nguyen Van A" role="Self (Admin)" lastCheckup="Jan 15, 2023" condition="Hypertension" />
  </div>
</template>

<script setup>
import DoctorCard from '@/components/patient/DoctorCard.vue';
</script>
