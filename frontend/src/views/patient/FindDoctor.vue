<template>
  <div class="bg-gray-50 border border-gray-100 rounded-3xl p-8 max-w-4xl mx-auto shadow-md">

    <div class="mb-8">
      <h1 class="text-3xl sm:text-4xl font-extrabold text-gray-900 mb-2">
        Find a Specialist
      </h1>
      <p class="text-gray-600 text-lg">
        Connect with top doctors in your area. Send your family's medical records securely for remote consultation.
      </p>
    </div>

    <div class="flex flex-col sm:flex-row space-y-4 sm:space-y-0 sm:space-x-4">
      
      <div class="flex-grow relative">
        <input
          type="text"
          placeholder="Search by doctor name or location..."
          class="w-full pl-12 pr-4 py-4 border border-gray-200 rounded-xl focus:ring-teal-500 focus:border-teal-500 text-gray-700 placeholder-gray-500 shadow-sm transition-colors"
        >
        <svg class="absolute left-4 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
      </div>

      <div class="relative sm:w-1/4 min-w-[180px]">
        <div class="w-full bg-gray-100 py-4 px-4 border border-gray-200 rounded-xl cursor-pointer flex justify-between items-center text-gray-700 shadow-sm hover:bg-gray-200 transition-colors">
          <span>All Specialties</span>
          <svg class="w-5 h-5 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path></svg>
        </div>
      </div>

      <button class="sm:w-auto px-8 py-4 bg-teal-500 text-white font-bold rounded-xl shadow-lg shadow-teal-300/50 hover:bg-teal-600 transition-colors">
        Search
      </button>
    </div>
  </div>
</template>

<script setup>
// Trong component đơn giản này, không cần logic Vue nào
// nếu bạn chỉ hiển thị giao diện tĩnh.
</script>

<style scoped>
/* Không cần style scoped nếu chỉ dùng Tailwind */
</style>